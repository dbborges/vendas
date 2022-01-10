package br.com.lecom.catalogomanager.catalogo.mapper;

import br.com.lecom.catalogomanager.catalogo.dto.CatalogoDTO;
import br.com.lecom.catalogomanager.catalogo.entity.Catalogo;
import org.mapstruct.Mapper;

@Mapper
public interface CatalogoMapper {

    Catalogo toModel(CatalogoDTO catalogoDTO);

    CatalogoDTO toDTO(Catalogo catalogo);
}