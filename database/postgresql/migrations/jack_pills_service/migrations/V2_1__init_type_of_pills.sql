create table if not exists jack_pills_service.type_of_pills (
    gid                  uuid              not null default uuid_generate_v1mc(),
    type                 varchar(20)       not null unique,
    PRIMARY KEY(gid)
);