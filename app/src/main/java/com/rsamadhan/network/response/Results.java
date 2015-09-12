package com.rsamadhan.network.response;

/**
 * Created by Prathmesh on 12-09-2015.
 */
public class Results {

    String activity_id;
    String activity_title;
    String activity_description;
    String description;
    String created_date;
    private String status;

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getActivity_description() {
        return activity_description;
    }

    public void setActivity_description(String activity_description) {
        this.activity_description = activity_description;
    }

    public String getActivity_title() {
        return activity_title;
    }

    public void setActivity_title(String activity_title) {
        this.activity_title = activity_title;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
