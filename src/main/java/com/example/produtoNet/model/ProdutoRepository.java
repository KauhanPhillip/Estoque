package com.example.produtoNet.model;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // força carregar categoria (e lote se quiser)
    @Override
    @EntityGraph(attributePaths = { "categoria", "lote" })
    List<Produto> findAll();

    // Buscar produtos por categoria
    List<Produto> findByCategoria(Categoria categoria);

    // Buscar produtos por faixa de preço
    List<Produto> findByPrecoBetween(BigDecimal precoMin, BigDecimal precoMax);

    // Buscar produtos com estoque abaixo de um limite
    @Query("SELECT p FROM Produto p WHERE p.quantidadeEstoque < :limite")
    List<Produto> findProdutosComEstoqueBaixo(@Param("limite") Integer limite);
}
