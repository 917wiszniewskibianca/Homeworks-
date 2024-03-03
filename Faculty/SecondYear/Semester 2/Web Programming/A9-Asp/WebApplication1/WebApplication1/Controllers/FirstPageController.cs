using System;
using System.Web.Mvc;

namespace WebApplication1.Controllers
{
    public class FirstPageController : Controller
    {
        // GET
        public ActionResult FirstPage()
        {
            return View();
        }
        
        public ActionResult showOnPage()
        {
            ViewBag.ShowPopup = true;
            return View("FirstPage");
        }
    }
}