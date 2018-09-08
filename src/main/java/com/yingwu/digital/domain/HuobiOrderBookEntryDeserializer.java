package com.yingwu.digital.domain;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.math.BigDecimal;

public class HuobiOrderBookEntryDeserializer extends JsonDeserializer<HuobiOrderBookEntry> {

    @Override
    public HuobiOrderBookEntry deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);
        final BigDecimal price = node.get(0).decimalValue();
        final BigDecimal qty = node.get(1).decimalValue();
        return new HuobiOrderBookEntry(price,qty);
    }
}
