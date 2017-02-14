/*
Feb 13, 2017
FXMLDocumentController.java, FXMLDocumentController, Joni Sikiö <joni.sikio@student.lut.fi> 
Kuvaus sisällöstä: 
Kehitysympäristö: NetBeans
Muutoshistoria:
Lisenssi: default

 */
package bankaccount;

import javafx.scene.paint.Color;
import java.awt.Paint;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Joni Sikiö <joni.sikio@student.lut.fi>
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button convertBankNumberButton;
    @FXML
    private TextField smallBankNumberField;
    @FXML
    private Label longBankNumberLabel;
    @FXML
    private Label checkDigitCorrectLabel;
    @FXML
    private Label checkDigitNumberLabel;
    @FXML
    private Label shortBankNumberFormatLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) { // Initializes the GUI
        checkDigitCorrectLabel.setText("");
        longBankNumberLabel.setText("");
        checkDigitNumberLabel.setText("");
        
    }    

    @FXML
    private void convertBankNumberButtonAction(ActionEvent event) {
        FinnishBankAccountNumber fban = new FinnishBankAccountNumber(smallBankNumberField.getText()); // new FinnishBankAccountNumber instance is created
        
        if (fban.convertBankNumber()){
            shortBankNumberFormatLabel.setText("");
            longBankNumberLabel.setText(fban.getLongBankNumberString());  // labels text is set to the long bank account number format
            checkDigitNumberLabel.setText(String.valueOf(fban.getCheckDigitValue()));
            if (fban.getCheckDigitCorrect()){  //Check digit was correct
                checkDigitCorrectLabel.setText("Correct");
                checkDigitCorrectLabel.setTextFill(Color.GREEN);
            } else { //Check digit was not correct
                checkDigitCorrectLabel.setText("False");
                checkDigitCorrectLabel.setTextFill(Color.RED);

            }
        } else { //short format bank account number was in an incorrect format.
            shortBankNumberFormatLabel.setText("Incorrect bank account number format");
        }
        
    }
    
}
