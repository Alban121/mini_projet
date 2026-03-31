import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ParcAutomobile {
    //Attributs
    private ArrayList<Vehicule> listeVehicule;
    private ArrayList<Employe> listeEmploye;
    private ArrayList<Affectation> listeAffectation;

    //Constructeurs
    public ParcAutomobile() {
        this.listeVehicule = new ArrayList<>();
        this.listeEmploye = new ArrayList<>();
        this.listeAffectation = new ArrayList<>();
    }

    //Methodes
    //Helpers

    private int recupererEntier(String entierDemander) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(entierDemander);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException erreur) {
                System.out.println("La saisie n'est pas valide. Veuillez reessayez.");
            }
        }
    }
    private String recupererString(String stringDemande) {
        Scanner scanner = new Scanner(System.in);
        boolean valide = true;
        String stringRecupere = "";
        while (valide) {
            System.out.println(stringDemande);
            stringRecupere = scanner.nextLine();
            if (stringRecupere.trim().isEmpty()) {
                System.out.println("Saisissez une chaine de caractere");
            } else {
                valide = false;
            }
        }
        return stringRecupere.trim();
    }
    private int faireChoix(String message, int min, int max) {
        do {
            int choix = recupererEntier(message);
            if (choix >= min && choix <= max) {
                return choix;
            }
            System.out.println("Choix invalide. Reessayez");
        } while (true);
    }

    //Menu
    public void menuGestion() {
        do {
            System.out.println("\n=== GESTION DU PARC AUTOMOBILE ===");
            System.out.println("1. Gérer les Véhicules");
            System.out.println("2. Gérer les Employés");
            System.out.println("3. Gérer les Affectations");
            System.out.println("4. Quitter");
            int choix = faireChoix("Choisissez une option : ", 1, 4);
            switch (choix) {
                case 1:
                    menuVoiture();
                    break;
                case 2:
                    break;
                case 3:
                    menuAffectation();
                    break;
                case 4:
                    return;
            }
        } while (true);
    }

    public void menuAffectation() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\n=== GESTION DES AFFECTATIONS ===");
            System.out.println("1. Afficher les affectations");
            System.out.println("2. Ajouter une affectation ");
            System.out.println("3. Historique des affectations");
            System.out.println("4. Retour au menu principal");
            int choix = faireChoix("Choisissez une option : ", 1, 4);
            switch (choix) {
                case 1:
                    afficherAffectation(true);//true pour les actives
                    break;
                case 2:
                    ajouterAffectation();
                    break;
                case 3:
                    afficherAffectation(false);//false pour l'historique
                    break;
                case 4:
                    return;
            }

        } while (true);
    }

    public void menuVoiture() {
        do {
            System.out.println("\n=== GESTION DES VEHICULE ===");
            System.out.println("1. Afficher les vehicules");
            System.out.println("2. Ajouter un vehicule ");
            System.out.println("3. Retirer un vehicule");
            System.out.println("4. Retour au menu principal");
            int choix = faireChoix("Choisissez une option : ", 1, 4);

            switch (choix) {
                case 1:
                    afficherVehicule();
                    break;
                case 2:
                    ajouterVehicule(creerVehicule());
                    break;
                case 3:
                    afficherAffectation(false);
                    break;
                case 4:
                    return;
            }

        } while (true);
    }

    //Vehicule
    public Vehicule creerVehicule() {
        System.out.println("--- Ajout d'un nouveau véhicule ---");
        int id = recupererEntier("Entrez l ID : ");
        String marque = recupererString("Entrez la marque : ");
        String modele = recupererString("Entrez le modele : ");
        String immatriculation = recupererString("Entrez l immatriculation : ");
        int kilometrage = recupererEntier("Entrez le killometrage : ");
        return new Vehicule(id, modele, modele, immatriculation, kilometrage);
    }

    public void ajouterVehicule(Vehicule newVehicule) {
        listeVehicule.add(newVehicule);
        System.out.println("Un nouveau vehicule a ete ajoute.");
        //listeVehicule.sort(Comparator.comparing(Vehicule::getMarque));
        //le tri est a mettre ici ou dans l'affichage au choix
    }

    public void supprimerVehicule(int id) {
        boolean supprimer = listeVehicule.removeIf(vehicule -> vehicule.getId() == id);
        if (supprimer) {
            System.out.println("Vehicule d id " + id + " a ete supprime avec succes.");
        } else {
            System.out.println("Vehicule d id " + id + " introuvable, suppresion impossible.");
        }
    }

    public void retournerVehicule(int id) {
        Vehicule aRetourner = null;
        for (Vehicule vehicule : this.listeVehicule) {
            if (vehicule.getId() == id) {
                aRetourner = vehicule;
                break;
            }
        }
        if (aRetourner != null) {
            aRetourner.setDisponible(true);
            System.out.println("Vehicule d id : " + id + " retourner avec succes.");
        } else {
            System.out.println("Vehicule d id : " + id + " introuvable, retour impossible.");
        }
    }

    public void afficherVehicule() {
        if (!listeVehicule.isEmpty()) {
            listeVehicule.sort(Comparator.comparing(Vehicule::getMarque));
            int compteur = 0;
            System.out.println("Vehicule du parc automobile : " +
                    "\n(pour plus d informations sur un vehicule en particulier utilise la fonction de recherche par id)");
            System.out.println("\n Actuellement disponible : ");
            System.out.println("\tID\t\tMarque\t\tModele");
            for (Vehicule vehicule : listeVehicule) {
                if (vehicule.getDisponible()) {
                    System.out.println("\t" + vehicule.getId() + "\t\t" + vehicule.getMarque() + "\t\t" + vehicule.getModele());
                    compteur++;
                }
            }

            if (compteur != listeVehicule.size()) {
                if (compteur == 0) {
                    System.out.println("Aucune voiture disponible pour le moment");
                }
                System.out.println("\n Actuellement indisponible : ");
                System.out.println("\tID\t\tMarque\t\tModele");
                for (Vehicule vehicule : listeVehicule) {
                    if (!vehicule.getDisponible()) {
                        System.out.println("\t" + vehicule.getId() + "\t\t" + vehicule.getMarque() + "\t\t" + vehicule.getModele());
                    }
                }
            } else {
                System.out.println("\nTous les vehicules sont actuellement disponibles");
            }
        }
    }

    public void rechercherVehiculeId(int id) {
        for (Vehicule vehicule : listeVehicule) {
            if (vehicule.getId() == id) {
                vehicule.afficherDetail();
                break; // break car seulement un vehicule par id
            }
        }
    }

    public void rechercherVehiculeKilometrage(int kilometrage) {
        boolean auMoinsUn = true;
        for (Vehicule vehicule : listeVehicule) {
            if (vehicule.getKilometrage() <= kilometrage) {
                if (auMoinsUn) {
                    System.out.println("Vehicule ayant moins de " + kilometrage + " kilometres.");
                    auMoinsUn = false;
                }
                vehicule.afficherDetail();
            }
        }
        if (auMoinsUn) {
            System.out.println("Aucun vehicule n'a moins de " + kilometrage + " kilometres.");
        }
    }

    public void rechercherVehiculeMarque(String marque) {
        for (Vehicule vehicule : listeVehicule) {
            if (vehicule.getMarque().equals(marque)) {
                vehicule.afficherDetail();
            }
        }
    }

    public void rechercherVehiculeModele(String modele) {
        for (Vehicule vehicule : listeVehicule) {
            if (vehicule.getModele().equals(modele)) {
                vehicule.afficherDetail();
            }
        }
    }

    public void rechercherVehiculeImmatriculation(String immatricualtion) {
        for (Vehicule vehicule : listeVehicule) {
            if (vehicule.getImmatriculation().equals(immatricualtion)) {
                vehicule.afficherDetail();
                break;//car unique
            }
        }
    }

    //Employe
    public void ajouterEmploye(Employe newEmploye) {
        listeEmploye.add(newEmploye);
        System.out.println("Un nouvel employe a ete ajoute.\nSon id est : " + newEmploye.getId() + ".");
        listeEmploye.sort(Comparator.comparing(Employe::getNom));
    }

    public void supprimerEmploye(int id) {
        boolean supprimer = listeEmploye.removeIf(employe -> employe.getId() == id);
        if (supprimer) {
            System.out.println("Employe d id " + id + " a ete supprime avec succes.");
        } else {
            System.out.println("Employe d id " + id + " introuvable, suppresion impossible.");
        }
    }

    public void afficherEmploye() {
        if (!this.listeEmploye.isEmpty()) {
            int compteur = 0;
            System.out.println("Employe du parc automobile : " +
                    "\n(pour plus d informations sur un employe en particulier utilise la fonction de recherche par id)");
            System.out.println("\tID\t\tNom\t\tPrenom");
            for (Employe employe : listeEmploye) {
                System.out.println("\t" + employe.getId() + "\t\t" + employe.getNom() + "\t\t" + employe.getPrenom());
            }
        } else {
            System.out.println("Il n'y a aucun employe dans le parc automobile");
        }
    }

    public void rechercherEmployeId(int id) {
        for (Employe employe : listeEmploye) {
            if (employe.getId() == id) {
                employe.afficherDetail();
                break; // break car seulement un vehicule par id
            }
        }
    }

    public void rechercherEmployeNom(String nom) {
        for (Employe employe : listeEmploye) {
            if (employe.getNom().equals(nom)) {
                employe.afficherDetail();
            }
        }
    }

    public void rechercherEmployePrenom(String prenom) {
        for (Employe employe : listeEmploye) {
            if (employe.getPrenom().equals(prenom)) {
                employe.afficherDetail();
            }
        }
    }

    public void rechercherEmployePoste(String poste) {
        for (Employe employe : listeEmploye) {
            if (employe.getNom().equals(poste)) {
                employe.afficherDetail();
            }
        }
    }

    //Affectation
        /*public void afficherAffectation() {
            // 1. On vérifie d'abord s'il y a au moins une affectation active
            boolean aUneAffectationActive = listeAffectation.stream()
                    .anyMatch(Affectation::getActif);

            // 2. On affiche le résultat en fonction
            if (!aUneAffectationActive) {
                System.out.println("Aucune affectation actuellement en cours.");
            } else {
                System.out.println("=== Affectations en cours ===");
                listeAffectation.stream()
                        .filter(Affectation::getActif) // On garde que les actives
                        .forEach(Affectation::afficherDetail); // On les affiche
            }
        }*/
    public void afficherAffectation(boolean active) {//True pour afficher les actives, False pour l'historique
        String messageVide = (active ? "Aucune affectation actuellement en cours." : "Aucune affectation dans l'historique.");
        String titre = (active ? "=== Affectations en cours ===" : "=== Historique des affectations ===");

        if (listeAffectation.isEmpty()) {
            System.out.println(messageVide);
        } else {
            boolean auMoinsUn = false;
            System.out.println(titre);

            for (Affectation affectation : listeAffectation) {
                if (affectation.getActif()) {
                    affectation.afficherDetail();
                    auMoinsUn = true;
                }
            }
            if (!auMoinsUn) {
                System.out.println(messageVide);
            }
        }
    }

    public void ajouterAffectation() {
        System.out.println("Pas code");
    }

}
