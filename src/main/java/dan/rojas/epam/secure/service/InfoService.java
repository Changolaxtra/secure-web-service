package dan.rojas.epam.secure.service;

import dan.rojas.epam.secure.config.InfoConfig;
import dan.rojas.epam.secure.model.Stats;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Slf4j
public class InfoService {

  private final InfoConfig infoConfig;

  public Stats getRandomStats() {
    log.info("Generating random stats...");
    return Stats.builder()
        .cpu(getRandomFromList(infoConfig.getCpuList()))
        .ram(getRandomFromList(infoConfig.getRamList()))
        .brand(getRandomFromList(infoConfig.getBrandList()))
        .build();
  }

  public String getAboutAbstract() {
    log.info("Getting about text...");
    return infoConfig.getAbout();
  }

  private <T> T getRandomFromList(final List<T> list) {
    T value = null;
    if (!CollectionUtils.isEmpty(list)) {
      final Random random = new Random();
      value = list.get(random.nextInt(list.size()));
    }
    return value;
  }
}
