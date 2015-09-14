using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace RSamadhanEntityFrameWork
{
    public partial class UserComment
    {
        public string CreatedDateString
        {
            get
            {
                return CreatedDate.ToString();
            }
        }

        public string ModifiedDateString
        {
            get
            {
                return ModifiedDate.ToString();
            }
        }
    }
}
