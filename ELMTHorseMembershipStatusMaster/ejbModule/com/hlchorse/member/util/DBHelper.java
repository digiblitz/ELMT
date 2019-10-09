/*
 * DBHelper.java
 *
 * Created on August 28, 2006, 6:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlchorse.member.util;

/**
 *
 * @author harmohan
 */
public final class DBHelper {
    public static final String USEA_MEMBER_STATUS_MASTER = "tblMembershipStatusMaster";
    
    public static final java.sql.Date toSQLDate(java.util.Date inDate) {
        return new java.sql.Date(inDate.getTime());
    }
    
    /** Creates a new instance of DBHelper */
    public DBHelper() {
    }
    
}
