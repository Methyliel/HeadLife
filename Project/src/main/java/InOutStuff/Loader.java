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
                Map<Point, iLandscape> map = null;
                List<iGameObject> objects = new ArrayList<iGameObject>();
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
                        Map<Point, String> levelMap = new HashMap<Point, String>();
                        jsonReader.beginArray();
                        //Визначаємо кількість клітинок на карті
                        int arrayBorder = roomLength * roomWidth;
                        //Задаємо номер рядка(координату по осі ОУ) і стовбчика(координату по осі ОХ) для клитинки
                        int cellColumn = 0, cellRow = 1;
                        if (arrayBorder > 0) {
                            //Ширина кімнати - це кількість клітинок по осі ОХ
                            for (int x = 0; x < roomWidth; x++) {
                                //Довжина кімнати - це кількість клітинок по осі ОУ
                                for (int y = 0; y < roomLength; y++) {
                                    Point cellLocation = new Point(x, y);
                                    levelMap.put(cellLocation, jsonReader.nextString());
                                }
                            }
                            //Приводимо карту в підходящий вигляд
                            map = this.convertMap(levelMap,  roomWidth, roomLength);
                            //Якщо карта була зібрана неправильно, повертаємо помилку
                            if (null == map) {
                                return new Exception("Wrong data in file " + levelFilePath + " at attribute " + name);
                            }

                            //Написати обробку об'єктів розташованих на карті(шо я мала наувазі? треба подумати)
                            
                        }
                        //Якщо кількість клітинок не додатнє число, то повертаємо помилку
                        else {
                            return new Exception("Wrong structure at file " + levelFilePath);
                        }
                        jsonReader.endArray();
                        attributeNameFound = true;
                    }
                    if ("objects".equals(name)) {
                        jsonReader.beginArray();
                        //Файл читається до кінця
                        int counter = 0;
                        AbstractFactory factory = new Factory();
                        String objectName = "";
                        int x = 0, y = 0;
                        while (jsonReader.hasNext()) {
                            //Кожен перший елемент - ім'я об'єкту
                            if (0 == counter) {
                                objectName = jsonReader.nextString();
                            }
                            //Другий - координата по ОХ
                            if (1 == counter) {
                                x = jsonReader.nextInt();
                            }
                            //Третій - координата по ОУ
                            if (2 == counter) {
                                y = jsonReader.nextInt();
                            }
                            //Перевіряємо чи є достатньо данних щоб створити об'єкт
                            if ((!"".equals(objectName)) && (0 != x) && (0 != y)) {
                                //Перевіряємо чи не виходять координати за діапазон
                                if (x < roomWidth && y < roomLength && x >= 0 && y >= 0) {
                                    //Створюємо об'єкт і перевіряємо його
                                    iGameObject obj = factory.createGameObject(objectName, new Point(x, y));
                                    if (null == obj) {
                                        return new Exception("Wrong data in file " + levelFilePath + " at attribute " + name);
                                    }
                                    else {
                                        objects.add(obj);
                                    }
                                }
                                //Інакше, повертаємо помилку
                                else {
                                    return new Exception("Wrong data in file " + levelFilePath + " at attribute " + name);
                                }
                            }
                            //Інакше, повертаємо помилку
                            else {
                                return new Exception("Wrong structure at file " + levelFilePath);
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
                        attributeNameFound = true;
                    }
                    //Якщо не передбачена обробка поточного поля, повертаємо помилку
                    if (!attributeNameFound) {
                        return new Exception("Wrong structure at file " + levelFilePath);
                    }
                }
                jsonReader.endObject();
                if (null != map && null != objects) {
                    this.currentLevel = new Level(map, objects);
                }
                else {
                    return new Exception("Wrong structure at file " + levelFilePath);
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
    private Map<Point, iLandscape> convertMap(Map<Point, String> levelMap, int width, int lenght) {
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
