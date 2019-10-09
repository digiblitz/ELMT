/*******************************************************************************
 * * * Copyright: 2019 digiBlitz Foundation
 *  * * 
 *  * * License: digiBlitz Public License 1.0 (DPL) 
 *  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 *  * * 
 *  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 *  * * 
 *  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 *  * * 
 *  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
package com.hlckavery.statful;

import com.hlccommon.util.HLCHorseDescriptionVO;
import com.hlccommon.util.HLCHorseGradeVO;
import com.hlccommon.util.HLCHorseRegisterationVO;
import com.hlccommon.util.HLCHorseRegistrationVO;
import com.hlccommon.util.HLCHorseRelationVO;
import com.hlccommon.util.HLCHorseUserVO;
import com.hlccommon.util.HLCRequestHorseDetVO;
import com.hlcreg.DAO.HLCHorseRegDAO;
import com.hlcreg.form.HLCArabianSeaEntityLocal;
import com.hlcreg.form.HLCArabianSeaEntityLocalHome;
import com.hlcreg.util.DBHelper;
import com.hlcreg.util.Debug;
import com.hlcreg.util.HLCHorseDescription;
import com.hlcreg.util.HLCHorseMemberDetails;
import com.hlcreg.util.HLCHorseServiceTypeDetails;
import com.hlcreg.util.HLCPaymentDetails;
import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteBusiness;
import javax.ejb.FinderException;
import javax.ejb.SessionBean;

/**
 * This is the bean class for the KaverySessionBeanStatfulBean enterprise bean.
 * Created Aug 19, 2006 1:05:08 PM
 * @author harmohan
 */

public class HLCKaverySessionBeanStatfulBean implements SessionBean, HLCKaverySessionBeanStatfulRemoteBusiness {
    private SessionContext context;
    private InitialContext ic = null;
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con;
    ResultSet rs= null;
    PreparedStatement prepStmt = null;
    HLCArabianSeaEntityLocalHome home;
    HLCArabianSeaEntityLocal remote;
    Vector vObj;
    
    String horseMemberId;
    int Id=0;
    String name = "ejb/HLCArabianSeaJNDI";
    
    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">
    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise bean, Web services)
    // TODO Add business methods or web service operations
    /**
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    public void setSessionContext(SessionContext aContext) {
        context = aContext;
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
        
    }
    // </editor-fold>
    
    /**
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */
    public void ejbCreate() {
        try{
            InitialContext jndiContext = getInitialContext();
            Object obj = jndiContext.lookup(name);
            home = (HLCArabianSeaEntityLocalHome)obj;
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //create(PaymentDetails objPayment)
    public boolean addPaymentDetails(HLCPaymentDetails objPayment) throws RemoteException {
        
        try{
            remote = home.create(objPayment);
        } catch(Exception exp){
            throw new EJBException("createHorseServiceTypeDetails: " + exp.getMessage());
        }
        return true;
    }
    
    public boolean deleteHorseServiceType(String horseMemberId) throws RemoteException {
        HLCHorseRegDAO horseDAO = new HLCHorseRegDAO();
        boolean bol = false;
        try{
            bol = horseDAO.deleteHorseServiceTypeMaster(horseMemberId);
            Debug.print(" Succefully deleted Horse Service Type Master : "+bol);
            
        } catch(Exception exp){
            throw new EJBException("createHorseServiceTypeDetails: " + exp.getMessage());
        }
        return bol;
    }
    
    public boolean addHorseServiceType(HLCHorseServiceTypeDetails objHorseService) throws RemoteException {
        HLCHorseRegDAO horseDAO = new HLCHorseRegDAO();
        boolean bol = false;
        try{
            remote = home.create(objHorseService);
        } catch(Exception exp){
            throw new EJBException("createHorseServiceTypeDetails: " + exp.getMessage());
        }
        return true;
    }
    
    public void addHorseRegistration(HLCHorseMemberDetails objHorseMem, HLCHorseDescription objHorseDesc,  HLCPaymentDetails objPayment) throws RemoteException{
        try{
            remote = home.create(objHorseMem, objHorseDesc, objPayment);
        } catch(Exception exp){
            throw new EJBException("addHorseRegistration: " + exp.getMessage());
        }
    }
    
    
    public ArrayList getGenMemberId() throws RemoteException, FinderException {
        Debug.print("inside the Kavery Session bean getGenMemberId");
        
        ArrayList A = new ArrayList();
        Collection col = home.findByGenMemberID();
        ArrayList array = new ArrayList(col);
        for (Iterator it=array.iterator(); it.hasNext( ); ) {
            // Object anObject = it.next( );
            String str = String.valueOf(it.next( ));
            String[] result = str.split(":");
            for (int x=1; x<result.length; x++){
                System.out.println(result[x]);
                A.add(result[1]);
            }
            
            //System.out.println( nonuseaOrgId); //anObject.toString() );
        }
        //Collection c = home.findByGenMemberID();
        Debug.print("after the Kavery Session bean findByGenMemberID");
        return A;
    }
    //findByGenUserID
    
    public ArrayList getGenUserId() throws RemoteException, FinderException {
        Debug.print("inside the Kavery Session bean getGenUserId");
        
        ArrayList A = new ArrayList();
        Collection col = home.findByGenUserID();
        ArrayList array = new ArrayList(col);
        for (Iterator it=array.iterator(); it.hasNext( ); ) {
            // Object anObject = it.next( );
            String str = String.valueOf(it.next( ));
            String[] result = str.split(":");
            for (int x=1; x<result.length; x++){
                System.out.println(result[x]);
                A.add(result[1]);
            }
            
            //System.out.println( nonuseaOrgId); //anObject.toString() );
        }
        
        //Collection c = home.findByGenUserID();
        Debug.print("after the Kavery Session bean findByGenUserID");
        return A;
    }
    
    public String getUserId(String memberId) throws RemoteException {
        //findByMemberIdUserID(String memberId)
        String memId = memberId;
        String userId = null;
        try {
            Collection col = home.findByMemberIdUserID(memId);
            ArrayList array = new ArrayList(col);
            for (Iterator it=array.iterator(); it.hasNext( ); ) {
                // Object anObject = it.next( );
                String str = String.valueOf(it.next( ));
                String[] result = str.split(":");
                for (int x=1; x<result.length; x++){
                    System.out.println(result[x]);
                    userId =result[1];
                }
                
                //System.out.println( nonuseaOrgId); //anObject.toString() );
            }
        }catch (Exception e){
            Debug.print("Error while getting userId : "+e.getMessage());
        }
        return userId;
    }
    
    private boolean isNotNull(String data) {
        return (data!=null && data.trim().length()>0) ? true :false;
    }
    
    public boolean editHorseMemberDetails(HLCHorseDescription objHorseDesc) throws RemoteException, FinderException{
        System.out.print("Member ID : "+objHorseDesc.getHorseMemberId());
        if (objHorseDesc == null && (objHorseDesc.getHorseMemberId()).equals("") ) {
            throw new EJBException("Horse Member ID can't be empty");
        }objHorseDesc.getHorseMemberId();
        if (memberExists(objHorseDesc.getHorseMemberId()) == false) {
            throw new EJBException("Horse Member ID Not Exists : " + horseMemberId);
        }
        
        Debug.print("Horse objHorseDesc.getHorseMemberId()"+objHorseDesc.getHorseMemberId());
        
        Debug.print("Horse DEscription : \n"+objHorseDesc);
        if (isNotNull(objHorseDesc.getHorseMemberId())) {
            remote.setHorseMemberId(objHorseDesc.getHorseMemberId());
        }
        
        if (isNotNull(objHorseDesc.getHorseMembershipTypeId())) {
            remote.setHorseMembershipTypeId(objHorseDesc.getHorseMembershipTypeId());
        }
        if (isNotNull(objHorseDesc.getImportedFrom())) {
            remote.setImportedFrom(objHorseDesc.getImportedFrom());
        }
        if (isNotNull(String.valueOf(objHorseDesc.getImportDate()))) {
            remote.setImportDate(objHorseDesc.getImportDate());
        }
        if (isNotNull(objHorseDesc.getForeignGrade())) {
            remote.setForeignGrade(objHorseDesc.getForeignGrade());
        }
        remote.setForeignPoints(objHorseDesc.getForeignPoints());
        if (isNotNull(objHorseDesc.getAssignedGrade())) {
            remote.setAssignedGrade(objHorseDesc.getAssignedGrade());
        }
        remote.setAssignedPoints(objHorseDesc.getAssignedPoints());
        objHorseDesc.reset();
        Debug.print(" Horse escription : "+objHorseDesc);
        return true;
        
    }
    
    public boolean  getMemberStatus(String horseMemberId) throws RemoteException {
        PreparedStatement prepStmt1=null;
        ResultSet rs= null;
        boolean bol = false;
        try {
            makeConnection();
            
            bol = DBHelper.getMemberShipStatus(con,horseMemberId);
            
            releaseConnection();
        }catch (Exception e) {
            releaseConnection();
            e.printStackTrace();
        }finally {
            releaseConnection();
        }
        
        return bol;
    }
    
    
    private boolean memberExists(String horseMemberId) {
        Debug.print("Kavery Session Bean memberExists");
        
        if ( !(horseMemberId.equals(this.horseMemberId)) ) {
            try {
                remote = home.findByPrimaryKey(horseMemberId);
                this.horseMemberId = horseMemberId;
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }
    
    public boolean deleteHorseMembership(String horseMemberId) throws RemoteException{
        Debug.print("Kavery Session Bean HorseMemberDetail");
        Debug.print("Horse Memebr Id :" + horseMemberId);
        boolean result = false;
        if (horseMemberId == null) {
            throw new EJBException("horseMemberId can't be empty");
        }
        if (memberExists(horseMemberId) == false) {
            throw new EJBException("statusId Not Exists" + horseMemberId);
        }
        try {
            remote.remove();
            result = true;
        } catch (Exception ex) {
            throw new EJBException("remove horseMemberDetail : " + ex.getMessage());
        } finally{
            return result;
        }
    }
    
    
    public String createHorseRegistration(HLCHorseRegistrationVO objHrRegVO, HLCHorseDescriptionVO objHrDescVO,
            HLCHorseUserVO objMainOwner,HLCHorseUserVO objHorseTrainer, HLCHorseUserVO objAddOwner, String serviceId, boolean companyStatus) throws RemoteException{
        Debug.print("KaverySessionBean createHorseRegistration ");
        boolean result = false;
        HLCHorseRegDAO dao = new HLCHorseRegDAO();
        HLCHorseGradeVO objHrGradVO = new HLCHorseGradeVO();
        String riderId = objHrRegVO.getRiderMemberId();
        String ownerId = objHrRegVO.getOwnerId();
        String trainerId = objHrRegVO.getTrainerUserId();
        String addOwnerId = objHrRegVO.getAddOwnerId();
        boolean addOwnerStatus = objHrRegVO.isAddOwnerStatus();
        Debug.print("riderId:"+riderId);
        Debug.print("ownerId:"+ownerId);
        Debug.print("addOwnerId:"+addOwnerId);
        Debug.print("trainerId is "+trainerId);
        horseMemberId = null;
        
        if(ownerId==null && objMainOwner.getUserId()== null){
            Debug.print("Inside Owner Id:" + ownerId);
            if(companyStatus==true){
                ownerId = dao.insertUserDetails(objMainOwner.getFirstName(), objMainOwner.getLastName(), null,
                        null, objMainOwner.getEmailId(), objMainOwner.getLoginName());
            } else{
                ownerId = dao.insertUserDetails(objMainOwner.getFirstName(), objMainOwner.getLastName(), objMainOwner.getDob(),
                        objMainOwner.getGender(), objMainOwner.getEmailId(), null);
            }
            
            objHrRegVO.setOwnerId(ownerId);
            Debug.print("New Owner Registration Id:" + ownerId);
            boolean userReuslt = dao.insertContactDetails(ownerId, objMainOwner.getAddress(), objMainOwner.getCity(),
                    objMainOwner.getState(), objMainOwner.getCountry(), objMainOwner.getZip(), objMainOwner.getPhoneNo(),
                    objMainOwner.getFaxNo());
            Debug.print("New Owner Registration :" + userReuslt);
        }
        
        /// Trainer Details
        if(trainerId==null && objMainOwner.getUserId()== null){
            Debug.print("Inside Owner Id:" + trainerId);
            if(companyStatus==true){
                trainerId = dao.insertUserDetails(objMainOwner.getFirstName(), objMainOwner.getLastName(), null,
                        null, objMainOwner.getEmailId(), objMainOwner.getLoginName());
            } else{
                trainerId = dao.insertUserDetails(objMainOwner.getFirstName(), objMainOwner.getLastName(), objMainOwner.getDob(),
                        objMainOwner.getGender(), objMainOwner.getEmailId(), null);
            }
            
            objHrRegVO.setTrainerUserId(trainerId);
            Debug.print("New Trainer Registration Id:" + trainerId);
            boolean userReuslt = dao.insertContactDetails(trainerId, objMainOwner.getAddress(), objMainOwner.getCity(),
                    objMainOwner.getState(), objMainOwner.getCountry(), objMainOwner.getZip(), objMainOwner.getPhoneNo(),
                    objMainOwner.getFaxNo());
            Debug.print("New Owner Registration :" + userReuslt);
        }
        
        
        Debug.print("Owner Registration Id:" + ownerId);
        if(riderId!=null && riderId.trim().length()!=0 && ownerId!=null && ownerId.trim().length()!=0){
            objHrRegVO.setTrainerUserId(trainerId);
            horseMemberId = dao.insertHorseMemberDetails(objHrRegVO);
            Debug.print("Horse MemberId from KaverySessionBean createHorseRegistration():" + horseMemberId);
            if(horseMemberId!=null && horseMemberId.trim().length()!=0){
                objHrDescVO.setHorseMemberId(horseMemberId);
                result = dao.insertHorseDescriptionDetails(objHrDescVO);
            }
            /*
             * For Grade points
             */
            if(horseMemberId!=null && horseMemberId.trim().length()!=0){
                objHrGradVO.setHorseMemberId(horseMemberId);
                result = dao.insertHorseGradeDetails(objHrGradVO);
            }
            
            boolean serviceResult = false;
            if(serviceId!=null && serviceId.trim().length()!=0 && horseMemberId!=null && horseMemberId.trim().length()!=0){
                serviceResult = dao.insertServiceDetails(horseMemberId, serviceId);
            }
            Debug.print("serviceResult:" + serviceResult);
            
            String riderRelId = dao.selectRelationTypeId("Rider");
            String ownerRelId = dao.selectRelationTypeId("Owner");
            String trainerRelId = "";
            if(trainerId!=null && trainerId.trim().length()!=0){
                trainerRelId = dao.selectRelationTypeId("Trainer");
            }
            String riderUserId = dao.selectUserId(riderId);
            
            HLCHorseRelationVO objHrRelVO = new HLCHorseRelationVO();
            objHrRelVO.setHorseMemberId(horseMemberId);
            objHrRelVO.setRelationshipTypeId(ownerRelId);
            objHrRelVO.setUserId(ownerId);
            objHrRelVO.setRelationshipStatus("Pending");
            Debug.print("Owner Id Before insert horse relation:" + ownerId);
            boolean horOwnResult = dao.insertHorseRelationDetails(objHrRelVO);
            Debug.print("KaverySessionBean () Insert Owner Relation Details result :" + horOwnResult);
            
            HLCHorseRelationVO objHrRelRiderVO = new HLCHorseRelationVO();
            objHrRelRiderVO.setHorseMemberId(horseMemberId);
            objHrRelRiderVO.setRelationshipTypeId(riderRelId);
            objHrRelRiderVO.setUserId(riderUserId);
            objHrRelRiderVO.setRelationshipStatus("Pending");
            Debug.print("Rider User Id Before insert horse relation:" + riderUserId);
            boolean horRelResult = dao.insertHorseRelationDetails(objHrRelRiderVO);
            Debug.print("KaverySessionBean () Insert Rider Relation Details result :" + horRelResult);

            /// Trainer Relation

            if(trainerRelId!=null && trainerRelId.trim().length()!=0){
                HLCHorseRelationVO objTrnrHrRelVO = new HLCHorseRelationVO();
                objTrnrHrRelVO.setHorseMemberId(horseMemberId);
                objTrnrHrRelVO.setRelationshipTypeId(trainerRelId);
                objTrnrHrRelVO.setUserId(trainerId);
                objTrnrHrRelVO.setRelationshipStatus("Pending");
                Debug.print("Trainer Id Before insert horse relation:" + trainerId);
                boolean horTrainerResult = dao.insertHorseRelationDetails(objTrnrHrRelVO);
                Debug.print("KaverySessionBean () Insert Trainer Relation Details result :" + horTrainerResult);

                Debug.print("KaverySessionBean createHorseRegistration() result :" + result);
            }
        }
        return horseMemberId;
    }
    
    public boolean createHorseRiderAndOwnerDetails(String horseMemberId, String riderId, String ownerId,
            HLCHorseUserVO objMainOwner, boolean companyStatus, boolean isCard, String reqId) throws RemoteException{
        Debug.print("KaverySessionBean createHorseRiderAndOwnerDetails " + reqId);
        boolean result = false;
        HLCHorseRegDAO dao = new HLCHorseRegDAO();
        Debug.print("riderId:"+riderId);
        Debug.print("ownerId:"+ownerId);
        String riderRelId = dao.selectRelationTypeId("Rider");
        String ownerRelId = dao.selectRelationTypeId("Owner");
        boolean horRelResult = false;
        boolean horOwnResult  = false;
        
        if(ownerId==null && objMainOwner.getUserId()== null){
            Debug.print("Inside Owner Id:" + ownerId);
            if(companyStatus==true){
                ownerId = dao.insertUserDetails(objMainOwner.getFirstName(), objMainOwner.getLastName(), null,
                        null, objMainOwner.getEmailId(), objMainOwner.getLoginName());
            } else{
                ownerId = dao.insertUserDetails(objMainOwner.getFirstName(), objMainOwner.getLastName(), objMainOwner.getDob(),
                        objMainOwner.getGender(), objMainOwner.getEmailId(), null);
            }
            Debug.print("Owner Registration Id:" + ownerId);
            boolean userReuslt = dao.insertContactDetails(ownerId, objMainOwner.getAddress(), objMainOwner.getCity(),
                    objMainOwner.getState(), objMainOwner.getCountry(), objMainOwner.getZip(), objMainOwner.getPhoneNo(),
                    objMainOwner.getFaxNo());
            Debug.print("New Owner Registration :" + userReuslt);
        }
        
        String cardStatus = "Pending";
        
        if(isCard==true){
            cardStatus = "Active";
        }
        
        if(ownerId!=null && ownerId.trim().length()!=0){
            HLCHorseRelationVO objHrRelVO = new HLCHorseRelationVO();
            objHrRelVO.setHorseMemberId(horseMemberId);
            objHrRelVO.setRelationshipTypeId(ownerRelId);
            objHrRelVO.setUserId(ownerId);
            objHrRelVO.setRelationshipStatus(cardStatus);
            objHrRelVO.setRequestId(reqId);
            horOwnResult = dao.insertHorseRelationDetails(objHrRelVO);
            Debug.print("KaverySessionBean () Add Owner Relation Details result :" + horOwnResult);
        }
        
        if(riderId!=null && riderId.trim().length()!=0 ){
            String riderUserId = dao.selectUserId(riderId);
            HLCHorseRelationVO objHrRelRiderVO = new HLCHorseRelationVO();
            objHrRelRiderVO.setHorseMemberId(horseMemberId);
            objHrRelRiderVO.setRelationshipTypeId(riderRelId);
            objHrRelRiderVO.setUserId(riderUserId);
            objHrRelRiderVO.setRelationshipStatus(cardStatus);
            objHrRelRiderVO.setRequestId(reqId);
            horRelResult = dao.insertHorseRelationDetails(objHrRelRiderVO);
            Debug.print("KaverySessionBean () Add Rider Relation Details result :" + horRelResult);
        }
        if(horOwnResult==true || horRelResult==true) {
            result = true;
        }
        return result;
    }
    
    public String editHorseRiderAndOwnerDetails(String horseMemberId, String horseName, String horseRegisterName , String requestBy,
            String riderId, String ownerId, boolean ownerRequest, HLCHorseUserVO objMainOwner, boolean companyStatus,
            String paymentId, float amount, String serviceId,boolean reqValue) throws RemoteException {
        Debug.print("KaverySessionBean editHorseRiderAndOwnerDetails ");
        boolean result = false;
        HLCHorseRegDAO dao = new HLCHorseRegDAO();
        Debug.print("riderId:"+riderId);
        Debug.print("ownerId:"+ownerId);
        String riderRelId = dao.selectRelationTypeId("Rider");
        String ownerRelId = dao.selectRelationTypeId("Owner");
        boolean horRelResult = false;
        boolean horOwnResult  = false;
        
        if(ownerId==null && objMainOwner.getUserId()== null && ownerRequest==true){
            Debug.print("Inside Owner Id:" + ownerId);
            if(companyStatus==true){
                ownerId = dao.insertUserDetails(objMainOwner.getFirstName(), objMainOwner.getLastName(), null,
                        null, objMainOwner.getEmailId(), objMainOwner.getLoginName());
            } else{
                ownerId = dao.insertUserDetails(objMainOwner.getFirstName(), objMainOwner.getLastName(), objMainOwner.getDob(),
                        objMainOwner.getGender(), objMainOwner.getEmailId(), null);
            }
            Debug.print("Owner Registration Id:" + ownerId);
            boolean userReuslt = dao.insertContactDetails(ownerId, objMainOwner.getAddress(), objMainOwner.getCity(),
                    objMainOwner.getState(), objMainOwner.getCountry(), objMainOwner.getZip(), objMainOwner.getPhoneNo(),
                    objMainOwner.getFaxNo());
            Debug.print("New Owner Registration :" + userReuslt);
        }
        
        
        boolean serviceResult = false;
        if(serviceId!=null && serviceId.trim().length()!=0 && horseMemberId!=null && horseMemberId.trim().length()!=0){
            serviceResult = dao.insertServiceDetails(horseMemberId, serviceId);
        }
        Debug.print("       serviceResult:" + serviceResult);
        
        HLCRequestHorseDetVO reqHrDetVO = new HLCRequestHorseDetVO();
        if(horseMemberId!=null && horseMemberId.trim().length()!=0) reqHrDetVO.setHorseMemberId(horseMemberId);
        if(ownerId!=null && ownerId.trim().length()!=0) reqHrDetVO.setReqOwnerId(ownerId);
        if(riderId!=null && riderId.trim().length()!=0 ){
            String riderUserId = dao.selectUserId(riderId);
            reqHrDetVO.setReqRiderId(riderUserId);
        }
        if(horseName!=null && horseName.trim().length()!=0) reqHrDetVO.setReqHorseName(horseName);
        if(horseRegisterName!=null && horseRegisterName.trim().length()!=0) reqHrDetVO.setReqHorseRegName(horseRegisterName);
        if(requestBy!=null && requestBy.trim().length()!=0) reqHrDetVO.setRequestedBy(requestBy);
        if(paymentId!=null && paymentId.trim().length()!=0) reqHrDetVO.setPaymentId(paymentId);
        reqHrDetVO.setReqAmount(amount);
        reqHrDetVO.setAddPaymentStatus(false);
        String requestId = dao.insertRequestHorseMemberDetailsChange(reqHrDetVO,reqValue);
        //if(requestId!=null && requestId.trim().length()!=0) result = true;
        return requestId;
    }
    
    public boolean editHorseOwner(String horseMemberId, String ownerId, HLCHorseUserVO objMainOwner, boolean companyStatus, boolean relationStatus) throws RemoteException{
        Debug.print("KaverySessionBean editHorseOwner ");
        boolean result = false;
        HLCHorseRegDAO dao = new HLCHorseRegDAO();
        Debug.print(" Before   horseMemberId" + horseMemberId);
        Debug.print("  Before  ownerId" + ownerId);
        
        if(horseMemberId!=null && horseMemberId.trim().length()!=0 && (ownerId==null || ownerId.trim().length()==0)){
            Debug.print("Inside Owner Id:" + ownerId);
            if(companyStatus==true){
                ownerId = dao.insertUserDetails(objMainOwner.getFirstName(), objMainOwner.getLastName(), null,
                        null, objMainOwner.getEmailId(), objMainOwner.getLoginName());
            } else{
                ownerId = dao.insertUserDetails(objMainOwner.getFirstName(), objMainOwner.getLastName(), objMainOwner.getDob(),
                        objMainOwner.getGender(), objMainOwner.getEmailId(), null);
            }
            
            Debug.print("Owner Registration Id:" + ownerId);
            
            boolean userReuslt = dao.insertContactDetails(ownerId, objMainOwner.getAddress(), objMainOwner.getCity(),
                    objMainOwner.getState(), objMainOwner.getCountry(), objMainOwner.getZip(), objMainOwner.getPhoneNo(),
                    objMainOwner.getFaxNo());
            Debug.print("New Owner Registration :" + userReuslt);
        }
        
        Debug.print("   horseMemberId" + horseMemberId);
        Debug.print("   ownerId" + ownerId);
        
        String ownerRelId = dao.selectRelationTypeId("Owner");
        String preOwnerRelId = dao.selectRelationTypeId("Previous Owner");
        String priOwnerId = getOwnerIdFromHrMember(horseMemberId);
        String relationId = "";
        if(priOwnerId!=null && priOwnerId.trim().length()!=0){
            relationId = getRelationId(horseMemberId, priOwnerId, ownerRelId);
        }
        
        if(relationId!=null && relationId.trim().length()!=0 && preOwnerRelId!=null && preOwnerRelId.trim().length()!=0
                && priOwnerId!=null && priOwnerId.trim().length()!=0){
            boolean upResult = updateOwnerwithRelationship(relationId, preOwnerRelId, priOwnerId);
            Debug.print("Update Owner details in Relationship table:" + upResult);
        }
        String cardStatus = "Pending";
        if(relationStatus==true){
         cardStatus = "Active";
        }
        
        HLCHorseRelationVO objHrRelVO = new HLCHorseRelationVO();
        objHrRelVO.setHorseMemberId(horseMemberId);
        objHrRelVO.setRelationshipTypeId(ownerRelId);
        objHrRelVO.setUserId(ownerId);
        objHrRelVO.setRelationshipStatus(cardStatus);
        boolean horOwnResult = dao.insertHorseRelationDetails(objHrRelVO);
        
        if(horseMemberId!=null && horseMemberId.trim().length()!=0 && ownerId!=null && ownerId.trim().length()!=0){
            result = dao.updateOwnerDetails(horseMemberId, ownerId);
        }
        
        Debug.print("   editHorseOwnerDetails Result:" + result);
        return result;
    }
    
    public boolean editHorseTrainer(String horseMemberId, String trainerId, HLCHorseUserVO objMainTrainer, boolean companyStatus, boolean relationStatus, String previousRiderId, String userId) throws RemoteException{
        Debug.print("KaverySessionBean editHorseTrainer ");
        boolean result = false;
        HLCHorseRegDAO dao = new HLCHorseRegDAO();
        Debug.print(" Before HorseMemberId" + horseMemberId);
        Debug.print("  Before Trainer ID " + trainerId);
        
        if(horseMemberId!=null && horseMemberId.trim().length()!=0 && (trainerId==null || trainerId.trim().length()==0)){
            Debug.print("Inside Trainer Id:" + trainerId);
            if(companyStatus==true){
                trainerId = dao.insertUserDetails(objMainTrainer.getFirstName(), objMainTrainer.getLastName(), null,
                        null, objMainTrainer.getEmailId(), objMainTrainer.getLoginName());
            } else{
                trainerId = dao.insertUserDetails(objMainTrainer.getFirstName(), objMainTrainer.getLastName(), objMainTrainer.getDob(),
                        objMainTrainer.getGender(), objMainTrainer.getEmailId(), null);
            }
            
            Debug.print("Trainer Registration Id:" + trainerId);
            
            boolean userReuslt = dao.insertContactDetails(trainerId, objMainTrainer.getAddress(), objMainTrainer.getCity(),
                    objMainTrainer.getState(), objMainTrainer.getCountry(), objMainTrainer.getZip(), objMainTrainer.getPhoneNo(),
                    objMainTrainer.getFaxNo());
            Debug.print("New Trainer Registration :" + userReuslt);
        }
        
        Debug.print("   horseMemberId" + horseMemberId);
        Debug.print("   trainerId" + trainerId);
        
        String trainerRelId = dao.selectRelationTypeId("Trainer");
        String preTrainerRelId = dao.selectRelationTypeId("Previous Trainer");
        String priTrainerId = getTrainerIdFromHrMember(horseMemberId);
        String relationId = "";
        if(priTrainerId!=null && priTrainerId.trim().length()!=0){
            relationId = getRelationId(horseMemberId, previousRiderId, trainerRelId);
        }
        Debug.print("preTrainerRelId is "+preTrainerRelId);
        Debug.print("trainerRelId is "+trainerRelId);
        Debug.print("priTrainerId is "+priTrainerId);
        Debug.print("relationId is "+relationId);
        
        if(relationId!=null && relationId.trim().length()!=0 && preTrainerRelId!=null && preTrainerRelId.trim().length()!=0
                && priTrainerId!=null && priTrainerId.trim().length()!=0){
            boolean upResult = updateOwnerwithRelationship(relationId, preTrainerRelId, previousRiderId);
            Debug.print("Update Trainer details in Relationship table:" + upResult);
            
            boolean date_update = dao.updateRelationDateInfo(relationId,horseMemberId,userId);
            Debug.print("Date_update is "+date_update);
        }
        
        String cardStatus = "Pending";
        if(relationStatus==true){
         cardStatus = "Active";
        }
        
        HLCHorseRelationVO objHrRelVO = new HLCHorseRelationVO();
        objHrRelVO.setHorseMemberId(horseMemberId);
        objHrRelVO.setRelationshipTypeId(trainerRelId);
        objHrRelVO.setUserId(trainerId);
        objHrRelVO.setRelationshipStatus(cardStatus);
        boolean horOwnResult = dao.insertHorseRelationDetails(objHrRelVO);
        
        if(horseMemberId!=null && horseMemberId.trim().length()!=0 && trainerId!=null && trainerId.trim().length()!=0){
            result = dao.updateTrainerDetails(horseMemberId, trainerId);
        }

        Debug.print("   editHorseTrainerDetails Result:" + result);
        return result;
    }
  
    public boolean insertTrainerInfo(String horseMemberId, String trainerId, HLCHorseUserVO objMainTrainer,boolean companyStatus,String relationStatus) throws RemoteException {
        Debug.print("KaverySessionBean insertTrainerInfo RequestStatus: " + trainerId+" companyStatus "+companyStatus);
        ArrayList horseDetList  = new ArrayList();
        boolean horOwnResult = false;
        try {
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 && (trainerId==null || trainerId.trim().length()==0)){
                Debug.print("Inside Trainer Id:" + trainerId);
                if(companyStatus==true){
                    trainerId = new HLCHorseRegDAO().insertUserDetails(objMainTrainer.getFirstName(), objMainTrainer.getLastName(), null,
                            null, objMainTrainer.getEmailId(), objMainTrainer.getLoginName());
                } else{
                    trainerId = new HLCHorseRegDAO().insertUserDetails(objMainTrainer.getFirstName(), objMainTrainer.getLastName(), objMainTrainer.getDob(),
                            objMainTrainer.getGender(), objMainTrainer.getEmailId(), null);
                }
                
                Debug.print("Trainer Registration Id:" + trainerId);
                
                boolean userReuslt = new HLCHorseRegDAO().insertContactDetails(trainerId, objMainTrainer.getAddress(), objMainTrainer.getCity(),
                        objMainTrainer.getState(), objMainTrainer.getCountry(), objMainTrainer.getZip(), objMainTrainer.getPhoneNo(),
                        objMainTrainer.getFaxNo());
                Debug.print("New Trainer Registration :" + userReuslt);
            }
            
// Relation Details
            
            HLCHorseRelationVO objHrRelVO = new HLCHorseRelationVO();
            String trainerRelId = new HLCHorseRegDAO().selectRelationTypeId("Trainer");
            objHrRelVO.setHorseMemberId(horseMemberId);
            objHrRelVO.setRelationshipTypeId(trainerRelId);
            objHrRelVO.setUserId(trainerId);
            objHrRelVO.setRelationshipStatus(String.valueOf(relationStatus));
            horOwnResult = new HLCHorseRegDAO().insertHorseRelationDetails(objHrRelVO);
            
            boolean membDetUpdate = new HLCHorseRegDAO().updateTrainerInfo(horseMemberId,trainerId);
            Debug.print(" Member Details Update in table is "+membDetUpdate);
        }
        catch(Exception e){
            Debug.print("Exception in getAllHorseDetails:" + e);
        }
        Debug.print("KaverySessionBean insertTrainerInfo : ");
        return horOwnResult;
    }
    
    public boolean updateTrainerInfo(String horseMemberId, String trainerId) throws RemoteException {
        Debug.print("KaverySessionBean updateTrainerInfo "+horseMemberId+": " + trainerId);
        boolean result = false;
        try {
            if(horseMemberId!=null && trainerId!=null){
                result = new HLCHorseRegDAO().updateTrainerInfo(horseMemberId,trainerId);
            }
        }catch(Exception e){
            Debug.print("Exception in getAllHorseDetails:" + e);
        }
        Debug.print("KaverySessionBean updateTrainerInfo");
        return result;
    }
    
    public ArrayList getAllHorseDetails(String requestStatus, String paramMembershipTypeId, int pageNo) throws RemoteException {
        Debug.print("KaverySessionBean getAllHorseDetails RequestStatus: " + requestStatus);
        Debug.print("KaverySessionBean getAllHorseDetails MembershipTypeId: " + paramMembershipTypeId);
        ArrayList horseDetList  = new ArrayList();
        try {
            if(requestStatus!=null && requestStatus.trim().length()!=0 && paramMembershipTypeId!=null && paramMembershipTypeId.trim().length()!=0){
                horseDetList = (ArrayList)new HLCHorseRegDAO().selectAllHorseDetails(requestStatus, paramMembershipTypeId, pageNo);
            }
        }catch(Exception e){
            Debug.print("Exception in getAllHorseDetails:" + e);
        }
        Debug.print("KaverySessionBean getAllHorseDetails RequestStatus: " + requestStatus);
        return horseDetList;
    }
    
    public boolean updateBalanaceAmount(String paymentId, float balAmount) throws RemoteException{
        Debug.print("KaverySessionBean updateBalanaceAmount: " + paymentId);
        boolean result  = false;
        HLCHorseRegDAO dao = new HLCHorseRegDAO();
        try {
            if(paymentId!=null && paymentId.trim().length()!=0){
                Debug.print("KaverySessionBean inside updateHorseDetailsByOwner : " + paymentId);
                result = dao.updateBalanaceAmount(paymentId, balAmount);
            }
        }catch(Exception e){
            Debug.print("Exception in updateBalanaceAmount:" + e);
        }
        Debug.print("KaverySessionBean updateBalanaceAmount : " + result);
        return result;
    }
    
    public boolean updatePendingAmount(String userId, String paymentId, float checkAmount) throws RemoteException{
        Debug.print("KaverySessionBean updatePendingAmount paymentId: " + paymentId);
        boolean result  = false;
        HLCHorseRegDAO dao = new HLCHorseRegDAO();
        try {
            if(paymentId!=null && paymentId.trim().length()!=0){
                Debug.print("KaverySessionBean inside updateHorseDetailsByOwner : " + paymentId);
                result = dao.updatePendingAmount(userId, paymentId, checkAmount);
            }
        }catch(Exception e){
            Debug.print("Exception in updatePendingAmount:" + e);
        }
        Debug.print("KaverySessionBean updatePendingAmount : " + result);
        return result;
    }
    
    public int horseRowCount(String requestStatus, String paramMembershipTypeId) throws RemoteException{
        Debug.print("KaverySessionBean horseRowCount:");
        int rowCount = 0;
        try {
            if(requestStatus!=null && requestStatus.trim().length()!=0 && paramMembershipTypeId!=null && paramMembershipTypeId.trim().length()!=0){
                rowCount = new HLCHorseRegDAO().countHorseRegisteration(requestStatus, paramMembershipTypeId);
            }
        }catch(Exception e){
            Debug.print("Exception in getAllHorseDetails:" + e);
        }
        Debug.print("KaverySessionBean horseRowCount " + rowCount);
        return rowCount;
    }
    
    public float getBalanceAmount(String paymentId) throws RemoteException {
        Debug.print("KaverySessionBean getBalanceAmount:");
        float balAmount = 0;
        try {
            if(paymentId!=null && paymentId.trim().length()!=0){
                balAmount = new HLCHorseRegDAO().getBalanceAmount(paymentId);
            }
        }catch(Exception e){
            Debug.print("Exception in getBalanceAmount:" + e);
        }
        Debug.print("KaverySessionBean getBalanceAmount " + balAmount);
        return balAmount;
    }
    
    
    public int horseUserRowCount(String userId) throws RemoteException{
        Debug.print("KaverySessionBean horseRowCount:");
        int rowCount = 0;
        try {
            if(userId!=null && userId.trim().length()!=0){
                rowCount = new HLCHorseRegDAO().countHorseOwner(userId);
            }
        }catch(Exception e){
            Debug.print("Exception in getAllHorseDetails:" + e);
        }
        Debug.print("KaverySessionBean horseRowCount " + rowCount);
        return rowCount;
    }
    
    public ArrayList getAllStatusDetails() throws RemoteException {
        ArrayList statusList  = new ArrayList();
        try {
            statusList = (ArrayList)new HLCHorseRegDAO().selectAllStatusDetails();
        }catch(Exception e){
            Debug.print("Exception in getAllStatusDetails:" + e);
        }
        return statusList;
    }
    
    public ArrayList getAllHorseRelationMaster() throws RemoteException {
        ArrayList relaStatusList  = new ArrayList();
        try {
            relaStatusList = (ArrayList)new HLCHorseRegDAO().selectAllHorseRelationMaster();
        }catch(Exception e){
            Debug.print("Exception in getAllHorseRelationMaster:" + e);
        }
        return relaStatusList;
    }
    
    public String createHorseRequest(HLCRequestHorseDetVO reqHrDetVO) throws RemoteException {
        String result = "";
        reqHrDetVO.setAddPaymentStatus(true);
        try {
            result = new HLCHorseRegDAO().insertRequestHorseMemberDetails(reqHrDetVO);
        }catch(Exception e){
            Debug.print("Exception in getAllHorseRelationMaster:" + e);
        }
        return result;
    }
    
 /*  public ArrayList getAllHorseDetails(String requestStatus) throws RemoteException {
        Debug.print("KaverySessionBean getAllHorseDetails RequestStatus: " + requestStatus);
        ArrayList horseDetList  = new ArrayList();
        try {
            horseDetList = (ArrayList)new HorseRegDAO().selectAllHorseDetails(requestStatus);
        }catch(Exception e){
            Debug.print("Exception in getAllHorseDetails:" + e);
        }
        Debug.print("KaverySessionBean getAllHorseDetails RequestStatus: " + requestStatus);
        return horseDetList;
    }
  */
    
    public boolean upgradeHorse(String horseMemberId, String membershipTypeId, String paymentId, String serviceTypeId, String membershipAmount, String amount) throws RemoteException {
        Debug.print("KaverySessionBean upgradeHorse : " + horseMemberId);
        boolean result  = false;
        HLCHorseRegDAO dao = new HLCHorseRegDAO();
        try {
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 && membershipTypeId!=null && membershipTypeId.trim().length()!=0
                    && paymentId!=null && paymentId.trim().length()!=0){
                Debug.print("KaverySessionBean inside upgradeHorse paymentId : " + paymentId);
                result = dao.upgradeHorseDetails(horseMemberId, membershipTypeId, paymentId,membershipAmount,amount);
                result = (boolean)dao.deleteHorseServiceType(horseMemberId);
                Debug.print("KaverySessionBean delete HorseType : " + result);
                
                boolean serviceResult = false;
                if(serviceTypeId!=null && serviceTypeId.trim().length()!=0 && horseMemberId!=null && horseMemberId.trim().length()!=0){
                    serviceResult = dao.insertServiceDetails(horseMemberId, serviceTypeId);
                }
                Debug.print("Service Type Add in Upgrade:" + serviceResult);
            }
        }catch(Exception e){
            Debug.print("Exception in upgradeHorse:" + e);
        }
        return result;
    }
    
    public boolean upgrade_FEH_YEH_Horse(String horseMemberId, String membershipTypeId, String paymentId, String serviceTypeId, String membershipAmount) throws RemoteException {
        Debug.print("KaverySessionBean upgrade_FEH_YEH_Horse : " + horseMemberId);
        boolean result  = false;
        HLCHorseRegDAO dao = new HLCHorseRegDAO();
        try {
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 && membershipTypeId!=null && membershipTypeId.trim().length()!=0
                    && paymentId!=null && paymentId.trim().length()!=0){
                Debug.print("KaverySessionBean inside upgrade_FEH_YEH_Horse paymentId : " + paymentId);
                result = dao.upgradeHorseDetails(horseMemberId, membershipTypeId, paymentId,membershipAmount,"0");
                result = (boolean)dao.deleteHorseServiceType(horseMemberId);
                Debug.print("KaverySessionBean delete HorseType : " + result);
            }
        }catch(Exception e){
            Debug.print("Exception in upgrade_FEH_YEH_Horse:" + e);
        }
        return result;
    }    
    
    
     public boolean upgradeNonRegisteredHorse(String horseMemberId, String membershipTypeId, String paymentId,String membershipAmount, String totalAmount) throws RemoteException {
        Debug.print("KaverySessionBean upgradeNonRegisteredHorse : " + horseMemberId);
        boolean result  = false;
        HLCHorseRegDAO dao = new HLCHorseRegDAO();
        try {
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 && membershipTypeId!=null && membershipTypeId.trim().length()!=0
                    && paymentId!=null && paymentId.trim().length()!=0){
                Debug.print("KaverySessionBean inside upgradeNonRegisteredHorse paymentId : " + paymentId);
                result = dao.upgradeNonRegisteredDetails(horseMemberId, membershipTypeId, paymentId,membershipAmount,totalAmount);
              
            }
        }catch(Exception e){
            Debug.print("Exception in upgradeHorse:" + e);
        }
        return result;
    }
    
    
    public boolean updateHorseDetailsByOwner(String horseMemberId,String riderId, String prevRiderId, String  addRiderId, HLCHorseDescriptionVO objHrDescVO) throws RemoteException {
        Debug.print("KaverySessionBean updateHorseDetailsByOwner horseMemberId: " + horseMemberId);
        boolean result  = false;
        HLCHorseRegDAO dao = new HLCHorseRegDAO();
        try {
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 && objHrDescVO!=null ){
                Debug.print("KaverySessionBean inside updateHorseDetailsByOwner : " + horseMemberId);
                result = dao.updateHorseDetails(horseMemberId, riderId, prevRiderId, addRiderId);
                Debug.print("KaverySessionBean After Horse Update  : " + result);
                if(result==true){
                    result = dao.updateDescriptionDetails(horseMemberId, objHrDescVO);
                }
                Debug.print("KaverySessionBean Update Horse Description Status  : " + result);
            }
        }catch(Exception e){
            Debug.print("Exception in upgradeHorse:" + e);
        }
        Debug.print("KaverySessionBean Final Horse Update Status  : " + result);
        return result;
    }
    
    public boolean updateHorseDescriptionByOwnerOrRider(String horseMemberId, HLCHorseDescriptionVO objHrDescVO) throws RemoteException {
        Debug.print("KaverySessionBean updateHorseDescriptionByOwnerOrRider horseMemberId: " + horseMemberId);
        boolean result  = false;
        HLCHorseRegDAO dao = new HLCHorseRegDAO();
        try {
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 && objHrDescVO!=null ){
                Debug.print("KaverySessionBean inside updateHorseDetailsByOwner : " + horseMemberId);
                result = dao.updateDescriptionDetails(horseMemberId, objHrDescVO);
                Debug.print("KaverySessionBean Update updateHorseDescriptionByOwnerOrRider Status  : " + result);
            }
        }catch(Exception e){
            Debug.print("Exception in updateHorseDescriptionByOwnerOrRider:" + e);
        }
        Debug.print("KaverySessionBean Final Horse updateHorseDescriptionByOwnerOrRider  : " + result);
        return result;
    }
    
    
    public boolean deGradeOwnerRider(String relationId, String relationType) throws RemoteException{
        Debug.print("KaverySessionBean deGradeOwnerRider relationId: " + relationId);
        boolean result  = false;
        HLCHorseRegDAO dao = new HLCHorseRegDAO();
        String preOwnerRelId = "";
        if(relationType!=null && relationType.equalsIgnoreCase("Owner")){
            preOwnerRelId = dao.selectRelationTypeId("Previous Owner");
        } else if(relationType.equalsIgnoreCase("Rider")){
            preOwnerRelId = dao.selectRelationTypeId("Previous Rider");
        }
        if(relationType.equalsIgnoreCase("Trainer")){
            preOwnerRelId = dao.selectRelationTypeId("Previous Trainer");
        }
        try {
            if(relationId!=null && relationId.trim().length()!=0 && preOwnerRelId!=null){
                result = dao.degradeOwnerRider(relationId, preOwnerRelId);
                Debug.print("KaverySessionBean Update deGradeOwnerRider Status  : " + result);
            }
        }catch(Exception e){
            Debug.print("Exception in updateHorseDescriptionByOwnerOrRider:" + e);
        }
        Debug.print("KaverySessionBean Final Horse updateHorseDescriptionByOwnerOrRider  : " + result);
        return result;
    }
    
    public boolean updateOwnerwithRelationship(String relationId, String relationshipTypeId , String userId) throws RemoteException {
        Debug.print("KaverySessionBean updateOwnerwithRelationship horseMemberId: " + relationId);
        boolean result  = false;
        HLCHorseRegDAO dao = new HLCHorseRegDAO();
        try {
            if(relationId!=null && relationId.trim().length()!=0 && relationshipTypeId!=null && relationshipTypeId.trim().length()!=0
                    && userId!=null && userId.trim().length()!=0){
                Debug.print("KaverySessionBean inside updateOwnerwithRelationship : " + horseMemberId);
                result = dao.updateOwnerwithRelationship(relationId, relationshipTypeId, userId);
                
                Debug.print("KaverySessionBean Update updateOwnerwithRelationship Status  : " + result);
            }
        }catch(Exception e){
            Debug.print("Exception in updateOwnerwithRelationship:" + e);
        }
        Debug.print("KaverySessionBean Final Horse updateOwnerwithRelationship  : " + result);
        return result;
    }
    
    public boolean updateHorseUpgradeForNotRegisterHorse(String horseMemberId, String competitionName, String registereName, String baRegisteredName, String baPastName,
            String riderId, String prevRiderId, String  addRiderId, HLCHorseDescriptionVO objHrDescVO,String serviceId) throws RemoteException{
        Debug.print("KaverySessionBean updateHorseUpgradeForNotRegisterHorse horseMemberId: " + horseMemberId);
        boolean result  = false;
        HLCHorseRegDAO dao = new HLCHorseRegDAO();
        HLCHorseGradeVO objHrGradVO = new HLCHorseGradeVO();
        String riderRelId = dao.selectRelationTypeId("Rider");
        Debug.print("riderId:" + riderId);
        String riderUserId = dao.selectUserId(riderId);
        Debug.print("riderRelId:" + riderRelId);
        Debug.print("riderUserId:" + riderUserId);
        boolean rExist = dao.isRiderExistInHrMember(horseMemberId,riderId);
        Debug.print("rExist:" + rExist);
        String exRiderId = dao.getRiderIdFromHrMember(horseMemberId);
        Debug.print("exRiderId:" + exRiderId);
        String exRiderUserId = "";
        if(exRiderId!=null && exRiderId.trim().length()!=0) {
            exRiderUserId = dao.selectUserId(exRiderId);
        }
        Debug.print("exRiderUserId:" + exRiderUserId);
        
        if(rExist==false && horseMemberId!=null){
            boolean riderExist = dao.isRiderExist(horseMemberId,riderRelId, exRiderUserId);
            if(riderExist==true){
                String preOwnerRelId = dao.selectRelationTypeId("Previous Rider");
                boolean upRider = dao.updateRiderDetails(horseMemberId, riderRelId, exRiderUserId, preOwnerRelId);
                Debug.print("Rider Changed Sucessfully in Relationship table:" + upRider);
            }
           
            HLCHorseRelationVO objHrRelRiderVO = new HLCHorseRelationVO();
            objHrRelRiderVO.setHorseMemberId(horseMemberId);
            objHrRelRiderVO.setRelationshipTypeId(riderRelId);
            objHrRelRiderVO.setUserId(riderUserId);
            objHrRelRiderVO.setRelationshipStatus("Pending");
            boolean horRelResult = dao.insertHorseRelationDetails(objHrRelRiderVO);
            Debug.print("Rider inserted Sucessfully in Relationship table:" + horRelResult);
        }

       boolean delServiceresult = (boolean)dao.deleteHorseServiceType(horseMemberId);
       Debug.print("KaverySessionBean delete HorseType : " + delServiceresult);
       boolean serviceResult = false;
        if(serviceId!=null && serviceId.trim().length()!=0 && horseMemberId!=null && horseMemberId.trim().length()!=0){
            serviceResult = dao.insertServiceDetails(horseMemberId, serviceId);
        }
        Debug.print(" serviceResult:" + serviceResult); 
        try {
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 && objHrDescVO!=null ){
                Debug.print("KaverySessionBean inside updateHorseDetailsByAdmin : " + horseMemberId);
                result = dao.updateHorseDetailsByAdmin(horseMemberId, competitionName, registereName, baRegisteredName, baPastName,
                        riderId, prevRiderId, addRiderId);
                Debug.print("KaverySessionBean After Horse Update  : " + result);
                if(result==true){
                    result = dao.updateDescriptionDetails(horseMemberId, objHrDescVO);
                }
                Debug.print("KaverySessionBean updateHorseDetailsByAdmin  : " + result);
            }
             if(horseMemberId!=null && horseMemberId.trim().length()!=0){
                objHrGradVO.setHorseMemberId(horseMemberId);
                result = dao.insertHorseGradeDetails(objHrGradVO);
            }
        }catch(Exception e){
            e.printStackTrace();
            Debug.print("Exception in updateHorseDetailsByAdmin:" + e);
        }
        Debug.print("KaverySessionBean Final updateHorseDetailsByAdmin : " + result);
        return result;
    }
    
    
    public boolean updateHorseDetailsByAdmin(String horseMemberId, String competitionName, String registereName, String baRegisteredName, String baPastName,
            String riderId, String prevRiderId, String  addRiderId, HLCHorseDescriptionVO objHrDescVO) throws RemoteException{
        Debug.print("KaverySessionBean updateHorseDetailsByAdmin horseMemberId: " + horseMemberId);
        boolean result  = false;
        HLCHorseRegDAO dao = new HLCHorseRegDAO();
        
        String riderRelId = dao.selectRelationTypeId("Rider");
        String riderUserId = dao.selectUserId(riderId);
        boolean rExist = dao.isRiderExistInHrMember(horseMemberId,riderId);
        String exRiderId = dao.getRiderIdFromHrMember(horseMemberId);
        String exRiderUserId = "";
        if(exRiderId!=null && exRiderId.trim().length()!=0) {
            exRiderUserId = dao.selectUserId(exRiderId);
        }
        
        if(rExist==false && horseMemberId!=null){
            boolean riderExist = dao.isRiderExist(horseMemberId,riderRelId, exRiderUserId);
            if(riderExist==true){
                String preOwnerRelId = dao.selectRelationTypeId("Previous Rider");
                boolean upRider = dao.updateRiderDetails(horseMemberId, riderRelId, exRiderUserId, preOwnerRelId);
                Debug.print("Rider Changed Sucessfully in Relationship table:" + upRider);
            }
            
            HLCHorseRelationVO objHrRelRiderVO = new HLCHorseRelationVO();
            objHrRelRiderVO.setHorseMemberId(horseMemberId);
            objHrRelRiderVO.setRelationshipTypeId(riderRelId);
            objHrRelRiderVO.setUserId(riderUserId);
            objHrRelRiderVO.setRelationshipStatus("Active");
            boolean horRelResult = dao.insertHorseRelationDetails(objHrRelRiderVO);
            Debug.print("Rider inserted Sucessfully in Relationship table:" + horRelResult);
        }
        
        try {
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 && objHrDescVO!=null ){
                Debug.print("KaverySessionBean inside updateHorseDetailsByAdmin : " + horseMemberId);
                result = dao.updateHorseDetailsByAdmin(horseMemberId, competitionName, registereName, baRegisteredName, baPastName,
                        riderId, prevRiderId, addRiderId);
                Debug.print("KaverySessionBean After Horse Update  : " + result);
                if(result==true){
                    result = dao.updateDescriptionDetailsByAdmin(horseMemberId, objHrDescVO);
                }
                Debug.print("KaverySessionBean updateHorseDetailsByAdmin  : " + result);
            }
        }catch(Exception e){
            Debug.print("Exception in updateHorseDetailsByAdmin:" + e);
        }
        Debug.print("KaverySessionBean Final updateHorseDetailsByAdmin : " + result);
        return result;
    }
    public boolean updateRequestHorseDetailsByAdmin(String horseMemberId, String relationId, String competitionName, String registereName,
            String riderId, String ownerId, HLCHorseUserVO objMainOwner, boolean companyStatus, boolean ownerStatus) throws RemoteException{
        
        Debug.print("KaverySessionBean updateRequestHorseDetailsByAdmin horseMemberId: " + horseMemberId);
        HLCHorseRegDAO dao = new HLCHorseRegDAO();
        boolean result  = false;
        
        try {
            if((horseMemberId!=null && horseMemberId.trim().length()!=0) && ((competitionName!=null && competitionName.trim().length()!=0) ||
                    (registereName!=null && registereName.trim().length()!=0))){
                Debug.print("KaverySessionBean inside updateRequestHorseDetailsByAdmin : " + horseMemberId);
                result = dao.updateRequetsHorseDetailsByAdmin(horseMemberId, competitionName, registereName);
                Debug.print("KaverySessionBean updateRequetsHorseDetailsByAdmin  : " + result);
            }
            
            //============================
            if(riderId!=null && riderId.trim().length()!=0){
                Debug.print("Rider Changing Process Started..");
                String riderRelId = dao.selectRelationTypeId("Rider");
                String riderUserId = dao.selectUserId(riderId);
                Debug.print("Rider User Id:" + riderUserId);
                boolean rExist = dao.isRiderExistInHrMember(horseMemberId,riderId);
                Debug.print("Rider already Exist:" + rExist);
                String exRiderId = dao.getRiderIdFromHrMember(horseMemberId);
                String exRiderUserId = "";
                
                if(exRiderId!=null && exRiderId.trim().length()!=0) {
                    exRiderUserId = dao.selectUserId(exRiderId);
                }
                
                if(rExist==false){
                    Debug.print("horseMemberId:" + horseMemberId);
                    Debug.print("riderRelId:" + riderRelId);
                    Debug.print("exRiderUserId:" + exRiderUserId);
                    boolean riderExist = dao.isRiderExist(horseMemberId,riderRelId, exRiderUserId);
                    Debug.print("Rider Exist in tblHorseMemberRelationDetails: " + riderExist);
                    
                    if(riderExist==true){
                        String preOwnerRelId = dao.selectRelationTypeId("Previous Rider");
                        boolean upRider = dao.updateRiderDetails(horseMemberId, riderRelId, exRiderUserId, preOwnerRelId);
                        Debug.print("Rider Changed Sucessfully in Relationship table:" + upRider);
                    }
                    
                    HLCHorseRelationVO objHrRelRiderVO = new HLCHorseRelationVO();
                    objHrRelRiderVO.setHorseMemberId(horseMemberId);
                    objHrRelRiderVO.setRelationshipTypeId(riderRelId);
                    objHrRelRiderVO.setUserId(riderUserId);
                    objHrRelRiderVO.setRelationshipStatus("Active");
                    objHrRelRiderVO.setRequestId(relationId);
                    boolean horRelResult = dao.insertHorseRelationDetails(objHrRelRiderVO);
                    Debug.print("Rider inserted Sucessfully in Relationship table:" + horRelResult);
                }
            }
            
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 && riderId!=null && riderId.trim().length()!=0){
                Debug.print("KaverySessionBean inside updateRequestHorseDetailsByAdmin : " + horseMemberId);
                result = dao.updateRequetsHorseDetailsForRider(horseMemberId, riderId);
                Debug.print("KaverySessionBean updateRequetsHorseDetailsForRider  : " + result);
            }
            //============================
            ///////////////
            if((ownerStatus ==true) && (horseMemberId!=null && horseMemberId.trim().length()!=0) && (((ownerId==null || ownerId.trim().length()==0) && objMainOwner.getUserId()== null))){
                Debug.print("Inside Owner Id:" + ownerId);
                if(companyStatus==true){
                    ownerId = dao.insertUserDetails(objMainOwner.getFirstName(), objMainOwner.getLastName(), null,
                            null, objMainOwner.getEmailId(), objMainOwner.getLoginName());
                } else{
                    ownerId = dao.insertUserDetails(objMainOwner.getFirstName(), objMainOwner.getLastName(), objMainOwner.getDob(),
                            objMainOwner.getGender(), objMainOwner.getEmailId(), null);
                }
                
                Debug.print("Owner Registration Id:" + ownerId);
                
                boolean userReuslt = dao.insertContactDetails(ownerId, objMainOwner.getAddress(), objMainOwner.getCity(),
                        objMainOwner.getState(), objMainOwner.getCountry(), objMainOwner.getZip(), objMainOwner.getPhoneNo(),
                        objMainOwner.getFaxNo());
                Debug.print("New Owner Registration :" + userReuslt);
            }
            
            if(ownerStatus ==true && ownerId!=null && ownerId.trim().length()!=0) {
                Debug.print("   horseMemberId" + horseMemberId);
                Debug.print("   ownerId" + ownerId);
                String ownerRelId = dao.selectRelationTypeId("Owner");
                String preOwnerRelId = dao.selectRelationTypeId("Previous Owner");
                String priOwnerId = getOwnerIdFromHrMember(horseMemberId);
                String ownRelationId = "";
                if(priOwnerId!=null && priOwnerId.trim().length()!=0){
                    ownRelationId = getRelationId(horseMemberId, priOwnerId, ownerRelId);
                }
                
                if(ownRelationId!=null && ownRelationId.trim().length()!=0 && preOwnerRelId!=null && preOwnerRelId.trim().length()!=0
                        && priOwnerId!=null && priOwnerId.trim().length()!=0){
                    boolean upResult = updateOwnerwithRelationship(ownRelationId, preOwnerRelId, priOwnerId);
                    Debug.print("Update Owner details in Relationship table:" + upResult);
                }
                
                HLCHorseRelationVO objHrRelVO = new HLCHorseRelationVO();
                objHrRelVO.setHorseMemberId(horseMemberId);
                objHrRelVO.setRelationshipTypeId(ownerRelId);
                objHrRelVO.setUserId(ownerId);
                objHrRelVO.setRelationshipStatus("Active");
                objHrRelVO.setRequestId(relationId);
                boolean horOwnResult = dao.insertHorseRelationDetails(objHrRelVO);
                
                if(horseMemberId!=null && horseMemberId.trim().length()!=0 && ownerId!=null && ownerId.trim().length()!=0){
                    result = dao.updateOwnerDetails(horseMemberId, ownerId);
                }
                Debug.print("   editHorseOwnerDetails Result:" + result);
            }
            //////////////
            
            if(relationId!=null && relationId.trim().length()!=0){
                result = dao.updateRequestStatus(relationId);
                Debug.print("KaverySessionBean updateRequestStatus  : " + result);
            }
            
        }catch(Exception e){
            Debug.print("Exception in updateRequestHorseDetailsByAdmin:" + e);
        }
        Debug.print("KaverySessionBean Final updateRequestHorseDetailsByAdmin : " + result);
        return result;
    }
    
    
    public boolean updateRequestHorseDetailsByAdmin(String horseMemberId, String relationId, String competitionName, String registereName,
            String riderId, String ownerId, HLCHorseUserVO objMainOwner, boolean companyStatus, boolean ownerStatus, String oldCompetitionName) throws RemoteException{
        
        Debug.print("KaverySessionBean updateRequestHorseDetailsByAdmin horseMemberId: " + horseMemberId);
        HLCHorseRegDAO dao = new HLCHorseRegDAO();
        boolean result  = false;
        
        try {
            if((horseMemberId!=null && horseMemberId.trim().length()!=0) && ((competitionName!=null && competitionName.trim().length()!=0) ||
                    (registereName!=null && registereName.trim().length()!=0))){
                Debug.print("KaverySessionBean inside updateRequestHorseDetailsByAdmin : " + horseMemberId);
                result = dao.updateRequetsHorseDetailsByAdmin(horseMemberId, competitionName, registereName, oldCompetitionName);
                Debug.print("KaverySessionBean updateRequetsHorseDetailsByAdmin  : " + result);
            }
            
            //============================
            if(riderId!=null && riderId.trim().length()!=0){
                Debug.print("Rider Changing Process Started..");
                String riderRelId = dao.selectRelationTypeId("Rider");
                String riderUserId = dao.selectUserId(riderId);
                Debug.print("Rider User Id:" + riderUserId);
                boolean rExist = dao.isRiderExistInHrMember(horseMemberId,riderId);
                Debug.print("Rider already Exist:" + rExist);
                String exRiderId = dao.getRiderIdFromHrMember(horseMemberId);
                String exRiderUserId = "";
                
                if(exRiderId!=null && exRiderId.trim().length()!=0) {
                    exRiderUserId = dao.selectUserId(exRiderId);
                }
                
                if(rExist==false){
                    Debug.print("horseMemberId:" + horseMemberId);
                    Debug.print("riderRelId:" + riderRelId);
                    Debug.print("exRiderUserId:" + exRiderUserId);
                    boolean riderExist = dao.isRiderExist(horseMemberId,riderRelId, exRiderUserId);
                    Debug.print("Rider Exist in tblHorseMemberRelationDetails: " + riderExist);
                    
                    if(riderExist==true){
                        String preOwnerRelId = dao.selectRelationTypeId("Previous Rider");
                        boolean upRider = dao.updateRiderDetails(horseMemberId, riderRelId, exRiderUserId, preOwnerRelId);
                        Debug.print("Rider Changed Sucessfully in Relationship table:" + upRider);
                    }
                    
                    HLCHorseRelationVO objHrRelRiderVO = new HLCHorseRelationVO();
                    objHrRelRiderVO.setHorseMemberId(horseMemberId);
                    objHrRelRiderVO.setRelationshipTypeId(riderRelId);
                    objHrRelRiderVO.setUserId(riderUserId);
                    objHrRelRiderVO.setRelationshipStatus("Active");
                    objHrRelRiderVO.setRequestId(relationId);
                    boolean horRelResult = dao.insertHorseRelationDetails(objHrRelRiderVO);
                    Debug.print("Rider inserted Sucessfully in Relationship table:" + horRelResult);
                }
            }
            
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 && riderId!=null && riderId.trim().length()!=0){
                Debug.print("KaverySessionBean inside updateRequestHorseDetailsByAdmin : " + horseMemberId);
                result = dao.updateRequetsHorseDetailsForRider(horseMemberId, riderId);
                Debug.print("KaverySessionBean updateRequetsHorseDetailsForRider  : " + result);
            }
            //============================
            ///////////////
            if((ownerStatus ==true) && (horseMemberId!=null && horseMemberId.trim().length()!=0) && (((ownerId==null || ownerId.trim().length()==0) && objMainOwner.getUserId()== null))){
                Debug.print("Inside Owner Id:" + ownerId);
                if(companyStatus==true){
                    ownerId = dao.insertUserDetails(objMainOwner.getFirstName(), objMainOwner.getLastName(), null,
                            null, objMainOwner.getEmailId(), objMainOwner.getLoginName());
                } else{
                    ownerId = dao.insertUserDetails(objMainOwner.getFirstName(), objMainOwner.getLastName(), objMainOwner.getDob(),
                            objMainOwner.getGender(), objMainOwner.getEmailId(), null);
                }
                
                Debug.print("Owner Registration Id:" + ownerId);
                
                boolean userReuslt = dao.insertContactDetails(ownerId, objMainOwner.getAddress(), objMainOwner.getCity(),
                        objMainOwner.getState(), objMainOwner.getCountry(), objMainOwner.getZip(), objMainOwner.getPhoneNo(),
                        objMainOwner.getFaxNo());
                Debug.print("New Owner Registration :" + userReuslt);
            }
            
            if(ownerStatus ==true && ownerId!=null && ownerId.trim().length()!=0) {
                Debug.print("   horseMemberId" + horseMemberId);
                Debug.print("   ownerId" + ownerId);
                String ownerRelId = dao.selectRelationTypeId("Owner");
                String preOwnerRelId = dao.selectRelationTypeId("Previous Owner");
                String priOwnerId = getOwnerIdFromHrMember(horseMemberId);
                String ownRelationId = "";
                if(priOwnerId!=null && priOwnerId.trim().length()!=0){
                    ownRelationId = getRelationId(horseMemberId, priOwnerId, ownerRelId);
                }
                
                if(ownRelationId!=null && ownRelationId.trim().length()!=0 && preOwnerRelId!=null && preOwnerRelId.trim().length()!=0
                        && priOwnerId!=null && priOwnerId.trim().length()!=0){
                    boolean upResult = updateOwnerwithRelationship(ownRelationId, preOwnerRelId, priOwnerId);
                    Debug.print("Update Owner details in Relationship table:" + upResult);
                }
                
                HLCHorseRelationVO objHrRelVO = new HLCHorseRelationVO();
                objHrRelVO.setHorseMemberId(horseMemberId);
                objHrRelVO.setRelationshipTypeId(ownerRelId);
                objHrRelVO.setUserId(ownerId);
                objHrRelVO.setRelationshipStatus("Active");
                objHrRelVO.setRequestId(relationId);
                boolean horOwnResult = dao.insertHorseRelationDetails(objHrRelVO);
                
                if(horseMemberId!=null && horseMemberId.trim().length()!=0 && ownerId!=null && ownerId.trim().length()!=0){
                    result = dao.updateOwnerDetails(horseMemberId, ownerId);
                }
                Debug.print("   editHorseOwnerDetails Result:" + result);
            }
            //////////////
            
            if(relationId!=null && relationId.trim().length()!=0){
                result = dao.updateRequestStatus(relationId);
                Debug.print("KaverySessionBean updateRequestStatus  : " + result);
            }
            
        }catch(Exception e){
            Debug.print("Exception in updateRequestHorseDetailsByAdmin:" + e);
        }
        Debug.print("KaverySessionBean Final updateRequestHorseDetailsByAdmin : " + result);
        return result;
    }
    
    
    
    public String getUserTypeName(String userTypeId) throws RemoteException {
        Debug.print("KaverySessionBean getUserTypeName userTypeId: " + userTypeId);
        String userName  = "";
        try {
            userName = new HLCHorseRegDAO().selectUserTypeName(userTypeId);
        }catch(Exception e){
            Debug.print("Exception in getUserTypeName:" + e);
        }
        return userName;
    }
    
    public String getMemberIdForRider(String userId) throws RemoteException {
        Debug.print("KaverySessionBean getMemberId: " + userId);
        String memberId  = "";
        try {
            memberId = new HLCHorseRegDAO().selectMemberId(userId);
        }catch(Exception e){
            Debug.print("Exception in getMemberId:" + e);
        }
        return memberId;
    }
    
    
    public boolean updatePaymentStatus(com.hlcreg.util.HLCPaymentVO objPaymentVO) throws RemoteException{
        Debug.print("KaverySessionBean updatePaymentStatus: ");
        boolean result  = false;
        try {
            result = new HLCHorseRegDAO().updatePaymentStatus(objPaymentVO);
        }catch(Exception e){
            Debug.print("Exception in updatePaymentStatus:" + e);
        }
        return result;
    }
    
    
    public boolean updateRealtionshipStatus(String horseMemberId) throws RemoteException {
        Debug.print("KaverySessionBean updateRealtionshipStatus: ");
        boolean result  = false;
        try {
            if(horseMemberId!=null && horseMemberId.trim().length()!=0) {
                result = new HLCHorseRegDAO().updateRealtionshipStatus(horseMemberId);
            }
        }catch(Exception e){
            Debug.print("Exception in updateRealtionshipStatus:" + e);
        }
        return result;
    }
    
    public String getHorseEditCharge() throws RemoteException {
        String price  = "";
        try {
            price = new HLCHorseRegDAO().selectHorseEditCharge();
        }catch(Exception e){
            Debug.print("Exception in getHorseEditCharge:" + e);
        }
        return price;
    }
    
    //=============================================select a All Country price Details =========================================
    public String selectHorseServiceTypeId(String typName) throws RemoteException{
        Debug.print("HorseRegDAO.selectHorseServiceTypeId() ");
        Debug.print("typName :"+typName);
        
        String typId  = "";
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select horse_service_type_id from tblHorseServiceTypeMaster " +
                    "where horse_service_type_name = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,typName);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next()){
                typId = rs.getString(1);
            }
            rs.close();
            prepStmt.close();
            Debug.print("HorseServiceTypeId :"+typId);
            
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in selectHorseServiceTypeId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in selectHorseServiceTypeId():" + e.getMessage());
        }
        finally
        {
            releaseConnection();
        }
        return typId;
    }
    
    public String[] getHorseServiceCharge() throws RemoteException {
        String price[]  = {};
        try {
            price = (String[])new HLCHorseRegDAO().selectHorseServiceCharge();
        }catch(Exception e){
            Debug.print("Exception in getHorseServiceCharge:" + e);
        }
        return price;
    }
    
    public String[] getAllEmailIds(String horseMemberId) throws RemoteException {
        String emailIds[]  = {};
        try {
            if(horseMemberId!=null && horseMemberId.trim().length()!=0){
                emailIds = (String[])new HLCHorseRegDAO().selectEmailIds(horseMemberId);
            }
        }catch(Exception e){
            Debug.print("Exception in getAllEmailIds:" + e);
        }
        return emailIds;
    }
    
    public String getOwnerIdFromHrMember(String hrMemberId) throws RemoteException {
        Debug.print("KaverySessionBean getOwnerIdFromHrMember hrMemberId: " + hrMemberId);
        String ownerId  = "";
        try {
            ownerId = new HLCHorseRegDAO().selectOwnerIdFromHrMember(hrMemberId);
        }catch(Exception e){
            Debug.print("Exception in getOwnerIdFromHrMember:" + e);
        }
        return ownerId;
    }
    
    public String getTrainerIdFromHrMember(String hrMemberId) throws RemoteException {
        Debug.print("KaverySessionBean getTrainerIdFromHrMember hrMemberId: " + hrMemberId);
        String ownerId  = "";
        try {
            ownerId = new HLCHorseRegDAO().selectTrainerIdFromHrMember(hrMemberId);
        }catch(Exception e){
            Debug.print("Exception in getTrainerIdFromHrMember:" + e);
        }
        return ownerId;
    }    
    
    public String getRelationId(String hrMemberId, String userId, String relationshipId) throws RemoteException {
        Debug.print("KaverySessionBean getRelationId hrMemberId: " + hrMemberId);
        String realtionId  = "";
        try {
            realtionId = new HLCHorseRegDAO().selectRelationIdFromMemberIdUserIdRelationId(hrMemberId, userId, relationshipId);
        }catch(Exception e){
            Debug.print("Exception in getRelationId:" + e);
        }
        return realtionId;
    }
    
    public HLCHorseRegisterationVO getHorseDetailsByHorseMemberId(String horseMemberId) throws RemoteException {
        Debug.print("KaverySessionBean getHorseDetailsByHorseMemberId horseMenberId: " + horseMemberId);
        HLCHorseRegisterationVO objHrRegDet  = new HLCHorseRegisterationVO();
        try {
            if(horseMemberId!=null && horseMemberId.trim().length()!=0){
                objHrRegDet = (HLCHorseRegisterationVO)new HLCHorseRegDAO().selectHorseDetailsByHorseMemberId(horseMemberId);
            }
        }catch(Exception e){
            Debug.print("Exception in getHorseDetailsByHorseMemberId:" + e);
        }
        return objHrRegDet;
    }
    
    public InitialContext getInitialContext() throws javax.naming.NamingException {
        if( this.ic == null ) {
            ic = new InitialContext();
        }
        System.out.println("This is from getInitialContext()");
        return ic;
    }
    
    public String getNextId() throws RemoteException {
        Debug.print("KaverySessionBean Payment getNextId ");
        String nextPaymentId = "";
        try {
            nextPaymentId = new HLCHorseRegDAO().getNextId();
        } catch(Exception e){
            Debug.print("Exception in getAllSpecification:" + e);
        }
        return nextPaymentId;
    }
    
    public String getStatusId(String statusName) throws RemoteException{
        String statusId = "";
        if(statusName!=null && statusName.trim().length()!=0){
            statusId = new HLCHorseRegDAO().selectStatusId(statusName);
        }
        return statusId;
    }
    
    public boolean updateRequestStatus(String horseMemberId, String requestStatus, String comments, java.util.Date actDate) throws RemoteException{
        Debug.print("KaverySessionBean updateRequestStatus horseMemberId "+ horseMemberId);
        boolean result = false;
        if(horseMemberId!=null && horseMemberId.trim().length()!=0){
            result = new HLCHorseRegDAO().updateRequestStatus(horseMemberId,requestStatus, comments, actDate);
        }
        return result;
    }
    
    public boolean updateRequestStatus(String requestId) throws RemoteException{
        Debug.print("KaverySessionBean updateRequestStatus requestId "+ requestId);
        boolean result = false;
        if(requestId!=null && requestId.trim().length()!=0){
            result = new HLCHorseRegDAO().updateRequestStatus(requestId);
        }
        return result;
    }
    
    public boolean updateRequestStatusForAdmin(String requestId) throws RemoteException{
        Debug.print("KaverySessionBean updateRequestStatus requestId "+ requestId);
        boolean result = false;
        if(requestId!=null && requestId.trim().length()!=0){
            result = new HLCHorseRegDAO().updateRequestStatusForAdmin(requestId);
        }
        return result;
    }
    
    public boolean updatePaymentCheckAmount(float checkAmount, String paymentId) throws RemoteException {
        Debug.print("KaverySessionBean updateRequestStatus horseMemberId "+ horseMemberId);
        boolean result = false;
        if(paymentId!=null && paymentId.trim().length()!=0 ){
            result = new HLCHorseRegDAO().updatePaymentCheckAmount(checkAmount,paymentId);
        }
        return result;
    }
    
    public boolean isHorseExist(String horseName) throws RemoteException {
        Debug.print("KaverySessionBean isHorseExist horseName "+ horseName);
        boolean result = false;
        if(horseName!=null && horseName.trim().length()!=0 ){
            result = new HLCHorseRegDAO().isHorseExist(horseName);
        }
        
        Debug.print("KaverySessionBean isHorseExist():"+ result);
        return result;
    }
    
    public boolean isHorsePriOwnerExist(String horseMemberId, String userId) throws RemoteException {
        Debug.print("KaverySessionBean isHorsePriOwnerExist horseMemberId "+ horseMemberId);
        boolean result = false;
        if(horseMemberId!=null && horseMemberId.trim().length()!=0 && userId!=null && userId.trim().length()!=0 ){
            result = new HLCHorseRegDAO().isHorsePriOwnerExist(horseMemberId, userId);
        }
        Debug.print("KaverySessionBean isHorsePriOwnerExist():"+ result);
        return result;
    }
    
    public boolean isHorsePriRiderExist(String horseMemberId, String userId) throws RemoteException {
        Debug.print("KaverySessionBean isHorsePriRiderExist horseMemberId "+ horseMemberId);
        boolean result = false;
        if(horseMemberId!=null && horseMemberId.trim().length()!=0 && userId!=null && userId.trim().length()!=0 ){
            result = new HLCHorseRegDAO().isHorsePriRiderExist(horseMemberId, userId);
        }
        Debug.print("KaverySessionBean isHorsePriRiderExist():"+ result);
        return result;
    }
    
    public boolean isHorsePriTrainerExist(String horseMemberId,String userId,String relationId) throws RemoteException {
        Debug.print("KaverySessionBean isHorsePriTrainerExist horseMemberId "+ horseMemberId+" User Id "+userId);
        boolean result = false;
        String trainerRelId = new HLCHorseRegDAO().selectRelationTypeId("Trainer");
        
        if(horseMemberId!=null && horseMemberId.trim().length()!=0){
                result = new HLCHorseRegDAO().isHorsePriTrainerExist(horseMemberId,trainerRelId);
        }
        
        boolean date_update = new HLCHorseRegDAO().updateTrainerRelationDateInfo(relationId, userId, horseMemberId);
        Debug.print("Date_update is "+date_update);        
        
        Debug.print("KaverySessionBean isHorsePriOwnerExist():"+ result);
        return result;
    }
    
    public boolean isHorsePriOwnerRiderExistInRelation(String relationId, String userId) throws RemoteException {
        Debug.print("KaverySessionBean isHorsePriOwnerRiderExistInRelation horseMemberId "+ relationId);
        boolean result = false;
        if(relationId!=null && relationId.trim().length()!=0 && userId!=null && userId.trim().length()!=0 ){
            result = new HLCHorseRegDAO().isHorsePriOwnerRiderExistInRelation(relationId, userId);
        }
        Debug.print("KaverySessionBean isHorsePriOwnerRiderExistInRelation():"+ result);
        return result;
    }
    public boolean statusChangeForHorse(String horseMemberId, java.util.Date actDate) throws RemoteException{
        Debug.print("KaverySessionBean statusChangeForHorse horseMemberId: " + horseMemberId);
        HLCHorseRegDAO dao = new HLCHorseRegDAO();
        boolean result = false;
        try {
            if(horseMemberId!=null && horseMemberId.trim().length()!=0){
                String statusId = dao.getStatusName("Active");
                Debug.print("Status Id for Active:" + statusId);
                if(statusId!=null && statusId.trim().length()!=0) {
                    result = dao.updateStatusId(horseMemberId, statusId, actDate);
                    Debug.print("Status Change Result:" + result);
                }
            }
        }catch(Exception e){
            Debug.print("Exception in statusChangeForHorse:" + e);
        }
        return result;
    }
    
    public ArrayList getAllHorseDetailsByOwnerId(String ownerId, int rCnt) throws RemoteException {
        Debug.print("KaverySessionBean getAllHorseDetailsByOwnerId ownerId: " + ownerId);
        ArrayList horseDetList  = new ArrayList();
        try {
            horseDetList = (ArrayList)new HLCHorseRegDAO().selectAllHorseDetailsByOwnerId(ownerId,rCnt);
        }catch(Exception e){
            Debug.print("Exception in getAllHorseDetailsByOwnerId:" + e);
        }
        return horseDetList;
    }
    
    public ArrayList getAllPendingPaymentsForHorse(String ownerId) throws RemoteException {
        Debug.print("KaverySessionBean getAllHorseDetailsByOwnerId ownerId: " + ownerId);
        ArrayList horseDetList  = new ArrayList();
        try {
            horseDetList = (ArrayList)new HLCHorseRegDAO().selectAllPendingPaymentsForHorse(ownerId);
        }catch(Exception e){
            Debug.print("Exception in getAllHorseDetailsByOwnerId:" + e);
        }
        return horseDetList;
    }
    
    public ArrayList getAllPendingPaymentsForHorseUser(String userId) throws RemoteException {
        Debug.print("KaverySessionBean getAllPendingPaymentsForHorseUser userId: " + userId);
        ArrayList horseDetList  = new ArrayList();
        try {
            horseDetList = (ArrayList)new HLCHorseRegDAO().selectAllPendingPaymentsForHorseUser(userId);
        }catch(Exception e){
            Debug.print("Exception in getAllPendingPaymentsForHorseUser:" + e);
        }
        return horseDetList;
    }
    
    public ArrayList getAllDuePaymentsForHorseUser(String userId) throws RemoteException {
        Debug.print("KaverySessionBean getAllDuePaymentsForHorseUser userId: " + userId);
        ArrayList horseDetList  = new ArrayList();
        try {
            horseDetList = (ArrayList)new HLCHorseRegDAO().selectAllDuePaymentsForHorseUser(userId);
        }catch(Exception e){
            Debug.print("Exception in getAllDuePaymentsForHorseUser:" + e);
        }
        return horseDetList;
    }
    
    public ArrayList getAllDuePaymentsForHorse(String userId) throws RemoteException {
        Debug.print("KaverySessionBean selectAllDuePaymentsForHorse userId: " + userId);
        ArrayList horseDetList  = new ArrayList();
        try {
            horseDetList = (ArrayList)new HLCHorseRegDAO().selectAllDuePaymentsForHorse(userId);
        }catch(Exception e){
            Debug.print("Exception in selectAllDuePaymentsForHorse:" + e);
        }
        return horseDetList;
    }
    
    public ArrayList getHorseRequestForm(boolean status) throws RemoteException {
        Debug.print("KaverySessionBean getHorseRequestForm status: " + status);
        ArrayList horseDetList  = new ArrayList();
        try {
            horseDetList = (ArrayList)new HLCHorseRegDAO().selectHorseRequestForm(status);
        }catch(Exception e){
            Debug.print("Exception in getHorseRequestForm:" + e);
        }
        return horseDetList;
    }
                //----------------------------------------------------satheesh Start MMMA_0010: Human/Non-Human relationships -----------------------------------------------------------
   
	  public Vector displayUserTypeDetails() throws RemoteException, FinderException{
        Debug.print("KaverySessionBean getAllHorseBreedDetails**********************");

        Vector results = (Vector)new HLCHorseRegDAO().displayUserTypeDetails();
        Debug.print("After calling KaverySessionBean getAllHorseBreedDetails **********************");
        return results;
    }
	 
	 
	 
	 
	  
	 public ArrayList getHorseRequestForm1(String uTypeId) throws RemoteException {
        Debug.print("KaverySessionBean getHorseRequestForm status: " + uTypeId);
        ArrayList horseDetList  = new ArrayList();
        try {
            horseDetList = (ArrayList)new HLCHorseRegDAO().selectHorseRequestForm1(uTypeId);
        }catch(Exception e){
            Debug.print("Exception in getHorseRequestForm:" + e);
        }
        return horseDetList;
    }


 public boolean createHorseOwner(String relationDesc,String relationStatus,String SpecieId) throws RemoteException {
        Debug.print("KaverySessionBean createHorseBreed() breedDesc:" + relationDesc);
        boolean result = false;
        if(relationDesc!=null && relationDesc.trim().length()!=0 ){
            if(new HLCHorseRegDAO().isHorseBreedExist1(SpecieId,relationDesc)){
                //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
                result = new HLCHorseRegDAO().insertHorseBreed1(relationDesc,relationStatus,SpecieId);
                Debug.print("KaverySessionBean createHorseBreed Result:" +  result);
            }

        }
        return result;
    }

 public ArrayList getHorseRelationshipDetailsByRequestId1(String reqId) throws RemoteException {
        Debug.print("KaverySessionBean getHorseRelationshipDetailsByRequestId reqId: " + reqId);
        ArrayList horseRelaList  = new ArrayList();
        try {
            horseRelaList = (ArrayList)new HLCHorseRegDAO().selectHorseRelationshipDetailsByRequestId1(reqId);
        }catch(Exception e){
            Debug.print("Exception in getHorseRelationshipDetailsByRequestId:" + e);
        }
        return horseRelaList;
    }



 public boolean editHorserealtion(String realtion,String realtionStatus, String realtionId,String uTypeId) throws RemoteException {
        Debug.print("KaverySessionBean editHorseBreed() breedDesc:" +realtion);
        boolean result = false;
        if(realtionId!=null && realtionId.trim().length()!=0 && realtion!=null && realtion.trim().length()!=0 ){
            if(new HLCHorseRegDAO().isHorserealtionExist(realtion,realtionId,uTypeId)){
                //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
                 result = new HLCHorseRegDAO().updateHorserealtion(realtion,realtionStatus,realtionId);
                Debug.print("KaverySessionBean editHorseBreed Result:" +  result);
            }
        }
        return result;
    }


 public boolean deleteBreed(String  chkRoleIdArr[]) throws RemoteException {

        boolean result = false;
        if(chkRoleIdArr!=null   ){

                result = new HLCHorseRegDAO().deleteBreed(chkRoleIdArr);
                Debug.print("KaverySessionBean editHorseBreed Result:" +  result);

        }
        return result;
    }





   //----------------------------------------------------satheesh End MMMA_0010: Human/Non-Human relationships -----------------------------------------------------------

    public HLCRequestHorseDetVO getHorseRequestFormById(String reqId) throws RemoteException {
        Debug.print("KaverySessionBean getHorseRequestForm status: " + reqId);
        HLCRequestHorseDetVO horseDetList  = new HLCRequestHorseDetVO();
        try {
            horseDetList = (HLCRequestHorseDetVO)new HLCHorseRegDAO().selectHorseRequestFormById(reqId);
        }catch(Exception e){
            Debug.print("Exception in getHorseRequestForm:" + e);
        }
        return horseDetList;
    }
    
    
    public ArrayList getAllHorseDetailsByRiderId(String riderMemberId) throws RemoteException {
        Debug.print("KaverySessionBean getAllHorseDetailsByRiderId riderMemberId: " + riderMemberId);
        ArrayList horseDetList  = new ArrayList();
        try {
            horseDetList = (ArrayList)new HLCHorseRegDAO().selectAllHorseDetailsByRiderId(riderMemberId);
        }catch(Exception e){
            Debug.print("Exception in getAllHorseDetailsByRiderId:" + e);
        }
        return horseDetList;
    }
    
    public String[] getHorsePaymentDetails(String paymentId) throws RemoteException{
        Debug.print("KaverySessionBean getHorsePaymentDetails paymentId: " + paymentId);
        String  horsePaymentDet[]  = {};
        try {
            horsePaymentDet = (String[])new HLCHorseRegDAO().selectHorsePaymentDetails(paymentId);
        }catch(Exception e){
            Debug.print("Exception in getHorsePaymentDetails:" + e);
        }
        return horsePaymentDet;
    }
    
    public ArrayList getHorseChildPaymentDetailsByAdmin(String parentPaymentId) throws RemoteException{
        Debug.print("kaverySessionBean getHorseChildPaymentDetailsByAdmin parentPaymentId:" + parentPaymentId);
        ArrayList HorseChildPaymentDet = null;
        
        try{
            HorseChildPaymentDet = new HLCHorseRegDAO().selectHorseChildPaymentDetails(parentPaymentId);
        }
        catch(Exception childPayment){
            Debug.print("Exception in getHorseChildPaymentDetailsByAdmin:" + childPayment.getMessage());
            
        }
        return HorseChildPaymentDet;
    }
    
    public String[] getHorseAddRequestByReqId(String requestId) throws RemoteException{
        Debug.print("KaverySessionBean getHorseAddRequestByReqId requestId: " + requestId);
        String  horsePaymentDet[]  = {};
        try {
            horsePaymentDet = (String[])new HLCHorseRegDAO().selectHorseAddRequestByReqId(requestId);
        }catch(Exception e){
            Debug.print("Exception in getHorseAddRequestByReqId:" + e);
        }
        return horsePaymentDet;
    }
    
    public ArrayList getHorseRelationshipDetailsDetails(String horseMemberId) throws RemoteException {
        Debug.print("KaverySessionBean getHorseRelationshipDetailsDetails horseMemberId: " + horseMemberId);
        ArrayList horseRelaList  = new ArrayList();
        try {
            horseRelaList = (ArrayList)new HLCHorseRegDAO().selectHorseRelationshipDetailsDetails(horseMemberId);
        }catch(Exception e){
            Debug.print("Exception in getHorseRelationshipDetailsDetails:" + e);
        }
        return horseRelaList;
    }
    
    
    public ArrayList getHorseRelationshipDetailsByRequestId(String reqId) throws RemoteException {
        Debug.print("KaverySessionBean getHorseRelationshipDetailsByRequestId reqId: " + reqId);
        ArrayList horseRelaList  = new ArrayList();
        try {
            horseRelaList = (ArrayList)new HLCHorseRegDAO().selectHorseRelationshipDetailsByRequestId(reqId);
        }catch(Exception e){
            Debug.print("Exception in getHorseRelationshipDetailsByRequestId:" + e);
        }
        return horseRelaList;
    }
    
    public boolean createMembershipRefundDetails(String userId, String comments, float totalAmt, float checkAmt) throws RemoteException {
        boolean result = false;
        float balanceAmount = 0.0f;
        
        if(totalAmt<checkAmt) {
            balanceAmount = checkAmt - totalAmt;
        }
        
        Debug.print("Balance Amount:" + balanceAmount);
        
        if(userId!=null && userId.trim().length()!=0 && balanceAmount!=0){
            try{
                result = new HLCHorseRegDAO().insertMembershipRefundDetails(userId, comments, balanceAmount);
            } catch(Exception e){
                Debug.print("Exception in createMembershipRefundDetails:" + e.getMessage());
            }
        }
        Debug.print("Refund Applied Result:" + result);
        return result;
    }
    public String[] getHorseRelationshipDetailsByRelationId(String relationId) throws RemoteException {
        Debug.print("KaverySessionBean getHorseRelationshipDetailsByRelationId horseMemberId: " + relationId);
        String horseRelaList[]  = {};
        try {
            horseRelaList = (String[])new HLCHorseRegDAO().selectHorseRelationshipDetailsByRelationId(relationId);
        }catch(Exception e){
            Debug.print("Exception in getHorseRelationshipDetailsByRelationId:" + e);
        }
        return horseRelaList;
    }
    
    public boolean updateOwnerRiderActivation(String relationId, String relationshipTypeId, String relationshipStatusId) throws RemoteException {
        boolean result = false;
        try{
            result = (boolean)new HLCHorseRegDAO().updateOwnerRiderActivation(relationId, relationshipTypeId, relationshipStatusId);
        } catch(Exception exp){
            throw new EJBException("createHorseServiceTypeDetails: " + exp.getMessage());
        }
        return result;
    }

    public boolean deleteTrainerInfo(String horseMemberId) throws RemoteException {
        boolean result = false;
        try{
            result = (boolean)new HLCHorseRegDAO().deleteTrainerDetails(horseMemberId);
        } catch(Exception exp){
            throw new EJBException("createHorseServiceTypeDetails: " + exp.getMessage());
        }
        return result;
    }
    
    //=========================Horse Status Update=========================================
    public boolean approveHorseStatusByAdmin(String horseMemberId, String requestStatus, String comments, java.util.Date activitationDate, String modifiedBy, java.util.Date currDate) throws RemoteException{
        Debug.print("KaverySessionStatfulBean approveHorseStatusByAdmin horseMemberId "+ horseMemberId);
        Debug.print("modifiedById in KaverySessionStatfulBean approveHorseStatusByAdmin horseMemberId "+ modifiedBy);
        boolean result = false;
        if(horseMemberId!=null && horseMemberId.trim().length()!=0){
            result = new HLCHorseRegDAO().approveHorseStatusByAdmin(horseMemberId,requestStatus, comments, activitationDate, modifiedBy,currDate);
        }
        return result;
    }
    //=========================================Horse Reg Email Content==============================================
    public ArrayList getUserDets(String memberId) throws RemoteException {
        Debug.print("KaverySessionBean getUserDets memberId: " + memberId);
        ArrayList userDetList  = new ArrayList();
        try {
            userDetList = (ArrayList)new HLCHorseRegDAO().selectUserDets(memberId);
        }catch(Exception e){
            Debug.print("Exception in getUserDets:" + e);
        }
        return userDetList;
    }
   //===================================== Horse Reg Email Content=================================================
     public ArrayList getUserDetails(String horseMemberId) throws RemoteException {
        Debug.print("KaverySessionBean getUserDetails horseMemberId: " + horseMemberId);
        ArrayList userDetList  = new ArrayList();
        try {
            userDetList = (ArrayList)new HLCHorseRegDAO().selectUserDetails(horseMemberId);
        }catch(Exception e){
            Debug.print("Exception in getUserDetails:" + e);
        }
        return userDetList;
    }
     
      public ArrayList getOwnerDetails(String horseMemberId) throws RemoteException {
        Debug.print("KaverySessionBean getOwnerDetails horseMemberId: " + horseMemberId);
        ArrayList userDetList  = new ArrayList();
        try {
            userDetList = (ArrayList)new HLCHorseRegDAO().selectOwnerDetails(horseMemberId);
    }catch(Exception e){
            Debug.print("Exception in getOwnerDetails:" + e);
        }
        return userDetList;
    }
      
       public ArrayList getOwnerDetailsForMail(String logName) throws RemoteException {
        Debug.print("KaverySessionBean getOwnerDetailsForMail logName: " + logName);
        ArrayList userDetList  = new ArrayList();
        try {
            userDetList = (ArrayList)new HLCHorseRegDAO().selectOwnerDetailsForMail(logName);
        }catch(Exception e){
            Debug.print("Exception in getOwnerDetailsForMail:" + e);
        }
        return userDetList;
    }
       
        public ArrayList getPreOwnerRiderDetails(String horseMemberId) throws RemoteException {
        Debug.print("KaverySessionBean getPreOwnerRiderDetails logName: " + horseMemberId);
        ArrayList userDetList  = new ArrayList();
        try {
            userDetList = (ArrayList)new HLCHorseRegDAO().selectPreOwnerRiderDetails(horseMemberId);
        }catch(Exception e){
            Debug.print("Exception in getPreOwnerRiderDetails:" + e);
        }
        return userDetList;
    }
     
        public String getPrimaryOwnEmailId(String horseMemberId) throws RemoteException{
            String emailId = "";
            if(horseMemberId!=null && horseMemberId.trim().length()!=0){
                emailId = new HLCHorseRegDAO().selectEmailId(horseMemberId);
            }
            return emailId;
        }
        
          public ArrayList getHorseOwnRidChangeDetails(String requestId) throws RemoteException{          
           ArrayList horseChangeDets= new ArrayList();          
           if(requestId!=null && requestId.trim().length()!=0){               
               horseChangeDets = new HLCHorseRegDAO().selectHorseOwnRidChangeDetails(requestId);
           }           
           return horseChangeDets;
       } 
       
       public ArrayList getHorseRelationDetails(String horseMemberId) throws RemoteException {
        Debug.print("KaverySessionBean getHorseRelationDetails horseMemberId: " + horseMemberId);
        ArrayList horseRelaList  = new ArrayList();
        try {
            horseRelaList = (ArrayList)new HLCHorseRegDAO().selectHorseRelationDetails(horseMemberId);
        }catch(Exception e){
            Debug.print("Exception in getHorseRelationDetails:" + e);
        }
        return horseRelaList;
    }
        
    /**
     * Name         :makeConnection
     * Description  :This method will Create a databacse connection
     * @ param      :
     * @return      :void
     * @throws      :EJBException
     */
    
    private void makeConnection() {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(dbName);
            
            con = ds.getConnection();
        } catch (Exception ex) {
            throw new EJBException("Unable to connect to database. " + ex.getMessage());
        }
    }
    
    /**
     * Name         :releaseConnection
     * Description  :This method will release the databacse connection
     * @ param      :
     * @return      :void
     * @throws      :EJBException
     */
    private void releaseConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            throw new EJBException("releaseConnection: " + ex.getMessage());
        }
    }
    private Object HLCHorseRegDAO() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    
    
}
