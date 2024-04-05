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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
import java.nio.file.Paths;
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
            List<Path> result = findByFileName(".", "AreaCodeIcon.png");
            String pathname = String.valueOf(result.get(0));
            myPicture = ImageIO.read(new File(pathname));
        } catch (IOException e) {
            //e.printStackTrace();
        }
        assert myPicture != null;
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setBounds(150,75,100,100);
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
            logoResult = findByFileName(".", "AreaCodeIcon.png");
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
            getAreaCode();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public static void getAreaCode() throws Exception {
        List<Path> result = findByFileName(".", "areacodes.csv");
        String file = String.valueOf(result.get(0));
        String enteredZipCode = USER_TEXT.getText();
        BufferedReader reader = null;
        String line;
        try{
            OUTPUT_LABEL.setText("Output");
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null){
                String[] row = line.split(","); // Removed (?=([^\"]*\"[^\"]*\")*[^\"]*$)
                for(String index : row) {
                    //System.out.printf("%-10s", index); // this prints all the rows
                    if (index.equals(enteredZipCode)) {
                        //System.out.println(line);
                        OUTPUT_LABEL.setText(line);
                        System.out.println(line);
                        USER_TEXT.setText("");
                    }

                }
                if(OUTPUT_LABEL.getText().equals("Output")) {
                    OUTPUT_LABEL.setText("Area code not found");
                }
            }
        } finally {
            try {
                assert reader != null;
                reader.close();
            }
            catch(Exception d){
                //d.printStackTrace();
            }
        }
    }
    // Finds file paths for the area code csv file. Allows a user to just need the file on their computer for the
    // program to work.
    public static List<Path> findByFileName(String pathString, String fileName)
            throws IOException {
        Path path = Paths.get(pathString);
        List<Path> result;
        try (Stream<Path> pathStream = Files.find(path, Integer.MAX_VALUE, (p, basicFileAttributes) -> p.getFileName().toString().equalsIgnoreCase(fileName))
        ) {
            result = pathStream.collect(Collectors.toList());
        }
        return result;
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                getAreaCode();
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