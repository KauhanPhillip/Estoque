package com.example.produtoNet.controller;

import com.example.produtoNet.model.Lote;
import com.example.produtoNet.model.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/lotes")
public class LoteController {

    @Autowired
    private LoteRepository loteRepository;

    // --- FORMULÁRIO (GET) ---
    @GetMapping("/formulario")
    public String formulario(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            Lote lote = loteRepository.getReferenceById(id);
            model.addAttribute("lote", lote);
            model.addAttribute("modo", "edicao");
        } else {
            model.addAttribute("lote", new Lote());
            model.addAttribute("modo", "novo");
        }
        return "lotes/formulario";
    }

    // --- CADASTRAR (POST) ---
    @PostMapping("/formulario")
    @Transactional
    public String cadastrar(
            @RequestParam String codigoLote,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFabricacao,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataValidade,
            @RequestParam(required = false, defaultValue = "false") boolean ativo
    ) {
        Lote lote = new Lote();
        lote.setCodigoLote(codigoLote);
        lote.setDataFabricacao(dataFabricacao);
        lote.setDataValidade(dataValidade);
        lote.setAtivo(ativo);

        loteRepository.save(lote);
        return "redirect:/lotes/listagem";
    }

    // --- ATUALIZAR (PUT) ---
    @PutMapping("/formulario")
    @Transactional
    public String atualizar(
            @RequestParam Long id,
            @RequestParam String codigoLote,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFabricacao,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataValidade,
            @RequestParam(value = "ativo", required = false, defaultValue = "false") boolean ativo
    ) {
        Lote lote = loteRepository.getReferenceById(id);
        lote.setCodigoLote(codigoLote);
        lote.setDataFabricacao(dataFabricacao);
        lote.setDataValidade(dataValidade);
        lote.setAtivo(ativo); // ✅ Se checkbox estiver desmarcado, vem false

        loteRepository.save(lote);
        return "redirect:/lotes/listagem";
    }

    // --- LISTAGEM (GET) ---
    @GetMapping("/listagem")
    public String listagem(Model model) {
        model.addAttribute("lista", loteRepository.findAll());
        return "lotes/listagem";
    }

    // --- EXCLUIR (DELETE) ---
    @DeleteMapping
    @Transactional
    public String excluir(@RequestParam Long id) {
        loteRepository.deleteById(id);
        return "redirect:/lotes/listagem";
    }

    // --- FORMULÁRIO DE EDIÇÃO ---
    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Lote lote = loteRepository.getReferenceById(id);
        model.addAttribute("lote", lote);
        return "lotes/formulario-editar";
    }
}
