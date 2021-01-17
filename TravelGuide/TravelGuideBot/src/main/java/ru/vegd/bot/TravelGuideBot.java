package ru.vegd.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.vegd.receiver.LocationDataReceiver;
import ru.vegd.response.Response;
import ru.vegd.response.ResponseBuilder;

import java.io.IOException;

@Component
public class TravelGuideBot extends TelegramLongPollingBot {

    //@Value("${bot.name}")
    private String botUsername = "Trav_Guide_bot";

    //@Value("{bot.token}")
    private String botToken = "1595934785:AAG4KHp2_hm0duu4DAC9PRcGu21DHm6VRhc";

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            ResponseBuilder responseBuilder = new ResponseBuilder();
            Response response = null;
            try {
                response = responseBuilder.build(new LocationDataReceiver().getLocationInformation(update.getMessage().getText()));
            } catch (IOException e) {
                // TODO
            }

            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            if (response.getDescription().isEmpty() || response.getDescription() == null) {
                message.setText("Город не найден.");
            } else {
                message.setText(response.getDescription());
            }
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                // TODO
            }
        }
    }
}
