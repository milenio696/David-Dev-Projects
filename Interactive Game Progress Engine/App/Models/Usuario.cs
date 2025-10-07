using System.ComponentModel.DataAnnotations;

namespace BattleFightGrupo1.Models
{
    public class Usuario
    {
        private int id;
        private string nombre;
        private string cedula;
        private string genero;
        private DateTime fechaRegistro;
        private string estado;
        private string usuarioLogin;
        private string contrasena;

        public Usuario(int id, string nombre, string cedula, string genero, DateTime fechaRegistro, string estado, string usuarioLogin, string contrasena)
        {
            Id = id;
            Nombre = nombre;
            Cedula = cedula;
            Genero = genero;
            FechaRegistro = fechaRegistro;
            Estado = estado;
            UsuarioLogin = usuarioLogin;
            Contrasena = contrasena;
        }

        public Usuario()
        {
            Id = 0;
            Nombre = "";
            Cedula = "";
            Genero = "";
            FechaRegistro = DateTime.Today;
            Estado = "";
            UsuarioLogin = "";
            Contrasena = "";
        }

        public int Id { get => id; set => id = value; }
        public string Nombre { get => nombre; set => nombre = value; }
        public string Cedula { get => cedula; set => cedula = value; }
        public string Genero { get => genero; set => genero = value; }

        [Display(Name = "Fecha Registro")]
        public DateTime FechaRegistro { get => fechaRegistro; set => fechaRegistro = value; }
        public string Estado { get => estado; set => estado = value; }
        [Display(Name = "Nombre de Usuario")]
        public string UsuarioLogin { get => usuarioLogin; set => usuarioLogin = value; }
        [Display(Name = "Contraseña")]
        public string Contrasena { get => contrasena; set => contrasena = value; }
    }
}
