
package ihungryburger.service;

import ihungryburger.model.Burger;


public class BurgerCollection {
    private Burger[] burger = new Burger[0];
    
    public String generateOrderID() {
        if(burger.length<=0) {
            return "B0001";
        } else {
            String recentlyGeneratedOrderID = burger[burger.length-1].getOrderID();
            String extractedInteger = recentlyGeneratedOrderID.substring(1);
            int convertedExtractedValueToAnInteger = Integer.parseInt(extractedInteger);
            int incrementedValueForGeneratingNewID = ++convertedExtractedValueToAnInteger;
            String formattedGeneratedID = String.format("B%04d", incrementedValueForGeneratingNewID);
            return formattedGeneratedID;
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
    
    public int indexOf(String key) {
        for(int i=0;i<burger.length;i++) {
            if(burger[i].getCustomerID().equals(key))
                return i;
        }
        return -1;
    }
    
    public String extractedName(int index) {
        return burger[index].getCustomerName();
    }
    
    public void extendBurger() {
        Burger[] tempBurger = new Burger[burger.length+1];
        for(int i=0;i<burger.length;i++) {
            tempBurger[i]=burger[i];
        }
        burger=tempBurger;
    }
    
    public void addBurger(Burger newBurger) {
        extendBurger();
        burger[burger.length-1]=newBurger;
    }
    
    public String[] removedDuplicateCustomers() {
        String[] removedDuplicateCustomers = new String[0];
        for(int i=0;i<burger.length;i++) {
            if(!isDuplicated(removedDuplicateCustomers,burger[i].getCustomerID())) {
                String[] tempRemovedDuplicateCustomers = new String[removedDuplicateCustomers.length+1];
                for(int j=0;j<removedDuplicateCustomers.length;j++) {
                    tempRemovedDuplicateCustomers[j]=removedDuplicateCustomers[j];
                }
                tempRemovedDuplicateCustomers[tempRemovedDuplicateCustomers.length-1]=burger[i].getCustomerID();
                removedDuplicateCustomers=tempRemovedDuplicateCustomers;
            }
        }
        return removedDuplicateCustomers;
    }
    
    public boolean isDuplicated(String[] removedDuplicateCustomers, String customerID) {
        for(int i=0;i<removedDuplicateCustomers.length;i++) {
            if(removedDuplicateCustomers[i].equals(customerID))
                return true;
        } 
        return false;
    }
    
    public double[] totalPurchasedForRelevantCustomers(String[] removedDuplicateCustomers) {
        double[] totalPurchased = new double[removedDuplicateCustomers.length];
        for(int i=0;i<removedDuplicateCustomers.length;i++) {
            for(int j=0;j<burger.length;j++) {
                if(removedDuplicateCustomers[i].equals(burger[j].getCustomerID()))
                    totalPurchased[i]+=burger[j].getBurgerQty()*Burger.BURGERPRICE;
            }
        }
        return totalPurchased;
    }
    
    public String[] sortedTotalPurchasedForRelevantCustomers(String[] removedDuplicateCustomers, double[] totalPurchased) {
        for(int i=totalPurchased.length-1;i>=0;i--) {
            for(int j=0;j<i;j++) {
                if(totalPurchased[j]<totalPurchased[j+1]) {
                    double tempTotalPurchased = totalPurchased[j];
                    totalPurchased[j] = totalPurchased[j+1];
                    totalPurchased[j+1] = tempTotalPurchased;
                    
                    String tempRemovedDuplicateCustomers = removedDuplicateCustomers[j];
                    removedDuplicateCustomers[j] = removedDuplicateCustomers[j+1];
                    removedDuplicateCustomers[j+1] = tempRemovedDuplicateCustomers;
                }
            }
        }
        return removedDuplicateCustomers;
    }
    
    public double[] sortedTotalPurchased(double[] totalPurchased) {
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
    
    public String[] DesOrderedNamesForRelevantCustomers(String[] removedDuplicateCustomers) {
        String[] desOrderedCustomerNames = new String[removedDuplicateCustomers.length];
        for(int i=0;i<removedDuplicateCustomers.length;i++) {
            for(int j=0;j<burger.length;j++) {
                if(removedDuplicateCustomers[i].equals(burger[j].getCustomerID())) {
                    desOrderedCustomerNames[i]=burger[j].getCustomerName();
                }
            }
        }
        return desOrderedCustomerNames;
    }
    
    public Burger extractLatestBurger() {
        return burger[burger.length-1];
    }
    
    public int haveAlreadyPlacedBurger(String key) {
        for(int i=0;i<burger.length;i++) {
            if(burger[i].getOrderID().equalsIgnoreCase(key))
                return i;
        }
        return -1;
    }
    
    public Burger searchedBurgerForRelevantIndex(int index) {
        return burger[index];
    }
    
    public String getStatusInTextMode(int status) {
        String statusInTextMode;
        switch (status) {
            case 0:
                statusInTextMode="PREPARING";
                break;
            case 1:
                statusInTextMode="DELIVERED";
                break;
            case 2:
            default:
                statusInTextMode="CANCELLED";
        }
        return statusInTextMode;
    }
    
    public Burger[] getBurgerReference() {
        return burger;
    }
}
