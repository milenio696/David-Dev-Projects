using BattleFightGrupo1.Models;
using BattleFightGrupo1.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace BattleFightGrupo1.Controllers
{
    public class EquipoController : Controller
    {
        private Service service;

        public EquipoController()
        {
            service = new Service();
        }

        #region Index
        public IActionResult Index()
        {
            var userName = HttpContext.Session.GetString("NombreUsuario");
            ViewBag.NombreUsuario = userName;
            var equipos = service.GetAllEquipos();
            ViewBag.Categorias = new SelectList(new[] { "Seleccione una Categoría", "Beginner", "Middle", "Expert" });
            return View(equipos);
        }

        [HttpPost]
        public ActionResult Index(string Categoria)
        {
            var userName = HttpContext.Session.GetString("NombreUsuario");
            ViewBag.NombreUsuario = userName;
            var model = new List<Equipo>();
            if (!string.IsNullOrEmpty(Categoria))
            {
                model = service.FiltroPorCategoria(Categoria);
            }
            else
            {
                model = service.GetAllEquipos();
            }
            ViewBag.Categorias = new SelectList(new[] { "Seleccione una Categoría", "Beginner", "Middle", "Expert" });
            return View(model);
        }

        #endregion

        #region Create
        // GET: EquipoController/Create
        [HttpGet]
        public ActionResult Create()
        {
            var userName = HttpContext.Session.GetString("NombreUsuario");
            ViewBag.NombreUsuario = userName;
            ViewBag.Jugadores = new SelectList(service.GetAllUsuarios(), "UsuarioLogin", "UsuarioLogin");
            ViewBag.Categorias = new SelectList(new[] { "Beginner", "Middle", "Expert" });
            return View();
        }

        // POST: EquipoController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Equipo equipo)
        {
            if (string.IsNullOrEmpty(equipo.Jugador1) && string.IsNullOrEmpty(equipo.Jugador2) && string.IsNullOrEmpty(equipo.Jugador3) && string.IsNullOrEmpty(equipo.Jugador4))
            {
                ModelState.AddModelError("Jugador1", "Debe seleccionar al menos un jugador.");
            }
            if (!string.IsNullOrEmpty(equipo.Jugador1) || !string.IsNullOrEmpty(equipo.Jugador2) || !string.IsNullOrEmpty(equipo.Jugador3) || !string.IsNullOrEmpty(equipo.Jugador4))
            {
                ModelState.Remove("Jugador1");
                ModelState.Remove("Jugador2");
                ModelState.Remove("Jugador3");
                ModelState.Remove("Jugador4");
            }
            ModelState.Remove("TorneoEquipos");
            if (ModelState.IsValid)
            {
                try
                {
                    equipo.fillCodigoEquipo();
                    service.AddEquipo(equipo);
                    return RedirectToAction(nameof(Index));
                }
                catch (Exception ex) { Console.WriteLine("Error: " + ex); }
            }
            ViewBag.Jugadores = new SelectList(service.GetAllUsuarios(), "UsuarioLogin", "UsuarioLogin");
            ViewBag.Categorias = new SelectList(new[] { "Beginner", "Middle", "Expert" });
            return View(equipo);
        }
        #endregion

        #region Edit
        // GET: EquipoController/Edit/5
        public ActionResult Edit(int id)
        {
            var userName = HttpContext.Session.GetString("NombreUsuario");
            ViewBag.NombreUsuario = userName;
            var equipo = service.GetEquipoById(id);
            ViewBag.Jugadores = new SelectList(service.GetAllUsuarios(), "UsuarioLogin", "UsuarioLogin");
            ViewBag.Categorias = new SelectList(new[] { "Beginner", "Middle", "Expert" });
            return View(equipo);
        }


        // POST: EquipoController/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(Equipo equipo)
        {

            if (string.IsNullOrEmpty(equipo.Jugador1) && string.IsNullOrEmpty(equipo.Jugador2) && string.IsNullOrEmpty(equipo.Jugador3) && string.IsNullOrEmpty(equipo.Jugador4))
            {
                ModelState.AddModelError("Jugador1", "Debe seleccionar al menos un jugador.");
            }
            if (string.IsNullOrEmpty(equipo.CodigoEquipo))
            {
                ModelState.Remove("CodigoEquipo");
            }
            if (!string.IsNullOrEmpty(equipo.Jugador1) || !string.IsNullOrEmpty(equipo.Jugador2) || !string.IsNullOrEmpty(equipo.Jugador3) || !string.IsNullOrEmpty(equipo.Jugador4))
            {
                ModelState.Remove("Jugador1");
                ModelState.Remove("Jugador2");
                ModelState.Remove("Jugador3");
                ModelState.Remove("Jugador4");
            }
            ModelState.Remove("TorneoEquipos");
            if (ModelState.IsValid)
            {
                try
                {
                    equipo.fillCodigoEquipo();
                    service.UpdateEquipo(equipo);
                    return RedirectToAction(nameof(Index));
                }
                catch (Exception ex)
                {
                    Console.WriteLine("Error: " + ex);
                }
            }

            ViewBag.Jugadores = new SelectList(service.GetAllUsuarios(), "UsuarioLogin", "UsuarioLogin");
            ViewBag.Categorias = new SelectList(new[] { "Beginner", "Middle", "Expert" });
            return View(equipo);
        }
        #endregion

        #region Delete
        // GET: EquipoController/Delete/5
        public ActionResult Delete(int id)
        {
            var userName = HttpContext.Session.GetString("NombreUsuario");
            ViewBag.NombreUsuario = userName;

            try
            {
                var model = service.GetAllEquipos().FirstOrDefault(user => user.Id == id);
                if (model == null)
                {
                    return NotFound();
                }
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
        public ActionResult Delete(Equipo equipo)
        {
            service.DeleteEquipo(equipo.Id);
            return RedirectToAction(nameof(Index));
        }
        #endregion

        #region Filter
        [HttpPost]
        public ActionResult Filter(string categoria)
        {
            var model = string.IsNullOrEmpty(categoria)
                ? service.GetAllEquipos()
                : service.FiltroPorCategoria(categoria);

            return View("Index", model);
        }
        #endregion
    }
}

