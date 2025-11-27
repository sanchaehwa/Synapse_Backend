INSERT INTO category (id, created_date, last_modified_date, category_name)
VALUES (1, NOW(), NOW(), '커피'),
       (2, NOW(), NOW(), '차'),
       (3, NOW(), NOW(), '한식'),
       (4, NOW(), NOW(), '양식');
INSERT INTO kiosk_menu
(id, created_date, last_modified_date, description, image_url, inventory, is_available, is_deleted, menu_name, price,
 admin_id, category_id)
VALUES (1, NOW(), NOW(), '진한 원두로 만든 따뜻한 아메리카노', '/img/ame1.jpg', 50, 1, 0, '아메리카노', 4500, 1, 1),
       (2, NOW(), NOW(), '부드러운 우유와 에스프레소의 조화', '/img/latte1.jpg', 40, 1, 0, '카페라떼', 5000, 1, 1);
INSERT INTO kiosk_menu
(id, created_date, last_modified_date, description, image_url, inventory, is_available, is_deleted, menu_name, price,
 admin_id, category_id)
VALUES (3, NOW(), NOW(), '은은한 산미의 아메리카노', '/img/ame2.jpg', 60, 1, 0, '아메리카노', 4300, 2, 1),
       (4, NOW(), NOW(), '달콤한 바닐라 시럽이 들어간 라떼', '/img/vanilla2.jpg', 50, 1, 0, '바닐라라떼', 5500, 2, 1);
INSERT INTO kiosk_menu
(id, created_date, last_modified_date, description, image_url, inventory, is_available, is_deleted, menu_name, price,
 admin_id, category_id)
VALUES (5, NOW(), NOW(), '신선한 채소와 고추장을 곁들인 비빔밥', '/img/bibim3.jpg', 30, 1, 0, '비빔밥', 9000, 3, 3),
       (6, NOW(), NOW(), '깊은 맛의 돼지고기 김치찌개', '/img/stew3.jpg', 20, 1, 0, '김치찌개', 8500, 3, 3);
INSERT INTO kiosk_menu
(id, created_date, last_modified_date, description, image_url, inventory, is_available, is_deleted, menu_name, price,
 admin_id, category_id)
VALUES (7, NOW(), NOW(), '크림이 듬뿍 들어간 까르보나라', '/img/pasta4.jpg', 25, 1, 0, '까르보나라', 12000, 4, 4),
       (8, NOW(), NOW(), '촉촉하고 부드러운 스테이크', '/img/steak4.jpg', 15, 1, 0, '스테이크', 25000, 4, 4);
INSERT INTO option_category
(id, created_date, last_modified_date, name, platform_type, admin_id, kiosk_menu_id)
VALUES (1, NOW(), NOW(), '사이즈 선택', 'KIOSK', 1, 1),
       (2, NOW(), NOW(), '샷 추가', 'KIOSK', 2, 3),
       (3, NOW(), NOW(), '매운맛 단계', 'KIOSK', 3, 6),
       (4, NOW(), NOW(), '익힘 정도', 'KIOSK', 4, 8);
INSERT INTO menu_option
(id, created_date, last_modified_date, option_name, price, option_category_id)
VALUES (1, NOW(), NOW(), '라지 사이즈', 1000, 1),
       (2, NOW(), NOW(), '샷 1회 추가', 500, 2),
       (3, NOW(), NOW(), '더 매운맛', 0, 3),
       (4, NOW(), NOW(), '미디엄 웰던', 0, 4);
ALTER TABLE order_item
    MODIFY order_id BIGINT NULL;
