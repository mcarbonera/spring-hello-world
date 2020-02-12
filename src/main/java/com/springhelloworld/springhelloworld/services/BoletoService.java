package com.springhelloworld.springhelloworld.services;

import com.springhelloworld.springhelloworld.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    /* Na prática a data do vencimento vem de algum serviço a ser consultado */
    public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(instanteDoPedido);
        cal.add(Calendar.DAY_OF_MONTH,7);
        pagto.setDataVencimento(cal.getTime());
    }
}
