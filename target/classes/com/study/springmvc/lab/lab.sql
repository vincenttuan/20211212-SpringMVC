create table if not exists fund (
	fid integer not null auto_increment,
	fname varchar(50),
	createtime datetime default current_timestamp,
	primary key(fid)
);

create table if not exists fundstock (
	sid integer not null auto_increment,
	fid integer,
	symbol varchar(50),
	share integer,
	foreign key(fid) references fund(fid), -- 外鍵關聯
	primary key(sid)
);