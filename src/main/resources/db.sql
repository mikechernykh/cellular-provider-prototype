CREATE TABLE tariffs (
  id            BIGINT      NOT NULL AUTO_INCREMENT,
  name          VARCHAR(50) NOT NULL UNIQUE,
  activity_sign BOOLEAN     NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

CREATE TABLE users (
  id        BIGINT       NOT NULL AUTO_INCREMENT,
  full_name VARCHAR(255) NOT NULL,
  tariff_id BIGINT       NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_USERS_TARIFF_ID FOREIGN KEY (tariff_id) REFERENCES tariffs (id)
)
  ENGINE = InnoDB;

CREATE TABLE options (
  id             BIGINT      NOT NULL AUTO_INCREMENT,
  name           VARCHAR(50) NOT NULL,
  cur_price      DOUBLE      NOT NULL,
  cur_currency   VARCHAR(10) NOT NULL,
  old_price      DOUBLE      NOT NULL,
  old_currency   VARCHAR(10) NOT NULL,
  date_of_change DATE        NOT NULL,
  tariff_id      BIGINT      NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_OPTIONS_TARIFF_ID FOREIGN KEY (tariff_id) REFERENCES tariffs (id)
)
  ENGINE = InnoDB;

INSERT INTO tariffs (name, activity_sign) VALUE ('Tariff 1', TRUE);
INSERT INTO tariffs (name, activity_sign) VALUE ('Tariff 2', TRUE);
INSERT INTO tariffs (name, activity_sign) VALUE ('Tariff 3', TRUE);
INSERT INTO tariffs (name, activity_sign) VALUE ('Tariff 4', TRUE);
INSERT INTO tariffs (name, activity_sign) VALUE ('Tariff 5', TRUE);
INSERT INTO tariffs (name, activity_sign) VALUE ('Tariff 6', TRUE);
INSERT INTO tariffs (name, activity_sign) VALUE ('Tariff 7', TRUE);
INSERT INTO tariffs (name, activity_sign) VALUE ('Tariff 8', TRUE);

INSERT INTO users (full_name, tariff_id) VALUE ('John Doe', 1);
INSERT INTO users (full_name, tariff_id) VALUE ('Peter Falk', 2);
INSERT INTO users (full_name, tariff_id) VALUE ('Jessica Alba', 4);
INSERT INTO users (full_name, tariff_id) VALUE ('Frank Sinatra', 3);
INSERT INTO users (full_name, tariff_id) VALUE ('LeBron James', 1);
INSERT INTO users (full_name, tariff_id) VALUE ('James Bond', 8);

INSERT INTO options (name, cur_price, cur_currency, old_price, old_currency, date_of_change, tariff_id)
  VALUE ('Voice call', 1.20, 'RUB', 6.43, 'RUB', '2017-08-15', 1);
INSERT INTO options (name, cur_price, cur_currency, old_price, old_currency, date_of_change, tariff_id)
  VALUE ('SMS', 2.00, 'RUB', 6.43, 'RUB', '2017-08-15', 1);

INSERT INTO options (name, cur_price, cur_currency, old_price, old_currency, date_of_change, tariff_id)
  VALUE ('Voice call', 1.20, 'RUB', 6.43, 'RUB', '2017-08-15', 2);
INSERT INTO options (name, cur_price, cur_currency, old_price, old_currency, date_of_change, tariff_id)
  VALUE ('SMS', 2.00, 'RUB', 6.43, 'RUB', '2017-10-24', 2);

INSERT INTO options (name, cur_price, cur_currency, old_price, old_currency, date_of_change, tariff_id)
  VALUE ('Voice call', 1.20, 'RUB', 6.43, 'RUB', '2017-11-12', 3);
INSERT INTO options (name, cur_price, cur_currency, old_price, old_currency, date_of_change, tariff_id)
  VALUE ('SMS', 2.00, 'RUB', 6.43, 'RUB', '2017-10-22', 3);

INSERT INTO options (name, cur_price, cur_currency, old_price, old_currency, date_of_change, tariff_id)
  VALUE ('Voice call', 1.20, 'RUB', 6.43, 'RUB', '2017-10-10', 4);
INSERT INTO options (name, cur_price, cur_currency, old_price, old_currency, date_of_change, tariff_id)
  VALUE ('SMS', 2.00, 'RUB', 6.43, 'RUB', '2017-11-01', 4);

INSERT INTO options (name, cur_price, cur_currency, old_price, old_currency, date_of_change, tariff_id)
  VALUE ('Voice call', 1.20, 'RUB', 6.43, 'RUB', '2017-11-12', 5);
INSERT INTO options (name, cur_price, cur_currency, old_price, old_currency, date_of_change, tariff_id)
  VALUE ('SMS', 2.00, 'RUB', 6.43, 'RUB', '2017-08-15', 5);

INSERT INTO options (name, cur_price, cur_currency, old_price, old_currency, date_of_change, tariff_id)
  VALUE ('Voice call', 1.20, 'RUB', 6.43, 'RUB', '2017-11-11', 6);
INSERT INTO options (name, cur_price, cur_currency, old_price, old_currency, date_of_change, tariff_id)
  VALUE ('SMS', 2.00, 'RUB', 6.43, 'RUB', '2017-10-24', 6);

INSERT INTO options (name, cur_price, cur_currency, old_price, old_currency, date_of_change, tariff_id)
  VALUE ('Voice call', 1.20, 'RUB', 6.43, 'RUB', '2017-11-12', 7);
INSERT INTO options (name, cur_price, cur_currency, old_price, old_currency, date_of_change, tariff_id)
  VALUE ('SMS', 2.00, 'RUB', 6.43, 'RUB', '2017-08-23', 7);

INSERT INTO options (name, cur_price, cur_currency, old_price, old_currency, date_of_change, tariff_id)
  VALUE ('Voice call', 1.20, 'RUB', 6.43, 'RUB', '2017-10-24', 8);
INSERT INTO options (name, cur_price, cur_currency, old_price, old_currency, date_of_change, tariff_id)
  VALUE ('SMS', 2.00, 'RUB', 6.43, 'RUB', '2017-08-16', 8);