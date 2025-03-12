-- users
INSERT INTO users (first_name, last_name, username, age, email, password)
VALUES ('John', 'Doe', 'johndoe', 25, 'john.doe@example.com', 'password123'),
       ('Jane', 'Smith', 'janesmith', 30, 'jane.smith@example.com', 'password456');

-- user_statistic
INSERT INTO user_statistic (user_id, learning_time, total_cards_amount)
VALUES (1, 1000000000, 5),
       (2, 1000000000, 3);


INSERT INTO word_type(id, type)
VALUES (1, 'VERB'),
       (2, 'NOUN'),
       (3, 'ADVERB'),
       (4, 'ADJECTIVE');

-- word_card
INSERT INTO word_card (word, id, definition, word_type, user_id)
VALUES ('run', 1, 'To move quickly on foot', 1, 1),
       ('happy', 2, 'Feeling or showing pleasure', 4, 1),
       ('book', 3, 'A written or printed work consisting of pages', 2, 2);

-- stems
INSERT INTO stems (word_card_id, word)
VALUES (1, 'run'),
       (1, 'running'),
       (2, 'happy'),
       (2, 'happier'),
       (3, 'book'),
       (3, 'books');

-- examples
INSERT INTO examples (word_card_id, example)
VALUES (1, 'I run every morning.'),
       (1, 'She runs faster than me.'),
       (2, 'I am happy today.'),
       (2, 'They are happy with the results.'),
       (3, 'This book is interesting.'),
       (3, 'I bought a new book.');

-- short_definition
INSERT INTO short_definitions (word_card_id, definition)
VALUES (1, 'Move quickly'),
       (2, 'Feeling joy'),
       (3, 'Written work');

-- achievements
INSERT INTO achievements (title, progress)
VALUES ('First Card Learned', 100),
       ('10 Cards Mastered', 50),
       ('Grammar Guru', 0);

-- users_achievements
INSERT INTO users_achievements (user_id, achievement_id)
VALUES (1, 1),
       (2, 1),
       (2, 2);