package com.github.gilvangobbato.domain.repository;

import com.github.gilvangobbato.domain.entities.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationTypeRepository extends JpaRepository<OperationType, Long> {

}
