package modulo.versao;

/**
 *
 * @author Marcos Junior
 */
public class Versao {

    private String nomesys = "Sistema de Controle de Loja";
    private String versao = "v2.3.2";
    private String notas = ""
            + "" + nomesys + " " + versao + "\n"
            + "Data do Desenvolvimento: 26/02/2018.\n"
            + "Data da Implantação: 20/04/2018.\n\n"
            + "Nota da Versão 2.2.2\n"
            + "Data da Atualização: 30/09/2018.\n"
            + "Resolvido problema de dupicidade ao cadastrar usuário.\n"
            + "Resolvido problema que não permitia alterar o cadastro da lista de ruptura.\n"
            + "Correção de erros nos campos dos formulários.\n"
            + "Inclusão do acompanhamento de Água e Luz.\n"
            + "Inclusão do acompanhamento do Índice do Inventário.\n"
            + "Incluisão do Nível de Acesso agora com nível de acordo com seu cargo.\n"
            + "Implementação do DashBoard Inclusão dos gráficos do Indice de Invetário Loja.\n"
            + "Implementação do DashBoard Inclusão dos gráficos de contas de Água e Energia.\n"
            + "Implementação do DashBoard Inclusão dos gráficos da Lista de Ruptura.\n"
            + "Implementação do Sistema de atualização de Tabelas em fase de testes.\n"
            + "Inclusão da atualização de tabelas (Cargos Mundanção no Nível de Acesso).\n"
            + "Notas do Sistema Implementado.\n"
            + "Tempo da animação ao logar no sistema ajustado.\n"
            + "Correção de bugs e ajustes de modo geral.\n"
            + "Correção de bugs ao Logar no Sistema Sistema fechava ao tentar acessar com matrícula errada.\n\n\n"
            
            + "Nota da Versão 2.3.2\n"
            + "Data da Atualização: 20/10/2018.\n"
            + "Implementação do modulo campanhas versão ALFA.\n"
            + "Cadastro de produtos de campanhas.\n"
            + "Cadastro de metas para os produtos de campanha.\n"
            + "Acompanhamentos e manutenção das vendas.\n"
            + "Relatórios e Gráficos em desenvolvimento.\n"
            + "Menu disponível mais não funcional.\n"
            + "Implementação do sistema de autorização de terminal.\n"
            + "Agora se permite liberar um terminal para seu uso.\n"
            + "Correção de erros e ajuste no nível de acesso.\n"
            + "";
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
