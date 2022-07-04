package dan.rojas.epam.secure.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Stats {
  String cpu;
  String ram;
  String brand;
}
