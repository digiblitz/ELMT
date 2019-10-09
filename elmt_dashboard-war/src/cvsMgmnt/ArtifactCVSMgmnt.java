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
// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 19-03-2012 10:33:26
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ArtifactCVSMgmnt.java

package cvsMgmnt;

import java.io.File;
import java.io.PrintStream;
import java.util.*;
import org.apache.tools.ant.*;

public class ArtifactCVSMgmnt
{

    public ArtifactCVSMgmnt()
    {
        project = null;
    }

    public void executeTarget(String targetName, String userName, String password, String artifactName)
        throws Exception
    {
        System.out.println((new StringBuilder()).append("target name:").append(targetName).toString());
        System.out.println((new StringBuilder()).append("userName:").append(userName).toString());
        System.out.println((new StringBuilder()).append("password:").append(password).toString());
        System.out.println((new StringBuilder()).append("artifactName:").append(artifactName).toString());
        System.out.println("Starting Test Runner ....");
        System.out.println("Checking ANT_HOME ...");
        if(ANT_HOME == null)
            throw new Exception("ANT_HOME is not set. Please set environment variable ANT_HOME");
        ArtifactCVSMgmnt testRunner = new ArtifactCVSMgmnt();
        System.out.println("Initializing Ant Project ...");
        testRunner.init("C:\\eBlitz\\EID\\apache-ant-1.7.1\\artBuildFile\\build.xml", CURRENT_DIR);
        Map propMap = new HashMap();
        propMap.put("userName", userName);
        propMap.put("password", password);
        propMap.put("artifactName", artifactName);
        testRunner.setProperties(propMap, true);
        System.out.println(testRunner.project.getProperties());
        if(targetName.equals("checkOut"))
            testRunner.runTarget("cvs-checkout");
        else
        if(targetName.equals("commit"))
            testRunner.runTarget("commit");
    }

    private void init(String buildFile, String baseDir)
        throws Exception
    {
        System.out.println(buildFile);
        System.out.println(baseDir);
        project = new Project();
        project.init();
        if(buildFile == null)
            buildFile = "C:\\eBlitz\\EID\\apache-ant-1.7.1\\artBuildFile\\build.xml";
        if(baseDir == null)
            baseDir = ".";
        project.setBasedir(baseDir);
        ProjectHelper helper = ProjectHelper.getProjectHelper();
        project.setProjectReference(helper);
        helper.parse(project, new File(buildFile));
        DefaultLogger logger = new DefaultLogger();
        logger.setErrorPrintStream(System.err);
        logger.setOutputPrintStream(System.out);
        project.addBuildListener(logger);
    }

    private void setProperties(Map propertyMap, boolean overridable)
        throws Exception
    {
        if(project == null)
            throw new Exception("Properties can not be set. Please initialize the project first.");
        if(propertyMap == null)
            throw new Exception("Properties map provided was null");
        Set propKeys = propertyMap.keySet();
        Iterator iterator = propKeys.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            String propertyKey = (String)iterator.next();
            String propertyValue = (String)propertyMap.get(propertyKey);
            if(propertyValue != null)
                if(overridable)
                    project.setProperty(propertyKey, propertyValue);
                else
                    project.setUserProperty(propertyKey, propertyValue);
        } while(true);
        project.setSystemProperties();
    }

    private void runTarget(String targetName)
        throws Exception
    {
        if(project == null)
            throw new Exception("Properties can not be set. Please initialize the project first.");
        if(targetName == null)
            targetName = project.getDefaultTarget();
        if(targetName == null)
        {
            throw new Exception((new StringBuilder()).append("Target Not found :").append(targetName).toString());
        } else
        {
            project.executeTarget(targetName);
            return;
        }
    }

    private static final String BUILD_FILE = "C:\\eBlitz\\EID\\apache-ant-1.7.1\\artBuildFile\\build.xml";
    private static final String CURRENT_DIR = System.getProperty("user.dir");
    private static final String ANT_HOME = System.getenv("ANT_HOME");
    private Project project;

}
