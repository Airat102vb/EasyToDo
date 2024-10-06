drop table users if exists;
drop table todo if exists;

CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  full_name VARCHAR(100),
  login VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(100)
  CONSTRAINT login_not_empty CHECK (login <> '')
);

CREATE TABLE IF NOT EXISTS todo (
  id SERIAL PRIMARY KEY,
  user_id LONG NOT NULL,
  entry TEXT,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
