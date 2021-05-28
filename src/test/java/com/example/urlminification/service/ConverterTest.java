package com.example.urlminification.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ConverterTest {
    private Converter converter = new Converter();

    @Test
    public void encodeLess62() {
        assertEquals("a", converter.encode(0));
    }

    @Test
    public void encodeMore62() {
        assertEquals("bp", converter.encode(77));
    }

    @Test
    public void decodeOneCharacter() {
        assertEquals(3, converter.decode("d"));
    }

    @Test
    public void decodeTwoCharacters() {
        assertEquals(100, converter.decode("bM"));
    }
}
