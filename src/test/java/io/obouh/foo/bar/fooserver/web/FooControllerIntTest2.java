package io.obouh.foo.bar.fooserver.web;

import io.obouh.foo.bar.fooserver.configuration.TestContainersTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class FooControllerIntTest2 extends TestContainersTestConfiguration {

    private MockMvc mockMvc;

    @Autowired
    private FooController fooController;

    @Test
    public void should_get_greetings() throws Exception{
        // Given
        this.mockMvc = MockMvcBuilders.standaloneSetup(fooController).build();

        String name = "Oualid";

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/hello?name="+name))
                // Then
                .andExpect(status().isOk());
    }
}
