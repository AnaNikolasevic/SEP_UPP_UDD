package com.project.online_library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.online_library.dto.MandatoryBookDTO;
import com.project.online_library.dto.WriterDTO;
import com.project.online_library.model.MandatoryBook;
import com.project.online_library.model.Writer;
import com.project.online_library.repository.WriterRepository;

@Service
public class WriterService {
	@Autowired
	WriterRepository writerRepository;

	public ArrayList<WriterDTO> getAll() {
		// TODO Auto-generated method stub
		ArrayList<WriterDTO> writersDTO = new ArrayList<WriterDTO>();
		List<Writer> writers = writerRepository.findAll();
		for (Writer writer : writers) {
			ArrayList<MandatoryBookDTO> mandatoryBooksDTO = new ArrayList<MandatoryBookDTO>();
			for (MandatoryBook mandatoryBook : writer.getMandatoryBookList()) {
				MandatoryBookDTO mb = new MandatoryBookDTO(mandatoryBook);
				mandatoryBooksDTO.add(mb);
			}
			WriterDTO writerDTO = new WriterDTO(writer.getId(),
												writer.getFirstName(),
												writer.getLastName(),
												writer.getEmail(),
												writer.getPassword(),
												writer.getUsername(),
												mandatoryBooksDTO);
			writersDTO.add(writerDTO);
		}
		return writersDTO;
	}
}
