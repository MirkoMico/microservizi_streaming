package com.microservizi.moviecatalogservice.repository;

import com.microservizi.moviecatalogservice.model.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieInfoRepository extends JpaRepository<MovieInfo, Long> {
}
