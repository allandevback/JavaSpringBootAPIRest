package br.com.alura.videos.controller;

import br.com.alura.videos.controller.dto.CategoriaDto;
import br.com.alura.videos.controller.dto.VideoDto;
import br.com.alura.videos.controller.dto.VideoDtoDetalhado;
import br.com.alura.videos.controller.form.AtualizarCategoriaForm;
import br.com.alura.videos.controller.form.AtualizarVideoForm;
import br.com.alura.videos.controller.form.CategoriaForm;
import br.com.alura.videos.controller.form.VideoForm;
import br.com.alura.videos.modelo.Categoria;
import br.com.alura.videos.modelo.Video;
import br.com.alura.videos.repository.CategoriaRepository;
import br.com.alura.videos.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
@Profile(value = {"prod", "test", "dev"})
public class CategoriasController {

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping
    @Cacheable(value = "listagemDeCategorias")
    public Page<CategoriaDto> listarCategorias(@RequestParam(required = false)
                                                           String tituloCategoria,
                                               @PageableDefault(page = 0, size = 5)
                                                       Pageable paginacao){
        if(tituloCategoria == null){
            Page<Categoria> categorias = categoriaRepository.findAll(paginacao);
            return CategoriaDto.conversorCategoria(categorias);
        }
        Page<Categoria> categorias = categoriaRepository.findByTituloCategoria(tituloCategoria, paginacao);
        return CategoriaDto.conversorCategoria(categorias);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> detalharCategoria(@PathVariable("id") Long categoriaId){
        Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
        if(!categoria.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new CategoriaDto(categoria.get()));
    }

    @GetMapping("/{id}/videos")
    @Cacheable(value = "listagemDeVideosPorCategoria")
    public Page<VideoDto> listarVideosPorCategoria(@PathVariable("id") Long categoriaId,
                                                   @PageableDefault(page = 0, size = 5)
                                                           Pageable paginacao){
        Page<Video> video = videoRepository.findByCategoria_CategoriaId(categoriaId, paginacao);
        return VideoDto.conversor(video);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listagemDeCategorias", allEntries = true)
    public ResponseEntity<CategoriaDto> criarCategoria(@RequestBody @Valid CategoriaForm categoriaForm,
                                                UriComponentsBuilder uriBuilder){
        Categoria categoria = categoriaForm.conversorCategoriaForm();
        categoriaRepository.save(categoria);

        URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getCategoriaId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listagemDeCategorias", allEntries = true)
    public ResponseEntity<CategoriaDto> atualizarCategoria(@PathVariable("id") Long categoriaId,
                                                   @RequestBody @Valid AtualizarCategoriaForm categoriaForm){
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(categoriaId);
        if(!optionalCategoria.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Categoria categoria = categoriaForm.atualizarCategoria(categoriaId, categoriaRepository);
        return ResponseEntity.ok(new CategoriaDto(categoria));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listagemDeCategorias", allEntries = true)
    public ResponseEntity<?> deletarCategoria(@PathVariable("id") Long categoriaId){
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(categoriaId);
        if(!optionalCategoria.isPresent()){
            return ResponseEntity.notFound().build();
        }
        categoriaRepository.deleteById(categoriaId);
        return ResponseEntity.ok().build();
    }
}
