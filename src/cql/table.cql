create keyspace analysis with replication = {'class':'SimpleStrategy', 'replication_factor':1};


create table analysis.bus_data(
    ID INT primary key
   ,CARDID TEXT
   ,LINEID INT
   ,BEGINTIME BIGINT
   ,ENDTIME BIGINT
   ,STATIONFROM INT
   ,STATIONTO INT
   ,BUSID INT
);

