package wang66.Exceptions;

public class CardFrozenException extends Exception {
    public CardFrozenException(){}
    public CardFrozenException(String message){
        super(message);
    }
}
