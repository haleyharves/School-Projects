


public class Account implements User<String>{
    private String username;
    private String password;
    private boolean access;
    private int balance;

    public Account(String n, String p, int b){
        username = n;
        password = p;
        balance = b;
        access = false;
    }
    public void login(String name, String t){
        if (!access) {
            if (name.equals(username) && t.equals(password)) {
                access = true;
                System.out.println("Login Successful.");
            }
            else {
                throw new LoginFailedException();
            }
        }
        else {
            System.out.println("Error, already logged in.");
        }
    }
    public void logout(){
        if (access = true){
            access = false;
            System.out.println("Goodbye!");
        }
        else if (access = false){
            throw new LoginErrorException();
        }
    }
    public boolean getStatus(){
        return access;
    }

    public int getBalance(){
        if (access == true){
            return balance;
    }
        else {
            throw new LoginErrorException();
        }
    }
    public void withdraw50(ATM atm){
        if (!access){
            throw new LoginErrorException();
        }
        System.out.println("$50 is being removed from your account.");
        balance = balance - 50;
        atm.remove(50);
        System.out.println("Current account balance is now: " + balance);
    }
    public void withdraw100(ATM atm){
        if (!access){
            throw new LoginErrorException();
        }
        System.out.println("$100 is being removed from your account.");
        balance = balance - 100;
        atm.remove(100);
        System.out.println("Current account balance is now: " + balance);
    }
    public void withdraw200(ATM atm){
        if(!access){
            throw new LoginErrorException();
        }
        System.out.println("$200 is being removed from your account.");
        balance = balance - 200;
        atm.remove(200);
        System.out.println("Current account balance is now: "+ balance);
    }





}
