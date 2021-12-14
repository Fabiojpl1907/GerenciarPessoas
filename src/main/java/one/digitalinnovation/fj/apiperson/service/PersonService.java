package one.digitalinnovation.fj.apiperson.service;


import lombok.AllArgsConstructor;
import one.digitalinnovation.fj.apiperson.dto.request.PersonDTO;
import one.digitalinnovation.fj.apiperson.dto.response.MessageResponseDTO;
import one.digitalinnovation.fj.apiperson.entity.Person;
import one.digitalinnovation.fj.apiperson.exception.PersonNotFoundException;
import one.digitalinnovation.fj.apiperson.mapper.PersonMapper;
import one.digitalinnovation.fj.apiperson.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


// avisa ao spring que esta classe ira gerenciar todos as
// regras de negocio de nossa aplicação

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    // Substituido por @AllArgs....
/*
    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
*/

    // codigo repetido em findById e Delete
    // foi extraido via refactor e criado um metodo
    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }


    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);
        // convertar todo DTO em uma unica entidade
        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }


    // metodo para listar todas as pessoas
    public List<PersonDTO> lastAll() {
        // metodo para listar todo mundo
        List<Person> allPeople = personRepository.findAll();

        // converter o objeto allPeple em um objeto PersonDTO
        // chamar a api stream() usada para transformação e manipulação de listas
        // map() mapeia daca um dos registr de allPeople
        return allPeople.stream().map(personMapper::toDTO).collect(Collectors.toList());
    }


    // diferença entre melhoria e refatorar
    // para refator devo ter teste unitario para garantor que
    // ao alterar o codigo , não quebrei a aplicação
    public PersonDTO findById(Long id) throws PersonNotFoundException  {
        Person person = verifyIfExists(id);
        return personMapper.toDTO( person );
    }

    public void delete(Long id) throws PersonNotFoundException  {
            verifyIfExists(id);
            personRepository.deleteById(id);
    }

    // pareceido com criar pessoa
    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);
        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Updated person with ID ");
    }

}
