package Map;

import Interfaces.iGameObject;
import Interfaces.iLandscape;

import java.awt.*;
import java.util.Map;
import java.util.List;

public class Level {
    private Map<Point, iLandscape> map;
    private List<iGameObject> gameObjects;

    public Level(Map<Point, iLandscape> levelMap, List<iGameObject> objectList) {
        this.map = levelMap;
        this.gameObjects = objectList;
    }
    public Map<Point, iLandscape> getMap() {
        return this.map;
    }
    public List<iGameObject> getGameObjects() {
        return this.gameObjects;
    }
}
