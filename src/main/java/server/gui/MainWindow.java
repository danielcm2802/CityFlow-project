package server.gui;

import server.algo.CityBuilder;
import server.gui.body.CityMap;
import server.gui.body.mainPanel;
import server.gui.navBar.navigationBar;
import server.models.Graph;
import server.models.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    navigationBar navBar;
    mainPanel mainPanel;
    public CityMap cityMap;


    public MainWindow(Graph cityGraph, ArrayList<Vehicle> vehicles) {
        navBar = new navigationBar();
        mainPanel = new mainPanel(cityGraph, vehicles);
        ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
        cityMap = mainPanel.cityMap;
        this.setSize(800, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(logo.getImage());
        this.setVisible(true);

        this.add(navBar, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);


    }

    public void refreshMap() {
        cityMap.repaint();
    }

//    public static void main(String[] args) {
//        new MainWindow(CityBuilder.build_city_Grid(5,4, 30, (short) 2, 15, (short) 1));
//    }
}
