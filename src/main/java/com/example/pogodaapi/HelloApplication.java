package com.example.pogodaapi;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Group root = new Group();
        TextField city = new TextField("Podaj nazwę miasta");
        root.getChildren().add(city);
        city.setMaxWidth(200);
        city.setLayoutY(30);
        city.setLayoutX(50);
        Button btnSubmit = new Button();
        Image search_icon = new Image("https://cdn3.iconfinder.com/data/icons/feather-5/24/search-512.png");
        ImageView searchIV = new ImageView(search_icon);
        searchIV.setFitHeight(16);
        searchIV.setFitWidth(16);
        btnSubmit.setGraphic(searchIV);
        btnSubmit.setLayoutY(30);
        btnSubmit.setLayoutX(200);
        root.getChildren().add(btnSubmit);

        Text main = new Text();
        main.setY(150);
        main.setX(50);
        main.setTextAlignment(TextAlignment.RIGHT);
        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String cityString = city.getText();
                JSONURL jsonurl = new JSONURL(cityString);

                String iconSource = jsonurl.getIcon();
                Image weatherIcon = new Image(iconSource);
                ImageView weatherIV = new ImageView(weatherIcon);
                weatherIV.setY(40);
                weatherIV.setX(100);
                weatherIV.setStyle("background-color: grey;");

                JSONObject jsonMain = jsonurl.getMain();
                String jsonRain ;
                if (jsonurl.getjOAll().has("rain")){
                    jsonRain =  jsonurl.getRain().getString("1h") + "mm";
                } else
                    jsonRain = "Brak opadow";
                String jsonSnow;
                if (jsonurl.getjOAll().has("Snow")){
                    jsonSnow =  jsonurl.getSnow().getString("1h") + "mm";
                } else
                    jsonSnow = "Brak opadow";
                JSONObject wind = jsonurl.getWind();
                WindDirection wd = new WindDirection(wind.getInt("deg"));
                String kierunek = wd.getKierunek();
                main.setText(
                        "Temperatura : " + jsonMain.get("temp") +"st. C\n"+
                        "Temperatura minimalna : " + jsonMain.get("temp_min") +"st. C\n"+
                        "Wilgotność : " + jsonMain.get("humidity") +"%\n"+
                        "Ciśnienie : " + jsonMain.get("pressure") +"hPa\n"+
                        "Temperatura Odczuwalna : " + jsonMain.get("feels_like") +"st. C\n"+
                        "Temperatura maksymalna : " + jsonMain.get("temp_max") +"st. C\n"+
                        "Widoczność : " + jsonurl.getVisibility() +" metrów(maks 10km)\n"+
                                "Prędkość wiatru : " + wind.get("speed") +"m/s\n"+
                                "Kierunek wiatru : " +  kierunek+"\n"+
                        "Opady deszczu : " + jsonRain +"\n"+
                        "Opady śniegu : " + jsonSnow +"\n"+
                        "Zachmurzenie : " + jsonurl.getClouds().get("all") +"%\n"+
                        "Nazwa : " + jsonurl.getName() +"\n"
                );



                root.getChildren().clear();

                root.getChildren().add(city);
                root.getChildren().add(btnSubmit);

                root.getChildren().add(weatherIV);

                root.getChildren().add(main);










            }
        });





        Scene scene = new Scene(root,300,600);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}