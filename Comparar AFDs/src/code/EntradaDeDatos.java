package code;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EntradaDeDatos{
    private VBox vb;
    private HBox hb;
    private Automata automata1, automata2;
    private AutomataGrid ag1, ag2;
    private GridPane grid1, grid2;
    private Button comparar;
    
    
    public EntradaDeDatos(){
        vb = new VBox();
        hb = new HBox();
        ag1 = new AutomataGrid();
        ag2 = new AutomataGrid();
        grid1 = ag1.crearGrid();
        grid2 = ag2.crearGrid();
        
        comparar = new Button("Comparar Automatas");
        
    }
    
    public void launch(Stage escenario){
        Scene escena = new Scene(crearVBox(), 600, 400);
        escenario.setScene(escena);
        escenario.show();
    }
    
    private VBox crearVBox(){
        vb.getChildren().add(crearHBox());
        vb.getChildren().add(comparar);
        
        comparar.setOnAction(e -> agregarComparacion());
        
        vb.setSpacing(10);
        vb.setAlignment(Pos.CENTER);
        return vb;
    }
    
    private HBox crearHBox(){
        hb.getChildren().addAll(grid1, grid2);
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(30);
        
        return hb;
    }
    
    private void agregarComparacion(){
        automata1 = ag1.getAutomata();
        automata2 = ag2.getAutomata();
        
        String [] alfabeto = automata1.getAlfabeto();
        
        //vb.getChildren().add()
        GridPane tabla = new GridPane();
        
        tabla.add(new Label(subscript("M1M2")), 0, 0);
        for(int i = 0; i < alfabeto.length; i++){
            tabla.add(new Label(alfabeto[i]), i+1, 0);
        }
        comparar();
        
    }
    
    private void comparar(){
        Delta delta1 = automata1.getDelta();
        Delta delta2 = automata1.getDelta();
        
        int [] e1 = delta1.getQ();
        int [] e2 = delta2.getQ();
        boolean [] eF1 = automata1.getEstadosFinales();
        boolean [] eF2 = automata2.getEstadosFinales();
        int [] t1 = delta1.getΔ();
        int [] t2 = delta2.getΔ();
        
        String [] alfabeto = automata1.getAlfabeto();
        
        int eA1 =0, eA2 =0;// Estado Actual q0q'0
        for(int i = 0; i < e1.length; i++){
            if(eF1[i] != eF2[i]){}
            for(String simbolo: alfabeto){
                //t1[i]
            }
        }
        
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
