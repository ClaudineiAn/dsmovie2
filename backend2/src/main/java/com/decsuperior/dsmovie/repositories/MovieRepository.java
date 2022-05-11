package com.decsuperior.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.decsuperior.dsmovie.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
