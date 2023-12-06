import java.util.ArrayList;
import java.util.Scanner;

class Index{
     static ArrayList<Account> accounts = new ArrayList<Account>();
    public static void main(String[] args) {
         mainMenu();
    }
    public static void mainMenu(){
        System.out.println("What would you like to do? ");
         Scanner input = new Scanner(System.in);
        System.out.print("1: Create account 2: Login 3: Logout ");
        int userInput = input.nextInt();
        if(userInput==1){
            createAccount();
        } else if(userInput==2){
            login();
        } else{
            System.out.println("Thanks for banking with us");
        }
        
    }
    public static void createAccount(){
        Scanner input = new Scanner(System.in);
        System.out.print("Input your first name: ");
        String firstname = input.next();
        System.out.print("Input your last name: ");
        String lastname = input.next();
        System.out.print("Input your email: ");
        String email = input.next();
        System.out.print("password ");
        String password = input.next();
        Account newAccount = new Account(firstname, lastname, email, password);
        accounts.add(newAccount);
        mainMenu();
    }
    public static void login(){
        Scanner input = new Scanner(System.in);
        System.out.print("Input your email: ");
        String email = (input.next()).trim();
        System.out.print("password ");
        String password = (input.next()).trim();
        boolean accountFound = false; 
        Account account = null;
        for (int i = 0; i < accounts.size(); i++) {
            account = accounts.get(i);
            if(account.checkLogin(email, password)){
                accountFound= true;
                break;
            }
        }
        if(accountFound){
            dashboard(account);
        } else{
            System.out.println("Incorrect login details");
            mainMenu();
        }
    }
    private static void dashboard(Account account){
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome "+ account.getFullName());
        System.out.println("1. Transfer 2. Check account details 3. Main menu 4. logout");
        int inputValue = input.nextInt();
        if(inputValue==1){
            transfer(account);
        } else if(inputValue==2){
            System.out.println(account.toString());
            mainMenu();
        } else if(inputValue==3){
            mainMenu();
        } else if(inputValue==4){
            account=null;
            System.out.println("Logout successful");
            mainMenu();
        }
         else{
            System.out.println("Invalid entry");
            dashboard(account);
        }

    }
    private static void transfer(Account account){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter recipient account number: ");
        String accountNumber = input.next();
        System.out.print("Amount: ");
        double amount = input.nextDouble();
       
        if(amount<=account.getAmount()){
            boolean accountFound = false; 
            Account recipientAccount= null;
            for (int i = 0; i < accounts.size(); i++) {
                recipientAccount = accounts.get(i);
                if(recipientAccount.getAccountNumber().equals(accountNumber)){
                    accountFound= true;
                    break;
                }
            }
            if(accountFound){
                 System.out.println("Are you sure you want to transfer "+ amount+
                 " to "+ recipientAccount.getFirstname() +" "+ recipientAccount.getLastname()+ "?");
                 System.out.println("1. Yes 2. No");
                 int sureTransfer = input.nextInt();
                 if(sureTransfer==1){
                     account.transfer(amount, accountNumber);
                    recipientAccount.receiveMoney(amount);
                    System.out.println("Transfer successful");
                    System.out.println("Do you want to perfome another treansaction? ");
                    System.out.println("1. Yes 2. No");
                    int continueTransaction = input.nextInt();
                    if(continueTransaction==1){
                        login();
                    } else{
                       System.out.println("Thank you");
                       mainMenu();
                    }
                } else{
                    transfer(account);
                }
               
            }else{
                 System.out.println("Incorrect account number");
                 login();
            }
            
            
        } else{
            System.out.println("Insufficient fund");
            login();
        }
       
        
    }
   
}