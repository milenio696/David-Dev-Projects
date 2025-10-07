using BattleFightGrupo1.Models;
using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;
using System.Data.Entity;
using System.Data.Entity.Migrations;

namespace BattleFightGrupo1.Services
{
    public class Service : DbContext
    {
        #region DbSets
        public DbSet<Equipo> equipoDB { get; set; }
        public DbSet<Torneo> torneoDB { get; set; }
        public DbSet<Usuario> usuarioDB { get; set; }
        public DbSet<TorneoEquipo> TorneoEquipos { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<TorneoEquipo>().HasKey(te => new { te.TorneoId, te.EquipoId });
            modelBuilder.Entity<TorneoEquipo>().HasRequired(te => te.Torneo).WithMany(t => t.TorneoEquipos).HasForeignKey(te => te.TorneoId).WillCascadeOnDelete(false);
            modelBuilder.Entity<TorneoEquipo>().HasRequired(te => te.Equipo).WithMany(e => e.TorneoEquipos).HasForeignKey(te => te.EquipoId).WillCascadeOnDelete(false);
            base.OnModelCreating(modelBuilder);
        }
        #endregion

        public Service() : base("BattleFight") { }

        #region equipo
        public void AddEquipo(Equipo equipo)
        {
            equipoDB.Add(equipo);
            SaveChanges();
        }

        public Equipo GetEquipoById(int id)
        {
            return equipoDB.FirstOrDefault(e => e.Id == id);
        }

        public List<Equipo> GetAllEquipos()
        {
            return equipoDB.ToList();
        }

        public List<Equipo> FiltroPorCategoria(string categoria)
        {
            if (categoria == "Seleccione una Categoría")
            {
                return GetAllEquipos();
            }

            var listaPorCategoriaFiltrados = equipoDB.Where(x => x.Categoria == categoria).ToList();
            return listaPorCategoriaFiltrados;
        }



        public void UpdateEquipo(Equipo updatedEquipo)
        {
            var existingEquipo = equipoDB.FirstOrDefault(p => p.Id == updatedEquipo.Id);
            if (existingEquipo != null)
            {
                existingEquipo.NombreEquipo = updatedEquipo.NombreEquipo;
                existingEquipo.Jugador1 = updatedEquipo.Jugador1;
                existingEquipo.Jugador2 = updatedEquipo.Jugador2;
                existingEquipo.Jugador3 = updatedEquipo.Jugador3;
                existingEquipo.Jugador4 = updatedEquipo.Jugador4;
                existingEquipo.Puntaje = updatedEquipo.Puntaje;
                existingEquipo.Categoria = updatedEquipo.Categoria;
                existingEquipo.CodigoEquipo = updatedEquipo.CodigoEquipo;
                existingEquipo.FechaRegistro = updatedEquipo.FechaRegistro;
            }
            SaveChanges();
        }

        public void DeleteEquipo(int id)
        {
            var equipo = equipoDB.FirstOrDefault(e => e.Id == id);
            if (equipo != null)
            {
                equipoDB.Remove(equipo);
                SaveChanges();
            }
        }
        #endregion

        #region torneo
        public void AddTorneo(Torneo torneo)
        {
            torneoDB.Add(torneo);
            SaveChanges();
        }

        public Torneo GetTorneoById(int id)
        {
            var torneoBuscado = torneoDB.FirstOrDefault(e => e.Id == id);
            if (torneoBuscado != null)
                return torneoBuscado;
            else throw new Exception("Este torneo no está registrado");
        }

        public List<Torneo> GetAllTorneos()
        {
            return torneoDB.ToList();
        }

        public void UpdateTorneo(Torneo updatedTorneo)
        {
            var existingTorneo = torneoDB.FirstOrDefault(p => p.Id == updatedTorneo.Id);
            if (existingTorneo != null)
            {
                existingTorneo.CodigoTorneo = updatedTorneo.CodigoTorneo;
                existingTorneo.FechaTorneo = updatedTorneo.FechaTorneo;
                existingTorneo.PremioMonetario = updatedTorneo.PremioMonetario;
                existingTorneo.Categoria = updatedTorneo.Categoria;
                existingTorneo.Ganador = updatedTorneo.Ganador;
            }
            SaveChanges();
        }

        public void DeleteTorneo(int id)
        {
            var torneo = torneoDB.FirstOrDefault(e => e.Id == id);
            if (torneo != null)
            {
                var relatedEntries = TorneoEquipos.Where(te => te.TorneoId == id).ToList();
                TorneoEquipos.RemoveRange(relatedEntries);
                torneoDB.Remove(torneo);
                SaveChanges();
            }
        }

        public List<Equipo> GetEquiposByTorneoId(int torneoId)
        {
            var torneoEquipos = this.TorneoEquipos.Where(te => te.TorneoId == torneoId).OrderByDescending(te => te.Fecha).Take(2).ToList();
            var equipoIds = torneoEquipos.Select(te => te.EquipoId).ToList();
            var equipos = this.equipoDB.Where(e => equipoIds.Contains(e.Id)).ToList();
            return equipos;
        }

        #endregion

        #region usuario
        public void AddUsuario(Usuario usuario)
        {
            usuarioDB.Add(usuario);
            SaveChanges();
        }

        public Usuario GetUsuarioById(int id)
        {
            return usuarioDB.FirstOrDefault(e => e.Id == id);
        }

        public List<Usuario> GetAllUsuarios()
        {
            return usuarioDB.ToList();
        }

        public void UpdateUsuario(Usuario updatedUsuario)
        {
            var existingUsuario = usuarioDB.FirstOrDefault(p => p.Id == updatedUsuario.Id);
            if (existingUsuario != null)
            {
                existingUsuario.Nombre = updatedUsuario.Nombre;
                existingUsuario.Cedula = updatedUsuario.Cedula;
                existingUsuario.Genero = updatedUsuario.Genero;
                existingUsuario.FechaRegistro = updatedUsuario.FechaRegistro;
                existingUsuario.Estado = updatedUsuario.Estado;
                existingUsuario.UsuarioLogin = updatedUsuario.UsuarioLogin;
                existingUsuario.Contrasena = updatedUsuario.Contrasena;
            }
            SaveChanges();
        }

        public void DeleteUsuario(int id)
        {
            var usuario = usuarioDB.FirstOrDefault(e => e.Id == id);
            if (usuario != null)
            {
                usuarioDB.Remove(usuario);
                SaveChanges();
            }
        }
        public Usuario validarLogin(string user, string pass)
        {
            var usuarioLoguedo = usuarioDB.FirstOrDefault(l => l.UsuarioLogin == user && l.Contrasena == pass);

            if (usuarioLoguedo != null && usuarioLoguedo.Estado == "Activo")
            {
                return usuarioLoguedo;
            }
            else throw new Exception("Datos de inicio de sesion incorrectos o usuario inactivo.");
        }
        #endregion

    }
}
