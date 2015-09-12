using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Models
{
    public class CreateWorkkardRequestData
    {
        public CreateWorkkardRequestData(Workkard workkardData)
        {
            workkard = workkardData;
        }

        public Workkard workkard { get; set; }
    }

    public class Workkard
    {
        public string workkard_number { get; set; }
        public string workkard_title { get; set; }
        public string workkard_short_name { get; set; }
        public string workkard_summary { get; set; }
        public List<string> template_fields { get; set; }
    }

    public class WorkkardCreationResponse
    {
        public long workkard_id { get; set; }
        public Creator creator { get; set; }
    }

    public class Creator
    {
        public long user_id { get; set; }
    }
}
