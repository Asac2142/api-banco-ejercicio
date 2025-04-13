DROP TABLE IF EXISTS movimientos;
DROP TABLE IF EXISTS cuenta;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS persona;

CREATE TABLE persona (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(20),
    edad INT CHECK (edad >= 0),
    identificacion VARCHAR(20) NOT NULL UNIQUE,
    direccion VARCHAR(255),
    telefono VARCHAR(20)
);

CREATE TABLE cliente (
    id BIGINT PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    estado BOOLEAN,
    CONSTRAINT fk_persona FOREIGN KEY (id) REFERENCES persona(id) ON DELETE CASCADE
);

CREATE TABLE cuenta (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    numero_cuenta VARCHAR(20) NOT NULL UNIQUE,
    tipo_cuenta VARCHAR(50) NOT NULL,
    saldo_inicial DECIMAL(15, 2) NOT NULL CHECK (saldo_inicial >= 0),
    estado BOOLEAN,
    cliente_id BIGINT NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE movimientos (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    fecha DATE NOT NULL,
    tipo_movimiento VARCHAR(50) NOT NULL,
    valor DECIMAL(15, 2) NOT NULL,
    saldo DECIMAL(15, 2) NOT NULL,
    cuenta_id BIGINT NOT NULL,
    CONSTRAINT fk_cuenta FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)
);