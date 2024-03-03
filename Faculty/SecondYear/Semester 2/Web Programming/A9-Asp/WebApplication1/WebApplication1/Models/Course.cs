using System.Collections.Generic;

namespace WebApplication1.Models
{
    public class Course
    {
        public int Id { get; set; }
        public int ProfessorId { get; set; }
        public string CourseName { get; set; }
        public List<string> Participants { get; set; }
        public List<int> Grades {get ; set;  }

        
        public Course(int id, int professorId, string courseName, List<string> participants, List<int> grades)
        {
            Id = id;
            ProfessorId = professorId;
            CourseName = courseName;
            Participants = participants;
            Grades = grades;
        }

        public void Deconstruct(out int id, out int professorId, out string courseName, out List<string> participants, out List<int> grades)
        {
            id = Id;
            professorId = ProfessorId;
            courseName = CourseName;
            participants = Participants;
            grades = Grades;
        }
        
    }
}