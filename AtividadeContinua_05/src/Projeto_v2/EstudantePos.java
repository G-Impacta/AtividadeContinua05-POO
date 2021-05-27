package Projeto_v2;

import java.util.List;

public class EstudantePos extends Estudante {
    
    //Declaração de Variaveis.
    protected String tema;
    protected String orientador;    
        
    //Instruções.
    
    //Método Construtor.
    public EstudantePos(long id, String nome, String email, String tema, String orientador) {
        
        super(id, nome, email);
        
        this.tema = tema;
        this.orientador = orientador;   
    }
    
    //O método ira calcular o total de créditos do estudante.
    @Override
    public int getTotalCreditos() {        
                
        //Inicia o total de créditos com 0;
        int totalCreditos = 0;        
                
        //Armazena em uma lista todas às disciplinas que o aluno está matriculado.
        List<Disciplina> disciplinas = getDisciplinasMatriculadas();        
                
        //Percorre a lista de disciplinas e soma os créditos das mesmas.
        for(Disciplina disciplina : disciplinas){totalCreditos += disciplina.getCreditos();}        
                
        //Retorna o total de créditos calculado.
        return totalCreditos;        
    }

    //Metodos Getters.
    public String getTema() {return tema;}

    public String getOrientador() {return orientador;}
}