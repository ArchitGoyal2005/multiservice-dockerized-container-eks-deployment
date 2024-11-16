package edu.bbte.softeng.bmim2214.dao;

import edu.bbte.softeng.bmim2214.model.MaintenanceModel;
import edu.bbte.softeng.bmim2214.exception.MaintenanceNoId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MaintenanceMemoryDB implements MaintenanceDao {

    private final Map<Long, MaintenanceModel> maintenanceDatabase = new ConcurrentHashMap<>();
    private long currentId;
    private final Logger log = LoggerFactory.getLogger(MaintenanceMemoryDB.class);

    @Override
    public void createMaintenance(MaintenanceModel maintenance) {
        currentId++;
        maintenance.setMaintenanceId(currentId);
        maintenanceDatabase.put(currentId, maintenance);
        log.error("Maintenance started");
    }

    @Override
    public void deleteMaintenance(long id) throws MaintenanceNoId {

        if (maintenanceDatabase.containsKey(id)) {
            maintenanceDatabase.remove(id);
        } else {
            throw new MaintenanceNoId("There is no such an id");
        }


    }

    @Override
    public void updateMaintenance(MaintenanceModel maintenance) throws MaintenanceNoId {
        long id = maintenance.getMaintenanceId();
        if (maintenanceDatabase.containsKey(id)) {
            maintenanceDatabase.put(id, maintenance);
        } else {
            throw new MaintenanceNoId("There is no such an id");
        }

    }

    @Override
    public MaintenanceModel readMaintenance(long id) throws MaintenanceNoId {
        if (maintenanceDatabase.containsKey(id)) {
            return maintenanceDatabase.get(id);
        } else {
            throw new MaintenanceNoId("There is no such an id");
        }

    }

    @Override
    public List<MaintenanceModel> getAllMaintenances() {
        return new ArrayList<>(maintenanceDatabase.values());
    }
}
