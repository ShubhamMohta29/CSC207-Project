package classes.saveCard;

import java.time.LocalDateTime;

public class saveCardResponseModel {
    private final String cardName;
    private final LocalDateTime timeSaved;

    public saveCardResponseModel(String cardName, LocalDateTime timeSaved) {
        this.cardName = cardName;
        this.timeSaved = timeSaved;
    }

    public String getCardName() { return cardName; }
    public LocalDateTime getTimeSaved() { return timeSaved; }
}
