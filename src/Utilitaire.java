final class Utilitaire extends Vehicule {
    public static int nbUtilitaire = 0;
    private int taille;

    public Utilitaire(int id, String marque, String modele, String immatriculation, int kilometrage, int taille){
        super( id, marque,  modele,  immatriculation,  kilometrage);
        this.taille = taille;
        nbUtilitaire++;
    }
    public int getTaille(){
        return taille;
    }
    public void setTaille(int taille){
        if (taille < 0){
            return;
        }
        this.taille = taille;
    }

    @Override
    public String toCSV() {
        return super.toCSV() + ";" + this.taille;
    }

    @Override
    public String toString() {
        return super.toString() + "\n|Taille : " + this.taille ;
    }
}