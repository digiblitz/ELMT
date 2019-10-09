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
/************************* class ODBCDatasource ************************
 *
 * Contains the ODBC specific information and is responsible for 
 * preparing a valid ODBC ConnectionInfo object.
 *   
 ***********************************************************************/
private class ODBCDatasource extends Datasource
{

	private final String database_dll = "crdb_odbc.dll";

	/************************************************************************
	 * 
	 * Constructor - Prepare the ConnectionInfo object with the information
	 *               provided and store it in this.oConnectionInfo
	 *               
	 ***********************************************************************/
	ODBCDatasource (String new_username, String new_password, String new_dsn, String new_database)
	{
	
		this.oConnectionInfo = new ConnectionInfo(); // Make a copy, don't change the original.

		PropertyBag oPropertyBag1 = this.oConnectionInfo.getAttributes();

		// Set new table logon properties attributes
		PropertyBag oPropertyBag2 = new PropertyBag();
		oPropertyBag2.put("Database", new_database);
		oPropertyBag2.put("DSN", new_dsn);
		oPropertyBag2.put("Database DLL", database_dll);
		
		// Set the connection info objects members
		// 1. Pass the Logon Properties to the main PropertyBag
		oPropertyBag1.put("QE_LogonProperties", oPropertyBag2);

		this.oConnectionInfo.setAttributes(oPropertyBag1);
		this.oConnectionInfo.setUserName(new_username);
		this.oConnectionInfo.setPassword(new_password);
		// The Kind of connectionInfos is CRQE (Crystal Reports Query Engine).
		this.oConnectionInfo.setKind(ConnectionInfoKind.CRQE);

	}

}

%>
