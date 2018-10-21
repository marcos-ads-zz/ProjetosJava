package modulo.versao;

/**
 *
 * @author Marcos Junior
 */
public class Versao {

    private String nomesys = "Sistema de Controle de Loja";
    private String versao = "v2.3.3";
    private String notas = "Notas da "+versao+"\n"
            + "Nota da Versão v2.3.3\n"
            + "Implementação do modulo campanhas versão ALFA\n"
            + "Implementação do menu gráficos de produtos em falta.\n"
            + "Acompanhamentos de vendas em fase de desenvolvimento\n"
            + "Relatórios e Gráficos em desenvolvimento\n"
            + "Implementação do sistema de autorização de terminal\n"
            + "Segurança da janela de configuração implementada\n"
            + "Sistema de configuração da base de dados melhorado e colocado senha para acesso\n"
            + "API para conexão em desenvolvimento"
            + "Correção de bugs\n"
            + "Não é mais possível cadastrar uma falta com quantidade zerada\n"
            + "Abas Caixa, Farmaceutico, Dermoconsultora e Balconista foram removidos\n"
            + "para dar lugar a aba Campanhas\n"
            + "Erros do botões da aba campanha corrigidos";
    private String licenca = ""
            + "    " + nomesys + " é um software livre; você pode redistribuí-lo e/ou \n"
            + "    modificá-lo dentro dos termos da Licença Pública Geral GNU como \n"
            + "    publicada pela Fundação do Software Livre (FSF); na versão 3 da \n"
            + "    Licença, ou (a seu critério) qualquer versão posterior.\n"
            + "\n"
            + "    Este programa é distribuído na esperança de que possa ser  útil, \n"
            + "    mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO\n"
            + "    a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a\n"
            + "    Licença Pública Geral GNU para maiores detalhes.\n"
            + "\n"
            + "    Você deve ter recebido uma cópia da Licença Pública Geral GNU junto\n"
            + "    com este programa, Se não, veja <http://www.gnu.org/licenses/>.";
    private String info = "Dados do Sistema/n"
            + "Desenvolvedor Marcos Neri\n"
            + "Data de Lançamento da 1ª Versão 26/02/2018\n"
            + "Licença Pública Geral GNU\n"
            + "Product Version: Sistema de Controle de Loja " + versao + "\n"
            + "Atualizações: O Sistema está atualizado para a versão Controle de Loja " + versao + "\n"
            + "Desenvolvido em Java 8\n"
            + "IDE utilizada NetBeans IDE 8.2 (Build 201705191307)\n"
            + "Banco de Dados Utilizado: PostgreSQL 10.3 Released!\n";

    public String getNomesys() {
        return nomesys;
    }

    public String getVersao() {
        return versao;
    }

    public String getNotas() {
        return notas;
    }

    public String getLicenca() {
        return licenca;
    }

    public String getInfo() {
        return info;
    }


}
