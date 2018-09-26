DROP TABLE IF EXISTS abtesting;

CREATE TABLE abtesting (
  service_name      VARCHAR(100) PRIMARY KEY NOT NULL,
  active            VARCHAR(1) NOT NULL,
  endpoint          VARCHAR(100) NOT NULL,
  weight            INT);


INSERT INTO abtesting (service_name, active,  endpoint, weight) VALUES ('category-service', 'Y','http://category-service-new:9102',5);
