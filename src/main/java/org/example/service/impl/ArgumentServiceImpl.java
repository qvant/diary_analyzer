package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.service.ArgumentService;
import org.example.service.OutputService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArgumentServiceImpl implements ArgumentService {
    private final OutputService outputService;

    @Override
    public Optional<String> getPath(String[] args) {
        if (args.length == 0) {
            outputService.show("No directory given");
            return Optional.empty();
        }
        return Optional.of(args[0]);
    }
}
