package edu.bbte.softeng.bmim2214;

import edu.bbte.softeng.bmim2214.dao.MaintenanceMemoryDB;
import edu.bbte.softeng.bmim2214.presentation.MaintenanceFrontend;

public class Main {
    public static void main(String[] args) {
        MaintenanceMemoryDB maintenanceDao = new MaintenanceMemoryDB();
        MaintenanceFrontend maintenanceFrontend = new MaintenanceFrontend(maintenanceDao);
        maintenanceFrontend.display();
    }

}
