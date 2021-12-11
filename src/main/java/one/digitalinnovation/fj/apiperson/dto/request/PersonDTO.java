package one.digitalinnovation.fj.apiperson.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

// objeto responsavel por receber os dados de entrada
public class PersonDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String cpf;

    private String birthDate;


    private List<PhoneDTO> phones;
}
