package com.coffee.corner.repositories;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<ID, T> {

  void add(ID id, T item);

  Optional<T> findById(ID id);

  int size();

  List<T> getAll();
}
