package io.obouh.foo.bar.fooserver.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import io.obouh.foo.bar.fooserver.domains.Foo;
import io.obouh.foo.bar.fooserver.repositories.FooRepository;
import jakarta.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * @author tdupuy
 * @created 22/02/2023 - 15:04
 * @project foo-testing
 */
@ExtendWith(MockitoExtension.class)
public class FooServiceUnitTest {

    @InjectMocks
    private FooService fooService;

    @Mock
    private FooRepository fooRepository;

    @Test
    public void should_throw_an_exception_when_foo_not_found() {
        // Given
        when(fooRepository.findById(any())).thenReturn(Optional.empty());

        // Then
        assertThrows(EntityNotFoundException.class, () -> fooService.greetById(1L));
    }

    @Test
    public void should_greet_by_id_when_foo_is_found() {
        // Given
        Foo foo = new Foo();
        foo.setId(1L);
        foo.setName(", j'ai un caillou dans ma chaussure.");

        Optional<Foo> optionalFoo = Optional.of(foo);
        when(fooRepository.findById(eq(1L))).thenReturn(optionalFoo);

        String expected = "Hi , j'ai un caillou dans ma chaussure.";

        // When
        String actual = fooService.greetById(1L);

        //Then
        assertEquals(actual, expected);
    }

}
