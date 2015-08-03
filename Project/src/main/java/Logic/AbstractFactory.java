package Logic;

import Interfaces.iGameObject;
import Interfaces.iLandscape;
import Objects.Head;

import java.awt.*;

public abstract class AbstractFactory {
    public abstract iLandscape createLandscape(String landType);
    public abstract iGameObject createGameObject(String objectName, Point objectLocation);
    public abstract Head createHead(String name, Point objectLocation);
}
