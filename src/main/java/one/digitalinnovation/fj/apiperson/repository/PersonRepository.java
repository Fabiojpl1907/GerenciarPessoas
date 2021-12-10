package one.digitalinnovation.fj.apiperson.repository;

import one.digitalinnovation.fj.apiperson.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;


// extender da Spring Data Jpa
// passar a entidade que quermos gerenciar - person
// e o tipo de dados do Id

// com Spring administra a abertura e fechamanto do BD , voce não precisa fazer isto
// bem como as implementação de CRUD

public interface PersonRepository extends JpaRepository<Person,Long> {
}