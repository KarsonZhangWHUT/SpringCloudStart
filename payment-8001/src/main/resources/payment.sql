create database spring_cloud_start;

use spring_cloud_start;

create table `payment`
(
    `id`     bigint(20) not null auto_increment comment 'id',
    `serial` varchar(200) default '',
    primary key (`id`)
) engine = InnoDB
  auto_increment = 1
  default charset = utf8;
