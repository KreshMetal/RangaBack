CREATE TABLE users (
    login VARCHAR PRIMARY KEY NOT NULL,
    password VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    role VARCHAR NOT NULL,
	banned BOOLEAN DEFAULT FALSE NOT NULL
);

CREATE TABLE comics (
    comics_id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    author VARCHAR NOT NULL,
    url VARCHAR NOT NULL,
    rating DOUBLE PRECISION DEFAULT 0.0 NOT NULL
);

CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    login VARCHAR NOT NULL,
    comics_id BIGINT NOT NULL,
    creation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    text VARCHAR NOT NULL,
    
    FOREIGN KEY (login) REFERENCES users(login),
    FOREIGN KEY (comics_id) REFERENCES comics(comics_id)
);

CREATE TABLE favorite_comics (
    login VARCHAR NOT NULL,
    comics_id BIGINT NOT NULL,

    PRIMARY KEY (login, comics_id),

    FOREIGN KEY (login) REFERENCES users(login),
    FOREIGN KEY (comics_id) REFERENCES comics(comics_id)
);

CREATE TABLE ratings (
    login VARCHAR NOT NULL,
    comics_id BIGINT NOT NULL,
    score INT NOT NULL,

    PRIMARY KEY (login, comics_id),
    
    FOREIGN KEY (login) REFERENCES users(login),
    FOREIGN KEY (comics_id) REFERENCES comics(comics_id)
);


INSERT INTO users VALUES ('admin', '$2a$12$w3Snwe2XxtVNh0W7xqrOJu.b4qrv/ZV41BIfLsTn4ARX/hyxG7WMS', 'name', 'email', 'ADMIN'), ('moder', '$2a$12$w3Snwe2XxtVNh0W7xqrOJu.b4qrv/ZV41BIfLsTn4ARX/hyxG7WMS', 'name', 'email', 'MODER');
 
INSERT INTO comics(name, description, author, url) VALUES 
('Токийский гуль', 'Описание', 'Кто-то', 'https://mangalib.me/toukyou-kushu?section=info'),
('История о герое-исследователе', 'Такаги Кайто — старшеклассник с низким статусом, и так сказать, типичный «фоновый персонаж»', 'Kaito', 'https://mangalib.me/mobu-kara-hajimaru-tansaku-eiyutan?section=info'),
('Безголовый цветок', 'Происшествие на квадратной плитке неожиданно переросло в небольшое сюрреалистичное приключение, от которого зависит жизнь двух друзей.', 'Konkoria', 'https://mangalib.me/bezgolovyi-cvetok?section=info'),
('Баскетболистка', 'Дуань Хань, обычный 22-летний дизайнер интерьеров, оказался втянут в разборки между бандами. Его подставили и ложно обвинили в убийстве, за что он был приговорен к смертной казни.', 'Menghuage', 'https://mangalib.me/the-basketball-girl?section=info'),
('Башня Бога', 'Описание', 'Ктото', 'https://mangalib.me/toukyou-kushu?section=info'),
('Сказания о Демонах и Богах', 'Сильнейший в мире заклинатель демонов Не Ли погиб в битве с Мудрецом-Императором. Но его душа воплотилась вновь, вернувшись на десятилетия назад, в нём самом, тринадцатилетнем подростке.', 'Mad Snail', 'https://mangalib.me/yaoshenji?section=info'),
('Белая кровь', 'Корею поглотил хаос.', 'LIM Lina', 'https://mangalib.me/toukyou-kushu?section=info'),
('Я убил игрока академии', 'Я убил игрока.', 'Salam Sallyeo', 'https://mangalib.me/yaoshenji?section=info'),
('Прощай, Эри', 'Мать Юты смертельно больна. Она попросила запечатлеть её последние дни на только что подаренный сыну телефон.', 'Tatsuki Fujimoto', 'https://mangalib.me/akademi-peulleieoleul-jug-yeossda?section=info'),
('Оглянись', 'Фудзино — четвероклассница, которая рисует ёнкомы для школьной газеты.', 'Tatsuki Fujimoto', 'https://mangalib.me/yaoshenji?section=info'),
('Звёздное Дитя', 'Что, если бы дорога в шоу-бизнес была открыта для тебя с самого начала?', 'Aka Akasaka', 'https://mangalib.me/white-blood_?section=info'); 

