package com.project;

import com.project.context.ApplicationContextInjector;
import com.project.service.ExpositionService;

public class ApplicationRunner {
    public static void main(String[] args) {
//        DBConnector dbConnector = new DBConnector("database");

        ExpositionService expositionService = ApplicationContextInjector.getInstance().getExpositionService();

        System.out.println(expositionService.showByTitle("Moy"));


    }
}
