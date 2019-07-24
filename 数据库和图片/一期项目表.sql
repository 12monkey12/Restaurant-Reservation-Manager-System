create table cuisine(
       c_id int primary key, --��ϵ���
       c_name varchar2(20) unique not null --��ϵ����
)
create sequence seq_cuisine_c_id start with 1 increment by 1;
select * from cuisine
drop table cuisine
drop sequence seq_cuisine_c_id

create table dishes(
       d_id int primary key, --��Ʒ���
       d_name varchar2(20) not null, --��Ʒ����
       d_fid int constraint FK_DISHES_D_FID references cuisine(c_id), --������ϵ���
       d_prices number(9) not null, --����
       d_remark varchar2(255), --��Ʒ����
       d_photo blob --��ƷͼƬ
);
select * from dishes
create sequence seq_dishes_d_id start with 1 increment by 1;
drop sequence seq_dishes_d_id
select d_id,d_name,c_name,d_prices from cuisine c,dishes d where c.c_id = d.d_fid;

select d_id,d_name,c_name,d_prices from cuisine c,dishes d where c.c_id = d.d_fid order by d_id

insert into cuisine values (seq_cuisine_c_id.nextval, '���');
insert into cuisine values (seq_cuisine_c_id.nextval, '����');
insert into cuisine values (seq_cuisine_c_id.nextval, '����');
insert into cuisine values (seq_cuisine_c_id.nextval, '³��');
insert into cuisine values (seq_cuisine_c_id.nextval, '�ղ�');
insert into cuisine values (seq_cuisine_c_id.nextval, '����');
insert into cuisine values (seq_cuisine_c_id.nextval, '���');
insert into cuisine values (seq_cuisine_c_id.nextval, '�ղ�');
insert into cuisine values (seq_cuisine_c_id.nextval, '��ˮ');

commit;
select * from dishes order by d_id;
insert into dishes values (seq_dishes_d_id.nextval,'���⳴��Ѫ����', 1, 38, '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�����', 1, 38, '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'����ȥ�Ǽ�צ', 1,68 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��Ƥ�⽷', 1,22 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�罷��ͷ����', 1,48 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��Ƥ�⽷', 1,22 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�⽷���ⷹ', 1, 26 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'÷�˿���', 1,18 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��ζ��Ϻ', 1,78 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'Ƥ���趹��', 1,22 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'Ƥ��������', 1,18 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'ơ��Ѽ', 1,38 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'���������', 1,18 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��˺����', 1,16 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'���С�㶹', 1,28 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�����', 1,68 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'���Ų˻ع���', 1,32 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��໨����', 1,18 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�������', 1,18 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'С����', 1,26 , '����', null);
--
insert into dishes values (seq_dishes_d_id.nextval,'С�㰮СϺ', 1,36 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��������', 1,26 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'������˿', 1,18 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��ɳ��������', 1,78 , '����', null);

insert into dishes values (seq_dishes_d_id.nextval,'�����Ź���', 2,18 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'���ӳ�����', 2,18 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'���¶�Ƥ��', 2,30 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��ݸ�ն�', 2, 49, '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��֭����ͷ', 2,30 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�ɶ�ľ������ɽ', 2,20 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�ͼ���˺�̼�', 2,30 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'ɳ������', 2,20 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�����ߵؼ�', 2,49 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�������', 2,26 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��Դ���׷�', 2,23 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�ع���', 2,36 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�ҳ������', 2,20 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��ɿͼҶ���', 2,20 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�²˳���', 2,20 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�ͼҳ�����', 2,28 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�ͼ�������', 2, 16, '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��Զ����', 2,23 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�����', 2,30 , '����', null);
--
insert into dishes values (seq_dishes_d_id.nextval,'����Ŵ�׹�', 2,48 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�̻����ӱ�', 2,20 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��ͷ������', 2,30 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'����������', 2,20 , '����', null);

insert into dishes values (seq_dishes_d_id.nextval,'��Ȼ���', 3,32 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��������', 3,18 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�ײ˶���������', 3,28 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�ײ�������', 3,20, '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'������', 3,52, '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'������', 3,18 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�����Ź���', 3,26, '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'���޷�Ƭ', 3,38 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'����', 3,18 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��������', 3,32 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�⽷����˿', 3,19 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'����Ѽ', 3,32 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��ݮɽҩ', 3,28 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'���Ŷ���', 3,18 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'ëѪ��', 3,52 , '����', null);
--
insert into dishes values (seq_dishes_d_id.nextval,'�ŹǼ�', 3, 66, '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�ݽ�����', 3,108 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'������', 3,20 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'ˮ��ţ��', 3,48 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'ˮ����Ƭ', 3,40 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�Ĵ��㳦', 3,32 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'����ţ���', 3,22 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'С���������', 3,38 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�����ˮ��', 3,28 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�������Ӽ�', 3,52 , '����', null);

insert into dishes values (seq_dishes_d_id.nextval,'һƷ����', 4,22 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'���պ���', 4,88 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��˿���', 4,128 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�װ��ı�', 4,56 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�Ǵ׻ƺ�����', 4,98 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��ת��', 4,36 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�ͱ�˫��', 4,26 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��ԭ�Ǳ���', 4,68 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�׽���', 4,48 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'������Ƭ', 4,36 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��Զ����', 4,22 , '����', null);
--
insert into dishes values (seq_dishes_d_id.nextval,'��������', 4,28 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'���ö���', 4,36, '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��������', 4,48, '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��������', 4,68 , '����', null);

insert into dishes values (seq_dishes_d_id.nextval,'����', 5,36 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'ˮ������', 5,36 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'����з��ʨ��ͷ', 5,99 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��������', 5,28 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�����м�', 5,48 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��ˮѼ', 5,86 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�����', 5,48 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�������˿', 5,18 , '����', null);
--
insert into dishes values (seq_dishes_d_id.nextval,'��βϺ', 5,58 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'������', 5,36 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'½�������ͷ��', 5,86 , '����', null);

insert into dishes values (seq_dishes_d_id.nextval,'����ǽ', 6,168 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��������', 6,38 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'���ߺ�', 6,28 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'����±��', 6,18 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'����±��', 6,18 , '����', null);
--
insert into dishes values (seq_dishes_d_id.nextval,'���ü�', 6,22 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'ɳ�ذ���', 6,18 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'����ɳ����', 6,18 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�����̷�', 6,28 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�˻��׷�', 6,18 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��֦��', 6,28 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��ѩ��', 6,48 , '����', null);

insert into dishes values (seq_dishes_d_id.nextval,'��������', 7,68 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'������', 7,28 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��з��', 7,38 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��������', 7,28 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��ը����', 7,36 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��Ҷ������', 7,48 , '����', null);
--
insert into dishes values (seq_dishes_d_id.nextval,'���ۻ����', 7,66 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�л�ͯ��', 7,68 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��������', 7,66 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'˿��±������', 7,108, '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'���Ǽ���', 7,68 , '����', null);

insert into dishes values (seq_dishes_d_id.nextval,'�𿾹���', 8,68 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'������', 8,49 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��������', 8,66 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�ʲ��', 8,28 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��÷Բ��', 8,36 , '����', null);
--
insert into dishes values (seq_dishes_d_id.nextval,'���������', 8,50 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'���ҹ�����', 8,88, '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'����ʯ��', 8,98 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��Ƥë����', 8,28 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'�����ձ�', 8,16 , '����', null);

insert into dishes values (seq_dishes_d_id.nextval,'35��С����', 9,18 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'45��С�O��', 9,20 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'500��������(��)', 9,10 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'500����������ơ��(��', 9,7 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'����', 9,11 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'����', 9, 10, '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��ƿ����', 9, 8, '����', null);
--
insert into dishes values (seq_dishes_d_id.nextval,'��ƿѩ��', 9, 8, '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��װ����', 9, 4, '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��װ���ϼ�',9, 4, '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'��װѩ��', 9,4 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'600����������ơ��', 9,8 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'ũ��ɽȪ', 9, 3, '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'���һ��ƻ����', 9,24 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'���һ��ƻ����24', 9,8 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'500����ѩ�����', 9,10 , '����', null);
insert into dishes values (seq_dishes_d_id.nextval,'500�����ྩơ��', 9,5 , '����', null);

alter session set nls_date_format='yyyy-mm-dd hh:mi:ss';

commit;
delete from dishes where D_NAME = '������';

delete from dishes where D_fid = 9;
--��ˡ����ˡ����ˡ�³�ˡ��ղˡ����ˡ���ˡ��ղ�


