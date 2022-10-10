package com.lippio.shortest_path.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.lippio.shortest_path.pojo.Country;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NodeRelationDeserializer extends JsonDeserializer<Map<String, Country>> {

    @Override
    public Map<String, Country> deserialize(JsonParser jsonParser,
                                            DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        if(node == null || node.isEmpty() || !node.isArray()) {
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
