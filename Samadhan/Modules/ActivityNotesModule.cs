using BusinessLayer;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Utilities;
using Nancy;
using Models;

namespace Samadhan.Modules
{
    public class ActivityNotesModule : BaseModule
    {
        UserCommentsBusinessLayer userCommentsBusinessLayer = new UserCommentsBusinessLayer();

        public ActivityNotesModule()
        {
            Get["/activityNotes/{id}/{domain}"] = parameters =>
                {
                    var activityId = parameters.id;
                    var domain = parameters.domain;

                    return Response.AsJson(new ActivityCommentsClass { ActivityCommentsResult = userCommentsBusinessLayer.GetActivityCommentsList(activityId) });
                };
        }
    }
}