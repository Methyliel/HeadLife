package Map;

import Interfaces.iLandscape;

import java.util.HashMap;
import java.util.Map;

public class SingleLands {
    public static SingleLands lands;
    private Map<String, iLandscape> landscapeExamples;

    private SingleLands() {
        this.landscapeExamples = new HashMap<String, iLandscape>();
        this.landscapeExamples.put("Ground", new Ground());
        this.landscapeExamples.put("Rock", new Rock());
        this.landscapeExamples.put("Exit", new Exit());
    }
    public static SingleLands instance() {
        if (null == SingleLands.lands) {
            SingleLands.lands = new SingleLands();
        }
        return SingleLands.lands;
    }
    public iLandscape getLand(String name) {
        return this.landscapeExamples.get(name);
    }
}
