package com.project.online_library.camundaServices;

import com.project.online_library.dto.FormSubmissionDto;
import com.project.online_library.model.BookPrototype;
import com.project.online_library.model.Editor;
import com.project.online_library.repository.BookPrototypeRepository;
import com.project.online_library.repository.EditorRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GenerateEditorDelegate implements JavaDelegate {

    @Autowired
    EditorRepository editorRepository;

    @Autowired
    BookPrototypeRepository bookPrototypeRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        // random generisanje editora
        List<Editor> editors = editorRepository.findAll();
        Random rand = new Random(); //instance of random class
        System.out.println(editors.size());
        int int_random = rand.nextInt(editors.size());
        Editor editor = editors.get(int_random);
        System.out.println(editor.getId());

        //dodeljivanje editora knjizi

        List<FormSubmissionDto> form = (List<FormSubmissionDto>)delegateExecution.getVariable("form");
        BookPrototype bookPrototype = bookPrototypeRepository.findByTitle(form.get(0).getFieldValue());
        bookPrototype.setEditor(editor);
        bookPrototypeRepository.save(bookPrototype);
        //setovanje editor variable u provesu
        delegateExecution.setVariable("editorId", editor.getId());

    }


}
