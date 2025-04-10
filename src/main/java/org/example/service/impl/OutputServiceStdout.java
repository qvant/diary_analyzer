package org.example.service.impl;

import org.example.service.OutputService;
import org.springframework.stereotype.Service;

@Service
public class OutputServiceStdout implements OutputService {


    @Override
    public void show(String message) {
        System.out.println(message);
    }
}
