package Objects;

import Interfaces.iDrawable;
import Interfaces.iGameObject;
import Interfaces.iItem;
import Interfaces.iMoveable;
import java.awt.*;

public class Potion implements iDrawable, iMoveable, iGameObject, iItem {
    private final String NAME;
    private Image icon;
    private Point pixelLocation;
    private Point location;
    private String passRights;
    private String bloodColor;

    public Potion(String name) {
        this.NAME = name;
        bloodColor = BloodColors.instance().getSomeColor();
    }
    public void setPixelLocation(Point location) {
        if (null != location) {
            this.pixelLocation = location;
        }
    }
    public Point getPixelLocation() {
        return this.pixelLocation;
    }
    public void setImage(Image image) {
        if (null != image) {
            this.icon = image;
        }
    }
    public Image getImage() {
        return this.icon;
    }
    public String getName() {
        return this.NAME;
    }
    public void action(iGameObject user) {
        if (user instanceof Head) {
            if (this.bloodColor.equals(((Head) user).getBloodColor())) {
                //Додати здоров'я голові
            }
        }
    }
    public void setLocation(Point location) {
        if (null != location) {
            this.location = location;
        }
    }
    public Point getLocation() {
        return this.location;
    }
    public void setPassRights(String passrights) {
        if (null != passrights && (!"".equals(passrights))) {
            this.passRights = passrights;
        }
    }
    public String getPassRights() {
        return this.passRights;
    }
}
