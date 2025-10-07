using System.ComponentModel.DataAnnotations;

namespace BattleFightGrupo1.Models
{
    public class Torneo
    {
        private int id;
        private string codigoTorneo;
        private DateTime fechaTorneo;
        private double premioMonetario;
        private string categoria;
        private List<TorneoEquipo> torneoEquipos;
        private string ganador;

        public Torneo(int id, string codigoTorneo, DateTime fechaTorneo, double premioMonetario, string categoria, List<TorneoEquipo> torneoEquipos, string ganador)
        {
            Id = id;
            CodigoTorneo = codigoTorneo;
            FechaTorneo = fechaTorneo;
            PremioMonetario = premioMonetario;
            Categoria = categoria;
            TorneoEquipos = torneoEquipos;
            Ganador = ganador;
        }
        public Torneo()
        {
            Id = 0;
            CodigoTorneo = "";
            FechaTorneo = DateTime.Today;
            PremioMonetario = 0;
            Categoria = "";
            TorneoEquipos = new List<TorneoEquipo>();
        }

        public int Id { get => id; set => id = value; }
        [Display(Name = "Código de Torneo")]
        public string CodigoTorneo { get => codigoTorneo; set => codigoTorneo = value; }
        [Display(Name = "Fecha Torneo")]
        public DateTime FechaTorneo { get => fechaTorneo; set => fechaTorneo = value; }
        [Display(Name = "Premio Monetario")]
        public double PremioMonetario { get => premioMonetario; set => premioMonetario = value; }
        [Display(Name = "Categoría")]
        public string Categoria { get => categoria; set => categoria = value; }
        public List<TorneoEquipo> TorneoEquipos { get => torneoEquipos; set => torneoEquipos = value; }
        public string Ganador { get => ganador; set => ganador = value; }

    }
}
