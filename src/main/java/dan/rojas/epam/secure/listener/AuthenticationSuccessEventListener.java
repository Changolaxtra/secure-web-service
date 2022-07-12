package dan.rojas.epam.secure.listener;

import dan.rojas.epam.secure.service.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationSuccessEventListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

  private final LoginAttemptService loginAttemptService;

  @SneakyThrows
  @Override
  public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
    final String username = ((User) event.getAuthentication().getPrincipal()).getUsername();
    log.info("Login successful for User {}", username);
    if (loginAttemptService.isLocked(username)) {
      throw new Exception("Login error, account is locked");
    }

    log.info("Resetting login attempts for User {}", username);
    loginAttemptService.resetAttempts(username);
  }
}
