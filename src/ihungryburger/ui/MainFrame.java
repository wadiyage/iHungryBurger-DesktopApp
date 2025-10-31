package ihungryburger.ui;

import ihungryburger.ui.panels.DashboardPanel;
import ihungryburger.ui.panels.PlaceBurgerPanel;
import ihungryburger.ui.panels.search.SearchBestCustomerPanel;
import ihungryburger.ui.panels.search.SearchBurgerPanel;
import ihungryburger.ui.panels.search.SearchCustomerPanel;
import ihungryburger.ui.panels.search.SearchPanel;
import ihungryburger.ui.panels.update.UpdateBurgerQTYPanel;
import ihungryburger.ui.panels.update.UpdateBurgerStatusPanel;
import ihungryburger.ui.panels.view.CanceledPanel;
import ihungryburger.ui.panels.view.DeliveredPanel;
import ihungryburger.ui.panels.view.PreparingPanel;
import ihungryburger.ui.panels.view.ViewBurgersPanel;
import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private int mouseX, mouseY;
    
    public MainFrame() {
        setUndecorated(true);
        setTitle("iHungryBurger Management");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setPreferredSize(new java.awt.Dimension(810, 390));
        
        mainPanel.add(new DashboardPanel(this), "dashboard");
        mainPanel.add(new PlaceBurgerPanel(this), "place");
        
        mainPanel.add(new SearchPanel(this), "search");
        mainPanel.add(new SearchBurgerPanel(this), "searchBurger");
        mainPanel.add(new SearchCustomerPanel(this), "searchCustomer");
        mainPanel.add(new SearchBestCustomerPanel(this), "searchBestCustomer");
        
        mainPanel.add(new UpdateBurgerStatusPanel(this), "updateStatus");
        mainPanel.add(new UpdateBurgerQTYPanel(this), "updateQTY");
        
        mainPanel.add(new ViewBurgersPanel(this), "viewBurgers");
        mainPanel.add(new PreparingPanel(this), "preparing");
        mainPanel.add(new DeliveredPanel(this), "delivered");
        mainPanel.add(new CanceledPanel(this), "canceled");
        
        add(mainPanel);
        cardLayout.show(mainPanel, "dashboard");
        
        pack();
        setLocationRelativeTo(null);
        
        mainPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                mouseX = e.getX();
                mouseY = e.getY();
            }
            
        });
        
        mainPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int newX = getX() + e.getX() - mouseX;
                int newY = getY() + e.getY() - mouseY;
                setLocation(newX, newY);
            }
            
        });
    }
    
    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
    
    
}
