/**
 * Area Code finder application - Caleb Waschkowski
 * - This application searches for area codes based on input from the user.
 * - Feel free to download the code and make changes or use for your own projects.
 * - Color's used were suggested by my UI expert Fiancé.
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class HomePage implements ActionListener, KeyListener {
    private static final JTextField USER_TEXT = new JTextField(20);
    private static final JLabel USER_LABEL = new JLabel("Area Code");
    private static final JLabel OUTPUT_LABEL = new JLabel("Output");
    public HomePage() {
        // Area Code Title
        USER_LABEL.setBounds(10,20,165,25);
        Color userColor = new Color(255,255,255);
        USER_LABEL.setForeground(userColor);
        // User text field
        USER_TEXT.setBounds(100,20,165,25);
        USER_TEXT.addKeyListener(this);
        // Button
        JButton LookUpButton = new JButton("Look Up");
        LookUpButton.setBounds(8,100,100,50);
        LookUpButton.addActionListener(this);
        Color buttonColor = new Color(11, 23, 100);
        LookUpButton.setForeground(buttonColor);
        // Output label
        OUTPUT_LABEL.setBounds(8,-35,500,200);
        Color outputColor = new Color(255,255,255);
        OUTPUT_LABEL.setForeground(outputColor);
        // Image label
        BufferedImage myPicture = null;
        try {
            List<Path> result = Areacode.findByFileName(".", "AreaCodeIcon.png");
            String pathname = String.valueOf(result.get(0));
            myPicture = ImageIO.read(new File(pathname));
        } catch (IOException e) {
            //e.printStackTrace();
        }
        JLabel picLabel;
        if (myPicture == null) {
            throw new AssertionError();
        }else {
            picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setBounds(150, 75, 100, 100);
        }
        // setup for panel
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(null);
        panel.add(USER_LABEL);
        panel.add(USER_TEXT);
        panel.add(LookUpButton);
        panel.add(OUTPUT_LABEL);
        panel.add(picLabel);
        Color color = new Color(11, 28, 74);
        panel.setBackground(color);
        // Sets up the MyFrame
        // Allows the use of the MyFrame object throughout the class
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame();
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(315, 210);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Area code Searcher");
        List<Path> logoResult;
        try {
            logoResult = Areacode.findByFileName(".", "AreaCodeIcon.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String file = String.valueOf(logoResult.get(0));
        ImageIcon logo = new ImageIcon(file);
        frame.setIconImage(logo.getImage());
        //frame.addKeyListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        try {
            Areacode.setLabelAndText(OUTPUT_LABEL, USER_TEXT);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                Areacode.setLabelAndText(OUTPUT_LABEL, USER_TEXT);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_DELETE) {
            USER_TEXT.setText("");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}