import java.util.Date;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {//TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    ParcAutomobile parcAutomobile = new ParcAutomobile();
    Vehicule vehicule = new Vehicule(12, "bmw", "i8", "45rfc67", 2000);
    Vehicule vehiculet = new Vehicule(24, "bmw", "i8", "45rfc67", 2000, false);
    parcAutomobile.afficherVehicule();
    parcAutomobile.ajouterVehicule(vehicule);
    parcAutomobile.ajouterVehicule(vehiculet);
    parcAutomobile.afficherVehicule();
}
