package client.gui.body;

import client.ServerConn;
import client.gui.myColors;
import model.Route;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

//main panel of the application.
//shows the main part of the interface of a user
public class mainPanel extends JPanel implements ActionListener {

    public JComboBox<Integer> sourceBox;
    public JComboBox<Integer> destBox;
    public JButton submitButton;
    public ServerConn serverConn;
    public HashMap<String,Integer> gridSize;
    public mainPanel(ServerConn server) {
        serverConn = server;
        try {
            gridSize = serverConn.getGridSize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
        for (int i = 0; i < gridSize.get("GRID_SIZE"); i++) {
            sourceBox.addItem(i);
        }
        sourceBox.setBackground(myColors.secondaryColor);
        sourceBox.setForeground(myColors.accentColor);
        sourceBox.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(sourceBox, gbc);

        //cobox dest
        gbc.gridx = 1;
        destBox = new JComboBox<>();
        for (int i = 0; i < gridSize.get("GRID_SIZE"); i++) {
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
        submitButton.addActionListener(this);
        submitButton.setBackground(myColors.highlightColor);
        submitButton.setForeground(myColors.primaryColor);
        submitButton.setFocusPainted(false);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(submitButton, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton && sourceBox.getSelectedIndex() != destBox.getSelectedIndex()) {
            LinkedList<Route> route;
            try {
                route = serverConn.getRoute(sourceBox.getSelectedIndex(),destBox.getSelectedIndex());
            } catch (IOException ex) {
                System.out.println("error");
                return;
            }
            this.removeAll();


            JPanel scrollContent = new JPanel();
            scrollContent.setLayout(new BoxLayout(scrollContent, BoxLayout.Y_AXIS));
            scrollContent.setBackground(myColors.primaryColor);

            //convert linked list to the diraction route
            convert_toistructions(route,scrollContent);

            JPanel wrapperPanel = new JPanel(new GridBagLayout());
            wrapperPanel.setBackground(myColors.primaryColor);
            wrapperPanel.add(scrollContent);

            JScrollPane scrollPane = new JScrollPane(wrapperPanel);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);

            this.setLayout(new BorderLayout());
            this.add(scrollPane, BorderLayout.CENTER);
            this.revalidate(); // Refresh the layout
            this.repaint();


        }
    }

    //adds a diraction panel based on the information and adds it to the scrollable panel
    private void addDiraction(JPanel scrollContent, ImageIcon imageIcon,String title, String description) {
        JPanel itemPanel = new JPanel(new BorderLayout());
        itemPanel.setBackground(new Color(28, 28, 28));
        itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        itemPanel.setPreferredSize(new Dimension(400, 120));


        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.DARK_GRAY);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        JLabel subtitleLabel = new JLabel(description);
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

        textPanel.add(titleLabel);
        textPanel.add(Box.createRigidArea(new Dimension(0, 4)));
        textPanel.add(subtitleLabel);


        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);

        itemPanel.add(textPanel, BorderLayout.WEST);
        itemPanel.add(imageLabel, BorderLayout.EAST);

        scrollContent.add(itemPanel);
        scrollContent.add(Box.createRigidArea(new Dimension(0, 8)));
    }

    //convert the linked list to diractions and adds the instructions to the scrollable panel
    private void convert_toistructions(LinkedList<Route> route,JPanel scrollContent) {
        Iterator<Route> iterator = route.iterator();
        Iterator<Route> iterator2 = route.iterator();
        iterator2.next();
        int counter = 1;
        char turn = '/';
        ImageIcon[] imageIcon = new ImageIcon[3];
        imageIcon[0] = new ImageIcon(getClass().getResource("/leftTurn.png"));
        imageIcon[1] = new ImageIcon(getClass().getResource("/rightTurn.png"));
        imageIcon[2] = new ImageIcon(getClass().getResource("/straitTurn.png"));
        Route route1=null;
        Route route2;
        boolean found = false;
        while (iterator.hasNext()&&!found) {
            route1 = iterator.next();

            if(!iterator2.hasNext()) {
                found = true;
                continue;
            }
            iterator2.next();
            route2 = iterator2.next();

            if(route1.id%gridSize.get("COLS") == route2.id%gridSize.get("COLS")) {
                if(route1.id>route2.id) {
                    if(turn == '/' || turn == 'N')
                        addDiraction(scrollContent,imageIcon[2],
                                counter+". continue on "+ route2.id%gridSize.get("COLS")+1 + " Ave.",
                                "current node: "+route1.id);
                    if(turn == 'W')
                        addDiraction(scrollContent,imageIcon[1],
                                counter+". turn right to "+ route2.id%gridSize.get("COLS")+1 + " Ave.",
                                "current node: "+route1.id);
                    if(turn == 'E')
                        addDiraction(scrollContent,imageIcon[0],
                                counter+". turn left to "+ route2.id%gridSize.get("COLS")+1 + " Ave.",
                                "current node: "+route1.id);
                    turn = 'N';
                }
                if(route1.id<route2.id) {
                    if(turn == '/' || turn == 'S')
                        addDiraction(scrollContent,imageIcon[2],
                                counter+". continue on "+ route2.id%gridSize.get("COLS")+1 + " Ave.",
                                "current node: "+route1.id);
                    if(turn == 'E')
                        addDiraction(scrollContent,imageIcon[1],
                                counter+". turn right to "+ route2.id%gridSize.get("COLS")+1 + " Ave.",
                                "current node: "+route1.id);
                    if(turn == 'W')
                        addDiraction(scrollContent,imageIcon[0],
                                counter+". turn left to "+ route2.id%gridSize.get("COLS")+1 + " Ave.",
                                "current node: "+route1.id);
                    turn = 'S';
                }

            }
            else if(route1.id<route2.id) {
                if(turn == '/' || turn == 'E')
                    addDiraction(scrollContent,imageIcon[2],
                            counter+". continue on "+ route2.id/gridSize.get("COLS")+1 + " st.",
                            "current node: "+route1.id);
                if(turn == 'N')
                    addDiraction(scrollContent,imageIcon[1],
                            counter+". turn right to "+ route2.id/gridSize.get("COLS")+1 + " st.",
                            "current node: "+route1.id);
                if(turn == 'S')
                    addDiraction(scrollContent,imageIcon[0],
                            counter+". turn left to "+ route2.id/gridSize.get("COLS")+1 + " st.",
                            "current node: "+route1.id);
                turn = 'E';
            }
            else if(route1.id>route2.id) {
                if(turn == '/' || turn == 'W')
                    addDiraction(scrollContent,imageIcon[2],
                            counter+". continue on "+ route2.id/gridSize.get("COLS")+1 + " st.",
                            "current node: "+route1.id);
                if(turn == 'S')
                    addDiraction(scrollContent,imageIcon[1],
                            counter+". turn right to "+ route2.id/gridSize.get("COLS")+1 + " st.",
                            "current node: "+route1.id);
                if(turn == 'N')
                    addDiraction(scrollContent,imageIcon[0],
                            counter+". turn left to "+ route2.id/gridSize.get("COLS")+1 + " st.",
                            "current node: "+route1.id);
                turn = 'W';
            }
            counter++;
            iterator.next();
        }

        addDiraction(scrollContent,new ImageIcon(getClass().getResource("/checkflag.png"))
        ,"got to your destination","final node: " + route1.id);

    }

}
