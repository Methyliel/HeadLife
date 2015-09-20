package Map;

import Interfaces.iLandscape;
import Interfaces.iMoveable;
import java.awt.*;

public class Ground implements iLandscape {
    private Image image;
    private final String TYPE;

    public Ground() {
        this.TYPE = "Ground";
    }
    public boolean canMove(iMoveable object) {
        return true;
    }
    public String getLandType() {
        return this.TYPE;
    }
    public void setImage(Image image) {
        if (null != image) {
            this.image = image;
        }
    }
    public Image getImage() {
        return this.image;
    }
}
