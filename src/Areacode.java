import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Areacode {


    public Areacode(){

    }

    public static void setLabelAndText(JLabel OUTPUT_LABEL,JTextField USER_TEXT) throws Exception {
        OUTPUT_LABEL.setText("Output");
        String enteredZipCode = USER_TEXT.getText();
        String areacode = Areacode.getAreaCodeFromCSV(enteredZipCode);
        OUTPUT_LABEL.setText(areacode);
        USER_TEXT.setText("");
    }

    public static void getAreaCode(JLabel OUTPUT_LABEL, JTextField USER_TEXT) throws Exception {
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

    public static String getAreaCodeFromCSV(String enteredZipCode) throws Exception {
        List<Path> result = findByFileName(".", "areacodes.csv");
        String file = String.valueOf(result.get(0));
        //String enteredZipCode = USER_TEXT.getText();
        BufferedReader reader = null;
        String line;
        try{
            //OUTPUT_LABEL.setText("Output");
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null){
                String[] row = line.split(","); // Removed (?=([^\"]*\"[^\"]*\")*[^\"]*$)
                for(String index : row) {
                    //System.out.printf("%-10s", index); // this prints all the rows
                    if (index.equals(enteredZipCode)) {
                        //System.out.println(line);
                        //OUTPUT_LABEL.setText(line);
                        System.out.println(line);
                        return line;

                        //USER_TEXT.setText("");
                    }

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
        return "Area Code not found";
    }
}
