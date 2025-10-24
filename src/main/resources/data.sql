-- ==============================
-- 1. Admin
-- ==============================

INSERT INTO admin (
    user_type,
    store_name,
    store_type,
    email,
    password,
    created_date,
    last_modified_date
) VALUES
      ('ADMIN', '카페 1호점', 'CAFE', 'admin1@example.com', 'qwer1234!', NOW(), NOW()),
      ('ADMIN', '카페 2호점', 'CAFE', 'admin2@example.com', 'qwer1234!', NOW(), NOW()),
      ('ADMIN', '레스토랑 1호점', 'GENERAL_RESTAURANT', 'admin3@example.com', 'qwer1234!', NOW(), NOW()),
      ('ADMIN', '레스토랑 2호점', 'GENERAL_RESTAURANT', 'admin4@example.com', 'qwer1234!', NOW(), NOW());

-- ==============================
-- 4. Option Category
-- ==============================
INSERT INTO option_category (
    name,
    platform_type,
    admin_id,
    qrcode_menu_id,
    kiosk_menu_id,
    created_date,
    last_modified_date
) VALUES
      ('사이즈', 'QRCODE', 1, 1, NULL, NOW(), NOW()),
      ('사이드', 'QRCODE', 1, 1, NULL, NOW(), NOW()),
      ('사이즈', 'QRCODE', 1, 3, NULL, NOW(), NOW()),
      ('사이드', 'QRCODE', 1, 3, NULL, NOW(), NOW()),
      ('드레싱', 'QRCODE', 1, 5, NULL, NOW(), NOW()),
      ('토핑', 'QRCODE', 1, 5, NULL, NOW(), NOW()),
      ('드레싱', 'QRCODE', 1, 7, NULL, NOW(), NOW()),
      ('토핑', 'QRCODE', 1, 7, NULL, NOW(), NOW());


-- ==============================
-- 5. Option Item
-- ==============================
INSERT INTO option_item (
    option_item_name,
    price,
    option_category_id,
    created_date,
    last_modified_date
) VALUES
      ('Small', 0, 1, NOW(), NOW()),
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
      ('베이컨', 1200, 6, NOW(), NOW()),
      ('올리브드레싱', 0, 7, NOW(), NOW()),
      ('발사믹드레싱', 0, 7, NOW(), NOW()),
      ('치즈', 800, 8, NOW(), NOW()),
      ('견과류', 1000, 8, NOW(), NOW());

-- ==============================
-- 2. QRCode Menu Category
-- ==============================
INSERT INTO qrcode_menu_category (
    admin_id,
    category_name,
    created_date,
    last_modified_date

) VALUES
      (1,'버거', NOW(), NOW()),
      (1,'샐러드', NOW(), NOW());

-- ==============================
-- 3. QRCode Menu
-- ==============================
INSERT INTO qrcode_menu (
    menu_name,
    menu_description,
    img_url,
    qr_menu_category_id,
    admin_id,
    menu_price,
    created_date,
    last_modified_date
) VALUES
      ('불고기버거', '달콤 짭조름한 불고기 패티', 'https://via.placeholder.com/150?text=Menu+Image', 1, 1, 7000, NOW(), NOW()),
      ('치킨버거', '바삭한 치킨 패티', 'https://via.placeholder.com/150?text=Menu+Image', 1, 1, 7200, NOW(), NOW()),
      ('새우버거', '탱글한 새우 패티와 신선한 야채', 'https://via.placeholder.com/150?text=Menu+Image', 1, 1, 7500, NOW(), NOW()),
      ('베이컨버거', '짭조름한 베이컨과 치즈의 조화','https://via.placeholder.com/150?text=Menu+Image' , 1, 1, 7700, NOW(), NOW()),
      ('시저샐러드', '신선한 시저드레싱 샐러드', 'https://via.placeholder.com/150?text=Menu+Image', 2, 1, 6500, NOW(), NOW()),
      ('그릭샐러드', '건강한 그릭 스타일 샐러드', 'https://via.placeholder.com/150?text=Menu+Image', 2, 1, 6800, NOW(), NOW()),
      ('과일샐러드', '달콤한 과일과 요거트 드레싱', 'https://via.placeholder.com/150?text=Menu+Image', 2, 1, 6300, NOW(), NOW()),
      ('치킨샐러드', '단백질 가득 바삭한 치킨 샐러드', 'https://via.placeholder.com/150?text=Menu+Image', 2, 1, 6900, NOW(), NOW());

SELECT * FROM admin;
SELECT * FROM option_item;
SELECT * FROM qrcode_menu;
