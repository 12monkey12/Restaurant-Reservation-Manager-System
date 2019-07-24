create table rusers(
       u_id int primary key, --用户编号
       u_account varchar2(20) unique not null, --用户账号
       u_password varchar2(255) not null, --用户密码
       u_name varchar2(20) unique not null, --用户姓名
       u_sex varchar2(2) constraint CK_RUSERS_U_SEX check(u_sex='男' or u_sex='女'), --性别
       u_tel number(20) --电话号码
)
create sequence sqe_rusers_u_id start with 1 increment by 1
drop table rusers;
drop sequence sqe_rusers_u_id;

insert into rusers values(sqe_rusers_u_id.nextval,'link123','123','张三','男',15555555555);
insert into rusers values(sqe_rusers_u_id.nextval,'link124','456','晓梦','女',16666666666);
insert into rusers values(sqe_rusers_u_id.nextval,'test','123','华纳','男',1777777777);
select * from rusers
delete rusers where u_account = '11';

--管理员表
create table radmin(
       a_id int primary key, --管理员编号
       a_account varchar2(20) unique not null, --管理员账号
       a_password varchar2(20) not null, --管理员密码
       a_name varchar2(20) unique not null, --管理员姓名
       a_sex varchar2(2) not null
)
drop table radmin
drop sequence sqe_radmin_a_id
create sequence sqe_radmin_a_id start with 1 increment by 1

insert into radmin values(sqe_radmin_a_id.nextval,'admin1','123456','李家耀','男');
insert into radmin values(sqe_radmin_a_id.nextval,'admin2','123456','刘帅','男');
insert into radmin values(sqe_radmin_a_id.nextval,'admin3','123456','李俊','男');
select * from radmin
commit;

--餐桌表
create table rtables(
       t_id int primary key, --桌子号
       t_type varchar2(9) not null, --桌子类型
       t_state number(2) default '1' --餐桌状态 0表示已预约 1表示已使用  2表示可用

)
select count(*) from rtables;
select count(*) from rtables where t_state = 1
delete from rtables where t_type = '八人桌';
select * from rtables

create sequence seq_rtables_t_id start with 1 increment by 1;
drop sequence seq_rtables_t_id;
drop table rtables;
commit;

insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '四人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '八人桌', 2);
insert into rtables values ( seq_rtables_t_id.nextval, '八人桌', 2);
insert into rtables values ( seq_rtables_t_id.nextval, '八人桌', 2);
insert into rtables values ( seq_rtables_t_id.nextval, '八人桌', 2);
insert into rtables values ( seq_rtables_t_id.nextval, '八人桌', 2);
insert into rtables values ( seq_rtables_t_id.nextval, '八人桌', 2);
insert into rtables values ( seq_rtables_t_id.nextval, '八人桌', 2);
insert into rtables values ( seq_rtables_t_id.nextval, '八人桌', 2);
insert into rtables values ( seq_rtables_t_id.nextval, '八人桌', 2);
insert into rtables values ( seq_rtables_t_id.nextval, '八人桌', 2);
insert into rtables values ( seq_rtables_t_id.nextval, '十二人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '十二人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '十二人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '十二人桌', default);
insert into rtables values ( seq_rtables_t_id.nextval, '十二人桌', default);

--预约表
create table reservation(
       --预约号
       r_id int primary key,
       r_tid int constraint FK_RESERVATION_R_TID references rtables(t_id), --桌子号
       r_uid int constraint FK_RESERVATION_R_UID references rusers(u_id), -- 客户号
       r_date date, --预约日期
       --r_type number(2) default '0', --餐桌状态 0表示未预约
       --预约的时间段
       --r_time number(3) -- 0代表上午 1代表中午，2代表下午，3代表晚上
       r_type number(2) default '0', --r订单状态 0：未赴约  1：已赴约   2：违约
       r_note varchar2(200)  --备注      
)

insert into reservation values(seq_reservation_r_id.nextval, 1, 1,'2019-07-08 18',default,'暂无' );
insert into reservation values(seq_reservation_r_id.nextval, 2, 3,'2019-07-01 11',default,'暂无' );
insert into reservation values(seq_reservation_r_id.nextval, 3, 2,'2019-07-09 09',default,'暂无' );
insert into reservation values(seq_reservation_r_id.nextval, 4, 1,'2019-07-06 10',default,'暂无' );
insert into reservation values(seq_reservation_r_id.nextval, 4, 1,'2019-07-11 10',default,'暂无' );
insert into reservation values(seq_reservation_r_id.nextval, 4, 2,'2019-07-11 10',default,'暂无' );
insert into reservation values(seq_reservation_r_id.nextval, 4, 3,'2019-07-11 10',default,'暂无' );

create sequence seq_reservation_r_id start with 1 increment by 1;
drop sequence seq_reservation_r_id;
select * from reservation where r_uid = 3;
drop table reservation;
commit;



--订单表
create table orders(
       o_id int primary key, --订单编号
       o_uid int constraint FK_ORDERS_O_UID references rusers(u_id), --用户编号
       o_tid int constraint FK_ORDERS_O_TID references rtables(t_id),--餐桌号
       o_totalPrice number(10) not null, --总金额
       o_starttime date, --下单时间
       o_state number(2) default '0',  --订单状态，0代表未结账  1：已结账
       o_endtime date, --结账时间
       o_number int --就餐人数
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

--订单明细表
create table orderdetail(
       ot_id int primary key, --订单明细编号
       ot_oid int constraint FK_ORDERDETAIL_OT_OID references orders(o_id), --订单号
       ot_did int constraint FK_ORDERDETAIL_OT_DID references dishes(d_id), --菜品号
       ot_price number(5) not null,
       ot_fcount number(10) not null --订餐数量
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
















--今日特色菜
create table specialfood(
       s_id int primary key, --菜品编号
       s_name varchar2(20) not null, --菜品名称
       s_fid int constraint FK_SPECIALFOOD_S_FID references cuisine(c_id), --所属菜系编号
       s_prices number(9) not null, --单价
       s_remark varchar2(255), --菜品描述
       s_photo blob --菜品图片
)
drop table specialfood;
select * from specialfood;
insert into specialfood select * from dishes d where d.d_id = 1;
commit;
