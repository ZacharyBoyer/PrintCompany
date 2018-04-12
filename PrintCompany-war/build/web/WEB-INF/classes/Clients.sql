/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Zach
 * Created: Mar 30, 2018
 */

CREATE TABLE `Clients` (
  `id` int() NOT NULL AUTO_INCREMENT,
  `Agentid` int() NOT NULL, 
  `FirstName` varchar(max) NOT NULL,
  `LastName` varchar(max) NOT NULL,
  `StreetNumber` varchar(max) NOT NULL,
  `StreetName` varchar(max) NOT NULL,
  `City` varchar(max) NOT NULL,
  `Province` varchar(max) NOT NULL,
  `PostalCode` varchar(100) NOT NULL,
  `TelOffice` varchar(20) NOT NULL,
  `TelCell` varchar(20) NOT NULL,
  `Email` varchar(max) NOT NULL,
  `Company` varchar(max) NOT NULL
  `CompanyType` varchar(100) NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=latin1;