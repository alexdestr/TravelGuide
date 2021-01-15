package ru.vegd;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TravelGuideBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "Trav_Guide_bot";
    }

    @Override
    public String getBotToken() {
        return "1595934785:AAG4KHp2_hm0duu4DAC9PRcGu21DHm6VRhc";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            message.setText("just test");
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                // TODO
            }
        }
    }
}
