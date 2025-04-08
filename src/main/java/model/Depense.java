package main.java.model;

public class Depense {
    private int id_depense;
    private String id_prevision;
    private int montant_depense;
    public int getId_depense() {
        return id_depense;
    }
    public void setId_depense(int id_depense) {
        this.id_depense = id_depense;
    }
    public String getId_prevision() {
        return id_prevision;
    }
    public void setId_prevision(String id_prevision) {
        this.id_prevision = id_prevision;
    }
    public int getMontant_depense() {
        return montant_depense;
    }
    public void setMontant_depense(int montant_depense) {
        this.montant_depense = montant_depense;
    }
    public Depense(int id_depense, String id_prevision, int montant_depense) {
        this.id_depense = id_depense;
        this.id_prevision = id_prevision;
        this.montant_depense = montant_depense;
    }
    public Depense(String id_prevision, int montant_depense) {
        this.id_prevision = id_prevision;
        this.montant_depense = montant_depense;
    }
   
    

}
