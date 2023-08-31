/**
 * Area Code finder application - Caleb Waschkowski
 * - This application searches for area codes based on input form the use.
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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AreaCodeGUI implements ActionListener, KeyListener {
    public JFrame FRAME;// Allows the use of the MyFrame object throughout the class
    public JPanel PANEL = new JPanel();
    public static JTextField USER_TEXT = new JTextField(20);
    public static JLabel userlabel = new JLabel("Area Code");
    public static JLabel outputLabel = new JLabel("Output");
    public AreaCodeGUI() {
        // Area Code Title
        userlabel.setBounds(10,20,165,25);
        Color userColor = new Color(255,255,255);
        userlabel.setForeground(userColor);

        // User text field
        USER_TEXT.setBounds(100,20,165,25);
        USER_TEXT.addKeyListener(this);
        // Button
        JButton button = new JButton("Look Up");
        button.setBounds(8,100,100,50);
        button.addActionListener(this);
        Color buttonColor = new Color(11, 23, 100);
        button.setForeground(buttonColor);
        // Output label
        outputLabel.setBounds(8,-35,500,200);
        Color outputColor = new Color(255,255,255);
        outputLabel.setForeground(outputColor);
        // Image label
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("/Users/calebwaschkowski/eclipse-workspace/Areacode finder/src/AreaCodeIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }   
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setBounds(150,75,100,100);
        // setup for PANEL
        PANEL.setVisible(true);
        PANEL.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        PANEL.setLayout(null);
        PANEL.add(userlabel);
        PANEL.add(USER_TEXT);
        PANEL.add(button);
        PANEL.add(outputLabel);
        PANEL.add(picLabel);
        Color color = new Color(11, 28, 74);
        PANEL.setBackground(color);
        // Sets up the MyFrame
        FRAME = new JFrame();
        FRAME.add(PANEL, BorderLayout.CENTER);
        FRAME.setSize(315, 210);
        FRAME.setVisible(true);
        FRAME.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        FRAME.setTitle("Area code Searcher");
        //FRAME.addKeyListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println("Clicked");
        getAreaCode();
    }
    public static void getAreaCode(){
        String file = "/Users/calebwaschkowski/eclipse-workspace/Areacode Finder/src/areacodesNew2.csv";
        //String file = "/Users/Suzanne Waschkowski/Desktop/areacodesNew2.csv";
        String enteredZipCode = USER_TEXT.getText();
        BufferedReader reader = null;
        String line;
        try{
            outputLabel.setText("Output");
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null){
                String[] row = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
                for(String index : row) {
                    //System.out.printf("%-10s", index); // this prints all the rows
                    if (index.equals(enteredZipCode)) {
                        //System.out.println(line);
                        outputLabel.setText(line);
                        USER_TEXT.setText("");
                    }
                }
                if(outputLabel.getText().equals("Output")) {outputLabel.setText("Area code not found");}
            }
        }catch(Exception d){
            d.printStackTrace();
        }
        finally {
            try {
                assert reader != null;
                reader.close();
            }
            catch(Exception d){
                d.printStackTrace();
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            getAreaCode();
        }

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            getAreaCode();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
