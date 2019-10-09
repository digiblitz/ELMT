/*
 * InsertMembershipRegActionForm.java
 *
 * Created on September 7, 2006, 2:20 AM
 */

package com.mrm.actionform;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author karthikeyan
 * @version
 */

public class InsertMembershipRegActionForm extends org.apache.struts.action.ActionForm {
    
    private String selDisp;
    private String memType;
    private String memId;
    private String donAmt_sel;
    private String donAmnt_txt;
    private String mailAddr;
    private String memNo;
    
    private String select1;
    private String birthmonth1;
    private String birthday1;
    private String birthyear1;
    private String gender1;
    private String phone1;
    private String mobile1;
    private String fax1;
    private String lid1;
    
    private String select2;
    private String birthmonth2;
    private String birthday2;
    private String birthyear2;
    private String gender2;
    private String phone2;
    private String mobile2;
    private String fax2;
    private String lid2;
    
    private String select3;
    private String birthmonth3;
    private String birthday3;
    private String birthyear3;
    private String gender3;
    private String phone3;
    private String mobile3;
    private String fax3;
    private String lid3;
    
    private String select4;
    private String birthmonth4;
    private String birthday4;
    private String birthyear4;
    private String gender4;
    private String phone4;
    private String mobile4;
    private String fax4;
    private String lid4;
    
    private String totAmt;
    private String r11;
    private String chkNo;
    private String chkDat;
    private String bank;
    private String ctypSelect;
    private String crdNo;
    private String cvvNo;       
    private String prName;
    private String expirymonth;
    private String expiryyear;
 
   
    public void setBank(String bank) {
        this.bank = bank;
    }
   
    public String getSelDisp() {
        return selDisp;
    }
    public void setSelDisp(String selDisp) {
        this.selDisp = selDisp;
    }
   
    public void setDonAmnt_txt(String donAmnt_txt) {
        this.donAmnt_txt = donAmnt_txt;
    }

    public String getDonAmnt_txt() {
        return donAmnt_txt;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemType(String memType) {
        this.memType = memType;
    }

    public String getMemType() {
        return memType;
    }

    public String getMailAddr() {
        return mailAddr;
    }

    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr;
    }

    public String getMemNo() {
        return memNo;
    }

    public void setMemNo(String memNo) {
        this.memNo = memNo;
    }

    public String getSelect1() {
        return select1;
    }

    public void setSelect1(String select1) {
        this.select1 = select1;
    }

    public String getBirthmonth1() {
        return birthmonth1;
    }

    public void setBirthmonth1(String birthmonth1) {
        this.birthmonth1 = birthmonth1;
    }

    public String getBirthday1() {
        return birthday1;
    }

    public void setBirthday1(String birthday1) {
        this.birthday1 = birthday1;
    }

    public String getBirthyear1() {
        return birthyear1;
    }

    public void setBirthyear1(String birthyear1) {
        this.birthyear1 = birthyear1;
    }

    public String getGender1() {
        return gender1;
    }

    public void setGender1(String gender1) {
        this.gender1 = gender1;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getFax1() {
        return fax1;
    }

    public void setFax1(String fax1) {
        this.fax1 = fax1;
    }

    public String getLid1() {
        return lid1;
    }

    public void setLid1(String lid1) {
        this.lid1 = lid1;
    }

    public String getSelect2() {
        return select2;
    }

    public void setSelect2(String select2) {
        this.select2 = select2;
    }

    public String getBirthmonth2() {
        return birthmonth2;
    }

    public void setBirthmonth2(String birthmonth2) {
        this.birthmonth2 = birthmonth2;
    }

    public String getBirthday2() {
        return birthday2;
    }

    public void setBirthday2(String birthday2) {
        this.birthday2 = birthday2;
    }

    public String getBirthyear2() {
        return birthyear2;
    }

    public void setBirthyear2(String birthyear2) {
        this.birthyear2 = birthyear2;
    }

    public String getGender2() {
        return gender2;
    }

    public void setGender2(String gender2) {
        this.gender2 = gender2;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getMobile2() {
        return mobile2;
    }

    public String getFax2() {
        return fax2;
    }

    public void setFax2(String fax2) {
        this.fax2 = fax2;
    }

    public String getLid2() {
        return lid2;
    }

    public void setLid2(String lid2) {
        this.lid2 = lid2;
    }

    public String getSelect3() {
        return select3;
    }

    public void setSelect3(String select3) {
        this.select3 = select3;
    }

    public String getBirthmonth3() {
        return birthmonth3;
    }

    public void setBirthmonth3(String birthmonth3) {
        this.birthmonth3 = birthmonth3;
    }

    public String getBirthday3() {
        return birthday3;
    }

    public void setBirthday3(String birthday3) {
        this.birthday3 = birthday3;
    }

    public String getBirthyear3() {
        return birthyear3;
    }

    public void setBirthyear3(String birthyear3) {
        this.birthyear3 = birthyear3;
    }

    public String getGender3() {
        return gender3;
    }

    public void setGender3(String gender3) {
        this.gender3 = gender3;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getMobile3() {
        return mobile3;
    }

    public void setMobile3(String mobile3) {
        this.mobile3 = mobile3;
    }

    public String getFax3() {
        return fax3;
    }

    public void setFax3(String fax3) {
        this.fax3 = fax3;
    }

    public String getLid3() {
        return lid3;
    }

    public void setLid3(String lid3) {
        this.lid3 = lid3;
    }

    public String getSelect4() {
        return select4;
    }

    public void setSelect4(String select4) {
        this.select4 = select4;
    }

    public String getBirthmonth4() {
        return birthmonth4;
    }

    public void setBirthmonth4(String birthmonth4) {
        this.birthmonth4 = birthmonth4;
    }

    public String getBirthday4() {
        return birthday4;
    }

    public void setBirthday4(String birthday4) {
        this.birthday4 = birthday4;
    }

    public String getBirthyear4() {
        return birthyear4;
    }

    public void setBirthyear4(String birthyear4) {
        this.birthyear4 = birthyear4;
    }

    public void setGender4(String gender4) {
        this.gender4 = gender4;
    }

    public String getGender4() {
        return gender4;
    }

    public String getPhone4() {
        return phone4;
    }

    public void setPhone4(String phone4) {
        this.phone4 = phone4;
    }

    public String getMobile4() {
        return mobile4;
    }

    public void setMobile4(String mobile4) {
        this.mobile4 = mobile4;
    }

    public String getFax4() {
        return fax4;
    }

    public void setFax4(String fax4) {
        this.fax4 = fax4;
    }

    public String getLid4() {
        return lid4;
    }

    public void setLid4(String lid4) {
        this.lid4 = lid4;
    }

    public String getTotAmt() {
        return totAmt;
    }

    public void setTotAmt(String totAmt) {
        this.totAmt = totAmt;
    }

    public String getR11() {
        return r11;
    }

    public void setR11(String r11) {
        this.r11 = r11;
    }

    public String getChkDat() {
        return chkDat;
    }

    public void setChkDat(String chkDat) {
        this.chkDat = chkDat;
    }

    public String getChkNo() {
        return chkNo;
    }

    public void setChkNo(String chkNo) {
        this.chkNo = chkNo;
    }

    public String getBank() {
        return bank;
    }

    public String getCtypSelect() {
        return ctypSelect;
    }

    public void setCtypSelect(String ctypSelect) {
        this.ctypSelect = ctypSelect;
    }

    public String getCrdNo() {
        return crdNo;
    }

    public void setCrdNo(String crdNo) {
        this.crdNo = crdNo;
    }

    public String getCvvNo() {
        return cvvNo;
    }

    public void setCvvNo(String cvvNo) {
        this.cvvNo = cvvNo;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public String getExpirymonth() {
        return expirymonth;
    }

    public void setExpirymonth(String expirymonth) {
        this.expirymonth = expirymonth;
    }

    public void setExpiryyear(String expiryyear) {
        this.expiryyear = expiryyear;
    }

    public String getExpiryyear() {
        return expiryyear;
    }

      
    
}
