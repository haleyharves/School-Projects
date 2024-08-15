/*
 * Haley Harves
 * May 13, 2024
 * This collection of code runs an ATM object that interacts with bank
 * accounts as well as bank administration
 * I used the class lectures and geeks4geeks.com to help me with this project
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage stage){
        //initualizing objects
        ATM atm = new ATM();
        Admin Tom = new Admin(1234, 5678);
        Account Haley = new Account("haleyharves", "password", 2000);

        // first screen
        Label screenOneText = new Label("How would you like to login?");
        RadioButton AdminB = new RadioButton("Admin");
        RadioButton AccB = new RadioButton("Account");
        Button submit1 = new Button("Submit");

        HBox Radios = new HBox(20, AdminB, AccB);
        Radios.setPadding(new Insets(10));
        HBox screenOneTextBox = new HBox(screenOneText);
        screenOneTextBox.setAlignment(Pos.CENTER);
        HBox submit10 = new HBox(20, submit1);
        submit10.setAlignment(Pos.CENTER);
        VBox FirstScreen = new VBox(10, screenOneText, Radios, submit10);

        Scene FirstScene = new Scene(FirstScreen);

        //login sequence for ADMIN
        Label usernameAD = new Label("Username:");
        TextField userAD = new TextField();
        Label passwordAD = new Label("Password:");
        TextField passAD = new TextField();
        Button submitAD = new Button("Submit");
        Label resultAD = new Label();

        HBox usernameBoxAD = new HBox(20, usernameAD, userAD);
        usernameBoxAD.setPadding(new Insets(10));
        usernameBoxAD.setAlignment(Pos.CENTER);
        HBox passBoxAD = new HBox(20, passwordAD, passAD);
        passBoxAD.setPadding(new Insets(10));
        passBoxAD.setAlignment(Pos.CENTER);
        HBox submitADBox = new HBox(submitAD);
        submitADBox.setAlignment(Pos.CENTER);
        HBox resultADBox = new HBox(resultAD);
        resultADBox.setAlignment(Pos.CENTER);
        VBox loginBoxAD = new VBox(10, usernameBoxAD, passBoxAD, submitADBox, resultADBox);

        Scene loginSequenceAD = new Scene(loginBoxAD);

        //login sequence for ACCOUNT
        Label usernameACC = new Label("Username");
        TextField userACC = new TextField();
        Label passwordACC = new Label("Password:");
        TextField passACC = new TextField();
        Button submitACC = new Button("Submit");
        Label resultACC = new Label();

        HBox usernameBoxACC = new HBox(20, usernameACC, userACC);
        usernameBoxACC.setPadding(new Insets(10));
        usernameBoxACC.setAlignment(Pos.CENTER);
        HBox passBoxACC = new HBox(20, passwordACC, passACC);
        passBoxACC.setPadding(new Insets(10));
        passBoxACC.setAlignment(Pos.CENTER);
        HBox submitACCBox = new HBox(submitACC);
        submitACCBox.setAlignment(Pos.CENTER);
        HBox resultACCBox = new HBox(resultACC);
        resultACCBox.setAlignment(Pos.CENTER);
        VBox loginBoxACC = new VBox(10, usernameBoxACC, passBoxACC, submitACCBox, resultACCBox);

        Scene loginSequenceACC = new Scene(loginBoxACC);

        // admin screen
        Label welcomeAdmin = new Label("Welcome, Admin");
        Button refillATM = new Button("Refill ATM");

            //refill ATM page
            Label refillLabel = new Label("How much are you putting in the ATM?");
            TextField refillText = new TextField();
            Button refillSubmit = new Button("Submit");
            Label refillResult = new Label();

            HBox refill = new HBox(20, refillLabel, refillText, refillSubmit);
            refill.setPadding(new Insets(10));
            refill.setAlignment(Pos.CENTER);
            VBox refillBox = new VBox(10, refill, refillResult);

            Scene refillATMScene = new Scene(refillBox);
        
        Button checkButton = new Button("Check ATM Balance");
        Button AdminLogout = new Button("Logout");
        Label adminResult = new Label();

        HBox welcomeBox = new HBox(welcomeAdmin);
        welcomeBox.setAlignment(Pos.CENTER);
        welcomeBox.setPadding(new Insets(10));
        HBox adminMenuH = new HBox(20, refillATM, checkButton, AdminLogout);
        adminMenuH.setPadding(new Insets(15));
        HBox adminResultBox = new HBox(adminResult);
        adminResultBox.setAlignment(Pos.CENTER);
        adminResultBox.setPadding(new Insets(10));
        VBox adminMenu = new VBox(10, welcomeBox, adminMenuH, adminResultBox);

        Scene adminScene = new Scene(adminMenu);

        // account screen
        Label welcomeAccount = new Label("Welcome, Account");
        Button getBalance = new Button("Get Balance");
        Label withdraw = new Label("Withdraw:");
        Button fifty = new Button("$50");
        Button hundred = new Button("$100");
        Button twohun = new Button("$200");
        Button AccLogout = new Button("Logout");
        Label AccResult = new Label();

        VBox checkBox = new VBox(20, getBalance);
        checkBox.setPadding(new Insets(10));
        checkBox.setAlignment(Pos.CENTER);
        VBox withdraws = new VBox(10, withdraw, fifty, hundred, twohun);
        withdraws.setPadding(new Insets(10));
        HBox topACC = new HBox(20, welcomeAccount);
        topACC.setAlignment(Pos.CENTER);
        HBox bottomACC = new HBox(20, AccResult);
        bottomACC.setAlignment(Pos.CENTER);
        VBox logoutBox = new VBox(AccLogout);
        logoutBox.setPadding(new Insets(10));
        logoutBox.setAlignment(Pos.CENTER);

        
        BorderPane BPAccount = new BorderPane(withdraws, topACC, logoutBox, bottomACC, checkBox);

        Scene AccScene = new Scene(BPAccount);

        stage.setScene(FirstScene);
        stage.setTitle("Login Menu");
        stage.show();

        // the following action events are for the various submit and 
        //aciton buttons within the atm
        // they go in order of the screens written above 

        // showig user either the admin login or account login page
        submit1.setOnAction(event -> {
            if (AdminB.isSelected()){
                stage.setScene(loginSequenceAD);
                stage.setTitle("Admin Login");
                stage.show();
            }
            else if (AccB.isSelected()){
                stage.setScene(loginSequenceACC);
                stage.setTitle("Account Login");
                stage.show();
            }
        });

        // taking admin login as an int and logging in 
        submitAD.setOnAction(event -> {
            String uI = userAD.getText();
            String pI = passAD.getText();
            int u = Integer.parseInt(uI);
            int p = Integer.parseInt(pI);
            try {
                Tom.login(u, p);
            } catch (LoginFailedException e){
                resultAD.setText("Login Failled. Try Again");
            }
            if (Tom.getStatus()){
               stage.setScene(adminScene);
               stage.setTitle("Admin Menu");
               stage.show(); 
            }
        });
        // logging the account in 
        submitACC.setOnAction(event -> {
            String u = userACC.getText();
            String p = passACC.getText();
            try {
                Haley.login(u, p);
            } catch (LoginFailedException e){
                resultACC.setText("Login Failed. Try Again");
            }
            if (Haley.getStatus()){
                stage.setScene(AccScene);
                stage.setTitle("Account Menu");
                stage.show();
            }
        });
        //admin methods
        refillATM.setOnAction(event -> {
            stage.setScene(refillATMScene);
            stage.setTitle("Refill ATM");
            stage.show();
        });
        refillSubmit.setOnAction(event -> {
            String refillAmS = refillText.getText();
            int am = Integer.parseInt(refillAmS);
            try{
                Tom.refillATM(atm, am);
            } catch (LoginErrorException e){
                refillResult.setText("Error. Try Again.");
            }
            stage.setScene(adminScene);
            stage.setTitle("Admin Menu");
            stage.show();
        });
        checkButton.setOnAction(event -> {
            try {
            adminResult.setText("This ATM's balance is: $" +Tom.checkATMBalance(atm));
            } catch (LoginErrorException e){
                adminResult.setText("Login Error Exception occured");
            }
        });
        AdminLogout.setOnAction(event -> {
            try {
            Tom.logout();
            } catch (LoginErrorException e){
                adminResult.setText("Login error occured");
            }
            stage.setScene(FirstScene);
            stage.setTitle("Login Menu");
            stage.show();
        });
        // account methods
        getBalance.setOnAction(event -> {
            try{
            AccResult.setText("Account balance is: $" + Haley.getBalance());
            } catch (LoginErrorException e){
                AccResult.setText("Login error exception occured");
            }
        });
        fifty.setOnAction(event -> {
            try{
                Haley.withdraw50(atm);
                AccResult.setText("Amount has been withdrawn");
            } catch (LoginErrorException e){
                AccResult.setText("Login Error occured.");
            }
        });
        hundred.setOnAction(event -> {
            try {
                Haley.withdraw100(atm);
                AccResult.setText("Amount has been withdrawn");
            } catch (LoginErrorException e){
                AccResult.setText("Login Error Occured");
            }
        });
        twohun.setOnAction(event -> {
            try {
                Haley.withdraw200(atm);
                AccResult.setText("Amount has been withdrawn");
            } catch (LoginErrorException e){
                AccResult.setText("Login error has occured");
            }
        });
        AccLogout.setOnAction(event -> {
            try {
                Haley.logout();
                stage.setScene(FirstScene);
                stage.setTitle("Login Page");
                stage.show();
            } catch (LoginErrorException e){
                AccResult.setText("Login error occured.");
            }
        });

    }
       
}

