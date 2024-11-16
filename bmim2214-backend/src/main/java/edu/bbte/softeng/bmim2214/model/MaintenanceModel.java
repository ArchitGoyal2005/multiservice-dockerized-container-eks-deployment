package edu.bbte.softeng.bmim2214.model;

import java.sql.Date;
import java.time.LocalDate;

public class MaintenanceModel {

    private long maintenanceId;
    private boolean isActive;
    private Date dueDate;

    public MaintenanceModel() {
    }

    public MaintenanceModel(long maintenanceId, boolean isActive, Date dueDate) {
        this.maintenanceId = maintenanceId;
        this.isActive = isActive;
        this.dueDate = dueDate;
    }


    public long getMaintenanceId() {
        return maintenanceId;
    }

    public boolean isActiveMaintenance() {
        return isActive;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setMaintenanceId(long maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isMaintenanceDue(Date dueDate) {
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        Date today = new Date(year - 1900, localDate.getMonthValue() - 1, localDate.getDayOfMonth());

        return dueDate.equals(today);
    }

    @Override
    public String toString() {
        return "MaintenanceModel{"
                + "maintenanceId=" + maintenanceId
                + ", isActive=" + isActive
                + ", dueDate=" + dueDate
                + '}';
    }
}
