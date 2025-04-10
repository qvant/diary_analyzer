package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.service.AnalyzerService;
import org.example.service.ArgumentService;
import org.example.service.ReportBuilderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportBuilderServiceImpl implements ReportBuilderService {
    private final AnalyzerService analyzerService;
    private final ArgumentService argumentService;

    @Override
    public void build(String[] args) {
        Optional<String> path = argumentService.getPath(args);
        if (path.isPresent()) {
            analyzerService.analyze(path.get());
        }
    }
}