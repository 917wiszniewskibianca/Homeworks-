namespace WebApplication1.Models
{
    public class Person
    {
        public int Id { get; set; }
        public string Name { get; set; }
        
        public string Password { get; set; }
        public string Role { get; set; }
        
      

        public Person(int id, string name, string password,string role)
        {
            Id = id;
            Name = name;
            Password = password;
            Role = role;
        }

        public void Deconstruct(out int id, out string name,out string password, out string role)
        {
            id = Id;
            name = Name;
            password = Password;
            role = Role;
        }
    }
}