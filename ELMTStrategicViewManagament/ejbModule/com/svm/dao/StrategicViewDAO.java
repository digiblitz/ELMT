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
package com.svm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class StrategicViewDAO
{
  private static final String dbName = "java:/ELMTMSSQLDS";
  private Connection conn = null;

  public List getOrgLevelArtifacts()
  {
    List artifactList = new ArrayList();
    ResultSet rs = null;
    try {
      makeConnection();
      Statement statement = this.conn.createStatement();
      rs = statement.executeQuery("select * from tblMfgStrategic");

      while (rs.next())
      {
        artifactList.add(rs.getString(1) + "," + rs.getString(2));
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    } finally {
      releaseConnection();
    }
    return artifactList;
  }

  public List getBSCArtifacts()
  {
    List artifactList = new ArrayList();
    ResultSet rs = null;
    try {
      makeConnection();
      Statement statement = this.conn.createStatement();
      rs = statement.executeQuery("select * from tblMfgStrategicBSC");

      while (rs.next())
      {
        artifactList.add(rs.getString(1) + "," + rs.getString(2));
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    } finally {
      releaseConnection();
    }
    return artifactList;
  }

  public List getOrderArtifacts()
  {
    List artifactList = new ArrayList();
    ResultSet rs = null;
    try {
      makeConnection();
      Statement statement = this.conn.createStatement();
      rs = statement.executeQuery("select * from tblMfgStrategicOrder");

      while (rs.next())
      {
        artifactList.add(rs.getString(1) + "," + rs.getString(2));
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    } finally {
      releaseConnection();
    }
    return artifactList;
  }

  public List getOrderToCashArtifacts()
  {
    List artifactList = new ArrayList();
    ResultSet rs = null;
    try {
      makeConnection();
      Statement statement = this.conn.createStatement();
      rs = statement.executeQuery("select * from tblMfgStrategicOTC");

      while (rs.next())
      {
        artifactList.add(rs.getString(1) + "," + rs.getString(2));
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    } finally {
      releaseConnection();
    }
    return artifactList;
  }

  public List getOrderEntryArtifacts()
  {
    List artifactList = new ArrayList();
    ResultSet rs = null;
    try {
      makeConnection();
      Statement statement = this.conn.createStatement();
      rs = statement.executeQuery("select * from tblMfgStrategicOE");

      while (rs.next())
      {
        artifactList.add(rs.getString(1) + "," + rs.getString(2));
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    } finally {
      releaseConnection();
    }
    return artifactList;
  }

  public boolean addStrategicOrgLevelAtrifact(String artifactName, String artifactPath, String artifactType, String artifactDesc, String artifactLevel)
  {
    boolean result = true;
    makeConnection();
    try
    {
      String selectStatement = "insert into " + artifactLevel + " values (?,?,?,?)";
      PreparedStatement ps = this.conn.prepareStatement(selectStatement);
      ps.setString(1, artifactName);
      ps.setString(2, artifactPath);
      ps.setString(3, artifactType);
      ps.setString(4, artifactDesc);

      ps.executeUpdate();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    } finally {
      releaseConnection();
    }
    return false;
  }
  
  
  

  public Connection makeConnection()
  {
    try
    {
      InitialContext ic = new InitialContext();
      DataSource ds = (DataSource)ic.lookup("java:/ELMTMSSQLDS");
      this.conn = ds.getConnection();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return this.conn;
  }

  public void releaseConnection()
  {
    try
    {
      if (this.conn != null)
        this.conn.close();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
