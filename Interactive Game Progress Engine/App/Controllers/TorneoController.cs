using BattleFightGrupo1.Models;
using BattleFightGrupo1.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace BattleFightGrupo1.Controllers
{
    public class TorneoController : Controller
    {

        private Service service;

        public TorneoController()

        {
            service = new Service();
        }

        #region Index
        // GET: TorneoController
        public ActionResult Index()
        {
            var userName = HttpContext.Session.GetString("NombreUsuario");
            ViewBag.NombreUsuario = userName;
            var model = service.GetAllTorneos();
            return View(model);
        }
        #endregion

        #region Create
        // GET: TorneoController/Create
        public ActionResult Create()
        {
            var userName = HttpContext.Session.GetString("NombreUsuario");
            ViewBag.NombreUsuario = userName;
            ViewBag.Jugadores = new SelectList(service.GetAllUsuarios(), "UsuarioLogin", "UsuarioLogin");
            ViewBag.Categorias = new SelectList(new[] { "Beginner", "Middle", "Expert" });
            return View();
        }

        // POST: TorneoController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Torneo torneo)
        {
            try
            {
                ModelState.Remove("Ganador");
                ModelState.Remove("Equipos");
                if (ModelState.IsValid)
                {
                    service.AddTorneo(torneo);
                    return RedirectToAction(nameof(Index));
                }
            }
            catch (Exception ex) { Console.WriteLine("Error: " + ex); }
            ViewBag.Jugadores = new SelectList(service.GetAllUsuarios(), "UsuarioLogin", "UsuarioLogin");
            return View();
        }
        #endregion

        #region Edit
        // GET: TorneoController/Edit/5
        public ActionResult Edit(int id)
        {
            var userName = HttpContext.Session.GetString("NombreUsuario");
            ViewBag.NombreUsuario = userName;
            var torneo = service.GetTorneoById(id);
            if (torneo == null)
            {
                return NotFound();
            }
            var equipos = service.GetAllEquipos().Where(e => e.Categoria == torneo.Categoria).ToList();
            ViewBag.Equipos = new SelectList(equipos, "Id", "NombreEquipo", torneo.TorneoEquipos?.Select(e => e.EquipoId));
            return View(torneo);
        }

        // POST: TorneoController/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(int id, Torneo torneo, int selectedEquipo1, int selectedEquipo2)
        {
            if (selectedEquipo1 == selectedEquipo2)
            {
                ViewData["EquiposError"] = "Los dos equipos seleccionados no pueden ser el mismo.";
                ViewBag.Equipos = new SelectList(service.GetAllEquipos().Where(e => e.Categoria == torneo.Categoria), "Id", "NombreEquipo", new List<int> { selectedEquipo1, selectedEquipo2 });
                return View(torneo);
            }
            else if (service.GetTorneoById(id) == null)
            {
                return View(torneo);
            }
            else
            {
                var equipo1 = service.GetEquipoById(selectedEquipo1);
                var equipo2 = service.GetEquipoById(selectedEquipo2);
                if (equipo1 == null || equipo2 == null)
                {
                    ViewData["EquiposError"] = "Error al obtener la información de los equipos seleccionados.";
                    return View(torneo);
                }
                ModelState.Remove("Ganador");
                if (ModelState.IsValid)
                {
                    if (equipo1.Puntaje > equipo2.Puntaje)
                    {
                        torneo.Ganador = equipo1.NombreEquipo;
                    }
                    else if (equipo2.Puntaje > equipo1.Puntaje)
                    {
                        torneo.Ganador = equipo2.NombreEquipo;
                    }
                    else
                    {
                        torneo.Ganador = "Empate";
                    }
                    service.UpdateTorneo(torneo);
                    service.TorneoEquipos.Add(new TorneoEquipo
                    {
                        TorneoId = torneo.Id,
                        EquipoId = equipo1.Id,
                        Fecha = DateTime.Now
                    });
                    service.TorneoEquipos.Add(new TorneoEquipo
                    {
                        TorneoId = torneo.Id,
                        EquipoId = equipo2.Id,
                        Fecha = DateTime.Now
                    });

                    service.SaveChanges();
                    return RedirectToAction(nameof(Index));
                }
            }
            ViewBag.Equipos = new SelectList(service.GetAllEquipos().Where(e => e.Categoria == torneo.Categoria), "Id", "NombreEquipo", new List<int> { selectedEquipo1, selectedEquipo2 });
            return View(torneo);
        }

        #endregion

        #region Delete
        // GET: TorneoController/Delete/5
        public ActionResult Delete(int id)
        {
            try
            {
                var userName = HttpContext.Session.GetString("NombreUsuario");
                ViewBag.NombreUsuario = userName;
                var model = service.GetAllTorneos().FirstOrDefault(t => t.Id == id);
                if (model == null)
                {
                    return NotFound();
                }
                var equipos = service.GetEquiposByTorneoId(id);
                ViewBag.Equipos = equipos;
                return View(model);
            }
            catch (System.Exception ex)
            {
                Console.WriteLine("Error: " + ex);
                return View();
            }
        }

        // POST: EquipoController/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Delete(Torneo torneo)
        {
            service.DeleteTorneo(torneo.Id);
            return RedirectToAction(nameof(Index));
        }
        #endregion
    }
}
