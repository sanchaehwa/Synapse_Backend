-- ==============================
-- 1. Admin
-- ==============================
INSERT INTO admin (user_type,
                   store_name,
                   store_type,
                   email,
                   password,
                   created_date,
                   last_modified_date)
VALUES ('ADMIN', '카페 1호점', 'CAFE', 'admin1@example.com', 'qwer1234!', NOW(), NOW()),
       ('ADMIN', '카페 2호점', 'CAFE', 'admin2@example.com', NOW(), NOW(), NOW()),
       ('ADMIN', '레스토랑 1호점', 'GENERAL_RESTAURANT', 'admin3@example.com', 'qwer1234!', NOW(), NOW()),
       ('ADMIN', '레스토랑 2호점', 'GENERAL_RESTAURANT', 'admin4@example.com', 'qwer1234!', NOW(), NOW());

-- ==============================
-- 2. Kiosk Category
-- ==============================
INSERT INTO category (category_name,
                      created_date,
                      last_modified_date)
VALUES ('버거', NOW(), NOW()),
       ('샐러드', NOW(), NOW()),
       ('사이드', NOW(), NOW()),
       ('음료', NOW(), NOW()),
       ('디저트', NOW(), NOW());

-- ==============================
-- 3. Kiosk Menu
-- ==============================
-- ==============================
-- ==============================
INSERT INTO kiosk_menu (menu_name,
                        description,
                        price,
                        category_id,
                        admin_id,
                        image_url,
                        created_date,
                        last_modified_date,
                        is_available,
                        is_deleted)
VALUES ('불고기버거', '달콤 짭조름한 불고기 패티', 7000, 1, 1, 'https://via.placeholder.com/150?text=Bulgogi+Burger', NOW(), NOW(), 0,
        0),
       ('치킨버거', '바삭한 치킨 패티', 7200, 1, 1, 'https://via.placeholder.com/150?text=Chicken+Burger', NOW(), NOW(), 0, 0),
       ('새우버거', '탱글한 새우 패티와 신선한 야채', 7500, 1, 1, 'https://via.placeholder.com/150?text=Shrimp+Burger', NOW(), NOW(), 0,
        0),
       ('베이컨버거', '짭조름한 베이컨과 치즈의 조화', 7700, 1, 1, 'https://via.placeholder.com/150?text=Bacon+Burger', NOW(), NOW(), 0,
        0),
       ('시저샐러드', '신선한 시저드레싱 샐러드', 6500, 2, 1, 'https://via.placeholder.com/150?text=Caesar+Salad', NOW(), NOW(), 0, 0),
       ('그릭샐러드', '건강한 그릭 스타일 샐러드', 6800, 2, 1, 'https://via.placeholder.com/150?text=Greek+Salad', NOW(), NOW(), 0, 0),
       ('과일샐러드', '달콤한 과일과 요거트 드레싱', 6300, 2, 1, 'https://via.placeholder.com/150?text=Fruit+Salad', NOW(), NOW(), 0, 0),
       ('치킨샐러드', '단백질 가득 바삭한 치킨 샐러드', 6900, 2, 1, 'https://via.placeholder.com/150?text=Chicken+Salad', NOW(), NOW(), 0,
        0);
-- ==============================
-- 4. Option Category
-- ==============================
INSERT INTO option_category (name,
                             platform_type,
                             admin_id,
                             kiosk_menu_id,
                             created_date,
                             last_modified_date)
VALUES ('사이즈', 'KIOSK', 1, 1, NOW(), NOW()),
       ('사이드', 'KIOSK', 1, 1, NOW(), NOW()),
       ('사이즈', 'KIOSK', 1, 2, NOW(), NOW()),
       ('사이드', 'KIOSK', 1, 2, NOW(), NOW()),
       ('드레싱', 'KIOSK', 1, 5, NOW(), NOW()),
       ('토핑', 'KIOSK', 1, 5, NOW(), NOW()),
       ('드레싱', 'KIOSK', 1, 6, NOW(), NOW()),
       ('토핑', 'KIOSK', 1, 6, NOW(), NOW());

-- ==============================
-- 5. Option Item
-- ==============================
INSERT INTO menu_option (option_name,
                         price,
                         option_category_id,
                         created_date,
                         last_modified_date)
VALUES ('Small', 0, 1, NOW(), NOW()),
       ('Medium', 500, 1, NOW(), NOW()),
       ('Large', 1000, 1, NOW(), NOW()),
       ('감자튀김', 1000, 2, NOW(), NOW()),
       ('콜라', 500, 2, NOW(), NOW()),
       ('Small', 0, 3, NOW(), NOW()),
       ('Medium', 500, 3, NOW(), NOW()),
       ('Large', 1000, 3, NOW(), NOW()),
       ('감자튀김', 1000, 4, NOW(), NOW()),
       ('콜라', 500, 4, NOW(), NOW()),
       ('시저드레싱', 0, 5, NOW(), NOW()),
       ('랜치드레싱', 0, 5, NOW(), NOW()),
       ('치킨', 1500, 6, NOW(), NOW()),
       ('베이컨', 1200, 6, NOW(), NOW())




