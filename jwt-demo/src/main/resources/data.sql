INSERT INTO towns (name)
VALUES ('Саратов');

INSERT INTO languages (name)
VALUES ('Русский'),
       ('Английский');

INSERT INTO subscription_types (price, description, name)
VALUES (250, 'Подписка на музыку', 'Music'),
       (0, 'Подписка на знакомства', 'Dating');

INSERT INTO profiles (login, home_town_id, marital_status, short_description, frozen)
VALUES ('admin', 1, 'DATING', 'my status', false),
       ('test', 1, 'MARRIED', 'test', false);

INSERT INTO profile_family (first_profile_id, second_profile_id)
VALUES (1, 2),
       (2, 1);

INSERT INTO account_ids (birth_date, login, name, password, role, sex, surname, profile_id, frozen)
VALUES ('2004-07-09', 'admin', 'Михаил', '$2a$10$uezbzRQyCde.P6A8TU2ogef4zHEGL0kDpxpqBLFVJrXPVPQdep1Z2', 'ADMIN', 'MALE', 'Рубин', 1, false),
       ('2000-01-01', 'test', 'TEST', 'test', 'USER', 'MALE', 'TEST', 2, false);

INSERT INTO profile_known_languages (language_id, profile_id)
VALUES (1, 1);

INSERT INTO news (profile_id, text)
VALUES (1, 'Тестовая новость');