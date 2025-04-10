package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.repository.DiaryRepository;
import org.example.service.AnalyzerService;
import org.example.service.OutputService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AnalyzerServiceImpl implements AnalyzerService {
    private final OutputService outputService;
    private final DiaryRepository diaryRepository;

    @Override
    public void analyze(String path) {

        HashMap<String, Integer> dayAchievements = new HashMap<>();
        List<String> diariesPaths = diaryRepository.getDiariesList(path);
        for (String diaryPath : diariesPaths) {
            var oneDay = diaryRepository.getDiaryStats(diaryPath);
            oneDay.keySet().stream().map(x -> dayAchievements.merge(x, 1, (oldValue, newValue) -> oldValue + newValue)).collect(Collectors.toSet());
        }
        dayAchievements.keySet().stream().forEach(x -> outputService.show(x + dayAchievements.get(x)));
    }
}
