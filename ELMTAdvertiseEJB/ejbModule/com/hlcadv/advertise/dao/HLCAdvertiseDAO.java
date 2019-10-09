/*
 * AdvertiseDAO.java
 *
 * Created on September 10, 2006, 4:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcadv.advertise.dao;



import com.hlccommon.util.HLCAdsDetailedVO;
import com.hlccommon.util.HLCAdvertisementVO;
import com.hlccommon.util.DBHelper;
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCEJBAllJNDIs;
import com.hlccommon.util.HLCManifestVO;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;

/**
 *
 * @author Shiva Kumar Subbiaha
 */
public class HLCAdvertiseDAO {
    
    /** Creates a new instance of AdvertiseDAO */
    public HLCAdvertiseDAO() {
    }
    
    
    public List SearchMediaTypeAndDisplayType(String mediaId, String displayTypeId){    
    
         ArrayList subTypeList = new ArrayList();
         
          Statement stmt = null;
         Connection conn = null;
         ResultSet rs = null;
         StringBuffer buffer = new StringBuffer();
         boolean clauseFalg = true;
         if(mediaId!=null && mediaId.trim().length()>0){          
             buffer.append((clauseFalg)?" WHERE ":" AND ");
             buffer.append(" media_id='"+mediaId+"'");
             clauseFalg = false;
         }
         
         if(displayTypeId!=null && displayTypeId.trim().length()>0){          
             buffer.append((clauseFalg)?" WHERE":" AND ");
             buffer.append(" dst.display_type_id='"+displayTypeId+"'");
             clauseFalg = false;
          }
       
             buffer.append((clauseFalg)?" WHERE ":" AND ");
             buffer.append(" dd.display_sub_type_id = dst.display_sub_type_id ");
        
             
         
   	try {
           // String selectStatement = "select dimension_id from " + DBHelper.USEA_ADV_DIMENSION_DETAILS + " where display_sub_type_id = ? and media_id=? " ;
          String selectStatement="SELECT dimension_id,dd.display_sub_type_id,dst.display_type_id,dimension_type_id,"+
              " media_id,dimension_name,height,width,units,image_path  "+
              " FROM tblAdvDimensionDetails dd,tblAdvDisplaySubTypes  dst "+ buffer.toString();   
          
            Debug.print("SQL ="+ selectStatement); 
             conn = getConnection();
             stmt = conn.createStatement();
   
            rs = stmt.executeQuery(selectStatement);
            HLCAdvertisementVO advertisementVO = null;
            while (rs.next()){
                advertisementVO = new HLCAdvertisementVO();
                advertisementVO.setDimensionId(rs.getString(1));
		advertisementVO.setDisplaySubType(rs.getString(2));
                advertisementVO.setDisplayType(rs.getString(3));
                advertisementVO.setDimensionType(rs.getString(4));
		advertisementVO.setMediaType(rs.getString(5));
		advertisementVO.setDimensionalName(rs.getString(6));
		advertisementVO.setHeight(rs.getString(7));
		advertisementVO.setWidth(rs.getString(8));
		advertisementVO.setDimensionalUnit(rs.getString(9));
		advertisementVO.setImagePath(rs.getString(10));
                subTypeList.add(advertisementVO);
            }            
         
             releaseConnection(conn,rs,stmt);
            Debug.print("DimensionDetailBean in findByMediaTypeAndDisplayType:" + subTypeList);
        } 
        catch(SQLException sql){
            releaseConnection(conn,rs,stmt);
            throw new EJBException("SQL Exception in findByMediaTypeAndDisplayType:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(conn,rs,stmt);
            throw new EJBException("General Exception  in findByMediaTypeAndDisplayType:" + e.getMessage());
        }
    return subTypeList;
    }
    
    
     public HLCAdvertisementVO getDimensionDetails(String dimensionId){    
    
         ArrayList subTypeList = new ArrayList();
         
         PreparedStatement prepStmt = null;
         Connection conn = null;
         ResultSet rs = null;
         HLCAdvertisementVO advertisementVO = null;
   	try {
           // String selectStatement = "select dimension_id from " + DBHelper.USEA_ADV_DIMENSION_DETAILS + " where display_sub_type_id = ? and media_id=? " ;
          String selectStatement=" SELECT dimension_id,dst.display_sub_type_name,dtm.display_type_name,dimtm.dimension_type_name,"+
                        " ms.media_name,dimension_name,height,width,units,image_path "+
                        " FROM tblAdvDimensionDetails dd,tblAdvDisplaySubTypes  dst,tblAdvDisplayTypeMaster dtm, "+
                        " tblAdvMediaMaster ms,tblAdvDimensionTypeMaster dimtm "+
                        " WHERE dd.dimension_type_id=dimtm.dimension_type_id AND dd.media_id=ms.media_id "+ 
                        " AND dd.display_sub_type_id = dst.display_sub_type_id "+
                        " AND dst.display_type_id=dtm.display_type_id AND "+
                        " dimension_id=?";
             conn = getConnection();
             prepStmt = conn.prepareStatement(selectStatement);
            Debug.print("DimensionDetailBean findByDisplaySubTypeName:" + selectStatement);
            prepStmt.setString(1,dimensionId);
      
             rs = prepStmt.executeQuery();
            
            if(rs.next()){
                advertisementVO = new HLCAdvertisementVO();
                advertisementVO.setDimensionId(rs.getString(1));
		advertisementVO.setDisplaySubType(rs.getString(2));
                advertisementVO.setDisplayType(rs.getString(3));
                advertisementVO.setDimensionType(rs.getString(4));
		advertisementVO.setMediaType(rs.getString(5));
		advertisementVO.setDimensionalName(rs.getString(6));
		advertisementVO.setHeight(rs.getString(7));
		advertisementVO.setWidth(rs.getString(8));
		advertisementVO.setDimensionalUnit(rs.getString(9));
		advertisementVO.setImagePath(rs.getString(10));                
            }            
         
             releaseConnection(conn,rs,prepStmt);
            Debug.print("DimensionDetailBean in findByMediaTypeAndDisplayType:" + subTypeList);
        } 
        catch(SQLException sql){
            releaseConnection(conn,rs,prepStmt);
            throw new EJBException("SQL Exception in findByMediaTypeAndDisplayType:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(conn,rs,prepStmt);
            throw new EJBException("General Exception  in findByMediaTypeAndDisplayType:" + e.getMessage());
        }
    return advertisementVO;
    }
    
     
    public ArrayList getFrequencyDetails(){    
         ArrayList freqList = new ArrayList();
         PreparedStatement prepStmt = null;
         Connection conn = null;
         ResultSet rs = null;
   	try {
           // String selectStatement = "select dimension_id from " + DBHelper.USEA_ADV_DIMENSION_DETAILS + " where display_sub_type_id = ? and media_id=? " ;
          String selectStatement=" SELECT frequency_id,frequency_name, media_id from tblAdvFrequencyMaster";
             conn = getConnection();
             prepStmt = conn.prepareStatement(selectStatement);
             rs = prepStmt.executeQuery();
            ArrayList al = null;
            while(rs.next()){
                al = new ArrayList();
                String frequencyId = rs.getString(1);
                String frequencyName = rs.getString(2);
                String mediaId = rs.getString(3);
                String freqlist [] = {frequencyId,frequencyName,mediaId};
                freqList.add(freqlist);
            }            
         
            releaseConnection(conn,rs,prepStmt);
            Debug.print("DimensionDetailBean in findByMediaTypeAndDisplayType:" + freqList);
        } 
        catch(SQLException sql){
            releaseConnection(conn,rs,prepStmt);
            throw new EJBException("SQL Exception in findByMediaTypeAndDisplayType:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(conn,rs,prepStmt);
            throw new EJBException("General Exception  in findByMediaTypeAndDisplayType:" + e.getMessage());
        }
        return freqList;
    }
       
    public ArrayList getFrequencyDetails(String mediaId){    
         ArrayList freqList = new ArrayList();
         PreparedStatement prepStmt = null;
         Connection conn = null;
         ResultSet rs = null;
   	try {
           // String selectStatement = "select dimension_id from " + DBHelper.USEA_ADV_DIMENSION_DETAILS + " where display_sub_type_id = ? and media_id=? " ;
          String selectStatement=" SELECT frequency_id,frequency_name, media_id from tblAdvFrequencyMaster where media_id = ?";
             conn = getConnection();
             prepStmt = conn.prepareStatement(selectStatement);
             prepStmt.setString(1,mediaId);
             
             rs = prepStmt.executeQuery();
            ArrayList al = null;
            while(rs.next()){
                al = new ArrayList();
                String frequencyId = rs.getString(1);
                String frequencyName = rs.getString(2);
                String mediaIdVal = rs.getString(3);
                al.add(frequencyId);
                al.add(frequencyName);
                al.add(mediaIdVal);
                freqList.add(al);
            }            
         
             releaseConnection(conn,rs,prepStmt);
            Debug.print("DimensionDetailBean in findByMediaTypeAndDisplayType:" + freqList);
        } 
        catch(SQLException sql){
            releaseConnection(conn,rs,prepStmt);
            throw new EJBException("SQL Exception in findByMediaTypeAndDisplayType:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(conn,rs,prepStmt);
            throw new EJBException("General Exception  in findByMediaTypeAndDisplayType:" + e.getMessage());
        }
    return freqList;
    }
    
    
    
     public ArrayList getPriceDetails(String mediaId, String dispId, String subTypeId){    
         ArrayList priceList = null;
         PreparedStatement prepStmt = null;
         Connection conn = null;
         ResultSet rs = null;
   	try {
           // String selectStatement = "select dimension_id from " + DBHelper.USEA_ADV_DIMENSION_DETAILS + " where display_sub_type_id = ? and media_id=? " ;
          String selectStatement="select A.frequency_name, B.price from tblAdvFrequencyMaster A, tblAdvMapPrice B, tblAdvMapSubType C " + 
                  " where B.adv_map_id = C.adv_map_id and B.frequency_id = A.frequency_id and  " +
                  " C.media_id = ? and C.display_type_id = ? and " +
                  " C.display_sub_type_id = ? order by A.frequency_id";
          
            conn = getConnection();
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,mediaId);
            prepStmt.setString(2,dispId);
            prepStmt.setString(3,subTypeId);
              
            rs = prepStmt.executeQuery();
            priceList = new ArrayList();
            while(rs.next()){
                String fqName = rs.getString(1);
                String price = rs.getString(2);
                String fqArray[] = {fqName,price};
                priceList.add(fqArray);
            }
            
            releaseConnection(conn,rs,prepStmt);
            Debug.print("AdvertiseDAO in getPriceDetails:" + priceList);
        } 
        catch(SQLException sql){
            releaseConnection(conn,rs,prepStmt);
            throw new EJBException("SQL Exception in AdvertiseDAO getPriceDetails:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(conn,rs,prepStmt);
            throw new EJBException("General Exception  in AdvertiseDAO getPriceDetails:" + e.getMessage());
        }
        return priceList;
    }
     
     
    public int getPriceFromMap(String mapId, int dispId){    
         int price = 0;
         PreparedStatement prepStmt = null;
         Connection conn = null;
         ResultSet rs = null;
   	try {
           // String selectStatement = "select dimension_id from " + DBHelper.USEA_ADV_DIMENSION_DETAILS + " where display_sub_type_id = ? and media_id=? " ;
          String selectStatement="select price from tblAdvMapPrice where adv_map_id=? and frequency_id = ?";
          
        
            conn = getConnection();
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,mapId);
            prepStmt.setInt(2,dispId);
              
            rs = prepStmt.executeQuery();
            while(rs.next()){
                 price = rs.getInt(1);
            }  
            releaseConnection(conn,rs,prepStmt);
            Debug.print("AdvertiseDAO in getPriceFromMap:" + price);
        } 
        catch(SQLException sql){
            releaseConnection(conn,rs,prepStmt);
            throw new EJBException("SQL Exception in AdvertiseDAO getPriceFromMap:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(conn,rs,prepStmt);
            throw new EJBException("General Exception  in AdvertiseDAO getPriceFromMap:" + e.getMessage());
        }
        return price;
    }
     
     
    public ArrayList getMapIdDetails(String mediaId, String dispId, String subTypeId){    
         ArrayList priceList = null;
         PreparedStatement prepStmt = null;
         Connection conn = null;
         ResultSet rs = null;
   	try {
           // String selectStatement = "select dimension_id from " + DBHelper.USEA_ADV_DIMENSION_DETAILS + " where display_sub_type_id = ? and media_id=? " ;
          String selectStatement=" SELECT adv_map_id from tblAdvMapSubType where media_id = ? and display_type_id= ? and display_sub_type_id=?";
             conn = getConnection();
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,mediaId);
            prepStmt.setString(2,dispId);
            prepStmt.setString(3,subTypeId);
              
            rs = prepStmt.executeQuery();
            priceList = new ArrayList();
            while(rs.next()){
                priceList.add(rs.getString(1));
            }            
            releaseConnection(conn,rs,prepStmt);
            Debug.print("AdvertiseDAO in getMapIdDetails:" + priceList);
        } 
        catch(SQLException sql){
            releaseConnection(conn,rs,prepStmt);
            throw new EJBException("SQL Exception in AdvertiseDAO getMapIdDetails:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(conn,rs,prepStmt);
            throw new EJBException("General Exception  in AdvertiseDAO getMapIdDetails:" + e.getMessage());
        }
        return priceList;
    }
    
    public ArrayList getPriceDetailForAdvertisement(String advertisementId){
         ArrayList priceList = null;
         PreparedStatement prepStmt = null;
         Connection conn = null;
         ResultSet rs = null;
   	try {
            String selectStatement = " select A.media_name, B.issue_name, C.display_type_name, D.display_sub_type_name, " +
                    " E.dimension_type_name, F.placement, F.amount from tblAdvMediaMaster A, " + 
                    " tblAdvIssueMaster B, tblAdvDisplayTypeMaster C, tblAdvDisplaySubTypes D, " +
                    " tblAdvDimensionTypeMaster E, tblAdvertisementDetails F, tblAdvMapSubType G where F.issue_id = B.issue_id " +
                    " and F.dimension_id = E.dimension_type_id and F.adv_map_id = G.adv_map_id and G.media_id = A.media_id and " +
                    " G.display_type_id = C.display_type_id and G.display_sub_type_id = D.display_sub_type_id and " +
                    " F.advertisement_id = ? ";
          
            
            Debug.print(selectStatement);
          
            conn = getConnection();
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,advertisementId);
          
            rs = prepStmt.executeQuery();
            priceList = new ArrayList();
            while(rs.next()){
                String mediaName = rs.getString(1);
                String issueName = rs.getString(2);
                String dispName = rs.getString(3);
                String dispSubName = rs.getString(4);
                String dimName = rs.getString(5);
                String placement = rs.getString(6);
                String amount = rs.getString(7);
                
                String priceDet[] = {mediaName, issueName, dispName, dispSubName, dimName, placement, amount };
                priceList.add(priceDet);
            }            
            releaseConnection(conn,rs,prepStmt);
            Debug.print("AdvertiseDAO in getPriceDetailForAdvertisement:" + priceList);
        } 
        catch(SQLException sql){
            releaseConnection(conn,rs,prepStmt);
            throw new EJBException("SQL Exception in AdvertiseDAO getPriceDetailForAdvertisement:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(conn,rs,prepStmt);
            throw new EJBException("General Exception  in AdvertiseDAO getMapIdDetails:" + e.getMessage());
        }
        return priceList;
    }
    
    
       
    
    
     public ArrayList getDimensionDetailBySubTypeAdvertisement(String mediaId, String subTypeId){    
         ArrayList dimDetails = null;
         PreparedStatement prepStmt = null;
         Connection conn = null;
         ResultSet rs = null;
   	try {
            String selectStatement = " select A.media_name, B.display_type_name, C.display_sub_type_name, " +
                    " D.dimension_name, D.height, D.width, D.units, D.image_path from tblAdvMediaMaster A, tblAdvDisplayTypeMaster B,  " + 
                    " tblAdvDisplaySubTypes C, tblAdvDimensionDetails D  " +
                    " where D.media_id = A.media_id and D.display_sub_type_id = C.display_sub_type_id and  " +
                    " C.display_type_id = B.display_type_id and  " +
                    " D. media_id = ? and D.display_sub_type_id = ? ";
          
            
            Debug.print(selectStatement);
            conn = getConnection();
            prepStmt = conn.prepareStatement(selectStatement);
            
            
            prepStmt.setString(1,mediaId);
            prepStmt.setString(2,subTypeId);
          
            rs = prepStmt.executeQuery();
            dimDetails = new ArrayList();
            while(rs.next()){
                String mediaName = rs.getString(1);
                String dispName = rs.getString(2);
                String dispSubName = rs.getString(3);
                String dimName = rs.getString(4);
                String height = rs.getString(5);
                String width = rs.getString(6);
                String units = rs.getString(7);
                String imagePath = rs.getString(8);
                
                String dimList[] = {mediaName,  dispName, dispSubName, dimName, height, width, units, imagePath };
                dimDetails.add(dimList);
            }            
            releaseConnection(conn,rs,prepStmt);
            Debug.print("AdvertiseDAO in getDimensionDetailBySubTypeAdvertisement:" + dimDetails);
        } 
        catch(SQLException sql){
            releaseConnection(conn,rs,prepStmt);
            throw new EJBException("SQL Exception in AdvertiseDAO getDimensionDetailBySubTypeAdvertisement:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(conn,rs,prepStmt);
            throw new EJBException("General Exception  in AdvertiseDAO getDimensionDetailBySubTypeAdvertisement:" + e.getMessage());
        }
        return dimDetails;
    }
     
     
    //=============================================Insert Manifest details=========================================      
    public boolean insertManifest(String advDetailId, String advertisementId, String issueId, String  advMapId, 
            String dimensionId, String placement, String  splInstructions, String pageNo) {
            Debug.print("AdvertiseDAO.insertManifest():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            Connection conn = null;
            try {
                String insertStatement = "insert into " + DBHelper.USEA_ADV_MANIFEST_DETAILS + " (adv_detail_id, advertisement_id, issue_id, " +
                        "adv_map_id, dimension_id, placement, spl_instructions, page_no) " +
                        " values( ? , ? , ? , ? , ? , ?, ?, ?)";
                conn = getConnection();
                prepStmt = conn.prepareStatement(insertStatement);
                
                prepStmt.setString(1, advDetailId);
                prepStmt.setString(2, advertisementId);
                prepStmt.setString(3, issueId);
                prepStmt.setString(4, advMapId);
                prepStmt.setString(5, dimensionId);
                prepStmt.setString(6, placement);
                prepStmt.setString(7, splInstructions);
                prepStmt.setString(8, pageNo);
                prepStmt.executeUpdate();
                releaseConnection(conn,prepStmt);
                boolean statusChange = updateShowStatus(advDetailId);
                Debug.print("insertManifest statusChange:" + statusChange);
                result = true;
            }
            catch(SQLException sql){
                releaseConnection(conn,prepStmt);
                Debug.print("SQL Exception in AdvertiseDAO.insertManifest():" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection(conn,prepStmt);
                Debug.print("General Exception  in AdvertiseDAO.insertManifest():" + e.getMessage());
            }
            return result;
        }
//=============================================Update Manifest details=========================================      
    private boolean updateShowStatus(String adsDetailId) {
            Debug.print("AdvertiseDAO.updateShowStatus():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            Connection conn = null;
            try {
                String insertStatement = "UPDATE " + DBHelper.USEA_ADV_ADS_DETAILS + " set show_status = 0 " +
                        " where  adv_detail_id= ? ";
                conn = getConnection();
                prepStmt = conn.prepareStatement(insertStatement);
                
                prepStmt.setString(1, adsDetailId);
                
                prepStmt.executeUpdate();
                releaseConnection(conn,prepStmt);
                result = true;
            }
            catch(SQLException sql){
                releaseConnection(conn,prepStmt);
                Debug.print("SQL Exception in AdvertiseDAO.updateShowStatus():" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection(conn,prepStmt);
                Debug.print("General Exception  in AdvertiseDAO.updateShowStatus():" + e.getMessage());
            }
            return result;
        }     
 //=============================================Update Manifest details=========================================      
    public boolean updateManifest(String advManifestId, String issueId, 
            String dimensionId, String placement, String  splInstructions, String pageNo) {
            Debug.print("AdvertiseDAO.updateManifest():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            Connection conn = null;
            try {
                String insertStatement = "UPDATE " + DBHelper.USEA_ADV_MANIFEST_DETAILS + " set issue_id = ?, " +
                        " dimension_id = ?, placement = ?, spl_instructions = ?, page_no = ? " +
                        " where  adv_manifest_id= ? ";
                conn = getConnection();
                prepStmt = conn.prepareStatement(insertStatement);
                
                prepStmt.setString(1, issueId);
                prepStmt.setString(2, dimensionId);
                prepStmt.setString(3, placement);
                prepStmt.setString(4, splInstructions);
                prepStmt.setString(5, pageNo);
                prepStmt.setString(6, advManifestId);
                
                prepStmt.executeUpdate();
                releaseConnection(conn,prepStmt);
                result = true;
            }
            catch(SQLException sql){
                releaseConnection(conn,prepStmt);
                Debug.print("SQL Exception in AdvertiseDAO.updateManifest():" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection(conn,prepStmt);
                Debug.print("General Exception  in AdvertiseDAO.updateManifest():" + e.getMessage());
            }
            return result;
        }    
    
     //=============================================Get Ads from Original Details=========================================      
     public ArrayList getAdvertisementDetailsByAdsId(String advertisementId){
         Debug.print("AdvertiseDAO.getAdvertisementDetailsByAdsId():" + advertisementId);
         ArrayList adsDetList = null;
         PreparedStatement prepStmt = null;
         Connection conn = null;
         ResultSet rs = null;
   	try {
            String selectStatement = " select  F.adv_detail_id, F.advertisement_id, A.media_id, A.media_name, B.issue_name, C.display_type_name, D.display_sub_type_name, " +
                    " E.dimension_type_name, F.placement, F.amount,  F.adv_map_id, F.issue_id, F.dimension_id , F.show_status  from tblAdvMediaMaster A, " + 
                    " tblAdvIssueMaster B, tblAdvDisplayTypeMaster C, tblAdvDisplaySubTypes D, " +
                    " tblAdvDimensionTypeMaster E, tblAdvertisementDetails F, tblAdvMapSubType G where F.issue_id = B.issue_id " +
                    " and F.dimension_id = E.dimension_type_id and F.adv_map_id = G.adv_map_id and G.media_id = A.media_id and " +
                    " G.display_type_id = C.display_type_id and G.display_sub_type_id = D.display_sub_type_id and " +
                    " F.advertisement_id = ? ";
            
            Debug.print(selectStatement);
          
            conn = getConnection();
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,advertisementId);
          
            rs = prepStmt.executeQuery();
            adsDetList = new ArrayList();
            while(rs.next()){
                String advDetailId = rs.getString(1);
                String adsId = rs.getString(2);
                String mediaId = rs.getString(3);
                String mediaName = rs.getString(4);
                String issueName = rs.getString(5);
                String dispName = rs.getString(6);
                String dispSubName = rs.getString(7);
                String dimName = rs.getString(8);
                String placement = rs.getString(9);
                String amount = rs.getString(10);
                String advMapId = rs.getString(11);
                String issueId = rs.getString(12);
                String dimensionId = rs.getString(13);
                boolean showStatus = rs.getBoolean(14);
                
                HLCAdsDetailedVO objAdsDetVO = new HLCAdsDetailedVO();
                
                objAdsDetVO.setAdvDetailId(advDetailId);
                objAdsDetVO.setAdsId(adsId);
                objAdsDetVO.setMediaId(mediaId);
                objAdsDetVO.setMediaName(mediaName);
                objAdsDetVO.setIssueName(issueName);
                objAdsDetVO.setDispName(dispName);
                objAdsDetVO.setDispSubName(dispSubName);
                objAdsDetVO.setDimName(dimName);
                objAdsDetVO.setPlacement(placement);
                objAdsDetVO.setAmount(amount);
                objAdsDetVO.setMapId(advMapId);
                objAdsDetVO.setIssueId(issueId);
                objAdsDetVO.setDimId(dimensionId);
                objAdsDetVO.setShowStatus(showStatus);
               
                adsDetList.add(objAdsDetVO);
            }            
            releaseConnection(conn,rs,prepStmt);
            Debug.print("AdvertiseDAO in getAdvertisementDetailsByAdsId:" + adsDetList);
        } 
        catch(SQLException sql){
            releaseConnection(conn,rs,prepStmt);
            throw new EJBException("SQL Exception in AdvertiseDAO getAdvertisementDetailsByAdsId:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(conn,rs,prepStmt);
            throw new EJBException("General Exception  in AdvertiseDAO getAdvertisementDetailsByAdsId:" + e.getMessage());
        }
        return adsDetList;
    }
     
      //=============================================Get Ads from Original Details=========================================      
     public HLCAdsDetailedVO getAdsDetailsByAdsDetId(String advDetailId){
         Debug.print("AdvertiseDAO.getAdsDetailsByAdsDetId():" + advDetailId);
         PreparedStatement prepStmt = null;
         Connection conn = null;
         ResultSet rs = null;
         HLCAdsDetailedVO objAdsDetVO = new HLCAdsDetailedVO();
   	try {
            String selectStatement = " select  F.adv_detail_id, F.advertisement_id, A.media_id, A.media_name, B.issue_name, C.display_type_name, D.display_sub_type_name, " +
                    " E.dimension_type_name, F.placement, F.amount, F.adv_map_id, F.issue_id, F.dimension_id , F.show_status from tblAdvMediaMaster A, " + 
                    " tblAdvIssueMaster B, tblAdvDisplayTypeMaster C, tblAdvDisplaySubTypes D,  " +
                    " tblAdvDimensionTypeMaster E, tblAdvertisementDetails F, tblAdvMapSubType G where F.issue_id = B.issue_id " +
                    " and F.dimension_id = E.dimension_type_id and F.adv_map_id = G.adv_map_id and G.media_id = A.media_id and " +
                    " G.display_type_id = C.display_type_id and G.display_sub_type_id = D.display_sub_type_id and " +
                    " F.adv_detail_id = ? ";
            Debug.print(selectStatement);
          
            conn = getConnection();
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,advDetailId);
          
            rs = prepStmt.executeQuery();
            if(rs.next()){
                String advDetailIdVal = rs.getString(1);
                String adsId = rs.getString(2);
                String mediaId = rs.getString(3);
                String mediaName = rs.getString(4);
                String issueName = rs.getString(5);
                String dispName = rs.getString(6);
                String dispSubName = rs.getString(7);
                String dimName = rs.getString(8);
                String placement = rs.getString(9);
                String amount = rs.getString(10);
                String advMapId = rs.getString(11);
                String issueId = rs.getString(12);
                String dimensionId = rs.getString(13);
                boolean showStatus = rs.getBoolean(14);
                
                objAdsDetVO.setAdvDetailId(advDetailIdVal);
                objAdsDetVO.setAdsId(adsId);
                objAdsDetVO.setMediaId(mediaId);
                objAdsDetVO.setMediaName(mediaName);
                objAdsDetVO.setIssueName(issueName);
                objAdsDetVO.setDispName(dispName);
                objAdsDetVO.setDispSubName(dispSubName);
                objAdsDetVO.setDimName(dimName);
                objAdsDetVO.setPlacement(placement);
                objAdsDetVO.setAmount(amount);
                objAdsDetVO.setMapId(advMapId);
                objAdsDetVO.setIssueId(issueId);
                objAdsDetVO.setDimId(dimensionId);
                objAdsDetVO.setShowStatus(showStatus);
            }
            releaseConnection(conn,rs,prepStmt);
            Debug.print("AdvertiseDAO in getAdsDetailsByAdsDetId:" + objAdsDetVO);
        } 
        catch(SQLException sql){
            releaseConnection(conn,rs,prepStmt);
            throw new EJBException("SQL Exception in AdvertiseDAO getAdsDetailsByAdsDetId:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(conn,rs,prepStmt);
            throw new EJBException("General Exception  in AdvertiseDAO getAdsDetailsByAdsDetId:" + e.getMessage());
        }
        return objAdsDetVO;
    }
     
     //=============================================Get Ads from Manifest Details=========================================      
     public ArrayList getAllManifesttDetailsByAdsId(String advertisementId){
         Debug.print("AdvertiseDAO.getAllManifesttDetailsByAdsId():" + advertisementId);
         ArrayList manifestList = null;
         PreparedStatement prepStmt = null;
         Connection conn = null;
         ResultSet rs = null;
   	try {
            String selectStatement = " select H.adv_manifest_id,H.adv_detail_id, H.advertisement_id, A.media_id, A.media_name, B.issue_name, " +
                    " C.display_type_name, D.display_sub_type_name, E.dimension_type_name, H.placement, H.spl_instructions , " +
                    " H.page_no, F.adv_map_id, F.issue_id, F.dimension_id from tblAdvMediaMaster A, tblAdvIssueMaster B, tblAdvDisplayTypeMaster C, " +
                    " tblAdvDisplaySubTypes D, tblAdvDimensionTypeMaster E, tblAdvertisementDetails F, tblAdvMapSubType G, " +
                    " tblAdvManifestDetails H where F.issue_id = B.issue_id and F.dimension_id = E.dimension_type_id and " +
                    " F.adv_map_id = G.adv_map_id and G.media_id = A.media_id and G.display_type_id = C.display_type_id and " +
                    " G.display_sub_type_id = D.display_sub_type_id and H.advertisement_id = F.advertisement_id and " +
                    " H.adv_detail_id = F.adv_detail_id and H.advertisement_id = ? ";
            
            Debug.print(selectStatement);
          
            conn = getConnection();
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,advertisementId);
          
            rs = prepStmt.executeQuery();
            manifestList = new ArrayList();
            while(rs.next()){
                String advManifestId = rs.getString(1);
                String advDetailId = rs.getString(2);
                String advertisementIdVal = rs.getString(3);
                String mediaId = rs.getString(4);
                String mediaName = rs.getString(5);
                String issueName = rs.getString(6);
                String displayTypeName = rs.getString(7);
                String displaySubTypeName = rs.getString(8);
                String dimensionTypeName = rs.getString(9);
                String placement = rs.getString(10);
                String splInstructions = rs.getString(11);
                String pageNo = rs.getString(12);
                String advMapId = rs.getString(13);
                String issueId = rs.getString(14);
                String dimensionId = rs.getString(15);
                
                HLCManifestVO objManifestVO = new HLCManifestVO();
                objManifestVO.setAdvManifestId(advManifestId);
                objManifestVO.setAdvDetailId(advDetailId);
                objManifestVO.setAdvertisementId(advertisementIdVal);
                objManifestVO.setMediaId(mediaId);
                objManifestVO.setMediaName(mediaName);
                objManifestVO.setIssueName(issueName);
                objManifestVO.setDisplayTypeName(displayTypeName);
                objManifestVO.setDisplaySubTypeName(displaySubTypeName);
                objManifestVO.setDimensionTypeName(dimensionTypeName);
                objManifestVO.setPlacement(placement);
                objManifestVO.setSplInstructions(splInstructions);
                objManifestVO.setPageNo(pageNo);
                objManifestVO.setMapId(advMapId);
                objManifestVO.setIssueId(issueId);
                objManifestVO.setDimId(dimensionId);
                manifestList.add(objManifestVO);
            }            
            releaseConnection(conn,rs,prepStmt);
            Debug.print("AdvertiseDAO in getAllManifesttDetailsByAdsId:" + manifestList);
        } 
        catch(SQLException sql){
            releaseConnection(conn,rs,prepStmt);
            throw new EJBException("SQL Exception in AdvertiseDAO getAllManifesttDetailsByAdsId:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(conn,rs,prepStmt);
            throw new EJBException("General Exception  in AdvertiseDAO getAllManifesttDetailsByAdsId:" + e.getMessage());
        }
        return manifestList;
    }
     
        //=============================================Get  Manifest Details particular by ManifestId=========================================      
     
     public HLCManifestVO getManifesttDetailsByManifestId(String advManifestId){
         Debug.print("AdvertiseDAO.getManifesttDetailsByManifestId():" + advManifestId);
         HLCManifestVO objManifestVO = new HLCManifestVO();
         PreparedStatement prepStmt = null;
         Connection conn = null;
         ResultSet rs = null;
   	try {
            String selectStatement = " select H.adv_manifest_id,H.adv_detail_id, H.advertisement_id, A.media_id, A.media_name, B.issue_name, " +
                    " C.display_type_name, D.display_sub_type_name, E.dimension_type_name, H.placement, H.spl_instructions , " +
                    " H.page_no, F.adv_map_id, F.issue_id, F.dimension_id  from tblAdvMediaMaster A, tblAdvIssueMaster B, tblAdvDisplayTypeMaster C, " +
                    " tblAdvDisplaySubTypes D, tblAdvDimensionTypeMaster E, tblAdvertisementDetails F, tblAdvMapSubType G, " +
                    " tblAdvManifestDetails H where F.issue_id = B.issue_id and F.dimension_id = E.dimension_type_id and " +
                    " F.adv_map_id = G.adv_map_id and G.media_id = A.media_id and G.display_type_id = C.display_type_id and " +
                    " G.display_sub_type_id = D.display_sub_type_id and H.advertisement_id = F.advertisement_id and " +
                    " H.adv_detail_id = F.adv_detail_id and H.adv_manifest_id = ? ";
            
            Debug.print(selectStatement);
          
            conn = getConnection();
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,advManifestId);
          
            rs = prepStmt.executeQuery();
            if(rs.next()){
                String advManifestIdVal = rs.getString(1);
                String advDetailId = rs.getString(2);
                String advertisementId = rs.getString(3);
                String mediaId = rs.getString(4);
                String mediaName = rs.getString(5);
                String issueName = rs.getString(6);
                String displayTypeName = rs.getString(7);
                String displaySubTypeName = rs.getString(8);
                String dimensionTypeName = rs.getString(9);
                String placement = rs.getString(10);
                String splInstructions = rs.getString(11);
                String pageNo = rs.getString(12);
                String advMapId = rs.getString(13);
                String issueId = rs.getString(14);
                String dimensionId = rs.getString(15);
            
                
                objManifestVO.setAdvManifestId(advManifestIdVal);
                objManifestVO.setAdvDetailId(advDetailId);
                objManifestVO.setAdvertisementId(advertisementId);
                objManifestVO.setMediaId(mediaId);
                objManifestVO.setMediaName(mediaName);
                objManifestVO.setIssueName(issueName);
                objManifestVO.setDisplayTypeName(displayTypeName);
                objManifestVO.setDisplaySubTypeName(displaySubTypeName);
                objManifestVO.setDimensionTypeName(dimensionTypeName);
                objManifestVO.setPlacement(placement);
                objManifestVO.setSplInstructions(splInstructions);
                objManifestVO.setPageNo(pageNo);
                objManifestVO.setMapId(advMapId);
                objManifestVO.setIssueId(issueId);
                objManifestVO.setDimId(dimensionId);
            }            
            releaseConnection(conn,rs,prepStmt);
            Debug.print("AdvertiseDAO in getManifesttDetailsByManifestId:" + objManifestVO);
        } 
        catch(SQLException sql){
            releaseConnection(conn,rs,prepStmt);
            throw new EJBException("SQL Exception in AdvertiseDAO getManifesttDetailsByManifestId:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(conn,rs,prepStmt);
            throw new EJBException("General Exception  in AdvertiseDAO getManifesttDetailsByManifestId:" + e.getMessage());
        }
        return objManifestVO;
    }
   //=============================================Get  Manifest Details particular by ManifestId=========================================      
     
     public HLCManifestVO getManifesttDetailsByAdsDetId(String advDetailId){
         Debug.print("AdvertiseDAO.getManifesttDetailsByAdsDetId():" + advDetailId);
         HLCManifestVO objManifestVO = new HLCManifestVO();
         PreparedStatement prepStmt = null;
         Connection conn = null;
         ResultSet rs = null;
   	try {
            String selectStatement = " select H.adv_manifest_id,H.adv_detail_id, H.advertisement_id, A.media_id, A.media_name, B.issue_name, " +
                    " C.display_type_name, D.display_sub_type_name, E.dimension_type_name, H.placement, H.spl_instructions , " +
                    " H.page_no, F.adv_map_id, F.issue_id, F.dimension_id  from tblAdvMediaMaster A, tblAdvIssueMaster B, tblAdvDisplayTypeMaster C, " +
                    " tblAdvDisplaySubTypes D, tblAdvDimensionTypeMaster E, tblAdvertisementDetails F, tblAdvMapSubType G, " +
                    " tblAdvManifestDetails H where F.issue_id = B.issue_id and F.dimension_id = E.dimension_type_id and " +
                    " F.adv_map_id = G.adv_map_id and G.media_id = A.media_id and G.display_type_id = C.display_type_id and " +
                    " G.display_sub_type_id = D.display_sub_type_id and H.advertisement_id = F.advertisement_id and " +
                    " H.adv_detail_id = F.adv_detail_id and H.adv_detail_id = ? ";
            
            Debug.print(selectStatement);
          
            conn = getConnection();
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,advDetailId);
          
            rs = prepStmt.executeQuery();
            if(rs.next()){
                String advManifestId = rs.getString(1);
                String advDetailIdVal = rs.getString(2);
                String advertisementId = rs.getString(3);
                String mediaId = rs.getString(4);
                String mediaName = rs.getString(5);
                String issueName = rs.getString(6);
                String displayTypeName = rs.getString(7);
                String displaySubTypeName = rs.getString(8);
                String dimensionTypeName = rs.getString(9);
                String placement = rs.getString(10);
                String splInstructions = rs.getString(11);
                String pageNo = rs.getString(12);
                String advMapId = rs.getString(13);
                String issueId = rs.getString(14);
                String dimensionId = rs.getString(15);
            
                
                objManifestVO.setAdvManifestId(advManifestId);
                objManifestVO.setAdvDetailId(advDetailIdVal);
                objManifestVO.setAdvertisementId(advertisementId);
                objManifestVO.setMediaId(mediaId);
                objManifestVO.setMediaName(mediaName);
                objManifestVO.setIssueName(issueName);
                objManifestVO.setDisplayTypeName(displayTypeName);
                objManifestVO.setDisplaySubTypeName(displaySubTypeName);
                objManifestVO.setDimensionTypeName(dimensionTypeName);
                objManifestVO.setPlacement(placement);
                objManifestVO.setSplInstructions(splInstructions);
                objManifestVO.setPageNo(pageNo);
                objManifestVO.setMapId(advMapId);
                objManifestVO.setIssueId(issueId);
                objManifestVO.setDimId(dimensionId);
            }            
            releaseConnection(conn,rs,prepStmt);
            Debug.print("AdvertiseDAO in getManifesttDetailsByAdsDetId:" + objManifestVO);
        } 
        catch(SQLException sql){
            releaseConnection(conn,rs,prepStmt);
            throw new EJBException("SQL Exception in AdvertiseDAO getManifesttDetailsByAdsDetId:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(conn,rs,prepStmt);
            throw new EJBException("General Exception  in AdvertiseDAO getManifesttDetailsByAdsDetId:" + e.getMessage());
        }
        return objManifestVO;
    }
              
       public ArrayList getAllManifestDetailForUser(String advertisementId) {
           Debug.print("AdvertiseDAO.getAllManifestDetailForUser():" + advertisementId);
           ArrayList manifestList = new ArrayList();
            try {
                ArrayList adsIds = getAllAdsDetailId(advertisementId);
                if(adsIds!=null && adsIds.size()!=0){
                    Iterator itIds = adsIds.iterator();
                    while(itIds.hasNext()){
                        String adsId = (String)itIds.next();
                        ArrayList tempManifestList = new ArrayList();
                        HLCManifestVO objManifestVO = new HLCManifestVO();
                        HLCAdsDetailedVO objAdsDetVO = new HLCAdsDetailedVO();
                        
                        objAdsDetVO = getAdsDetailsByAdsDetId(adsId);
                        objManifestVO = getManifesttDetailsByAdsDetId(adsId);
                        
                        tempManifestList.add(objAdsDetVO);
                        tempManifestList.add(objManifestVO);
                        
                        manifestList.add(tempManifestList);
                    }
                }
                Debug.print("AdvertiseDAO in getAllManifestDetailForUser:" + advertisementId);
            } 
            catch(Exception e){
                throw new EJBException("General Exception  in AdvertiseDAO getAllManifestDetailForUser:" + e.getMessage());
            }
            return manifestList;

       }
   
     private ArrayList getAllAdsDetailId(String advertisementId){
         ArrayList adsDetIds = null;
         PreparedStatement prepStmt = null;
         Connection conn = null;
         ResultSet rs = null;
   	try {
            String selectStatement=" SELECT adv_detail_id from tblAdvertisementDetails where advertisement_id = ? ";
            conn = getConnection();
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,advertisementId);

              
            rs = prepStmt.executeQuery();
            adsDetIds = new ArrayList();
            while(rs.next()){
                adsDetIds.add(rs.getString(1));
            }            
            releaseConnection(conn,rs,prepStmt);
            Debug.print("AdvertiseDAO in getAllAdsDetailId:" + adsDetIds);
        } 
        catch(SQLException sql){
            releaseConnection(conn,rs,prepStmt);
            throw new EJBException("SQL Exception in AdvertiseDAO getAllAdsDetailId:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(conn,rs,prepStmt);
            throw new EJBException("General Exception  in AdvertiseDAO getAllAdsDetailId:" + e.getMessage());
        }
        return adsDetIds;
    }           
   
    private Connection getConnection() {
            Debug.print("DimensionDetailBean : makeConnection");
            Connection conn;
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup(HLCEJBAllJNDIs.USEA_DB);
                conn = ds.getConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to database. " + ex.getMessage());
            }
           return conn; 
        }
    

    private void releaseConnection(Connection conn,ResultSet rs,PreparedStatement ps) {
            Debug.print("DimensionDetailBean releaseConnection");
            try {
               if(rs!=null)
                  rs.close();
               if(ps!=null)
                  ps.close();  
               if(!conn.isClosed())
                   conn.close();
            } catch (Exception e) {
                
            }
        }
    

        private void releaseConnection(Connection conn, PreparedStatement ps) {
            Debug.print("DimensionDetailBean releaseConnection");
            try {
               if(ps!=null)
                  ps.close();  
               if(!conn.isClosed())
                   conn.close();
            } catch (Exception e) {
                
            }
        }

    
    
     private void releaseConnection(Connection conn,ResultSet rs,Statement stmt) {
            Debug.print("DimensionDetailBean releaseConnection");
            try {
               if(rs!=null)
                  rs.close();
               if(stmt!=null)
                  stmt.close();  
               if(!conn.isClosed())
                   conn.close();
            } catch (Exception e) {
                
            }
        }

   
    
}
