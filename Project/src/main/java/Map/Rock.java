package Map;

import Interfaces.iDrawable;
import Interfaces.iLandscape;
import Interfaces.iMoveable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Rock implements iLandscape{
    private Image image;
    private final String TYPE;

    public Rock() {
        this.TYPE = "Rock";
        Image img = null;
        try {
            img = ImageIO.read(new File(System.getProperty("user.dir") + "/src/main/resources/image/rock.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setImage(img);
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
    @Override
    public boolean canMove(iMoveable object) {
        String passRights = object.getPassRights();
        if (passRights.contains("flying")) {
            return true;
        }
        return false;
    }
    @Override
    public String getLandType() {
        return this.TYPE;
    }
}
