package com.rsamadhan.network.response;

import java.util.ArrayList;

/**
 * Created by Prathmesh on 12-09-2015.
 */
public class ActivityCommentsListData {
    private ArrayList<ActivityCommentsResult> ActivityCommentsResult;

    public ArrayList<com.rsamadhan.network.response.ActivityCommentsResult> getActivityCommentsResult() {
        return ActivityCommentsResult;
    }

    public void setActivityCommentsResult(ArrayList<com.rsamadhan.network.response.ActivityCommentsResult> activityCommentsResult) {
        ActivityCommentsResult = activityCommentsResult;
    }
}
