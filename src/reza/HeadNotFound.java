package reza;

public class HeadNotFound extends RuntimeException {
    public HeadNotFound() {
        super("Error, this name of origin or destination does not exist! Try again.");
    }
}
