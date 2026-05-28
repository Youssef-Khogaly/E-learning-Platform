
drop database if exists E_learning;
create database if not exists E_learning;
use E_learning;

SET default_storage_engine = InnoDB;

drop table if exists users;

create table users(
                      id bigint primary key auto_increment,
                      name varchar(256) not null ,
                      email varchar(256) unique not null ,
                      password varchar(512) not null ,
                      role enum('Admin','Instructor','Student') not null default 'Student'
);

drop table if exists courses;

create table courses(
                        id bigint primary key auto_increment,
                        title varchar(128) not null ,
                        `desc` TEXT,
                        priceInCents bigint not null ,
                        currency varchar(6) not null ,
                        state enum('DRAFT','PUBLISHED','UNPUBLISHED') not null ,
                        publishedAt TIMESTAMP,
                        unPublishedAt TIMESTAMP,
                        instructorId bigint not null ,
                        constraint courseInstructor foreign key (instructorId) references users(id)
                            on update CASCADE  on delete restrict
);

drop table if exists sections;

create table sections(
                         id bigint primary key auto_increment,
                         `index` integer not null ,
                         title varchar(128) not null,
                         courseId bigint not null ,
                         constraint courseSection foreign key (courseId) references courses(id)
                             on update CASCADE  on delete restrict
);

drop table if exists lessons;

create table lessons(
                        id bigint primary key auto_increment,
                        `index` integer,
                        title varchar(128) not null,
                        lastPublishedAt TIMESTAMP,
                        lastUnpublishedAt TIMESTAMP,
                        isPreview boolean default false,
                        type enum('TXT' , 'VIDEO') not null,
                        state enum('DRAFT','PUBLISHED','UNPUBLISHED') not null ,
                        sectionId bigint not null ,
                        constraint lessonSection foreign key (sectionId) references sections(id)
                            on update CASCADE  on delete restrict
);

drop table if exists videos;

create table videos(
                       id varchar(32) primary key not null ,
                       title varchar(128) not null,
                       isPublic boolean,
                       isMp4Support boolean,
                       createAt timestamp,
                       updateAt timestamp,
                       metaData json,
                       status enum('uploading','uploaded','processing','ready') not null ,
                       fileSize integer ,
                       isPlayable boolean,
                       width integer,
                       height INT,
                       bitrate BIGINT,
                       duration integer,
                       framerate integer,
                       samplerate INT,
                       video_codec VARCHAR(50),
                       audio_codec VARCHAR(50),
                       aspect_ratio VARCHAR(20),
                       ownerId bigint not null,
                       constraint videoOwner foreign key (ownerId) references users(id)
                           on update cascade on delete restrict
);

drop table if exists video_encoded_qualities;

create table video_encoded_qualities(
                                        videoId varchar(32) not null ,
                                        quality enum('240p','360p','480p','720p','1080p','2160p') not null ,
                                        primary key (videoId,quality),
                                        constraint video foreign key(videoId) references videos(id)
);

drop table if exists lessonsContent;

create table lessonsContent(
                               id bigint primary key not null ,
                               txt TEXT default null,
                               duration int ,
                               videoId varchar(32),
                               constraint videoLesson foreign key (videoId) references videos(id)
                                   on update cascade on delete cascade ,

                               constraint lesson foreign key (id)references lessons(id)
);

drop table if exists enrollments;

create table enrollments(
                            id bigint primary key auto_increment,
                            usrId bigint not null ,
                            courseId bigint not null ,
                            enrollDate timestamp not null ,
                            constraint usrEnroll foreign key (usrId) references users(id)
                                on update cascade on delete restrict,
                            constraint courseEnroll foreign key (courseId) references courses(id)
                                on update CASCADE  on delete restrict,
                            constraint uniqueEnroll unique (usrId,courseId)
);

drop table if exists payments;

create table payments(
                         id BINARY(16) primary key ,
                         userId bigint not null ,
                         priceInCents bigint not null ,
                         currency varchar(6) not null ,
                         method enum('STRIPE') not null,
                         status enum('EXPIRED','FAILED','PENDING','SUCCESS') not null,
                         course_id bigint not null ,
                         transaction_id varchar(64),
                         session_id varchar(64) unique not null ,
                         paidAt bigint null ,
                         createdAt TIMESTAMP,
                         constraint usrPayment foreign key (userId) references users(id)
                             on update cascade on delete restrict,
                         constraint coursePayment foreign key (course_id) references courses(id)
                             on update CASCADE  on delete restrict
);