package com.rsamadhan.network.response;

/**
 * Created by Anurag Singh on 14/9/15 11:11 AM.
 */
public class ActivityCommentsResult {

    private String MobileNumber;
    private String Comment;
    private String UserId;
    private String ActivityId;
    private String CreatedDate;
    private String CreatedDateString;

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

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String activityId) {
        ActivityId = activityId;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getCreatedDateString() {
        return CreatedDateString;
    }

    public void setCreatedDateString(String createdDateString) {
        CreatedDateString = createdDateString;
    }
}
