/*
 * AnnualMeetingStore.java
 *
 * Created on October 31, 2006, 5:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmee.ann;

import com.hlcmeeting.util.HLCAnnualUserVO;
import java.lang.*;
import java.util.Hashtable;
import java.util.Enumeration;

/**
 *
 * @author K.Suresh
 */
public class HLCAnnualMeetingStore {
    protected Hashtable viewers = new Hashtable();
    
    /** Creates a new instance of AnnualMeetingStore */
    public HLCAnnualMeetingStore() {
    }
    
    
    public void addParticipant(String userId, HLCAnnualUserVO tempObjAnnualUser){
        if(!viewers.containsKey(userId)){
            viewers.put(userId,tempObjAnnualUser);
        }
    }

    public void removeParticipant(String userId){
        if(viewers.containsKey(userId)){
            viewers.remove(userId); 
        }
    }
    
    public void updateParticipant(String userId, HLCAnnualUserVO objActivityVO) { 
         if(viewers.containsKey(userId)){
            HLCAnnualUserVO tempObjAnnualUser = (HLCAnnualUserVO)viewers.get(userId);
           // viewers.put(userId, tempObjAnnualUser);
         }
    }
  
    public Enumeration getEnumeration() {
        return viewers.elements();
    }
    
    public int getSize(){
        return viewers.size();
    }
    
    public double getTotalCost(){
        Enumeration e = viewers.elements();
        HLCAnnualUserVO objAnnualUser = new HLCAnnualUserVO();
        double totalCost = 0.00d;

        while(e.hasMoreElements()) {
            objAnnualUser = (HLCAnnualUserVO) e.nextElement();
            //totalCost += amount;
        }
        return totalCost;
    }
}
