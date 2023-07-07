DROP TABLE MEMBER;
DROP TABLE SEQ_USERNO;

CREATE TABLE MEMBER(
    USERNO NUMBER PRIMARY KEY,                      --회원번호
    USERID VARCHAR2(15) NOT NULL UNIQUE,            --회원아이디
    USERPWD VARCHAR2(15) NOT NULL,                  --회원비밀번호 
    USERNAME VARCHAR2(20) NOT NULL,                 --회원이름
    GENDER CHAR(1) CHECK(GENDER IN ('M','F')),      --성별
    AGE NUMBER,                                     --나이
    EMAIL VARCHAR2(30),                             --이메일
    PHONE CHAR(11),                                 --전화번호 -기 없음
    ADDRESS VARCHAR2(100),                          --주소
    HOBBY VARCHAR2(50),                             --취미
    ENROLLDATE DATE DEFAULT SYSDATE NOT NULL        --회원가입일
);


SELECT * FROM MEMBER;

CREATE SEQUENCE SEQ_USERNO
NOCACHE;

INSERT INTO MEMBER
VALUES(SEQ_USERNO.NEXTVAL,'admin','1234','관리자','M',45,'admin@iei.co.kr','01012345678','서울',NULL,'2021-07-27');

INSERT INTO MEMBER
VALUES(SEQ_USERNO.NEXTVAL,'admin2','1234','관리자2','F',30,'admin2@iei.co.kr','01011112222','용인',NULL,DEFAULT);

commit;

CREATE TABLE TEST(
    TNO NUMBER,
    TNAME VARCHAR2(20),
    TDATE DATE
);

SELECT * FROM TEST;