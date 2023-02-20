package io.obouh.foo.bar.fooserver.repositories;

import io.obouh.foo.bar.fooserver.domains.Foo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooRepository extends JpaRepository<Foo, Long> {
}
