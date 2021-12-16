package bt.org.dsp.dessungskillingprogram.trainerManager.controller;

import bt.org.dsp.dessungskillingprogram.lib.ResponseMessage;
import bt.org.dsp.dessungskillingprogram.trainerManager.dto.TrainersDTO;
import bt.org.dsp.dessungskillingprogram.trainerManager.model.Trainers;
import bt.org.dsp.dessungskillingprogram.trainerManager.service.TrainerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(value = "/trainer")
public class TrainerController {
    @Autowired
    private final TrainerService trainerService;

    @PostMapping(value = "/save")
    public ResponseMessage save(@RequestPart("trainerDetails") String trainerDetails)throws JsonProcessingException {
        return trainerService.save(trainerDetails);
    }

    @GetMapping(value = "/list")
    public List<Trainers> getTrainerList(){

        return trainerService.getTrainerList();
    }

}
