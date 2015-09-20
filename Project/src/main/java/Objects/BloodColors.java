package Objects;

public class BloodColors {
    public static final String fuchsia = "COLOR_Fuchsia";
    public static final String violet = "COLOR_Violet";
    public static final String indigo = "COLOR_Indigo";
    public static final String cerulean = "COLOR_Cerulean";
    public static final String olive = "COLOR_Olive";
    public static final String yellow = "COLOR_Yellow";
    public static final String bronze = "COLOR_Bronze";

    public static String getSomeColour() {
        int r = (int) (Math.random() * 7);
        if (0 == r) {
            return BloodColors.fuchsia;
        }
        if (1 == r) {
            return BloodColors.violet;
        }
        if (2 == r) {
            return BloodColors.indigo;
        }
        if (3 == r) {
            return BloodColors.cerulean;
        }
        if (4 == r) {
            return BloodColors.olive;
        }
        if (5 == r) {
            return BloodColors.yellow;
        }
        if (6 == r) {
            return BloodColors.bronze;
        }
        return null;
    }
}
