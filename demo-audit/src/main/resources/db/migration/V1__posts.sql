create table post (
    id serial primary key,
    text text,

    created_by varchar not null,
    created_at timestamptz not null,
    changed_by varchar,
    changed_at timestamptz
);



create table revinfo (
    rev serial primary key,
    REVTSTMP bigint
);

create table post_AUD (
    id bigint not null,
    REV integer not null references revinfo(rev),
    REVTYPE int,
    text text,
    primary key (id, REV)
);

CREATE SEQUENCE revinfo_seq;