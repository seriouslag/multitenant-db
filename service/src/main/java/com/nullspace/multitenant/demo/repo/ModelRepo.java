package com.nullspace.multitenant.demo.repo;

import com.nullspace.multitenant.demo.models.Model;
import org.springframework.data.repository.CrudRepository;

public interface ModelRepo extends CrudRepository<Model, String> {
}
