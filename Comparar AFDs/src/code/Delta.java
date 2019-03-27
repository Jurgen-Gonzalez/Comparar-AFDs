package code;

public class Delta {
    private int [] q; //Estado
    private String [] Σ; //Alfabeto
    private int [] δ; //Delta - representa la funcion δ(q,σ)   Alt Gr + 235

    public Delta(int [] q, String [] σ, int [] δ) {
        this.q = q;
        this.Σ = Σ;
        this.δ = δ;
    }

    public int [] getQ() {
        return q;
    }

    public String [] getΣ() {
        return Σ;
    }

    public int [] getΔ() {
        return δ;
    }
    
    
}
