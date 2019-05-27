package br.com.hbsis;

public class NoSuchStrategyError extends RuntimeException {    

    public NoSuchStrategyError(String message) {
        super(message);
    }
}

