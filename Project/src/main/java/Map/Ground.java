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
