package client.gui;

import client.ServerConn;
import client.gui.body.mainPanel;
import client.gui.navBar.navigationBar;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public navigationBar navBar;
    public mainPanel mainPanel;



    public MainWindow(ServerConn serverConn) {
        navBar = new navigationBar();
        mainPanel = new mainPanel(serverConn);
        ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
        this.setSize(500, 700);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(logo.getImage());
        this.setVisible(true);

        this.add(navBar, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);


    }
}
