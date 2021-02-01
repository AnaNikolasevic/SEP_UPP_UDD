package com.project.online_library.camundaServices;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.online_library.dto.FormSubmissionDto;

@Service
public class CamundaService {
	@Autowired
	private RuntimeService runtimeService;
	
	public void addBoardMemberDecision(String processInstanceId, List<FormSubmissionDto> dto) {
		// TODO Auto-generated method stub
		System.out.println(runtimeService.getVariable(processInstanceId,"boardMemberDecision"));

		System.out.println((List<String>)runtimeService.getVariable(processInstanceId,"boardMemberDecision"));
        if((List<String>)runtimeService.getVariable(processInstanceId,"boardMemberDecision") == null) {
        	ArrayList<String> boardMemberDecision = new ArrayList<String>();
        	boardMemberDecision.add(dto.get(0).getFieldValue());
            runtimeService.setVariable(processInstanceId, "boardMemberDecision", boardMemberDecision);
        } else {
        	List<String> boardMemberDecision = (List<String>)runtimeService.getVariable(processInstanceId,"boardMemberDecision");
        	boardMemberDecision.add(dto.get(0).getFieldValue());
	        runtimeService.setVariable(processInstanceId, "boardMemberDecision", boardMemberDecision);
        }
	}

}
