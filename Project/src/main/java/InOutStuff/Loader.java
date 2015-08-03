package InOutStuff;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Interfaces.iLandscape;
import Map.Level;
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
                            Map<Point, iLandscape> map = this.convertMap(levelMap);
                            //Якщо карта була зібрана неправильно, повертаємо помилку
                            if (null == map) {
                                return new Exception("Wrong data in file " + levelFilePath + " at attribute " + name);
                            }
                            //Записуємо отриману карту в поле об'екта класу Level

                            //Написати обробку об'єктів розташованих на карті
                            
                        }
                        //Якщо кількість клітинок не додатнє число, то повертаємо помилку
                        else {
                            return new Exception("Wrong structure at file " + levelFilePath);
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
    //Після написання фабрики, дописати метод
    private Map<Point, iLandscape> convertMap(Map<Point, String> levelMap) {
        return null;
    }
    public Level getLevel() {
        return this.currentLevel;
    }
}
