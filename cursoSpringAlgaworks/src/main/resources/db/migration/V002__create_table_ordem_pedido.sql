create table ordem_pedido(
	id int not null primary key auto_increment,
	cliente_id int not null,
	descricao text not null,
	preco decimal(10,2) not null,
	status varchar(20) not null,
	data_abertura datetime not null,
	data_finalizacao datetime
);

alter table ordem_pedido add constraint fk_ordem_pedido_cliente
foreign key (cliente_id) references cliente (id);
