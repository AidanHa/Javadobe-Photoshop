/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadobe.photoshop;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Aidan Ha and Sufyan Khan
 */
public class JavadobePhotoshop {

    public static File file;
    public static JFrame frame;
    public static JFileChooser chooser;
    public static BufferedImage img;
    public static Picture photo;
    public static JMenu editMenu = new JMenu("Edit");
    public static final int guiWidth = 1000;
    public static final int guiHeight = 800;

    public static void main(String[] args) {
        frame = new JFrame("Javadobe Photoshop");

        createMenu(frame);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(guiWidth, guiHeight));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setContentPane(pane);
        frame.pack();
    }

    public static void createMenu(JFrame frame) {
        //Create JMenuBar and JMenuBar components
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem[] fileMenuItems = new JMenuItem[3];
        JMenuItem[] editMenuItems = new JMenuItem[8];
        chooser = new JFileChooser(System.getProperty("user.home") + "/Desktop");
        editMenu = new JMenu("Edit");
        editMenu.setEnabled(false);

        //Create JMenuItems for File menu
        fileMenuItems[0] = new JMenuItem("Open");
        fileMenuItems[1] = new JMenuItem("Save As");
        fileMenuItems[2] = new JMenuItem("Exit");
        // fileMenuItems[1].setEnabled(false);
        //Create JMenuItems for Edit menu
        editMenuItems[0] = new JMenuItem("Revert Changes");
        editMenuItems[1] = new JMenuItem("Horizontal Flip");
        editMenuItems[2] = new JMenuItem("Vertical Flip");
        editMenuItems[3] = new JMenuItem("Gray Scale");
        editMenuItems[4] = new JMenuItem("Sepia Tone");
        editMenuItems[5] = new JMenuItem("Invert Colour");
        editMenuItems[6] = new JMenuItem("Gaussian Blur");
        editMenuItems[7] = new JMenuItem("Bulge Effect");

        //Add menu compoenents to JMenuBar and to JMenus
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        char letter;
        for (int i = 0; i < fileMenuItems.length; i++) {
            letter = fileMenuItems[i].getText().charAt(0);
            fileMenuItems[i].setAccelerator(KeyStroke.getKeyStroke(letter, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            fileMenu.add(fileMenuItems[i]);

            if (i == 1) {
                fileMenu.addSeparator();
            }

            fileMenuItems[i].addActionListener(new FileMenuAction());
        }

        for (int i = 0; i < editMenuItems.length; i++) {
            letter = editMenuItems[i].getText().charAt(0);
            if (i == 6) {
                letter = 'U';
            }
            if (i == 5) {
                letter = 'P';
            }

            editMenuItems[i].addActionListener(new EditMenuAction());
            editMenuItems[i].setAccelerator(KeyStroke.getKeyStroke(letter, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            editMenu.add(editMenuItems[i]);

            if (i == 0) {
                editMenu.addSeparator();
            }
        }
        frame.setJMenuBar(menuBar);
    }

    public static class FileMenuAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Open":
                    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        file = chooser.getSelectedFile();
                        BufferedImage img = null;

                        try {
                            img = ImageIO.read(file);
                            frame.remove(photo);
                        } catch (Exception ex) {
                        }

                        try {
                            photo = new Picture(img, 0, 0, file);
                        } catch (Exception ex) {
                        }

                        frame.add(photo);
                        frame.revalidate();
                        frame.repaint();
                    }
                    editMenu.setEnabled(true);
                    //fileMenuItems[1].setEnabled(true);
                    break;
                case "Save As":
                    if (editMenu.isEnabled()) {
                        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                            File file = new File(chooser.getSelectedFile().getAbsolutePath() + ".png");
                            try {
                                BufferedImage img = new BufferedImage(photo.getWidth(), photo.getHeight(), BufferedImage.TYPE_INT_RGB);
                                photo.paintComponent(img.getGraphics());
                                ImageIO.write(img, "png", file);
                            } catch (IOException ex) {
                                Logger.getLogger(JavadobePhotoshop.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                         JOptionPane.showMessageDialog(null, "please open an image first!", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }

                    break;
                default:
                    System.exit(0);
            }
        }
    }

    public static class EditMenuAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand().replace(" ", "");
            String methodName = command.substring(0, 1).toLowerCase() + command.substring(1);

            try {
                Method method = Picture.class.getMethod(methodName);
                img = (BufferedImage) method.invoke(photo);
                frame.remove(photo);

                if (methodName.equals("grayScale") || methodName.equals("sepiaTone")) {
                    photo = new Picture(img, 0, 0, file, photo.baseColorImage);
                } else {
                    photo = new Picture(img, 0, 0, file);
                }

            } catch (Exception ex) {
            }

            frame.add(photo);
            frame.repaint();
            frame.revalidate();

        }
    }

}
