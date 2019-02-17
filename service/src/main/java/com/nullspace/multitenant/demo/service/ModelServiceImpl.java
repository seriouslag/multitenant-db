package com.nullspace.multitenant.demo.service;

import com.nullspace.multitenant.demo.models.Model;
import com.nullspace.multitenant.demo.repo.ModelRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ModelServiceImpl implements ModelService {
	
	private final ModelRepo modelRepo;
	
	public ModelServiceImpl(ModelRepo modelRepo) {
		this.modelRepo = modelRepo;
	}
	
	@Override
	public Iterable<Model> findAll() {
		return modelRepo.findAll();
	}
	
	@Transactional
	@Override
	public Model save(Model model) {
		return modelRepo.save(model);
	}
}
