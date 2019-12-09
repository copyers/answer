# answer
Answerandquestion
##问答社区
git测试


###脚本
```sql
create table user
(
    ID           bigint auto_increment
        primary key,
    ACCOUNT_ID   varchar(100) null,
    NAME         varchar(50)  null,
    TOKEN        char(36)     null,
    GMT_CREATE   bigint       null,
    GMT_MODIFIED bigint       null,
    bio          varchar(256) null,
    avatar_url   varchar(100) null
);



create table question
(
    id            bigint auto_increment
        primary key,
    title         varchar(50)   null,
    description   text          null,
    gmt_create    bigint        null,
    gmt_modified  bigint        null,
    creator       bigint           null,
    comment_count int default 0 null,
    view_count    int default 0 null,
    like_count    int default 0 null,
    tag           varchar(256)  null
);
create table comment
(
    id           bigint auto_increment
        primary key,
    parent_id    bigint           not null comment '父类id',
    type         int              not null comment '父类类型
',
    commentator  bigint              not null comment '评论人id',
    gmt_create   bigint           not null comment '创建时间',
    gmt_modified bigint           not null comment '更新时间',
    like_count   bigint default 0 null comment '点赞数',
    content      varchar(1024)    null comment '评论内容'
);


```

mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate

config --global core.autocrlf false