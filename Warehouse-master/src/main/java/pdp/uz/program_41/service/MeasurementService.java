package pdp.uz.program_41.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.program_41.entity.Measurement;
import pdp.uz.program_41.payload.Result;
import pdp.uz.program_41.repository.MeasurementRepository;

import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;


    public Result add(Measurement measurement){
        boolean existsMeasurementByNameAndActive = measurementRepository.existsMeasurementByNameAndActive(measurement.getName(), true);
        if(existsMeasurementByNameAndActive){
            return new Result("Such measurement already exist!",false);
        }
        Measurement measurement1 = new Measurement();
        measurement1.setName(measurement.getName());
        measurementRepository.save(measurement1);
        return new Result("New measurement successfully saved.", true);
    }

    public Result get(int page){
        boolean existsMeasurementByActive = measurementRepository.existsMeasurementByActive(true);
        if(!existsMeasurementByActive){
            return new Result("Measurements not exist yet!", false);
        }
        Pageable pageable = PageRequest.of(page, 10);
        Page<Measurement> page1 = measurementRepository.getMeasurementByActive(true, pageable);
        return new Result(page1);
    }

    public Result getById(Integer id){
        boolean existsMeasurementByIdAndActive = measurementRepository.existsMeasurementByIdAndActive(id, true);
        if(!existsMeasurementByIdAndActive){
            return new Result("Such measurement id not exist!",false);
        }
        Optional<Measurement>  optionalMeasurement = measurementRepository.findById(id);
        return new Result(optionalMeasurement.get());
    }

    public Result edit(Integer id, Measurement measurement){
        boolean existsMeasurementByIdAndActive = measurementRepository.existsMeasurementByIdAndActive(id, true);
        if(!existsMeasurementByIdAndActive){
            return new Result("Such measurement id not exist!", false);
        }
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        Measurement measurement1 = optionalMeasurement.get();
        boolean existsMeasurementByNameAndActive = measurementRepository.existsMeasurementByNameAndActive(measurement.getName(), true);
        if(existsMeasurementByNameAndActive){
            return new Result("Such measurement already exist!",false);
        }
        measurement1.setName(measurement.getName());
        measurementRepository.save(measurement1);
        return new Result("Given measurement successfully edited.", true);
    }

    public Result delete(Integer id){
        boolean existsMeasurementByIdAndActive = measurementRepository.existsMeasurementByIdAndActive(id, true);
        if(!existsMeasurementByIdAndActive){
            return new Result("Such measurement id not exist!", false);
        }
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        Measurement measurement = optionalMeasurement.get();
        measurement.setActive(false);
        measurementRepository.save(measurement);
        return new Result("Given measurement successfully deleted.", true);
    }
}
