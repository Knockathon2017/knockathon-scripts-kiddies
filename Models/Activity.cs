using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Models
{
    public class CreateActivityRequestData
    {
        public CreateActivityRequestData(Activity activityData)
        {
            activity = activityData;
        }

        public Activity activity { get; set; }
    }

    public class Activity
    {
        public long workkard_id { get; set; }
        public string title { get; set; }
        public string description { get; set; }
        public long due_date { get; set; }
        public long owner_id { get; set; }
        public long assignee_team_member { get; set; }
        public string template_type { get; set; }
        public List<string> skillset { get; set; }
        public List<string> template_fields { get; set; }
    }

    public class ActivityCreationResponse
    {
        public long activity_id { get; set; }
    }
}
