package com.example.produtoNet.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Produto")
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidadeEstoque;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    // N:1 — cada produto pertence a UM lote
    @ManyToOne
    @JoinColumn(name = "lote_id")
    private Lote lote;

    public Produto() {}

    public Produto(DadosCadastroProduto dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.preco = dados.preco();
        this.quantidadeEstoque = dados.quantidadeEstoque();
        this.categoria = dados.categoria();
        // lote será setado no Controller via dados.loteId()
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public BigDecimal getPreco() { return preco; }
    public Integer getQuantidadeEstoque() { return quantidadeEstoque; }
    public Categoria getCategoria() { return categoria; }
    public Lote getLote() { return lote; }

    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public void setQuantidadeEstoque(Integer quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public void setLote(Lote lote) { this.lote = lote; }

    public void atualizaDados(DadosAlteracaoProduto dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.preco = dados.preco();
        this.quantidadeEstoque = dados.quantidadeEstoque();
        this.categoria = dados.categoria();
        // lote setado no Controller
    }
}
