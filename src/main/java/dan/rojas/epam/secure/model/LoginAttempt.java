package dan.rojas.epam.secure.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "login_attempt")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LoginAttempt {
  @Id
  @EqualsAndHashCode.Include
  private String username;
  private Integer attempts;
  private Boolean locked;
  private Date stamp;
}
