package com.uce.edu.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;

import com.uce.edu.demo.ProyectoGrupalApplication;
import com.uce.edu.demo.repository.modelo.Empleado;

@Repository
@Transactional
public class EmpleadoRepositoryImpl implements IEmpleadoRepository {

	private static Logger LOG = Logger.getLogger(ProyectoGrupalApplication.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override

	public void insertar(Empleado e) {
		this.entityManager.persist(e);

	}

	@Override

	public Empleado buscar(Integer id) {

		return this.entityManager.find(Empleado.class, id);
	}

	@Override

	public void actualizar(Empleado e) {
		this.entityManager.merge(e);
	}

	@Override

	public void eliminar(Integer id) {
		Empleado em = this.buscar(id);
		this.entityManager.remove(em);

	}

}
