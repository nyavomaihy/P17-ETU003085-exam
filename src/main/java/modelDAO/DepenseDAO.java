package main.java.modelDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import main.java.Connexion.*;
import main.java.model.*;
public class DepenseDAO {
    public static void save(Connection conn,Depense dep) throws IOException {
        
            String sqlQuery = "INSERT INTO Depense_naina (id_prevision,depense_montant) VALUES (?,?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
                pstmt.setString(1, dep.getId_prevision());
                pstmt.setInt(2, dep.getMontant_depense());
                pstmt.executeUpdate();
                
            } catch (Exception e) {
                throw new IOException("AAA ee");
            } 
        
    }
    public static void save(Depense dep) throws IOException{
        try {
            Connection conn=DatabaseConnection.getConnexion();
            DepenseDAO.save(conn,dep);
        } catch (Exception e) {
            throw new IOException("AAA ee");
        }
    }
    public static Depense[] findAll() throws IOException {
        List<Depense> result = new ArrayList<>();
        String sql = "SELECT * FROM Depense_naina";

        try (Connection conn = DatabaseConnection.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id= rs.getInt("id_depense");
                String nom = rs.getString("id_prevision");
                int montant = rs.getInt("depense_montant");

                Depense dep=new Depense(id,nom,montant);
                result.add(dep);
            }

        } catch (SQLException e) {
            throw new IOException("Erreur lors de l'accès à la base de données", e);
        }
        return result.toArray(new Depense[0]);
    }
    
    public static Depense getById(int id) throws IOException {
        Depense result = null;

        String sqlQuery = "SELECT * FROM Depense_naina where id_depense = ?";
        
        
        try (Connection conn = DatabaseConnection.getConnexion();
            PreparedStatement statement = conn.prepareStatement(sqlQuery);){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result=new Depense(rs.getInt("id_depense"),rs.getString("id_prevision"),rs.getInt("depense_montant"));
            }            
        } catch (Exception e) {
            throw new IOException(e);
        }
        return result;
    }
    public static Prevision[] getEtat() throws IOException{
        List<Prevision> result= new ArrayList<>();
        String sql = "SELECT libelle,montant,montant_principale  FROM Prevision_naina ";

        try (Connection conn = DatabaseConnection.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String id= rs.getString("libelle");
                int montant = rs.getInt("montant_principale");
                int sum = rs.getInt("montant");
                result.add(new Prevision(id, montant, sum));
            }

        } catch (SQLException e) {
            throw new IOException("Erreur lors de l'accès à la base de données", e);
        }
        return result.toArray(new Prevision[0]);        
    }
}
