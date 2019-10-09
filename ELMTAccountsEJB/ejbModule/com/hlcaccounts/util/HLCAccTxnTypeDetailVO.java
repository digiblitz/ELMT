/*
 * AccTxnTypeDetailVO.java
 *
 * Created on May 28, 2007, 6:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcaccounts.util;

/**
 *
 * @author karthikeyan
 */


                          
import java.io.Serializable;
import java.util.*;

public class HLCAccTxnTypeDetailVO implements Serializable{

	public HLCAccTxnTypeDetailVO()
	{}	
	    private String transaction_type_id;
	    private String transaction_name;
	    private String transaction_type;
	    private String account_no;
	    private String sub_account_no;
	    private String item_no;	    
	    private String class_name;	 
            private Date add_date;
            
		public String getAccount_no() {
			return account_no;
		}


		public void setAccount_no(String account_no) {
			this.account_no = account_no;
		}


		public String getClass_name() {
			return class_name;
		}


		public void setClass_name(String class_name) {
			this.class_name = class_name;
		}


		public String getItem_no() {
			return item_no;
		}


		public void setItem_no(String item_no) {
			this.item_no = item_no;
		}


		public String getSub_account_no() {
			return sub_account_no;
		}


		public void setSub_account_no(String sub_account_no) {
			this.sub_account_no = sub_account_no;
		}


		public String getTransaction_name() {
			return transaction_name;
		}


		public void setTransaction_name(String transaction_name) {
			this.transaction_name = transaction_name;
		}


		public String getTransaction_type() {
			return transaction_type;
		}


		public void setTransaction_type(String transaction_type) {
			this.transaction_type = transaction_type;
		}


		public String getTransaction_type_id() {
			return transaction_type_id;
		}


		public void setTransaction_type_id(String transaction_type_id) {
			this.transaction_type_id = transaction_type_id;
		}
		
		public String toString(){	        
	        
                    StringBuffer sb = new StringBuffer();
	        
	        sb.append("transaction_type_id :" + transaction_type_id + "\n");
	        sb.append("transaction_name :" + transaction_name + "\n");
	        sb.append("transaction_type :" + transaction_type + "\n");
	        
	        sb.append("account_no :" + account_no + "\n");
	        sb.append("sub_account_no :" + sub_account_no + "\n");
	        sb.append("item_no :" + item_no + "\n");
	        sb.append("class_name :" + class_name + "\n");
	        sb.append("add_date :" + add_date + "\n");
                
	        return sb.toString();
	    }	

    public Date getAdd_date() {
        return add_date;
    }

    public void setAdd_date(Date add_date) {
        this.add_date = add_date;
    }
		
}

