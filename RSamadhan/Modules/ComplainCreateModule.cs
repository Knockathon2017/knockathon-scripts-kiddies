using BusinessLayer;
using Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Utilities;
using Nancy;

namespace RSamadhan.Modules
{
    public class ComplainCreateModule : BaseModule
    {
        public ComplainCreateModule()
        {
            UserComplaintsBusinessLayer userComplaintsBusinessLayer = new UserComplaintsBusinessLayer();

            Post["/CreateComplain"] = _ =>
                {
                    string userIdString = Request.Form.UserId;
                    long? userId = userIdString != null ? long.Parse(userIdString) : (long?)null;                    
                    string mobileNumber = Request.Form.MobileNumber;
                    string complaintContent = Request.Form.ComplaintContent;
                    string domain = Request.Form.Domain;
                    string latitude = Request.Form.Latitude;
                    string longitude = Request.Form.Longitude;
                    bool isPublicComplaint = Request.Form.IsPublicComplaint;

                    string key = string.Empty;
                    string workkardTitle = string.Empty;
                    string workkardSummary = string.Empty;
                    string response = string.Empty;

                    switch ((Enums.domain)Enum.Parse(typeof(Enums.domain), domain))
                    {
                        case Enums.domain.edu:
                            {
                                key = Constants.exzeoApiEducationKey;
                                workkardTitle = Constants.educationTitle;
                                workkardSummary = Constants.educationSummary;
                                break;
                            }

                        case Enums.domain.crime:
                            {
                                key = Constants.exzeoApiHealthKey;
                                workkardTitle = Constants.healthTitle;
                                workkardSummary = Constants.healthSummary;
                                break;
                            }

                        case Enums.domain.admin:
                            {
                                key = Constants.exzeoApiAdministrationKey;
                                workkardTitle = Constants.administrationTitle;
                                workkardSummary = Constants.administrationSummary;
                                break;
                            }
                    }

                    var userComplaint = userComplaintsBusinessLayer.GetUserComplaint(userId, mobileNumber, domain);

                    if (userComplaint == null)
                    {
                        string workkardNumber = userId.HasValue ? userId.Value.ToString() : mobileNumber;                        
                                                
                        var workkard = new Workkard()
                        {   
                            workkard_number = workkardNumber,
                            workkard_title = workkardTitle,
                            workkard_short_name = Constants.defaultWorkkardShortName,
                            workkard_summary = workkardSummary                                    
                        };

                        var createWorkkardData = new CreateWorkkardRequestData(workkard);
                        var postContent = JsonConvert.SerializeObject(createWorkkardData);
                        response = ExzeoApiPostRequest(string.Format("{0}workkard/new", Constants.exzeoApiBaseUrl), key, postContent);

                        var workkardCreationResponse = JsonConvert.DeserializeObject<WorkkardCreationResponse>(response);
                        long workkardId = workkardCreationResponse.workkard_id;
                        long creatorId = workkardCreationResponse.creator.user_id;

                        response = CreateActivity(domain, workkardId, workkardTitle, complaintContent, creatorId, key);
                        var activityCreationResponse = JsonConvert.DeserializeObject<ActivityCreationResponse>(response);
                        long activityId = activityCreationResponse.activity_id;

                        userComplaintsBusinessLayer.CreateUserComplaint(userId, mobileNumber, complaintContent, domain, latitude, longitude, isPublicComplaint, workkardId, activityId, creatorId);
                    }
                    else
                    {
                        response = CreateActivity(domain, userComplaint.WorkkardId, workkardTitle, complaintContent, userComplaint.creatorId, key);
                        var activityCreationResponse = JsonConvert.DeserializeObject<ActivityCreationResponse>(response);
                        long activityId = activityCreationResponse.activity_id;
                        userComplaintsBusinessLayer.CreateUserComplaint(userId, mobileNumber, complaintContent, domain, latitude, longitude, isPublicComplaint, userComplaint.WorkkardId, activityId, userComplaint.creatorId);
                    }
                    return Response.AsJson(new ResponseResult { Result = Enums.ResponseResult.success.ToString() });
                };
        }

        private string CreateActivity(string domain, long workkardId, string workkardTitle, string complaintContent, long creatorId, string key)
        {
            List<string> skillsetList = new List<string>();
            skillsetList.Add(domain);            
            TimeSpan epochTime = DateTime.UtcNow.AddDays(20) - new DateTime(1970, 1, 1);
            long timestamp = (long)epochTime.TotalSeconds;

            var activity = new Activity
            {
                workkard_id = workkardId,
                title = workkardTitle,
                description = complaintContent,
                template_type = Constants.defaultActivityShortName,
                skillset = skillsetList,
                due_date = timestamp,
                owner_id = creatorId,
                assignee_team_member = creatorId
            };

            var createActivityData = new CreateActivityRequestData(activity);
            var postContent = JsonConvert.SerializeObject(createActivityData);
            return ExzeoApiPostRequest(string.Format("{0}activity/new", Constants.exzeoApiBaseUrl), key, postContent);
        }
    }
}