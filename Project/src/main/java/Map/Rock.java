package Map;

import Interfaces.iLandscape;
import Interfaces.iMoveable;
import java.awt.*;

public class Rock implements iLandscape{
    private Image image;
    private final String TYPE;

    public Rock() {
        this.TYPE = "Rock";
    }
    public void setImage(Image image) {
        if (null != image) {
            this.image = image;
        }
    }
    public Image getImage() {
        return this.image;
    }
    public boolean canMove(iMoveable object) {
        String passRights = object.getPassRights();
        if (passRights.contains("flying")) {
            return true;
        }
        return false;
    }
    public String getLandType() {
        return this.TYPE;
    }
}
