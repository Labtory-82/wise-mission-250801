package org.example;

import java.util.HashMap;
import java.util.Map;

public class Rq {

    //명령어 파라미터를 저장할 맵
    private Map <String, String> paramMap;

    public Rq(String command) {
        paramMap = new HashMap<>();

        String[] commandBits = command.split("\\?");

        String actionName = commandBits[0];
        String queryString  = "";

        if (commandBits.length > 1) {
            queryString = commandBits[1];
        }

        String[] queryStringBits = queryString.split("&");

        for (String param : queryStringBits) {
            String[] paramBits = param.split("=");
            String key = paramBits[0];
            String value = null;

            if (paramBits.length < 2) {
                continue;
            }

            value = paramBits[1];

            paramMap.put(key, value);
        }
    }

    private String getParam(String key) {
        return paramMap.get(key);
    }

}
