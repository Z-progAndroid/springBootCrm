package com.inmozara.crm.utils.comon;

import java.util.List;

public interface IService<T,ID> {

    T save(T t);

    T update(T t);

    T delete(ID id);

    T find(ID id);

    List<T> findAll();
}
