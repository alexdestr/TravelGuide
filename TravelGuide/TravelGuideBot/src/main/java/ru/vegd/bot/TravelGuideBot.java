package ru.vegd.bot;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
@PropertySource("bot.properties")
public class TravelGuideBot extends TelegramLongPollingBot {

    private final static org.slf4j.Logger logger =
            LoggerFactory.getLogger(TravelGuideBot.class);

    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

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
                logger.warn("IOException:", e);
            }

            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            if (response != null && response.getDescription() != null) {
                message.setText(response.getDescription());
            } else {
                message.setText("Город не найден.");
            }
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                logger.warn("Telegram API exception: ", e);
            }
        }
    }
}
