package com.github.gilvangobbato.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "OperationType")
@Table(name = "operations_types")
public class OperationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operation_type_id", nullable = false)
    private Long operationTypeId;

    @Column(name = "description", length = 20, nullable = false)
    private String description;

    @Column(name = "multiplier", nullable = false)
    private Integer multiplier;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationType that = (OperationType) o;
        return operationTypeId.equals(that.operationTypeId) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationTypeId, description);
    }
}
