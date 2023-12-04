package com.example.practice.project.utilities;

import com.example.practice.project.customexception.BusinessException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ModelConverter {

    private ModelConverter() {
    }

    /**
     * convertToMap.
     *
     * @param obj obj
     * @return Map
     */
    public static Map<String, Object> convertToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (Exception e) {
                throw new BusinessException(e.getLocalizedMessage());
            }
        }
        return map;
    }
}
