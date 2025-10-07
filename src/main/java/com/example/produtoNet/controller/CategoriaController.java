package com.example.produtoNet.controller;

import com.example.produtoNet.model.Categoria;
import com.example.produtoNet.model.CategoriaRepository;
import com.example.produtoNet.model.DadosAlteracaoCategoria;
import com.example.produtoNet.model.DadosCadastroCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    // --- FORMULÁRIO (GET) - novo/edição ---
    @GetMapping("/formulario")
    public String carregaFormulario(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            Categoria categoria = repository.getReferenceById(id);
            model.addAttribute("categoria", categoria);
            model.addAttribute("modo", "edicao");
        } else {
            model.addAttribute("categoria", new Categoria());
            model.addAttribute("modo", "novo");
        }
        return "categorias/formulario";
    }

    // --- CADASTRAR (POST) ---
    @PostMapping("/formulario")
    @Transactional
    public String cadastraCategoria(DadosCadastroCategoria dados) {
        Categoria categoria = new Categoria(dados);
        repository.save(categoria);
        return "redirect:/categorias/listagem";
    }

    // --- ATUALIZAR (PUT) ---
    @PutMapping("/formulario")
    @Transactional
    public String alteraCategoria(
            @RequestParam("idCategoria") Long idCategoria, // vem do hidden th:field="*{idCategoria}"
            DadosAlteracaoCategoria dados
    ) {
        Categoria categoria = repository.getReferenceById(idCategoria);
        categoria.atualizaDados(dados);
        repository.save(categoria);
        return "redirect:/categorias/listagem";
    }

    // --- LISTAGEM (GET) ---
    @GetMapping("/listagem")
    public String carregaListagem(Model model) {
        model.addAttribute("lista", repository.findAll());
        return "categorias/listagem";
    }

    // --- EXCLUIR (DELETE) ---
    @DeleteMapping
    @Transactional
    public String removeCategoria(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/categorias/listagem";
    }

    // --- RELATÓRIO (GET) ---
    @GetMapping("/relatorio")
    public String carregaRelatorio(Model model) {
        model.addAttribute("categoriasComProdutos", repository.findCategoriasComProdutos());
        model.addAttribute("categoriasSemProdutos", repository.findCategoriasSemProdutos());
        model.addAttribute("contadorProdutos", repository.contarProdutosPorCategoria());
        return "categorias/relatorio";
    }
}
