package com.springhelloworld.springhelloworld.dto;

import com.springhelloworld.springhelloworld.domain.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoriaDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min=5,max=80,message="O tamanho deve ter entre 5 e 80 caracteres.")
    private String nome;

    public CategoriaDto() {

    }

    public CategoriaDto(Categoria obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
