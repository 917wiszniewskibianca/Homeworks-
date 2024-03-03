using System;
using System.Web.Mvc;
using System.Web.Security;
using MySql.Data.MySqlClient;

namespace WebApplication1.Controllers
{
    public class HomeController : Controller
    {    private string _connectionString = "server=localhost;port=8889;user=Bianca;password=muzicuta123;database=WebApplication1-Asp2;";
        // GET: Course/CoursesStudentParticipates
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Login(string username, string password)
        {
            using (var connection = new MySqlConnection(_connectionString))
            {
                    connection.Open();

                    string query = "SELECT password FROM Person Where name=@username and role='professor'";
                    MySqlCommand command = new MySqlCommand(query, connection);
                    command.Parameters.AddWithValue("@username", username);
                    using (var reader = command.ExecuteReader())
                    {
                        if (reader.Read())
                        {
                            string databasePassword = reader.GetString("password");
                            if (databasePassword == password)
                            {
                                // Authentication successful - keep the info in a cookie 
                                FormsAuthentication.SetAuthCookie(username, false);
                                return RedirectToAction("FirstPage", "FirstPage");


                            }
                        }
                    }
                    
                    connection.Close();

            }


            ViewBag.ErrorMessage = "Invalid username or password.";
            return View("Index");
        }

    }
}