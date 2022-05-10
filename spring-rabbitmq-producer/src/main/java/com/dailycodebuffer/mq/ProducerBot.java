package com.dailycodebuffer.mq;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class ProducerBot extends TelegramLongPollingBot {
    @Value("${telegram.bot.username}")
    private String username;
    @Value("${telegram.bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                String text = message.getText();
                if(text.equals("Починаю лекцію")){
                    SendMessage sm = new SendMessage();
                    sm.setText("Ви відправили: " + text);
                    sm.setChatId(String.valueOf(message.getChatId()));
                    try {
                        execute(sm);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    JSONObject obj = new JSONObject();

                    obj.put("message", message.getText());
                    obj.put("authorID", message.getChatId());
                    obj.put("authorName", message.getFrom().getFirstName());

                    System.out.println(message.getFrom());

                    HttpClient httpClient = HttpClientBuilder.create().build();
                    HttpPost request = new HttpPost("http://localhost:9000/publish");
                    request.setEntity(new StringEntity(obj.toString(), ContentType.APPLICATION_JSON));
                    try {
                        HttpResponse response = httpClient.execute(request);
                        System.out.println(response.getStatusLine().getStatusCode());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
        }
    }
}