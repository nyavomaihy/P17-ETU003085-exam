package com.itu.Hello;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import main.java.model.*;
import main.java.modelDAO.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
public class EtatServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Prevision [] dep= DepenseDAO.getEtat();
        req.setAttribute("previsions", dep);
        RequestDispatcher dispat = req.getRequestDispatcher("/Etat.jsp");
        dispat.forward(req,res);
    }
}