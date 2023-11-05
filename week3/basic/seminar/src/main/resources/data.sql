INSERT INTO MEMBER(ID, NICKNAME, NAME, AGE, PART, GENERATION)
VALUES (1, '유난', '최윤한', 20, 'SERVER', 33),
       (2, 'DDD', '정준서', 24, 'SERVER', 33),
       (3, '멜로니', '김해린', 26, 'PLAN', 33),
       (4, '티벳여우', '이태희', 25, 'ANDROID', 33);


INSERT INTO POST(POST_ID, TITLE, CONTENT, MEMBER_ID)
VALUES
    (1, '안녕하세요 제목1', '안녕하세요 내용1', 1),
    (2, '안녕하세요 제목2', '안녕하세요 내용2', 1),
    (3, '안녕하세요 제목3', '안녕하세요 내용3', 1),
    (4, '반갑습니다 제목4', '반갑습니다', 2),
    (5, '안녕하세요 제목5', '안녕하세요', 3);