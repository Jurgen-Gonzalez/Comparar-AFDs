package code;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI{
    private ScrollPane scroll;
    private VBox vb;
    private HBox hb;
    private Automata automata1, automata2;
    private AutomataGrid ag1, ag2;
    private GridPane grid1, grid2;
    private Button comparar;
    private GridPane tabla;
    private Label notificacion;
    
    
    public GUI(){
        scroll = new ScrollPane();
        vb = new VBox();
        hb = new HBox();
        ag1 = new AutomataGrid();
        ag2 = new AutomataGrid();
        grid1 = ag1.crearGrid();
        grid2 = ag2.crearGrid();
        
        comparar = new Button("Comparar Automatas");
        tabla = new GridPane();
        notificacion = new Label("Los automatas son compatibles");
    }
    
    public void launch(Stage escenario){
        Scene escena = new Scene(crearScrollPane(), 700, 400);
        escenario.setScene(escena);
        escenario.setTitle("Comparador de AFDs");
        escenario.show();
    }
    
    private ScrollPane crearScrollPane(){
        scroll.setContent(vb);
        
        vb.getChildren().add(new Label("Programa para comparar dos automatas finitos deterministas"
                + "y saber si son equivalentes"));
        vb.getChildren().add(crearHBox());
        vb.getChildren().add(comparar);
        
        comparar.setOnAction(e -> agregarComparacion());
        
        vb.setSpacing(10);
        vb.setAlignment(Pos.TOP_CENTER);
        return scroll;
    }
    
    private HBox crearHBox(){
        hb.getChildren().addAll(grid1, grid2);
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(30);
        
        return hb;
    }
    
    private void agregarComparacion(){
        vb.getChildren().remove(tabla);
        vb.getChildren().remove(notificacion);
        tabla = new GridPane();
        tabla.setAlignment(Pos.CENTER);
        tabla.setHgap(15);
        
        automata1 = ag1.getAutomata();
        automata2 = ag2.getAutomata();
        
        String [] alfabeto = automata1.getAlfabeto();
        
        vb.getChildren().add(tabla);
        
        tabla.add(new Label(subscript("M1M2")), 0, 0);
        for(int i = 0; i < alfabeto.length; i++){
            tabla.add(new Label(alfabeto[i]), i+1, 0);
        }
        comparar();
        
    }
    
    private void comparar(){
        Delta delta1 = automata1.getDelta();
        Delta delta2 = automata2.getDelta();
        
        boolean [] eF1 = automata1.getEstadosFinales();
        boolean [] eF2 = automata2.getEstadosFinales();
        int [] t1 = delta1.getΔ();
        int [] t2 = delta2.getΔ();
        
        String [] alfabeto = automata1.getAlfabeto();
        
        ArrayList<Label> m1m2 = new ArrayList();
        
        m1m2.add(new Label("q0q'0"));
        tabla.add(m1m2.get(0), 0, 1);
        
        int transicion1, transicion2, qProveniente1, qProveniente2;
        Label label;
        for(int i = 0; i < m1m2.size(); i++){ // i - renglon de m1m2 en el que vamos
                //Estados de donde proviene la transicion
                qProveniente1 = Integer.parseInt(m1m2.get(i).getText().replaceAll("q", "").split("'")[0]);
                qProveniente2 = Integer.parseInt(m1m2.get(i).getText().replaceAll("q", "").split("'")[1]);
                
                if(eF1[qProveniente1] != eF2[qProveniente2]){
                    notificacion = new Label("Los automatas no son equivalentes");
                    break;
                }
            for(int k = 0; k < alfabeto.length; k++){ // k - columna del alfabeto en la que vamos
                
                transicion1  = t1[qProveniente1 * alfabeto.length + k] ; //posicion en estadosDelta
                transicion2  = t2[qProveniente2 * alfabeto.length + k] ; //posicion en estadosDelta
                label = new Label("q"+transicion1+"q'"+transicion2);
                
                
                tabla.add(new Label("q"+transicion1+"q'"+transicion2), k+1, i+1);
                if(!contieneLabel(m1m2, label)){
                    m1m2.add(label);
                    tabla.add(new Label("q"+transicion1+"q'"+transicion2), 0, m1m2.size());
                }
            }
        }
        vb.getChildren().add(notificacion);
        
    }
    
    private boolean contieneLabel(ArrayList<Label> m1m2, Label label){
        for(Label l: m1m2){
            if(l.getText().equals(label.getText())) return true;
        }
        return false;
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
