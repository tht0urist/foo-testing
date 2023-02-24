package io.obouh.foo.bar.fooserver.repository;

import io.obouh.foo.bar.fooserver.configuration.TestContainersJpaTestConfiguration;
import io.obouh.foo.bar.fooserver.domains.Foo;
import io.obouh.foo.bar.fooserver.repositories.FooRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class FooRepositoryIntTest extends TestContainersJpaTestConfiguration {

    @Autowired
    private FooRepository fooRepository;

    @Test
    public void should_save_foo(){
        // Given
        Foo foo = new Foo();
        foo.setName("Oualid");
        long countBeforeSave = fooRepository.count();

        // When
        foo = fooRepository.save(foo);
        long countAfterSave = fooRepository.count();

        // Then
        assertThat(foo.getId()).isNotNull();
        assertThat(countAfterSave).isEqualTo(countBeforeSave + 1);
    }
}
