final class Moto extends Vehicule{
    private int cylindre;

    public Moto(int id, String marque, String modele, String immatriculation, int kilometrage, int cylindre){
        super( id, marque,  modele,  immatriculation,  kilometrage);
        this.cylindre = cylindre;
    }
}
