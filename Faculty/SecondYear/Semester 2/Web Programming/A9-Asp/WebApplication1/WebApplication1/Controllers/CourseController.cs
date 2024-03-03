using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;
using MySql.Data.MySqlClient;

namespace WebApplication1.Controllers
{
    public class CourseController : Controller
    {
        private string _connectionString = "server=localhost;port=8889;user=Bianca;password=muzicuta123;database=WebApplication1-Asp2;";

        // GET: Course/CoursesStudentParticipates
        public ActionResult AllCoursesForStudent(string studentName)
        {
            using (var connection = new MySqlConnection(_connectionString))
            {
                connection.Open();
                string query = "SELECT coursename,participants FROM Course";
                MySqlCommand command = new MySqlCommand(query, connection);
                List<string> result = new List<string>();
                using (var reader = command.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        string courseName = reader.GetString("coursename");
                        string participants = reader.GetString("participants");
                        string[] stringArray = participants.Split(',');
                        bool exists = stringArray.Contains(studentName);
                        if (exists)
                        {
                            result.Add(courseName);
                        }
                        
                    }
                    ViewBag.List = result;
                    
                }
            }
            return View("AllCoursesForStudent");
        }

        public ActionResult AllParticipantsToCourse(string courseName)
        {
            using (var connection = new MySqlConnection(_connectionString))
            {
                connection.Open();
                string query = "SELECT participants FROM Course where coursename=@courseName"; // am luat participantii de la cursul spus 
                MySqlCommand command = new MySqlCommand(query, connection);     
                command.Parameters.AddWithValue("@courseName", courseName);

                using (var reader = command.ExecuteReader())
                {
                    if(reader.Read())
                    {
                        string participants = reader.GetString("participants");
                        string[] stringArray = participants.Split(',');
                        ViewBag.StringArray = stringArray;
                        
                    }
                }
                connection.Close();
            }
            return View("AllParticipantsToCourse");
        }

        public ActionResult AddGrade(string studentName, string courseName, string studentGrade)
        {
            using (var connection = new MySqlConnection(_connectionString))
            {  Console.WriteLine("we are in addGrade");
                connection.Open();
                string query = "SELECT participants,gardes FROM Course where coursename=@courseName"; // am luat participantii de la cursul spus 
                MySqlCommand command = new MySqlCommand(query, connection);     
                command.Parameters.AddWithValue("@courseName", courseName);
                var reader = command.ExecuteReader();
                if (reader.Read())
                {
                    string participants1 = reader.GetString("participants");
                    string grades = reader.GetString("gardes");
                    string[] stringArray1 = participants1.Split(',');
                    string[] gradesArray = grades.Split(',');
                    bool exists = stringArray1.Contains(studentName);

                    if (exists)
                    {
                        Console.WriteLine("we are in exists in addgrade");
                        int index = Array.IndexOf(stringArray1, studentName);
                        gradesArray[index] = studentGrade;
                        // query to add everything back to the database 
                        string query1 = "UPDATE Course SET gardes = @Grades WHERE coursename = @CourseName";
                        MySqlCommand command1 = new MySqlCommand(query1, connection);
                        command1.Parameters.AddWithValue("@Grades", string.Join(",", gradesArray));
                        command1.Parameters.AddWithValue("@CourseName", courseName);
                        command1.ExecuteNonQuery();
                    }
                    else
                    {
                        Console.WriteLine("we are in doesnt exist in addgrade");
                        stringArray1.Append(studentName);
                        gradesArray.Append(grades.ToString());
                        string query1 = "UPDATE Course SET gardes = @Grades WHERE coursename = @CourseName";
                        MySqlCommand command1 = new MySqlCommand(query1, connection);
                        command1.Parameters.AddWithValue("@Grades", string.Join(",", gradesArray));
                        command1.Parameters.AddWithValue("@CourseName", courseName);
                        command1.ExecuteNonQuery();

                        string query2 = "UPDATE Course SET participants = @Participants WHERE coursename = @CourseName";
                        MySqlCommand command2 = new MySqlCommand(query2, connection);
                        command2.Parameters.AddWithValue("@Participants", string.Join(",", stringArray1));
                        command2.Parameters.AddWithValue("@CourseName", courseName);
                        command2.ExecuteNonQuery();
                    }
                }


                connection.Close();
            }
            return RedirectToAction("showOnPage", "FirstPage");
        
        }
    }
}