package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
class CarController implements ItemControllerInterface<Car> {
    @Autowired
    private CarService carservice;

    @GetMapping("/createCar")
    public String createPage(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "CreateCar";
    }

    @PostMapping("/createCar")
    public String createPost(@ModelAttribute Car car, Model model){
        carservice.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String listPage(Model model){
        List<Car> allCars = carservice.findAll();
        model.addAttribute("cars", allCars);
        return "CarList";
    }
    @GetMapping("/editCar/{itemId}")
    public String editPage(@PathVariable String itemId, Model model) {
        Car car = carservice.findById(itemId);
        model.addAttribute("car", car);
        return "EditCar";
    }

    @PostMapping("/editCar")
    public String editPost(@ModelAttribute Car car, Model model) {
        System.out.println(car.getItemId());
        carservice.update(car.getItemId(), car);
        return "redirect:listCar";
    }

    @GetMapping("/deleteCar/{itemId}")
    public String deletePage(@PathVariable String itemId, Model model) {
        Car car = carservice.findById(itemId);
        if (car == null) {
            return "redirect:listCar";
        }
        model.addAttribute("car", car);
        return "deleteCar";
    }

    @PostMapping("/deleteCar")
    public String deletePost(@ModelAttribute Car car) {
        carservice.delete(car.getItemId());
        return "redirect:listCar";
    }
}