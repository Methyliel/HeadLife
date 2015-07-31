package Map;

import Interfaces.iLandscape;
import Interfaces.iMoveable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Ground implements iLandscape {
    private Image image;
    private final String TYPE;

    public Ground() {
        this.TYPE = "Ground";
        Image img = null;
        try {
            img = ImageIO.read(new File(System.getProperty("user.dir") + "/src/main/resources/image/ground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setImage(img);
    }
    @Override
    public boolean canMove(iMoveable object) {
        return true;
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
