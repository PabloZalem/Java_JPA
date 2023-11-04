import br.com.zalempablo.DAO.CategoriaDAO;
import br.com.zalempablo.DAO.ClienteDAO;
import br.com.zalempablo.DAO.PedidoDAO;
import br.com.zalempablo.DAO.ProdutoDAO;
import br.com.zalempablo.VO.RelatorioDeVendas;
import br.com.zalempablo.model.*;
import br.com.zalempablo.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {
    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager entityManager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
        ClienteDAO clienteDAO = new ClienteDAO(entityManager);

        Produto produto = produtoDAO.buscarPorId(1L);
        Cliente cliente = clienteDAO.buscarPorId(1L);

        entityManager.getTransaction().begin();

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));

        PedidoDAO pedidoDAO = new PedidoDAO(entityManager);
        pedidoDAO.cadastrar(pedido);

        entityManager.getTransaction().commit();

        BigDecimal totalVendido = pedidoDAO.valorTotalDeVenda();
        System.out.println(totalVendido);

        List<RelatorioDeVendas> relatorioDeVendasList = pedidoDAO.relatorioDeVendas();
        relatorioDeVendasList.forEach(System.out::println);
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Apple","IOS", new BigDecimal("2500"), celulares);

        Cliente cliente = new Cliente("Pablo", "12345");

        EntityManager entityManager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
        CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);
        ClienteDAO clienteDAO = new ClienteDAO(entityManager);

        entityManager.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);
        clienteDAO.cadastrar(cliente);

        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
