package pl.flaaaxxx.springhomeworkweek7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.flaaaxxx.springhomeworkweek7.dao.VehicleDaoImpl;
import pl.flaaaxxx.springhomeworkweek7.model.Vehicle;
import pl.flaaaxxx.springhomeworkweek7.service.VehicleService;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class VehicleController {

    private VehicleDaoImpl vehicleDaoImpl;

    @Autowired
    public VehicleController(VehicleDaoImpl vehicleDaoImpl) {
        this.vehicleDaoImpl = vehicleDaoImpl;
    }

    @GetMapping("/show")
    public String showVehicle(Model model){
        model.addAttribute("vehicleList", vehicleDaoImpl.findAll());
        return "allVehicles";
    }

    @GetMapping("/add-vehicle")
    public String addVehicle(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "addVehicle";
    }

    @PostMapping("/add-execute")
    public String addExecute(@ModelAttribute Vehicle vehicle) {
        if (vehicle.getId() == 0)
            vehicleDaoImpl.saveVehicle(vehicle);
        else
            vehicleDaoImpl.updateVehicle(vehicle);
        return "redirect:/show";
    }

    @PostMapping("/delete-vehicle/{id}")
    public String deleteExecute(@PathVariable("id") long id) {
        vehicleDaoImpl.deleteVehicle(id);
        return "redirect:/show";
    }

    @PostMapping(path="/edit-vehicle/{id}")
    public String editVehicle(@PathVariable("id") long id, Model model) {
        model.addAttribute("vehicle", vehicleDaoImpl.getVehicle(id));
        return "addVehicle";
    }

    @PostMapping(path="/search-vehicles", params = {"startDate", "endDate"})
    public String seachVehicles(@RequestParam(value="startDate", required=false) Date startDate,
                                @RequestParam(value="endDate", required=false) Date endDate, Model model){
        model.addAttribute("vehicleList", vehicleDaoImpl.findRangeDate(startDate, endDate));
        return "allVehicles";
    }

//    InitBinder is used to transform data from forms.
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }


}
