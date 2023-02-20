package io.obouh.foo.bar.fooserver.web;

import io.obouh.foo.bar.fooserver.domains.Foo;
import io.obouh.foo.bar.fooserver.services.FooService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FooControllerUnitTest {

    private MockMvc mockMvc;

    @InjectMocks
    private FooController fooController;

    @Mock
    private FooService fooService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(fooController);
        this.mockMvc = MockMvcBuilders.standaloneSetup(fooController).build();
    }

    @Test
    public void should_get_greetings_by_id() throws Exception{
        // Given
        Long id = 1L;
        Foo foo = new Foo();
        foo.setName("Oualid");

        when(fooService.greetById(eq(id))).thenReturn("Hi "+foo.getName());

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/hello/"+id))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().string("Hi Oualid"));
    }

    @Test
    public void should_get_greetings_by_name() throws Exception{
        // Given
        String name = "Oualid";

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/hello?name="+name))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().string("Hi Oualid"));
    }
}
