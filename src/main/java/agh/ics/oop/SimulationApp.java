package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SimulationApp extends Application {
    public void start(Stage primaryStage) throws IOException {
        List<MoveDirection> mov = List.of(MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD);
        List<Vector2d> pos = List.of(new Vector2d(0,0), new Vector2d(1,0), new Vector2d(2,0));
        AbstractWorldMap map = new GrassField(10);
        Simulation simulation = new Simulation(mov, pos, map);
        ConsoleMapDisplay dis = new ConsoleMapDisplay();
        map.addObserver(dis);
        SimulationEngine engine = new SimulationEngine(List.of(simulation));


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();
        SimulationPresenter presenter = loader.getController();

        configureStage(primaryStage, viewRoot);

        map.addObserver(presenter);
        presenter.setWorldMap(map);
        engine.runSync();
        primaryStage.show();
    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
