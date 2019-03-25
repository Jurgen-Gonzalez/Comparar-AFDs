package code;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class EntradaDeDatos{
    private HBox hb;
    private Automata automata1, automata2;
    private GridPane grid1, grid2;
    
    
    public EntradaDeDatos(){
        hb = new HBox();
        grid1 = new AutomataGrid().crearGrid();
        grid2 = new AutomataGrid().crearGrid();
        
    }
    
    public void launch(Stage escenario){
        Scene escena = new Scene(crearHBox(), 600, 400);
        escenario.setScene(escena);
        escenario.show();
    }
    
    private HBox crearHBox(){
        hb.getChildren().addAll(grid1, grid2);
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(30);
        
        return hb;
    }
    
    public static String superscript(String str) {
        str = str.replaceAll("0", "⁰");
        str = str.replaceAll("1", "¹");
        str = str.replaceAll("2", "²");
        str = str.replaceAll("3", "³");
        str = str.replaceAll("4", "⁴");
        str = str.replaceAll("5", "⁵");
        str = str.replaceAll("6", "⁶");
        str = str.replaceAll("7", "⁷");
        str = str.replaceAll("8", "⁸");
        str = str.replaceAll("9", "⁹");         
        return str;
}

    public static String subscript(String str) {
        str = str.replaceAll("0", "₀");
        str = str.replaceAll("1", "₁");
        str = str.replaceAll("2", "₂");
        str = str.replaceAll("3", "₃");
        str = str.replaceAll("4", "₄");
        str = str.replaceAll("5", "₅");
        str = str.replaceAll("6", "₆");
        str = str.replaceAll("7", "₇");
        str = str.replaceAll("8", "₈");
        str = str.replaceAll("9", "₉");
        return str;
    }
}
