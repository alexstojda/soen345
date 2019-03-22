create table owner
(
	id INTEGER
		constraint owner_pk
			primary key autoincrement,
	first_name STRING,
	last_name STRING,
	address STRING,
	city STRING,
	phone_number STRING
);
