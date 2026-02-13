package bettapcq.projectu2w2.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Record con id " + id + " non trovato");
    }
}
