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
    FinnishBankAccountNumber fban;
    @FXML
    private Label checkDigitCorrectLabel;
    @FXML
    private Label checkDigitNumberLabel;
    @FXML
    private Label shortBankNumberFormatLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fban = new FinnishBankAccountNumber();
        checkDigitCorrectLabel.setText("");
        longBankNumberLabel.setText("");
        checkDigitNumberLabel.setText("");
        // TODO
    }    

    @FXML
    private void convertBankNumberButtonAction(ActionEvent event) {
        fban.setShortBankNumber(smallBankNumberField.getText());
        if (fban.convertBankNumber()){
            shortBankNumberFormatLabel.setText("");
            longBankNumberLabel.setText(fban.getLongBankNumberString());
            checkDigitNumberLabel.setText(String.valueOf(fban.getCheckDigitValue()));
            if (fban.getCheckDigitCorrect()){
                checkDigitCorrectLabel.setText("Correct");
                checkDigitCorrectLabel.setTextFill(Color.GREEN);
            } else {
                checkDigitCorrectLabel.setText("False");
                checkDigitCorrectLabel.setTextFill(Color.RED);

            }
        } else {
            shortBankNumberFormatLabel.setText("Incorrect bank account number format");
        }
        
    }
    
}
