package server.gui.body;

import server.gui.myColors;
import server.models.Graph;

import javax.swing.*;
import java.awt.*;

public class mainPanel extends JPanel {
    public mainPanel(Graph cityGraph) {
        this.setBackground(myColors.primaryColor);
        this.setLayout(new BorderLayout());

        // Create the CityMap and keep it fixed size
        CityMap cityMap = new CityMap(cityGraph);

        // Wrapper to center it using FlowLayout
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
        mapWrapper.setBackground(new Color(255, 255, 255, 230)); // Slight transparency
        mapWrapper.setLayout(new GridBagLayout()); // Perfect center

        // Optional bottom panel for buttons or future UI
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false); // Keep background

        this.add(mapWrapper, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

    }
}
