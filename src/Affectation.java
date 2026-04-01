import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Affectation {
    //Attributs
    private int id;
    private Vehicule vehicule;
    private Employe employe;
    private LocalDateTime dateAffectation;
    private LocalDateTime dateRetour;
    private boolean actif;


    //Constructeur
    public Affectation(int id, Vehicule vehicule, Employe employe, LocalDateTime dateAffectation, LocalDateTime dateRetour, boolean actif) {
        this.id = id;
        this.vehicule = vehicule;
        this.employe = employe;
        this.dateAffectation = dateAffectation;
        this.dateRetour = dateRetour;
        this.actif = actif;
    }

    //getter
    public int getId() {
        return this.id;
    }

    public Vehicule getVehicule() {
        return this.vehicule;
    }

    public Employe getEmploye() {
        return this.employe;
    }

    public LocalDateTime getDateAffectation() {
        return this.dateAffectation;
    }

    public LocalDateTime getDateRetour() {
        return this.dateRetour;
    }

    public boolean getActif() {
        return this.actif;
    }

    //setter
    public void setVehicule(Vehicule newVehicule) {
        this.vehicule = newVehicule;
    }

    public void setEmploye(Employe newEmploye) {
        this.employe = newEmploye;
    }

    public void setDateAffectation(LocalDateTime newDateAffectation) {
        this.dateAffectation = newDateAffectation;
    }

    public void setDateRetour(LocalDateTime newDateRetour) {
        this.dateRetour = newDateRetour;
    }

    public void setActif(boolean newActif) {
        this.actif = newActif;
    }

    //méthodes

    @Override
    public String toString() {
        DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/yyyy 'à' HH:mm");
        String dateAffectationFormate = this.dateAffectation.format(formateur);
        String dateRetourFormate = "L affectation est encore active";
        if (this.dateRetour != null) {
            dateRetourFormate = this.dateRetour.format(formateur);
        }
        return "=== Détail de l'Affectation " + this.id + " ===\n" +
                "| Statut         : " + (this.actif ? "EN COURS" : "TERMINÉE") + "\n" +
                "| Employé        : " + this.employe.getPrenom() + " " + this.employe.getNom() + "\n" +
                "| "+ this.vehicule.getClass().getSimpleName() +"       : " + this.vehicule.getMarque() + " " + this.vehicule.getModele() + " (" + this.vehicule.getImmatriculation() + ")\n" +
                "| Date de début  : " + dateAffectationFormate + "\n" +
                "| Date de retour : " + dateRetourFormate + "\n" +
                "===============================";
    }
}
