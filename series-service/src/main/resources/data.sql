INSERT INTO public.media (id, description, owner_id,  rating, title) VALUES (10, 'Crime series about a teacher who makes drug'::varchar(255), '1', 3.3::double precision, 'Breaking Bad'::varchar(255));
INSERT INTO public.media (id, description, owner_id,  rating, title) VALUES (20, 'Crime series about a teacher who makes drug'::varchar(255), '1', 4.2::double precision, 'Breaking Good'::varchar(255));
-- update public.ID_Generator set sequence=? where sequence=? and name=?
INSERT INTO public.series (id,status) VALUES (10::integer, 'ONGOING'::varchar(255));
INSERT INTO public.series (id,status) VALUES (20::integer, 'FINISHED'::varchar(255));
INSERT INTO public.season (id, name, owner_id, season_number, year, series_id) VALUES (DEFAULT, 'The begin'::varchar(255),'1', 1::integer, 2020::integer, 10::integer);
INSERT INTO public.season (id, name, owner_id, season_number, year, series_id) VALUES (DEFAULT, 'The next'::varchar(255), '1', 1::integer, 2021::integer, 10::integer);
INSERT INTO public.episode (id, episode_number, duration, name, owner_id, season_id) VALUES (DEFAULT, 45::integer, 1::integer, 'My name is Walter'::varchar(255), '1', 1::integer);
INSERT INTO public.episode (id, episode_number, duration, name, owner_id, season_id) VALUES (DEFAULT, 45::integer, 2::integer,'Next ep'::varchar(255), '1', 1::integer);
INSERT INTO public.media_director (person_id, media_id) VALUES (1::integer, 10::integer);
INSERT INTO public.media_director (person_id, media_id) VALUES (2::integer, 10::integer);
INSERT INTO public.media_actor (person_id, media_id) VALUES (2::integer, 10::integer);
INSERT INTO public.media_actor (person_id, media_id) VALUES (1::integer, 20::integer);
INSERT INTO public.media_genre (genre_id, media_id) VALUES (1::integer, 10::integer);
INSERT INTO public.media_genre (genre_id, media_id) VALUES (2::integer, 10::integer);