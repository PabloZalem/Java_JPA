package br.com.zalempablo.DAO;

import br.com.zalempablo.model.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDAO {
    private EntityManager entity;

    public ProdutoDAO(EntityManager entity) {
        this.entity = entity;
    }

    public void cadastrar(Produto produto){
        this.entity.persist(produto);
    }

    public void atualizar(Produto produto){
        this.entity.merge(produto);
    }

    public void remover(Produto produto){
        produto = entity.merge(produto);
        this.entity.remove(produto);
    }

    public Produto buscarPorId(Long id){
        return entity.find(Produto.class,id);
    }

    public List<Produto> buscarTodos(){
        String jpql = " SELECT p FROM Produto p";
        return entity.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nome){
        String jpql = " SELECT p FROM Produto p WHERE p.nome = :nome ";
        return entity.createQuery(jpql, Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome ";
        return entity.createQuery(jpql, Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoComNome(String nome){
        String jpql = " SELECT p.preco FROM Produto p WHERE p.nome = :nome ";
        return entity.createQuery(jpql, BigDecimal.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }
}
