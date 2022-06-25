package br.com.alura.videos.controller;

import br.com.alura.videos.controller.dto.VideoDtoDetalhado;
import br.com.alura.videos.controller.form.AtualizarVideoForm;
import br.com.alura.videos.controller.dto.VideoDto;
import br.com.alura.videos.controller.form.VideoForm;
import br.com.alura.videos.modelo.Video;
import br.com.alura.videos.repository.CategoriaRepository;
import br.com.alura.videos.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
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
import java.util.Optional;

@RestController
@RequestMapping("/videos")
@Profile(value = {"prod", "test", "dev"})
public class VideosController {

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping
    public Page<VideoDto> listarVideos(@RequestParam(required = false) String titulo,
                                       @PageableDefault(page = 0, size = 5)
                                               Pageable paginacao){
        if(titulo == null){
            Page<Video> video = videoRepository.findAll(paginacao);
            return VideoDto.conversor(video);
        }
        Page<Video> video = videoRepository.findByTitulo(titulo, paginacao);
        return VideoDto.conversor(video);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listagemDeVideosPorCategoria", allEntries = true)
    public ResponseEntity<VideoDto> postarVideo(@RequestBody @Valid VideoForm videoForm,
                                                UriComponentsBuilder uriBuilder){
        Video video = videoForm.conversor(categoriaRepository);
        videoRepository.save(video);

        URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new VideoDto(video));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDtoDetalhado> detalharVideo(@PathVariable/*("id")*/ Long id){
        Optional<Video> video = videoRepository.findById(id);
        if(!video.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new VideoDtoDetalhado(video.get()));
    }

    @GetMapping("/free")
    public Page<VideoDto> listarVideosComCategoriaLivre(@PageableDefault(page = 0, size = 5)
                                                                    Pageable paginacao){
        Page<Video> videos = videoRepository.findByCategoria_CategoriaId(1L, paginacao);
        return VideoDto.conversor(videos);
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listagemDeVideosPorCategoria", allEntries = true)
    public ResponseEntity<VideoDto> atualizarVideo(@PathVariable/*("id")*/ Long id,
                                                   @RequestBody @Valid AtualizarVideoForm videoForm){
        Optional<Video> optionalVideo = videoRepository.findById(id);
        if(!optionalVideo.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Video video = videoForm.atualizar(id, videoRepository, categoriaRepository);
        return ResponseEntity.ok(new VideoDto(video));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listagemDeVideosPorCategoria", allEntries = true)
    public ResponseEntity<?> deletarVideo(@PathVariable/*("id")*/ Long id){
        Optional<Video> optionalVideo = videoRepository.findById(id);
        if(!optionalVideo.isPresent()){
            return ResponseEntity.notFound().build();
        }
        videoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
