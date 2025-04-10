package org.example;

import lombok.RequiredArgsConstructor;
import org.example.service.ReportBuilderService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
@RequiredArgsConstructor
public class DiaryAnalyzerApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiaryAnalyzerApp.class);
        ReportBuilderService reportBuilderService = context.getBean(ReportBuilderService.class);
        reportBuilderService.build(args);
    }
}
