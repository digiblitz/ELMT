/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hlcform.util;

/**
 *
 * @author Dhivya
 */
public class HLCUserSearchResultVO implements java.io.Serializable{
    
    /** Creates a new instance of UserSearchResultVO */
    public HLCUserSearchResultVO() {
    }
    private String firstName;
    private String lastName;
    private String loginName;
    private String password;
    private String memberId;
    private String emailId;
    private String userId;
    //membership_type_id, state,status_name
    private String membershipTypeName;
    private String statusName;
    private String state;
    private String roleName;
    private String roleId;
    
    
        
    

    public String getRoleId() {
        return roleId;
    }


    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getUserId() {
        return userId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMembershipTypeName() {
        return membershipTypeName;
    }

    public void setMembershipTypeName(String membershipTypeName) {
        this.membershipTypeName = membershipTypeName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getRoleName() {
        return roleName;
    }
  

}
