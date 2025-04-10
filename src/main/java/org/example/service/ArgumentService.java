package org.example.service;

import java.util.Optional;

public interface ArgumentService {
    Optional<String> getPath(String[] args);
}
