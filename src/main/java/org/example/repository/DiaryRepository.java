package org.example.repository;

import java.util.List;
import java.util.Map;

public interface DiaryRepository {
    List<String> getDiariesList(String path);

    Map<String, Integer> getDiaryStats(String path);
}
