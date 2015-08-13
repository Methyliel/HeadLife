package Logic;

import Interfaces.iGameObject;
import Interfaces.iLandscape;
import Interfaces.iMoveable;
import Map.Level;
import Objects.Factory;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Generator {
    private Level gameLevel;
    private int WIDTH;
    private int LENGHT;

    public Exception generateLevel(int width, int lenght, List<String> landscapeTypes, List<String> objectTypes,
                                   int objectNumber, List<String> enemyTypes, int enemyNumber, Point startPoint) {
        if (width > 0) {
            this.WIDTH = width;
        }
        else {
            return new Exception("Wrong format of data");
        }
        if (lenght > 0) {
            this.LENGHT = lenght;
        }
        else {
            return new Exception("Wrong format of data");
        }
        Map<Point, iLandscape> levelMap = this.generateMap(landscapeTypes, startPoint);
        if (null == levelMap) {
            return new Exception("Can't create a map");
        }
        List<iGameObject> levelObjects = this.generateObjectsList(objectTypes, objectNumber,
                                                                    enemyTypes, enemyNumber, levelMap, startPoint);
        if (null == levelObjects) {
            return new Exception("Can't create game objects");
        }
        this.gameLevel = new Level(levelMap, levelObjects);
        return null;
    }
    private Map<Point, iLandscape> generateMap(List<String> landscapeTypes, Point startPoint) {
        int landscapeNumber = landscapeTypes.size();
        Map<Point, String> mapPrevious = new HashMap<Point, String>();
        for (int x = 0; x < this.WIDTH; x++) {
            for (int y = 0; y < this.LENGHT; y++) {
                int landscapeIndex = (int) (Math.random() * landscapeNumber);
                String landscapeName = landscapeTypes.get(landscapeIndex);
                if (null == landscapeName || "".equals(landscapeName)) {
                    return null;
                }
                mapPrevious.put(new Point(x, y), landscapeName);
            }
        }
        Map<Point, String> mapNew = mapPrevious;
        for (int step = 0; step < 20; step++) {
            for (int x = 0; x < this.WIDTH; x++) {
                for (int y = 0; y < this.LENGHT; y++) {
                    int landscapeArray[] = new int[landscapeNumber];
                    for (int i = 0; i < landscapeNumber; i++) {
                        landscapeArray[i] = 0;
                    }
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            Point newPoint = new Point(x + i, y + j);
                            String land = mapPrevious.get(newPoint);
                            if (null == land || "".equals(land)) {
                                return null;
                            }
                            int landscapeCounter = landscapeTypes.indexOf(land);
                            if (-1 == landscapeCounter) {
                                return null;
                            }
                            landscapeArray[landscapeCounter] += 1;
                        }
                    }
                    int max = landscapeArray[0], index = 0;
                    for (int i = 0; i < landscapeNumber; i++) {
                        if (landscapeArray[i] > max) {
                            max = landscapeArray[i];
                            index = i;
                        }

                    }
                    mapNew.put(new Point(x, y), landscapeTypes.get(index));
                }
            }
            mapPrevious = mapNew;
        }
        mapNew.put(startPoint, landscapeTypes.get(0));
        Map<Point, iLandscape> levelMap = new HashMap<Point, iLandscape>();
        AbstractFactory factory = new Factory();
        for (int i = 0; i < this.WIDTH; i++) {
            for (int j = 0; j < this.LENGHT; j++) {
                iLandscape land = factory.createLandscape(mapNew.get(new Point(i, j)));
                if (null == land) {
                    return null;
                }
                levelMap.put(new Point(i, j), land);
            }
        }

        return levelMap;
    }
    private List<iGameObject> generateObjectsList(List<String> objectTypes,int objectNumber, List<String> enemyTypes,
                                                  int enemyNumber, Map<Point, iLandscape> levelMap, Point startPoint) {
        List<iGameObject> objects = new ArrayList<iGameObject>();
        AbstractFactory factory = new Factory();
        for (int i = 0; i < objectNumber; i++) {
            int x = (int) (Math.random() * this.WIDTH);
            int y = (int) (Math.random() * this.LENGHT);
            Point location = new Point(x, y);
            int index = (int) (Math.random() * objectTypes.size());
            iGameObject object = factory.createGameObject(objectTypes.get(index), location);
            int k = 0;
            boolean notDone = this.cheakPoint(objects, levelMap, startPoint, object);
            while (notDone || k < 10) {
                int newX = (int) (Math.random() * this.WIDTH);
                int newY = (int) (Math.random() * this.LENGHT);
                Point newLocation = new Point(x, y);
                if (object instanceof iMoveable) {
                    ((iMoveable) object).setLocation(newLocation);
                }
                notDone = this.cheakPoint(objects, levelMap, startPoint, object);
                k++;
            }
            if (notDone) {
                objects.add(object);
            }
            else {
                return null;
            }
        }
        for (int i = 0; i < enemyNumber; i++) {
            int x = (int) (Math.random() * this.WIDTH);
            int y = (int) (Math.random() * this.LENGHT);
            Point location = new Point(x, y);
            int index = (int) (Math.random() * enemyTypes.size());
            iGameObject object = factory.createGameObject(enemyTypes.get(index), location);
            int k = 0;
            boolean notDone = this.cheakPoint(objects, levelMap, startPoint, object);
            while (notDone || k < 10) {
                int newX = (int) (Math.random() * this.WIDTH);
                int newY = (int) (Math.random() * this.LENGHT);
                Point newLocation = new Point(x, y);
                if (object instanceof iMoveable) {
                    ((iMoveable) object).setLocation(newLocation);
                }
                notDone = this.cheakPoint(objects, levelMap, startPoint, object);
                k++;
            }
            if (notDone) {
                objects.add(object);
            }
            else {
                return null;
            }
        }
        return objects;
    }
    private boolean cheakPoint(List<iGameObject> objects, Map<Point, iLandscape> levelMap,
                               Point startPoint, iGameObject object) {
        if (object instanceof iMoveable) {
            Point location = ((iMoveable) object).getLocation();
            if (location.equals(startPoint)) {
                return false;
            }
            iLandscape land = levelMap.get(location);
            if (!land.canMove((iMoveable) object)) {
                return false;
            }
            for (int i = 0; i < objects.size(); i++) {
                if (objects.get(i) instanceof iMoveable) {
                    Point objectLocation = ((iMoveable) objects.get(i)).getLocation();
                    if (location.equals(objectLocation)) {
                        return false;
                    }
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }
    public Level getGameLevel() {
        return this.gameLevel;
    }
}