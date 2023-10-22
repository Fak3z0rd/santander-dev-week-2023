package me.dio.service;

import me.dio.domain.model.Album;
import me.dio.domain.model.Artist;
import me.dio.domain.model.Track;
import org.springframework.stereotype.Service;

@Service
public interface ArtistService {

    Artist findById(Long id);

    Artist create(Artist artistToCreate);

    Artist findByName(String name);

    Album addAlbum(Album album);

    Track addSingle(Track single);
}
