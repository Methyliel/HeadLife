package Objects;

import Interfaces.iDrawable;
import Interfaces.iGameObject;
import Interfaces.iItem;
import Interfaces.iMoveable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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
        Image img = null;
        try {
            String imagePath = "/src/main/resources/image/" + this.NAME + this.bloodColor + ".png";
            img = ImageIO.read(new File(System.getProperty("user.dir") + imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setImage(img);
        this.setPassRights("pushed");
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
            this.icon = image;
        }
    }
    @Override
    public Image getImage() {
        return this.icon;
    }
    @Override
    public String getName() {
        return this.NAME;
    }
    @Override
    public void action(iGameObject user) {
        if (user instanceof Head) {
            if (this.bloodColor.equals(((Head) user).getBloodColor())) {
                //Додати здоров'я голові
            }
        }
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
