package com.example.produtoNet.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    // Derived Query 1: Buscar categoria por nome
    List<Categoria> findByNomeCategoriaContainingIgnoreCase(String nomeCategoria);
    
    // Derived Query 2: Buscar categorias por descrição
    List<Categoria> findByDescricaoContainingIgnoreCase(String descricao);
    
    // JPQL Query 1: Buscar categorias com produtos
    @Query("SELECT c FROM Categoria c WHERE SIZE(c.listaProduto) > 0")
    List<Categoria> findCategoriasComProdutos();
    
    // JPQL Query 2: Buscar categorias sem produtos
    @Query("SELECT c FROM Categoria c WHERE SIZE(c.listaProduto) = 0")
    List<Categoria> findCategoriasSemProdutos();
    
    // Native Query: Contar produtos por categoria
    @Query(value = "SELECT c.nome_categoria, COUNT(p.id) as total_produtos " +
                   "FROM categoria c LEFT JOIN produto p ON c.id_categoria = p.id_categoria " +
                   "GROUP BY c.id_categoria, c.nome_categoria", nativeQuery = true)
    List<Object[]> contarProdutosPorCategoria();
}

