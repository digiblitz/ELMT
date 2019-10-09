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
/*************************** class Datasource **************************
 * 
 * Datasource classes are responsible for assembling the connection 
 * information only the connection attributes common to all connection 
 * types are in this class.  The attributes specific to a particular 
 * connection type will be in the subclass responsible for that type.
 *   
 ***********************************************************************/
private abstract class Datasource 
{
	protected ConnectionInfo oConnectionInfo;
	protected String tableNameQualifier = ""; // By default the Table name will not be qualified.
	
	/************************************************************************
	 * 
	 * Return the modifed connection info object pointing to the new 
	 * datasource.
	 *               
	 ***********************************************************************/	
	public ConnectionInfo getConnInfo() { return oConnectionInfo; }

	/************************************************************************
	 * 
	 * Set the tableNameQualifier.  If this is not set, no qualifier will
	 * be used and a relative table name will be used as the fully qualified
	 * table name.
	 *               
	 ***********************************************************************/	
	public void setTableNameQualifier(String tableNameQualifier) 
	{ this.tableNameQualifier = tableNameQualifier; }
	
	/************************************************************************
	 * 
	 * Return the TableNameQualifier.  If is has not been set, it defaults to
	 * an empty string.
	 *               
	 ***********************************************************************/	
	public String getTableNameQualifier() { return tableNameQualifier; }
}

%>


