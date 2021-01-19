package com.project.online_library.service;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.online_library.dto.FormSubmissionDto;
import com.project.online_library.enums.WriterMembershipStatus;
import com.project.online_library.model.BoardMember;
import com.project.online_library.model.MandatoryBook;
import com.project.online_library.model.Writer;
import com.project.online_library.repository.BoardMemberRepository;
import com.project.online_library.repository.MandatoryBookRepository;
import com.project.online_library.repository.WriterRepository;

@Service
public class MandatoryBookService implements JavaDelegate{

	@Autowired
	WriterRepository writerRepository;
	
	@Autowired
	MandatoryBookRepository mandatoryBookRepository;
	 
	@Autowired
    BoardMemberRepository boardMemberRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		List<FormSubmissionDto> mandatoryBookVariable = (List<FormSubmissionDto>) execution.getVariable("mandatoryBook");
		Writer writer = writerRepository.findByUsername(mandatoryBookVariable.get(2).getFieldValue());

		MandatoryBook mandatoryBook = new MandatoryBook();
        mandatoryBook.setTitle(mandatoryBookVariable.get(0).getFieldValue());
        mandatoryBook.setPath(mandatoryBookVariable.get(1).getFieldValue());
        mandatoryBook.setWriter(writer);
        mandatoryBookRepository.save(mandatoryBook);
        
        ArrayList<MandatoryBook> mandatoryBookList = new ArrayList<MandatoryBook>();
        mandatoryBookList.add(mandatoryBook);
        
        writer.setMandatoryBookList(mandatoryBookList);
        writerRepository.save(writer);
        
        for (BoardMember boardMember : boardMemberRepository.findAll()) {
			boardMember.getWriterMembership().put(writer.getUsername(), WriterMembershipStatus.WAITING);
			boardMemberRepository.save(boardMember);
		}
	}

}
