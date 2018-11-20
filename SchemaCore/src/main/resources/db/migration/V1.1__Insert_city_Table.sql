CREATE TABLE lastmile_core.city
(
  city_id    BIGINT                   NOT NULL,
  name_ar    CHARACTER VARYING(100)   NOT NULL,
  name_en    CHARACTER VARYING(100)   NOT NULL,
  country_id BIGINT                   NOT NULL,
  created    TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  version    BIGINT                   NOT NULL DEFAULT 0,
  CONSTRAINT city_pk PRIMARY KEY (city_id),
  CONSTRAINT city_country_fk FOREIGN KEY (country_id)
  REFERENCES lastmile_core.country (country_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);


INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (476, 'Leova', 'Leova', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2603, 'سبتة', 'Ceuta', 150, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1891, 'كراني', 'Kranj', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1986, 'Mislinja', 'Mislinja', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2040, 'بركو', 'Brčko', 176, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2270, 'باربودا', 'Barbuda', 207, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (116, 'كيغالي', 'Kigali', 1, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1, 'بنادر', 'Banaadir', 2, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2, 'وقويي جالبيد', 'Woqooyi Galbeed', 2, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2605, 'جدو', 'Gedo', 2, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (3, 'ذمار', 'Dhamār', 3, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (4, 'محافظة حضرموت', 'Muhafazat Hadramawt', 3, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (5, 'صنعاء', 'Sanaa', 3, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (6, 'محافظة الحديدة', 'Muhafazat al Hudaydah', 3, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (279, 'عدن', 'Aden', 3, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (7, 'Shabiyat بنغازي', 'Shabiyat Banghazi', 4, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1300, 'طرابلس', 'Tripoli', 4, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1301, 'Shabiyat سبها', 'Shabiyat Sabha', 4, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1302, 'Shabiyat مصراتة', 'Shabiyat Misratah', 4, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1303, 'Sha`biyat نالوت', 'Sha`biyat Nalut', 4, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (8, 'الانبار', 'Anbar', 5, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (9, 'محافظة كركوك', 'Muhafazat Kirkuk', 5, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (10, 'محافظة كربلاء', 'Muhafazat Karbala', 5, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (11, 'محافظة اربيل', 'Muhafazat Arbil', 5, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (12, 'محافظة واسط', 'Muhafazat Wasit', 5, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (13, 'محافظة السليمانية كما', 'Muhafazat as Sulaymaniyah', 5, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (14, 'Mayorality بغداد', 'Mayorality of Baghdad', 5, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (15, 'محافظة بابل', 'Muhafazat Babil', 5, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (16, 'النجف', 'An Najaf', 5, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (17, 'محافظة نينوى', 'Muhafazat Ninawa', 5, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (18, 'محافظة البصرة', 'Basra Governorate', 5, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (19, 'ميسان', 'Maysan', 5, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (20, 'المدينة المنورة', 'Al Madinah al Munawwarah', 6, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (21, 'المنطقة الشرقية', 'Eastern Province', 6, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (22, 'تبوك', 'Tabuk', 6, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (23, 'منطقة مكة المكرمة', 'Makkah Province', 6, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (24, 'نجران', 'Najran', 6, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (25, 'عسير', 'Asir', 6, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (26, 'جازان', 'Jizan', 6, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (27, 'Hail منطقة', 'Hail Region', 6, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (28, 'القصيم', 'Al-Qassim', 6, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (29, 'الرياض', 'Ar Riyāḑ', 6, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (30, 'الباحة', 'Al Bahah', 6, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (31, 'Ostan الإلكتروني طهران', 'Ostan-e Tehran', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (32, 'زنجان', 'Zanjan', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (33, 'فارس', 'Fars', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (34, 'يزد', 'Yazd', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (35, 'مازندران', 'Māzandarān', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (36, 'أذربيجان الشرقية', 'East Azerbaijan', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (37, 'Ostan الإلكترونية خراسان الإلكترونية الشوملي', 'Ostan-e Khorasan-e Shomali', 7, '2016-10-20 13:41:43.233783+02',
   0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (38, 'سمنان', 'Semnān', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (39, 'Ostan الإلكترونية كردستان', 'Ostan-e Kordestan', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (40, 'رضوي خراسان', 'Razavi Khorasan', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (41, 'كرمان', 'Kerman', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (42, 'Ostan الإلكترونية جيلان', 'Ostan-e Gilan', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (43, 'Ostan الإلكترونية قزوين', 'Ostan-e Qazvin', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (44, 'Ostan الإلكترونية أذربيجان الغربية', 'Ostan-e Azarbayjan-e Gharbi', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (45, 'المركزي', 'Markazi', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (46, 'Ostan الإلكترونية همدان', 'Ostan-e Hamadan', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (47, 'هرمزكان', 'Hormozgan', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (48, 'خوزستان', 'Khuzestan', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (49, 'Ostan الإلكترونية كرمنشاه', 'Ostan-e Kermanshah', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (50, 'أصفهان', 'Isfahan', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (51, 'البرز', 'Alborz', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (52, 'Ostan الإلكترونية إيلام', 'Ostan-e Ilam', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (53, 'Ostan الإلكترونية كلستان', 'Ostan-e Golestan', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (54, 'بوشهر', 'Bushehr', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (55, 'Ostan الإلكترونية جهار محال بختياري زارة شؤون المحاربين القدامى', 'Ostan-e Chahar Mahal va Bakhtiari', 7,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (278, 'Ostan الإلكترونية أردبيل', 'Ostan-e Ardabil', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (795, 'سيستان وبلوشستان', 'Sistan and Baluchestan', 7, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (56, 'وندا سول', 'Lunda Sul', 8, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (57, 'لواندا نورتي', 'Luanda Norte', 8, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (706, 'مكسيكو', 'Moxico', 8, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1312, 'يغي', 'Uíge', 8, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1313, 'مقاطعة بينغو', 'Bengo Province', 8, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1314, 'مقاطعة كوانزا نورتي', 'Cuanza Norte Province', 8, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1315, 'مقاطعة مالانج', 'Malanje Province', 8, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1316, 'مقاطعة لواندا', 'Luanda Province', 8, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1317, 'كابيندا', 'Cabinda', 8, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2053, 'مقاطعة كونين', 'Cunene Province', 8, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2054, 'مقاطعة ناميبي', 'Namibe Province', 8, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2055, 'كواندو Cobango', 'Cuando Cobango', 8, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2056, 'بنغيلا', 'Benguela', 8, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2057, 'المكتب الدولي للمعارض', 'Bíe', 8, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2058, 'هوامبو', 'Huambo', 8, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (58, 'ليماسول', 'Limassol', 9, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (59, 'نيقوسيا', 'Nicosia', 9, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (60, 'أموشوستوس', 'Ammochostos', 9, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (61, 'لارنكا', 'Larnaka', 9, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (62, 'بافوس', 'Pafos', 9, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (63, 'كيرينيا', 'Keryneia', 9, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (64, 'ناخيتشيفان', 'Nakhichevan', 10, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (65, 'إميشلي رايون', 'Imishli Rayon', 10, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (406, 'سامكيت مدينة', 'Sumqayit City', 10, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (407, 'سابيراباد رايون', 'Sabirabad Rayon', 10, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (408, 'كوسار رايون', 'Qusar Rayon', 10, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (409, 'مدينة باكو', 'Baku City', 10, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (66, 'زنجبار حضر / الغربية', 'Zanzibar Urban/West', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (67, 'بيمبا الشمالية', 'Pemba North', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (68, 'طنجة', 'Tanga', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (69, 'تابورا', 'Tabora', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (70, 'روكوا', 'Rukwa', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (71, 'سينغيدا', 'Singida', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (72, 'شينيانغا', 'Shinyanga', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (73, 'موانزا', 'Mwanza', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (74, 'مارا', 'Mara', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (75, 'كليمنجارو', 'Kilimanjaro', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (76, 'موروجورو', 'Morogoro', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (77, 'مبيا', 'Mbeya', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (78, 'زنجبار وسط / جنوب', 'Zanzibar Central/South', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (79, 'بواني', 'Pwani', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (80, 'كيغوما', 'Kigoma', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (81, 'إيرينغا', 'Iringa', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (82, 'زنجبار الشمالية', 'Zanzibar North', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (83, 'دودوما', 'Dodoma', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (84, 'منطقة دار السلام', 'Dar es Salaam Region', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (85, 'بيمبا الجنوبية', 'Pemba South', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (86, 'كاجيرا', 'Kagera', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (87, 'منيارة', 'Manyara', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (88, 'أروشا', 'Arusha', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (708, 'روفوما', 'Ruvuma', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (709, 'متوارا', 'Mtwara', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (710, 'ليندي', 'Lindi', 11, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (89, 'آهال', 'Ahal', 12, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (90, 'محافظة حماة', 'Hama Governorate', 13, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (91, 'محافظة حلب', 'Aleppo Governorate', 13, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (92, 'محافظة دمشق', 'Damascus Governorate', 13, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (93, 'محافظة اللاذقية', 'Latakia Governorate', 13, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (94, 'محافظة السويداء', 'As-Suwayda Governorate', 13, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (178, 'محافظة القنيطرة', 'Quneitra Governorate', 13, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (95, 'مقاطعة سيونيك', 'Syunik Province', 14, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (96, 'أرارات', 'Ararat Province', 14, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (451, 'يريفان', 'Yerevan', 14, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (452, 'مقاطعة أرمافير', 'Armavir Province', 14, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (453, 'مقاطعة كوتايك', 'Kotayk Province', 14, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (454, 'مقاطعة Gegharkunik', 'Gegharkunik Province', 14, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (455, 'آراغاتسوتن', 'Aragatsotn Province', 14, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (456, 'مقاطعة لوري', 'Lori Province', 14, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (457, 'مقاطعة تافوشي', 'Tavush Province', 14, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (458, 'مقاطعة شيراك', 'Shirak Province', 14, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (97, 'مقاطعة لوابولا', 'Luapula Province', 15, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (717, 'محافظة الغربية', 'Western Province', 15, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (718, 'المنطقة الشمالية', 'Northern Province', 15, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (719, 'حزام النحاس', 'Copperbelt', 15, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (720, 'مقاطعة Muchinga', 'Muchinga Province', 15, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (721, 'المنطقة الجنوبية', 'Southern Province', 15, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (722, 'مقاطعة لوساكا', 'Lusaka Province', 15, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (723, 'شمال غرب مقاطعة', 'North-Western Province', 15, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (98, 'منطقة كوالي', 'Kwale District', 16, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (99, 'مقاطعة نيروبي', 'Nairobi Province', 16, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (100, 'منطقة نيري', 'Nyeri District', 16, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (101, 'منطقة ناكورو', 'Nakuru District', 16, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (102, 'منطقة Muranga', 'Muranga District', 16, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (103, 'منطقة مومباسا', 'Mombasa District', 16, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (104, 'منطقة خليج هوما', 'Homa Bay District', 16, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (105, 'منطقة مانديرا', 'Mandera District', 16, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (106, 'منطقة كيامبو', 'Kiambu District', 16, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (107, 'منطقة نزويا العابرة', 'Trans Nzoia District', 16, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (108, 'كيسومو', 'Kisumu', 16, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (109, 'منطقة كيسي', 'Kisii District', 16, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (110, 'منطقة كيليفي', 'Kilifi District', 16, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (111, 'منطقة كيريشو', 'Kericho District', 16, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (112, 'منطقة غاريسا', 'Garissa District', 16, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (113, 'أوسين جيشو', 'Uasin Gishu', 16, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (114, 'منطقة ثاراكا', 'Tharaka District', 16, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (115, 'منطقة BOMET', 'Bomet District', 16, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (277, 'منطقة سيايا', 'Siaya District', 16, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (117, 'نورد كيفو', 'Nord Kivu', 17, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (118, 'مقاطعة كيفو الجنوبية', 'South Kivu Province', 17, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (726, 'مقاطعة كاتانغا', 'Katanga Province', 17, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1359, 'كينشاسا مدينة', 'Kinshasa City', 17, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1360, 'الكونغو السفلى', 'Bas-Congo', 17, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (119, 'المنطقة الوسطى', 'Central Region', 19, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (120, 'مبومو', 'Mbomou', 20, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1401, 'بانغي', 'Bangui', 20, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (121, 'نهر الإنجليزية', 'English River', 21, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (122, 'تاكاماكا', 'Takamaka', 21, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (123, 'Ouadaï', 'Ouadaï', 22, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1422, 'شاري باغيرمي منطقة', 'Chari-Baguirmi Region', 22, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1423, 'غون اوكسيدنتال منطقة', 'Logone Occidental Region', 22, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1424, 'حجر-لميس', 'Hadjer-Lamis', 22, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (124, 'عجلون', 'Ajloun', 23, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (125, 'محافظة عمان', 'Amman Governorate', 23, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (126, 'مادبا', 'Madaba', 23, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (127, 'جرش', 'Jerash', 23, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (128, 'اربد', 'Irbid', 23, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (129, 'Tafielah', 'Tafielah', 23, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (130, 'الزرقاء', 'Zarqa', 23, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (131, 'الكرك', 'Karak', 23, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (132, 'معان', 'Ma’an', 23, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (133, 'المفرق', 'Mafraq', 23, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (134, 'العقبة', 'Aqaba', 23, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (135, 'البلقاء', 'Balqa', 23, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (136, 'اليونان الوسطى', 'Central Greece', 24, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (137, 'جزر البحر الأيوني', 'Ionian Islands', 24, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (138, 'بيلوبونيز', 'Peloponnese', 24, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (139, 'أتيكا', 'Attica', 24, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (140, 'غرب اليونان', 'West Greece', 24, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (141, 'تساليا', 'Thessaly', 24, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (142, 'كريت', 'Crete', 24, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (143, 'جنوب إيجة', 'South Aegean', 24, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (144, 'إبيروس', 'Epirus', 24, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (145, 'شمال بحر ايجه', 'North Aegean', 24, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (146, 'غرب مقدونيا', 'West Macedonia', 24, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (628, 'مقدونيا الوسطى', 'Central Macedonia', 24, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (629, 'الشرق مقدونيا وتراقيا', 'East Macedonia and Thrace', 24, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (147, 'محافظة شمال لبنان', 'Mohafazat Liban-Nord', 25, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (148, 'محافظة الجنوب', 'South Governorate', 25, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (149, 'محافظة جبل لبنان', 'Mohafazat Mont-Liban', 25, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (150, 'محافظة عكار', 'Mohafazat Aakkar', 25, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (151, 'بيروت', 'Beyrouth', 25, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (152, 'محافظة بعلبك الهرمل', 'Mohafazat Baalbek-Hermel', 25, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (153, 'القدس', 'Jerusalem', 27, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (179, 'المقاطعة المركزية', 'Central District', 27, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (180, 'المنطقة الشمالية', 'Northern District', 27, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (181, 'حيفا', 'Haifa', 27, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (182, 'المنطقة الجنوبية', 'Southern District', 27, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (183, 'تل أبيب', 'Tel Aviv', 27, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (154, 'الفروانية', 'Al Farwaniyah', 28, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (155, 'محافظة حولي', 'Muhafazat Hawalli', 28, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (156, 'الأحمدي', 'Al Aḩmadī', 28, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (157, 'شركة العاصمة لل', 'Al Asimah', 28, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (158, 'محافظة شمال الباطنة', 'Al Batinah North Governorate', 29, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (159, 'محافظة ظفار', 'Muhafazat Zufar', 29, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (160, 'محافظة مسقط', 'Muhafazat Masqat', 29, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (161, 'محافظة إعلان المنطقة الداخلية', 'Muhafazat ad Dakhiliyah', 29, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (162, 'البلديات أم صلال', 'Baladiyat Umm Salal', 30, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (163, 'البلديات الرماد الشمال', 'Baladiyat ash Shamal', 30, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (164, 'البلديات الريان', 'Baladiyat ar Rayyan', 30, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (165, 'الوكرة', 'Al Wakrah', 30, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (166, 'وا البلديات الخور ADH Dhakhirah', 'Baladiyat al Khawr wa adh Dhakhirah', 30, '2016-10-20 13:41:43.233783+02',
   0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (167, 'البلديات الدوحة', 'Baladiyat ad Dawhah', 30, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (168, 'المنامة', 'Manama', 31, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (169, 'المحافظة الوسطى', 'Central Governorate', 31, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (170, 'المحافظة الجنوبية', 'Southern Governorate', 31, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (171, 'المحرق', 'Muharraq', 31, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (172, 'رأس الخيمة', 'Ras al Khaymah', 32, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (173, 'الرماد Shariqah', 'Ash Shariqah', 32, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (174, 'دبي', 'Dubai', 32, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (175, 'الفجيرة', 'Al Fujayrah', 32, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (176, 'ابو ظبي', 'Abu Dhabi', 32, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (177, 'عجمان', 'Ajman', 32, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (184, 'ملاطية', 'Malatya', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (185, 'ماردين', 'Mardin', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (186, 'اسكي شهير', 'Eskişehir', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (187, 'أضنة', 'Adana', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (188, 'يوزغات', 'Yozgat', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (189, 'هاتاي', 'Hatay', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (190, 'أنقرة', 'Ankara', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (191, 'آيدن', 'Aydın', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (192, 'جاناقال', 'Çanakkale', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (193, 'أنطاليا', 'Antalya', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (194, 'إزمير', 'Izmir', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (195, 'كيركيال', 'Kırıkkale', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (196, 'سيارة نقل', 'Van', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (197, 'أوشاك', 'Uşak', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (198, 'شانلي اورفا', 'Şanlıurfa', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (199, 'مانيسا', 'Manisa', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (200, 'تونجلي', 'Tunceli', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (201, 'دنيزلي', 'Denizli', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (202, 'مرسين', 'Mersin', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (203, 'قيصري', 'Kayseri', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (204, 'سيفاس', 'Sivas', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (205, 'شيرناك', 'Şırnak', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (206, 'أفيون قره حصار', 'Afyonkarahisar', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (207, 'ديار بكر', 'Diyarbakir', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (208, 'كوتاهيا', 'Kütahya', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (209, 'سيرت', 'Siirt', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (210, 'قونية', 'Konya', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (211, 'غازي عنتاب', 'Gaziantep', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (212, 'أغري', 'Ağrı', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (213, 'عثمانية', 'Osmaniye', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (214, 'نيغدة', 'Nigde', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (215, 'نفسهير', 'Nevsehir', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (216, 'موش', 'Muş', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (217, 'موغلا', 'Muğla', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (218, 'كيرسيهر', 'Kırşehir', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (219, 'كيليس', 'Kilis', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (220, 'بينجول', 'Bingöl', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (221, 'باليكسير', 'Balıkesir', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (222, 'إيلازيغ', 'Elazığ', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (223, 'الرجل الوطواط', 'Batman', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (224, 'كرمان', 'Karaman', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (225, 'قهرمان', 'Kahramanmaraş', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (226, 'اسبرطة', 'Isparta', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (227, 'إجدير', 'Iğdır', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (228, 'أرضروم', 'Erzurum', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (229, 'ارزينجان', 'Erzincan', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (230, 'هكاري', 'Hakkâri', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (231, 'بوردور', 'Burdur', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (232, 'بيلجيك', 'Bilecik', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (233, 'بتليس', 'Bitlis', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (234, 'أكساراي', 'Aksaray', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (235, 'أديامان', 'Adiyaman', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (236, 'الجراب', 'Bursa', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (630, 'زونجولداك', 'Zonguldak', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (631, 'توكات', 'Tokat', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (632, 'اسطنبول', 'Istanbul', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (633, 'أرتفين', 'Artvin', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (634, 'يالوفا', 'Yalova', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (635, 'كيركلاريلي', 'Kırklareli', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (636, 'أدرنة', 'Edirne', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (637, 'طرابزون', 'Trabzon', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (638, 'جيرسون', 'Giresun', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (639, 'سامسون', 'Samsun', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (640, 'بولو', 'Bolu', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (641, 'تيكيرداغ', 'Tekirdağ', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (642, 'كاستامونو', 'Kastamonu', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (643, 'ساكاريا', 'Sakarya', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (644, 'سينوب', 'Sinop', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (645, 'أماسيا', 'Amasya', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (646, 'ريزي', 'Rize', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (647, 'أوردو', 'Ordu', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (648, 'كانكيري', 'Çankırı', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (649, 'قوجا', 'Kocaeli', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (650, 'كارس', 'Kars', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (651, 'Karabuek', 'Karabuek', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (652, 'Guemueshane', 'Guemueshane', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (653, 'Duezce', 'Duezce', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (654, 'كوريم', 'Çorum', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (655, 'بارتين', 'Bartın', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (656, 'بايبورت', 'Bayburt', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (657, 'أردهان', 'Ardahan', 33, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (237, 'الصومالية', 'Somali', 34, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (238, 'أوروميا', 'Oromiya', 34, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (239, 'هراري منطقة', 'Harari Region', 34, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (240, 'أمهرة', 'Amhara', 34, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (241, 'غامبيلا', 'Gambela', 34, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (242, 'إقليم عفر', 'Afar Region', 34, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (243, 'دير داوا', 'Dire Dawa', 34, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (244, 'الأمم الجنوبية، ومنطقة الشعب', 'Southern Nations, Nationalities, and Peoples Region', 34,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (245, 'بينشانجول قماز', 'Bīnshangul Gumuz', 34, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (247, 'تيغري', 'Tigray', 34, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (248, 'اديس ابابا', 'Addis Ababa', 34, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (246, 'ماكيل منطقة', 'Maekel Region', 35, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (249, 'القليوبية', 'Qalyubia', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (250, 'الغربية', 'Gharbia', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (251, 'الدقهلية', 'Dakahlia', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (252, 'المنوفية', 'Monufia', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (253, 'سوهاج', 'Sohag', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (254, 'محافظة القاهرة', 'Cairo Governorate', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (255, 'شمال سيناء', 'North Sinai', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (256, 'قنا', 'Qena', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (257, 'كفر الشيخ', 'Kafr el-Sheikh', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (258, 'البحيرة', 'Beheira', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (259, 'المنيا', 'Minya', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (260, 'الشرقية', 'Sharqia', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (261, 'الجيزة', 'Giza', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (262, 'محافظة الإسماعيلية', 'Ismailia Governorate', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (263, 'محافظة دمياط', 'Damietta Governorate', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (264, 'بورسعيد', 'Port Said', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (265, 'بني سويف', 'Beni Suweif', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (266, 'أسيوط', 'Asyut', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (267, 'أسوان', 'Aswan', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (268, 'السويس', 'Suez', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (269, 'الفيوم', 'Faiyum', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (270, 'الأقصر', 'Luxor', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (271, 'الإسكندرية', 'Alexandria', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (272, 'البحر الاحمر', 'Red Sea', 36, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (273, 'Qarku ط Vlores', 'Qarku i Vlores', 37, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (668, 'Qarku ط Gjirokastres', 'Qarku i Gjirokastres', 37, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (669, 'Qarku ط Dibres', 'Qarku i Dibres', 37, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (670, 'Qarku ط Korces', 'Qarku i Korces', 37, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (671, 'Qarku ط Elbasanit', 'Qarku i Elbasanit', 37, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1867, 'Qarku ط Beratit', 'Qarku i Beratit', 37, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1868, 'Qarku ط Tiranes', 'Qarku i Tiranes', 37, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1869, 'Qarku ط Shkodres', 'Qarku i Shkodres', 37, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1870, 'Qarku ط Lezhes', 'Qarku i Lezhes', 37, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1871, 'Qarku ط Fierit', 'Qarku i Fierit', 37, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1872, 'Qarku ط Durresit', 'Qarku i Durresit', 37, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2614, 'Qarku ط Kukesit', 'Qarku i Kukesit', 37, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (274, 'جنوب كردفان', 'Southern Kordofan', 38, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (276, 'الخرطوم', 'Khartoum', 38, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (275, 'وسط الاستوائية', 'Central Equatoria', 39, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (280, 'مقاطعة ميري بوجمبورا', 'Bujumbura Mairie Province', 40, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (281, 'إقليم تفر ', 'Tverskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (349, 'سان بطرسبرج', 'St.-Petersburg', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (350, 'إقليم ريازان ', 'Ryazanskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (351, 'روستوف', 'Rostov Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (352, 'ريسبوبليكا جمهورية ماري ايل', 'Respublika Mariy-El', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (353, 'موسكو أوبلاست', 'Moscow Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (354, 'إقليم كيروف ', 'Kirovskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (355, 'تشيليابينسك', 'Chelyabinsk', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (356, 'إقليم بريانسك ', 'Bryanskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (357, 'إقليم كالوغا ', 'Kaluzhskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (358, 'إقليم فولغوغراد ', 'Volgogradskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (359, 'إقليم سامارا ', 'Samarskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (360, 'جمهورية كومي', 'Komi Republic', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (361, 'Stavropolskiy كراي', 'Stavropolskiy Kray', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (362, 'إقليم كورسك ', 'Kurskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (363, 'موسكو', 'Moscow', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (364, 'تتارستان', 'Tatarstan', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (365, 'كرتاشييفو-Cherkesiya', 'Karachayevo-Cherkesiya', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (418, 'بولفاما', 'Põlvamaa', 42, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (366, 'إقليم نيجني نوفغورود ', 'Nizhegorodskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (367, 'مورمانسك', 'Murmansk', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (368, 'تولاسكايا أوبلاست ', 'Tulskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (369, 'إقليم كاليننغراد ', 'Kaliningradskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (370, 'إقليم ليبتسك ', 'Lipetskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (371, 'كراسنودار كراي', 'Krasnodarskiy Kray', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (372, 'بيرم كراي', 'Perm Krai', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (373, 'إقليم ساراتوف ', 'Saratovskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (374, 'إقليم سمولينسك ', 'Smolenskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (375, 'إقليم ياروسلاف ', 'Yaroslavskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (376, 'إقليم ليننغراد ', 'Leningradskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (377, 'باشكورتوستان', 'Bashkortostan', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (378, 'ريسبوبليكا أديغيا', 'Respublika Adygeya', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (379, 'إقليم فولوغدا ', 'Vologodskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (380, 'فلاديمير أوبلاست ', 'Vladimirskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (381, 'تشوفاشيا', 'Chuvashia', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (382, 'أودمورتسكيا ريسبوبليكا', 'Udmurtskaya Respublika', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (383, 'إقليم فورونز ', 'Voronezhskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (384, 'إقليم بيلغورود ', 'Belgorodskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (385, 'إقليم تامبوف ', 'Tambovskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (386, 'إقليم كوستروما ', 'Kostromskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (387, 'أوسيتيا الشمالية', 'North Ossetia', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (388, 'جمهورية كاريليا', 'Republic of Karelia', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (389, 'Arkhangelskaya', 'Arkhangelskaya', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (390, 'إقليم بسكوف ', 'Pskovskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (391, 'الشيشان', 'Chechnya', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (392, 'أوليانوفسك أوبلاست', 'Ulyanovsk Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (393, 'إقليم أورينبورغ ', 'Orenburgskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (394, 'إقليم إيفانوفو ', 'Ivanovskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (395, 'إقليم نوفغورود ', 'Novgorodskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (396, 'داغستان', 'Dagestan', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (397, 'إقليم سفردلوفسك ', 'Sverdlovskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (398, 'ريسبوبليكا موردوفيا', 'Respublika Mordoviya', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (399, 'إقليم بينزا ', 'Penzenskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (400, 'إقليم أوريل ', 'Orlovskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (401, 'ريسبوبليكا انغوشيا', 'Respublika Ingushetiya', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (402, 'نينيت أوكروغ أوكروغ', 'Nenetskiy Avtonomnyy Okrug', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (403, 'أستراخان أوبلاست ', 'Astrakhanskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (404, 'قبردينو بلقاريا ريسبوبليكا', 'Kabardino-Balkarskaya Respublika', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (405, 'كالاميكيا', 'Kalmykiya', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (876, 'إقليم كيميروفو ', 'Kemerovskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (877, 'كراسنويارسك كراي', 'Krasnoyarskiy Kray', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (878, 'التاي كراي', 'Altai Krai', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (879, 'نوفوسيبيرسك أوبلاست ', 'Novosibirskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (880, 'تايومنسكايا أوبلاست ', 'Tyumenskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (881, 'يامالو نينيت أوكروغ أوكروغ', 'Yamalo-Nenetskiy Avtonomnyy Okrug', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (882, 'أومسك أوبلاست ', 'Omskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (883, 'جمهورية التاي', 'Altai Republic', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (884, 'ريسبوبليكا خاكاسيا', 'Respublika Khakasiya', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (885, 'خانتي مانسي أوكروغ أوكروغ-يورغا', 'Khanty-Mansiyskiy Avtonomnyy Okrug-Yugra', 41,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (886, 'تومسك أوبلاست ', 'Tomskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (887, 'إقليم إركوتسك ', 'Irkutskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (888, 'ريسبوبليكا تيفا', 'Respublika Tyva', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (889, 'كورغانسكايا أوبلاست ', 'Kurganskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1215, 'أمورسكاي أوبلاست ', 'Amurskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1216, 'ريسبوبليكا بورياتيا', 'Respublika Buryatiya', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1217, 'بريمورسكي كراي', 'Primorskiy Kray', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1218, 'ريسبوبليكا ساخا (ياكوتيا)', 'Respublika Sakha (Yakutiya)', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1219, 'خاباروفسك كراي', 'Khabarovsk Krai', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1220, 'إقليم بايكال', 'Transbaikal Territory', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1221, 'الأوبلاست اليهودية الذاتية', 'Jewish Autonomous Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1269, 'Kamtchatski كراي', 'Kamtchatski Kray', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1270, 'ساخالين أوبلاست ', 'Sakhalinskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1271, 'إقليم ماغادان ', 'Magadanskaya Oblast', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1272, 'شوكوتسكي أوكروغ أوكروغ', 'Chukotskiy Avtonomnyy Okrug', 41, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (410, 'بارنوما', 'Pärnumaa', 42, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (411, 'فوروما', 'Võrumaa', 42, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (412, 'تارتو', 'Tartu', 42, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (413, 'المؤسسة الدولية للتنمية فيروما', 'Ida-Virumaa', 42, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (414, 'فيلجاندي', 'Viljandimaa', 42, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (415, 'اني فيريوما', 'Lääne-Virumaa', 42, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (416, 'Harjumaa', 'Harjumaa', 42, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (417, 'رابلاما', 'Raplamaa', 42, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (419, 'فلغما', 'Valgamaa', 42, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (420, 'يارفا', 'Järvamaa', 42, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (421, 'جوغفما', 'Jõgevamaa', 42, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (422, 'هيوما', 'Hiiumaa', 42, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (423, 'Lääne', 'Lääne', 42, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (424, 'Saare', 'Saare', 42, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (282, 'بيرم رازغراد', 'Oblast Razgrad', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (601, 'فارنا', 'Varna', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (602, 'نوفغورود سموليان', 'Oblast Smolyan', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (603, 'مقاطعة صوفيا', 'Sofia Province', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (604, 'نوفغورود فيليكو ترنوفو', 'Oblast Veliko Tarnovo', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (605, 'نوفغورود مونتانا', 'Oblast Montana', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (606, 'نوفغورود سيليسترا', 'Oblast Silistra', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (607, 'نوفغورود يامبول', 'Oblast Yambol', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (608, 'بلاغويفغارد', 'Blagoevgrad', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (609, 'نوفغورود فراتسا', 'Oblast Vratsa', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (610, 'بلوفديف', 'Plovdiv', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (611, 'بورغاس', 'Burgas', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (612, 'نوفغورود فيدين', 'Oblast Vidin', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (613, 'بازارجيك', 'Pazardzhik', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (614, 'نوفغورود تارغوفيشته', 'Oblast Targovishte', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (615, 'لوفيتش', 'Lovech', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (616, 'هاسكوفو', 'Haskovo', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (617, 'بيرم دوبريتش', 'Oblast Dobrich', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (618, 'نوفغورود ستارا زاكورة', 'Oblast Stara Zagora', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (619, 'نوفغورود كيوستينديل', 'Oblast Kyustendil', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (620, 'بيرم بليفين', 'Oblast Pleven', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (621, 'صوفيا العاصمة', 'Sofia-Capital', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (622, 'نوفغورود شومين', 'Oblast Shumen', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1629, 'يونكوبينغ', 'Jönköping', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (623, 'نوفغورود سليفن', 'Oblast Sliven', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (624, 'غابروفو', 'Gabrovo', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (625, 'نوفغورود روسه', 'Oblast Ruse', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (626, 'برنيك', 'Pernik', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (627, 'نوفغورود كارجلي', 'Oblast Kardzhali', 43, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (283, 'منطقة فالميرا', 'Valmiera District', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (284, 'ساولكراستي Novads', 'Saulkrastu Novads', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (285, 'Zilupes Novads', 'Zilupes Novads', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (286, 'Viesites Novads', 'Viesites Novads', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (287, 'فنتسبيلز', 'Ventspils', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (288, 'Varaklanu Novads', 'Varaklanu Novads', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (289, 'Inčukalns', 'Inčukalns', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (290, 'بلدية فالكا', 'Valka Municipality', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (291, 'Vainodes Novads', 'Vainodes Novads', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (292, 'Garkalne', 'Garkalne', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (293, 'تكما Rajons', 'Tukuma Rajons', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (294, 'Mārupe', 'Mārupe', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (295, 'بلدية تالسي', 'Talsi Municipality', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (296, 'غول', 'Ogre', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (297, 'جلبينيز Rajons', 'Gulbenes Rajons', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (298, 'Engure', 'Engure', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (299, 'Skrundas Novads', 'Skrundas Novads', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (300, 'Siguldas Novads', 'Siguldas Novads', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (301, 'بلدية سالدوس', 'Saldus Municipality', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (302, 'سالاسبيلس Novads', 'Salaspils Novads', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (303, 'Salacgrivas Novads', 'Salacgrivas Novads', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (304, 'Rujienas Novads', 'Rujienas Novads', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (305, 'Rugaju Novads', 'Rugaju Novads', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (306, 'Ropazu Novads', 'Ropazu Novads', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (307, 'روخاس Novads', 'Rojas Novads', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (308, 'ريغا', 'Riga', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (309, 'ريزكن', 'Rezekne', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (310, 'بلدية بريلي', 'Preili Municipality', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (311, 'Babīte', 'Babīte', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (312, 'Ozolnieku Novads', 'Ozolnieku Novads', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (313, 'Olaine', 'Olaine', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (314, 'Burtnieki', 'Burtnieki', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (315, 'بلدية الماضونة', 'Madona Municipality', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (316, 'لدزاس Rajons', 'Ludzas Rajons', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (317, 'يفاني', 'Līvāni', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (318, 'يمبازو Rajons', 'Limbazu Rajons', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (319, 'يابايا', 'Liepaja', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (320, 'ليال فاردة', 'Lielvārde', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (321, 'Plavinu Novads', 'Plavinu Novads', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (322, 'كلديجاس Rajons', 'Kuldigas Rajons', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (323, 'Dundaga', 'Dundaga', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (324, 'Koknese', 'Koknese', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (325, 'Ķekava', 'Ķekava', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (326, 'كغومس', 'Ķegums', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (327, 'كاندافا', 'Kandava', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (328, 'Carnikava', 'Carnikava', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (329, 'جيلجافاس Rajons', 'Jelgavas Rajons', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (330, 'جورمالا', 'Jurmala', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (331, 'جلجافا', 'Jelgava', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (332, 'بلدية يكاب', 'Jēkabpils Municipality', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (333, 'Jaunpiebalga', 'Jaunpiebalga', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (334, 'بلدية دوجافبيلز', 'Daugavpils municipality', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (335, 'إيكشكيل', 'Ikšķile', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (336, 'Lecava', 'Lecava', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (337, 'غروبينا', 'Grobiņa', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (338, 'دوبيليز Rajons', 'Dobeles Rajons', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (339, 'دوجافبيلز', 'Daugavpils', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (340, 'آيزيبوتي', 'Aizpute', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (341, 'سيسو Novads', 'Cesu Novads', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (342, 'بروكني', 'Brocēni', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (343, 'باوسكا Novads', 'Bauskas Novads', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (344, 'بالفو Novads', 'Balvu Novads', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (345, 'Baldone', 'Baldone', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (346, 'Aloja', 'Aloja', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (347, 'أيزكروكليز Rajons', 'Aizkraukles Rajons', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (348, 'Ādaži', 'Ādaži', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (705, 'Stopinu Novads', 'Stopinu Novads', 44, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (425, 'مقاطعة أوتينا', 'Utena County', 45, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (426, 'مقاطعة فيلنيوس', 'Vilnius County', 45, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (427, 'مقاطعة أليتس', 'Alytus County', 45, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (428, 'مقاطعة تيلسياي', 'Telšiai County', 45, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (429, 'مقاطعة تاوراغه', 'Tauragė County', 45, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (430, 'مقاطعة كلايبيدا', 'Klaipėda County', 45, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (431, 'مقاطعة شياولياي', 'Šiauliai County', 45, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (432, 'بانيفزيس', 'Panevėžys', 45, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (433, 'مقاطعة كاوناس', 'Kaunas County', 45, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (434, 'مقاطعة مارييامبول', 'Marijampolė County', 45, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (435, 'نوربوتين', 'Norrbotten', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (436, 'Västerbotten', 'Västerbotten', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1584, 'أوبسالا', 'Uppsala', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1627, 'فاسترا جوتالاند', 'Västra Götaland', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1628, 'سكين', 'Skåne', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1630, 'أوريبرو', 'Örebro', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1631, 'Östergötland', 'Östergötland', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1632, 'Södermanland', 'Södermanland', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1633, 'يافلبوري', 'Gävleborg', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1634, 'كالمار', 'Kalmar', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1635, 'Kronoberg', 'Kronoberg', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1636, 'جوتلاند', 'Gotland', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1637, 'Västmanland', 'Västmanland', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1638, 'هالاند', 'Halland', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1639, 'دالارنا', 'Dalarna', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1640, 'ستوكهولم', 'Stockholm', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1641, 'جيمتلاند', 'Jämtland', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1642, 'Västernorrland', 'Västernorrland', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1643, 'فارملاند', 'Värmland', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1644, 'بليكينج', 'Blekinge', 46, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1645, 'سفالبارد', 'Svalbard', 47, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2611, 'جان ماين', 'Jan Mayen', 47, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (437, 'Mangistauskaya أوبلاست ', 'Mangistauskaya Oblast', 48, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (438, 'في Zapadno-Kazakhstanskaya أوبلاست ', 'Zapadno-Kazakhstanskaya Oblast', 48, '2016-10-20 13:41:43.233783+02',
   0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (439, 'Aktyubinskaya أوبلاست ', 'Aktyubinskaya Oblast', 48, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (440, 'كاراجهاندي Oblysy', 'Qaraghandy Oblysy', 48, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (441, 'أتيراو Oblysy', 'Atyrau Oblysy', 48, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (896, 'اوبليس جامبيل', 'Zhambyl Oblysy', 48, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (897, 'الشرق كازاخستان', 'East Kazakhstan', 48, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (898, 'يوجنو Kazakhstanskaya أوبلاست ', 'Yuzhno-Kazakhstanskaya Oblast', 48, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (899, 'ألماتي Oblysy', 'Almaty Oblysy', 48, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (900, 'أكمولا Oblysy', 'Aqmola Oblysy', 48, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (901, 'سيفيرو-Kazakhstanskaya أوبلاست ', 'Severo-Kazakhstanskaya Oblast', 48, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (902, 'كيزيلورودا Oblysy', 'Qyzylorda Oblysy', 48, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (903, 'كوستاناي Oblysy', 'Qostanay Oblysy', 48, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (904, 'بافلودار Oblysy', 'Pavlodar Oblysy', 48, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (905, 'أستانا Qalasy', 'Astana Qalasy', 48, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (906, 'ألماتي Qalasy', 'Almaty Qalasy', 48, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (442, 'ساميغريلو وزيمو سفانيتي', 'Samegrelo and Zemo Svaneti', 49, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (443, 'شيدا كارتلي', 'Shida Kartli', 49, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (444, 'Kalaki تبليسي', 'Kalaki Tbilisi', 49, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (445, 'أبخازيا', 'Abkhazia', 49, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (446, 'أجاريا', 'Ajaria', 49, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (447, 'كاخيتي', 'Kakheti', 49, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (448, 'رشا-Lechkhumi وكفيمو سفانيتي', 'Racha-Lechkhumi and Kvemo Svaneti', 49, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (449, 'متسخيتا-Mtianeti', 'Mtskheta-Mtianeti', 49, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (450, 'غوريا', 'Guria', 49, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (704, 'إيميريتي', 'Imereti', 49, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (459, 'Raionul إيدينت', 'Raionul Edineţ', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (460, 'جاجوزيا', 'Gagauzia', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (461, 'أونغيني', 'Ungheni', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (462, 'UNITATEA Teritoriala الدين Stinga Nistrului', 'Unitatea Teritoriala din Stinga Nistrului', 50,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (463, 'Teleneşti', 'Teleneşti', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (464, 'تاراسليا', 'Taraclia', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (465, 'MUNICIPIUL كيشيناو', 'Municipiul Chisinau', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (466, 'Raionul ستيفان فودا', 'Raionul Stefan Voda', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (467, 'Străşeni', 'Strășeni', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (468, 'Raionul سوروكا', 'Raionul Soroca', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (469, 'Şoldăneşti', 'Şoldăneşti', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (470, 'ريسكاني', 'Rîşcani', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (471, 'Rezina', 'Rezina', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (472, 'أورهي', 'Orhei', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (473, 'Raionul Ocniţa', 'Raionul Ocniţa', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (474, 'Anenii نوي', 'Anenii Noi', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (475, 'Nisporeni', 'Nisporeni', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (477, 'Sîngerei', 'Sîngerei', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (478, 'هينسشتي', 'Hînceşti', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (479, 'Raionul دوباساري', 'Raionul Dubasari', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (480, 'Raionul Causeni', 'Raionul Causeni', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (481, 'Laloveni', 'Laloveni', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (482, 'Glodeni', 'Glodeni', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (483, 'Floreşti', 'Floreşti', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (484, 'Făleşti', 'Făleşti', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (485, 'Drochia', 'Drochia', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (486, 'Briceni', 'Briceni', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (487, 'Criuleni', 'Criuleni', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (488, 'سيميشليا', 'Cimişlia', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (489, 'Cantemir', 'Cantemir', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (490, 'كاهول', 'Cahul', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (491, 'Donduşeni', 'Donduşeni', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (492, 'MUNICIPIUL بندر', 'Municipiul Bender', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (493, 'باسارابياسكا', 'Basarabeasca', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (494, 'MUNICIPIUL بالتي', 'Municipiul Balti', 50, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (495, 'مينسك', 'Minsk', 51, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (496, 'غوميل', 'Gomel', 51, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (498, 'فيتبسك', 'Vitebsk', 51, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (499, 'Grodnenskaya', 'Grodnenskaya', 51, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (500, 'ماغيليف', 'Mogilev', 51, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (501, 'مدينة مينسك', 'Minsk City', 51, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (502, 'بريست', 'Brest', 51, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (497, 'لفيفسكا أوبلاست', 'Lvivska Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (563, 'تشيركاسكا أوبلاست', 'Cherkaska Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (564, 'كيروفوهرادسكا أوبلاست', 'Kirovohradska Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (565, 'زيتوميرسكا أوبلاست', 'Zhytomyrska Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (566, 'فينيتسكا أوبلاست', 'Vinnytska Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (567, 'ريفنينسكا أوبلاست ', 'Rivnenska Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (568, 'زاكارباتيا أوبلاست', 'Zakarpattia Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (569, 'زابوريزهيا', 'Zaporizhia', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (570, 'أوديسا', 'Odessa', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (571, 'Donetska أوبلاست ', 'Donetska Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (572, 'جمهورية القرم', 'Republic of Crimea', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (573, 'كييف أوبلاست', 'Kyiv Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (574, 'خميلنياتسكا أوبلاست', 'Khmelnytska Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (575, 'Kharkivska أوبلاست ', 'Kharkivska Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (576, 'فولينسكا أوبلاست ', 'Volynska Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (577, 'ايفانو فرانكيفسكا أوبلاست', 'Ivano-Frankivska Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (578, 'خيرسوسكا أوبلاست', 'Khersonska Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (579, 'Dnipropetrovska أوبلاست ', 'Dnipropetrovska Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (580, 'ترنوبيلسكا أوبلاست', 'Ternopilska Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (581, 'Luhanska أوبلاست ', 'Luhanska Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (582, 'سومسكا أوبلاست ', 'Sumska Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (583, 'تشيرنيفتسي', 'Chernivtsi', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (584, 'بولتافسكا أوبلاست', 'Poltavska Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (585, 'جورود سيفاستوبول', 'Gorod Sevastopol', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (586, 'Mykolayivska أوبلاست ', 'Mykolayivska Oblast', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (587, 'تشيرنيهيف', 'Chernihiv', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (588, 'مدينة كييف', 'Kyiv City', 52, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (503, 'فنلندا الغربية', 'Western Finland', 53, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (504, 'Haeme', 'Haeme', 53, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (505, 'Lapponia', 'Lapponia', 53, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (506, 'أوستروبوتني الشمالية', 'Northern Ostrobothnia', 53, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (507, 'لابلاند', 'Lapland', 53, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (508, 'فنلندا الشرقية', 'Eastern Finland', 53, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (509, 'كاريليا الشمالية', 'North Karelia', 53, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (510, 'كاينو', 'Kainuu', 53, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (511, 'أولو', 'Oulu', 53, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (512, 'جنوب غرب فنلندا', 'Southwest Finland', 53, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (513, 'بايات هامي', 'Päijänne Tavastia', 53, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (514, 'فنلندا الوسطى', 'Central Finland', 53, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (515, 'أوسيما', 'Uusimaa', 53, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (516, 'بوهيانما الوسطى', 'Central Ostrobothnia', 53, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (517, 'ساتا كونتا', 'Satakunta', 53, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (518, 'الشمالية سافو', 'Northern Savo', 53, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (519, 'جنوب كاريليا', 'South Karelia', 53, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (520, 'سافو الجنوبية', 'Southern Savonia', 53, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (521, 'JUDETUL ألبا', 'Judetul Alba', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (522, 'تيليورمان', 'Teleorman', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (523, 'JUDETUL برايلا', 'Judetul Braila', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (524, 'في Judetul براسوف', 'Judetul Brasov', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (525, 'JUDETUL بوزاو', 'Judetul Buzau', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (526, 'JUDETUL سالاج', 'Judetul Salaj', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (527, 'JUDETUL دامبوفيتا', 'Judetul Dambovita', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (528, 'هونيدوارا', 'Hunedoara', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (529, 'فرانتشا', 'Vrancea', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (530, 'إيلفوف', 'Ilfov', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (531, 'JUDETUL اياسي', 'Judetul Iasi', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (532, 'فاسلوي', 'Vaslui', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (533, 'JUDETUL بوتوساني', 'Judetul Botosani', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (534, 'هارغيتا', 'Harghita', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (535, 'في Judetul ارجيس', 'Judetul Arges', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (536, 'مارامريس', 'Maramureş', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (537, 'JUDETUL ميهيدينتي', 'Judetul Mehedinti', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (538, 'اراد', 'Arad', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (539, 'JUDETUL فالسيا', 'Judetul Valcea', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (540, 'براهوفا', 'Prahova', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (541, 'ساتو ماري', 'Satu Mare', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (542, 'سوسيفا', 'Suceava', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (543, 'JUDETUL سيبيو', 'Judetul Sibiu', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (544, 'JUDETUL كالاراسي', 'Judetul Calarasi', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (545, 'كونستانتا', 'Constanta', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (546, 'بيهور', 'Bihor', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (547, 'JUDETUL موريس', 'Judetul Mures', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (548, 'JUDETUL ايالوميتا', 'Judetul Ialomita', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (549, 'JUDETUL باكاو', 'Judetul Bacau', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (550, 'غيورغيو', 'Giurgiu', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (551, 'JUDETUL كاراس سيفيرين', 'Judetul Caras-Severin', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (552, 'كوفاسنا', 'Covasna', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (553, 'JUDETUL كلوج', 'Judetul Cluj', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (554, 'JUDETUL جالاتي', 'Judetul Galati', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (555, 'تولسيا', 'Tulcea', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (556, 'JUDETUL نيمت', 'Judetul Neamt', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (557, 'مقاطعة أولت', 'Olt County', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (558, 'غورج', 'Gorj', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (559, 'JUDETUL تيميس', 'Judetul Timis', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (560, 'JUDETUL بيستريتا ناسود', 'Judetul Bistrita-Nasaud', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (561, 'دولج', 'Dolj', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (562, 'بوخارست', 'Bucuresti', 55, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (589, 'سزابولكس زاتمار بيريغ', 'Szabolcs-Szatmár-Bereg', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (590, 'بورسود أباوج زمبلين', 'Borsod-Abaúj-Zemplén', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (591, 'جاز ناجيكن سزولنوك', 'Jász-Nagykun-Szolnok', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (592, 'هاجو-بيهار', 'Hajdú-Bihar', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (593, 'كسونجراد megye', 'Csongrad megye', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (594, 'بيكيس', 'Bekes', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (595, 'هيفيز megye', 'Heves megye', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (596, 'باكس كيسكون', 'Bács-Kiskun', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (597, 'الآفات megye', 'Pest megye', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1784, 'سوموغي megye', 'Somogy megye', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1785, 'فيزبرم megye', 'Veszprem megye', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1786, 'فيجر', 'Fejér', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1787, 'زالة', 'Zala', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1788, 'جيور موسون سوبرون', 'Győr-Moson-Sopron', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1789, 'خدمات القيمة المضافة', 'Vas', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1790, 'نوجراد megye', 'Nograd megye', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1791, 'بارانيا', 'Baranya', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1792, 'تولنا megye', 'Tolna megye', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1793, 'كوماروم إسترغم', 'Komárom-Esztergom', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1794, 'بودابست', 'Budapest', 56, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (673, 'أوبستينا فراب سيست', 'Opstina Vrapciste', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (674, 'بلدية فالاندوفو', 'Valandovo Municipality', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (675, 'فيليس', 'Veles', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (676, 'تيتوفو', 'Tetovo', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (677, 'ستروميكا', 'Strumica', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (678, 'ستروغا', 'Struga', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (679, 'أوبستينا ستيب', 'Opstina Stip', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (680, 'أوبستينا كاربوس', 'Opstina Karpos', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (681, 'كومانوفو', 'Kumanovo', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (682, 'بلدية رسن', 'Resen Municipality', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (683, 'أوبستينا رادوفيس', 'Opstina Radovis', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (684, 'أوبستينا Probistip', 'Opstina Probistip', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (685, 'بريليب', 'Prilep', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (686, 'أوبستينا يبكوفو', 'Opstina Lipkovo', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (687, 'أوهريد', 'Ohrid', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (688, 'نوفو سيلو', 'Novo Selo', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (689, 'نيغوتينو', 'Negotino', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (690, 'كراتوفو', 'Kratovo', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (691, 'أوبستينا كوكاني', 'Opstina Kocani', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (692, 'أوبستينا كيشيفو', 'Opstina Kicevo', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (693, 'كافادارشي', 'Kavadarci', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (694, 'بوجوفينيه', 'Bogovinje', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (695, 'جرادسكو', 'Gradsko', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (696, 'غوستيفار', 'Gostivar', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (697, 'غيفيغليا', 'Gevgelija', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (698, 'كيسيلا فودا', 'Kisela Voda', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (699, 'حرم', 'Debar', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (700, 'ماكيدونسكي برود', 'Makedonski Brod', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (701, 'بوجدانسي', 'Bogdanci', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (702, 'بيتولا', 'Bitola', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (703, 'ديميرهيسار', 'Demir Hisar', 57, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (598, 'بانسكوبيستريكي KRAJ', 'Banskobystricky kraj', 58, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (599, 'بريزوفسكى KRAJ', 'Presovsky kraj', 58, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (600, 'كوسيكي KRAJ', 'Kosicky kraj', 58, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1795, 'نتريانسكي KRAJ', 'Nitriansky kraj', 58, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1796, 'زيلينسكي KRAJ', 'Zilinsky kraj', 58, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1797, 'براتيسلافسكي KRAJ', 'Bratislavsky kraj', 58, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1798, 'ترنيسكي KRAJ', 'Trnavsky kraj', 58, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1799, 'ترينسيانسكي KRAJ', 'Trenciansky kraj', 58, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (658, 'محافظة مازوفيا', 'Masovian Voivodeship', 59, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (659, 'Subcarpathian فويفود', 'Subcarpathian Voivodeship', 59, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (660, 'محافظة لوبلين', 'Lublin Voivodeship', 59, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (661, 'محافظة بولندا الصغرى', 'Lesser Poland Voivodeship', 59, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (662, 'سويتوكرزيسكي', 'Świętokrzyskie', 59, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (663, 'مقاطعتها', 'Podlasie', 59, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (664, 'محافظة فارمينسكو-مازورسكي', 'Warmian-Masurian Voivodeship', 59, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (665, 'لودز فويفود', 'Łódź Voivodeship', 59, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1814, 'محافظة سيليزيا', 'Silesian Voivodeship', 59, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1815, 'كوجاوسكو بومورسكي', 'Kujawsko-Pomorskie', 59, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1816, 'أقل محافظة سيليزيا', 'Lower Silesian Voivodeship', 59, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1817, 'بولندا الكبرى', 'Greater Poland Voivodeship', 59, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1818, 'غرب محافظة بومرسكي', 'West Pomeranian Voivodeship', 59, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1819, 'وبوش', 'Lubusz', 59, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1820, 'أوبول فويفود', 'Opole Voivodeship', 59, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1821, 'كلب صغير طويل الشعر فويفود', 'Pomeranian Voivodeship', 59, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (666, 'فينمارك Fylke', 'Finnmark Fylke', 60, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (667, 'ترومس Fylke', 'Troms Fylke', 60, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1832, 'هوردالاند Fylke', 'Hordaland Fylke', 60, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1833, 'آكيرشوس', 'Akershus', 60, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1834, 'fylke رومسدال أكثر عوج', 'More og Romsdal fylke', 60, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1835, 'أوبلاند', 'Oppland', 60, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1836, 'هدمارك', 'Hedmark', 60, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1837, 'روغالاند Fylke', 'Rogaland Fylke', 60, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1838, 'بوسكيرود', 'Buskerud', 60, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1839, 'فيستفولد', 'Vestfold', 60, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1840, 'سترة-أغدر Fylke', 'Vest-Agder Fylke', 60, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1841, 'نور-ترونديلاغ Fylke', 'Nord-Trondelag Fylke', 60, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1842, 'سوجن فيوردان Fylke', 'Sogn og Fjordane Fylke', 60, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1843, 'تيليمارك', 'Telemark', 60, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1844, 'نوردلاند Fylke', 'Nordland Fylke', 60, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1845, 'أوست-أغدر', 'Aust-Agder', 60, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1846, 'تروندلاج Fylke', 'Sor-Trondelag Fylke', 60, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1847, 'أوستفولد', 'Østfold', 60, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1848, 'مقاطعة أوسلو', 'Oslo County', 60, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (672, 'فويفودينا', 'Vojvodina', 61, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (707, 'زامبيزي منطقة', 'Zambezi Region', 63, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2059, 'خماس', 'Khomas', 63, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2060, 'إيرونغو', 'Erongo', 63, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2061, 'أوشيكوتو', 'Oshikoto', 63, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2062, 'كافانغو الشرق', 'Kavango East', 63, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2063, 'كونين', 'Kunene', 63, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2064, 'أوتجوزوندتوبا', 'Otjozondjupa', 63, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2065, 'أوشانا', 'Oshana', 63, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2066, 'كاراس', 'Karas', 63, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2067, 'أوموساتي', 'Omusati', 63, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2068, 'أوماهيكي', 'Omaheke', 63, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (711, 'ماتابيليلاند الشمالية', 'Matabeleland North', 64, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (712, 'مقاطعة ميدلاندز', 'Midlands Province', 64, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (713, 'هراري', 'Harare', 64, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (714, 'محافظة جنوب ماتابيليلاند', 'Matabeleland South Province', 64, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (715, 'ماشونالاند الغربية', 'Mashonaland West', 64, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (716, 'بولاوايو', 'Bulawayo', 64, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (724, 'Ndzuwani', 'Ndzuwani', 66, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (725, 'القمر الكبرى', 'Grande Comore', 66, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (727, 'المنطقة الوسطى', 'Central Region', 67, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (728, 'المنطقة الجنوبية', 'Southern Region', 67, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (729, 'بيريا', 'Berea', 68, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (730, 'ماسيرو', 'Maseru', 68, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (731, 'يرايب', 'Leribe', 68, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (732, 'المقاطعة المركزية', 'Central District', 69, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (733, 'منطقة كوينينج', 'Kweneng District', 69, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (734, 'شمال غرب', 'North-West', 69, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (735, 'الجنوب الشرقي', 'South-East', 69, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (736, 'شمال شرق', 'North-East', 69, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (737, 'منطقة فيلهمز بلينز', 'Plaines Wilhems District', 70, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (738, 'منطقة بامبليماوسيس', 'Pamplemousses District', 70, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (739, 'منطقة النهر الأسود', 'Black River District', 70, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (740, 'لويس منطقة الميناء', 'Port Louis District', 70, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (741, 'منطقة موكا', 'Moka District', 70, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (742, 'ريفيير دو منطقة ريمبارت', 'Riviere du Rempart District', 70, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (743, 'منطقة سافان', 'Savanne District', 70, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (744, 'منطقة فلاك', 'Flacq District', 70, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (915, 'رودريغز', 'Rodrigues', 70, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (745, 'منطقة هوهو', 'Hhohho District', 71, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (746, 'منطقة مانزيني', 'Manzini District', 71, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (747, 'منطقة وبومبو', 'Lubombo District', 71, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (748, 'غوتنغ', 'Gauteng', 73, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (749, 'محافظة الشمالية الغربية', 'Province of North West', 73, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (750, 'مبومالانغا', 'Mpumalanga', 73, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (751, 'كوازولو ناتال', 'KwaZulu-Natal', 73, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (752, 'دولة أورانج الحرة', 'Orange Free State', 73, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (753, 'كيب الشمالية', 'Northern Cape', 73, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (754, 'الكاب الشرقية', 'Eastern Cape', 73, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (755, 'ليمبوبو', 'Limpopo', 73, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (756, 'مقاطعة الكاب الغربية', 'Province of the Western Cape', 73, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (757, 'تيتي', 'Tete', 74, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (758, 'مقاطعة مابوتو', 'Maputo Province', 74, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (759, 'في Provincia De زامبيزيا', 'Provincia de Zambezia', 74, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (760, 'مقاطعة دلغادو كابو', 'Cabo Delgado Province', 74, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (761, 'نامبولا', 'Nampula', 74, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (762, 'مقاطعة إنهامبان', 'Inhambane Province', 74, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (763, 'سيداد دي مابوتو', 'Cidade de Maputo', 74, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (764, 'مقاطعة نياسا', 'Niassa Province', 74, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (765, 'مقاطعة مانيكا', 'Manica Province', 74, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (766, 'محافظة غزة', 'Gaza Province', 74, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (767, 'مقاطعة سوفالا', 'Sofala Province', 74, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (768, 'قاشقادري', 'Qashqadaryo', 76, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (812, 'سمرقند Viloyati', 'Samarqand Viloyati', 76, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (890, 'طشقند الشهري', 'Toshkent Shahri', 76, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (769, 'فوكيت', 'Phuket', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (770, 'Changwat سوخوثاي', 'Changwat Sukhothai', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (771, 'Changwat شيانغ راي', 'Changwat Chiang Rai', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (776, 'Changwat فيتشابوري', 'Changwat Phetchaburi', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (777, 'مقاطعة تشيانج ماي', 'Chiang Mai Province', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (778, 'Changwat ترانج', 'Changwat Trang', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (779, 'Changwat تاك', 'Changwat Tak', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (780, 'Changwat سورات ثاني', 'Changwat Surat Thani', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (781, 'Changwat شومفون', 'Changwat Chumphon', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (782, 'Changwat راتشابوري', 'Changwat Ratchaburi', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (783, 'Changwat رانونج', 'Changwat Ranong', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (784, 'Changwat براشواب خيرى خان', 'Changwat Prachuap Khiri Khan', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (785, 'Changwat فاياو', 'Changwat Phayao', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (786, 'Changwat فانجنجا', 'Changwat Phangnga', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (787, 'Changwat ناخون سي ثامارات', 'Changwat Nakhon Si Thammarat', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (788, 'Changwat امفون', 'Changwat Lamphun', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (789, 'Changwat ماي هونغ سون', 'Changwat Mae Hong Son', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (790, 'Changwat امبانج', 'Changwat Lampang', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (791, 'Changwat كرابي', 'Changwat Krabi', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (792, 'Changwat كامبينغ فيت', 'Changwat Kamphaeng Phet', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (793, 'Changwat كانشانابوري', 'Changwat Kanchanaburi', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (794, 'Changwat ساموت سونغخرام', 'Changwat Samut Songkhram', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (976, 'Changwat لوب بوري', 'Changwat Lop Buri', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (977, 'Changwat ناخون راتشاسيما', 'Changwat Nakhon Ratchasima', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (978, 'Changwat سا كايو', 'Changwat Sa Kaeo', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (979, 'Changwat فيتشابون', 'Changwat Phetchabun', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (980, 'Changwat تشايافوم', 'Changwat Chaiyaphum', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (981, 'Changwat ناخون فانوم', 'Changwat Nakhon Phanom', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (982, 'Changwat خون كاين', 'Changwat Khon Kaen', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (983, 'Changwat نونغ خاي', 'Changwat Nong Khai', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (984, 'Changwat بوريرام', 'Changwat Buriram', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (985, 'Changwat سورين', 'Changwat Surin', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (986, 'Changwat ياسوثون', 'Changwat Yasothon', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (987, 'Changwat يالا', 'Changwat Yala', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (988, 'Changwat ساكون ناخون', 'Changwat Sakon Nakhon', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (989, 'Changwat فرا ناخون سي أيوثايا', 'Changwat Phra Nakhon Si Ayutthaya', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (990, 'Changwat أوتاراديت', 'Changwat Uttaradit', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (991, 'Changwat سيساكيت', 'Changwat Sisaket', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (992, 'Changwat عوتاي ثاني', 'Changwat Uthai Thani', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (993, 'Changwat أودون ثاني', 'Changwat Udon Thani', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (994, 'Changwat أوبون راتشاثاني', 'Changwat Ubon Ratchathani', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (995, 'Changwat ترات', 'Changwat Trat', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (996, 'بانكوك', 'Bangkok', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (997, 'Changwat فيشيت', 'Changwat Phichit', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (998, 'Changwat باثوم ثاني', 'Changwat Pathum Thani', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (999, 'Changwat سوفان بوري', 'Changwat Suphan Buri', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1000, 'Changwat سونغكلا', 'Changwat Songkhla', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1001, 'Changwat تشون بورى', 'Changwat Chon Buri', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1002, 'Changwat الغناء بوري', 'Changwat Sing Buri', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1003, 'Changwat روي إت', 'Changwat Roi Et', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1004, 'Changwat ساتون', 'Changwat Satun', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1005, 'Changwat سارة بوري', 'Changwat Sara Buri', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1006, 'Changwat تشاي نات', 'Changwat Chai Nat', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1007, 'Changwat ساموت ساخون', 'Changwat Samut Sakhon', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1008, 'Changwat ساموت براكان', 'Changwat Samut Prakan', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1009, 'Changwat ناخون باتوم', 'Changwat Nakhon Pathom', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1010, 'Changwat ناراثيوات', 'Changwat Narathiwat', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1011, 'Changwat رايونج', 'Changwat Rayong', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1012, 'Changwat براشين بوري', 'Changwat Prachin Buri', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1013, 'Changwat فراى', 'Changwat Phrae', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1014, 'Changwat فيتسانولوك', 'Changwat Phitsanulok', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1015, 'Changwat مها ساراخام', 'Changwat Maha Sarakham', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1016, 'Changwat فاتهالونج', 'Changwat Phatthalung', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1017, 'Changwat باتاني', 'Changwat Pattani', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1018, 'Changwat نونثابوري', 'Changwat Nonthaburi', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1019, 'Changwat ناخون نايوك', 'Changwat Nakhon Nayok', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1020, 'Changwat نونغ بوا Lamphu', 'Changwat Nong Bua Lamphu', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1021, 'Changwat ناخون صوان', 'Changwat Nakhon Sawan', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1022, 'Changwat نان', 'Changwat Nan', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1023, 'Changwat موكداهان', 'Changwat Mukdahan', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1024, 'Changwat ويي', 'Changwat Loei', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1025, 'Changwat كالاسين', 'Changwat Kalasin', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1026, 'Changwat شانثابوري', 'Changwat Chanthaburi', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1027, 'Changwat أنغ ثونغ', 'Changwat Ang Thong', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1028, 'Changwat شاشوينجساو', 'Changwat Chachoengsao', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1029, 'Changwat بوينج اساسه', 'Changwat Bueng Kan', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1030, 'Changwat أمنات تشاروين', 'Changwat Amnat Charoen', 77, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (772, 'زابل', 'Zabul', 78, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (773, 'قندهار', 'Kandahar', 78, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (774, 'كابول', 'Kabul', 78, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (775, 'هرات', 'Herat', 78, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (796, 'البنجاب', 'Punjab', 79, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (797, 'السند', 'Sindh', 79, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (798, 'خيبر باختونخوا', 'Khyber Pakhtunkhwa', 79, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (799, 'جيلجيت بالتستان', 'Gilgit-Baltistan', 79, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (800, 'بلوشستان', 'Balochistan', 79, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (801, 'آزاد كشمير', 'Azad Kashmir', 79, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (802, 'المناطق القبلية الخاضعة للإدارة الاتحادية', 'Federally Administered Tribal Areas', 79,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (803, 'منطقة العاصمة اسلام اباد', 'Islamabad Capital Territory', 79, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (804, 'قسم دكا', 'Dhaka Division', 80, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (805, 'قسم سيلهيت', 'Sylhet Division', 80, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (806, 'قسم راجشاهي', 'Rajshahi Division', 80, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (807, 'شيتاغونغ', 'Chittagong', 80, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (808, 'قسم رانجبور', 'Rangpur Division', 80, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (809, 'قسم خولنا', 'Khulna Division', 80, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (874, 'قسم باريسال', 'Barisal Division', 80, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (810, 'سومطرة الشمالية', 'North Sumatra', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (811, 'اتشيه', 'Aceh', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1031, 'يوجياكرتا', 'Yogyakarta', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1032, 'جافا الشرق', 'East Java', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1033, 'جاوة الوسطى', 'Central Java', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1034, 'جاكرتا', 'Jakarta', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1035, 'جنوب سولاويزي', 'South Sulawesi', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1036, 'بالي', 'Bali', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1037, 'شمال سولاويزي', 'North Sulawesi', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1038, 'بابوا', 'Papua', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1039, 'مالوكو الشمالية', 'North Maluku', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1040, 'كاليمانتان الشرقية', 'East Kalimantan', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1041, 'جزر رياو', 'Riau Islands', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1042, 'يافا الغربيه', 'West Java', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1043, 'نوسا الغربية', 'West Nusa Tenggara', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1044, 'كاليمانتان الجنوبية', 'South Kalimantan', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1045, 'جنوب سومطرة', 'South Sumatra', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1046, 'سولاويسي الوسطى', 'Central Sulawesi', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1047, 'لامبونج', 'Lampung', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1048, 'كاليمانتان الغربية', 'West Kalimantan', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1049, 'جامبي', 'Jambi', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1050, 'بانتين', 'Banten', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1051, 'كاليمانتان الوسطى', 'Central Kalimantan', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1052, 'رياو', 'Riau', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1053, 'سومطرة الغربية', 'West Sumatra', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1054, 'سولاويزي الغربي', 'West Sulawesi', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1055, 'بنجكولو', 'Bengkulu', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1056, 'نوسا تينجارا الشرقية', 'East Nusa Tenggara', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1057, 'مالوكو', 'Maluku', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1058, 'جنوب سولاويزي', 'Southeast Sulawesi', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1059, 'جزر بانجكا', 'Bangka–Belitung Islands', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1060, 'جورونتالو', 'Gorontalo', 81, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (813, 'غورنو باداخشان', 'Gorno-Badakhshan', 82, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (891, 'Viloyati سوغد', 'Viloyati Sughd', 82, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (814, 'كيدا', 'Kedah', 83, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1098, 'باهانج', 'Pahang', 83, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1099, 'ولاية سيلانجور', 'Selangor', 83, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1100, 'جوهور', 'Johor', 83, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1101, 'ملقا', 'Melaka', 83, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1102, 'ساراواك', 'Sarawak', 83, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1103, 'صباح', 'Sabah', 83, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1104, 'بيراك', 'Perak', 83, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1105, 'نيجيري سمبيلان', 'Negeri Sembilan', 83, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1106, 'كيلانتان', 'Kelantan', 83, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1107, 'كوالا لامبور', 'Kuala Lumpur', 83, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1108, 'بينانغ', 'Penang', 83, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1109, 'لابوان', 'Labuan', 83, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1110, 'تيرينجانو', 'Terengganu', 83, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1111, 'برليس', 'Perlis', 83, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2604, 'بوتراجايا', 'Putrajaya', 83, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (815, 'محافظة الغربية', 'Western Province', 84, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (816, 'المنطقة الجنوبية', 'Southern Province', 84, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (817, 'محافظة أوفا', 'Province of Uva', 84, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (818, 'محافظة ساباراغاموا', 'Province of Sabaragamuwa', 84, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (819, 'المنطقة الوسطى', 'Central Province', 84, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (820, 'المقاطعة الشمالية الغربية', 'North Western Province', 84, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (821, 'محافظة شمال الوسطى', 'North Central Province', 84, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (822, 'المنطقة الشرقية', 'Eastern Province', 84, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (823, 'تيمفو Dzongkhag', 'Thimphu Dzongkhag', 85, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (824, 'منطقة Chukha', 'Chukha District', 85, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (875, 'مقاطعة مونغار', 'Mongar District', 85, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (825, 'تاميل نادو', 'Tamil Nadu', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (826, 'ماهاراشترا', 'Maharashtra', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (827, 'كارناتاكا', 'Karnataka', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (828, 'ولاية اندرا براديش', 'Andhra Pradesh', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (829, 'هاريانا', 'Haryana', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (830, 'بنغال الغربية', 'West Bengal', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (831, 'غوجارات', 'Gujarat', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (832, 'تيلانجانا', 'Telangana', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (833, 'الاتحاد إقليم بودوتشيري', 'Union Territory of Puducherry', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (834, 'ماديا براديش', 'Madhya Pradesh', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (835, 'غوا', 'Goa', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (836, 'ولاية كيرالا', 'Kerala', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (837, 'ولاية اوتار براديش', 'Uttar Pradesh', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (838, 'هيماشال براديش', 'Himachal Pradesh', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (839, 'كشمير', 'Kashmir', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (840, 'راجستان', 'Rajasthan', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (841, 'تريبورا', 'Tripura', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (842, 'ميغالايا', 'Meghalaya', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (843, 'أسام', 'Assam', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (844, 'أوتارانتشال', 'Uttarakhand', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (845, 'البنجاب', 'Punjab', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (846, 'أوديشا', 'Odisha', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (847, 'بيهار', 'Bihar', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (848, 'سيكيم', 'Sikkim', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (849, 'دادرا وناغار هافيلي', 'Dadra and Nagar Haveli', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (850, 'إقليم العاصمة الوطنية دلهي', 'National Capital Territory of Delhi', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (851, 'ميزورام', 'Mizoram', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (852, 'جهارخاند', 'Jharkhand', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (853, 'تشهاتيسجاره', 'Chhattisgarh', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (854, 'الاتحاد إقليم جزر أندامان ونيكوبار', 'Union Territory of Andaman and Nicobar Islands', 86,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (855, 'ناجالاند', 'Nagaland', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (856, 'Laccadives', 'Laccadives', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (857, 'اروناتشال براديش', 'Arunachal Pradesh', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (858, 'مانيبور', 'Manipur', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (859, 'دامان وديو', 'Daman and Diu', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (860, 'شانديغار', 'Chandigarh', 86, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (861, 'منطقة التبت ذاتية الحكم', 'Tibet Autonomous Region', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (862, 'يونان', 'Yunnan', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (863, 'تشينغهاى', 'Qinghai', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (864, 'منطقة شينجيانغ ذاتية الحكم الويغورية', 'Xinjiang Uyghur Autonomous Region', 87,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (916, 'قويتشو', 'Guizhou', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1112, 'سيتشوان', 'Sichuan', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1113, 'هونان', 'Hunan', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1114, 'خبى', 'Hebei', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1115, 'خنان', 'Henan', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1116, 'شنغهاي', 'Shanghai', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1117, 'تشجيانغ', 'Zhejiang', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1118, 'جيانغسو', 'Jiangsu', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1119, 'قوانغدونغ', 'Guangdong', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1120, 'شاندونغ', 'Shandong', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1121, 'فوجيان', 'Fujian', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1122, 'قانسو', 'Gansu', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1123, 'هوبى', 'Hubei', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1124, 'شانشى', 'Shanxi', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (1125, 'منطقة قوانغشى ذاتية الحكم لقومية تشوانغ', 'Guangxi Zhuang Autonomous Region', 87,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1126, 'تشونغتشينغ', 'Chongqing', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (1127, 'منطقة قوانغشى ذاتية الحكم لقومية هوى Ningsia', 'Ningsia Hui Autonomous Region', 87,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1128, 'جيانغشى', 'Jiangxi', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1129, 'شنشى', 'Shaanxi', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1130, 'انهوى', 'Anhui', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1131, 'هاينان', 'Hainan', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1132, 'بكين', 'Beijing', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1133, 'تيانجين', 'Tianjin', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1134, 'منطقة منغوليا الداخلية', 'Inner Mongolia Autonomous Region', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1135, 'لياونينغ', 'Liaoning', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1234, 'هيلونغجيانغ', 'Heilongjiang', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1235, 'جيلين', 'Jilin', 87, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (865, 'كافو أتول', 'Kaafu Atoll', 88, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (866, 'المنطقة الغربية', 'Western Region', 90, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (867, 'المنطقة الوسطى', 'Central Region', 90, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (868, 'المنطقة الشرقية', 'Eastern Region', 90, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (869, 'بعيدا الغربية', 'Far Western', 90, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (870, 'ماغواي منطقة', 'Magway Region', 91, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (871, 'يانجون منطقة', 'Yangon Region', 91, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (872, 'ماندالاي منطقة', 'Mandalay Region', 91, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (873, 'الدولة كايا', 'Kayah State', 91, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (892, 'غوفي ألتاي ايماج', 'Govi-Altay Aymag', 92, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (893, 'هوفزجول ايماج', 'Hovsgol Aymag', 92, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (894, 'هوود', 'Hovd', 92, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (895, 'بيان-OElgiy ايماج', 'Bayan-OElgiy Aymag', 92, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1222, 'الشرق غوبي ايماج', 'East Gobi Aymag', 92, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1223, 'أولان باتور الساخن', 'Ulaanbaatar Hot', 92, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1224, 'آرهانجاي ايماج', 'Arhangay Aymag', 92, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1225, 'جوفي الأوسط', 'Middle Govĭ', 92, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1226, 'سيلينجي ايماج', 'Selenge Aymag', 92, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1227, 'هينتي ايماج', 'Hentiy Aymag', 92, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1228, 'Aimak الوسطى', 'Central Aimak', 92, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1229, 'أومنجوفي', 'Ömnögovĭ', 92, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1230, 'جوفي سامبر', 'Govi-Sumber', 92, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1231, 'بيانهونجور ايماج', 'Bayanhongor Aymag', 92, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1232, 'ساهباتار ايماج', 'Suhbaatar Aymag', 92, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1233, 'أوفورهانجاي', 'Övörhangay', 92, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (907, 'ايسيك كول منطقة', 'Issyk-Kul Region', 93, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (908, 'أوبلاست جلال أباد', 'Jalal-Abad oblast', 93, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (909, 'تالاس', 'Talas', 93, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (910, 'نارين نوفغورود', 'Naryn oblast', 93, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (911, 'أوش Oblasty', 'Osh Oblasty', 93, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (912, 'Chuyskaya أوبلاست ', 'Chuyskaya Oblast', 93, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (913, 'جورود بيشكيك', 'Gorod Bishkek', 93, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (914, 'باتكن', 'Batken', 93, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (917, 'تينه نغي آن', 'Tinh Nghe An', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (918, 'تينه سوك ترانج', 'Tinh Soc Trang', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (919, 'تينه ترا فينه', 'Tinh Tra Vinh', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (920, 'تينه توين كوانج', 'Tinh Tuyen Quang', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (921, 'تينه فينه لونج', 'Tinh Vinh Long', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (922, 'تينه باك نينه', 'Tinh Bac Ninh', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (923, 'تينه ين باي', 'Tinh Yen Bai', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (924, 'تينه ها تينه', 'Tinh Ha Tinh', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (925, 'تينه كين جيانج', 'Tinh Kien Giang', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (926, 'تينه GJong ناي', 'Tinh GJong Nai', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (927, 'تينه با ريا فونج تاو', 'Tinh Ba Ria-Vung Tau', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (928, 'تينه لاو كاي', 'Tinh Lao Cai', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (929, 'هاو جيانج', 'Hau Giang', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (930, 'تينه فينه فوك', 'Tinh Vinh Phuc', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (931, 'تينه فو ثو', 'Tinh Phu Tho', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (932, 'تينه نينه بينه', 'Tinh Ninh Binh', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (933, 'تينه نام GJinh', 'Tinh Nam GJinh', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (934, 'ثانه فو هانوي', 'Thanh Pho Ha Noi', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (935, 'تينه بينه GJinh', 'Tinh Binh GJinh', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (936, 'تينه فو ين', 'Tinh Phu Yen', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (937, 'تينه تاي نينه', 'Tinh Tay Ninh', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (938, 'تينه لانج سون', 'Tinh Lang Son', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (939, 'تينه ها جيانج', 'Tinh Ha Giang', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (940, 'تينه تين جيانج', 'Tinh Tien Giang', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (941, 'تينه بينه دونغ', 'Tinh Binh Duong', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (942, 'تينه ثوا ثين هوي', 'Tinh Thua Thien-Hue', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (943, 'تينه كوانج نام', 'Tinh Quang Nam', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (944, 'مدينة هو تشي منه', 'Ho Chi Minh City', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (945, 'تينه هونغ الين', 'Tinh Hung Yen', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (946, 'تينه ثانه هوا', 'Tinh Thanh Hoa', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (947, 'تينه التايلاندية نجوين', 'Tinh Thai Nguyen', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (948, 'تينه بينه التايلاندية', 'Tinh Thai Binh', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (949, 'لونغ آن', 'Long An', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (950, 'تينه سون لا', 'Tinh Son La', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (951, 'تينه كوانغ تري', 'Tinh Quang Tri', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (952, 'تينه كوانغ بينه', 'Tinh Quang Binh', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (953, 'تينه كوانج نجاي', 'Tinh Quang Ngai', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (954, 'غيا لاي', 'Gia Lai', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (955, 'تينه ها نام', 'Tinh Ha Nam', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (956, 'تينه بينه ثوان', 'Tinh Binh Thuan', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (957, 'تينه خانه هوا', 'Tinh Khanh Hoa', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (958, 'تينه هوا بينه', 'Tinh Hoa Binh', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (959, 'تينه كا ماو', 'Tinh Ca Mau', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (960, 'تينه باك جيانج', 'Tinh Bac Giang', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (961, 'آن جيانغ', 'An Giang', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (962, 'تينه لاي تشاو', 'Tinh Lai Chau', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (963, 'كون توم', 'Kon Tum', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (964, 'تينه GJong ثاب', 'Tinh GJong Thap', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (965, 'ثانه فو ثو', 'Thanh Pho Can Tho', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (966, 'ثانه فو هاي فونغ', 'Thanh Pho Hai Phong', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (967, 'تينه هاي دونج', 'Tinh Hai Duong', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (968, 'داك نونغ', 'Dak Nong', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (969, 'تينه ديان بيان', 'Tinh Dien Bien', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (970, 'ثانه فو GJA نانغ', 'Thanh Pho GJa Nang', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (971, 'تينه لام GJong', 'Tinh Lam GJong', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (972, 'تينه كاو بانج', 'Tinh Cao Bang', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (973, 'تينه GJak لاك', 'Tinh GJak Lak', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (974, 'تينه بن تري', 'Tinh Ben Tre', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (975, 'تينه باك ليو', 'Tinh Bac Lieu', 97, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1061, 'ديلي', 'Dili', 98, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1062, 'فينتيان', 'Vientiane', 99, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1063, 'خاموان', 'Khammouan', 99, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1064, 'مقاطعة يون لين', 'Yunlin County', 100, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1065, 'هوالين', 'Hualien', 100, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1066, 'تشانجوا', 'Changhua', 100, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1067, 'مياولي', 'Miaoli', 100, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1068, 'كاوشيونغ', 'Kaohsiung', 100, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1069, 'مدينة تايتشونغ', 'Taichung City', 100, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1070, 'تاويوان', 'Taoyuan', 100, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1071, 'تايبيه الجديدة', 'New Taipei', 100, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1072, 'تايتونج', 'Taitung', 100, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1073, 'تاينان', 'Tainan', 100, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1074, 'نانتو', 'Nantou', 100, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1075, 'بينغتونغ', 'Pingtung', 100, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1076, 'شينتشو', 'Hsinchu', 100, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1077, 'ييلان', 'Yilan', 100, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1078, 'مقاطعة شينتشو', 'Hsinchu County', 100, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1079, 'كيلونغ', 'Keelung', 100, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1080, 'جايبور', 'Chiayi', 100, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1081, 'شبه جزيرة زامبوانغا', 'Zamboanga Peninsula', 101, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1082, 'لوزون الوسطى', 'Central Luzon', 101, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1083, 'ايلوكوس', 'Ilocos', 101, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1084, 'فيساياس الغربية', 'Western Visayas', 101, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1085, 'CALABARZON', 'Calabarzon', 101, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1086, 'فيساياس الوسطى', 'Central Visayas', 101, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1087, 'منطقة العاصمة الوطنية', 'National Capital Region', 101, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1088, 'كاراغا', 'Caraga', 101, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1089, 'دافاو', 'Davao', 101, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1090, 'فيساياس الشرقية', 'Eastern Visayas', 101, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1091, 'Soccsksargen', 'Soccsksargen', 101, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1092, 'وادي كاجايان', 'Cagayan Valley', 101, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1093, 'MIMAROPA', 'Mimaropa', 101, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1094, 'بيكول', 'Bicol', 101, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (1095, 'منطقة الحكم الذاتي في مينداناو مسلم', 'Autonomous Region in Muslim Mindanao', 101,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1096, 'مينداناو الشمالية', 'Northern Mindanao', 101, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1097, 'كورديليرا', 'Cordillera', 101, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1136, 'وونغ تاي سين', 'Wong Tai Sin', 102, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1137, 'جنوبي', 'Southern', 102, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1138, 'مدينة كولون', 'Kowloon City', 102, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1139, 'منطقة لونغ يوين', 'Yuen Long District', 102, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1140, 'شام شوي بو', 'Sham Shui Po', 102, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1141, 'شا تين', 'Sha Tin', 102, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1142, 'وان تشاي', 'Wanchai', 102, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1143, 'شمال', 'North', 102, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2606, 'ياو تسيم مونغ', 'Yau Tsim Mong', 102, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2610, 'الشرقية', 'Eastern', 102, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2615, 'الوسطى والمنطقة الغربية', 'Central and Western District', 102, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1144, 'منطقة توتونغ', 'Tutong District', 103, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1145, 'منطقة بيليت', 'Belait District', 103, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1146, 'بروناي ومنطقة موارا', 'Brunei and Muara District', 103, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1650, 'فيلا ريال', 'Vila Real', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1147, 'منطقة تمبرونج', 'Temburong District', 103, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1148, 'بنوم بنه', 'Phnom Penh', 105, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1149, 'كاندال', 'Kandal', 105, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1150, 'بريه سيهانوك', 'Preah Sihanouk', 105, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1151, 'باتامبانج', 'Battambang', 105, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1152, 'جيونج جي دو', 'Gyeonggi-do', 106, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1153, 'تشانج تشونج نام دو', 'Chungcheongnam-do', 106, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1154, 'جانج وون دو', 'Gangwon-do', 106, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1155, 'جيونج سانج نام دو', 'Gyeongsangnam-do', 106, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1156, 'تشانغتشيونغ الشمالية', 'Chungcheongbuk-do', 106, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1157, 'غوانغجو', 'Gwangju', 106, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1158, 'جولا نام دو', 'Jeollanam-do', 106, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1159, 'جيونجسانجبك دو', 'Gyeongsangbuk-do', 106, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1160, 'أولسان', 'Ulsan', 106, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1161, 'دايجون', 'Daejeon', 106, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1162, 'دايجو', 'Daegu', 106, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1163, 'سيول', 'Seoul', 106, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1164, 'إنتشون', 'Incheon', 106, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1165, 'بوسان', 'Busan', 106, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1166, 'جولابوك دو', 'Jeollabuk-do', 106, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1167, 'جيجو دو', 'Jeju-do', 106, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1168, 'واكاياما', 'Wakayama', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1169, 'كاناغاوا', 'Kanagawa', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1170, 'هيوغو', 'Hyōgo', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1171, 'نيجاتا', 'Niigata', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1172, 'جيفو', 'Gifu', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1173, 'فوكوكا', 'Fukuoka', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1174, 'ايباراكي', 'Ibaraki', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1175, 'كوتشي', 'Kochi', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1176, 'سايتاما', 'Saitama', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1177, 'أوكيناوا', 'Okinawa', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1178, 'أيشي', 'Aichi', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1179, 'توتوري', 'Tottori', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1180, 'طوكيو', 'Tokyo', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1181, 'مي', 'Mie', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1182, 'إيشيكاوا', 'Ishikawa', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1183, 'كيوتو', 'Kyoto', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1184, 'كوماموتو', 'Kumamoto', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1185, 'توياما', 'Toyama', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1186, 'شيمان', 'Shimane', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1187, 'ياماغوتشي', 'Yamaguchi', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1188, 'أوساكا', 'Ōsaka', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1189, 'نارا', 'Nara', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1190, 'فوكوشيما كين', 'Fukushima-ken', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1191, 'ياماناشي', 'Yamanashi', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1192, 'أوكاياما', 'Okayama', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1193, 'شيزوكا', 'Shizuoka', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1194, 'توتشيغي', 'Tochigi', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1195, 'ناغانو', 'Nagano', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1196, 'كاغاوا', 'Kagawa', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1197, 'ناغازاكي', 'Nagasaki', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1198, 'أويتا', 'Oita', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1199, 'ساغا', 'Saga Prefecture', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1200, 'هيروشيما', 'Hiroshima', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1201, 'ياماغاتا', 'Yamagata', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1202, 'فوكوي', 'Fukui', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1203, 'ميازاكي', 'Miyazaki', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1204, 'غونما', 'Gunma', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1205, 'توكوشيما', 'Tokushima', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1206, 'شيبا', 'Chiba', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1207, 'كاجوشيما', 'Kagoshima', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1208, 'محافظة شيغا', 'Shiga Prefecture', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1209, 'ايمى', 'Ehime', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1237, 'أوموري', 'Aomori', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1266, 'اكيتا', 'Akita', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1267, 'مياجي', 'Miyagi', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1268, 'ايواتي', 'Iwate', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1273, 'هوكايدو', 'Hokkaido', 107, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1210, 'بيونغ يانغ', 'Pyongyang', 108, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1236, 'تشاغانغ دو', 'Chagang-do', 108, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (1211, 'مجلس تنمية المجتمع الشمالي الغربي', 'North West Community Development Council', 109,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (1212, 'مجلس تنمية المجتمع سنغافورة الوسطى', 'Central Singapore Community Development Council', 109,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (1213, 'مجلس تنمية المجتمع الجنوبي الغربي', 'South West Community Development Council', 109,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (1214, 'منطقة تنمية المجتمع الشمالي الشرقي', 'North East Community Development Region', 109,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1238, 'المقاطعة الشمالية', 'Northern Territory', 111, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1239, 'جنوب استراليا', 'South Australia', 111, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1240, 'أستراليا الغربية', 'Western Australia', 111, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1241, 'كوينزلاند', 'Queensland', 111, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1278, 'نيو ساوث ويلز', 'New South Wales', 111, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1279, 'فيكتوريا', 'Victoria', 111, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1280, 'تسمانيا', 'Tasmania', 111, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1281, 'إقليم العاصمة الأسترالية', 'Australian Capital Territory', 111, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2608, 'ماجورو أتول', 'Majuro Atoll', 113, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1242, 'حالة ياب', 'State of Yap', 114, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1243, 'مقاطعة انجا', 'Enga Province', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1426, 'باماكو منطقة', 'Bamako Region', 146, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1244, 'مقاطعة سيبيك الغربية', 'West Sepik Province', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1245, 'مقاطعة بريطانيا الأوسط الجديد', 'East New Britain Province', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1246, 'العاصمة الوطنية', 'National Capital', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1247, 'المنطقة الشمالية', 'Northern Province', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1248, 'مقاطعة الخليج', 'Gulf Province', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1249, 'مقاطعة المرتفعات الغربية', 'Western Highlands Province', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1250, 'أيرلندا جديدة', 'New Ireland', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1251, 'مقاطعة المرتفعات الجنوبية', 'Southern Highlands Province', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1252, 'محافظة مادانغ', 'Madang Province', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1253, 'مقاطعة اليد', 'Manus Province', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1254, 'مقاطعة موروبى', 'Morobe Province', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1255, 'مقاطعة شيمبو', 'Chimbu Province', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1256, 'مقاطعة بريطانيا غربي جديد', 'West New Britain Province', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1257, 'المنطقة الوسطى', 'Central Province', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1258, 'مقاطعة المرتفعات الشرقية', 'Eastern Highlands Province', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1259, 'محافظة الغربية', 'Western Province', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1260, 'بوغانفيل', 'Bougainville', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1261, 'مقاطعة سيبيك الشرق', 'East Sepik Province', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1274, 'مقاطعة خليج ميلن', 'Milne Bay Province', 115, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1262, 'محافظة القنال', 'Guadalcanal Province', 116, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1263, 'جزر جيلبرت', 'Gilbert Islands', 117, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1264, 'فونافوتي', 'Funafuti', 118, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2612, 'فايتوبو', 'Vaitupu', 118, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1265, 'أنابار', 'Anabar', 119, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1275, 'شييفا', 'Shefa Province', 120, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1369, 'ولاية ريفرز', 'Rivers State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1276, 'مقاطعة بيناما', 'Penama Province', 120, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1277, 'محافظة الجنوب', 'South Province', 121, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1282, 'ساوثلاند', 'Southland', 123, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1283, 'كانتربري', 'Canterbury', 123, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1284, 'وايكاتو', 'Waikato', 123, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1285, 'أوكلاند', 'Auckland', 123, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1286, 'ولينغتون', 'Wellington', 123, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1287, 'تاراناكي', 'Taranaki', 123, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1288, 'ماناواتو-وانجانوي', 'Manawatu-Wanganui', 123, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1289, 'تاسمان', 'Tasman', 123, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1290, 'نورثلاند منطقة', 'Northland Region', 123, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1291, 'خليج بلنتي منطقة', 'Bay of Plenty Region', 123, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1292, 'هوكس باي', 'Hawkes Bay', 123, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1293, 'جيسبورن', 'Gisborne', 123, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1294, 'أوتاجو', 'Otago', 123, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1295, 'الساحل الغربي', 'West Coast', 123, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1296, 'نيلسون', 'Nelson', 123, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1297, 'مارلبورو', 'Marlborough', 123, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2530, 'جزر تشاتام', 'Chatham Islands', 123, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1298, 'وسط', 'Central', 124, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1299, 'الغربي', 'Western', 124, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1304, 'مركز', 'Centre', 125, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1305, 'أداماوا منطقة', 'Adamaoua Region', 125, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1306, 'جنوب غرب منطقة', 'South-West Region', 125, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1307, 'منطقة شمال', 'North Region', 125, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1308, 'جنوب', 'South', 125, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1309, 'ساحلي', 'Littoral', 125, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1310, 'شمال المنطقة الغربية', 'North-West Region', 125, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1311, 'منطقة الغربية', 'West Region', 125, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1318, 'المنطقة دي Sedhiou', 'Region de Sedhiou', 126, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1319, 'فاتيك', 'Fatick', 126, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1320, 'كاولاك', 'Kaolack', 126, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1321, 'سانت لويس', 'Saint-Louis', 126, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1322, 'وغا', 'Louga', 126, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1323, 'تامباكوندا', 'Tambacounda', 126, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1324, 'المنطقة دي Kaffrine', 'Region de Kaffrine', 126, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1325, 'كولدا', 'Kolda', 126, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1326, 'المنطقة دي كيدوغو', 'Region de Kedougou', 126, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1327, 'داكار', 'Dakar', 126, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1328, 'سانغا', 'Sangha', 127, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1329, 'بوانت نوار', 'Pointe-Noire', 127, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1330, 'برازافيل', 'Brazzaville', 127, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1331, 'شنترين', 'Santarém', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1332, 'لشبونة', 'Lisbon', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1333, 'ايفورا', 'Évora', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1334, 'كاستيلو برانكو', 'Castelo Branco', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1335, 'فارو', 'Faro', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1336, 'باجة', 'Beja', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1337, 'سيتوبال', 'Setúbal', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1338, 'بورتاليجري', 'Portalegre', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1339, 'الماديرا', 'Madeira', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1340, 'ليريا', 'Leiria', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1341, 'كويمبرا', 'Coimbra', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1342, 'غواردا', 'Guarda', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1646, 'فيسيو', 'Viseu', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1647, 'براغا', 'Braga', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1648, 'بورتو', 'Porto', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1649, 'براغانسا', 'Bragança', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1651, 'فيانا دو كاستيلو', 'Viana do Castelo', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1652, 'افيرو', 'Aveiro', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2069, 'الأزور', 'Azores', 128, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1343, 'مقاطعة مونتسيرادو', 'Montserrado County', 129, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1344, 'مقاطعة نيمبا', 'Nimba County', 129, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1345, 'أبيدجان', 'Abidjan', 130, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1346, 'منطقة قصر مونتيجز', 'District des Montagnes', 130, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1347, 'ساسندرا-مراهو', 'Sassandra-Marahoue', 130, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1348, 'كومو البريد', 'Comoe', 130, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1349, 'منطقة غرب أعالي', 'Upper West Region', 131, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1350, 'منطقة فولتا', 'Volta Region', 131, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1351, 'منطقة أكرا الكبرى', 'Greater Accra Region', 131, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1352, 'المنطقة الغربية', 'Western Region', 131, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1353, 'برونغ أهافو', 'Brong-Ahafo', 131, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1354, 'المنطقة الشرقية', 'Eastern Region', 131, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1355, 'منطقة الشرق العلوي', 'Upper East Region', 131, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1356, 'منطقة أشانتي', 'Ashanti Region', 131, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1357, 'المنطقة الوسطى', 'Central Region', 131, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1358, 'بيوكو نورت', 'Bioko Norte', 132, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1361, 'ولاية كادونا', 'Kaduna State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1362, 'أداماوا', 'Adamawa', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1363, 'لاغوس', 'Lagos', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1364, 'إقليم العاصمة الاتحادية', 'Federal Capital Territory', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1365, 'الدولة تارابا', 'Taraba State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1366, 'الدولة اكوا', 'Akwa Ibom State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1367, 'ولاية النيجر', 'Niger State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1368, 'ولاية سوكوتو', 'Sokoto State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1370, 'ولاية أويو', 'Oyo State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1371, 'الدولة المنظمة البحرية الدولية', 'Imo State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1372, 'الدولة أوندو', 'Ondo State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1373, 'ولاية اوجون', 'Ogun State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1374, 'ولاية كوارا', 'Kwara State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1375, 'الدولة كروس ريفر', 'Cross River State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1376, 'الدولة إينوغو', 'Enugu State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1377, 'ولاية كوجي', 'Kogi State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1378, 'ولاية كاتسينا', 'Katsina State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1379, 'ولاية كانو', 'Kano State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1380, 'ولاية بلاتو', 'Plateau State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1381, 'الدولة أوسان', 'Osun State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1382, 'الدولة غومبي', 'Gombe State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1383, 'ولاية يوبى', 'Yobe State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1384, 'الدولة زامفارا', 'Zamfara State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1385, 'دلتا', 'Delta', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1386, 'ولاية كيبي', 'Kebbi State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1387, 'ايدو', 'Edo', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1388, 'بوتشي', 'Bauchi', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1389, 'انامبرا', 'Anambra', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1390, 'الدولة إكيتي', 'Ekiti State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1391, 'الدولة إيبوني', 'Ebonyi State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1392, 'ولاية أبيا', 'Abia State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1582, 'الدولة ناساراوا', 'Nasarawa State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1583, 'ولاية بايلسا', 'Bayelsa State', 133, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1393, 'مركز', 'Centre', 134, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1394, 'أوت-Bassins', 'Hauts-Bassins', 134, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1395, 'شلالات منطقة', 'Cascades Region', 134, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1396, 'بحري', 'Maritime', 135, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1397, 'كاشيو منطقة', 'Cacheu Region', 136, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1398, 'بولاما وبيجاغوس', 'Bolama and Bijagos', 136, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1399, 'بيساو', 'Bissau', 136, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1400, 'منطقة دي نواكشوط', 'District de Nouakchott', 137, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1402, 'ساحلي', 'Littoral', 138, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1403, 'وزارة أتلانتيك', 'Atlantique Department', 138, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1404, 'أوغوي البحرية', 'Ogooué-Maritime', 139, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1405, 'إيستوير', 'Estuaire', 139, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1406, 'هوت أوجوي', 'Haut-Ogooué', 139, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1407, 'Ngouni', 'Ngouni', 139, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1408, 'موين أوجوي', 'Moyen-Ogooué', 139, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1409, 'المنطقة الغربية', 'Western Area', 140, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1410, 'جزيرة ساو تومي', 'São Tomé Island', 141, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1411, 'برينسيبي', 'Principe', 141, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1412, 'القسم الغربي', 'Western Division', 143, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1413, 'مدينة بانجول', 'City of Banjul', 143, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1414, 'كانكان منطقة', 'Kankan Region', 144, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1415, 'بوكي منطقة', 'Boke Region', 144, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1416, 'نزيريكوري منطقة', 'Nzerekore Region', 144, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1417, 'مامو منطقة', 'Mamou Region', 144, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1418, 'ابي منطقة', 'Labe Region', 144, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1419, 'كنديا', 'Kindia', 144, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1420, 'فاراناه', 'Faranah', 144, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1421, 'كوناكري منطقة', 'Conakry Region', 144, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1425, 'نيامي', 'Niamey', 145, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1427, 'أوسرد', 'Aousserd', 147, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1506, 'مراكش-تانسيفت-الحوز', 'Marrakech-Tensift-Al Haouz', 147, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1507, 'شرقي', 'Oriental', 147, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1508, 'دكالة عبدة', 'Doukkala-Abda', 147, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1509, 'المنطقة دي سوس ماسة درعة', 'Region de Souss-Massa-Draa', 147, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1510, 'المنطقة دو الدار البيضاء', 'Region du Grand Casablanca', 147, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1511, 'المنطقة دي مكناس تافيلالت', 'Region de Meknes-Tafilalet', 147, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (1512, 'المنطقة دي الرباط سلا زمور زعير', 'Region de Rabat-Sale-Zemmour-Zaer', 147, '2016-10-20 13:41:43.233783+02',
   0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1513, 'منطقة طنجة تطوان', 'Region de Tanger-Tetouan', 147, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (1514, 'العيون بوجدور الساقية الحمراء', 'Laayoune-Boujdour-Sakia El Hamra', 147, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1515, 'تازة الحسيمة تاونات', 'Taza-Al Hoceima-Taounate', 147, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1516, 'الغرب شراردة بني حسين', 'Gharb-Chrarda-Beni Hssen', 147, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1517, 'الشاوية ورديغة', 'Chaouia-Ouardigha', 147, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1518, 'منطقة فاس بولمان', 'Region de Fes-Boulemane', 147, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1519, 'تادلة أزيلال', 'Tadla-Azilal', 147, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1520, 'كلميم السمارة', 'Guelmim-Es Semara', 147, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2619, 'واد-الدهب', 'Oued-Ed-Dahab', 147, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1428, 'محافظة زغوان', 'Gouvernorat de Zaghouan', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1429, 'ولاية تونس', 'Gouvernorat de Tunis', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1430, 'ولاية توزر', 'Gouvernorat de Tozeur', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1431, 'تطاوين', 'Tataouine', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1432, 'المنستير', 'Gouvernorat de Monastir', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1433, 'ولاية سوسة', 'Gouvernorat de Sousse', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1434, 'ولاية سليانة', 'Gouvernorat de Siliana', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1435, 'محافظة بنزرت', 'Gouvernorat de Bizerte', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1436, 'ولاية نابل', 'Gouvernorat de Nabeul', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1437, 'محافظة سيدي بوزيد', 'Gouvernorat de Sidi Bouzid', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1438, 'ولاية القيروان', 'Gouvernorat de Kairouan', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1439, 'ولاية صفاقس', 'Gouvernorat de Sfax', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1440, 'قفصة', 'Gafsa', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1441, 'ولاية قابس', 'Gouvernorat de Gabes', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1442, 'محافظة بن عروس', 'Gouvernorat de Ben Arous', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1443, 'ولاية باجة', 'Gouvernorat de Beja', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1444, 'ولاية أريانة', 'Gouvernorat de lAriana', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1445, 'محافظة القصرين', 'Gouvernorat de Kasserine', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1446, 'ولاية المهدية', 'Gouvernorat de Mahdia', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1447, 'ولاية الكاف', 'Gouvernorat de Kef', 148, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1448, 'تيبازة', 'Tipaza', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1449, 'صيدا', 'Saida', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1450, 'تلمسان', 'Tlemcen', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1451, 'تيزي وزو', 'Tizi Ouzou', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1452, 'المسكرة', 'Mascara', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1453, 'تيسمسيلت', 'Tissemsilt', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1454, 'تندوف', 'Tindouf', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1455, 'تيارت', 'Tiaret', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1456, 'باتنة', 'Batna', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1457, 'خنشلة', 'Khenchela', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1458, 'تمنراست', 'Tamanrasset', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1459, 'ميلة', 'Mila', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1460, 'الشلف', 'Chlef', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1461, 'سكيكدة', 'Skikda', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1462, 'أم البواقي', 'Oum el Bouaghi', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1463, 'عين تموشنت', 'Aïn Témouchent', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1464, 'عين الدفلى', 'Aïn Defla', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1465, 'سيدي بلعباس', 'Sidi Bel Abbès', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1466, 'بجاية', 'Béjaïa', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1467, 'سطيف', 'Sétif', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1468, 'ورقلة', 'Ouargla', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1469, 'المدية', 'Medea', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1470, 'غليزان', 'Relizane', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1471, 'بومرداس', 'Boumerdes', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1472, 'الجزائر', 'Algiers', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1473, 'وهران', 'Oran', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1474, 'نعمة', 'Naama', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1475, 'المسيلة', 'MSila', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1476, 'مستغانم', 'Mostaganem', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1477, 'البليدة', 'Blida', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1478, 'البويرة', 'Bouira', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1479, 'الأغواط', 'Laghouat', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1480, 'جيجل', 'Jijel', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1481, 'إليزي', 'Illizi', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1482, 'الجلفة', 'Djelfa', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1483, 'قالمة', 'Guelma', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1484, 'غرداية', 'Ghardaia', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1485, 'الطارف', 'El Tarf', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1486, 'قسنطينة', 'Constantine', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1487, 'شرم البيض', 'El Bayadh', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1488, 'عنابة', 'Annaba', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1489, 'بسكرة', 'Biskra', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1490, 'بشار', 'Béchar', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1491, 'أدرار', 'Adrar', 149, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1492, 'أندلسيا', 'Andalusia', 150, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1493, 'مورسيا', 'Murcia', 150, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1494, 'إكستريمادورا', 'Extremadura', 150, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1495, 'كاستيل-لامانشا', 'Castille-La Mancha', 150, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1496, 'فالنسيا', 'Valencia', 150, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1497, 'جزر الكناري', 'Canary Islands', 150, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1498, 'جزر البليار', 'Balearic Islands', 150, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1499, 'مليلية', 'Melilla', 150, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1822, 'كانتابريا', 'Cantabria', 150, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1823, 'بلد الباسك', 'Basque Country', 150, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1824, 'أراغون', 'Aragon', 150, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1825, 'قشتالة وليون', 'Castille and León', 150, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1826, 'نافار', 'Navarre', 150, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1827, 'غاليسيا', 'Galicia', 150, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1828, 'كاتالونيا', 'Catalonia', 150, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1829, 'إمارة أستورياس', 'Principality of Asturias', 150, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1830, 'مدريد', 'Madrid', 150, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1831, 'اريوخا', 'La Rioja', 150, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1500, 'سردينيا', 'Sardinia', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1501, 'صقلية', 'Sicily', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1502, 'كالابريا', 'Calabria', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1503, 'بوليا', 'Apulia', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1504, 'بازيليكاتا', 'Basilicate', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1505, 'كامبانيا', 'Campania', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1849, 'فينيتو', 'Veneto', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1850, 'إيميليا-رومانيا', 'Emilia-Romagna', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1851, 'فريولي فينيتسيا جوليا', 'Friuli Venezia Giulia', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1852, 'لومباردي', 'Lombardy', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1853, 'ليغوريا', 'Liguria', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1854, 'بيدمونت', 'Piedmont', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1855, 'ترينتينو ألتو أديجي', 'Trentino-Alto Adige', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1856, 'اتيوم', 'Latium', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1857, 'توسكانا', 'Tuscany', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1858, 'ابروز', 'Abruzzo', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1859, 'أومبريا', 'Umbria', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1860, 'وادي أوستا', 'Aosta Valley', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1861, 'مناطق الحدود بين إنكلترة و إسكتلندة', 'The Marches', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1862, 'موليز', 'Molise', 151, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1521, 'مرسلوك', 'Marsaxlokk', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1522, 'عز-زوريق', 'Iz-Zurrieq', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1523, 'عز-Zejtun', 'Iz-Zejtun', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1524, 'عز-زبج', 'Iz-Zebbug', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1525, 'هاز-زبج', 'Haz-Zebbug', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1526, 'هاز-زابار', 'Haz-Zabbar', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1527, 'التاسع وشوكية', 'Ix-Xewkija', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1528, 'التاسع وزاجرا', 'Ix-Xaghra', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1529, 'ايل حزام فاليتا', 'Il-Belt Valletta', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1530, 'تازبيكس', 'Ta Xbiex', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1531, 'تراكسين', 'Tarxien', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1532, 'حقيبة-سليما', 'Tas-Sliema', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1533, 'هو-سيجيوي', 'Is-Siggiewi', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1534, 'سانت فينيرا', 'Saint Venera', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1535, 'سانت لوسيا', 'Saint Lucia', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1536, 'سانت بول خليج', 'Saint Paul’s Bay', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1537, 'سانات', 'Sannat', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1538, 'سانت لورانس', 'Saint Lawrence', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1539, 'سانت جوليان', 'Saint Julian', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1540, 'آسفي', 'Safi', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1541, 'باولا', 'Paola', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1542, 'فيكتوريا', 'Victoria', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1543, 'الأشعة تحت الحمراء-الرباط', 'Ir-Rabat', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1544, 'ايل قرندي', 'Il-Qrendi', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1545, 'كورمي', 'Qormi', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1546, 'ايل قلعة', 'Il-Qala', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1547, 'تل بييتا', 'Tal-Pieta', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1548, 'في وفي Naxxar', 'In-Naxxar', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1549, 'ايل مونكسار', 'Il-Munxar', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1550, 'L-المطرفة', 'L-Imtarfa', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1551, 'L-Imsida', 'L-Imsida', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1552, 'L-Imqabba', 'L-Imqabba', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1553, 'ايل موستا', 'Il-Mosta', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1554, 'غاجنسييلم', 'Ghajnsielem', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1555, 'ايل مليحة', 'Il-Mellieha', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1556, 'L-Imdina', 'L-Imdina', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1557, 'مارساسكالا', 'Marsaskala', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1558, 'ايل مرسى', 'Il-Marsa', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1559, 'هو-سويكي', 'Is-Swieqi', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1560, 'لوقا', 'Luqa', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1561, 'L-إيسلا', 'L-Isla', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1562, 'L-Imgarr', 'L-Imgarr', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1563, 'L-إكلين', 'L-Iklin', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1564, 'اللجا', 'Lija', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1565, 'كيركوب', 'Kirkop', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1566, 'ايل كالكارا', 'Il-Kalkara', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1567, 'ايل غزيرا', 'Il-Gzira', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1568, 'ايل Furjana', 'Il-Furjana', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1569, 'ايل بيرجو', 'Il-Birgu', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1570, 'ايل هامرون', 'Il-Hamrun', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1571, 'ايل جودجا', 'Il-Gudja', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1572, 'هال جازاك', 'Hal Ghaxaq', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1573, 'L-جاسري', 'L-Ghasri', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1574, 'هال غرغور', 'Hal Gharghur', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1575, 'L-الغرب', 'L-Gharb', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1576, 'بورملا', 'Bormla', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1577, 'بيرزبوجا', 'Birzebbuga', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1578, 'بيركيركارا', 'Birkirkara', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1579, 'بالزان', 'Balzan', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1580, 'أتارد', 'Attard', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1581, 'سانت جون', 'Saint John', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2616, 'ايل فجورا', 'Il-Fgura', 152, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1585, 'النمسا السفلى', 'Lower Austria', 153, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1586, 'ستيريا', 'Styria', 153, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1587, 'بورغنلاند', 'Burgenland', 153, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1665, 'كارينثيا', 'Carinthia', 153, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1666, 'النمسا العليا', 'Upper Austria', 153, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1667, 'فورارلبرغ', 'Vorarlberg', 153, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1668, 'تيرول', 'Tyrol', 153, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1669, 'سالزبورغ', 'Salzburg', 153, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1670, 'فيينا', 'Vienna', 153, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1588, 'يوتلاند الوسطى', 'Central Jutland', 154, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1589, 'شمال الدنمارك', 'North Denmark', 154, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1590, 'جنوب الدنمارك', 'South Denmark', 154, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1591, 'نيوزيلندا', 'Zealand', 154, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1592, 'منطقة العاصمة', 'Capital Region', 154, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1593, 'شمال غرب', 'Northwest', 156, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1594, 'شمالي شرقي', 'Northeast', 156, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1595, 'الشرق', 'East', 156, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2109, 'شبه الجزيرة الجنوبية', 'Southern Peninsula', 156, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2110, 'جنوب', 'South', 156, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2111, 'Westfjords', 'Westfjords', 156, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2112, 'منطقة العاصمة', 'Capital Region', 156, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2113, 'غرب', 'West', 156, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1596, 'إنكلترا', 'England', 157, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1597, 'ويلز', 'Wales', 157, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1598, 'أسكتلندا', 'Scotland', 157, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1599, 'أيرلندا الشمالية', 'Northern Ireland', 157, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1600, 'أولستر', 'Ulster', 158, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1693, 'كونوت', 'Connaught', 158, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1694, 'مونستر', 'Munster', 158, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1695, 'ينستر', 'Leinster', 158, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1601, 'كانتون ريف بازل', 'Basel-Landschaft', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1602, 'برن', 'Bern', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1603, 'سانت غالن', 'Saint Gallen', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1604, 'أرجاو', 'Aargau', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1605, 'زيوريخ', 'Zurich', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1606, 'سولوتورن', 'Solothurn', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1607, 'زوغ', 'Zug', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1608, 'جريسنس', 'Grisons', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1609, 'فاليه', 'Valais', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1610, 'فصفصة نبات', 'Lucerne', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1611, 'فو', 'Vaud', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1612, 'فريبورغ', 'Fribourg', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1613, 'شفيتس', 'Schwyz', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1614, 'ثورجو', 'Thurgau', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1615, 'تيسان', 'Ticino', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1616, 'العصر الجوارسي أو الجوري', 'Jura', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1617, 'جنيف', 'Geneva', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1618, 'أوري', 'Uri', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1619, 'أبنزل أوسيرهودن', 'Appenzell Ausserrhoden', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1620, 'شافهاوزن', 'Schaffhausen', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1621, 'نيوشاتل', 'Neuchâtel', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1622, 'نيدوالدن', 'Nidwalden', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1623, 'جلاروس', 'Glarus', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1624, 'أوبوالدن', 'Obwalden', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1625, 'أبنزل إينرهودن', 'Appenzell Innerrhoden', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1626, 'مدينة بازل', 'Basel-City', 159, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1653, 'أوفيريجسيل محافظة', 'Provincie Overijssel', 160, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1654, 'هولندا الجنوبية', 'South Holland', 160, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1655, 'درينثي محافظة', 'Provincie Drenthe', 160, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1656, 'جيلديرلاند محافظة', 'Provincie Gelderland', 160, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1657, 'هولندا الشمالية', 'North Holland', 160, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1658, 'فريسلاند', 'Friesland', 160, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1659, 'براهات الشمالية', 'North Brabant', 160, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1660, 'زيلاند محافظة', 'Provincie Zeeland', 160, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1661, 'جرونينجن', 'Groningen', 160, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1662, 'أوتريخت محافظة', 'Provincie Utrecht', 160, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1663, 'فليفولاند محافظة', 'Provincie Flevoland', 160, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1664, 'ليمبورغ', 'Limburg', 160, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1671, 'فلاندرز', 'Flanders', 161, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1672, 'والونيا', 'Wallonia', 161, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1673, 'بروكسل العاصمة', 'Brussels Capital', 161, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1674, 'ساكسونيا', 'Saxony', 162, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1675, 'هيس', 'Hesse', 162, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1676, 'بادن فورتمبيرغ منطقة', 'Baden-Württemberg Region', 162, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1677, 'بافاريا', 'Bavaria', 162, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1678, 'راينلاند-بفالز', 'Rheinland-Pfalz', 162, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1679, 'مكلنبورغ-فوربومرن', 'Mecklenburg-Vorpommern', 162, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1680, 'شمال الراين وستفاليا', 'North Rhine-Westphalia', 162, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1681, 'ساكسونيا-آنهالت', 'Saxony-Anhalt', 162, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1682, 'براندنبورغ', 'Brandenburg', 162, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1683, 'ساكسونيا السفلى', 'Lower Saxony', 162, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1684, 'تورينجيا', 'Thuringia', 162, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1685, 'شليسفيغ هولشتاين', 'Schleswig-Holstein', 162, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1686, 'سارلاند', 'Saarland', 162, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1687, 'أرض برلين', 'Land Berlin', 162, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1688, 'هامبورغ', 'Hamburg', 162, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1689, 'بريمن', 'Bremen', 162, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1690, 'منطقة دي Diekirch', 'District de Diekirch', 163, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1691, 'حي دي لوكسمبورغ', 'District de Luxembourg', 163, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1692, 'منطقة دي Grevenmacher', 'District de Grevenmacher', 163, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1696, 'جيروند', 'Gironde', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1697, 'شمال', 'North', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1698, 'با-دو-كاليه', 'Pas-de-Calais', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1699, 'موسيل', 'Moselle', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1700, 'كورسيكا', 'Corsica', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1701, 'أوت رين', 'Haut-Rhin', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1702, 'بس رين', 'Bas-Rhin', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1703, 'اندز', 'Landes', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1704, 'اليي', 'Allier', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1705, 'رون', 'Rhône', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1706, 'وادي اللوار', 'Pays de la Loire', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1707, 'مركز', 'Centre', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1708, 'هاوت سافوي', 'Haute-Savoie', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1709, 'سين البحرية', 'Seine-Maritime', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1710, 'شارانت', 'Charente', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1711, 'إيل-دو-فرانس', 'Île-de-France', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1712, 'سيين إت مارن', 'Charente-Maritime', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1713, 'آردن', 'Ardennes', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1714, 'كانتال', 'Cantal', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1715, 'هوت-لوار', 'Haute-Loire', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1716, 'بوي دي دوم', 'Puy-de-Dôme', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1717, 'بريتاني', 'Brittany', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1718, 'سافوي', 'Savoy', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1719, 'الفوج', 'Vosges', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1720, 'مورت وموزيل', 'Meurthe et Moselle', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1721, 'مارن', 'Marne', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1722, 'السوم', 'Somme', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1723, 'أيسن', 'Aisne', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1724, 'إيل دو فرانس', 'Oise', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1725, 'هوت مارن', 'Haute-Marne', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1726, 'هوت-سون', 'Haute-Saône', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1727, 'أور', 'Eure', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1728, 'كوريز', 'Corrèze', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1729, 'إيسر', 'Isère', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1730, 'فيين', 'Vienne', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1731, 'دوبس', 'Doubs', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1732, 'دوكس سفرس', 'Deux-Sèvres', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1733, 'عين', 'Ain', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1734, 'بروفنس ألب كوت دازور', 'Provence-Alpes-Côte dAzur', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1735, 'العصر الجوارسي أو الجوري', 'Jura', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1736, 'أفيرون', 'Aveyron', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1737, 'أوب', 'Aube', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1738, 'تارن', 'Tarn', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1739, 'ARDECHE', 'Ardèche', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1740, 'البرانيس ​​الأطلسية', 'Pyrénées-Atlantiques', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1741, 'كوت دي أور', 'Cote dOr', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1742, 'دوردوني', 'Dordogne', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1743, 'المانش', 'Manche', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1744, 'سون ولوار', 'Saône-et-Loire', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1745, 'يون', 'Yonne', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1746, 'كالفادوس', 'Calvados', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1747, 'الكثير', 'Lot', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1748, 'الكثير غارون', 'Lot-et-Garonne', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1749, 'هيرو', 'Hérault', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1750, 'دروم', 'Drôme', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1751, 'أورن', 'Orne', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1752, 'بيرينيه-أورينتال', 'Pyrénées-Orientales', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1753, 'اود', 'Aude', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1754, 'جارد', 'Gard', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1755, 'لوار', 'Loire', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1756, 'العلوي غارون', 'Upper Garonne', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1757, 'ARIEGE', 'Ariège', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1758, 'أوتس-بيرينيه', 'Hautes-Pyrénées', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1759, 'تارن وغارون', 'Tarn-et-Garonne', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1760, 'كروز', 'Creuse', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1761, 'الخيام', 'Gers', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1762, 'إقليم بلفور', 'Territoire de Belfort', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1763, 'هوت فيين', 'Haute-Vienne', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1764, 'ميوز', 'Meuse', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1765, 'نيفر', 'Nièvre', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1766, 'لوزر', 'Lozère', 164, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1767, 'سانت جوليا دي لوريا', 'Sant Julià de Loria', 166, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1768, 'أندورا لا فيلا', 'Andorra la Vella', 166, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1769, 'اماسانا', 'La Massana', 166, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1770, 'أوردينو', 'Ordino', 166, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1771, 'كانيلو', 'Canillo', 166, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2044, 'Beltinci', 'Beltinci', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1772, 'اسكالدس إنغورداني', 'Escaldes-Engordany', 166, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1773, 'نزلوا', 'Encamp', 166, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1774, 'فادوز', 'Vaduz', 167, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1775, 'تريسنبرغ', 'Triesenberg', 167, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1776, 'تريزين', 'Triesen', 167, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1777, 'شلينبرغ', 'Schellenberg', 167, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1778, 'مورن', 'Mauren', 167, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1779, 'ستشان', 'Schaan', 167, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1780, 'روجل', 'Ruggell', 167, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1781, 'اشن', 'Eschen', 167, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1782, 'GEMEINDE جمبرين', 'Gemeinde Gamprin', 167, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1783, 'بلزرس', 'Balzers', 167, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1800, 'كرالوفيهراديكي KRAJ', 'Kralovehradecky kraj', 171, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1801, 'بوهيميا الوسطى', 'Central Bohemia', 171, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1802, 'زلين', 'Zlín', 171, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1803, 'جنوب مورافيا', 'South Moravian', 171, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1804, 'كارلوفارسكاي KRAJ', 'Karlovarsky kraj', 171, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1805, 'جيهوشيسكي كراج', 'Jihocesky kraj', 171, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1806, 'KRAJ أولوموك', 'Olomoucky kraj', 171, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1807, 'Hlavni ميستو براغ', 'Hlavni mesto Praha', 171, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1808, 'مورافسكوسليزسكاي KRAJ', 'Moravskoslezsky kraj', 171, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1809, 'كراي فسكوسينا', 'Kraj Vysocina', 171, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1810, 'بلزينسكاي KRAJ', 'Plzensky kraj', 171, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1811, 'KRAJ يبيريكي', 'Liberecky kraj', 171, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1812, 'باردوبيك KRAJ', 'Pardubicky kraj', 171, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1813, 'أوستكي KRAJ', 'Ustecky kraj', 171, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1863, 'سيرافالي', 'Serravalle', 173, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1864, 'كاستيلو دي سان مارينو سيتا', 'Castello di San Marino Citta', 173, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1865, 'كاستيلو دي فيتانو', 'Castello di Faetano', 173, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1866, 'كاستيلو دى بورجو ماجوري', 'Castello di Borgo Maggiore', 173, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1873, 'OBČINA Zuzemberk', 'Obcina Zuzemberk', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1879, 'OBČINA Zirovnica', 'Obcina Zirovnica', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1880, 'OBČINA لاسكو', 'Obcina Lasko', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1881, 'OBČINA Hoce-Slivnica', 'Obcina Hoce-Slivnica', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1882, 'سلوفينيسكا بيستريسا', 'Slovenska Bistrica', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1884, 'OBČINA جيليزنيكي', 'Obcina Zelezniki', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1886, 'OBČINA Zalec', 'Obcina Zalec', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1888, 'OBČINA Ivancna جوريكا', 'Obcina Ivancna Gorica', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1889, 'زاجورج أوب سافي', 'Zagorje ob Savi', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1892, 'OBČINA Loska دولينا', 'Obcina Loska Dolina', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1895, 'Vransko', 'Vransko', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1897, 'OBČINA Sencur', 'Obcina Sencur', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1898, 'Vipava', 'Vipava', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1899, 'OBČINA Verzej', 'Obcina Verzej', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1904, 'OBČINA Velike Lasce', 'Obcina Velike Lasce', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1905, 'Trzin', 'Trzin', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1906, 'OBČINA Trzic', 'Obcina Trzic', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1907, 'تربنجه', 'Trebnje', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1908, 'تربوفلي', 'Trbovlje', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1909, 'OBČINA تولمين', 'Obcina Tolmin', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1910, 'فيلينيه', 'Velenje', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1911, 'OBČINA Tisina', 'Obcina Tisina', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1913, 'طابور', 'Tabor', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1914, 'سفيتا آنا', 'Sveta Ana', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1915, 'OBČINA مخزن', 'Obcina Store', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1917, 'OBČINA Kocevje', 'Obcina Kocevje', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1918, 'كامنيك', 'Kamnik', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1919, 'Duplek', 'Duplek', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1920, 'Hajdina', 'Hajdina', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1921, 'Gorenja فاس-Poljane', 'Gorenja Vas-Poljane', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1922, 'OBČINA Sostanj', 'Obcina Sostanj', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1923, 'Medvode', 'Medvode', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1924, 'سلوفينيا غرادتش', 'Slovenj Gradec', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (1925, 'OBČINA Smartno الحزب الثوري المؤسسي Litiji', 'Obcina Smartno pri Litiji', 174,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1926, 'OBČINA Smartno أوب باكي', 'Obcina Smartno ob Paki', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1927, 'سلوفنيسكه كونجيتسه', 'Slovenske Konjice', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1929, 'OBČINA على قدم وساق', 'Obcina Apace', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1930, 'OBČINA Skofljica', 'Obcina Skofljica', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1931, 'شكوفجا لوكا', 'Škofja Loka', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1932, 'كوبر', 'Koper', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1934, 'OBČINA سيزانا', 'Obcina Sezana', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1935, 'Sevnica', 'Sevnica', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1936, 'بلدية Šentjur', 'Municipality of Šentjur', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1937, 'OBČINA Sentjernej', 'Obcina Sentjernej', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1938, 'دراوغراد', 'Dravograd', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1939, 'OBČINA Sentilj', 'Obcina Sentilj', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1940, 'OBČINA Divaca', 'Obcina Divaca', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1941, 'OBČINA كرشكو', 'Obcina Krsko', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1943, 'OBČINA Sempeter-Vrtojba', 'Obcina Sempeter-Vrtojba', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1944, 'نوفا جوريكا', 'Nova Gorica', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1945, 'OBČINA SEMIC', 'Obcina Semic', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1946, 'Litija', 'Litija', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1947, 'OBČINA روسه', 'Obcina Ruse', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1948, 'OBČINA Rogasovci', 'Obcina Rogasovci', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1949, 'OBČINA روغاسكا سلاتينا', 'Obcina Rogaska Slatina', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1950, 'Ribnica', 'Ribnica', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1951, 'OBČINA Recica اوب Savinji', 'Obcina Recica ob Savinji', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1952, 'OBČINA Ravne غ Koroskem', 'Obcina Ravne na Koroskem', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1953, 'رادوفلجيكا', 'Radovljica', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1954, 'OBČINA DOMZALE', 'Obcina Domzale', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1955, 'Radlje اوب Dravi', 'Radlje ob Dravi', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1956, 'رادينسي', 'Radenci', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1957, 'OBČINA Radece', 'Obcina Radece', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1958, 'OBČINA سباق-فرام', 'Obcina Race-Fram', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1959, 'بتوي', 'Ptuj', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1960, 'Prevalje', 'Prevalje', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1961, 'بوستوينا', 'Postojna', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1962, 'Brezovica', 'Brezovica', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1964, 'بريدفور', 'Preddvor', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1965, 'بيران', 'Piran', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1966, 'Polzela', 'Polzela', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1967, 'ليوبليانا', 'Ljubljana', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1968, 'OBČINA Poljcane', 'Obcina Poljcane', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1969, 'Dobrova-Polhov غرادتش', 'Dobrova-Polhov Gradec', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1970, 'بلدية Dornava', 'Municipality of Dornava', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1971, 'ناكلو', 'Naklo', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1972, 'Podlehnik', 'Podlehnik', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1974, 'OBČINA أورموج', 'Obcina Ormoz', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1975, 'PIVKA', 'Pivka', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1976, 'Pesnica', 'Pesnica', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1977, 'Osilnica', 'Osilnica', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1978, 'Odranci', 'Odranci', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1979, 'Mestna OBČINA نوفو ميستو', 'Mestna Obcina Novo mesto', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1980, 'مورسكا سوبوتا', 'Murska Sobota', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1981, 'Mozirje', 'Mozirje', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1982, 'OBČINA Moravce', 'Obcina Moravce', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1983, 'Mokronog-Trebelno', 'Mokronog-Trebelno', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1985, 'كرانجسكا غورا', 'Kranjska Gora', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1987, 'Miren-Kostanjevica', 'Miren-Kostanjevica', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (1988, 'OBČINA Miklavz غ Dravskem Polju', 'Obcina Miklavz na Dravskem Polju', 174, '2016-10-20 13:41:43.233783+02',
   0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1989, 'OBČINA مجيستا', 'Obcina Mezica', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1990, 'متليكا', 'Metlika', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1991, 'OBČINA MENGES', 'Obcina Menges', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1992, 'Hrpelje-Kozina', 'Hrpelje-Kozina', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1993, 'Markovci', 'Markovci', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1994, 'ماريبور', 'Maribor', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1995, 'Makole', 'Makole', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1996, 'OBČINA Majsperk', 'Obcina Majsperk', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1997, 'Lukovica', 'Lukovica', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1998, 'Lovrenc غ Pohorju', 'Lovrenc na Pohorju', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1999, 'تسجيل-Dragomer', 'Log–Dragomer', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2000, 'ليوتومير', 'Ljutomer', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2001, 'Ljubno', 'Ljubno', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2002, 'ليندافا', 'Lendava', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2003, 'لينارت', 'Lenart', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2004, 'كوزما', 'Kuzma', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2005, 'جورني Petrovci', 'Gornji Petrovci', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2007, 'كوستانيويتسا نا كركي', 'Kostanjevica na Krki', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2008, 'كومن', 'Komen', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2009, 'OBČINA كوباريد', 'Obcina Kobarid', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2010, 'إيليرسكا بيتريسا', 'Ilirska Bistrica', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2011, 'OBČINA Kidricevo', 'Obcina Kidricevo', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2012, 'OBČINA Jursinci', 'Obcina Jursinci', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2013, 'جيسينيس', 'Jesenice', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2014, 'يزولا', 'Izola', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2015, 'زاي', 'Ig', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2016, 'إيدرييا', 'Idrija', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2017, 'هراستنيك', 'Hrastnik', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2018, 'Horjul', 'Horjul', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2020, 'Grosuplje', 'Grosuplje', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2021, 'جورني غراد', 'Gornji Grad', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2022, 'غرنيا رادغنا', 'Gornja Radgona', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2023, 'OBČINA Gorisnica', 'Obcina Gorisnica', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2024, 'OBČINA Straza', 'Obcina Straza', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2025, 'وزارة العمل الحزب الثوري المؤسسي Ljubljani', 'Dol pri Ljubljani', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2026, 'دولينيسكي توبليتسي', 'Dolenjske Toplice', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2027, 'بردا', 'Brda', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2028, 'بلدية دوبرنا', 'Municipality of Dobrna', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2029, 'Destrnik', 'Destrnik', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2031, 'OBČINA تشرنملي', 'Obcina Crnomelj', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2032, 'OBČINA اسود نا Koroskem', 'Obcina Crna na Koroskem', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2033, 'Cerkvenjak', 'Cerkvenjak', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2034, 'تسركنيتسا', 'Cerknica', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2035, 'OBČINA بريزيسي', 'Obcina Brezice', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2036, 'بلدية Cerklje غ Gorenjskem', 'Municipality of Cerklje na Gorenjskem', 174, '2016-10-20 13:41:43.233783+02',
   0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2037, 'سيلجي', 'Celje', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2038, 'Cankova', 'Cankova', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2041, 'Borovnica', 'Borovnica', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2042, 'OBČINA بليد', 'Obcina Bled', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2043, 'سيلنيتشا اوب Dravi', 'Selnica ob Dravi', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2046, 'OBČINA Ajdovščina قابل لل', 'Obcina Ajdovscina', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2047, 'Puconci', 'Puconci', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2048, 'OBČINA Razkrizje', 'Obcina Razkrizje', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2049, 'Cerkno', 'Cerkno', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2050, 'LOGATEC', 'Logatec', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2051, 'Videm', 'Videm', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2052, 'OBČINA Zrece', 'Obcina Zrece', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2607, 'Cirkulane', 'Cirkulane', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2617, 'بوينج', 'Bohinj', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2618, 'Kostel', 'Kostel', 174, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1874, 'فوكوفار-سيرميوم', 'Vukovar-Sirmium', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1875, 'فارازدينسكا زوبانيجا', 'Varazdinska Zupanija', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1876, 'إيستارسكا زوبانيجا', 'Istarska Zupanija', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1877, 'كاربينسكو زاجورسكا جوبانيا', 'Krapinsko-Zagorska Zupanija', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1883, 'مقاطعة زغرب', 'Zagreb County', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (1885, 'فيروفيتيكو-بودرافسكا جوبانيا', 'Viroviticko-Podravska Zupanija', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1887, 'مدينة زغرب', 'City of Zagreb', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1890, 'زادارسكا جوبانيا', 'Zadarska Zupanija', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1893, 'سبليتسكو دالماتينسكا', 'Splitsko-Dalmatinska Zupanija', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1894, 'مقاطعة بريمروسكو غورانسكا', 'Primorsko-Goranska Zupanija', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1896, 'سيبينسكو كنينسكا جوبانيا', 'Sibensko-Kninska Zupanija', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (1900, 'بجيلوفارسكو بيلوغورسكا زوبانيجو', 'Bjelovarsko-Bilogorska Zupanija', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1901, 'كوبرفنيكو كريزفاكا جوبانيا', 'Koprivnicko-Krizevacka Zupanija', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1902, 'أوسجيكو بارانجا', 'Osjecko-Baranjska Zupanija', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1916, 'سلافونسكي برود-بوسافينا', 'Slavonski Brod-Posavina', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1928, 'بوزيسكو-سلافونسكا جوبانيا', 'Pozesko-Slavonska Zupanija', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1933, 'سيساكو موسلافاكا جوبانيا', 'Sisacko-Moslavacka Zupanija', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1942, 'ليكو سنجسكا جوبانيا', 'Licko-Senjska Zupanija', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1963, 'Megimurska جوبانيا', 'Megimurska Zupanija', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (1984, 'مقاطعة دوبروفاكو-نيريتفانسكا', 'Dubrovacko-Neretvanska Zupanija', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2045, 'كارلوفاكا جوبانيا', 'Karlovacka Zupanija', 175, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1878, 'اتحاد البوسنة والهرسك', 'Federation of Bosnia and Herzegovina', 176, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1912, 'جمهورية صربسكا', 'Republic of Srspka', 176, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1903, 'أولسيني', 'Ulcinj', 177, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (1973, 'بودغوريتشا', 'Podgorica', 177, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2006, 'كوتور', 'Kotor', 177, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2019, 'هرسك نوفي', 'Herceg Novi', 177, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2030, 'دانيلوفغراد', 'Danilovgrad', 177, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2039, 'بودفا', 'Budva', 177, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2070, 'كنيسة المسيح', 'Christ Church', 179, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2071, 'سانت مايكل', 'Saint Michael', 179, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2072, 'سانت أندرو', 'Saint Andrew', 179, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2073, 'سانت توماس', 'Saint Thomas', 179, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2074, 'سانت جيمس', 'Saint James', 179, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2075, 'القديس جورج', 'Saint George', 179, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2076, 'سانت فيليب', 'Saint Philip', 179, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2077, 'سانت لوسي', 'Saint Lucy', 179, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2078, 'القديس يوسف', 'Saint Joseph', 179, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2079, 'ريبيرا غراندي', 'Ribeira Grande', 180, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2080, 'ساو دومينغوس', 'São Domingos', 180, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2081, 'ريبيرا غراندي دي سانتياغو', 'Ribeira Grande de Santiago', 180, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2082, 'ديميرارا ماهايكا منطقة', 'Demerara-Mahaica Region', 181, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2083, 'الشرق بيربيس-كورنتين منطقة', 'East Berbice-Corentyne Region', 181, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2084, 'العلوي ديميرارا بيربيس منطقة', 'Upper Demerara-Berbice Region', 181, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2085, 'منطقة نيكيري', 'Distrikt Nickerie', 183, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2086, 'منطقة كوروني', 'Distrikt Coronie', 183, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2087, 'مقاطعة بارا', 'Distrikt Para', 183, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2088, 'مقاطعة باراماريبو', 'Distrikt Paramaribo', 183, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2089, 'منطقة كوميويجني', 'Distrikt Commewijne', 183, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2090, 'منطقة ماروويجني', 'Distrikt Marowijne', 183, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2091, 'منطقة انيكا', 'Distrikt Wanica', 183, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2092, 'منطقة سارامكا', 'Distrikt Saramacca', 183, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2093, 'مقاطعة بروكوبوندو', 'Distrikt Brokopondo', 183, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2094, 'منطقة سيباليويني', 'Distrikt Sipaliwini', 183, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2095, 'توكانتينز', 'Tocantins', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2096, 'الفقرة', 'Para', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2097, 'بيرنامبوكو', 'Pernambuco', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2098, 'ريو غراندي دو نورتي', 'Rio Grande do Norte', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2099, 'بارايبا', 'Paraíba', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2100, 'سيارا', 'Ceara', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2101, 'ألاغواس', 'Alagoas', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2102, 'مارانهاو', 'Maranhao', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2103, 'بياوي', 'Piaui', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2104, 'بهية', 'Bahia', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2105, 'أمابا', 'Amapa', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2106, 'سيرغيبي', 'Sergipe', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2107, 'أمازوناس', 'Amazonas', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2108, 'ماتو غروسو', 'Mato Grosso', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2153, 'سانتا كاتارينا', 'Santa Catarina', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2154, 'ساو باولو', 'Sao Paulo', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2155, 'ريو دي جانيرو', 'Rio de Janeiro', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2156, 'اسبيريتو سانتو', 'Espirito Santo', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2157, 'ميناس جيرايس', 'Minas Gerais', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2158, 'ريو غراندي دو سول', 'Rio Grande do Sul', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2159, 'غوياس', 'Goias', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2160, 'بارانا', 'Parana', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2161, 'ماتو غروسو دو سول', 'Mato Grosso do Sul', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2162, 'المقاطعة الاتحادية', 'Federal District', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2400, 'روندونيا', 'Rondonia', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2401, 'فدان', 'Acre', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2402, 'رورايما', 'Roraima', 184, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2114, 'Qaasuitsup', 'Qaasuitsup', 185, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2115, 'كيكاتا', 'Qeqqata', 185, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2116, 'Kujalleq', 'Kujalleq', 185, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2117, 'سيرمرسوك', 'Sermersooq', 185, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2118, 'البلدية دي سانت بيير', 'Commune de Saint-Pierre', 186, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2119, 'البلدية دي ميكلون-Langlade', 'Commune de Miquelon-Langlade', 186, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2120, 'بوينس آيرس', 'Buenos Aires', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2121, 'ميسيونس', 'Misiones Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2122, 'بوينس آيرس F.D.', 'Buenos Aires F.D.', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2123, 'مقاطعة سانتا في', 'Santa Fe Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2124, 'إنتري ريوس', 'Entre Ríos Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2125, 'مقاطعة تشاكو', 'Chaco Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2126, 'مقاطعة كورينتس', 'Corrientes Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2127, 'فورموسا', 'Formosa Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2461, 'مقاطعة سان خوان', 'San Juan Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2462, 'مقاطعة نيوكوين', 'Neuquén Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2463, 'مقاطعة توكومان', 'Tucumán Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2464, 'لا بامبا', 'La Pampa Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2465, 'سانتياغو ديل استيرو', 'Santiago del Estero Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2466, 'ريو نيغرو', 'Río Negro Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2467, 'مقاطعة مندوزا', 'Mendoza Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2468, 'مقاطعة قرطبة', 'Cordoba Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2469, 'مقاطعة سانتا كروز', 'Santa Cruz Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2470, 'تييرا ديل فويغو مقاطعة', 'Tierra del Fuego Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2471, 'تشوبوت', 'Chubut Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2472, 'مقاطعة سان لويس', 'San Luis Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2473, 'سالتا', 'Salta Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2474, 'كاتاماركا', 'Catamarca Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2475, 'خوخوي', 'Jujuy Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2476, 'مقاطعة لاريوخا', 'La Rioja Province', 189, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2128, 'الإدارة المركزية', 'Departamento Central', 190, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2129, 'وزارة ميسيونيس', 'Departamento de Misiones', 190, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2130, 'دائرة بارانا ألتو', 'Departamento del Alto Parana', 190, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2131, 'وزارة أمامباي', 'Departamento del Amambay', 190, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2132, 'مقاطعة بوكويرون', 'Departamento de Boqueron', 190, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2133, 'وزارة إيتابوا', 'Departamento de Itapua', 190, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2134, 'وزارة باراغواي ألتو', 'Departamento de Alto Paraguay', 190, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2135, 'وزارة كورديليرا لا', 'Departamento de la Cordillera', 190, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2136, 'وزارة Paraguari', 'Departamento de Paraguari', 190, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2137, 'وزارة كازابا', 'Departamento de Caazapa', 190, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2138, 'أسونسيون', 'Asuncion', 190, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2139, 'كانيلونز', 'Canelones', 191, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2140, 'وزارة تاكواريمبو', 'Departamento de Tacuarembo', 191, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2141, 'سوريانو', 'Soriano', 191, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2142, 'وزارة ريفيرا', 'Departamento de Rivera', 191, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2143, 'وزارة سان خوسيه', 'Departamento de San Jose', 191, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2144, 'مالدونادو', 'Maldonado', 191, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2145, 'كولونيا', 'Colonia', 191, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2146, 'وزارة مونتيفيديو', 'Departamento de Montevideo', 191, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2147, 'وزارة بايساندو', 'Departamento de Paysandu', 191, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2148, 'سيرو لارجو', 'Cerro Largo', 191, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2149, 'افاييخا', 'Lavalleja', 191, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2150, 'وزارة ريو نيغرو', 'Departamento de Rio Negro', 191, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2151, 'فلوريدا', 'Florida', 191, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2152, 'أرتيجاس', 'Artigas', 191, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2163, 'نويفا اسبارتا', 'Nueva Esparta', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2164, 'فارغاس', 'Vargas', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2165, 'عاصمة', 'Capital', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2166, 'كارابوبو', 'Carabobo', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2167, 'استادو تروخيو', 'Estado Trujillo', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2170, 'أنزواتيغي', 'Anzoátegui', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2359, 'سوليا', 'Zulia', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2360, 'ياراكوي', 'Yaracuy', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2361, 'أراغوا', 'Aragua', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2362, 'غواريكو', 'Guárico', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2363, 'بوليفار', 'Bolívar', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2364, 'كوجيديس', 'Cojedes', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2365, 'دلتا أماكورو', 'Delta Amacuro', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2366, 'ميريدا', 'Mérida', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2367, 'تاشيرا', 'Táchira', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2368, 'ميراندا', 'Miranda', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2369, 'باريناس', 'Barinas', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2370, 'فالكون', 'Falcón', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2371, 'لارا', 'Lara', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2372, 'أمازوناس', 'Amazonas', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2373, 'بورتوغيزا', 'Portuguesa', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2374, 'موناجاس', 'Monagas', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2375, 'سوكري', 'Sucre', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2376, 'أبور', 'Apure', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2377, 'ديبيندينسياز فيدرالز', 'Dependencias Federales', 192, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2168, 'تاماوليباس', 'Tamaulipas', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2169, 'سان لويس بوتوسي', 'San Luis Potosí', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2209, 'استادو دي مكسيكو', 'Estado de Mexico', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2210, 'غيريرو', 'Guerrero', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2211, 'أواكساكا', 'Oaxaca', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2212, 'الهيدلج من نبلاء الأسبان', 'Hidalgo', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2213, 'فيراكروز', 'Veracruz', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2214, 'بويبلا', 'Puebla', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2215, 'موريلوس', 'Morelos', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2216, 'مكسيكو سيتي', 'Mexico City', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2217, 'يوكاتان', 'Yucatán', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2218, 'تلاكسكالا', 'Tlaxcala', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2219, 'تاباسكو', 'Tabasco', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2220, 'تشياباس', 'Chiapas', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2221, 'كويريتارو', 'Querétaro', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2222, 'كوينتانا رو', 'Quintana Roo', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2223, 'كامبيتشي', 'Campeche', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2224, 'نويفو ليون', 'Nuevo León', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2515, 'سونورا', 'Sonora', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2516, 'زاكاتيكاس', 'Zacatecas', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2517, 'تشيهواهوا', 'Chihuahua', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2518, 'سينالوا', 'Sinaloa', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2519, 'خاليسكو', 'Jalisco', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2520, 'كواهويلا', 'Coahuila', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2521, 'دورانجو', 'Durango', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2522, 'استادو دي باجا كاليفورنيا', 'Estado de Baja California', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2523, 'ميشواكان', 'Michoacán', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2524, 'غواناخواتو', 'Guanajuato', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2525, 'ناياريت', 'Nayarit', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2526, 'كوليما', 'Colima', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2527, 'باجا كاليفورنيا سور', 'Baja California Sur', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2528, 'اغواسكالينتيس', 'Aguascalientes', 193, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2171, 'سانت كاترين', 'Saint Catherine', 194, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2172, 'سانت إليزابيث', 'Saint Elizabeth', 194, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2173, 'سانت جيمس', 'Saint James', 194, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2174, 'أبرشية سانت آن', 'Parish of Saint Ann', 194, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2175, 'سانت ماري', 'Saint Mary', 194, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2176, 'بورتلاند', 'Portland', 194, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2177, 'يستمورلاند', 'Westmoreland', 194, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2178, 'سانت توماس', 'Saint Thomas', 194, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2179, 'كلارندون', 'Clarendon', 194, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2180, 'مانشستر', 'Manchester', 194, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2181, 'كينغستون', 'Kingston', 194, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2182, 'ناسيونال', 'Nacional', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2183, 'مقاطعة سانتياغو', 'Provincia de Santiago', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2184, 'مقاطعة ميرابال إيرماناس', 'Provincia de Hermanas Mirabal', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2185, 'بويرتو بلاتا', 'Puerto Plata', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2186, 'في Provincia De مقاطعة باراهونا', 'Provincia de Barahona', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2187, 'في Provincia دي سان بيدرو دي ماكوريس مقاطعة', 'Provincia de San Pedro de Macoris', 195,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2188, 'في Provincia De مقاطعة سان كريستوبال', 'Provincia de San Cristobal', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2189, 'في Provincia دي سان خوسيه دي مقاطعة Ocoa', 'Provincia de San Jose de Ocoa', 195,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2190, 'مقاطعة دوارتي في Provincia', 'Provincia Duarte', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2191, 'في Provincia دي مونتي كريستي مقاطعة', 'Provincia de Monte Cristi', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2192, 'في Provincia دي لا مقاطعة التاجراسيا', 'Provincia de La Altagracia', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2193, 'في Provincia دي سانتياغو مقاطعة رودريجيز', 'Provincia de Santiago Rodriguez', 195,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2194, 'في Provincia دي سانتو دومينغو مقاطعة', 'Provincia de Santo Domingo', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2195, 'في Provincia De مقاطعة سان خوان', 'Provincia de San Juan', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2196, 'في Provincia De مقاطعة بدرنالس', 'Provincia de Pedernales', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2197, 'مقاطعة إسبايلات في Provincia', 'Provincia Espaillat', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2198, 'في Provincia دي لا مقاطعة رومانا', 'Provincia de La Romana', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2199, 'في Provincia De مقاطعة الاستقلال', 'Provincia de Independencia', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2200, 'في Provincia دي لا فيغا مقاطعة', 'Provincia de La Vega', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2201, 'في Provincia دي إل مقاطعة Seibo', 'Provincia de El Seibo', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2202, 'في Provincia سانشيز راميريز مقاطعة', 'Provincia Sanchez Ramirez', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2203, 'في Provincia De مونسنور مقاطعة نويل', 'Provincia de Monsenor Nouel', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2204, 'في Provincia دي مونتي مقاطعة بلاتا', 'Provincia de Monte Plata', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2205, 'في Provincia De مقاطعة بيرافيا', 'Provincia de Peravia', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2206, 'مقاطعة عمدة هاتو', 'Provincia de Hato Mayor', 195, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2207, 'سابا', 'Saba', 197, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2208, 'بونير', 'Bonaire', 197, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2225, 'في Provincia دي ماتانزاس', 'Provincia de Matanzas', 199, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2226, 'في Provincia دي فيلا كلارا', 'Provincia de Villa Clara', 199, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2227, 'هافانا', 'La Habana', 199, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2228, 'في Provincia De Camagueey', 'Provincia de Camagueey', 199, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2229, 'في Provincia دي سييغو دي افيلا', 'Provincia de Ciego de Avila', 199, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2230, 'شمال إلوثيرا', 'North Eleuthera', 201, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2231, 'منطقة نيو بروفيدنس', 'New Providence District', 201, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2232, 'منطقة أباكو الوسطى', 'Central Abaco District', 201, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2233, 'مدينة منطقة فريبورت', 'City of Freeport District', 201, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2234, 'جزيرة الميناء', 'Harbour Island', 201, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2235, 'منطقة شمال أندروس', 'North Andros District', 201, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2236, 'بيميني', 'Bimini', 201, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2237, 'سانديز أبرشية', 'Sandys Parish', 202, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2238, 'القديس جورج', 'Saint George', 202, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2239, 'مدينة هاملتون', 'Hamilton lastmile_core.city', 202, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2240, 'مدينة بورت أوف سبين', 'City of Port of Spain', 204, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2241, 'تاون الأمراء', 'Princes Town', 204, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2242, 'مدينة سان فرناندو', 'City of San Fernando', 204, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2243, 'تونابونا / بياركو', 'Tunapuna/Piarco', 204, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2244, 'سانجر غراندي', 'Sangre Grande', 204, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2245, 'كوفا تاباكوايت تالبارو', 'Couva-Tabaquite-Talparo', 204, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2246, 'توباغو', 'Tobago', 204, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2247, 'سيبريا', 'Siparia', 204, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2248, 'سان خوان / افنتيلي', 'San Juan/Laventille', 204, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2249, 'توباغو الشرقية', 'Eastern Tobago', 204, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2250, 'مايارو', 'Mayaro', 204, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2251, 'نقطة فورتين', 'Point Fortin', 204, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2252, 'دييغو مارتن', 'Diego Martin', 204, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2253, 'العقوبات / Debe', 'Penal/Debe', 204, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2254, 'شجواناس', 'Chaguanas', 204, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2255, 'البلدة من اريما', 'Borough of Arima', 204, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2256, 'سانت بول شارلستون', 'Saint Paul Charlestown', 205, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2257, 'سانت ماري كايون', 'Saint Mary Cayon', 205, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2258, 'سانت جورج باستير', 'Saint George Basseterre', 205, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2259, 'سانت أندرو', 'Saint Andrew', 206, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2260, 'القديس جورج', 'Saint George', 206, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2261, 'سانت جون', 'Saint John', 206, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2262, 'القديس بول', 'Saint Paul', 206, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2263, 'سانت ديفيد', 'Saint David', 206, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2264, 'سانت باتريك', 'Saint Patrick', 206, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2265, 'رعية القديس يوحنا', 'Parish of Saint John', 207, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2266, 'رعية القديس جاورجيوس', 'Parish of Saint George', 207, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2267, 'رعية القديسة مريم', 'Parish of Saint Mary', 207, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2268, 'رعية القديس فيليب', 'Parish of Saint Philip', 207, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2269, 'رعية القديس بولس', 'Parish of Saint Paul', 207, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2271, 'رعية القديس بطرس', 'Parish of Saint Peter', 207, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2272, 'فيو فورت', 'Vieux-Fort', 208, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2273, 'سوفرير', 'Soufriere', 208, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2274, 'ربع براسلين', 'Quarter of Praslin', 208, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2275, 'ربع دوفين', 'Quarter of Dauphin', 208, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2276, 'ميكو الربع', 'Micoud Quarter', 208, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2277, 'لابوري الربع', 'Laborie Quarter', 208, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2278, 'جروس-جزيرة', 'Gros-Islet', 208, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2279, 'دينيري الربع', 'Dennery Quarter', 208, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2280, 'تشويسيول الربع', 'Choiseul Quarter', 208, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2281, 'كاستري الربع', 'Castries Quarter', 208, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2282, 'آنس-لا-راي', 'Anse-la-Raye', 208, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2283, 'غرينادين', 'Grenadines', 212, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2284, 'رعية القديس جاورجيوس', 'Parish of Saint George', 212, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2285, 'رعية شارلوت', 'Parish of Charlotte', 212, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2286, 'القديس مرقس', 'Saint Mark', 217, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2287, 'سانت باتريك', 'Saint Patrick', 217, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2288, 'سانت أندرو', 'Saint Andrew', 217, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2289, 'القديس جورج', 'Saint George', 217, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2290, 'مقاطعة بليز', 'Belize District', 219, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2291, 'منطقة كايو', 'Cayo District', 219, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2292, 'منطقة توليدو', 'Toledo District', 219, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2293, 'ستان منطقة الخور', 'Stann Creek District', 219, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2294, 'منطقة الممشى البرتقال', 'Orange Walk District', 219, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2295, 'منطقة كوروزال', 'Corozal District', 219, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2296, 'وزارة ليبرتاد لا', 'Departamento de La Libertad', 220, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2297, 'وزارة باز لا', 'Departamento de La Paz', 220, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2298, 'وزارة أوسولوتان', 'Departamento de Usulutan', 220, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2299, 'وزارة سان سلفادور', 'Departamento de San Salvador', 220, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2300, 'وزارة كوسكاتلان', 'Departamento de Cuscatlan', 220, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2301, 'وزارة سونسوناتي', 'Departamento de Sonsonate', 220, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2302, 'وزارة سان فيسنتي', 'Departamento de San Vicente', 220, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2303, 'وزارة سانتا آنا', 'Departamento de Santa Ana', 220, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2304, 'وزارة سان ميغيل', 'Departamento de San Miguel', 220, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2305, 'وزارة اهواتشابان', 'Departamento de Ahuachapan', 220, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2306, 'وزارة مورازان', 'Departamento de Morazan', 220, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2307, 'وزارة شالاتنانغو', 'Departamento de Chalatenango', 220, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2308, 'وزارة كاباناس', 'Departamento de Cabanas', 220, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2309, 'إدارة الاتحاد لا', 'Departamento de La Union', 220, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2310, 'وزارة ثاكابا', 'Departamento de Zacapa', 221, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2311, 'وزارة غواتيمالا', 'Departamento de Guatemala', 221, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2312, 'وزارة سكاتيبيكيز', 'Departamento de Sacatepequez', 221, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2313, 'وزارة ريتالوليو', 'Departamento de Retalhuleu', 221, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2314, 'وزارة كويتزالتنانغو', 'Departamento de Quetzaltenango', 221, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2315, 'وزارة ايسكوينتلا', 'Departamento de Escuintla', 221, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2316, 'Suchitepeque', 'Suchitepeque', 221, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2317, 'وزارة جوتيابا', 'Departamento de Jutiapa', 221, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2318, 'وزارة خالابا', 'Departamento de Jalapa', 221, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2319, 'وزارة هوهوتنانغو', 'Departamento de Huehuetenango', 221, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2320, 'وزارة تشيمالتنانغو', 'Departamento de Chimaltenango', 221, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2321, 'وزارة فيراباز ألتا', 'Departamento de Alta Verapaz', 221, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2322, 'وزارة كورتيس', 'Departamento de Cortes', 222, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2323, 'وزارة القولون', 'Departamento de Colon', 222, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2324, 'أطلانتيدا', 'Departamento de Atlantida', 222, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2325, 'وزارة مورازان فرانسيسكو', 'Departamento de Francisco Morazan', 222, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2326, 'وزارة سانتا باربرا', 'Departamento de Santa Barbara', 222, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2327, 'وزارة كوماياغوا', 'Departamento de Comayagua', 222, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2328, 'وزارة كوبان', 'Departamento de Copan', 222, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2329, 'غراسياس قسم ديوس', 'Departamento de Gracias a Dios', 222, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2330, 'وزارة مبيرا', 'Departamento de Lempira', 222, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2331, 'وزارة يورو', 'Departamento de Yoro', 222, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2332, 'وزارة فالي', 'Departamento de Valle', 222, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2333, 'وزارة باز لا', 'Departamento de La Paz', 222, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2334, 'جزر الخليج', 'Bay Islands', 222, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2335, 'وزارة بارايسو ايل', 'Departamento de El Paraiso', 222, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2336, 'وزارة تشولوتيكا', 'Departamento de Choluteca', 222, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2337, 'وزارة اولانجو', 'Departamento de Olancho', 222, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2338, 'وزارة كارازو', 'Departamento de Carazo', 223, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2339, 'وزارة مسايا', 'Departamento de Masaya', 223, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2340, 'وزارة ريفاس', 'Departamento de Rivas', 223, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2341, 'وزارة سيغوفيا نويفا', 'Departamento de Nueva Segovia', 223, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2342, 'وزارة غرناطة', 'Departamento de Granada', 223, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2343, 'وزارة ماناغوا', 'Departamento de Managua', 223, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2344, 'وزارة ماتاغلبا', 'Departamento de Matagalpa', 223, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2345, 'إدارة ليون', 'Departamento de Leon', 223, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2346, 'وزارة خينوتيغا', 'Departamento de Jinotega', 223, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2347, 'وزارة إستيلي', 'Departamento de Esteli', 223, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2348, 'وزارة شونتالز', 'Departamento de Chontales', 223, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2349, 'وزارة تشينانديغا', 'Departamento de Chinandega', 223, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2350, 'وزارة بواكو', 'Departamento de Boaco', 223, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2351, 'المنطقة المستقلة اتلانتيكو سور', 'Region Autonoma Atlantico Sur', 223, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2352, 'مقاطعة سان خوسيه', 'Provincia de San Jose', 224, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2353, 'محافظة ألاخويلا', 'Provincia de Alajuela', 224, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2354, 'محافظة قرطاج', 'Provincia de Cartago', 224, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2355, 'محافظة غواناكاست', 'Provincia de Guanacaste', 224, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2356, 'محافظة ليمون', 'Provincia de Limon', 224, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2357, 'محافظة بونتاريناس', 'Provincia de Puntarenas', 224, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2358, 'محافظة هيريديا', 'Provincia de Heredia', 224, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2378, 'محافظة زامورا-شينشيب', 'Provincia de Zamora-Chinchipe', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2379, 'محافظة غواياس', 'Provincia del Guayas', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2380, 'محافظة تونغوراهوا', 'Provincia del Tungurahua', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2381, 'محافظة كارتشي', 'Provincia del Carchi', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2382, 'محافظة مانابي', 'Provincia de Manabi', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2383, 'محافظة نابو', 'Provincia de Napo', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2384, 'محافظة مورونا سانتياغو', 'Provincia de Morona-Santiago', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2385, 'محافظة سانتو دومينغو دي لوس Tsachilas', 'Provincia de Santo Domingo de los Tsachilas', 225,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2386, 'مقاطعة سانتا إيلينا', 'Provincia de Santa Elena', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2387, 'محافظة كوتوباكسي', 'Provincia de Cotopaxi', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2388, 'محافظة بيشينشا', 'Provincia de Pichincha', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2389, 'محافظة إمبابورا', 'Provincia de Imbabura', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2390, 'مقاطعة من شيمبورازو', 'Provincia del Chimborazo', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2391, 'مقاطعة لوس ريوس', 'Provincia de Los Rios', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2392, 'مقاطعة من باستازا', 'Provincia del Pastaza', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2393, 'محافظة فرانسيسكو دي اوريلانا', 'Provincia de Francisco de Orellana', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2394, 'مقاطعة إل أورو', 'Provincia de El Oro', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2395, 'مقاطعة من ازواي', 'Provincia del Azuay', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2396, 'محافظة وخا', 'Provincia de Loja', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2397, 'محافظة كنار', 'Provincia del Canar', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2398, 'محافظة بوليفار', 'Provincia de Bolivar', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2399, 'محافظة ازميرالدا', 'Provincia de Esmeraldas', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2458, 'مقاطعة سوكومبيوس', 'Provincia de Sucumbios', 225, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2403, 'كونديناماركا', 'Cundinamarca', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2404, 'فالي زارة كاوكا ديل', 'Departamento del Valle del Cauca', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2405, 'وزارة كازاناري', 'Departamento de Casanare', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2406, 'وزارة ميتا', 'Departamento del Meta', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2407, 'وزارة كالداس', 'Departamento de Caldas', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2408, 'وزارة سيزار', 'Departamento del Cesar', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2409, 'أنتيوكيا', 'Antioquia', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2410, 'وزارة بوليفار', 'Departamento de Bolivar', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2411, 'وزارة بوياكا', 'Departamento de Boyaca', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2412, 'مديرية سوكري', 'Departamento de Sucre', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2413, 'بوغوتا العاصمة', 'Bogota D.C.', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2414, 'اتلانتيكو', 'Atlántico', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2415, 'وزارة كاوكا', 'Departamento del Cauca', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2416, 'سانتاندير', 'Departamento de Santander', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2417, 'بروفيدنسيا واي سانتا كاتالينا، أرخبيل سان اندريس وزارة',
   'Providencia y Santa Catalina, Departamento de Archipielago de San Andres', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2418, 'وزارة ريزارالدا', 'Departamento de Risaralda', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2419, 'وزارة غواخيرا', 'Departamento de La Guajira', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2420, 'وزارة كوينديو', 'Quindio Department', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2421, 'وزارة شوكو', 'Departamento del Choco', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2422, 'وزارة توليما', 'Departamento de Tolima', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2423, 'وزارة غواينيا', 'Departamento del Guainia', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2424, 'وزارة هويلا', 'Departamento del Huila', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2425, 'دائرة نارينو', 'Departamento de Narino', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2426, 'نورتي وزارة سانتاندير', 'Departamento de Norte de Santander', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2427, 'إدارة قرطبة', 'Departamento de Cordoba', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2428, 'ماجدالينا', 'Departamento del Magdalena', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2429, 'أمازوناس', 'Amazonas', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2430, 'وزارة كاكويتا', 'Departamento del Caqueta', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2431, 'وزارة فيتشادا', 'Departamento del Vichada', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2460, 'وزارة جوافياري', 'Departamento del Guaviare', 226, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2432, 'انكاش', 'Ancash', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2433, 'أديس أبابا', 'Tumbes', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2434, 'لا ليبرتاد', 'La Libertad', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2435, 'المنطقة دي هوانوكو', 'Region de Huanuco', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2436, 'منطقة سان مارتن دي', 'Region de San Martin', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2437, 'بيورا', 'Piura', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2438, 'امبايكي', 'Lambayeque', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2439, 'اوكايالى', 'Ucayali', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2440, 'لوريتو', 'Loreto', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2441, 'كاخاماركا', 'Cajamarca', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2442, 'أمازوناس', 'Amazonas', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2501, 'منطقة ليما', 'Lima region', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2502, 'اياكوتشو', 'Ayacucho', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2503, 'تاكنا', 'Tacna', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2504, 'إيكا', 'Ica', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2505, 'بونو', 'Puno', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2506, 'هوانكافليكا', 'Huancavelica', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2507, 'وزارة موكيجوا', 'Departamento de Moquegua', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2508, 'في Provincia دي ليما', 'Provincia de Lima', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2509, 'كالاو', 'Callao', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2510, 'جونين منطقة', 'Junín Region', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2511, 'ابوريماك', 'Apurimac', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2512, 'كوسكو', 'Cusco', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2513, 'أريكويبا', 'Arequipa', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2514, 'باسكو منطقة', 'Pasco Region', 227, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2443, 'محافظة فيراغواس', 'Provincia de Veraguas', 228, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2444, 'مقاطعة بنما', 'Provincia de Panama', 228, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2445, 'محافظة دارين', 'Provincia del Darien', 228, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2446, 'غونا يالا', 'Guna Yala', 228, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2447, 'محافظة القولون', 'Provincia de Colon', 228, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2448, 'محافظة كوكلي', 'Provincia de Cocle', 228, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2449, 'مقاطعة لوس سانتوس', 'Provincia de Los Santos', 228, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2450, 'Ngoebe-البوق', 'Ngoebe-Bugle', 228, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2451, 'محافظة هيريرا', 'Provincia de Herrera', 228, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2452, 'مقاطعة شيريكي', 'Provincia de Chiriqui', 228, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2453, 'مقاطعة بوكاس ديل تورو', 'Provincia de Bocas del Toro', 228, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2459, 'أمبيرا-Wounaan', 'Embera-Wounaan', 228, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2454, 'بقسم لغرب', 'Departement de lOuest', 229, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2455, 'الجنوب الشرقي', 'Sud-Est', 229, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2456, 'سود', 'Sud', 229, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2457, 'نورد', 'Nord', 229, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2477, 'المنطقة ديل Biobio', 'Region del Biobio', 230, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2478, 'مولي', 'Maule', 230, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2479, 'سانتياغو العاصمة', 'Santiago Metropolitan', 230, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2480, 'المنطقة دي فالبارايسو', 'Region de Valparaiso', 230, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2481, 'المنطقة دي لا أراوكاريا', 'Region de la Araucania', 230, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2482, 'كوكيمبو', 'Coquimbo', 230, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2483, 'المنطقة دي لوس ريوس', 'Region de Los Rios', 230, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2484, 'أنتوفاغاستا', 'Antofagasta', 230, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2485, 'أتاكاما', 'Atacama', 230, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2486, 'المنطقة ديل ليبرتادور جنرال برناردو أوهيجينس', 'Region del Libertador General Bernardo OHiggins', 230,
   '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2487, 'لوس لاغوس', 'Los Lagos', 230, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2599, 'نيوفاوندلاند ولابرادور', 'Newfoundland and Labrador', 246, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2488, 'المنطقة دي ماجلان ذ دي لا المقاطعة التشيلية بالقارة القطبية الجنوبية',
   'Region de Magallanes y de la Antartica Chilena', 230, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2489, 'Aysen', 'Aysen', 230, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2490, 'تارباتشا', 'Tarapacá', 230, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2491, 'المنطقة دي أريكا ذ Parinacota', 'Region de Arica y Parinacota', 230, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2492, 'شركة بني', 'El Beni', 231, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2493, 'قسم تاريخا', 'Departamento de Tarija', 231, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2494, 'قسم شوكيساكا', 'Departamento de Chuquisaca', 231, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2495, 'مقاطعة سانتا كروز', 'Departamento de Santa Cruz', 231, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2496, 'قسم بوتوسي', 'Departamento de Potosi', 231, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2497, 'أورورو', 'Oruro', 231, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2498, 'قسم لاباز', 'Departamento de La Paz', 231, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2499, 'قسم كوتشابامبا', 'Departamento de Cochabamba', 231, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2500, 'قسم باندو', 'Departamento de Pando', 231, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2531, 'جزر ليوارد', 'Leeward Islands', 232, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2532, 'ديزيل دو تنفيس', 'Iles du Vent', 232, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2609, 'نوكونونو', 'Nukunonu', 234, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2529, 'فافأو', 'Vavau', 235, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2533, 'أتوا', 'Atua', 237, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2534, 'تواماساغا', 'Tuamasaga', 237, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2613, 'سايبان', 'Saipan', 240, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2535, 'تكساس', 'Texas', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2536, 'ألاباما', 'Alabama', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2537, 'فرجينيا', 'Virginia', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2538, 'فرجينيا الغربية', 'West Virginia', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2539, 'أركنساس', 'Arkansas', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2540, 'ولاية ديلاوير', 'Delaware', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2541, 'فلوريدا', 'Florida', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2542, 'جورجيا', 'Georgia', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2543, 'إلينوي', 'Illinois', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2544, 'إنديانا', 'Indiana', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2545, 'ماريلاند', 'Maryland', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2546, 'كانساس', 'Kansas', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2547, 'كنتاكي', 'Kentucky', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2548, 'ميسوري', 'Missouri', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2549, 'ميسيسيبي', 'Mississippi', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2550, 'شمال كارولينا', 'North Carolina', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2551, 'أوهايو', 'Ohio', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2552, 'كارولينا الجنوبية', 'South Carolina', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2553, 'تينيسي', 'Tennessee', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2554, 'مقاطعة كولومبيا', 'District of Columbia', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2555, 'لويزيانا', 'Louisiana', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2556, 'نيو جيرسي', 'New Jersey', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2557, 'أوكلاهوما', 'Oklahoma', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2558, 'ولاية بنسلفانيا', 'Pennsylvania', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2562, 'كونيتيكت', 'Connecticut', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2563, 'أيوا', 'Iowa', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2564, 'ماساتشوستس', 'Massachusetts', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2565, 'مين', 'Maine', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2566, 'ميشيغان', 'Michigan', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2567, 'مينيسوتا', 'Minnesota', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2568, 'نبراسكا', 'Nebraska', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2569, 'نيويورك', 'New York', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2570, 'جنوب داكوتا', 'South Dakota', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2571, 'ولاية ويسكونسن', 'Wisconsin', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2572, 'شمال داكوتا', 'North Dakota', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2573, 'نيو هامبشاير', 'New Hampshire', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2574, 'جزيرة رود', 'Rhode Island', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2575, 'فيرمونت', 'Vermont', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2576, 'أريزونا', 'Arizona', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2577, 'كاليفورنيا', 'California', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2578, 'المكسيك جديدة', 'New Mexico', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2579, 'يوتا', 'Utah', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2580, 'كولورادو', 'Colorado', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2581, 'نيفادا', 'Nevada', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2582, 'ايداهو', 'Idaho', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2583, 'ألاسكا', 'Alaska', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2584, 'مونتانا', 'Montana', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2585, 'ولاية أوريغون', 'Oregon', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2586, 'واشنطن', 'Washington', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2587, 'وايومنغ', 'Wyoming', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2588, 'هاواي', 'Hawaii', 241, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2559, 'سانت توماس جزيرة', 'Saint Thomas Island', 243, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2560, 'سانت كروا جزيرة', 'Saint Croix Island', 243, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2561, 'القديس يوحنا جزيرة', 'Saint John Island', 243, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2589, 'المنطقة الشرقية', 'Eastern District', 245, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2590, 'كولومبيا البريطانية', 'British Columbia', 246, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2591, 'ساسكاتشوان', 'Saskatchewan', 246, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2592, 'ألبرتا', 'Alberta', 246, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2593, 'أونتاريو', 'Ontario', 246, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2594, 'كيبيك', 'Quebec', 246, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2595, 'جزيرة الأمير إدوارد', 'Prince Edward Island', 246, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2596, 'مانيتوبا', 'Manitoba', 246, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2597, 'برونزيك جديد', 'New Brunswick', 246, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2598, 'مقاطعة نفوفا سكوشيا', 'Nova Scotia', 246, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2600, 'نونافوت', 'Nunavut', 246, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2601, 'يوكون', 'Yukon', 246, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2602, 'الاقاليم الشمالية الغربية', 'Northwest Territories', 246, '2016-10-20 13:41:43.233783+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2632, 'جزر فيرجن البريطانية', 'British Virgin Islands', 211, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2633, 'إقليم المحيط البريطاني الهندي', 'British Indian Ocean Territory', 89, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2634, 'جزيرة الكريسماس', 'Christmas Island', 112, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2635, 'جزر كوكوس', 'Cocos [Keeling] Islands', 95, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2636, 'جزر كوك', 'Cook Islands', 110, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2637, 'جزر كايمان', 'Cayman Islands', 218, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2638, 'المناطق الجنوبية لفرنسا', 'French Southern Territories', 94, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2639, 'غيانا الفرنسية', 'French Guiana', 182, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2640, 'غيرنسي', 'Guernsey', 170, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2641, 'جبل طارق', 'Gibraltar', 142, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2642, 'غوام', 'Guam', 239, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2643, 'جزيرة آيل أوف مان', 'Isle of Man', 169, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2644, 'جيرسي', 'Jersey', 168, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2645, 'كوسوفو', 'Kosovo', 62, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2646, 'ماكاو', 'Macao', 104, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2647, 'مايوت', 'Mayotte', 65, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2648, 'موناكو', 'Monaco', 165, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2620, 'جزر مارشال', 'Marshall Islands', 113, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2621, 'نيوي', 'Niue', 238, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2622, 'جزر بيتكيرن', 'Pitcairn Islands', 233, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2623, 'بالاو', 'Palau', 96, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2624, 'فلسطين', 'Palestine', 26, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2625, 'بورتوريكو', 'Puerto Rico', 242, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2626, 'جورجيا الجنوبية وجزر ساندويتش الجنوبي', 'South Georgia and the South Sandwich Islands', 187,
   '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2627, 'سانت هيلانة', 'Saint Helena', 178, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2628, 'سانت مارتن', 'Sint Maarten', 198, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2629, 'جزر تركس وكايكوس', 'Turks and Caicos Islands', 209, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2630, 'جزر العذراء الأمريكية', 'U.S. Virgin Islands', 243, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version) VALUES
  (2631, 'جزر الولايات المتحدة البعيدة الصغيرة', 'U.S. Minor Outlying Islands', 244, '2016-11-20 13:24:59.067072+02',
   0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2649, 'واليس وفوتونا', 'Wallis and Futuna', 236, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2650, 'أرض', 'Åland', 54, '2016-11-20 13:24:59.067072+02', 0);
INSERT INTO lastmile_core.city (city_id, name_ar, name_en, country_id, created, version)
VALUES (2651, 'أروبا', 'Aruba', 210, '2016-12-13 14:01:58.569168+02', 0);

