/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Zach
 * Created: Mar 30, 2018
 */

CREATE TABLE `Employees`(
  `id` int() NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(max) NOT NULL,
  `LastName` varchar(max) NOT NULL,
  `Email` varchar(max) NOT NULL,
  `UserName` varchar(max) NOT NULL
  `Password` varchar(100) NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=latin1;