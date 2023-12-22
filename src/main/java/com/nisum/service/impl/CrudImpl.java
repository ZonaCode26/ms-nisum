package com.nisum.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.nisum.repository.IGenericRepository;
import com.nisum.service.ICrud;

public abstract class CrudImpl<T, ID> implements ICrud<T, ID> {

  protected abstract IGenericRepository<T, ID> getRepository();

  @Override
  public T registrar(T obj) {
    // TODO Auto-generated method stub
    return getRepository().save(obj);
  }

  @Override
  public T modificar(T obj) {
    // TODO Auto-generated method stub
    return getRepository().save(obj);
  }

  @Transactional
  @Override
  public List<T> listar() {
    // TODO Auto-generated method stub
    return getRepository().findAll();
  }

  @Transactional
  @Override
  public T listarPorId(ID obj) {
    // TODO Auto-generated method stub
    return getRepository().findById(obj).orElse(null);
  }

  @Override
  public void eliminar(ID obj) {
    // TODO Auto-generated method stub
    getRepository().deleteById(obj);
  }

}
