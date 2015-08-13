import Interfaces.iGameObject;
import Interfaces.iLandscape;
import Interfaces.iMoveable;
import Logic.Generator;
import Map.Level;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GeneratorTest {
    public static void main() {
        int width = 4;
        int lenght = 5;
        List<String> landscapeTypes = new ArrayList<String>();
        landscapeTypes.add("Ground");
        landscapeTypes.add("Rock");
        landscapeTypes.add("Slime");
        List<String> objectTypes = new ArrayList<String>();
        objectTypes.add("Potion");
        objectTypes.add("Horn");
        int objectNumber = 3;
        List<String> enemyTypes = new ArrayList<String>();
        enemyTypes.add("AradiaBotRemains");
        enemyTypes.add("EctobiologicalBird");
        enemyTypes.add("EctobiologicalWorm");
        int enemyNumber = 2;
        Point startPoint = new Point(1, 1);
        Generator generator = new Generator();
        Exception ex = generator.generateLevel(width, lenght, landscapeTypes, objectTypes, objectNumber,
                enemyTypes, enemyNumber, startPoint);
        if (null == ex) {
            Level level = generator.getGameLevel();
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < lenght; j++) {
                    iLandscape land = level.getMap().get(new Point(i, j));
                    System.out.print(land.getLandType());
                }
                System.out.print("\n");
            }
            List<iGameObject> objects = level.getGameObjects();
            for (int i = 0; i < objects.size(); i++) {
                if (objects.get(i) instanceof iMoveable) {
                    System.out.println(objects.get(i).getName() + " [" +
                    Integer.toString(((iMoveable) objects.get(i)).getLocation().x) + ", " +
                    Integer.toString(((iMoveable) objects.get(i)).getLocation().y) + "]");
                }
            }
        }
    }
}
