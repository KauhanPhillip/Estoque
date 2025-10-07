package com.example.produtoNet.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoteRepository extends JpaRepository<Lote, Long> {
    List<Lote> findAllByOrderByDataValidadeAsc();
}
