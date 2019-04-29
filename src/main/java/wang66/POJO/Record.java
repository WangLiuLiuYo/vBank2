package wang66.POJO;

import java.math.BigDecimal;

public class Record {
    private String selfCardId;
    private String cardId;
    private BigDecimal money;
    private String date=null;
    private String comment;


    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setSelfCardId(String selfCardId) {
        this.selfCardId = selfCardId;
    }

    public String getSelfCardId() {
        return selfCardId;
    }
}
