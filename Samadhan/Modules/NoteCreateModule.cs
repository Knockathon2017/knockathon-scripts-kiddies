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
    public class NoteCreateModule : BaseModule
    {
        public NoteCreateModule()
        {
            Post["/ActivityAddNote"] = _ =>
                {
                    long activityId = Request.Form.ActivityId;
                    string descriptionText = Request.Form.Description;
                    string domain = Request.Form.Domain;

                    var key = GetKeyBasedOnDomain(domain);

                    var noteData = new Note()
                    {
                        privacy = Constants.PublicNote,
                        description = descriptionText
                    };

                    var postContent = JsonConvert.SerializeObject(noteData);
                    return ExzeoApiPutRequest(string.Format("{0}activity/add_note/{1}", Constants.exzeoApiBaseUrl, activityId), key, postContent);
                };
        }
    }
}