package wang66.POJO;

import java.math.BigDecimal;

public class CardBalance {
    private String cardId;
    private BigDecimal balance;

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCardId() {
        return cardId;
    }
}
