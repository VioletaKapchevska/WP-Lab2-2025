package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Controller
@RequestMapping("/artist")
public class ArtistController {
    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping()
    public String getArtistPage(Model model,HttpSession session) {
        Song chosenSong = (Song)session.getAttribute("song");
        model.addAttribute("artists",artistService.listArtists());
        model.addAttribute("chosenSong",chosenSong);
        return "artistsList";
    }
    //delete an artist
    @GetMapping("/delete/{id}")
    public String deleteArtist(@PathVariable(name = "id") String id){
       // System.out.println("id: "+id);
        this.artistService.deteleArtist(Long.valueOf(id));
        return "redirect:/artist";
    }
    //add artist form
    @GetMapping("/add-form")
    public String getAddArtistPage(Model model){
        model.addAttribute("artists",this.artistService.listArtists());
        return "add-artist";
    }

    //add the artist
    @PostMapping("/add")
    public String saveArtist(@RequestParam(name="aName") String name,
                           @RequestParam(name="aLastName") String lastName){
        this.artistService.addNewArtist(name,lastName);//dodavame nov artist
        return "redirect:/artist";
    }

}
