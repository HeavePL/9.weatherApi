package com.example.pogodaapi;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {




        JSONURL jsonurl = new JSONURL("Kostrzyn");
        System.out.println(jsonurl.getjOAll());


    }

    public static void main(String[] args) {
        launch();
    }
}