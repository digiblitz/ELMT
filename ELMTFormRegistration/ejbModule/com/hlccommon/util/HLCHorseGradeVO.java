/*
 * HorseGradeVO.java
 *
 * Created on March 30, 2007, 1:30 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;
import java.util.Date;

/**
 *
 * @author Punitha
 */
public class HLCHorseGradeVO {
        
    /** Creates a new instance of HorseGradeVO */
    public HLCHorseGradeVO() {
    }
        private String horseMemberId;
        private float careerGradePoints;
        private float currentGradePoints;
        private float careerAwardPoints;;
        private float currentAwardPoints;
        private String horseGradeLevelId;
        private float assignedPoints;;
        private Date grade1Date;
        private Date grade2Date;
        private Date grade3Date;
        private Date addDate;
        private boolean activeStatus;
        
 /*
  * Create Getter methods
  *
  */       

    public String getHorseMemberId() {
        return horseMemberId;
    }

    public String getHorseGradeLevelId() {
        return horseGradeLevelId;
    }

     public Date getAddDate() {
        return addDate;
    }

    public float getAssignedPoints() {
        return assignedPoints;
    }

    public float getCareerAwardPoints() {
        return careerAwardPoints;
    }

    public float getCareerGradePoints() {
        return careerGradePoints;
    }

    public float getCurrentAwardPoints() {
        return currentAwardPoints;
    }

    public float getCurrentGradePoints() {
        return currentGradePoints;
    }

    public Date getGrade1Date() {
        return grade1Date;
    }

    public Date getGrade2Date() {
        return grade2Date;
    }

    public Date getGrade3Date() {
        return grade3Date;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

   
/*
 * Create setter methods
 */
    

 
     

    public void setHorseGradeLevelId(String horseGradeLevelId) {
        this.horseGradeLevelId = horseGradeLevelId;
    }

    public void setHorseMemberId(String horseMemberId) {
        this.horseMemberId = horseMemberId;
    }
   
  

    public void setGrade1Date(Date grade1Date) {
        this.grade1Date = grade1Date;
    }

    public void setGrade2Date(Date grade2Date) {
        this.grade2Date = grade2Date;
    }

    public void setGrade3Date(Date grade3Date) {
        this.grade3Date = grade3Date;
    }

    public void setAssignedPoints(float assignedPoints) {
        this.assignedPoints = assignedPoints;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
 
    public void setCareerAwardPoints(float careerAwardPoints) {
        this.careerAwardPoints = careerAwardPoints;
    }

    public void setCareerGradePoints(float careerGradePoints) {
        this.careerGradePoints = careerGradePoints;
    }

    public void setCurrentAwardPoints(float currentAwardPoints) {
        this.currentAwardPoints = currentAwardPoints;
    }

    public void setCurrentGradePoints(float currentGradePoints) {
        this.currentGradePoints = currentGradePoints;
    }

   

    

   
}
