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


import com.lmsstock.util.HLCEmployeeDetails;
import com.lmsstock.util.HLCMemberHistoryDetail;
import com.lmsstock.util.LMSStockDetails;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.*;
import java.sql.*;
import java.util.Date;



public interface LMSStockstatelessRemote extends EJBObject, LMSStockstatelessRemoteBusiness {
    
		
//================ parthi Here : Stock Module ===================================================
	
  //=========== For Course Master Maintenance ===========================
	
	public ArrayList getCourseList()throws RemoteException;
	
	public String addCourse(String courseCode, String courseName,String yearOfCourse, String Pages, String courseBarcode)throws RemoteException;
	
	public String updateCourse(String courseID, String courseCode, String courseName, String yearOfCourse, String pages)
			throws RemoteException;
	
	public String deleteCourse(String courseID, String courseCode)throws RemoteException;
	
	 //=========== For Program Master Maintenance ===========================
	
		public ArrayList getProgramList()throws RemoteException;
		
		public String addProgram(String progCode, String progName, String progBarcode)throws RemoteException;
		
		public String updateProgram(String progID, String progCode, String progName)throws RemoteException;
		
		public String deleteProgram(String progID, String progCode)throws RemoteException;
	
		 //=========== For Course Master Maintenance ===========================
		
		public ArrayList getPressList()throws RemoteException;
		
		public String addPress(String pressName, String pressAddress,String pressCity, String pressZip)throws RemoteException;
		
		public String updatePress(String pressID, String pressName, String pressAddress, String pressCity, String pressZip)
				throws RemoteException;
		
		public String deletePress(String pressID, String pressName)throws RemoteException;
		
		
		//=========== For centre Master Maintenance ===========================
		
				public ArrayList getCentreList()throws RemoteException;
				
				public String addCentre(String centreCode, String centreName, String centreAddress,String centreCity, 
						String centreZip)throws RemoteException;
				
				public String updateCentre(String centreID, String centreCode, String centreName, String centreAddress,String centreCity,
						String centreZip)throws RemoteException;
				
				public String deleteCentre(String centreID, String centreCode)throws RemoteException;
		
		
//========= For Program Course Mapping ==================
		public String mappingEntry(String progCode, String courseCode, String course_year, String medium,
				String programBarcode,String courseBarcode)throws RemoteException;
	
 //========= For Print Order to Press ==================	
		public String addPrintOrder(String po_no, String orderDate, String pressName,String pressAddress,String courseCode,
				String courseName,
				String printNature,String pages,String medium, String copies)throws RemoteException;
		
		public String getPO_NO()throws RemoteException;

		public ArrayList getOrderList(String po_no,String orderDate,String pressName)throws RemoteException;
		
//================= Inward Entry ===============================
		public String addInward(String po_no,String orderDate,String pressName,String dc_no,String deliveryDate,String courseCode,
				String courseName,String medium,String copies, String courseBarcode)throws RemoteException;
		
// ================ Material request to warehouse ================================
		public String addMatRequest(String centreCode,String centreName,String programCode,String programName,String medium,
				String year_of_study,String quantity,String requestedDate)throws RemoteException;
		
		public ArrayList getRequestList(String centreCode,String requestedDate)throws RemoteException;
		
// ================= Material Issue Part ================================
		
		public ArrayList searchRequestList(String requestedDate)throws RemoteException;
		
		public String matIssue(String centreCode,String centreName,String progCode,String progName,String mediumType,
				String yeas_Of_Study,String available_Quantity,String request_Quantity,String Issue_Quantity,
				String Issue_Date,String requestedDate)throws RemoteException;
		
		public String getCourseAvailQuantity(String courseCode,String medium,String YOS)throws RemoteException;
		
		public String matCourseIssue(String courseBarcode, String courseCode, String courseName, String medium, String centreCode,
				  String centreName, String YOS, String availQuantity, String reqQuantity, String issQuantity, String reqDate, String issDate)throws RemoteException;
				
		//------------ Pending Material Issue Part -------------------------
		
		public ArrayList searchPendRequestList(String requestedType)throws RemoteException;	
	
 // ====================== Inward Report Part =======================
		
		public ArrayList getInwardReport(String requestedType,String fromDate,String toDate)throws RemoteException;	
		
		public ArrayList getPressReport(String requestedType,String fromDate,String toDate)throws RemoteException;
		
		public ArrayList getRequestReport(String requestedType,String fromDate,String toDate)throws RemoteException;
		
		public ArrayList getIssueReport(String requestedType,String fromDate,String toDate)throws RemoteException;
		
		public ArrayList getProgramcodelist()throws RemoteException;
		
 // ======================== Stock Status ==========================
		public ArrayList getStockStatus(String requestedType, String searchCode)throws RemoteException;
}
