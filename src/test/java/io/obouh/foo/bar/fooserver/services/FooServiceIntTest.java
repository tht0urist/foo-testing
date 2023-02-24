package io.obouh.foo.bar.fooserver.services;

import io.obouh.foo.bar.fooserver.configuration.TestContainersJpaTestConfiguration;
import io.obouh.foo.bar.fooserver.domains.Foo;
import io.obouh.foo.bar.fooserver.repositories.FooRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

@DataJpaTest(includeFilters = @ComponentScan.Filter(value = FooService.class, type = ASSIGNABLE_TYPE))
public class FooServiceIntTest extends TestContainersJpaTestConfiguration {

    @Autowired
    private FooService fooService;

    @Autowired
    private FooRepository fooRepository;

    @BeforeEach
    public void prepareDataSet(){
        fooRepository.deleteAll(); // Clean database between each test
    }

    @Test
    public void should_greet_by_id(){
        // Given
        Foo foo = new Foo();
        foo.setName("FooName");
        foo = fooRepository.save(foo);
        Long id = foo.getId();

        // When
        String result = fooService.greetById(id);

        // Then
        assert(result).equals("Hi " + foo.getName());
    }

    @Test
    public void should_throw_exception_when_not_found(){
        // Given
        Long id = 999L;

        // When && Then
        assertThrows(EntityNotFoundException.class,()-> fooService.greetById(id));
    }
}
