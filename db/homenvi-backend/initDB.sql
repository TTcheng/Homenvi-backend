CREATE DATABASE iems_monitor;
CREATE USER 'iems'@'%' IDENTIFIED BY 'iems';
GRANT ALL PRIVILEGES ON iems_monitor.* TO 'iems'@'%';
flush privileges;
