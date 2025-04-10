package org.example.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.repository.DiaryRepository;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@Slf4j
public class DiaryRepositoryLocal implements DiaryRepository {
    @Override
    public List<String> getDiariesList(String path) {
        try {
            Stream<Path> files = Files.list(Path.of(path));
            return files.filter(x -> !Files.isDirectory(x)).map(x -> String.valueOf(x)).filter(x -> x.endsWith(".md")).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Integer> getDiaryStats(String path) {
        log.debug(path);
        HashMap<String, Integer> dayAchievements = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(path)));
            String line;
            while (!(line = bufferedReader.readLine()).isEmpty()) {
                int separator = line.indexOf('-');
                if (separator < 0) {
                    continue;
                }
                String item = line.substring(0, separator);
                String numberPart = line.substring(separator + 1);
                try {
                    int value = Integer.parseInt(numberPart.trim());
                    if (value == 1) {
                        dayAchievements.put(item, 1);
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return dayAchievements;
    }
}
