package dan.rojas.epam.secure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "service.info")
@Configuration
public class InfoConfig {
  private String about;
  private List<String> cpuList;
  private List<String> ramList;
  private List<String> brandList;
}
