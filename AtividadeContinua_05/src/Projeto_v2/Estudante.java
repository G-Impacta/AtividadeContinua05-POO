package Projeto_v2;

import java.util.ArrayList;
import java.util.List;

public abstract class Estudante {
    
    //Declaração de Variaveis.
    protected long id;
    protected String nome;
    protected String email;
    protected List<Matricula> matriculas = new ArrayList<>();
    
    //Instruções.
    
    //Método Construtor.
    public Estudante(long id, String nome, String email){
        
        this.id = id;
        this.nome = nome;
        this.email = email;        
    }   
        
    //O método irá adicionar matriculas a lista de matriculas de cada estudante.
    protected void addMatricula(Matricula matricula){matriculas.add(matricula);}
    
    //O método irá listar todas ás disciplinas que o estudante participa.
    protected List<Disciplina> getDisciplinasMatriculadas(){
        
        //Instancia uma lista de Disciplinas vazia.
        List<Disciplina> disciplinas = new ArrayList<>();
        
        //Percorre a lista de matriculas do estudante e adiciona a Disciplina à lista de disciplinas.
        for(Matricula matricula : matriculas){disciplinas.add(matricula.getDisciplina());}
        
        //Retorna a lista de Disciplinas.
        return disciplinas;
    }

    protected abstract int getTotalCreditos();
        
    //Metodos Getters.
    public long getId() {return id;}

    public String getNome() {return nome;}

    public String getEmail() {return email;}     

    public List<Matricula> getMatriculas() {return matriculas;}
}