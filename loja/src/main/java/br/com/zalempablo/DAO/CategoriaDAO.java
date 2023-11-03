package br.com.zalempablo.DAO;

import br.com.zalempablo.model.Categoria;

import javax.persistence.EntityManager;

public class CategoriaDAO {
    private EntityManager entity;

    public CategoriaDAO(EntityManager entity) {
        this.entity = entity;
    }

    public void cadastrar(Categoria categoria){
        this.entity.persist(categoria);
    }

    public void atualizar(Categoria categoria){
        this.entity.merge(categoria);
    }

    public void remover(Categoria categoria){
        categoria = entity.merge(categoria);
        this.entity.remove(categoria);
    }
}
