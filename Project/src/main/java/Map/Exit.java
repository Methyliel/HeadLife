package Map;

import Interfaces.iLandscape;
import Interfaces.iMoveable;
import Objects.Head;
import java.awt.*;

public class Exit implements iLandscape {
    private Image image;
    private final String TYPE;
    private String gate;

    public Exit() {
        this.TYPE = "Ground";
    }
    public boolean canMove(iMoveable object) {
        if (object instanceof Head) {
            return true;
        }
        return false;
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
    public void setGate(String newLevelName) {
        if (null != newLevelName && (!"".equals(newLevelName))) {
            this.gate = newLevelName;
        }
    }
    public String getGate() {
        return this.gate;
    }
}
