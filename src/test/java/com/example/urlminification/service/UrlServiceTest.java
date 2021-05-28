package com.example.urlminification.service;

import com.example.urlminification.dto.UrlDto;
import com.example.urlminification.entity.Url;
import com.example.urlminification.repository.UrlRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {
    @Mock
    UrlRepository urlRepository;

    @Mock
    Converter converter;

    @InjectMocks
    UrlService urlService;

    @Test
    public void convertToShortUrlTest() {
        Url url = new Url();
        url.setLongUrl("https://www.google.ru/maps/place/%D0%9D%D0%B5%D0%BA%D1%80%D0%B0%D1%81%D0%BE%D0%B2%D0%B0+65/@55.0508186,82.94064,15z/data=!4m5!3m4!1s0x42dfe5484959c6b5:0xa3389af878f2726f!8m2!3d55.0469668!4d82.9379926");
        url.setId(0);
        url.setCreatedDate(new Date());

        when(urlRepository.save(any(Url.class))).thenReturn(url);
        when(converter.encode(url.getId())).thenReturn("a");

        UrlDto urlDto = new UrlDto();
        urlDto.setLongUrl("https://www.google.ru/maps/place/%D0%9D%D0%B5%D0%BA%D1%80%D0%B0%D1%81%D0%BE%D0%B2%D0%B0+65/@55.0508186,82.94064,15z/data=!4m5!3m4!1s0x42dfe5484959c6b5:0xa3389af878f2726f!8m2!3d55.0469668!4d82.9379926");

        assertEquals("a", urlService.convertToShortUrl(urlDto));
    }

    @Test
    public void getOriginalUrlTest() {
        when(converter.decode("b")).thenReturn(1);

        Url url = new Url();
        url.setLongUrl("https://www.google.ru/maps/");
        url.setCreatedDate(new Date());
        url.setId(1);

        when(urlRepository.save(any(Url.class))).thenReturn(url);
        when(urlRepository.findById(1)).thenReturn(java.util.Optional.of(url));

        assertEquals("https://www.google.ru/maps/", urlService.getOriginalUrl("b"));
    }

}
