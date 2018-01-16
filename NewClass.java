/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadobe.photoshop;

/**
 *
 * @author Aidan Ha and Sufyan Khan
 */
/*public class JavadobePhotoshop {

    public static JFrame frame;
    public static JPanel panel;
    public static JFileChooser chooser;
    public static BufferedImage imageFile;
    public static Picture image;

    public static void main(String[] args) {
        frame = new JFrame("Javadobe Photoshop");
        panel = new JPanel();  
        

        createMenu(frame);
        //image = new Picture(imageFile, 0, 0);


        //panel.add(image);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(1000, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setContentPane(panel);
        frame.pack();
    }

    public static class FileMenuAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Open")) {
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();

                    try {
                        System.out.println("das");
                        imageFile = ImageIO.read(file);
                        image = new Picture(imageFile, 0, 0);
                        frame.add(image);
                        //image.setIcon(new ImageIcon(imageFile));
                    } catch (IOException ex) {
                    }
                }
            } else if (e.getActionCommand().equals("Save")) {

            }
        }
    }

    public static class EditMenuAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                Method method = Picture.class.getMethod(e.getActionCommand().replace(" ", ""));
                method.invoke(image);
            } catch (Exception ex) {
            }
        }
    }

    public static void createMenu(JFrame frame) {
        //Create JMenuBar and JMenuBar components
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenuItem[] fileMenuItems = new JMenuItem[3];
        JMenuItem[] editMenuItems = new JMenuItem[8];
        chooser = new JFileChooser(System.getProperty("user.home") + "/Desktop");

        //Create JMenuItems for File menu
        fileMenuItems[0] = new JMenuItem("Open");
        fileMenuItems[1] = new JMenuItem("Save As");
        fileMenuItems[2] = new JMenuItem("Exit");

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

}*/
