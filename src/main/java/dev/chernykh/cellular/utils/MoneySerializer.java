package dev.chernykh.cellular.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.javamoney.moneta.Money;

import java.io.IOException;

public class MoneySerializer extends JsonSerializer<Money> {
    @Override
    public void serialize(Money money, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws
        IOException {
        jsonGenerator.writeString(money.getCurrency().toString() + " " + money.getNumber().doubleValue());
    }
}
