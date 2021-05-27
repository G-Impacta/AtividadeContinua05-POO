package Projeto_v2;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class GestaoAcademicaApp2 {
    public static void main(String[] args) {
        
        //Declaração de Variaveis.
        String arquivoDisciplina = "Disciplinas.txt"; //Caminho do arquivo.
        String arquivoEstudante = "Estudantes.txt"; //Caminho do arquivo.
        String arquivoMatricula = "Matriculas.txt"; //Caminho do arquivo.
        String nomeFaculdade = "Faculdade Impacta"; //Nome da faculdade.
        int escolha; //Variavel flag.
        int loop = 1; //Variavel flag.
        
        //Instancia o Scanner para entrada de dados do usuário.
        Scanner entrada = new Scanner(System.in);
        
        //Instancia e cria uma Faculdade.
        Faculdade faculdade = new Faculdade(nomeFaculdade);
        
        try{
            
            //Carrega os dados do arquivo para a faculdade.
            faculdade.carregarDadosArquivo(arquivoDisciplina, arquivoEstudante, arquivoMatricula);
            
        }catch(FileNotFoundException erro){
            
            //Mensagem de erro, caso o arquivo não seja encontrado.
            System.out.println(erro.getMessage());            
                        
            //E o programa será encerrado.
            System.exit(0);
        }
        
        //Loop de interação com o usuário.
        while(loop == 1){
            
            //Apresenta o nome da faculdade acessada.
            System.out.println("Você está em " + faculdade.getNome() + "\n");
            
            //Informa a lista de opções disponiveis para o usuário.
            System.out.println("Escolha uma opção:");
            System.out.println("[0] Listar Estudantes da Faculdade");
            System.out.println("[1] Listar Disciplinas da Faculdade");
            System.out.println("[2] Listar Estudantes Matriculados em uma Disciplina");
            System.out.println("[3] Listar Disciplinas que Estudante está Matriculado");
            System.out.println("[4] Encerrar Programa");
            
            //Captura e armazena a entrada do usuário.
            escolha = entrada.nextInt();            
            
            //Defini quais métodos serão chamados a partir da entrada do usuário.
            switch(escolha){
                
                //Caso a entrada do usuário seja 0.
                case 0:
                    
                    //Invoca o método listar estudantes.
                    listarEstudantes(faculdade);
                    break; //Interrompe o case.
                
                //Caso a entrada do usuário seja 1.
                case 1:
                    
                    //Invoca o método listar disciplinas.
                    listarDisciplinas(faculdade);
                    break; //Interrompe o case.
                    
                //Caso a entrada do usuário seja 2.    
                case 2:
                    
                    //Solicita ao usuário o código da disciplina.
                    System.out.print("Informe o código da disciplina: ");
                    
                    //Limpa a memoria da variavel de entrada de dados.
                    entrada = new Scanner(System.in);
                    
                    //Captura e armazena a entrada do usuário.
                    String codigoDisciplina = entrada.nextLine();
                    
                    //Invoca o método listar estudantes matriculados na disciplina.
                    listarEstudantesMatriculados(faculdade, codigoDisciplina);
                    break; //Interrompe o case.
                
                //Caso a entrada do usuário seja 2.
                case 3:
                    
                    //Solicita ao usuário o código do estudante.
                    System.out.print("Informe o código do estudante: ");                    
                                        
                    //Limpa a memoria da variavel de entrada de dados.
                    entrada = new Scanner(System.in);
                    
                    //Captura e armazena a entrada do usuário.
                    long codigoEstudante = entrada.nextLong();
                    
                    //Invoca o método listar disciplinas em que estudantes estão matriculados.
                    listarDisciplinasMatriculadas(faculdade, codigoEstudante);
                    break; //Interrompe o case.
                
                //Caso a entrada do usuário seja 4.    
                case 4:
                    
                    //Altera a variavel flag, e encerra o loop de interação.
                    loop = -1;
                    break; //Interrompe o case.
                
                //Caso a entrada do usuário não coincida com as opções disponiveis. 
                default:
                    
                    //Apresenta uma mensagem.
                    System.out.println("Opção invalida!");                    
            }
        }
    }    
        
    //O método irá imprimir na tela a lista de todos os estudantes registrados na faculdade.
    public static void listarEstudantes(Faculdade faculdade){
        
        //Armazena a lista de estudantes da faculdade.
        List<Estudante> estudantes = faculdade.getEstudantes();
        
        //Armazenará o indice de cada estudante da lista.
        int indiceEstudante = 0;
        
        //Mensagem de informação.
        System.out.println("\nLista de Estudantes:");
        
        //Percorre a lista de estudantes da faculdade.
        for(Estudante estudante : estudantes){            
            
            if(estudante instanceof EstudanteGrad){
                
                //Imprimi no console ás informações de cada estudante da lista.
                System.out.println("[" + indiceEstudante + "] Número: " + 
                        ((EstudanteGrad)estudante).id + ", Nome: " + 
                        ((EstudanteGrad)estudante).nome + ", Créditos: " + 
                        ((EstudanteGrad)estudante).getTotalCreditos() + 
                        ", Horas Complementares: " + ((EstudanteGrad)estudante).horasAtividades);
                
            } else {
                
                //Imprimi no console ás informações de cada estudante da lista.
                System.out.println("[" + indiceEstudante + "] Número: " + 
                        ((EstudantePos)estudante).id + ", Nome: " + 
                        ((EstudantePos)estudante).nome + ", Créditos: " + 
                        ((EstudantePos)estudante).getTotalCreditos() + ", Tema: " + 
                        ((EstudantePos)estudante).tema + ", Orientador: " + 
                        ((EstudantePos)estudante).orientador);
            }
            
            //Acrescenta-se +1 ao indice dos estudantes.
            indiceEstudante += 1;
        }
        
        //Quebra de linha;
        System.out.println();
    }    
        
    //O método irá imprimir na tela a lista de todas as disciplinas registradas na faculdade.
    public static void listarDisciplinas(Faculdade faculdade){
        
        //Armazena a lista de disciplinas da faculdade.
        List<Disciplina> disciplinas = faculdade.getDisciplinas();
        
        //Armazenará o indice de cada disciplina da lista.
        int indiceDisciplina = 0;        
                
        //Mensagem de informação.
        System.out.println("\nLista de Disciplinas:");
        
        //Percorre a lista de disciplinas da faculdade.
        for(Disciplina disciplina : disciplinas){
            
            //Imprimi no console ás informações de cada disciplina da lista.
            System.out.println("[" + indiceDisciplina + "] " + "Código: " +
                    disciplina.getCodigo());
            
            //Acrescenta-se +1 ao indice das disciplinas.
            indiceDisciplina += 1;
        }
                
        //Quebra de linha;
        System.out.println();
    }    
        
    //O método irá buscar uma disciplina da faculdade a partir do código da disciplina.
    public static Disciplina buscarDisciplina(Faculdade faculdade, String registro){
        
        //Armazena a lista de disciplinas da faculdade.
        List<Disciplina> disciplinas = faculdade.getDisciplinas();
        
        //Percorre a lista de disciplinas da faculdade.
        for(Disciplina disciplina : disciplinas){
            
            //Verifica se o registro informado é igual ao da disciplina.
            if(disciplina.getCodigo().equals(registro)){
                
                //Caso seja, retorna a disciplina.
                return disciplina;
            }
        }
        
        //Caso não encontre o registro informado retorna null.
        return null;
    }   
        
    //O método irá imprimir na tela a lista de todos os estudantes participantes da disciplina.
    public static void listarEstudantesMatriculados(Faculdade faculdade, String codigoDisciplina){
        
        //Irá buscar uma disciplina a partir do código informado e guardará na variavel.
        Disciplina disciplina = buscarDisciplina(faculdade, codigoDisciplina);
        
        //Caso não seja encontrada uma disciplina,
        if(disciplina == null){
            
            //Uma mensagem será impressa na tela,
            System.out.println("Código da disciplina não encontrado!");
            
            //E o programa será encerrado.
            System.exit(0);
        }
        
        //Caso a disciplina seja encontrada, armazenará a lista de matriculas na variavel.
        List<Matricula> matriculas = disciplina.getMatriculas();        
                        
        //Mensagem de informação.
        System.out.println("\nDisciplina: " + disciplina.getCodigo() + "\n");
        
        //Mensagem de informação.
        System.out.println("Lista de Estudantes:");
        
        //Armazenará o indice de cada matricula da lista.
        int indiceMatricula = 0;
        
        //Percorre a lista de matriculas da disciplina.
        for(Matricula matricula : matriculas){
            
            //Armazena o estudante registrado na matricula.
            Estudante estudante = matricula.getEstudante();           
                        
            if(estudante instanceof EstudanteGrad){
                
                //Imprimi no console ás informações de cada estudante da lista.
                System.out.println("[" + indiceMatricula + "] Número: " + 
                        ((EstudanteGrad)estudante).id + ", Nome: " + 
                        ((EstudanteGrad)estudante).nome + ", E-Mail: " + 
                        ((EstudanteGrad)estudante).email + ", Créditos: " + 
                        ((EstudanteGrad)estudante).getTotalCreditos() + 
                        ", Horas Complementares: " + ((EstudanteGrad)estudante).horasAtividades);
                
            } else {
                
                //Imprimi no console ás informações de cada estudante da lista.
                System.out.println("[" + indiceMatricula + "] Número: " + 
                        ((EstudantePos)estudante).id + ", Nome: " + 
                        ((EstudantePos)estudante).nome + ", E-Mail: " + 
                        ((EstudantePos)estudante).email + ", Créditos: " + 
                        ((EstudantePos)estudante).getTotalCreditos() + ", Tema: " + 
                        ((EstudantePos)estudante).tema + ", Orientador: " + 
                        ((EstudantePos)estudante).orientador);
            }
            
            //Acrescenta-se +1 ao indice das matriculas.
            indiceMatricula += 1;
        }
        
        //Imprimi o total de estudantes encontrados na disciplina.
        System.out.println("\nForam encontrados " + indiceMatricula + " estudantes matriculados nesta disciplina.");
        
        //Quebra de linha;
        System.out.println();
    }    
            
    //O método irá buscar um estudante da faculdade a partir do código do estudante.
    public static Estudante buscarEstudante(Faculdade faculdade, long registro){
        
        //Armazena a lista de estudantes da faculdade.
        List<Estudante> estudantes = faculdade.getEstudantes();
        
        //Percorre a lista de estudantes da faculdade.
        for(Estudante estudante : estudantes){
            
            //Verifica se o registro informado é igual ao do estudante.
            if(estudante.getId() == registro){
                
                //Caso seja, retorna o estudante.
                return estudante;
            }
        }
        
        //Caso não encontre o registro informado retorna null.
        return null;
    }    
        
    public static void listarDisciplinasMatriculadas(Faculdade faculdade, long codigoEstudante){        
                
        //Irá buscar um estudante a partir do código informado e guardará na variavel.
        Estudante estudante = buscarEstudante(faculdade, codigoEstudante);        
                
        //Caso não seja encontrado um estudante,
        if(estudante == null){
            
            //Uma mensagem será impressa na tela,
            System.out.println("Código do estudante não encontrado!");
            
            //E o programa será encerrado.
            System.exit(0);
        }        
                
        //Caso o estudante seja encontrado, armazenará a lista de matriculas na variavel.
        List<Matricula> matriculas = estudante.getMatriculas();        
                
        //Armazenará o indice de cada matricula da lista.
        int indiceMatricula = 0;
                                
        //Mensagem de informação.
        System.out.println("\nEstudante: " + estudante.getNome() + "\n");
        
        //Mensagem de informação.
        System.out.println("Lista de Disciplinas:");
                
        //Percorre a lista de matriculas do estudante.
        for(Matricula matricula : matriculas){            
                        
            //Armazena a disciplina registrada na matricula.
            Disciplina disciplina = matricula.getDisciplina();
            
            System.out.println("[" + indiceMatricula +"] " + "Código: " + disciplina.getCodigo() 
                    + ", Créditos: " + disciplina.getCreditos());            
                        
            //Acrescenta-se +1 ao indice das matriculas.
            indiceMatricula += 1;
        }
        
        //Imprimi o total de creditos que o estudante possui.
        System.out.println("\nTotal de créditos do estudante: " + estudante.getTotalCreditos());
                
        //Imprimi o total de disciplinas encontradas para o estudante.
        System.out.println("\nForam encontrados " + indiceMatricula + " disciplinas para este estudante.");
                
        //Quebra de linha;
        System.out.println();        
    }    
}