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
package com.um.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO
{
  private static final String dbName = "java:/EIDMFGMSSQLDS";
  private Connection conn = null;

  public String getUserInfo(String userID, String password)
  {
    String sqlQuery = "select * from tblMfgUserMaster where user_name=? and password=?";
    ResultSet rs = null;
    String result = null;
    try {
      makeConnection();
      PreparedStatement ps = this.conn.prepareStatement(sqlQuery);
      ps.setString(1, userID);
      ps.setString(2, password);
      rs = ps.executeQuery();
      if (rs.next())
        result = "valid";
      else
        result = "invalidUserIdPwd";
    }
    catch (Exception exception) {
      exception.printStackTrace();
    } finally {
      releaseConnection();
    }
    return result;
  }

  public Connection makeConnection()
  {
    try
    {
      InitialContext ic = new InitialContext();
      DataSource ds = (DataSource)ic.lookup("java:/EIDMFGMSSQLDS");
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
    catch (Exception exception)
    {
      exception.printStackTrace();
    }
  }
}
