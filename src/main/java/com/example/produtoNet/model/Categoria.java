package com.example.produtoNet.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;
    private String nomeCategoria;
    private String descricao;
    
    @OneToMany(mappedBy = "categoria")
    private List<Produto> listaProduto = new ArrayList<>();

    public Categoria(){}

    public Categoria(DadosCadastroCategoria dados) {
        this.nomeCategoria = dados.nomeCategoria();
        this.descricao = dados.descricao();
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Produto> getListaProduto() {
        return listaProduto;
    }

    public void atualizaDados(DadosAlteracaoCategoria dados) {
        this.nomeCategoria = dados.nomeCategoria();
        this.descricao = dados.descricao();
    }
}

