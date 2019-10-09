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
package com.lmsstock.stateless;



import com.lmsstock.util.DBHelper;
import com.lmsstock.util.Debug;
import com.lmsstock.util.LMSStockDAO;
import com.lmsstock.util.LMSStockDetails;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;







public class LMSStockstatelessBean implements SessionBean, LMSStockstatelessRemoteBusiness {
   
	
	private SessionContext context;
    private InitialContext ic = null;
    private static final String dbName = "java:/LMSMSSQLDS";
    private Connection con;
    ResultSet rs= null;
    PreparedStatement prepStmt = null;
   
    String mailId;
    Collection col;
    
    LMSStockDAO objDAO = new LMSStockDAO();
    LMSStockDetails bookObj;
    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">
    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise bean, Web services)
    // TODO Add business methods or web service operations
    /**
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    public void setSessionContext(SessionContext aContext) {
        context = aContext;
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
        
    }
    // </editor-fold>
    
    /**
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */
    public void ejbCreate() {
        /*try{
            InitialContext jndiContext = getInitialContext();
            Object obj = jndiContext.lookup(name);
            home = (HLCArabianSeaEntityUserLocalHome)obj;
        } catch(Exception e){
            e.printStackTrace();
        }*/
    }
   
  
 

 //======================= Parthi Here : Bean for Course master ====================
    
    public ArrayList getCourseList()throws RemoteException
	{
		ArrayList courseList = null;
		try{
			courseList = objDAO.getCourseList();
		}
		catch(Exception exp){
            
            exp.printStackTrace();
        }
		return courseList;
	}
	
    
    
   public String addCourse(String courseCode, String courseName,String yearOfCourse, String Pages,String courseBarcode)throws RemoteException
    {
    	String result = null;
    	try{
    		if(courseCode != null)
    		{
    			result = objDAO.addCourse(courseCode, courseName, yearOfCourse, Pages, courseBarcode);
    		}    	
    	}
    	catch(Exception exp){
            
            exp.printStackTrace();
        }
    	
    	return result;
    }
    
    
	
	public String updateCourse(String courseID, String courseCode, String courseName, String yearOfCourse, String pages)throws RemoteException
	{
		String result = null;
    	try{
    		if(courseID != null && courseCode != null)
    		{
    			result = objDAO.updateCourse(courseID,courseCode,courseName,yearOfCourse,pages);
    		}    	
    	}
    	catch(Exception exp){
            
            exp.printStackTrace();
        }
    	
    	return result;
	}
	
	public String deleteCourse(String courseID, String courseCode)throws RemoteException
	{
		String result = null;
    	try{
    		if(courseID != null && courseCode != null)
    		{
    			result = objDAO.deleteCourse(courseID,courseCode);
    		}    	
    	}
    	catch(Exception exp){
            
            exp.printStackTrace();
        }
    	
    	return result;
	}
    
    
         
 //======================= Parthi Here : Bean for Program master ====================
    
    public ArrayList getProgramList()throws RemoteException
	{
		ArrayList progList = null;
		try{
			progList = objDAO.getProgramList();
		}
		catch(Exception exp){
            
            exp.printStackTrace();
        }
		return progList;
	}
	
    
    
   public String addProgram(String progCode, String progName, String progBarcode)throws RemoteException
    {
    	String result = null;
    	try{
    		if(progCode != null)
    		{
    			result = objDAO.addProgram(progCode, progName,progBarcode);
    		}    	
    	}
    	catch(Exception exp){
            
            exp.printStackTrace();
        }
    	
    	return result;
    }
    
    
	
	public String updateProgram(String progID, String progCode, String progName)throws RemoteException
	{
		String result = null;
    	try{
    		if(progID != null && progID != null)
    		{
    			result = objDAO.updateProgram(progID,progCode,progName);
    		}    	
    	}
    	catch(Exception exp){
            
            exp.printStackTrace();
        }
    	
    	return result;
	}
	
	public String deleteProgram(String progID, String progCode)throws RemoteException
	{
		String result = null;
    	try{
    		if(progID != null && progID != null)
    		{
    			result = objDAO.deleteProgram(progID,progCode);
    		}    	
    	}
    	catch(Exception exp){
            
            exp.printStackTrace();
        }
    	
    	return result;
	} 
    
    
//======================= Parthi Here : Bean for press master ====================
    
    public ArrayList getPressList()throws RemoteException
	{
		ArrayList pressList = null;
		try{
			pressList = objDAO.getPressList();
		}
		catch(Exception exp){
            
            exp.printStackTrace();
        }
		return pressList;
	}
	
    
    
   public String addPress(String pressName, String pressAddress,String pressCity, String pressZip)throws RemoteException
    {
    	String result = null;
    	try{
    		if(pressName != null)
    		{
    			result = objDAO.addPress(pressName, pressAddress, pressCity, pressZip);
    		}    	
    	}
    	catch(Exception exp){
            
            exp.printStackTrace();
        }
    	
    	return result;
    }
    
    
	
	public String updatePress(String pressID, String pressName, String pressAddress, String pressCity, String pressZip)throws RemoteException
	{
		String result = null;
    	try{
    		if(pressID != null && pressName != null)
    		{
    			result = objDAO.updatePress(pressID,pressName,pressAddress,pressCity,pressZip);
    		}    	
    	}
    	catch(Exception exp){
            
            exp.printStackTrace();
        }
    	
    	return result;
	}
	
	public String deletePress(String pressID, String pressName)throws RemoteException
	{
		String result = null;
    	try{
    		if(pressID != null && pressID != null)
    		{
    			result = objDAO.deletePress(pressID,pressName);
    		}    	
    	}
    	catch(Exception exp){
            
            exp.printStackTrace();
        }
    	
    	return result;
	}
	
	
//======================= Parthi Here : Bean for centre master ====================
    
	public ArrayList getCentreList()throws RemoteException
	{
		ArrayList centreList = null;
		try{
			centreList = objDAO.getCentreList();
		}
		catch(Exception exp){
            
            exp.printStackTrace();
        }
		return centreList;
	}
	
    
    
	public String addCentre(String centreCode, String centreName, String centreAddress,String centreCity, String centreZip)throws RemoteException
    {
    	String result = null;
    	try{
    		if(centreCode != null)
    		{
    			result = objDAO.addCentre(centreCode, centreName, centreAddress, centreCity, centreZip );
    		}    	
    	}
    	catch(Exception exp){
            
            exp.printStackTrace();
        }
    	
    	return result;
    }
    
    
	
	public String updateCentre(String centreID, String centreCode, String centreName, String centreAddress,String centreCity, String centreZip)throws RemoteException
	{
		String result = null;
    	try{
    		if(centreID != null && centreID != null)
    		{
    			result = objDAO.updateCentre(centreID, centreCode, centreName, centreAddress, centreCity, centreZip);
    		}    	
    	}
    	catch(Exception exp){
            
            exp.printStackTrace();
        }
    	
    	return result;
	}
	
	public String deleteCentre(String centreID, String centreCode)throws RemoteException
	{
		String result = null;
    	try{
    		if(centreID != null && centreID != null)
    		{
    			result = objDAO.deleteCentre(centreID,centreCode);
    		}    	
    	}
    	catch(Exception exp){
            
            exp.printStackTrace();
        }
    	
    	return result;
	}
    
	public String mappingEntry(String progCode, String courseCode, String course_year, String medium, String programBarcode,String courseBarcode)throws RemoteException
	{
		String result = null;
    	try{
    		if(progCode != null)
    		{
    			result = objDAO.mappingEntry(progCode, courseCode,course_year,medium,programBarcode, courseBarcode);
    		}    	
    	}
    	catch(Exception exp){
            
            exp.printStackTrace();
        }
    	
    	return result;	
	}
    //=============== for print order to press ====================
	public String addPrintOrder(String po_no, String orderDate, String pressName,String pressAddress,String courseCode,String courseName,String printNature,String pages,String medium ,String copies)throws RemoteException
	{
		String result = null;
    	try{
    		if(pressName != null)
    		{
    			result = objDAO.addPrintOrder(po_no, orderDate, pressName, pressAddress, courseCode, courseName, printNature, pages,medium, copies);
    		}    	
    	}
    	catch(Exception exp){
            
            exp.printStackTrace();
        }
    	
    	return result;	
	}
    //============ get the PO_NO ================
	 public String getPO_NO() throws RemoteException
	    {
	         String PO_NO = null;
	         try{
	        	 PO_NO=objDAO.getPO_NO();  
	         }
	         catch(Exception exp){
	            
	             exp.printStackTrace();
	         }
	         return PO_NO;    
	    }
	//============== get the OrederList =====================
	 public ArrayList getOrderList(String po_no,String orderDate,String pressName)throws RemoteException
	 {
		 ArrayList orderList =null;
         try{
        	 if(po_no != null)
        		 orderList=objDAO.getOrderList(po_no, orderDate, pressName);  
         }
         catch(Exception exp){
            
             exp.printStackTrace();
         }
         return orderList;  
	 }
	
	// ========================= Ineard Entry ===================
	 public String addInward(String po_no,String orderDate,String pressName,String dc_no,String deliveryDate,String courseCode,
				String courseName,String medium,String copies, String courseBarcode)throws RemoteException
	{
		 String result = null;
         try{
        	 if(po_no != null)
        	 result=objDAO.addInward(po_no, orderDate, pressName, dc_no,deliveryDate, courseCode, courseName,medium, copies, courseBarcode);  
         }
         catch(Exception exp){
            
             exp.printStackTrace();
         }
         return result;  
	}
	 
	 
	 // ================ Material request to Warehouse ===================================
	 public String addMatRequest(String centreCode,String centreName,String programCode,String programName,String medium,
				String year_of_study,String quantity,String requestedDate)throws RemoteException
	{
		 String result = null;
         try{
        	 if(centreCode != null)
        	 result=objDAO.addMatRequest(centreCode, centreName, programCode, programName, medium, year_of_study, quantity, requestedDate); 
         }
         catch(Exception exp){
            
             exp.printStackTrace();
         }
         return result;  
	}
    
	 public ArrayList getRequestList(String centreCode,String requestedDate)throws RemoteException
	 {

		 ArrayList requestList = null;
         try{
        	 if(centreCode != null)
        		 requestList=objDAO.getRequestList(centreCode,requestedDate);
         }
         catch(Exception exp){
            
             exp.printStackTrace();
         }
         return requestList; 
	 }
	 
	//====================== Material Issue Part =====================
	 public ArrayList searchRequestList(String requestedDate)throws RemoteException
	 {
		 ArrayList requestList = null;
         try{
        	 if(requestedDate != null)
        		 requestList=objDAO.searchRequestList(requestedDate);
         }
         catch(Exception exp){
            
             exp.printStackTrace();
         }
         return requestList;  
	 }
	 
	 public String matIssue(String centreCode,String centreName,String progCode,String progName,String mediumType,
				String yeas_Of_Study,String available_Quantity,String request_Quantity,String Issue_Quantity,
				String Issue_Date,String requestedDate)throws RemoteException
	{
		 String result = null;
         try{
        	 if(progCode != null)
        		 result=objDAO.matIssue(centreCode, centreName, progCode, progName, mediumType, yeas_Of_Study, available_Quantity,
        				 request_Quantity, Issue_Quantity, Issue_Date, requestedDate);
         }
         catch(Exception exp){
            
             exp.printStackTrace();
         }
         return result;
	}
	 
//==================== Pending Material Issue Part ===================
	 public ArrayList searchPendRequestList(String requestedType)throws RemoteException
	 {
		 ArrayList requestList = null;
         try{
        	 if(requestedType != null)
        		 requestList=objDAO.searchPendRequestList(requestedType);
         }
         catch(Exception exp){
            
             exp.printStackTrace();
         }
         return requestList; 
	 }
	 
// ======================= Material Issue by course Wise =====================
	 public String getCourseAvailQuantity(String courseCode,String medium,String YOS)throws RemoteException
	 {
		String availQuantity = null;
         try{
        	 if(courseCode != null)
        		 availQuantity=objDAO.getCourseAvailQuantity(courseCode,medium,YOS);
         }
         catch(Exception exp){
            
             exp.printStackTrace();
         }
         return availQuantity; 
	 }
	 
	 public String matCourseIssue(String courseBarcode, String courseCode, String courseName, String medium, String centreCode,
			  String centreName, String YOS, String availQuantity, String reqQuantity, String issQuantity, String reqDate, String issDate)
	 {
		 String result = null;
		 try{
        	 if(courseCode != null)
        		 result=objDAO.matCourseIssue(courseBarcode, courseCode, courseName, medium, centreCode,
						  centreName, YOS, availQuantity, reqQuantity, issQuantity, reqDate,  issDate);
         }
         catch(Exception exp){
            
             exp.printStackTrace();
         }
		 
		 return result;
	 }
// ====================== Inward report Part ======================
	 public ArrayList getInwardReport(String requestedType,String fromDate,String toDate)throws RemoteException
	 {
		 ArrayList inwardtList = null;
         try{
        	 if(requestedType != null)
        		 inwardtList=objDAO.getInwardReport(requestedType, fromDate, toDate);
         }
         catch(Exception exp){
            
             exp.printStackTrace();
         }
         return inwardtList; 
	 }
	 
	// ====================== Press report Part ======================
		 public ArrayList getPressReport(String requestedType,String fromDate,String toDate)throws RemoteException
		 {
			 ArrayList presstList = null;
	         try{
	        	 if(requestedType != null)
	        		 presstList=objDAO.getPressReport(requestedType, fromDate, toDate);
	         }
	         catch(Exception exp){
	            
	             exp.printStackTrace();
	         }
	         return presstList; 
		 }
		 
		// ====================== Request report Part ======================
				 public ArrayList getRequestReport(String requestedType,String fromDate,String toDate)throws RemoteException
				 {
					 ArrayList reqtList = null;
			         try{
			        	 if(requestedType != null)
			        		 reqtList=objDAO.getRequestReport(requestedType, fromDate, toDate);
			         }
			         catch(Exception exp){
			            
			             exp.printStackTrace();
			         }
			         return reqtList; 
				 }
				 
				// ====================== Issue report Part ======================
				 public ArrayList getIssueReport(String requestedType,String fromDate,String toDate)throws RemoteException
				 {
					 ArrayList issList = null;
			         try{
			        	 if(requestedType != null)
			        		 issList=objDAO.getIssueReport(requestedType, fromDate, toDate);
			         }
			         catch(Exception exp){
			            
			             exp.printStackTrace();
			         }
			         return issList; 
				 }
	 
				 
				 
				 public ArrayList getProgramcodelist()throws RemoteException
				 {
					 ArrayList stockStatus_list = new ArrayList();
					 try{
			        	 
			        		 stockStatus_list=objDAO.Programcodelist();
			         }
			         catch(Exception exp){
			            
			             exp.printStackTrace();
			         }
					 
					 
					 return stockStatus_list;
				 }
				 
	// ========================= get Stock Status =================================
				 
				 public ArrayList getStockStatus(String requestedType, String searchCode)throws RemoteException
				 {
					 ArrayList stockStatus_list = new ArrayList();
					 try{
			        	 if(requestedType != null)
			        		 stockStatus_list=objDAO.getStockStatus(requestedType,searchCode);
			         }
			         catch(Exception exp){
			            
			             exp.printStackTrace();
			         }
					 
					 
					 return stockStatus_list;
				 }
				 
				
    private void makeConnection() {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(dbName);
            con = ds.getConnection();
            Debug.print("getConnection() .........");
            
        }
        catch (SQLException ex) {
        	Debug.print("SQLException() .........");
        	//makeConnection();
          throw new EJBException("SQLException Unable to connect to database. " + ex.getMessage());
        }
       
        catch (Exception ex1) {
        	Debug.print("Exception() .........");
        	//makeConnection();
            throw new EJBException("Exception Unable to connect to database. " + ex1.getMessage());
        }
    }
    
    /**
     * Name         :releaseConnection
     * Description  :This method will release the databacse connection
     * @ param      :
     * @return      :void
     * @throws      :EJBException
     */
    private void releaseConnection() {
        try {
            //prepStmt.close();
            //rs.close();
            if(!con.isClosed()){
                Debug.print("connection released !!");
                con.close();
            }
        } catch (SQLException ex) {
            throw new EJBException("releaseConnection: " + ex.getMessage());
        }
    }
	
	
	
       
}
