package com.example.demo53.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo53.Trainings;
import com.example.demo53.repository.TrainingsRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TrainingsController {
       
       @Autowired
       TrainingsRepository trainingrepo;
       
       @GetMapping(path="/trainings")
       public List<Trainings> getAllCustomers() {
              System.out.println("Get all Customers...");
        
              List<Trainings> trainings = new ArrayList<>();
              
              trainingrepo.findAll().forEach(trainings::add);

              return trainings;
       }
       
       
       @PostMapping(path = "/trainings/create")
       public Trainings postCustomer(@RequestBody Trainings training) {
                   
              Trainings _training = trainingrepo.save(new Trainings(training.getId(),training.getStatus(),training.getProgress(),
                      training.getRating(),training.getAmountReceived(),training.getStartDate(),training.getEndDate(),
                      training.getStartTime(),training.getEndTime(),training.getMentor(),training.getSkills(),training.getUser()));
              return _training;
       }
       
       @GetMapping(path="/propose/{id}")
       public ResponseEntity<Trainings> updateTrainings(@PathVariable("id") long id, @RequestBody Trainings training) {
              
              System.out.println("inside propose");
              System.out.println("Update Trainings with id = " + id + "...");

              Optional<Trainings> trainingdata = trainingrepo.findById(id);

              if (trainingdata.isPresent()) {
                     Trainings _training = trainingdata.get();
                     _training.setStatus("proposed");
                     return new ResponseEntity<>(trainingrepo.save(_training), HttpStatus.OK);
              } else {
                     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
              }
       }

@GetMapping(path="/approve/{id}")
public ResponseEntity<Trainings> updateTrainingStatus(@PathVariable("id") long id, @RequestBody Trainings training) {
       System.out.println("inside approve");
       System.out.println("Update Trainings with ID = " + id + "...");

       Optional<Trainings> trainingdata = trainingrepo.findById(id);

       if (trainingdata.isPresent()) {
              Trainings _training = trainingdata.get();
              _training.setStatus("approved");
              return new ResponseEntity<>(trainingrepo.save(_training), HttpStatus.OK);
       } else {
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
}      
@GetMapping("/trainingsCompleted")
public List<Trainings> getCompletedTrainings() {
       
    
       List<Trainings> trainings = new ArrayList<>();
       
      // trainingrepo.findCompletedTrainings("completed");

       return trainings;
}

@GetMapping(path="/hello")
public String getMapped() {
	return "Hi";
}



}
