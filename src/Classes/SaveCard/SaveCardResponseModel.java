package Classes.SaveCard;

import java.time.LocalDateTime;

public class SaveCardResponseModel {
    private final String cardName;
    private final LocalDateTime timeSaved;

    public SaveCardResponseModel(String cardName, LocalDateTime timeSaved) {
        this.cardName = cardName;
        this.timeSaved = timeSaved;
    }

    public String getCardName() { return cardName; }
    public LocalDateTime getTimeSaved() { return timeSaved; }
}
