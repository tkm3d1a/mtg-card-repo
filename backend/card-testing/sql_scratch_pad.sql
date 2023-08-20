-- SQL Scratch pad for testing setting up different items needed for data transforms

-- Table needed for consolidating data
CREATE TABLE UserCardConsolidated(
     consolidatedId INT NOT NULL AUTO_INCREMENT,
     id VARCHAR(36) NOT NULL,
     appUserId VARCHAR(36) NOT NULL ,
     count INT,
     cardName VARCHAR(255),
     CONSTRAINT PK_tableId PRIMARY KEY (consolidatedId),
     CONSTRAINT FK_cardsId FOREIGN KEY (id) REFERENCES Cards(id),
     CONSTRAINT FK_userId FOREIGN KEY (appUserId) REFERENCES AppUser(appUserId)
);

-- select for getting unique card names and their counts
select
    count(uc.id) as Card_ID,
    c.cardName,
#     c.cardSetName,
#     uc.isFoil,
#     c.cardSetLetters,
    au.userName
from UserCard uc
         join Cards c on c.id = uc.id
         join AppUser au on au.appUserId = uc.appUserId
where
        au.userName = 'tkm3d1a'
# AND
#     c.cardTypeLine not like ('%Land%')
group by
    c.cardName,
#     c.cardSetName,
    au.userName
# HAVING Card_ID > 1
ORDER BY
#     3,
1 desc
;