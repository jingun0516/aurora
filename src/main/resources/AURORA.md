```mysql

CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT primary key,
    login_id VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE web_link
(
    id         BIGINT AUTO_INCREMENT primary key,
    created_by VARCHAR(255) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    url        VARCHAR(255) NOT NULL,
    category   VARCHAR(255) NOT NULL
);

CREATE TABLE web_link_permission
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id         BIGINT                 NOT NULL,
    weblink_id      BIGINT                 NOT NULL,
    permission_type VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (weblink_id) REFERENCES web_link (id) ON DELETE CASCADE,
    UNIQUE (user_id, weblink_id, permission_type)
);


```