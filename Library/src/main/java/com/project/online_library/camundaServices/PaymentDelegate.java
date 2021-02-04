package com.project.online_library.camundaServices;
import java.util.ArrayList;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.online_library.model.BoardMember;
import com.project.online_library.model.Writer;
import com.project.online_library.repository.BoardMemberRepository;
import com.project.online_library.repository.MandatoryBookRepository;
import com.project.online_library.repository.WriterRepository;


@Service
public class PaymentDelegate implements JavaDelegate{

	@Autowired
	WriterRepository writerRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		int seconds = 12;

        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        String username = (String) execution.getVariable("writer");
        Writer writer = writerRepository.findByUsername(username);
        //writer.setActiveAccount(true);
        //writerRepository.save(writer);
	}

}
