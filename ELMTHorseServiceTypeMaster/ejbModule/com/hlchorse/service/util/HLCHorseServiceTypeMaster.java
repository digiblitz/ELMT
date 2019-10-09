/*
 * HorseServiceTypeMaster.java
 *
 * Created on August 28, 2006, 6:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlchorse.service.util;

/**
 *
 * @author harmohan
 */
public class HLCHorseServiceTypeMaster implements java.io.Serializable {

        private String horseServiceTypeId;
        private String horseServiceTypeName;
        private String horseServiceTypeAmount;
        private String transaction_type_id;
        private String userTypeId;
        private int activeStatus;
        
        public HLCHorseServiceTypeMaster() { }
        public HLCHorseServiceTypeMaster(String horseServiceTypeId,String horseServiceTypeName,String horseServiceTypeAmount,String transaction_type_id) {

                 this.horseServiceTypeId  = horseServiceTypeId ;
                 this.horseServiceTypeName = horseServiceTypeName;
                 this.horseServiceTypeAmount = horseServiceTypeAmount;
                 this.transaction_type_id = transaction_type_id;
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

    public String getTransaction_type_id() {
        return transaction_type_id;
    }

    public void setTransaction_type_id(String transaction_type_id) {
        this.transaction_type_id = transaction_type_id;
    }
    /***********************Block Changed By Nisha Rao*********************************/
    public void setUserTypeId(String user_type_id){
        this.userTypeId=user_type_id;
    }
    public String getUserTypeId(){
        return userTypeId;
    }
    public void setActiveStatus(int active_status){
        this.activeStatus=active_status;
    }
    public int getActiveStatus(){
        return activeStatus;
    }
    /***********************Block Changed By Nisha Rao*********************************/


}