package com.uce.edu.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Cliente;

@Repository
@Transactional
public class ClienteRepositoryImpl implements IClienteRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override

	public void insertar(Cliente c) {
		this.entityManager.persist(c);

	}

	@Override

	public Cliente buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Cliente.class, id);
	}

	@Override

	public void actualizar(Cliente c) {
		this.entityManager.merge(c);

	}

	@Override

	public void eliminar(Integer id) {
		Cliente c = this.buscar(id);
		this.entityManager.remove(c);

	}

	@Override

	public Cliente buscarPorCedula(String cedula) {
		TypedQuery<Cliente> myQuery = this.entityManager
				.createQuery("SELECT c FROM Cliente c WHERE c.cedula=:datoCedula", Cliente.class);
		myQuery.setParameter("datoCedula", cedula);
		try {
			return myQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	@Override

	public List<Cliente> buscarTodosClientes() {
		TypedQuery<Cliente> myQuery = this.entityManager.createQuery("Select c from Cliente c", Cliente.class);
		return myQuery.getResultList();

	}

	@Override

	public List<Cliente> buscarClientesPorApellido(String apellido) {
		TypedQuery<Cliente> myQuery = this.entityManager.createQuery("Select c from Cliente c WHERE c.apellido=: dato1",
				Cliente.class);
		myQuery.setParameter("dato1", apellido);
		return myQuery.getResultList();
	}

	@Override

	public boolean verificarReserva(Integer id) {
		Cliente cliente = this.buscar(id);
		if (cliente.getReserva().isEmpty()) {
			return false;
		} else {
			return true;
		}

	}
}