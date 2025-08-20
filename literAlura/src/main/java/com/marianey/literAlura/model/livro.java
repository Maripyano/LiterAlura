package com.marianey.literAlura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private int numeroDownloads;

    @ManyToOne
    private Autor autor;

    public Livro() {}

    public Livro(String titulo, int numeroDownloads) {
        this.titulo = titulo;
        this.numeroDownloads = numeroDownloads;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getNumeroDownloads() { return numeroDownloads; }
    public void setNumeroDownloads(int numeroDownloads) { this.numeroDownloads = numeroDownloads; }
    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor.getNome() + ", Downloads: " + numeroDownloads;
    }
}