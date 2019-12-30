package pl.flaaaxxx.springhomeworkweek7.dao;

import pl.flaaaxxx.springhomeworkweek7.model.Vehicle;

import java.util.Date;
import java.util.List;

public interface VehicleDao {

    void saveVehicle(Vehicle vehicle);
    List<Vehicle> findAll();
    void updateVehicle(Vehicle newVehicle);
    void deleteVehicle(long id);
    Vehicle getVehicle(long id);
    List<Vehicle> findRangeDate(Date startDate, Date endDate);
}
