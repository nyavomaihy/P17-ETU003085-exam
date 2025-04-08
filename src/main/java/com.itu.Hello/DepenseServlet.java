package com.itu.Hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import main.java.model.*;
import main.java.modelDAO.*;
import java.sql.*;

public class DepenseServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Prevision p=new Prevision();
        Prevision[] prev= p.findAll();
        req.setAttribute("prevision", prev);
        RequestDispatcher dispat = req.getRequestDispatcher("/InsertDepense.jsp");
        dispat.forward(req,res);
    }
        
    }

