package agh.ics.oop.presenter;

import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationApp;
import agh.ics.oop.model.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.List;


public class SimulationPresenter implements MapChangeListener {
    private WorldMap<WorldElement, Vector2d> map;
    @FXML
    private GridPane mapGrid;
    @FXML
    private TextField moveListTextField;
    @FXML
    private Label moveDescriptionLabel;

    private int xMin;
    private int yMin;
    private int xMax;
    private int yMax;
    private  int mapWidth;
    private  int mapHeight;

    private final int width = 50;
    private final int height = 50;

    public void setWorldMap(WorldMap<WorldElement, Vector2d> map) {
        this.map = map;
    }

    public void xyLabel(){
        mapGrid.getColumnConstraints().add(new ColumnConstraints(width));
        mapGrid.getRowConstraints().add(new RowConstraints(height));
        Label label = new Label("y/x");
        mapGrid.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.CENTER);
    }

    public void updateBounds(){
        xMin = map.getCurrentBounds().lowerLeft().getX();
        yMin = map.getCurrentBounds().lowerLeft().getY();
        xMax = map.getCurrentBounds().upperRight().getX();
        yMax = map.getCurrentBounds().upperRight().getY();
        mapWidth = xMax - xMin + 1;
        mapHeight = yMax - yMin + 1;
    }

    public void columnsFunction(){
        for (int i = 0; i < mapWidth; i++){
            Label label = new Label(Integer.toString(xMin+i));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getColumnConstraints().add(new ColumnConstraints(width));
            mapGrid.add(label, i+1, 0);
        }
    }

    public void rowsFunction(){
        for (int i = 0; i < mapHeight; i++){
            Label label = new Label(Integer.toString(yMax-i));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getRowConstraints().add(new RowConstraints(height));
            mapGrid.add(label, 0, i+1);
        }
    }

    public void addElements() {
        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMax; j >= yMin; j--) {
                Vector2d pos = new Vector2d(i,j);
                if(map.isOccupied(pos)){
                    mapGrid.add(new Label(map.objectAt(pos).toString()),i-xMin+1,yMax-j+1);
                }
                else {
                    mapGrid.add(new Label(" "),i-xMin+1,yMax-j+1);
                }
                mapGrid.setHalignment(mapGrid.getChildren().get(mapGrid.getChildren().size()-1), HPos.CENTER);
            }
        }
    }

    private void drawMap() {
        updateBounds();
        xyLabel();
        columnsFunction();
        rowsFunction();
        addElements();
        mapGrid.setGridLinesVisible(true);
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    @Override
    public void mapChanged(WorldMap map, String message) {
        setWorldMap(map);
        Platform.runLater(() -> {
            clearGrid();
            drawMap();
            moveDescriptionLabel.setText(message);
        });
    }

    @FXML
    private void startSimulation() {
        String moveList = moveListTextField.getText();
        List<MoveDirection> mov = OptionParser.parse(moveList.split(" "));
        List<Vector2d> pos = List.of(new Vector2d(5,0), new Vector2d(1,1), new Vector2d(-2,0));
        AbstractWorldMap map = new GrassField(10);
        map.addObserver(this);
        map.addObserver((worldmap, mes) -> System.out.println(worldmap.getId() + ": " + mes));
        Simulation simulation = new Simulation(mov, pos, map);
        SimulationEngine engine = new SimulationEngine(List.of(simulation));
        moveDescriptionLabel.setText("Simulation started with moves: " + moveList);
        new Thread(() -> {
            engine.runSync();
        }).start();

    }

    @FXML
    private void newGame() {
        SimulationApp simulationApp = new SimulationApp();
        try {
            simulationApp.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
