-- phpMyAdmin SQL Dump
-- version 4.4.15.10
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 08. Feb 2019 um 11:08
-- Server-Version: 5.5.60-MariaDB
-- PHP-Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `nightlifeapi`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `events`
--

CREATE TABLE IF NOT EXISTS `events` (
  `EventID` int(10) unsigned NOT NULL,
  `Name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `LocLat` double(15,8) NOT NULL,
  `LocLong` double(15,8) NOT NULL,
  `Date` date NOT NULL,
  `PriceIndex` int(11) NOT NULL,
  `Age` int(11) NOT NULL,
  `EntryFee` double(8,2) NOT NULL,
  `LongDescription` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `ShortDescription` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `AddressCity` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `AddressPLZ` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `AddressStreet` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `AddressNr` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Daten für Tabelle `events`
--

INSERT INTO `events` (`EventID`, `Name`, `Type`, `LocLat`, `LocLong`, `Date`, `PriceIndex`, `Age`, `EntryFee`, `LongDescription`, `ShortDescription`, `AddressCity`, `AddressPLZ`, `AddressStreet`, `AddressNr`) VALUES
(1, 'Freistädter Poetry Slam', 'Event', 48.51260030, 14.50141680, '2019-01-26', 1, 16, 0.00, 'Jede Slammerin, jeder Slammer – egal ob allein oder in Gruppenformation – hat sechs Minuten Zeit eigene Texte vorzutragen. Ob dramatisch oder lieblich, geflüstert oder geschrien, humorvoll oder ernst … je mitreißender, desto besser.', 'Gedanken freien Lauf lassen', 'Freistadt', '4240', 'Salzgasse', '25'),
(2, 'Lennox Jahresfeier 2019', 'Event', 48.30468990, 14.28752970, '2019-02-22', 2, 16, 0.00, 'Uns gibt es inzwischen 4 JAHRE - Das möchten wir mit euch am 22. und 23. Februar 2019 feiern!\nFür diesen besonderen Anlass haben wir uns etwas ganz besonderes für euch einfallen lassen...\n...seid gespannt!', 'Lennox feiert GEBURTSTAG!', 'Linz', '4020', 'Marienstraße', '2a'),
(3, 'Lennox Jahresfeier 2019', 'Event', 48.30468990, 14.28752970, '2019-02-23', 2, 16, 0.00, 'Uns gibt es inzwischen 4 JAHRE - Das möchten wir mit euch am 22. und 23. Februar 2019 feiern!\nFür diesen besonderen Anlass haben wir uns etwas ganz besonderes für euch einfallen lassen...\n...seid gespannt!', 'Lennox feiert GEBURTSTAG!', 'Linz', '4020', 'Marienstraße', '2a');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `migrations`
--

CREATE TABLE IF NOT EXISTS `migrations` (
  `id` int(10) unsigned NOT NULL,
  `migration` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Daten für Tabelle `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(28, '2018_11_27_133724_create_events_table', 1),
(29, '2018_11_27_133744_create_venues_table', 1),
(30, '2018_11_29_113224_create_venue_events_table', 1),
(31, '2018_11_29_113302_create_opening_hours_table', 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `opening_hours`
--

CREATE TABLE IF NOT EXISTS `opening_hours` (
  `OpeningHoursID` int(10) unsigned NOT NULL,
  `VenueID` int(10) unsigned NOT NULL,
  `WeekDay` int(11) NOT NULL,
  `DOpen` time NOT NULL,
  `DClose` time NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Daten für Tabelle `opening_hours`
--

INSERT INTO `opening_hours` (`OpeningHoursID`, `VenueID`, `WeekDay`, `DOpen`, `DClose`) VALUES
(1, 16, 1, '08:00:00', '04:00:00'),
(2, 16, 2, '08:00:00', '04:00:00'),
(3, 16, 3, '08:00:00', '04:00:00'),
(4, 16, 4, '08:00:00', '04:00:00'),
(5, 16, 5, '08:00:00', '04:00:00'),
(6, 16, 6, '08:00:00', '00:00:00'),
(7, 15, 0, '18:00:00', '02:00:00'),
(8, 15, 1, '18:00:00', '02:00:00'),
(9, 15, 2, '18:00:00', '02:00:00'),
(10, 15, 3, '18:00:00', '02:00:00'),
(11, 15, 4, '18:00:00', '00:00:00'),
(12, 14, 4, '20:00:00', '04:00:00'),
(13, 14, 5, '20:00:00', '04:00:00'),
(14, 13, 5, '21:00:00', '04:00:00'),
(15, 12, 1, '18:00:00', '00:00:00'),
(16, 12, 2, '18:00:00', '00:00:00'),
(17, 12, 3, '18:00:00', '00:00:00'),
(18, 12, 4, '18:00:00', '02:00:00'),
(19, 12, 5, '18:00:00', '02:00:00'),
(20, 11, 3, '20:00:00', '04:00:00'),
(21, 11, 4, '21:00:00', '05:00:00'),
(22, 11, 5, '21:00:00', '05:00:00'),
(23, 10, 3, '22:00:00', '06:00:00'),
(24, 10, 4, '22:00:00', '06:00:00'),
(25, 10, 5, '22:00:00', '06:00:00'),
(26, 9, 1, '16:00:00', '02:00:00'),
(27, 9, 2, '16:00:00', '02:00:00'),
(28, 9, 3, '16:00:00', '02:00:00'),
(29, 9, 4, '16:00:00', '04:00:00'),
(30, 9, 5, '18:00:00', '04:00:00'),
(31, 8, 1, '17:00:00', '01:00:00'),
(32, 8, 2, '17:00:00', '01:00:00'),
(33, 8, 3, '17:00:00', '01:00:00'),
(34, 8, 4, '17:00:00', '04:00:00'),
(35, 8, 5, '17:00:00', '04:00:00'),
(36, 6, 1, '16:00:00', '00:00:00'),
(37, 6, 2, '16:00:00', '00:00:00'),
(38, 6, 3, '16:00:00', '00:00:00'),
(39, 6, 4, '16:00:00', '02:00:00'),
(40, 6, 5, '16:00:00', '02:00:00'),
(42, 5, 2, '21:00:00', '04:00:00'),
(43, 5, 3, '21:00:00', '04:00:00'),
(44, 5, 4, '21:00:00', '06:00:00'),
(45, 5, 5, '21:00:00', '06:00:00');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `venues`
--

CREATE TABLE IF NOT EXISTS `venues` (
  `VenueID` int(10) unsigned NOT NULL,
  `Name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `LocLat` double(15,8) NOT NULL,
  `LocLong` double(15,8) NOT NULL,
  `PriceIndex` int(11) NOT NULL,
  `Age` int(11) NOT NULL,
  `EntryFee` double(8,2) NOT NULL,
  `LongDescription` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `ShortDescription` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `AddressCity` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `AddressPLZ` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `AddressStreet` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `AddressNr` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Daten für Tabelle `venues`
--

INSERT INTO `venues` (`VenueID`, `Name`, `Type`, `LocLat`, `LocLong`, `PriceIndex`, `Age`, `EntryFee`, `LongDescription`, `ShortDescription`, `AddressCity`, `AddressPLZ`, `AddressStreet`, `AddressNr`) VALUES
(4, 'Lokalname', 'Bar', 0.00000000, 0.00000000, 6, 18, 5.00, 'Lange Beschreibung des Lokals.', 'Kurze Beschreibung des Lokals.', 'Freistadt', '4240', 'Straße', '10'),
(5, 'Party Maus', 'Bar', 48.50418790, 14.50418370, 3, 18, 5.00, 'Gute Stimmung und eine gelungene Partynacht mit legendärer Musik garantiert!', 'Der Partystadl in der City', 'Freistadt', '4240', 'Kasernstraße', '3'),
(6, 'Sailor''s Bar', 'Bar', 48.51047000, 14.50295899, 2, 16, 0.00, 'Eine kleine, gemütliche Bar in der Freistädter Altstadt. Die gemütlichen Sitzecken laden zum Verweilen und einem längeren gemütlichen Abend ein.', 'Feine kleine Bar in der Innenstadt.', 'Freistadt', '4240', 'Dechanthofgasse', '1'),
(7, 'Local-Bühne', 'Bar', 48.51260029, 14.50360549, 3, 16, 10.00, 'Produktionen die ansprechen, gut für den Kopf sind und als begehrte Stücke unter Cineasten gehandelt werden, stehen am Programm. Willkommen im spannenden Hinterhof des Mainstream.', 'Gemütliche Bar im Kino Freistadt', 'Freistadt', '4240', 'Salzgasse', '25'),
(8, 'Lennox', 'Bar', 48.30468990, 14.28971840, 2, 16, 0.00, 'Der ideale Treffpunkt für einen After-Work-Drink (zu Happy-Hour-Preisen), ehe dann bis in die Morgenstunden Nightlife herrscht.', 'Feine Drinks von bester Qualität bei angenehmer Musik!', 'Linz', '4020', 'Marienstrasse', '2a'),
(9, 'Turmstadl', 'Bar', 48.51007279, 14.50238430, 2, 16, 0.00, 'Freunde treffen, einfach mal abschalten und eine gute Zeit haben. Es erwartet Dich eine gemütliche, kleine Bar mitten in der Freistädter Altstadt.', 'Gemütliche Altstadt Bar', 'Freistadt', '4240', 'Eisengasse', '22b'),
(10, 'Musikpark A1', 'Diskothek', 48.29198870, 14.30261510, 2, 18, 5.00, 'Feiert im Musikpark A1 die fettesten Parties in stylischem Ambiente mit topaktueller Musik!', 'Die geilste Diskothek in Oberösterreich!', 'Linz', '4020', 'Hamerlingstraße', '42'),
(11, 'Evers', 'Diskothek', 48.35759269, 14.47763869, 2, 18, 6.00, 'Im Evers Unterweitersdorf feierst du auf zwei einzigartigen Floors. Ein uriger Tanzschuppen und ein exklusiver Club-Bereich laden von Donnerstag bis Samstag ein.', 'Feiern, Tanzen und Flirten im einzigartigen Tanzschuppen', 'Radingdorf', '4210', 'Betriebsstraße', '15'),
(12, 'Latino', 'Bar', 48.51076789, 14.50248240, 2, 16, 0.00, 'Den Gästen werden innovative Ideen, Unterhaltung & Emotionen geliefert, nicht nur gutes Essen & Trinken.', 'In einer einzigartigen Atmosphäre wird die richtige Fiesta Stimmung vermittelt, die den Alltag vergessen lässt.', 'Freistadt', '4240', 'Eisengasse', '10'),
(13, 'Schlag - Der Partyschuppen', 'Diskothek', 48.52633810, 14.52979109, 2, 18, 0.00, 'Beste Disco in Freistadt​', 'unSCHLAGbar seit mehr als 50 Jahren!', 'Grünbach bei Freistadt', '4264', 'Schlag', '16'),
(14, 'Rockford', 'Bar', 48.51196980, 14.50411250, 1, 16, 0.00, 'Treffpunkt für Jugend mit attraktiven Preisen!\nSpritzer € 2,50 / Cola Rum € 2,00 / Toast um € 1,50', 'Treffpunkt für Jugend mit attraktiven Preisen', 'Freistadt', '4240', 'Samtgasse', '7'),
(15, 'Loungerie', 'Pub', 48.36807250, 14.51278920, 1, 16, 0.00, 'Für nettes Ambiente am Abend sorgt das Pub im FH-Gebäude. Dort gibt es Getränke aller Art, Cocktails und auch Snacks für den kleinen Hunger', 'Gemütliches Pub mit günstigen Preisen!', 'Hagenberg', '4232', 'Softwarepark', '12'),
(16, 'Café Monika', 'Bar', 48.36711740, 14.51806860, 2, 16, 0.00, 'Gemütliche Atmosphäre und angemesse Preise laden zu verweilen ein!', 'Kleine Bar im Ortskern von Hagenberg', 'Hagenberg', '4232', 'Hauptstraße', '85');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `venue_events`
--

CREATE TABLE IF NOT EXISTS `venue_events` (
  `VenueEventID` int(10) unsigned NOT NULL,
  `VenueID` int(10) unsigned NOT NULL,
  `WeekDay` int(11) NOT NULL,
  `LongDescription` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `ShortDescription` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `VenueEventName` text COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Daten für Tabelle `venue_events`
--

INSERT INTO `venue_events` (`VenueEventID`, `VenueID`, `WeekDay`, `LongDescription`, `ShortDescription`, `VenueEventName`) VALUES
(1, 15, 0, 'Seidl Night von 19:00 - 21:00 Uhr um € 1,50', 'Seidl Night', 'Loungerie Seidl Night'),
(2, 15, 2, 'alle Cocktails von 21:00 - 00:00 Uhr zum vergünstigten Preis von € 4,50', 'verbilligte Cocktails!', 'Loungerie Cocktail Night'),
(3, 11, 3, 'Der Tanzabend im Evers mit Schlager, Fox & Oldie Hits und prickelnden Getränkespecials. Für Dancing Stars von gestern, heute & morgen!', 'Tanzabend im Evers!', 'Schlager Leckerbissen'),
(4, 11, 4, 'Cola Rum bis 23.00 Uhr um nur 1 €uro! Danach um nur 2 €uro! Bacardi 0,35 ltr. inkl. 2 Fl. Coca Cola um nur 29 €uro!', 'Jeden Freitag ermäßigte Getränke!', 'Hoch die Hände, WOCHENENDE!');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`EventID`);

--
-- Indizes für die Tabelle `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `opening_hours`
--
ALTER TABLE `opening_hours`
  ADD PRIMARY KEY (`OpeningHoursID`),
  ADD KEY `opening_hours_venueid_foreign` (`VenueID`);

--
-- Indizes für die Tabelle `venues`
--
ALTER TABLE `venues`
  ADD PRIMARY KEY (`VenueID`);

--
-- Indizes für die Tabelle `venue_events`
--
ALTER TABLE `venue_events`
  ADD PRIMARY KEY (`VenueEventID`),
  ADD KEY `venue_events_venueid_foreign` (`VenueID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `events`
--
ALTER TABLE `events`
  MODIFY `EventID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT für Tabelle `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT für Tabelle `opening_hours`
--
ALTER TABLE `opening_hours`
  MODIFY `OpeningHoursID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=46;
--
-- AUTO_INCREMENT für Tabelle `venues`
--
ALTER TABLE `venues`
  MODIFY `VenueID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT für Tabelle `venue_events`
--
ALTER TABLE `venue_events`
  MODIFY `VenueEventID` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `opening_hours`
--
ALTER TABLE `opening_hours`
  ADD CONSTRAINT `opening_hours_venueid_foreign` FOREIGN KEY (`VenueID`) REFERENCES `venues` (`VenueID`);

--
-- Constraints der Tabelle `venue_events`
--
ALTER TABLE `venue_events`
  ADD CONSTRAINT `venue_events_venueid_foreign` FOREIGN KEY (`VenueID`) REFERENCES `venues` (`VenueID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
