create table if not exists jack_pills_service.schedule (
    gid                      uuid                 not null default uuid_generate_v1mc(),
    intake_date              date                 not null,
    type_gid                 uuid                 not null,
    pill_gid                 uuid                 not null,
    stock_gid                uuid                 not null,
    is_taken                 boolean,
    FOREIGN KEY  (type_gid) REFERENCES jack_pills_service.type_of_pills(gid),
    FOREIGN KEY  (pill_gid) REFERENCES jack_pills_service.pills(gid),
    FOREIGN KEY  (stock_gid) REFERENCES jack_pills_service.stocks(gid)
)