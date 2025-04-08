package com.itu.Hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import main.java.model.*;
import main.java.modelDAO.*;
import java.sql.*;


public class PrevisionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String libelle=req.getParameter("libelle");
        int montan=Integer.parseInt(req.getParameter("montant"));

        Prevision dep= new Prevision(libelle,montan);
        dep.save();

        RequestDispatcher dispat = req.getRequestDispatcher("/Prevision.jsp");
        dispat.forward(req,res);
    }
}
