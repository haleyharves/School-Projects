

public class ATMTesting{
    public static void main(String[] args) {
       ATM atm = new ATM();
        Admin Tom = new Admin(1234, 5678);
        Account Haley = new Account("haleyharves", "password", 2000);

        /*Tom.login(1234, 5678);
        Tom.checkATMBalance(atm);
        Tom.logout();
        
        Haley.login("haleyharves", "password");
        Haley.getBalance();
        Haley.withdraw200(atm);
        Haley.getBalance();
        Haley.logout();

        Tom.login(1234,5678);
        Tom.checkATMBalance(atm);
        Tom.refillATM(atm);
        Tom.logout();*/
        
        Tom.checkATMBalance(atm);

    }
}