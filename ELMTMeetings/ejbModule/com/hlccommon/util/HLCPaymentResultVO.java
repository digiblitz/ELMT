/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hlccommon.util;

import java.io.Serializable;

/**
 *
 * @author parteek
 */
public class HLCPaymentResultVO implements Serializable {
    private String tmStmp;
    private String errorCd;
    private String lngMsg;
    private String amount;
    private String sslAvsResponse;
    private String sslCvv2Response;
    private String finalCorecAction;

    public String getTmStmp() {
        return tmStmp;
    }

    public void setTmStmp(String tmStmp) {
        this.tmStmp = tmStmp;
    }

    public String getErrorCd() {
        return errorCd;
    }

    public void setErrorCd(String errorCd) {
        this.errorCd = errorCd;
    }

    public String getLngMsg() {
        return lngMsg;
    }

    public void setLngMsg(String lngMsg) {
        this.lngMsg = lngMsg;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSslAvsResponse() {
        return sslAvsResponse;
    }

    public void setSslAvsResponse(String sslAvsResponse) {
        this.sslAvsResponse = sslAvsResponse;
    }

    public String getSslCvv2Response() {
        return sslCvv2Response;
    }

    public void setSslCvv2Response(String sslCvv2Response) {
        this.sslCvv2Response = sslCvv2Response;
    }

    public String getFinalCorecAction() {
        return finalCorecAction;
    }

    public void setFinalCorecAction(String finalCorecAction) {
        this.finalCorecAction = finalCorecAction;
    }


}
