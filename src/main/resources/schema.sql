create table COIN
(
   id varchar(255) not null,
   symbol varchar(255) not null,
   name varchar(255) not null,
   priceUsd varchar(255) not null,
   explorer varchar(255),
   changePercent24Hr varchar(100),
   marketCapUsd varchar(100),
   maxSupply varchar(100),
   rank varchar(100),
   supply varchar(100),
   volumeUsd24Hr varchar(100),
   vwap24Hr varchar(100),
   primary key(id)
);