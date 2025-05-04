-- users
CREATE TABLE IF NOT EXISTS users
(
    id          SERIAL PRIMARY KEY,
    create_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    chat_id     BIGINT       NOT NULL UNIQUE,
    username    VARCHAR(128) NOT NULL
);

-- locations
CREATE TABLE IF NOT EXISTS locations
(
    id                 SERIAL PRIMARY KEY,
    create_date        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    update_date        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    country            VARCHAR(255) NOT NULL,
    city               VARCHAR(255) NOT NULL,
    street             VARCHAR(255) NOT NULL,
    building           VARCHAR(50)  NOT NULL,
    entrance           VARCHAR(50),
    latitude           DOUBLE PRECISION,
    longitude          DOUBLE PRECISION,
    timezone           VARCHAR(50)  NOT NULL,
    original_address   TEXT         NOT NULL,
    normalized_address TEXT         NOT NULL,

    UNIQUE (city, street, building)
);

-- routes
CREATE TABLE IF NOT EXISTS routes
(
    id               SERIAL PRIMARY KEY,
    create_date      TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    update_date      TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    from_location_id INT      NOT NULL REFERENCES locations (id),
    to_location_id   INT      NOT NULL REFERENCES locations (id),
    transport_type   SMALLINT NOT NULL,

    UNIQUE (from_location_id, to_location_id, transport_type)
);

-- settings
CREATE TABLE IF NOT EXISTS settings
(
    id            SERIAL PRIMARY KEY,
    create_date   TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    update_date   TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    route_id      INT      NOT NULL REFERENCES routes (id),
    arrival_time  TIME     NOT NULL,
    buffer_time   INT      NOT NULL,
    schedule_type SMALLINT NOT NULL,

    UNIQUE (route_id, arrival_time, buffer_time, schedule_type)
);

-- user_settings
CREATE TABLE user_settings
(
    user_id     INT NOT NULL PRIMARY KEY REFERENCES users (id),
    setting_id  INT NOT NULL REFERENCES settings (id),
    create_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
