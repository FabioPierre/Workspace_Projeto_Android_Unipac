package br.com.adsunipac.provision.atributos;

import java.io.Serializable;

/**
 * Created by TECNOLOGIA on 20/11/2017.
 */

public class Mantimento implements Serializable{
    private Long id;
    private String nome;
    private String quantidade;
    private String estoque;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getEstoque() {
        return estoque;
    }

    public void setEstoque(String estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return " " + getEstoque() + " -- " + getQuantidade() + " -- " + getNome();
    }

}
