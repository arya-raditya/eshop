package id.ac.ui.cs.advprog.eshop.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public Car create(Car car) {
        return carRepository.create(car);
    }

    @Override
    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> carList = new ArrayList<>();
        carIterator.forEachRemaining(carList::add);
        return carList;
    }

    @Override
    public Car findById(String itemId) {
        return carRepository.findById(itemId);
    }

    @Override
    public Car update(String itemId, Car car) {
        return carRepository.update(itemId, car);
    }

    @Override
    public void delete(String itemId) {
        carRepository.delete(itemId);
    }

    @Override
    public void deleteCarById(String itemId) {
        delete(itemId);
    }
}