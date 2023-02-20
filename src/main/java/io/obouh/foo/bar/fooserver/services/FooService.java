package io.obouh.foo.bar.fooserver.services;

import io.obouh.foo.bar.fooserver.domains.Foo;
import io.obouh.foo.bar.fooserver.repositories.FooRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class FooService {

    private final FooRepository fooRepository;

    public FooService(FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    @Transactional(readOnly = true)
    public String greetById(Long id){
        Optional<Foo> foo = fooRepository.findById(id);
        if(foo.isEmpty()){
            throw new EntityNotFoundException();
        }
        return "Hi "+ foo.get().getName();
    }
}
