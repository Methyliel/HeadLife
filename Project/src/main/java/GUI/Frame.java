package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Frame extends JFrame implements KeyListener{
    private JPanel panel;
    private boolean paused = false;
    private int charNumber = 1;

    public Frame() {
        this.setLocation(100, 100);
        this.setName("HeadLife");
        this.setSize(480, 352);
        this.panel = new JPanel();
        panel.setLocation(0, 0);
        panel.setSize(480, 352);
        this.add(panel);
        this.addKeyListener(this);
        this.setVisible(true);
    }
    public void drawStartScreen() throws Exception{
        String catalogPath = System.getProperty("user.dir") + "/src/main/resources/images/";
        Image startScreen = ImageIO.read(new File(catalogPath + "startGame.png"));
        Image charIcon = ImageIO.read(new File(catalogPath + "/objects/heads/"
                                        + Integer.toString(this.charNumber) + ".png"));
        BufferedImage img = new BufferedImage(480, 352, BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        g.drawImage(startScreen, 0, 0, null);
        g.drawImage(charIcon, 192, 128, null);
        this.panel.getGraphics().drawImage(img, 0, 0, 480, 352, null);
    }
    public void keyTyped(KeyEvent keyEvent) {

    }

    public void keyPressed(KeyEvent keyEvent) {
        if (KeyEvent.VK_RIGHT == keyEvent.getKeyCode()) {
            if (7 == this.charNumber) {
                this.charNumber = 1;
            }
            else {
                this.charNumber++;
            }
            try {
                this.drawStartScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (KeyEvent.VK_LEFT == keyEvent.getKeyCode()) {
            if (1 == this.charNumber) {
                this.charNumber = 7;
            }
            else {
                this.charNumber--;
            }
            try {
                this.drawStartScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void keyReleased(KeyEvent keyEvent) {

    }
}
