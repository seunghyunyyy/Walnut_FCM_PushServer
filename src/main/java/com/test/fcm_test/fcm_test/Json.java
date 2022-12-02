package com.test.fcm_test.fcm_test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class Json {
    public JsonArray stringToMessageJsonArray(String jsonString) {

        JsonArray MessageList = new JsonArray();

        log.info(jsonString);

        JsonArray jsonArray = (JsonArray) JsonParser.parseString(jsonString);

        for(int i = 0; i < jsonArray.size(); i++) {
            JsonObject message = new JsonObject();
            JsonObject notificationJson = new JsonObject();
            JsonObject dataJson = new JsonObject();
            JsonObject jsonObject = (JsonObject) jsonArray.get(i);

            String token = jsonObject.get("token").getAsString();
            String title = jsonObject.get("title").getAsString();
            String body = jsonObject.get("body").getAsString();
            String opcode = jsonObject.get("opcode").getAsString();
            Long tokenId = jsonObject.get("tokenId").getAsLong();
            Long msgId = jsonObject.get("msgId").getAsLong();

            dataJson.addProperty("opcode", opcode);
            dataJson.addProperty("tokenId", tokenId);
            dataJson.addProperty("msgId", msgId);

            notificationJson.addProperty("title", title);
            notificationJson.addProperty("body", body);

            message.addProperty("token", token);
            message.add("notification", notificationJson);
            message.add("data", dataJson);

            MessageList.add(message);
            log.info(MessageList.toString());
        }
        log.info(MessageList.toString());
        return MessageList;
    }
    public JsonObject stringToMessageJsonObject(String jsonString) {

        JsonObject message = new JsonObject();
        JsonObject notificationJson = new JsonObject();
        JsonObject dataJson = new JsonObject();
        log.info(jsonString);


        JsonObject jsonObject = (JsonObject) JsonParser.parseString(jsonString);


        String token = jsonObject.get("token").getAsString();
        String title = jsonObject.get("title").getAsString();
        String body = jsonObject.get("body").getAsString();
        String opcode = jsonObject.get("opcode").getAsString();
        Long tokenId = jsonObject.get("tokenId").getAsLong();
        Long msgId = jsonObject.get("msgId").getAsLong();

        dataJson.addProperty("opcode", opcode);
        dataJson.addProperty("tokenId", tokenId);
        dataJson.addProperty("msgId", msgId);

        notificationJson.addProperty("title", title);
        notificationJson.addProperty("body", body);

        message.addProperty("token", token);
        message.add("notification", notificationJson);
        message.add("data", dataJson);

        log.info(message.toString());

        return message;
    }
}

