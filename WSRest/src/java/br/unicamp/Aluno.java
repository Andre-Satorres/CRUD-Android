/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package br.unicamp;

import java.util.Objects;

/**
 *
 * @author u17162
 */
public class Aluno 
{
    private int id;
    private String nome;
    private String curso;
    
    @Override
    public String toString() 
    {
        return "Aluno{" + "id=" + id + ", nome=" + nome + ", curso=" + curso + '}';
    }
    
    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.nome);
        hash = 59 * hash + Objects.hashCode(this.curso);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
        {
            return true;
        }
        if (obj == null) 
        {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final Aluno other = (Aluno) obj;
        if (this.id != other.id) 
        {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) 
        {
            return false;
        }
        if (!Objects.equals(this.curso, other.curso)) 
        {
            return false;
        }
        return true;
    }
    
    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getNome() 
    {
        return nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public String getCurso() 
    {
        return curso;
    }

    public void setCurso(String curso) 
    {
        this.curso = curso;
    }
}