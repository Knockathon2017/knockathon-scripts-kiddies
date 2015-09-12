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
                    string mobileNumber = parameter.MobileNumber;
                    string domain = parameter.Domain;

                    long workkardId = userComplaintsBusinessLayer.GetWorkkardId(userId, mobileNumber, domain);

                    return ExzeoApiGetRequest(string.Format("{0}workkard/activities/{1}", Constants.exzeoApiBaseUrl, workkardId), GetKeyBasedOnDomain(domain));
                };

            Get["/PublicActivities/{domain}"] = parameter =>
                {
                    string domain = parameter.domain;
                    var activitiesIds = userComplaintsBusinessLayer.GetOpenPublicActivitiesIds(domain);
                    List<ActivityDetail> activityDetailsList = new List<ActivityDetail>();

                    foreach (var activityId in activitiesIds)
                    {
                        var activityDetail = JsonConvert.DeserializeObject<ActivityDetail>(ExzeoApiGetRequest(string.Format("{0}activity/{1}", Constants.exzeoApiBaseUrl, activityId), GetKeyBasedOnDomain(domain)));
                        activityDetailsList.Add(activityDetail);
                    }

                    return Response.AsJson(activityDetailsList);
                };
        }        
    }
}