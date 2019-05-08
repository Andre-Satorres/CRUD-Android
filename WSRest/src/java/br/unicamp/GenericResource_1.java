/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unicamp;

import static java.awt.Event.DELETE;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.DELETE;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author u17162
 */
@Path("generic")
public class GenericResource_1 
{
    private ArrayList<Aluno> listaAluno = new ArrayList<>();
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource_1
     */
    public GenericResource_1() 
    {
        Aluno a1 = new Aluno();
        a1.setId(17162);
        a1.setNome("André Amadeu Satorres");
        a1.setCurso("Ciência da Computação");
        
        Aluno a2 = new Aluno();
        a2.setId(17184);
        a2.setNome("Iuri Guimarães Slywitch");
        a2.setCurso("Ciência da Computação");
        
        Aluno a3 = new Aluno();
        a3.setId(17188);
        a3.setNome("Luca Assumpcao Dillenburg");
        a3.setCurso("Educação Física");
        
        listaAluno.add(a1);
        listaAluno.add(a2);
        listaAluno.add(a3);
    }

    /**
     * Retrieves representation of an instance of br.unicamp.GenericResource_1
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() 
    {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource_1
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) 
    {
    }
    
    @GET
    @Path("/consulta")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Aluno> consultaAluno()
    {
        return listaAluno;
    }
    
    @GET
    @Path("consultaId/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Aluno consultaIdAluno(@PathParam("id") int id)
    {
        ArrayList<Aluno> listaTudo = listaAluno;
        
        for(Aluno a: listaTudo)
        {
            if(a.getId() == id)
                return a;
        }
        return null;
    }
    
    @GET
    @Path("consultaCurso/{curso}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Aluno> consultaCurso(@PathParam("curso") String curso)
    {
        ArrayList<Aluno> listaTudo = listaAluno;
        ArrayList<Aluno> ret = new ArrayList();
        
        for(Aluno a: listaTudo)
        {
            if(a.getCurso().toLowerCase().equals(curso.toLowerCase()))
                ret.add(a);
        }
        
        return ret;
    }
    
    @POST
    @Path("/incluirAluno")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Aluno> incluiAluno(Aluno aluno)
    {
        listaAluno.add(aluno);
        return listaAluno;
    }
    
    @POST
    @Path("/alterarAluno")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Aluno> alterarAluno(Aluno aluno)
    {
        ArrayList<Aluno> listaTudo = listaAluno;
        for(Aluno a: listaTudo)
        {
            if(a.getId() == aluno.getId())
            {
                a.setNome(aluno.getNome());
                a.setCurso(aluno.getCurso());
            } 
        }
        
        return listaTudo;
    }
    
    @GET
    @Path("/excluirAluno/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ArrayList<Aluno> excluirAluno(@PathParam("id") Integer id) throws Exception
    {
        for(Aluno a: listaAluno)
        {
            if(a.getId() == id)
                listaAluno.remove(a);  
        }  
        return listaAluno;
    }
}
