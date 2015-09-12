using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Models
{
    public class ActivityDetail
    {
        public long activity_id { get; set; }
        public string activity_title { get; set; }
        public string activity_description { get; set; }
        public DateTime created_date { get; set; }
        public string status { get; set; }
        public DateTime last_updated_date { get; set; }
        public long workkard_id { get; set; }
    }    
}
