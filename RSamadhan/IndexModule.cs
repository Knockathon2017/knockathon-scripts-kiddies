using Nancy;
using System;
using System.IO;
using System.Net;

namespace RSamadhan
{   
    public class IndexModule : NancyModule
    {
        public IndexModule()
        {
            Get["/"] = parameters =>
            {
                //return View["index"];
                //var baseAddress = "https://publicapi.tryexzeo.com/1.0/workkard/97090";
                //var http = (HttpWebRequest)WebRequest.Create(new Uri(baseAddress));
                //http.Accept = "application/json";
                //http.ContentType = "application/json";
                //http.Method = "GET";

                ////string parsedContent = <<PUT HERE YOUR JSON PARSED CONTENT>>;
                ////ASCIIEncoding encoding = new ASCIIEncoding();
                ////Byte[] bytes = encoding.GetBytes(parsedContent);

                ////Stream newStream = http.GetRequestStream();
                ////newStream.Write(bytes, 0, bytes.Length);
                ////newStream.Close();

                //http.Headers[HttpRequestHeader.Authorization] = "Bearer NTI4ZmRjNTMtMzRiOC00MGMxLTljY2UtYjYyMGE3YjI3N2Fk";

                //var response = http.GetResponse();

                //var stream = response.GetResponseStream();
                //var sr = new StreamReader(stream);
                //var content = sr.ReadToEnd();


                //var x = Request.

                return "Hello122221";
            };
        }
    }
}