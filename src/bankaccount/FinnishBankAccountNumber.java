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
    
    FinnishBankAccountNumber(){
        
    }
    
    public boolean convertBankNumber(){
        String tempValue;
        
        if (shortBankNumber.length()>=9 && shortBankNumber.length()<= 15 && shortBankNumber.charAt(6) == '-'){ //Fix dis
            longBankNumberString = shortBankNumber.substring(0, 6);
            tempValue = shortBankNumber.substring(7, shortBankNumber.length());
            
            
            if(shortBankNumber.charAt(0) == '4' || shortBankNumber.charAt(0) == '5'){ 
                longBankNumberString += tempValue.charAt(0);
                tempValue = tempValue.substring(1);
            } 

            while(longBankNumberString.length()+tempValue.length() < 14){
                longBankNumberString += '0';
            }

            longBankNumberString += tempValue;
            
            System.out.println(longBankNumberString);     
            longBankNumber = Long.valueOf(longBankNumberString);
            System.out.println(longBankNumber);
            
            checkDigit();
            return true;
        } else {
            System.out.println("Bank number is not in correct format");
            return false;
        }
        
    }

    void setShortBankNumber(String text) {
        shortBankNumber = text;
    }
    
    public void checkDigit(){
        int sum=0;
        if(!longBankNumberString.isEmpty()){
            for(int i=12; i>=0;i--){
                int currentNumber = Character.getNumericValue(longBankNumberString.charAt(i));
                if(i%2 == 1){ //Joka toinen alkaen 2:sta numerosta
                    sum += currentNumber;
                } else {   //Joka toinen alkaen 1:sta numerosta
                    currentNumber += currentNumber; //kerrotaan 2:lla
                    if(currentNumber >= 10){
                        currentNumber = 1 + currentNumber%10; // Modulo hack
                    }
                    sum += currentNumber;
                }
             }
            checkDigit = sum+(10-sum%10)-sum;
            System.out.println(checkDigit);
            
        } 
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
