package com.example.urlminification.service;

import com.example.urlminification.dto.UrlDto;
import com.example.urlminification.entity.Url;
import com.example.urlminification.repository.UrlRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private final Converter converter;

    public UrlService(UrlRepository urlRepository, Converter converter) {
        this.urlRepository = urlRepository;
        this.converter = converter;
    }

    public String convertToShortUrl(UrlDto urlFromRequest) {
        Url url = urlRepository.findByLongUrl(urlFromRequest.getLongUrl());
        if (url == null) {
            Url urlToDB = new Url();
            urlToDB.setLongUrl(urlFromRequest.getLongUrl());
            urlToDB.setCreatedDate(new Date());
            urlToDB = urlRepository.save(urlToDB);
            String shortUrl = converter.encode(urlToDB.getId());
            urlToDB.setShortUrl(shortUrl);
            urlRepository.save(urlToDB);
            return shortUrl;
        }
        else {
            return url.getShortUrl();
        }
    }

    public String getOriginalUrl(String shortUrl) {
        int id = converter.decode(shortUrl);
        Url originalUrl = urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found original url with " + shortUrl));

        return originalUrl.getLongUrl();
    }
}

