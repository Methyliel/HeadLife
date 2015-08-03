package Map;

import Interfaces.iLandscape;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SingleLands {
    private static SingleLands lands;
    private Map<String, iLandscape> landscapeExamples;

    private SingleLands() {
        this.landscapeExamples = new HashMap<String, iLandscape>();
        String catalogPath = System.getProperty("user.dir") + "/src/main/resources/images/landscapes/";
        String imagePath = catalogPath + "ground.png";
        try {
            Image landIcon = ImageIO.read(new File(imagePath));
            iLandscape land = new Ground();
            land.setImage(landIcon);
            this.landscapeExamples.put("Ground", land);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imagePath = catalogPath + "rock.png";
        try {
            Image landIcon = ImageIO.read(new File(imagePath));
            iLandscape land = new Rock();
            land.setImage(landIcon);
            this.landscapeExamples.put("Rock", land);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imagePath = catalogPath + "slime.png";
        try {
            Image landIcon = ImageIO.read(new File(imagePath));
            iLandscape land = new Slime();
            land.setImage(landIcon);
            this.landscapeExamples.put("Slime", land);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imagePath = catalogPath + "exit.png";
        try {
            Image landIcon = ImageIO.read(new File(imagePath));
            iLandscape land = new Exit();
            land.setImage(landIcon);
            this.landscapeExamples.put("Exit", land);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SingleLands instance() {
        if (null == SingleLands.lands) {
            SingleLands.lands = new SingleLands();
        }
        return SingleLands.lands;
    }
    public iLandscape getLand(String name) {
        return this.landscapeExamples.get(name);
    }
}
