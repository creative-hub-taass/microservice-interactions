package com.creativehub.backend.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
@Table(name = "RichiesteCollaborazione")
public class RichiestaCollaborazione {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "idMittente", nullable = false)
	private Long idMittente;

	@Column(name = "idDestinatario")
	private Long idDestinatario;

	@Column(name = "titolo")
	private String titolo;

	@Column(name = "descrizione")
	private String descrizione;

	@Column(name = "timestamp")
	private Timestamp timestamp;

	@Column(name = "contatto")
	private String contatto; //FIXME: contatto come?

	@Column(name = "categoria")
	private String categoria;
}
