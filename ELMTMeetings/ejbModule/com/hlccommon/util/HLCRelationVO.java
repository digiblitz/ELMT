/*
 * RelationVO.java
 *
 * Created on April 19, 2007, 1:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;
import java.io.*;

/**
 *
 * @author Suresh
 */
public class HLCRelationVO implements Serializable{
    
    /** Creates a new instance of RelationVO */
    public HLCRelationVO() {
    }
    
    private String horseMemberId;
    private String horseName;
    private String firstName;
    private String lastName;
    private String memberId;
    private String userId;

    public void setHorseMemberId(String horseMemberId) {
        this.horseMemberId = horseMemberId;
    }

    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHorseMemberId() {
        return horseMemberId;
    }

    public String getHorseName() {
        return horseName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getUserId() {
        return userId;
    }
    
    
    
}
