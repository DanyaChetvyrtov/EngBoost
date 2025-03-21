package app.first.myEng.engBoost.models.exception;

public class InnerServerError extends RuntimeException {
    public InnerServerError(String message) {
        super(message);
    }
}
