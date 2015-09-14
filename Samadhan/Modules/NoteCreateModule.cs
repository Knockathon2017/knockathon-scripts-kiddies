using Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Utilities;
using Nancy;
using BusinessLayer;

namespace Samadhan.Modules
{
    public class NoteCreateModule : BaseModule
    {
        UserCommentsBusinessLayer userCommentsBusinessLayer = new UserCommentsBusinessLayer();

        public NoteCreateModule()
        {
            Post["/ActivityAddNote"] = _ =>
                {
                    string userIdString = Request.Form.UserId;
                    long? userId = userIdString != null ? long.Parse(userIdString) : (long?)null;
                    string mobileNumber = Request.Form.MobileNumber;
                    long activityId = Request.Form.ActivityId;
                    string descriptionText = Request.Form.Description;

                    userCommentsBusinessLayer.CreateUserComment(userId, mobileNumber, descriptionText, activityId);
                    return Response.AsJson(new ResponseResult { Result = Enums.ResponseResult.success.ToString() });
                };
        }
    }
}