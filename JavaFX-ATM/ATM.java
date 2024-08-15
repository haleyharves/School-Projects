import java.io.*;

public class ATM {
   private int balance;
   private PrintWriter outputFile;
   public ATM(){
    balance = 10000;
   }
   public void refill(int i){
    balance = balance + i;
    try{
      outputFile = new PrintWriter(new FileWriter("record.txt", true));
      outputFile.println(i + " dollars was refilled in the machine. Balance is now: " + balance);
      outputFile.close();
   } catch (IOException e){
      System.out.println("Unable to open file");
   } 
}
   public void remove(int i){
    balance = balance - i;
    try{
      outputFile = new PrintWriter(new FileWriter("record.txt", true));
      outputFile.println(i + " dollars was withdrawn from the machine. Balance is now: " + balance);
      outputFile.close();
   } catch (IOException e){
      System.out.println("Unable to open file");
   } 
}
   public int getBalance(){
    return balance;
   }
}
