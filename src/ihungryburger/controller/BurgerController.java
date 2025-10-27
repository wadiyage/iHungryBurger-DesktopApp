/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ihungryburger.controller;

import ihungryburger.model.Burger;
import ihungryburger.ui.search.SearchBestCustomerFrame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public class BurgerController {
    private JTable tblCommonReference;
    
    public BurgerController() {
        
    }
    
    public BurgerController(JTable tblCommon) {
        tblCommonReference = tblCommon;
    }
    
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
        fw.close();
    } 
    
    public String getStatusInTextMode(int status) {
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
    
    public boolean haveAlreadyTaken(String burgerID) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("data/Burger.txt");
        BufferedReader br = new BufferedReader(fr);
        
        String line = br.readLine();
        
        Burger burger = null;
        while(line!=null) {
            String[] rowData = line.split("-");
            if(rowData[0].equalsIgnoreCase(burgerID)) {
                return true;
            }
            line = br.readLine();
        }
        return false;
    }
    
    public Burger retrieveBurger(String burgerID) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("data/Burger.txt");
        BufferedReader br = new BufferedReader(fr);
        
        String line = br.readLine();
        
        Burger burger = null;
        while(line!=null) {
            String[] rowData = line.split("-");
            if(rowData[0].equalsIgnoreCase(burgerID)) {
                burger = new Burger(rowData[0], rowData[1], rowData[2], Integer.parseInt(rowData[3]), statusAsInteger(rowData[4]));
            }
            line = br.readLine();
        }
        return burger;
    }
        
    private int statusAsInteger(String statusInTextMode) {
        int statusAsInteger=0;
        switch(statusInTextMode) {
            case "PREPARING": 
                statusAsInteger=Burger.PREPARING;
                break;
            case "DELIVERED": 
                statusAsInteger=Burger.DELIVERED;
                break;    
            case "CANCELED": 
                statusAsInteger=Burger.DELIVERED;
                break;
            default:
        }
        return statusAsInteger;
    }
    
    public void preparingBurgerDetails() throws FileNotFoundException, IOException {
        DefaultTableModel dtm = (DefaultTableModel) tblCommonReference.getModel();
        dtm.setRowCount(0);
        
        FileReader fr = new FileReader("data/Burger.txt");
        BufferedReader br = new BufferedReader(fr);
        
        String line = br.readLine();
        while(line!=null) {
            String[] rowData = line.split("-");
            if(rowData[4].equals("PREPARING")) {
                Object[] objectVisedRowData = {rowData[0],rowData[1],rowData[2],rowData[3],(double)(Integer.parseInt(rowData[3])*Burger.BURGERPRICE)};
                dtm.addRow(objectVisedRowData);
            }
            line = br.readLine();
        }
    }
    
    public void deliveredBurgerDetails() throws FileNotFoundException, IOException {
        DefaultTableModel dtm = (DefaultTableModel) tblCommonReference.getModel();
        dtm.setRowCount(0);
        
        FileReader fr = new FileReader("data/Burger.txt");
        BufferedReader br = new BufferedReader(fr);
        
        String line = br.readLine();
        while(line!=null) {
            String[] rowData = line.split("-");
            if(rowData[4].equals("DELIVERED")) {
                Object[] objectVisedRowData = {rowData[0],rowData[1],rowData[2],rowData[3],(double)(Integer.parseInt(rowData[3])*Burger.BURGERPRICE)};
                dtm.addRow(objectVisedRowData);
            }
            line = br.readLine();
        }
    }
    
    public void canceledBurgerDetails() throws FileNotFoundException, IOException {
        DefaultTableModel dtm = (DefaultTableModel) tblCommonReference.getModel();
        dtm.setRowCount(0);
        
        FileReader fr = new FileReader("data/Burger.txt");
        BufferedReader br = new BufferedReader(fr);
        
        String line = br.readLine();
        while(line!=null) {
            String[] rowData = line.split("-");
            if(rowData[4].equals("CANCELED")) {
                Object[] objectVisedRowData = {rowData[0],rowData[1],rowData[2],rowData[3],(double)(Integer.parseInt(rowData[3])*Burger.BURGERPRICE)};
                dtm.addRow(objectVisedRowData);
            }
            line = br.readLine();
        }
    }
    
    public boolean haveAlreadyCustomer(String customerID) throws FileNotFoundException, IOException {
        DefaultTableModel dtm = (DefaultTableModel) tblCommonReference.getModel();
        dtm.setRowCount(0);
        
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
    
    public String getCustomerDetails(String customerID) throws FileNotFoundException, IOException {
        DefaultTableModel dtm = (DefaultTableModel) tblCommonReference.getModel();
        dtm.setRowCount(0);
        
        FileReader fr = new FileReader("data/Burger.txt");
        BufferedReader br = new BufferedReader(fr);

        String line = br.readLine();
        String customerName = "";
        while(line!=null) {
            String[] rowData = line.split("-");
            
            if(rowData[1].equals(customerID)) {
                Object[] objectVisedRowData = {rowData[0],rowData[3],(Integer.parseInt(rowData[3]))*Burger.BURGERPRICE};
                customerName = rowData[2];
                dtm.addRow(objectVisedRowData);
            }
            line = br.readLine();
        }
        br.close();
        fr.close();
        
        return customerName;
    }
    
    public void bestCustomerDetails() throws IOException {
        DefaultTableModel dtm = (DefaultTableModel) tblCommonReference.getModel();
        dtm.setRowCount(0);
        
        String[] removedDuplicateCustomers = removeDuplicatedCustomers();
        double[] totalPurchased = findTotalPurchasedForRelevantCustomer(removedDuplicateCustomers);
        
        removedDuplicateCustomers = sortRemovedDuplicatedCustomers(removedDuplicateCustomers, totalPurchased);
        double[] sortedTotalPurchased = sortTotalPurchasedForRelevantCustomers(totalPurchased);
        String[] desSortedCustomerNames = desSortedNamesRegardingToTotalPurchasedForRelevantCustomers(removedDuplicateCustomers);
        
        for(int i=0;i<removedDuplicateCustomers.length;i++) {
            Object[] objectVisedRowData = {removedDuplicateCustomers[i],desSortedCustomerNames[i],sortedTotalPurchased[i]};
            dtm.addRow(objectVisedRowData);
        }
    }
    
    private String[] removeDuplicatedCustomers() throws FileNotFoundException, IOException {
        String[] removedDuplicatedCustomers = new String[0];
        
        FileReader fr = new FileReader("data/Burger.txt");
        BufferedReader br = new BufferedReader(fr);
                
        String line = br.readLine();
        while(line!=null) {
            String[] rowData = line.split("-");
            if(!HaveDuplicated(removedDuplicatedCustomers, rowData[1])) {
                String[] tempRemovedDuplicatedCustomers = new String[removedDuplicatedCustomers.length+1];
                for(int i=0;i<removedDuplicatedCustomers.length;i++) {
                    tempRemovedDuplicatedCustomers[i]=removedDuplicatedCustomers[i];
                }
                tempRemovedDuplicatedCustomers[tempRemovedDuplicatedCustomers.length-1]=rowData[1];
                removedDuplicatedCustomers=tempRemovedDuplicatedCustomers;
            }
            line = br.readLine();
        }
        return removedDuplicatedCustomers;
    }
    
    private boolean HaveDuplicated(String[] removedDuplicatedCustomers, String customerID) { 
        for(int i=0;i<removedDuplicatedCustomers.length;i++) {
            if(removedDuplicatedCustomers[i].equals(customerID)) {
                return true;
            }
        }
        return false;
    }
    
    private double[] findTotalPurchasedForRelevantCustomer(String[] removedDuplicatedCustomers) throws IOException {
        double[] totalPurchasedForRelevantCustomer = new double[removedDuplicatedCustomers.length];
        FileReader fr = null;
        try {
            for(int i=0;i<removedDuplicatedCustomers.length;i++) {
                fr = new FileReader("data/Burger.txt");
                BufferedReader br = new BufferedReader(fr);
                
                String line = br.readLine();
                while(line!=null) {
                    String[] rowData = line.split("-");
                    if(removedDuplicatedCustomers[i].equals(rowData[1])) {
                        totalPurchasedForRelevantCustomer[i]+=Integer.parseInt(rowData[3])*Burger.BURGERPRICE;
                    }
                    line = br.readLine();
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SearchBestCustomerFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(SearchBestCustomerFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return totalPurchasedForRelevantCustomer;
    }
    
    private String[] sortRemovedDuplicatedCustomers(String[] removedDuplicatedCustomers, double[] totalPurchased) {
        for(int i=totalPurchased.length-1;i>=0;i--) {
            for(int j=0;j<i;j++) {
                if(totalPurchased[j]<totalPurchased[j+1]) {
                    double tempTotalPurchased = totalPurchased[j];
                    totalPurchased[j] = totalPurchased[j+1];
                    totalPurchased[j+1] = tempTotalPurchased;
                    
                    String tempCustomerID = removedDuplicatedCustomers[j];
                    removedDuplicatedCustomers[j] = removedDuplicatedCustomers[j+1];
                    removedDuplicatedCustomers[j+1] = tempCustomerID;
                }
            }
        }
        return removedDuplicatedCustomers;
    }
    
    private double[] sortTotalPurchasedForRelevantCustomers(double[] totalPurchased) {
        for(int i=totalPurchased.length-1;i>=0;i--) {
            for(int j=0;j<i;j++) {
                if(totalPurchased[j]<totalPurchased[j+1]) {
                    double temp = totalPurchased[j];
                    totalPurchased[j] = totalPurchased[j+1];
                    totalPurchased[j+1] = temp;
                }
            }
        }
        return totalPurchased;
    }
    
    private String[] desSortedNamesRegardingToTotalPurchasedForRelevantCustomers(String[] removedDuplicatedCustomers) throws FileNotFoundException, IOException {
        String[] sortedRemovedDuplicatedCustomerNames = new String[removedDuplicatedCustomers.length];
        
        for(int i=0;i<removedDuplicatedCustomers.length;i++) {
            FileReader fr = new FileReader("data/Burger.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String line = br.readLine();
            while(line!=null) {
                String[] rowData = line.split("-");
                if(removedDuplicatedCustomers[i].equals(rowData[1])) {
                    sortedRemovedDuplicatedCustomerNames[i]=rowData[2];
                }
                line = br.readLine();
            }
        }
        return sortedRemovedDuplicatedCustomerNames;
    }
    
}
