package Interfaces;

import java.awt.*;

public interface iLandscape {
    public boolean canMove(iMoveable object);
    public String getLandType();
    public void setImage(Image image);
    public Image getImage();
}
