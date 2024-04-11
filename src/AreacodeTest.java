import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AreacodeTest {


        @Test
        void areacodeReturnsCorrectCode() throws Exception {
            try {
                Assertions.assertEquals("402,Bellevue,Nebraska,US,41.13667,-95.89084", Areacode.getAreaCodeFromCSV("402"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }

