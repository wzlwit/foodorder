package jac.fsd02.foodorder.service;

import jac.fsd02.foodorder.exception.RecordNotFoundException;
import jac.fsd02.foodorder.model.City;
import jac.fsd02.foodorder.repository.AdminCityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdminCityService {
    @Autowired
    AdminCityRepository repository;

    public List<City> getCityList() {
        return (List<City>) repository.findAll();
    }

    public City getCityById(long id) throws RecordNotFoundException {
        Optional<City> cityInDb =  repository.findById(id);
        if(cityInDb.isPresent()){
            return cityInDb.get();
        }
        else{
            throw new RecordNotFoundException("There is no City");
        }
    }

    public City saveOrUpdateCity(City city) {
        return repository.save(city);
    }

    public void deleteCity(long id) {
        repository.deleteById(id);
    }
}
