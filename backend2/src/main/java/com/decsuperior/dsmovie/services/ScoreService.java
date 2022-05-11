package com.decsuperior.dsmovie.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.decsuperior.dsmovie.dto.MovieDTO;
import com.decsuperior.dsmovie.dto.ScoreDTO;
import com.decsuperior.dsmovie.entities.Movie;
import com.decsuperior.dsmovie.entities.Score;
import com.decsuperior.dsmovie.entities.User;
import com.decsuperior.dsmovie.repositories.MovieRepository;
import com.decsuperior.dsmovie.repositories.ScoreRepository;
import com.decsuperior.dsmovie.repositories.UserRepository;

@Service
public class ScoreService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ScoreRepository scoreRepository;
	
	@Transactional
	public MovieDTO saveScore(ScoreDTO dto) {
		
		User user = userRepository.findByEmail(dto.getEmail());
		if(user == null) {
			user = new User();
			user.setEmail(dto.getEmail());
			user = userRepository.saveAndFlush(user);
		}
		
		Movie movie = movieRepository.findById(dto.getMovieId()).get();
		Score score = new Score();
		score.setMovie(movie);
		score.setUser(user);
		score.setValue(dto.getScore());
		score = scoreRepository.saveAndFlush(score);
		double sum=0.0;
		int size = 0;
		for(Score s : movie.getScores()){
			sum += s.getValue();
			++size;
		}
		double avg=sum/size;
		movie.setScore(avg);
		movie.setCount(size);
		movie=movieRepository.save(movie);
		return new MovieDTO(movie);
	}
}
