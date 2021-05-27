package Projeto_v2;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {    
        
    //Declaração de Variaveis.
    private String codigo;
    private int creditos;
    private List<Matricula> matriculas = new ArrayList<>();
    
    //Instruções.
    
    //Método Construtor.
    public Disciplina(String codigo, int creditos){
        
        this.codigo = codigo;
        this.creditos = creditos;     
    }
    
    //O método irá adicionar matriculas a lista de matriculas de cada disciplina.
    public void addMatricula(Matricula matricula){matriculas.add(matricula);}    
    
    //O método irá listar todos os estudantes participantes das disciplinas.
    public List<Estudante> getEstudantesMatriculados(){
        
        //Instancia uma lista de estudantes vazia.
        List<Estudante> estudantes = new ArrayList<>();
        
        //Percorre a lista de matriculas da disciplina e adiciona o estudante a lista de estudantes.
        for(Matricula matricula : matriculas){estudantes.add(matricula.getEstudante());}
        
        //Retorna a lista de Estudantes.
        return estudantes;
    }
    
    //Metodos Getters.
    public String getCodigo() {return codigo;}

    public int getCreditos() {return creditos;}

    public List<Matricula> getMatriculas() {return matriculas;}    
}
