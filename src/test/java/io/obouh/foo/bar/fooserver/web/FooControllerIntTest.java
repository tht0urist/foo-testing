package io.obouh.foo.bar.fooserver.web;

import io.obouh.foo.bar.fooserver.domains.Foo;
import io.obouh.foo.bar.fooserver.services.FooService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FooControllerIntTest {

    private MockMvc mockMvc;

    @MockBean
    private FooService fooService;

    @BeforeEach
    public void init(){
        FooController fooController = new FooController(fooService);
        mockMvc = MockMvcBuilders.standaloneSetup(fooController).build();
    }

    @Test
    public void shoud_get_greetings_using_name() throws Exception{
        // Given
        String name = "Oualid";

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/hello?name="+name))
                // Then
                .andExpect(status().isOk());
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
}
