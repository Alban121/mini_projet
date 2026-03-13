import java.util.ArrayList;
import java.util.Comparator;

public class ParcAutomobile {
    //Attributs
    private ArrayList<Vehicule> listeVehicule;
    private ArrayList<Employe> listeEmploye;
    private ArrayList<Affectation> listeAffectation;

    //Constructeurs
    public ParcAutomobile(){
        this.listeVehicule = new ArrayList<>();
        this.listeEmploye = new ArrayList<>();
        this.listeAffectation = new ArrayList<>();
    }

    //Methodes
        //Vehicule
    public void ajouterVehicule(Vehicule newVehicule){
        this.listeVehicule.add(newVehicule);
        System.out.println("Un nouveau vehicule a ete ajoute.\nSon id est : " + newVehicule.getId() + ".");
        this.listeVehicule.sort(Comparator.comparing(Vehicule::getMarque));
    }

    public void supprimerVehicule(int id){
        boolean supprimer = this.listeVehicule.removeIf(vehicule -> vehicule.getId() == id);
        if (supprimer){
            System.out.println("Vehicule d id " + id + " a ete supprime avec succes.");
        }else {
            System.out.println("Vehicule d id " + id + " introuvable, suppresion impossible.");
        }
    }

    public void retournerVehicule(int id){
        Vehicule aRetourner = null;
        for (Vehicule vehicule : this.listeVehicule){
            if (vehicule.getId() == id){
                aRetourner = vehicule;
                break;
            }
        }
        if (aRetourner != null){
            aRetourner.setDisponible(true);
            System.out.println("Vehicule d id : " + id + " retourner avec succes.");
        }else{
            System.out.println("Vehicule d id : " + id + " introuvable, retour impossible.");
        }
    }

    public void afficherVehicule(){
        if (!this.listeVehicule.isEmpty()) {
            int compteur = 0;
            System.out.println("Vehicule du parc automobile : " +
                    "\n(pour plus d informations sur un vehicule en particulier utilise la fonction de recherche par id)");
            System.out.println("\n Actuellement disponible : ");
            System.out.println("\tID\t\tMarque\t\tModele");
            for (Vehicule vehicule : this.listeVehicule) {
                if (vehicule.getDisponible()) {
                    System.out.println("\t" + vehicule.getId() + "\t\t" + vehicule.getMarque() + "\t\t" + vehicule.getModele());
                    compteur++;
                }
            }
            if (compteur != this.listeVehicule.size()) {
                System.out.println("\n Actuellement indisponible : ");
                System.out.println("\tID\t\tMarque\t\tModele");
                for (Vehicule vehicule : this.listeVehicule) {
                    if (!vehicule.getDisponible()) {
                        System.out.println("\t" + vehicule.getId() + "\t\t" + vehicule.getMarque() + "\t\t" + vehicule.getModele());
                    }
                }
            }else {
                System.out.println("\nTous les vehicules sont actuellement disponibles");
            }
        }
    }

}
