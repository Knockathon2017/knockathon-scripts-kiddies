using BusinessLayer;
using Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Utilities;
using Nancy;

namespace Samadhan.Modules
{
    public class ActivitiesModule : BaseModule
    {
        UserComplaintsBusinessLayer userComplaintsBusinessLayer = new UserComplaintsBusinessLayer();

        public ActivitiesModule()
        {
            Get["/AllWorkkardActivities/{UserId?}/{MobileNumber}/{Domain}"] = parameter =>
                {
                    string userIdString = parameter.UserId;
                    long? userId = userIdString != null ? long.Parse(userIdString) : (long?)null;
                    //long? userId = null;
                    string mobileNumber = parameter.MobileNumber;
                    string domain = parameter.Domain;

                    long? workkardId = userComplaintsBusinessLayer.GetWorkkardId(userId, mobileNumber, domain);

                    if (workkardId.HasValue)
                    {
                        var activitiesJson = ExzeoApiGetRequest(string.Format("{0}workkard/activities/{1}", Constants.exzeoApiBaseUrl, workkardId), GetKeyBasedOnDomain(domain));
                        var userActivityDetail = JsonConvert.DeserializeObject<UserActivityDetail>(activitiesJson);

                        foreach (var activity in userActivityDetail.results)
                        {
                            activity.activity_description = userComplaintsBusinessLayer.GetComplaintByActivityId(activity.activity_id).ComplaintContent;
                        }
                        return Response.AsJson(userActivityDetail);
                    }
                    else
                        return Response.AsJson(new UserActivityDetail());
                };

            Get["/PublicActivities/{UserId?}/{MobileNumber}/{Domain}"] = parameter =>
                {
                    string userIdString = parameter.UserId;
                    long? userId = userIdString != null ? long.Parse(userIdString) : (long?)null;      
                    //long? userId = null;
                    string mobileNumber = parameter.MobileNumber;
                    string domain = parameter.Domain;

                    var activitiesList = userComplaintsBusinessLayer.GetOpenPublicActivities(userId, mobileNumber, domain);
                    List<ActivityDetail> activityDetailsList = new List<ActivityDetail>();

                    foreach (var activity in activitiesList)
                    {
                        var activityDetailJson = ExzeoApiGetRequest(string.Format("{0}activity/{1}", Constants.exzeoApiBaseUrl, activity.ActivityId), GetKeyBasedOnDomain(domain));
                        var activityDetail = JsonConvert.DeserializeObject<ActivityDetail>(activityDetailJson);
                        activityDetail.activity_description = activity.ComplaintContent;
                        activityDetailsList.Add(activityDetail);
                    }

                    return Response.AsJson(activityDetailsList);
                };
        }        
    }
}