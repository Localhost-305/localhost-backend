package com.api.domain.util;





import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapObjectList {
    public static ArrayList<Object> mapObjectList(List<Object[]> objectList, String[] columns) {
        Map<Integer, Map<String, String>> resultMap = new HashMap<>();
        ArrayList listOfObjects = new ArrayList<>();

        for (int i = 0; i < objectList.size(); i++) {
            Object[] object = objectList.get(i);
            Map<String, String> columnMap = new HashMap<>();
            for (int j = 0; j < columns.length; j++) {
                if (object[j] != null) {
                    columnMap.put(columns[j], object[j].toString());
                } else {
                    columnMap.put(columns[j], null);
                }
            }
            listOfObjects.add(columnMap);
            resultMap.put(i, columnMap);

        }

        return listOfObjects;
    }
}