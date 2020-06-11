package com.cis.sys100_rest.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuNodeDto implements Comparable<MenuNodeDto> {

	private UUID p;
	private Integer so;
	private String name;
	private UUID q;
	private String image;
	private String url;
	private Integer position;

	public MenuNodeDto() {
	}

	public MenuNodeDto(Integer so, String name, UUID q, String image, String url, Integer position) {
		super();

		this.so = so;
		this.name = name;
		this.q = q;
		this.image = image;
		this.url = url;
		this.position = position;
	}

	@Override
	public int compareTo(MenuNodeDto o) {
		return so.compareTo(o.getSo());
	}

	@Override
	public String toString() {
		return "Menu [p=" + p + ", so=" + so + ", name=" + name + ", q=" + q + "]\n";
	}

}
