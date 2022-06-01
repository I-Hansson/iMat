package MittKonto;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.io.IOException;

public class Account  extends AnchorPane {
    IMatDataHandler handler = IMatDataHandler.getInstance();
    Customer user = handler.getCustomer();
    CreditCard card = handler.getCreditCard();
    @FXML
    AnchorPane personPane;
    @FXML AnchorPane personButton;
    Boolean person = false;



    @FXML AnchorPane cardPane;
    @FXML AnchorPane kortButton;
    Boolean kort = false;


    @FXML
    TextField fornamn;
    @FXML
    TextField efternamn;
    @FXML
    TextField mobilNummer;
    @FXML
    TextField email;
    @FXML
    TextField gatuAddress;
    @FXML
    TextField postOrt;
    @FXML
    TextField postNummer;

    @FXML
    TextField kortinne;
    @FXML
    TextField kort1;
    @FXML
    TextField kort2;
    @FXML
    TextField kort3;
    @FXML
    TextField kort4;
    @FXML
    TextField datum1;
    @FXML
    TextField datum2;
    @FXML
    TextField cvc;



public  Account(){
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AccountDesign.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
        fxmlLoader.load();
    } catch (IOException exception) {
        throw new RuntimeException(exception);
    }
    ifAlreadyUser();
    personCliked();
}
@FXML
public void personCliked(){
    ifAlreadyUser();
    person = true;
    kort = false;
    checkIS();
    personPane.toFront();

}
@FXML
public void kortCliked(){
    person = false;
    kort = true;
    checkIS();
    cardPane.toFront();
}
public void checkIS(){
    if (person){
        personButton.setStyle("-fx-background-color: rgb(51, 153, 255)");
    }else{
        personButton.setStyle("-fx-background-color: rgb(255,255,255)");
    }
    if (kort){
       kortButton.setStyle("-fx-background-color: rgb(51, 153, 255)");
    }else{
        kortButton.setStyle("-fx-background-color: rgb(255,255,255)");
    }
}
@FXML
public void saveDetails(){
    user.setFirstName(fornamn.getText());
    user.setLastName(efternamn.getText());
    user.setMobilePhoneNumber(mobilNummer.getText());
    user.setEmail(email.getText());
    user.setAddress(gatuAddress.getText());
    user.setPostAddress(postOrt.getText());
    user.setPostCode(postNummer.getText());

    card.setHoldersName(kortinne.getText());
    card.setCardNumber(kort1.getText()+" " +kort2.getText()+" " +kort3.getText()+" " +kort4.getText());
        if(datum1.getText().equals("")){
            card.setValidMonth(0);
        }else{
            card.setValidMonth(Integer.parseInt(datum1.getText()));
        }
        if(datum2.getText() .equals("")){
            card.setValidYear(0);
        }else{
            card.setValidYear(Integer.parseInt(datum2.getText()));
    }
     if(cvc.getText().equals("")){
        card.setVerificationCode(0);
      }else{
         card.setVerificationCode(Integer.parseInt(cvc.getText()));
    }




    System.out.println("sparad!!");

}

public void ifAlreadyUser() {
    if (!user.getFirstName().isEmpty()) {
        fornamn.setText(user.getFirstName());
        efternamn.setText(user.getLastName());
        mobilNummer.setText(user.getMobilePhoneNumber());
        email.setText(user.getEmail());
        gatuAddress.setText(user.getAddress());
        postOrt.setText(user.getPostAddress());
        postNummer.setText(user.getPostCode());

    }
        String[] split = {""};
        split = card.getCardNumber().split(" ");
        if(split.length >= 1){
            kort1.setText(split[0]);
        }
        kortinne.setText(card.getHoldersName());

        if(split.length >= 2){
            kort2.setText(split[1]);
        }
    if(split.length >= 3){
        kort3.setText(split[2]);
    }
    if(split.length >= 4 ){
        kort4.setText(split[3]);
    }
    if(card.getValidMonth() == 0){
        datum1.setText("");
    }else{
        datum1.setText(String.valueOf(card.getValidMonth()));
    }
    if(card.getValidYear() == 0){
        datum2.setText("");
    }else{
        datum2.setText(String.valueOf(card.getValidYear()));
    }
    if(card.getVerificationCode() == 0){
        cvc.setText("");
    }else{
        cvc.setText(String.valueOf(card.getVerificationCode()));
    }

}
}

