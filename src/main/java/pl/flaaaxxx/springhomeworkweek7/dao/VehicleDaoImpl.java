package pl.flaaaxxx.springhomeworkweek7.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pl.flaaaxxx.springhomeworkweek7.model.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class VehicleDaoImpl implements VehicleDao {

    private JdbcTemplate jdbcTemplate;

    public VehicleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, null, vehicle.getMark(), vehicle.getModel(), vehicle.getColor(), vehicle.getProductionDate());
    }

    @Override
    public List<Vehicle> findAll() {
        List<Vehicle> vehicleList = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        maps.stream().forEach(element -> {
            try {
                vehicleList.add(new Vehicle(
                        Long.parseLong(String.valueOf(element.get("id"))),
                        String.valueOf(element.get("mark")),
                        String.valueOf(element.get("model")),
                        String.valueOf(element.get("color")),
                        new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(element.get("productionDate")))
                ));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return vehicleList;
    }

    @Override
    public void updateVehicle(Vehicle newVehicle) {
        String sql = "UPDATE vehicles SET id=?, mark=?,  model=?, color=?, productionDate=? WHERE id=?";
        jdbcTemplate.update(sql, newVehicle.getId(), newVehicle.getMark(), newVehicle.getModel(), newVehicle.getColor(), newVehicle.getProductionDate(), newVehicle.getId());
    }

    @Override
    public void deleteVehicle(long id) {
        String sql = "DELETE FROM vehicles WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public Vehicle getVehicle(long id) {
        String sql = "SELECT * FROM vehicles WHERE id=?";
        return jdbcTemplate.queryForObject(sql, (resultSet, i) -> new Vehicle(resultSet.getLong("id"),
                resultSet.getString("mark"),
                resultSet.getString("model"),
                resultSet.getString("color"),
                resultSet.getDate("productionDate")
        ), id);
    }

    @Override
    public List<Vehicle> findRangeDate(Date startDate, Date endDate) {
        List<Vehicle> vehicleList = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE productionDate BETWEEN ? AND ?";

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, startDate, endDate);
        maps.stream().forEach(element -> {
            try {
                vehicleList.add(new Vehicle(
                        Long.parseLong(String.valueOf(element.get("id"))),
                        String.valueOf(element.get("mark")),
                        String.valueOf(element.get("model")),
                        String.valueOf(element.get("color")),
                        new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(element.get("productionDate")))
                ));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return vehicleList;
    }

}
