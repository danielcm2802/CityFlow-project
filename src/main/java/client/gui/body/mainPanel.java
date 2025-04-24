package client.gui.body;

import client.ServerConn;
import client.gui.myColors;
import server.Server;
import server.models.Graph;
import server.models.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class mainPanel extends JPanel implements ActionListener {

    public JComboBox<Integer> sourceBox;
    public JComboBox<Integer> destBox;
    public JButton submitButton;
    public ServerConn serverConn;
    public mainPanel(ServerConn server) {
        serverConn = server;
        this.setBackground(myColors.primaryColor);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //source
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        JLabel sourceLabel = new JLabel("Source:");
        sourceLabel.setForeground(myColors.accentColor);
        this.add(sourceLabel, gbc);

        //dest
        gbc.gridx = 1;
        JLabel destLabel = new JLabel("Destination:");
        destLabel.setForeground(myColors.accentColor);
        this.add(destLabel, gbc);

        //combox source
        gbc.gridx = 0;
        gbc.gridy = 1;
        sourceBox = new JComboBox<>();
        for (int i = 0; i <= 20; i++) {
            sourceBox.addItem(i);
        }
        sourceBox.setBackground(myColors.secondaryColor);
        sourceBox.setForeground(myColors.accentColor);
        sourceBox.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(sourceBox, gbc);

        //cobox dest
        gbc.gridx = 1;
        destBox = new JComboBox<>();
        for (int i = 0; i <= 20; i++) {
            destBox.addItem(i);
        }
        destBox.setBackground(myColors.secondaryColor);
        destBox.setForeground(myColors.accentColor);
        destBox.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(destBox, gbc);

        //Submit Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        submitButton = new JButton("Submit");
        submitButton.setBackground(myColors.highlightColor);
        submitButton.setForeground(myColors.primaryColor);
        submitButton.setFocusPainted(false);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(submitButton, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {

        }
    }
}
