using RSamadhanEntityFrameWork;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace BusinessLayer
{
    public class UserCommentsBusinessLayer
    {
        public int CreateUserComment(long? userId, string mobileNumber, string comment, long activityId)
        {
            using (var db = new cc_web_aaEntities())
            {
                db.UserComments.Add(new UserComment
                {
                    UserId = userId,
                    MobileNumber = mobileNumber,
                    Comment = comment,
                    ActivityId = activityId,
                    CreatedDate = DateTime.UtcNow,
                    ModifiedDate = DateTime.UtcNow
                });
                return db.SaveChanges();
            }
        }

        public List<UserComment> GetActivityCommentsList(long activityId)
        {
            using (var db = new cc_web_aaEntities())
            {
                return db.UserComments.Where(c => c.ActivityId == activityId).ToList();
            }
        }
    }
}
