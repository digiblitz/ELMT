/*
 * HorseServiceTypeMaster.java
 *
 * Created on August 28, 2006, 6:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmrm.util;

/**
 *
 * @author harmohan
 */
public class HLCHorseServiceTypeMaster implements java.io.Serializable {

        private String horseServiceTypeId;
        private String horseServiceTypeName;
        private String horseServiceTypeAmount;

        public HLCHorseServiceTypeMaster() { }
        public HLCHorseServiceTypeMaster(String horseServiceTypeId,String horseServiceTypeName,String horseServiceTypeAmount) {

                 this.horseServiceTypeId  = horseServiceTypeId ;
                 this.horseServiceTypeName = horseServiceTypeName;
                 this.horseServiceTypeAmount = horseServiceTypeAmount;
        }

        //getter
        public String getHorseServiceTypeId(){
                return horseServiceTypeId;
        }
        public String getHorseServiceTypeName(){
                return horseServiceTypeName;
        }
        public String getHorseServiceTypeAmount(){
                return horseServiceTypeAmount;
        }

        //setter
        public void setHorseServiceTypeId(String horseServiceTypeId) {
                this.horseServiceTypeId  = horseServiceTypeId ;
        }
        public void setHorseServiceTypeName(String horseServiceTypeName) {
                this.horseServiceTypeName = horseServiceTypeName;
        }
        public void setHorseServiceTypeAmount(String horseServiceTypeAmount ) {
                 this.horseServiceTypeAmount = horseServiceTypeAmount;
        }
}