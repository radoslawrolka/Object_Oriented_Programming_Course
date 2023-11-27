package agh.ics.oop.presenter;

import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldElement;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.model.util.MapVisualizer;
import javafx.scene.control.Label;
import javafx.fxml.FXML;


public class SimulationPresenter implements MapChangeListener {
    private WorldMap<WorldElement, Vector2d> map;

    @FXML
    private Label infoLabel;

    public void setWorldMap(WorldMap<WorldElement, Vector2d> map) {
        this.map = map;
    }

    public void drawMap() {
        infoLabel.setText(map.toString());
    }

    @Override
    public void mapChanged(WorldMap map, String message) {
        setWorldMap(map);
        drawMap();
    }
}
