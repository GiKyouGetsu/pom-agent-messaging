package com.avaya.ept.pom.pojo;

public class AGTResp {
    
    private String METHOD;
    private int result;
    private String errorMsg;
    public String getMETHOD() {
        return METHOD;
    }
    
    public void setMETHOD(String mETHOD) {
        METHOD = mETHOD;
    }
    
    public int getResult() {
        return result;
    }


    public void setResult(int result) {
        this.result = result;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    
    
    @Override
    public String toString() {
        return "AGTResp [METHOD=" + METHOD + ", result=" + result + ", errorMsg=" + errorMsg + "]";
    }

}
