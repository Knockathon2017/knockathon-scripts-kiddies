using RSamadhanEntityFrameWork;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BusinessLayer
{
    public class UserComplaintsBusinessLayer
    {
        public UserComplaint GetUserComplaint(long? userId, string mobileNumber, string domain)
        {
            using (var db = new cc_web_aaEntities())
            {
                if (userId.HasValue)
                    return db.UserComplaints.FirstOrDefault(u => u.UserId == userId && u.Domain == domain);
                else
                    return db.UserComplaints.FirstOrDefault(u => u.MobileNumber == mobileNumber && u.Domain == domain);              
            }
        }

        public int CreateUserComplaint(long? userId, string mobileNumber, string complaintContent, string domain, string latitude, string longitude, bool isPublicComplaint, long workkardId, long activityId, long creatorId)
        {
            using (var db = new cc_web_aaEntities())
            {
                db.UserComplaints.Add(new UserComplaint
                {
                    UserId = userId,
                    MobileNumber = mobileNumber,
                    ComplaintContent = complaintContent,
                    Domain = domain,
                    Latitude = latitude,
                    Longitude = longitude,
                    IsPublicComplaint = isPublicComplaint,
                    WorkkardId = workkardId,
                    ActivityId = activityId,
                    creatorId = creatorId,
                    CreatedDate = DateTime.UtcNow,
                    ModifiedDate = DateTime.UtcNow
                });
                return db.SaveChanges();
            }
        }
    }
}
