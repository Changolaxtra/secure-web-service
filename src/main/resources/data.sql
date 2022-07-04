-- User user/pass
INSERT INTO users (username, password, enabled)
  values ('user',
    '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
    1);

INSERT INTO authorities (username, authority)
  values ('user', 'VIEW_INFO');


-- User admin/pass
INSERT INTO users (username, password, enabled)
  values ('admin',
    '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
    1);

INSERT INTO authorities (username, authority)
  values ('admin', 'VIEW_INFO');
INSERT INTO authorities (username, authority)
  values ('admin', 'VIEW_ADMIN');