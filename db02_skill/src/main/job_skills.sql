-- CS3810 - Principles of Database Systems - Spring 2021
-- Instructor: Thyago Mota
-- Description: job_skills database script
-- Alejandro Rojas

-- database creation and use
DROP DATABASE job_skills;
CREATE DATABASE job_skills;

USE job_skills;

-- tables creation
CREATE TABLE SKILL
(
        ID int NOT NULL,
        SkillName VARCHAR(255) NOT NULL,
        PRIMARY KEY(ID)
);

CREATE TABLE POSITIONS
(
        ID int NOT NULL,
        PositionName VARCHAR(255) NOT NULL,
        PRIMARY KEY(ID)
);

CREATE TABLE PositionSkill
(
        SkillID int not null,
        PositionID int not null,
        primary key (SkillID, PositionID),
        foreign key (SkillID)	    references   SKILL(ID),
        foreign key (PositionID)     references  POSITIONS(id));

-- data access
CREATE USER 'job_skills'@'%';
GRANT SELECT ON jobs.* TO 'job_skills'@'%';
CREATE USER 'job_skills_admin'@'%';
GRANT ALL ON *.* TO 'job_skills_admin'@'%';

-- queries

-- a) what is the total number of job positions?
Select count(ID) AS 'Total # of job position' from SKILL;
4540
-- b) what is the total number of skills?
select count(ID) AS 'Total # of skills' from Positions;
5474
-- c) which job position titles have the word "database"?
select SkillName AS 'Database like jobs' from SKILL where SkillName like '%database%';

-- d) provide an alphabetical list of all job position titles that require "sql" or "mysql" as a skill.
select p.SkillName from SKILL p
inner join PositionSkill ps on p.ID = ps.SkillID
inner join POSITIONS s on ps.PositionID = s.ID
where s.PositionName in ('sql', 'mysql')
order by 1;

-- e) which skills "database analyst" like positions have that "database admin" like positions don't?
select a.skills AS "Analyst-like, non admin-like skills" from(
select s.PositionName as 'skills' from POSITIONS s
inner join PositionSkill ps on s.ID = ps.PositionID
join SKILL p on p.ID = ps.SkillID
where SkillName like '%database analyst%') a
where a.skills not in (
select s.PositionName as 'skills' from POSITIONS s
inner join PositionSkill ps on s.ID = ps.PositionID
join SKILL p on p.ID = ps.SkillID
where SkillName like '%database admin%')
group by a.skills
order by a.skills;


-- f) list the top 20 skills required by job positions having the word "database" in their titles.
select s.PositionName as 'skills'
from PositionSkill ps
inner join POSITIONS s
on ps.PositionID = s.ID
inner join SKILL j on ps.SkillID = j.ID
and j.SkillName like '%database%'
group by ps.PositionID
order by ps.PositionID
limit 20;

-- Looking at the skills table, what I have developed so far in 3810 is queries, server, and mysql and data management.
-- This are the topics that we have touched or have been mention this semester.
-- I have learned or read on some other skills in the list like Spring boot and other web services.
