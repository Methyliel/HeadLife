package Map;

import Interfaces.iGameObject;
import Interfaces.iLandscape;
import Interfaces.iMoveable;
import Objects.Head;
import java.awt.*;

public class Slime implements iLandscape {
    private Image image;
    private final String TYPE;

    public Slime() {
        this.TYPE = "Ground";
    }
    public boolean canMove(iMoveable object) {
        if (object instanceof iGameObject) {
            if ("Head".equals(((iGameObject) object).getName())) {
                this.doSomeDamage((Head) object);
                return true;
            }
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
    private void doSomeDamage(Head head) {

    }
}
