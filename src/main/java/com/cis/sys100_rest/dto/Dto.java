package com.cis.sys100_rest.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dto {

	protected UUID id;

	public Dto() {

	}

	public Dto(UUID id) {
		super();
		this.id = id;
	}

}
