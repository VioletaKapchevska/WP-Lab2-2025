package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Song findByTrackId(Long trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public void deleteSongById(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public void addNewSong(String title, String genre, int releaseYear, Album album) {
        Song song = new Song(title, genre, releaseYear, album);
          songRepository.save(song);
    }

    @Override
    public void editSong(Long trackId, String title, String genre, int releaseYear,List<Artist> performers,Album album) {
        Song song = new Song(trackId,title, genre, releaseYear,performers, album);
        //song.setTitle(title);
       // song.setId(Long.valueOf(trackId));
       // song.setReleaseYear(releaseYear);
       // song.setGenre(genre);
      //  song.setAlbum(album);
        //ja zacuvvuame posle edit
        //songRepository.saveSongAfterEdit(song);
        songRepository.save(song);
    }

}


