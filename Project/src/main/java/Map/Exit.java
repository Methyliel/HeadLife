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
    public void setGate(String newLevelName) {
        if (null != newLevelName && (!"".equals(newLevelName))) {
            this.gate = newLevelName;
        }
    }
    public String getGate() {
        return this.gate;
    }
}
