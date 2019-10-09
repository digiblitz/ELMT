/*
 * HorseVO.java
 *
 * Created on November 12, 2007, 5:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcutil;
import java.util.Date;
import java.util.ArrayList;
/**
 *
 * @author Vidhya
 */
public class HLCHorseVO {
    
    /** Creates a new instance of HorseVO */
    public HLCHorseVO() {
    }
        private String horseId;
        private String horseMemberId;
        private String horseName;
        private String color;
        private String gender;
        private String breed;
       

    public String getBreed() {
        return breed;
    }

    public String getColor() {
        return color;
    }

    public String getGender() {
        return gender;
    }

    public String getHorseId() {
        return horseId;
    }

    public String getHorseMemberId() {
        return horseMemberId;
    }

    public String getHorseName() {
        return horseName;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHorseId(String horseId) {
        this.horseId = horseId;
    }

    public void setHorseMemberId(String horseMemberId) {
        this.horseMemberId = horseMemberId;
    }

    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }
}
