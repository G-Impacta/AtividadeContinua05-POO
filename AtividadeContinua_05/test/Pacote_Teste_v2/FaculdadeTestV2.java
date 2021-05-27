package Pacote_Teste_v2;

import Projeto_v2.Disciplina;
import Projeto_v2.Estudante;
import Projeto_v2.EstudanteGrad;
import Projeto_v2.EstudantePos;
import Projeto_v2.Faculdade;
import Projeto_v2.Matricula;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

public class FaculdadeTestV2 {
    
    private final Faculdade faculdade; //Armazena a referencia a Faculdade.
    private final String nomeFaculdadeEsperado; //Armazena o nome esperado da faculdade.
    
    private final ArrayList<String[]> disciplinasArray; //Armazena os dados lidos do arquivo disciplina.
    private final ArrayList<String[]> estudantesArray; //Armazena os dados lidos do arquivo estudante.
    private final ArrayList<String[]> matriculasArray; //Armazena os dados lidos do arquivo matricula.
    
    public FaculdadeTestV2() throws FileNotFoundException{

        String arquivoDisciplina = "Disciplinas.txt"; //Caminho do arquivo.
        String arquivoEstudante = "Estudantes.txt"; //Caminho do arquivo.
        String arquivoMatricula = "Matriculas.txt"; //Caminho do arquivo.
        
        nomeFaculdadeEsperado = "Faculdade Impacta de Tecnologia"; //Defini o nomde da Faculdade.
        faculdade = new Faculdade(nomeFaculdadeEsperado); //Cria uma FAculdade.
        
        //Carrega os dados na classe Faculdade.
        faculdade.carregarDadosArquivo(arquivoDisciplina, arquivoEstudante, arquivoMatricula);
                
        this.disciplinasArray = new ArrayList<>(); //Inicia a lista de dados do arquivo disciplinas.
        this.estudantesArray = new ArrayList<>(); //Inicia a lista de dados do arquivo estudantes.
        this.matriculasArray = new ArrayList<>(); //Inicia a lista de dados do arquivo matriculas.
        
        lerArquivo(arquivoDisciplina, disciplinasArray); //Le o arquivo disciplinas e guarda os dados no arraylist.
        lerArquivo(arquivoEstudante, estudantesArray); //Le o arquivo estudantes e guarda os dados no arraylist.
        lerArquivo(arquivoMatricula, matriculasArray); //Le o arquivo matriculas e guarda os dados no arraylist.
    }
    
    //Le um arquivo e grava as informações em um array list.
    public void lerArquivo(String arquivo, ArrayList colecao) throws FileNotFoundException{        
                                
        //Variaveis com os Buffers dos arquivos lidos.
        BufferedReader arquivoLido = new BufferedReader(new FileReader(arquivo));        
                
        try { //Trata uma possivel exceção.            
                        
            //Le a primeira linha do arquivo e armazena na variavel.
            String linhaArquivo = arquivoLido.readLine();
            
            //Irá percorre o arquivo até a ultima linha.
            while(linhaArquivo != null){
                
                //Adiciona as informações do arquivo em um array.
                colecao.add(linhaArquivo.split(":"));
                
                //Avança para a proxima linha do arquivo.
                linhaArquivo = arquivoLido.readLine();
            }
            
            //Fecha o arquivo lido.
            arquivoLido.close();
        
        //Libera uma mensagem de erro.    
        } catch(IOException error){System.out.println("Não foi possivel ler o arquivo.");}
    }    
    
    //Procura um estudante na lista de estudantes pelo id.
    public Estudante findEstudanteById(int id, List<Estudante> estudantes) {
        
        //Percorre a lista.
        for (Estudante estudante : estudantes) {
            
            if (id == estudante.getId()) { //Verifica se o id informado é igual ao id do estudante atual.
                
                return estudante; //Caso seja retorna o estudante encontrado.
            }
        }
        
        return null; //Caso não encontre o estudante retorna null.
    }    
    
    @Test
    public void testNomeFaculdade() { //Testa a classe de Faculdade.
        
        String nomeObtido = faculdade.getNome(); //Armazena o nome da faculdade.
        
        //Testa se o nome da faculdade corresponde ao nome esperado.
        assertEquals(nomeFaculdadeEsperado, nomeObtido);
    }
    
    @Test
    public void testEstudantes() { //Testa a classe de Estudantes.
        
        //Armazena a coleção de estudantes da faculdade.
        List<Estudante> estudantes = faculdade.getEstudantes();
        
        int numeroEsperado = estudantesArray.size(); //Armazena o total de estudantes contidos no array list.
        int numeroObtido = estudantes.size(); //Armazena o total de alunos contidos na faculdade.
        
        //Testa se quantidade de alunos na faculdade é igual a quantidade de alunos do array.
        Assert.assertEquals(numeroEsperado, numeroObtido);
        
        //Percorre o arraylist de alunos obtido através do arquivo.
        for (String[] estudanteArray : estudantesArray) {
            
            int id = Integer.parseInt(estudanteArray[0]); //Armazena o id do aluno atual.
            
            //Verifica se o mesmo id se encontra na lista de alunos da faculdade.
            Estudante estudanteEncontrado = findEstudanteById(id, estudantes);
            
            //Testa se o estudante foi encontrado.
            assertNotNull(estudanteEncontrado);
            
            //Para cada tipo de estudante realiza um teste especifico.
            if(estudanteArray[3].equals("GRAD")){               

                String nomeEsperado = estudanteArray[1]; //Armazena o nome do aluno atual.
                String emailEsperado = estudanteArray[2]; //Armazena o email do aluno atual.
                int horasAtividadesEsperado = Integer.parseInt(estudanteArray[4]); //Armazena a quantidade de horas de atividades do aluno atual.     
                
                assertEquals(nomeEsperado, estudanteEncontrado.getNome()); //Testa se o nome coincidi com o aluno atual.
                assertEquals(emailEsperado, estudanteEncontrado.getEmail()); //Testa se o email coincidi com o aluno atual.
                
                //Testa se a quantidade de horas de atividades coincidi com o aluno atual.
                assertEquals(horasAtividadesEsperado, ((EstudanteGrad)estudanteEncontrado).getHorasAtividades());
                
            } else {                
                
                String nomeEsperado = estudanteArray[1]; //Armazena o nome do aluno atual.
                String emailEsperado = estudanteArray[2]; //Armazena o email do aluno atual.
                String temaEsperado = estudanteArray[4]; //Armazena o tema do aluno atual.
                String orientadorEsperado = estudanteArray[5]; //Armazena o orientador do aluno atual.

                assertEquals(nomeEsperado, estudanteEncontrado.getNome()); //Testa se o nome coincidi com o aluno atual.
                assertEquals(emailEsperado, estudanteEncontrado.getEmail()); //Testa se o email coincidi com o aluno atual.
                
                assertEquals(temaEsperado, ((EstudantePos)estudanteEncontrado).getTema()); //Testa se tema coincidi com o aluno atual.
                assertEquals(orientadorEsperado, ((EstudantePos)estudanteEncontrado).getOrientador()); //Testa se o orientador coincidi com o aluno atual.
            }
        }
    }    

    //Procura uma disciplina na lista de disciplinas pelo código.
    public Disciplina findDisciplinaByCodigo(String codigo, List<Disciplina> disciplinas) {
        
        //Percorre a lista.
        for (Disciplina disciplina : disciplinas) {
            
            if (codigo.equals(disciplina.getCodigo())) { //Verifica se o código informado é igual ao código da disciplina atual.
                
                return disciplina; //Caso seja retorna a disciplina encontrada.
            }
        }
        
        return null; //Caso não encontre a disciplina retorna null.
    }
    
    @Test
    public void testDisciplinas() { //Testa a classe de Disciplinas.
        
        //Armazena a coleção de disciplinas da faculdade.
        List<Disciplina> disciplinas = faculdade.getDisciplinas();
        
        int numeroEsperado = disciplinasArray.size(); //Armazena o total de disciplinas contidas no array list.
        int numeroObtido = disciplinas.size(); //Armazena o total de disciplinas contidas na faculdade.
        
        //Testa se quantidade de disciplinas na faculdade é igual a quantidade de disciplinas do array.
        assertEquals(numeroEsperado, numeroObtido);
        
        //Percorre o arraylist de disciplinas obtido através do arquivo.
        for (String[] disciplinaArray : disciplinasArray) {
            
            String codigo = disciplinaArray[0]; //Armazena o código da disciplina atual.
            int creditos = Integer.parseInt(disciplinaArray[1]); //Armazena a quantidade de créditos da disciplina atual.
            
            //Verifica se o mesmo código se encontra na lista de disciplinas da faculdade.
            Disciplina disciplinaEncontrada = findDisciplinaByCodigo(codigo, disciplinas);
            
            //Testa se a disciplina foi encontrado.
            assertNotNull(disciplinaEncontrada);
            
            //Testa se os creditos coincidi com a disciplina atual.
            assertEquals(creditos, disciplinaEncontrada.getCreditos());
        }
    }
    
    //Procura na lista de matriculas o total de matriculas de um estudante.
    public int findEstudanteInMatriculas(int id){
        
        int total = 0; //Armazena o total de estudantes encontrados.
        
        //Percorre a lista.
        for (String[] matriculasArray : matriculasArray) {
            
            //Verifica se o id corresponde ao estudante e caso corresponda soma-se +1.
            if((id == Integer.parseInt(matriculasArray[0]))){total++;} 
        }
        
        return total; //Retorna o total de matriculas por estudante.
    }    
        
    //Procura na lista de matriculas o total de matriculas de uma disciplina.
    public int findDisciplinaInMatriculas(String codigo){
        
        int total = 0; //Armazena o total de disciplinas encontradas.
        
        //Percorre a lista.
        for (String[] matriculasArray : matriculasArray) {
            
            //Verifica se o código corresponde a disciplina e caso corresponda soma-se +1.
            if((codigo.equals(matriculasArray[1]))){total++;}
        }
        
        return total; //Retorna o total de matriculas por disciplina.
    }
    
    @Test
    public void testMatriculas() { //Testa a classe de Matricula.
        
        List<Estudante> estudantes = faculdade.getEstudantes(); //Armazena a lista de estudantes da faculdade.
        List<Disciplina> disciplinas = faculdade.getDisciplinas(); //Armazena a lista de disciplinas da faculdade.
        List<Matricula> matriculas =  null; //Armazena uma lista de matricula.
        
        //Percorre a lista de estudantes.
        for (int i = 0; i < estudantesArray.size(); i++) {
            
            String[] estudanteArray = estudantesArray.get(i); //Armazena os dados do estudante.
            
            int id = Integer.parseInt(estudanteArray[0]); //Armazena o id do estudante atual.
            
            //Verifica se o mesmo id se encontra na lista de alunos da faculdade.
            Estudante estudante = findEstudanteById(id, estudantes);           
                        
            //Testa se o estudante foi encontrado.
            assertNotNull(estudante);
            
            //Testa se o total de estudantes matriculados do arraylist corresponde ao total de matriculas de um estudante.
            assertEquals(findEstudanteInMatriculas(id), estudante.getMatriculas().size());
        }
        
        //Percorre a lista de disciplinas.
        for (int i=0; i<disciplinasArray.size(); i++) {
            
            String[] disciplinaArray = disciplinasArray.get(i); //Armazena os dados da disciplina.
            
            String codigo = disciplinaArray[0]; //Armazena o código da disciplina atual.            
            
            //Verifica se o mesmo código se encontra na lista de disciplinas da faculdade.
            Disciplina disciplina = findDisciplinaByCodigo(codigo, disciplinas);
            
            //Testa se a disciplina foi encontrado.
            assertNotNull(disciplina);
            
            //Testa se o total de matriculas por disciplinas corresponde ao total de matriculas da disciplina.
            assertEquals(findDisciplinaInMatriculas(codigo), disciplina.getMatriculas().size());
        }        
        
        //Percorre a lista de matriculas.
        for (String[] matriculaArray : matriculasArray) {
            
            int idEstudante = Integer.parseInt(matriculaArray[0]); //Armazena o id do estudante atual.
            String codigoDisciplina = matriculaArray[1]; //Armazena o código da disciplina atual.
            
            //Verifica se o mesmo id se encontra na lista de alunos da faculdade.
            Estudante estudante = findEstudanteById(idEstudante, estudantes);
            
            //Verifica se o mesmo código se encontra na lista de disciplinas da faculdade.
            Disciplina disciplina = findDisciplinaByCodigo(codigoDisciplina, disciplinas);
            
            Matricula matriculaEncontradaInEstudante = null; //Armazena a matricula encontrada em estudante.
            matriculas = estudante.getMatriculas(); //Armazena a lista de matriculas da faculdade.
            
            //Percorre a lista de matriculas da faculdade.
            for (Matricula matricula : matriculas) {
                
                //Verifica se o estudante e a disciplina corresponde a matricula atual.
                if (codigoDisciplina.equals(matricula.getDisciplina().getCodigo()) 
                        && idEstudante == matricula.getEstudante().getId()) {
                    
                    //Caso corresponda armazena a referencia da matricula encontrada.
                    matriculaEncontradaInEstudante = matricula;
                    
                    break; //Encerra o loop.
                }
            }
            
            //Testa se a matricula foi encontrada.
            assertNotNull(matriculaEncontradaInEstudante);
            
            Matricula matriculaEncontradaInDisciplina = null; //Armazena a matricula encontrada em disciplina.
            matriculas = disciplina.getMatriculas(); //Armazena a lista de matriculas da faculdade.
            
            //Percorre a lista de matriculas da faculdade.
            for (Matricula matricula : matriculas) {
                
                //Verifica se o estudante e a disciplina corresponde a matricula atual.
                if (codigoDisciplina.equals(matricula.getDisciplina().getCodigo()) 
                           && idEstudante == matricula.getEstudante().getId()) {
                    
                    //Caso corresponda armazena a referencia da matricula encontrada.
                    matriculaEncontradaInDisciplina = matricula;
                    
                    break; //Encerra o loop.
                }
            }
            
            //Testa se a matricula foi encontrada.
            assertNotNull(matriculaEncontradaInDisciplina);
        }
    }    
}
