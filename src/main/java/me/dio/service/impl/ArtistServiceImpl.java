package me.dio.service.impl;

import me.dio.domain.model.Album;
import me.dio.domain.model.Artist;
import me.dio.domain.model.Track;
import me.dio.domain.repository.AlbumRepository;
import me.dio.domain.repository.ArtistRepository;
import me.dio.domain.repository.TrackRepository;
import me.dio.service.ArtistService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;

    private final TrackRepository trackRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository, AlbumRepository albumRepository, TrackRepository trackRepository){
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.trackRepository = trackRepository;
    };
    @Override
    public Artist findById(Long id) {
        return artistRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Artist findByName(String name){
        List<Artist> artistList = artistRepository.findAll();
        for (Artist artist: artistList) {
            if (Objects.equals(name, artist.getName())) {
                return artist;
            }
        }
        throw new IllegalArgumentException(name + " not found!");
    }

    @Override
    public Album addAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Track addSingle(Track single) {
        return trackRepository.save(single);
    }

    @Override
    public Artist create(Artist artistToCreate) {
        if (artistToCreate.getId() != null && artistRepository.existsById(artistToCreate.getId())){
            throw new IllegalArgumentException("This artist ID already exists");
        }
        List<Artist> artistList = artistRepository.findAll();
        for (Artist artist: artistList) {
            if (Objects.equals(artistToCreate.getName(), artist.getName())) {
                throw new IllegalArgumentException("This artist name already exists");
            }
        }

        return artistRepository.save(artistToCreate);
    }
}
