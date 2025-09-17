-- -----------------------------------------------------
-- Base de datos: microservicios
-- -----------------------------------------------------

-- Crear la base de datos
CREATE DATABASE microservicios;

-- Conectarse a la base de datos
\c microservicios;

-- Crear tabla Persona
CREATE TABLE persona (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(20),
    edad INTEGER,
    identificacion VARCHAR(50),
    direccion VARCHAR(200),
    telefono VARCHAR(20)
);

-- Crear tabla Cliente (hereda de Persona)
CREATE TABLE cliente (
    id BIGINT PRIMARY KEY REFERENCES persona(id) ON DELETE CASCADE,
    clienteId VARCHAR(50) UNIQUE NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    estado BOOLEAN DEFAULT TRUE
);

-- Crear tabla Cuenta
CREATE TABLE cuenta (
    numeroCuenta BIGSERIAL PRIMARY KEY,
    tipoCuenta VARCHAR(50) NOT NULL,
    saldoInicial NUMERIC(15,2) DEFAULT 0,
    estado BOOLEAN DEFAULT TRUE,
    clienteId BIGINT NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (clienteId) REFERENCES cliente(id)
);

-- Crear tabla Movimiento
CREATE TABLE movimiento (
    id BIGSERIAL PRIMARY KEY,
    fecha DATE DEFAULT CURRENT_DATE,
    tipoMovimiento VARCHAR(50) NOT NULL,
    valor NUMERIC(15,2) NOT NULL,
    saldoDisponible NUMERIC(15,2) NOT NULL,
    cuentaId BIGINT NOT NULL,
    CONSTRAINT fk_cuenta FOREIGN KEY (cuentaId) REFERENCES cuenta(numeroCuenta)
);

-- Opcional: Insertar datos de ejemplo
INSERT INTO persona (nombre, genero, edad, identificacion, direccion, telefono)
VALUES 
('Jose Lema', 'M', 35, '0999999999', 'Otavalo sn y principal', '098254785'),
('Marianela Montalvo', 'F', 28, '0888888888', 'Amazonas y NNUU', '097548965'),
('Juan Osorio', 'M', 40, '0777777777', '13 junio y Equinoccial', '098874587');

INSERT INTO cliente (id, clienteId, contrasena, estado)
VALUES
(1, '225487', '1234', TRUE),
(2, '495878', '1234', TRUE),
(3, '496825', '5678', TRUE);

INSERT INTO cuenta (tipoCuenta, saldoInicial, estado, clienteId)
VALUES
('Ahorro', 2000, TRUE, 1),
('Corriente', 100, TRUE, 1),
('Ahorro', 0, TRUE, 2),
('Ahorro', 540, TRUE, 3);
