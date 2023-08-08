package pl.Jakub.project.Jakub.EngineerProject.CarParts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.Jakub.project.Jakub.EngineerProject.CarTruck.CarTruck;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarPartsRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<CarParts> searchCarsPart(String name_CarParts, String manufacturer_CarParts, String manufacturer_reference_number_CarParts, String state_CarParts, String minPrice_CarParts, String maxPrice_CarParts){
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM carPart WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (name_CarParts != null) {
            queryBuilder.append(" AND carParts_name = ?");
            params.add(name_CarParts);
        }

        if (manufacturer_CarParts != null) {
            queryBuilder.append(" AND carParts_manufacturer = ?");
            params.add(manufacturer_CarParts);
        }

        if (manufacturer_reference_number_CarParts != null) {
            queryBuilder.append(" AND carParts_manufacturer_reference_number = ?");
            params.add(manufacturer_reference_number_CarParts);
        }

        if (state_CarParts != null) {
            queryBuilder.append(" AND carParts_state = ?");
            params.add(state_CarParts);
        }

        if (minPrice_CarParts != null) {
            queryBuilder.append(" AND carParts_price >= ?");
            params.add(minPrice_CarParts);
        }

        if (maxPrice_CarParts != null) {
            queryBuilder.append(" AND carParts_price <= ?");
            params.add(maxPrice_CarParts);
        }


        return jdbcTemplate.query(queryBuilder.toString(), params.toArray(), BeanPropertyRowMapper.newInstance(CarParts.class));
    }

    public List<CarParts> getAll() {
        return jdbcTemplate.query("SELECT carParts_id, carParts_name, carParts_state, carParts_manufacturer, carParts_manufacturer_reference_number, carParts_photo, carParts_title, carParts_description, carParts_price, carParts_name_user, carParts_town, carParts_phone FROM carPart",
                BeanPropertyRowMapper.newInstance(CarParts.class));
    }

    public CarParts getById(int id) {
        return jdbcTemplate.queryForObject("SELECT carParts_id, carParts_name, carParts_state, carParts_manufacturer, carParts_manufacturer_reference_number, carParts_photo, carParts_title, carParts_description, carParts_price, carParts_name_user, carParts_town, carParts_phone FROM carPart WHERE " +
                "carParts_id = ?", BeanPropertyRowMapper.newInstance(CarParts.class), id);
    }

    public int save(List<CarParts> carParts) {
        carParts.forEach(carPart -> jdbcTemplate.update("INSERT INTO carPart (carParts_id, carParts_name, carParts_state, carParts_manufacturer, carParts_manufacturer_reference_number, carParts_photo, carParts_title, carParts_description, carParts_price, carParts_name_user, carParts_town, carParts_phone) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                carPart.getCarParts_id(), carPart.getCarParts_name(), carPart.getCarParts_state(), carPart.getCarParts_manufacturer(), carPart.getCarParts_manufacturer_reference_number(), carPart.getCarParts_photo(), carPart.getCarParts_title(), carPart.getCarParts_description(), carPart.getCarParts_price(), carPart.getCarParts_name_user(), carPart.getCarParts_town(), carPart.getCarParts_phone()));
        return 1;
    }


    public int update(CarParts carPart) {
        return jdbcTemplate.update("UPDATE carPart SET carParts_name=?, carParts_state=?, carParts_manufacturer=?, carParts_manufacturer_reference_number=?, carParts_photo=?, carParts_title=?, carParts_description=?, carParts_price=?, carParts_name_user=?, carParts_town=?, carParts_phone=? WHERE carPart_id=?",
                carPart.getCarParts_name(), carPart.getCarParts_state(), carPart.getCarParts_manufacturer(), carPart.getCarParts_manufacturer_reference_number(), carPart.getCarParts_photo(), carPart.getCarParts_title(), carPart.getCarParts_description(), carPart.getCarParts_price(), carPart.getCarParts_name_user(), carPart.getCarParts_town(), carPart.getCarParts_phone(), carPart.getCarParts_id());
    }


    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM carPart WHERE carParts_id=?", id);
    }
}
