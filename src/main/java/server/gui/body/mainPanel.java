package server.gui.body;

import server.gui.myColors;
import server.models.Graph;
import server.models.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// the body of the aplication
public class mainPanel extends JPanel {
    public CityMap cityMap;

    public mainPanel(Graph cityGraph, ArrayList<Vehicle> vehicles) {
        this.setBackground(myColors.primaryColor);
        this.setLayout(new BorderLayout());

        //adds city map
        cityMap = new CityMap(cityGraph, vehicles);
        JPanel mapWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        mapWrapper.setOpaque(false);
        JPanel cityMapCard = new JPanel();
        cityMapCard.setLayout(new BorderLayout());
        cityMapCard.setBackground(Color.WHITE);
        cityMapCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        cityMapCard.add(cityMap, BorderLayout.CENTER);
        mapWrapper.add(cityMapCard);
        mapWrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mapWrapper.setBackground(new Color(255, 255, 255, 230));
        mapWrapper.setLayout(new GridBagLayout());



        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);

        this.add(mapWrapper, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

    }
}
