package com.example.gallery;

public class Picture {
	public int imageId;
	public String date;
	public String picpath;
	public String name;

	Picture(int id, String d, String path, String n) {
		imageId = id;
		date = d;
		picpath = path;
		name = n;
	}
}
