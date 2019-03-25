package code;

public class Delta {
    private int q; //Estado
    private char σ; //Sigma - representa una letra del abecedario del automata  Alt Gr + 229
    private int δ; //Delta - representa la funcion δ(q,σ)   Alt Gr + 235

    public Delta(int q, char σ, int δ) {
        this.q = q;
        this.σ = σ;
        this.δ = δ;
    }

    public int getQ() {
        return q;
    }

    public char getΣ() {
        return σ;
    }

    public int getΔ() {
        return δ;
    }
    
    
}
