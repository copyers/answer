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
    id            bigint auto_increment
        primary key,
    parent_id     bigint           not null comment '父类id',
    type          int              not null comment '父类类型
',
    commentator   bigint           not null comment '评论人id',
    gmt_create    bigint           not null comment '创建时间',
    gmt_modified  bigint           not null comment '更新时间',
    like_count    bigint default 0 null comment '点赞数',
    content       varchar(1024)    null comment '评论内容',
    comment_count int    default 0 null
);
create table notification
(
    id            bigint auto_increment
        primary key,
    notifier      bigint        null,
    receiver      bigint        null,
    outerId       bigint        null,
    type          int           null,
    gmt_create    bigint        null,
    status        int default 0 null,
    NOTIFIER_NAME varchar(100)  null,
    OUTER_TITLE   varchar(256)  null
);



```

mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate

config --global core.autocrlf false

###项目部署
- JDK
- Maven 
- Git 
- MySQL
##步骤
- yum update
- yum install git
- mkdir App
- cd App
- git clone XXXXX.git
- yum install maven
- mvn -v
- mvn clean compile package
- cp src/main/resources/application.properties src/main/resources/application-production.properties
- vim src/main/resources/application-production.properties
- mvn package
- profile 启动