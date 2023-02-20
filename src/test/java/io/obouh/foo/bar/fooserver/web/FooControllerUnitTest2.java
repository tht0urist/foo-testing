package io.obouh.foo.bar.fooserver.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FooControllerUnitTest2 {

    @InjectMocks
    private FooController fooController;

    @Test
    public void should_get_greetings() {
        // Given
        String name = "Oualid";

        // When
        String result = fooController.helloByName(name);

        // Then
        assert(result).equals("Hi "+name);
    }
}
