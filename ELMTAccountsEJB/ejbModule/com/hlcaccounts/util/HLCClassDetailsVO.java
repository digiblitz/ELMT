/*
 * ClassDetailsVO.java
 *
 * Created on May 28, 2007, 4:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcaccounts.util;

/**
 *
 * @author karthikeyan
 */


import java.util.*;
import java.io.Serializable;


public class HLCClassDetailsVO implements Serializable{

	public HLCClassDetailsVO()
	{}	
	    private String class_id;
	    private String class_name;
	    private String parent_class_name;	    	    
		
		public String getClass_id() {
			return class_id;
		}

		public void setClass_id(String class_id) {
			this.class_id = class_id;
		}

		public String getClass_name() {
			return class_name;
		}

		public void setClass_name(String class_name) {
			this.class_name = class_name;
		}

		public String getParent_class_name() {
			return parent_class_name;
		}

		public void setParent_class_name(String parent_class_name) {
			this.parent_class_name = parent_class_name;
		}
                
                public String toString(){
	        StringBuffer sb = new StringBuffer();
	        sb.append("class_id :" + class_id + "\n");
	        sb.append("class_name :" + class_name + "\n");
	        sb.append("parent_class_name :" + parent_class_name + "\n");
	        	                
	        return sb.toString();
	    }
		
}

