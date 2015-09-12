using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Utilities;

namespace Samadhan.Modules
{
    public class ActivityNotesModule : BaseModule
    {
        public ActivityNotesModule()
        {
            Get["/activityNotes/{id}/{domain}"] = parameters =>
                {
                    var activityId = parameters.id;
                    var domain = parameters.domain;
                    return ExzeoApiGetRequest(string.Format("{0}activity/notes/{1}", Constants.exzeoApiBaseUrl, activityId), GetKeyBasedOnDomain(domain));                        
                };
        }
    }
}