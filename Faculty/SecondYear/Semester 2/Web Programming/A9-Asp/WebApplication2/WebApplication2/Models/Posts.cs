using System;

namespace WebApplication2.Models
{
    public class Posts
    {
        public int id { get; set; }
        public string user { get; set; }
        public string topic { get; set; }
        public string text { get; set; }
        public DateTime date { get; set; }

        public Posts(int id, string user, string topicid, string text, DateTime date)
        {
            this.id = id;
            this.user = user;
            this.topic = topicid;
            this.text = text;
            this.date = date;
        }
        
    }
}