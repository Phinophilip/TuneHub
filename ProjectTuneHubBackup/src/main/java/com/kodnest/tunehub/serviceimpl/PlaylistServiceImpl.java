package com.kodnest.tunehub.serviceimpl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.repository.PlaylistRepository;
import com.kodnest.tunehub.repository.SongRepository;
import com.kodnest.tunehub.service.PlaylistService;


@Service
public class PlaylistServiceImpl implements PlaylistService{
	@Autowired
	PlaylistRepository plr;
	
	@Autowired
	SongRepository sr;
	
	@Override
	public void addPlaylist(Playlist playlist) {
		plr.save(playlist);
	}
	
	@Override
	public void addSonglist(Song song) {
		sr.save(song);
	}

	@Override
	public List<Playlist> getPlaylist() {
		return plr.findAll();
	}

	public List<Song> getSonglist(String name) {
	    Playlist playlist = plr.findByName(name); // Fetch the playlist by name
	    List<Song>s = playlist.getSongs();
	    if (s != null) {
	        return s; // Return the list of songs associated with the playlist
	    }
	    return Collections.emptyList(); // Return an empty list if playlist not found
	}

	@Override
	public Playlist findByName(String playlistName) {
		return plr.findByName(playlistName);
	}

	@Override
	public void updatePlaylist(Playlist playlist) {
		plr.save(playlist);
		
	}

	@Override
	public void deletePlaylistById(Integer id) {
		plr.deleteById(id);
		
	}


}

