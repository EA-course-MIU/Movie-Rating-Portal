INSERT INTO public.movie (id, average_rating, description, owner_id, title, duration, year)
VALUES (1, 4.8,
        'The people of Wakanda fight to protect their home from intervening world powers as they mourn the death of King T"Challa.',
        1, 'Wakanda Forever', 161, 2022);

INSERT INTO public.movie (id, average_rating, description, owner_id, title, duration, year)
VALUES (2, 4.3,
        'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.',
        2, 'The Shawshank Redemption',  144, 1994);

UPDATE public.id_generator SET sequence = 2 WHERE name = 'media';

INSERT INTO public.media_genre (genre_id, media_id)
VALUES (1, 1),
       (3, 1),
       (2, 2),
       (4, 2);

INSERT INTO public.media_director (director_id, media_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO public.media_actor (actor_id, media_id)
VALUES (1, 1),
       (3, 1),
       (5, 1),
       (2, 2),
       (4, 2),
       (6, 2),
       (7, 2);

