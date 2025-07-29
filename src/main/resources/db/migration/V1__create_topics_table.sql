CREATE TABLE IF NOT EXISTS topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL UNIQUE,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    status ENUM('NO_RESPONDIDO','NO_SOLUCIONADO','SOLUCIONADO','CERRADO') DEFAULT 'NO_RESPONDIDO' NOT NULL,
    autor VARCHAR(100) NOT NULL,
    curso ENUM('SPRING_BOOT','JAVA','SQL','PYTHON') NOT NULL
);

CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('USER','ADMIN') DEFAULT 'USER' NOT NULL
);

-- Insertar usuarios de prueba
-- Contraseñas: password -> $2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5O
INSERT INTO usuarios (username, password, role) VALUES
('moderador', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5O', 'USER'),
('admin', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5O', 'ADMIN');

-- Insertar datos iniciales para pruebas
INSERT INTO topicos (titulo, mensaje, fecha_creacion, status, autor, curso) VALUES
('Problema con Spring Boot', 'No puedo iniciar mi aplicación Spring Boot', NOW(), 'NO_RESPONDIDO', 'usuario1', 'SPRING_BOOT'),
('Duda sobre Java Streams', '¿Cómo usar map y filter juntos?', NOW(), 'SOLUCIONADO', 'usuario2', 'JAVA'),
('Error en consulta SQL', 'Mi consulta JOIN no retorna los resultados esperados', NOW(), 'NO_SOLUCIONADO', 'usuario3', 'SQL');