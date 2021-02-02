package com.project.online_library.customTypes;

import org.camunda.bpm.engine.impl.form.type.SimpleFormFieldType;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.FileValue;
import org.camunda.bpm.engine.variable.value.TypedValue;
import org.camunda.feel.syntaxtree.Val;

public class FileFormType  extends SimpleFormFieldType {

    private static final String FILE = "file_";
    private String fileType;

    public FileFormType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    protected TypedValue convertValue(TypedValue propertyValue) {
        if (propertyValue instanceof FileValue) {
            return propertyValue;
        } else {
            final Object value = propertyValue.getValue();
            System.out.println("ISPISUJEEEEEM VREDNOOOST OVDEEE: " + value);
            return Variables.stringValue((String) value);
        }
    }

    @Override
    public String getName() {
        return FileFormType.FILE.concat(this.fileType);
    }


    @Override
    public Object convertFormValueToModelValue(Object o) {
        return null;
    }

    @Override
    public String convertModelValueToFormValue(Object o) {
        return null;
    }
}
