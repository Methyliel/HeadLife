package Objects;

import Interfaces.iAttack;
import Interfaces.iDrawable;
import Interfaces.iGameObject;
import Interfaces.iMoveable;
import java.awt.*;


public class EctobiologicalWorm implements iDrawable, iMoveable, iGameObject, iAttack {
    private final String NAME;
    private Image portrait;
    private Point pixelLocation;
    private Point location;
    private String passRights;
    private int hp;
    private int damage;
    private int range;

    public EctobiologicalWorm(String name) {
        this.NAME = name;
    }
    @Override
    public void setHealthPoint(int healthPoint) {
        if (healthPoint >= 0) {
            this.hp = healthPoint;
        }
    }
    @Override
    public int getHealthPoint() {
        return this.hp;
    }
    @Override
    public void setDamage(int damage) {
        if (damage > 0) {
            this.damage = damage;
        }
    }
    @Override
    public int getDamage() {
        return this.damage;
    }
    @Override
    public void setRange(int range) {
        if (range >= 0) {
            this.range = range;
        }
    }
    @Override
    public int getRange() {
        return this.range;
    }
    @Override
    public void setPixelLocation(Point location) {
        if (null != location) {
            this.pixelLocation = location;
        }
    }
    @Override
    public Point getPixelLocation() {
        return this.pixelLocation;
    }
    @Override
    public void setImage(Image image) {
        if (null != image) {
            this.portrait = image;
        }
    }
    @Override
    public Image getImage() {
        return this.portrait;
    }
    @Override
    public String getName() {
        return this.NAME;
    }
    @Override
    public void setLocation(Point location) {
        if (null != location) {
            this.location = location;
        }
    }
    @Override
    public Point getLocation() {
        return this.location;
    }
    @Override
    public void setPassRights(String passrights) {
        if (null != passrights && (!"".equals(passrights))) {
            this.passRights = passrights;
        }
    }
    @Override
    public String getPassRights() {
        return this.passRights;
    }
}
