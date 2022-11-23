insert into public.person (id,name)
values (1,'Alex'),
        (2,'Alex2'),
        (3,'Alex3'),
        (4,'Alex4'),
        (5,'Alex5');
insert into public.position (id,title,person_id)
values (1,'director',1),
        (2,'Actor',1),
        (3,'director',2),
        (4,'Actor',3),
        (5,'director',4);

insert into public.genre  (id,name)
values (1,'Action'),
        (2,'Drama'),
        (3,'Comedy'),
        (4,'Crime'),
        (5,'Animation'),
        (6,'SuperHero');

