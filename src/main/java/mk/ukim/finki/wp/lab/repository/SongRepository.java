package mk.ukim.finki.wp.lab.repository;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SongRepository {
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    static public List<Song> songs = new ArrayList<>(5);


    @PostConstruct
    public void init(){
        List<Artist> allArtists = artistRepository.findAll();
        List<Album> allAlbums = albumRepository.findAll();

        songs.add(new Song("01", "Silent Echoes", "Rock", 2015, List.of(allArtists.get(0)), allAlbums.get(0)));
        songs.add(new Song("02", "Whispered Dreams", "Jazz", 2018, List.of(allArtists.get(1)), allAlbums.get(1)));
        songs.add(new Song("03", "Rhythmic Waves", "Pop", 2020, List.of(allArtists.get(2)), allAlbums.get(2)));
        songs.add(new Song("04", "Melody of Rain", "Classical", 2022, List.of(allArtists.get(3)), allAlbums.get(3)));
        songs.add(new Song("05", "Soul Voyage", "Soul", 2017, List.of(allArtists.get(4)), allAlbums.get(4)));
    }

    public List<Song> findAll(){
        return songs;
    }
    public Song findByTrackId(String trackId){
        return songs.stream().filter(s -> s.getTrackId().equals(trackId)).findFirst().orElse(null);
    }
    public Artist addArtistToSong(Artist artist, Song song){
        song.performers.add(artist);
        return artist;
    }
    public void deleteById(String songId){
        songs.removeIf(s -> s.getTrackId().equals(songId));
    }
   public Optional<Song> save(Song song){
       songs.removeIf(s -> s.getTrackId().equals(song.getTrackId()));
       songs.add(song);
       return Optional.of(song);
   }



}
