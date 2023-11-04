package br.com.zalempablo.DAO;

import br.com.zalempablo.model.Cliente;
import br.com.zalempablo.model.Pedido;
import br.com.zalempablo.model.Produto;

import javax.persistence.EntityManager;

public class ClienteDAO {
    private EntityManager entity;

    public ClienteDAO(EntityManager entity) {
        this.entity = entity;
    }

    public void cadastrar(Cliente cliente){
        this.entity.persist(cliente);
    }

    public Cliente buscarPorId(Long id){
        return entity.find(Cliente.class,id);
    }
}
