DROP SCHEMA carrental;
CREATE SCHEMA carrental;
USE carrental;
-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-08-08 09:05:46.055

-- tables
-- Table: Branch
CREATE TABLE Branch
    (id      BIGINT      NOT NULL AUTO_INCREMENT,
     name    VARCHAR(30) NOT NULL,
     address VARCHAR(50) NOT NULL,
     CONSTRAINT Branch_pk PRIMARY KEY (id));

-- Table: Car
CREATE TABLE Car
    (vin_number VARCHAR(20)   NOT NULL,
     brand      VARCHAR(20)   NOT NULL,
     model      VARCHAR(20)   NOT NULL,
     body_type  VARCHAR(20)   NOT NULL,
     color      VARCHAR(20)   NOT NULL,
     year       NUMERIC(4, 0) NOT NULL,
     mileage_km NUMERIC(7, 1) NULL,
     status     VARCHAR(20)   NOT NULL,
     price      DECIMAL(4, 2) NOT NULL,
     branch_id  BIGINT        NULL,
     CONSTRAINT Car_pk PRIMARY KEY (vin_number));

-- Table: Car_return
CREATE TABLE Car_return
    (reservation_Id  BIGINT         NOT NULL,
     date            DATETIME(0)    NOT NULL,
     comments        VARCHAR(500)   NULL,
     additional_fees DECIMAL(10, 2) NULL,
     employee_Id     BIGINT         NULL,
     CONSTRAINT Car_return_pk PRIMARY KEY (reservation_Id));

-- Table: Car_take
CREATE TABLE Car_take
    (reservation_Id BIGINT       NOT NULL,
     date           DATETIME(0)  NOT NULL,
     comments       VARCHAR(500) NULL,
     employee_Id    BIGINT       NULL,
     CONSTRAINT Car_take_pk PRIMARY KEY (reservation_Id));

-- Table: Customer
CREATE TABLE Customer
    (id      BIGINT      NOT NULL AUTO_INCREMENT,
     name    VARCHAR(20) NOT NULL,
     surname VARCHAR(20) NOT NULL,
     email   VARCHAR(30) NULL,
     address VARCHAR(50) NOT NULL,
     CONSTRAINT Customer_pk PRIMARY KEY (id));

-- Table: Employee
CREATE TABLE Employee
    (id           BIGINT      NOT NULL AUTO_INCREMENT,
     name         VARCHAR(20) NOT NULL,
     surname      VARCHAR(20) NOT NULL,
     job_position VARCHAR(20) NULL,
     branch_id    BIGINT      NULL,
     CONSTRAINT Employee_pk PRIMARY KEY (id));

-- Table: Income
CREATE TABLE Income
    (year_and_month DATE           NOT NULL,
     total          DECIMAL(10, 2) NOT NULL,
     CONSTRAINT Income_pk PRIMARY KEY (year_and_month));

-- Table: Reservation
CREATE TABLE Reservation
    (id             BIGINT        NOT NULL AUTO_INCREMENT,
     Customer_id    BIGINT        NOT NULL,
     Car_vin_number VARCHAR(20)   NOT NULL,
     date_from      DATETIME(0)   NOT NULL,
     date_to        DATETIME(0)   NOT NULL,
     total_price    DECIMAL(8, 2) NOT NULL,
     branch_id_from BIGINT        NOT NULL,
     branch_id_to   BIGINT        NOT NULL,
     CONSTRAINT Reservation_pk PRIMARY KEY (id));

-- foreign keys
-- Reference: Car_Branch (table: Car)
ALTER TABLE Car
    ADD CONSTRAINT Car_Branch FOREIGN KEY Car_Branch (branch_id)
        REFERENCES Branch (id)
        ON DELETE SET NULL
        ON UPDATE CASCADE;

-- Reference: Car_return_Reservation (table: Car_return)
ALTER TABLE Car_return
    ADD CONSTRAINT Car_return_Reservation FOREIGN KEY Car_return_Reservation (reservation_Id)
        REFERENCES Reservation (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE;

-- Reference: Car_take_Reservation (table: Car_take)
ALTER TABLE Car_take
    ADD CONSTRAINT Car_take_Reservation FOREIGN KEY Car_take_Reservation (reservation_Id)
        REFERENCES Reservation (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE;

-- Reference: Employee_Branch (table: Employee)
ALTER TABLE Employee
    ADD CONSTRAINT Employee_Branch FOREIGN KEY Employee_Branch (branch_id)
        REFERENCES Branch (id)
        ON DELETE SET NULL
        ON UPDATE CASCADE;

-- Reference: Reservation_Branch_from (table: Reservation)
ALTER TABLE Reservation
    ADD CONSTRAINT Reservation_Branch_from FOREIGN KEY Reservation_Branch_from (branch_id_from)
        REFERENCES Branch (id)
        ON UPDATE CASCADE;

-- Reference: Reservation_Branch_to (table: Reservation)
ALTER TABLE Reservation
    ADD CONSTRAINT Reservation_Branch_to FOREIGN KEY Reservation_Branch_to (branch_id_to)
        REFERENCES Branch (id)
        ON UPDATE CASCADE;

-- Reference: Reservation_Car (table: Reservation)
ALTER TABLE Reservation
    ADD CONSTRAINT Reservation_Car FOREIGN KEY Reservation_Car (Car_vin_number)
        REFERENCES Car (vin_number)
        ON UPDATE CASCADE;

-- Reference: Reservation_Customer (table: Reservation)
ALTER TABLE Reservation
    ADD CONSTRAINT Reservation_Customer FOREIGN KEY Reservation_Customer (Customer_id)
        REFERENCES Customer (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE;

-- Reference: Return_Employee (table: Car_return)
ALTER TABLE Car_return
    ADD CONSTRAINT Car_return_Employee FOREIGN KEY Car_return_Employee (employee_Id)
        REFERENCES Employee (id)
        ON DELETE SET NULL
        ON UPDATE SET NULL ;

-- Reference: Take_Employee (table: Car_take)
ALTER TABLE Car_take
    ADD CONSTRAINT Car_take_Employee FOREIGN KEY Car_take_Employee (employee_Id)
        REFERENCES Employee (id)
        ON DELETE SET NULL
        ON UPDATE SET NULL ;

-- End of file.

