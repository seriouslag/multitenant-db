package com.nullspace.multitenant.demo.service;

import com.nullspace.multitenant.demo.models.Model;

public interface ModelService {
	Iterable<Model> findAll();
	Model save(Model model);
}
