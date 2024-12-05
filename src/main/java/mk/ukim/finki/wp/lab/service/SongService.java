package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;

public interface SongService {
    List<Song> listSongs();
    Artist addArtistToSong(Artist artist, Song song);
    public Song findByTrackId(Long trackId);
    void deleteSongById(Long id);
    public void addNewSong(String title, String genre, int releaseYear, Album album);
    void editSong(Long trackId,String title,String genre,int releaseYear,List<Artist> performers,Album album);

}
