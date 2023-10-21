package me.dio.domain.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "tb_artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Album> albums;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Track> singles;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Track> getSingles() {
        return singles;
    }

    public void setSingles(List<Track> singles) {
        this.singles = singles;
    }

    public void addAlbum(Album album) {
        this.albums.add(album);
    }

    public void addSingle(Track single) {
        this.singles.add(single);
    }
}
