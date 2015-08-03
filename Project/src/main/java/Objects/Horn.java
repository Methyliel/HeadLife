package Objects;

import Interfaces.iDrawable;
import Interfaces.iGameObject;
import Interfaces.iItem;
import Interfaces.iMoveable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Horn implements iDrawable, iMoveable, iGameObject, iItem {
    private final String NAME;
    private Image icon;
    private Point pixelLocation;
    private Point location;
    private String passRights;
    private List<Image> messages;

    public Horn(String name) {
        this.NAME = name;
        Image img = null;
        try {
            String imagePath = "/src/main/resources/image/" + this.NAME + ".png";
            img = ImageIO.read(new File(System.getProperty("user.dir") + imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setImage(img);
        this.setPassRights("pushed");
        this.messages = new ArrayList<Image>();
        int messageNumber = 4;
        for (int i = 0; i < messageNumber; i++) {
            Image msg = null;
            try {
                String imagePath = "/src/main/resources/image/message/" + i + ".png";
                msg = ImageIO.read(new File(System.getProperty("user.dir") + imagePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.messages.add(msg);
        }
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
    public Image getMessage(int msgNumber) {
        if (msgNumber >= this.messages.size()) {
            return null;
        }
        else {
            return this.messages.get(msgNumber);
        }
    }
}
