package one.digitalinnovation.fj.apiperson.service;


import one.digitalinnovation.fj.apiperson.dto.request.PersonDTO;
import one.digitalinnovation.fj.apiperson.dto.response.MessageResponseDTO;
import one.digitalinnovation.fj.apiperson.entity.Person;
import one.digitalinnovation.fj.apiperson.mapper.PersonMapper;
import one.digitalinnovation.fj.apiperson.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


// avisa ao spring que esta classe ira gerenciar todos as
// regras de negocio de nossa aplicação

@Service

public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }


    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        // convertar todo DTO em uma unica entidade
        Person savedPerson = personRepository.save(personToSave);

        return MessageResponseDTO
                .builder()
                .message("Pessoa criada com ID : " + savedPerson.getId())
                .build();

    }

    // metodo para listar todas as pessoas
    public List<PersonDTO> lastAll() {
        // metodo para listar todo mundo
        List<Person> allPeople = personRepository.findAll();

        // converter o obbjeto allPeple em um objeto PersonDTO
        // chamar a api stream() usada para transformação e manipulação de listas
        // map() mapeia daca um dos registr de allPeople


        return allPeople.stream().map(personMapper::toDTO).collect(Collectors.toList());
    }
}
