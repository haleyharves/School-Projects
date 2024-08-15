

public class LoginErrorException extends RuntimeException {
    public LoginErrorException(){
        super("Can not complete request. User is not logged in.");
    }
}