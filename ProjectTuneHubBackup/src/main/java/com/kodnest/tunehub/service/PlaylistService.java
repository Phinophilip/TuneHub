package com.kodnest.tunehub.service;

import java.util.List;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;

public interface PlaylistService {

	public void addPlaylist(Playlist pl);

	public void addSonglist(Song song);

	public List<Playlist> getPlaylist();
	
	public List<Song> getSonglist(String name);

	public Playlist findByName(String playlistName);

	public void updatePlaylist(Playlist existingPlaylist);

	public void deletePlaylistById(Integer id);

}
