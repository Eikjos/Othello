package Othello;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {

    public BufferedImage image;

    public ImagePanel(String pathImage) {
        try {
            this.image = ImageIO.read(new File(pathImage));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
