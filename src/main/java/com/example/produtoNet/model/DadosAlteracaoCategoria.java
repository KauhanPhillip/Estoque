package com.example.produtoNet.model;

public class DadosAlteracaoCategoria {
    private Long id;
    private String nomeCategoria;
    private String descricao;
    
    public DadosAlteracaoCategoria() {}
    
    public DadosAlteracaoCategoria(Long id, String nomeCategoria, String descricao) {
        this.id = id;
        this.nomeCategoria = nomeCategoria;
        this.descricao = descricao;
    }
    
    public Long id() {
        return id;
    }
    
    public String nomeCategoria() {
        return nomeCategoria;
    }
    
    public String descricao() {
        return descricao;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

