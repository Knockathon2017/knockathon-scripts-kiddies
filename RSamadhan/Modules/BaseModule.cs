using Nancy;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Web;
using System.Web.Configuration;
using Utilities;

namespace RSamadhan.Modules
{
    public class BaseModule : NancyModule
    {
        public BaseModule()
        {
        }

        protected BaseModule(string modulePath)
            : base(modulePath)
        {
        }

        protected string ExzeoApiGetRequest(string url, string key)
        {
            var baseAddress = url;
            var http = (HttpWebRequest)WebRequest.Create(new Uri(baseAddress));
            http.Accept = "application/json";
            http.ContentType = "application/json";
            http.Method = "GET";

            http.Headers[HttpRequestHeader.Authorization] = WebConfigurationManager.AppSettings[key];

            var response = http.GetResponse();

            var stream = response.GetResponseStream();
            var sr = new StreamReader(stream);
            var content = sr.ReadToEnd();

            return content;
        }

        protected string ExzeoApiPostRequest(string url, string key, string postContent)
        {
            var baseAddress = url;
            var http = (HttpWebRequest)WebRequest.Create(new Uri(baseAddress));
            http.Accept = "application/json";
            http.ContentType = "application/json";
            http.Method = "POST";
                  
            ASCIIEncoding encoding = new ASCIIEncoding();
            Byte[] bytes = encoding.GetBytes(postContent);

            Stream newStream = http.GetRequestStream();
            newStream.Write(bytes, 0, bytes.Length);
            newStream.Close();

            http.Headers[HttpRequestHeader.Authorization] = WebConfigurationManager.AppSettings[key];

            var response = http.GetResponse();

            var stream = response.GetResponseStream();
            var sr = new StreamReader(stream);
            var content = sr.ReadToEnd();

            return content;
        }
    }
}