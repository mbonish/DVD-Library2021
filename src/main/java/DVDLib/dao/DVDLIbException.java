package DVDLib.dao;

public class DVDLibException extends Exception {
    public DVDLibException(String message){
        super(message);
    }
    public DVDLibException(String message, Throwable cause){
        super(message, cause);
    }
}
