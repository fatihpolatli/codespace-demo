
 create table PUBLIC.user(
 	 id bigint not null,
      username varchar_ignorecase(255) not null primary key,
      password varchar_ignorecase(255) not null,
      email varchar_ignorecase(255) not null, 
      enabled boolean not null);



  create table PUBLIC.authorities (
      username varchar_ignorecase(50) not null,
      authority varchar_ignorecase(50) not null,
      constraint fk_authorities_users foreign key(username) references user(username));
      create unique index ix_auth_username on authorities (username,authority);
      
     
      
      create PUBLIC.table groups (
  id bigint generated by default as identity(start with 0) primary key,
  group_name varchar_ignorecase(50) not null);



create table PUBLIC.group_authorities (
  group_id bigint not null,
  authority varchar(50) not null,
  constraint fk_group_authorities_group foreign key(group_id) references groups(id));


create table PUBLIC.group_members (
  id bigint generated by default as identity(start with 0) primary key,
  username varchar(50) not null,
  group_id bigint not null,
  constraint fk_group_members_group foreign key(group_id) references groups(id));

  
  create table PUBLIC.persistent_logins (
  username varchar(64) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null);
  
  
  
 create table PUBLIC.money_types(
 	 id bigint not null primary key,
      money_label varchar_ignorecase(50) not null);
      
 create table PUBLIC.user_account(
 	 id bigint not null primary key,
 	 user_id bigint not null,
 	 account_type bigint not null,
      balance bigint not null,
       constraint fk_user_account foreign key(user_id) references user);
       
        create table PUBLIC.account_history(
 	 id bigint not null primary key,
 	 account_id bigint not null, 	 
      amount bigint not null,
      type int not null,
      action_date timestamp default 'now',
       constraint fk_account_log foreign key(account_id) references user_account);
      
  