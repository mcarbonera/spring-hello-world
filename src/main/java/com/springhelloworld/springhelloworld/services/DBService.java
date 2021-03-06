package com.springhelloworld.springhelloworld.services;

import com.springhelloworld.springhelloworld.domain.*;
import com.springhelloworld.springhelloworld.enums.EstadoPagamento;
import com.springhelloworld.springhelloworld.enums.TipoCliente;
import com.springhelloworld.springhelloworld.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    EstadoRepository estadoRepository;
    @Autowired
    CidadeRepository cidadeRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    PagamentoRepository pagamentoRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    public void instantiateTestDatabase() throws ParseException {
        /* RELACIONAMENTO CATEGORIA PRODUTO */
        Categoria cat1 = new Categoria(null, "Categoria---1");
        Categoria cat2 = new Categoria(null, "Categoria---2");
        Categoria cat3 = new Categoria(null, "Categoria---3");
        Categoria cat4 = new Categoria(null, "Categoria---4");
        Categoria cat5 = new Categoria(null, "Categoria---5");
        Categoria cat6 = new Categoria(null, "Categoria---6");
        Categoria cat7 = new Categoria(null, "Categoria---7");

        Produto p1 = new Produto(null, "Produto 1",2000.00);
        Produto p2 = new Produto(null, "Produto 2",800.00);
        Produto p3 = new Produto(null, "Produto 3",80.00);
        Produto p4 = new Produto(null, "Produto 4",300.00);
        Produto p5 = new Produto(null, "Produto 5",50.00);
        Produto p6 = new Produto(null, "Produto 6",200.00);
        Produto p7 = new Produto(null, "Produto 7",1200.00);
        Produto p8 = new Produto(null, "Produto 8",800.00);
        Produto p9 = new Produto(null, "Produto 9",100.00);
        Produto p10 = new Produto(null, "Produto 10",180.00);
        Produto p11 = new Produto(null, "Produto 11",90.00);

        cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProdutos().addAll(Arrays.asList(p2,p4));
        cat3.getProdutos().addAll(Arrays.asList(p5,p6));
        cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
        cat5.getProdutos().addAll(Arrays.asList(p8));
        cat6.getProdutos().addAll(Arrays.asList(p9,p10));
        cat7.getProdutos().addAll(Arrays.asList(p11));

        p1.getCategorias().addAll(Arrays.asList(cat1,cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1,cat2,cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1,cat4));

        p4.getCategorias().addAll(Arrays.asList(cat2));
        p5.getCategorias().addAll(Arrays.asList(cat3));
        p6.getCategorias().addAll(Arrays.asList(cat3));
        p7.getCategorias().addAll(Arrays.asList(cat4));
        p8.getCategorias().addAll(Arrays.asList(cat5));
        p9.getCategorias().addAll(Arrays.asList(cat6));
        p10.getCategorias().addAll(Arrays.asList(cat6));
        p11.getCategorias().addAll(Arrays.asList(cat7));

        categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
        produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));

        /* RELACIONAMENTO CIDADE ESTADO */
        Estado est1 = new Estado(null,"Minas Gerais");
        Estado est2 = new Estado(null,"São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2,c3));

        estadoRepository.saveAll(Arrays.asList(est1,est2));
        cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));

        /* RELACIONAMENTO CLIENTE ENDERECO */
        Cliente cli1 = new Cliente(null, "Maria Silva", "marcelocarbonera@live.com", "1234567890986", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("1234564321","123455432"));

        Endereco e1 = new Endereco(null,"Rua Flores", "300", "Apto 303", "Jardim", "345676434", cli1, c1);
        Endereco e2 = new Endereco(null,"Avenida Matos", "105", "Sala 800", "Centro", "345765335", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteRepository.saveAll(Arrays.asList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1,e2));

        /* RELACIONAMENTO PEDIDO PAGAMENTO */
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));

        /* RELACIONAMENTO PRODUTO PEDIDO (ITEM_PEDIDO) */
        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1,ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
    }
}
