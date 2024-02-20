create table if not exists jack_pills_service.stocks (
    gid                      uuid                 not null default uuid_generate_v1mc(),
    pill_gid                 uuid                 not null,
    type_gid                 uuid                 not null,
    due_date                 date,
    quantity                 int,
    FOREIGN KEY (pill_gid) REFERENCES jack_pills_service.pills(gid),
    FOREIGN KEY (type_gid) REFERENCES jack_pills_service.type_of_pills(gid),
    PRIMARY KEY(gid)
)