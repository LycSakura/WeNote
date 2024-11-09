drop database if exists wenote;
create database if not exists wenote charset=utf8;
use wenote;
create table users(
userName char(20) not null primary key,
nickname char(10) not null,#昵称
password char(100) not null,
telephone char(11) not null,
photo char(100) not null default 'default.jpg',
isAuthor char(1) not null default 'N',#'Y'表示是作者，'N'表示不是作者
brief char(24) not null,#简介也叫座右铭，不超过24个字符
createTime timestamp not null default current_timestamp#创建时间
)engine=InnoDB charset=utf8;

create table note(
noteID int auto_increment primary key,
author char(20) not null,
noteTitle char(100) not null,
noteContent text not null,
visit int not null default 0,#笔记的访问次数，默认值是零
categoryName char(50) not null,
createTime timestamp not null default current_timestamp,#创建时间
updateTime timestamp null,#修改时间
constraint note_users_fk foreign key (author) references users(userName)
)engine=InnoDB charset=utf8;

create table tag(
tagName char(50) not null,
noteID int not null,
primary key(tagName,noteID),
constraint tag_note_fk foreign key (noteID) references note(noteID)
)engine=InnoDB charset=utf8;

create table comment(
commentID int auto_increment primary key,
noteID int not null,
userName char(20) not null,
commentTitle char(100) not null,#评论有标题
commentContent text not null,
remoteIP char(50) not null,
createTime timestamp not null default current_timestamp,#创建时间
constraint comment_note_fk foreign key (noteID) references note(noteID),
constraint comment_users_fk foreign key (userName) references users(userName)
)engine=InnoDB charset=utf8;

use wenote;
show tables;
desc users;
show create table users;

insert into users values('administrator','笔记作者',md5(md5('administrator')),'00000000001','default.jpg','Y','简单就是美LESS IS MORE',default);
insert into users values('failure','不放弃',md5(md5('failure')),'00000000002','default.jpg','N','接受失败，但不选择放弃。',default);
insert into users values('success','多努力',md5(md5('success')),'00000000003','default.jpg','N','多一点努力，多一点成功。',default);

insert into note values(null,'administrator','administrator笔记标题1','administrator笔记内容1',default,'笔记类别1',default,null);
insert into note values(null,'administrator','administrator笔记标题2','administrator笔记内容2',default,'笔记类别1',default,null);
insert into note values(null,'administrator','administrator笔记标题3','administrator笔记内容3',default,'笔记类别2',default,null);

insert into tag values('笔记标签1',1);
insert into tag values('笔记标签1',2);
insert into tag values('笔记标签1',3);
insert into tag values('笔记标签2',1);
insert into tag values('笔记标签2',2);
insert into tag values('笔记标签2',3);
insert into tag values('笔记标签3',1);
insert into tag values('笔记标签3',2);
insert into tag values('笔记标签3',3);

insert into comment values(null,1,'failure','failure评论标题1','failure评论内容1','127.0.0.1',default);
insert into comment values(null,2,'failure','failure评论标题2','failure评论内容2','127.0.0.1',default);
insert into comment values(null,3,'failure','failure评论标题3','failure评论内容3','127.0.0.1',default);
insert into comment values(null,1,'success','success评论标题4','success评论内容4','127.0.0.1',default);
insert into comment values(null,2,'success','success评论标题5','success评论内容5','127.0.0.1',default);
insert into comment values(null,3,'success','success评论标题6','success评论内容6','127.0.0.1',default);

select * from users;
select * from note;
select * from comment;

#修改笔记的浏览次数
select * from note;
update note set visit=visit+1 where noteID=1;
select * from note;

#删除某篇笔记前，首先先删除该笔记的所有评论和所有标签（注意先删除子表记录）
insert into note values(10,'administrator','administrator笔记标题10','administrator笔记内容10',default,'笔记类别2',null,null);
insert into tag values('笔记标签1',10);
insert into comment values(100,10,'failure','failure评论标题100','failure评论内容100','127.0.0.1',null);
delete from note where noteID=10;#删除失败
delete from tag where noteID=10;
delete from comment where noteID=10;
delete from note where noteID=10;



#按照用户名，查询用户（判断用户名是否可用）
select * from users where userName='failure';
#按照用户名和密码，查询用户（判断用户能否成功登录）
select * from users where userName='failure' and password=md5(md5('failure'));
#按照笔记的ID，查询笔记详细信息
select * from note where noteID=1;
#按照笔记的ID，查询笔记的所有标签名
select tagName from tag where noteID=1;
#查询所有类别名称（过滤掉重复的名称）
select distinct categoryName from note;
#查询所有标签名称（过滤掉重复的名称）
select distinct tagName from tag;
#按照笔记发表时间的降序，查询所有笔记
select * from note order by createTime desc;

#查询某篇笔记的下一篇笔记
select * from note where noteID>2 order by noteID limit 1;
#查询某篇笔记的上一篇笔记
select * from note where noteID<2 order by noteID desc limit 1;
#查询某个用户发布的评论
select * from comment where userName='failure' order by createTime desc;
#模糊查询
select * from note where noteTitle like '%笔记%' or noteContent like '%笔记%' order by createTime desc;

#查询所有笔记类别及类别对应的笔记篇数
select categoryName,count(*) as num from note group by categoryName;
#查询所有笔记标签及标签对应的笔记篇数
select tagName,count(*) num from tag join note on tag.noteID=note.noteID group by tagName;

#按照笔记ID，查询笔记的评论信息以及发布评论的用户信息
select commentID,commentTitle,commentContent,comment.createTime createTime,
remoteIP,noteID,comment.userName userName,nickName,photo,brief 
from comment join users on comment.userName=users.userName 
where noteID=1 order by createTime desc;

#根据类别名，查询该类别的所有笔记
select * from note where categoryName='笔记类别2' order by createTime desc;
#根据标签名，查询该标签的所有笔记
select note.noteID,author,noteTitle,noteContent,visit,categoryName,
createTime,updateTime from note join tag on tag.noteID=note.noteID 
where tagName='笔记标签1' order by createTime desc;