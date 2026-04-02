final class Moto extends Vehicule{
    public static int nbMoto = 0;
    private int cylindre;

    public Moto(int id, String marque, String modele, String immatriculation, int kilometrage, int cylindre){
        super( id, marque,  modele,  immatriculation,  kilometrage);
        this.cylindre = cylindre;
        nbMoto++;
    }

    @Override
    public String toCSV() {
        return super.toCSV() + ";" + this.cylindre;
    }
    @Override
    public String toString() {
        return super.toString() + "\n|Cylindre : " + this.cylindre ;
    }
}