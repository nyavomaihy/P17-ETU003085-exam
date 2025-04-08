package com.itu.Hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import main.java.Connexion.DatabaseConnection;
import main.java.model.*;
import main.java.modelDAO.*;
import main.java.service.DepenseService;

import java.sql.*;
public class FaireDepenseServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            PrintWriter out=res.getWriter();
            String libelles=req.getParameter("libelles");
            int libelle=Integer.parseInt(req.getParameter("libelles"));
            int montant = Integer.parseInt(req.getParameter("montantdepense"));
            Depense emp=new Depense(libelles,montant);
            
            Prevision prevision = Prevision.getById(libelle);

            Connection conn=DatabaseConnection.getConnexion();
            DepenseService.DepenseMontan(conn, prevision, emp);
            out.println(prevision.getId_prevision());
            res.sendRedirect(req.getContextPath()+"/depense");
    }

}
