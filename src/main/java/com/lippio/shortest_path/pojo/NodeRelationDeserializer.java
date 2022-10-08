package com.lippio.shortest_path.pojo;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NodeRelationDeserializer extends JsonDeserializer<Map<String, Country>> {

    @Override
    public Map<String, Country> deserialize(JsonParser jsonParser,
                                            DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        // TODO Merge it
        if(node == null || node.isEmpty()) {
            return new HashMap<>();
        }
        if(!node.isArray()) {
            return new HashMap<>();
        }

        Map<String, Country> newMap = new HashMap<>();
        for (int i = 0; i < node.size(); i++) {
            // Null will be replaced by related country instance
            // TODO: find solution to do in single action
            newMap.put(node.get(i).asText(), null);
        }



        return newMap;
    }
}
