select * from Cards
where cardLayout != 'normal';

select * from UserCard
order by 1;

select count(distinct id), appUserId from UserCard group by appUserId;

select count(id) from UserCard;

select
    uc.id as Card_ID,
    count(uc.id) as Card_Count,
    c.cardName
#     c.cardSetName,
#     uc.isFoil,
#     c.cardSetLetters,
#     au.userName
from UserCard uc
         join Cards c on c.id = uc.id
#     join AppUser au on au.appUserId = uc.appUserId
# where
#     au.userName = 'tkm3d1a'
# AND
#     c.cardTypeLine not like ('%Land%')
group by
    c.cardName
#     c.cardSetName,
#     au.userName
# HAVING Card_ID > 1
ORDER BY
#     3,
2 desc
;