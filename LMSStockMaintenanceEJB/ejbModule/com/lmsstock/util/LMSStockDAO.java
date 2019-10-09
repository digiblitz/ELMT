/*
 * MemberUpdateDAO.java
 *
 * Created on September 16, 2006, 12:06 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.lmsstock.util;

import java.rmi.RemoteException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.sql.*;
import java.util.*;
import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.naming.*;

import com.lmsstock.util.Debug;



/**
 *
 * @CopyRights @digiBlitz
 */
public class LMSStockDAO {
    
 //=================Member Details =============================
    
    private static final String dbName = "java:/LMSMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private PreparedStatement prepStmt1 = null;
    private ResultSet rs = null;
    private  String selStmt = null;
    
     
    /** Creates a new instance of MemberUpdateDAO */
    public LMSStockDAO() {
    }
    
  
 
    
  //===============Parthi Here: frmAddNewCourse =========================
    
    public ArrayList getCourseList()
    {
    	 Debug.print("LMSStockDAO getCourseList()");
		    
		    ArrayList courseList= new ArrayList();
		    ResultSet rs=null;
		    String course_id ="";
		    String course_code = "";
		    String course_name ="";
		    String year_of_course = "";
		    String pages ="";
		    String course_barcode = "";
		    
		  
		        try {
		        	makeConnection();
					 String selectStatement = "select course_id, course_code, course_name, year_of_course, Pages ,course_barcode from tblCourseMaster ORDER BY course_code";
					 prepStmt = con.prepareStatement(selectStatement);	     
				    rs = prepStmt.executeQuery();	        	
				    while(rs.next()){			   
				    	course_id=rs.getString(1);
				    	course_code=rs.getString(2);
				    	course_name=rs.getString(3);
				    	year_of_course = rs.getString(4);
				    	pages = rs.getString(5);
				    	course_barcode = rs.getString(6);
					   String[] temps={course_id,course_code,course_name,year_of_course,pages,course_barcode};
					   courseList.add(temps);
				    }
		   			  
			       rs.close();
			       prepStmt.close();
			       releaseConnection(); 
			    } 
		        catch (Exception ex) {
			        	ex.printStackTrace();
			             //Debug.print("Exception:" + ex.getMessage());
			        }
		       
		    return courseList;
    }
    
    public String addCourse(String courseCode, String courseName,String yearOfCourse, String Pages, String courseBarcode)
    {
    	String result = "Failure";
    	try { 
            makeConnection();         
               
            String insertStmt=  "insert into tblCourseMaster(course_code, course_name, year_of_course, Pages,course_barcode) values (?, ?, ?,?,?)";
            Debug.print("Quary : "+insertStmt);
            prepStmt = con.prepareStatement(insertStmt);
            prepStmt.setString(1,courseCode);
            prepStmt.setString(2,courseName);
            prepStmt.setString(3,yearOfCourse);
            prepStmt.setString(4,Pages);
            prepStmt.setString(5,courseBarcode);
           
           
            int cnt = prepStmt.executeUpdate();
  	      
            if(cnt>0){         	  
            	result = "success";                
            }
          
            prepStmt.close();
          
        } 
        catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling addCourse() : "+sqe.getMessage());         
        } finally {
            releaseConnection();
        }
    	
    	return result;
    }
    
    public String updateCourse(String courseID, String courseCode, String courseName, String yearOfCourse, String pages)
    {
   	 String result = "Failure";
    	try { 
            makeConnection();         
               
            String updateStatement =  "update tblCourseMaster set course_code=? , course_name=?, year_of_course=?, Pages=? where course_id = ?";
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1,courseCode);               
            prepStmt.setString(2,courseName); 
            prepStmt.setString(3,yearOfCourse);               
            prepStmt.setString(4,pages); 
            prepStmt.setString(5,courseID);
            
            Debug.print("Quary : "+updateStatement);
          
           
            int cnt = prepStmt.executeUpdate();
   	      
            if(cnt>0){         	  
            	result = "success";                
            }
          
            prepStmt.close();
          
        } 
        catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling updateCourse() : "+sqe.getMessage());         
        } finally {
            releaseConnection();
        }
    	
    	return result;
    }
    
    public String deleteCourse(String courseID, String courseCode)
    {
   	 String result = "Failure";
    	try { 
            makeConnection();         
               
            String updateStatement =  "delete from tblCourseMaster  where course_code = ? and course_id = ?";
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1,courseCode);
            prepStmt.setString(2,courseID);
            
            Debug.print("Quary : "+updateStatement);
          
           
            int cnt = prepStmt.executeUpdate();
   	      
            if(cnt>0){         	  
            	result = "success";                
            }
          
            prepStmt.close();
          
        } 
        catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling deleteCourse() : "+sqe.getMessage());         
        } finally {
            releaseConnection();
        }
    	
    	return result;
    }
    
    
//===============Parthi Here: frmAddNewProgram =========================
    
    public ArrayList getProgramList()
    {
    	 Debug.print("LMSStockDAO getProgramList()");
		    
		    ArrayList progList= new ArrayList();
		    ResultSet rs=null;
		    String prog_id ="";
		    String prog_code = "";
		    String prog_name ="";
		    String prog_barcode ="";
		    
		  
		        try {
		        	makeConnection();
					 String selectStatement = "select program_id, program_code, program_name, program_barcode from tblProgramMaster ORDER BY program_code";
					 prepStmt = con.prepareStatement(selectStatement);	     
				    rs = prepStmt.executeQuery();	        	
				    while(rs.next()){			   
				    	prog_id=rs.getString(1);	
				    	prog_code=rs.getString(2);
				    	prog_name=rs.getString(3);
				    	prog_barcode=rs.getString(4);
				    	
					   String[] temps={prog_id,prog_code,prog_name,prog_barcode};
					   progList.add(temps);
				    }
		   			  
			       rs.close();
			       prepStmt.close();
			       releaseConnection(); 
			    } 
		        catch (Exception ex) {
			        	ex.printStackTrace();
			             //Debug.print("Exception:" + ex.getMessage());
			        }
		       
		    return progList;
    }
    
    public String addProgram(String progCode, String progName, String progBarcode)
    {
    	String result = "Failure";
    	try { 
            makeConnection();         
               
            String insertStmt=  "insert into tblProgramMaster(program_code, program_name, program_barcode) values (?, ?, ?)";
            Debug.print("Quary : "+insertStmt);
            prepStmt = con.prepareStatement(insertStmt);
            prepStmt.setString(1,progCode);
            prepStmt.setString(2,progName);
            prepStmt.setString(3,progBarcode);        
           
            int cnt = prepStmt.executeUpdate();
  	      
            if(cnt>0){         	  
            	result = "success";                
            }
          
            prepStmt.close();
          
        } 
        catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling addProgram() : "+sqe.getMessage());         
        } finally {
            releaseConnection();
        }
    	
    	return result;
    }
    
    public String updateProgram(String progID, String progCode, String progName)
    {
   	 String result = "Failure";
    	try { 
            makeConnection();         
               
            String updateStatement =  "update tblProgramMaster set  program_code=?, program_name=? where program_id = ?";
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1,progCode);               
            prepStmt.setString(2,progName); 
            prepStmt.setString(3,progID);
            
            Debug.print("Quary : "+updateStatement);
          
           
            int cnt = prepStmt.executeUpdate();
   	      
            if(cnt>0){         	  
            	result = "success";                
            }
          
            prepStmt.close();
          
        } 
        catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling updateProgram() : "+sqe.getMessage());         
        } finally {
            releaseConnection();
        }
    	
    	return result;
    }
    
    public String deleteProgram(String progID, String progCode)
    {
   	 String result = "Failure";
    	try { 
            makeConnection();         
               
            String updateStatement =  "delete from tblProgramMaster  where program_code = ? and program_id = ?";
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1,progCode);
            prepStmt.setString(2,progID);
            
            Debug.print("Quary : "+updateStatement);
          
           
            int cnt = prepStmt.executeUpdate();
   	      
            if(cnt>0){         	  
            	result = "success";                
            }
          
            prepStmt.close();
          
        } 
        catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling deleteProgram() : "+sqe.getMessage());         
        } finally {
            releaseConnection();
        }
    	
    	return result;
    }
    
    
    
    
//===============Parthi Here: frmAddNewCourse =========================
    
    public ArrayList getPressList()
    {
    	Debug.print("LMSStockDAO getPressList()");
		    
		    ArrayList pressList= new ArrayList();
		    ResultSet rs=null;
		    String pressID ="";
		    String pressName = "";
		    String pressAddress ="";
		    String pressCity = "";
		    String pressZip ="";
		    
		  
		        try {
		        	makeConnection();
					 String selectStatement = "select press_id, press_name, Address, city, zip_code from tblPressMaster ORDER BY press_id";
					 prepStmt = con.prepareStatement(selectStatement);	     
				    rs = prepStmt.executeQuery();	        	
				    while(rs.next()){			   
				    	pressID=rs.getString(1);	
				    	pressName=rs.getString(2);
				    	pressAddress=rs.getString(3);
				    	pressCity = rs.getString(4);
				    	pressZip = rs.getString(5);
				    	
					   String[] temps={pressID,pressName,pressAddress,pressCity,pressZip};
					   pressList.add(temps);
				    }
		   			  
			       rs.close();
			       prepStmt.close();
			       releaseConnection(); 
			    } 
		        catch (Exception ex) {
			        	ex.printStackTrace();
			             //Debug.print("Exception:" + ex.getMessage());
			        }
		       
		    return pressList;		
    }
    
    public String addPress(String pressName, String pressAddress,String pressCity, String pressZip)
    {
    	String result = "Failure";
    	try { 
            makeConnection();         
               
            String insertStmt=  "insert into tblPressMaster(press_name, Address, city, zip_code) values (?, ?, ?,?)";
            Debug.print("Quary : "+insertStmt);
            prepStmt = con.prepareStatement(insertStmt);
            prepStmt.setString(1,pressName);
            prepStmt.setString(2,pressAddress);
            prepStmt.setString(3,pressCity);
            prepStmt.setString(4,pressZip);
           
           
            int cnt = prepStmt.executeUpdate();
  	      
            if(cnt>0){         	  
            	result = "success";                
            }
          
            prepStmt.close();
          
        } 
        catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling addPress() : "+sqe.getMessage());         
        } finally {
            releaseConnection();
        }
    	
    	return result;
    }
    
    public String updatePress(String pressID, String pressName, String pressAddress, String pressCity, String pressZip)
    {
   	 String result = "Failure";
    	try { 
            makeConnection();         
               
            String updateStatement =  "update tblPressMaster set press_name=? , Address=?, city=?, zip_code	=? where press_id = ?";
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1,pressName);               
            prepStmt.setString(2,pressAddress); 
            prepStmt.setString(3,pressCity);               
            prepStmt.setString(4,pressZip); 
            prepStmt.setString(5,pressID);
            
            Debug.print("Quary : "+updateStatement);
          
           
            int cnt = prepStmt.executeUpdate();
   	      
            if(cnt>0){         	  
            	result = "success";                
            }
          
            prepStmt.close();
          
        } 
        catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling updatePress() : "+sqe.getMessage());         
        } finally {
            releaseConnection();
        }
    	
    	return result;
    }
    
    public String deletePress(String pressID, String pressName)
    {
   	 String result = "Failure";
    	try { 
            makeConnection();         
               
            String updateStatement =  "delete from tblPressMaster  where press_name = ? and press_id = ?";
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1,pressName);
            prepStmt.setString(2,pressID);
            
            Debug.print("Quary : "+updateStatement);
          
           
            int cnt = prepStmt.executeUpdate();
   	      
            if(cnt>0){         	  
            	result = "success";                
            }
          
            prepStmt.close();
          
        } 
        catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling deletePress() : "+sqe.getMessage());         
        } finally {
            releaseConnection();
        }
    	
    	return result;
    }
    
    
//===============Parthi Here: frmAddNewCentre =========================
    
    public ArrayList getCentreList()
    {
    	Debug.print("LMSStockDAO getCentreList()");
		    
		    ArrayList pressList= new ArrayList();
		    ResultSet rs=null;
		    String centreID ="";
		    String centreCode ="";
		    String centreName = "";
		    String centreAddress ="";
		    String centreCity = "";
		    String centreZip ="";
		    
		  
		        try {
		        	makeConnection();
					 String selectStatement = "select centre_id, centre_code, centre_name, centre_address, centre_city, centre_zip from tblCentreMaster";
					 prepStmt = con.prepareStatement(selectStatement);	     
				    rs = prepStmt.executeQuery();	        	
				    while(rs.next()){			   
				    	centreID=rs.getString(1);	
				    	centreCode=rs.getString(2);
				    	centreName=rs.getString(3);
				    	centreAddress = rs.getString(4);
				    	centreCity = rs.getString(5);
				    	centreZip = rs.getString(6);
				    	
					   String[] temps={centreID,centreCode,centreName,centreAddress,centreCity,centreZip};
					   pressList.add(temps);
				    }
		   			  
			       rs.close();
			       prepStmt.close();
			       releaseConnection(); 
			    } 
		        catch (Exception ex) {
			        	ex.printStackTrace();
			             //Debug.print("Exception:" + ex.getMessage());
			        }
		       
		    return pressList;		
    }
    
    public String addCentre(String centreCode, String centreName, String centreAddress,String centreCity, String centreZip)
    {
    	String result = "Failure";
    	try { 
            makeConnection();         
               
            String insertStmt=  "insert into tblCentreMaster(centre_code, centre_name, centre_address, centre_city, centre_zip) values (?, ?, ?, ?, ?)";
            Debug.print("Quary : "+insertStmt);
            prepStmt = con.prepareStatement(insertStmt);
            prepStmt.setString(1,centreCode);
            prepStmt.setString(2,centreName);
            prepStmt.setString(3,centreAddress);
            prepStmt.setString(4,centreCity);
            prepStmt.setString(5,centreZip);
           
           
            int cnt = prepStmt.executeUpdate();
  	      
            if(cnt>0){         	  
            	result = "success";                
            }
          
            prepStmt.close();
          
        } 
        catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling addCentre() : "+sqe.getMessage());         
        } finally {
            releaseConnection();
        }
    	
    	return result;
    }
    
    public String updateCentre(String centreID, String centreCode, String centreName, String centreAddress,String centreCity, String centreZip)
    {
   	 String result = "Failure";
    	try { 
            makeConnection();         
          
            String updateStatement =  "update tblCentreMaster set centre_code=? , centre_name=?, centre_address=?, centre_city=?, centre_zip=? where centre_id = ?";
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1,centreCode);               
            prepStmt.setString(2,centreName); 
            prepStmt.setString(3,centreAddress);               
            prepStmt.setString(4,centreCity); 
            prepStmt.setString(5,centreZip);
            prepStmt.setString(6,centreID);
            
            Debug.print("Quary : "+updateStatement);
          
           
            int cnt = prepStmt.executeUpdate();
   	      
            if(cnt>0){         	  
            	result = "success";                
            }
          
            prepStmt.close();
          
        } 
        catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling updateCentre() : "+sqe.getMessage());         
        } finally {
            releaseConnection();
        }
    	
    	return result;
    }
    
    public String deleteCentre(String centreID, String centreCode)
    {
   	 String result = "Failure";
    	try { 
            makeConnection();         
               
            String updateStatement =  "delete from tblCentreMaster  where centre_code = ? and centre_id = ?";
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1,centreCode);
            prepStmt.setString(2,centreID);
            
            Debug.print("Quary : "+updateStatement);
          
           
            int cnt = prepStmt.executeUpdate();
   	      
            if(cnt>0){         	  
            	result = "success";                
            }
          
            prepStmt.close();
          
        } 
        catch (SQLException sqe) {
            releaseConnection();
            Debug.print("Error while calling deleteCentre() : "+sqe.getMessage());         
        } finally {
            releaseConnection();
        }
    	
    	return result;
    }
    
    
    
   public String mappingEntry(String courseCode, String progCode, String course_year, String medium, String programBarcode,String courseBarcode)
   {
	   String result = "Failure";
   	try { 
           makeConnection();         
              
           String insertStmt=  "insert into tblWarehouseMaster(program_code, course_code,year_Of_Course,medium,program_barcode, course_barcode) values (?, ?, ?, ?, ?, ?)";
           Debug.print("Quary : "+insertStmt);
           prepStmt = con.prepareStatement(insertStmt);
           prepStmt.setString(1,progCode);
           prepStmt.setString(2,courseCode);
           prepStmt.setString(3,course_year);
           prepStmt.setString(4,medium);
           prepStmt.setString(5,programBarcode);
           prepStmt.setString(6,courseBarcode);
          
          
          
           int cnt = prepStmt.executeUpdate();
 	      
           if(cnt>0){         	  
           	result = "success";                
           }
         
           prepStmt.close();
         
       } 
       catch (SQLException sqe) {
           releaseConnection();
           Debug.print("Error while calling mappingEntry() : "+sqe.getMessage());         
       } finally {
           releaseConnection();
       }
   	
   	return result;
   }
    
  //=================== For print order to press ===================  
   public String addPrintOrder(String po_no, String orderDate,String pressName,String pressAddress,String courseCode,String courseName,String printNature,String pages,String medium, String copies) 
   {
	   String result = "Failure";
	   	try { 
	           makeConnection();         
	              
	           String insertStmt=  "insert into tblPrintingOrder(PO_No, ordered_date, printer_name, printer_address,course_code, " +
	           		"course_name, medium, nature_of_print, pages, no_of_copies, pendingTo_receive_copies) values 	(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        		   
	           Debug.print("Quary : "+insertStmt);
	           prepStmt = con.prepareStatement(insertStmt);
	           prepStmt.setString(1,po_no);
	           prepStmt.setString(2,orderDate);
	           prepStmt.setString(3,pressName);
	           prepStmt.setString(4,pressAddress);
	           prepStmt.setString(5,courseCode);
	           prepStmt.setString(6,courseName);
	           prepStmt.setString(7,medium);
	           prepStmt.setString(8,printNature);
	           prepStmt.setString(9,pages);
	           prepStmt.setString(10,copies);
	           prepStmt.setString(11,copies);
	          
	          
	           int cnt = prepStmt.executeUpdate();
	 	      
	           if(cnt>0){         	  
	           	result = "success";                
	           }
	         
	           prepStmt.close();
	         
	       } 
	       catch (SQLException sqe) {
	           releaseConnection();
	           Debug.print("Error while calling addPrintOrder() : "+sqe.getMessage());         
	       } finally {
	           releaseConnection();
	       }
	   	
	   	return result;
   }
    
   //================ get the PO_NO =============================
   
   public String getPO_NO()
   {         
     String PO_NO = null;
     PreparedStatement prepSelect = null;
     makeConnection();
     try {
         String selectStatement = "SELECT max(cast(PO_No as int)) from tblPrintingOrder";
         
         prepSelect = con.prepareStatement(selectStatement);
         
         ResultSet rs = prepSelect.executeQuery();
         while (rs.next()) {
        	 PO_NO = rs.getString(1);
         }
         if (PO_NO == null)
        	 PO_NO = "0";
         
         long nextId = Long.valueOf(PO_NO).longValue();
         
         if(nextId==0){
             nextId = 1;
         } else{
             nextId = nextId+1;
         }
         rs.close();
         prepSelect.close();
         
         PO_NO = Long.toString(nextId);
        
         releaseConnection();
         Debug.print("PO_NO is : " + PO_NO);
         
     } catch(SQLException sql){
         releaseConnection();
         Debug.print("SQL Exception in getPO_NO :" + sql.getMessage());
     } catch(Exception e){
         releaseConnection();
         Debug.print("General Exception  in getPO_NO :" + e.getMessage());
     }
     
     return PO_NO;
   }
   
   // ============= get the orderList ==================
   public ArrayList getOrderList(String po_no,String orderDate,String pressName)
   {
	   Debug.print("LMSStockDAO getOrderList()");
	    
	    ArrayList orderList= new ArrayList();
	    ResultSet rs=null;
	   
	    
	  
	        try {
	        	makeConnection();
				 String selectStatement = "select PO_No, ordered_date, printer_name, printer_address, course_code, course_name, medium, " +
				 		"nature_of_print, pages, no_of_copies from tblPrintingOrder where PO_No=? and ordered_date=? and printer_name=?";
				 prepStmt = con.prepareStatement(selectStatement);	 
				 prepStmt.setString(1,po_no);
				 prepStmt.setString(2,orderDate);
				 prepStmt.setString(3,pressName);
				 
			    rs = prepStmt.executeQuery();	
			    
			    
			    
			    
			    while(rs.next()){			   
			    	String PO_No=rs.getString(1);	
			    	String ordered_date = rs.getString(2);
			    	String pressName_1=rs.getString(3);
			    	String pressAddress=rs.getString(4);
			    	String course_code = rs.getString(5);
			    	String course_name = rs.getString(6);
			    	String medium = rs.getString(7);
			    	String nature_of_print = rs.getString(8);
			    	String pages = rs.getString(9);
			    	String no_of_copies = rs.getString(10);
			    	
				   String[] temps={PO_No,ordered_date,pressName_1,pressAddress,course_code,course_name,medium,nature_of_print,pages,no_of_copies};
				   orderList.add(temps);
			    }
	   			  
		       rs.close();
		       prepStmt.close();
		       releaseConnection(); 
		    } 
	        catch (Exception ex) {
		        	ex.printStackTrace();
		             //Debug.print("Exception:" + ex.getMessage());
		        }
	       
	    return orderList;	
   }
   
  // ======================== For Inward Entry ===================
   public String addInward(String po_no,String orderDate,String pressName,String dc_no,String deliveryDate,String courseCode,
			String courseName,String medium,String copies, String courseBarcode)
   {
	   String result = "Failure";
	   String feedBack  = null;
	   	try { 
	           makeConnection();         
	              
	           String insertStmt=  "insert into tblInwardMaster(PO_No, ordered_date, press_name, DC_No,delivery_date, " +
	           		"course_code, course_name, medium, received_copies, course_barcode) values 	(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        		   
	           Debug.print("Quary : "+insertStmt);
	           prepStmt = con.prepareStatement(insertStmt);
	           prepStmt.setString(1,po_no);
	           prepStmt.setString(2,orderDate);
	           prepStmt.setString(3,pressName);
	           prepStmt.setString(4,dc_no);
	           prepStmt.setString(5,deliveryDate);
	           prepStmt.setString(6,courseCode);
	           prepStmt.setString(7,courseName);
	           prepStmt.setString(8,medium);
	           prepStmt.setString(9,copies);
	           prepStmt.setString(10,courseBarcode);
	          
	          
	           int cnt = prepStmt.executeUpdate();
	 	      
	           if(cnt>0){         	  
	           	result = "success";                
	           }
	         
	           prepStmt.close();
	           
	           feedBack = receivePO(po_no, orderDate, pressName, courseCode, medium, copies);
	           feedBack = updateWarehouseStock(courseCode, medium, copies);
	           
	         
	       } 
	       catch (SQLException sqe) {
	           releaseConnection();
	           Debug.print("Error while calling addInward() : "+sqe.getMessage());         
	       } finally {
	           releaseConnection();
	       }
	   	
	   	return result;
   }
    
   
   public String receivePO(String po_no, String orderDate, String pressName, String courseCode, String medium, String copies)
	{
		String result = null;
		int pending_copies= 0;
		int Updated_Copies = 0;
		String P_ID = null;
		
		// --------------------- Select no of copies from printing order table ------------------------
		try {
        	//makeConnection();
			 String selectStatement = "select printing_id, pendingTo_receive_copies from tblPrintingOrder where PO_No=? and ordered_date=? and printer_name=? and course_code=? and medium=?";
			 prepStmt = con.prepareStatement(selectStatement);	 
			 prepStmt.setString(1,po_no);
			 prepStmt.setString(2,orderDate);
			 prepStmt.setString(3,pressName);
			 prepStmt.setString(4,courseCode);
			 prepStmt.setString(5,medium);
			 
		    rs = prepStmt.executeQuery();
		    
		    while(rs.next()){			   
		    	P_ID=rs.getString(1);	
		    	pending_copies = Integer.parseInt(rs.getString(2));
		    }
   			  
	       rs.close();
	       prepStmt.close();
	       //releaseConnection(); 
	    } 
        catch (Exception ex) {
	        	ex.printStackTrace();
	             Debug.print("Exception:" + ex.getMessage());
	     }
		
		//---------------- Update printing order table ---------------------
		
		Updated_Copies = pending_copies - Integer.parseInt(copies);
		
		try { 
            //makeConnection();         
               
            String updateStatement =  "update tblPrintingOrder set pendingTo_receive_copies=? where printing_id=?";
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setLong(1,Updated_Copies);               
            prepStmt.setString(2,P_ID);             
            
            Debug.print("Quary : "+updateStatement);          
           
            int cnt = prepStmt.executeUpdate();
   	      
            if(cnt>0){         	  
            	result = "success";                
            }
          
            prepStmt.close();
          
        } 
        catch (SQLException sqe) {
           // releaseConnection();
            Debug.print("Error while calling receivePO() : "+sqe.getMessage());         
        } finally {
           // releaseConnection();
        }
		
		return result;
	}
   
   public String updateWarehouseStock(String courseCode,String medium, String copies)
   {
	   String result = null;
		int total_stock = 0;
		int Updated_Copies = 0;
		String stock_id = null;
		
		// --------------------- Select no of copies from warehouse master table ------------------------
		try {
       	//makeConnection();
			 String selectStatement = "select stock_id, total_stock from tblWarehouseMaster where course_code=? and medium=?";
			 prepStmt = con.prepareStatement(selectStatement);	 
			 prepStmt.setString(1,courseCode);
			 prepStmt.setString(2,medium);
			
			 
		    rs = prepStmt.executeQuery();
		    
		    while(rs.next()){			   
		    	stock_id=rs.getString(1);	
		    	total_stock = Integer.parseInt(rs.getString(2));
		    }
  			  
	       rs.close();
	       prepStmt.close();
	      // releaseConnection(); 
	    } 
       catch (Exception ex) {
	        	ex.printStackTrace();
	             Debug.print("Exception:" + ex.getMessage());
	     }
		
		//---------------- Update warehouse table ---------------------
		
		Updated_Copies = total_stock + Integer.parseInt(copies);
		
		try { 
           //makeConnection();         
              
           String updateStatement =  "update tblWarehouseMaster set total_stock=? where stock_id=?";
           prepStmt = con.prepareStatement(updateStatement);
           prepStmt.setLong(1,Updated_Copies);               
           prepStmt.setString(2,stock_id);             
           
           Debug.print("Quary : "+updateStatement);          
          
           int cnt = prepStmt.executeUpdate();
  	      
           if(cnt>0){         	  
           	result = "success";                
           }
         
           prepStmt.close();
         
       } 
       catch (SQLException sqe) {
          // releaseConnection();
           Debug.print("Error while calling updateWarehouseStock() : "+sqe.getMessage());         
       } finally {
           //releaseConnection();
       }
		
		return result;
   }
   
   
   //================ Material request to warehouse ==========================
   public String addMatRequest(String centreCode,String centreName,String programCode,String programName,String medium,
			String year_of_study,String quantity,String requestedDate)
   {
	   String result = "Failure";
	   String requestedID = null;
	   int last_Quantity=0;
	   int updated_Quantity =0;
	   
	// --------------------- Select no of quantity from request master table ------------------------
			try {
	         	makeConnection();
				 String selectStatement = "select request_id, quantity from tblRequestMaster where centre_code=? and program_code=? and requested_date=? and medium=?";
				 prepStmt = con.prepareStatement(selectStatement);	 
				 prepStmt.setString(1,centreCode);
				 prepStmt.setString(2,programCode);
				 prepStmt.setString(3,requestedDate);
				 prepStmt.setString(4,medium);
				 
			    rs = prepStmt.executeQuery();
			    
			    while(rs.next()){			   
			    	requestedID=rs.getString(1);	
			    	last_Quantity = Integer.parseInt(rs.getString(2));
			    }
	  			  
		       rs.close();
		       prepStmt.close();
		      
		    } 
	       catch (Exception ex) {
	    	      releaseConnection();
		        	ex.printStackTrace();
		             Debug.print("Exception:" + ex.getMessage());
		     }
			finally {
		           releaseConnection();
		       }
			
	   
	   
			// --------------------- update on request master table ------------------------
	   
	   if(requestedID != null && requestedID!="" && last_Quantity != 0)	   
	   {
		   updated_Quantity = last_Quantity + Integer.parseInt(quantity);
			
			try { 
	           makeConnection();         
	              
	           String updateStatement =  "update tblRequestMaster set quantity=? where request_id=?";
	           prepStmt = con.prepareStatement(updateStatement);
	           prepStmt.setLong(1,updated_Quantity);               
	           prepStmt.setString(2,requestedID);             
	           
	           Debug.print("Quary : "+updateStatement);          
	          
	           int cnt = prepStmt.executeUpdate();
	  	      
	           if(cnt>0){         	  
	           	result = "success";                
	           }
	         
	           prepStmt.close();
	         
	       } 
	       catch (SQLException sqe) {
	          releaseConnection();
	           Debug.print("Error while calling addMatRequest() on update : "+sqe.getMessage());         
	       } finally {
	           releaseConnection();
	       }
	   }
	// --------------------- insert  into request master table ------------------------
	   else
	   { 	
		   	try { 
		           makeConnection();         
		              
		           String insertStmt=  "insert into tblRequestMaster(centre_code, centre_name, program_code, program_name, year_of_study," +
		           		" medium, quantity, requested_date) values 	(?, ?, ?, ?, ?, ?, ?, ?)";
		        		   
		           Debug.print("Quary : "+insertStmt);
		           prepStmt = con.prepareStatement(insertStmt);
		           
		           prepStmt.setString(1,centreCode);
		           prepStmt.setString(2,centreName);
		           prepStmt.setString(3,programCode);
		           prepStmt.setString(4,programName);
		           prepStmt.setString(5,year_of_study);	          
		           prepStmt.setString(6,medium);
		           prepStmt.setString(7,quantity);
		           prepStmt.setString(8,requestedDate);
		          
		           int cnt = prepStmt.executeUpdate();
		 	      
		           if(cnt>0){         	  
		           	result = "success";                
		           }
		         
		           prepStmt.close();
		         
		       } 
		       catch (SQLException sqe) {
		           releaseConnection();
		           Debug.print("Error while calling addMatRequest() on insert: "+sqe.getMessage());         
		       } finally {
		           releaseConnection();
		       }
	   }
	   	
	   	return result; 
   }
   
   // ============= get material requested list =====================
   public ArrayList getRequestList(String centreCode,String requestedDate)
   {
	   Debug.print("LMSStockDAO getRequestList()");
	    
	    ArrayList requestList= new ArrayList();
	    ResultSet rs=null;
	  
	        try {
	        	makeConnection();
				 String selectStatement = "select request_id, centre_code, centre_name, program_code, program_name, year_of_study," +
	           		" medium, quantity, requested_date from tblRequestMaster where centre_code=? and requested_date=? ";
				 prepStmt = con.prepareStatement(selectStatement);	 
				 prepStmt.setString(1,centreCode);
				 prepStmt.setString(2,requestedDate);
				 
			    rs = prepStmt.executeQuery();	
			    
			    while(rs.next()){			   
			    	String requestID=rs.getString(1);	
			    	String centre_code = rs.getString(2);
			    	String centre_name=rs.getString(3);
			    	String program_code=rs.getString(4);
			    	String program_name = rs.getString(5);
			    	String year_of_study = rs.getString(6);
			    	String medium = rs.getString(7);
			    	String quantity = rs.getString(8);
			    	String requested_date = rs.getString(9);
			    	
			    	
				   String[] temps={requestID,centre_code,centre_name,program_code,program_name,year_of_study,medium,quantity,requested_date};
				   requestList.add(temps);
			    }
	   			  
		       rs.close();
		       prepStmt.close();
		       releaseConnection(); 
		    } 
	        catch (Exception ex) {
		        	ex.printStackTrace();
		             //Debug.print("Exception:" + ex.getMessage());
		        }
	       
	    return requestList;
   }
   
   
   //======================= Material Issue Part ================
   public ArrayList searchRequestList(String requestedDate)
   {
	   ArrayList listObj = new ArrayList();
	   ResultSet rs1=null;
	   String centre_code = null;
	   String centre_name = null; 
	   String program_code = null;
	   String program_name = null;  
	   String year_of_study = null;
	   String medium = null;
	   String quantity = null;
	   String requested_date= null;
	   String available_Stock = null;
   	
	  	try {
	          makeConnection();          
	          selStmt="select centre_code, centre_name, program_code, program_name, year_of_study, medium, quantity, requested_date from " +
	          		"tblRequestMaster where requested_date = ? order by centre_code";
	          Debug.print("Query: "+selStmt);
	          
	          prepStmt = con.prepareStatement(selStmt);
	          prepStmt.setString(1,requestedDate);  
	           
	         
	          rs = prepStmt.executeQuery();
	          
	          while(rs.next()) {
	        	  centre_code = rs.getString(1);
	        	  centre_name = rs.getString(2); 
	        	  program_code = rs.getString(3);
	        	  program_name = rs.getString(4);  
	        	  year_of_study = rs.getString(5);
	        	  medium = rs.getString(6);
	        	  quantity = rs.getString(7);
	        	  requested_date= rs.getString(8);
	          	
	          	
	          		String selStmt1="select MIN(total_stock) from tblWarehouseMaster where program_code=? and year_Of_Course=? and medium=?";
	          		prepStmt1 = con.prepareStatement(selStmt1);
	          		prepStmt1.setString(1,program_code);
	          		prepStmt1.setString(2,year_of_study); 
	          		prepStmt1.setString(3,medium); 
	          		
	          		rs1 = prepStmt1.executeQuery();
	          		
	          	   while(rs1.next()) {
	          		 available_Stock = rs1.getString(1); 
	          	   }
	               rs1.close();
	               prepStmt1.close();
	          		
	          	String[] temps={centre_code,centre_name,program_code,program_name, year_of_study,medium, quantity,requested_date,available_Stock};
	          	listObj.add(temps);
	                             
	          } 
	          
	          rs.close();
	          prepStmt.close();
	        
	      } catch(SQLException sqe){
	      
	      
	          Debug.print("Exception in LMSStockDAO searchRequestList() result: " + sqe.getMessage());
	          sqe.printStackTrace();
	      } finally {
	          releaseConnection();
	      }
	  	return listObj;
   }
   //---------------------- Material Issue By course wise ------------------------
   public String getCourseAvailQuantity(String courseCode,String medium,String YOS)
   {
	   String availQuantity = null;
	   
		try {
	          makeConnection();  
	          String selStmt1="select total_stock from tblWarehouseMaster where course_code=? and year_Of_Course=? and medium=?";
        		prepStmt1 = con.prepareStatement(selStmt1);
        		prepStmt1.setString(1,courseCode);
        		prepStmt1.setString(2,YOS); 
        		prepStmt1.setString(3,medium); 
        		
        		rs = prepStmt1.executeQuery();
        		
        	   while(rs.next()) {
        		   availQuantity = rs.getString(1); 
        	   }
		}	    
	    catch(SQLException sqe){
	   
	   
	       Debug.print("Exception in LMSStockDAO getCourseAvailQuantity() result: " + sqe.getMessage());
	       sqe.printStackTrace();
	   } finally {
	       releaseConnection();
	   }
	   return availQuantity;
   }
   
   
   
   
   
   
   
   public String matIssue(String centreCode,String centreName,String progCode,String progName,String mediumType,
			String yeas_Of_Study,String available_Quantity,String request_Quantity,String Issue_Quantity,
			String Issue_Date,String requestedDate)
   {
			   String result = "Failure";
			   String IssueID = null;
			   int issQuantity = 0;
			   int updated_issueQuantity = 0;
			   
			 // ---------------------- Select issued quantity from Issue Master table ----------------------------
			   try {
			        	makeConnection();
						 String selectStatement = "select issue_id, issued_quantity from tblIssueMaster where centre_code=? and program_code=? and requested_date=? and medium=? and year_of_study=?";
						 prepStmt = con.prepareStatement(selectStatement);	 
						 prepStmt.setString(1,centreCode);
						 prepStmt.setString(2,progCode);
						 prepStmt.setString(3,requestedDate);
						 prepStmt.setString(4,mediumType);
						 prepStmt.setString(5,yeas_Of_Study);
						 
					    rs = prepStmt.executeQuery();
					    
					    while(rs.next()){			   
					    	IssueID=rs.getString(1);	
					    	issQuantity = Integer.parseInt(rs.getString(2));
					    }
			 			  
				       rs.close();
				       prepStmt.close();
			      
			    } 
		      catch (Exception ex) {
			   	      releaseConnection();
				        	ex.printStackTrace();
				             Debug.print("Exception:" + ex.getMessage());
			     }
				finally {
			           releaseConnection();
			       }
				
		  
		  
			  // ---------------------- update issued quantity from Issue Master table ----------------------------
		  
		  if(IssueID != null && IssueID!="" && issQuantity != 0)	   
		  {
			   updated_issueQuantity = issQuantity + Integer.parseInt(Issue_Quantity);
				System.out.println("issQuantity : "+issQuantity);
				System.out.println("Issue_Quantity : "+Issue_Quantity);
				System.out.println("updated_issueQuantity : "+updated_issueQuantity);
				
				try { 
		          makeConnection();         
		             
		          String updateStatement =  "update tblIssueMaster set issued_quantity=?, issued_date=? where issue_id=?";
		          prepStmt = con.prepareStatement(updateStatement);
		          prepStmt.setLong(1,updated_issueQuantity);  
		          prepStmt.setString(2,Issue_Date); 
		          prepStmt.setString(3,IssueID);             
		          
		          Debug.print("Quary : "+updateStatement);          
		         
		          int cnt = prepStmt.executeUpdate();
		 	      
		          if(cnt>0){         	  
		          	result = "success";                
		          }
		        
		          prepStmt.close();
		        
		      } 
		      catch (SQLException sqe) {
		         releaseConnection();
		          Debug.print("Error while calling matIssue() on update : "+sqe.getMessage());         
		      } finally {
		          releaseConnection();
		      }
		  }
		// --------------------- insert  into request master table ------------------------
		  else
		  { 	
			   	try { 
			           makeConnection();         
			              
			           String insertStmt=  "insert into tblIssueMaster(centre_code, centre_name, program_code, program_name, year_of_study," +
			           		" medium, requested_quantity, requested_date, issued_quantity, issued_date) values 	(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			        		   
			           Debug.print("Quary : "+insertStmt);
			           prepStmt = con.prepareStatement(insertStmt);
			           
			           prepStmt.setString(1,centreCode);
			           prepStmt.setString(2,centreName);
			           prepStmt.setString(3,progCode);
			           prepStmt.setString(4,progName);
			           prepStmt.setString(5,yeas_Of_Study);
			           prepStmt.setString(6,mediumType); 
			           prepStmt.setString(7,request_Quantity);
			           prepStmt.setString(8,requestedDate);
			           prepStmt.setString(9,Issue_Quantity);
			           prepStmt.setString(10,Issue_Date);
			          
			           int cnt = prepStmt.executeUpdate();
			 	      
			           if(cnt>0){         	  
			           	result = "success";                
			           }
			         
			           prepStmt.close();
			         
			       } 
			       catch (SQLException sqe) {
			           releaseConnection();
			           Debug.print("Error while calling matIssue() on insert: "+sqe.getMessage());         
			       } finally {
			           releaseConnection();
			       }
		  		}
		  
		  //------------- select requested item from request master table -------------------------------
		  String requestedID = null;
		  int last_Quantity=0;
		  int updated_Quantity =0;
		  
		// --------------------- Select no of quantity from request master table ------------------------
				try {
		        	makeConnection();
					 String selectStatement = "select request_id, quantity from tblRequestMaster where centre_code=? and program_code=? and requested_date=? and medium=?";
					 prepStmt = con.prepareStatement(selectStatement);	 
					 prepStmt.setString(1,centreCode);
					 prepStmt.setString(2,progCode);
					 prepStmt.setString(3,requestedDate);
					 prepStmt.setString(4,mediumType);
					 
				    rs = prepStmt.executeQuery();
				    
				    while(rs.next()){			   
				    	requestedID=rs.getString(1);	
				    	last_Quantity = Integer.parseInt(rs.getString(2));
				    }
		 			  
			       rs.close();
			       prepStmt.close();
			      
			    } 
		      catch (Exception ex) {
		   	      releaseConnection();
			        	ex.printStackTrace();
			             Debug.print("Exception:" + ex.getMessage());
			     }
				finally {
			           releaseConnection();
			       }
				
		  
		  
				// --------------------- update on request master table ------------------------
		  
		  if(requestedID != null && requestedID!="" && last_Quantity != 0)	   
		  {
			   updated_Quantity = last_Quantity - Integer.parseInt(Issue_Quantity);
				if(updated_Quantity != 0 || updated_Quantity > 0)
				{
					try { 
			          makeConnection();         
			             
			          String updateStatement =  "update tblRequestMaster set quantity=? where request_id=?";
			          prepStmt = con.prepareStatement(updateStatement);
			          prepStmt.setLong(1,updated_Quantity);               
			          prepStmt.setString(2,requestedID);             
			          
			          Debug.print("Quary : "+updateStatement);          
			         
			          int cnt = prepStmt.executeUpdate();
			 	      
			          if(cnt>0){         	  
			          	result = "success";                
			          }
			        
			          prepStmt.close();
			        
			      } 
			      catch (SQLException sqe) {
			         releaseConnection();
			          Debug.print("Error while calling addMatRequest() on update : "+sqe.getMessage());         
			      } finally {
			          releaseConnection();
			      }
			  }
			 else
			 {
				 try { 
			            makeConnection();         
			               
			            String delStatement =  "delete from tblRequestMaster  where  request_id = ?";
			            prepStmt = con.prepareStatement(delStatement);
			            prepStmt.setString(1,requestedID);
			          
			            
			            Debug.print("Quary : "+delStatement);
			          
			           
			            int cnt = prepStmt.executeUpdate();
			   	      
			            if(cnt>0){         	  
			            	result = "success";                
			            }
			          
			            prepStmt.close();
			          
			        } 
			        catch (SQLException sqe) {
			            releaseConnection();
			            Debug.print("Error while calling deleteReqyest() : "+sqe.getMessage());         
			        } finally {
			            releaseConnection();
			        }
			 }
	     }
		  
		//======================= Update the warehouse details ==========================
		  int totalStock=0;
		  String stockID = null;
		 
		  try {
	        	makeConnection();
				 String selectStatement = "select stock_id, total_stock from tblWarehouseMaster where program_code=? and year_Of_Course=? and medium=?";
				 prepStmt = con.prepareStatement(selectStatement);	 
				 prepStmt.setString(1,progCode);
				 prepStmt.setString(2,yeas_Of_Study);
				 prepStmt.setString(3,mediumType);
				 
			    rs = prepStmt.executeQuery();
			    
			    while(rs.next()){
			    	stockID = rs.getString(1);
			    	totalStock = Integer.parseInt(rs.getString(2));
			    	totalStock = totalStock - Integer.parseInt(Issue_Quantity);
			    	
			    	String upStmt1="update tblWarehouseMaster set total_stock=? where stock_id=?";
	          		prepStmt1 = con.prepareStatement(upStmt1);
	          		prepStmt1.setLong(1,totalStock);
	          		prepStmt1.setString(2,stockID);           		
	          		
	          		 int cnt = prepStmt1.executeUpdate();
			 	      
			          if(cnt>0){         	  
			          	result = "success";                
			          }
	                prepStmt1.close();
			    	
			    	
			    }
	 			  
		       rs.close();
		       prepStmt.close();
		      
		    } 
	      catch (Exception ex) {
	   	      releaseConnection();
		        	ex.printStackTrace();
		             Debug.print("Exception:" + ex.getMessage());
		     }
			finally {
		           releaseConnection();
		       }
			
			
		  return result;
   }
   
   //==================== material Issue for Course wise ============================
   public String matCourseIssue(String courseBarcode, String courseCode, String courseName, String medium, String centreCode,
			  String centreName, String YOS, String availQuantity, String reqQuantity, String issQuantity, String reqDate, String issDate)
   {
	   String result = "Failure";
	   String IssueID = null;
	   int issueQuantity = 0;
	   int updated_issueQuantity = 0;
	   
	 // ---------------------- Select issued quantity from Issue Master table ----------------------------
	   try {
	        	makeConnection();
				 String selectStatement = "select issue_id, issued_quantity from tblIssueMaster where centre_code=? and program_code=? and requested_date=? and medium=? and year_of_study=?";
				 prepStmt = con.prepareStatement(selectStatement);	 
				 prepStmt.setString(1,centreCode);
				 prepStmt.setString(2,courseCode);
				 prepStmt.setString(3,reqDate);
				 prepStmt.setString(4,medium);
				 prepStmt.setString(5,YOS);
				 
			    rs = prepStmt.executeQuery();
			    
			    while(rs.next()){			   
			    	IssueID=rs.getString(1);	
			    	issueQuantity = Integer.parseInt(rs.getString(2));
			    }
	 			  
		       rs.close();
		       prepStmt.close();
	      
	    } 
      catch (Exception ex) {
	   	      releaseConnection();
		        	ex.printStackTrace();
		             Debug.print("Exception:" + ex.getMessage());
	     }
		finally {
	           releaseConnection();
	       }
		
  
  
	  // ---------------------- update issued quantity from Issue Master table ----------------------------
  
  if(IssueID != null && IssueID!="" && issueQuantity != 0)	   
  {
	   updated_issueQuantity = issueQuantity + Integer.parseInt(issQuantity);
		System.out.println("issueQuantity : "+issueQuantity);
		System.out.println("issQuantity : "+issQuantity);
		System.out.println("updated_issueQuantity : "+updated_issueQuantity);
		
		try { 
          makeConnection();         
             
          String updateStatement =  "update tblIssueMaster set issued_quantity=?, issued_date=? where issue_id=?";
          prepStmt = con.prepareStatement(updateStatement);
          prepStmt.setLong(1,updated_issueQuantity);  
          prepStmt.setString(2,issDate); 
          prepStmt.setString(3,IssueID);             
          
          Debug.print("Quary : "+updateStatement);          
         
          int cnt = prepStmt.executeUpdate();
 	      
          if(cnt>0){         	  
          	result = "success";                
          }
        
          prepStmt.close();
        
      } 
      catch (SQLException sqe) {
         releaseConnection();
          Debug.print("Error while calling matCourseIssue() on update : "+sqe.getMessage());         
      } finally {
          releaseConnection();
      }
  }
// --------------------- insert  into request master table ------------------------
  else
  { 	
	   	try { 
	           makeConnection();         
	              
	           String insertStmt=  "insert into tblIssueMaster(centre_code, centre_name, program_code, program_name, year_of_study," +
	           		" medium, requested_quantity, requested_date, issued_quantity, issued_date) values 	(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        		   
	           Debug.print("Quary : "+insertStmt);
	           prepStmt = con.prepareStatement(insertStmt);
	           
	           prepStmt.setString(1,centreCode);
	           prepStmt.setString(2,centreName);
	           prepStmt.setString(3,courseCode);
	           prepStmt.setString(4,courseName);
	           prepStmt.setString(5,YOS);
	           prepStmt.setString(6,medium); 
	           prepStmt.setString(7,reqQuantity);
	           prepStmt.setString(8,reqDate);
	           prepStmt.setString(9,issQuantity);
	           prepStmt.setString(10,issDate);
	          
	           int cnt = prepStmt.executeUpdate();
	 	      
	           if(cnt>0){         	  
	           	result = "success";                
	           }
	         
	           prepStmt.close();
	         
	       } 
	       catch (SQLException sqe) {
	           releaseConnection();
	           Debug.print("Error while calling matCourseIssue() on insert: "+sqe.getMessage());         
	       } finally {
	           releaseConnection();
	       }
  		}
  
/*  //------------- select requested item from request master table -------------------------------
  String requestedID = null;
  int last_Quantity=0;
  int updated_Quantity =0;
  
// --------------------- Select no of quantity from request master table ------------------------
		try {
        	makeConnection();
			 String selectStatement = "select request_id, quantity from tblRequestMaster where centre_code=? and program_code=? and requested_date=? and medium=?";
			 prepStmt = con.prepareStatement(selectStatement);	 
			 prepStmt.setString(1,centreCode);
			 prepStmt.setString(2,progCode);
			 prepStmt.setString(3,requestedDate);
			 prepStmt.setString(4,mediumType);
			 
		    rs = prepStmt.executeQuery();
		    
		    while(rs.next()){			   
		    	requestedID=rs.getString(1);	
		    	last_Quantity = Integer.parseInt(rs.getString(2));
		    }
 			  
	       rs.close();
	       prepStmt.close();
	      
	    } 
      catch (Exception ex) {
   	      releaseConnection();
	        	ex.printStackTrace();
	             Debug.print("Exception:" + ex.getMessage());
	     }
		finally {
	           releaseConnection();
	       }
		
  
  
		// --------------------- update on request master table ------------------------
  
  if(requestedID != null && requestedID!="" && last_Quantity != 0)	   
  {
	   updated_Quantity = last_Quantity - Integer.parseInt(Issue_Quantity);
		if(updated_Quantity != 0 || updated_Quantity > 0)
		{
			try { 
	          makeConnection();         
	             
	          String updateStatement =  "update tblRequestMaster set quantity=? where request_id=?";
	          prepStmt = con.prepareStatement(updateStatement);
	          prepStmt.setLong(1,updated_Quantity);               
	          prepStmt.setString(2,requestedID);             
	          
	          Debug.print("Quary : "+updateStatement);          
	         
	          int cnt = prepStmt.executeUpdate();
	 	      
	          if(cnt>0){         	  
	          	result = "success";                
	          }
	        
	          prepStmt.close();
	        
	      } 
	      catch (SQLException sqe) {
	         releaseConnection();
	          Debug.print("Error while calling addMatRequest() on update : "+sqe.getMessage());         
	      } finally {
	          releaseConnection();
	      }
	  }
	 else
	 {
		 try { 
	            makeConnection();         
	               
	            String delStatement =  "delete from tblRequestMaster  where  request_id = ?";
	            prepStmt = con.prepareStatement(delStatement);
	            prepStmt.setString(1,requestedID);
	          
	            
	            Debug.print("Quary : "+delStatement);
	          
	           
	            int cnt = prepStmt.executeUpdate();
	   	      
	            if(cnt>0){         	  
	            	result = "success";                
	            }
	          
	            prepStmt.close();
	          
	        } 
	        catch (SQLException sqe) {
	            releaseConnection();
	            Debug.print("Error while calling deleteReqyest() : "+sqe.getMessage());         
	        } finally {
	            releaseConnection();
	        }
	 }
 }
  */
//======================= Update the warehouse details ==========================
  int totalStock=0;
  String stockID = null;
 
  try {
    	makeConnection();
		 String selectStatement = "select stock_id, total_stock from tblWarehouseMaster where course_code=? and year_Of_Course=? and medium=?";
		 prepStmt = con.prepareStatement(selectStatement);	 
		 prepStmt.setString(1,courseCode);
		 prepStmt.setString(2,YOS);
		 prepStmt.setString(3,medium);
		 
	    rs = prepStmt.executeQuery();
	    
	    while(rs.next()){
	    	stockID = rs.getString(1);
	    	totalStock = Integer.parseInt(rs.getString(2));
	    	totalStock = totalStock - Integer.parseInt(issQuantity);
	    	
	    	String upStmt1="update tblWarehouseMaster set total_stock=? where stock_id=?";
      		prepStmt1 = con.prepareStatement(upStmt1);
      		prepStmt1.setLong(1,totalStock);
      		prepStmt1.setString(2,stockID);           		
      		
      		 int cnt = prepStmt1.executeUpdate();
	 	      
	          if(cnt>0){         	  
	          	result = "success";                
	          }
            prepStmt1.close();
	    	
	    	
	    }
			  
       rs.close();
       prepStmt.close();
      
    } 
  catch (Exception ex) {
	      releaseConnection();
        	ex.printStackTrace();
             Debug.print("Exception:" + ex.getMessage());
     }
	finally {
           releaseConnection();
       }
	
	
  return result;
   }
   
 //=================== get the pending request ==========================  
   public ArrayList searchPendRequestList(String requestedType)
   {
	   ArrayList listObj = new ArrayList();
	   ResultSet rs1=null;
	   String centre_code = null;
	   String centre_name = null; 
	   String program_code = null;
	   String program_name = null;  
	   String year_of_study = null;
	   String medium = null;
	   String quantity = null;
	   String requested_date= null;
	   String available_Stock = null;
		
	  	try {
	          makeConnection(); 
	          if(requestedType.equalsIgnoreCase("Program Code Wise"))
	          {
	        	  selStmt="select centre_code, centre_name, program_code, program_name, year_of_study, medium, quantity, requested_date from " +
	  	          "tblRequestMaster order by program_code";
	          }
	          else if(requestedType.equalsIgnoreCase("Centre Code Wise"))
	          {
	        	  selStmt="select centre_code, centre_name, program_code, program_name, year_of_study, medium, quantity, requested_date from " +
	  	          "tblRequestMaster order by centre_code";
	          }
	          else if(requestedType.equalsIgnoreCase("Date Wise"))
	          {
	        	  selStmt="select centre_code, centre_name, program_code, program_name, year_of_study, medium, quantity, requested_date from " +
	  	          "tblRequestMaster order by requested_date DESC";
	          }
	          else if(requestedType.equalsIgnoreCase("Year Wise"))
	          {
	        	  selStmt="select centre_code, centre_name, program_code, program_name, year_of_study, medium, quantity, requested_date from " +
	  	          "tblRequestMaster order by year_of_study";
	          }
	          
	          
	         /* selStmt="select centre_code, centre_name, program_code, program_name, year_of_study, medium, quantity, requested_date from " +
	          		"tblRequestMaster where requested_date = ? order by centre_code";*/
	          Debug.print("Query: "+selStmt);
	          
	          prepStmt = con.prepareStatement(selStmt);
	         // prepStmt.setString(1,requestedDate);  
	           
	         
	          rs = prepStmt.executeQuery();
	          
	          while(rs.next()) {
	        	  centre_code = rs.getString(1);
	        	  centre_name = rs.getString(2); 
	        	  program_code = rs.getString(3);
	        	  program_name = rs.getString(4);  
	        	  year_of_study = rs.getString(5);
	        	  medium = rs.getString(6);
	        	  quantity = rs.getString(7);
	        	  requested_date= rs.getString(8);
	          	
	          	
	          		String selStmt1="select MIN(total_stock) from tblWarehouseMaster where program_code=? and year_Of_Course=? and medium=?";
	          		prepStmt1 = con.prepareStatement(selStmt1);
	          		prepStmt1.setString(1,program_code);
	          		prepStmt1.setString(2,year_of_study); 
	          		prepStmt1.setString(3,medium); 
	          		
	          		rs1 = prepStmt1.executeQuery();
	          		
	          	   while(rs1.next()) {
	          		 available_Stock = rs1.getString(1); 
	          	   }
	               rs1.close();
	               prepStmt1.close();
	          		
	          	String[] temps={centre_code,centre_name,program_code,program_name, year_of_study,medium, quantity,requested_date,available_Stock};
	          	listObj.add(temps);
	                             
	          } 
	          
	          rs.close();
	          prepStmt.close();
	        
	      } catch(SQLException sqe){
	      
	      
	          Debug.print("Exception in LMSStockDAO searchPendRequestList() result: " + sqe.getMessage());
	          sqe.printStackTrace();
	      } finally {
	          releaseConnection();
	      }
  	return listObj;
}
   
 
   //========================= Inward Report =============================
   
   public ArrayList getInwardReport(String requestedType,String fromDate,String toDate)
   {
	   ArrayList inwardReport = new ArrayList();
		try {
	          makeConnection(); 
	          if(requestedType.equalsIgnoreCase("Course Code Wise"))
	          {
	        	  selStmt="select course_code, course_name, medium, PO_No, press_name, ordered_date, DC_No, delivery_date, received_copies" +
	        	  		" from tblInwardMaster where delivery_date BETWEEN ? AND ? order by course_code";
	          }
	          else if(requestedType.equalsIgnoreCase("Press Name Wise"))
	          {
	        	  selStmt="select course_code, course_name, medium, PO_No, press_name, ordered_date, DC_No, delivery_date, received_copies" +
		        	  		" from tblInwardMaster where delivery_date BETWEEN ? AND ? order by press_name";
	          }
	          else if(requestedType.equalsIgnoreCase("Delivery Date Wise"))
	          {
	        	  selStmt="select course_code, course_name, medium, PO_No, press_name, ordered_date, DC_No, delivery_date, received_copies" +
		        	  		" from tblInwardMaster where delivery_date BETWEEN ? AND ? order by delivery_date DESC";
	          }
	                 
	          
	         /* selStmt="select centre_code, centre_name, program_code, program_name, year_of_study, medium, quantity, requested_date from " +
	          		"tblRequestMaster where requested_date = ? order by centre_code";*/
	          Debug.print("Query: "+selStmt);
	          
	          prepStmt = con.prepareStatement(selStmt);
	          prepStmt.setString(1,fromDate); 
	          prepStmt.setString(2,toDate); 
	           
	         
	          rs = prepStmt.executeQuery();
	          
	          while(rs.next()) {
	        	String course_code = rs.getString(1);
	        	String course_name = rs.getString(2); 
	        	String medium = rs.getString(3);
	        	String PO_No = rs.getString(4);  
	        	String press_name = rs.getString(5);
	        	String ordered_date = rs.getString(6);
	        	String DC_No = rs.getString(7);
	        	String delivery_date= rs.getString(8);
	        	String received_copies= rs.getString(9);
	          		
	          		
	          	String[] temps={delivery_date, DC_No, press_name, course_code, course_name, medium,  PO_No, ordered_date, received_copies};
	          	inwardReport.add(temps);
	                             
	          } 
	          
	          rs.close();
	          prepStmt.close();
	        
	      } catch(SQLException sqe){
	      
	      
	          Debug.print("Exception in LMSStockDAO getInwardReport() result: " + sqe.getMessage());
	          sqe.printStackTrace();
	      } finally {
	          releaseConnection();
	      }
	   
	   
	   return inwardReport;
   }
   
   
//========================= Press Report =============================
   
   public ArrayList getPressReport(String requestedType,String fromDate,String toDate)
   {
	   ArrayList pressReport = new ArrayList();
		try {
	          makeConnection(); 
	          
	          if(requestedType.equalsIgnoreCase("Course Code Wise"))
	          {
	        	  selStmt="select PO_No, ordered_date, printer_name, course_code, course_name, medium, nature_of_print, no_of_copies, " +
	        	  		"pendingTo_receive_copies from tblPrintingOrder where ordered_date BETWEEN ? AND ? order by course_code";
	          }
	          else if(requestedType.equalsIgnoreCase("Press Name Wise"))
	          {
	        	  selStmt="select PO_No, ordered_date, printer_name, course_code, course_name, medium, nature_of_print, no_of_copies," +
		        	  		" pendingTo_receive_copies from tblPrintingOrder where ordered_date BETWEEN ? AND ? order by printer_name";
	          }
	          else if(requestedType.equalsIgnoreCase("Ordered Date Wise"))
	          {
	        	  selStmt="select PO_No, ordered_date, printer_name, course_code, course_name, medium, nature_of_print, no_of_copies," +
		        	  		" pendingTo_receive_copies from tblPrintingOrder where ordered_date BETWEEN ? AND ? order by ordered_date DESC";
	          }
	                 
	          
	         /* selStmt="select centre_code, centre_name, program_code, program_name, year_of_study, medium, quantity, requested_date from " +
	          		"tblRequestMaster where requested_date = ? order by centre_code";*/
	          Debug.print("Query: "+selStmt);
	          
	          prepStmt = con.prepareStatement(selStmt);
	          prepStmt.setString(1,fromDate); 
	          prepStmt.setString(2,toDate); 
	           
	         
	          rs = prepStmt.executeQuery();
	          
	          while(rs.next()) {
	        	String PO_No = rs.getString(1);
	        	String ordered_date = rs.getString(2); 
	        	String printer_name = rs.getString(3);
	        	String course_code = rs.getString(4);  
	        	String course_name  = rs.getString(5);  
	        	String medium = rs.getString(6);
	        	String nature_of_print = rs.getString(7);
	        	String no_of_copies = rs.getString(8);
	        	String pendingTo_receive_copies= rs.getString(9);
	        	
	          		
	          		
	          	String[] temps={PO_No, ordered_date, printer_name, course_code, course_name, medium, nature_of_print, no_of_copies, pendingTo_receive_copies};
	          	pressReport.add(temps);
	                             
	          } 
	          
	          rs.close();
	          prepStmt.close();
	        
	      } catch(SQLException sqe){
	      
	      
	          Debug.print("Exception in LMSStockDAO getInwardReport() result: " + sqe.getMessage());
	          sqe.printStackTrace();
	      } finally {
	          releaseConnection();
	      }
	   
	   
	   return pressReport;
   }
   
   
//========================= Request Report =============================
   
   public ArrayList getRequestReport(String requestedType,String fromDate,String toDate)
   {
	   ArrayList reqReport = new ArrayList();
		try {
	          makeConnection(); 
	          
	          if(requestedType.equalsIgnoreCase("Centre Code Wise"))
	          {
	        	  selStmt="select requested_date, centre_code, centre_name, program_code, program_name, medium, year_of_study," +
	        	  		" quantity from tblRequestMaster where requested_date BETWEEN ? AND ? order by centre_code";
	          }
	          else if(requestedType.equalsIgnoreCase("Program Code Wise"))
	          {
	        	  selStmt="select requested_date, centre_code, centre_name, program_code, program_name, medium, year_of_study," +
		        	  		" quantity from tblRequestMaster where requested_date BETWEEN ? AND ? order by program_code";
	          }
	          else if(requestedType.equalsIgnoreCase("Academic Year Wise"))
	          {
	        	  selStmt="select requested_date, centre_code, centre_name, program_code, program_name, medium, year_of_study," +
		        	  		" quantity from tblRequestMaster where requested_date BETWEEN ? AND ? order by year_of_study";
	          }
	          
	          else if(requestedType.equalsIgnoreCase("Requested Date Wise"))
	          {
	        	  selStmt="select requested_date, centre_code, centre_name, program_code, program_name, medium, year_of_study," +
		        	  		" quantity from tblRequestMaster where requested_date BETWEEN ? AND ? order by requested_date DESC";
	          }
	                 
	          
	         /* selStmt="select centre_code, centre_name, program_code, program_name, year_of_study, medium, quantity, requested_date from " +
	          		"tblRequestMaster where requested_date = ? order by centre_code";*/
	          Debug.print("Query: "+selStmt);
	          
	          prepStmt = con.prepareStatement(selStmt);
	          prepStmt.setString(1,fromDate); 
	          prepStmt.setString(2,toDate); 
	           
	         
	          rs = prepStmt.executeQuery();
	          
	          while(rs.next()) {
	        	String requested_date = rs.getString(1);
	        	String centre_code = rs.getString(2); 
	        	String centre_name = rs.getString(3);
	        	String program_code = rs.getString(4);  
	        	String program_name  = rs.getString(5);  
	        	String medium = rs.getString(6);
	        	String year_of_study = rs.getString(7);
	        	String quantity = rs.getString(8);
	        	
	        	
	          		
	          		
	          	String[] temps={requested_date, centre_code, centre_name, program_code, program_name, medium, year_of_study, quantity};
	          	reqReport.add(temps);
	                             
	          } 
	          
	          rs.close();
	          prepStmt.close();
	        
	      } catch(SQLException sqe){
	      
	      
	          Debug.print("Exception in LMSStockDAO getRequestReport() result: " + sqe.getMessage());
	          sqe.printStackTrace();
	      } finally {
	          releaseConnection();
	      }
	   
	   
	   return reqReport;
   }
   
   
   
//========================= Request Report =============================
   
   public ArrayList getIssueReport(String requestedType,String fromDate,String toDate)
   {
	   ArrayList issReport = new ArrayList();
		try {
	          makeConnection(); 
	          
	          if(requestedType.equalsIgnoreCase("Centre Code Wise"))
	          {
	        	  selStmt="select issued_date, centre_code, centre_name, program_code, program_name, medium,  year_of_study, " +
	        	  		"issued_quantity  from tblIssueMaster where issued_date BETWEEN ? AND ? order by centre_code";
	          }
	          else if(requestedType.equalsIgnoreCase("Program Code Wise"))
	          {
	        	  selStmt="select issued_date, centre_code, centre_name, program_code, program_name, medium,  year_of_study, " +
		        	  		"issued_quantity  from tblIssueMaster where issued_date BETWEEN ? AND ? order by program_code";
	          }
	          else if(requestedType.equalsIgnoreCase("Issued Date Wise"))
	          {
	        	  selStmt="select issued_date, centre_code, centre_name, program_code, program_name, medium,  year_of_study, " +
		        	  		"issued_quantity  from tblIssueMaster where issued_date BETWEEN ? AND ? order by issued_date DESC";
	          }
	          
	          
	                 
	          
	         /* selStmt="select centre_code, centre_name, program_code, program_name, year_of_study, medium, quantity, requested_date from " +
	          		"tblRequestMaster where requested_date = ? order by centre_code";*/
	          Debug.print("Query: "+selStmt);
	          
	          prepStmt = con.prepareStatement(selStmt);
	          prepStmt.setString(1,fromDate); 
	          prepStmt.setString(2,toDate); 
	           
	         
	          rs = prepStmt.executeQuery();
	          
	          while(rs.next()) {
	        	String issued_date = rs.getString(1);
	        	String centre_code = rs.getString(2); 
	        	String centre_name = rs.getString(3);
	        	String program_code = rs.getString(4);  
	        	String program_name  = rs.getString(5);  
	        	String medium = rs.getString(6);
	        	String year_of_study = rs.getString(7);
	        	String issued_quantity = rs.getString(8);
	        	
	        	
	          		
	          		
	          	String[] temps={issued_date, centre_code, centre_name, program_code, program_name, medium, year_of_study, issued_quantity};
	          	issReport.add(temps);
	                             
	          } 
	          
	          rs.close();
	          prepStmt.close();
	        
	      } catch(SQLException sqe){
	      
	      
	          Debug.print("Exception in LMSStockDAO getIssueReport() result: " + sqe.getMessage());
	          sqe.printStackTrace();
	      } finally {
	          releaseConnection();
	      }
	   
	   
	   return issReport;
   }
   
   public ArrayList Programcodelist()
   {
	   ArrayList issReport = new ArrayList();
		try {
	          makeConnection(); 
	          
	        
	        	  selStmt="select program_code, program_name from tblProgramMaster";
	          
	          
	          
	                 
	          
	         /* selStmt="select centre_code, centre_name, program_code, program_name, year_of_study, medium, quantity, requested_date from " +
	          		"tblRequestMaster where requested_date = ? order by centre_code";*/
	          Debug.print("Query: "+selStmt);
	          
	          prepStmt = con.prepareStatement(selStmt);
	         // prepStmt.setString(1,fromDate); 
	         // prepStmt.setString(2,toDate); 
	           
	         
	          rs = prepStmt.executeQuery();
	          
	          while(rs.next()) {
	        	String program_code = rs.getString(1);
	        	String program_name = rs.getString(2);
	        	
	          		
	          		
	          	String[] temps={program_code,program_name};
	          	issReport.add(temps);
	                             
	          } 
	          
	          rs.close();
	          prepStmt.close();
	        
	      } catch(SQLException sqe){
	      
	      
	          Debug.print("Exception in LMSStockDAO getIssueReport() result: " + sqe.getMessage());
	          sqe.printStackTrace();
	      } finally {
	          releaseConnection();
	      }
	   
	   
	   return issReport;
   }
   
  // ================================= get Stock Status =================================
   
   
   public ArrayList getStockStatus(String requestedType, String searchCode)
   {
	   ArrayList stockStatus_list = new ArrayList();
	   
	   
	  /* System.out.println("requestedType on DAO : "+requestedType);
		  System.out.println("searchCode on DAO : "+searchCode);*/
		  
		  
		try {
	          makeConnection(); 
	          
	          if(requestedType.equalsIgnoreCase("Course Code Wise"))
	          {
	        	  selStmt="select t1.program_code, t2.program_name, t1.course_code,t3.course_name, t1.year_Of_Course, t1.medium, " +
	        	  		"t1.total_stock from tblWarehouseMaster t1 inner join tblProgramMaster t2 on t2.program_code=t1.program_code " +
	        	  		"inner join tblCourseMaster t3 on t3.course_code=t1.course_code where t1.course_code=?";
	          }
	          else if(requestedType.equalsIgnoreCase("Program Code Wise"))
	          {
	        	  selStmt="select t1.program_code, t2.program_name, t1.course_code,t3.course_name, t1.year_Of_Course, t1.medium," +
	        	  		" t1.total_stock from tblWarehouseMaster t1 inner join tblProgramMaster t2 on t2.program_code=t1.program_code" +
	        	  		" inner join tblCourseMaster t3 on t3.course_code=t1.course_code where t1.program_code=?";
	          }
	             
	     
	          Debug.print("Query: "+selStmt);
	          
	          prepStmt = con.prepareStatement(selStmt);
	          prepStmt.setString(1,searchCode); 
	         
	           
	         
	          rs = prepStmt.executeQuery();
	          
	          while(rs.next()) {	        
	        	String program_code = rs.getString(1);  
	        	String program_name  = rs.getString(2);  
	        	String course_code = rs.getString(3);  
	        	String course_name  = rs.getString(4); 
	        	String year_of_study = rs.getString(5);
	        	String medium = rs.getString(6);
	        	String total_stock = rs.getString(7);
	        	
	        	
	        /*	System.out.println("program_code on DAO : "+program_code);
	        	System.out.println("program_name on DAO : "+program_name);
	        	System.out.println("course_code on DAO : "+course_code);
	        	System.out.println("course_name on DAO : "+course_name);
	        	System.out.println("year_of_study on DAO : "+year_of_study);
	        	System.out.println("medium on DAO : "+medium);
	        	System.out.println("total_stock on DAO : "+total_stock);*/
	          		
	          		
	          	String[] temps={program_code, program_name, course_code, course_name, year_of_study, medium, total_stock};
	          	stockStatus_list.add(temps);
	                             
	          } 
	          
	          rs.close();
	          prepStmt.close();
	        
	      } catch(SQLException sqe){
	      
	      
	          Debug.print("Exception in LMSStockDAO getStockStatuss() result: " + sqe.getMessage());
	          sqe.printStackTrace();
	      } finally {
	          releaseConnection();
	      }
	   
	   
	   return stockStatus_list;
   }
   
   
   
   
   
    
    /**
     * Name         :makeConnection
     * Description  :This method will Create a databacse connection
     * @ param      :
     * @return      :void
     * @throws      :EJBException
     */
    
    private void makeConnection() {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(dbName);
            con = ds.getConnection();
            Debug.print(" Opening a connection...");
        } catch (Exception ex) {
            Debug.print("Unable to connect to database. " + ex.getMessage());
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
                con.close();
            }
            Debug.print(" Closing a connection...");
        } catch (SQLException ex) {
            Debug.print("releaseConnection: " + ex.getMessage());
        }
    }
    
    
    
    
}