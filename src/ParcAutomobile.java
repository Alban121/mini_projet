import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class ParcAutomobile {
    //Attributs
    private final ArrayList<Vehicule> listeVehicule;
    private final ArrayList<Employe> listeEmploye;
    private final ArrayList<Affectation> listeAffectation;
    public static int nbVehiculeDisponible = 0;

    // RESET = "\u001B[0m";
    // ROUGE = "\u001B[31m";

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
            System.out.println("4. Enregistre les modificationts dans la base de donnee (fichier.txt)");// Le mieux serait de le faire automatiquement quand on quitte le projet mais nous n'avons pas envie d'avoir des donnees de tests dans nos fichier .txt
            System.out.println("5. Quitter");
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
                    sauvegarderEmployes();
                    sauvegarderVehicules();
                    System.out.println("\n Sauvegarde reussie.");
                    break;
                case 5:
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
            System.out.println("5. Rechercher une affectation");
            System.out.println("6. Modifier une affectation");
            System.out.println("7. Retour au menu principal");
            int choix = faireChoix("Choisissez une option : ", 1, 7);
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
                    System.out.println(rechercherAffectationID(recupererEntier("Quel est l Id de l affectation recherche ?")));
                    break;
                case 6:
                    modifierAffectation();
                    break;
                case 7:
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
            System.out.println("4. Rechercher un vehicule");
            System.out.println("5. Modifier vehicule");
            System.out.println("6. Retour au menu principal");
            int choix = faireChoix("Choisissez une option : ", 1, 6);

            switch (choix) {
                case 1:
                    afficherVehicule(listeVehicule);
                    break;
                case 2:
                    ajouterVehicule(creerVehicule());
                    break;
                case 3:
                    supprimerVehicule(recupererEntier("ID du vehicule a supprime ?"));
                    break;
                case 4:
                    rechercherVehicule();
                    break;
                case 5:
                    modifierVehicule();
                    break;
                case 6:
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
            System.out.println("4. Rechercher un employe");
            System.out.println("5. Rechercher un employe");
            System.out.println("6. Retour au menu principal");
            int choix = faireChoix("Choisissez une option : ", 1, 6);

            switch (choix) {
                case 1:
                    afficherEmploye(listeEmploye);
                    break;
                case 2:
                    ajouterEmploye(creerEmploye());
                    break;
                case 3:
                    supprimerEmploye(recupererEntier("ID de l employe a supprime ?"));
                    break;
                case 4:
                    rechercherEmploye();
                    break;
                case 5:
                    modifierEmploye();
                    break;
                case 6:
                    return;
            }

        } while (true);
    }

    //Chargempent et sauvegarde fichier .txt
    public void chargerEmployes() {
        try {
            File fichier = new File("employes.txt");
            Scanner lecteurFichier = new Scanner(fichier);

            while (lecteurFichier.hasNextLine()) {
                String ligne = lecteurFichier.nextLine();
                String[] donnees = ligne.split(";"); // Découpe la ligne dans un tableau, chaque case est une variable

                if (donnees.length == 5) {
                    int id = Integer.parseInt(donnees[0]);
                    String nom = donnees[1];
                    String prenom = donnees[2];
                    String poste = donnees[3];
                    String email = donnees[4];

                    this.listeEmploye.add(new Employe(id, nom, prenom, poste, email));
                }
            }
            lecteurFichier.close();
            System.out.println("Base d'employes chargee !");

        } catch (FileNotFoundException e) {
            System.out.println("Fichier employes.txt introuvable. Liste vide.");
        }
    }

    public void chargerVehicules() {
        try {
            File fichier = new File("vehicules.txt");
            Scanner lecteurFichier = new Scanner(fichier);

            while (lecteurFichier.hasNextLine()) {
                String ligne = lecteurFichier.nextLine();
                String[] donnees = ligne.split(";");

                if (donnees.length == 7) {
                    String type = donnees[0];
                    int id = Integer.parseInt(donnees[1]);
                    String marque = donnees[2];
                    String modele = donnees[3];
                    String immatriculation = donnees[4];
                    int kilometrage = Integer.parseInt(donnees[5]);
                    int specifique = Integer.parseInt(donnees[6]); // Portes, Cylindrée ou Taille

                    switch (type) {
                        case "Voiture":
                            this.listeVehicule.add(new Voiture(id, marque, modele, immatriculation, kilometrage, specifique));
                            break;
                        case "Moto":
                            this.listeVehicule.add(new Moto(id, marque, modele, immatriculation, kilometrage, specifique));
                            break;
                        case "Utilitaire":
                            this.listeVehicule.add(new Utilitaire(id, marque, modele, immatriculation, kilometrage, specifique));
                            break;
                    }
                }
            }
            lecteurFichier.close();
            System.out.println("Base de vehicules chargee !");

        } catch (FileNotFoundException e) {
            System.out.println("Fichier vehicules.txt introuvable. Liste vide.");
        }
    }

    public void sauvegarderEmployes() {
        try {
            // Le PrintWriter +etFileWriter permet d écrire dans un fichier (et de le créer si il existe pas)
            PrintWriter ecrivain = new PrintWriter(new FileWriter("employes.txt"));

            for (Employe employe : this.listeEmploye) {
                ecrivain.println(employe.toCSV()); // Écrit la ligne et passe à la suivante
            }

            ecrivain.close();
            System.out.println("Sauvegarde des employés réussie.");

        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des employes : " + e.getMessage());
        }
    }

    public void sauvegarderVehicules() {
        try {
            PrintWriter ecrivain = new PrintWriter(new FileWriter("vehicules.txt"));

            for (Vehicule vehicule : this.listeVehicule) {
                ecrivain.println(vehicule.toCSV());
            }

            ecrivain.close();
            System.out.println("Sauvegarde des vehicules reussie.");

        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des vehicules : " + e.getMessage());
        }
    }

    //Vehicule
    public Vehicule creerVehicule() {
        System.out.println("--- Ajout d'un nouveau véhicule ---");

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
        Vehicule aSupp = null;
        for (Vehicule v : listeVehicule){
            if (v.getId() == id && v.getDisponible()){
                aSupp = v;
                break;
            }
        }
        if (aSupp == null){
            System.out.println("Le vehicule d id " + id + " est introuvable ou actuellement affecte, suppresion impossible.");
            return;
        }
        switch (aSupp.getClass().getSimpleName()){
            case "Voiture":
                Voiture.nbVoiture--;
                break;
            case "Moto":
                Moto.nbMoto--;
                break;
            case "Utilitaire":
                Utilitaire.nbUtilitaire--;
                break;
        }
        Vehicule.nbVehicule--;
        ParcAutomobile.nbVehiculeDisponible--;
        listeVehicule.remove(aSupp);
        System.out.println("Vehicule d id " + id + " a ete supprime avec succes.");
    }


    public void afficherVehicule(ArrayList<Vehicule> listeVehiculeAfficher) {
        if (!listeVehiculeAfficher.isEmpty() || Vehicule.nbVehicule == 0) {
            listeVehiculeAfficher.sort(Comparator.comparing(Vehicule::getMarque));

            ArrayList<Vehicule> voitureArrayList = new ArrayList<>();
            ArrayList<Vehicule> motoArrayList = new ArrayList<>();
            ArrayList<Vehicule> utilitaireArrayList = new ArrayList<>();

            System.out.println("Vehicule du parc automobile : " +
                    "\nIl y a " + Vehicule.nbVehicule + " vehicules : " + Voiture.nbVoiture + " voitures, " + Moto.nbMoto + " motos, " + Utilitaire.nbUtilitaire + " utilitaires"+
                    "\n(pour plus d informations sur un vehicule en particulier utilise la fonction de recherche par id)");
            //Affichage des vehicules disponibles separe par type et rier dans l'odre alphabetique selon la marque
            if (ParcAutomobile.nbVehiculeDisponible != 0){
                System.out.println("\n Actuellement disponible dans la parc "+ "(" + ParcAutomobile.nbVehiculeDisponible + ") : ");
                System.out.println("\tID\t\tMarque\t\tModele");
                for (Vehicule vehicule : listeVehiculeAfficher){
                    if (vehicule instanceof Voiture){voitureArrayList.add(vehicule);}else
                    if (vehicule instanceof Moto){motoArrayList.add(vehicule);} else
                    if (vehicule instanceof Utilitaire){utilitaireArrayList.add(vehicule);}
                }//boucle de tri entre les differentes classes
                for (Vehicule voiture : voitureArrayList){
                    if (voiture.getDisponible()) {System.out.println("\t" + voiture.getId() + "\t\t" + voiture.getMarque() + "\t\t" + voiture.getModele());}
                }
                for (Vehicule moto : motoArrayList){
                    if (moto.getDisponible()) {System.out.println("\t" + moto.getId() + "\t\t" + moto.getMarque() + "\t\t" + moto.getModele());}
                }
                for (Vehicule utilitaire : utilitaireArrayList){
                    if (utilitaire.getDisponible()) {System.out.println("\t" + utilitaire.getId() + "\t\t" + utilitaire.getMarque() + "\t\t" + utilitaire.getModele());}
                }//Trois boucles pour afficher les vehicule disponible, une pour chaque type de vehicule
            }
            else {
                System.out.println("Aucun vehicule disponible pour le moment");
            }
            if (Vehicule.nbVehicule != ParcAutomobile.nbVehiculeDisponible){
                System.out.println("\n Actuellement indisponible" + "(" + (Vehicule.nbVehicule - ParcAutomobile.nbVehiculeDisponible) + ") : ");
                System.out.println("\tID\t\tMarque\t\tModele");
                for (Vehicule voiture : voitureArrayList){
                    if (!voiture.getDisponible()) {System.out.println("\t" + voiture.getId() + "\t\t" + voiture.getMarque() + "\t\t" + voiture.getModele());}
                }
                for (Vehicule moto : motoArrayList){
                    if (!moto.getDisponible()) {System.out.println("\t" + moto.getId() + "\t\t" + moto.getMarque() + "\t\t" + moto.getModele());}
                }
                for (Vehicule utilitaire : utilitaireArrayList){
                    if (!utilitaire.getDisponible()) {System.out.println("\t" + utilitaire.getId() + "\t\t" + utilitaire.getMarque() + "\t\t" + utilitaire.getModele());}
                }//boucle pour les vehicules indisponibles
            }
        }else {
            System.out.println("Aucun vehicule enregistre dans la parc automobile");
        }
    }

    public void modifierVehicule() {
        System.out.println("--- Modification d'un véhicule ---");
        int id = recupererEntier("Entrez l ID du vehicule à modifier : ");

        Vehicule vehicule = rechercherVehiculeId(this.listeVehicule, id);

        if (vehicule == null) {
            System.out.println("Vehicule introuvable.");
            return;
        }

        while (true) {
            System.out.println("\nVous modifiez : " + vehicule.getMarque() + " " + vehicule.getModele());
            System.out.println("Que souhaitez-vous modifier ?");
            System.out.println("1. Marque");
            System.out.println("2. Modèle");
            System.out.println("3. Immatriculation");
            System.out.println("4. Kilometrage");

            // Option dynamique selon le type de vehicule
            if (vehicule instanceof Voiture) {
                System.out.println("5. Nombre de portes");
            } else if (vehicule instanceof Moto) {
                System.out.println("5. Cylindrée");
            } else if (vehicule instanceof Utilitaire) {
                System.out.println("5. Taille (Volume)");
            }

            System.out.println("6. Terminer les modifications");

            int choix = faireChoix("Votre choix : ", 1, 6);

            switch (choix) {
                case 1:
                    vehicule.setMarque(recupererString("Nouvelle marque : "));
                    break;
                case 2:
                    vehicule.setModele(recupererString("Nouveau modèle : "));
                    break;
                case 3:
                    vehicule.setImmatriculation(recupererString("Nouvelle immatriculation : "));
                    break;
                case 4:
                    // Ton setter gère déjà la vérification pour ne pas baisser le kilométrage, c'est parfait !
                    vehicule.setKilometrage(recupererEntier("Nouveau kilométrage : "));
                    break;
                case 5:
                    // Cast du vehicule pour acceder aux methodes de la classe fille
                    if (vehicule instanceof Voiture) {
                        ((Voiture) vehicule).setNbPorte(recupererEntier("Nouveau nombre de portes : "));
                    } else if (vehicule instanceof Moto) {
                        ((Moto) vehicule).setCylindre(recupererEntier("Nouvelle cylindre : "));
                    } else if (vehicule instanceof Utilitaire) {
                        ((Utilitaire) vehicule).setTaille(recupererEntier("Nouvelle taille : "));
                    } else {
                        System.out.println("Option invalide.");
                    }
                    break;
                case 6:
                    return;
            }
        }
    }

    public void rechercherVehicule(){
        do {
            System.out.println("Quel type de recherche voulez vous faire ?\n |1 : Id |2 : Immatriculation |3 : Critere multiples\n |4 : quitter la recherche");
            int choix = faireChoix("Choisissez une option", 1, 4);
            switch (choix){
                case 1:
                    System.out.println(rechercherVehiculeId(listeVehicule, recupererEntier("Id du vehicule recherche ?")));
                    break;
                case 2:
                    System.out.println(rechercherVehiculeImmatriculation(listeVehicule, recupererString("Immatriculation du vehicule recherche ?")));
                    break;
                case 3:
                    rechercheVehiculeMultiCritere();
                    break;
                case 4:
                    return;
            }
        }while (true);
    }

    public void rechercheVehiculeMultiCritere() {
        ArrayList<Vehicule> listeFiltree = new ArrayList<>(this.listeVehicule);

        while (true) {
            System.out.println("\n=== RECHERCHE AVANCEE VEHICULE (" + listeFiltree.size() + " résultat(s) trouvé(s)) ===");
            afficherVehicule(listeFiltree);

            System.out.println("\nQue souhaitez-vous faire ?");
            System.out.println("1. Ajouter un filtre : Marque");
            System.out.println("2. Ajouter un filtre : Kilométrage max");
            System.out.println("3. Ajouter un filtre : Type");
            System.out.println("4. Ajouter un filtre : Modele");
            System.out.println("5. Réinitialiser la recherche (Tout voir)");
            System.out.println("6. Quitter la recherche");

            int choix = faireChoix("Votre choix : ", 1, 6);

            switch (choix){
                case 1:
                    listeFiltree = rechercherVehiculeMarque(listeFiltree, recupererString("Quelle marque recherchez vous ? : "));
                break;

                case 2:
                    listeFiltree = rechercherVehiculeKilometrage(listeFiltree, recupererEntier("Kilometrage maximum autorise ? : "));
                break;

                case 3:
                    listeFiltree = rechercherVehiculeType(listeFiltree, recupererString("Quel type de vehicule cherchez vous ? (moto, voiture ou utilitaire)"));
                    break;
                case 4:
                    listeFiltree = rechercherVehiculeModele(listeFiltree, recupererString("Quelle modele cherchez vous ?"));
                    break;


                case 5:
                listeFiltree = new ArrayList<>(this.listeVehicule);
                System.out.println("Filtres reinitialises.");
                break;
                case 6:
                    return;
            }
        }
    }

    public Vehicule rechercherVehiculeId(ArrayList<Vehicule> listeSource, int id) {
        for (Vehicule vehicule : listeSource) {
            if (vehicule.getId() == id) {
                return vehicule;
            }
        }
        return null;
    }

    public Vehicule rechercherVehiculeImmatriculation(ArrayList<Vehicule> listeSource, String immatriculation) {
        for (Vehicule vehicule : listeSource) {
            if (vehicule.getImmatriculation().equalsIgnoreCase(immatriculation)) {
                return vehicule;
            }
        }
        return null;
    }

    public ArrayList<Vehicule> rechercherVehiculeKilometrage(ArrayList<Vehicule> listeSource, int kilometrageMax) {
        ArrayList<Vehicule> resultats = new ArrayList<>();
        for (Vehicule vehicule : listeSource) {
            if (vehicule.getKilometrage() <= kilometrageMax) {
                resultats.add(vehicule);
            }
        }
        return resultats;
    }

    public ArrayList<Vehicule> rechercherVehiculeMarque(ArrayList<Vehicule> listeSource, String marque) {
        ArrayList<Vehicule> resultats = new ArrayList<>();
        for (Vehicule vehicule : listeSource) {
            if (vehicule.getMarque().equalsIgnoreCase(marque)) {
                resultats.add(vehicule);
            }
        }
        return resultats;
    }

    public ArrayList<Vehicule> rechercherVehiculeModele(ArrayList<Vehicule> listeSource, String modele) {
        ArrayList<Vehicule> resultats = new ArrayList<>();
        for (Vehicule vehicule : listeSource) {
            if (vehicule.getModele().equalsIgnoreCase(modele)) {
                resultats.add(vehicule);
            }
        }
        return resultats;
    }

    public ArrayList<Vehicule> rechercherVehiculeType(ArrayList<Vehicule> listeSource, String type) {
        ArrayList<Vehicule> resultats = new ArrayList<>();
        for (Vehicule vehicule : listeSource) {
            if (vehicule.getClass().getSimpleName().equalsIgnoreCase(type)){
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

    public void afficherEmploye(ArrayList<Employe> listeEmployeAfficher) {
        if (!listeEmployeAfficher.isEmpty()) {
            listeEmployeAfficher.sort(Comparator.comparing(Employe::getNom));
            System.out.println("Employe du parc automobile : " +
                    "\n(pour plus d informations sur un employe en particulier utilise la fonction de recherche par id)");
            System.out.println("\tID\t\tNom\t\tPrenom");
            for (Employe employe : listeEmployeAfficher) {
                System.out.println("\t" + employe.getId() + "\t\t" + employe.getNom() + "\t\t" + employe.getPrenom());
            }
        } else {
            System.out.println("Il n'y a aucun employe dans le parc automobile");
        }
    }

    public void modifierEmploye() {
        System.out.println("--- Modification d un employe ---");
        int id = recupererEntier("Entrez l'ID de l'employe à modifier : ");
        Employe employe = rechercherEmployeId(listeEmploye, id);

        if (employe == null) {
            System.out.println("Employe introuvable.");
            return;
        }

        while (true) {
            System.out.println("\nVous modifiez : " + employe.getPrenom() + " " + employe.getNom());
            System.out.println("Que souhaitez-vous modifier ?");
            System.out.println("1. Nom");
            System.out.println("2. Prénom");
            System.out.println("3. Poste");
            System.out.println("4. Email");
            System.out.println("5. Terminer les modifications");

            int choix = faireChoix("Votre choix : ", 1, 5);

            switch (choix) {
                case 1:
                    employe.setNom(recupererString("Nouveau nom : "));
                    System.out.println("Nom mis à jour.");
                    break;
                case 2:
                    employe.setPrenom(recupererString("Nouveau prenom : "));
                    System.out.println("Prénom mis à jour.");
                    break;
                case 3:
                    employe.setPoste(recupererString("Nouveau poste : "));
                    System.out.println("Poste mis à jour.");
                    break;
                case 4:
                    employe.setEmail(recupererString("Nouvel email : "));
                    System.out.println("Email mis à jour.");
                    break;
                case 5:
                    return;
            }
        }
    }

    public void rechercherEmploye(){
        do {
            System.out.println("Quel type de recherche voulez vous faire ?\n |1 : Id |2 : Critere multiples\n |3 : quitter la recherche");
            int choix = faireChoix("Choisissez une option", 1, 3);
            switch (choix){
                case 1:
                    Employe e = rechercherEmployeId(listeEmploye, recupererEntier("Id de l employe recherche ?"));
                    System.out.println(e);
                    System.out.println("Voulez vous afficher les affectations en rapport avec cette emplote ?");
                    if (faireChoix("1 : oui 2 : non", 1, 2) == 1){
                        afficherAffectationEmploye(e);
                    }

                    break;
                case 2:
                    rechercheEmployeMultiCritere();
                    break;
                case 3:
                    return;
            }
        }while (true);
    }

    public void rechercheEmployeMultiCritere() {
        ArrayList<Employe> listeFiltree = new ArrayList<>(this.listeEmploye);

        while (true) {
            System.out.println("\n=== RECHERCHE AVANCEE EMPLOYE (" + listeFiltree.size() + " résultat(s) trouvé(s)) ===");
            afficherEmploye(listeFiltree);

            System.out.println("\nQue souhaitez-vous faire ?");
            System.out.println("1. Ajouter un filtre : Nom");
            System.out.println("2. Ajouter un filtre : Prenom");
            System.out.println("3. Ajouter un filtre : Poste");
            System.out.println("4. Réinitialiser la recherche (Tout voir)");
            System.out.println("5. Quitter la recherche");

            int choix = faireChoix("Votre choix : ", 1, 5);

            switch (choix){
                case 1:
                    listeFiltree = rechercherEmployesParNom(listeFiltree, recupererString("Quelle nom recherchez vous ? : "));
                    break;

                case 2:
                    listeFiltree = rechercherEmployesParPrenom(listeFiltree, recupererString("Quelle prenom recherchez vous  ? : "));
                    break;

                case 3:
                    listeFiltree = rechercherEmployesParPoste(listeFiltree, recupererString("Quel poste recherchez vous ?"));
                    break;

                case 4:
                    listeFiltree = new ArrayList<>(this.listeEmploye);
                    System.out.println("Filtres reinitialises.");
                    break;
                case 5:
                    return;
            }
        }
    }


    public Employe rechercherEmployeId(ArrayList<Employe> listeEmploye, int id) {
        for (Employe employe : listeEmploye) {
            if (employe.getId() == id) {
                return employe;
            }
        }
        return null;
    }

    public ArrayList<Employe> rechercherEmployesParNom(ArrayList<Employe> listeEmploye, String nom) {
        ArrayList<Employe> resultats = new ArrayList<>();
        for (Employe employe : listeEmploye) {
            if (employe.getNom().equalsIgnoreCase(nom)) {
                resultats.add(employe);
            }
        }
        return resultats;
    }

    public ArrayList<Employe> rechercherEmployesParPrenom(ArrayList<Employe> listeEmploye, String prenom) {
        ArrayList<Employe> resultats = new ArrayList<>();
        for (Employe employe : listeEmploye) {
            if (employe.getPrenom().equalsIgnoreCase(prenom)) {
                resultats.add(employe);
            }
        }
        return resultats;
    }

    public ArrayList<Employe> rechercherEmployesParPoste(ArrayList<Employe> listeEmploye, String poste) {
        ArrayList<Employe> resultats = new ArrayList<>();
        for (Employe employe : listeEmploye) {
            if (employe.getPoste().equalsIgnoreCase(poste)) {
                resultats.add(employe);
            }
        }
        return resultats;
    }

    //Affectation

    public void afficherAffectation(boolean active) {//True pour afficher les actives, False pour l'historique
        String messageVide = (active ? "Aucune affectation actuellement en cours." : "Aucune affectation dans l'historique.");
        String titre = (active ? "=== Affectations en cours ===" : "=== Historique des affectations ===");

        if (listeAffectation.isEmpty()) {
            System.out.println(messageVide);
        } else {
            listeAffectation.sort(Comparator.comparing(Affectation::getDateAffectation).reversed());//trie des affectations dans l'ordre plus recent au plus ancien
            boolean auMoinsUn = false;
            System.out.println(titre);
            System.out.println(" (Pour plus d information sur une affectation utilisez la recherche par ID");

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

            Vehicule vehicule = rechercherVehiculeId(listeVehicule,idVehicule);
            Employe employe = rechercherEmployeId(listeEmploye, idEmploye);
            if (vehicule != null && employe != null) {
                int limite = 0;
                for (Affectation a : listeAffectation){
                    if (a.getActif() && a.getEmploye().getId() == employe.getId()){
                        limite++;
                    }
                }
                if (limite < Variable.LIMITEAFFECTATIONEMPLOYE.getValeur()){
                    if (vehicule.getDisponible()){
                        vehicule.setDisponible(false);
                        return new Affectation(idAffectation, vehicule, employe, LocalDateTime.now(), null, true);
                    } else {
                        System.out.println("Vehicule choisi indisponible.");
                    }
                }else {
                    System.out.println("L employe a deja le nombre maximum d affectation (3).");
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
        ParcAutomobile.nbVehiculeDisponible--;
    }

    public void afficherAffectationEmploye(Employe employe) {
        if (employe == null) {
            System.out.println("Impossible d afficher les affectations : l'employe est introuvable.");
            return;
        }

        System.out.println("\n=== Dossier des affectations de " + employe.getPrenom() + " " + employe.getNom() + " ===");

        // 1 boucle : Les affectations actives
        System.out.println("\n--- EN COURS ---");
        boolean aDesAffectationsActives = false;
        for (Affectation affectation : this.listeAffectation) {
            if (affectation.getEmploye().getId() == employe.getId() && affectation.getActif()) {
                System.out.println(affectation);
                aDesAffectationsActives = true;
            }
        }
        if (!aDesAffectationsActives) {
            System.out.println("Aucune affectation en cours.");
        }

        // 2  inactives
        System.out.println("\n--- HISTORIQUE (Terminées) ---");
        boolean aDesAffectationsInactives = false;
        for (Affectation affectation : this.listeAffectation) {
            // On cherche le même employe mais cette fois avec !getActif() (faux)
            if (affectation.getEmploye().getId() == employe.getId() && !affectation.getActif()) {
                System.out.println(affectation);
                aDesAffectationsInactives = true;
            }
        }
        if (!aDesAffectationsInactives) {
            System.out.println("Aucune affectation dans l'historique.");
        }
        System.out.println("=========================================");
    }

    public boolean finirAffectation(int id){
        for (Affectation affectation : listeAffectation){
            if (affectation.getId() == id && affectation.getActif()){
                affectation.setActif(false);
                affectation.getVehicule().setDisponible(true);
                ParcAutomobile.nbVehiculeDisponible++;
                affectation.setDateRetour(LocalDateTime.now());
                int augmentationKm = recupererEntier("Kilometrage effectue pendant l affectation");
                affectation.getVehicule().setKilometrage(affectation.getVehicule().getKilometrage() + augmentationKm);
                if(augmentationKm >= Variable.NOMBREKILOMETREALERTE.getValeur()){
                    System.out.println("\u001B[31m" + "Le kilometrage effectue necessite de faire une revision " + "\u001B[0m");
                }
                return true;
            }
        }
        return false;
    }

    public void modifierAffectation() {
        System.out.println("--- Modification d'une affectation ---");

        Affectation affectation = rechercherAffectationID(recupererEntier("Quel est l Id de l affectation que vous voulez modifier ?"));

        if (affectation == null) {
            System.out.println("Affectation introuvable.");
            return;
        }
        if (!affectation.getActif()) {
            System.out.println("Impossible de modifier une affectation terminée (Historique).");
            return;
        }
        while (true) {
            System.out.println("\nVous modifiez l affectation n " + affectation.getId() + " (" + affectation.getEmploye().getNom() + " - " + affectation.getVehicule().getMarque() + " " +  affectation.getVehicule().getModele() + ")");
            System.out.println("Que souhaitez-vous modifier ?");
            System.out.println("1. Changer l'employe");
            System.out.println("2. Changer le vehicule");
            System.out.println("3. Terminer les modifications");

            int choix = faireChoix("Votre choix : ", 1, 3);

            switch (choix) {
                case 1:
                    Employe nouvelEmploye = rechercherEmployeId(listeEmploye, recupererEntier("Entrez l'ID du nouvel employé : "));

                    if (nouvelEmploye == null) {
                        System.out.println("Employe introuvable.");
                    } else if (nouvelEmploye.getId() == affectation.getEmploye().getId()) {
                        System.out.println("L affectation est deja assignee à cet employe.");
                    } else {
                        // Vérification de la limite de 3 affectations pour le nouvel employé
                        int limite = 0;
                        for (Affectation a : listeAffectation) {
                            if (a.getActif() && a.getEmploye().getId() == nouvelEmploye.getId()) {
                                limite++;
                            }
                        }
                        if (limite >= 3) {
                            System.out.println("Le nouvel employe possede deja le maximum d affectations autorisees (" +  Variable.LIMITEAFFECTATIONEMPLOYE + ").");
                        } else {
                            affectation.setEmploye(nouvelEmploye);
                            System.out.println("Employe mis à jour avec succes.");
                        }
                    }
                    break;

                case 2:
                    Vehicule nouveauVehicule = rechercherVehiculeId(this.listeVehicule, recupererEntier("Entrez l'ID du nouveau véhicule : "));

                    if (nouveauVehicule == null) {
                        System.out.println("Vehicule introuvable.");
                    } else if (nouveauVehicule.getId() == affectation.getVehicule().getId()) {
                        System.out.println("L affectation utilise déjà ce vehicule.");
                    } else if (!nouveauVehicule.getDisponible()) {
                        System.out.println("Le nouveau véhicule choisi est actuellement indisponible.");
                    } else {
                        affectation.getVehicule().setDisponible(true);

                        nouveauVehicule.setDisponible(false);

                        affectation.setVehicule(nouveauVehicule);

                        System.out.println("Vehicule mis à jour avec succes.");
                    }
                    break;

                case 3:
                    return;
            }
        }
    }
    public Affectation rechercherAffectationID(int id){
        for (Affectation affectation : listeAffectation) {
            if (affectation.getId() == id) {
                return affectation;
            }
        }
        return null;
    }
}