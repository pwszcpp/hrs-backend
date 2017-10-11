CREATE TABLE Pracownik (
    id integer not null auto_increment,
    imie varchar(255),
    nazwisko varchar(255),
    wiek int,
    pensja int,
    plec varchar(100),
    primary key (id)
);