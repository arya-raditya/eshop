package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class CarRepository implements ItemRepositoryInterface<Car> {
    private List<Car> carData = new ArrayList<>();

    @Override
    public Car create(Car car) {
        car.setItemId(UUID.randomUUID().toString());
        carData.add(car);
        return car;
    }

    @Override
    public Iterator<Car> findAll() {
        return carData.iterator();
    }

    @Override
    public Car findById(String id) {
        for (Car car : carData) {
            if (car.getItemId().equals(id)) {
                return car;
            }
        }
        return null;
    }

    @Override
    public Car update(String id, Car updatedCar) {
        for (Car car : carData) {
            if (car.getItemId().equals(id)) {
                car.setItemName(updatedCar.getItemName());
                updatedCar.setCarColor(car.getCarColor()); // Assuming you want to update carColor as well
                car.setItemQuantity(updatedCar.getItemQuantity());
                return car;
            }
        }
        return null;
    }

    @Override
    public void delete(String id) {
        carData.removeIf(car -> car.getItemId().equals(id));
    }
}