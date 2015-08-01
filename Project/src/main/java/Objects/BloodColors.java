package Objects;

import java.util.ArrayList;
import java.util.List;

public class BloodColors {
    private static BloodColors colorList = null;
    private final List<String> colors;

    private BloodColors() {
        this.colors = new ArrayList<String>();
        this.colors.add("COLOR_Fuchsia");
        this.colors.add("COLOR_Violet");
        this.colors.add("COLOR_Indigo");
        this.colors.add("COLOR_Cerulean");
        this.colors.add("COLOR_Olive");
        this.colors.add("COLOR_Yellow");
        this.colors.add("COLOR_Bronze");
    }
    public static BloodColors instance() {
        if (null == BloodColors.colorList) {
            BloodColors.colorList = new BloodColors();
        }
        return BloodColors.colorList;
    }
    public String getSomeColor() {
        int colorNumber = (int) (Math.random() * this.colors.size());
        return this.colors.get(colorNumber);
    }
    public String getExactColor(int colorNumber) {
        if (colorNumber >= this.colors.size()) {
            return null;
        }
        else {
            return this.colors.get(colorNumber);
        }
    }
}
