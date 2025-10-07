package com.example.produtoNet.model;

import java.math.BigDecimal;

public class DadosAlteracaoProduto {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidadeEstoque;
    private Categoria categoria;
    private Long loteId; // um único lote

    public DadosAlteracaoProduto() {}

    public DadosAlteracaoProduto(Long id, String nome, String descricao, BigDecimal preco,
                                 Integer quantidadeEstoque, Categoria categoria, Long loteId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
        this.loteId = loteId;
    }

    // getters no estilo do projeto
    public Long id() { return id; }
    public String nome() { return nome; }
    public String descricao() { return descricao; }
    public BigDecimal preco() { return preco; }
    public Integer quantidadeEstoque() { return quantidadeEstoque; }
    public Categoria categoria() { return categoria; }
    public Long loteId() { return loteId; }

    // getter JavaBean
    public Long getLoteId() { return loteId; }

    // setters
    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public void setQuantidadeEstoque(Integer quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public void setLoteId(Long loteId) { this.loteId = loteId; }
}
