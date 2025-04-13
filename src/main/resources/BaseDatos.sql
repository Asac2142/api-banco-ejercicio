CREATE TABLE Persona (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20)
);

CREATE TABLE Cliente (
    id SERIAL PRIMARY KEY,
    persona_id INT NOT NULL UNIQUE,
    fecha_registro DATE NOT NULL,
    FOREIGN KEY (persona_id) REFERENCES Persona(id)
);

CREATE TABLE Cuenta (
    id SERIAL PRIMARY KEY,
    numero_cuenta VARCHAR(20) NOT NULL UNIQUE,
    tipo_cuenta VARCHAR(50) NOT NULL,
    saldo DECIMAL(15, 2) NOT NULL,
    cliente_id INT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id)
);

CREATE TABLE Movimientos (
    id SERIAL PRIMARY KEY,
    fecha DATE NOT NULL,
    tipo_movimiento VARCHAR(50) NOT NULL,
    monto DECIMAL(15, 2) NOT NULL,
    cuenta_id INT NOT NULL,
    FOREIGN KEY (cuenta_id) REFERENCES Cuenta(id)
);
