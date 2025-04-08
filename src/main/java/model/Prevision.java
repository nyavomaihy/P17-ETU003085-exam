package main.java.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.Connexion.DatabaseConnection;

public class Prevision {
    private int reste;
    public int getReste() {
        return reste;
    }
    public void setReste(int reste) {
        this.reste = reste;
    }
    public int getPric() {
        return pric;
    }
    public void setPric(int pric) {
        this.pric = pric;
    }
    private int pric;
    private int id_prevision;
    private String libelle;
    private int montant;
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public int getId_prevision() {
        return id_prevision;
    }
    public void setId_prevision(int id_prevision) {
        this.id_prevision = id_prevision;
    }
    public int getMontant() {
        return montant;
    }
    public void setMontant(int montant) {
        this.montant = montant;
    }
    public Prevision(int id_prevision, String libelle, int montant) {
        this.id_prevision = id_prevision;
        this.libelle = libelle;
        this.montant = montant;
    }
    public Prevision(String libelle, int montant) {
        this.libelle = libelle;
        this.montant = montant;
    }public Prevision(String libelle, int pric ,int reste) {
        this.libelle = libelle;
        this.pric = pric;
        this.reste=reste;
    }
   
    public void save(Connection conn) throws IOException {
        String sqlQuery = "INSERT INTO Prevision_naina (libelle,montant_principale,montant) VALUES ( ?, ? ,?)";
        try (PreparedStatement statement = conn.prepareStatement(sqlQuery);){
             statement.setString(1, this.getLibelle());
            statement.setInt(2, this.getMontant());
            statement.setInt(3, this.getMontant());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new IOException("rien");
        } 
    }
    public void save() throws IOException{
        try {
            Connection conn=DatabaseConnection.getConnexion();
            this.save(conn);
        } catch (Exception e) {
            throw new IOException("RIEN");
        }
    }
    
    public  Prevision[] findAll() throws IOException {
        List<Prevision> result = new ArrayList<>();
        String sql = "SELECT * FROM Prevision_naina";

        try (Connection conn = DatabaseConnection.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id= rs.getInt("id_prevision");
                String nom = rs.getString("libelle");
                int montant= rs.getInt("montant");
                Prevision dep=new Prevision(id,nom,montant);
                result.add(dep);
            }

        } catch (SQLException e) {
            throw new IOException("Erreur lors de l'accès à la base de données", e);
        }
        return result.toArray(new Prevision[0]);
    }
    
    public void update(Connection conn) throws IOException {

        String sqlQuery = "UPDATE Prevision_naina Set libelle=? , montant=? where id_prevision=?";
        
        try (PreparedStatement statement = conn.prepareStatement(sqlQuery);){
            statement.setString(1, this.getLibelle());
            statement.setInt(2, this.getMontant());
            statement.setInt(3, this.getId_prevision());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            throw new IOException("rien");
        }
    }
    public static Prevision getById(int id) throws IOException {
        Prevision result = null;

        String sqlQuery = "SELECT * FROM Prevision_naina where id_prevision = ?";
        
        
        try (Connection conn = DatabaseConnection.getConnexion();
            PreparedStatement statement = conn.prepareStatement(sqlQuery);){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result=new Prevision(rs.getInt("id_prevision"),rs.getString("libelle"), rs.getInt("montant"));
            }            
        } catch (Exception e) {
            throw new IOException(e);
        }
        return result;
    }
    public Prevision(){}
}
