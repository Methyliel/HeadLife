package Objects;

import Interfaces.*;
import Logic.AbstractFactory;
import Map.SingleLands;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Factory extends AbstractFactory{
    @Override
    public iLandscape createLandscape(String landType) {
        iLandscape land = SingleLands.instance().getLand(landType);
        return land;
    }
    @Override
    public iGameObject createGameObject(String objectName, Point objectLocation) {
        String imagePath = System.getProperty("user.dir") + "/src/main/resources/images/objects/" + objectName + ".png";
        try {
            Image icon = ImageIO.read(new File(imagePath));
            int cellWidth = 40;
            iGameObject object = null;
            if ("AradiaBotRemains".equals(objectName)) {
                object = new AradiaBotRemains("AradiaBotRemains");
                ((iMoveable) object).setPassRights("walk");
                ((iAttack) object).setRange(4);
                ((iAttack) object).setDamage(3);
                ((iAttack) object).setHealthPoint(1);
            }
            if ("EctobiologicalBird".equals(objectName)) {
                object = new EctobiologicalBird("EctobiologicalBird");
                ((iMoveable) object).setPassRights("flying");
                ((iAttack) object).setRange(6);
                ((iAttack) object).setDamage(2);
                ((iAttack) object).setHealthPoint(2);
            }
            if ("EctobiologicalWorm".equals(objectName)) {
                object = new EctobiologicalWorm("EctobiologicalWorm");
                ((iMoveable) object).setPassRights("walk");
                ((iAttack) object).setRange(2);
                ((iAttack) object).setDamage(3);
                ((iAttack) object).setHealthPoint(4);
            }
            if ("Horn".equals(objectName)) {
                object = new Horn("Horn");
                ((iMoveable) object).setPassRights("pushed");
            }
            if ("Potion".equals(objectName)) {
                object = new Potion("Potion");
                ((iMoveable) object).setPassRights("pushed");
            }
            if (null != object) {
                ((iMoveable) object).setLocation(objectLocation);
                ((iDrawable) object).setImage(icon);
                int x = objectLocation.x * cellWidth;
                int y = objectLocation.y * cellWidth;
                ((iDrawable) object).setPixelLocation(new Point(x, y));
            }
            return object;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Head createHead(String name, Point objectLocation) {
        String imagePath = System.getProperty("user.dir") + "/src/main/resources/images/headsIcons/" + name + ".png";
        try {
            Image icon = ImageIO.read(new File(imagePath));
            int cellWidth = 40;
            Head head = null;
            if ("Feferi".equals(name)) {
                head = new Head("Feferi");
                head.setBloodColor(BloodColors.fuchsia);
                ((iAttack) head).setRange(1);
                ((iAttack) head).setDamage(2);
                ((iAttack) head).setHealthPoint(3);
            }
            if ("Eridan".equals(name)) {
                head = new Head("Eridan");
                head.setBloodColor(BloodColors.violet);
                ((iAttack) head).setRange(1);
                ((iAttack) head).setDamage(3);
                ((iAttack) head).setHealthPoint(2);
            }
            if ("Equius".equals(name)) {
                head = new Head("Equius");
                head.setBloodColor(BloodColors.indigo);
                ((iAttack) head).setRange(1);
                ((iAttack) head).setDamage(4);
                ((iAttack) head).setHealthPoint(5);
            }
            if ("Vriska".equals(name)) {
                head = new Head("Vriska");
                head.setBloodColor(BloodColors.cerulean);
                ((iAttack) head).setRange(1);
                ((iAttack) head).setDamage(3);
                ((iAttack) head).setHealthPoint(3);
            }
            if ("Nepeta".equals(name)) {
                head = new Head("Nepeta");
                head.setBloodColor(BloodColors.olive);
                ((iAttack) head).setRange(1);
                ((iAttack) head).setDamage(3);
                ((iAttack) head).setHealthPoint(4);
            }
            if ("Sollux".equals(name)) {
                head = new Head("Sollux");
                head.setBloodColor(BloodColors.yellow);
                ((iAttack) head).setRange(1);
                ((iAttack) head).setDamage(5);
                ((iAttack) head).setHealthPoint(3);
            }
            if ("Tavros".equals(name)) {
                head = new Head("Tavros");
                head.setBloodColor(BloodColors.bronze);
                ((iAttack) head).setRange(2);
                ((iAttack) head).setDamage(3);
                ((iAttack) head).setHealthPoint(3);
            }
            if (null != head) {
                ((iMoveable) head).setLocation(objectLocation);
                ((iMoveable) head).setPassRights("walk");
                ((iDrawable) head).setImage(icon);
                int x = objectLocation.x * cellWidth;
                int y = objectLocation.y * cellWidth;
                ((iDrawable) head).setPixelLocation(new Point(x, y));
            }
            return head;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
