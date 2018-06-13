/*
 * Para alterar esse cabeçalho de licença, escolha Cabeçalhos de licença em Propriedades do projeto.
 * Para alterar este arquivo de modelo, escolha Ferramentas | Modelos
 * e abra o modelo no editor.
 */
package cep;

/**
 *
 * @author Marcos Júnior <marcosneri@outlook.com.br>
 */
public class Cep {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Cep();
    }

    public Cep() {
        try {
            String cep = "64600002";

            CepWebService cepWebService = new CepWebService(cep);

            if (cepWebService.getResultado() == 1) {
                System.out.println(cepWebService.getTipo_logradouro() + " " + cepWebService.getLogradouro());
                System.out.println(cepWebService.getBairro());
                System.out.println(cepWebService.getCidade());
                System.out.println(cepWebService.getEstado());
                System.out.println(cepWebService.getResultado());
                System.out.println("Resultado: "+cepWebService.getResultado_txt());
            } else {
                System.out.println("Servidor não está respondendo.");
            }
        } catch (Exception ex) {
        }
    }

}
