package com.decsuperior.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.decsuperior.dsmovie.entities.Score;
import com.decsuperior.dsmovie.entities.ScorePK;

public interface ScoreRepository extends JpaRepository<Score, ScorePK> {
	
}
