package com.careconnect.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "familiares")
@PrimaryKeyJoinColumn(name = "usuario_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Familiar extends Usuario {

    private String zona;

    @OneToMany(mappedBy = "familiar", cascade = CascadeType.ALL)
    private List<AdultoMayor> adultosACargo = new ArrayList<>();
}