package one.digitalinnovation.fj.apiperson.service;


import one.digitalinnovation.fj.apiperson.dto.response.MessageResponseDTO;
import one.digitalinnovation.fj.apiperson.entity.Person;
import one.digitalinnovation.fj.apiperson.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
// avisa ao spring que esta classe ira gerenciar todos as
// regras de negocio de nossa aplicação

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person) {
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO.builder().message("Pessoa criada com ID : " + savedPerson.getId()).build();
    }








}
