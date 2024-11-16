package edu.bbte.softeng.bmim2214.dao;

import edu.bbte.softeng.bmim2214.model.MaintenanceModel;
import edu.bbte.softeng.bmim2214.exception.MaintenanceNoId;

import java.util.List;

public interface MaintenanceDao {
    void createMaintenance(MaintenanceModel maintenance);

    void deleteMaintenance(long id) throws MaintenanceNoId;

    void updateMaintenance(MaintenanceModel maintenance) throws MaintenanceNoId;

    MaintenanceModel readMaintenance(long id) throws MaintenanceNoId;

    List<MaintenanceModel> getAllMaintenances();

}
