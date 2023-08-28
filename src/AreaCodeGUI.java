import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
public class AreaCodeGUI implements ActionListener, KeyListener {
    public JFrame FRAME;// Allows the use of the MyFrame object throughout the class
    public JPanel PANEL = new JPanel();
    public static JTextField USER_TEXT = new JTextField(20);
    public static JLabel userlabel = new JLabel("Area Code");
    public static JLabel outputLabel = new JLabel("Output");
    public AreaCodeGUI() {
        // Area Code Title
        userlabel.setBounds(10,20,165,25);
        // User text field
        USER_TEXT.setBounds(100,20,165,25);
        USER_TEXT.addKeyListener(this);
        // Button
        JButton button = new JButton("Look Up");
        button.setBounds(8,100,100,50);
        button.addActionListener(this);
        // Output label
        outputLabel.setBounds(8,-35,500,200);
        // setup for PANEL
        PANEL.setVisible(true);
        PANEL.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        PANEL.setLayout(null);
        PANEL.add(userlabel);
        PANEL.add(USER_TEXT);
        PANEL.add(button);
        PANEL.add(outputLabel);
        // Sets up the MyFrame
        FRAME = new JFrame();
        FRAME.add(PANEL, BorderLayout.CENTER);
        FRAME.setSize(500, 200);
        FRAME.setVisible(true);
        FRAME.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        FRAME.setTitle("Area code Searcher");
        //FRAME.addKeyListener(this);
        // Debugging statements
        System.out.println("Added title");
        System.out.println("Added PANEL and BorderLayout");
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
                        System.out.println(line);
                        outputLabel.setText(line);
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
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            getAreaCode();
            System.out.println("Pressed");
        }

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            getAreaCode();
            System.out.println("Pressed");
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key Released");
    }
}
