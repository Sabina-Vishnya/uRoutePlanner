-- users
INSERT INTO users (chat_id, username)
VALUES (1223, 'user1'),
       (4567, 'user2'),
       (1111, 'user3'),
       (6645, 'user4'),
       (9843, 'user5');

-- locations
INSERT INTO locations (country, city, street, building, entrance, latitude, longitude, timezone, normalized_address, original_address)
VALUES ('Россия', 'Москва', 'Волоколамское шоссе', '6', '1', 55.808306, 37.5008, 'UTC+3', 'Волоколамское шоссе, 6', 'москва, Волоколамское шоссе '),
       ('Россия', 'Москва', 'Кутузовский проспект', '32', NULL, 55.7404654, 37.5320262, 'UTC+3', 'Кутузовский проспект, 32', 'Москва, Кутузовский проспект 32'),
       ('Россия', 'Москва', 'Светлый проезд', '5', NULL, 55.8125352, 37.4902025, 'UTC+3', 'Светлый проезд 5', 'москва, Светлый проезд 5'),
       ('Россия', 'Москва', 'Волоколамское шоссе', '8', NULL, 55.8089475, 37.4993656, 'UTC+3', 'Волоколамское шоссе, 8', 'москва, Волоколамское шоссе, 8');

-- routes
INSERT INTO routes (from_location_id, to_location_id, transport_type)
VALUES ((SELECT id FROM locations WHERE street = 'Волоколамское шоссе' AND building = '6'),
        (SELECT id FROM locations WHERE street = 'Кутузовский проспект' AND building = '32'),
        0),
       ((SELECT id FROM locations WHERE street = 'Светлый проезд' AND building = '5'),
        (SELECT id FROM locations WHERE street = 'Кутузовский проспект' AND building = '32'),
        0);

-- settings
INSERT INTO settings (route_id, arrival_time, buffer_time, schedule_type)
VALUES ((SELECT id
         FROM routes
         WHERE from_location_id = (SELECT id FROM locations WHERE street = 'Волоколамское шоссе' AND building = '6')
           AND to_location_id = (SELECT id FROM locations WHERE street = 'Кутузовский проспект' AND building = '32')),
        '9:00', 30, 0),
       ((SELECT id
         FROM routes
         WHERE from_location_id = (SELECT id FROM locations WHERE street = 'Волоколамское шоссе' AND building = '6')
           AND to_location_id = (SELECT id FROM locations WHERE street = 'Кутузовский проспект' AND building = '32')),
        '10:00', 30, 0);

-- user_settings
INSERT INTO user_settings (user_id, setting_id)
VALUES ((SELECT id FROM users WHERE username = 'user1'),
        (SELECT id FROM settings WHERE arrival_time = '09:00')),
       ((SELECT id FROM users WHERE username = 'user2'),
        (SELECT id FROM settings WHERE arrival_time = '09:00')),
       ((SELECT id FROM users WHERE username = 'user3'),
        (SELECT id FROM settings WHERE arrival_time = '10:00')),
       ((SELECT id FROM users WHERE username = 'user4'),
        (SELECT id FROM settings WHERE arrival_time = '09:00')),
       ((SELECT id FROM users WHERE username = 'user5'),
        (SELECT id FROM settings WHERE arrival_time = '10:00'));