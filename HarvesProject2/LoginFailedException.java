

public class LoginFailedException extends RuntimeException{
    public LoginFailedException(){
        super("Login/Logout has failed. Please try again.");
    }
}
