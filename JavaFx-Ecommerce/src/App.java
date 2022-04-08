import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
    }

    public static String ImgToString(File file) throws IOException{
        String encodedFile = new String();
        FileInputStream streamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        streamReader.read(bytes);
        encodedFile = new String(Base64.getEncoder().encodeToString(bytes));
        streamReader.close();
        return encodedFile;
    }
}
