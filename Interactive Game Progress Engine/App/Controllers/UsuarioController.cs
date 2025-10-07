using BattleFightGrupo1.Models;
using BattleFightGrupo1.Services;
using Microsoft.AspNetCore.Mvc;

namespace BattleFightGrupo1.Controllers
{
    public class UsuarioController : Controller
    {
        private Service service;

        public UsuarioController()
        {
            service = new Service();
        }

        #region Index
        // GET: UsuarioController
        public ActionResult Index()
        {
            var userName = HttpContext.Session.GetString("NombreUsuario");
            ViewBag.NombreUsuario = userName;
            var usuarios = service.GetAllUsuarios();
            return View(usuarios);
        }
        #endregion

        #region login
        public ActionResult Login()
        {
            var userName = HttpContext.Session.GetString("NombreUsuario");
            ViewBag.NombreUsuario = userName;
            return View();
        }


        // POST: UsuarioController/LOGIN
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Login(string user, string pass)
        {
            try
            {
                var UsuarioLogueado = service.validarLogin(user, pass);
                HttpContext.Session.SetString("NombreUsuario", UsuarioLogueado.Nombre);
                return RedirectToAction("Index", "AcercaDe");
            }
            catch (Exception ex)
            {
                ViewBag.Error = ex.Message;
                return View();
            }
        }

        public IActionResult Logout()
        {
            HttpContext.Session.Clear();
            return RedirectToAction("Login", "Usuario");
        }
        #endregion

        #region Create
        // GET: UsuarioController/Create
        public ActionResult Create()
        {
            var userName = HttpContext.Session.GetString("NombreUsuario");
            ViewBag.NombreUsuario = userName;
            return View();
        }

        // POST: UsuarioController/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Usuario user)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    service.AddUsuario(user);
                    return RedirectToAction(nameof(Index));
                }
            }
            catch (System.Exception ex)
            {
                Console.WriteLine("Error: " + ex);
            }
            return View();
        }
        #endregion

        #region Edit

        // GET: UsuarioController/Edit/5
        public ActionResult Edit(int id)
        {
            var userName = HttpContext.Session.GetString("NombreUsuario");
            ViewBag.NombreUsuario = userName;
            var model = service.GetAllUsuarios().FirstOrDefault(user => user.Id == id);
            if (model == null)
            {
                return NotFound();
            }
            return View(model);
        }

        // POST: ProductoController/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(Usuario user)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    service.UpdateUsuario(user);
                    return RedirectToAction("Index");
                }
                return View(user);
            }
            catch (System.Exception ex)
            {
                Console.WriteLine("Error: " + ex);
                return View();
            }
        }
        #endregion

        #region Delete
        // GET: UsuarioController/Delete/5
        public ActionResult Delete(int id)
        {

            try
            {
                var userName = HttpContext.Session.GetString("NombreUsuario");
                ViewBag.NombreUsuario = userName;
                var model = service.GetAllUsuarios().FirstOrDefault(user => user.Id == id);
                if (model == null)
                {
                    return NotFound();
                }
                if (model.Nombre == userName)
                {
                    ViewBag.ErrorMessage = "No puedes eliminar tu propio usuario.";
                    ViewBag.DisableDeleteButton = true; // Set flag to disable delete button
                }
                return View(model);

            }
            catch (System.Exception ex)
            {
                Console.WriteLine("Error: " + ex);
                return View();
            }
        }

        // POST: UsuarioController/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Delete(Usuario user)
        {
            service.DeleteUsuario(user.Id);
            return RedirectToAction(nameof(Index));
        }
        #endregion
    }
}
