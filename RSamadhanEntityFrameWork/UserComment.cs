//------------------------------------------------------------------------------
// <auto-generated>
//    This code was generated from a template.
//
//    Manual changes to this file may cause unexpected behavior in your application.
//    Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace RSamadhanEntityFrameWork
{
    using System;
    using System.Collections.Generic;
    
    public partial class UserComment
    {
        public int Id { get; set; }
        public Nullable<long> UserId { get; set; }
        public string MobileNumber { get; set; }
        public string Comment { get; set; }
        public long ActivityId { get; set; }
        public System.DateTime CreatedDate { get; set; }
        public System.DateTime ModifiedDate { get; set; }
    }
}