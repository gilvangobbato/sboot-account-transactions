package com.github.gilvangobbato.presentation.mapper;

public interface RepresentationMapper<R, E> {

    R toRepresentation(E entity);

    E toEntity(R representation);

}
