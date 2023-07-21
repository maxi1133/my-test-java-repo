package com.zaka.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class CommonUtils {

    final static ObjectMapper objectMapper = new ObjectMapper();

    public static HashMap<String, String> convertObjectToHashMap(Object obj) {
        return objectMapper.convertValue(obj, HashMap.class);
    }
}
