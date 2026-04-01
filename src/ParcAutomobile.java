import java.time.LocalDateTime;
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
                    menuEmploye();
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

        do {
            System.out.println("\n=== GESTION DES AFFECTATIONS ===");
            System.out.println("1. Afficher les affectations");
            System.out.println("2. Ajouter une affectation ");
            System.out.println("3. Terminer une affectation ");
            System.out.println("4. Historique des affectations");
            System.out.println("5. Retour au menu principal");
            int choix = faireChoix("Choisissez une option : ", 1, 5);
            switch (choix) {
                case 1:
                    afficherAffectation(true);//true pour les actives
                    break;
                case 2:
                    Affectation affectation = creerAffectation();
                    if (affectation != null) {
                        ajouterAffectation(affectation);
                    }
                    break;
                case 3:
                    if (!finirAffectation(recupererEntier("ID de l affectation a terminer ? La date actuelle sera applique en temps que date de retour"))){
                        System.out.println("Id introuvable, impossible de terminer l affectation");
                    }
                    break;
                case 4:
                    afficherAffectation(false);//false pour l'historique
                    break;
                case 5:
                    return;
            }

        } while (true);
    }

    public void menuVoiture() {
        do {
            System.out.println("\n=== GESTION DES VEHICULES ===");
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
                    supprimerVehicule(recupererEntier("ID du vehicule a supprime ?"));
                    break;
                case 4:
                    return;
            }

        } while (true);
    }

    public void menuEmploye(){
        do {
            System.out.println("\n=== GESTION DES EMPLOYES ===");
            System.out.println("1. Afficher les employes");
            System.out.println("2. Ajouter un employe ");
            System.out.println("3. Retirer un employe");
            System.out.println("4. Retour au menu principal");
            int choix = faireChoix("Choisissez une option : ", 1, 4);

            switch (choix) {
                case 1:
                    afficherEmploye();
                    break;
                case 2:
                    ajouterEmploye(creerEmploye());
                    break;
                case 3:
                    supprimerEmploye(recupererEntier("ID de l employe a supprime ?"));
                    break;
                case 4:
                    return;
            }

        } while (true);
    }

    //Vehicule
    public Vehicule creerVehicule() {
        System.out.println("--- Ajout d'un nouveau véhicule ---");

        // 1. On demande le type en premier
        System.out.println("Quel type de véhicule souhaitez-vous ajouter ?");
        System.out.println("1. Voiture");
        System.out.println("2. Utilitaire");
        System.out.println("3. Moto");
        int type = faireChoix("Votre choix : ", 1, 3);

        int id = recupererEntier("Entrez l ID : ");
        String marque = recupererString("Entrez la marque : ");
        String modele = recupererString("Entrez le modele : ");
        String immatriculation = recupererString("Entrez l immatriculation : ");
        int kilometrage = recupererEntier("Entrez le killometrage : ");

        if (type == 1) {
            int portes = recupererEntier("Entrez le nombre de portes : ");
            return new Voiture(id, marque, modele, immatriculation, kilometrage, portes);
        }
        else if (type == 2) {
            int taille = recupererEntier("Entrez la taille de l utilitaire (en cm) : ");
            return new Utilitaire(id, marque, modele, immatriculation, kilometrage, taille);
        }
        else {
            int cylindree = recupererEntier("Entrez la cylindrée (en cc) : ");
            return new Moto(id,  marque, modele, immatriculation, kilometrage, cylindree);
        }
    }

    public void ajouterVehicule(Vehicule newVehicule) {
        listeVehicule.add(newVehicule);
        System.out.println("Un nouveau vehicule a ete ajoute.");
        //listeVehicule.sort(Comparator.comparing(Vehicule::getMarque));
        //le tri est a mettre ici ou dans l'affichage au choix
    }

    public void supprimerVehicule(int id) {
        if (listeVehicule.removeIf(vehicule -> vehicule.getId() == id && vehicule.getDisponible())) {
            System.out.println("Vehicule d id " + id + " a ete supprime avec succes.");
        } else {
            System.out.println("Le vehicule d id " + id + " est introuvable ou actuellement affecte, suppresion impossible.");
        }
    }

    public void retournerVehicule(int id) {
        Vehicule aRetourner = null;
        for (Vehicule vehicule : listeVehicule) {
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
        }else {
            System.out.println("Aucun vehicule enregistre dans la parc automobile");
        }
    }

    public Vehicule rechercherVehiculeId(int id) {
        for (Vehicule vehicule : listeVehicule) {
            if (vehicule.getId() == id) {
                return vehicule;
            }
        }
        return null;
    }

    public Vehicule rechercherVehiculeImmatriculation(String immatriculation) {
        for (Vehicule vehicule : listeVehicule) {
            // Une plaque d'immatriculation est unique ! Donc on retourne directement l'objet
            if (vehicule.getImmatriculation().equalsIgnoreCase(immatriculation)) {
                return vehicule;
            }
        }
        return null;
    }

    public ArrayList<Vehicule> rechercherVehiculeKilometrage(int kilometrageMax) {
        ArrayList<Vehicule> resultats = new ArrayList<>();
        for (Vehicule vehicule : listeVehicule) {
            if (vehicule.getKilometrage() <= kilometrageMax) {
                resultats.add(vehicule);
            }
        }
        return resultats;
    }

    public ArrayList<Vehicule> rechercherVehiculeMarque(String marque) {
        ArrayList<Vehicule> resultats = new ArrayList<>();
        for (Vehicule vehicule : listeVehicule) {
            if (vehicule.getMarque().equalsIgnoreCase(marque)) {
                resultats.add(vehicule);
            }
        }
        return resultats;
    }

    public ArrayList<Vehicule> rechercherVehiculeModele(String modele) {
        ArrayList<Vehicule> resultats = new ArrayList<>();
        for (Vehicule vehicule : listeVehicule) {
            if (vehicule.getModele().equalsIgnoreCase(modele)) {
                resultats.add(vehicule);
            }
        }
        return resultats;
    }

    //Employe
    public Employe creerEmploye(){
        System.out.println("--- Creation d un nouvel employe ---");
        int id = recupererEntier("Entrez l ID : ");//rajouter un check sur l id pour qu il soit unique
        String nom = recupererString("Entrez le nom : ");
        String prenom = recupererString("Entrez le prenom : ");
        String poste = recupererString("Entrez le poste : ");
        String email = recupererString("Entrez l email : ");
        return new Employe(id, nom, prenom, poste, email);
    }

    public void ajouterEmploye(Employe newEmploye) {
        listeEmploye.add(newEmploye);
        System.out.println("Un nouvel employe a ete ajoute.");
        //listeEmploye.sort(Comparator.comparing(Employe::getNom));
    }


    public void supprimerEmploye(int id) {
        for (Affectation affectation : listeAffectation) {
            if (affectation.getEmploye().getId() == id && affectation.getActif()) {
                System.out.println("Suppression impossible : Cet employe a au moins une affectation en cours !");
                return;
            }
        }

        if (listeEmploye.removeIf(employe -> employe.getId() == id)) {
            System.out.println("Employe d id " + id + " a ete supprime avec succes.");
        } else {
            System.out.println("Employe introuvable.");
        }
    }

    public void afficherEmploye() {
        if (!this.listeEmploye.isEmpty()) {
            listeEmploye.sort(Comparator.comparing(Employe::getNom));
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

    public Employe rechercherEmployeId(int id) {
        for (Employe employe : listeEmploye) {
            if (employe.getId() == id) {
                return employe;
            }
        }
        return null;
    }

    public ArrayList<Employe> rechercherEmployesParNom(String nom) {
        ArrayList<Employe> resultats = new ArrayList<>();
        for (Employe employe : listeEmploye) {
            if (employe.getNom().equalsIgnoreCase(nom)) {
                resultats.add(employe);
            }
        }
        return resultats;
    }

    public ArrayList<Employe> rechercherEmployesParPrenom(String prenom) {
        ArrayList<Employe> resultats = new ArrayList<>();
        for (Employe employe : listeEmploye) {
            if (employe.getPrenom().equalsIgnoreCase(prenom)) {
                resultats.add(employe);
            }
        }
        return resultats;
    }

    public ArrayList<Employe> rechercherEmployesParPoste(String poste) {
        ArrayList<Employe> resultats = new ArrayList<>();
        for (Employe employe : listeEmploye) {
            if (employe.getPoste().equalsIgnoreCase(poste)) {
                resultats.add(employe);
            }
        }
        return resultats;
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
                if (affectation.getActif() == active) {
                    System.out.println(affectation);
                    auMoinsUn = true;
                }
            }
            if (!auMoinsUn) {
                System.out.println(messageVide);
            }
        }
    }

    public Affectation creerAffectation() {
        if (listeVehicule.isEmpty() || listeEmploye.isEmpty()) {
            System.out.println("Il n y a pas de vehicule disponible ou d employe");
            return null;
        }

        System.out.println("=== CREATION D UNE NOUVLLE AFFECTATION ===");
        while (true) {
            int idEmploye = recupererEntier("Quel est l ID de l employe ?");
            int idVehicule = recupererEntier("Quel est l ID du vehicule");
            int idAffectation = recupererEntier("Quel est l id de l affectation");

            Vehicule vehicule = rechercherVehiculeId(idVehicule);
            Employe employe = rechercherEmployeId(idEmploye);
            if (vehicule != null && employe != null) {
                if (vehicule.getDisponible()){
                    vehicule.setDisponible(false);
                    return new Affectation(idAffectation, vehicule, employe, LocalDateTime.now(), null, true);
                } else {
                    System.out.println("Vehicule choisi indisponible");
                }
            }else {
                System.out.println("ID employe ou ID vehicule introuvable.");
            }
            int choix = recupererEntier("Tapez 0 pour reessayer, ou 1 pour annuler et quitter : ");
            if (choix == 1) {
                System.out.println("Création d affectation annulee.");
                return null;
            }
        }

    }

    public void ajouterAffectation(Affectation nouvelleAffectation) {
        listeAffectation.add(nouvelleAffectation);
    }

    public boolean finirAffectation(int id){
        for (Affectation affectation : listeAffectation){
            if (affectation.getId() == id){
                affectation.setActif(false);
                affectation.getVehicule().setDisponible(true);
                affectation.setDateRetour(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

}
