/*******************************************************************************
 * * * Copyright: 2019 digiBlitz Foundation
 *  * * 
 *  * * License: digiBlitz Public License 1.0 (DPL) 
 *  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 *  * * 
 *  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 *  * * 
 *  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 *  * * 
 *  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import java.io.*;
/**
 * Servlet implementation class AnotherServlet
 */
public class OdeServlet extends HttpServlet {
 
 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//PrintWriter pw = response.getWriter();
	     String src1 =request.getParameter("Source");
	     
	     System.out.println("value of source" + src1); 
     // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    //System.out.println("Enter the source directory or file name : ");
	    //pw.println("Inside DoGET");
     //   String source = in.readLine();

     File src = new File(src1);

//System.out.println("Enter the destination directory or file name : ");

//String destination = in.readLine();
     
     String variable = System.getenv("CATALINA_HOME");  
	   System.out.println(variable); 
	 
	   
String destination = variable + "//webapps//ode//WEB-INF//processes";
System.out.println("Destination directory :" +destination);
File dst = new File(destination); 
CopyDirectory cd = new CopyDirectory();
cd.copyDirectory(src, dst);

//Request Dispatcher

RequestDispatcher view = request.getRequestDispatcher("/new.html");
view.forward(request,response);


}

}


