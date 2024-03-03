using System;
using System.Collections.Generic;
using System.Web.Mvc;
using MySql.Data.MySqlClient;
using WebApplication2.Models;

namespace WebApplication2.Controllers
{
    public class PostsController : Controller
    {
        private string _connectionString = "server=localhost;port=8889;user=Bianca;password=muzicuta123;database=WindowsApp2-asp_3;";

        public ActionResult ShowPosts(string username)
        {
            Session["username"] = username;
            using (var connection = new MySqlConnection(_connectionString))
            {
                connection.Open();
                string currentNumElementsQuery1 = "SELECT COUNT(*) AS numElements FROM Posts";
                MySqlCommand currentNumElementsCmd1 = new MySqlCommand(currentNumElementsQuery1, connection);
                int currentNumElements1 = Convert.ToInt32(currentNumElementsCmd1.ExecuteScalar());

                Session["nrElements"] = currentNumElements1;
                
                string query = "SELECT * FROM Posts";
                MySqlCommand command = new MySqlCommand(query, connection);
                List<Posts> result = new List<Posts>();
                using (var reader = command.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        int id = reader.GetInt32("id");
                        string user = reader.GetString("user");
                        int topicid = reader.GetInt32("topicid");
                        string text = reader.GetString("text");
                        DateTime date = reader.GetDateTime("date");
                        Posts post = new Posts(id, user, topicid.ToString(), text, date);
                        result.Add(post);
                    }

                    reader.Close();
                }

                foreach (Posts post in result)
                {
                    string query1 = "SELECT topicname FROM Topics where id = @topicid";
                    MySqlCommand command1 = new MySqlCommand(query1, connection);
                    string id = post.topic;

                    command1.Parameters.AddWithValue("@topicid", id);
                    var reader1 = command1.ExecuteReader();

                    if (reader1.Read())
                    {
                        String newTopic = reader1.GetString("topicname");
                        post.topic = newTopic;
                    }

                    reader1.Close();
                }

                ViewBag.List = result;
                connection.Close();
            }

            return View("Posts");
        }

public ActionResult ModifyPosts(string newTopic, string newText, string idd)
{
    int id = Int32.Parse(idd);

    using (var connection = new MySqlConnection(_connectionString))
    {
        connection.Open();

        string query1 = "SELECT id FROM Topics WHERE topicname = @newTopic";
        MySqlCommand command1 = new MySqlCommand(query1, connection);
        command1.Parameters.AddWithValue("@newTopic", newTopic);
        int topicId = -1;
        bool exists;

        using (var rider = command1.ExecuteReader())
        {
            exists = rider.Read();
            if (exists)
            {
                topicId = rider.GetInt32("id");
            }
        }

        if (exists)
        {
            string query2 = "UPDATE Posts SET topicid = @topicName, text = @text WHERE id = @postId";
            MySqlCommand command2 = new MySqlCommand(query2, connection);
            command2.Parameters.AddWithValue("@topicName", topicId);
            command2.Parameters.AddWithValue("@text", newText);
            command2.Parameters.AddWithValue("@postId", id);
            command2.ExecuteNonQuery();
        }
        else
        {
            string query3 = "INSERT INTO Topics (topicname) VALUES (@topic)";
            MySqlCommand command3 = new MySqlCommand(query3, connection);
            command3.Parameters.AddWithValue("@topic", newTopic);
            command3.ExecuteNonQuery();

            string query4 = "SELECT id FROM Topics ORDER BY id DESC LIMIT 1";
            MySqlCommand command4 = new MySqlCommand(query4, connection);
            
            using (var rider2 = command4.ExecuteReader())
            {
                if (rider2.Read())
                {
                    topicId = rider2.GetInt32("id");
                }
            }

            string query5 = "UPDATE Posts SET topicid = @topicName, text = @text WHERE id = @postId";
            MySqlCommand command5 = new MySqlCommand(query5, connection);
            command5.Parameters.AddWithValue("@topicName", topicId);
            command5.Parameters.AddWithValue("@text", newText);
            command5.Parameters.AddWithValue("@postId", id);
            command5.ExecuteNonQuery();
        }
    }

    string username = Session["username"] as string;
    return ShowPosts(username);
}

        public ActionResult AddPost(string addText, string addTopic)
        {
            using (var connection = new MySqlConnection(_connectionString))
            {
                connection.Open();
                // Check if the topic name appears in the database; if it does, get its id
                string query1 = "SELECT id FROM Topics WHERE topicname = @newTopic";
                MySqlCommand command1 = new MySqlCommand(query1, connection);
                command1.Parameters.AddWithValue("@newTopic", addTopic);
                var rider = command1.ExecuteReader();
                int topicId = -1;
                bool exists;
                if (rider.Read())
                {
                    exists = true;
                    topicId = rider.GetInt32("id");
                }
                else
                {
                    exists = false;
                }

                rider.Close();

                if (exists)
                {
                    string query2 =
                        "INSERT INTO Posts (user, topicid, text, date) VALUES (@user, @topicid, @text, @date)";
                    MySqlCommand command2 = new MySqlCommand(query2, connection);
                    string user = Session["username"] as string;
                    DateTime date = DateTime.Now;
                    command2.Parameters.AddWithValue("@user", user);
                    command2.Parameters.AddWithValue("@topicid", topicId);
                    command2.Parameters.AddWithValue("@text", addText);
                    command2.Parameters.AddWithValue("@date", date);
                    command2.ExecuteNonQuery();
                }
                else
                {
                    string query3 = "INSERT INTO Topics (topicname) VALUES (@topic)";
                    MySqlCommand command3 = new MySqlCommand(query3, connection);
                    command3.Parameters.AddWithValue("@topic", addTopic);
                    command3.ExecuteNonQuery();

                    string query4 = "SELECT id FROM Topics ORDER BY id DESC LIMIT 1";
                    MySqlCommand command4 = new MySqlCommand(query4, connection);
                    var rider1 = command4.ExecuteReader();
                    if (rider1.Read())
                    {
                        topicId = rider1.GetInt32("id");
                    }

                    rider1.Close();

                    string query2 =
                        "INSERT INTO Posts (user, topicid, text, date) VALUES (@user, @topicid, @text, @date)";
                    MySqlCommand command2 = new MySqlCommand(query2, connection);
                    string user = Session["username"] as string;
                    DateTime date = DateTime.Now;
                    command2.Parameters.AddWithValue("@user", user);
                    command2.Parameters.AddWithValue("@topicid", topicId);
                    command2.Parameters.AddWithValue("@text", addText);
                    command2.Parameters.AddWithValue("@date", date);
                    command2.ExecuteNonQuery();
                }
                string currentNumElementsQuery1 = "SELECT COUNT(*) AS numElements FROM Posts";
                MySqlCommand currentNumElementsCmd1 = new MySqlCommand(currentNumElementsQuery1, connection);
                int currentNumElements1 = Convert.ToInt32(currentNumElementsCmd1.ExecuteScalar());
                Session["nrElements"] = currentNumElements1;
                connection.Close();

            }

            string username = Session["username"] as string;
            Session["refresh"] = "yes";
            return ShowPosts(username);
        }
    }
}