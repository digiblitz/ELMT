#-------------------------------------------------------------------------------
# * * Copyright: 2019 digiBlitz Foundation
#  * * 
#  * * License: digiBlitz Public License 1.0 (DPL) 
#  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
#  * * 
#  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
#  * * 
#  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
#  * * 
#  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
#-------------------------------------------------------------------------------
<%!
/**************************** class LogonForm ***************************
 * 
 * This logon form is used to gather the logon information needed 
 * to login to Enterprise
 *   
 ***********************************************************************/
private class LogonForm {
	
	HttpServletResponse response;
	HttpServletRequest request;
	
	String cmsname;
	String username;
	String password;
	String authType;

	int num_reports_needed = 0;
	String[] reportname;

	public static final int NONE = 0;
	public static final int REPORT_NAME = 1;
	public static final int REPORT_NAME_X2 = 2;
	
	/************************************************************************
	 * 
	 * Constructor - Save the response and request objects
	 *               
	 ***********************************************************************/	
	LogonForm(HttpServletResponse response, HttpServletRequest request)
	{
		this.response = response;
		this.request = request;
	}

	/************************************************************************
	 * 
	 * Constructor with options - Add some optional boxes to request other
	 *                            input from the user such as reportnames.
	 *               
	 ***********************************************************************/	
	LogonForm(HttpServletResponse response, HttpServletRequest request, int extra_requests) {
		
		this.response = response;
		this.request = request;
		switch (extra_requests) {
			case NONE:
				break;
			case REPORT_NAME:
				num_reports_needed = 1;
				reportname = new String[1];
				break;
			case REPORT_NAME_X2:
				num_reports_needed = 2;
				reportname = new String[2];
				break;
		}
			 
	}

	/************************************************************************
	 * 
	 * If we do not already have the logon information, print a logon form.
	 * Return true if a logon form was printed, false if it wasn't.
	 *               
	 ***********************************************************************/	
	public boolean display_if_needed() throws java.io.IOException {
		
		if (request.getParameter("LogonForm.cmsname") == null) {
			// display logon form and exit
			String form = "";
			
			form += "<HTML>\r\n";
			form += "  <HEAD><TITLE></TITLE></HEAD>\r\n";
			form += "    <BODY>\r\n";
			form += "      <table>\r\n";
			form += "      <form method=\"post\">\r\n";
			form += "        <tr><td>CMS Name: </td><td><input type=\"text\" name=\"LogonForm.cmsname\"></td></tr>\r\n";
			form += "        <tr><td>User Name: </td><td><input type=\"text\" name=\"LogonForm.username\"></td></tr>\r\n";
			form += "        <tr><td>Password: </td><td><input type=\"password\" name=\"LogonForm.password\"></td></tr>\r\n";
			form += "        <tr><tr/>\r\n";
			form += "        <tr><td>Authentication Type:</td><td>\r\n";
			form += "          <select name=\"LogonForm.authType\" size=1 width=250>\r\n";
			form += "            <option value=\"secEnterprise\">Enterprise\r\n";
			form += "	     <option value=\"secLDAP\">LDAP\r\n";
  			form += "          </select>\r\n";
		    form += "        </td></tr>\r\n";
		    form += "        <tr></tr>\r\n";
			form += "        <tr></tr>\r\n";

			for (int i = 0; i < num_reports_needed; i++) {
				form += "        <tr><td>Report " + (i+1) + ": </td><td><input type=\"text\" name=\"LogonForm.reportnames[" + i + "]\"></td></tr>\r\n";
			}
			
		    form += "        <tr><td/><td><input type=\"submit\" value=\"Submit_Form\"></td></tr>\r\n";			
    		form += "      </form>\r\n";
			form += "      </table>\r\n";
		    form += "  </BODY>\r\n";
		    form += "</HTML>\r\n";
			
			response.getWriter().print(form);
			return true;
			
		} else {
			// The form has been filled in.  Read in the Results.
			cmsname = request.getParameter("LogonForm.cmsname");
			username = request.getParameter("LogonForm.username");
			password = request.getParameter("LogonForm.password");
			authType = request.getParameter("LogonForm.authType");

			for (int i = 0; i < num_reports_needed; i++) {
				reportname[i] = request.getParameter("LogonForm.reportnames[" + i + "]");
			}
			
			return false;
		}
	}
}
%>

