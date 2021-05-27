package Projeto_v2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Faculdade {    
        
    //Declaração de Variaveis.
    private String nome;
    private List<Estudante> estudantes = new ArrayList<>();
    private List<Disciplina> disciplinas = new ArrayList<>();
    
    //Instruções.
    
    //Método Construtor.
    public Faculdade(String nome){
        
        this.nome = nome;
    }    
    
    //Método Carregar Dados(Irá carregar os dados dos arquivos txt). 
    public void carregarDadosArquivo(String arqDisciplina, String arqEstudante, String arqMatricula) throws FileNotFoundException {
                        
        //Variaveis com os Buffers dos arquivos lidos.
        BufferedReader arquivoDisciplina = new BufferedReader(new FileReader(arqDisciplina));
        BufferedReader arquivoEstudante = new BufferedReader(new FileReader(arqEstudante));
        BufferedReader arquivoMatricula = new BufferedReader(new FileReader(arqMatricula));
        
        try {            
                        
            //Le a primeira linha do arqDisciplina e armazena na variavel.
            String linhaDisciplina = arquivoDisciplina.readLine();
            
            //Irá percorre o arquivo Disciplina até a ultima linha.
            while(linhaDisciplina != null){
                
                //Separa cada parte da linha em variaveis individuais.
                String codigoDisciplina = linhaDisciplina.split(":")[0];
                int creditosDisciplina = Integer.parseInt(linhaDisciplina.split(":")[1]);
                
                //Usando às informações do arquivo cria uma disciplina e adiciona a lista de disciplinas da faculdade.
                disciplinas.add(new Disciplina(codigoDisciplina, creditosDisciplina));
                
                //Avança para a proxima linha do arquivo.
                linhaDisciplina = arquivoDisciplina.readLine();
            }
            
            //Fecha o arquivo lido.
            arquivoDisciplina.close();            
                        
            //Le a primeira linha do arqEstudante e armazena na variavel.
            String linhaEstudante = arquivoEstudante.readLine();
            
            //Irá percorre o arquivo Estudante até a ultima linha.
            while(linhaEstudante != null){
                
                String[] dadosEstudante = linhaEstudante.split(":");
                
                if(dadosEstudante[3].equals("GRAD")){
                    
                    //Separa cada parte da lista com os dados dos estudantes em variaveis individuais.
                    long idEstudante = Long.valueOf(dadosEstudante[0]);
                    String nomeEstudante = dadosEstudante[1];
                    String emailEstudante = dadosEstudante[2];
                    int horasComplementaresEstudante = Integer.parseInt(dadosEstudante[4]);                    
                    
                    //Usando às informações do arquivo cria um estudante e adiciona a lista de estudantes da faculdade.
                    estudantes.add(new EstudanteGrad(idEstudante, nomeEstudante, emailEstudante, horasComplementaresEstudante));                   
                    
                } else {                    
                                        
                    //Separa cada parte da lista com os dados dos estudantes em variaveis individuais.
                    long idEstudante = Long.valueOf(dadosEstudante[0]);
                    String nomeEstudante = dadosEstudante[1];
                    String emailEstudante = dadosEstudante[2];
                    String temaEstudante = dadosEstudante[4];
                    String orientadorEstudante = dadosEstudante[5];                                        
                    
                    //Usando às informações do arquivo cria um estudante e adiciona a lista de estudantes da faculdade.
                    estudantes.add(new EstudantePos(idEstudante, nomeEstudante, emailEstudante, temaEstudante, orientadorEstudante));                    
                }
                
                //Avança para a proxima linha do arquivo.
                linhaEstudante = arquivoEstudante.readLine();
            }
            
            //Fecha o arquivo lido.
            arquivoEstudante.close();            
                        
            //Le a primeira linha do arqMatricula e armazena na variavel.
            String linhaMatricula = arquivoMatricula.readLine();
            
            //Irá percorre o arquivo Matricula até a ultima linha.
            while(linhaMatricula != null){
                
                //Separa cada parte da linha em variaveis individuais.
                int idEstudante = Integer.parseInt(linhaMatricula.split(":")[0]);
                String codigoDisciplina = linhaMatricula.split(":")[1];
                
                //Declara ás variaveis que receberam os parametros para a criação de uma Matricula.
                Estudante estudante = null;
                Disciplina disciplina = null;
                
                //Busca estudante na lista de estudantes da faculdade.
                //Percorre a lista de estudantes da faculdade.
                for(Estudante indiceEstudante : estudantes){
                    
                    //Verifica se o código no arquivo Matricula está na lista.
                    if(indiceEstudante.getId() == idEstudante){
                        
                        //Caso esteja, guarda o objeto estudante na variavel declarada anteriormente.
                        estudante = indiceEstudante;
                        
                        //Encerra o laço.
                        break;
                    }
                }
                                
                //Busca disciplina na lista de disciplinas da faculdade.
                //Percorre a lista de disciplinas da faculdade.
                for(Disciplina indiceDisciplina : disciplinas){
                    
                    //Verifica se o código no arquivo Matricula está na lista.
                    if(indiceDisciplina.getCodigo().equals(codigoDisciplina)){
                        
                        //Caso esteja, guarda o objeto disciplina na variavel declarada anteriormente.
                        disciplina = indiceDisciplina;
                        
                        //Encerra o laço.
                        break;                        
                    }
                }
                
                //Verifica se o estudante e a disciplina foram encontrados.
                if(estudante != null && disciplina != null){
                    
                    //Instancia e cria uma nova Matricula com as informações do estudante e da disciplina encontados.
                    Matricula matricula = new Matricula(estudante, disciplina);
                    
                    //Adiciona a lista de matriculas do estudante e da discplina a Matricula criada.
                    estudante.addMatricula(matricula);
                    disciplina.addMatricula(matricula);
                }
                
                //Avança para a proxima linha do arquivo.
                linhaMatricula = arquivoMatricula.readLine();
            }
            
            //Fecha o arquivo lido.
            arquivoMatricula.close();
            
        } catch(IOException e){}      
    }
        
    //Métodos Getters.
    public String getNome() {return nome;}

    public List<Estudante> getEstudantes() {return estudantes;}

    public List<Disciplina> getDisciplinas() {return disciplinas;}    
}