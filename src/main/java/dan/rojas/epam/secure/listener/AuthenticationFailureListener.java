package dan.rojas.epam.secure.listener;

import dan.rojas.epam.secure.service.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

  private final LoginAttemptService loginAttemptService;

  @SneakyThrows
  @Override
  public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
    final String username = (String) event.getAuthentication().getPrincipal();
    log.info("Failure Event for User {}", username);
    loginAttemptService.registerFailedAttempt(username);

    if (loginAttemptService.isLocked(username)) {
      throw new Exception("Login failure, account is locked");
    }
  }

}
