package main.java.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import main.java.model.*;
import main.java.modelDAO.DepenseDAO;
public class DepenseService {
    public static void DepenseMontan(Connection conn,Prevision prev,Depense dep) throws IOException{
    Prevision p = Prevision.getById(prev.getId_prevision());

        if (prev.getMontant() < dep.getMontant_depense()) {
            throw new IOException("Votre solde est inferieur aux montant inserer impossible de l'inserer");
        }else{
            try {
                conn.setAutoCommit(false);   
                try {
                    DepenseDAO.save(dep);
                    p.setMontant(p.getMontant()-dep.getMontant_depense());
                    p.update(conn);
                    conn.commit();
                    conn.close();
                }    
                catch (Exception e) {
                    conn.rollback();
                    throw new IOException("erreur de base");
                }     
            } 
            catch (Exception e) {
                throw new IOException("erreur");
            }
            
        }
    } 
}   

