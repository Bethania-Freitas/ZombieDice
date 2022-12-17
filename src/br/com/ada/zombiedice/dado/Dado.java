package br.com.ada.zombiedice.dado;

import java.util.Random;

public abstract class Dado {

    private Face[] faces;
    private Face faceSorteada;

    protected Dado(Face[] faces) {
        this.faces = faces;
    }

    public void jogar(){
        int sorteado = new Random().nextInt(0,faces.length);
        this.faceSorteada = faces[sorteado];
    }

    public Face getFaceSorteada(){
        if(faceSorteada == null) {
            jogar();
        }
        return faceSorteada;
    }

    public void resetFaceSorteada() {
        this.faceSorteada = null;
    }

    public abstract TipoDado getTipoDado();
}
