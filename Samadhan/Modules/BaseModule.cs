using Nancy;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Web;
using System.Web.Configuration;
using Utilities;

namespace Samadhan.Modules
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
            var http = (HttpWebRequest)WebRequest.Create(new Uri(url));
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
            var http = (HttpWebRequest)WebRequest.Create(new Uri(url));
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

        protected string ExzeoApiPutRequest(string url, string key, string postContent)
        {
            var http = (HttpWebRequest)WebRequest.Create(new Uri(url));
            http.Accept = "application/json";
            http.ContentType = "application/json";
            http.Method = "PUT";

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

        protected string ExzeoApiPutRequest(string url, string key)
        {
            var http = (HttpWebRequest)WebRequest.Create(new Uri(url));
            http.Accept = "application/json";
            http.ContentType = "application/json";            
            http.Method = "PUT";            

            http.Headers[HttpRequestHeader.Authorization] = WebConfigurationManager.AppSettings[key];

            var response = http.GetResponse();

            var stream = response.GetResponseStream();
            var sr = new StreamReader(stream);
            var content = sr.ReadToEnd();

            return content;
        }

        protected string GetKeyBasedOnDomain(string domain)
        {
            switch ((Enums.domain)Enum.Parse(typeof(Enums.domain), domain))
            {
                case Enums.domain.edu: return Constants.exzeoApiEducationKey;

                case Enums.domain.crime: return Constants.exzeoApiCrimeKey;

                case Enums.domain.admin: return Constants.exzeoApiAdministrationKey;
            }
            return null;
        }
    }
}