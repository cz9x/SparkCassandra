原始数据
建表语句
drop keyspace nirvana;
create keyspace nirvana with replication = {'class':'SimpleStrategy', 'replication_factor':1};
use nirvana;

create type event(event_id text, time timestamp, extra text);
create type history(page text, enter_time timestamp, exit_time timestamp);

create table app_profile(
    id uuid primary key,
    app_key text,
    device_id text,
    device_type text,
    device_os text,
    device_model text,
    device_brand text,
    device_res text,
    app_version text,
    app_name text,
    app_source text,
    app_channel text,
    app_network text,
    app_gps text,
    user_id text,
    user_name text,
    app_id text,
    ctime timestamp,
    events set<frozen<event>>,
    histories set<frozen<history>>
);

插入测试数据
insert into nirvana.app_profile
(id,app_key,device_id,device_type,device_os,device_model,device_brand,device_res,app_version,app_name,app_source,app_channel,app_network,app_gps,user_id,user_name,app_id,ctime,events,histories)
values
(1,1,001,ios,)


select a.device_id,a.app_gps,a.app_key,a.app_name,a.app_network
      ,a.app_source,a.app_version,a.device_model,a.device_os,a.device_type,a.time
from (
select device_id,app_gps,app_key,app_name,app_network,app_source,app_version,device_model,device_os,device_type,time
       row_number() over(partition by device_id order by time desc) rn
from app_profile
) a left join new_user b on a.device_id=b.device_id
where a.rn=1 and b.device_id is null



device_id,app_gps,app_key,app_name,app_network,app_source,app_version,device_model,device_os,device_type,time










app_source,app_name,app_key,app_version,device_type,device_model,device_os,device_id,time