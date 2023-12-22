package com.nisum.service;

import java.util.List;

public interface ICrud<T, ID> {

  T registrar(T obj);

  T modificar(T obj);

  List<T> listar();

  T listarPorId(ID obj);

  void eliminar(ID obj);
}
