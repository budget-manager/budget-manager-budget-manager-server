```derby
create sequence hibernate_sequence start with 1 increment by 1;
create table budget
(
    budget_id         bigint       not null,
    budgeted_amount   bigint       not null,
    end_date          timestamp    not null,
    name              varchar(100) not null,
    recurring         boolean,
    start_date        timestamp    not null,
    threshold_percent double       not null,
    user_id           bigint       not null,
    primary key (budget_id)
);
create table transaction
(
    transaction_id bigint       not null,
    amount         bigint       not null,
    created        timestamp    not null,
    date           timestamp    not null,
    name           varchar(100) not null,
    note           varchar(1000),
    budget_id      bigint       not null,
    primary key (transaction_id)
);
create table user
(
    user_id   bigint       not null,
    oauth2key varchar(100) not null,
    username  varchar(100) not null,
    primary key (user_id)
);
alter table user
    add constraint UK_5w1bxv1osg998s2h9mkp2s0kw unique (oauth2key);
alter table user
    add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);
alter table budget
    add constraint FKkuh8cj1roovp9nh6ut2igrxm2 foreign key (user_id) references user;
alter table transaction
    add constraint FK7ul8m5q12we515aa7b7ao0p44 foreign key (budget_id) references budget;

```

[`ddl.sql`](sql/ddl.sql)