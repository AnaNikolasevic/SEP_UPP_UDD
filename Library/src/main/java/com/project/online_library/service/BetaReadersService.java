package com.project.online_library.service;

import com.project.online_library.model.BetaReader;
import com.project.online_library.model.Reader;
import com.project.online_library.repository.BetaReaderRepository;
import com.project.online_library.repository.BookPrototypeRepository;
import com.project.online_library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetaReadersService {

    @Autowired
    BetaReaderRepository betaReaderRepository;

    @Autowired
    ReaderRepository readerRepository;

    public String checkPenaltyPoints(String oneBetaReaderUsername){

        BetaReader betaReader = betaReaderRepository.findByUsername(oneBetaReaderUsername);

        betaReader.setPenaltyPoints(betaReader.getPenaltyPoints() + 1);
        betaReaderRepository.save(betaReader);

        if(betaReader.getPenaltyPoints() == 5){
            Reader reader = new Reader();
            reader.setCity(betaReader.getCity());
            reader.setCountry(betaReader.getCountry());
            reader.setEmail(betaReader.getEmail());
            reader.setFirstName(betaReader.getFirstName());
            reader.setLastName(betaReader.getLastName());
            reader.setPassword(betaReader.getPassword());
            reader.setUsername(betaReader.getUsername());
            reader.setEnabled(betaReader.isEnabled());
            readerRepository.save(reader);
            betaReaderRepository.delete(betaReader);

            return reader.getEmail();
        }

        return null;
    }
}
