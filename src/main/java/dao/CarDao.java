package dao;

import entity.Car;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class CarDao {
    @Autowired
    @Getter @Setter
    private JdbcTemplate template;

    public void insertCar(Car carData) {
        template.update("insert into car values("+carData.getId()+","+carData.getYear()+",'"+carData.getMake()+"','"+carData.getModel()+"',"+carData.getSalesPrice()+")");
    }

    public void deleteCar(int carId) {
        template.update("delete from car where id="+carId);
    }

    public List<Car> getCarList() {
        return template.query("select * from car", (resultSet, i) -> {
            Car data = new Car();
            data.setId(resultSet.getInt("id"));
            data.setYear(resultSet.getInt("year"));
            data.setMake(resultSet.getString("make"));
            data.setModel(resultSet.getString("model"));
            data.setSalesPrice(resultSet.getFloat("salesprice"));
            return data;
        });
    }

}
