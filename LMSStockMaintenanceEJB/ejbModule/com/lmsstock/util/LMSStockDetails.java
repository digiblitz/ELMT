/*
 * MemberDetails.java
 *
 * Created on August 24, 2006, 12:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.lmsstock.util;
import java.io.Serializable;
import java.util.*;
/**
 *
 * @author harmohan
 */
public class LMSStockDetails implements Serializable {
    
	private String user_id = null;
	private String first_name = null;
	private String last_name = null;
	private String email_id = null;
	private String department_id = null;
	private String department_name = null;
	private String requested_item = null;
	private String ISBN_Number = null;
	private String author = null;
	private String title = null;
	private String year_of_publication = null;
	private String publisher = null;
	private String series = null;
	private String edition = null;
	private String no_of_volumes = null;
	private String format = null;
	private String paper_thickness = null;
	private String paper_size = null;
	private String paper_color = null;
	private String quantity = null;
	private String commands = null;
	
	
        
    /** Creates a new instance of MemberDetails */
    public LMSStockDetails() {
    }



	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}



	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}



	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}



	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}



	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}



	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}



	public String getRequested_item() {
		return requested_item;
	}
	public void setRequested_item(String requested_item) {
		this.requested_item = requested_item;
	}



	public String getISBN_Number() {
		return ISBN_Number;
	}
	public void setISBN_Number(String iSBN_Number) {
		ISBN_Number = iSBN_Number;
	}



	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}



	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}



	public String getYear_of_publication() {
		return year_of_publication;
	}
	public void setYear_of_publication(String year_of_publication) {
		this.year_of_publication = year_of_publication;
	}



	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}



	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}



	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}



	public String getNo_of_volumes() {
		return no_of_volumes;
	}
	public void setNo_of_volumes(String no_of_volumes) {
		this.no_of_volumes = no_of_volumes;
	}



	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}



	public String getPaper_thickness() {
		return paper_thickness;
	}
	public void setPaper_thickness(String paper_thickness) {
		this.paper_thickness = paper_thickness;
	}



	public String getPaper_size() {
		return paper_size;
	}
	public void setPaper_size(String paper_size) {
		this.paper_size = paper_size;
	}



	public String getPaper_color() {
		return paper_color;
	}
	public void setPaper_color(String paper_color) {
		this.paper_color = paper_color;
	}



	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}



	public String getCommands() {
		return commands;
	}
	public void setCommands(String commands) {
		this.commands = commands;
	}
   
    
    }

   
    


