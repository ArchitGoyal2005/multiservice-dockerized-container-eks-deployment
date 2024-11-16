package edu.bbte.softeng.bmim2214.presentation;

import edu.bbte.softeng.bmim2214.dao.MaintenanceMemoryDB;
import edu.bbte.softeng.bmim2214.exception.MaintenanceNoId;
import edu.bbte.softeng.bmim2214.model.MaintenanceModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaintenanceFrontend {

    private final MaintenanceMemoryDB maintenanceMemoryDB;
    private final Logger log = LoggerFactory.getLogger(MaintenanceFrontend.class);


    public MaintenanceFrontend(MaintenanceMemoryDB maintenanceMemoryDB) {
        this.maintenanceMemoryDB = maintenanceMemoryDB;
    }

    public void display() {
        JFrame frame = new JFrame("Maintenance");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton addMaintenanceButton = new JButton("Add Maintenance");
        JButton updateMaintenanceButton = new JButton("Update Maintenance");
        JButton deleteMaintenanceButton = new JButton("Delete Maintenance");
        JButton getMaintenanceButton = new JButton("Search Maintenance");
        JButton listMaintenancesButton = new JButton("List Maintenance");

        frame.setLayout(new FlowLayout());
        frame.add(addMaintenanceButton);
        frame.add(updateMaintenanceButton);
        frame.add(deleteMaintenanceButton);
        frame.add(getMaintenanceButton);
        frame.add(listMaintenancesButton);

        addMaintenanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMaintenance();
            }
        });

        updateMaintenanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateMaintenance();
                } catch (MaintenanceNoId ex) {
                    JOptionPane.showMessageDialog(null, "Id does not exist");
                    log.warn("Id does not exist");
                }
            }
        });

        deleteMaintenanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteMaintenance();
                } catch (MaintenanceNoId ex) {
                    JOptionPane.showMessageDialog(null, "Id does not exist");
                    log.warn("Id does not exist");
                }
            }
        });

        getMaintenanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    log.warn(getMaintenance());
                } catch (MaintenanceNoId ex) {
                    JOptionPane.showMessageDialog(null, "Id does not exist");
                    log.warn("Id does not exist");
                }
            }
        });
        listMaintenancesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listMaintenances();
            }
        });

        frame.setVisible(true);
    }

    private void addMaintenance() {
        JRadioButton activeTrue = new JRadioButton("True");
        JRadioButton activeFalse = new JRadioButton("False");
        ButtonGroup activeGroup = new ButtonGroup();
        activeGroup.add(activeTrue);
        activeGroup.add(activeFalse);

        JSpinner dueSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dueSpinner, "yyyy-MM-dd");
        dueSpinner.setEditor(dateEditor);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Active Status:"));
        panel.add(activeTrue);
        panel.add(activeFalse);
        panel.add(new JLabel("Due Date:"));
        panel.add(dueSpinner);

        activeFalse.setSelected(true);

        int result = JOptionPane.showConfirmDialog(null, panel, "Create Maintenance", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            MaintenanceModel maintenance = new MaintenanceModel();
            maintenance.setActive(activeTrue.isSelected());
            maintenance.setDueDate(new java.sql.Date(((java.util.Date) dueSpinner.getValue()).getTime()));

            maintenanceMemoryDB.createMaintenance(maintenance);
            JOptionPane.showMessageDialog(null, "Maintenance created successfully!");
        }
    }


    private void updateMaintenance() throws MaintenanceNoId {
        JPanel panel = new JPanel();
        JTextField idField = new JTextField(12);
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        JRadioButton activeTrue = new JRadioButton("True");
        panel.add(new JLabel("Active Status:"));
        panel.add(activeTrue);
        JRadioButton activeFalse = new JRadioButton("False");
        activeFalse.setSelected(true);
        panel.add(activeFalse);
        panel.add(new JLabel("Due Date:"));
        JSpinner dueSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dueSpinner, "yyyy-MM-dd");
        dueSpinner.setEditor(dateEditor);
        panel.add(dueSpinner);

        int result = JOptionPane.showConfirmDialog(null, panel, "Update Maintenance", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            MaintenanceModel maintenance = new MaintenanceModel();

            long id;
            try {
                id = Long.parseLong(idField.getText());
            } catch (NumberFormatException e) {
                throw new MaintenanceNoId("Id does not exist");
            }
            maintenance.setMaintenanceId(id);
            maintenance.setActive(activeTrue.isSelected());
            maintenance.setDueDate(new java.sql.Date(((java.util.Date) dueSpinner.getValue()).getTime()));

            maintenanceMemoryDB.updateMaintenance(maintenance);
            JOptionPane.showMessageDialog(null, "Maintenance update successfully!");
        }

    }

    private void deleteMaintenance() throws MaintenanceNoId {
        JTextField idField = new JTextField(12);

        JPanel panel = new JPanel();
        panel.add(new JLabel("ID:"));
        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Delete Maintenance", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            long id;
            try {
                id = Long.parseLong(idField.getText());
            } catch (NumberFormatException e) {
                throw new MaintenanceNoId("Id does not exist");
            }

            maintenanceMemoryDB.deleteMaintenance(id);
            JOptionPane.showMessageDialog(null, "Maintenance deleted successfully!");
        }
    }

    private String getMaintenance() throws MaintenanceNoId {
        JTextField idField = new JTextField(12);

        JPanel panel = new JPanel();
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        int result = JOptionPane.showConfirmDialog(null, panel, "Search Maintenance", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            long id;
            try {
                id = Long.parseLong(idField.getText());
            } catch (NumberFormatException e) {
                throw new MaintenanceNoId("Id does not exist");
            }
            String maintenanceStr = maintenanceMemoryDB.readMaintenance(id).toString();
            JOptionPane.showMessageDialog(null, maintenanceStr, "Result", JOptionPane.INFORMATION_MESSAGE);
            return maintenanceStr;
        }
        return "There is no maintenance";
    }

    private void listMaintenances() {
        StringBuilder maintenancesList = new StringBuilder();
        for (MaintenanceModel car : maintenanceMemoryDB.getAllMaintenances()) {
            maintenancesList.append(car.toString()).append('\n');
        }
        JOptionPane.showMessageDialog(null, maintenancesList.toString(),
                "Maintenance List", JOptionPane.INFORMATION_MESSAGE);
    }

}
