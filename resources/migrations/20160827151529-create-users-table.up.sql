CREATE TABLE IF NOT EXISTS "users" (
  "id" SERIAL PRIMARY KEY,
  "name"  VARCHAR(100),
  "email" VARCHAR(100),
  "token" VARCHAR(255),
  "expires_at" TIMESTAMP,
  "created_at" TIMESTAMP DEFAULT now()
);
