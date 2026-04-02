//varibles modifiables dans l enum

void main() {
    ParcAutomobile parcAutomobile = new ParcAutomobile();
    //pour avoir des valeurs tests de base(equivalent seeders en bdd)
    parcAutomobile.chargerEmployes();
    parcAutomobile.chargerVehicules();
    parcAutomobile.menuGestion();

}