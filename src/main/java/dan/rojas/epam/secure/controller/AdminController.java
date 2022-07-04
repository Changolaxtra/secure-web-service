package dan.rojas.epam.secure.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

  @GetMapping("/admin")
  public String getAdminLabel() {
    return "You are admin!";
  }

}
