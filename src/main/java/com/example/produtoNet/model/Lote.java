package com.example.produtoNet.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lote")
public class Lote {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String codigoLote;

    private LocalDate dataFabricacao;

    @Column(nullable = false)
    private LocalDate dataValidade;

    private Boolean ativo = true;

    // (opcional) lado inverso: um lote possui vários produtos
    @OneToMany(mappedBy = "lote")
    private Set<Produto> produtos = new HashSet<>();

    public Long getId() { return id; }
    public String getCodigoLote() { return codigoLote; }
    public void setCodigoLote(String codigoLote) { this.codigoLote = codigoLote; }
    public LocalDate getDataFabricacao() { return dataFabricacao; }
    public void setDataFabricacao(LocalDate dataFabricacao) { this.dataFabricacao = dataFabricacao; }
    public LocalDate getDataValidade() { return dataValidade; }
    public void setDataValidade(LocalDate dataValidade) { this.dataValidade = dataValidade; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    public Set<Produto> getProdutos() { return produtos; }
}
