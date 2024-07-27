use xihu;

drop table if EXISTS user;
create table user
(
    id             bigint primary key auto_increment not null,
    name           varchar(64)                       not null default '' comment '用户名',
    password       varchar(32)                       not null default '' comment '密码',
    image          varchar(255)                      not null default 'https://static-swiperimages.oss-cn-wuhan-lr.aliyuncs.com/images/default.png' comment '图片url',
    openid         varchar(255)                      not null default '' comment '唯一标识',
    identification varchar(16)                       not null default '线上观众',
    enterprise     varchar(255)                      not null default '',
    department     varchar(255)                      not null default '',
    position       varchar(255)                      not null default '',
    tel            varchar(16)                       not null default '',
    email          varchar(32)                       not null default '',
    integral       int                                        default 0 comment '用户所持有的积分',
    create_time    timestamp                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time    timestamp                         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete      tinyint                           not null default 0
);



drop table if exists integral;
create table integral
(
    id             bigint              not null primary key auto_increment,
    type           int                 not null default 1 comment '1代表可以进行多次的活动，0代表该活动只能进行一次',
    description    nvarchar(32) unique not null default '' comment '积分活动的名字',
    amount         int                 not null default 0 comment '完成一次可以获得的积分数',
    detail         nvarchar(255)       not null default '' comment '详情，如签到即送100积分 （每日仅限一次）',
    to_do          nvarchar(32)        not null default '' comment '未完成时显示的字',
    finished_words nvarchar(32)        not null default '' comment '完成时显示的字',
    url            varchar(255)        not null default '' comment '进行该活动而前往的url地址',
    create_time    timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time    timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete      tinyint             not null default 0
);


drop table if exists integral_action;

-- create table integral_action(
-- 	id bigint identity(1,1) primary key not null,
-- 	user_id bigint not null,
-- 	integral_id bigint not null,
-- 	create_time date not null default getdate(),
-- 	create_detail_time datetime not null default getdate()
-- )
-- GO


DROP table if exists goods;
create table goods
(
    id          bigint PRIMARY key not null auto_increment,
    name        varchar(32)        not null unique default '' comment '礼品的名字',
    price       int                not null        default '0' comment '礼品的价格',
    description varchar(255)       not null        DEFAULT '' comment '商品的描述',
    remain_num  int                not null        DEFAULT 10000 check (remain_num >= 0) comment '剩余量',
    category    varchar(16)        not null        default '优惠卷',
    url         varchar(255)       not null        default '' comment '封面url',
    create_time timestamp          NOT NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp          NOT NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   tinyint            not null        default 0
);


drop table if exists integral_record;
create table integral_record
(
    id          bigint    not null PRIMARY key auto_increment,
    user_id     bigint    not null default 1 comment '操作的用户的id',
    type        int       not null DEFAULT 0 comment '0代表积分增加，1代表消费积分减少',
    matter_id   bigint    not null DEFAULT 1 comment '对应的商品或者积分增加行为的id',
    create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    change_num  int       not null comment '积分的变化数',
    is_delete   tinyint   not null default 0
);



drop table if exists agenda;
create table agenda
(
    id          bigint PRIMARY key not null auto_increment,
    title       varchar(32)        not null unique default '' comment '标题',
    tag         varchar(32)        not null        default '' comment '分类标签',
    cover       varchar(255)       not null        default '' comment '封面url',
    content_url varchar(255)       not null        default '' comment '视频或直播url',
    sub_title   varchar(16)        not null        default '',
    start_time  datetime           not null        default CURRENT_TIMESTAMP,
    end_time    datetime           not null        default CURRENT_TIMESTAMP,
    timespan    varchar(32)        not null comment '如18:00-18:45',
    location    nvarchar(32)       not null        default '线上直播',
    is_live     tinyint            not null        default 0 comment '0表示已结束，1表示进行中',
    resource_id bigint             not null        default 0 comment '0代表没有资源',
    create_time timestamp          NOT NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp          NOT NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   tinyint            not null        default 0
);


drop table if exists subscription;
create table subscription
(
    id          bigint    not null primary key auto_increment,
    user_id     bigint    not null comment '订阅用户id',
    matter_id   bigint    not null comment '活动或者是会议的id',
    type        tinyint   not null DEFAULT 0 comment '0代表会议，1代表活动',
    is_delete   tinyint   not null default 0 comment '0代表未失效，1代表失效',
    create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    unique key key_user_matter_type (user_id, matter_id, type),
    index idx_user_matter_type (user_id, matter_id, type),
    index idx_user_type (user_id, type)
);


drop table if exists agenda_content;
create table agenda_content
(
    id          bigint        not null primary key auto_increment,
    agenda_id   bigint        not null,
    start_time  datetime      not null,
    end_time    datetime      not null,
    timespan    varchar(32)   not null comment '如17:00-17:05',
    content     varchar(255)  not null comment '内容',
    guest_list  nvarchar(255) not null comment '嘉宾介绍',
    create_time timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   tinyint       not null default 0
);


drop table if exists activity;
create table activity
(
    id           bigint       not null primary key auto_increment,
    name         varchar(32)  not null UNIQUE,
    column_name  varchar(16)  not null comment '专栏名',
    content_type tinyint      not null default 0 comment '0代表会议, 1代表文章',
    content_id   bigint       not null,
    cover        varchar(510) not null comment '封面图片url',
    description  varchar(255) not null,
    is_bookable  tinyint      not null default 0 comment '0代表不可订阅',
    book_count   int          not null default 0 comment '订阅人数',
    timespan     varchar(32)  not null,
    create_time  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete    tinyint      not null default 0
);



drop table if exists guest;
create table guest
(
    id          bigint       not null primary key auto_increment,
    name        nvarchar(16) not null,
    description nvarchar(64) not null,
    image       varchar(510) not null,
    agenda_id   bigint       not null comment '视频id',
    agenda_time date         default null comment '参会日期，不参会则为空',
    is_previous tinyint      not null default 0 comment '1表示往届',
    is_expert   tinyint      not null default 0,
    create_time timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   tinyint      not null default 0 comment '0表示未删除',
    index idx_name (name),
    index idx_date (agenda_time)
);


drop table if exists album;
create table album
(
    id          bigint      not null auto_increment primary key comment '主键',
    count       int         not null default 0 comment '该集合的资源个数，资源为图片或者视频',
    like_num    int         not null default 0 comment '该集合的点赞数',
    type        tinyint     not null default 1 comment '1代表图片集合，2代表视频集合',
    category    varchar(16) not null default '' comment '集合的分类',
    create_time timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   tinyint     not null default 0 comment '0表示未删除',
    unique key unique_category_type (category, type),
    index idx_category_type (category, type),
    index idx_type (type)
);


drop table if exists achievement;
create table achievement
(
    id          bigint        not null PRIMARY key auto_increment,
    title       nvarchar(32)  not null default '',
    description nvarchar(512) not null default '',
    count       bigint        not null default 0 comment '观看人数',
    category    nvarchar(16)  not null default '' comment '白皮书，新品发布，书籍',
    type        tinyint       not null default 0 comment '0表示普通展示成果,1代表下载，2代表试用类',
    images      varchar(510)  not null default '' comment '附带的图片的连接，用逗号隔开',
    url         varchar(255)  not null DEFAULT '' comment '下载链接或者空',
    create_time timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   tinyint       not null default 0
);



drop table if exists news;
create table news
(
    id           bigint       not null PRIMARY key auto_increment,
    title        varchar(32)  not null default '' comment '标题',
    publisher    varchar(16)  not null default '' comment '出版商',
    image        varchar(255) not null comment '图片的url',
    description  varchar(128) not null,
    publish_time date         not null DEFAULT '2024-05-07',
    create_time  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete    tinyint      not null default 0,
    unique key unique_title_publisher (title, publisher)
);

drop table if exists news_content;
create table news_content
(
    id          bigint primary key not null auto_increment,
    news_id     bigint             not null default 0 comment '对应的news的id',
    content     varchar(2046)      not null default '' comment 'html代码',
    create_time timestamp          NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   tinyint            not null default 0
);


drop table if exists image;
create table image
(
    id          bigint       not null PRIMARY key auto_increment,
    name        nvarchar(32) not null default '' comment '图片名',
    column_type varchar(64)  not null default '' comment '专栏名字',
    type        tinyint      not null default 3 comment '0代表封面，1代表会议图片，2代表人物头像，3代表精彩图片，4表示帖子图片',
    url         varchar(255) not null default '' comment '图片的地址',
    album_id    bigint       not null default 0 comment '对应图片合集的id，0代表不对应任何合集',
    create_time timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   tinyint      not null default 0,
    unique key unique_url (url)
);



drop table if exists video;
create table video
(
    id          bigint       not null primary key auto_increment,
    name        nvarchar(32) not null default '',
    column_type nvarchar(16) not null default '' comment '图片的分类',
    type        tinyint      not null default 1 comment '0代表会议,1代表普通视频',
    cover       varchar(255) not null,
    path        varchar(255) not null,
    album_id    bigint       not null default 0 comment '对应的视频集合id，如果为0就是不对应任何集合',
    watch_count int          not null default 0 comment '观看数',
    like_num    int          not null default 0 comment '点赞数',
    resource_id bigint       not null default 0 comment '0代表没有资源',
    create_time timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   tinyint      not null default 0
);



drop table if exists resource;
create table resource
(
    id          bigint       not null primary key auto_increment,
    name        nvarchar(32) not null default '',
    cover       varchar(255) not null default '',
    description nvarchar(64) not null default '',
    url         varchar(255) not null default '',
    type        tinyint      not null default 0 comment '0代表ppt，1代表pdf',
    create_time timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   tinyint      not null default 0
);

drop table if exists topic;
create table topic
(
    id          bigint       not null primary key auto_increment comment '主键',
    content     varchar(32)  not null unique comment '话题标题',
    hot         int          not null default 100 comment '热度',
    like_num    int          not null default 0 comment '点赞数',
    cover       varchar(255) not null comment '封面链接',
    post_num    int          not null default 0 comment '话题帖子的数量',
    watch_num   int          not null default 0 comment '观看数',
    create_time timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   tinyint      not null default 0
);

drop table if exists post;
create table post
(
    id           bigint        not null primary key auto_increment comment '主键',
    user_id      bigint        not null default 0 comment '发帖用户id',
    content      varchar(510)  not null default '' comment '帖子内容',
    hot          int           not null default 100 comment '热度',
    images       varchar(2046) not null default '' comment '帖子图片的url，用逗号隔开',
    comment_num  int           not null default 0 comment '帖子的评论数',
    like_num     int           not null default 0 comment '帖子的点赞数',
    top_flag     tinyint       not null default 0 comment '0表示不置顶,1表示置顶',
    publish_time datetime      not null comment '发帖日期',
    create_time  timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time  timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete    tinyint       not null default 0
);


drop table if exists comment;
create table comment
(
    id          bigint      not null primary key auto_increment,
    user_id     bigint      not null comment '用户id',
    post_id     bigint      not null comment '帖子id',
    content     varchar(32) not null comment '评论内容',
    ip          varchar(16) not null comment 'ip地址',
    like_count  int         not null default 0 comment '点赞数',
    parent_id   bigint      not null default 0 comment '父评论id,0代表没有父评论',
    create_time timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   tinyint     not null default 0,
    index idx_user_post (user_id, post_id),
    index idx_user (user_id),
    index idx_parent (parent_id)
);


drop table if exists praise;
create table praise
(
    id          bigint    not null primary key auto_increment comment '主键',
    user_id     bigint    not null comment '点赞用户id',
    liked_id    bigint    not null comment '被点赞的事务的id',
    type        tinyint   not null default 1 comment '1表示对帖子点赞，2表示对评论点赞，3表示对话题点赞',
    create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   tinyint   not null default 0,
    unique index uniq_user_type_liked (user_id, type, liked_id),
    index idx_user_type (user_id, type),
    index idx_liked_type (liked_id, type)
);


drop table if exists topic_post_reference;
create table topic_post_reference
(
    id bigint not null auto_increment primary key comment '主键',
    post_id bigint not null comment '帖子id',
    topic_id bigint not null comment '话题id',
    create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   tinyint   not null default 0,
    unique index idx_post_topic(post_id,topic_id)
)
