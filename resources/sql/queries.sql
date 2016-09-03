--:name create-user! :! :n
--:doc creates a new user
INSERT INTO users
(name, email)
VALUES (:name, :email);

--:name select-all-users :! :*
--:doc select all users
SELECT * FROM users;

--:name select-user-by-email :! :1
--:doc retrieve a user given the email.
SELECT * FROM users WHERE email = :email;
