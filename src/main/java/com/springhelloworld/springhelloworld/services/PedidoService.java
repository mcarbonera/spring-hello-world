package com.springhelloworld.springhelloworld.services;

import com.springhelloworld.springhelloworld.domain.ItemPedido;
import com.springhelloworld.springhelloworld.domain.PagamentoComBoleto;
import com.springhelloworld.springhelloworld.domain.Pedido;
import com.springhelloworld.springhelloworld.enums.EstadoPagamento;
import com.springhelloworld.springhelloworld.exceptions.ObjectNotFoundException;
import com.springhelloworld.springhelloworld.repositories.ItemPedidoRepository;
import com.springhelloworld.springhelloworld.repositories.PagamentoRepository;
import com.springhelloworld.springhelloworld.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

    public Pedido find(Integer id){
        Optional<Pedido> obj = pedidoRepository.findById(id);
        //return obj.orElse(null);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    /*
     *                            _____________________________________________
     *                           |                                             |
     * Produto -------------- Pedido -------------- Cliente -------------- Endereco
     *            |             |
     *       ItemPedido      Pagamento
     *                       /      \
     *                      /        \
     *      PagamentoComBoleto    PagamentoComCartao
     * */
    @Transactional
    public Pedido insert(Pedido obj) {
        /* pedido */
        obj.setId(null);
        obj.setInstante(new Date());
        /* cliente */
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        /* pagamento*/
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if(obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = pedidoRepository.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        /* ItemPedido */
        for(ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());

        emailService.sendOrderConfirmationEmail(obj);
        return obj;
    }
}

