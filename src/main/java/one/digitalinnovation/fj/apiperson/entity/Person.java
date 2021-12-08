package one.digitalinnovation.fj.apiperson.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

// ver comentarios sobre as anotações
// na classe Phone

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firsName;

    @Column(nullable = false)
    private String lastName;

    // so cadastrar cpf unicos
    @Column(nullable = false , unique = true)
    private String cpf;

    private LocalDate birthDate;

    // JPA disponibiliza a anotao de relacionamentos ( 1,N , n,n  , etc )
    // no banco de dados sera criada uma tabela intermediaria
    // para tratar do relacionamento
    @OneToMany(fetch =FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE} )
    private List<Phone> phones;


}
