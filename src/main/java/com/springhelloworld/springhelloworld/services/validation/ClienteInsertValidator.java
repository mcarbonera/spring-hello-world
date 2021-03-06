package com.springhelloworld.springhelloworld.services.validation;

import com.springhelloworld.springhelloworld.domain.Cliente;
import com.springhelloworld.springhelloworld.dto.ClienteNewDto;
import com.springhelloworld.springhelloworld.enums.TipoCliente;
import com.springhelloworld.springhelloworld.exceptions.FieldMessage;
import com.springhelloworld.springhelloworld.repositories.ClienteRepository;
import com.springhelloworld.springhelloworld.services.validation.utils.DocumentUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert ann) {

    }

    @Override
    public boolean isValid(ClienteNewDto objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !DocumentUtil.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj","CPF inválido"));
        }
        if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !DocumentUtil.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj","CNPJ inválido"));
        }
        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
        if(aux != null) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        for(FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
