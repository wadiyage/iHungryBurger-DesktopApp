/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ihungryburger.controller;

import ihungryburger.model.Burger;
import ihungryburger.service.BurgerList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public class BurgerController {
    
    public String generateBurgerID() throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("data/Burger.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        if(line==null) {
            return "B0001";
        } else {
            String lastGeneratedBurgerID = "";
            while(line!=null) {
                String[] rowData = line.split("-");
                lastGeneratedBurgerID = rowData[0];
                line = br.readLine();
            }
            br.close();
            fr.close();

            
            String separatedInteger = lastGeneratedBurgerID.substring(1);
            int separatedIntegerConvertedForInteger=0;
            try {
                separatedIntegerConvertedForInteger = Integer.parseInt(separatedInteger);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number format in String.");
            }
            int newlyGeneratedBurgerID = ++separatedIntegerConvertedForInteger;
            String formattedNewlyGeneratedBurgerID = String.format("B%04d", newlyGeneratedBurgerID);
            return formattedNewlyGeneratedBurgerID;
        }
        
        
    }
    
    public boolean isValidatedCustomerID(String customerID) {
        if(customerID.charAt(0)==48 && customerID.length()==10)
            return true;
        return false;
    }
    
    public boolean isValidatedPhoneNumber(String phoneNumber) {
        for(int i=0;i<phoneNumber.length();i++) {
            if(!(phoneNumber.charAt(i)>=48) && (phoneNumber.charAt(i)<=57)) 
                return false;
        }
        return true;
    }
    
    public boolean haveAlreadyOrdered(String customerID) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("data/Burger.txt");
        BufferedReader br = new BufferedReader(fr);
        
        String line = br.readLine();
        while(line!=null) {
            String[] rowData = line.split("-");
            if(rowData[1].equals(customerID)) {
                return true;
            }
            line = br.readLine();
        }
        return false;
    }
    
    public String searchCustomer(String customerID) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("data/Burger.txt");
        BufferedReader br = new BufferedReader(fr);
        
        String line = br.readLine();
        
        String customerName = ""                ;
        while(line!=null) {
            String[] rowData = line.split("-");
            if(rowData[1].equals(customerID)) {
                customerName = rowData[2];
                return customerName;
            }
            line = br.readLine();
        }
        return customerName;
    }
    
    public void addNewBurger(Burger burger) throws IOException {
        FileWriter fw = new FileWriter("data/Burger.txt", true);
        fw.write(burger.getBurgerID()+"-"+burger.getCustomerID()+"-"+burger.getCustomerName()+"-"+Integer.toString(burger.getBurgerQty())+"-"+getStatusInTextMode(burger.getStatus())+"\n");
        // burgerList.add(burger);
        fw.close();
    } 
    
    private String getStatusInTextMode(int status) {
        String statusInTextMode = "";
        switch(status) {
            case 0: 
                statusInTextMode = "PREPARING";
                break;
            case 1: 
                statusInTextMode = "DELIVERED";
                break;
            case 2: 
                statusInTextMode = "CANCELED";
                break;
            default:
        }
        return statusInTextMode;
    }
    
    
}
