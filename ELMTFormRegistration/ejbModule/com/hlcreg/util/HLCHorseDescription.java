/*
 * HorseDescription.java
 *
 * Created on August 21, 2006, 11:48 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcreg.util;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author harmohan
 */
public class HLCHorseDescription implements Serializable {
    private String horseMemberId;
    private String horseMembershipTypeId;
    private String color;
    private String gender;
    private String height;
    private String yearFoaled;
    private String breed;
    private String country;
    private String sire;
    private String sireBreed;
    private String dam;
    private String damBreed;
    
    //suresh here
    private String breed2;
    private String sireBreed2;
    private String damBreed2;
    private String notes;
    private String splNotes;
    
    //===========
    private String importedFrom;
    private Date importDate;
    private String foreignGrade;
    private double foreignPoints;
    private String assignedGrade;
    private double assignedPoints;
    
    
    
    /** Creates a new instance of HorseDescription */
    public HLCHorseDescription() {
    }
    
    public HLCHorseDescription(String horseMemberId,String horseMembershipTypeId, String color, String gender, String height, String yearFoaled, String breed, 
            String country, String sire, String sireBreed, String dam, String damBreed, String breed2, String sireBreed2, String damBreed2,
            String importedFrom, Date importDate, String foreignGrade, double foreignPoints, String assignedGrade, 
            double assigned_points, String notes, String splNotes){
        
        this.horseMemberId = horseMemberId;
        this.horseMembershipTypeId = horseMembershipTypeId;
        this.color = color;
        this.gender = gender;
        this.height = height;
        this.yearFoaled = yearFoaled;
        this.breed = breed;
        this.country = country;
        this.sire = sire;
        this.sireBreed = sireBreed;
        this.dam = dam;
        this.damBreed = damBreed;
        this.importedFrom = importedFrom;
        this.importDate = importDate;
        this.foreignGrade = foreignGrade;
        this.foreignPoints = foreignPoints;
        this.assignedGrade = assignedGrade;
        this.assignedPoints = assignedPoints;
        
        this.breed2 = breed2;
        this.sireBreed2 = sireBreed2;
        this.damBreed2 = damBreed2;
        this.notes = notes;
        this.splNotes = splNotes;
    }
    
    // getters methods
    public String getHorseMembershipTypeId() {
        return horseMembershipTypeId;
    }
        public String getHorseMemberId() {
           return horseMemberId;
        }
         public String getColor() {
           return color;
        }
          public String getGender() {
           return gender;
        }
           public String getHeight() {
           return height;
        }
            public String getYearFoaled() {
           return yearFoaled;
        }
         public String getBreed() {
           return breed;
        }
         public String getCountry() {
           return country;
        }
          public String getSire() {
           return sire;
        }
           public String getSireBreed() {
           return sireBreed;
        }
            public String getDam() {
           return dam;
        }
          public String getDamBreed() {
           return damBreed;
        }
         public String getImportedFrom() {
           return importedFrom;
        }
          public Date getImportDate() {
           return importDate;
        }
           public String getForeignGrade() {
           return foreignGrade;
        }
            public double getForeignPoints() {
           return foreignPoints;
        }
         public String getAssignedGrade() {
           return assignedGrade;
        }
         public double getAssignedPoints() {
           return assignedPoints;
        }
         
         
          // setters methods
        public void setHorseMembershipTypeId(String horseMembershipTypeId) {
           this.horseMembershipTypeId = horseMembershipTypeId;
        }
        public void setHorseMemberId(String horseMemberId) {
           this.horseMemberId = horseMemberId;
        }
        public void setColor(String color) {
           this.color = color;
        }
        public void setGender(String gender) {
           this.gender = gender;
        }
        public void setHeight(String height) {
           this.height = height;
        }
        public void setYearFoaled(String yearFoaled) {
           this.yearFoaled = yearFoaled;
        }
        public void setBreed(String breed) {
           this.breed = breed;
        }
        public void setCountry(String country) {
           this.country = country;
        }
        public void setSire(String sire) {
           this.sire = sire;
        }
        public void setSireBreed(String sireBreed) {
           this.sireBreed = sireBreed;
        }
        public void setDam(String dam) {
           this.dam = dam;
        }
        public void setDamBreed(String damBreed) {
           this.damBreed = damBreed;
        }
        public void setImportedFrom(String importedFrom) {
           this.importedFrom = importedFrom;
        }
        public void setImportDate(Date importDate) {
           this.importDate = importDate;
        }
        public void setForeignGrade(String foreignGrade) {
           this.foreignGrade = foreignGrade;
        }
        public void setForeignPoints(double foreignPoints) {
          this.foreignPoints = foreignPoints;
        }
        public void setAssignedGrade(String assignedGrade) {
           this.assignedGrade = assignedGrade;
        }
        public void setAssignedPoints(double assignedPoints) {
           this.assignedPoints = assignedPoints;
        }
        
         
    public void reset() {
    	horseMemberId=null;
	horseMembershipTypeId=null;
	color=null;
	gender=null;
	height=null;
	yearFoaled=null;
        breed=null;
	country=null;
        sire=null;
	sireBreed=null;
	dam=null;
	damBreed=null;
	importedFrom=null;
	importDate=null;
	foreignGrade=null;
	assignedPoints=0;
	foreignPoints=0;
	assignedGrade=null;
    }
        
        
   public String toString(){
     
    StringBuffer buffer = new StringBuffer()
   	.append(" horseMemberId :"+ horseMemberId+"\n")
	.append(" horseMembershipTypeId :"+ horseMembershipTypeId+"\n")
	.append(" color :"+ color+"\n")
	.append(" gender :"+gender+"\n")
	.append(" height :"+height+"\n")
	.append(" yearFoaled :"+yearFoaled+"\t\t")
        .append(" breed :"+breed+"\n")
	.append(" country :"+country+"\n")
        .append(" sire :"+sire+"\n")
        .append(" sireBreed :"+sireBreed+"\n")
	.append(" dam :"+dam+"\n")
	.append(" damBreed :"+damBreed+"\t\t")
        .append(" importedFrom :"+importedFrom+"\n")
	.append(" importDate :"+importDate+"\n")
        .append(" foreignGrade :"+foreignGrade+"\n")
        .append(" assignedPoints :"+assignedPoints+"\n")
	.append(" foreignPoints :"+foreignPoints+"\n")
        .append(" assignedGrade :"+assignedGrade+"\n");
	
    return buffer.toString();
  }

    public void setBreed2(String breed2) {
        this.breed2 = breed2;
    }

    public void setSireBreed2(String sireBreed2) {
        this.sireBreed2 = sireBreed2;
    }

    public void setDamBreed2(String damBreed2) {
        this.damBreed2 = damBreed2;
    }

    public String getBreed2() {
        return breed2;
    }

    public String getSireBreed2() {
        return sireBreed2;
    }

    public String getDamBreed2() {
        return damBreed2;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setSplNotes(String splNotes) {
        this.splNotes = splNotes;
    }

    public String getNotes() {
        return notes;
    }

    public String getSplNotes() {
        return splNotes;
    }
        
}

