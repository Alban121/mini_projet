public sealed abstract class Vehicule permits Utilitaire, Moto, Voiture {
    //Attributs
    public static int nbVehicule = 0;
    private final int id;
    private String marque;
    private String modele;
    private String immatriculation;
    private int kilometrage;
    private boolean disponible;

    //Constructeurs
    public Vehicule(int id, String marque, String modele, String immatriculation, int kilometrage, boolean disponible){
        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.kilometrage = kilometrage;
        this.disponible = disponible;
        ParcAutomobile.nbVehiculeDisponible++;
        Vehicule.nbVehicule++;
    }
    public Vehicule(int id, String marque, String modele, String immatriculation, int kilometrage){
        this(id, marque, modele, immatriculation, kilometrage, true);//appelle le contructeur precedent avec disponible vrai
    }

    //Getter
    public int getId(){return this.id;}
    public String getMarque(){return this.marque;}
    public String getModele(){return this.modele;}
    public String getImmatriculation(){return this.immatriculation;}
    public int getKilometrage(){return this.kilometrage;}
    public boolean getDisponible(){return this.disponible;}

    //Setter
    public void setMarque(String newMarque) {
        this.marque = newMarque;
    }
    public void setModele(String newModele) {
        this.modele = newModele;
    }
    public void setImmatriculation(String newImmatriculation) {
        this.immatriculation = newImmatriculation;
    }
    public void setKilometrage(int newKilometrage) {
        if (newKilometrage < this.kilometrage) {
            System.out.println("La valeur de kilometrage ne peut pas etre inferieur a la valeur precedente\n");
        }else {
            this.kilometrage = newKilometrage;
        }
    }
    public void setDisponible(boolean newDisponible) {
        this.disponible = newDisponible;
    }

    public String toCSV() {//Pour avoir le format du stockage dans le .txt
        return this.getClass().getSimpleName() + ";" + this.getId() + ";" + this.getMarque() + ";" + this.getModele() + ";" + this.getImmatriculation() + ";" + this.getKilometrage();
    }
    //Methode
    @Override
    public String toString() {
        return "Information vehicule n°" + this.id +
                "\n|Marque : " + this.marque +
                "\n|Modele : " + this.modele +
                "\n|Immatriculation : " + this.immatriculation +
                "\n|Kilometrage : " + this.kilometrage +
                "\n|Disponible : " + (this.disponible ? "oui" : "non");
    }
}