```mysql

CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT primary key,
    login_id VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE web_link
(
    id BIGINT AUTO_INCREMENT primary key,
    created_by VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL
)

```