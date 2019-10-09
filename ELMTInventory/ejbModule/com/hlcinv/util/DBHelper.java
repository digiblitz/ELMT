/*
 * DBHelper.java
 *
 * Created on September 29, 2006, 4:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcinv.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author harmohan
 */
public class DBHelper {
    public static final String USEA_INV_INVENTORY_MASTER = "tblInventoryMaster"; 
   
    /** Creates a new instance of DBHelper */
    public DBHelper() {
    }
    
    public static final int toInt(String data) {
        return Integer.parseInt(data);
    }
    
    public static final double toDouble(String data) {
        return Double.parseDouble(data);
    }
    public static final Date stringToDate(String date) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date dt =(Date) formatter.parse(date); 
        return dt;    
    }
    
    public static final java.sql.Date stringToSQLDate(String date) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date dt =(Date) formatter.parse(date); 
        return new java.sql.Date(dt.getTime());    
    }
    
    public static final java.sql.Date toSQLDate(java.util.Date inDate) {
        return new java.sql.Date(inDate.getTime());
    }
    
}
