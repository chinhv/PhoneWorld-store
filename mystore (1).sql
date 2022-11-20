-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 18, 2022 lúc 06:01 PM
-- Phiên bản máy phục vụ: 10.4.25-MariaDB
-- Phiên bản PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `mystore`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart_item_tb`
--

CREATE TABLE `cart_item_tb` (
  `id` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `cart_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `cart_item_tb`
--

INSERT INTO `cart_item_tb` (`id`, `count`, `cart_id`, `product_id`) VALUES
(1, 1, 1, 2),
(3, 1, 1, 5),
(4, 6, 1, 30),
(5, 2, 1, 27),
(6, 2, 1, 8);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart_tb`
--

CREATE TABLE `cart_tb` (
  `id` int(11) NOT NULL,
  `total_payment` bigint(20) NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `cart_tb`
--

INSERT INTO `cart_tb` (`id`, `total_payment`, `user_id`) VALUES
(1, 0, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category_tb`
--

CREATE TABLE `category_tb` (
  `id` int(11) NOT NULL,
  `category_name` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `category_tb`
--

INSERT INTO `category_tb` (`id`, `category_name`) VALUES
(1, 'Điện thoại'),
(2, 'Máy tính bảng'),
(3, 'Phụ kiện');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `comment_tb`
--

CREATE TABLE `comment_tb` (
  `id` int(11) NOT NULL,
  `comment` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `manufacturer_tb`
--

CREATE TABLE `manufacturer_tb` (
  `id` int(11) NOT NULL,
  `manufacturer_name` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `manufacturer_tb`
--

INSERT INTO `manufacturer_tb` (`id`, `manufacturer_name`) VALUES
(1, 'Apple'),
(2, 'Samsung'),
(3, 'Oppo'),
(4, 'Xiaomi'),
(5, 'Vivo'),
(6, 'Huawei');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_tb`
--

CREATE TABLE `order_tb` (
  `id` int(11) NOT NULL,
  `acception` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `note` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_tb`
--

CREATE TABLE `product_tb` (
  `id` int(11) NOT NULL,
  `battery` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `cpu` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `img` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `ram` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `manufacturer_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `product_tb`
--

INSERT INTO `product_tb` (`id`, `battery`, `cpu`, `description`, `img`, `name`, `price`, `ram`, `category_id`, `manufacturer_id`) VALUES
(1, '2018', 'Apple A15 Bionic', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/iphone-se-white-600x600.jpg', 'iPhone SE (2022)', 10490000, '4', 1, 1),
(2, '4323', 'Apple A15 Bionic', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/iphone-13-pro-max-xanh-la-thumb-1-600x600.jpg', 'iPhone 13 Pro Max', 28490000, '6', 1, 1),
(3, '2438', 'Apple A15 Bionic', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/iphone-13-mini-blue-2-600x600.jpg', ' iPhone 13 mini', 17490000, '4', 1, 1),
(4, '4352', 'Apple A16 Bionic', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/iphone-14-pro-max-bac-thumb-600x600.jpg', 'iPhone 14 Pro Max', 33590000, '6', 1, 1),
(5, '4500', 'Snapdragon 8 Gen 1', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/samsung-galaxy-s22-plus-090222-103121-600x600.jpg', 'Samsung Galaxy S22+ 5G', 19990000, '8', 1, 2),
(6, '4500', 'Exynos 2100', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/samsung-galaxy-s21-fe-trang-600x600.jpg', 'Samsung Galaxy S21 FE 5G', 12990000, '8', 1, 2),
(7, '5000', 'Exynos 1280', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/samsung-galaxy-m33-5g-1-600x600.jpg', 'Samsung Galaxy M33 5G', 7690000, '6', 1, 2),
(8, '5000', 'MediaTek MT6769V', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/samsung-galaxy-a22-4g-mint-1-600x600.jpg', 'Samsung Galaxy A22', 5290000, '6', 1, 2),
(9, '5000', 'MediaTek Helio G35', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/oppo-a16-silver-600x600.jpg', 'OPPO A16', 3190000, '3', 1, 3),
(10, '5000', 'Snapdragon 680', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/oppo-a77s-den-thumb-2-600x600.jpg', 'OPPO A77s', 5990000, '8', 1, 3),
(11, '5000', 'Snapdragon 8 Gen 1', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/oppo-find-x5-pro-trang-thumb-600x600.jpg', 'OPPO Find X5 Pro 5G', 26990000, '12', 1, 3),
(12, '4500', 'MediaTek Dimensity 8100', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/oppo-reno8-pro-thumb-xanh-600x600.jpg', 'OPPO Reno8 series', 18490000, '12', 1, 3),
(13, '5000', 'MediaTek Dimensity 810', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/xiaomi-redmi-note-11s-5g-120422-104710-600x600.jpg', 'Xiaomi Redmi Note 11S series', 6190000, '6', 1, 4),
(14, '5020', 'Snapdragon 732G', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/xiaomi-redmi-note-10-pro-thumb-xam-600x600-200x200.jpg', 'Xiaomi Redmi Note 10 Pro', 7090000, '6', 1, 4),
(15, '5020', 'MediaTek Helio G25', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/xiaomi-redmi-9a-grey-600x600-600x600.jpg', 'Xiaomi Redmi 9A', 2290000, '2', 1, 4),
(16, '6000', 'JLQ JR510', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/xiaomi-poco-c40-thumb-vang-600x600.jpg', 'POCO C40', 2990000, '2', 1, 4),
(17, '5000', 'MediaTek Helio P35', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/vivo-y15s-2021-261021-114837-600x600.jpg', 'Vivo Y15 series', 3190000, '3', 1, 5),
(18, '5000', 'Snapdragon 680', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/vivo-y55-2022-260422-053719-600x600.jpg', 'Vivo Y55', 6490000, '8', 1, 5),
(19, '4500', 'MediaTek Dimensity 900', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/vivo-v25-5g-vang-thumb-600x600.jpg', 'Vivo V25 series', 10490000, '8', 1, 5),
(20, '4500', 'MediaTek Dimensity 9000', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/vivo-x80-xanh-thumb-600x600.jpg', 'Vivo X80', 19490000, '12', 1, 5),
(21, 'Chip H1', 'Nghe 20h, sạc 3h', 'Chống ồn chủ động', 'http://localhost:8080/api/product/loadFile/bluetooth-airpods-max-apple-thumb2-600x600.jpeg', 'Tai nghe chụp tai Bluetooth AirPods Max ', 1490000, '', 3, 1),
(22, 'Chip H1', 'Nghe 5h, sạc 24h', 'Bluetooth 5.0', 'http://localhost:8080/api/product/loadFile/bluetooth-airpods-2-apple-mv7n2-imei-ava-600x600.jpg', 'Tai nghe chụp tai Bluetooth AirPods Max ', 3190000, '', 3, 1),
(23, 'Power Delivery', '2 Type-C', 'Sạc nhanh 35W', 'http://localhost:8080/api/product/loadFile/adapter-sac-dual-type-c-35w-apple-mnwp3-trang-thumb-1-600x600.jpeg', 'Adapter Sạc Dual Type C 35W', 1250000, '', 3, 1),
(24, 'Dây dài 2m', '87W lightning', 'Hàng chính hãng 100%', 'http://localhost:8080/api/product/loadFile/cap-type-c-lightning-2m-apple-mqgh2-trang-thumb-600x600.jpeg', 'Cáp Type C - Lightning 2m', 890000, '', 3, 1),
(25, 'Nghe 8h', 'Chống nước IPX7', 'Chống ồn chủ động', 'http://localhost:8080/api/product/loadFile/bluetooth-true-wireless-samsung-galaxy-buds2-pro-trang-600x600.jpg', 'Tai nghe Bluetooth True Wireless', 3990000, '', 3, 2),
(26, 'Cáp micro dài 2m', 'Cổng USB', '15W sạc thường', 'http://localhost:8080/api/product/loadFile/bo-adapter-sac-kem-cap-micro-samsung-ta20hw-thumb-600x600.jpeg', 'Bộ adapter sạc 15W', 390000, '', 3, 2),
(27, 'Power Delivery', 'Cổng USB, Type-C', '65W sạc nhanh', 'http://localhost:8080/api/product/loadFile/adapter-sac-usb-type-c-pd-65w-samsung-ep-t6530n-thumb-600x600.jpeg', 'Adapter sạc 3 cổng USB Type C', 1990000, '', 3, 2),
(28, 'Lõi Polymer', 'Cổng USB, Type-C', '25W sạc nhanh', 'http://localhost:8080/api/product/loadFile/polymer-10000mah-type-c-25w-samsung-eb-p3300-190722-052634-600x600.jpeg', 'Pin sạc dự phòng Polymer 10.000 mAh', 890000, '', 3, 2),
(29, '8600', 'Apple A13 Bionic', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/iPad-9-5G-den-600x600.jpg', 'iPad 9 4G', 11490000, '4', 2, 1),
(30, '7500', 'Apple M1', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/ipad-pro-m1-11-inch-wifi-gray-9-600x600.jpg', 'iPad Pro M1 11 inch 5G', 41490000, '16', 2, 1),
(31, '5175', 'Apple A15 Bionic', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/ipad-mini-6-wifi-cellular-grey-1-600x600.jpg', 'iPad mini 6 WiFi', 13490000, '4', 2, 1),
(32, '7587', 'Apple M1', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/ipad-air-5-m1-wifi-cellular-64gb-090322-094032-600x600.jpg', 'iPad Air 5 M1 WiFi', 15490000, '8', 2, 1),
(33, '10835', 'Apple M2', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/ipad-pro-2021-129-inch-gray-600x600.jpg', 'iPad Pro M2 12.9 inch WiFi', 31990000, '12', 2, 1),
(34, '5100', 'MediaTek MT8768T', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/samsung-galaxy-tab-a7-lite-gray-600x600.jpg', 'Samsung Galaxy Tab A7 Lite', 4990000, '3', 2, 2),
(35, '7040', 'UniSOC T618', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/samsung-galaxy-tab-a8-thumb-600x600.jpg', 'Samsung Galaxy Tab A8 (2022)', 8490000, '4', 2, 2),
(36, '7040', 'Exynos 9611', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/samsung-galaxy-tab-s6-lite-600x600-1-600x600.jpg', 'Samsung Galaxy Tab S6 Lite', 9990000, '4', 2, 2),
(37, '10090', 'Snapdragon 778G', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/samsung-galaxy-tab-s7-fe-wifi-600x600.jpg', 'Samsung Galaxy Tab S7 FE', 12990000, '4', 2, 2),
(38, '8000', 'Snapdragon 8 Gen 1', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/samsung-galaxy-tab-s8-white-thumb-600x600.jpg', 'Samsung Galaxy Tab S8 5G Series', 20990000, '8', 2, 2),
(39, '7250', 'Snapdragon 865', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/huawei-matepad-11-9-600x600.jpg', 'Huawei MatePad 11 WiFi', 12190000, '6', 2, 6),
(40, '5100', 'Kirin 710A', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/huawei-matepad-t10-1-600x600.jpg', 'Huawei MatePad T10', 3090000, '2', 2, 6),
(41, '5100', 'Kirin 710A', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/huawei-matepad-t10s-4gb-121121-025123-600x600.jpg', 'Huawei MatePad T10s', 4090000, '2', 2, 6),
(42, '7250', 'Kirin 820', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/huawei-matepad-xam-128gb-600x600.jpg', 'Huawei MatePad WiFi', 6390000, '4', 2, 6),
(43, '8720', 'Snapdragon 860', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/Redmi-Pad-Black-thumb-org-600x600.jpg', 'Xiaomi Pad 5 WiFi', 10490000, '6', 2, 4),
(44, '8000', 'MediaTek Helio G99', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/xiaomi-pad-5-grey-600x600.jpg', 'Xiaomi Redmi Pad (4GB/128GB)', 6990000, '4', 2, 4),
(45, '8000', 'MediaTek Helio G99', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/Redmi-Pad-Sliver-thumb-org-1-2-600x600.jpg', 'Xiaomi Redmi Pad (3GB/64GB)', 6290000, '3', 2, 4),
(46, '7100', 'Snapdragon 680', 'Hàng chính hãng, mới 100%', 'http://localhost:8080/api/product/loadFile/OPPO-pad-thumb-x%C3%A1m-600x600.jpg', 'OPPO Pad Air', 6990000, '4', 2, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles_tb`
--

CREATE TABLE `roles_tb` (
  `id` int(11) NOT NULL,
  `role_name` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `roles_tb`
--

INSERT INTO `roles_tb` (`id`, `role_name`) VALUES
(1, 'USER'),
(2, 'ADMIN');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users_tb`
--

CREATE TABLE `users_tb` (
  `id` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `users_tb`
--

INSERT INTO `users_tb` (`id`, `email`, `password`, `phone`, `user_name`) VALUES
(1, 'xyz123@gmail.com', '$2a$10$JVWNbMfVYLnAf6M95WtTvOXMQeyCPhwcoTMqku49qJ.Cd8BiP6AfG', '098674223', 'Nguyen Van B'),
(2, 'admin@gmail.com', '$2a$10$MoEejXPCDU1JY0Gn89qh7es5T7/woP.RPimC5t040a0TK5bRXbZj.', '097542126', 'Admin'),
(3, 'abc@gmail.com', '$2a$10$NxtgvipWHX1ylbxZ4R.QfutSk2mJ50g1x9Dhhig.51PaEWe5ydqXq', '098888855', 'Vu Duc Chinh'),
(4, 'vuducchinh2002@gmail.com', '$2a$10$KaNCI4ZsORS70g2TReNJOu85Y3dMHr9hex3bNA53KXZ5Q4th34yrS', '0966453312', 'chinh123');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_role`
--

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(3, 1),
(4, 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `cart_item_tb`
--
ALTER TABLE `cart_item_tb`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt2jl3nhhvoaocjs1dwtud5eb9` (`cart_id`),
  ADD KEY `FKqpwi0h0ns468sifuvdrt5cduj` (`product_id`);

--
-- Chỉ mục cho bảng `cart_tb`
--
ALTER TABLE `cart_tb`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK95hnmsd3q7o4fw8nyendqpru7` (`user_id`);

--
-- Chỉ mục cho bảng `category_tb`
--
ALTER TABLE `category_tb`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `comment_tb`
--
ALTER TABLE `comment_tb`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbvp0hkl28bt8yojahjejwoq17` (`user_id`);

--
-- Chỉ mục cho bảng `manufacturer_tb`
--
ALTER TABLE `manufacturer_tb`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `order_tb`
--
ALTER TABLE `order_tb`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKle6nscctsikvo959pn5oor4us` (`user_id`);

--
-- Chỉ mục cho bảng `product_tb`
--
ALTER TABLE `product_tb`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK49klrdq6a2h7nvn5ityhg07m3` (`category_id`),
  ADD KEY `FKpd31k535fm5q3dckegj8u2vb6` (`manufacturer_id`);

--
-- Chỉ mục cho bảng `roles_tb`
--
ALTER TABLE `roles_tb`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `users_tb`
--
ALTER TABLE `users_tb`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FK4s2kdwqc4y5ofrcsw4cd0dnqr` (`role_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `cart_item_tb`
--
ALTER TABLE `cart_item_tb`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `cart_tb`
--
ALTER TABLE `cart_tb`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `category_tb`
--
ALTER TABLE `category_tb`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `comment_tb`
--
ALTER TABLE `comment_tb`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `manufacturer_tb`
--
ALTER TABLE `manufacturer_tb`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `order_tb`
--
ALTER TABLE `order_tb`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `product_tb`
--
ALTER TABLE `product_tb`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT cho bảng `roles_tb`
--
ALTER TABLE `roles_tb`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `users_tb`
--
ALTER TABLE `users_tb`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `cart_item_tb`
--
ALTER TABLE `cart_item_tb`
  ADD CONSTRAINT `FKqpwi0h0ns468sifuvdrt5cduj` FOREIGN KEY (`product_id`) REFERENCES `product_tb` (`id`),
  ADD CONSTRAINT `FKt2jl3nhhvoaocjs1dwtud5eb9` FOREIGN KEY (`cart_id`) REFERENCES `cart_tb` (`id`);

--
-- Các ràng buộc cho bảng `cart_tb`
--
ALTER TABLE `cart_tb`
  ADD CONSTRAINT `FK95hnmsd3q7o4fw8nyendqpru7` FOREIGN KEY (`user_id`) REFERENCES `users_tb` (`id`);

--
-- Các ràng buộc cho bảng `comment_tb`
--
ALTER TABLE `comment_tb`
  ADD CONSTRAINT `FKbvp0hkl28bt8yojahjejwoq17` FOREIGN KEY (`user_id`) REFERENCES `users_tb` (`id`);

--
-- Các ràng buộc cho bảng `order_tb`
--
ALTER TABLE `order_tb`
  ADD CONSTRAINT `FKle6nscctsikvo959pn5oor4us` FOREIGN KEY (`user_id`) REFERENCES `users_tb` (`id`);

--
-- Các ràng buộc cho bảng `product_tb`
--
ALTER TABLE `product_tb`
  ADD CONSTRAINT `FK49klrdq6a2h7nvn5ityhg07m3` FOREIGN KEY (`category_id`) REFERENCES `category_tb` (`id`),
  ADD CONSTRAINT `FKpd31k535fm5q3dckegj8u2vb6` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturer_tb` (`id`);

--
-- Các ràng buộc cho bảng `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK1flathnfnv135y8mpsbrwfhfe` FOREIGN KEY (`user_id`) REFERENCES `users_tb` (`id`),
  ADD CONSTRAINT `FK4s2kdwqc4y5ofrcsw4cd0dnqr` FOREIGN KEY (`role_id`) REFERENCES `roles_tb` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
