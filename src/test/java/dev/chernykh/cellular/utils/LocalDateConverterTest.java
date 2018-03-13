package dev.chernykh.cellular.utils;

import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class LocalDateConverterTest {
    private LocalDateConverter converter = new LocalDateConverter();
    private String checkingDate = "2018-03-12";
    
    @Test
    public void shouldConvertToDatabaseColumn() {
        Date expected = converter.convertToDatabaseColumn(LocalDate.parse(checkingDate));
        Date actual = Date.valueOf(checkingDate);
        
        assertThat(expected, notNullValue());
        assertThat(actual, notNullValue());
        assertEquals(expected, actual);
    }
    
    @Test
    public void shouldConvertToEntityAttribute() {
        LocalDate expected = converter.convertToEntityAttribute(Date.valueOf(checkingDate));
        LocalDate actual = LocalDate.parse(checkingDate);
        
        assertThat(expected, notNullValue());
        assertThat(actual, notNullValue());
        assertEquals(expected, actual);
    }
}