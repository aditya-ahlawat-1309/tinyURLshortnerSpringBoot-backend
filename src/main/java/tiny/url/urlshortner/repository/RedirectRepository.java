package tiny.url.urlshortner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tiny.url.urlshortner.entity.Redirect;

import java.util.Optional;

@Repository
public interface RedirectRepository extends JpaRepository<Redirect, Long> {

    Optional<Redirect> findByAlias(String alias);

    Boolean existsByAlias(String alias);
}
