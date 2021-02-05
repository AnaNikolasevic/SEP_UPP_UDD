package com.project.online_library.camundaServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.TypedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.online_library.dto.FormSubmissionDto;

@Service
public class CamundaService {
	@Autowired
	private RuntimeService runtimeService;
	
	public void makeAndFillList(String processInstanceId, String value, String variableName) {
		// TODO Auto-generated method stub

        if((List<String>)runtimeService.getVariable(processInstanceId,variableName) == null) {
        	ArrayList<String> list = new ArrayList<String>();
        	list.add(value);
            runtimeService.setVariable(processInstanceId, variableName, list);
        } else {
        	List<String> list = (List<String>)runtimeService.getVariable(processInstanceId,variableName);
        	list.add(value);
	        runtimeService.setVariable(processInstanceId, variableName, list);
        }
	}

	public void makeAndFillMap(String processInstanceId, List<FormSubmissionDto> dto, String variableName) {
		// TODO Auto-generated method stub
        if((Map<String, String>)runtimeService.getVariable(processInstanceId,variableName) == null) {
        	Map<String, String> map = new LinkedHashMap<String, String>();
        	map.put(dto.get(0).getFieldValue().toString(), dto.get(0).getFieldValue().toString());
            runtimeService.setVariable(processInstanceId, variableName, map);
        } else {
        	Map<String, String> map = (Map<String, String>)runtimeService.getVariable(processInstanceId,variableName);
        	map.put(dto.get(0).getFieldValue().toString(), dto.get(0).getFieldValue().toString());
        	runtimeService.setVariable(processInstanceId, variableName, map);
        }
	}

}
