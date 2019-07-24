create table cuisine(
       c_id int primary key, --²ËÏµ±àºÅ
       c_name varchar2(20) unique not null --²ËÏµÃû³Æ
)
create sequence seq_cuisine_c_id start with 1 increment by 1;
select * from cuisine
drop table cuisine
drop sequence seq_cuisine_c_id

create table dishes(
       d_id int primary key, --²ËÆ·±àºÅ
       d_name varchar2(20) not null, --²ËÆ·Ãû³Æ
       d_fid int constraint FK_DISHES_D_FID references cuisine(c_id), --ËùÊô²ËÏµ±àºÅ
       d_prices number(9) not null, --µ¥¼Û
       d_remark varchar2(255), --²ËÆ·ÃèÊö
       d_photo blob --²ËÆ·Í¼Æ¬
);
select * from dishes
create sequence seq_dishes_d_id start with 1 increment by 1;
drop sequence seq_dishes_d_id
select d_id,d_name,c_name,d_prices from cuisine c,dishes d where c.c_id = d.d_fid;

select d_id,d_name,c_name,d_prices from cuisine c,dishes d where c.c_id = d.d_fid order by d_id

insert into cuisine values (seq_cuisine_c_id.nextval, 'Ïæ²Ë');
insert into cuisine values (seq_cuisine_c_id.nextval, 'ÔÁ²Ë');
insert into cuisine values (seq_cuisine_c_id.nextval, '´¨²Ë');
insert into cuisine values (seq_cuisine_c_id.nextval, 'Â³²Ë');
insert into cuisine values (seq_cuisine_c_id.nextval, 'ËÕ²Ë');
insert into cuisine values (seq_cuisine_c_id.nextval, 'Ãö²Ë');
insert into cuisine values (seq_cuisine_c_id.nextval, 'Õã²Ë');
insert into cuisine values (seq_cuisine_c_id.nextval, '»Õ²Ë');
insert into cuisine values (seq_cuisine_c_id.nextval, '¾ÆË®');

commit;
select * from dishes order by d_id;
insert into dishes values (seq_dishes_d_id.nextval,'À°Èâ³´ÖíÑªÍè×Ó', 1, 38, 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¸í´ñÌÀ', 1, 38, 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Èı½·È¥¹Ç¼¦×¦', 1,68 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'»¢Æ¤¼â½·', 1,22 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¶ç½·ÓãÍ·°ëÌõ', 1,48 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'»¢Æ¤¼â½·', 1,22 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¼â½·À°Èâ·¹', 1, 26 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Ã·²Ë¿ÛÈâ', 1,18 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÃÀÎ¶¿¾Ïº', 1,78 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Æ¤µ°°è¶¹¸¯', 1,22 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Æ¤µ°ÊİÈâÖà', 1,18 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Æ¡¾ÆÑ¼', 1,38 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Çà²ËÊİÈâÖà', 1,18 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÊÖËº°ü²Ë', 1,16 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Ëá²ËĞ¡Íã¶¹', 1,28 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Ëá²ËÓã', 1,68 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÍâÆÅ²Ë»Ø¹øÈâ', 1,32 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Ïã´à»¨ÉúÃ×', 1,18 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÏãÀ±Óã¿é', 1,18 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Ğ¡³´Èâ', 1,26 , 'ÔİÎŞ', null);
--
insert into dishes values (seq_dishes_d_id.nextval,'Ğ¡Óã°®Ğ¡Ïº', 1,36 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÑÌËñ³´À°Èâ', 1,26 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÓãÏãÈâË¿', 1,18 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'³¤É³Èı½·²İÓã', 1,78 , 'ÔİÎŞ', null);

insert into dishes values (seq_dishes_d_id.nextval,'ÓñÃ×ÅÅ¹ÇÌÀ', 2,18 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÇÑ×Ó³´¶¹½Ç', 2,18 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'´´ĞÂ¶¹Æ¤¼¦', 2,30 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¶«İ¸ÉÕ¶ì', 2, 49, 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¹ÄÖ­ÕôÓãÍ·', 2,30 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ºÉ¶¹Ä¾¶ú³´»´É½', 2,20 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¿Í¼ÒÊÖËºÏÌ¼¦', 2,30 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'É³½ªÖíÊÖ', 2,20 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'°×ÇĞ×ßµØ¼¦', 2,49 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'°××ÆÇï¿û', 2,26 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ºÓÔ´ÕôÃ×·Û', 2,23 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'»Ø¹øÈâ', 2,36 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¼Ò³£²ğ¹ÇÈâ', 2,20 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¼åÉ¿Í¼Ò¶¹¸¯', 2,20 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¾Â²Ë³´µ°', 2,20 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¿Í¼Ò³´Èı±¦', 2,28 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¿Í¼ÒÖíÈâÌÀ', 2, 16, 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÇåÔ¶¼¦Öà', 2,23 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Ëá²ËÓã', 2,30 , 'ÔİÎŞ', null);
--
insert into dishes values (seq_dishes_d_id.nextval,'ÎåÏãÅ´Ã×¹Ç', 2,48 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÏÌ»ÆÇÑ×Ó±¤', 2,20 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÓãÍ·¶¹¸¯ÌÀ', 2,30 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÓñÃ×ËÉ×ÓÁ£', 2,20 , 'ÔİÎŞ', null);

insert into dishes values (seq_dishes_d_id.nextval,'×ÎÈ»´à¹Ç', 3,32 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'´¨±±Á¹·Û', 3,18 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'°×²Ë¶¹¸¯Íè×ÓÌÀ', 3,28 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'°×²ËìÀ¶¹¸¯', 3,20, 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'²ö×ìÍÜ', 3,52, 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'µØÈıÏÊ', 3,18 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¶¬¹ÏÅÅ¹ÇÌÀ', 3,26, 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'·òÆŞ·ÎÆ¬', 3,38 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¸¯Öñ', 3,18 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¹¬±£¼¦¶¡', 3,32 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¼â½·ÍÁ¶¹Ë¿', 3,19 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'½´°åÑ¼', 3,32 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'À¶İ®É½Ò©', 3,28 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÂéÆÅ¶¹¸¯', 3,18 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Ã«ÑªÍú', 3,52 , 'ÔİÎŞ', null);
--
insert into dishes values (seq_dishes_d_id.nextval,'ÅÅ¹Ç¼¦', 3, 66, 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Åİ½·¿¾Óã', 3,108 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÈıÏÊÌÀ', 3,20 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Ë®ÖóÅ£Èâ', 3,48 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Ë®ÖóÈâÆ¬', 3,40 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ËÄ´¨Ïã³¦', 3,32 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Î÷ºşÅ£Èâ¸ş', 3,22 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Ğ¡³´ØüÏØÏã¸É', 3,38 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÖØÇì¿ÚË®¼¦', 3,28 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÖØÇìÀ±×Ó¼¦', 3,52 , 'ÔİÎŞ', null);

insert into dishes values (seq_dishes_d_id.nextval,'Ò»Æ·¶¹¸¯', 4,22 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'´ĞÉÕº£²Î', 4,88 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÈıË¿Óã³á', 4,128 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'°×°ÇËÄ±¦', 4,56 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÌÇ´×»ÆºÓÀğÓã', 4,98 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¾Å×ª´ó³¦', 4,36 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÓÍ±¬Ë«´à', 4,26 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'°ÇÔ­¿Ç±«Óã', 4,68 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'´×½·Óã', 4,48 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÔãìÖÓãÆ¬', 4,36 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÕĞÔ¶ÕôÍè', 4,22 , 'ÔİÎŞ', null);
--
insert into dishes values (seq_dishes_d_id.nextval,'ÎÚÔÆÍĞÔÂ', 4,28 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÓÍÆÃ¶¹Üğ', 4,36, 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÄÌÌÀöêÓã', 4,48, 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¹øËú»ÆÓã', 4,68 , 'ÔİÎŞ', null);

insert into dishes values (seq_dishes_d_id.nextval,'¿¾·½', 5,36 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Ë®¾§ëÈÌã', 5,36 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÇåìÀĞ··ÛÊ¨×ÓÍ·', 5,99 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'½ğÁêÍè×Ó', 5,28 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'»ÆÄàìĞ¼¦', 5,48 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÑÎË®Ñ¼', 5,86 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'½ğÏã±ı', 5,48 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¼¦ÌÀÖó¸ÉË¿', 5,18 , 'ÔİÎŞ', null);
--
insert into dishes values (seq_dishes_d_id.nextval,'·ïÎ²Ïº', 5,58 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÎŞÎıÈâ', 5,36 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Â½¸å¼ö½´ÖíÍ·Èâ', 5,86 , 'ÔİÎŞ', null);

insert into dishes values (seq_dishes_d_id.nextval,'·ğÌøÇ½', 6,168 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¸£ÖİÓãÍè', 6,38 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¶¦±ßºı', 6,28 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÕÄÖİÂ±Ãæ', 6,18 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÆÎÌïÂ±Ãæ', 6,18 , 'ÔİÎŞ', null);
--
insert into dishes values (seq_dishes_d_id.nextval,'º£òÃ¼å', 6,22 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'É³ÏØ°èÃæ', 6,18 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÏÃÃÅÉ³²èÃæ', 6,18 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÃöÄÏÏÌ·¹', 6,28 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ĞË»¯Ã×·Û', 6,18 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÀóÖ¦Èâ', 6,28 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'°×Ñ©¼¦', 6,48 , 'ÔİÎŞ', null);

insert into dishes values (seq_dishes_d_id.nextval,'Î÷ºş´×Óã', 7,68 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¶«ÆÂÈâ', 7,28 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÈüĞ·¸ş', 7,38 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¼ÒÏçÄÏÈâ', 7,28 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¸ÉÕ¨ÏìÁå', 7,36 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ºÉÒ¶·ÛÕôÈâ', 7,48 , 'ÔİÎŞ', null);
--
insert into dishes values (seq_dishes_d_id.nextval,'¸òòÛ»ÆÓã¸ş', 7,66 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'½Ğ»¯Í¯¼¦', 7,68 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÏãËÖìËÈâ', 7,66 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Ë¿¹ÏÂ±Õô»ÆÓã', 7,108, 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'±ùÌÇ¼×Óã', 7,68 , 'ÔİÎŞ', null);

insert into dishes values (seq_dishes_d_id.nextval,'»ğ¿¾¹ğÓã', 8,68 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'·½À°Óã', 8,49 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ëçÏÊ÷¬Óã', 8,66 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÏÊ²Ëï¾', 8,28 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÑîÃ·Ô²×Ó', 8,36 , 'ÔİÎŞ', null);
--
insert into dishes values (seq_dishes_d_id.nextval,'ÇåìÀÂíÌã±î', 8,50 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'»ğìÒ¹û×ÓÀê', 8,88, 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÇåìÀÊ¯¼¦', 8,98 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'»¢Æ¤Ã«¶¹¸¯', 8,28 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÓÍËÖÉÕ±ı', 8,16 , 'ÔİÎŞ', null);

insert into dishes values (seq_dishes_d_id.nextval,'35¡ãĞ¡¾¢¾Æ', 9,18 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'45¡ãĞ¡àO¾Æ', 9,20 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'500ºÁÉı°ÙÍş(¹Ş)', 9,10 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'500ºÁÉı¹ş¶û±õÆ¡¾Æ(¹Ş', 9,7 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'°ÙÍş', 9,11 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'´¿Éú', 9, 10, 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'´óÆ¿¿ÉÀÖ', 9, 8, 'ÔİÎŞ', null);
--
insert into dishes values (seq_dishes_d_id.nextval,'´óÆ¿Ñ©±Ì', 9, 8, 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¹Ş×°¿ÉÀÖ', 9, 4, 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¹Ş×°ÍõÀÏ¼ª',9, 4, 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'¹Ş×°Ñ©±Ì', 9,4 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'600ºÁÉı¹ş¶û±õÆ¡¾Æ', 9,8 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'Å©·òÉ½Èª', 9, 3, 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÌìµØÒ»ºÅÆ»¹û´×', 9,24 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'ÌìµØÒ»ºÅÆ»¹û´×24', 9,8 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'500ºÁÉıÑ©»¨½ğ±ê', 9,10 , 'ÔİÎŞ', null);
insert into dishes values (seq_dishes_d_id.nextval,'500ºÁÉıÑà¾©Æ¡¾Æ', 9,5 , 'ÔİÎŞ', null);

alter session set nls_date_format='yyyy-mm-dd hh:mi:ss';

commit;
delete from dishes where D_NAME = 'ÓÍËÖÉÕ';

delete from dishes where D_fid = 9;
--Ïæ²Ë¡¢ÔÁ²Ë¡¢´¨²Ë¡¢Â³²Ë¡¢ËÕ²Ë¡¢Ãö²Ë¡¢Õã²Ë¡¢»Õ²Ë


