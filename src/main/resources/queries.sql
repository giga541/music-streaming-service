-- UPDATE statements

UPDATE music_services
SET name='lost'
WHERE id = 2;

UPDATE genres
SET description='Jazz'
WHERE id = 2;

UPDATE artists
SET country='South Africa'
WHERE id = 1;

UPDATE albums
SET description='Jazz'
WHERE id = 2;

UPDATE songs
SET title='unknown but cool'
WHERE id = 2;

UPDATE users
SET last_login='2023-08-20'
WHERE id = 1;

UPDATE subscriptions
SET end_date=NULL
WHERE id = 2;

UPDATE payment_methods
SET type='Crypto'
WHERE id = 1;

UPDATE playlists
SET name='lost'
WHERE id = 3;

UPDATE reviews
SET user_id=9
WHERE id = 4;

--DELETE statements

DELETE
FROM music_services
WHERE id = 1;

DELETE
FROM playlists
WHERE id = 1;

DELETE
FROM playlists
WHERE id = 2;

DELETE
FROM playlists
WHERE id = 3;

DELETE
FROM subscriptions
WHERE id = 1;

DELETE
FROM subscriptions
WHERE id = 2;

DELETE
FROM users
WHERE id = 2;

DELETE
FROM payment_methods
WHERE id = 1;

DELETE
FROM stream_histories
WHERE id = 2;

DELETE
FROM reviews
WHERE id = 2;

--Join all tables

SELECT music_services.name,
       users.username,
       users.email,
       subscriptions.plan,
       payment_methods.type,
       playlists.name,
       artists.name,
       albums.title,
       songs.title,
       songs.duration_seconds,
       genres.name,
       stream_histories.played_at,
       stream_histories.listened_seconds,
       reviews.rating,
       reviews.comment
FROM music_services
         JOIN users ON users.music_service_id = music_services.id
         JOIN subscriptions ON subscriptions.user_id = users.id
         JOIN payment_methods ON payment_methods.user_id = users.id
         JOIN playlists ON playlists.user_id = users.id
         JOIN artists ON artists.music_service_id = music_services.id
         JOIN albums ON albums.artist_id = artists.id
         JOIN songs ON songs.album_id = albums.id
         JOIN genres ON genres.id = songs.genre_id
         JOIN stream_histories ON stream_histories.song_id = songs.id
         JOIN reviews ON reviews.song_id = songs.id;

--Join statements

SELECT songs.title, reviews.rating, reviews.comment
FROM songs
         INNER JOIN reviews ON reviews.song_id = songs.id;

SELECT songs.title, reviews.rating, reviews.comment
FROM songs
         LEFT JOIN reviews ON reviews.song_id = songs.id;

SELECT songs.title, reviews.rating, reviews.comment
FROM songs
         RIGHT JOIN reviews ON reviews.song_id = songs.id;

SELECT users.username, playlists.name
FROM users
         LEFT JOIN playlists ON playlists.user_id = users.id;

-- FULL JOIN simulated with UNION
SELECT artists.name, albums.title
FROM artists
         LEFT JOIN albums ON albums.artist_id = artists.id
UNION
SELECT artists.name, albums.title
FROM artists
         RIGHT JOIN albums ON albums.artist_id = artists.id;

--
-- Aggregate functions with group by without having

-- 1. Total number of songs per genre
SELECT genres.name, COUNT(songs.id) AS total_songs
FROM genres
         JOIN songs ON songs.genre_id = genres.id
GROUP BY genres.name;

-- 2. Average song duration per album
SELECT albums.title, AVG(songs.duration_seconds) AS avg_duration
FROM albums
         JOIN songs ON songs.album_id = albums.id
GROUP BY albums.title;

-- 3. Total listened seconds per song
SELECT songs.title, SUM(stream_histories.listened_seconds) AS total_listened
FROM songs
         JOIN stream_histories ON stream_histories.song_id = songs.id
GROUP BY songs.title;

-- 4. Number of playlists per user
SELECT users.username, COUNT(playlists.id) AS playlist_count
FROM users
         JOIN playlists ON playlists.user_id = users.id
GROUP BY users.username;

-- 5. Maximum rating per song
SELECT songs.title, MAX(reviews.rating) AS max_rating
FROM songs
         JOIN reviews ON reviews.song_id = songs.id
GROUP BY songs.title;

-- 6. Minimum rating per song
SELECT songs.title, MIN(reviews.rating) AS min_rating
FROM songs
         JOIN reviews ON reviews.song_id = songs.id
GROUP BY songs.title;

-- 7. Total revenue per subscription plan
SELECT plan, SUM(price_per_month) AS total_revenue
FROM subscriptions
GROUP BY plan;

--
-- Aggregate functions with group by with habing

-- 1. Genres with more than 1 song
SELECT genres.name, COUNT(songs.id) AS total_songs
FROM genres
         JOIN songs ON songs.genre_id = genres.id
GROUP BY genres.name
HAVING COUNT(songs.id) > 1;

-- 2. Albums with average song duration over 200 seconds
SELECT albums.title, AVG(songs.duration_seconds) AS avg_duration
FROM albums
         JOIN songs ON songs.album_id = albums.id
GROUP BY albums.title
HAVING AVG(songs.duration_seconds) > 200;

-- 3. Songs with total listened time over 200 seconds
SELECT songs.title, SUM(stream_histories.listened_seconds) AS total_listened
FROM songs
         JOIN stream_histories ON stream_histories.song_id = songs.id
GROUP BY songs.title
HAVING SUM(stream_histories.listened_seconds) > 200;

-- 4. Users with more than 1 playlist
SELECT users.username, COUNT(playlists.id) AS playlist_count
FROM users
         JOIN playlists ON playlists.user_id = users.id
GROUP BY users.username
HAVING COUNT(playlists.id) > 1;

-- 5. Songs with average rating above 3
SELECT songs.title, AVG(reviews.rating) AS avg_rating
FROM songs
         JOIN reviews ON reviews.song_id = songs.id
GROUP BY songs.title
HAVING AVG(reviews.rating) > 3;

-- 6. Artists with more than 1 album
SELECT artists.name, COUNT(albums.id) AS album_count
FROM artists
         JOIN albums ON albums.artist_id = artists.id
GROUP BY artists.name
HAVING COUNT(albums.id) > 1;

-- 7. Subscription plans with total revenue more than 5
SELECT plan, SUM(price_per_month) AS total_revenue
FROM subscriptions
GROUP BY plan
HAVING SUM(price_per_month) > 5;