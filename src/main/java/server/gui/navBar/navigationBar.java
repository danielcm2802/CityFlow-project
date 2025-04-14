package server.gui.navBar;

import server.gui.myColors;

import javax.swing.*;
import java.awt.*;

public class navigationBar extends JPanel {
    JLabel navBarLabel;
    public navigationBar() {
        ImageIcon navBarIcon = new ImageIcon(getClass().getResource("/logowhite.png"));

        navBarLabel = new JLabel("CityFlow", JLabel.LEFT);
        navBarLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        navBarLabel.setForeground(Color.WHITE);
        navBarLabel.setIcon(new ImageIcon(navBarIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        navBarLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5)); // Padding

        this.setBackground(myColors.secondaryColor);
        this.setPreferredSize(new Dimension(100, 75));
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(0, 0, 0, 60)));
        this.add(navBarLabel, BorderLayout.WEST);
    }

}
