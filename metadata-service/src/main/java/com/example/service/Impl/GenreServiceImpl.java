package com.example.service.Impl;

import com.example.dto.GenreDto;
import com.example.entity.Person;
import com.example.mapper.GenreMapper;
import com.example.service.GenreService;
import com.example.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.repo.GenreRepo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepo genreRepo;

    private final GenreMapper genreMapper;
    @Override
    public List <GenreDto> getAll () {
        return genreMapper.toDtos ((List<Genre>) genreRepo.findAll());
    }

    public GenreDto getById (int id) {
        return genreMapper.toDto (genreRepo.findById (id).get ());
    }

    @Override
    @Transactional
    public GenreDto addGenre (GenreDto genreDto) {
        Genre genre = genreMapper.toEntity (genreDto);
        if(genreRepo.findById (genre.getId ()).isPresent ()){
            throw new RuntimeException ("genre id :"+genre.getId () + "is existing");
        }
        else {
            genreRepo.save (genre);
        }

        return genreMapper.toDto (genre);
    }

    @Override
    @Transactional
    public GenreDto deleteById (Integer id) {
        Genre genre = genreRepo.findById (id).get ();
            genreRepo.deleteById (id);

       return genreMapper.toDto (genre);
    }
    @Override
    @Transactional
    public GenreDto updateById (Integer id,GenreDto genreDto) {
        Genre genre = genreMapper.toEntity (genreDto);
        Genre exist = genreRepo.findById (id) .orElseThrow (()->new IllegalStateException (
                "Genre id:"+id+"does not exist"));;

        if(exist.getName () !=null &&exist.getName ().length ()>0 && !Objects.equals (exist.getName (),genre.getName ())){
            exist.setName (genre.getName ());
        }
        return genreMapper.toDto (exist);
    }

    @Override
    public List <GenreDto> findByName (String name) {
        return genreMapper.toDtos (genreRepo.findAllByName (name));
    }
}
