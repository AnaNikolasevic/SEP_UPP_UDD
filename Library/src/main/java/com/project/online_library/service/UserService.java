package com.project.online_library.service;

import com.project.online_library.dto.FormSubmissionDto;
import com.project.online_library.dto.LoginDto;
import com.project.online_library.model.BetaReader;
import com.project.online_library.model.Reader;
import com.project.online_library.model.Users;
import com.project.online_library.repository.BetaReaderRepository;
import com.project.online_library.repository.ReaderRepository;
import com.project.online_library.repository.UserRepository;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements JavaDelegate {

    @Autowired
    IdentityService identityService;

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    BetaReaderRepository betaReaderRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        List<FormSubmissionDto> registration = (List<FormSubmissionDto>) delegateExecution.getVariable("registration");
        System.out.println(registration);
        User user = identityService.newUser("");
        for (FormSubmissionDto formField : registration) {
            if (formField.getId().equals("username")) {
                user.setId(formField.getFieldValue());
            }
            if (formField.getId().equals("name")) {
                user.setFirstName(formField.getFieldValue());
            }
            if (formField.getId().equals("surname")) {
                user.setLastName(formField.getFieldValue());
            }
            if (formField.getId().equals("email")) {
                user.setEmail(formField.getFieldValue());
            }
            if (formField.getId().equals("password")) {
                user.setPassword(formField.getFieldValue());
            }
        }
        identityService.saveUser(user);
    }

    public Users login(LoginDto loginDto) {

        Users user = userRepository.findByUsername(loginDto.getUsername());
        return user;
    }


}
