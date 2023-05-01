package at.fhtw.swen2.tourxultra.service.mapper;

public interface Mapper<E, T> {

    T fromEntity(E entity);

    E toEntity(T dto);

}
