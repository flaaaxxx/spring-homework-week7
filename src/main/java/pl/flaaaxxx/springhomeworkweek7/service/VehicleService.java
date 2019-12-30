package pl.flaaaxxx.springhomeworkweek7.service;

import org.springframework.stereotype.Service;
import pl.flaaaxxx.springhomeworkweek7.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {
    List<Vehicle> vehicleList;

    public VehicleService() {
        this.vehicleList = new ArrayList<>();
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public void show(){
        for (Vehicle vehicle : vehicleList) {
            System.out.println(vehicle);
        }
    }
}
