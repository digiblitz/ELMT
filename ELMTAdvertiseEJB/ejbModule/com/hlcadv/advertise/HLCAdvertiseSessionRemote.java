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
package com.hlcadv.advertise;

import com.hlccommon.util.HLCAdsDetailedVO;
import com.hlccommon.util.HLCAdvertisementVO;
import com.hlccommon.util.HLCAdvertiserVO;
import com.hlccommon.util.HLCManifestVO;
import javax.ejb.EJBObject;
import java.rmi.*;
import java.util.*;
import javax.ejb.*;

/**
 * This is the remote interface for AdvertiseSession enterprise bean.
 */
public interface HLCAdvertiseSessionRemote extends EJBObject, HLCAdvertiseSessionRemoteBusiness {
     
    /**=================================  Media Master ===================================================**/
     public boolean createMedia(String mediaName , String mediaDescription) throws RemoteException;  
     public boolean editMedia(String mediaId, String mediaName , String mediaDescription) throws RemoteException;
     public Vector getAllMediaDetails() throws RemoteException,FinderException;
     public String[] getMediaDetails(String mediaId) throws RemoteException,FinderException;
     public boolean deleteMedia(String mediaId) throws RemoteException,FinderException;
      /**====================================================================================**/
 /**=================================  Dimension Master ===================================================**/
     public boolean createDimension(String dimensionTypeName) throws RemoteException;  
     public boolean editDimension(String dimensionTypeId, String dimensionTypeName) throws RemoteException;
     public Vector getAllDimensionDetails() throws RemoteException,FinderException;
     public String[] getDimensionDetails(String dimensionTypeId) throws RemoteException,FinderException;
     public HLCAdvertisementVO getDisplayDetails(String dimensionTypeId) throws RemoteException,FinderException;
     public boolean deleteDimension(String dimensionTypeId) throws RemoteException,FinderException;
      /**====================================================================================**/     
     
     /**=================================  Issue Master ===================================================**/
     public boolean createIssue(String issueName,String mediaId) throws RemoteException;  
     public boolean editIssue(String issueId, String issueName,String mediaId) throws RemoteException;
     public Vector getAllIssueDetails() throws RemoteException,FinderException;
     public String[] getIssueDetails(String issueId) throws RemoteException,FinderException;
     public boolean deleteIssue(String issueId) throws RemoteException,FinderException;
     public Vector getIssueByMedia(String mediaId) throws RemoteException,FinderException;
    
      /**====================================================================================**/     
     
    /**=================================  Display Master ===================================================**/
     public boolean createDisplayType(String displayTypeName , String mediaId, String displayTypeDescription) throws RemoteException;  
     public boolean editDisplayType(String displayTypeId, String displayTypeName , String mediaId, String displayTypeDescription) throws RemoteException;
     public Vector getAllDisplayTypeDetails() throws RemoteException,FinderException;
     public String[] getDisplayTypeDetails(String displayTypeId) throws RemoteException,FinderException;
     public boolean deleteDisplayType(String displayTypeId) throws RemoteException,FinderException;
     public Vector getMediaDisplayTypeDetails(String mediaId) throws RemoteException,FinderException;
     //public Vector getAllDisplayTypeDetails() throws RemoteException,FinderException;
     
     /**====================================================================================**/     
     
          
     /**=================================  Display Sub Master ===================================================**/
     public boolean createDisplaySubType(String displaySubTypeName , String displayTypeId, String displaySubTypeDescription) throws RemoteException;  
     public boolean editDisplaySubType(String displaySubTypeId, String displaySubTypeName , String displayTypeId, String displaySubTypeDescription) throws RemoteException;
     public Vector getAllDisplaySubTypeDetails() throws RemoteException,FinderException;
     public String[] getDisplaySubTypeDetails(String displaySubTypeId) throws RemoteException,FinderException;
     public boolean deleteDisplaySubType(String displaySubTypeId) throws RemoteException,FinderException;
     public Vector getDisplayTypeFromSubType(String displayTypeId) throws RemoteException,FinderException;
     /**====================================================================================**/     

             
     /**=================================  Dimension Detail Master ===================================================**/
     public boolean createDimDetails(String displaySubTypeId , String dimensionTypeId, String mediaId, String dimensionName, String height,
             String width,  String units,  String imagePath) throws RemoteException;  
     public boolean editDimDetails(String dimensionId, String displaySubTypeId , String dimensionTypeId, String mediaId, String dimensionName, String height,
             String width,  String units,  String imagePath) throws RemoteException;
     public Vector getAllDimDetails() throws RemoteException,FinderException;
     public String[] getDimDetails(String dimensionId) throws RemoteException,FinderException;
     public boolean deleteDimDetails(String dimensionId) throws RemoteException,FinderException;
     public List searchDimByMediaAndSubTypeId(String mediaId, String subTypeId) throws RemoteException,FinderException;
     public ArrayList getDimensionDetailBySubTypeAdvertisement(String mediaId, String subTypeId) throws RemoteException;
     /**====================================================================================**/     
     
    /**=================================  Map Price Detail Master ===================================================**/
     public boolean createMapPrice(String displayTypeId , String displaySubTypeId, String frequencyId, String mediaId, String price) throws RemoteException;  
     public boolean editMapPrice(String advMapId, String displayTypeId , String displaySubTypeId, String frequencyId, String mediaId, String price) throws RemoteException;
     public Vector getAllMapPriceDetails() throws RemoteException,FinderException;
     public String[] getMapPriceDetails(String advMapId) throws RemoteException,FinderException;
     public boolean deleteMapPriceDetails(String advMapId) throws RemoteException,FinderException;
     public ArrayList getAllMapByMedIssDisTyDisSubType(String mediaId,String frequencyId,String displayTypeId, String displaySubTypeId) throws RemoteException,FinderException;
     public ArrayList getAllMapByMedDisTyDisSubType(String mediaId, String displayTypeId, String displaySubTypeId) throws RemoteException,FinderException;     
     public List getPriceDetails(String mediaId, String dispId, String subTypeId) throws RemoteException,FinderException;
     public List getMapIdDetails(String mediaId, String dispId, String subTypeId) throws RemoteException,FinderException;
     public int getPriceFromMap(String mapId, int dispId) throws RemoteException;
     public ArrayList getPriceDetailsByMediaIdDispId(String mediaId, String dispId) throws RemoteException;
     /**====================================================================================**/   

   /**=================================  Advertiser Request ===================================================**/
     public boolean createAdvertiser(HLCAdvertiserVO objAdvt,ArrayList adsDet) throws RemoteException;  
     public boolean editAdvertiser(HLCAdvertiserVO objAdvt, ArrayList adsDet) throws RemoteException;
     public ArrayList getAllAdvertiserDetails() throws RemoteException,FinderException;
     public ArrayList getAllAdvertiserStatusDetails(String requestStatus) throws RemoteException,FinderException;
     public ArrayList getAdvertiserDetails(String advertisementId) throws RemoteException,FinderException;
     public boolean deleteAdvertiserDetails(String advertisementId) throws RemoteException,FinderException;
     public boolean advertisementStatusChange(String advertisementId, String status) throws RemoteException;
     public ArrayList getAllAdvertiserMediaBasedStatusDetails(String mediaId,String requestStatus) throws RemoteException,FinderException;
     public ArrayList searchByMedia(String mediaId) throws RemoteException;
     public ArrayList searchByAgency(String agency) throws RemoteException;
     public ArrayList searchByUser(String userId) throws RemoteException;
     public ArrayList viewAllAdsForUser(String userId) throws RemoteException;
     /**====================================================================================**/        
     public boolean createManifest(String advDetailId, String advertisementId, String issueId, String  advMapId, 
            String dimensionId, String placement, String  splInstructions, String pageNo) throws RemoteException;
     public boolean editManifest(String advManifestId, String issueId, 
            String dimensionId, String placement, String  splInstructions, String pageNo) throws RemoteException;
     public List getFrequencyDetails() throws RemoteException,FinderException ;
     public List getFrequencyDetails(String mediaId) throws RemoteException,FinderException;
     public ArrayList getAdvertisementDetailsByAdsId(String adsId) throws RemoteException;
    public HLCAdsDetailedVO getAdsDetailsByAdsDetId(String advDetailId) throws RemoteException;
    public ArrayList getAllManifesttDetailsByAdsId(String advertisementId) throws RemoteException;
    public HLCManifestVO getManifesttDetailsByManifestId(String advManifestId) throws RemoteException;
    public HLCManifestVO getManifesttDetailsByAdsDetId(String advDetailId) throws RemoteException;
    public ArrayList getAllManifestDetailForUser(String advertisementId) throws RemoteException;

     //=================================================================
}
