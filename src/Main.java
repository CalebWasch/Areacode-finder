import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Zipcode finder application");
        new AreaCodeGUI();


        List<Path> result = AreaCodeGUI.findByFileName(".", "areacodesNew2.csv");
        System.out.println(result.get(0));
        //result.forEach(System.out::println);





    }
}
