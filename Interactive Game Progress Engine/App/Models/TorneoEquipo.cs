using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace BattleFightGrupo1.Models
{
    public class TorneoEquipo
    {

        private int torneoId;
        private Torneo torneo;
        private int equipoId;
        private Equipo equipo;
        private DateTime fechaDeCompetencia;

        public TorneoEquipo(int torneoId, Torneo torneo, int equipoId, Equipo equipo, DateTime fechaDeCompetencia)
        {
            this.torneoId = torneoId;
            this.torneo = torneo;
            this.equipoId = equipoId;
            this.equipo = equipo;
            this.fechaDeCompetencia = fechaDeCompetencia;
        }
        public TorneoEquipo()
        {
            this.torneoId = 0; ;
            this.torneo = new Torneo();
            this.equipoId = 0;
            this.equipo = new Equipo();
            this.fechaDeCompetencia = DateTime.Today;
        }

        [Key, Column(Order = 0)]
        public int TorneoId { get; set; }
        public Torneo Torneo { get; set; }

        [Key, Column(Order = 1)]
        public int EquipoId { get; set; }
        public Equipo Equipo { get; set; }

        [Display(Name = "Fecha de Torneo")]
        public DateTime Fecha { get; set; } = DateTime.Now;
    }
}
