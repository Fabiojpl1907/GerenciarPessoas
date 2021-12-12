package one.digitalinnovation.fj.apiperson.mapper;

import one.digitalinnovation.fj.apiperson.dto.request.PersonDTO;
import one.digitalinnovation.fj.apiperson.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.Mapping;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target= "birthDate", source= "birthDate" ,dateFormat = "dd-MM-yyy")

    // toModel para converter de objeto para banco de dados
    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}