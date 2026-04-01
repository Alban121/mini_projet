final class Voiture extends Vehicule{
    public static int nbVoiture = 0;
    private int nbPorte;

    public Voiture(int id, String marque, String modele, String immatriculation, int kilometrage, int nbPorte){
        super( id, marque,  modele,  immatriculation,  kilometrage);
        this.nbPorte = nbPorte;
        nbVoiture++;
    }

    @Override
    public String toString() {
        return super.toString() + "\n|Nombre de porte : " + this.nbPorte ;
    }
}