package com.coffee.corner.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GenericRepositoryImpl<ID, T> implements GenericRepository<ID, T> {
  private final Map<ID, T> itemsMap = new HashMap<>();

  @Override
  public void add(ID id, T item) {
    this.itemsMap.put(id, item);
  }

  @Override
  public Optional<T> findById(ID id) {
    return Optional.ofNullable(this.itemsMap.get(id));
  }

  @Override
  public int size() {
    return itemsMap.size();
  }

  @Override
  public List<T> getAll() {
    return itemsMap.values().stream().collect(Collectors.toUnmodifiableList());
  }

}
