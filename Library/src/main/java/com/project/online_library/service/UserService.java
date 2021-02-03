package com.project.online_library.service;

import com.project.online_library.dto.FormSubmissionDto;
import com.project.online_library.dto.LoginDto;
import com.project.online_library.dto.UserDTO;
import com.project.online_library.model.*;
import com.project.online_library.repository.*;
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

    @Autowired
    EditorRepository editorRepository;

    @Autowired
    WriterRepository writerRepository;

    @Autowired
    LectorRepository lectorRepository;

    @Autowired
    BoardMemberRepository boardMemberRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        List<FormSubmissionDto> registration = (List<FormSubmissionDto>) delegateExecution.getVariable("registration");
        System.out.println(registration);
        User user = identityService.newUser("");
        for (FormSubmissionDto formField : registration) {
            if (formField.getId().equals("username")) {
                user.setId(formField.getFieldValue().toString());
            }
            if (formField.getId().equals("name")) {
                user.setFirstName(formField.getFieldValue().toString());
            }
            if (formField.getId().equals("surname")) {
                user.setLastName(formField.getFieldValue().toString());
            }
            if (formField.getId().equals("email")) {
                user.setEmail(formField.getFieldValue().toString());
            }
            if (formField.getId().equals("password")) {
                user.setPassword(formField.getFieldValue().toString());
            }
        }
        if(writerRepository.findByUsername(user.getId())!=null) {
        	delegateExecution.setVariable("writer", user.getId());
        }
        identityService.saveUser(user);
    }

    public UserDTO login(LoginDto loginDto) {

        BetaReader betaReader = betaReaderRepository.findByUsername(loginDto.getUsername());
        BoardMember boardMember = boardMemberRepository.findByUsername(loginDto.getUsername());
        Editor editor = editorRepository.findByUsername(loginDto.getUsername());
        Lector lector = lectorRepository.findByUsername(loginDto.getUsername());
        Reader reader = readerRepository.findByUsername(loginDto.getUsername());
        Writer writer = writerRepository.findByUsername(loginDto.getUsername());

        if (betaReader != null) {
            return new UserDTO(betaReader.getUsername(), betaReader.getPassword(), "betaReader");
        } else if (boardMember != null) {
            return new UserDTO(boardMember.getUsername(), boardMember.getPassword(), "boardMember");
        } else if (editor != null) {
            return new UserDTO(editor.getUsername(), editor.getPassword(), "editor");
        } else if (lector != null) {
            return new UserDTO(lector.getUsername(), lector.getPassword(), "lector");
        } else if (reader != null) {
            return new UserDTO(reader.getUsername(), reader.getPassword(), "reader");
        } else if (writer != null) {
            return new UserDTO(writer.getUsername(), writer.getPassword(), "writer");
        } else {
            return null;
        }
    }

}
