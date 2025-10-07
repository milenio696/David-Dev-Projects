using System.ComponentModel.DataAnnotations;
using static BattleFightGrupo1.Services.Service;

namespace BattleFightGrupo1.Models
{
    public class Equipo
    {
        private int id;
        private string nombreEquipo;
        private string jugador1;
        private string jugador2;
        private string jugador3;
        private string jugador4;
        private int puntaje;
        private string categoria;
        private string codigoEquipo;
        private DateTime fechaRegistro;
        public List<TorneoEquipo> torneoEquipos { get; set; }

        public Equipo(int id, string nombreEquipo, string jugador1, string jugador2, string jugador3, string jugador4, int puntaje, string categoria, string codigoEquipo, DateTime fechaRegistro, TorneoEquipo torneoEquipos)
        {
            this.id = id;
            this.nombreEquipo = nombreEquipo;
            this.jugador1 = jugador1;
            this.jugador2 = jugador2;
            this.jugador3 = jugador3;
            this.jugador4 = jugador4;
            this.puntaje = puntaje;
            this.categoria = categoria;
            this.codigoEquipo = codigoEquipo;
            this.fechaRegistro = fechaRegistro;
            this.torneoEquipos = new List<TorneoEquipo>();
        }
        public Equipo()
        {
            this.id = 0;
            this.nombreEquipo = "";
            this.jugador1 = "";
            this.jugador2 = "";
            this.jugador3 = "";
            this.jugador4 = "";
            this.puntaje = 0;
            this.categoria = "";
            this.codigoEquipo = "";
            this.fechaRegistro = DateTime.Today;
            this.torneoEquipos = new List<TorneoEquipo>();
        }

        public void fillCodigoEquipo()
        {
            {
                string tipoEquipo = Categoria.Substring(0, 1);
                int cantidadJugadores = 0;
                if (!string.IsNullOrEmpty(Jugador1)) cantidadJugadores++;
                if (!string.IsNullOrEmpty(Jugador2)) cantidadJugadores++;
                if (!string.IsNullOrEmpty(Jugador3)) cantidadJugadores++;
                if (!string.IsNullOrEmpty(Jugador4)) cantidadJugadores++;
                string randomDigits = new Random().Next(10, 100).ToString();
                string anioRegistro = FechaRegistro.Year.ToString().Substring(2, 2);
                CodigoEquipo = $"{tipoEquipo}{cantidadJugadores}{randomDigits}{anioRegistro}";
            }
        }

        public int Id { get => id; set => id = value; }

        [Display(Name = "Nombre Equipo")]
        public string NombreEquipo { get => nombreEquipo; set => nombreEquipo = value; }

        [Display(Name = "Jugador 1")]
        public string Jugador1 { get => jugador1; set => jugador1 = value; }

        [Display(Name = "Jugador 2")]
        public string Jugador2 { get => jugador2; set => jugador2 = value; }

        [Display(Name = "Jugador 3")]
        public string Jugador3 { get => jugador3; set => jugador3 = value; }

        [Display(Name = "Jugador 4")]
        public string Jugador4 { get => jugador4; set => jugador4 = value; }

        [Range(1, 100,
        ErrorMessage = "El valor del puntaje deb ser entre {1} y {2}.")]
        public int Puntaje { get => puntaje; set => puntaje = value; }

        [Display(Name = "Categoría")]
        public string Categoria { get => categoria; set => categoria = value; }

        [Display(Name = "Código Equipo")]
        public string CodigoEquipo { get => codigoEquipo; set => codigoEquipo = value; }

        [Display(Name = "Fecha Registro")]
        public DateTime FechaRegistro { get => fechaRegistro; set => fechaRegistro = value; }
        public List<TorneoEquipo> TorneoEquipos { get; set; }

    }
}
