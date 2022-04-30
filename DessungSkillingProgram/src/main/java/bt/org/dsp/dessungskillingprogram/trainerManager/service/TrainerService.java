package bt.org.dsp.dessungskillingprogram.trainerManager.service;

import bt.org.dsp.dessungskillingprogram.base.BaseService;
import bt.org.dsp.dessungskillingprogram.lib.ResponseMessage;
import bt.org.dsp.dessungskillingprogram.master.dspcenter.DSPCentreDTO;
import bt.org.dsp.dessungskillingprogram.master.dspcenter.IDSPCentre;
import bt.org.dsp.dessungskillingprogram.trainerManager.dto.TrainersDTO;
import bt.org.dsp.dessungskillingprogram.trainerManager.model.Trainers;
import bt.org.dsp.dessungskillingprogram.trainerManager.repository.ITrainerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class TrainerService extends BaseService {
    @Autowired
    private ITrainerRepository iTrainerRepository;
    @Autowired
    private IDSPCentre idspCentre;

    @Transactional
    public ResponseMessage save(String trainerDetails) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TrainersDTO trainersDTO = new ObjectMapper().readValue(trainerDetails, TrainersDTO.class);
        Trainers trainers = new Trainers();
        if (StringUtils.isEmpty(trainerDetails)) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setResponseText("Nothing to save.");
            return responseMessage;
        }
        try{
            trainers.setId(trainersDTO.getId());
//            trainers.setDspCentre(idspCentre.findAllById(trainersDTO.getDspCentreId()));
//            trainers.setTrainingProgramme(iTrainingProgrammeRepository.findAllById(trainersDTO
//                    .getTrainingProgramme()));
            trainers.setTrainerId(trainersDTO.getTrainerId());
            trainers.setTrainerName(trainersDTO.getTrainerName());
            trainers.setTrainerAffiliation(trainersDTO.getTrainerAffiliation());
            trainers.setSex(trainersDTO.getSex());
            trainers.setDateOfJoining(trainersDTO.getDateOfJoining());
            trainers.setDesignation(trainersDTO.getDesignation());
            trainers.setCreatedBy(authentication.getName());
            trainers.setCreatedDate(new Date());
            iTrainerRepository.save(trainers);
            responseMessage.setStatus(SUCCESSFUL_STATUS);
            responseMessage.setText("Saved successfully.");
            return responseMessage;
        }catch (Exception ex){
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setResponseText("Could not save." + ex);
            return responseMessage;
        }
    }

    public List<Trainers> getTrainerList() {
        return iTrainerRepository.findAll();
    }

    @Transactional
    public ResponseMessage deleteTrainerById(Integer id) {
        try {
            iTrainerRepository.deleteTrainerById(id);
        } catch (Exception ex) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Trainer couldn't be deleted.");
        }
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Trainer deleted successfully.");
        return responseMessage;
    }

}
