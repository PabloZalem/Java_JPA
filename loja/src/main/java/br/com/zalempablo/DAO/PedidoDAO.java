package br.com.zalempablo.DAO;

import br.com.zalempablo.VO.RelatorioDeVendas;
import br.com.zalempablo.model.Pedido;
import br.com.zalempablo.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class PedidoDAO {
    private EntityManager entity;

    public PedidoDAO(EntityManager entity) {
        this.entity = entity;
    }

    public void cadastrar(Pedido pedido){
        this.entity.persist(pedido);
    }

    public BigDecimal valorTotalDeVenda(){
        String jqpl = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return entity.createQuery(jqpl, BigDecimal.class)
                .getSingleResult();
    }

    public List<RelatorioDeVendas> relatorioDeVendas(){
        String jqpl = "SELECT new br.com.zalempablo.VO.RelatorioDeVendas(" +
                "  produto.nome" +
                ", SUM(item.quantidade) as quantidade" +
                ", MAX(pedido.data))" +
                " FROM Pedido pedido" +
                " JOIN pedido.produtos item " +
                " JOIN item.produto produto " +
                " GROUP BY produto.nome " +
                " ORDER BY item.quantidade DESC";
        return entity.createQuery(jqpl, RelatorioDeVendas.class).getResultList();
    }

    public Pedido buscarPedidoDoCliente(Long id){
        return entity.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id",Pedido.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate data){
        CriteriaBuilder criteriaBuilder = entity.getCriteriaBuilder();
        CriteriaQuery<Produto> query = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);

        Predicate filtros = criteriaBuilder.and();
        if(nome != null && !nome.trim().isEmpty()){
            filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("nome"),nome));
        }
        if(preco != null){
            filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("preco"),preco));
        }
        if(data != null){
            filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("data"),data));
        }
        query.where(filtros);
        return entity.createQuery(query).getResultList();
    }
}
