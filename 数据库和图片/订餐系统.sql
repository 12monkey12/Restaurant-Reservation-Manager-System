create table rusers(
       u_id int primary key, --�û����
       u_account varchar2(20) unique not null, --�û��˺�
       u_password varchar2(255) not null, --�û�����
       u_name varchar2(20) unique not null, --�û�����
       u_sex varchar2(2) constraint CK_RUSERS_U_SEX check(u_sex='��' or u_sex='Ů'), --�Ա�
       u_tel number(20) --�绰����
)
create sequence sqe_rusers_u_id start with 1 increment by 1
drop table rusers;
drop sequence sqe_rusers_u_id;

insert into rusers values(sqe_rusers_u_id.nextval,'link123','123','����','��',15555555555);
insert into rusers values(sqe_rusers_u_id.nextval,'link124','456','����','Ů',16666666666);
insert into rusers values(sqe_rusers_u_id.nextval,'test','123','����','��',1777777777);
select * from rusers
delete rusers where u_account = '11';

--����Ա��
create table radmin(
       a_id int primary key, --����Ա���
       a_account varchar2(20) unique not null, --����Ա�˺�
       a_password varchar2(20) not null, --����Ա����
       a_name varchar2(20) unique not null, --����Ա����
       a_sex varchar2(2) not null
)
drop table radmin
drop sequence sqe_radmin_a_id
create sequence sqe_radmin_a_id start with 1 increment by 1

insert into radmin values(sqe_radmin_a_id.nextval,'admin1','123456','���ҫ','��');
insert into radmin values(sqe_radmin_a_id.nextval,'admin2','123456','��˧','��');
insert into radmin values(sqe_radmin_a_id.nextval,'admin3','123456','�','��');
select * from radmin
commit;

--������
create table rtables(
       t_id int primary key, --���Ӻ�
       t_type varchar2(9) not null, --��������
       t_state number(2) default '1' --����״̬ 0��ʾ��ԤԼ 1��ʾ��ʹ��  2��ʾ����

)
select count(*) from rtables;
select count(*) from rtables where t_state = 1
delete from rtables where t_type = '������';
select * from rtables

create sequence seq_rtables_t_id start with 1 increment by 1;
drop sequence seq_rtables_t_id;
drop table rtables;
commit;

insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', default);
insert into rtables values ( seq_rtables_t_id.nextval, '������', 2);
insert into rtables values ( seq_rtables_t_id.nextval, '������', 2);
insert into rtables values ( seq_rtables_t_id.nextval, '������', 2);
insert into rtables values ( seq_rtables_t_id.nextval, '������', 2);
insert into rtables values ( seq_rtables_t_id.nextval, '������', 2);
insert into rtables values ( seq_rtables_t_id.nextval, '������', 2);
insert into rtables values ( seq_rtables_t_id.nextval, '������', 2);
insert into rtables values ( seq_rtables_t_id.nextval, '������', 2);
insert into rtables values ( seq_rtables_t_id.nextval, '������', 2);
insert into rtables values ( seq_rtables_t_id.nextval, '������', 2);
insert into rtables values ( seq_rtables_t_id.nextval, 'ʮ������', default);
insert into rtables values ( seq_rtables_t_id.nextval, 'ʮ������', default);
insert into rtables values ( seq_rtables_t_id.nextval, 'ʮ������', default);
insert into rtables values ( seq_rtables_t_id.nextval, 'ʮ������', default);
insert into rtables values ( seq_rtables_t_id.nextval, 'ʮ������', default);

--ԤԼ��
create table reservation(
       --ԤԼ��
       r_id int primary key,
       r_tid int constraint FK_RESERVATION_R_TID references rtables(t_id), --���Ӻ�
       r_uid int constraint FK_RESERVATION_R_UID references rusers(u_id), -- �ͻ���
       r_date date, --ԤԼ����
       --r_type number(2) default '0', --����״̬ 0��ʾδԤԼ
       --ԤԼ��ʱ���
       --r_time number(3) -- 0�������� 1�������磬2�������磬3��������
       r_type number(2) default '0', --r����״̬ 0��δ��Լ  1���Ѹ�Լ   2��ΥԼ
       r_note varchar2(200)  --��ע      
)

insert into reservation values(seq_reservation_r_id.nextval, 1, 1,'2019-07-08 18',default,'����' );
insert into reservation values(seq_reservation_r_id.nextval, 2, 3,'2019-07-01 11',default,'����' );
insert into reservation values(seq_reservation_r_id.nextval, 3, 2,'2019-07-09 09',default,'����' );
insert into reservation values(seq_reservation_r_id.nextval, 4, 1,'2019-07-06 10',default,'����' );
insert into reservation values(seq_reservation_r_id.nextval, 4, 1,'2019-07-11 10',default,'����' );
insert into reservation values(seq_reservation_r_id.nextval, 4, 2,'2019-07-11 10',default,'����' );
insert into reservation values(seq_reservation_r_id.nextval, 4, 3,'2019-07-11 10',default,'����' );

create sequence seq_reservation_r_id start with 1 increment by 1;
drop sequence seq_reservation_r_id;
select * from reservation where r_uid = 3;
drop table reservation;
commit;



--������
create table orders(
       o_id int primary key, --�������
       o_uid int constraint FK_ORDERS_O_UID references rusers(u_id), --�û����
       o_tid int constraint FK_ORDERS_O_TID references rtables(t_id),--������
       o_totalPrice number(10) not null, --�ܽ��
       o_starttime date, --�µ�ʱ��
       o_state number(2) default '0',  --����״̬��0����δ����  1���ѽ���
       o_endtime date, --����ʱ��
       o_number int --�Ͳ�����
)
select o_id,o_uid,o_tid,o_totalPrice,o_starttime,o_state,o_endtime,o_number from orders order by o_id
delete from orders where o_id in (62,46);
commit;

update orders set o_state = 1,o_endtime = sysdate where o_id = 21
alter session set nls_date_format='yyyy-mm-dd hh24:mi:ss';
alter session set nls_date_format='yyyy-mm-dd hh24';
create sequence seq_orders_o_id start with 1 increment by 1;
select * from orders;
drop table orders;
select orders.*,to_char(o_starttime,'yyyy-mm-dd hh24:mi:ss') as str_time from orders;  --
drop sequence seq_orders_o_id;
select * from orders where to_char(o_starttime, 'yyyy-mm-dd') = '2019-07-08';
select * from orders where to_char(o_starttime, 'yyyy-mm-dd') = to_char(sysdate, 'yyyy-mm-dd');

insert into orders values(seq_orders_o_id.nextval, 1,1,152, '2019-07-08 18:15',default,'2019-07-08 19:15',5);
insert into orders values(seq_orders_o_id.nextval, 2,5,162, '2019-07-01 11:15',default,'2019-07-01 12:45',4);
insert into orders values(seq_orders_o_id.nextval, 3,3,185, '2019-07-09 09:15',default,'2019-07-09 11:15',3);
insert into orders values(seq_orders_o_id.nextval, 2,34,177, '2019-07-06 10:15',default,'2019-07-06 10:15',8);
insert into orders values(seq_orders_o_id.nextval, 2,34,177, '2019-07-10 10:15',default,'2019-07-10 11:15',4);
insert into orders values(seq_orders_o_id.nextval, 3,3,185, '2019-07-11 09:15',default,'2019-07-09 11:15',3);
insert into orders values(seq_orders_o_id.nextval, 2,34,177, '2019-07-11 10:15',default,'2019-07-06 10:15',8);
insert into orders values(seq_orders_o_id.nextval, 1,34,177, '2019-07-11 10:15',default,'2019-07-10 11:15',4);
insert into orders values(seq_orders_o_id.nextval, 1,34,177, '2019-07-11 10:15',default,null,4);
insert into orders values(seq_orders_o_id.nextval, 1,34,177, '2019-07-11 10:15',default,null,4);

--������ϸ��
create table orderdetail(
       ot_id int primary key, --������ϸ���
       ot_oid int constraint FK_ORDERDETAIL_OT_OID references orders(o_id), --������
       ot_did int constraint FK_ORDERDETAIL_OT_DID references dishes(d_id), --��Ʒ��
       ot_price number(5) not null,
       ot_fcount number(10) not null --��������
)

create sequence seq_orderdetail_ot_id start with 1 increment by 1;
select * from orderdetail;
drop table orderdetail;
drop sequence seq_orderdetail_ot_id;
select ot_did,sum(ot_fcount) from orderdetail group by ot_did;
select d.d_id,d.d_name,c.c_name,nvl((select sum(ot_fcount) from orderdetail od,orders o 
       where od.ot_did = d.d_id and od.ot_oid = o.o_id and o.o_state = 1 group by ot_did),0) salecount,d.d_prices from dishes d,cuisine c 
       where d.d_fid = c.c_id order by salecount desc;
select * from orders o,orderdetail od
       where o.o_id = od.ot_oid and o.o_state = 1
select ot_did, sum(ot_fcount) from orderdetail od,orders o 
       where od.ot_oid = o.o_id and o.o_state = 1 group by ot_did

select sum() from cuisine c group by c.c_id;

select c1.c_name,nvl((select sum(d.d_prices*od.ot_fcount) from orders o,orderdetail od,dishes d,cuisine c
       where o.o_id = od.ot_oid and o.o_state = 1 and to_char(o.o_starttime,'yyyy') = '2019'
       and d.d_id = od.ot_did and c.c_id = d.d_fid and c.c_id = c1.c_id
       group by c.c_id),0) salecount from cuisine c1 order by salecount desc;
       
select sum(d.d_prices*od.ot_fcount) from orders o,orderdetail od,dishes d,cuisine c
       where o.o_id = od.ot_oid and o.o_state = 1 and d.d_id = od.ot_did and c.c_id = d.d_fid
       group by c.c_id
       
select c_id, sum(d_prices*ot_fcount) from cuisine c,dishes d,orderdetail od,orders o 
       where c.c_id = d.d_fid and od.ot_did = d.d_id and o.o_id = od.ot_oid and o.o_state = 1
       group by c_id
       
select c.c_name, nvl((select sum(d_prices*ot_fcount) from cuisine c,dishes d,orderdetail od,orders o 
       where c.c_id = d.d_fid and od.ot_did = d.d_id and o.o_id = od.ot_oid and c.c_id = c1.c_id
       ),0) from 

insert into orderdetail values(seq_orderdetail_ot_id.nextval, 2, 3, 68, 2);
insert into orderdetail values(seq_orderdetail_ot_id.nextval, 3, 2, 38, 1);
insert into orderdetail values(seq_orderdetail_ot_id.nextval, 4, 6, 42, 2);
insert into orderdetail values(seq_orderdetail_ot_id.nextval, 4, 6, 42, 2);
insert into orderdetail values(seq_orderdetail_ot_id.nextval, 45, 6, 42, 2);
insert into orderdetail values(seq_orderdetail_ot_id.nextval, 45, 6, 42, 2);

select o_tid,o_starttime from orders where o_state = 0 and to_char(o_starttime, 'yyyy-mm-dd') = ?;

select r_tid,r_date,t_state from reservation,rtables
       where r_tid = t_id and r_type = 0 and to_char(r_date, 'yyyy-mm-dd') = '2019-07-13';
       
select * from reservation;
















--������ɫ��
create table specialfood(
       s_id int primary key, --��Ʒ���
       s_name varchar2(20) not null, --��Ʒ����
       s_fid int constraint FK_SPECIALFOOD_S_FID references cuisine(c_id), --������ϵ���
       s_prices number(9) not null, --����
       s_remark varchar2(255), --��Ʒ����
       s_photo blob --��ƷͼƬ
)
drop table specialfood;
select * from specialfood;
insert into specialfood select * from dishes d where d.d_id = 1;
commit;
