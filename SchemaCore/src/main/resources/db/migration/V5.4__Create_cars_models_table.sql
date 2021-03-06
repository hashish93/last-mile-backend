CREATE TABLE lastmile_core.cars_models (
  model_id bigint NOT NULL,
  brand_id bigint NOT NULL,
  model_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (model_id)
) ;

INSERT INTO lastmile_core.cars_models (model_id , brand_id , model_name) VALUES
(1, 1, 'TL'),
(2, 1, 'MDX'),
(3, 1, 'RDX'),
(4, 1, 'RL'),
(5, 1, 'Integra'),
(6, 1, 'TSX'),
(7, 2, 'A1'),
(8, 2, 'A3'),
(9, 2, 'A4'),
(10, 2, 'A5'),
(11, 2, 'A6'),
(12, 2, 'A8'),
(13, 2, 'S3'),
(14, 2, 'S4'),
(15, 2, 'S5'),
(16, 2, 'S6'),
(17, 2, 'S8'),
(18, 2, 'RS4'),
(19, 2, 'RS6'),
(20, 2, 'R8'),
(21, 2, 'Allroad'),
(22, 2, 'Q5'),
(23, 2, 'Q7'),
(26, 4, 'DB9'),
(27, 4, 'DBS'),
(28, 4, 'Vantage'),
(29, 5, '1600'),
(30, 5, 'M Z4'),
(31, 5, 'M1'),
(32, 5, 'M3'),
(33, 5, 'M5'),
(34, 5, 'M6'),
(35, 5, 'Serie 1'),
(36, 5, 'Serie 3'),
(37, 5, 'Serie 5'),
(38, 5, 'Serie 6'),
(39, 5, 'Serie 7'),
(40, 5, 'Serie 8'),
(41, 5, 'X1'),
(42, 5, 'X3'),
(43, 5, 'X5'),
(44, 5, 'X6'),
(45, 5, 'X6 M'),
(46, 5, 'Z3'),
(47, 5, 'Z4'),
(48, 6, 'Enclave'),
(49, 6, 'LaCrosse'),
(50, 6, 'LeSabre'),
(51, 6, 'Otro'),
(52, 6, 'Regal'),
(53, 6, 'Riviera'),
(54, 7, 'BLS'),
(55, 7, 'Catera'),
(56, 7, 'Concours'),
(57, 7, 'CTS'),
(58, 7, 'Deville'),
(59, 7, 'DHS'),
(60, 7, 'Escalade'),
(61, 7, 'Seville'),
(62, 7, 'SRX'),
(63, 7, 'STS'),
(64, 8, '1500'),
(65, 8, '210'),
(66, 8, '400'),
(67, 8, '454'),
(68, 8, 'Apache'),
(69, 8, 'Astra'),
(70, 8, 'Astro'),
(71, 8, 'Avalanche'),
(72, 8, 'Aveo'),
(73, 8, 'Bell Air'),
(74, 8, 'Blazer'),
(75, 8, 'Bravada'),
(76, 8, 'C 350'),
(77, 8, 'C 3500'),
(78, 8, 'C-15'),
(79, 8, 'C-20'),
(80, 8, 'C36'),
(81, 8, 'Camaro'),
(82, 8, 'Captiva'),
(83, 8, 'Cavalier'),
(84, 8, 'Chevy'),
(85, 8, 'Chevy Monza'),
(86, 8, 'Cheyenne'),
(87, 8, 'Citation-x11'),
(88, 8, 'Colorado'),
(89, 8, 'Corsa'),
(90, 8, 'Corsica'),
(91, 8, 'Corvette'),
(92, 8, 'Cruze'),
(93, 8, 'Custom'),
(94, 8, 'Cutlass'),
(95, 8, 'Deluxe'),
(96, 8, 'El Camino'),
(97, 8, 'Epica'),
(98, 8, 'Equinox'),
(99, 8, 'Eurocar'),
(100, 8, 'G-30'),
(101, 8, 'HHR'),
(102, 8, 'Impala'),
(103, 8, 'Kodiac'),
(104, 8, 'LUV'),
(105, 8, 'Malibu'),
(106, 8, 'Matiz'),
(107, 8, 'Meriva'),
(108, 8, 'Montecarlo'),
(109, 8, 'Monza'),
(110, 8, 'Monza C2'),
(111, 8, 'OldsMobile'),
(112, 8, 'Optra'),
(113, 8, 'Pop'),
(114, 8, 'S 10'),
(115, 8, 'Safari'),
(116, 8, 'Sierra'),
(117, 8, 'Silhouete'),
(118, 8, 'Silverado'),
(119, 8, 'Sonora'),
(120, 8, 'Spark'),
(121, 8, 'Suburban'),
(122, 8, 'Tahoe'),
(123, 8, 'Tornado'),
(124, 8, 'Tracker'),
(125, 8, 'Trailbalzer'),
(126, 8, 'Transport'),
(127, 8, 'Uplander'),
(128, 8, 'Vanette'),
(129, 8, 'Vectra'),
(130, 8, 'Venture'),
(131, 8, 'Yukon'),
(132, 8, 'Zafira'),
(133, 9, '300 C'),
(134, 9, '300 M'),
(135, 9, 'Aspen'),
(136, 9, 'Atos'),
(137, 9, 'Caravan'),
(138, 9, 'Cirrus'),
(139, 9, 'Concorde'),
(140, 9, 'Crossfire'),
(141, 9, 'D-350'),
(142, 9, 'Grand Caravan'),
(143, 9, 'Grand Voyager'),
(144, 9, 'Intrepid'),
(145, 9, 'Le Baron'),
(146, 9, 'LHS'),
(147, 9, 'Neon'),
(148, 9, 'New Yorker'),
(149, 9, 'Pacifica'),
(150, 9, 'PT Cruiser'),
(151, 9, 'RAM'),
(152, 9, 'Sebring'),
(153, 9, 'Shadow'),
(154, 9, 'Stratus'),
(155, 9, 'Town Country'),
(156, 9, 'Valiant'),
(157, 9, 'Voyager'),
(158, 10, 'Sedan'),
(159, 11, '1000'),
(160, 11, 'Atos'),
(161, 11, 'Attitude'),
(162, 11, 'Avenger'),
(163, 11, 'Caravan'),
(164, 11, 'Challenger'),
(165, 11, 'Charger'),
(166, 11, 'Coronet'),
(167, 11, 'D 600'),
(168, 11, 'D-150'),
(169, 11, 'D-250'),
(170, 11, 'D-350'),
(171, 11, 'Dakota'),
(172, 11, 'Dart'),
(173, 11, 'Durango'),
(174, 11, 'Grand Caravan'),
(175, 11, 'H-100'),
(176, 11, 'Intrepid'),
(177, 11, 'Journey'),
(178, 11, 'Neon'),
(179, 11, 'Nitro'),
(180, 11, 'RAM'),
(181, 11, 'Royal Mónaco'),
(182, 11, 'Stealth'),
(183, 11, 'Stratus'),
(184, 11, 'Valiant'),
(185, 11, 'Vanette'),
(186, 11, 'Verna'),
(187, 11, 'Viper'),
(188, 12, 'F1'),
(189, 12, 'F4'),
(190, 12, 'F5'),
(191, 14, '360'),
(192, 14, '430'),
(193, 14, '550'),
(194, 14, '575'),
(196, 14, '612'),
(197, 15, '500'),
(198, 15, 'Abarth'),
(199, 15, 'Albea'),
(200, 15, 'Bravo'),
(201, 15, 'Ducato Cargo Van'),
(202, 15, 'Grande Punto'),
(204, 15, 'Palio'),
(205, 15, 'Panda'),
(206, 15, 'Stilo'),
(208, 16, 'Aerostar'),
(209, 16, 'Bronco'),
(210, 16, 'Contour'),
(211, 16, 'Cougar'),
(212, 16, 'Courier'),
(213, 16, 'Crown'),
(214, 16, 'E-150'),
(215, 16, 'Ecoline'),
(216, 16, 'EcoSport'),
(217, 16, 'Edge'),
(218, 16, 'Escape'),
(219, 16, 'Escort'),
(220, 16, 'Excurcion'),
(221, 16, 'Expedition'),
(222, 16, 'Explorer'),
(223, 16, 'F-100'),
(224, 16, 'F-150'),
(225, 16, 'F-200'),
(226, 16, 'F-250'),
(227, 16, 'F-350'),
(228, 16, 'F-450'),
(229, 16, 'F-550'),
(230, 16, 'Fairlane'),
(231, 16, 'Fairmont'),
(232, 16, 'Falcon'),
(233, 16, 'Fiesta'),
(234, 16, 'FiveHundred'),
(235, 16, 'Focus'),
(236, 16, 'Freestar'),
(237, 16, 'Fusion'),
(239, 16, 'Ghia'),
(240, 16, 'Grand Marquis'),
(241, 16, 'Ikon'),
(242, 16, 'Ka'),
(243, 16, 'Lobo'),
(244, 16, 'LTD'),
(245, 16, 'Maverick'),
(246, 16, 'Mercury'),
(247, 16, 'Mondeo'),
(248, 16, 'Mustang'),
(249, 16, 'Mystique'),
(250, 16, 'Ranger'),
(251, 16, 'Sable'),
(252, 16, 'Taurus'),
(253, 16, 'ThunderBird'),
(254, 16, 'Topaz'),
(255, 16, 'Transit'),
(256, 16, 'Vanette'),
(257, 16, 'Windstar'),
(258, 17, 'Metro'),
(259, 18, 'Acadia'),
(260, 18, 'Canyon'),
(261, 18, 'Jimmy'),
(262, 18, 'Safari'),
(264, 18, 'Sierra'),
(265, 18, 'Sonoma'),
(266, 18, 'Yukon'),
(267, 19, 'Accord'),
(268, 19, 'City'),
(269, 19, 'Civic'),
(270, 19, 'Crosstour'),
(271, 19, 'CR-V'),
(272, 19, 'Fit'),
(273, 19, 'Odissey'),
(274, 19, 'Passaport'),
(275, 19, 'Pilot'),
(276, 19, 'Ridgeline'),
(277, 20, 'H1'),
(278, 20, 'H2'),
(279, 20, 'H2 Alpha'),
(280, 20, 'H3'),
(281, 20, 'H3 Alpha'),
(282, 22, 'Q-45'),
(283, 22, 'I-35'),
(284, 22, 'Q-30'),
(285, 22, 'I-30'),
(286, 23, 'Rodeo'),
(287, 23, 'Amigo'),
(288, 23, 'ELF'),
(294, 25, 'Cherokee'),
(295, 25, 'CJ7'),
(296, 25, 'Commander'),
(297, 25, 'Compass'),
(298, 25, 'Grand Cherokee'),
(299, 25, 'Grand Wagon'),
(300, 25, 'Laredo'),
(301, 25, 'Liberty'),
(302, 25, 'Patriot'),
(303, 25, 'Rubicon'),
(304, 25, 'Sahara'),
(305, 25, 'Wagoneer'),
(306, 25, 'Wrangler'),
(307, 26, 'Gallardo'),
(308, 26, 'Murcielago'),
(309, 27, 'Defender'),
(310, 27, 'Discovery'),
(311, 27, 'FreeLander'),
(312, 27, 'LR2'),
(313, 27, 'LR3'),
(314, 27, 'RangeRover'),
(315, 28, 'Aviator'),
(316, 28, 'Continental'),
(317, 28, 'LS'),
(318, 28, 'Mark'),
(319, 28, 'MKX'),
(320, 28, 'Navigator'),
(321, 28, 'Town Car'),
(322, 28, 'Zephyr'),
(323, 29, 'Quattroporte'),
(324, 29, '3200 GT'),
(325, 29, 'Gran Turismo'),
(326, 30, '3'),
(327, 30, '5'),
(328, 30, '6'),
(329, 30, 'CX7'),
(330, 30, 'CX9'),
(331, 30, 'LX'),
(332, 30, 'MPV'),
(333, 30, 'MX-5'),
(334, 30, 'Pick Up'),
(335, 31, '220'),
(336, 31, '230'),
(337, 31, '280 SE'),
(338, 31, '450'),
(339, 31, '450 SLC'),
(340, 31, '500SEL'),
(341, 31, 'B-Class'),
(342, 31, 'C-Class'),
(343, 31, 'CL-Class'),
(344, 31, 'CLK-Class'),
(345, 31, 'CLS-Class'),
(346, 31, 'A-Class'),
(347, 31, 'E-Class'),
(348, 31, 'G-Class'),
(349, 31, 'GL-Class'),
(350, 31, 'GLK-Class'),
(351, 31, 'ML-Class'),
(352, 31, 'R-Class'),
(353, 31, 'S-Class'),
(354, 31, 'SL-Class'),
(355, 31, 'SLK-Class'),
(356, 31, 'Sprinter'),
(357, 31, 'Unimog'),
(358, 31, 'Vito'),
(359, 32, 'Cougar'),
(360, 32, 'Grand Marquis'),
(361, 32, 'Mariner'),
(362, 32, 'Milan'),
(363, 32, 'Montego'),
(364, 32, 'Mountaineer'),
(365, 32, 'Sable'),
(366, 34, 'Magnett'),
(367, 34, 'MG'),
(368, 34, 'MGA'),
(369, 34, 'MGB'),
(370, 34, 'TF'),
(371, 34, 'ZR'),
(372, 34, 'ZT'),
(373, 35, 'Cooper'),
(374, 35, 'Morris'),
(375, 36, '3000 GT'),
(376, 36, 'Eclipse'),
(377, 36, 'Endeavor'),
(378, 36, 'Galant'),
(379, 36, 'L-200'),
(380, 36, 'Lancer'),
(381, 36, 'Montero'),
(382, 36, 'Montero Sport'),
(383, 36, 'Outlander'),
(384, 37, '350Z'),
(385, 37, 'Almera'),
(386, 37, 'Altima'),
(387, 37, 'Aprio'),
(388, 37, 'Armada'),
(389, 37, 'Cabstar'),
(390, 37, 'Datsun'),
(391, 37, 'Estacas'),
(392, 37, 'Frontier'),
(393, 37, 'Lucino'),
(394, 37, 'Maxima'),
(395, 37, 'Micra'),
(396, 37, 'Murano'),
(397, 37, 'Nx 2000'),
(398, 37, 'Pathfinder'),
(399, 37, 'Pick Up'),
(400, 37, 'Platina'),
(401, 37, 'Quest'),
(402, 37, 'Rogue'),
(403, 37, 'Sakura'),
(404, 37, 'Samurai'),
(405, 37, 'Sentra'),
(406, 37, 'Serie 200'),
(407, 37, 'Serie 240'),
(408, 37, 'Serie 300'),
(409, 37, 'TIIDA'),
(410, 37, 'Titan'),
(411, 37, 'Tsuru'),
(412, 37, 'Urban'),
(413, 37, 'X-Terra'),
(414, 37, 'X-Trail'),
(415, 38, 'Bravada'),
(416, 38, 'Delta 88'),
(417, 38, 'Eigthy'),
(418, 39, '206'),
(419, 39, '207'),
(420, 39, '306'),
(421, 39, '307'),
(422, 39, '405'),
(424, 39, '407'),
(425, 39, '607'),
(426, 39, 'Grand Raid'),
(427, 39, 'Partner'),
(428, 40, 'Grand Voyager'),
(429, 41, 'Aztek'),
(430, 41, 'Boneville'),
(431, 41, 'Fiero'),
(432, 41, 'Firebird'),
(433, 41, 'G3'),
(434, 41, 'G4'),
(435, 41, 'G5'),
(436, 41, 'G6'),
(437, 41, 'Grand Am'),
(438, 41, 'Grand Prix'),
(439, 41, 'GTO'),
(440, 41, 'Matiz'),
(441, 41, 'Montana'),
(442, 41, 'Solstice'),
(443, 41, 'Sunfire'),
(444, 41, 'Torrent'),
(445, 41, 'Trans Am'),
(446, 41, 'Transport'),
(447, 42, '550'),
(448, 42, '911'),
(449, 42, '914'),
(450, 42, '924'),
(451, 42, 'Boxter'),
(452, 42, 'Carrera GT'),
(453, 42, 'Cayenne'),
(454, 42, 'Cayman'),
(455, 42, 'GT3 Cup'),
(456, 42, 'Panamera'),
(457, 44, '10'),
(458, 44, '12'),
(459, 44, 'Clio'),
(460, 44, 'Kangoo'),
(461, 44, 'Laguna'),
(462, 44, 'Megane'),
(463, 44, 'Scenic'),
(464, 44, 'Scenic ll'),
(465, 44, 'Trafic'),
(468, 47, '9-3'),
(469, 47, '9-5'),
(470, 48, 'Alhambra'),
(471, 48, 'Altea'),
(472, 48, 'Cordoba'),
(474, 48, 'Leon'),
(475, 48, 'Toledo'),
(481, 50, 'Aerio'),
(482, 50, 'Grand Vitara'),
(483, 50, 'Swift'),
(484, 50, 'SX4'),
(485, 50, 'Samurai'),
(486, 50, 'Kazashi'),
(487, 50, 'Xl7'),
(488, 51, '4 Runner'),
(489, 51, 'Avalon'),
(490, 51, 'Avanza'),
(491, 51, 'Camry'),
(492, 51, 'Corolla'),
(493, 51, 'Echo'),
(494, 51, 'FJ Cruiser'),
(495, 51, 'Hiace'),
(496, 51, 'Highlander'),
(497, 51, 'Hilux'),
(498, 51, 'Matrix'),
(499, 51, 'MR2'),
(500, 51, 'Pick Up'),
(501, 51, 'Previa'),
(502, 51, 'RAV-4'),
(503, 51, 'Sequoia'),
(504, 51, 'Sienna'),
(505, 51, 'Solara'),
(506, 51, 'SR5'),
(507, 51, 'Supra'),
(508, 51, 'Tacoma'),
(509, 51, 'Tercel'),
(510, 51, 'Tundra'),
(511, 51, 'Yaris'),
(512, 52, 'Atlantic'),
(513, 52, 'Beetle'),
(514, 52, 'Bora'),
(515, 52, 'Brasilia'),
(516, 52, 'Buggy'),
(517, 52, 'Cabrio'),
(518, 52, 'Caribe'),
(519, 52, 'Combi'),
(520, 52, 'Crafter'),
(521, 52, 'CrossFox'),
(522, 52, 'Derby'),
(523, 52, 'Eos'),
(524, 52, 'Eurovan'),
(525, 52, 'Golf'),
(526, 52, 'GTI'),
(527, 52, 'Jetta'),
(528, 52, 'Lupo'),
(529, 52, 'New Beetle'),
(530, 52, 'Passat'),
(531, 52, 'Pointer'),
(532, 52, 'Polo'),
(533, 52, 'Polo Classic'),
(534, 52, 'Routan'),
(535, 52, 'Safari'),
(536, 52, 'Sedan'),
(537, 52, 'Sharan'),
(538, 52, 'SportVan'),
(539, 52, 'Touareg'),
(540, 52, 'Van'),
(541, 53, 'C70'),
(542, 53, 'S40'),
(543, 53, 'S60'),
(544, 53, 'S80'),
(545, 53, 'V50'),
(546, 53, 'V70'),
(547, 53, 'XC'),
(548, 53, 'XC 60'),
(549, 53, 'XC 90'),
(550, 3, '33'),
(551, 3, '75'),
(552, 3, '90'),
(553, 3, '145'),
(554, 3, '146'),
(555, 3, '147'),
(556, 3, '155'),
(557, 3, '156'),
(558, 3, '159'),
(559, 3, '164'),
(560, 3, '166'),
(561, 3, 'Alfasud'),
(562, 3, 'Alfetta'),
(563, 3, 'Brera'),
(564, 3, 'CrossVagon'),
(565, 3, 'GTV'),
(566, 3, 'GT'),
(567, 3, 'Giulia'),
(568, 3, 'Giulietta'),
(569, 3, 'Mito'),
(570, 3, 'RZ/SZ'),
(571, 3, 'Spider'),
(572, 3, 'SportWagon'),
(573, 3, 'Sprint'),
(574, 4, 'AR1'),
(575, 4, 'DB7'),
(576, 4, 'DB'),
(577, 4, 'Lagonda'),
(578, 4, 'V8 Vantage'),
(579, 4, 'V8'),
(580, 4, 'V12 Vanquish'),
(581, 4, 'Vanquish'),
(582, 4, 'Vantage'),
(583, 4, 'Virage'),
(584, 4, 'Volante'),
(585, 2, '80'),
(586, 2, '90'),
(587, 2, '100'),
(588, 2, '200'),
(589, 2, 'A2'),
(590, 2, 'A7'),
(591, 2, 'Cabriolet'),
(592, 2, 'Coupe'),
(593, 2, 'Q3'),
(594, 2, 'Quattro'),
(595, 2, 'RS2'),
(596, 2, 'S2'),
(597, 2, 'TT'),
(598, 2, 'V8'),
(599, 5, 'Z1'),
(600, 5, 'Z3'),
(601, 5, 'Z4'),
(602, 5, 'Z8'),
(603, 6, 'Century'),
(604, 6, 'Electra'),
(605, 6, 'Skylark'),
(606, 6, 'Roadmaster'),
(607, 7, 'Allante'),
(608, 7, 'Eldorado'),
(609, 7, 'Fleetwood'),
(610, 7, 'XLR'),
(611, 8, '2500'),
(612, 8, 'Alero'),
(613, 8, 'Beretta'),
(614, 8, 'C3 Picasso'),
(615, 8, 'Caprice'),
(616, 8, 'Chevelle'),
(617, 8, 'Evanda'),
(618, 8, 'K30'),
(619, 8, 'K1500'),
(620, 8, 'Kalos'),
(621, 8, 'Lacetti'),
(622, 8, 'Lumina'),
(623, 8, 'Nubira'),
(624, 8, 'Orlando'),
(625, 8, 'Rezzo'),
(626, 8, 'SSR'),
(627, 8, 'Trax'),
(628, 9, 'Airflite'),
(629, 9, 'Airflow'),
(630, 9, 'Avenger'),
(631, 9, 'Cordoba'),
(632, 9, 'ES'),
(633, 9, 'Firepower'),
(634, 9, 'GS'),
(635, 9, 'GTS'),
(636, 9, 'Imperial'),
(637, 9, 'KEW'),
(638, 9, 'Nassau'),
(639, 9, 'NewPort'),
(640, 9, 'Prowler'),
(641, 9, 'Royal'),
(642, 9, 'Saratoga'),
(643, 9, 'Simca'),
(644, 9, 'Six'),
(645, 9, 'SunBeam'),
(646, 9, 'TC'),
(647, 9, 'VIP'),
(648, 9, 'Viper'),
(649, 9, 'Vision'),
(651, 9, 'Wimbledon'),
(652, 9, 'Windsor'),
(653, 9, '160'),
(654, 9, '180'),
(655, 9, '2 Litre'),
(656, 9, 'Other'),
(657, 55, 'Arnage'),
(658, 55, 'Azure'),
(659, 55, 'Brooklands'),
(660, 55, 'Continental'),
(661, 55, 'Eight'),
(662, 55, 'Mulsanne'),
(663, 55, 'Turbo RT'),
(664, 55, 'Turbo R'),
(665, 55, 'Turbo S'),
(666, 58, 'Ceo'),
(667, 58, 'GoNow'),
(668, 58, 'Noble'),
(669, 58, 'Ufo'),
(670, 59, '2 CV'),
(671, 59, 'AMI 8'),
(672, 59, 'AX'),
(673, 59, 'BX'),
(674, 59, 'Berlingo'),
(675, 59, 'C1'),
(676, 59, 'C2'),
(677, 59, 'C3'),
(678, 59, 'C4 Grand Picasso'),
(679, 59, 'C4 Picasso'),
(680, 59, 'C4'),
(681, 59, 'C5'),
(682, 59, 'C6'),
(683, 59, 'C8'),
(684, 59, 'C-Crossover'),
(685, 59, 'CX'),
(686, 59, 'DS4'),
(687, 59, 'DS3'),
(688, 59, 'DS'),
(689, 59, 'Evasion'),
(690, 59, 'GSA'),
(691, 59, 'Jumpy'),
(692, 59, 'LN'),
(693, 59, 'Nemo Combi'),
(694, 59, 'SM'),
(695, 59, 'Saxo'),
(696, 59, 'Visa'),
(697, 59, 'XM'),
(698, 59, 'Xantia'),
(699, 59, 'Xsara Picasso'),
(700, 59, 'Xsara'),
(701, 59, 'ZX'),
(702, 60, 'C3'),
(703, 60, 'C4'),
(704, 60, 'C5'),
(705, 60, 'C6'),
(706, 61, 'Duster'),
(707, 61, 'Logan'),
(708, 61, 'Pick Up'),
(709, 61, 'Sandero'),
(710, 62, 'Espero'),
(711, 62, 'Evanda'),
(712, 62, 'Kalos'),
(713, 62, 'Korando'),
(714, 62, 'Lacetti'),
(716, 62, 'Lanos'),
(718, 62, 'Leganza'),
(719, 62, 'Matiz'),
(720, 62, 'Musso'),
(721, 62, 'Nexia'),
(722, 62, 'Nubira'),
(723, 62, 'Rezzo'),
(724, 62, 'Tacuma'),
(725, 63, 'Applause'),
(726, 63, 'Charade'),
(727, 63, 'Charmant'),
(728, 63, 'Copen'),
(729, 63, 'Cuore'),
(730, 63, 'Feroza/Sportrak'),
(731, 63, 'Freeclimper'),
(732, 63, 'Gran Move'),
(733, 63, 'Hijet'),
(734, 63, 'Move'),
(735, 63, 'Rocky/Fourtrak'),
(736, 63, 'Sirion'),
(737, 63, 'Terios'),
(738, 63, 'URV'),
(739, 11, 'Caliber'),
(740, 11, 'Demon'),
(741, 11, 'Hornet'),
(743, 14, '246'),
(744, 14, '250'),
(745, 14, '275'),
(746, 14, '278'),
(747, 14, '288'),
(748, 14, '308'),
(749, 14, '328'),
(750, 14, '330'),
(751, 14, '348'),
(752, 14, '365'),
(753, 14, '400'),
(754, 14, '412'),
(755, 14, '456'),
(756, 14, '512'),
(757, 14, '599 GTB'),
(758, 14, '750'),
(759, 14, 'California'),
(760, 14, 'Daytona'),
(761, 14, 'Dino GT4'),
(762, 14, 'Enzo Ferrari'),
(763, 14, 'F40'),
(764, 14, 'F50'),
(765, 14, 'F365'),
(766, 14, 'F360'),
(767, 14, 'F430'),
(768, 14, 'F550'),
(769, 14, 'Mondial'),
(770, 14, 'SuperAmerica'),
(771, 14, 'Testarossa'),
(772, 14, '125'),
(773, 14, '159'),
(774, 14, '166'),
(775, 14, '195'),
(776, 14, '196'),
(777, 14, '208'),
(778, 14, '212'),
(779, 14, '225'),
(780, 14, 'Scuderia Spider'),
(781, 14, 'Pinin'),
(782, 14, 'GTS'),
(783, 14, 'GTO'),
(784, 14, 'GTB'),
(785, 14, 'GT'),
(786, 14, 'GG50'),
(787, 15, '124'),
(788, 15, '126'),
(789, 15, '127'),
(790, 15, '130'),
(791, 15, '131'),
(792, 15, 'Barchetta'),
(793, 15, 'Brava'),
(794, 15, 'Cinquecento'),
(795, 15, 'Coupe'),
(796, 15, 'Croma'),
(797, 15, 'Dino'),
(798, 15, 'Doblo'),
(799, 15, 'Fiorino'),
(800, 15, 'Freemont'),
(801, 15, 'Linea'),
(802, 15, 'Idea'),
(803, 15, 'Marea'),
(804, 15, 'Marengo'),
(805, 15, 'Mito'),
(806, 15, 'Multipla'),
(807, 15, 'Punto'),
(808, 15, 'Qubo'),
(809, 15, 'Regata'),
(810, 15, 'Ritmo'),
(811, 15, 'Scudo'),
(812, 15, 'Sedici'),
(814, 15, 'Seicento'),
(815, 15, 'Spider Europa'),
(816, 15, 'Strada'),
(817, 15, 'Tempra'),
(818, 15, 'Tipo'),
(819, 15, 'Ulysse'),
(820, 15, 'Uno'),
(821, 15, 'X 1/9'),
(822, 16, 'B-Max'),
(823, 16, 'C-Max'),
(825, 16, 'Capri'),
(826, 16, 'Cortina'),
(827, 16, 'Econoline'),
(828, 16, 'Econovan'),
(829, 16, 'Excursion'),
(830, 16, 'Express'),
(831, 16, 'GT'),
(832, 16, 'Galaxy'),
(833, 16, 'Granada'),
(834, 16, 'Kuga'),
(835, 16, 'Orion'),
(836, 16, 'Probe'),
(837, 16, 'Puma'),
(838, 16, 'S-Max'),
(839, 16, 'Scorpio'),
(840, 16, 'Sierra'),
(841, 16, 'Tourneo'),
(842, 18, 'Savana'),
(843, 18, 'Syclone'),
(844, 18, 'Typhoon'),
(845, 18, 'Vandura'),
(846, 19, 'Aerodeck'),
(847, 19, 'CRX'),
(848, 19, 'Concerto'),
(849, 19, 'Element'),
(850, 19, 'FR-V'),
(851, 19, 'HR-V'),
(852, 19, 'Insight'),
(853, 19, 'Integra'),
(854, 19, 'Jazz'),
(855, 19, 'Prelude'),
(856, 19, 'S 2000'),
(857, 19, 'Shuttle'),
(858, 19, 'Stream'),
(859, 21, 'Accent'),
(860, 21, 'Atos'),
(861, 21, 'Coupe'),
(862, 21, 'Elantra'),
(863, 21, 'Excel'),
(864, 21, 'Galloper'),
(865, 21, 'Getz'),
(866, 21, 'Grandeur'),
(867, 21, 'H 100'),
(868, 21, 'H 200'),
(869, 21, 'H-1 Starex'),
(870, 21, 'H-1'),
(871, 21, 'Lantra'),
(872, 21, 'Matrix'),
(873, 21, 'Pony'),
(874, 21, 'S-Coupe'),
(875, 21, 'Santa Fe'),
(876, 21, 'Santamo'),
(877, 21, 'Sonata'),
(878, 21, 'Terracan'),
(879, 21, 'Trajet'),
(880, 21, 'Tucson'),
(881, 21, 'XG 30'),
(882, 21, 'XG 350'),
(883, 21, 'i 10'),
(884, 21, 'i 20'),
(885, 21, 'i 30'),
(886, 21, 'iX35'),
(887, 22, 'FX'),
(888, 22, 'G35'),
(889, 22, 'G37'),
(891, 22, 'QX-56'),
(892, 23, 'Campo'),
(893, 23, 'D-Max'),
(894, 23, 'Gemini'),
(895, 23, 'Midi'),
(896, 23, 'Pick up'),
(897, 23, 'Trooper'),
(898, 24, 'Daimler'),
(899, 24, 'E-Type'),
(900, 24, 'MK II'),
(901, 24, 'S-Type'),
(902, 24, 'X-Type'),
(903, 24, 'XF'),
(904, 24, 'XJ6'),
(905, 24, 'XJ8'),
(906, 24, 'XJ12'),
(907, 24, 'XJ40'),
(908, 24, 'XJR'),
(909, 24, 'XJSC'),
(910, 24, 'XJS'),
(911, 24, 'XJS'),
(912, 24, 'XJ'),
(913, 24, 'XKR'),
(914, 24, 'XK'),
(915, 25, 'Comanche'),
(916, 25, 'Renegade'),
(917, 25, 'Willys'),
(918, 64, 'Besta'),
(919, 64, 'Carens'),
(920, 64, 'Carnival'),
(921, 64, 'Ceed'),
(922, 64, 'Cerato'),
(923, 64, 'Clarus'),
(924, 64, 'Elan'),
(925, 64, 'Joice'),
(926, 64, 'K2500'),
(927, 64, 'K2700'),
(928, 64, 'Leo'),
(929, 64, 'Magentis'),
(930, 64, 'Mentor'),
(931, 64, 'Opirus'),
(932, 64, 'Picanto'),
(933, 64, 'Pregio'),
(934, 64, 'Pride'),
(935, 64, 'Retona'),
(936, 64, 'Rio'),
(937, 64, 'Roadster'),
(938, 64, 'Rocsta'),
(939, 64, 'Sephia'),
(940, 64, 'Shuma'),
(941, 64, 'Sorento'),
(942, 64, 'Soul'),
(943, 64, 'Sportage'),
(944, 64, 'Venga'),
(945, 65, '110'),
(946, 65, '111'),
(947, 65, '112'),
(948, 65, '1200'),
(949, 65, '2107'),
(950, 65, '2110'),
(951, 65, '2111'),
(952, 65, '2112'),
(953, 65, 'Aleko'),
(954, 65, 'Forma'),
(955, 65, 'Niva'),
(956, 65, 'Nova'),
(957, 65, 'Samara'),
(958, 26, 'Countach'),
(959, 26, 'Diablo'),
(960, 26, 'Espada'),
(961, 26, 'Jalpa'),
(962, 26, 'LM'),
(963, 26, 'Miura'),
(964, 26, 'Urraco'),
(965, 66, 'Beta'),
(966, 66, 'Dedra'),
(967, 66, 'Delta'),
(968, 66, 'Flaminia'),
(969, 66, 'Fulvia'),
(970, 66, 'Gamma'),
(971, 66, 'Kappa'),
(972, 66, 'Lybra'),
(973, 66, 'MUSA'),
(974, 66, 'Phedra'),
(975, 66, 'Prisma'),
(976, 66, 'Stratos'),
(977, 66, 'Thema'),
(978, 66, 'Thesis'),
(979, 66, 'Ypsilon'),
(980, 66, 'Zeta'),
(981, 27, 'Range Rover Evoque'),
(982, 27, 'Range Rover Sport'),
(983, 67, 'CT 200h'),
(984, 67, 'ES 300'),
(985, 67, 'ES 330'),
(986, 67, 'ES 350'),
(987, 67, 'GS Serries (All)'),
(988, 67, 'GX 470'),
(989, 67, 'IS Serries (All)'),
(990, 67, 'LS Serries (All)'),
(991, 67, 'LX 470'),
(992, 67, 'LX 570'),
(993, 67, 'RX Serries (All)'),
(994, 67, 'SC 400'),
(995, 67, 'SC 430'),
(996, 68, '340 R'),
(997, 68, 'Cortina'),
(998, 68, 'Elan'),
(999, 68, 'Elise'),
(1000, 68, 'Elite'),
(1001, 68, 'Esprit'),
(1002, 68, 'Europa'),
(1003, 68, 'Excel'),
(1004, 68, 'Exige'),
(1005, 68, 'Super Seven'),
(1006, 68, 'V8'),
(1007, 29, '222'),
(1008, 29, '224'),
(1009, 29, '228'),
(1010, 29, '418'),
(1011, 29, '420'),
(1012, 29, '422'),
(1013, 29, '424'),
(1014, 29, '430'),
(1015, 29, '3200'),
(1016, 29, '4200'),
(1017, 29, 'Biturbo'),
(1018, 29, 'Ghibli'),
(1019, 29, 'GranTurismo S'),
(1020, 29, 'Gransport'),
(1021, 29, 'Indy'),
(1022, 29, 'Karif'),
(1023, 29, 'MC12'),
(1024, 29, 'Merak'),
(1025, 29, 'Shamal'),
(1026, 29, 'Spyder'),
(1027, 30, '2'),
(1028, 30, '121'),
(1029, 30, '323'),
(1030, 30, '626'),
(1031, 30, '929'),
(1032, 30, 'B series'),
(1033, 30, 'Bongo'),
(1034, 30, 'Demio'),
(1035, 30, 'E series'),
(1036, 30, 'MX-3'),
(1037, 30, 'MX-6'),
(1038, 30, 'Millenia'),
(1039, 30, 'Premacy'),
(1040, 30, 'Protege'),
(1041, 30, 'RX-7'),
(1042, 30, 'RX-8'),
(1043, 30, 'Tribute'),
(1044, 30, 'Xedos'),
(1048, 31, '360'),
(1058, 31, '370'),
(1059, 31, '190'),
(1060, 31, '200'),
(1061, 31, '240'),
(1062, 31, '250'),
(1063, 31, '260'),
(1064, 31, '270'),
(1065, 31, '280'),
(1066, 31, '290'),
(1067, 31, '300'),
(1068, 31, '320'),
(1069, 31, '350'),
(1070, 31, '380'),
(1071, 31, '400'),
(1072, 31, '416'),
(1073, 31, '420'),
(1074, 31, '560'),
(1075, 31, '600'),
(1078, 31, 'CE Class'),
(1079, 31, 'CLA Class'),
(1080, 31, 'Citan'),
(1081, 31, 'E-Class'),
(1083, 31, 'MB 100'),
(1084, 31, 'Porter'),
(1085, 31, 'SLR'),
(1086, 31, 'V-Class'),
(1087, 31, 'Vaneo'),
(1088, 31, 'Vario'),
(1089, 31, 'Viano'),
(1090, 34, 'MGF'),
(1091, 34, 'Midget'),
(1092, 34, 'Montego'),
(1093, 34, 'TD'),
(1094, 34, 'ZS'),
(1095, 35, '1000'),
(1096, 35, '1300'),
(1097, 35, 'Clubman'),
(1098, 35, 'Cooper SD'),
(1099, 35, 'Cooper S'),
(1100, 35, 'Countryman'),
(1101, 35, 'Moke'),
(1102, 35, 'ONE'),
(1103, 36, 'Asx'),
(1104, 36, 'Canter'),
(1105, 36, 'Carisma'),
(1106, 36, 'Colt'),
(1107, 36, 'Cordia'),
(1108, 36, 'Cosmos'),
(1109, 36, 'Diamante'),
(1110, 36, 'Galloper'),
(1111, 36, 'Grandis'),
(1112, 36, 'L300'),
(1113, 36, 'L400'),
(1114, 36, 'Pajero Pinin'),
(1115, 36, 'Santamo'),
(1116, 36, 'Sapporo'),
(1117, 36, 'Sigma'),
(1118, 36, 'Space Gear'),
(1119, 36, 'Space Runner'),
(1120, 36, 'Space Star'),
(1121, 36, 'Space Wagon'),
(1122, 36, 'Starion'),
(1123, 36, 'Tredia'),
(1124, 37, '100 NX'),
(1125, 37, '200 SX'),
(1126, 37, '240 SX'),
(1127, 37, '280 ZX'),
(1128, 37, '300 ZX'),
(1129, 37, '370Z'),
(1130, 37, 'Almera Tino'),
(1131, 37, 'Bluebird'),
(1132, 37, 'Cargo'),
(1133, 37, 'Cherry'),
(1134, 37, 'GT-R'),
(1135, 37, 'Interstar'),
(1136, 37, 'Juke'),
(1137, 37, 'King Cab'),
(1138, 37, 'Kubistar'),
(1139, 37, 'Laurel'),
(1140, 37, 'Navara'),
(1141, 37, 'Note'),
(1142, 37, 'Patrol'),
(1143, 37, 'Pixo'),
(1144, 37, 'Prairie'),
(1145, 37, 'Primastar'),
(1146, 37, 'Primera'),
(1147, 37, 'Qashqai'),
(1148, 37, 'Serena'),
(1149, 37, 'Silvia'),
(1150, 37, 'Skyline'),
(1151, 37, 'Sunny'),
(1152, 37, 'Terrano'),
(1153, 37, 'Trade'),
(1154, 37, 'Urvan'),
(1155, 37, 'Vanette'),
(1156, 69, 'Agila'),
(1157, 69, 'Antara'),
(1158, 69, 'Ascona'),
(1159, 69, 'Astra'),
(1160, 69, 'Calibra'),
(1161, 69, 'Campo'),
(1162, 69, 'Cavalier'),
(1164, 69, 'Commodore'),
(1165, 69, 'Corsa'),
(1166, 69, 'Diplomat'),
(1167, 69, 'Frontera'),
(1168, 69, 'GT'),
(1169, 69, 'Insignia'),
(1170, 69, 'Kadett'),
(1171, 69, 'Manta'),
(1172, 69, 'Meriva'),
(1173, 69, 'Mokka'),
(1175, 69, 'Monterey'),
(1176, 69, 'Monza'),
(1177, 69, 'Movano'),
(1178, 69, 'Nova'),
(1179, 69, 'Omega'),
(1180, 69, 'Pick up Sportscap'),
(1181, 69, 'Rekord'),
(1182, 69, 'Senator'),
(1183, 69, 'Signum'),
(1184, 69, 'Sintra'),
(1185, 69, 'Speedster'),
(1186, 69, 'Tigra'),
(1187, 69, 'Vectra'),
(1188, 69, 'Vivaro'),
(1189, 69, 'Zafira'),
(1190, 39, '104'),
(1191, 39, '106'),
(1192, 39, '107'),
(1193, 39, '204'),
(1194, 39, '205'),
(1195, 39, '208'),
(1196, 39, '304'),
(1197, 39, '305'),
(1198, 39, '308'),
(1199, 39, '309'),
(1200, 39, '404'),
(1201, 39, '406'),
(1202, 39, '504'),
(1203, 39, '505'),
(1204, 39, '508'),
(1205, 39, '604'),
(1206, 39, '605'),
(1207, 39, '806'),
(1208, 39, '807'),
(1209, 39, '1007'),
(1210, 39, '3008'),
(1211, 39, '4007'),
(1212, 39, '5008'),
(1213, 39, 'Bipper Tepee'),
(1214, 39, 'Bipper'),
(1215, 39, 'Boxer'),
(1216, 39, 'Expert Tepee'),
(1217, 39, 'Expert'),
(1218, 39, 'J5'),
(1219, 39, 'Partner Tepee'),
(1221, 39, 'RCZ'),
(1222, 39, 'iOn'),
(1223, 40, 'Prowler'),
(1224, 41, '6000'),
(1225, 41, 'Sunbird'),
(1226, 41, 'Targa'),
(1227, 41, 'Vibe'),
(1228, 42, '356'),
(1229, 42, '912'),
(1230, 42, '928'),
(1231, 42, '944'),
(1232, 42, '959'),
(1233, 42, '962'),
(1234, 42, '968'),
(1235, 44, 'Alpine A110'),
(1236, 44, 'Alpine A310'),
(1237, 44, 'Alpine V6'),
(1238, 44, 'Avantime'),
(1239, 44, 'Coupe'),
(1240, 44, 'Espace'),
(1241, 44, 'Express'),
(1242, 44, 'Fluence'),
(1243, 44, 'Fuego'),
(1244, 44, 'Grand Espace'),
(1245, 44, 'Grand Scenic'),
(1246, 44, 'Koleos'),
(1247, 44, 'Mascott'),
(1248, 44, 'Master'),
(1249, 44, 'Modus'),
(1250, 44, 'P 1400'),
(1251, 44, 'R 4'),
(1252, 44, 'R 5'),
(1253, 44, 'R 9'),
(1254, 44, 'R 6'),
(1255, 44, 'R 11'),
(1256, 44, 'R 14'),
(1257, 44, 'R 18'),
(1258, 44, 'R 19'),
(1259, 44, 'R 20'),
(1260, 44, 'R21'),
(1261, 44, 'R 25'),
(1262, 44, 'R 30'),
(1263, 44, 'Rapid'),
(1264, 44, 'Safrane'),
(1265, 44, 'Spider'),
(1266, 44, 'Thalia'),
(1267, 44, 'Twingo'),
(1268, 44, 'Vel Satis'),
(1269, 44, 'Wind'),
(1270, 71, 'Corniche'),
(1271, 71, 'Flying Spur'),
(1272, 71, 'Park Ward'),
(1273, 71, 'Phantom'),
(1274, 71, 'Silver Cloud'),
(1275, 71, 'Silver Dawn'),
(1276, 71, 'Silver Seraph'),
(1277, 71, 'Silver Shadow'),
(1278, 71, 'Silver Spirit'),
(1279, 71, 'Silver Spur'),
(1280, 72, '25'),
(1281, 72, '45'),
(1282, 72, '75'),
(1283, 72, '100'),
(1284, 72, '111'),
(1285, 72, '114'),
(1286, 72, '115'),
(1287, 72, '200'),
(1288, 72, '213'),
(1289, 72, '214'),
(1290, 72, '216'),
(1291, 72, '218'),
(1292, 72, '220'),
(1293, 72, '400'),
(1294, 72, '414'),
(1295, 72, '416'),
(1296, 72, '418'),
(1297, 72, '420'),
(1298, 72, '600'),
(1299, 72, '618'),
(1300, 72, '620'),
(1301, 72, '623'),
(1302, 72, '800'),
(1303, 72, '825'),
(1304, 72, '827'),
(1305, 72, 'City Rover'),
(1306, 72, 'Metro'),
(1307, 72, 'Montego'),
(1308, 72, 'SD'),
(1309, 72, 'Streetwise'),
(1310, 47, '9-7X'),
(1311, 47, '90'),
(1312, 47, '96'),
(1313, 47, '99'),
(1314, 47, '900'),
(1315, 47, '9000'),
(1316, 48, 'Arosa'),
(1317, 48, 'Exeo'),
(1318, 48, 'Ibiza'),
(1319, 48, 'Inca'),
(1320, 48, 'Malaga'),
(1321, 48, 'Marbella'),
(1322, 48, 'Terra'),
(1323, 73, '105'),
(1324, 73, '120'),
(1325, 73, '130'),
(1326, 73, '135'),
(1327, 73, 'Citigo'),
(1328, 73, 'Fabia'),
(1329, 73, 'Favorit'),
(1330, 73, 'Felicia'),
(1331, 73, 'Forman'),
(1332, 73, 'Octavia'),
(1333, 73, 'Pick up'),
(1334, 73, 'Rapid'),
(1335, 73, 'Roomster'),
(1336, 73, 'Scout'),
(1337, 73, 'Superb'),
(1338, 73, 'Yeti'),
(1339, 49, 'Crossblade'),
(1340, 49, 'ForFour'),
(1341, 49, 'ForTwo'),
(1342, 49, 'Roadster'),
(1343, 74, 'Actyon'),
(1344, 74, 'Family'),
(1345, 74, 'Korando'),
(1346, 74, 'Kyron'),
(1347, 74, 'MUSSO'),
(1348, 74, 'Rexton'),
(1349, 74, 'Rodius'),
(1350, 75, 'B9 Tribeca'),
(1351, 75, 'Baja'),
(1352, 75, 'Forester'),
(1353, 75, 'Impreza'),
(1354, 75, 'Justy'),
(1355, 75, 'Legacy'),
(1356, 75, 'Libero'),
(1357, 75, 'Outback'),
(1358, 75, 'SVX'),
(1359, 75, 'Trezia'),
(1360, 75, 'Tribeca'),
(1361, 75, 'Vivio'),
(1362, 75, 'XT'),
(1363, 75, 'XV'),
(1364, 50, 'Alto'),
(1365, 50, 'Baleno'),
(1366, 50, 'Cappuccino'),
(1367, 50, 'Carry'),
(1368, 50, 'Crossover'),
(1369, 50, 'Ignis'),
(1370, 50, 'Jimny'),
(1371, 50, 'LJ'),
(1372, 50, 'Liana'),
(1373, 50, 'Splash'),
(1374, 50, 'Super-Carry'),
(1375, 50, 'Vitara'),
(1376, 50, 'Wagon R+'),
(1377, 50, 'X-90'),
(1378, 76, 'Chimaera'),
(1379, 76, 'Griffith'),
(1380, 76, 'Tuscan'),
(1381, 52, '181'),
(1382, 52, 'Amarok'),
(1383, 52, 'Caddy'),
(1384, 52, 'Corrado'),
(1385, 52, 'Fox'),
(1386, 52, 'Iltis'),
(1387, 52, 'Kaefer'),
(1388, 52, 'Karmann Ghia'),
(1389, 52, 'LT'),
(1390, 52, 'Phaeton'),
(1391, 52, 'Santana'),
(1392, 52, 'Scirocco'),
(1393, 52, 'T1'),
(1394, 52, 'T2'),
(1395, 52, 'T3'),
(1396, 52, 'T4'),
(1397, 52, 'T5'),
(1398, 52, 'Taro'),
(1399, 52, 'Tiguan'),
(1400, 52, 'Touran'),
(1401, 52, 'Vento'),
(1402, 52, 'up!'),
(1403, 53, '240'),
(1404, 53, '244'),
(1405, 53, '245'),
(1406, 53, '262'),
(1407, 53, '264'),
(1408, 53, '340'),
(1409, 53, '360'),
(1410, 53, '440'),
(1411, 53, '460'),
(1412, 53, '480'),
(1413, 53, '740'),
(1414, 53, '744'),
(1415, 53, '745'),
(1416, 53, '760'),
(1417, 53, '780'),
(1418, 53, '850'),
(1419, 53, '940'),
(1420, 53, '944'),
(1421, 53, '945'),
(1422, 53, '960'),
(1423, 53, '965'),
(1424, 53, 'Amazon'),
(1425, 53, 'C30'),
(1426, 53, 'Polar'),
(1427, 53, 'S70'),
(1428, 53, 'S90'),
(1429, 53, 'V40'),
(1430, 53, 'V60'),
(1431, 53, 'V90'),
(1433, 53, 'XC 70'),
(1434, 85, '311'),
(1435, 85, '353'),
(1436, 86, 'MF 3'),
(1437, 86, 'MF 25'),
(1438, 89, 'B3'),
(1439, 89, 'B5'),
(1440, 89, 'B6'),
(1441, 89, 'B7'),
(1442, 89, 'B8'),
(1443, 89, 'B10'),
(1444, 89, 'B12'),
(1445, 89, 'D3'),
(1446, 89, 'D 10'),
(1447, 89, 'Roadster S'),
(1448, 90, 'Atom'),
(1449, 18, 'Envoy');