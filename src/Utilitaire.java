final class Utilitaire extends Vehicule {
    private int taille;

    public Utilitaire(int id, String marque, String modele, String immatriculation, int kilometrage, int taille){
        super( id, marque,  modele,  immatriculation,  kilometrage);
        this.taille = taille;
    }

    //@Override
}
