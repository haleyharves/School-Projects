

interface User<T>{
    public void login(T u, T p);
    // this method takes in the password of the username's object and matches it with what it's supposed to be 
    public void logout();
    
    public boolean getStatus();

    
}
