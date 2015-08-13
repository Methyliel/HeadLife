package Objects;

import Interfaces.iAttack;
import Interfaces.iDrawable;
import Interfaces.iGameObject;
import Interfaces.iMoveable;
import java.awt.*;

public class EctobiologicalBird implements iDrawable, iMoveable, iGameObject, iAttack {
    private final String NAME;
    private Image portrait;
    private Point pixelLocation;
    private Point location;
    private String passRights;
    private int hp;
    private int damage;
    private int range;

    public EctobiologicalBird(String name) {
        this.NAME = name;
    }
    public void setHealthPoint(int healthPoint) {
        if (healthPoint >= 0) {
            this.hp = healthPoint;
        }
    }
    public int getHealthPoint() {
        return this.hp;
    }
    public void setDamage(int damage) {
        if (damage > 0) {
            this.damage = damage;
        }
    }
    public int getDamage() {
        return this.damage;
    }
    public void setRange(int range) {
        if (range >= 0) {
            this.range = range;
        }
    }
    public int getRange() {
        return this.range;
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
            this.portrait = image;
        }
    }
    public Image getImage() {
        return this.portrait;
    }
    public String getName() {
        return this.NAME;
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
