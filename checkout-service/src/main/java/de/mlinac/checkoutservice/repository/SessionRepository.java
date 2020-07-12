package de.mlinac.checkoutservice.repository;

//import com.netflix.discovery.shared.transport.decorator.SessionedEurekaHttpClient;
import de.mlinac.checkoutservice.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

    Session findById(int id);
}
