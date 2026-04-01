final class Voiture extends Vehicule{
    private int nbPorte;

    public Voiture(int id, String marque, String modele, String immatriculation, int kilometrage, int nbPorte){
        super( id, marque,  modele,  immatriculation,  kilometrage);
        this.nbPorte = nbPorte;
    }
}
