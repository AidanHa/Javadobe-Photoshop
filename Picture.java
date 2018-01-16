/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadobe.photoshop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Aidan Ha and Sufyan Khan
 */
public class Picture extends JPanel {

    private File file;
    public BufferedImage image, baseColorImage;
    int x, y;

    public Picture(BufferedImage image, int x, int y, File file) throws IOException {
        super();
        this.image = image;
        this.x = x;
        this.y = y;
        this.file = file;

        baseColorImage = ImageIO.read(file);
    }

    //Overload Constructor
    public Picture(BufferedImage image, int x, int y, File file, BufferedImage baseColorImage) throws IOException {
        this(image, x, y, file);
        this.baseColorImage = baseColorImage;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 800);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, x, y, null);
    }

    private BufferedImage flip(int x, int y, int w, int z) {
        BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g = img.createGraphics();
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), x, y, w, z, null);
        g.dispose();
        return img;
    }

    public BufferedImage horizontalFlip() {
        return flip(image.getWidth(), 0, 0, image.getHeight());
    }

    public BufferedImage verticalFlip() {
        return flip(0, image.getHeight(), image.getWidth(), 0);
    }

    public BufferedImage grayScale() {
        BufferedImage gray = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = gray.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return gray;
    }

    public BufferedImage revertChanges() throws IOException {
        return ImageIO.read(file);
    }

    public BufferedImage sepiaTone() throws IOException {

        for (int y = 0; y < baseColorImage.getHeight(); y++) {
            for (int x = 0; x < baseColorImage.getWidth(); x++) {
                Color color = new Color(baseColorImage.getRGB(x, y));

                int rRed = (int) (color.getRed() * 0.393 + color.getGreen() * 0.769 + color.getBlue() * 0.189);
                int rGreen = (int) (color.getRed() * 0.349 + color.getGreen() * 0.686 + color.getBlue() * 0.168);
                int rBlue = (int) (color.getRed() * 0.272 + color.getGreen() * 0.534 + color.getBlue() * 0.131);

                if (rRed > 255) {
                    rRed = 255;
                }
                if (rGreen > 255) {
                    rGreen = 255;
                }
                if (rBlue > 255) {
                    rBlue = 255;
                }

                int a = (color.getAlpha() << 24) | (rRed << 16) | (rGreen << 8) | rBlue;

                baseColorImage.setRGB(x, y, a);
            }
        }
        return baseColorImage;
    }

    public BufferedImage invertColour() {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgba = image.getRGB(x, y);
                Color col = new Color(rgba, true);
                col = new Color(255 - col.getRed(), 255 - col.getGreen(), 255 - col.getBlue());
                image.setRGB(x, y, col.getRGB());
            }
        }

        return image;
    }

    public BufferedImage gaussianBlur() {

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                double red = 0, green = 0, blue = 0;
                Color c = new Color(0);

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        try {
                            c = new Color(image.getRGB(x + i, y + j));
                        } catch (ArrayIndexOutOfBoundsException e) {
                            c = new Color(image.getRGB(x, y));
                        }

                        red += c.getRed();
                        green += c.getGreen();
                        blue += c.getBlue();
                    }
                }

                image.setRGB(x, y, new Color((int) (red / 9), (int) (green / 9), (int) (blue / 9)).getRGB());
            }
        }
        return image;
    }

    public BufferedImage bulgeEffect() {
        BufferedImage output = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        int bulgeEffect = 80;

        for (int x = 0; x < image.getWidth(); x++) {

            for (int y = 0; y < image.getHeight(); y++) {
                double r = Math.sqrt(Math.pow((x - image.getWidth() / 2), 2) + Math.pow((y - image.getHeight() / 2), 2));
                int xPrime = x;
                int yPrime = y;

                if (r * r < Math.pow(bulgeEffect, 2)) {
                    double angle = Math.atan2(y - image.getHeight() / 2, x - image.getWidth() / 2);
                    double newRadius = Math.pow(r, 2) / bulgeEffect;

                    xPrime += newRadius * Math.cos(angle) + image.getWidth() / 2 - x;
                    yPrime += newRadius * Math.sin(angle) + image.getHeight() / 2 - y;
                }

                int rgb = image.getRGB(xPrime, yPrime);
                output.setRGB(x, y, rgb);
            }
        }
        return output;
    }

}
