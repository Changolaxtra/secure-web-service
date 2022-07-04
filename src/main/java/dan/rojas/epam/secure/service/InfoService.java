package dan.rojas.epam.secure.service;

import dan.rojas.epam.secure.model.Stats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class InfoService {

  private final List<String> cpuList = Arrays.asList("Intel Core i7", "Intel Core i5", "AMD Ryzen 7", "AMD Ryzen 7");
  private final List<String> ramList = Arrays.asList("8 GB", "16 GB", "32 GB", "64 GB");
  private final List<String> brandList = Arrays.asList("HP", "Dell", "Lenovo");

  public Stats getRandomStats() {
    log.info("Generating random stats...");
    return Stats.builder()
        .cpu(getRandomFromList(cpuList))
        .ram(getRandomFromList(ramList))
        .brand(getRandomFromList(brandList))
        .build();
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
