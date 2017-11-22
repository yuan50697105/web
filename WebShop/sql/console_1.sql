SELECT last_insert_id() AS id;
/*
  用户
*/
INSERT INTO user(userid, password, name, sex, birthday, email, phone, address, province, city, activeCode)  VALUES (?,?,?,?,?,?,?,?,?,?,?);
DELETE FROM user WHERE id=?;
UPDATE user SET password=?,sex=?,birthday=?,email=?,phone=?,address=?,province=?,city=? WHERE id=?;
  SELECT * FROM user WHERE id=?;
SELECT * FROM user;
SELECT * FROM user WHERE userid LIKE concat('%',?,'%');
SELECT * FROM user WHERE userid=? AND password=?;
/*
  商品类型
*/
INSERT INTO goodstype(name, detail,fatherid) VALUES (?,?,?);
DELETE FROM goodstype WHERE id=?;
UPDATE goodstype SET name=?,detail=?,fatherid=? WHERE id=?;
SELECT * FROM goodstype;
SELECT * FROM goodstype WHERE name LIKE concat('%',?,'%');
/*
  商品
*/
INSERT INTO goods(title, brandName, detail, image, type, price, favoritePrice, repertory) VALUES (?,?,?,?,?,?,?,?);
DELETE FROM goods WHERE id=?;
UPDATE goods SET title=?,brandName=?,detail=?,image=?,type=?,price=?,favoritePrice=?,repertory=? WHERE id=?;
SELECT * FROM goods WHERE id=?;
SELECT * FROM goods;
  SELECT * FROM goods   WHERE
    title LIKE concat() OR
    brandName LIKE concat() OR
    type IN (SELECT id FROM goodstype WHERE name LIKE concat() AND fatherid=0) OR
    type IN (SELECT id FROM goodstype WHERE fatherid <> 0 AND fatherid IN (SELECT fatherid FROM goodstype WHERE name LIKE concat()));
/*

*/
INSERT INTO picture (imageURL, requestURL) VALUES (?,?);
DELETE FROM picture WHERE id=?;
UPDATE picture SET imageURL=?,requestURL=? WHERE id=?;
SELECT * FROM picture WHERE id=?;
  SELECT * FROM picture;

/*

*/

INSERT INTO timelimit (goodsid, limitDate, isEnd) VALUES (?,?,?);
DELETE FROM timelimit WHERE id=?;
UPDATE timelimit SET goodsid=?,limitDate=?,isEnd=? WHERE id=?;
SELECT * FROM timelimit WHERE goodsid IN (SELECT * FROM goods WHERE title LIKE concat());
/*

*/
INSERT INTO `order`(orderid, userid, totalPrice) VALUES (?,?,?);
DELETE FROM `order` WHERE id=?;
SELECT * FROM `order` WHERE id=?;
SELECT * FROM `order` WHERE userid IN (SELECT id FROM user WHERE userid LIKE concat()) OR orderid LIKE concat();
UPDATE `order` SET alipay=1 WHERE id=?;
UPDATE `order` SET sendTime=now(),finshStatus=? WHERE id=?;
INSERT INTO orderitem(orderid,goodsid,sum) VALUES (?,?,?);
DELETE FROM orderitem WHERE id=?;
SELECT * FROM orderitem WHERE id=?;
SELECT * FROM orderitem WHERE orderid=?;
