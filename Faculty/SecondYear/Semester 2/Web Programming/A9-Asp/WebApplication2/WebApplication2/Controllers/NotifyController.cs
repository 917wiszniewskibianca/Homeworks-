using System;
using System.IO;
using System.Web.Mvc;
using System.Web.Routing;
using MySql.Data.MySqlClient;

namespace WebApplication2.Controllers
{
    public class NotifyController : Controller
    {
        [HttpGet]
        public ActionResult ProcessRequest(RouteCollection routes)
        {
            string connectionString = "server=localhost;port=8889;user=Bianca;password=muzicuta123;database=WindowsApp2-asp_3;";

            try
            {
                using (MySqlConnection conn = new MySqlConnection(connectionString))
                {
                    conn.Open();
                    string currentNumElementsQuery1 = "SELECT COUNT(*) AS numElements FROM Posts";
                    MySqlCommand currentNumElementsCmd1 = new MySqlCommand(currentNumElementsQuery1, conn);
                    int currentNumElements1 = Convert.ToInt32(currentNumElementsCmd1.ExecuteScalar());

                    int previousNumElements = Convert.ToInt32(Session["nrElements"]);
                    if (currentNumElements1 != previousNumElements)
                    {
                        string currentNumElementsQuery = "SELECT * FROM Posts ORDER BY id DESC LIMIT 1";
                        MySqlCommand currentNumElementsCmd = new MySqlCommand(currentNumElementsQuery, conn);
                        var currentNumElementsReader = currentNumElementsCmd.ExecuteReader();

                        if (currentNumElementsReader.Read())
                        {
                            string user = currentNumElementsReader.GetString(currentNumElementsReader.GetOrdinal("user"));
                            int topic = currentNumElementsReader.GetInt32(currentNumElementsReader.GetOrdinal("topicid"));
                            string text = currentNumElementsReader.GetString(currentNumElementsReader.GetOrdinal("text"));
                            DateTime date = currentNumElementsReader.GetDateTime(currentNumElementsReader.GetOrdinal("date"));

                            var result = new
                            {
                                user = user,
                                topic = topic,
                                text = text,
                                date = date.ToString("yyyy-MM-dd")
                            };

                            return Json(result, JsonRequestBehavior.AllowGet);
                        }
                        else
                        {
                            return Content("");
                        }

                        currentNumElementsReader.Close();
                    }

                    conn.Close();
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
            }

            return Content("");
        }
    }
}
