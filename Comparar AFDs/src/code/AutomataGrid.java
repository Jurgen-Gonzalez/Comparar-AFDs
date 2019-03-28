package code;

import java.util.ArrayList;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AutomataGrid {
    private GridPane grid;
    private Label lAlfabeto, lEstados, lEstadosFinales, lDelta;
    private TextField tfAlfabeto;
    private ArrayList<Label> labelsEstados;
    private Button masEstados, menosEstados;
    private ArrayList<RadioButton> rbEstadosFinales;
    private Automata automata;
    private Delta delta;
    private Button verDelta;
    private TextField [] transiciones;
    private int contador;
    
    public AutomataGrid(){
        contador = 0;
        
        grid = new GridPane();
        lAlfabeto = new Label("Alfabeto");
        lEstados = new Label("Estados");
        lEstadosFinales = new Label("Estados Finales");
        lDelta = new Label("δ:");
        
        tfAlfabeto = new TextField();
        labelsEstados = new ArrayList<>();
        masEstados = new Button("+");
        menosEstados = new Button("-");
        
        rbEstadosFinales = new ArrayList<>();
        verDelta = new Button("Ver");
        
    }
    
    public GridPane crearGrid(){
        grid.setHgap(5);
        grid.setVgap(5);
       // grid.add(new Label("Automata"), 1, 0);
        
        grid.add(lAlfabeto, 0, 1);
        grid.add(tfAlfabeto, 1, 1, 12, 1);
        tfAlfabeto.setPromptText("ej: a, b, c");
        tfAlfabeto.getParent().requestFocus();
        
        grid.add(lEstados, 0, 2);
        crearEstado();
        grid.add(masEstados, 14, 2);
        grid.add(menosEstados, 15, 2);
        grid.add(lEstadosFinales, 0, 3);
        
        
        grid.add(lDelta, 0, 4);
        GridPane.setHalignment(lDelta, HPos.RIGHT);
        
        grid.add(new Label("q"), 1, 4);
        grid.add(new Label("σ"), 2, 4);
        Label del = new Label("δ(q,σ)");
        grid.add(del, 3, 4);
        GridPane.setColumnSpan(del, 4);
        grid.add(verDelta, 14, 4);
        
        masEstados.setOnAction(e -> crearEstado());
        menosEstados.setOnAction(e -> removerEstado());
        verDelta.setOnAction(e -> calcularDelta());
        
        
        return grid;
    }
    
    private void crearEstado(){
        if(contador <= 11){ //Limite de estados de 11 para efectos del diseño
            labelsEstados.add(new Label("q"+ (GUI.subscript(Integer.toString(contador)))));
            contador++;
            grid.add(labelsEstados.get(labelsEstados.size()-1), contador, 2, 1, 1);

            crearRadioButton();
        }
    }
    
    private void removerEstado(){
        if(contador != 1){ // No se puede tener un automata con 0 estados
            contador--;
            grid.getChildren().remove(labelsEstados.get(contador));
            labelsEstados.remove(contador);
            removerRadioButton();
        }
    }
    
    private void crearRadioButton(){
        rbEstadosFinales.add(new RadioButton());
        grid.add(rbEstadosFinales.get(contador-1), contador, 3,1,1);
    }
    
    private void removerRadioButton(){
        grid.getChildren().remove(rbEstadosFinales.get(contador));
        rbEstadosFinales.remove(contador);
    }
    
    private void calcularDelta(){
        if(tfAlfabeto.getText() != null){
            int [] estados = getEstadosDelta();
            String [] alfabeto = getAlfabetoDelta();

            transiciones = new TextField[labelsEstados.size()*getAlfabeto().length];
            for(int i = 0; i< transiciones.length; i++){
                transiciones[i] = new TextField();
                transiciones[i].setMaxWidth(45);


                grid.add(new Label("q"+GUI.subscript(Integer.toString(estados[i]))), 1, i+5);
                grid.add(new Label(""+alfabeto[i]), 2, i+5);
                grid.add(transiciones[i], 3, i+5, 2, 1);
            }
        }
        
    }
    
    public int [] getEstados(){
        int [] estados = new int[labelsEstados.size()];
        for(int i = 0; i < labelsEstados.size(); i++){
            estados[i] = i;
        }
        return estados;
    }
    
    
    public int [] getEstadosDelta(){ // q0, q1, q2 pero solamente los enteros
        int nEstados = labelsEstados.size();
        int nAlfabeto = getAlfabeto().length;
        int [] arregloEstados = new int[nEstados*nAlfabeto];
        
        int renglon = 0;
        for(int g = 0; g<nEstados; g++){
            for(int i = 0; i < nAlfabeto; i++){
                arregloEstados[renglon] = g;
                renglon++;
            }
            
        }
        
        return arregloEstados;
    }
    
    public String [] getAlfabeto(){ //Alfabeto real
        String alfabetoTexto = tfAlfabeto.getText();
        alfabetoTexto.replaceAll(" ", "");
        
        return alfabetoTexto.split(",");
    }
    
    public String [] getAlfabetoDelta(){ //Alfabeto listo para entregar a Delta
        String [] alfabeto = getAlfabeto();
        String [] alfa = new String[alfabeto.length*labelsEstados.size()];
        
        for(int i = 0; i < alfa.length;){
            for(String simbolo: alfabeto){
                alfa[i] = simbolo;
                i++;
            }
        }
        
        return alfa;
    }
    
    private boolean [] getEstadosFinales(){
        boolean [] estados = new boolean[rbEstadosFinales.size()];
        for(int i = 0; i < rbEstadosFinales.size(); i++){
            estados[i] = (rbEstadosFinales.get(i).isSelected())? true: false;
        }
        return estados;
    }
    
    public int [] getTransiciones(){
        int [] transicion = new int[transiciones.length];
        
        for(int i = 0; i < transicion.length; i++){
            transicion[i] = Integer.parseInt(transiciones[i].getText().replaceAll("q", ""));
        }
        return transicion;
    }
    
    public Automata getAutomata(){
        String [] alfabeto = getAlfabeto();
        int [] estados = getEstados();
        boolean [] estadosFinales = getEstadosFinales();
        delta = new Delta(getEstadosDelta(), getAlfabetoDelta(), getTransiciones());
        
        automata = new Automata(alfabeto, estados, estadosFinales, 0, delta);
        return automata;
    }
    
    
    
}
