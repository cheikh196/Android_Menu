package com.example.menu;

import java.io.Serializable;

public class Contact implements Serializable {
    String nom;
    String numero;
    String description;

    public Contact(String nom,String numero,String description)
    {
        this.nom=nom;
        this.numero=numero;
        this.description=description;
    }

    public String getNom()
    {
        return  nom;
    }
    public String getNumero()
    {
        return  numero;
    }
    public String getDescription()
    {
        return  description;
    }
}
