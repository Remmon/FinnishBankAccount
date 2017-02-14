/*
Feb 13, 2017
FinnishBankAccountNumber.java, FinnishBankAccountNumber, Joni Sikiö <joni.sikio@student.lut.fi> 
Kuvaus sisällöstä: 
Kehitysympäristö: NetBeans
Muutoshistoria:
Lisenssi: default

 */
package bankaccount;

/**
 *
 * @author Joni Sikiö <joni.sikio@student.lut.fi>
 */
public class FinnishBankAccountNumber {
    String shortBankNumber;
    
    long longBankNumber;
    String longBankNumberString;
    
    int checkDigit;
    boolean checkDigitCorrect;
    
    FinnishBankAccountNumber(String sBN){
        shortBankNumber = sBN;
    }
    
    public boolean convertBankNumber(){
        String tempValue;
        
        if (shortBankNumber.length()>=9 && shortBankNumber.length()<= 15 && shortBankNumber.charAt(6) == '-'){ //Here we check if the short bank number input is correct.
            longBankNumberString = shortBankNumber.substring(0, 6);             // Short bank number is divided into two parts
            tempValue = shortBankNumber.substring(7, shortBankNumber.length()); // char '-' is excluded
            
            
            if(shortBankNumber.charAt(0) == '4' || shortBankNumber.charAt(0) == '5'){ // Check if the bank is one of the exceptions (Sp, Pop, Aktia, Op, OKO Bank or Okobank). 
                longBankNumberString += tempValue.charAt(0); //First number from second part of the short bank number has to be before the zeroes
                tempValue = tempValue.substring(1); // first number is removed from the string
            } 

            while(longBankNumberString.length()+tempValue.length() < 14){
                longBankNumberString += '0';    // Here we add zeroes so the total length of the bank number will be 14 digits
            }

            longBankNumberString += tempValue; //Here we add the second part of the bank number and we get the full bank number in electronic format.
            
            longBankNumber = Long.valueOf(longBankNumberString); // Here we convert the long bank account number string to long format.
            
            checkDigit();
            return true;
            
        } else {
            System.out.println("Bank number is not in correct format");
            return false;
        }
        
    }
    
    public void checkDigit(){
        int sum=0;
        if(!longBankNumberString.isEmpty()){
            for(int i=12; i>=0;i--){
                int currentNumber = Character.getNumericValue(longBankNumberString.charAt(i));
                if(i%2 == 1){ //Every second character starting from the second character.
                    sum += currentNumber;
                } else {  //Every second character starting from the first character.
                    currentNumber += currentNumber; // This is essentially the same as multiplying the number by it's weight.
                    if(currentNumber >= 10){
                        currentNumber = 1 + currentNumber%10; // Modulo hack
                    }
                    sum += currentNumber;
                }
             }
            checkDigit = sum+(10-sum%10)-sum;   // Calculation of the check digit.
            
        } 
        
        // Here we check if the check digit in the short format bank account number is legit.
        if (Character.getNumericValue(longBankNumberString.charAt(longBankNumberString.length()-1)) == checkDigit){ 
            checkDigitCorrect = true;
        } else {
            checkDigitCorrect = false;
        }
    }
    
    public boolean getCheckDigitCorrect(){
            return checkDigitCorrect;
    }
    public int getCheckDigitValue(){
            return checkDigit;
    }
    
    public String getLongBankNumberString(){
        return longBankNumberString;
    }
    
}
