package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;

    public SongController(SongService songService, AlbumService albumService, ArtistService artistService) {
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
    }
    @GetMapping("/songs")
    public String getSongsPage(@RequestParam(required = false) String error, Model model){
     //da gi vrakja site pesni so albumite

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("songs",songService.listSongs());
        return "listSongs";

    }
    //ja vrakja stranata za dodavanje na nova pesna

    @GetMapping("/songs/add-form")
    public String getAddSongPage(Model model){
        model.addAttribute("albums",albumService.findAll());//albumite sto ni trebaat
        return "add-song";
    }

    //delete a song
    @GetMapping("/songs/delete/{id}")
    public String deleteSong(@PathVariable(name = "id") String id){
        System.out.println("id: "+id);
          this.songService.deleteSongById(id);//ja brisheme pesnata
          return "redirect:/songs";
    }


    //add new Song
    @PostMapping("/songs/add")
    public String saveSong(@RequestParam(name="songName") String title,
                           @RequestParam(name="trackId") String trackId,
                           @RequestParam(name="genre") String genre,
                           @RequestParam(name="rYear") String releaseYear,
                           @RequestParam(name="albumId") Long albumId){
       Album album = albumService.findById(albumId);
       Integer year = Integer.parseInt(releaseYear);
       this.songService.addNewSong(trackId,title,genre,year,album);
       return "redirect:/songs";
    }

   @GetMapping("/songs/edit-form/{id}")
   public String getEditSongForm(@PathVariable String id, Model model){
       Song song = this.songService.findByTrackId(id);
       List<Artist> artists = this.artistService.listArtists();
       List<Album> albums = this.albumService.findAll();
       model.addAttribute("song", song);
       model.addAttribute("artists", artists);
       model.addAttribute("albums", albums);
       return "add-song";
   }

    @GetMapping("/songs/edit/{songId}")
    public String editSong(@PathVariable Long songId,
                           @RequestParam(required = false) String title,
                           @RequestParam(required = false) String trackId,
                           @RequestParam(required = false) String genre,
                           @RequestParam(required = false) Integer releaseYear,
                           @RequestParam(required = false) Long albumId){
       Album album = albumService.findById(albumId);
        songService.editSong(trackId,title,genre,releaseYear,album);
        return "redirect:/songs";
    }


}
