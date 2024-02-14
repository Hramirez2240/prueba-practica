create table clients (
        id bigint not null,
        address varchar(255) not null,
        countryCode varchar(255) not null,
        demonym varchar(255) not null,
        email varchar(255) not null,
        firstLastName varchar(255) not null,
        firstName varchar(255) not null,
        middleName varchar(255),
        phoneNumber varchar(255) not null,
        secondLastName varchar(255),
        primary key (id)
    );

    create sequence clients_SEQ start with 1 increment by 50;