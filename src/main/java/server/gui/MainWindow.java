package server.gui;

import server.algo.CityBuilder;
import server.gui.body.mainPanel;
import server.gui.navBar.navigationBar;
import server.models.Graph;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    JPanel navBar;
    JPanel mainPanel;



    public MainWindow(Graph cityGraph) {
        navBar = new navigationBar();
        mainPanel = new mainPanel(cityGraph);
        ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));

        this.setSize(800, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(logo.getImage());
        this.setVisible(true);

        this.add(navBar, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);


    }
    public static void main(String[] args) {
        new MainWindow(CityBuilder.build_city_Grid(5,4, 30, (short) 2, 15, (short) 1));
    }
}
