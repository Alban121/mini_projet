
//Cette enum sert juste a modifier les valeurs des limites que l on a pose arbitrairement
public enum Variable{
    LIMITEAFFECTATIONEMPLOYE(3),
    NOMBREKILOMETREALERTE(10000);

    private final int valeur;

    Variable(int valeur){
        this.valeur = valeur;
    }
    public int getValeur(){return valeur;}
}
