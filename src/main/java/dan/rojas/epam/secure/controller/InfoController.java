package dan.rojas.epam.secure.controller;

import dan.rojas.epam.secure.dto.Stats;
import dan.rojas.epam.secure.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class InfoController {

  private final InfoService infoService;

  @GetMapping("/info")
  public Stats getStats() {
    return infoService.getRandomStats();
  }

  @GetMapping("/about")
  public String getAboutAbstract() {
    return infoService.getAboutAbstract();
  }
}
