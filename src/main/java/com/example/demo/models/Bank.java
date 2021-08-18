package com.example.demo.models;

public class Bank {

    private String Name;
    private String Short_Name;
    private Integer Main_Branch_ID;
    private Integer Expiry_Period;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getShort_Name() {
        return Short_Name;
    }

    public void setShort_Name(String short_Name) {
        Short_Name = short_Name;
    }

    public Integer getMain_Branch_ID() {
        return Main_Branch_ID;
    }

    public void setMain_Branch_ID(Integer main_Branch_ID) {
        Main_Branch_ID = main_Branch_ID;
    }

    public Integer getExpiry_Period() {
        return Expiry_Period;
    }

    public void setExpiry_Period(Integer expiry_Period) {
        Expiry_Period = expiry_Period;
    }

    public Integer getRefresh_Group_ID() {
        return Refresh_Group_ID;
    }

    public void setRefresh_Group_ID(Integer refresh_Group_ID) {
        Refresh_Group_ID = refresh_Group_ID;
    }

    public String getFIID() {
        return FIID;
    }

    public void setFIID(String FIID) {
        this.FIID = FIID;
    }

    public Boolean getReissue_with_new_exp_date() {
        return reissue_with_new_exp_date;
    }

    public void setReissue_with_new_exp_date(Boolean reissue_with_new_exp_date) {
        this.reissue_with_new_exp_date = reissue_with_new_exp_date;
    }

    public String getArabicname() {
        return arabicname;
    }

    public void setArabicname(String arabicname) {
        this.arabicname = arabicname;
    }

    public Boolean getEBC() {
        return isEBC;
    }

    public void setEBC(Boolean EBC) {
        isEBC = EBC;
    }

    private Integer Refresh_Group_ID;
    private String FIID;
    private Boolean reissue_with_new_exp_date;
    private String arabicname;
    private Boolean isEBC;
}
