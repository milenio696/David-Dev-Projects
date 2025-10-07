using BattleFightGrupo1.Models;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

namespace BattleFightGrupo1.Controllers
{
    public class AcercaDeController : Controller
    {
        #region Logger
        private readonly ILogger<AcercaDeController> _logger;

        public AcercaDeController(ILogger<AcercaDeController> logger)
        {
            _logger = logger;
        }
        #endregion

        #region Index
        public IActionResult Index()
        {
            var userName = HttpContext.Session.GetString("NombreUsuario");
            ViewBag.NombreUsuario = userName;
            return View();
        }
        #endregion

        #region Error
        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
        #endregion
    }
}
