INSERT INTO music_services(id, name, country)
VALUES (1, 'RadioG', 'Georgia'),
       (2, 'TambiWave', 'Persia');

INSERT INTO genres (id, name, description, music_service_id)
VALUES (1, 'Soul', 'Soul music', 1),
       (2, 'Rock', 'Guitar-driven music', 1);

INSERT INTO albums (id, title, release_date, artist_id)
VALUES (1, 'Pearls', '2011-01-24', 1),
       (2, 'The Wall', '1979-11-30', 2);

INSERT INTO artists (id, name, country, music_service_id)
VALUES (1, 'Sade', 'UK', 1),
       (2, 'Pink Floyd', 'UK', 1);

INSERT INTO songs (id, title, duration_seconds, release_date, album_id, genre_id)
VALUES (1, 'Smooth Operator', 228, '2010-11-29', 1, 1),
       (2, 'Another Brick in the Wall', 285, '1979-11-30', 2, 2);

INSERT INTO users (id, username, email, premium, registration_date, last_login, music_service_id)
VALUES (1, 'john_doe', 'john@email.com', 1, '2022-01-15', '2024-03-01 10:30:00', 1),
       (2, 'jane_smith', 'jane@email.com', 0, '2023-06-20', '2024-03-05 14:00:00', 1),
       (3, 'mike_jones', 'mike@email.com', 1, '2021-11-10', '2024-02-28 09:15:00', 2);

INSERT INTO subscriptions (id, plan, price_per_month, start_date, end_date, user_id)
VALUES (1, 'PREMIUM', 9.99, '2023-01-01', '2024-01-01', 1),
       (2, 'FAMILY', 14.99, '2023-03-15', '2024-03-15', 3),
       (3, 'FREE', 0.00, '2023-06-20', NULL, 2);

INSERT INTO payment_methods (id, type, last_four, default_method, user_id)
VALUES (1, 'CREDIT_CARD', '4242', 1, 1),
       (2, 'PAYPAL', NULL, 1, 2),
       (3, 'CREDIT_CARD', '1234', 1, 3);

INSERT INTO playlists (id, name, open, user_id)
VALUES (1, 'Morning Vibes', 1, 1),
       (2, 'Workout Mix', 0, 1),
       (3, 'Chill Evening', 1, 2),
       (4, 'Road Trip', 1, 3);

INSERT INTO reviews (id, rating, comment, created_at, song_id)
VALUES (1, 5, 'Absolute classic!', '2024-01-10 10:00:00', 1),
       (2, 4, 'Love this song', '2024-01-15 14:30:00', 2);

INSERT INTO stream_histories (id, played_at, listened_seconds, song_id)
VALUES (1, '2024-03-01 08:00:00', 228, 1),
       (2, '2024-03-01 08:04:00', 285, 2);