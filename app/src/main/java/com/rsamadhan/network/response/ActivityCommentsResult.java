package com.rsamadhan.network.response;

/**
 * Created by Anurag Singh on 14/9/15 11:11 AM.
 */
public class ActivityCommentsResult {

    private String MobileNumber;
    private String Comment;

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
