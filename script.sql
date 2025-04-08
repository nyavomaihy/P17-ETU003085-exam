DROP DATABASE nainaexam;
CREATE DATABASE nainaexam;
use nainaexam;

CREATE TABLE Prevision_naina(
    id_prevision INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(150),
    montant_principale INT,
    montant INT
);

CREATE table Depense_naina(
    id_depense INT AUTO_INCREMENT PRIMARY KEY,
    id_prevision INT,
    depense_montant INT,
    FOREIGN KEY (id_prevision) REFERENCES Prevision_naina(id_prevision)
);