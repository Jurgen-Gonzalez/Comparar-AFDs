package code;

public class Automata {
    private String [] alfabeto;
    private int [] estados;
    private boolean [] estadosFinales;
    private int estadoInicial;
    private Delta delta;

    public Automata(String[] alfabeto, int[] estados, boolean[] estadosFinales, int estadoInicial, Delta delta) {
        this.alfabeto = alfabeto;
        this.estados = estados;
        this.estadosFinales = estadosFinales;
        this.estadoInicial = estadoInicial;
        this.delta = delta;
    }

    public String[] getAlfabeto() {
        return alfabeto;
    }

    public int[] getEstados() {
        return estados;
    }

    public boolean[] getEstadosFinales() {
        return estadosFinales;
    }

    public int getEstadoInicial() {
        return estadoInicial;
    }

    public Delta getDelta() {
        return delta;
    }

    public void setDelta(Delta delta) {
        this.delta = delta;
    }
}
