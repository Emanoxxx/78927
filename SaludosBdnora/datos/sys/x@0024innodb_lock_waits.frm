TYPE=VIEW
query=select `r`.`trx_wait_started` AS `wait_started`,timediff(current_timestamp(),`r`.`trx_wait_started`) AS `wait_age`,timestampdiff(SECOND,`r`.`trx_wait_started`,current_timestamp()) AS `wait_age_secs`,`rl`.`lock_table` AS `locked_table`,`rl`.`lock_index` AS `locked_index`,`rl`.`lock_type` AS `locked_type`,`r`.`trx_id` AS `waiting_trx_id`,`r`.`trx_started` AS `waiting_trx_started`,timediff(current_timestamp(),`r`.`trx_started`) AS `waiting_trx_age`,`r`.`trx_rows_locked` AS `waiting_trx_rows_locked`,`r`.`trx_rows_modified` AS `waiting_trx_rows_modified`,`r`.`trx_mysql_thread_id` AS `waiting_pid`,`r`.`trx_query` AS `waiting_query`,`rl`.`lock_id` AS `waiting_lock_id`,`rl`.`lock_mode` AS `waiting_lock_mode`,`b`.`trx_id` AS `blocking_trx_id`,`b`.`trx_mysql_thread_id` AS `blocking_pid`,`b`.`trx_query` AS `blocking_query`,`bl`.`lock_id` AS `blocking_lock_id`,`bl`.`lock_mode` AS `blocking_lock_mode`,`b`.`trx_started` AS `blocking_trx_started`,timediff(current_timestamp(),`b`.`trx_started`) AS `blocking_trx_age`,`b`.`trx_rows_locked` AS `blocking_trx_rows_locked`,`b`.`trx_rows_modified` AS `blocking_trx_rows_modified`,concat(\'KILL QUERY \',`b`.`trx_mysql_thread_id`) AS `sql_kill_blocking_query`,concat(\'KILL \',`b`.`trx_mysql_thread_id`) AS `sql_kill_blocking_connection` from ((((`information_schema`.`innodb_lock_waits` `w` join `information_schema`.`innodb_trx` `b` on(`b`.`trx_id` = `w`.`blocking_trx_id`)) join `information_schema`.`innodb_trx` `r` on(`r`.`trx_id` = `w`.`requesting_trx_id`)) join `information_schema`.`innodb_locks` `bl` on(`bl`.`lock_id` = `w`.`blocking_lock_id`)) join `information_schema`.`innodb_locks` `rl` on(`rl`.`lock_id` = `w`.`requested_lock_id`)) order by `r`.`trx_wait_started`
md5=a220fb929bcd0fe52730169f87db8aaa
updatable=0
algorithm=2
definer_user=mariadb.sys
definer_host=localhost
suid=0
with_check_option=0
timestamp=2022-04-08 18:05:38
create-version=2
source=SELECT r.trx_wait_started AS wait_started,\n       TIMEDIFF(NOW(), r.trx_wait_started) AS wait_age,\n       TIMESTAMPDIFF(SECOND, r.trx_wait_started, NOW()) AS wait_age_secs,\n       rl.lock_table AS locked_table,\n       rl.lock_index AS locked_index,\n       rl.lock_type AS locked_type,\n       r.trx_id AS waiting_trx_id,\n       r.trx_started as waiting_trx_started,\n       TIMEDIFF(NOW(), r.trx_started) AS waiting_trx_age,\n       r.trx_rows_locked AS waiting_trx_rows_locked,\n       r.trx_rows_modified AS waiting_trx_rows_modified,\n       r.trx_mysql_thread_id AS waiting_pid,\n       r.trx_query AS waiting_query,\n       rl.lock_id AS waiting_lock_id,\n       rl.lock_mode AS waiting_lock_mode,\n       b.trx_id AS blocking_trx_id,\n       b.trx_mysql_thread_id AS blocking_pid,\n       b.trx_query AS blocking_query,\n       bl.lock_id AS blocking_lock_id,\n       bl.lock_mode AS blocking_lock_mode,\n       b.trx_started AS blocking_trx_started,\n       TIMEDIFF(NOW(), b.trx_started) AS blocking_trx_age,\n       b.trx_rows_locked AS blocking_trx_rows_locked,\n       b.trx_rows_modified AS blocking_trx_rows_modified,\n       CONCAT(\'KILL QUERY \', b.trx_mysql_thread_id) AS sql_kill_blocking_query,\n       CONCAT(\'KILL \', b.trx_mysql_thread_id) AS sql_kill_blocking_connection\n  FROM information_schema.innodb_lock_waits w\n       INNER JOIN information_schema.innodb_trx b    ON b.trx_id = w.blocking_trx_id\n       INNER JOIN information_schema.innodb_trx r    ON r.trx_id = w.requesting_trx_id\n       INNER JOIN information_schema.innodb_locks bl ON bl.lock_id = w.blocking_lock_id\n       INNER JOIN information_schema.innodb_locks rl ON rl.lock_id = w.requested_lock_id\n ORDER BY r.trx_wait_started;
client_cs_name=utf8mb3
connection_cl_name=utf8mb3_general_ci
view_body_utf8=select `r`.`trx_wait_started` AS `wait_started`,timediff(current_timestamp(),`r`.`trx_wait_started`) AS `wait_age`,timestampdiff(SECOND,`r`.`trx_wait_started`,current_timestamp()) AS `wait_age_secs`,`rl`.`lock_table` AS `locked_table`,`rl`.`lock_index` AS `locked_index`,`rl`.`lock_type` AS `locked_type`,`r`.`trx_id` AS `waiting_trx_id`,`r`.`trx_started` AS `waiting_trx_started`,timediff(current_timestamp(),`r`.`trx_started`) AS `waiting_trx_age`,`r`.`trx_rows_locked` AS `waiting_trx_rows_locked`,`r`.`trx_rows_modified` AS `waiting_trx_rows_modified`,`r`.`trx_mysql_thread_id` AS `waiting_pid`,`r`.`trx_query` AS `waiting_query`,`rl`.`lock_id` AS `waiting_lock_id`,`rl`.`lock_mode` AS `waiting_lock_mode`,`b`.`trx_id` AS `blocking_trx_id`,`b`.`trx_mysql_thread_id` AS `blocking_pid`,`b`.`trx_query` AS `blocking_query`,`bl`.`lock_id` AS `blocking_lock_id`,`bl`.`lock_mode` AS `blocking_lock_mode`,`b`.`trx_started` AS `blocking_trx_started`,timediff(current_timestamp(),`b`.`trx_started`) AS `blocking_trx_age`,`b`.`trx_rows_locked` AS `blocking_trx_rows_locked`,`b`.`trx_rows_modified` AS `blocking_trx_rows_modified`,concat(\'KILL QUERY \',`b`.`trx_mysql_thread_id`) AS `sql_kill_blocking_query`,concat(\'KILL \',`b`.`trx_mysql_thread_id`) AS `sql_kill_blocking_connection` from ((((`information_schema`.`innodb_lock_waits` `w` join `information_schema`.`innodb_trx` `b` on(`b`.`trx_id` = `w`.`blocking_trx_id`)) join `information_schema`.`innodb_trx` `r` on(`r`.`trx_id` = `w`.`requesting_trx_id`)) join `information_schema`.`innodb_locks` `bl` on(`bl`.`lock_id` = `w`.`blocking_lock_id`)) join `information_schema`.`innodb_locks` `rl` on(`rl`.`lock_id` = `w`.`requested_lock_id`)) order by `r`.`trx_wait_started`
mariadb-version=100703