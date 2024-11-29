package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        return songRepository.addArtistToSong(artist, song);
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public void deleteSongById(String id) {
        songRepository.deleteById(id);
    }

    @Override
    public Optional<Song> addNewSong(String trackId, String title, String genre, int releaseYear, Album album) {
          return songRepository.save(trackId,title,genre,releaseYear,album);
    }

    @Override
    public void editSong(String trackId, String title, String genre, int releaseYear, Album album) {
        Song song = songRepository.findByTrackId(trackId);
        song.setTitle(title);
        song.setId(Long.valueOf(trackId));
        song.setReleaseYear(releaseYear);
        song.setGenre(genre);
        song.setAlbum(album);
        //ja zacuvvuame posle edit
        //songRepository.saveSongAfterEdit(song);
    }

}


