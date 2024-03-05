package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.service.PlaylistService;
import com.kodnest.tunehub.serviceimpl.SongServiceImpl;

@Controller
public class PlaylistController {
	@Autowired
	SongServiceImpl ssi;

	@Autowired
	PlaylistService pls;

	@GetMapping("/createplaylist")
	public String createPlaylist(Model m) {
		m.addAttribute("songs", ssi.getSongs());
		return "CreatePlaylist";
	}

	@GetMapping("/displayplaylist")
	public String displayPlaylist(Model m) {
		m.addAttribute("playlist", pls.getPlaylist());
		return "DisplayPlaylist";
	}

	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		pls.addPlaylist(playlist);
		List<Song> songlist = playlist.getSongs();
		for (Song song : songlist) {
			song.getPlaylist().add(playlist);
			ssi.updateSong(song);
		}
		return "AdminHome";
	}

	@GetMapping("/songList")
	public String songList(@RequestParam("name") String name, Model model) {
		List<Song> songList = pls.getSonglist(name); // Retrieve the list of songs
		model.addAttribute("songlist", songList); // Add the list to the model
		return "SongList"; // Return the view name
	}

	@PostMapping("/customeraddplay")
	public String addPlayCustomer(@RequestParam("name") String sname,
			@RequestParam("playlistName") String playlistName) {
		Playlist existingPlaylist = pls.findByName(playlistName);
		if (existingPlaylist != null) {
			Song song = ssi.findByName(sname);
			if (song != null) {
				existingPlaylist.getSongs().add(song);
				pls.updatePlaylist(existingPlaylist);

				song.getPlaylist().add(existingPlaylist);
				ssi.updateSong(song);
			}
		} else {
			Playlist newPlaylist = new Playlist();
			newPlaylist.setName(playlistName);
			pls.addPlaylist(newPlaylist);
			Song song = ssi.findByName(sname);
			if (song != null) {
				newPlaylist.getSongs().add(song);
				pls.updatePlaylist(existingPlaylist);

				song.getPlaylist().add(newPlaylist);
				ssi.updateSong(song);
			}
		}
		return "CustomerHome";
	}

	@GetMapping("/viewplaylist")
	public String customerDisplayPlaylist(Model m) {
		m.addAttribute("playlist", pls.getPlaylist());
		return "CustomerDisplayPlaylist";
	}

	@DeleteMapping("/playlists/{id}")
	public ResponseEntity<String> deletePlaylist(@PathVariable Integer id) {
		pls.deletePlaylistById(id);
		return ResponseEntity.ok("Playlist deleted successfully");
	}

}
