using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Utilities;

namespace Samadhan.Modules
{
    public class ActivityActionsModule : BaseModule
    {
        public ActivityActionsModule()
        {
            Get["/closeActivity/{id}/{domain}"] = parameter =>
                {
                    long activityId = parameter.id;
                    string domain = parameter.domain;
                    return ExzeoApiPutRequest(string.Format("{0}activity/close/{1}", Constants.exzeoApiBaseUrl, activityId), GetKeyBasedOnDomain(domain));
                };
        }
    }
}