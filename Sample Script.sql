DROP TABLE MEMBER;
DROP TABLE SEQ_USERNO;

CREATE TABLE MEMBER(
    USERNO NUMBER PRIMARY KEY,                      --ȸ����ȣ
    USERID VARCHAR2(15) NOT NULL UNIQUE,            --ȸ�����̵�
    USERPWD VARCHAR2(15) NOT NULL,                  --ȸ����й�ȣ 
    USERNAME VARCHAR2(20) NOT NULL,                 --ȸ���̸�
    GENDER CHAR(1) CHECK(GENDER IN ('M','F')),      --����
    AGE NUMBER,                                     --����
    EMAIL VARCHAR2(30),                             --�̸���
    PHONE CHAR(11),                                 --��ȭ��ȣ -�� ����
    ADDRESS VARCHAR2(100),                          --�ּ�
    HOBBY VARCHAR2(50),                             --���
    ENROLLDATE DATE DEFAULT SYSDATE NOT NULL        --ȸ��������
);


SELECT * FROM MEMBER;

CREATE SEQUENCE SEQ_USERNO
NOCACHE;

INSERT INTO MEMBER
VALUES(SEQ_USERNO.NEXTVAL,'admin','1234','������','M',45,'admin@iei.co.kr','01012345678','����',NULL,'2021-07-27');

INSERT INTO MEMBER
VALUES(SEQ_USERNO.NEXTVAL,'admin2','1234','������2','F',30,'admin2@iei.co.kr','01011112222','����',NULL,DEFAULT);

commit;

CREATE TABLE TEST(
    TNO NUMBER,
    TNAME VARCHAR2(20),
    TDATE DATE
);

SELECT * FROM TEST;