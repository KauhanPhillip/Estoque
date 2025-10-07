package com.example.produtoNet.controller;

import com.example.produtoNet.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired private ProdutoRepository repository;
    @Autowired private CategoriaRepository repositoryCategoria;
    @Autowired private LoteRepository loteRepository;

    @GetMapping("/formulario")
    public String carregaFormulario(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            Produto produto = repository.getReferenceById(id);
            model.addAttribute("produto", produto);
            model.addAttribute("modo", "edicao");

            Long catSel  = (produto.getCategoria() != null) ? produto.getCategoria().getIdCategoria() : null;
            Long loteSel = (produto.getLote() != null) ? produto.getLote().getId() : null; // ajuste se for getIdLote()

            model.addAttribute("categoriaIdSelecionado", catSel);
            model.addAttribute("loteIdSelecionado", loteSel);
        } else {
            model.addAttribute("produto", new Produto());
            model.addAttribute("modo", "novo");
            model.addAttribute("categoriaIdSelecionado", null);
            model.addAttribute("loteIdSelecionado", null);
        }

        model.addAttribute("listaCategorias", repositoryCategoria.findAll());
        model.addAttribute("lotes", loteRepository.findAllByOrderByDataValidadeAsc());
        return "produtos/formulario";
    }

    @PostMapping("/formulario")
    @Transactional
    public String cadastraProduto(DadosCadastroProduto dados,
                                  @RequestParam(required = false) Long categoriaId,
                                  @RequestParam(required = false) Long loteId) {

        Produto produto = new Produto(dados);

        // categoria via param simples
        if (categoriaId != null) {
            produto.setCategoria(repositoryCategoria.getReferenceById(categoriaId));
        } else {
            produto.setCategoria(null);
        }

        // lote via param simples (ou do DTO, se vier)
        if (loteId == null) {
            loteId = (dados.loteId() != null) ? dados.loteId() : dados.getLoteId();
        }
        produto.setLote(loteId != null ? loteRepository.getReferenceById(loteId) : null);

        repository.save(produto);
        return "redirect:/produtos/listagem";
    }

    @PutMapping("/formulario")
    @Transactional
    public String alteraProduto(DadosAlteracaoProduto dados,
                                @RequestParam Long id, // vem do hidden
                                @RequestParam(required = false) Long categoriaId,
                                @RequestParam(required = false) Long loteId) {

        Produto produto = repository.getReferenceById(id);
        produto.atualizaDados(dados);

        if (categoriaId != null) {
            produto.setCategoria(repositoryCategoria.getReferenceById(categoriaId));
        } else {
            produto.setCategoria(null);
        }

        if (loteId == null) {
            loteId = (dados.loteId() != null) ? dados.loteId() : dados.getLoteId();
        }
        produto.setLote(loteId != null ? loteRepository.getReferenceById(loteId) : null);

        repository.save(produto);
        return "redirect:/produtos/listagem";
    }

    @GetMapping("/listagem")
    public String carregaListagem(Model model) {
        model.addAttribute("lista", repository.findAll());
        return "produtos/listagem";
    }

    @DeleteMapping
    @Transactional
    public String removeProduto(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/produtos/listagem";
    }

    @GetMapping("/estoque-baixo")
    public String carregaProdutosEstoqueBaixo(Model model) {
        model.addAttribute("lista", repository.findProdutosComEstoqueBaixo(10));
        return "produtos/estoque-baixo";
    }

    @GetMapping("/por-categoria")
    public String carregaProdutosPorCategoria(@RequestParam Long categoriaId, Model model) {
        Categoria categoria = repositoryCategoria.getReferenceById(categoriaId);
        model.addAttribute("lista", repository.findByCategoria(categoria));
        model.addAttribute("categoria", categoria);
        return "produtos/por-categoria";
    }

    @GetMapping("/por-preco")
    public String carregaProdutosPorPreco(@RequestParam BigDecimal precoMin,
                                          @RequestParam BigDecimal precoMax,
                                          Model model) {
        model.addAttribute("lista", repository.findByPrecoBetween(precoMin, precoMax));
        model.addAttribute("precoMin", precoMin);
        model.addAttribute("precoMax", precoMax);
        return "produtos/por-preco";
    }
}
