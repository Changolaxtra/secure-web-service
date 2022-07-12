package dan.rojas.epam.secure.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class MainController {

  @RequestMapping("/")
  public String index() {
    return "index.html";
  }

  @RequestMapping("/login.html")
  public String login() {
    return "login.html";
  }

  @RequestMapping("/login-error.html")
  public String loginError(Model model) {
    log.error("Error Login...");
    model.addAttribute("loginError", true);
    return "login.html";
  }
}
