CREATE TABLE customer
(
  customer_id INT(4) UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  first_name  VARCHAR(45) NULL,
  last_name   VARCHAR(45) NULL,
  mail        VARCHAR(45) NULL,
  phone       VARCHAR(45) NULL,
  address     VARCHAR(45) NULL
)
  ENGINE = InnoDB;

CREATE TABLE education
(
  education_id INT(4) UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  grade        VARCHAR(45) NULL
)
  ENGINE = InnoDB;

CREATE INDEX grade
  ON education (grade);

CREATE TABLE employee
(
  employee_id  INT(4) UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  firstname    VARCHAR(45)     NULL,
  lastname     VARCHAR(45)     NULL,
  mail         VARCHAR(45)     NULL,
  phone        VARCHAR(45)     NULL,
  address      VARCHAR(45)     NOT NULL,
  job_id       INT(4) UNSIGNED NOT NULL,
  education_id INT(4) UNSIGNED NOT NULL,
  CONSTRAINT fk_employee_education
  FOREIGN KEY (education_id) REFERENCES education (education_id)
)
  ENGINE = InnoDB;

CREATE INDEX fk_employee_job
  ON employee (job_id);

CREATE INDEX fk_employee_education
  ON employee (education_id);

CREATE TABLE job
(
  job_id   INT(4) UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  function VARCHAR(30) NULL
)
  ENGINE = InnoDB;

CREATE INDEX function
  ON job (function);

ALTER TABLE employee
  ADD CONSTRAINT fk_employee_job
FOREIGN KEY (job_id) REFERENCES job (job_id);

CREATE TABLE sales_order
(
  id          INT(4) UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  customer_id INT(4) UNSIGNED NOT NULL,
  employee_id INT(4) UNSIGNED NOT NULL,
  service_id  INT(4) UNSIGNED NOT NULL,
  order_date  DATE            NULL,
  CONSTRAINT fk_sales_order_customer
  FOREIGN KEY (customer_id) REFERENCES customer (customer_id),
  CONSTRAINT fk_sales_order_employee
  FOREIGN KEY (employee_id) REFERENCES employee (employee_id)
)
  ENGINE = InnoDB;

CREATE INDEX fk_sales_order_customer
  ON sales_order (customer_id);

CREATE INDEX fk_sales_order_employee
  ON sales_order (employee_id);

CREATE INDEX fk_sales_order_service
  ON sales_order (service_id);

CREATE TABLE service
(
  service_id    INT(4) UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  service_name  VARCHAR(45)     NULL,
  service_price INT(4) UNSIGNED NOT NULL
)
  ENGINE = InnoDB;

CREATE INDEX service_name
  ON service (service_name);

ALTER TABLE sales_order
  ADD CONSTRAINT fk_sales_order_service
FOREIGN KEY (service_id) REFERENCES service (service_id);

