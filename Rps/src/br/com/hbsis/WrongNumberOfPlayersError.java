package br.com.hbsis;

public class WrongNumberOfPlayersError extends RuntimeException {    

    public WrongNumberOfPlayersError(String message) {
        super(message);
    }
}
