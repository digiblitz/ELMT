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
// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 20-03-2012 10:35:39
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SatlujRemote.java

package com.svm.session;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBObject;

public interface SatlujRemote
    extends EJBObject
{

    public abstract List displayOrgLevelArtifacts()
        throws RemoteException;

    public abstract List displayBSCArtifacts()
        throws RemoteException;

    public abstract List displayOrderArtifacts()
        throws RemoteException;

    public abstract boolean addStrategicOrgLevelAtrifact(String s, String s1, String s2, String s3, String s4)
        throws RemoteException;

    public abstract List displayOrderToCashArtifacts()
        throws RemoteException;

    public abstract List displayOrderEntryArtifacts()throws RemoteException;

}
