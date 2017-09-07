SET GLOBAL event_scheduler = ON;

CREATE EVENT IFNOT EXISTS `event_tb_serial_number_reset` ON SCHEDULE EVERY 1 DAY STARTS '2017-06-22 00:00:00' 
ON COMPLETION PRESERVE ENABLE 
DO UPDATE tb_serial_number t SET t.max_serial_number=1 where type=1