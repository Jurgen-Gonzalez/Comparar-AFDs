package code;

import javafx.application.Application;
import javafx.stage.Stage;



public class CompararAFDs extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new EntradaDeDatos().launch(primaryStage);
    }
    
}
