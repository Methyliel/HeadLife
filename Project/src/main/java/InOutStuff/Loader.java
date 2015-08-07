package InOutStuff;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import Interfaces.iGameObject;
import Interfaces.iLandscape;
import Logic.AbstractFactory;
import Map.Level;
import Objects.Factory;
import com.google.gson.stream.JsonReader;

public class Loader {
    private Map<String, String> levelsList;
    private Level currentLevel;

    public Loader(File levelListFile) {
        this.levelsList = null;
        Map<String, String> levels = new HashMap<String, String>();
        try {
            JsonReader jsonReader = new JsonReader(new FileReader(levelListFile));
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String levelName = jsonReader.nextName();
                String levelPath = jsonReader.nextString();
                levels.put(levelName, levelPath);
            }
            jsonReader.endObject();
            this.levelsList = levels;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Exception load(String levelName) {
        //Спочатку перевіряємо чи в списку файлів з рівнями є заданий файл
        if (this.levelsList.containsKey(levelName)) {
            //Якщо в списку файлів з рівнями є заданий файл, формуємо строку зі шляхом до нього
            String levelFilePath = System.getProperty("user.dir") + this.levelsList.get(levelName);
            try {
                //Намагаємось відкрити заданий файл
                JsonReader jsonReader = new JsonReader(new FileReader(new File(levelFilePath)));
                Map<Point, iLandscape> levelMap = null;
                List<iGameObject> gameObjectsAtLevel = null;
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    String name = jsonReader.nextName();
                    //Данна змінна потрібна щоб слідкувати чи не з'явиться в файлі якесь нове поле з невідомим ім'ям
                    boolean attributeNameFound = false;
                    int roomWidth = 0;
                    if ("width".equals(name)) {
                        roomWidth = jsonReader.nextInt();
                        //Якщо значення, що описує ширину кімнати не додатнє, то повертаємо помилку
                        if (roomWidth >= 0) {
                            return new Exception("Wrong data in file " + levelFilePath + " at attribute " + name);
                        }
                        attributeNameFound = true;
                    }
                    int roomLength = 0;
                    if ("length".equals(name)) {
                        roomLength = jsonReader.nextInt();
                        //Якщо значення, що описує довжину кімнати не додатнє, то повертаємо помилку
                        if (roomLength >= 0) {
                            return new Exception("Wrong data in file " + levelFilePath + " at attribute " + name);
                        }
                        attributeNameFound = true;
                    }
                    if ("roomMap".equals(name)) {
                        levelMap = this.readMap(jsonReader, roomWidth, roomLength);
                        if (null == levelMap) {
                            return new Exception("Error in file " + levelFilePath + " at attribute " + name);
                        }
                        attributeNameFound = true;
                    }
                    if ("objects".equals(name)) {
                        gameObjectsAtLevel = this.readObjects(jsonReader, roomWidth, roomLength);
                        if (null == gameObjectsAtLevel) {
                            return new Exception("Error in file " + levelFilePath + " at attribute " + name);
                        }
                        attributeNameFound = true;
                    }
                    //Якщо не передбачена обробка поточного поля, повертаємо помилку
                    if (!attributeNameFound) {
                        return new Exception("Wrong structure at file " + levelFilePath);
                    }
                }
                jsonReader.endObject();

                if (null != levelMap && null != gameObjectsAtLevel) {
                    this.currentLevel = new Level(levelMap, gameObjectsAtLevel);
                }
                else {
                    return new Exception("Some error. Don't know what exactly. Probably, never showed up.");
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //Якщо ми не можемо відкрити заданий файл, повертаємо помилку
                return new Exception("Missing level file with given name " + levelName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        else {
            //Якщо заданого файлу немає в списку файлів з рівнями, повертаємо помилку
            return new Exception("Wrong level name " + levelName);
        }
    }
    private Map<Point, iLandscape> readMap(JsonReader jsonReader, int WIDTH, int LENGHT) throws IOException {
        Map<Point, String> levelMap = new HashMap<Point, String>();
        Map<Point, iLandscape> map = null;
        jsonReader.beginArray();
        //Визначаємо кількість клітинок на карті
        int arrayBorder = LENGHT * WIDTH;
        if (arrayBorder > 0) {
            //Ширина кімнати - це кількість клітинок по осі ОХ
            for (int x = 0; x < WIDTH; x++) {
                //Довжина кімнати - це кількість клітинок по осі ОУ
                for (int y = 0; y < LENGHT; y++) {
                    Point cellLocation = new Point(x, y);
                    levelMap.put(cellLocation, jsonReader.nextString());
                }
            }
            //Приводимо карту в підходящий вигляд
            map = this.convertMap(levelMap);
        }
        jsonReader.endArray();
        return map;
    }
    private List<iGameObject> readObjects(JsonReader jsonReader, int WIDTH, int LENGHT) throws IOException {
        List<iGameObject> gameObjectsAtLevel = new ArrayList<iGameObject>();
        jsonReader.beginArray();
        int counter = 0;
        String objectName = "";
        int x = 0, y = 0;
        AbstractFactory factory = new Factory();
        while (jsonReader.hasNext()) {
            if (0 == counter) {
                objectName = jsonReader.nextString();
            }
            if (1 == counter) {
                x = jsonReader.nextInt();
                if (x >= WIDTH) {
                    return null;
                }
            }
            if (2 == counter) {
                y = jsonReader.nextInt();
                if (y >= LENGHT) {
                    return null;
                }
                iGameObject object = factory.createGameObject(objectName, new Point(x, y));
                if (null != object) {
                    gameObjectsAtLevel.add(object);
                }
                else {
                    return null;
                }
            }
            if (2 == counter) {
                counter = 0;
                x = 0;
                y = 0;
                objectName = "";
            }
            else {
                counter++;
            }
        }
        jsonReader.endArray();
        return gameObjectsAtLevel;
    }
    public Map<Point, iLandscape> convertMap(Map<Point, String> levelMap) {
        Map<Point, iLandscape> newLevelMap = new HashMap<Point, iLandscape>();
        AbstractFactory factory = new Factory();
        for (Map.Entry<Point, String> entry : levelMap.entrySet()) {
            Point key = entry.getKey();
            String value = entry.getValue();
            iLandscape object = factory.createLandscape(value);
            if (null == object) {
                return null;
            }
            else {
                newLevelMap.put(key, object);
            }
        }
        return newLevelMap;
    }
    public Level getLevel() {
        return this.currentLevel;
    }
}
