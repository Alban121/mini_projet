public class Vehicule {
    //Attributs
    private int id;
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
    }
    public Vehicule(int id, String marque, String modele, String immatriculation, int kilometrage){
        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.kilometrage = kilometrage;
        this.disponible = true;
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
        String marqueTemoin = this.marque;
        this.marque = newMarque;
        if (marqueTemoin != null && marqueTemoin.equals(this.marque)) {
            System.out.println("La valeur de marque est la meme\n");
        }
    }
    public void setModele(String newModele) {
        String modeleTemoin = this.modele;
        this.modele = newModele;
        if (modeleTemoin != null && modeleTemoin.equals(this.modele)) {
            System.out.println("La valeur de modele est la meme\n");
        }
    }
    public void setImmatriculation(String newImmatriculation) {
        String immatriculationTemoin = this.immatriculation;
        this.immatriculation = newImmatriculation;
        if (immatriculationTemoin != null && immatriculationTemoin.equals(this.immatriculation)) {
            System.out.println("La valeur de immatriculation est la meme\n");
        }
    }
    public void setKilometrage(int newKilometrage) {
        int kilometrageTemoin = this.kilometrage;
        this.kilometrage = newKilometrage;
        if (kilometrageTemoin == this.kilometrage) {
            System.out.println("La valeur de kilometrage est la meme\n");
        }
    }
    public void setDisponible(boolean newDisponible) {
        boolean disponibleTemoin = this.disponible;
        this.disponible = newDisponible;
        if (disponibleTemoin == this.disponible) {
            System.out.println("La valeur de disponible est la meme\n");
        }
    }

    //Methode
    public void afficherdetail(){
        System.out.println("Information vehicule n°" + this.id + "\n|Marque : " + this.marque + "\n|Modele : " + this.modele + "\n|Immatriculation : " + this.immatriculation + "\n|Kilometrage : " + this.kilometrage + "\n|Disponible : " + ((this.disponible) ? "oui" : "non"));
    }
}
