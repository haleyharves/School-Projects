

public class Admin implements User<Integer>{
    private Integer username;
    private Integer password;
    private boolean access;

    public Admin(Integer u, Integer p){
        username = u;
        password = p;
        access = false;
    }
    public void login(Integer u, Integer p){
        if(u.equals(username) && p.equals(password)){
            System.out.println("Login successful! Welcome Admin.");
            access = true;
        }
        else{
            throw new LoginFailedException();
        }
    }
    public void logout(){
        if (access == true){
            access = false;
            System.out.println("Goodbye!");
        }
        else if (access == false){
            throw new LoginErrorException();
        }
    }
    public boolean getStatus(){
        return access;
    }
    public void refillATM(ATM atm, int i){
        if (access == true){
            atm.refill(i);
            }
        else if (access == false){
            throw new LoginErrorException();
        }
    }
    public int checkATMBalance(ATM atm){
        if (access == false){
            throw new LoginErrorException();     
    } else{
        return atm.getBalance();
    }


}
}
