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
/**************************** class Generic ***************************
 * 
 * Perform all generic Crystal Enterprise functionality including:
 * - Log on to Crystal Enterprise
 * - Create Report Client Document
 *   
 ***********************************************************************/
private class Generic
{
	private String cms;
	private String username;
	private String password;
	private String authType;
	private IEnterpriseSession oEnterpriseSession;
	private IInfoStore oInfoStore;
	private IInfoObjects oInfoObjects;
	private ReportClientDocument oReportClientDocument;

	public final int LAST_INSTANCE = -1;
	
	/************************************************************************
	 * 
	 * Constructor - Take in and save all the information needed to logon
	 *               to Enterprise.
	 *               
	 ***********************************************************************/	
	public Generic(String cmsName, String user, String pwd, String auth)
	{
		cms = cmsName;
		username = user;
		password = pwd;
		authType = auth;
		oEnterpriseSession = null;
	}
	
	/***********************************************************************
	 * 
	 * Logon Using information saved by the contructor.
	 * 
	 **********************************************************************/
	public IEnterpriseSession logon() throws SDKException { 
		if (oEnterpriseSession == null) {
			oEnterpriseSession = CrystalEnterprise.getSessionMgr().logon(username, password, cms, authType);
		}
		return oEnterpriseSession;
	}

	/************************************************************************
	 * 
	 * Get the ReportClientDocument object for the report template of the
	 * reportName specified.
	 * 
	 ***********************************************************************/
	public ReportClientDocument getClientDoc(String reportName) throws SDKException
	{
		IReportAppFactory oReportAppFactory;
		IEnterpriseSession oEnterpriseSession = this.logon();
		oInfoStore = (IInfoStore)oEnterpriseSession.getService("InfoStore");
		oInfoObjects = oInfoStore.query("Select * from CI_INFOOBJECTS where SI_PROGID = 'CrystalEnterprise.Report' and SI_INSTANCE = 0 and SI_NAME = '" + reportName + "'");
		oReportAppFactory = (IReportAppFactory)oEnterpriseSession.getService("", "RASReportService");
		oReportClientDocument = oReportAppFactory.openDocument((IInfoObject)oInfoObjects.get(0), 0, java.util.Locale.ENGLISH);
		return oReportClientDocument;	
	} 
	

	/***********************************************************************
	 * 
	 * Get the ReportClientDocument object for the report template of the
	 * reportName specified.
	 * 
	 **********************************************************************/
	public ReportClientDocument getClientDoc(String reportName, int instance_number) throws SDKException
	{
		IReportAppFactory oReportAppFactory;
		IEnterpriseSession oEnterpriseSession = this.logon();
		oInfoStore = (IInfoStore)oEnterpriseSession.getService("InfoStore");
		oInfoObjects = oInfoStore.query("Select * from CI_INFOOBJECTS where SI_PROGID = 'CrystalEnterprise.Report' and SI_INSTANCE = 1 and SI_NAME = '" + reportName + "'");
		oReportAppFactory = (IReportAppFactory)oEnterpriseSession.getService("", "RASReportService");

		if (instance_number == this.LAST_INSTANCE) {
			oReportClientDocument = oReportAppFactory.openDocument((IInfoObject)oInfoObjects.get(oInfoObjects.size()-1), 0, java.util.Locale.ENGLISH);
		} else {
			oReportClientDocument = oReportAppFactory.openDocument((IInfoObject)oInfoObjects.get(instance_number), 0, java.util.Locale.ENGLISH);
		}

		return oReportClientDocument;	
	} 
}
%>
