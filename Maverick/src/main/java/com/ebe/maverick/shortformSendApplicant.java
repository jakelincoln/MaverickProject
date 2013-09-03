package com.ebe.maverick;

import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: EBE13NKTW1

 * Date: 8/28/13
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class shortformSendApplicant {
    private String LName;
    private String FName;
    private String MName;
    private String SSN;
    private String DoB;
    private String Addr;
    private String City;
    private String State;
    private String Zip;
    private String HomePhone;
    private String CellPhone;
    private String OtherPhone;
    private String Email;
    private String Email2;
    private String Email3;
    private String Fax;
    private String ContactMethod;
    private String Source;
    private String Time;

    public shortformSendApplicant(){
        LName = "";
        FName = "";
        MName = "";
        SSN = "";
        DoB = "";
        Addr = "";
        City = "";
        State = "";
        Zip = "";
        HomePhone = "";
        CellPhone = "";
        OtherPhone = "";
        Email = "";
        Email2 = "";
        Email3 = "";
        Fax = "";
        ContactMethod = "";
        Source = "";
        Time = "";
    }

    public shortformSendApplicant(
            String LastName,
            String FirstName,
            String MiddleName,
            String SocialSecurityNum,
            String DateOfBirth,
            String Address,
            String AddrCity,
            String AddrState,
            String AddrZip,
            String HomeTelephone,
            String CellularTelephone,
            String OtherTelephone,
            String EmailAddress,
            String EmailAddress2,
            String EmailAddress3,
            String FaxNumber,
            String PreferredContactMethod,
            String RecruiterSource,
            String appTime){

        LName = LastName;
        FName = FirstName;
        MName = MiddleName;
        SSN = SocialSecurityNum;
        DoB = DateOfBirth;
        Addr = Address;
        City = AddrCity;
        State = AddrState;
        Zip = AddrZip;
        HomePhone = HomeTelephone;
        CellPhone = CellularTelephone;
        OtherPhone = OtherTelephone;
        Email = EmailAddress;
        Email2 = EmailAddress2;
        Email3 = EmailAddress3;
        Fax = FaxNumber;
        ContactMethod = PreferredContactMethod;
        Source = RecruiterSource;
        Time = appTime;
    }

    public shortformSendApplicant(shortformSendApplicant applicant){
        LName = applicant.getLName();
        FName = applicant.getFName();
        MName = applicant.getMName();
        SSN = applicant.getSSN();
        DoB = applicant.getDoB();
        Addr = applicant.getAddr();
        City = applicant.getCity();
        State = applicant.getState();
        Zip = applicant.getZip();
        HomePhone = applicant.getHomePhone();
        CellPhone = applicant.getCellPhone();
        OtherPhone = applicant.getOtherPhone();
        Email = applicant.getEmail();
        Email2 = applicant.getEmail2();
        Email3 = applicant.getEmail3();
        Fax = applicant.getFax();
        ContactMethod = applicant.getContactMethod();
        Source = applicant.getSource();
        Time = applicant.getTime();
    }

    public void setLName(String LastName){
        LName = LastName;
    }

    public void setFName(String FirstName){
        FName = FirstName;
    }

    public void setMName(String MiddleName){
        MName = MiddleName;
    }

    public void setSSN(String SocialSecurityNumber){
        SSN = SocialSecurityNumber;
    }

    public void setDoB(String DateOfBirth){
        DoB = DateOfBirth;
    }

    public void setAddr(String Address){
        Addr = Address;
    }

    public void setCity(String AddrCity){
        City = AddrCity;
    }

    public void setState(String AddrState){
        State = AddrState;
    }

    public void setZip(String AddrZip){
        Zip = AddrZip;
    }

    public void setHomePhone(String HomeTelephone){
        HomePhone = HomeTelephone;
    }

    public void setCellPhone(String CellularTelephone){
        CellPhone = CellularTelephone;
    }

    public void setOtherPhone(String OtherTelephone){
        OtherPhone= OtherTelephone;
    }

    public void setEmail(String EmailAddress1){
        Email = EmailAddress1;
    }

    public void setEmail2(String EmailAddress2){
        Email2 = EmailAddress2;
    }

    public void setEmail3(String EmailAddress3){
        Email3 = EmailAddress3;
    }

    public void setFax(String FaxNumber){
        Fax = FaxNumber;
    }

    public void setContactMethod(String PreferredContactMethod){
        ContactMethod = PreferredContactMethod;
    }

    public void setSource(String RecruiterSource){
        Source = RecruiterSource;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getLName(){
        return LName;
    }

    public String getFName() {
        return FName;
    }

    public String getMName() {
        return MName;
    }

    public String getSSN() {
        return SSN;
    }

    public String getAddr() {
        return Addr;
    }

    public String getDoB() {
        return DoB;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String getZip() {
        return Zip;
    }

    public String getHomePhone() {
        return HomePhone;
    }

    public String getCellPhone() {
        return CellPhone;
    }

    public String getOtherPhone() {
        return OtherPhone;
    }

    public String getEmail() {
        return Email;
    }

    public String getEmail2() {
        return Email2;
    }

    public String getEmail3() {
        return Email3;
    }

    public String getFax() {
        return Fax;
    }

    public String getContactMethod() {
        return ContactMethod;
    }

    public String getSource() {
        return Source;
    }

    public String getTime() {
        return Time;
    }




}
