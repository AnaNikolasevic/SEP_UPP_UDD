package com.project.online_library.camundaServices;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.form.type.AbstractFormFieldType;
import org.springframework.stereotype.Service;

import com.project.online_library.customTypes.MultipleEnumType;

@Service
public class CustomTypeProcessEnginePlugin extends AbstractProcessEnginePlugin{
	public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        if (processEngineConfiguration.getCustomFormTypes() == null) {
            processEngineConfiguration.setCustomFormTypes(new ArrayList<AbstractFormFieldType>());
        }

        List<AbstractFormFieldType> formTypes = processEngineConfiguration.getCustomFormTypes();
        formTypes.add(new MultipleEnumType("genres"));
        formTypes.add(new MultipleEnumType("betaReaders"));
    }
}
