package br.com.lecom.catalogomanager.catalogo.controller;

import br.com.lecom.catalogomanager.catalogo.dto.CatalogoDTO;
import br.com.lecom.catalogomanager.catalogo.service.CatalogoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@RestController
//@RequestMapping("/v1/catalogos")
public class CatalogoController {
    //implements CatalogoControllerDocs

//    private final CatalogoService catalogoService;
//
//    public CatalogoController(CatalogoService catalogoService) {
//        this.catalogoService = catalogoService;
//    }
//
//    @GetMapping
//    public ResponseEntity<Page<CatalogoDTO>> buscarTodos(Pageable pageable) {
//        return ResponseEntity.ok(catalogoService.buscarTodos(pageable));
//    }
//
//    @GetMapping("/{idCatalogo}")
//    public ResponseEntity<CatalogoDTO> buscarPeloId(
//            @PathVariable Long idCatalogo
//    ) {
//        var catalogViewtDTO = catalogoService.buscarPorId(idCatalogo);
//        return ResponseEntity.ok(catalogViewtDTO);
//    }
//
//    @PostMapping
//    public ResponseEntity<CatalogoDTO> cadastrar(
//            @Validated @RequestBody CatalogoDTO catalogoDTO
//    ) {
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(catalogoService.cadastrar(catalogoDTO));
//    }
//
//    @PatchMapping("/{idCatalogo}")
//    public ResponseEntity<CatalogoDTO> patch(
//            @PathVariable Long idCatalogo,
//            @Valid @RequestBody CatalogoDTO catalogoDTO
//    ) {
//        return ResponseEntity.ok(catalogoService.patch(idCatalogo, catalogoDTO));
//    }
}