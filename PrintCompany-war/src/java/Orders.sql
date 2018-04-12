/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Zach
 * Created: Mar 30, 2018
 */

CREATE TABLE `Orders` (
  `id` int() NOT NULL AUTO_INCREMENT,
  `Agentid` int() NOT NULL,
  `Clientid` int() NOT NULL,
  `InvcNum` varchar(max) NOT NULL,
  `Comments` varchar(max) NOT NULL,
  `FlyerQnty` int() NOT NULL,
  `FlyerLayout` varchar(max) NOT NULL,
  `Location` varchar(100) NOT NULL,
  `FlyerImage` blob NOT NULL,
  `FlryArtAprvl` bit() NOT NULL,
  `PaymentInfo` varchar(max) NOT NULL,
  `PaymentRcvd` bit() NOT NULL,
  `PrsnlCopys` int() NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=latin1;