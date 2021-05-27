package Projeto_v2;

import java.util.List;

public class EstudanteGrad extends Estudante {
    
     //Declaração de Variaveis.
    protected int horasAtividades;
    
    //Instruções.
    
    //Método Construtor.
    public EstudanteGrad(long id, String nome, String email, int horasAtividades) {
        
        super(id, nome, email);

        this.horasAtividades = horasAtividades;        
    }
    
    //O método ira calcular o total de créditos do estudante.
    @Override
    public int getTotalCreditos() {
        
        //Inicia o total de créditos com a quantidade de horas de atividades complementares.
        int totalCreditos = getHorasAtividades();
        
        //Armazena em uma lista todas às disciplinas que o aluno está matriculado.
        List<Disciplina> disciplinas = getDisciplinasMatriculadas();
        
        //Percorre a lista de disciplinas e soma os créditos das disciplinas com a quantidade de horas de atividades complementares.
        for(Disciplina disciplina : disciplinas){totalCreditos += disciplina.getCreditos();}
        
        //Retorna o total de créditos calculado.
        return totalCreditos;
    }

    //Metodos Getters.
    public int getHorasAtividades() {return horasAtividades;}    
}