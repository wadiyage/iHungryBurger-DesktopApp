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
public class BurgerList {
    private Node first;
    
    public void add(Burger burger) {
        add(size(), burger);
    }
    
    public void addFirst(Burger burger) {
        add(0, burger);
    }
    
    public void addLast(Burger burger) {
        add(burger);
    }
    
    public void add(int index, Burger burger) {
        if(index>=0 && index<=size()) {
            Node newNode = new Node(burger);
            if(index==0) {
                newNode.next=first;
                first=newNode;
            } else {
                int count=0;
                Node temp = first;
                while(count<index-1) {
                    count++;
                    temp=temp.next;
                }
                newNode.next=temp.next;
                temp.next = newNode;
            }
        }
    }

    private int size() {
        int count=0;
        Node temp = first;
        while(temp!=null) {
            count++;
            temp=temp.next;
        }
        return count;
    }
    
    public void printBurgers() {
        Node temp = first;
        
        System.out.print("[");
        while(temp!=null) {
            System.out.print(temp.burger.getBurgerID()+",");
            temp=temp.next;
        }
        System.out.print("\b]");
    }
    
    public void remove() {
        remove(size()-1);
    }
    
    public void removeFirst() {
        remove(0);
    }
    
    public void removeLast() {
        remove();
    }
    
    public void remove(int index) {
        if(index>=0 && index<size()) {
            if(index==0) {
                first = first.next;
            } else {
                int count=0;
                Node temp = first;
                while(count<index-1) {
                    count++;
                    temp=temp.next;
                }
                temp.next = temp.next.next;
            }
        }
    }
    
    public class Node {
        private Burger burger;
        private Node next;
        
        public Node(Burger burger) {
            this.burger = burger;
        }
    }
}
