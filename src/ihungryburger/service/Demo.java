/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ihungryburger.service;

import ihungryburger.model.Burger;

/**
 *
 * @author Dell
 */
public class Demo {
    public static void main(String[] args) {
        BurgerList burgerList = new BurgerList();
    
        Burger burger = new Burger("B0001", "0778051829", "Sandaruwan Wadiyage", 3, Burger.PREPARING);
        burgerList.add(burger);
        
        Burger burger1 = new Burger("B0002", "0778051829", "Sandaruwan Wadiyage", 3, Burger.PREPARING);
        burgerList.add(burger1);
        
        Burger burger2 = new Burger("B0003", "0778051829", "Sandaruwan Wadiyage", 3, Burger.PREPARING);
        burgerList.add(burger2);
        
        // burgerList.printBurgers(); // [B0001,B0002,B0003]
        
        burgerList.remove();
        // burgerList.printBurgers(); // [B0001,B0002]
        
        burgerList.removeFirst();
        burgerList.printBurgers(); // [B0002]
    }
}
