package edu.bbte.softeng.bmim2214;

import edu.bbte.softeng.bmim2214.model.MaintenanceModel;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class MaintenanceTest {

    @Test
    void testCreateMaintenance() {

        MaintenanceModel maintenanceModel = new MaintenanceModel();
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        Date today = new Date(year - 1900, localDate.getMonthValue() - 1, localDate.getDayOfMonth());
        maintenanceModel.setDueDate(today);
        assertTrue(maintenanceModel.isMaintenanceDue(today), "The maintenance dueDate should be " + today);

    }
}

