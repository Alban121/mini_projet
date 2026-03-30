public class Employe {

    //Attributs
    private int id;
    private String nom;
    private String prenom;
    private String poste;
    private String email;

    //Constructeur
    public Employe (int id ,String nom, String prenom, String poste, String email){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.email = email;

    }

    //getter
    public int getId(){return this.id;}
    public String getNom(){return this.nom;}
    public String getPrenom(){return this.prenom;}
    public String getPoste(){return this.poste;}
    public String getEmail(){return this.email;}

    //setter
    public void setNom(String newNom){this.nom = newNom;}
    public void setPrenom(String newPrenom){this.prenom = newPrenom;}
    public void setPoste(String newPoste){this.poste = newPoste;}
    public void setEmail(String newEmail){this.email = newEmail;}

    //métohde
    public void afficherDetail(){
        System.out.println("Information Employé n°" + this.id + "\n|Prénom : " + this.prenom + "\n|Nom : " + this.nom + "\n|Poste : " + this.poste + "\n|email : " + this.email);

    }


}
