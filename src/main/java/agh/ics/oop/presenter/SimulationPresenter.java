package agh.ics.oop.presenter;

import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import agh.ics.oop.model.util.MapVisualizer;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.fxml.FXML;

import java.util.List;


public class SimulationPresenter implements MapChangeListener {
    private WorldMap<WorldElement, Vector2d> map;
    @FXML
    private Label infoLabel;
    @FXML
    private TextField moveListTextField;
    @FXML
    private Label moveDescriptionLabel;

    public void setWorldMap(WorldMap<WorldElement, Vector2d> map) {
        this.map = map;
    }

    public void drawMap(WorldMap map) {
        infoLabel.setText(map.toString());
    }

    @Override
    public void mapChanged(WorldMap map, String message) {
        setWorldMap(map);
        Platform.runLater(() -> {
            drawMap(map);
            moveDescriptionLabel.setText(message);
        });

    }

    @FXML
    private void startSimulation() {
        String moveList = moveListTextField.getText();
        List<MoveDirection> mov = OptionParser.parse(moveList.split(" "));
        List<Vector2d> pos = List.of(new Vector2d(0,0), new Vector2d(1,0), new Vector2d(2,0));
        AbstractWorldMap map = new GrassField(10);
        map.addObserver(this);
        Simulation simulation = new Simulation(mov, pos, map);
        SimulationEngine engine = new SimulationEngine(List.of(simulation));
        moveDescriptionLabel.setText("Simulation started with moves: " + moveList);
        new Thread(() -> {
            engine.runSync();
        }).start();

    }
}
