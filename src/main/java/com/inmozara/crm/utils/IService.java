package com.inmozara.crm.utils;

import com.inmozara.crm.config.MensajeDTO;

import java.util.List;

public interface IService<T,ID> {

    T save(T t);

    T update(T t);

    MensajeDTO delete(ID id);

    T find(ID id);

    List<T> findAll();
}
