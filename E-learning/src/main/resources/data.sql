
-- Deterministic seed data for E_learning
USE E_learning;

SET FOREIGN_KEY_CHECKS=0;

DELETE FROM payments;
DELETE FROM enrollments;
DELETE FROM lessonsContent;
DELETE FROM video_encoded_qualities;
DELETE FROM videos;
DELETE FROM lessons;
DELETE FROM sections;
DELETE FROM courses;
DELETE FROM users;

SET FOREIGN_KEY_CHECKS=1;

-- Users
INSERT INTO users(id,name,email,password,role) VALUES
                                                   (1,'Admin User','admin@test.com','password','Admin'),
                                                   (2,'Instructor One','inst1@test.com','password','Instructor'),
                                                   (3,'Instructor Two','inst2@test.com','password','Instructor'),
                                                   (4,'Instructor Three','inst3@test.com','password','Instructor');

INSERT INTO users(id,name,email,password,role) VALUES (5,'Student 5','student5@test.com','password','Student');
INSERT INTO users(id,name,email,password,role) VALUES (6,'Student 6','student6@test.com','password','Student');
INSERT INTO users(id,name,email,password,role) VALUES (7,'Student 7','student7@test.com','password','Student');
INSERT INTO users(id,name,email,password,role) VALUES (8,'Student 8','student8@test.com','password','Student');
INSERT INTO users(id,name,email,password,role) VALUES (9,'Student 9','student9@test.com','password','Student');
INSERT INTO users(id,name,email,password,role) VALUES (10,'Student 10','student10@test.com','password','Student');
INSERT INTO users(id,name,email,password,role) VALUES (11,'Student 11','student11@test.com','password','Student');
INSERT INTO users(id,name,email,password,role) VALUES (12,'Student 12','student12@test.com','password','Student');
INSERT INTO users(id,name,email,password,role) VALUES (13,'Student 13','student13@test.com','password','Student');
INSERT INTO users(id,name,email,password,role) VALUES (14,'Student 14','student14@test.com','password','Student');
INSERT INTO users(id,name,email,password,role) VALUES (15,'Student 15','student15@test.com','password','Student');
INSERT INTO users(id,name,email,password,role) VALUES (16,'Student 16','student16@test.com','password','Student');
INSERT INTO users(id,name,email,password,role) VALUES (17,'Student 17','student17@test.com','password','Student');
INSERT INTO users(id,name,email,password,role) VALUES (18,'Student 18','student18@test.com','password','Student');
INSERT INTO users(id,name,email,password,role) VALUES (19,'Student 19','student19@test.com','password','Student');
INSERT INTO users(id,name,email,password,role) VALUES (20,'Student 20','student20@test.com','password','Student');
INSERT INTO users(id,name,email,password,role) VALUES (21,'Student 21','student21@test.com','password','Student');
INSERT INTO users(id,name,email,password,role) VALUES (22,'Student 22','student22@test.com','password','Student');
INSERT INTO users(id,name,email,password,role) VALUES (23,'Student 23','student23@test.com','password','Student');
INSERT INTO users(id,name,email,password,role) VALUES (24,'Student 24','student24@test.com','password','Student');

INSERT INTO courses(id,title,`desc`,priceInCents,currency,state,publishedAt,unPublishedAt,instructorId)
VALUES (1,'Course 1','Description for course 1',1000,'USD','PUBLISHED','2025-01-01 10:00:00',NULL,2);

INSERT INTO sections(id,`index`,title,courseId) VALUES (1,1,'Section 1 - Course 1',1);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (1,1,'Lesson 1','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',1);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (1,'Content for lesson 1',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (2,2,'Lesson 2','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',1);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0002','Video 2',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0002','240p'),
                                                         ('vid_0002','480p'),
                                                         ('vid_0002','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (2,NULL,300,'vid_0002');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (3,3,'Lesson 3','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',1);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (3,'Content for lesson 3',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (4,4,'Lesson 4','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',1);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0004','Video 4',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0004','240p'),
                                                         ('vid_0004','480p'),
                                                         ('vid_0004','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (4,NULL,300,'vid_0004');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (5,5,'Lesson 5','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',1);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (5,'Content for lesson 5',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (2,2,'Section 2 - Course 1',1);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (6,1,'Lesson 6','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',2);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (6,'Content for lesson 6',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (7,2,'Lesson 7','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',2);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0007','Video 7',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0007','240p'),
                                                         ('vid_0007','480p'),
                                                         ('vid_0007','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (7,NULL,300,'vid_0007');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (8,3,'Lesson 8','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',2);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (8,'Content for lesson 8',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (9,4,'Lesson 9','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',2);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0009','Video 9',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0009','240p'),
                                                         ('vid_0009','480p'),
                                                         ('vid_0009','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (9,NULL,300,'vid_0009');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (10,5,'Lesson 10','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',2);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (10,'Content for lesson 10',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (3,3,'Section 3 - Course 1',1);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (11,1,'Lesson 11','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',3);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (11,'Content for lesson 11',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (12,2,'Lesson 12','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',3);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0012','Video 12',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0012','240p'),
                                                         ('vid_0012','480p'),
                                                         ('vid_0012','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (12,NULL,300,'vid_0012');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (13,3,'Lesson 13','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',3);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (13,'Content for lesson 13',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (14,4,'Lesson 14','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',3);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0014','Video 14',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0014','240p'),
                                                         ('vid_0014','480p'),
                                                         ('vid_0014','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (14,NULL,300,'vid_0014');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (15,5,'Lesson 15','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',3);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (15,'Content for lesson 15',120,NULL);

INSERT INTO courses(id,title,`desc`,priceInCents,currency,state,publishedAt,unPublishedAt,instructorId)
VALUES (2,'Course 2','Description for course 2',2000,'USD','DRAFT','2025-01-01 10:00:00',NULL,3);

INSERT INTO sections(id,`index`,title,courseId) VALUES (4,1,'Section 1 - Course 2',2);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (16,1,'Lesson 16','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',4);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (16,'Content for lesson 16',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (17,2,'Lesson 17','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',4);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0017','Video 17',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0017','240p'),
                                                         ('vid_0017','480p'),
                                                         ('vid_0017','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (17,NULL,300,'vid_0017');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (18,3,'Lesson 18','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',4);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (18,'Content for lesson 18',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (19,4,'Lesson 19','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',4);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0019','Video 19',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0019','240p'),
                                                         ('vid_0019','480p'),
                                                         ('vid_0019','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (19,NULL,300,'vid_0019');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (20,5,'Lesson 20','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',4);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (20,'Content for lesson 20',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (5,2,'Section 2 - Course 2',2);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (21,1,'Lesson 21','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',5);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (21,'Content for lesson 21',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (22,2,'Lesson 22','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',5);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0022','Video 22',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0022','240p'),
                                                         ('vid_0022','480p'),
                                                         ('vid_0022','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (22,NULL,300,'vid_0022');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (23,3,'Lesson 23','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',5);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (23,'Content for lesson 23',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (24,4,'Lesson 24','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',5);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0024','Video 24',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0024','240p'),
                                                         ('vid_0024','480p'),
                                                         ('vid_0024','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (24,NULL,300,'vid_0024');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (25,5,'Lesson 25','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',5);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (25,'Content for lesson 25',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (6,3,'Section 3 - Course 2',2);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (26,1,'Lesson 26','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',6);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (26,'Content for lesson 26',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (27,2,'Lesson 27','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',6);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0027','Video 27',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0027','240p'),
                                                         ('vid_0027','480p'),
                                                         ('vid_0027','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (27,NULL,300,'vid_0027');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (28,3,'Lesson 28','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',6);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (28,'Content for lesson 28',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (29,4,'Lesson 29','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',6);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0029','Video 29',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0029','240p'),
                                                         ('vid_0029','480p'),
                                                         ('vid_0029','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (29,NULL,300,'vid_0029');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (30,5,'Lesson 30','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',6);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (30,'Content for lesson 30',120,NULL);

INSERT INTO courses(id,title,`desc`,priceInCents,currency,state,publishedAt,unPublishedAt,instructorId)
VALUES (3,'Course 3','Description for course 3',3000,'USD','UNPUBLISHED','2025-01-01 10:00:00',NULL,4);

INSERT INTO sections(id,`index`,title,courseId) VALUES (7,1,'Section 1 - Course 3',3);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (31,1,'Lesson 31','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',7);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (31,'Content for lesson 31',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (32,2,'Lesson 32','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',7);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0032','Video 32',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0032','240p'),
                                                         ('vid_0032','480p'),
                                                         ('vid_0032','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (32,NULL,300,'vid_0032');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (33,3,'Lesson 33','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',7);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (33,'Content for lesson 33',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (34,4,'Lesson 34','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',7);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0034','Video 34',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0034','240p'),
                                                         ('vid_0034','480p'),
                                                         ('vid_0034','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (34,NULL,300,'vid_0034');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (35,5,'Lesson 35','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',7);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (35,'Content for lesson 35',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (8,2,'Section 2 - Course 3',3);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (36,1,'Lesson 36','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',8);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (36,'Content for lesson 36',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (37,2,'Lesson 37','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',8);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0037','Video 37',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0037','240p'),
                                                         ('vid_0037','480p'),
                                                         ('vid_0037','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (37,NULL,300,'vid_0037');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (38,3,'Lesson 38','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',8);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (38,'Content for lesson 38',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (39,4,'Lesson 39','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',8);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0039','Video 39',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0039','240p'),
                                                         ('vid_0039','480p'),
                                                         ('vid_0039','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (39,NULL,300,'vid_0039');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (40,5,'Lesson 40','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',8);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (40,'Content for lesson 40',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (9,3,'Section 3 - Course 3',3);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (41,1,'Lesson 41','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',9);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (41,'Content for lesson 41',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (42,2,'Lesson 42','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',9);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0042','Video 42',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0042','240p'),
                                                         ('vid_0042','480p'),
                                                         ('vid_0042','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (42,NULL,300,'vid_0042');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (43,3,'Lesson 43','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',9);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (43,'Content for lesson 43',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (44,4,'Lesson 44','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',9);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0044','Video 44',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0044','240p'),
                                                         ('vid_0044','480p'),
                                                         ('vid_0044','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (44,NULL,300,'vid_0044');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (45,5,'Lesson 45','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',9);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (45,'Content for lesson 45',120,NULL);

INSERT INTO courses(id,title,`desc`,priceInCents,currency,state,publishedAt,unPublishedAt,instructorId)
VALUES (4,'Course 4','Description for course 4',4000,'USD','PUBLISHED','2025-01-01 10:00:00',NULL,2);

INSERT INTO sections(id,`index`,title,courseId) VALUES (10,1,'Section 1 - Course 4',4);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (46,1,'Lesson 46','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',10);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (46,'Content for lesson 46',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (47,2,'Lesson 47','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',10);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0047','Video 47',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0047','240p'),
                                                         ('vid_0047','480p'),
                                                         ('vid_0047','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (47,NULL,300,'vid_0047');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (48,3,'Lesson 48','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',10);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (48,'Content for lesson 48',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (49,4,'Lesson 49','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',10);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0049','Video 49',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0049','240p'),
                                                         ('vid_0049','480p'),
                                                         ('vid_0049','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (49,NULL,300,'vid_0049');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (50,5,'Lesson 50','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',10);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (50,'Content for lesson 50',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (11,2,'Section 2 - Course 4',4);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (51,1,'Lesson 51','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',11);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (51,'Content for lesson 51',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (52,2,'Lesson 52','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',11);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0052','Video 52',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0052','240p'),
                                                         ('vid_0052','480p'),
                                                         ('vid_0052','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (52,NULL,300,'vid_0052');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (53,3,'Lesson 53','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',11);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (53,'Content for lesson 53',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (54,4,'Lesson 54','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',11);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0054','Video 54',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0054','240p'),
                                                         ('vid_0054','480p'),
                                                         ('vid_0054','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (54,NULL,300,'vid_0054');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (55,5,'Lesson 55','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',11);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (55,'Content for lesson 55',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (12,3,'Section 3 - Course 4',4);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (56,1,'Lesson 56','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',12);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (56,'Content for lesson 56',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (57,2,'Lesson 57','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',12);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0057','Video 57',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0057','240p'),
                                                         ('vid_0057','480p'),
                                                         ('vid_0057','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (57,NULL,300,'vid_0057');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (58,3,'Lesson 58','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',12);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (58,'Content for lesson 58',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (59,4,'Lesson 59','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',12);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0059','Video 59',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0059','240p'),
                                                         ('vid_0059','480p'),
                                                         ('vid_0059','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (59,NULL,300,'vid_0059');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (60,5,'Lesson 60','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',12);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (60,'Content for lesson 60',120,NULL);

INSERT INTO courses(id,title,`desc`,priceInCents,currency,state,publishedAt,unPublishedAt,instructorId)
VALUES (5,'Course 5','Description for course 5',5000,'USD','DRAFT','2025-01-01 10:00:00',NULL,3);

INSERT INTO sections(id,`index`,title,courseId) VALUES (13,1,'Section 1 - Course 5',5);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (61,1,'Lesson 61','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',13);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (61,'Content for lesson 61',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (62,2,'Lesson 62','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',13);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0062','Video 62',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0062','240p'),
                                                         ('vid_0062','480p'),
                                                         ('vid_0062','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (62,NULL,300,'vid_0062');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (63,3,'Lesson 63','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',13);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (63,'Content for lesson 63',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (64,4,'Lesson 64','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',13);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0064','Video 64',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0064','240p'),
                                                         ('vid_0064','480p'),
                                                         ('vid_0064','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (64,NULL,300,'vid_0064');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (65,5,'Lesson 65','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',13);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (65,'Content for lesson 65',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (14,2,'Section 2 - Course 5',5);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (66,1,'Lesson 66','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',14);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (66,'Content for lesson 66',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (67,2,'Lesson 67','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',14);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0067','Video 67',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0067','240p'),
                                                         ('vid_0067','480p'),
                                                         ('vid_0067','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (67,NULL,300,'vid_0067');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (68,3,'Lesson 68','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',14);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (68,'Content for lesson 68',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (69,4,'Lesson 69','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',14);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0069','Video 69',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0069','240p'),
                                                         ('vid_0069','480p'),
                                                         ('vid_0069','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (69,NULL,300,'vid_0069');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (70,5,'Lesson 70','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',14);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (70,'Content for lesson 70',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (15,3,'Section 3 - Course 5',5);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (71,1,'Lesson 71','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',15);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (71,'Content for lesson 71',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (72,2,'Lesson 72','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',15);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0072','Video 72',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0072','240p'),
                                                         ('vid_0072','480p'),
                                                         ('vid_0072','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (72,NULL,300,'vid_0072');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (73,3,'Lesson 73','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',15);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (73,'Content for lesson 73',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (74,4,'Lesson 74','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',15);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0074','Video 74',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0074','240p'),
                                                         ('vid_0074','480p'),
                                                         ('vid_0074','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (74,NULL,300,'vid_0074');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (75,5,'Lesson 75','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',15);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (75,'Content for lesson 75',120,NULL);

INSERT INTO courses(id,title,`desc`,priceInCents,currency,state,publishedAt,unPublishedAt,instructorId)
VALUES (6,'Course 6','Description for course 6',6000,'USD','UNPUBLISHED','2025-01-01 10:00:00',NULL,4);

INSERT INTO sections(id,`index`,title,courseId) VALUES (16,1,'Section 1 - Course 6',6);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (76,1,'Lesson 76','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',16);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (76,'Content for lesson 76',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (77,2,'Lesson 77','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',16);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0077','Video 77',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0077','240p'),
                                                         ('vid_0077','480p'),
                                                         ('vid_0077','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (77,NULL,300,'vid_0077');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (78,3,'Lesson 78','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',16);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (78,'Content for lesson 78',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (79,4,'Lesson 79','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',16);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0079','Video 79',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0079','240p'),
                                                         ('vid_0079','480p'),
                                                         ('vid_0079','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (79,NULL,300,'vid_0079');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (80,5,'Lesson 80','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',16);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (80,'Content for lesson 80',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (17,2,'Section 2 - Course 6',6);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (81,1,'Lesson 81','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',17);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (81,'Content for lesson 81',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (82,2,'Lesson 82','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',17);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0082','Video 82',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0082','240p'),
                                                         ('vid_0082','480p'),
                                                         ('vid_0082','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (82,NULL,300,'vid_0082');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (83,3,'Lesson 83','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',17);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (83,'Content for lesson 83',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (84,4,'Lesson 84','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',17);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0084','Video 84',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0084','240p'),
                                                         ('vid_0084','480p'),
                                                         ('vid_0084','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (84,NULL,300,'vid_0084');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (85,5,'Lesson 85','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',17);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (85,'Content for lesson 85',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (18,3,'Section 3 - Course 6',6);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (86,1,'Lesson 86','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',18);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (86,'Content for lesson 86',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (87,2,'Lesson 87','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',18);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0087','Video 87',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0087','240p'),
                                                         ('vid_0087','480p'),
                                                         ('vid_0087','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (87,NULL,300,'vid_0087');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (88,3,'Lesson 88','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',18);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (88,'Content for lesson 88',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (89,4,'Lesson 89','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',18);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0089','Video 89',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0089','240p'),
                                                         ('vid_0089','480p'),
                                                         ('vid_0089','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (89,NULL,300,'vid_0089');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (90,5,'Lesson 90','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',18);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (90,'Content for lesson 90',120,NULL);

INSERT INTO courses(id,title,`desc`,priceInCents,currency,state,publishedAt,unPublishedAt,instructorId)
VALUES (7,'Course 7','Description for course 7',7000,'USD','PUBLISHED','2025-01-01 10:00:00',NULL,2);

INSERT INTO sections(id,`index`,title,courseId) VALUES (19,1,'Section 1 - Course 7',7);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (91,1,'Lesson 91','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',19);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (91,'Content for lesson 91',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (92,2,'Lesson 92','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',19);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0092','Video 92',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0092','240p'),
                                                         ('vid_0092','480p'),
                                                         ('vid_0092','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (92,NULL,300,'vid_0092');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (93,3,'Lesson 93','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',19);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (93,'Content for lesson 93',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (94,4,'Lesson 94','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',19);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0094','Video 94',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0094','240p'),
                                                         ('vid_0094','480p'),
                                                         ('vid_0094','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (94,NULL,300,'vid_0094');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (95,5,'Lesson 95','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',19);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (95,'Content for lesson 95',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (20,2,'Section 2 - Course 7',7);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (96,1,'Lesson 96','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',20);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (96,'Content for lesson 96',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (97,2,'Lesson 97','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',20);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0097','Video 97',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0097','240p'),
                                                         ('vid_0097','480p'),
                                                         ('vid_0097','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (97,NULL,300,'vid_0097');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (98,3,'Lesson 98','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',20);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (98,'Content for lesson 98',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (99,4,'Lesson 99','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',20);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0099','Video 99',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0099','240p'),
                                                         ('vid_0099','480p'),
                                                         ('vid_0099','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (99,NULL,300,'vid_0099');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (100,5,'Lesson 100','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',20);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (100,'Content for lesson 100',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (21,3,'Section 3 - Course 7',7);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (101,1,'Lesson 101','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',21);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (101,'Content for lesson 101',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (102,2,'Lesson 102','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',21);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0102','Video 102',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0102','240p'),
                                                         ('vid_0102','480p'),
                                                         ('vid_0102','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (102,NULL,300,'vid_0102');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (103,3,'Lesson 103','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',21);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (103,'Content for lesson 103',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (104,4,'Lesson 104','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',21);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0104','Video 104',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0104','240p'),
                                                         ('vid_0104','480p'),
                                                         ('vid_0104','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (104,NULL,300,'vid_0104');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (105,5,'Lesson 105','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',21);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (105,'Content for lesson 105',120,NULL);

INSERT INTO courses(id,title,`desc`,priceInCents,currency,state,publishedAt,unPublishedAt,instructorId)
VALUES (8,'Course 8','Description for course 8',8000,'USD','DRAFT','2025-01-01 10:00:00',NULL,3);

INSERT INTO sections(id,`index`,title,courseId) VALUES (22,1,'Section 1 - Course 8',8);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (106,1,'Lesson 106','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',22);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (106,'Content for lesson 106',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (107,2,'Lesson 107','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',22);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0107','Video 107',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0107','240p'),
                                                         ('vid_0107','480p'),
                                                         ('vid_0107','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (107,NULL,300,'vid_0107');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (108,3,'Lesson 108','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',22);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (108,'Content for lesson 108',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (109,4,'Lesson 109','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',22);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0109','Video 109',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0109','240p'),
                                                         ('vid_0109','480p'),
                                                         ('vid_0109','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (109,NULL,300,'vid_0109');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (110,5,'Lesson 110','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',22);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (110,'Content for lesson 110',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (23,2,'Section 2 - Course 8',8);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (111,1,'Lesson 111','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',23);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (111,'Content for lesson 111',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (112,2,'Lesson 112','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',23);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0112','Video 112',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0112','240p'),
                                                         ('vid_0112','480p'),
                                                         ('vid_0112','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (112,NULL,300,'vid_0112');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (113,3,'Lesson 113','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',23);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (113,'Content for lesson 113',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (114,4,'Lesson 114','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',23);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0114','Video 114',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0114','240p'),
                                                         ('vid_0114','480p'),
                                                         ('vid_0114','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (114,NULL,300,'vid_0114');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (115,5,'Lesson 115','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',23);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (115,'Content for lesson 115',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (24,3,'Section 3 - Course 8',8);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (116,1,'Lesson 116','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',24);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (116,'Content for lesson 116',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (117,2,'Lesson 117','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',24);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0117','Video 117',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0117','240p'),
                                                         ('vid_0117','480p'),
                                                         ('vid_0117','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (117,NULL,300,'vid_0117');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (118,3,'Lesson 118','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',24);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (118,'Content for lesson 118',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (119,4,'Lesson 119','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',24);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0119','Video 119',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0119','240p'),
                                                         ('vid_0119','480p'),
                                                         ('vid_0119','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (119,NULL,300,'vid_0119');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (120,5,'Lesson 120','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',24);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (120,'Content for lesson 120',120,NULL);

INSERT INTO courses(id,title,`desc`,priceInCents,currency,state,publishedAt,unPublishedAt,instructorId)
VALUES (9,'Course 9','Description for course 9',9000,'USD','UNPUBLISHED','2025-01-01 10:00:00',NULL,4);

INSERT INTO sections(id,`index`,title,courseId) VALUES (25,1,'Section 1 - Course 9',9);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (121,1,'Lesson 121','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',25);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (121,'Content for lesson 121',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (122,2,'Lesson 122','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',25);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0122','Video 122',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0122','240p'),
                                                         ('vid_0122','480p'),
                                                         ('vid_0122','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (122,NULL,300,'vid_0122');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (123,3,'Lesson 123','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',25);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (123,'Content for lesson 123',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (124,4,'Lesson 124','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',25);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0124','Video 124',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0124','240p'),
                                                         ('vid_0124','480p'),
                                                         ('vid_0124','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (124,NULL,300,'vid_0124');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (125,5,'Lesson 125','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',25);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (125,'Content for lesson 125',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (26,2,'Section 2 - Course 9',9);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (126,1,'Lesson 126','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',26);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (126,'Content for lesson 126',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (127,2,'Lesson 127','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',26);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0127','Video 127',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0127','240p'),
                                                         ('vid_0127','480p'),
                                                         ('vid_0127','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (127,NULL,300,'vid_0127');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (128,3,'Lesson 128','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',26);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (128,'Content for lesson 128',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (129,4,'Lesson 129','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',26);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0129','Video 129',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0129','240p'),
                                                         ('vid_0129','480p'),
                                                         ('vid_0129','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (129,NULL,300,'vid_0129');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (130,5,'Lesson 130','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',26);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (130,'Content for lesson 130',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (27,3,'Section 3 - Course 9',9);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (131,1,'Lesson 131','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',27);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (131,'Content for lesson 131',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (132,2,'Lesson 132','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',27);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0132','Video 132',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0132','240p'),
                                                         ('vid_0132','480p'),
                                                         ('vid_0132','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (132,NULL,300,'vid_0132');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (133,3,'Lesson 133','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',27);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (133,'Content for lesson 133',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (134,4,'Lesson 134','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',27);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0134','Video 134',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0134','240p'),
                                                         ('vid_0134','480p'),
                                                         ('vid_0134','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (134,NULL,300,'vid_0134');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (135,5,'Lesson 135','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',27);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (135,'Content for lesson 135',120,NULL);

INSERT INTO courses(id,title,`desc`,priceInCents,currency,state,publishedAt,unPublishedAt,instructorId)
VALUES (10,'Course 10','Description for course 10',10000,'USD','PUBLISHED','2025-01-01 10:00:00',NULL,2);

INSERT INTO sections(id,`index`,title,courseId) VALUES (28,1,'Section 1 - Course 10',10);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (136,1,'Lesson 136','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',28);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (136,'Content for lesson 136',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (137,2,'Lesson 137','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',28);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0137','Video 137',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0137','240p'),
                                                         ('vid_0137','480p'),
                                                         ('vid_0137','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (137,NULL,300,'vid_0137');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (138,3,'Lesson 138','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',28);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (138,'Content for lesson 138',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (139,4,'Lesson 139','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',28);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0139','Video 139',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0139','240p'),
                                                         ('vid_0139','480p'),
                                                         ('vid_0139','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (139,NULL,300,'vid_0139');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (140,5,'Lesson 140','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',28);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (140,'Content for lesson 140',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (29,2,'Section 2 - Course 10',10);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (141,1,'Lesson 141','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',29);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (141,'Content for lesson 141',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (142,2,'Lesson 142','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',29);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0142','Video 142',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0142','240p'),
                                                         ('vid_0142','480p'),
                                                         ('vid_0142','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (142,NULL,300,'vid_0142');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (143,3,'Lesson 143','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',29);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (143,'Content for lesson 143',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (144,4,'Lesson 144','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',29);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0144','Video 144',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0144','240p'),
                                                         ('vid_0144','480p'),
                                                         ('vid_0144','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (144,NULL,300,'vid_0144');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (145,5,'Lesson 145','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',29);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (145,'Content for lesson 145',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (30,3,'Section 3 - Course 10',10);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (146,1,'Lesson 146','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',30);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (146,'Content for lesson 146',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (147,2,'Lesson 147','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',30);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0147','Video 147',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0147','240p'),
                                                         ('vid_0147','480p'),
                                                         ('vid_0147','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (147,NULL,300,'vid_0147');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (148,3,'Lesson 148','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',30);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (148,'Content for lesson 148',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (149,4,'Lesson 149','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',30);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0149','Video 149',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',2);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0149','240p'),
                                                         ('vid_0149','480p'),
                                                         ('vid_0149','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (149,NULL,300,'vid_0149');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (150,5,'Lesson 150','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',30);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (150,'Content for lesson 150',120,NULL);

INSERT INTO courses(id,title,`desc`,priceInCents,currency,state,publishedAt,unPublishedAt,instructorId)
VALUES (11,'Course 11','Description for course 11',11000,'USD','DRAFT','2025-01-01 10:00:00',NULL,3);

INSERT INTO sections(id,`index`,title,courseId) VALUES (31,1,'Section 1 - Course 11',11);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (151,1,'Lesson 151','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',31);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (151,'Content for lesson 151',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (152,2,'Lesson 152','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',31);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0152','Video 152',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0152','240p'),
                                                         ('vid_0152','480p'),
                                                         ('vid_0152','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (152,NULL,300,'vid_0152');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (153,3,'Lesson 153','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',31);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (153,'Content for lesson 153',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (154,4,'Lesson 154','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',31);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0154','Video 154',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0154','240p'),
                                                         ('vid_0154','480p'),
                                                         ('vid_0154','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (154,NULL,300,'vid_0154');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (155,5,'Lesson 155','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',31);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (155,'Content for lesson 155',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (32,2,'Section 2 - Course 11',11);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (156,1,'Lesson 156','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',32);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (156,'Content for lesson 156',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (157,2,'Lesson 157','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',32);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0157','Video 157',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0157','240p'),
                                                         ('vid_0157','480p'),
                                                         ('vid_0157','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (157,NULL,300,'vid_0157');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (158,3,'Lesson 158','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',32);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (158,'Content for lesson 158',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (159,4,'Lesson 159','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',32);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0159','Video 159',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0159','240p'),
                                                         ('vid_0159','480p'),
                                                         ('vid_0159','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (159,NULL,300,'vid_0159');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (160,5,'Lesson 160','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',32);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (160,'Content for lesson 160',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (33,3,'Section 3 - Course 11',11);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (161,1,'Lesson 161','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',33);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (161,'Content for lesson 161',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (162,2,'Lesson 162','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',33);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0162','Video 162',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0162','240p'),
                                                         ('vid_0162','480p'),
                                                         ('vid_0162','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (162,NULL,300,'vid_0162');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (163,3,'Lesson 163','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',33);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (163,'Content for lesson 163',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (164,4,'Lesson 164','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',33);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0164','Video 164',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',3);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0164','240p'),
                                                         ('vid_0164','480p'),
                                                         ('vid_0164','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (164,NULL,300,'vid_0164');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (165,5,'Lesson 165','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',33);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (165,'Content for lesson 165',120,NULL);

INSERT INTO courses(id,title,`desc`,priceInCents,currency,state,publishedAt,unPublishedAt,instructorId)
VALUES (12,'Course 12','Description for course 12',12000,'USD','UNPUBLISHED','2025-01-01 10:00:00',NULL,4);

INSERT INTO sections(id,`index`,title,courseId) VALUES (34,1,'Section 1 - Course 12',12);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (166,1,'Lesson 166','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',34);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (166,'Content for lesson 166',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (167,2,'Lesson 167','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',34);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0167','Video 167',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0167','240p'),
                                                         ('vid_0167','480p'),
                                                         ('vid_0167','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (167,NULL,300,'vid_0167');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (168,3,'Lesson 168','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',34);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (168,'Content for lesson 168',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (169,4,'Lesson 169','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',34);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0169','Video 169',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0169','240p'),
                                                         ('vid_0169','480p'),
                                                         ('vid_0169','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (169,NULL,300,'vid_0169');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (170,5,'Lesson 170','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',34);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (170,'Content for lesson 170',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (35,2,'Section 2 - Course 12',12);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (171,1,'Lesson 171','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',35);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (171,'Content for lesson 171',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (172,2,'Lesson 172','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',35);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0172','Video 172',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0172','240p'),
                                                         ('vid_0172','480p'),
                                                         ('vid_0172','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (172,NULL,300,'vid_0172');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (173,3,'Lesson 173','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',35);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (173,'Content for lesson 173',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (174,4,'Lesson 174','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',35);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0174','Video 174',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0174','240p'),
                                                         ('vid_0174','480p'),
                                                         ('vid_0174','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (174,NULL,300,'vid_0174');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (175,5,'Lesson 175','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',35);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (175,'Content for lesson 175',120,NULL);
INSERT INTO sections(id,`index`,title,courseId) VALUES (36,3,'Section 3 - Course 12',12);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (176,1,'Lesson 176','2025-01-01 12:00:00',NULL,true,'TXT','PUBLISHED',36);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (176,'Content for lesson 176',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (177,2,'Lesson 177','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',36);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0177','Video 177',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0177','240p'),
                                                         ('vid_0177','480p'),
                                                         ('vid_0177','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (177,NULL,300,'vid_0177');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (178,3,'Lesson 178','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',36);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (178,'Content for lesson 178',120,NULL);
INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (179,4,'Lesson 179','2025-01-01 12:00:00',NULL,false,'VIDEO','PUBLISHED',36);

INSERT INTO videos(id,title,isPublic,isMp4Support,createAt,updateAt,metaData,status,fileSize,isPlayable,width,height,bitrate,duration,framerate,samplerate,video_codec,audio_codec,aspect_ratio,ownerId)
VALUES ('vid_0179','Video 179',true,true,NOW(),NOW(),'{}','ready',1000000,true,1920,1080,5000000,300,30,48000,'H264','AAC','16:9',4);

INSERT INTO video_encoded_qualities(videoId,quality) VALUES
                                                         ('vid_0179','240p'),
                                                         ('vid_0179','480p'),
                                                         ('vid_0179','720p');

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (179,NULL,300,'vid_0179');

INSERT INTO lessons(id,`index`,title,lastPublishedAt,lastUnpublishedAt,isPreview,type,state,sectionId)
VALUES (180,5,'Lesson 180','2025-01-01 12:00:00',NULL,false,'TXT','PUBLISHED',36);

INSERT INTO lessonsContent(id,txt,duration,videoId) VALUES (180,'Content for lesson 180',120,NULL);
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (1,5,1,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000001-1111-1111-1111-111111111111','-','')),5,1000,'USD','STRIPE','SUCCESS',1,'txn_1','sess_1',1738404000,'2025-02-01 10:00:00');
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (2,6,2,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000002-1111-1111-1111-111111111111','-','')),6,1000,'USD','STRIPE','SUCCESS',2,'txn_2','sess_2',1738404000,'2025-02-01 10:00:00');
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (3,7,3,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000003-1111-1111-1111-111111111111','-','')),7,1000,'USD','STRIPE','SUCCESS',3,'txn_3','sess_3',1738404000,'2025-02-01 10:00:00');
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (4,8,4,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000004-1111-1111-1111-111111111111','-','')),8,1000,'USD','STRIPE','SUCCESS',4,'txn_4','sess_4',1738404000,'2025-02-01 10:00:00');
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (5,9,5,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000005-1111-1111-1111-111111111111','-','')),9,1000,'USD','STRIPE','SUCCESS',5,'txn_5','sess_5',1738404000,'2025-02-01 10:00:00');
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (6,10,6,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000006-1111-1111-1111-111111111111','-','')),10,1000,'USD','STRIPE','SUCCESS',6,'txn_6','sess_6',1738404000,'2025-02-01 10:00:00');
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (7,11,7,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000007-1111-1111-1111-111111111111','-','')),11,1000,'USD','STRIPE','SUCCESS',7,'txn_7','sess_7',1738404000,'2025-02-01 10:00:00');
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (8,12,8,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000008-1111-1111-1111-111111111111','-','')),12,1000,'USD','STRIPE','SUCCESS',8,'txn_8','sess_8',1738404000,'2025-02-01 10:00:00');
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (9,13,1,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000009-1111-1111-1111-111111111111','-','')),13,1000,'USD','STRIPE','SUCCESS',1,'txn_9','sess_9',1738404000,'2025-02-01 10:00:00');
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (10,14,2,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000010-1111-1111-1111-111111111111','-','')),14,1000,'USD','STRIPE','SUCCESS',2,'txn_10','sess_10',1738404000,'2025-02-01 10:00:00');
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (11,15,3,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000011-1111-1111-1111-111111111111','-','')),15,1000,'USD','STRIPE','SUCCESS',3,'txn_11','sess_11',1738404000,'2025-02-01 10:00:00');
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (12,16,4,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000012-1111-1111-1111-111111111111','-','')),16,1000,'USD','STRIPE','SUCCESS',4,'txn_12','sess_12',1738404000,'2025-02-01 10:00:00');
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (13,17,5,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000013-1111-1111-1111-111111111111','-','')),17,1000,'USD','STRIPE','SUCCESS',5,'txn_13','sess_13',1738404000,'2025-02-01 10:00:00');
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (14,18,6,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000014-1111-1111-1111-111111111111','-','')),18,1000,'USD','STRIPE','SUCCESS',6,'txn_14','sess_14',1738404000,'2025-02-01 10:00:00');
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (15,19,7,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000015-1111-1111-1111-111111111111','-','')),19,1000,'USD','STRIPE','SUCCESS',7,'txn_15','sess_15',1738404000,'2025-02-01 10:00:00');
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (16,20,8,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000016-1111-1111-1111-111111111111','-','')),20,1000,'USD','STRIPE','SUCCESS',8,'txn_16','sess_16',1738404000,'2025-02-01 10:00:00');
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (17,21,1,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000017-1111-1111-1111-111111111111','-','')),21,1000,'USD','STRIPE','SUCCESS',1,'txn_17','sess_17',1738404000,'2025-02-01 10:00:00');
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (18,22,2,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000018-1111-1111-1111-111111111111','-','')),22,1000,'USD','STRIPE','SUCCESS',2,'txn_18','sess_18',1738404000,'2025-02-01 10:00:00');
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (19,23,3,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000019-1111-1111-1111-111111111111','-','')),23,1000,'USD','STRIPE','SUCCESS',3,'txn_19','sess_19',1738404000,'2025-02-01 10:00:00');
INSERT INTO enrollments(id,usrId,courseId,enrollDate) VALUES (20,24,4,'2025-02-01 10:00:00');
INSERT INTO payments(id,userId,priceInCents,currency,method,status,course_id,transaction_id,session_id,paidAt,createdAt) VALUES (UNHEX(REPLACE('00000020-1111-1111-1111-111111111111','-','')),24,1000,'USD','STRIPE','SUCCESS',4,'txn_20','sess_20',1738404000,'2025-02-01 10:00:00');