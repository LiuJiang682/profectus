drop table if exists invoice;
create table invoice(
    invoice_number bigint auto_increment primary key, 
    invoice_type   char(1) not null,
    invoice_date   varchar(20),
    total_amount   decimal(8, 2),
    net_amount     decimal(8,2),
    cash_method    char(1),
    security_fee   decimal(8,2)
);