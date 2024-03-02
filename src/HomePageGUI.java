import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomePageGUI implements ActionListener {
    private final JFrame frame = new JFrame();
    public HomePageGUI(){
        // Welcome label
        JLabel welcome = new JLabel("Welcome to Area Code Finder!");
        welcome.setBounds(135,20,250,25);
        Color userColor = new Color(255,255,255);
        welcome.setForeground(userColor);
        // New Features text
        JLabel newFeatures = new JLabel();
        newFeatures.setBounds(10,50,500,25);
        newFeatures.setForeground(userColor);
        newFeatures.setText("New Features: Hitting the delete button will remove\r" +
                             "All text in the input field.");
        // Button setup
        JButton openAreaCode = new JButton("Close");
        openAreaCode.setBounds(175,75,100,50);
        openAreaCode.addActionListener(this);
        Color buttonColor = new Color(11, 23, 100);
        openAreaCode.setForeground(buttonColor);
        // Panel setup
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(null);
        Color color = new Color(11, 28, 74);
        panel.setBackground(color);
        panel.add(welcome);
        panel.add(newFeatures);
        panel.add(openAreaCode);
        // Frame setup
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(500, 150);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Area Code Searcher");

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        new AreaCodeGUI();
        frame.dispose();

    }
}
