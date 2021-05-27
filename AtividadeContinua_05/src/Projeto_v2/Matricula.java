package Projeto_v2;

public class Matricula {    
        
    //Declaração de Variaveis.
    private Estudante estudante;
    private Disciplina disciplina;
    
    //Instruções.
    
    //Método Construtor.    
    public Matricula(Estudante estudante, Disciplina disciplina){
        
        this.estudante = estudante;
        this.disciplina = disciplina;
    }
    
    //Metodos Getters.
    public Estudante getEstudante() {return estudante;}

    public Disciplina getDisciplina() {return disciplina;}    
}
