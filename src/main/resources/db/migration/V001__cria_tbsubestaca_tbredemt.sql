create table tb_subestacao(
	id_subestacao int not null auto_increment,
    codigo varchar(3),
    nome   varchar(100),
    latitude decimal(15,13),
    longitude decimal(15,13),
    primary key (id_subestacao)
) engine=InnoDB default charset=utf8;

create table tb_rede_mt(
	id_rede_mt int not null auto_increment,
    id_subestacao int,
    codigo varchar(5),
    nome varchar(100),
    tensao_nominal decimal(5,2),
    primary key (id_rede_mt),
    constraint fk_tbredemt_tbsubestacao foreign key (id_subestacao) references tb_subestacao (id_subestacao)
) engine=InnoDB default charset=utf8;