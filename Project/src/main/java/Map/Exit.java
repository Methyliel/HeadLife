package Map;

import Interfaces.iGameObject;
import Interfaces.iLandscape;
import Interfaces.iMoveable;
import Objects.Head;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Exit implements iLandscape {
    private Image image;
    private final String TYPE;

    public Exit() {
        this.TYPE = "Ground";
        Image img = null;
        try {
            img = ImageIO.read(new File(System.getProperty("user.dir") + "/src/main/resources/exit/ground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setImage(img);
    }
    @Override
    public boolean canMove(iMoveable object) {
        if (object instanceof Head) {
            return true;
        }
        return false;
    }
    @Override
    public String getLandType() {
        return this.TYPE;
    }
    @Override
    public void setImage(Image image) {
        if (null != image) {
            this.image = image;
        }
    }
    @Override
    public Image getImage() {
        return this.image;
    }
}
