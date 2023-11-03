import br.com.zalempablo.DAO.CategoriaDAO;
import br.com.zalempablo.DAO.ProdutoDAO;
import br.com.zalempablo.model.Categoria;
import br.com.zalempablo.model.Produto;
import br.com.zalempablo.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
    public static void main(String[] args) {
        cadastrarProduto();

        EntityManager entityManager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);

        Produto p = produtoDAO.buscarPorId(1L);
        System.out.println(p.getPreco());

        List<Produto> produtos = produtoDAO.buscarTodos();
        produtos.forEach(p2 -> System.out.println(p.getNome()));

        List<Produto> produtos_1 = produtoDAO.buscarPorNome("Apple");
        produtos.forEach(p3 -> System.out.println(p.getNome()));

        List<Produto> produtos_2 = produtoDAO.buscarPorNomeDaCategoria("CELULARES");
        produtos.forEach(p4 -> System.out.println(p.getNome()));

        BigDecimal precoDoProduto = produtoDAO.buscarPrecoDoProdutoComNome("Apple");
        System.out.println(precoDoProduto);
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Apple","IOS", new BigDecimal("2500"), celulares);

        EntityManager entityManager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
        CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);

        entityManager.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);

        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
