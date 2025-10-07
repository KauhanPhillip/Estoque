package com.example.produtoNet.model;

public class DadosCadastroCategoria {
    private String nomeCategoria;
    private String descricao;
    
    public DadosCadastroCategoria() {}
    
    public DadosCadastroCategoria(String nomeCategoria, String descricao) {
        this.nomeCategoria = nomeCategoria;
        this.descricao = descricao;
    }
    
    public String nomeCategoria() {
        return nomeCategoria;
    }
    
    public String descricao() {
        return descricao;
    }
    
    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

