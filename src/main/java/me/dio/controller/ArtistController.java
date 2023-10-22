package me.dio.controller;

import me.dio.domain.model.Artist;
import me.dio.service.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Artist> findByName(@PathVariable String name){
        var artist = artistService.findByName(name);
        return ResponseEntity.ok(artist);
    }

    @PostMapping()
    public ResponseEntity<Artist> create(@RequestBody Artist artistToCreate) {
        var artistCreated = artistService.create(artistToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(artistCreated.getName())
                .toUri();
        return ResponseEntity.created(location).body(artistCreated);
    }
}

