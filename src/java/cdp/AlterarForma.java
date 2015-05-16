/*
 * AlterarForma.java
 *
 * Created on 27 de Agosto de 2006, 08:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

/**
 *
 * @author Diego Velasco
 */
public class AlterarForma extends Acao {
    
    private AgenteMovel agenteMovel;
    private String novaForma;
    
    /** Creates a new instance of AlterarForma */
    public AlterarForma() {
    }
    
    public AgenteMovel getAgenteMovel(){
        return this.agenteMovel;
    }
    
    public void setAgenteMovel(AgenteMovel agente){
        this.agenteMovel = agente;
    }

    public String getNovaForma(){
        return this.novaForma;
    }
    
    public void setNovaForma(String forma){
        this.novaForma = forma;
    }
    
    public String getNovaFormaHTML(){
        if (this.novaForma.equals("abelha"))
            return "abelha";	   
        if (this.novaForma.equals("abobora"))
            return "ab&oacute;bora";	   
        if (this.novaForma.equals("agua"))
            return "agua";	   
        if (this.novaForma.equals("alvo"))
            return "alvo";	   
        if (this.novaForma.equals("ambulancia"))
            return "ambul&acirc;ncia";	   
        if (this.novaForma.equals("aranha"))
            return "aranha";	   
        if (this.novaForma.equals("arvore"))
            return "&aacute;rvore";	   
        if (this.novaForma.equals("aviao"))
            return "avi&atilde;o";	   
        if (this.novaForma.equals("aviaoantigo"))
            return "avi&atilde;o antigo";	   
        if (this.novaForma.equals("balao"))
            return "bal&atilde;o";	   
        if (this.novaForma.equals("banana"))
            return "banana";	   
        if (this.novaForma.equals("bandeira"))
            return "bandeira";	   
        if (this.novaForma.equals("barco"))
            return "barco";	   
        if (this.novaForma.equals("barraca"))
            return "barraca";	   
        if (this.novaForma.equals("bicicleta"))
            return "bicicleta";	   
        if (this.novaForma.equals("boladebasquete"))
            return "bola de basquete";	   
        if (this.novaForma.equals("boladetenis"))
            return "bola de t&eacute;nis";	   
        if (this.novaForma.equals("borboleta"))
            return "borboleta";	   
        if (this.novaForma.equals("cachorro"))
            return "cachorro";	   
        if (this.novaForma.equals("cactus"))
            return "cactus";	   
        if (this.novaForma.equals("caixa"))
            return "caixa";	   
        if (this.novaForma.equals("caminhao"))
            return "caminh&atilde;o";	   
        if (this.novaForma.equals("canhao"))
            return "canh&atilde;o";	   
        if (this.novaForma.equals("carro"))
            return "carro";	   
        if (this.novaForma.equals("carro2"))
            return "carro 2";	   
        if (this.novaForma.equals("carta"))
            return "carta";	   
        if (this.novaForma.equals("casa"))
            return "casa";	   
        if (this.novaForma.equals("casa2"))
            return "casa 2";	   
        if (this.novaForma.equals("casa3"))
            return "casa 3";	   
        if (this.novaForma.equals("chave"))
            return "chave";	   
        if (this.novaForma.equals("cilindro"))
            return "cilindro";	   
        if (this.novaForma.equals("circulo1"))
            return "c&iacute;rculo 1";	   
        if (this.novaForma.equals("circulo2"))
            return "c&iacute;rculo 2";	   
        if (this.novaForma.equals("coelho"))
            return "coelho";	   
        if (this.novaForma.equals("computador"))
            return "computador";	   
        if (this.novaForma.equals("coracao"))
            return "cora&ccedil;&atilde;o";	   
        if (this.novaForma.equals("coroa"))
            return "coroa";	   
        if (this.novaForma.equals("cubo"))
            return "cubo";	   
        if (this.novaForma.equals("dardo"))
            return "dardo";	   
        if (this.novaForma.equals("dente"))
            return "dente";	   
        if (this.novaForma.equals("discovoador"))
            return "disco voador";	   
        if (this.novaForma.equals("dolar"))
            return "d&oacute;lar";	   
        if (this.novaForma.equals("domino1"))
            return "domin&oacute; 1";	   
        if (this.novaForma.equals("domino2"))
            return "domin&oacute; 2";	   
        if (this.novaForma.equals("domino3"))
            return "domin&oacute; 3";	   
        if (this.novaForma.equals("domino4"))
            return "domin&oacute; 4";	   
        if (this.novaForma.equals("domino5"))
            return "domin&oacute; 5";	   
        if (this.novaForma.equals("domino6"))
            return "domin&oacute; 6";	   
        if (this.novaForma.equals("escavadora"))
            return "escavadora";	   
        if (this.novaForma.equals("esquilo"))
            return "esquilo";	   
        if (this.novaForma.equals("estrela"))
            return "estrela";	   
        if (this.novaForma.equals("exclamacao"))
            return "exclama&ccedil;&atilde;o";	   
        if (this.novaForma.equals("facefeliz"))
            return "face feliz";	   
        if (this.novaForma.equals("faceneutra"))
            return "face neutra";	   
        if (this.novaForma.equals("facetriste"))
            return "face triste";	   
        if (this.novaForma.equals("falcao"))
            return "falc&atilde;o";	   
        if (this.novaForma.equals("fantasma"))
            return "fantasma";	   
        if (this.novaForma.equals("flor"))
            return "flor";	   
        if (this.novaForma.equals("fogo"))
            return "fogo";	   
        if (this.novaForma.equals("foguete"))
            return "foguete";	   
        if (this.novaForma.equals("folha"))
            return "folha";	   
        if (this.novaForma.equals("formiga"))
            return "formiga";	   
        if (this.novaForma.equals("garrafa"))
            return "garrafa";	   
        if (this.novaForma.equals("gato"))
            return "gato";	   
        if (this.novaForma.equals("gota"))
            return "gota";	   
        if (this.novaForma.equals("hexagono"))
            return "hex&aacute;gono";	   
        if (this.novaForma.equals("ima"))
            return "&iacute;m&atilde;";	   
        if (this.novaForma.equals("industria"))
            return "ind&uacute;stria";	   
        if (this.novaForma.equals("inseto"))
            return "inseto";	   
        if (this.novaForma.equals("lagarta"))
            return "lagarta";	   
        if (this.novaForma.equals("lapis"))
            return "l&aacute;pis";	   
        if (this.novaForma.equals("linha"))
            return "linha";	   
        if (this.novaForma.equals("linhapequena"))
            return "linha pequena";	   
        if (this.novaForma.equals("livro"))
            return "livro";	   
        if (this.novaForma.equals("lixeira"))
            return "lixeira";	   
        if (this.novaForma.equals("lobo"))
            return "lobo";	   
        if (this.novaForma.equals("loja"))
            return "loja";	   
        if (this.novaForma.equals("losango"))
            return "losango";	   
        if (this.novaForma.equals("lua"))
            return "lua";	   
        if (this.novaForma.equals("maca"))
            return "ma&ccedil;&atilde;";	   
        if (this.novaForma.equals("madeira"))
            return "madeira";	   
        if (this.novaForma.equals("moedacara"))
            return "moeda cara";	   
        if (this.novaForma.equals("moedacoroa"))
            return "moeda coroa";	   
        if (this.novaForma.equals("monumento"))
            return "monumento";	   
        if (this.novaForma.equals("morango"))
            return "morango";	   
        if (this.novaForma.equals("notamusical1"))
            return "nota musical 1";	   
        if (this.novaForma.equals("notamusical2"))
            return "nota musical 2";	   
        if (this.novaForma.equals("nuvem"))
            return "nuvem";	   
        if (this.novaForma.equals("olho"))
            return "olho";	   
        if (this.novaForma.equals("onibus"))
            return "&ocirc;nibus";	   
        if (this.novaForma.equals("ovelha"))
            return "ovelha";	   
        if (this.novaForma.equals("ovo"))
            return "ovo";	   
        if (this.novaForma.equals("passaro"))
            return "p&aacute;ssaro";	   
        if (this.novaForma.equals("pedras"))
            return "pedras";	   
        if (this.novaForma.equals("pegada"))
            return "pegada";	   
        if (this.novaForma.equals("peixe"))
            return "peixe";	   
        if (this.novaForma.equals("pentagono"))
            return "pent&aacute;gono";	   
        if (this.novaForma.equals("pessoa"))
            return "pessoa";	   
        if (this.novaForma.equals("pessoaempresario"))
            return "pessoa empres&aacute;rio";	   
        if (this.novaForma.equals("pessoaestudante"))
            return "pessoa estudante";	   
        if (this.novaForma.equals("pessoafazendeiro"))
            return "pessoa fazendeiro";	   
        if (this.novaForma.equals("pessoaformando"))
            return "pessoa formando";	   
        if (this.novaForma.equals("pessoagarcom"))
            return "pessoa gar&ccedil;om";	   
        if (this.novaForma.equals("pessoagari"))
            return "pessoa gari";	   
        if (this.novaForma.equals("pessoalenhador"))
            return "pessoa lenhador";	   
        if (this.novaForma.equals("pessoamedico"))
            return "pessoa m&eacute;dico";	   
        if (this.novaForma.equals("pessoapolicial"))
            return "pessoa policial";	   
        if (this.novaForma.equals("pessoasoldado"))
            return "pessoa soldado";	   
        if (this.novaForma.equals("pincel"))
            return "pincel";	   
        if (this.novaForma.equals("pinheiro"))
            return "pinheiro";	   
        if (this.novaForma.equals("pinodeboliche"))
            return "pino de boliche";	   
        if (this.novaForma.equals("planta"))
            return "planta";	   
        if (this.novaForma.equals("polvo"))
            return "polvo";	   
        if (this.novaForma.equals("ponto"))
            return "ponto";	   
        if (this.novaForma.equals("quadrado1"))
            return "quadrado 1";	   
        if (this.novaForma.equals("quadrado2"))
            return "quadrado 2";	   
        if (this.novaForma.equals("raio"))
            return "raio";	   
        if (this.novaForma.equals("rato"))
            return "rato";	   
        if (this.novaForma.equals("relogio"))
            return "rel&oacute;gio";	   
        if (this.novaForma.equals("roda"))
            return "roda";	   
        if (this.novaForma.equals("sapo"))
            return "sapo";	   
        if (this.novaForma.equals("seta"))
            return "seta";	   
        if (this.novaForma.equals("setafina"))
            return "seta fina";	   
        if (this.novaForma.equals("setapadrao"))
            return "seta padr&atilde;o";	   
        if (this.novaForma.equals("sol"))
            return "sol";	   
        if (this.novaForma.equals("tanque"))
            return "tanque";	   
        if (this.novaForma.equals("tartaruga"))
            return "tartaruga";	   
        if (this.novaForma.equals("telefone"))
            return "telefone";	   
        if (this.novaForma.equals("tijolos"))
            return "tijolos";	   
        if (this.novaForma.equals("trem"))
            return "trem";	   
        if (this.novaForma.equals("triangulo1"))
            return "tri&acirc;ngulo 1";	   
        if (this.novaForma.equals("triangulo2"))
            return "tri&acirc;ngulo 2";	   
        if (this.novaForma.equals("tubarao"))
            return "tubar&atilde;o";	   
        if (this.novaForma.equals("vaca"))
            return "vaca";	   
        if (this.novaForma.equals("vagao"))
            return "vag&atilde;o";	   
        if (this.novaForma.equals("van"))
            return "van";	   
        if (this.novaForma.equals("x"))
            return "x";	   
        if (this.novaForma.equals("xadrezbispo"))
            return "xadrez bispo";	   
        if (this.novaForma.equals("xadrezcavalo"))
            return "xadrez cavalo";	   
        if (this.novaForma.equals("xadrezpeao"))
            return "xadrez pe&atilde;o";	   
        if (this.novaForma.equals("xadrezrainha"))
            return "xadrez rainha";	   
        if (this.novaForma.equals("xadrezrei"))
            return "xadrez rei";	   
        if (this.novaForma.equals("xadreztorre"))
            return "xadrez torre";	 
        
        return this.novaForma;
    }
    
}
