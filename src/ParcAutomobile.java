import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

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
        //Menu de gestion
    public void menuGestion(){
        boolean continuer = true;

        do {
            int choix = 0;
            Scanner scanner = new Scanner(System.in);

            System.out.println("\n=== GESTION DU PARC AUTOMOBILE ===");
            System.out.println("1. Gérer les Véhicules");
            System.out.println("2. Gérer les Employés");
            System.out.println("3. Gérer les Affectations");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option : ");
            choix = scanner.nextInt();

            switch (choix){
                case 1:
                    break;
                case 2 :
                    break;
                case 3:
                    break;
                case 4:
                    continuer = false;
                    break;
            }

        }while(continuer);
    }
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

    public void rechercherVehiculeId(int id){
        for (Vehicule vehicule : listeVehicule){
            if (vehicule.getId() == id){
                vehicule.afficherDetail();
                break; // break car seulement un vehicule par id
            }
        }
    }
    public void rechercherVehiculeKilometrage(int kilometrage){
        boolean auMoinsUn = true;
        for (Vehicule vehicule : listeVehicule){
            if (vehicule.getKilometrage() <= kilometrage){
                if (auMoinsUn){
                    System.out.println("Vehicule ayant moins de " + kilometrage + " kilometres.");
                    auMoinsUn = false;
                }
                vehicule.afficherDetail();
            }
        }
        if (auMoinsUn){
            System.out.println("Aucun vehicule n'a moins de " + kilometrage + " kilometres.");
        }
    }
    public void rechercherVehiculeMarque(String marque){
        for (Vehicule vehicule : listeVehicule){
            if (vehicule.getMarque().equals(marque)){
                vehicule.afficherDetail();
            }
        }
    }
    public void rechercherVehiculeModele(String modele){
        for (Vehicule vehicule : listeVehicule){
            if (vehicule.getMarque().equals(modele)){
                vehicule.afficherDetail();
            }
        }
    }
    public void rechercherVehiculeImmatriculation(String immatricualtion){
        for (Vehicule vehicule : listeVehicule){
            if (vehicule.getMarque().equals(immatricualtion)){
                vehicule.afficherDetail();
                break;//car unique
            }
        }
    }

        //Employe
    public void ajouterEmploye(Employe newEmploye){
        listeEmploye.add(newEmploye);
        System.out.println("Un nouvel employe a ete ajoute.\nSon id est : " + newEmploye.getId() + ".");
        listeEmploye.sort(Comparator.comparing(Employe::getNom));
    }

    public void supprimerEmploye(int id){
        boolean supprimer = listeEmploye.removeIf(employe -> employe.getId() == id);
        if (supprimer){
            System.out.println("Employe d id " + id + " a ete supprime avec succes.");
        }else {
            System.out.println("Employe d id " + id + " introuvable, suppresion impossible.");
        }
    }

    public void afficherEmploye(){
        if (!this.listeEmploye.isEmpty()) {
            int compteur = 0;
            System.out.println("Employe du parc automobile : " +
                    "\n(pour plus d informations sur un employe en particulier utilise la fonction de recherche par id)");
            System.out.println("\tID\t\tNom\t\tPrenom");
            for (Employe employe : listeEmploye) {
                System.out.println("\t" + employe.getId() + "\t\t" + employe.getNom() + "\t\t" + employe.getPrenom());
            }
        }else {
            System.out.println("Il n'y a aucun employe dans le parc automobile");
        }
    }

    public void rechercherEmployeId(int id){
        for (Employe employe : listeEmploye){
            if (employe.getId() == id){
                employe.afficherDetail();
                break; // break car seulement un vehicule par id
            }
        }
    }
    public void rechercherEmployeNom(String nom){
        for (Employe employe : listeEmploye){
            if (employe.getNom().equals(nom)){
                employe.afficherDetail();
            }
        }
    }
    public void rechercherEmployePrenom(String prenom){
        for (Employe employe : listeEmploye){
            if (employe.getNom().equals(prenom)){
                employe.afficherDetail();
            }
        }
    }
    public void rechercherEmployePoste(String poste){
        for (Employe employe : listeEmploye){
            if (employe.getNom().equals(poste)){
                employe.afficherDetail();
            }
        }
    }

}
