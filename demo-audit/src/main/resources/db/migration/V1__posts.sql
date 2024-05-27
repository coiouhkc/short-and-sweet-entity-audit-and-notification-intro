create table posts (
id serial primary key,
text text,

created_by varchar not null,
created_at timestamptz not null,
changed_by varchar,
changed_at timestamptz
);