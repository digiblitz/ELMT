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
package com.stock.action;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import com.lmsstock.stateless.LMSStockstatelessRemote;
import com.lmsstock.stateless.LMSStockstatelessRemoteHome;
import com.lmsstock.util.Debug;


public class StockMaintenanceAction extends Action {
	
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	 Date date = new Date();

	public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		
		System.out.println("This is stock maintenance");
		 MessageResources mr=getResources(request);

        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        
        String jndiname=mr.getMessage("jndi.stock");
        
        Debug.print("ActionMessage.jndiname:" + jndiname);
        
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname); 
        Debug.print("ActionMessage.jndiname:" + jndiname);
        
        LMSStockstatelessRemoteHome Home = (LMSStockstatelessRemoteHome)PortableRemoteObject.narrow(objref,LMSStockstatelessRemoteHome.class);
        LMSStockstatelessRemote remote = Home.create();
	
        System.out.println("after create.............");
		
        HttpSession session = request.getSession(true);	
		
		 
		 String stockPro = request.getParameter("cmd"); 
		 
		 String PrivilegeName = request.getParameter("pName"); // get the privilege name from Url
		 session.setAttribute("pName", PrivilegeName);  // set the session value
		 
		 Debug.print("stockPro:" + stockPro);
		 Debug.print("PrivilegeName:" + PrivilegeName);
		 
		 
		 String stockMaintProcess = request.getParameter("stockMaintProcess");
		 
		//======================= Parthi Here : redirect the Form frmAddNewCourse ==================		 
		  if(stockPro!=null && stockPro.equalsIgnoreCase("initCourse")){
			  	
			  	ArrayList courseList = null;
			  	
			  	courseList = remote.getCourseList();
			  	
			  	request.setAttribute("courseList", courseList);
			  	request.setAttribute("status",null);
			  	return mapping.findForward("courseMaint"); 
         } 
		  
		  
		//======================= Parthi Here : redirect the Form frmAddNewProfram ==================		 
		  else if(stockPro!=null && stockPro.equalsIgnoreCase("initProg")){
				  	
				  	ArrayList progList = null;
				  	
				  	progList = remote.getProgramList();
				  	
				  	request.setAttribute("progList", progList);
				  	request.setAttribute("status",null);
				  	return mapping.findForward("progMaint"); 
	         }  
		  
		//======================= Parthi Here : redirect the Form frmAddNewPress ==================		 
		  else if(stockPro!=null && stockPro.equalsIgnoreCase("initPress")){
				  	
				  	ArrayList pressList = null;
				  	
				  	pressList = remote.getPressList();
				  	
				  	request.setAttribute("pressList", pressList);
				  	request.setAttribute("status",null);
				  	return mapping.findForward("pressMaint"); 
	         } 
		  
		//======================= Parthi Here : redirect the Form frmAddNewCentre==================		 
		  else if(stockPro!=null && stockPro.equalsIgnoreCase("initCentre")){
				  	
				  	ArrayList centreList = null;
				  	
				  	centreList = remote.getCentreList();
				  	
				  	request.setAttribute("centreList", centreList);
				  	request.setAttribute("status",null);
				  	return mapping.findForward("centreMaint"); 
	         } 
		  
		  
		//======================= Parthi Here : redirect the Form frmProgramCourseMapping ==================		 
		  else if(stockPro!=null && stockPro.equalsIgnoreCase("initMapPC")){
			   ArrayList courseList =null;
			   ArrayList progList =null;
			   
			    courseList = remote.getCourseList();
			  	progList = remote.getProgramList();
			  	
			  	request.setAttribute("progList", progList);
				request.setAttribute("courseList", courseList);
			  	
			  	request.setAttribute("status",null);
			  	return mapping.findForward("warehouseMaint"); 
			  	
				  	
	         }  
		//======================= Parthi Here : redirect the Form frmPrintingOrder ==================		 
		  else if(stockPro!=null && stockPro.equalsIgnoreCase("initPrintOrder")){
			   ArrayList courseList =null;
			  ArrayList pressList = null;			   
			    courseList = remote.getCourseList();			  	
			    pressList = remote.getPressList();
			    
				request.setAttribute("courseList", courseList);
				request.setAttribute("pressList", pressList);
			  	request.setAttribute("status",null);
			  	return mapping.findForward("printOrder");   
	         }
		
		//======================= Parthi Here : redirect the Form frmInwardEntry ==================		 
		  else if(stockPro!=null && stockPro.equalsIgnoreCase("initInward")){
			  
			   ArrayList courseList =null;
			  ArrayList pressList = null;			   
			    courseList = remote.getCourseList();			  	
			    pressList = remote.getPressList();
			    
				request.setAttribute("courseList", courseList);
				request.setAttribute("pressList", pressList);
			  	request.setAttribute("status",null);
			  	return mapping.findForward("inward");   
	         }
		  
		  
		//======================= Parthi Here : redirect the Form frmIndentRequest ==================		 
		  else if(stockPro!=null && stockPro.equalsIgnoreCase("initIndent")){
			  
			   ArrayList progList =null;
			  ArrayList centreList = null;			   
			  progList = remote.getProgramList();			  	
			  centreList = remote.getCentreList();
			    
				request.setAttribute("progList", progList);
				request.setAttribute("centreList", centreList);
			  	request.setAttribute("status",null);
			  	return mapping.findForward("indentRequest");   
	         }
		  
		//======================= Parthi Here : redirect the Form frmIssueDetails ==================		 
		  else if(stockPro!=null && stockPro.equalsIgnoreCase("initMatIssue")){
			   
			  	request.setAttribute("status",null);
			  	return mapping.findForward("matIssue");   
	         }
		  
		  
			//======================= Parthi Here : redirect the Form frmIssueDetails ==================		 
			  else if(stockPro!=null && stockPro.equalsIgnoreCase("initPendIssue")){
				   
				  	request.setAttribute("status",null);
				  	return mapping.findForward("pendingIssue");   
		         }
		  
		//======================= Parthi Here : redirect the Form frmIndentRequest ==================		 
		  else if(stockPro!=null && stockPro.equalsIgnoreCase("initSRDD")){
			  
			   ArrayList progList =null;
			  ArrayList centreList = null;			   
			  progList = remote.getProgramList();			  	
			  centreList = remote.getCentreList();
			    
				request.setAttribute("progList", progList);
				request.setAttribute("centreList", centreList);
			  	request.setAttribute("status",null);
			  	return mapping.findForward("SRDDRequest");   
	         }
		  
		//======================= Parthi Here : redirect the Form frmInwardReport==================		 
		  else if(stockPro!=null && stockPro.equalsIgnoreCase("initInwardReport")){
			   
			  	request.setAttribute("status",null);
			  	return mapping.findForward("inwardReport");   
	         }
		  
		//======================= Parthi Here : redirect the Form frmWorkOrderReports ==================		 
		  else if(stockPro!=null && stockPro.equalsIgnoreCase("initPressReport")){
			   
			  	request.setAttribute("status",null);
			  	return mapping.findForward("pressReport");   
	         }
		//======================= Parthi Here : redirect the Form frmWorkOrderReports ==================		 
		  else if(stockPro!=null && stockPro.equalsIgnoreCase("initRequestReport")){
			   
			  	request.setAttribute("status",null);
			  	return mapping.findForward("requestReport");   
	         }
		  
			  
		//======================= Parthi Here : redirect the Form frmWorkOrderReports ==================		 
		  else if(stockPro!=null && stockPro.equalsIgnoreCase("initIssueReport")){
			   
			  	request.setAttribute("status",null);
			  	return mapping.findForward("issueReport");   
	         }
		  
		//======================= Parthi Here : redirect the Form frmWorkOrderReports ==================		 
		  else if(stockPro!=null && stockPro.equalsIgnoreCase("initStockStatus")){
			   
			  	request.setAttribute("status",null);
			  	return mapping.findForward("stockStatus");   
	         }
		  
		//======================= Parthi Here : redirect the Form frmWorkOrderReports ==================		 
		  else if(stockPro!=null && stockPro.equalsIgnoreCase("initCourseIssue")){
			  ArrayList centreList = null;	
			  ArrayList courseList = null;	

			  courseList = remote.getCourseList();
			  centreList = remote.getCentreList();
			  
			  	request.setAttribute("status",null);
			  	 request.setAttribute("courseList", courseList);
				  request.setAttribute("centreList", centreList);
				  return mapping.findForward("courseIssue"); 
	         }
		  
		//======================= Parthi Here :Action for frmAddNewCourse ==================		
		  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("courseProcess")){
			  Debug.print("stockMaintProcess:" + stockMaintProcess);
			  String result = null;
			  ArrayList courseList = null;
			  
		      String course_Submit = request.getParameter("course_Submit");
		      
		      if(course_Submit!=null && course_Submit.equalsIgnoreCase("ADD")){
				  Debug.print("course_Submit:" + course_Submit);
				  
				  String courseCode = request.getParameter("courseCode");
				  String courseName = request.getParameter("courseName");
				  String yearOfCourse = request.getParameter("course_year");
				  String pages = request.getParameter("pages");
				  String courseBarcode = request.getParameter("courseBarcode");
				  
				  if(courseCode != null && courseCode !="")
				  {
					  result = remote.addCourse(courseCode,courseName,yearOfCourse, pages,courseBarcode);
				  }
				 
				  if(result.equalsIgnoreCase("success"))
				  {
					  request.setAttribute("status","New branch added successfully");
				  }
				  
				  courseList = remote.getCourseList();
				    
				  request.setAttribute("courseList", courseList);
				  return mapping.findForward("courseMaint"); 
		      }
		      
		      else if(course_Submit!=null && course_Submit.equalsIgnoreCase("UPDATE")){
				  Debug.print("course_Submit:" + course_Submit);
				  
				  String courseID = request.getParameter("courseID");
				  String courseCode = request.getParameter("courseCode");
				  String courseName = request.getParameter("courseName");
				  String yearOfCourse = request.getParameter("course_year");
				  String pages = request.getParameter("pages");
				  
				  if(courseID != null && courseID !="" && courseCode != null && courseCode != "")
				  {
					  result = remote.updateCourse(courseID,courseCode,courseName,yearOfCourse,pages);
				  }
				 
				  if(result.equalsIgnoreCase("success"))
				  {
					  request.setAttribute("status","Successfully Updated");
				  }
				  courseList = remote.getCourseList();
				    
				  request.setAttribute("courseList", courseList);
				  return mapping.findForward("courseMaint"); 
		      }
		      
		      else if(course_Submit!=null && course_Submit.equalsIgnoreCase("DELETE")){
				  Debug.print("course_Submit:" + course_Submit);
				  
				  String courseID = request.getParameter("courseID");
				  String courseCode = request.getParameter("courseCode");
				  String courseName = request.getParameter("courseName");
				  String yearOfCourse = request.getParameter("course_year");
				  String pages = request.getParameter("pages");
				  
				  if(courseID != null && courseID !="" && courseCode != null && courseCode != "")
				  {
					  result = remote.deleteCourse(courseID,courseCode);
				  }
				  
				  if(result.equalsIgnoreCase("success"))
				  {
					  request.setAttribute("status","Successfully Deleted");
				  }
				  courseList = remote.getCourseList();
				    
				  request.setAttribute("courseList", courseList);
				  return mapping.findForward("courseMaint"); 
		      }
		  }
		      
		    //======================= Parthi Here :Action for frmAddNewProgram ==================		
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("progProcess")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  String result = null;
				  ArrayList progList = null;
				  
			      String prog_Submit = request.getParameter("prog_Submit");
			      
			      if(prog_Submit!=null && prog_Submit.equalsIgnoreCase("ADD")){
					  Debug.print("prog_Submit:" + prog_Submit);
					  
					  String progCode = request.getParameter("progCode");
					  String progName = request.getParameter("progName");
					  String progBarcode = request.getParameter("progBarcode");
					  
					  
					  if(progCode != null && progCode !="")
					  {
						  result = remote.addProgram(progCode,progName,progBarcode);
					  }
					 
					  if(result.equalsIgnoreCase("success"))
					  {
						  request.setAttribute("status","New Program added successfully");
					  }
					  
					  progList = remote.getProgramList();
					  	
					  	request.setAttribute("progList", progList);
					  return mapping.findForward("progMaint"); 
			      }
			      
			      else if(prog_Submit!=null && prog_Submit.equalsIgnoreCase("UPDATE")){
					  Debug.print("prog_Submit:" + prog_Submit);
					  
					  String progID = request.getParameter("progID");
					  String progCode = request.getParameter("progCode");
					  String progName = request.getParameter("progName");
					  					  
					  if(progID != null && progID !="" && progCode != null && progCode != "")
					  {
						  result = remote.updateProgram(progID,progCode,progName);
					  }
					 
					  if(result.equalsIgnoreCase("success"))
					  {
						  request.setAttribute("status","Successfully Updated");
					  }
					  progList = remote.getProgramList();
					  	
					  	request.setAttribute("progList", progList);
					  return mapping.findForward("progMaint"); 
			      }
			      
			      else if(prog_Submit!=null && prog_Submit.equalsIgnoreCase("DELETE")){
					  Debug.print("prog_Submit:" + prog_Submit);
					  
					  String progID = request.getParameter("progID");
					  String progCode = request.getParameter("progCode");
					  String progName = request.getParameter("progName");
					
					  if(progID != null && progID !="" && progCode != null && progCode != "")
					  {
						  result = remote.deleteProgram(progID,progCode);
					  }
					  
					  if(result.equalsIgnoreCase("success"))
					  {
						  request.setAttribute("status","Successfully Deleted");
					  }
					  progList = remote.getProgramList();
					  	
					  	request.setAttribute("progList", progList);
					  return mapping.findForward("progMaint"); 
			      }
		      
		  }
		  
		  
		  
		  
		//======================= Parthi Here :Action for frmAddNewPress ==================		
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("pressProcess")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  String result = null;
				  ArrayList pressList = null;
				  
			      String press_Submit = request.getParameter("press_Submit");
			      
			      if(press_Submit!=null && press_Submit.equalsIgnoreCase("ADD")){
					  Debug.print("press_Submit:" + press_Submit);
					  
					  String pressName = request.getParameter("pressName");
					  String pressAddress = request.getParameter("pressAddress");
					  String pressCity = request.getParameter("pressCity");
					  String pressZip = request.getParameter("pressZip");
					  
					  if(pressName != null && pressName !="")
					  {
						  result = remote.addPress(pressName,pressAddress,pressCity, pressZip);
					  }
					 
					  if(result.equalsIgnoreCase("success"))
					  {
						  request.setAttribute("status","New Press added successfully");
					  }
					  
					  pressList = remote.getPressList();
					    
					  request.setAttribute("pressList", pressList);
					  return mapping.findForward("pressMaint"); 
			      }
			      
			      else if(press_Submit!=null && press_Submit.equalsIgnoreCase("UPDATE")){
					  Debug.print("press_Submit:" + press_Submit);
					  
					  String pressID = request.getParameter("pressID");
					  String pressName = request.getParameter("pressName");
					  String pressAddress = request.getParameter("pressAddress");
					  String pressCity = request.getParameter("pressCity");
					  String pressZip = request.getParameter("pressZip");
					  
					  
					  if(pressID != null && pressID !="" && pressName != null && pressName != "")
					  {
						  result = remote.updatePress(pressID,pressName,pressAddress,pressCity,pressZip);
					  }
					 
					  if(result.equalsIgnoreCase("success"))
					  {
						  request.setAttribute("status","Successfully Updated");
					  }
					  pressList = remote.getPressList();
					    
					  request.setAttribute("pressList", pressList);
					  return mapping.findForward("pressMaint"); 
			      }
			      
			      else if(press_Submit!=null && press_Submit.equalsIgnoreCase("DELETE")){
					  Debug.print("press_Submit:" + press_Submit);
					  
					  String pressID = request.getParameter("pressID");
					  String pressName = request.getParameter("pressName");
					  String pressAddress = request.getParameter("pressAddress");
					  String pressCity = request.getParameter("pressCity");
					  String pressZip = request.getParameter("pressZip");
					  
					  if(pressID != null && pressID !="" && pressName != null && pressName != "")
					  {
						  result = remote.deletePress(pressID,pressName);
					  }
					  
					  if(result.equalsIgnoreCase("success"))
					  {
						  request.setAttribute("status","Successfully Deleted");
					  }
					  pressList = remote.getPressList();
					    
					  request.setAttribute("pressList", pressList);
					  return mapping.findForward("pressMaint"); 
			      }
			  }
			   
		  
		//======================= Parthi Here :Action for frmAddNewPress ==================		
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("centreProcess")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  String result = null;
				  ArrayList centreList = null;
				  
			      String centre_Submit = request.getParameter("centre_Submit");
			      
			      if(centre_Submit!=null && centre_Submit.equalsIgnoreCase("ADD")){
					  Debug.print("centre_Submit:" + centre_Submit);
					  
					  String centreCode = request.getParameter("centreCode");
					  String centreName = request.getParameter("centreName");
					  String centreAddress = request.getParameter("centreAddress");
					  String centreCity = request.getParameter("centreCity");
					  String centreZip = request.getParameter("centreZip");
					  
					  if(centreCode != null && centreCode !="")
					  {
						  result = remote.addCentre(centreCode,centreName,centreAddress, centreCity, centreZip);
					  }
					 
					  if(result.equalsIgnoreCase("success"))
					  {
						  request.setAttribute("status","New Centre added successfully");
					  }
					  
					  centreList = remote.getCentreList();
					    
					  request.setAttribute("centreList", centreList);
					  return mapping.findForward("centreMaint"); 
			      }
			      
			      else if(centre_Submit!=null && centre_Submit.equalsIgnoreCase("UPDATE")){
					  Debug.print("centre_Submit:" + centre_Submit);
					  
					  String centreID = request.getParameter("centreID");
					  String centreCode = request.getParameter("centreCode");
					  String centreName = request.getParameter("centreName");
					  String centreAddress = request.getParameter("centreAddress");
					  String centreCity = request.getParameter("centreCity");
					  String centreZip = request.getParameter("centreZip");
					  
					  
					  if(centreID != null && centreID !="" && centreCode != null && centreCode != "")
					  {
						  result = remote.updateCentre(centreID,centreCode,centreName,centreAddress,centreCity,centreZip);
					  }
					 
					  if(result.equalsIgnoreCase("success"))
					  {
						  request.setAttribute("status","Successfully Updated");
					  }
					  centreList = remote.getCentreList();
					    
					  request.setAttribute("centreList", centreList);
					  return mapping.findForward("centreMaint"); 
			      }
			      
			      else if(centre_Submit!=null && centre_Submit.equalsIgnoreCase("DELETE")){
					  Debug.print("centre_Submit:" + centre_Submit);
					  
					  String centreID = request.getParameter("centreID");
					  String centreCode = request.getParameter("centreCode");
					  String centreName = request.getParameter("centreName");
					  String centreAddress = request.getParameter("centreAddress");
					  String centreCity = request.getParameter("centreCity");
					  String centreZip = request.getParameter("centreZip");
					  
					  if(centreID != null && centreID !="" && centreCode != null && centreCode != "")
					  {
						  result = remote.deleteCentre(centreID,centreCode);
					  }
					  
					  if(result.equalsIgnoreCase("success"))
					  {
						  request.setAttribute("status","Successfully Deleted");
					  }
					 centreList = remote.getCentreList();
					    
					  request.setAttribute("centreList", centreList);
					  return mapping.findForward("centreMaint"); 
			      }
			  }
			    
		  
		//======================= Parthi Here :Action for frmProgramCourseMapping ==================	
		  
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("warehouseProcess")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  String result = null;
				  ArrayList courseList = null;
				   ArrayList progList =null;
				   
			      String map_submit = request.getParameter("map_submit");
			      
			      if(map_submit!=null && map_submit.equalsIgnoreCase("Map")){
					  Debug.print("map_submit:" + map_submit);
					  
					  String courseCode = request.getParameter("courseCode");
					  String courseBarcode = request.getParameter("courseBarcode");
					  String programCode = request.getParameter("progCode");
					  String programBarcode = request.getParameter("progBarcode");
					  String course_year = request.getParameter("course_year");
					  String medium = request.getParameter("medium");				 
					 
					  
					  courseList = remote.getCourseList();
					  progList = remote.getProgramList();
					  
					  // get the program bar-code  for selected program name
					  
					  if(programBarcode.equals(null) || programBarcode.equals(""))
					  {					 				  	
						  if(progList!=null && progList.size()!=0){
							  
								Iterator itProgList = progList.iterator();
								while(itProgList.hasNext()){
									String[] proList = (String [])itProgList.next();											
									String progId = proList[0];
									String progCode = proList[1];
									String progName= proList[2];
									String prog_Barcode=proList[3];
									
									if(progCode.equals(programCode))
									{
										programBarcode = prog_Barcode;										
									}
								}
						  }
					  }
					  
					  // get the course bar-code for selected Course code
						 if(courseBarcode.equals(null) || courseBarcode.equals(""))
						 {
							
								if(courseList!=null && courseList.size()!=0){
									
									Iterator itCourseList = courseList.iterator();
									while(itCourseList.hasNext()){
										String[] courList = (String [])itCourseList.next();										
										String courseID = courList[0];
										String course_Code = courList[1];
										String courseName = courList[2];
										String Course_Barcode = courList[5];
										
										if(course_Code.equals(courseCode))
										{
											courseBarcode=Course_Barcode;
										}
									}
								}
						 }					 
				
						 System.out.println("courseCode : "+courseCode);
						  System.out.println("courseBarcode : "+courseBarcode);					  
						  System.out.println("programCode : "+programCode);
						  System.out.println("programBarcode : "+programBarcode);
						  System.out.println("course_year : "+course_year);
						  System.out.println("medium : "+medium);
						  
					  if(courseCode != null && courseCode !="")
					  {
						  result = remote.mappingEntry(courseCode,programCode,course_year,medium,programBarcode,courseBarcode);
					  }
					 
					  if(result.equalsIgnoreCase("success"))
					  {
						  request.setAttribute("status","Mapped successfully");
					  }
					   
					   
					  	
					  	request.setAttribute("progList", progList);
						request.setAttribute("courseList", courseList);
						
					  return mapping.findForward("warehouseMaint"); 
			      }
			      
			      else if(map_submit!=null && map_submit.equalsIgnoreCase("Find")){
					  Debug.print("map_submit:" + map_submit);
					  ArrayList cList = null;
					  ArrayList pList = null;
					  String cName = null;
					  String cCode = null;
					  String pName = null;
					  String pCode = null;
					  String courseBarcode = request.getParameter("courseBarcode");
					  String progBarcode = request.getParameter("progBarcode");
					  
					  // get the program name and program code for scanned bar-code
					  
					  if(progBarcode != null)
					  {
						  pList = remote.getProgramList();					  	
						
						  if(pList!=null && pList.size()!=0){
								Iterator itProgList = pList.iterator();
								while(itProgList.hasNext()){
									String[] proList = (String [])itProgList.next();											
									String progId = proList[0];
									String progCode = proList[1];
									String progName= proList[2];
									String prog_Barcode=proList[3];
									if(prog_Barcode.equals(progBarcode))
									{
										pCode = progCode;
										pName = progName;
									}
								}
						  }
					  }
					  
					  
					  // get the course name and course code for scanned bar-code
					 if(courseBarcode != null)
					 {
						  cList = remote.getCourseList();
					   
					     
							if(cList!=null && cList.size()!=0){
								Iterator itCourseList = cList.iterator();
								while(itCourseList.hasNext()){
									String[] courList = (String [])itCourseList.next();										
									String courseID = courList[0];
									String courseCode = courList[1];
									String courseName = courList[2];
									String Course_Barcode = courList[5];
									if(Course_Barcode.equals(courseBarcode))
									{
										cCode = courseCode;
										cName = courseName;
									}
								}
							}
					 }
						System.out.println("pCode :"+pCode);
						System.out.println("pName :"+pName);
						System.out.println("cCode :"+cCode);
						System.out.println("cName :"+cName);
						
						request.setAttribute("progList", pList);
						request.setAttribute("courseList", cList);
						
						request.setAttribute("progBarcode", progBarcode);
						request.setAttribute("courseBarcode", courseBarcode);
											
						request.setAttribute("pCode", pCode);
						request.setAttribute("pName", pName);
						request.setAttribute("cCode", cCode);
						request.setAttribute("cName", cName);
						return mapping.findForward("warehouseMaint"); 
					  
			      }
			    
			      
			  }
		  
		  
		//======================= Parthi Here :Action for frmPrintingOrder ==================		
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("SelectCourseName")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  String result = null;
				  ArrayList courseList = null;	
				  ArrayList pressList = null;	
				  String courseName = null;
				  String pages = null;
					  
				  	String po_no = request.getParameter("po_no");
				  	String orderDate = request.getParameter("orderDate");
					  String courseCode = request.getParameter("courseCode");
					  String pressName = request.getParameter("pressName");
					  String pressAddress = request.getParameter("pressAddress");
					  String printNature = request.getParameter("printNature");
					  String copies = request.getParameter("copies");
					  String medium = request.getParameter("medium");
					  
					  courseList = remote.getCourseList();
					  pressList = remote.getPressList();
					  
					  
						if(courseList!=null && courseList.size()!=0)
						{
							Iterator itCourseList = courseList.iterator();
							while(itCourseList.hasNext())
							{
								String[] couList = (String [])itCourseList.next();
								String couID = couList[0];
								String couCode = couList[1];
								String couName = couList[2];
								String year = couList[3];
								String NoOfpages = couList[4];
								
								
								
								if(courseCode.equalsIgnoreCase(couCode))
								{
									courseName = couName;
									pages = NoOfpages;									
								}
							}
						}
					  
						request.setAttribute("po_no", po_no);  
						request.setAttribute("orderDate", orderDate);
						request.setAttribute("pressName", pressName);  
						request.setAttribute("pressAddress", pressAddress);
						request.setAttribute("courseCode", courseCode);
						request.setAttribute("courseName", courseName);
						request.setAttribute("printNature", printNature);
						request.setAttribute("pages", pages);
						request.setAttribute("copies", copies);
						request.setAttribute("medium", medium);
						
					  request.setAttribute("courseList", courseList);
					  request.setAttribute("pressList", pressList);
					  return mapping.findForward("printOrder"); 
			      }
			 
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("SelectPressAddress")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  String result = null;
				  ArrayList courseList = null;	
				  ArrayList pressList = null;
				 String pressAddress = null;
					  
				  String po_no = request.getParameter("po_no");
				  String orderDate = request.getParameter("orderDate");
				  String pressName = request.getParameter("pressName");
			
				  String courseCode = request.getParameter("courseCode");
				  String courseName = request.getParameter("courseName");
				  String printNature = request.getParameter("printNature");
				  String pages = request.getParameter("pages");
				  String medium = request.getParameter("medium");
				  String copies = request.getParameter("copies");
					  
					  courseList = remote.getCourseList();
					  pressList = remote.getPressList();
					  
						if(pressList!=null && pressList.size()!=0)
						{
							Iterator itPressList = pressList.iterator();
							while(itPressList.hasNext())
							{
								String[] preList = (String [])itPressList.next();
								String preID = preList[0];
								String preName = preList[1];
								String preAddress = preList[2];
								String preCity = preList[3];
							
								
								if(pressName.equalsIgnoreCase(preName))
								{
									pressAddress = preAddress;	
									System.out.println("pressAddress : "+pressAddress);
								}
							}
						}
					  
						request.setAttribute("po_no", po_no);  
						request.setAttribute("orderDate", orderDate);
						request.setAttribute("pressName", pressName);  
						request.setAttribute("pressAddress", pressAddress);
						request.setAttribute("courseCode", courseCode);
						request.setAttribute("courseName", courseName);
						request.setAttribute("printNature", printNature);
						request.setAttribute("pages", pages);
						request.setAttribute("copies", copies);
						request.setAttribute("medium", medium);
						
					  request.setAttribute("courseList", courseList);
					  request.setAttribute("pressList", pressList);
					  return mapping.findForward("printOrder"); 
			      }
		  
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("printingProcess")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  
				  String print_submit = request.getParameter("print_submit");
				  
				   if(print_submit!=null && print_submit.equalsIgnoreCase("Generater")){
					  Debug.print("print_submit:" + print_submit);
					  ArrayList pressList = null;
					  ArrayList courseList = null;
						String po_no = "";
						
						po_no = remote.getPO_NO();
						 System.out.println("po_no : "+po_no);
						 
						  courseList = remote.getCourseList();
						  pressList = remote.getPressList();
						  
						  request.setAttribute("pressList", pressList);
						  request.setAttribute("courseList", courseList);
						 request.setAttribute("po_no",po_no);
						 return mapping.findForward("printOrder"); 
			      }
				  
				  
				   else if(print_submit!=null && print_submit.equalsIgnoreCase("ADD")){
					  Debug.print("print_submit:" + print_submit);
				  
					  String result = null;
					  ArrayList courseList = null;	
					  ArrayList pressList = null;
					  ArrayList orderList = null;
					  
					  String po_no = request.getParameter("po_no");
					  String orderDate = request.getParameter("orderDate");
					  String pressName = request.getParameter("pressName");
					  String pressAddress = request.getParameter("pressAddress");
					  String courseCode = request.getParameter("courseCode");
					  String courseName = request.getParameter("courseName");
					  String printNature = request.getParameter("printNature");
					  String pages = request.getParameter("pages");
					  String medium = request.getParameter("medium");
					  String copies = request.getParameter("copies");
					 
					  result = remote.addPrintOrder(po_no, orderDate, pressName, pressAddress, courseCode, courseName, printNature, pages, medium, copies);
					  
					  if(po_no!=null && orderDate !=null && pressName!=null)
					  {
						  orderList = remote.getOrderList(po_no,orderDate,pressName); // get the ordered list
					  }
					  
					  pressList = remote.getPressList();
					  courseList = remote.getCourseList();
					  
					  request.setAttribute("po_no", po_no);
					  request.setAttribute("orderDate", orderDate);
					  request.setAttribute("pressName", pressName);
					  request.setAttribute("pressAddress", pressAddress);
					  
					  request.setAttribute("pressList", pressList);
					  request.setAttribute("courseList", courseList);
					  request.setAttribute("orderList", orderList);
					  return mapping.findForward("printOrder"); 
			      }
			      
			      
			  }
		  
		//======================= Parthi Here :Action for frmPrintingOrder ==================		
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("SelectNameOfCourse")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  String result = null;
				  ArrayList courseList = null;	
				  ArrayList pressList = null;	
				  String courseName = null;
				
					  
				  	String po_no = request.getParameter("po_no");
				  	String orderDate = request.getParameter("orderDate");					 
					String dc_no = request.getParameter("dc_no");
				  	String deliveryDate = request.getParameter("deliveryDate");
				 	String pressName = request.getParameter("pressName");
				 	
				  	String courseCode = request.getParameter("courseCode");				
				  	
					String copies = request.getParameter("copies");
					String medium = request.getParameter("medium");
					  
					  courseList = remote.getCourseList();
					  pressList = remote.getPressList();
					  
					  
						if(courseList!=null && courseList.size()!=0)
						{
							Iterator itCourseList = courseList.iterator();
							while(itCourseList.hasNext())
							{
								String[] couList = (String [])itCourseList.next();
								String couID = couList[0];
								String couCode = couList[1];
								String couName = couList[2];
								String year = couList[3];
								String NoOfpages = couList[4];
								
								if(courseCode.equalsIgnoreCase(couCode))
								{
									courseName = couName;
									System.out.println("courseName : "+courseName);							
								}
							}
						}
					  
						request.setAttribute("po_no", po_no);  
						request.setAttribute("orderDate", orderDate);
						request.setAttribute("dc_no", dc_no);  
						request.setAttribute("deliveryDate", deliveryDate);
						
						request.setAttribute("pressName", pressName);  					
						request.setAttribute("courseCode", courseCode);
						request.setAttribute("courseName", courseName);					
						
						request.setAttribute("copies", copies);
						request.setAttribute("medium", medium);
						
					  request.setAttribute("courseList", courseList);
					  request.setAttribute("pressList", pressList);
					  return mapping.findForward("inward"); 
			      }
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("inwardProcess")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  
				  String inward_submit = request.getParameter("inward_submit");
				  
				   
					  if(inward_submit!=null && inward_submit.equalsIgnoreCase("ADD")){
						  Debug.print("inward_submit:" + inward_submit);
					  
						  String result = null;
						  ArrayList courseList = null;	
						  ArrayList pressList = null;
						
						  
						  String po_no = request.getParameter("po_no");
						  String orderDate = request.getParameter("orderDate");
						  String pressName = request.getParameter("pressName");
						  String dc_no = request.getParameter("dc_no");
						  String deliveryDate = request.getParameter("deliveryDate");
						  String courseCode = request.getParameter("courseCode");
						  String courseName = request.getParameter("courseName");
						  String courseBarcode = request.getParameter("courseBarcode");	
						  String medium = request.getParameter("medium");
						  String copies = request.getParameter("copies");
						 
						  result = remote.addInward(po_no, orderDate, pressName, dc_no,deliveryDate, courseCode, courseName,medium, copies, courseBarcode);
						  
						  pressList = remote.getPressList();
						  courseList = remote.getCourseList();
						  
						  request.setAttribute("courseList", courseList);
						  request.setAttribute("pressList", pressList);
						  return mapping.findForward("inward");
			      }
					  
					  if(inward_submit!=null && inward_submit.equalsIgnoreCase("Find")){
						  Debug.print("inward_submit:" + inward_submit);
						  ArrayList courseList = null;	
						  ArrayList pressList = null;
						  String courseCode = null;
						  String courseName = null;
						
						
							  
						  	String po_no = request.getParameter("po_no");
						  	String orderDate = request.getParameter("orderDate");					 
							String dc_no = request.getParameter("dc_no");
						  	String deliveryDate = request.getParameter("deliveryDate");
						 	String pressName = request.getParameter("pressName");
						 	
						  	String courseBarcode = request.getParameter("courseBarcode");				
						  	
							String copies = request.getParameter("copies");
							String medium = request.getParameter("medium");
							
							  System.out.println("po_no : "+po_no);	
							  System.out.println("orderDate : "+orderDate);	
							  System.out.println("dc_no : "+dc_no);	
							  System.out.println("deliveryDate : "+deliveryDate);	
							  System.out.println("pressName : "+pressName);	
							  System.out.println("courseBarcode : "+courseBarcode);	
							  System.out.println("copies : "+copies);	
							  
							  courseList = remote.getCourseList();
							  pressList = remote.getPressList();
							  
							  
								if(courseList!=null && courseList.size()!=0)
								{
									Iterator itCourseList = courseList.iterator();
									while(itCourseList.hasNext())
									{
										String[] couList = (String [])itCourseList.next();
										String couID = couList[0];
										String couCode = couList[1];
										String couName = couList[2];
										String year = couList[3];
										String NoOfpages = couList[4];
										String cBarcode = couList[5];
										
										if(cBarcode.equals(courseBarcode))
										{
											courseCode = couCode;
											courseName = couName;
											  System.out.println("courseCode : "+courseCode);	
											  System.out.println("courseName : "+courseName);	
																
										}
									}
								}
								
								
								  
								request.setAttribute("po_no", po_no);  
								request.setAttribute("orderDate", orderDate);
								request.setAttribute("dc_no", dc_no);  
								request.setAttribute("deliveryDate", deliveryDate);
								
								request.setAttribute("pressName", pressName);  					
								request.setAttribute("courseCode", courseCode);
								request.setAttribute("courseName", courseName);		
								
								request.setAttribute("courseBarcode", courseBarcode);		
								request.setAttribute("copies", copies);
								request.setAttribute("medium", medium);
								
							  request.setAttribute("courseList", courseList);
							  request.setAttribute("pressList", pressList);
							  return mapping.findForward("inward"); 
					  
					  }
			  }
		  
		  
		  
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("SelectCentreName")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  String result = null;
				  ArrayList centreList = null;	
				  ArrayList progList = null;
				  String centerName = null;
				  String progName = null;
					  
				  String centreCode = request.getParameter("centreCode");
				  
				  String programCode = request.getParameter("programCode");
				  String programName = request.getParameter("programName");
			
				  String medium = request.getParameter("medium");
				  String year_of_study = request.getParameter("year_of_study");
				  
				  String quantity = request.getParameter("quantity");
				  String requestedDate = request.getParameter("requestedDate");
				 
					  
				      centreList = remote.getCentreList();
				      progList = remote.getProgramList();
					  
						if(centreList!=null && centreList.size()!=0)
						{
							Iterator itCentreList = centreList.iterator();
							while(itCentreList.hasNext())
							{
								String[] cenList = (String [])itCentreList.next();
								String cenID = cenList[0];
								String cenCode = cenList[1];
								String cenName = cenList[2];
								
							
								
								if(centreCode.equalsIgnoreCase(cenCode))
								{
									centerName = cenName;	
									System.out.println("centerName : "+centerName);
								}
							}
						}
					  
						request.setAttribute("centreCode", centreCode); 
						request.setAttribute("centreName", centerName);
						
						request.setAttribute("programCode", programCode);
						request.setAttribute("programName", programName);  
						
						request.setAttribute("medium", medium);						
						request.setAttribute("year_of_study", year_of_study);
						
						request.setAttribute("quantity", quantity);
						request.setAttribute("requestedDate", requestedDate);
						
					  request.setAttribute("centreList", centreList);
					  request.setAttribute("progList", progList);
					  return mapping.findForward("indentRequest"); 
			      }
		  
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("SelectProgramName")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  String result = null;
				  ArrayList centreList = null;	
				  ArrayList progList = null;
				  String centerName = null;
				  String progName = null;
					  
				  String centreCode = request.getParameter("centreCode");
				  String centreName = request.getParameter("centreName");
				  
				  String programCode = request.getParameter("programCode");
				 
				  String medium = request.getParameter("medium");				  
				  String year_of_study = request.getParameter("year_of_study");
				  
				  String quantity = request.getParameter("quantity");
				  String requestedDate = request.getParameter("requestedDate");
				 
					  
				      centreList = remote.getCentreList();
				      progList = remote.getProgramList();
					  
						if(progList!=null && progList.size()!=0)
						{
							Iterator itProgList = progList.iterator();
							while(itProgList.hasNext())
							{
								String[] proList = (String [])itProgList.next();
								String proID = proList[0];
								String proCode = proList[1];
								String proName = proList[2];
								
								if(programCode.equalsIgnoreCase(proCode))
								{
									progName = proName;	
									System.out.println("progName : "+progName);
								}
							}
						}
					  
						request.setAttribute("centreCode", centreCode);						
						request.setAttribute("centreName", centreName); 
						
						request.setAttribute("programCode", programCode);
						request.setAttribute("progName", progName);
						
						request.setAttribute("medium", medium);						
						request.setAttribute("year_of_study", year_of_study);
						
						request.setAttribute("quantity", quantity);
						request.setAttribute("requestedDate", requestedDate);
						
						
					  request.setAttribute("centreList", centreList);
					  request.setAttribute("progList", progList);
					  return mapping.findForward("indentRequest"); 
			      }
		  
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("indentRequestProcess")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  
				  String indent_submit = request.getParameter("indent_submit");
				  
				   
					  if(indent_submit!=null && indent_submit.equalsIgnoreCase("ADD")){
						  Debug.print("indent_submit:" + indent_submit);
					  
						  String result = null;
						  ArrayList centreList = null;	
						  ArrayList progList = null;
						  ArrayList requestList = null;
						  
						  String centreCode = request.getParameter("centreCode");
						  String centreName = request.getParameter("centreName");
						  
						  String programCode = request.getParameter("programCode");
						  String programName = request.getParameter("programName");
						  
						  String medium = request.getParameter("medium");
						  String year_of_study = request.getParameter("year_of_study");
						  
						  String quantity = request.getParameter("quantity");
						  String requestedDate = request.getParameter("requestedDate");
						  
						 
						  result = remote.addMatRequest(centreCode, centreName, programCode, programName, medium, year_of_study, quantity, requestedDate);
						  
						  
						  if(centreCode!=null && requestedDate!=null)
						  {
							  requestList = remote.getRequestList(centreCode,requestedDate); // get the requested list list
						  }
						  
						  
						  centreList = remote.getCentreList();
					      progList = remote.getProgramList();
						  
					      request.setAttribute("centreCode", centreCode);						
						  request.setAttribute("centreName", centreName); 
					      
					      request.setAttribute("centreList", centreList);
						  request.setAttribute("progList", progList);
						  request.setAttribute("requestList", requestList);
						  return mapping.findForward("indentRequest"); 
			      }
			  }
		  
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("srddRequestProcess")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  
				  String SRDD_submit = request.getParameter("SRDD_submit");
				  
				   
					  if(SRDD_submit!=null && SRDD_submit.equalsIgnoreCase("ADD")){
						  Debug.print("SRDD_submit:" + SRDD_submit);
					  
						  String result = null;
						  ArrayList centreList = null;	
						  ArrayList progList = null;
						  ArrayList requestList = null;
						  
						  String centreCode = request.getParameter("centreCode");
						  String centreName = request.getParameter("centreName");
						  
						  String programCode = request.getParameter("programCode");
						  String programName = request.getParameter("programName");
						  
						  String medium = request.getParameter("medium");
						  String year_of_study = request.getParameter("year_of_study");
						  
						  String quantity = request.getParameter("quantity");
						  String requestedDate = request.getParameter("requestedDate");
						  
						 
						  result = remote.addMatRequest(centreCode, centreName, programCode, programName, medium, year_of_study, quantity, requestedDate);
						  
						  
						  if(centreCode!=null && requestedDate!=null)
						  {
							  requestList = remote.getRequestList(centreCode,requestedDate); // get the requested list list
						  }
						  
						  
						  centreList = remote.getCentreList();
					      progList = remote.getProgramList();
						  
					      request.setAttribute("centreCode", centreCode);						
						  request.setAttribute("centreName", centreName); 
					      
					      request.setAttribute("centreList", centreList);
						  request.setAttribute("progList", progList);
						  request.setAttribute("requestList", requestList);
						  return mapping.findForward("SRDDRequest"); 
			      }
			  }
		  
		  
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("SelectCentreName_SRDD")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  String result = null;
				  ArrayList centreList = null;	
				  ArrayList progList = null;
				  String centerName = null;
				  String progName = null;
					  
				  String centreCode = request.getParameter("centreCode");
				  
				  String programCode = request.getParameter("programCode");
				  String programName = request.getParameter("programName");
			
				  String medium = request.getParameter("medium");
				  String year_of_study = request.getParameter("year_of_study");
				  
				  String quantity = request.getParameter("quantity");
				  String requestedDate = request.getParameter("requestedDate");
				 
					  
				      centreList = remote.getCentreList();
				      progList = remote.getProgramList();
					  
						if(centreList!=null && centreList.size()!=0)
						{
							Iterator itCentreList = centreList.iterator();
							while(itCentreList.hasNext())
							{
								String[] cenList = (String [])itCentreList.next();
								String cenID = cenList[0];
								String cenCode = cenList[1];
								String cenName = cenList[2];
								
							
								
								if(centreCode.equalsIgnoreCase(cenCode))
								{
									centerName = cenName;	
									System.out.println("centerName : "+centerName);
								}
							}
						}
					  
						request.setAttribute("centreCode", centreCode); 
						request.setAttribute("centreName", centerName);
						
						request.setAttribute("programCode", programCode);
						request.setAttribute("programName", programName);  
						
						request.setAttribute("medium", medium);						
						request.setAttribute("year_of_study", year_of_study);
						
						request.setAttribute("quantity", quantity);
						request.setAttribute("requestedDate", requestedDate);
						
					  request.setAttribute("centreList", centreList);
					  request.setAttribute("progList", progList);
					  return mapping.findForward("SRDDRequest"); 
			      }
		  
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("SelectProgramName_SRDD")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  String result = null;
				  ArrayList centreList = null;	
				  ArrayList progList = null;
				  String centerName = null;
				  String progName = null;
					  
				  String centreCode = request.getParameter("centreCode");
				  String centreName = request.getParameter("centreName");
				  
				  String programCode = request.getParameter("programCode");
				 
				  String medium = request.getParameter("medium");				  
				  String year_of_study = request.getParameter("year_of_study");
				  
				  String quantity = request.getParameter("quantity");
				  String requestedDate = request.getParameter("requestedDate");
				 
					  
				      centreList = remote.getCentreList();
				      progList = remote.getProgramList();
					  
						if(progList!=null && progList.size()!=0)
						{
							Iterator itProgList = progList.iterator();
							while(itProgList.hasNext())
							{
								String[] proList = (String [])itProgList.next();
								String proID = proList[0];
								String proCode = proList[1];
								String proName = proList[2];
								
								if(programCode.equalsIgnoreCase(proCode))
								{
									progName = proName;	
									System.out.println("progName : "+progName);
								}
							}
						}
					  
						request.setAttribute("centreCode", centreCode);						
						request.setAttribute("centreName", centreName); 
						
						request.setAttribute("programCode", programCode);
						request.setAttribute("progName", progName);
						
						request.setAttribute("medium", medium);						
						request.setAttribute("year_of_study", year_of_study);
						
						request.setAttribute("quantity", quantity);
						request.setAttribute("requestedDate", requestedDate);
						
						
					  request.setAttribute("centreList", centreList);
					  request.setAttribute("progList", progList);
					  return mapping.findForward("SRDDRequest"); 
			      }
		  
		  //============= Material Issue Part ==================
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("matIssueProcess")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  
				  String matIssue_submit = request.getParameter("matIssue_submit");
				  
				   
					  if(matIssue_submit!=null && matIssue_submit.equalsIgnoreCase("Search")){
						  Debug.print("matIssue_submit:" + matIssue_submit);
					  
						  String result = null;						  
						  ArrayList requestList = null;
						  
						  String requestedDate = request.getParameter("requestedDate");
						 
						  requestList = remote.searchRequestList(requestedDate); // get the material requested list from database
						 
						  request.setAttribute("requestedDate", requestedDate);
						  request.setAttribute("requestList", requestList);
						  return mapping.findForward("matIssue"); 
			      }
					  else if(matIssue_submit!=null && matIssue_submit.equalsIgnoreCase("Issue")){
						 
						    int i=0;
						  	String result = "";
						  	ArrayList Mat_list = new ArrayList();
						  	int mat_count = Integer.parseInt(request.getParameter("mat_count"));
						  	
						  	while(mat_count > 0 && i < mat_count)
						  	{
						  		i++;
						  		String centreCode = request.getParameter("centreCode_"+i);					  		
						  		String centreName = request.getParameter("centreName_"+i);		
						  		String progCode = request.getParameter("progCode_"+i);
						  		
						  		String progName = request.getParameter("progName_"+i);					  		
						  		String mediumType  = request.getParameter("medium_"+i);		
						  		String yeas_Of_Study  = request.getParameter("year_"+i);
						  		
						  		String available_Quantity  = request.getParameter("aQuantity_"+i);					  		
						  		String request_Quantity  = request.getParameter("rQuantity_"+i);		
						  		String Issue_Quantity  = request.getParameter("iQuantity_"+i);
						  		
						  		String chk_Select   = request.getParameter("select_"+i);
						  		
						  		String Issue_Date = dateFormat.format(date); 
						  		String requestedDate =  request.getParameter("requestedDate");  //get current date time with Date()
						  				
						  		
						  		
						  		if(chk_Select!=null && chk_Select.trim().length() != 0)
						  		{
						  			System.out.println("centreCode : " +centreCode);
							  		System.out.println("centreName : " +centreName);
							  		System.out.println("progCode : " +progCode);
							  		System.out.println("progName : " +progName);
							  		System.out.println("mediumType : " +mediumType);
							  		System.out.println("yeas_Of_Study : " +yeas_Of_Study);
							  		System.out.println("available_Quantity : " +available_Quantity);
							  		System.out.println("request_Quantity : " +request_Quantity);
							  		System.out.println("Issue_Quantity : " +Issue_Quantity);
							  		System.out.println("Issue_Date : " +Issue_Date);
							  		System.out.println("requestedDate : " +requestedDate);
							  		
							  		result = remote.matIssue(centreCode, centreName, progCode, progName, mediumType, yeas_Of_Study, 
							  				available_Quantity, request_Quantity, Issue_Quantity, Issue_Date, requestedDate);						  							  		
							  		
						  		}
						  	}						  							  	
						  	
						  	if(result.equalsIgnoreCase("success"))
						  	{
						  		request.setAttribute("status","Suuessfully Issued.");
						  	}
					return mapping.findForward("matIssue"); 
					  }
			  }
		  
		//============= Pending Material Issue Part ==================
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("matPendingIssueProcess")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  
				  String pendIssue_submit = request.getParameter("pendIssue_submit");
				  
				   
					  if(pendIssue_submit!=null && pendIssue_submit.equalsIgnoreCase("Search")){
						  Debug.print("pendIssue_submit:" + pendIssue_submit);
					  
						  String result = null;						  
						  ArrayList requestList = null;
						  
						  String requestedType = request.getParameter("requestedType");
						 
						  requestList = remote.searchPendRequestList(requestedType);
						 
						  request.setAttribute("requestedType", requestedType);
						  request.setAttribute("requestList", requestList);
						  return mapping.findForward("pendingIssue"); 
			      }
					  else if(pendIssue_submit!=null && pendIssue_submit.equalsIgnoreCase("Issue")){
						 
						    int i=0;
						  	String result = "";
						  	ArrayList Mat_list = new ArrayList();
						  	int mat_count = Integer.parseInt(request.getParameter("mat_count"));
						  	
						  	while(mat_count > 0 && i < mat_count)
						  	{
						  		i++;
						  		String centreCode = request.getParameter("centreCode_"+i);					  		
						  		String centreName = request.getParameter("centreName_"+i);		
						  		String progCode = request.getParameter("progCode_"+i);
						  		
						  		String progName = request.getParameter("progName_"+i);					  		
						  		String mediumType  = request.getParameter("medium_"+i);		
						  		String yeas_Of_Study  = request.getParameter("year_"+i);
						  		
						  		String available_Quantity  = request.getParameter("aQuantity_"+i);					  		
						  		String request_Quantity  = request.getParameter("rQuantity_"+i);		
						  		String Issue_Quantity  = request.getParameter("iQuantity_"+i);
						  		String requestedDate =  request.getParameter("requestedDate_"+i); 
						  		String chk_Select   = request.getParameter("select_"+i);
						  		
						  		String Issue_Date = dateFormat.format(date); 
						  		 //get current date time with Date()
						  				
						  		
						  		
						  		if(chk_Select!=null && chk_Select.trim().length() != 0)
						  		{
						  			System.out.println("centreCode : " +centreCode);
							  		System.out.println("centreName : " +centreName);
							  		System.out.println("progCode : " +progCode);
							  		System.out.println("progName : " +progName);
							  		System.out.println("mediumType : " +mediumType);
							  		System.out.println("yeas_Of_Study : " +yeas_Of_Study);
							  		System.out.println("available_Quantity : " +available_Quantity);
							  		System.out.println("request_Quantity : " +request_Quantity);
							  		System.out.println("Issue_Quantity : " +Issue_Quantity);
							  		System.out.println("Issue_Date : " +Issue_Date);
							  		System.out.println("requestedDate : " +requestedDate);
							  		
							  		result = remote.matIssue(centreCode, centreName, progCode, progName, mediumType, yeas_Of_Study, 
							  				available_Quantity, request_Quantity, Issue_Quantity, Issue_Date, requestedDate);						  							  		
							  		
						  		}
						  	}						  							  	
						  	
						  	if(result.equalsIgnoreCase("success"))
						  	{
						  		request.setAttribute("status","Suuessfully Issued.");
						  	}
					return mapping.findForward("pendingIssue"); 
					  }
			  }
		  
		  
		  
	//========================== Inward report =======================
		 
		
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("inwardReportProcess")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  ArrayList inwardReport_List = new ArrayList();
				  InwardReportAction InRepObj = new InwardReportAction();
				  
				  String inwardReport_submit = request.getParameter("inwardReport_submit");

				  if(inwardReport_submit!=null && inwardReport_submit.equalsIgnoreCase("Find Report")){
					  Debug.print("inwardReport_submit:" + inwardReport_submit);
					  
					  String requestedType = request.getParameter("requestedType");
					  String fromDate = request.getParameter("fDate");
					  String toDate = request.getParameter("tDate");
					  
					  inwardReport_List = remote.getInwardReport(requestedType, fromDate, toDate);
					  
					  request.setAttribute("requestedType", requestedType);
					  request.setAttribute("fromDate", fromDate);
					  request.setAttribute("toDate", toDate);
					  request.setAttribute("inwardReport_List", inwardReport_List);
					  
					  return mapping.findForward("inwardReport");
				  }
				  else  if(inwardReport_submit!=null && inwardReport_submit.equalsIgnoreCase("Download")){
					  Debug.print("inwardReport_submit:" + inwardReport_submit);
					  
					  String requestedType = request.getParameter("requestedType");
					  String fromDate = request.getParameter("fDate");
					  String toDate = request.getParameter("tDate");
					  
					  inwardReport_List = remote.getInwardReport(requestedType, fromDate, toDate);
					  
					  InRepObj.Export_to_Excel(inwardReport_List, fromDate, toDate);
					  
					  return mapping.findForward("inwardReport");
				  }
				  
				  
			  }
		  
		//========================== Press report =======================
			 
			
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("pressReportProcess")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  ArrayList pressReport_List = new ArrayList();
				  PressReportAction pressRepObj = new PressReportAction();
				  
				  String pressReport_submit = request.getParameter("pressReport_submit");

				  if(pressReport_submit!=null && pressReport_submit.equalsIgnoreCase("Find Report")){
					  Debug.print("pressReport_submit:" + pressReport_submit);
					  
					  String requestedType = request.getParameter("requestedType");
					  String fromDate = request.getParameter("fDate");
					  String toDate = request.getParameter("tDate");
					  
					  pressReport_List = remote.getPressReport(requestedType, fromDate, toDate);
					  
					  request.setAttribute("requestedType", requestedType);
					  request.setAttribute("fromDate", fromDate);
					  request.setAttribute("toDate", toDate);
					  request.setAttribute("pressReport_List", pressReport_List);
					  
					  return mapping.findForward("pressReport");
				  }
				  else  if(pressReport_submit!=null && pressReport_submit.equalsIgnoreCase("Download")){
					  Debug.print("pressReport_submit:" + pressReport_submit);
					  
					  String requestedType = request.getParameter("requestedType");
					  String fromDate = request.getParameter("fDate");
					  String toDate = request.getParameter("tDate");
					  
					  pressReport_List = remote.getPressReport(requestedType, fromDate, toDate);
					  
					  pressRepObj.Export_to_Excel(pressReport_List, fromDate, toDate);
					  
					  return mapping.findForward("pressReport");
				  }
				  
				  
			  }
		  
		  
		//========================== Request report =======================
			 
			
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("requestReportProcess")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  ArrayList reqReport_List = new ArrayList();
				  RequestReportAction reqRepObj = new RequestReportAction();
				  
				  String requestReport_submit = request.getParameter("requestReport_submit");

				  if(requestReport_submit!=null && requestReport_submit.equalsIgnoreCase("Find Report")){
					  Debug.print("requestReport_submit:" + requestReport_submit);
					  
					  String requestedType = request.getParameter("requestedType");
					  String fromDate = request.getParameter("fDate");
					  String toDate = request.getParameter("tDate");
					  
					  reqReport_List = remote.getRequestReport(requestedType, fromDate, toDate);
					  
					  request.setAttribute("requestedType", requestedType);
					  request.setAttribute("fromDate", fromDate);
					  request.setAttribute("toDate", toDate);
					  request.setAttribute("reqReport_List", reqReport_List);
					  
					  return mapping.findForward("requestReport");
				  }
				  else  if(requestReport_submit!=null && requestReport_submit.equalsIgnoreCase("Download")){
					  Debug.print("requestReport_submit:" + requestReport_submit);
					  
					  String requestedType = request.getParameter("requestedType");
					  String fromDate = request.getParameter("fDate");
					  String toDate = request.getParameter("tDate");
					  
					  reqReport_List = remote.getRequestReport(requestedType, fromDate, toDate);
					  
					  reqRepObj.Export_to_Excel(reqReport_List, fromDate, toDate);
					  
					  return mapping.findForward("requestReport");
				  }
				  
				  
			  }
		  
		//========================== Issue report =======================
			 
			
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("issueReportProcess")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  ArrayList issReport_List = new ArrayList();
				  IssueReportAction issRepObj = new IssueReportAction();
				  
				  String issueReport_submit = request.getParameter("issueReport_submit");

				  if(issueReport_submit!=null && issueReport_submit.equalsIgnoreCase("Find Report")){
					  Debug.print("issueReport_submit:" + issueReport_submit);
					  
					  String reporttype = request.getParameter("reporttype");
					  
					  String requestedType = request.getParameter("requestedType");
					  String fromDate = request.getParameter("fDate");
					  String toDate = request.getParameter("tDate");
					  if(reporttype.equalsIgnoreCase("Daily")){
					 
					  
					  issReport_List = remote.getIssueReport(requestedType, fromDate, toDate);
					  
					  request.setAttribute("requestedType", requestedType);
					  request.setAttribute("fromDate", fromDate);
					  request.setAttribute("toDate", toDate);
					  request.setAttribute("issReport_List", issReport_List);
					  request.setAttribute("tnou", "tnou");
					  return mapping.findForward("issueReport");
					  }else{
						  ArrayList issProissue_List = new ArrayList();
						  ArrayList programslis= remote.getProgramcodelist();
						  
						  
						  issReport_List = remote.getIssueReport(requestedType, fromDate, toDate);
						  
						  if(programslis!=null && programslis.size()!=0)
							{
								Iterator itproList = programslis.iterator();
								
								while(itproList.hasNext())
								{
									
									String[] proList = (String [])itproList.next();	
														
						          	 String procode  = proList[0];
						          	String pro_name  = proList[1];
								
						  
						  if(issReport_List!=null && issReport_List.size()!=0)
							{
								Iterator itReqList = issReport_List.iterator();
								int eng_total = 0;int tam_total = 0;int total=0;
								
								while(itReqList.hasNext())
								{
									
									String[] reqList = (String [])itReqList.next();	
														
									String issued_date  = reqList[0];	
									String centre_code  = reqList[1];	
									String centre_name  = reqList[2];	
									String program_code  = reqList[3];	  
									String proname   = reqList[4];	
									String medium  = reqList[5];	
									String year_of_study  = reqList[6];	
									String issued_quantity = reqList[7];
									
									if(procode.equals(program_code)){
										
										if(medium.equals("English")){
											int a = Integer.parseInt(issued_quantity);
											eng_total=eng_total+a;	
										}else if(medium.equals("Tamil")){
											int a = Integer.parseInt(issued_quantity);
											tam_total=tam_total+a;
										}
										total=eng_total+tam_total;
									 
								}
							}
								String totals=Integer.toString(total);String englishtotal=Integer.toString(eng_total);
								String tamiltotal=Integer.toString(tam_total);
								  String[] temps={pro_name,englishtotal,tamiltotal,totals};
									issProissue_List.add(temps);
								
								}
						
							}
							}
						  
						  request.setAttribute("requestedType", requestedType);
						  request.setAttribute("fromDate", fromDate);
						  request.setAttribute("toDate", toDate);
						  request.setAttribute("issProissue_List", issProissue_List);
						  request.setAttribute("tnou", "digiBlitz");
						  return mapping.findForward("issueProReport");
					  }
					 
				  }
				  else  if(issueReport_submit!=null && issueReport_submit.equalsIgnoreCase("Download")){
					  Debug.print("issueReport_submit:" + issueReport_submit);
					  
					  String requestedType = request.getParameter("requestedType");
					  String fromDate = request.getParameter("fDate");
					  String toDate = request.getParameter("tDate");
					  
					  issReport_List = remote.getIssueReport(requestedType, fromDate, toDate);
					  
					  issRepObj.Export_to_Excel(issReport_List, fromDate, toDate);
					  
					  return mapping.findForward("issueReport");
				  }
				  
				  
			  }
		  
		  
		  
		//========================== Stock Status =======================
			 
			
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("stockStatusProcess")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  ArrayList stockStatus_List = new ArrayList();
				 
				  
				  String stochStatus_submit = request.getParameter("stochStatus_submit");

				  if(stochStatus_submit!=null && stochStatus_submit.equalsIgnoreCase("Find Stock")){
					  Debug.print("stochStatus_submit:" + stochStatus_submit);
					  
					  String requestedType = request.getParameter("requestedType");
					  String searchCode = request.getParameter("searchCode");	
					  String searchBarcode = request.getParameter("searchBarcode");	
					/*  System.out.println("requestedType : "+requestedType);
					  System.out.println("searchCode : "+searchCode);*/
					  
					  stockStatus_List = remote.getStockStatus(requestedType,searchCode);
					  
					  request.setAttribute("requestedType", requestedType);		
					  request.setAttribute("searchCode", searchCode);	
					  request.setAttribute("searchBarcode", searchBarcode);
					  
					  request.setAttribute("stockStatus_List", stockStatus_List);
					  
					  return mapping.findForward("stockStatus");
				  }
				  
				  else if(stochStatus_submit!=null && stochStatus_submit.equalsIgnoreCase("Find")){
					  Debug.print("stochStatus_submit:" + stochStatus_submit);
					  
					  String requestedType = request.getParameter("requestedType");
					  String searchBarcode = request.getParameter("searchBarcode");	
					  
					  System.out.println("requestedType : "+requestedType);
					  System.out.println("searchBarcode : "+searchBarcode);
					  
					  ArrayList courseList = null;
					  ArrayList progList = null;
					  String searchCode = null;
					  
					  courseList = remote.getCourseList();
					  progList = remote.getProgramList();
					  
					  // get the program code  for scanned bar-code
					  
					  if(requestedType.equalsIgnoreCase("Program Code Wise") && searchBarcode != null)
					  {					
						 // System.out.println("requestedType inside if : "+requestedType);
						  if(progList!=null && progList.size()!=0){
							  
								Iterator itProgList = progList.iterator();
								while(itProgList.hasNext()){
									String[] proList = (String [])itProgList.next();											
									String progId = proList[0];
									String progCode = proList[1];
									String progName= proList[2];
									String prog_Barcode=proList[3];
									
									if(searchBarcode.equals(prog_Barcode))
									{
										 //System.out.println("searchBarcode inside if : "+searchBarcode);
										searchCode = progCode;										
									}
								}
						  }
					  }
					  
					  // get the course code for scanned bar-code
					  if(requestedType.equalsIgnoreCase("Course Code Wise") && searchBarcode != null)
						 {
						//  System.out.println("requestedType inside if : "+requestedType);
								if(courseList!=null && courseList.size()!=0){
									
									Iterator itCourseList = courseList.iterator();
									while(itCourseList.hasNext()){
										String[] courList = (String [])itCourseList.next();										
										String courseID = courList[0];
										String course_Code = courList[1];
										String courseName = courList[2];
										String Course_Barcode = courList[5];
										
										if(searchBarcode.equals(Course_Barcode))
										{
											// System.out.println("searchBarcode inside if : "+searchBarcode);
											searchCode = course_Code;
										}
									}
								}
						 }					 
					  System.out.println("searchCode : "+searchCode);
					
					  request.setAttribute("searchCode", searchCode);
					  request.setAttribute("requestedType", requestedType);
					  request.setAttribute("searchBarcode", searchBarcode);
					  
					  return mapping.findForward("stockStatus");  
				  }
				  
				  
			  }
		//======================= Parthi Here :Action for frmPrintingOrder ==================		
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("SelectCourseNameOnIssue")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  String result = null;
				  ArrayList courseList = null;	
				  ArrayList centreList = null;	
				  String courseName = null;
				  String pages = null;
					  
				      String courseBarcode = request.getParameter("courseBarcode");
				      String courseCode = request.getParameter("courseCode");
					  //String courseName = request.getParameter("courseName");
					  String medium = request.getParameter("medium");
					  
					  String centreCode = request.getParameter("centreCode");
					  String centreName = request.getParameter("centreName");
					  String YOS = request.getParameter("YOS");
					  String availQuantity = request.getParameter("availQuantity");
					  
					  String reqQuantity = request.getParameter("reqQuantity");
					  String issQuantity = request.getParameter("issQuantity");
					  String reqDate = request.getParameter("reqDate");
					  String issDate = request.getParameter("issDate");
					  
					  
					  
					  
					  

 
					  courseList = remote.getCourseList();
					  centreList = remote.getCentreList();
					  
						if(courseList!=null && courseList.size()!=0)
						{
							Iterator itCourseList = courseList.iterator();
							while(itCourseList.hasNext())
							{
								String[] couList = (String [])itCourseList.next();
								String couID = couList[0];
								String couCode = couList[1];
								String couName = couList[2];
								String year = couList[3];
								String NoOfpages = couList[4];
								
								
								
								if(courseCode.equalsIgnoreCase(couCode))
								{
									courseName = couName;																
								}
							}
						}
						
					  
						request.setAttribute("courseBarcode", courseBarcode);  
						request.setAttribute("courseCode", courseCode);
						request.setAttribute("courseName", courseName);  
						request.setAttribute("medium", medium);
						
						request.setAttribute("centreCode", centreCode);
						request.setAttribute("centreName", centreName);
						request.setAttribute("YOS", YOS);
						request.setAttribute("availQuantity", availQuantity);
						
						request.setAttribute("reqQuantity", reqQuantity);
						request.setAttribute("issQuantity", issQuantity);
						request.setAttribute("reqDate", reqDate);
						request.setAttribute("issDate", issDate);
						
					  request.setAttribute("courseList", courseList);
					  request.setAttribute("centreList", centreList);
					  return mapping.findForward("courseIssue"); 
			      }
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("SelectCentreNameOnIssue")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  String result = null;
				  ArrayList centreList = null;	
				  ArrayList courseList = null;	
				  String centreName = null;
				  String progName = null;
					  
				  
				  String courseBarcode = request.getParameter("courseBarcode");
			      String courseCode = request.getParameter("courseCode");
				  String courseName = request.getParameter("courseName");
				  String medium = request.getParameter("medium");
				  
				  String centreCode = request.getParameter("centreCode");
				  //String centreName = request.getParameter("centreName");
				  String YOS = request.getParameter("YOS");
				  String availQuantity = request.getParameter("availQuantity");
				  
				  String reqQuantity = request.getParameter("reqQuantity");
				  String issQuantity = request.getParameter("issQuantity");
				  String reqDate = request.getParameter("reqDate");
				  String issDate = request.getParameter("issDate");
				 
					  
				  courseList = remote.getCourseList();
				  centreList = remote.getCentreList();
					  
						if(centreList!=null && centreList.size()!=0)
						{
							Iterator itCentreList = centreList.iterator();
							while(itCentreList.hasNext())
							{
								String[] cenList = (String [])itCentreList.next();
								String cenID = cenList[0];
								String cenCode = cenList[1];
								String cenName = cenList[2];
								
							
								
								if(centreCode.equalsIgnoreCase(cenCode))
								{
									centreName = cenName;	
									System.out.println("centerName : "+centreName);
								}
							}
						}
					  
						request.setAttribute("courseBarcode", courseBarcode);  
						request.setAttribute("courseCode", courseCode);
						request.setAttribute("courseName", courseName);  
						request.setAttribute("medium", medium);
						
						request.setAttribute("centreCode", centreCode);
						request.setAttribute("centreName", centreName);
						request.setAttribute("YOS", YOS);
						request.setAttribute("availQuantity", availQuantity);
						
						request.setAttribute("reqQuantity", reqQuantity);
						request.setAttribute("issQuantity", issQuantity);
						request.setAttribute("reqDate", reqDate);
						request.setAttribute("issDate", issDate);
						
					  request.setAttribute("courseList", courseList);
					  request.setAttribute("centreList", centreList);
					  return mapping.findForward("courseIssue"); 
			      }
		  
			  else if(stockMaintProcess!=null && stockMaintProcess.equalsIgnoreCase("courseMatIssue")){
				  Debug.print("stockMaintProcess:" + stockMaintProcess);
				  
				 String courseIssue_submit = request.getParameter("courseIssue_submit"); 
				  
				  if(courseIssue_submit!=null && courseIssue_submit.equalsIgnoreCase("Find")){
					  Debug.print("courseIssue_submit:" + courseIssue_submit);
					  String courseBarcode = request.getParameter("courseBarcode");
					  ArrayList courseList = null;
					  ArrayList centreList = null;
					  String courseCode = null;
					  String courseName = null;
					  
					  courseList = remote.getCourseList();
					  centreList = remote.getCentreList();
					  
						if(courseList!=null && courseList.size()!=0)
						{
							Iterator itCourseList = courseList.iterator();
							while(itCourseList.hasNext())
							{
								String[] couList = (String [])itCourseList.next();
								String couID = couList[0];
								String couCode = couList[1];
								String couName = couList[2];
								String year = couList[3];
								String NoOfpages = couList[4];
								String cbarcode = couList[5];
																
								if(cbarcode.equalsIgnoreCase(courseBarcode))
								{
									courseName = couName;	
									courseCode = couCode;
								}
							}
						}
						
						request.setAttribute("courseBarcode", courseBarcode);  
						request.setAttribute("courseCode", courseCode);
						request.setAttribute("courseName", courseName); 
						request.setAttribute("courseList", courseList);
						request.setAttribute("centreList", centreList);
						return mapping.findForward("courseIssue"); 
				  }
				  else if(courseIssue_submit!=null && courseIssue_submit.equalsIgnoreCase("Check")){
					  Debug.print("courseIssue_submit:" + courseIssue_submit);
					  String availQuantity = null;
					  ArrayList centreList = null;	
					  ArrayList courseList = null;	
					  //String centreName = null;
					  String progName = null;
						  
					  
					  String courseBarcode = request.getParameter("courseBarcode");
				      String courseCode = request.getParameter("courseCode");
					  String courseName = request.getParameter("courseName");
					  String medium = request.getParameter("medium");
					  
					  String centreCode = request.getParameter("centreCode");
					  String centreName = request.getParameter("centreName");
					  String YOS = request.getParameter("YOS");
					  //String availQuantity = request.getParameter("availQuantity");
					  
					  String reqQuantity = request.getParameter("reqQuantity");
					  String issQuantity = request.getParameter("issQuantity");
					  String reqDate = request.getParameter("reqDate");
					  String issDate = request.getParameter("issDate");
					  
					  availQuantity = remote.getCourseAvailQuantity(courseCode,medium,YOS);
					  courseList = remote.getCourseList();
					  centreList = remote.getCentreList();
					  
						request.setAttribute("courseBarcode", courseBarcode);  
						request.setAttribute("courseCode", courseCode);
						request.setAttribute("courseName", courseName);  
						request.setAttribute("medium", medium);
						
						request.setAttribute("centreCode", centreCode);
						request.setAttribute("centreName", centreName);
						request.setAttribute("YOS", YOS);
						request.setAttribute("availQuantity", availQuantity);
						
						request.setAttribute("reqQuantity", reqQuantity);
						request.setAttribute("issQuantity", issQuantity);
						request.setAttribute("reqDate", reqDate);
						request.setAttribute("issDate", issDate);
						
					  request.setAttribute("courseList", courseList);
					  request.setAttribute("centreList", centreList);
					  return mapping.findForward("courseIssue"); 
					  
				  }
				  
				  else if(courseIssue_submit!=null && courseIssue_submit.equalsIgnoreCase("Issue")){
					  Debug.print("courseIssue_submit:" + courseIssue_submit);				  
					  String result = null;
					  String status = null;
					  ArrayList centreList = null;	
					  ArrayList courseList = null;	
					  String courseBarcode = request.getParameter("courseBarcode");
				      String courseCode = request.getParameter("courseCode");
					  String courseName = request.getParameter("courseName");
					  String medium = request.getParameter("medium");
					  
					  String centreCode = request.getParameter("centreCode");
					  String centreName = request.getParameter("centreName");
					  String YOS = request.getParameter("YOS");
					  String availQuantity = request.getParameter("availQuantity");
					  
					  String reqQuantity = request.getParameter("reqQuantity");
					  String issQuantity = request.getParameter("issQuantity");
					  String reqDate = request.getParameter("reqDate");
					  String issDate = request.getParameter("issDate");
					  
					  if(courseCode != null && medium != null && YOS !=null)
					  {
						  result = remote.matCourseIssue(courseBarcode, courseCode, courseName, medium, centreCode,
								  centreName, YOS, availQuantity, reqQuantity, issQuantity, reqDate,  issDate);
					  }
				  
					  if(result.equalsIgnoreCase("success"))
					  {
						  status = "Material SuccessFully Issued...!";
					  }
					  courseList = remote.getCourseList();
					  centreList = remote.getCentreList();
					  
					  request.setAttribute("status", status);
					  request.setAttribute("courseList", courseList);
					  request.setAttribute("centreList", centreList);
					  return mapping.findForward("courseIssue");
				  }			  
			  
			  }
			 
		  
		  return null;
	}
}
