/*
 * InventoryMasterVO.java
 *
 * Created on September 29, 2006, 4:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcinv.util;

/**
 *
 * @author harmohan
 */
public class HLCInventoryMasterVO implements java.io.Serializable{
    
    private String productId;
    private String categoryId;
    private String subcategoryId;
    private String productName;
    private String productDescription;
    private String productCode;
    private String productGlAccountId;
    private String productCogAccountId;
    private String totalProductQuantity;
    private String buyProductPrice;
    private String profitMargin;
    private String discountMargin;
    private String salesPrice;
    private String perProductPrice;
    private String productImagepath;
    private String addDate;
    private String activeStatus;
    
    
    /** Creates a new instance of InventoryMasterVO */
    public HLCInventoryMasterVO() {
    }

    public HLCInventoryMasterVO(String productId,String categoryId,String subcategoryId,String productName,String productDescription,
            String productCode,String productGlAccountId,String productCogAccountId,String totalProductQuantity,String buyProductPrice,
            String profitMargin,String discountMargin,String salesPrice,String perProductPrice,String productImagepath) {
        
            this.productId = productId;
            this.categoryId = categoryId;
            this.subcategoryId = subcategoryId;
            this.productName = productName;
            this.productDescription = productDescription;
            this.productCode = productCode;
            this.productGlAccountId = productGlAccountId;
            this.productCogAccountId= productCogAccountId;
            this.totalProductQuantity = totalProductQuantity;
            this.buyProductPrice = buyProductPrice;
            this.profitMargin = profitMargin;
            this.discountMargin = discountMargin;
            this.salesPrice = salesPrice;
            this.perProductPrice = perProductPrice;
            this.productImagepath = productImagepath;
    }

    public String getActiveStatus() { return activeStatus; }
    public String getCategoryId() { return categoryId; }
    public String getSubcategoryId() { return subcategoryId; }
    public String getProductName() { return productName; }
    public String getProductDescription() { return productDescription; }
    public String getProductCode() { return productCode; }
    public String getProductCogAccountId() { return productCogAccountId; }
    public String getProductGlAccountId() { return productGlAccountId; }
    public String getTotalProductQuantity() { return totalProductQuantity; }
    public String getBuyProductPrice() { return buyProductPrice; }
    public String getProfitMargin() { return profitMargin; }
    public String getDiscountMargin() { return discountMargin; }
    public String getSalesPrice() { return salesPrice; }
    public String getPerProductPrice() { return perProductPrice; }
    public String getProductImagepath() { return productImagepath; }
    public String getProductId() { return productId; }
    public String getAddDate() { return addDate; }
    

    public void setActiveStatus(String activeStatus) {this.activeStatus = activeStatus; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId;  }
    public void setSubcategoryId(String subcategoryId) {this.subcategoryId = subcategoryId;  }
    public void setProductName(String productName) {this.productName = productName; }
    public void setProductDescription(String productDescription) { this.productDescription = productDescription; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    public void setProductCogAccountId(String productCogAccountId) { this.productCogAccountId = productCogAccountId; }
    public void setProductGlAccountId(String productGlAccountId) { this.productGlAccountId =  productGlAccountId; }
    public void setTotalProductQuantity(String totalProductQuantity) { this.totalProductQuantity = totalProductQuantity; }
    public void setBuyProductPrice(String buyProductPrice) { this.buyProductPrice = buyProductPrice; }
    public void setProfitMargin(String profitMargin) { this.profitMargin =  profitMargin; }
    public void setDiscountMargin(String discountMargin) { this.discountMargin = discountMargin; }
    public void setSalesPrice(String salesPrice) { this.salesPrice = salesPrice; }
    public void setPerProductPrice(String perProductPrice) { this.perProductPrice = perProductPrice; }
    public void setProductImagepath(String productImagepath) { this.productImagepath = productImagepath; }
    public void setProductId(String productId) { this.productId = productId; }
    public void setAddDate(String addDate) { this.addDate = addDate; }
    
    public String toString(){
     
    StringBuffer buffer = new StringBuffer()
   	.append(" productId :"+ productId+"\n")
	.append(" categoryId :"+ categoryId+"\n")
	.append(" subcategoryId :"+ subcategoryId+"\n")
	.append(" productName :"+productName+"\n")
	.append(" productDescription :"+productDescription+"\n")
	.append(" productCode :"+productCode+"\t\t")
        .append(" productGlAccountId :"+productGlAccountId+"\n")
	.append(" productCogAccountId :"+productCogAccountId+"\n")
        .append(" totalProductQuantity :"+totalProductQuantity+"\n")
	.append(" buyProductPrice :"+ buyProductPrice+"\n")
	.append(" profitMargin :"+ profitMargin+"\n")
	.append(" discountMargin :"+ discountMargin+"\n")
	.append(" salesPrice :"+ salesPrice+"\n")
	.append(" perProductPrice :"+ perProductPrice+"\n")
	.append(" productImagepath :"+ productImagepath+"\n")
	.append(" addDate :"+ addDate+"\n")
	.append(" activeStatus :"+ activeStatus+"\n");
    return buffer.toString();
  }
    
}
