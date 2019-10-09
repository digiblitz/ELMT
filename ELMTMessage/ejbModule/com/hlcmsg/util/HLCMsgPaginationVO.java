/*
 * MsgPaginationVO.java
 *
 * Created on October 1, 2006, 8:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmsg.util;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author harmohan
 */
public class HLCMsgPaginationVO implements Serializable{
    private String totalRecords;
    private List results;
    /** Creates a new instance of MsgPaginationVO */
    public HLCMsgPaginationVO() {
    }
    
    public List getResults() { return results; }
    public String getTotalRecords() {return totalRecords; }

    public void setResults(List results) {this.results = results; }
    public void setTotalRecords(String totalRecords) {this.totalRecords = totalRecords; }
}
