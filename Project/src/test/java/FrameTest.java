import GUI.Frame;

public class FrameTest {
    public static void main(String argc[]) {
        Frame frame = new Frame();
        try {
            frame.drawStartScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
