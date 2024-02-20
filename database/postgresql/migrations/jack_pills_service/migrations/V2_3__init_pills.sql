create table if not exists jack_pills_service.pills (
    gid                     uuid                not null default uuid_generate_v1mc(),
    type_gid                uuid                not null,
    name                    varchar(20)         not null unique,
    FOREIGN KEY (type_gid) REFERENCES jack_pills_service.type_of_pills(gid),
    PRIMARY KEY(gid)
)