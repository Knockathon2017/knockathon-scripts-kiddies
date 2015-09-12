package com.rsamadhan.network;

/**
 * Created by Anurag Singh on 12/9/15 9:52 AM.
 */
public class CrimeDomainRequet {

    private String UserId;
    private String MobileNumber;
    private String ComplaintContent;
    private String Domain = "crime";
    private String Latitude;
    private String Longitude;
    private boolean IsPublicComplaint = false;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getComplaintContent() {
        return ComplaintContent;
    }

    public void setComplaintContent(String complaintContent) {
        ComplaintContent = complaintContent;
    }

    public String getDomain() {
        return Domain;
    }

    public void setDomain(String domain) {
        Domain = domain;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public boolean getIsPublicComplaint() {
        return IsPublicComplaint;
    }

    public void setIsPublicComplaint(boolean isPublicComplaint) {
        IsPublicComplaint = isPublicComplaint;
    }
}
