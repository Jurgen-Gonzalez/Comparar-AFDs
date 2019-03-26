package code;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AutomataGrid {
    private GridPane grid;
    private Label lAlfabeto, lEstados, lEstadosFinales, lDelta;
    private TextField tfAlfabeto;
    private ArrayList<Label> labelsEstados;
    private Button masEstados, menosEstados;
    private ArrayList<ComboBox> comboBoxes;
    private int contador, contadorComboBoxes;
    
    public AutomataGrid(){
        contador = 0;
        contadorComboBoxes = 0;
        
        grid = new GridPane();
        lAlfabeto = new Label("Alfabeto");
        lEstados = new Label("Estados");
        lEstadosFinales = new Label("Estados Finales");
        lDelta = new Label("Delta");
        
        tfAlfabeto = new TextField();
        labelsEstados = new ArrayList<>();
        masEstados = new Button("+");
        menosEstados = new Button("-");
        comboBoxes = new ArrayList<>();
        
    }
    
    public GridPane crearGrid(){
        
        grid.add(lAlfabeto, 0, 1);
        grid.add(tfAlfabeto, 1, 1, 12, 1);
        grid.add(lEstados, 0, 2);
        
        crearEstado();
        
        grid.add(masEstados, 14, 2);
        grid.add(menosEstados, 15, 2);
        grid.add(lEstadosFinales, 0, 3);
        
        crearComboBox();
        
        grid.add(lDelta, 0, 4);
        
        masEstados.setOnAction(e -> crearEstado());
        menosEstados.setOnAction(e -> removerEstado());
        
        
        return grid;
    }
    
    private void crearEstado(){
        labelsEstados.add(new Label("q"+ (EntradaDeDatos.subscript(Integer.toString(contador)))));
        contador++;
        grid.add(labelsEstados.get(labelsEstados.size()-1), contador, 2, 1, 1);
        
        refrescaEstados();
    }
    
    private void removerEstado(){
        contador--;
        grid.getChildren().remove(labelsEstados.get(contador));
        labelsEstados.remove(contador);
        
        refrescaEstados();
    }
    
    public void crearComboBox(){
        ComboBox cb = new ComboBox();
        comboBoxes.add(cb);
        cb.setOnAction(e -> crearComboBox());

        refrescaEstados();
        
        contadorComboBoxes += 3;
        grid.add(cb, contadorComboBoxes, 3, 3,1);
    }
    
    private void refrescaEstados(){
        for(int i = 0; i < comboBoxes.size(); i++){
            comboBoxes.get(i).getItems().clear();
            comboBoxes.get(i).getItems().addAll(getEstados());
        }
    }
    
    public String [] getEstados(){
        ArrayList<String> s = new ArrayList<>();
        for(Label label: labelsEstados){
            s.add(label.getText());
        }
        return s.toArray(new String[s.size()]);
    }
    
    
    public String getAlfabetoText(){
        return tfAlfabeto.getText();
    }
    
    
}
