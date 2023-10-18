package vn.com.devmaster.service.managematerial.mapper;

import java.util.List;

public interface EntityMapper<E,D>{
     E toEntity(D d);
     List<E> toEntity(List<D> d);
     D toDto(E e);
     List<D> toDto(List<E> e);

}
