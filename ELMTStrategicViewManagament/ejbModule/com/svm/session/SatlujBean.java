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
// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 20-03-2012 10:35:26
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SatlujBean.java

package com.svm.session;


import com.svm.dao.StrategicViewDAO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class SatlujBean
    implements SessionBean
{

    public SatlujBean()
    {
    }

    public void setSessionContext(SessionContext aContext)
    {
        context = aContext;
    }

    public void ejbActivate()
    {
    }

    public void ejbPassivate()
    {
    }

    public void ejbRemove()
    {
    }

    public void ejbCreate()
    {
    }

    public List displayOrgLevelArtifacts()
    {
        StrategicViewDAO svdao = new StrategicViewDAO();
        return svdao.getOrgLevelArtifacts();
    }

    public List displayBSCArtifacts()
    {
        StrategicViewDAO svdao = new StrategicViewDAO();
        return svdao.getBSCArtifacts();
    }

    public List displayOrderArtifacts()
    {
        StrategicViewDAO svdao = new StrategicViewDAO();
        return svdao.getOrderArtifacts();
    }

    public boolean addStrategicOrgLevelAtrifact(String artifactName, String artifactPath, String artifactType, String artifactDesc, String artifactLevel)
    {
        StrategicViewDAO svdao = new StrategicViewDAO();
        svdao.addStrategicOrgLevelAtrifact(artifactName, artifactPath, artifactType, artifactDesc, artifactLevel);
        return true;
    }

    public List displayOrderToCashArtifacts()
    {
        StrategicViewDAO svdao = new StrategicViewDAO();
        return svdao.getOrderToCashArtifacts();
    }

    public List displayOrderEntryArtifacts()
    {
        StrategicViewDAO svdao = new StrategicViewDAO();
        return svdao.getOrderEntryArtifacts();
    }
    

    private SessionContext context;
    


}
