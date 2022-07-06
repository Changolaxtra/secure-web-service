package dan.rojas.epam.secure.service;

import dan.rojas.epam.secure.dao.LoginAttemptRepository;
import dan.rojas.epam.secure.model.LoginAttempt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.MINUTES;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginAttemptService {

  private final LoginAttemptRepository loginAttemptRepository;

  @Transactional
  public void registerFailedAttempt(final String username) {
    log.info("Registering log attempt for user {}", username);
    if (loginAttemptRepository.existsById(username)) {
      final LoginAttempt loginAttempt = loginAttemptRepository.getReferenceById(username);
      final Integer currentAttempts = Optional.ofNullable(loginAttempt.getAttempts()).orElse(0) + 1;
      loginAttempt.setAttempts(currentAttempts);
      loginAttempt.setLocked(currentAttempts >= 3);
      loginAttempt.setStamp(new Date());
      loginAttemptRepository.save(loginAttempt);
    } else {
      final LoginAttempt loginAttempt = new LoginAttempt();
      loginAttempt.setAttempts(1);
      loginAttempt.setUsername(username);
      loginAttempt.setLocked(false);
      loginAttempt.setStamp(new Date());
      loginAttemptRepository.save(loginAttempt);
    }
  }

  @Transactional
  public void resetAttempts(final String username) {
    log.info("Resetting attempts for user {}", username);
    if (loginAttemptRepository.existsById(username)) {
      loginAttemptRepository.deleteById(username);
    }
  }

  @Transactional
  public boolean isLocked(final String username) {
    boolean isLocked = false;
    if (loginAttemptRepository.existsById(username)) {
      final LoginAttempt loginAttempt = loginAttemptRepository.getReferenceById(username);
      log.info("Locked data-> flag: {} inWindow: {}", loginAttempt.getLocked(), isInLockedWindow(loginAttempt.getStamp()));
      isLocked = loginAttempt.getLocked() && isInLockedWindow(loginAttempt.getStamp());
    }
    log.info("Checking if {} is locked -> {}...", username, isLocked);
    return isLocked;
  }

  private boolean isInLockedWindow(final Date lockedDate) {
    final long minutes = MINUTES.between(dateToInstant(lockedDate), dateToInstant(new Date()));
    log.info("Hours {}", minutes);
    return minutes < 5;
  }

  private LocalTime dateToInstant(final Date date) {
    return LocalTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
  }


}
