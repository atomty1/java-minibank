import java.util.Random;

public class Account {
    private String firstname;
    private String lastname;
    private String email;
    private String accountNumber;
    private double amount;
    private String password;
    Account(String _firstname, String _lastname, String _email, String _password){
        if (_password.length()< 8) {
            throw new IllegalArgumentException("Password should be a minimum of 8 characters");
        } else {
            this.firstname = _firstname.trim();
            this.lastname = _lastname.trim(); 
            this.email= _email.trim();
            this.amount = 50000;
            this.password = _password.trim();
            this.accountNumber = (generateAccountNumber()).trim();
        }
    }

    private String generateAccountNumber(){
         int i = 0;
         String newAccountNumber = "";
        while (i< 10) {
            newAccountNumber+=generateRandom();
            i++;
       }
       return newAccountNumber;
    }
    private int generateRandom(){
        Random rand = new Random();
        return rand.nextInt(10);
    }
    @Override
    public String toString() {
       return "Firstname: "+this.firstname+" lastname: "+
       this.lastname+ " email: "+ this.email +" account number: "+
       this.accountNumber+ " amount: "+ this.amount;
    }
    public boolean checkLogin(String _email, String _password){
        if (email.equals(_email) && password.equals(_password)) return true;
        return false;
    }
    public void transfer(double _amount, String _accountNumber){
        if(_amount<=amount){
            amount-= _amount;
        }
    }
    public void receiveMoney(double _amount){
        amount+=_amount;
    }
    public String getAccountNumber(){
        return accountNumber;
    }
    public double getAmount(){
        return amount;
    }
    public String getFirstname(){
        return firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public String getFullName(){
        return getFirstname()+ " "+ getLastname();
    }
    
}
