package com.example.urlminification.repository;

import com.example.urlminification.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer> {
    Url findByLongUrl(String longUrl);
}
