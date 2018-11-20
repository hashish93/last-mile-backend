CREATE TABLE lastmile_core.country
(
  country_id BIGINT                   NOT NULL,
  name_ar    CHARACTER VARYING(50)    NOT NULL,
  name_en    CHARACTER VARYING(50)    NOT NULL,
  created    TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  version    BIGINT                   NOT NULL DEFAULT 0,
  CONSTRAINT country_pk PRIMARY KEY (country_id)
);

---------------------------------------------------------------------------------------
CREATE TABLE lastmile_core.country_codes (
  id        BIGINT                NOT NULL,
  iso       CHARACTER VARYING     NOT NULL,
  name      CHARACTER VARYING(80) NOT NULL,
  nicename  CHARACTER VARYING(80) NOT NULL,
  iso3      CHARACTER VARYING DEFAULT NULL,
  numcode   BIGINT            DEFAULT NULL,
  phonecode BIGINT                NOT NULL,
  PRIMARY KEY (id)
);
---------------------------------------------------------------------------------------

INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (1, 'رواندا', 'Rwanda', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (2, 'الصومال', 'Somalia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (3, 'اليمن', 'Yemen', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (4, 'ليبيا', 'Libya', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (5, 'العراق', 'Iraq', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (6, 'المملكة العربية السعودية', 'Saudi Arabia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (7, 'إيران', 'Iran', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (8, 'أنغولا', 'Angola', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (9, 'قبرص', 'Cyprus', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (10, 'أذربيجان', 'Azerbaijan', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (11, 'تنزانيا', 'Tanzania', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (12, 'تركمانستان', 'Turkmenistan', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (13, 'سوريا', 'Syria', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (14, 'أرمينيا', 'Armenia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (15, 'زامبيا', 'Zambia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (16, 'كينيا', 'Kenya', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (17, 'الكونغو', 'Congo', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (18, 'جيبوتي', 'Djibouti', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (19, 'أوغندا', 'Uganda', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (20, 'جمهورية افريقيا الوسطى', 'Central African Republic', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (21, 'سيشيل', 'Seychelles', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (22, 'تشاد', 'Chad', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (23, 'المملكة الأردنية الهاشمية', 'Hashemite Kingdom of Jordan', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (24, 'اليونان', 'Greece', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (25, 'لبنان', 'Lebanon', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (26, 'فلسطين', 'Palestine', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (27, 'إسرائيل', 'Israel', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (28, 'الكويت', 'Kuwait', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (29, 'سلطنة عمان', 'Oman', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (30, 'قطر', 'Qatar', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (31, 'البحرين', 'Bahrain', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (32, 'الإمارات العربية المتحدة', 'United Arab Emirates', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (33, 'ديك رومي', 'Turkey', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (34, 'أثيوبيا', 'Ethiopia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (35, 'إريتريا', 'Eritrea', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (36, 'مصر', 'Egypt', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (37, 'ألبانيا', 'Albania', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (38, 'سودان', 'Sudan', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (39, 'جنوب السودان', 'South Sudan', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (40, 'بوروندي', 'Burundi', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (41, 'روسيا', 'Russia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (42, 'استونيا', 'Estonia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (43, 'بلغاريا', 'Bulgaria', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (44, 'لاتفيا', 'Latvia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (45, 'جمهورية ليتوانيا', 'Republic of Lithuania', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (46, 'السويد', 'Sweden', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (47, 'سفالبارد وجان مايان', 'Svalbard and Jan Mayen', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (48, 'كازاخستان', 'Kazakhstan', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (49, 'جورجيا', 'Georgia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (50, 'جمهورية مولدوفا', 'Republic of Moldova', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (51, 'روسيا البيضاء', 'Belarus', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (52, 'أوكرانيا', 'Ukraine', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (53, 'فنلندا', 'Finland', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (54, 'أرض', 'Åland', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (55, 'رومانيا', 'Romania', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (56, 'هنغاريا', 'Hungary', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (57, 'مقدونيا', 'Macedonia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (58, 'جمهورية سلوفاكيا', 'Slovak Republic', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (59, 'بولندا', 'Poland', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (60, 'النرويج', 'Norway', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (61, 'صربيا', 'Serbia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (62, 'كوسوفو', 'Kosovo', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (63, 'ناميبيا', 'Namibia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (64, 'زيمبابوي', 'Zimbabwe', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (65, 'مايوت', 'Mayotte', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (66, 'جزر القمر', 'Comoros', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (67, 'ملاوي', 'Malawi', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (68, 'ليسوتو', 'Lesotho', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (69, 'بوتسوانا', 'Botswana', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (70, 'موريشيوس', 'Mauritius', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (71, 'سوازيلاند', 'Swaziland', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (72, 'جمع شمل', 'Réunion', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (73, 'جنوب أفريقيا', 'South Africa', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (74, 'موزمبيق', 'Mozambique', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (75, 'مدغشقر', 'Madagascar', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (76, 'أوزبكستان', 'Uzbekistan', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (77, 'تايلاند', 'Thailand', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (78, 'أفغانستان', 'Afghanistan', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (79, 'باكستان', 'Pakistan', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (80, 'بنغلاديش', 'Bangladesh', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (81, 'أندونيسيا', 'Indonesia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (82, 'طاجيكستان', 'Tajikistan', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (83, 'ماليزيا', 'Malaysia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (84, 'سيريلانكا', 'Sri Lanka', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (85, 'بوتان', 'Bhutan', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (86, 'الهند', 'India', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (87, 'الصين', 'China', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (88, 'جزر المالديف', 'Maldives', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (89, 'إقليم المحيط البريطاني الهندي', 'British Indian Ocean Territory', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (90, 'نيبال', 'Nepal', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (91, 'ميانمار (بورما)', 'Myanmar [Burma]', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (92, 'منغوليا', 'Mongolia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (93, 'قرغيزستان', 'Kyrgyzstan', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (94, 'المناطق الجنوبية لفرنسا', 'French Southern Territories', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (95, 'جزر كوكوس', 'Cocos [Keeling] Islands', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (96, 'بالاو', 'Palau', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (97, 'فيتنام', 'Vietnam', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (98, 'تيمور الشرقية', 'East Timor', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (99, 'لاوس', 'Laos', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (100, 'تايوان', 'Taiwan', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (101, 'الفلبين', 'Philippines', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (102, 'هونغ كونغ', 'Hong Kong', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (103, 'بروناي', 'Brunei', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (104, 'ماكاو', 'Macao', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (105, 'كمبوديا', 'Cambodia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (106, 'جمهورية كوريا', 'Republic of Korea', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (107, 'اليابان', 'Japan', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (108, 'كوريا الشمالية', 'North Korea', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (109, 'سنغافورة', 'Singapore', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (110, 'جزر كوك', 'Cook Islands', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (111, 'أستراليا', 'Australia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (112, 'جزيرة الكريسماس', 'Christmas Island', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (113, 'جزر مارشال', 'Marshall Islands', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (114, 'ولايات ميكرونيزيا الموحدة', 'Federated States of Micronesia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (115, 'بابوا غينيا الجديدة', 'Papua New Guinea', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (116, 'جزر سليمان', 'Solomon Islands', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (117, 'كيريباس', 'Kiribati', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (118, 'توفالو', 'Tuvalu', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (119, 'ناورو', 'Nauru', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (120, 'فانواتو', 'Vanuatu', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (121, 'كاليدونيا الجديدة', 'New Caledonia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (122, 'جزيرة نورفولك', 'Norfolk Island', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (123, 'نيوزيلاندا', 'New Zealand', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (124, 'فيجي', 'Fiji', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (125, 'الكاميرون', 'Cameroon', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (126, 'السنغال', 'Senegal', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (127, 'جمهورية الكونغو', 'Republic of the Congo', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (128, 'البرتغال', 'Portugal', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (129, 'ليبيريا', 'Liberia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (130, 'ساحل العاج', 'Ivory Coast', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (131, 'غانا', 'Ghana', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (132, 'غينيا الإستوائية', 'Equatorial Guinea', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (133, 'نيجيريا', 'Nigeria', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (134, 'بوركينا فاسو', 'Burkina Faso', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (135, 'توغو', 'Togo', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (136, 'غينيا بيساو', 'Guinea-Bissau', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (137, 'موريتانيا', 'Mauritania', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (138, 'بنين', 'Benin', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (139, 'الغابون', 'Gabon', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (140, 'سيرا ليون', 'Sierra Leone', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (141, 'ساو تومي وبرينسيبي', 'São Tomé and Príncipe', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (142, 'جبل طارق', 'Gibraltar', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (143, 'غامبيا', 'Gambia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (144, 'غينيا', 'Guinea', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (145, 'النيجر', 'Niger', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (146, 'مالي', 'Mali', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (147, 'المغرب', 'Morocco', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (148, 'تونس', 'Tunisia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (149, 'الجزائر', 'Algeria', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (150, 'إسبانيا', 'Spain', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (151, 'إيطاليا', 'Italy', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (152, 'مالطا', 'Malta', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (153, 'النمسا', 'Austria', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (154, 'الدنمارك', 'Denmark', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (155, 'جزر صناعية', 'Faroe Islands', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (156, 'أيسلندا', 'Iceland', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (157, 'المملكة المتحدة', 'United Kingdom', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (158, 'أيرلندا', 'Ireland', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (159, 'سويسرا', 'Switzerland', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (160, 'هولندا', 'Netherlands', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (161, 'بلجيكا', 'Belgium', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (162, 'ألمانيا', 'Germany', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (163, 'لوكسمبورغ', 'Luxembourg', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (164, 'فرنسا', 'France', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (165, 'موناكو', 'Monaco', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (166, 'أندورا', 'Andorra', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (167, 'ليختنشتاين', 'Liechtenstein', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (168, 'جيرسي', 'Jersey', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (169, 'جزيرة آيل أوف مان', 'Isle of Man', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (170, 'غيرنسي', 'Guernsey', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (171, 'التشيك', 'Czechia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (172, 'مدينة الفاتيكان', 'Vatican City', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (173, 'سان مارينو', 'San Marino', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (174, 'سلوفينيا', 'Slovenia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (175, 'كرواتيا', 'Croatia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (176, 'البوسنة والهرسك', 'Bosnia and Herzegovina', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (177, 'الجبل الأسود', 'Montenegro', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (178, 'سانت هيلانة', 'Saint Helena', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (179, 'بربادوس', 'Barbados', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (180, 'الرأس الأخضر', 'Cape Verde', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (181, 'غيانا', 'Guyana', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (182, 'غيانا الفرنسية', 'French Guiana', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (183, 'سورينام', 'Suriname', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (184, 'البرازيل', 'Brazil', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (185, 'الأرض الخضراء', 'Greenland', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (186, 'سان بيار وميكلون', 'Saint Pierre and Miquelon', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version) VALUES
  (187, 'جورجيا الجنوبية وجزر ساندويتش الجنوبية', 'South Georgia and the South Sandwich Islands',
   '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (188, 'جزر فوكلاند', 'Falkland Islands', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (189, 'الأرجنتين', 'Argentina', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (190, 'باراغواي', 'Paraguay', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (191, 'أوروغواي', 'Uruguay', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (192, 'فنزويلا', 'Venezuela', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (193, 'المكسيك', 'Mexico', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (194, 'جامايكا', 'Jamaica', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (195, 'جمهورية الدومنيكان', 'Dominican Republic', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (196, 'كوراساو', 'Curaçao', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (197, 'بونير، سينت أوستاتيوس وسابا', 'Bonaire, Sint Eustatius, and Saba', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (198, 'سانت مارتن', 'Sint Maarten', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (199, 'كوبا', 'Cuba', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (200, 'مارتينيك', 'Martinique', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (201, 'الباهاما', 'Bahamas', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (202, 'برمودا', 'Bermuda', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (203, 'أنغيلا', 'Anguilla', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (204, 'ترينداد وتوباغو', 'Trinidad and Tobago', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (205, 'سانت كيتس ونيفيس', 'Saint Kitts and Nevis', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (206, 'دومينيكا', 'Dominica', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (207, 'أنتيغوا وبربودا', 'Antigua and Barbuda', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (208, 'سانت لوسيا', 'Saint Lucia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (209, 'جزر تركس وكايكوس', 'Turks and Caicos Islands', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (210, 'أروبا', 'Aruba', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (211, 'جزر فيرجن البريطانية', 'British Virgin Islands', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (212, 'سانت فنسنت وجزر غرينادين', 'Saint Vincent and the Grenadines', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (213, 'مونتسيرات', 'Montserrat', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (214, 'جوادلوب', 'Guadeloupe', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (215, 'سانت مارتن', 'Saint Martin', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (216, 'سانت بارتيليمي', 'Saint-Barthélemy', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (217, 'غرينادا', 'Grenada', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (218, 'جزر كايمان', 'Cayman Islands', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (219, 'بليز', 'Belize', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (220, 'السلفادور', 'El Salvador', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (221, 'غواتيمالا', 'Guatemala', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (222, 'هندوراس', 'Honduras', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (223, 'نيكاراغوا', 'Nicaragua', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (224, 'كوستا ريكا', 'Costa Rica', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (225, 'الإكوادور', 'Ecuador', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (226, 'كولومبيا', 'Colombia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (227, 'بيرو', 'Peru', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (228, 'بناما', 'Panama', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (229, 'هايتي', 'Haiti', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (230, 'تشيلي', 'Chile', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (231, 'بوليفيا', 'Bolivia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (232, 'بولينيزيا الفرنسية', 'French Polynesia', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (233, 'جزر بيتكيرن', 'Pitcairn Islands', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (234, 'توكيلاو', 'Tokelau', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (235, 'تونغا', 'Tonga', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (236, 'واليس وفوتونا', 'Wallis and Futuna', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (237, 'ساموا', 'Samoa', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (238, 'نيوي', 'Niue', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (239, 'غوام', 'Guam', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (240, 'جزر مريانا الشمالية', 'Northern Mariana Islands', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (241, 'الولايات المتحدة', 'United States', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (242, 'بورتوريكو', 'Puerto Rico', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (243, 'جزر العذراء الأمريكية', 'U.S. Virgin Islands', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (244, 'جزر الولايات المتحدة البعيدة الصغيرة', 'U.S. Minor Outlying Islands', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (245, 'ساموا الأمريكية', 'American Samoa', '2016-10-20 12:37:45.494353+02', 0);
INSERT INTO lastmile_core.country (country_id, name_ar, name_en, created, version)
VALUES (246, 'كندا', 'Canada', '2016-10-20 12:37:45.494353+02', 0);

------------------------------------------------------------------------------------------------------------------------

INSERT INTO lastmile_core.country_codes (id, iso, name, nicename, iso3, numcode, phonecode) VALUES
  (1, 'AF', 'AFGHANISTAN', 'Afghanistan', 'AFG', 4, 93),
  (2, 'AL', 'ALBANIA', 'Albania', 'ALB', 8, 355),
  (3, 'DZ', 'ALGERIA', 'Algeria', 'DZA', 12, 213),
  (4, 'AS', 'AMERICAN SAMOA', 'American Samoa', 'ASM', 16, 1684),
  (5, 'AD', 'ANDORRA', 'Andorra', 'AND', 20, 376),
  (6, 'AO', 'ANGOLA', 'Angola', 'AGO', 24, 244),
  (7, 'AI', 'ANGUILLA', 'Anguilla', 'AIA', 660, 1264),
  (8, 'AQ', 'ANTARCTICA', 'Antarctica', 'AQ', NULL, 0),
  (9, 'AG', 'ANTIGUA AND BARBUDA', 'Antigua and Barbuda', 'ATG', 28, 1268),
  (10, 'AR', 'ARGENTINA', 'Argentina', 'ARG', 32, 54),
  (11, 'AM', 'ARMENIA', 'Armenia', 'ARM', 51, 374),
  (12, 'AW', 'ARUBA', 'Aruba', 'ABW', 533, 297),
  (13, 'AU', 'AUSTRALIA', 'Australia', 'AUS', 36, 61),
  (14, 'AT', 'AUSTRIA', 'Austria', 'AUT', 40, 43),
  (15, 'AZ', 'AZERBAIJAN', 'Azerbaijan', 'AZE', 31, 994),
  (16, 'BS', 'BAHAMAS', 'Bahamas', 'BHS', 44, 1242),
  (17, 'BH', 'BAHRAIN', 'Bahrain', 'BHR', 48, 973),
  (18, 'BD', 'BANGLADESH', 'Bangladesh', 'BGD', 50, 880),
  (19, 'BB', 'BARBADOS', 'Barbados', 'BRB', 52, 1246),
  (20, 'BY', 'BELARUS', 'Belarus', 'BLR', 112, 375),
  (21, 'BE', 'BELGIUM', 'Belgium', 'BEL', 56, 32),
  (22, 'BZ', 'BELIZE', 'Belize', 'BLZ', 84, 501),
  (23, 'BJ', 'BENIN', 'Benin', 'BEN', 204, 229),
  (24, 'BM', 'BERMUDA', 'Bermuda', 'BMU', 60, 1441),
  (25, 'BT', 'BHUTAN', 'Bhutan', 'BTN', 64, 975),
  (26, 'BO', 'BOLIVIA', 'Bolivia', 'BOL', 68, 591),
  (27, 'BA', 'BOSNIA AND HERZEGOVINA', 'Bosnia and Herzegovina', 'BIH', 70, 387),
  (28, 'BW', 'BOTSWANA', 'Botswana', 'BWA', 72, 267),
  (29, 'BV', 'BOUVET ISLAND', 'Bouvet Island', 'BV', NULL, 0),
  (30, 'BR', 'BRAZIL', 'Brazil', 'BRA', 76, 55),
  (31, 'IO', 'BRITISH INDIAN OCEAN TERRITORY', 'British Indian Ocean Territory', 'IO', NULL, 246),
  (32, 'BN', 'BRUNEI DARUSSALAM', 'Brunei Darussalam', 'BRN', 96, 673),
  (33, 'BG', 'BULGARIA', 'Bulgaria', 'BGR', 100, 359),
  (34, 'BF', 'BURKINA FASO', 'Burkina Faso', 'BFA', 854, 226),
  (35, 'BI', 'BURUNDI', 'Burundi', 'BDI', 108, 257),
  (36, 'KH', 'CAMBODIA', 'Cambodia', 'KHM', 116, 855),
  (37, 'CM', 'CAMEROON', 'Cameroon', 'CMR', 120, 237),
  (38, 'CA', 'CANADA', 'Canada', 'CAN', 124, 1),
  (39, 'CV', 'CAPE VERDE', 'Cape Verde', 'CPV', 132, 238),
  (40, 'KY', 'CAYMAN ISLANDS', 'Cayman Islands', 'CYM', 136, 1345),
  (41, 'CF', 'CENTRAL AFRICAN REPUBLIC', 'Central African Republic', 'CAF', 140, 236),
  (42, 'TD', 'CHAD', 'Chad', 'TCD', 148, 235),
  (43, 'CL', 'CHILE', 'Chile', 'CHL', 152, 56),
  (44, 'CN', 'CHINA', 'China', 'CHN', 156, 86),
  (45, 'CX', 'CHRISTMAS ISLAND', 'Christmas Island', 'CX', NULL, 61),
  (46, 'CC', 'COCOS (KEELING) ISLANDS', 'Cocos (Keeling) Islands', 'CC', NULL, 672),
  (47, 'CO', 'COLOMBIA', 'Colombia', 'COL', 170, 57),
  (48, 'KM', 'COMOROS', 'Comoros', 'COM', 174, 269),
  (49, 'CG', 'CONGO', 'Congo', 'COG', 178, 242),
  (50, 'CD', 'CONGO, THE DEMOCRATIC REPUBLIC OF THE', 'Congo, the Democratic Republic of the', 'COD', 180, 242),
  (51, 'CK', 'COOK ISLANDS', 'Cook Islands', 'COK', 184, 682),
  (52, 'CR', 'COSTA RICA', 'Costa Rica', 'CRI', 188, 506),
  (53, 'CI', 'COTE D''IVOIRE', 'Cote D''Ivoire', 'CIV', 384, 225),
  (54, 'HR', 'CROATIA', 'Croatia', 'HRV', 191, 385),
  (55, 'CU', 'CUBA', 'Cuba', 'CUB', 192, 53),
  (56, 'CY', 'CYPRUS', 'Cyprus', 'CYP', 196, 357),
  (57, 'CZ', 'CZECH REPUBLIC', 'Czech Republic', 'CZE', 203, 420),
  (58, 'DK', 'DENMARK', 'Denmark', 'DNK', 208, 45),
  (59, 'DJ', 'DJIBOUTI', 'Djibouti', 'DJI', 262, 253),
  (60, 'DM', 'DOMINICA', 'Dominica', 'DMA', 212, 1767),
  (61, 'DO', 'DOMINICAN REPUBLIC', 'Dominican Republic', 'DOM', 214, 1809),
  (62, 'EC', 'ECUADOR', 'Ecuador', 'ECU', 218, 593),
  (63, 'EG', 'EGYPT', 'Egypt', 'EGY', 818, 20),
  (64, 'SV', 'EL SALVADOR', 'El Salvador', 'SLV', 222, 503),
  (65, 'GQ', 'EQUATORIAL GUINEA', 'Equatorial Guinea', 'GNQ', 226, 240),
  (66, 'ER', 'ERITREA', 'Eritrea', 'ERI', 232, 291),
  (67, 'EE', 'ESTONIA', 'Estonia', 'EST', 233, 372),
  (68, 'ET', 'ETHIOPIA', 'Ethiopia', 'ETH', 231, 251),
  (69, 'FK', 'FALKLAND ISLANDS (MALVINAS)', 'Falkland Islands (Malvinas)', 'FLK', 238, 500),
  (70, 'FO', 'FAROE ISLANDS', 'Faroe Islands', 'FRO', 234, 298),
  (71, 'FJ', 'FIJI', 'Fiji', 'FJI', 242, 679),
  (72, 'FI', 'FINLAND', 'Finland', 'FIN', 246, 358),
  (73, 'FR', 'FRANCE', 'France', 'FRA', 250, 33),
  (74, 'GF', 'FRENCH GUIANA', 'French Guiana', 'GUF', 254, 594),
  (75, 'PF', 'FRENCH POLYNESIA', 'French Polynesia', 'PYF', 258, 689),
  (76, 'TF', 'FRENCH SOUTHERN TERRITORIES', 'French Southern Territories', 'TF', NULL, 0),
  (77, 'GA', 'GABON', 'Gabon', 'GAB', 266, 241),
  (78, 'GM', 'GAMBIA', 'Gambia', 'GMB', 270, 220),
  (79, 'GE', 'GEORGIA', 'Georgia', 'GEO', 268, 995),
  (80, 'DE', 'GERMANY', 'Germany', 'DEU', 276, 49),
  (81, 'GH', 'GHANA', 'Ghana', 'GHA', 288, 233),
  (82, 'GI', 'GIBRALTAR', 'Gibraltar', 'GIB', 292, 350),
  (83, 'GR', 'GREECE', 'Greece', 'GRC', 300, 30),
  (84, 'GL', 'GREENLAND', 'Greenland', 'GRL', 304, 299),
  (85, 'GD', 'GRENADA', 'Grenada', 'GRD', 308, 1473),
  (86, 'GP', 'GUADELOUPE', 'Guadeloupe', 'GLP', 312, 590),
  (87, 'GU', 'GUAM', 'Guam', 'GUM', 316, 1671),
  (88, 'GT', 'GUATEMALA', 'Guatemala', 'GTM', 320, 502),
  (89, 'GN', 'GUINEA', 'Guinea', 'GIN', 324, 224),
  (90, 'GW', 'GUINEA-BISSAU', 'Guinea-Bissau', 'GNB', 624, 245),
  (91, 'GY', 'GUYANA', 'Guyana', 'GUY', 328, 592),
  (92, 'HT', 'HAITI', 'Haiti', 'HTI', 332, 509),
  (93, 'HM', 'HEARD ISLAND AND MCDONALD ISLANDS', 'Heard Island and Mcdonald Islands', 'HM', NULL, 0),
  (94, 'VA', 'HOLY SEE (VATICAN CITY STATE)', 'Holy See (Vatican City State)', 'VAT', 336, 39),
  (95, 'HN', 'HONDURAS', 'Honduras', 'HND', 340, 504),
  (96, 'HK', 'HONG KONG', 'Hong Kong', 'HKG', 344, 852),
  (97, 'HU', 'HUNGARY', 'Hungary', 'HUN', 348, 36),
  (98, 'IS', 'ICELAND', 'Iceland', 'ISL', 352, 354),
  (99, 'IN', 'INDIA', 'India', 'IND', 356, 91),
  (100, 'ID', 'INDONESIA', 'Indonesia', 'IDN', 360, 62),
  (101, 'IR', 'IRAN, ISLAMIC REPUBLIC OF', 'Iran, Islamic Republic of', 'IRN', 364, 98),
  (102, 'IQ', 'IRAQ', 'Iraq', 'IRQ', 368, 964),
  (103, 'IE', 'IRELAND', 'Ireland', 'IRL', 372, 353),
  (104, 'IL', 'ISRAEL', 'Israel', 'ISR', 376, 972),
  (105, 'IT', 'ITALY', 'Italy', 'ITA', 380, 39),
  (106, 'JM', 'JAMAICA', 'Jamaica', 'JAM', 388, 1876),
  (107, 'JP', 'JAPAN', 'Japan', 'JPN', 392, 81),
  (108, 'JO', 'JORDAN', 'Jordan', 'JOR', 400, 962),
  (109, 'KZ', 'KAZAKHSTAN', 'Kazakhstan', 'KAZ', 398, 7),
  (110, 'KE', 'KENYA', 'Kenya', 'KEN', 404, 254),
  (111, 'KI', 'KIRIBATI', 'Kiribati', 'KIR', 296, 686),
  (112, 'KP', 'KOREA, DEMOCRATIC PEOPLE''S REPUBLIC OF', 'Korea, Democratic People''s Republic of', 'PRK', 408, 850),
  (113, 'KR', 'KOREA, REPUBLIC OF', 'Korea, Republic of', 'KOR', 410, 82),
  (114, 'KW', 'KUWAIT', 'Kuwait', 'KWT', 414, 965),
  (115, 'KG', 'KYRGYZSTAN', 'Kyrgyzstan', 'KGZ', 417, 996),
  (116, 'LA', 'LAO PEOPLE''S DEMOCRATIC REPUBLIC', 'Lao People''s Democratic Republic', 'LAO', 418, 856),
  (117, 'LV', 'LATVIA', 'Latvia', 'LVA', 428, 371),
  (118, 'LB', 'LEBANON', 'Lebanon', 'LBN', 422, 961),
  (119, 'LS', 'LESOTHO', 'Lesotho', 'LSO', 426, 266),
  (120, 'LR', 'LIBERIA', 'Liberia', 'LBR', 430, 231),
  (121, 'LY', 'LIBYAN ARAB JAMAHIRIYA', 'Libyan Arab Jamahiriya', 'LBY', 434, 218),
  (122, 'LI', 'LIECHTENSTEIN', 'Liechtenstein', 'LIE', 438, 423),
  (123, 'LT', 'LITHUANIA', 'Lithuania', 'LTU', 440, 370),
  (124, 'LU', 'LUXEMBOURG', 'Luxembourg', 'LUX', 442, 352),
  (125, 'MO', 'MACAO', 'Macao', 'MAC', 446, 853),
  (126, 'MK', 'MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF', 'Macedonia, the Former Yugoslav Republic of', 'MKD', 807, 389),
  (127, 'MG', 'MADAGASCAR', 'Madagascar', 'MDG', 450, 261),
  (128, 'MW', 'MALAWI', 'Malawi', 'MWI', 454, 265),
  (129, 'MY', 'MALAYSIA', 'Malaysia', 'MYS', 458, 60),
  (130, 'MV', 'MALDIVES', 'Maldives', 'MDV', 462, 960),
  (131, 'ML', 'MALI', 'Mali', 'MLI', 466, 223),
  (132, 'MT', 'MALTA', 'Malta', 'MLT', 470, 356),
  (133, 'MH', 'MARSHALL ISLANDS', 'Marshall Islands', 'MHL', 584, 692),
  (134, 'MQ', 'MARTINIQUE', 'Martinique', 'MTQ', 474, 596),
  (135, 'MR', 'MAURITANIA', 'Mauritania', 'MRT', 478, 222),
  (136, 'MU', 'MAURITIUS', 'Mauritius', 'MUS', 480, 230),
  (137, 'YT', 'MAYOTTE', 'Mayotte', 'YT', NULL, 269),
  (138, 'MX', 'MEXICO', 'Mexico', 'MEX', 484, 52),
  (139, 'FM', 'MICRONESIA, FEDERATED STATES OF', 'Micronesia, Federated States of', 'FSM', 583, 691),
  (140, 'MD', 'MOLDOVA, REPUBLIC OF', 'Moldova, Republic of', 'MDA', 498, 373),
  (141, 'MC', 'MONACO', 'Monaco', 'MCO', 492, 377),
  (142, 'MN', 'MONGOLIA', 'Mongolia', 'MNG', 496, 976),
  (143, 'MS', 'MONTSERRAT', 'Montserrat', 'MSR', 500, 1664),
  (144, 'MA', 'MOROCCO', 'Morocco', 'MAR', 504, 212),
  (145, 'MZ', 'MOZAMBIQUE', 'Mozambique', 'MOZ', 508, 258),
  (146, 'MM', 'MYANMAR', 'Myanmar', 'MMR', 104, 95),
  (147, 'NA', 'NAMIBIA', 'Namibia', 'NAM', 516, 264),
  (148, 'NR', 'NAURU', 'Nauru', 'NRU', 520, 674),
  (149, 'NP', 'NEPAL', 'Nepal', 'NPL', 524, 977),
  (150, 'NL', 'NETHERLANDS', 'Netherlands', 'NLD', 528, 31),
  (151, 'AN', 'NETHERLANDS ANTILLES', 'Netherlands Antilles', 'ANT', 530, 599),
  (152, 'NC', 'NEW CALEDONIA', 'New Caledonia', 'NCL', 540, 687),
  (153, 'NZ', 'NEW ZEALAND', 'New Zealand', 'NZL', 554, 64),
  (154, 'NI', 'NICARAGUA', 'Nicaragua', 'NIC', 558, 505),
  (155, 'NE', 'NIGER', 'Niger', 'NER', 562, 227),
  (156, 'NG', 'NIGERIA', 'Nigeria', 'NGA', 566, 234),
  (157, 'NU', 'NIUE', 'Niue', 'NIU', 570, 683),
  (158, 'NF', 'NORFOLK ISLAND', 'Norfolk Island', 'NFK', 574, 672),
  (159, 'MP', 'NORTHERN MARIANA ISLANDS', 'Northern Mariana Islands', 'MNP', 580, 1670),
  (160, 'NO', 'NORWAY', 'Norway', 'NOR', 578, 47),
  (161, 'OM', 'OMAN', 'Oman', 'OMN', 512, 968),
  (162, 'PK', 'PAKISTAN', 'Pakistan', 'PAK', 586, 92),
  (163, 'PW', 'PALAU', 'Palau', 'PLW', 585, 680),
  (164, 'PS', 'PALESTINE', 'Palestine', 'Palestine', NULL, 970),
  (165, 'PA', 'PANAMA', 'Panama', 'PAN', 591, 507),
  (166, 'PG', 'PAPUA NEW GUINEA', 'Papua New Guinea', 'PNG', 598, 675),
  (167, 'PY', 'PARAGUAY', 'Paraguay', 'PRY', 600, 595),
  (168, 'PE', 'PERU', 'Peru', 'PER', 604, 51),
  (169, 'PH', 'PHILIPPINES', 'Philippines', 'PHL', 608, 63),
  (170, 'PN', 'PITCAIRN', 'Pitcairn', 'PCN', 612, 0),
  (171, 'PL', 'POLAND', 'Poland', 'POL', 616, 48),
  (172, 'PT', 'PORTUGAL', 'Portugal', 'PRT', 620, 351),
  (173, 'PR', 'PUERTO RICO', 'Puerto Rico', 'PRI', 630, 1787),
  (174, 'QA', 'QATAR', 'Qatar', 'QAT', 634, 974),
  (175, 'RE', 'REUNION', 'Reunion', 'REU', 638, 262),
  (176, 'RO', 'ROMANIA', 'Romania', 'ROM', 642, 40),
  (177, 'RU', 'RUSSIAN FEDERATION', 'Russian Federation', 'RUS', 643, 70),
  (178, 'RW', 'RWANDA', 'Rwanda', 'RWA', 646, 250),
  (179, 'SH', 'SAINT HELENA', 'Saint Helena', 'SHN', 654, 290),
  (180, 'KN', 'SAINT KITTS AND NEVIS', 'Saint Kitts and Nevis', 'KNA', 659, 1869),
  (181, 'LC', 'SAINT LUCIA', 'Saint Lucia', 'LCA', 662, 1758),
  (182, 'PM', 'SAINT PIERRE AND MIQUELON', 'Saint Pierre and Miquelon', 'SPM', 666, 508),
  (183, 'VC', 'SAINT VINCENT AND THE GRENADINES', 'Saint Vincent and the Grenadines', 'VCT', 670, 1784),
  (184, 'WS', 'SAMOA', 'Samoa', 'WSM', 882, 684),
  (185, 'SM', 'SAN MARINO', 'San Marino', 'SMR', 674, 378),
  (186, 'ST', 'SAO TOME AND PRINCIPE', 'Sao Tome and Principe', 'STP', 678, 239),
  (187, 'SA', 'SAUDI ARABIA', 'Saudi Arabia', 'SAU', 682, 966),
  (188, 'SN', 'SENEGAL', 'Senegal', 'SEN', 686, 221),
  (189, 'CS', 'SERBIA AND MONTENEGRO', 'Serbia and Montenegro', 'CS', NULL, 381),
  (190, 'SC', 'SEYCHELLES', 'Seychelles', 'SYC', 690, 248),
  (191, 'SL', 'SIERRA LEONE', 'Sierra Leone', 'SLE', 694, 232),
  (192, 'SG', 'SINGAPORE', 'Singapore', 'SGP', 702, 65),
  (193, 'SK', 'SLOVAKIA', 'Slovakia', 'SVK', 703, 421),
  (194, 'SI', 'SLOVENIA', 'Slovenia', 'SVN', 705, 386),
  (195, 'SB', 'SOLOMON ISLANDS', 'Solomon Islands', 'SLB', 90, 677),
  (196, 'SO', 'SOMALIA', 'Somalia', 'SOM', 706, 252),
  (197, 'ZA', 'SOUTH AFRICA', 'South Africa', 'ZAF', 710, 27),
  (198, 'GS', 'SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS', 'South Georgia and the South Sandwich Islands', 'GS', NULL, 0),
  (199, 'ES', 'SPAIN', 'Spain', 'ESP', 724, 34),
  (200, 'LK', 'SRI LANKA', 'Sri Lanka', 'LKA', 144, 94),
  (201, 'SD', 'SUDAN', 'Sudan', 'SDN', 736, 249),
  (202, 'SR', 'SURINAME', 'Suriname', 'SUR', 740, 597),
  (203, 'SJ', 'SVALBARD AND JAN MAYEN', 'Svalbard and Jan Mayen', 'SJM', 744, 47),
  (204, 'SZ', 'SWAZILAND', 'Swaziland', 'SWZ', 748, 268),
  (205, 'SE', 'SWEDEN', 'Sweden', 'SWE', 752, 46),
  (206, 'CH', 'SWITZERLAND', 'Switzerland', 'CHE', 756, 41),
  (207, 'SY', 'SYRIAN ARAB REPUBLIC', 'Syrian Arab Republic', 'SYR', 760, 963),
  (208, 'TW', 'TAIWAN, PROVINCE OF CHINA', 'Taiwan, Province of China', 'TWN', 158, 886),
  (209, 'TJ', 'TAJIKISTAN', 'Tajikistan', 'TJK', 762, 992),
  (210, 'TZ', 'TANZANIA, UNITED REPUBLIC OF', 'Tanzania, United Republic of', 'TZA', 834, 255),
  (211, 'TH', 'THAILAND', 'Thailand', 'THA', 764, 66),
  (212, 'TL', 'TIMOR-LESTE', 'Timor-Leste', 'TL', NULL, 670),
  (213, 'TG', 'TOGO', 'Togo', 'TGO', 768, 228),
  (214, 'TK', 'TOKELAU', 'Tokelau', 'TKL', 772, 690),
  (215, 'TO', 'TONGA', 'Tonga', 'TON', 776, 676),
  (216, 'TT', 'TRINIDAD AND TOBAGO', 'Trinidad and Tobago', 'TTO', 780, 1868),
  (217, 'TN', 'TUNISIA', 'Tunisia', 'TUN', 788, 216),
  (218, 'TR', 'TURKEY', 'Turkey', 'TUR', 792, 90),
  (219, 'TM', 'TURKMENISTAN', 'Turkmenistan', 'TKM', 795, 7370),
  (220, 'TC', 'TURKS AND CAICOS ISLANDS', 'Turks and Caicos Islands', 'TCA', 796, 1649),
  (221, 'TV', 'TUVALU', 'Tuvalu', 'TUV', 798, 688),
  (222, 'UG', 'UGANDA', 'Uganda', 'UGA', 800, 256),
  (223, 'UA', 'UKRAINE', 'Ukraine', 'UKR', 804, 380),
  (224, 'AE', 'UNITED ARAB EMIRATES', 'United Arab Emirates', 'ARE', 784, 971),
  (225, 'GB', 'UNITED KINGDOM', 'United Kingdom', 'GBR', 826, 44),
  (226, 'US', 'UNITED STATES', 'United States', 'USA', 840, 1),
  (227, 'UM', 'UNITED STATES MINOR OUTLYING ISLANDS', 'United States Minor Outlying Islands', 'UM', NULL, 1),
  (228, 'UY', 'URUGUAY', 'Uruguay', 'URY', 858, 598),
  (229, 'UZ', 'UZBEKISTAN', 'Uzbekistan', 'UZB', 860, 998),
  (230, 'VU', 'VANUATU', 'Vanuatu', 'VUT', 548, 678),
  (231, 'VE', 'VENEZUELA', 'Venezuela', 'VEN', 862, 58),
  (232, 'VN', 'VIET NAM', 'Viet Nam', 'VNM', 704, 84),
  (233, 'VG', 'VIRGIN ISLANDS, BRITISH', 'Virgin Islands, British', 'VGB', 92, 1284),
  (234, 'VI', 'VIRGIN ISLANDS, U.S.', 'Virgin Islands, U.s.', 'VIR', 850, 1340),
  (235, 'WF', 'WALLIS AND FUTUNA', 'Wallis and Futuna', 'WLF', 876, 681),
  (236, 'EH', 'WESTERN SAHARA', 'Western Sahara', 'ESH', 732, 212),
  (237, 'YE', 'YEMEN', 'Yemen', 'YEM', 887, 967),
  (238, 'ZM', 'ZAMBIA', 'Zambia', 'ZMB', 894, 260),
  (239, 'ZW', 'ZIMBABWE', 'Zimbabwe', 'ZWE', 716, 263),
  (240, 'RS', 'SERBIA', 'Serbia', 'SRB', 688, 381),
  (241, 'AP', 'ASIA PACIFIC REGION', 'Asia / Pacific Region', 'AP', 0, 0),
  (242, 'ME', 'MONTENEGRO', 'Montenegro', 'MNE', 499, 382),
  (243, 'AX', 'ALAND ISLANDS', 'Aland Islands', 'ALA', 248, 358),
  (244, 'BQ', 'BONAIRE, SINT EUSTATIUS AND SABA', 'Bonaire, Sint Eustatius and Saba', 'BES', 535, 599),
  (245, 'CW', 'CURACAO', 'Curacao', 'CUW', 531, 599),
  (246, 'GG', 'GUERNSEY', 'Guernsey', 'GGY', 831, 44),
  (247, 'IM', 'ISLE OF MAN', 'Isle of Man', 'IMN', 833, 44),
  (248, 'JE', 'JERSEY', 'Jersey', 'JEY', 832, 44),
  (249, 'XK', 'KOSOVO', 'Kosovo', 'XK', 0, 381),
  (250, 'BL', 'SAINT BARTHELEMY', 'Saint Barthelemy', 'BLM', 652, 590),
  (251, 'MF', 'SAINT MARTIN', 'Saint Martin', 'MAF', 663, 590),
  (252, 'SX', 'SINT MAARTEN', 'Sint Maarten', 'SXM', 534, 1),
  (253, 'SS', 'SOUTH SUDAN', 'South Sudan', 'SSD', 728, 211);