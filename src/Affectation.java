public class Affectation {
    //Attributs
    Vehicule vehicule;
    Employe employe;
    String dateAffectation;
    String dateRetour;
    boolean actif;


    //Constructeur
    public Affectation(Vehicule vehicule, Employe employe, String dateAffectation, String dateRetour, boolean actif){
        this.vehicule = vehicule;
        this.employe = employe;
        this.dateAffectation = dateAffectation;
        this.dateRetour = dateRetour;
        this.actif = actif;
    }

    //getter
    public Vehicule getVehicule(){return this.vehicule;}
    public Employe getEmploye(){return this.employe;}
    public String getDateAffectation(){return this.dateAffectation;}
    public String getDateRetour(){return this.dateRetour;}
    public boolean getActif(){return this.actif;}

    //setter
    public void setVehicule(Vehicule newVehicule){this.vehicule = newVehicule;}
    public void setEmploye(Employe newEmploye){this.employe = newEmploye;}
    public void setDateAffectation(String newDateAffectation){this.dateAffectation = newDateAffectation;}
    public void setDateRetour(String newDateRetour){this.dateRetour = newDateRetour;}
    public void setActif(boolean newActif){this.actif = newActif;}

    //méthodes
    public void afficherAffectation(){

    }

    public void terminerAffectation(){

    }
}
