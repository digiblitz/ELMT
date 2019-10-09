/*
 * CRUDMain.java
 *
 * Created on December 20, 2006, 3:38 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmro.org;
import com.hlcmro.util.Debug;
import javax.naming.Context;
import java.util.Properties;
import java.util.*;
/**
 *
 * @author harmohan
 */
public class HLCCRUDMain {
    
    /** Creates a new instance of CRUDMain */
    public HLCCRUDMain() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       try {
            Context jndiContext = getInitialContext();
            Object obj=jndiContext.lookup("ejb/HLCVaigaiSessionBean");
            HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCVaigaiSessionRemoteHome.class);
            HLCVaigaiSessionRemote remote = home.create();
            
/*=========================Horse Dressage Advance level=====================================*/            
           /* String eventName = "2006 USEF Low level test A";
            boolean bol = remote.insertHorseDressageTrial(eventName);
            if (bol == true)
                Debug.print(" Succeffully Inserted : "+bol);
            
            String eventId = "ac0bd338-060a-4088-bd9f-80de309a09e9";
            bol = remote.deleteHorseDressageTrial(eventId);
            if (bol == true)
                Debug.print(" Succeffully Deleted : "+bol);
            
            eventId = "f5f05e96-1ff1-4ec1-8587-482c4e4d1b38";
            eventName = "2006 USEF High level test F";
            bol = remote.updateHorseDressageTrial(eventName,eventId);
            if (bol == true)
                Debug.print(" Succeffully Updated : "+bol);
            
            Vector vObj = (Vector)remote.displayHorseDressageTrial(eventId);
            Enumeration it = vObj.elements();
            while (it.hasMoreElements()) {
                String[] s = (String[]) it.nextElement();
                Debug.print(" List Content...\n");
                System.out.println("=========================");
                for(int i=0;i<s.length;i++)
                System.out.println(" " + s[i]);
                System.out.println("=========================");

            }
            
            vObj = (Vector)remote.displayAllHorseDressageTrial();
            it = vObj.elements();
            while (it.hasMoreElements()) {
                String[] s = (String[]) it.nextElement();
                Debug.print(" List Content...\n");
                System.out.println("=========================");
                for(int i=0;i<s.length;i++)
                System.out.println(" " + s[i]);
                System.out.println("=========================");

            }
            
            eventId = "f5f05e96-1ff1-4ec1-8587-482c4e4d1b38";
            bol = remote.activateHorseDressageTrial(eventId);
            if (bol == true)
                Debug.print(" Succeffully Activated : "+bol);
            */
            
/*====================================Arena Size =====================================*/
            
          /*  String arenaName = "Standard";
            boolean bol = remote.insertArenaSize(arenaName);
            if (bol == true)
                Debug.print(" Succeffully Inserted : "+bol);
            
            String arenaId = "95f78a9d-f4b6-444f-9138-d5e63c5ca332";
            bol = remote.deleteArenaSize(arenaId);
            if (bol == true)
                Debug.print(" Succeffully Deleted : "+bol);
            
            arenaId = "02461d66-f919-4a42-8a7c-431b4d3ddc9e";
            arenaName = "Small";
            bol = remote.updateArenaSize(arenaName,arenaId);
            if (bol == true)
                Debug.print(" Succeffully Updated : "+bol);
            
            Vector vObj = (Vector)remote.displayArenaSize(arenaId);
            Enumeration it = vObj.elements();
            while (it.hasMoreElements()) {
                String[] s = (String[]) it.nextElement();
                Debug.print(" List Content...\n");
                System.out.println("=========================");
                for(int i=0;i<s.length;i++)
                System.out.println(" " + s[i]);
                System.out.println("=========================");

            }
            
            vObj = (Vector)remote.displayAllArenaSize();
            it = vObj.elements();
            while (it.hasMoreElements()) {
                String[] s = (String[]) it.nextElement();
                Debug.print(" List Content...\n");
                System.out.println("=========================");
                for(int i=0;i<s.length;i++)
                System.out.println(" " + s[i]);
                System.out.println("=========================");

            }
            
            arenaId = "02461d66-f919-4a42-8a7c-431b4d3ddc9e";
            bol = remote.activateArenaSize(arenaId);
            if (bol == true)
                Debug.print(" Succeffully Activated : "+bol); */
            
            /*====================================Officials =====================================*/
            
           /* String judgeName = "President of the Ground Jury (Eventing Judge)";
            boolean bol = remote.insertOficial(judgeName);
            if (bol == true)
                Debug.print(" Succeffully Inserted : "+bol);
            
            String judgeId = "1bd72e4f-6c7c-49f1-8526-dc69dc7ef051";
            bol = remote.deleteOfficial(judgeId);
            if (bol == true)
                Debug.print(" Succeffully Deleted : "+bol);
            
            judgeId = "5fce859a-5308-49bb-b284-03ee4a4abc46";
            judgeName = "Technical Delegates (Eventing TD)";
            bol = remote.updateOfficial(judgeName,judgeId);
            if (bol == true)
                Debug.print(" Succeffully Updated : "+bol);
            
            Vector vObj = (Vector)remote.displayJudgeType(judgeId);
            Enumeration it = vObj.elements();
            while (it.hasMoreElements()) {
                String[] s = (String[]) it.nextElement();
                Debug.print(" List Content...\n");
                System.out.println("=========================");
                for(int i=0;i<s.length;i++)
                System.out.println(" " + s[i]);
                System.out.println("=========================");

            }
            
            vObj = (Vector)remote.displayAllJudgetype();
            it = vObj.elements();
            while (it.hasMoreElements()) {
                String[] s = (String[]) it.nextElement();
                Debug.print(" List Content...\n");
                System.out.println("=========================");
                for(int i=0;i<s.length;i++)
                System.out.println(" " + s[i]);
                System.out.println("=========================");

            }
            
            judgeId = "3430fd55-ab9d-4f23-a027-95e8e59ca28c";
            bol = remote.activateJudgeType(judgeId);
            if (bol == true)
                Debug.print(" Succeffully Activated : "+bol); */
            
            
         /*====================================REFUND Rule Master =====================================*/
            
          /*  String refundName = "Before Closing Date";
            boolean bol = remote.insertRefundRuleMaster(refundName);
            if (bol == true)
                Debug.print(" Succeffully Inserted : "+bol);
            
            String refundId = "c6f45874-052c-4814-b98a-a954832cecc5";
            bol = remote.deleteRefundRuleMaster(refundId);
            if (bol == true)
                Debug.print(" Succeffully Deleted : "+bol);
            
            refundId = "9b4e42f4-506d-4d13-a324-e4c62d5d32ae";
            refundName = "Refunds After Closing Date 2006 December";
            bol = remote.updateRefundRuleMaster(refundName,refundId);
            if (bol == true)
                Debug.print(" Succeffully Updated : "+bol);
            
            Vector vObj = (Vector)remote.displayRefundRule(refundId);
            Enumeration it = vObj.elements();
            while (it.hasMoreElements()) {
                String[] s = (String[]) it.nextElement();
                Debug.print(" List Content...\n");
                System.out.println("=========================");
                for(int i=0;i<s.length;i++)
                System.out.println(" " + s[i]);
                System.out.println("=========================");

            }
            
            vObj = (Vector)remote.displayAllRefundRule();
            it = vObj.elements();
            while (it.hasMoreElements()) {
                String[] s = (String[]) it.nextElement();
                Debug.print(" List Content...\n");
                System.out.println("=========================");
                for(int i=0;i<s.length;i++)
                System.out.println(" " + s[i]);
                System.out.println("=========================");

            }
            
            refundId = "c6f45874-052c-4814-b98a-a954832cecc5";
            bol = remote.activateRefundRuleType(refundId);
            if (bol == true)
                Debug.print(" Succeffully Activated : "+bol);   */
            
         /*====================================REFUND Rule Sub Type Master =====================================*/
            
            String refundSubName = "XXXXXX & YYYYYY fee";
            boolean bol = remote.insertRefundRuleSubTypeMaster(refundSubName,true);
            if (bol == true)
                Debug.print(" Succeffully Inserted : "+bol);
            
            String refundSubId = "6086d6ba-dd7c-4c80-b1e4-a93a3edac9a8";
            bol = remote.deleteRefundRuleSubMaster(refundSubId);
            if (bol == true)
                Debug.print(" Succeffully Deleted : "+bol);
            
            refundSubId = "52c8ea66-7d6e-437a-8829-1cc85c6c09bf";
            refundSubName = " non-refundable ";
            bol = remote.updateRefundRuleSubMaster(refundSubName,refundSubId,true);
            if (bol == true)
                Debug.print(" Succeffully Updated : "+bol);
            
            Vector vObj = (Vector)remote.displayRefundRuleSubType(refundSubId);
            Enumeration it = vObj.elements();
            while (it.hasMoreElements()) {
                String[] s = (String[]) it.nextElement();
                Debug.print(" List Content...\n");
                System.out.println("=========================");
                for(int i=0;i<s.length;i++)
                System.out.println(" " + s[i]);
                System.out.println("=========================");

            }
            
            vObj = (Vector)remote.displayAllRefundRuleSubType(true);
            it = vObj.elements();
            while (it.hasMoreElements()) {
                String[] s = (String[]) it.nextElement();
                Debug.print(" List Content...\n");
                System.out.println("=========================");
                for(int i=0;i<s.length;i++)
                System.out.println(" " + s[i]);
                System.out.println("=========================");

            }
            
            refundSubId = "60b85993-3be2-48c7-8ccb-fbeb41f9d153";
            bol = remote.activateRefundRuleSubType(refundSubId);
            if (bol == true)
                Debug.print(" Succeffully Activated : "+bol); 
            
          /*=========================DIVISION for Event Type Master =======================*/
            
           /* String eventName = "4D";
            boolean bol = remote.insertEventTypeMaster(eventName);
            if (bol == true)
                Debug.print(" Succeffully Inserted : "+bol);
            
            String eventId = "ac6e6d2a-c80a-4fce-8fa0-d02d087fb118";
            bol = remote.deleteEventTypeMaster(eventId);
            if (bol == true)
                Debug.print(" Succeffully Deleted : "+bol);
            
            eventId = "445c7c02-49fe-402f-a1cf-ac1fb3e51d77";
            eventName = "3D";
            bol = remote.updateEventTypeMaster(eventName,eventId);
            if (bol == true)
                Debug.print(" Succeffully Updated : "+bol);
            
            Vector vObj = (Vector)remote.displayEventTypeMaster(eventId);
            Enumeration it = vObj.elements();
            while (it.hasMoreElements()) {
                String[] s = (String[]) it.nextElement();
                Debug.print(" List Content...\n");
                System.out.println("=========================");
                for(int i=0;i<s.length;i++)
                System.out.println(" " + s[i]);
                System.out.println("=========================");

            }
            
            vObj = (Vector)remote.displayAllEventTypeMaster();
            it = vObj.elements();
            while (it.hasMoreElements()) {
                String[] s = (String[]) it.nextElement();
                Debug.print(" List Content...\n");
                System.out.println("=========================");
                for(int i=0;i<s.length;i++)
                System.out.println(" " + s[i]);
                System.out.println("=========================");

            }
            
            eventId = "445c7c02-49fe-402f-a1cf-ac1fb3e51d77";
            bol = remote.activateEventTypeMaster(eventId);
            if (bol == true)
                Debug.print(" Succeffully Activated : "+bol); */
            
         /*=========================DIVISION for Event Level Master =======================*/
            
          /*  String eventLevelName = "National";
            String eventLevelCode="CCI*";
            String junpingEfffort = "";
            boolean bol = remote.insertEventLevelMaster(eventLevelName,eventLevelCode,junpingEfffort);
            if (bol == true)
                Debug.print(" Succeffully Inserted : "+bol);
            
            String eventLevelId = "ab9f2838-93bd-4664-a863-1be65c4efdae";
            bol = remote.deleteEventLevelMaster(eventLevelId);
            if (bol == true)
                Debug.print(" Succeffully Deleted : "+bol);
            
            eventLevelId = "13a98724-68a4-4c89-83fe-53a521b1499a";
            eventLevelName = "";
            eventLevelCode = "CCI***";
            junpingEfffort = "";
            bol = remote.updateEventLevelMaster(eventLevelName,eventLevelCode,junpingEfffort,eventLevelId);
            if (bol == true)
                Debug.print(" Succeffully Updated : "+bol);
            
            Vector vObj = (Vector)remote.displayEventLevelMaster(eventLevelId);
            Enumeration it = vObj.elements();
            while (it.hasMoreElements()) {
                String[] s = (String[]) it.nextElement();
                Debug.print(" List Content...\n");
                System.out.println("=========================");
                for(int i=0;i<s.length;i++)
                System.out.println(" " + s[i]);
                System.out.println("=========================");

            }*/
            
        /*    vObj = (Vector)remote.displayAllEventLevelMaster();
            it = vObj.elements();
            while (it.hasMoreElements()) {
                String[] s = (String[]) it.nextElement();
                Debug.print(" List Content...\n");
                System.out.println("=========================");
                for(int i=0;i<s.length;i++)
                System.out.println(" " + s[i]);
                System.out.println("=========================");

            }*/
            
           /* vObj = (Vector)remote.displayAllEventLevelMaster(true);
            it = vObj.elements();
            while (it.hasMoreElements()) {
                String[] s = (String[]) it.nextElement();
                Debug.print(" List Content...\n");
                System.out.println("=========================");
                for(int i=0;i<s.length;i++)
                System.out.println(" " + s[i]);
                System.out.println("=========================");

            }
            
            eventLevelId = "40fe0ad2-2bd3-4a01-998a-1be039765248";
            bol = remote.activateEventLevelMaster(eventLevelId);
            if (bol == true)
                Debug.print(" Succeffully Activated : "+bol);
            */
       }
       catch( Exception e )
       {
          e.printStackTrace();
       }
    }
    public static Context getInitialContext()
        throws javax.naming.NamingException {
        Properties p =new Properties();
        p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
        p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
        p.setProperty( "java.naming.provider.url", "localhost:11199" );
        return new javax.naming.InitialContext(p);
    }
    
}
