create table if not exists jack_pills_service.intervals (
    gid                     uuid      not null default uuid_generate_v1mc(),
    type_gid                uuid      not null,
    cold_months             int,
    warm_months             int,
    FOREIGN KEY (type_gid) REFERENCES jack_pills_service.type_of_pills(gid),
    PRIMARY KEY(gid)
)