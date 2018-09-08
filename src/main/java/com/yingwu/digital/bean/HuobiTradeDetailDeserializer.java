package com.yingwu.digital.bean;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class HuobiTradeDetailDeserializer extends JsonDeserializer<HuobiTradeDetail> {

    @Override
    public HuobiTradeDetail deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);
        HuobiTradeDetail detail = new HuobiTradeDetail();
        detail.setAmount( node.get("amount").decimalValue());
        detail.setTs(node.get("ts").toString());
        detail.setPrice(node.get("price").decimalValue());
        detail.setId(node.get("id").asText());
        detail.setDirection(node.get("direction").asText());
        return detail;
    }
}
