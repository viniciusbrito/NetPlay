/* Traducao.java
 *
 * Created on 7 de Setembro de 2006, 13:14
 */

package interfaceNetLogo;
import cdp.*;
import cgt.*;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class Traducao {
    
    public Traducao() 
    {
    }
    
    //Traduz expressao
    public static String expressao (String expr, Modelo modelo)
    {System.out.println("expr = " + expr + "\n");
        String expressaoOriginal = expr.replaceAll("  ", " ");
        String expressao = "";
        boolean adicionado = false;
        
        String[] comandos = expressaoOriginal.split(" ");
        for(int i=0; i<comandos.length; i++, adicionado = false)
        {System.out.println("com ["+ i + "] = " + comandos[i] + "\n");
            //Traduzindo contador
            if(comandos[i].equals("cont"))
            {
                expressao += "count";
                adicionado = true;
            }
            
            //Traduzindo contagem de agentes
            if(comandos[i].length() > 0 && comandos[i].charAt(0) == '#')
            {
                adicionado = true;
                String nomeAgente;
                String[] campos = null;
                if(comandos[i].contains("."))
                {                    
                    campos = comandos[i].split("\\.");
                    nomeAgente = campos[0];
                }   
                else
                    nomeAgente = comandos[i]; 
                nomeAgente = nomeAgente.substring(1); //retirando #                
                
                //Procurando tipo do agente
                Agente agente = AplCadastrarAgente.obterAgente(modelo.getIdModelo().intValue(),
                        nomeAgente);               
                if(agente.getTipo().equals("Movel"))                                    
                    expressao += " conjunto_" + nomeAgente;    
                else
                    expressao += " patches";                
                
                if(campos != null)
                {
                    String condicao = campos[1].split("=")[0];
                    
                    //condicao cor
                    if(condicao.equals("cor"))
                    {
                        String cor = campos[1].split("=")[1]; 
                        if(agente.getTipo().equals("Movel"))
                            expressao += " with [color = " + cor(cor) + "]";
                        else
                            expressao += " with [pcolor = " + cor(cor) + "]";
                    }
                    
                    //condicao forma
                    else if(condicao.equals("forma"))
                    {
                        String forma = campos[1].split("=")[1]; 
                        if(agente.getTipo().equals("Movel"))
                            expressao += " with [shape = " + 
                                    "\"" + forma(forma) + "\"" + "]";
                    }
                    
                    else
                    {
                        //Descobrindo sinal logico
                        String sinal = "";
                        if(campos[1].contains(">="))                        
                            sinal = ">=";
                        else
                            if(campos[1].contains("<="))  
                                sinal = "<=";
                            else
                                if(campos[1].contains("!="))
                                    sinal = "!=";
                                else
                                    if(campos[1].contains(">"))
                                        sinal = ">";
                                    else
                                        if(campos[1].contains("<"))
                                            sinal = "<";
                                        else
                                            sinal = "=";
                         String[] campos2 = campos[1].split(sinal);
                                                
                        //Coordenadas x e y
                        if(campos2[0].equals("xcor") || 
                                campos2[0].equals("ycor"))
                        {
                            if(agente.getTipo().equals("Movel"))
                                expressao += " with [" + campos2[0] + " " +
                                        sinal + " " +
                                        campos2[1] + "]";
                            else
                                expressao += " with [p" + campos2[0] + " " +
                                        sinal + " " + campos2[1] + 
                                        " and pcolor = " + 
                                        cor(agente.getCor()) + "]";
                        }
                        
                        //Variavel logica
                        else if(campos2[1].equals("verdadeiro") ||
                                campos2[1].equals("falso"))
                        {
                            expressao += " with [" + campos2[0] + " = ";
                            if(campos2[1].equals("verdadeiro"))
                                expressao += "true";
                            else
                                expressao += "false";
                            if(agente.getTipo().equals("Movel"))
                                expressao += "]";
                            else
                                expressao += "pcolor = " + 
                                        cor(agente.getCor()) + "]";
                        }
                        
                        //Outras variaveis locais
                        else
                        {
                            if(agente.getTipo().equals("Movel"))
                                expressao += " with [ " + campos2[0] + " " +
                                        sinal + " " + campos2[1] + "]";
                            else
                                expressao += " with [ " + campos2[0] + " " +
                                        sinal + " " + campos2[1] + 
                                        " and pcolor = " + 
                                        cor(agente.getCor()) + "]";
                        }
                    }
                }
                else
                    if(!agente.getTipo().equals("Movel"))
                        expressao += " with [ pcolor = " +
                                cor(agente.getCor()) + " ]";
            }
            
            if(!adicionado)
                expressao += comandos[i];
            
            expressao += " ";
        }
        
        return expressao;
    }
    
    //Traduz forma de portugues para correspondente em NetLogo
    public static String forma (String forma) 
    {
        forma = forma.toUpperCase();
        
        if (forma.equals("CILINDRO3D"))
            return "cilindro";       
        if (forma.equals("AVIAO"))
            return "airplane";	   
        if (forma.equals("AVIAOANTIGO"))
            return "airplane 2";	   
        if (forma.equals("AMBULANCIA"))
            return "ambulance";	   
        if (forma.equals("FORMIGA"))
            return "ant";	   
        if (forma.equals("MACA"))
            return "apple";	   
        if (forma.equals("SETA"))
            return "arrow";	   
        if (forma.equals("SETAFINA"))
            return "arrow 3";	   
        if (forma.equals("BOLADEBASQUETE"))
            return "ball basketball";	   
        if (forma.equals("BOLADETENIS"))
            return "ball tennis";	   
        if (forma.equals("BALAO"))
            return "balloon";	   
        if (forma.equals("BANANA"))
            return "banana";	   
        if (forma.equals("ABELHA"))
            return "bee";	   
        if (forma.equals("BICICLETA"))
            return "bike";	   
        if (forma.equals("PASSARO"))
            return "bird side";	   
        if (forma.equals("BARCO"))
            return "boat";	   
        if (forma.equals("LIVRO"))
            return "book";	   
        if (forma.equals("GARRAFA"))
            return "bottle";	   
        if (forma.equals("PINODEBOLICHE"))
            return "bowling pin";	   
        if (forma.equals("CUBO"))
            return "box";	   
        if (forma.equals("INSETO"))
            return "bug";	   
        if (forma.equals("MONUMENTO"))
            return "building institution";	   
        if (forma.equals("LOJA"))
            return "building store";	   
        if (forma.equals("ESCAVADORA"))
            return "bulldozer top";	   
        if (forma.equals("ONIBUS"))
            return "bus";	   
        if (forma.equals("BORBOLETA"))
            return "butterfly";	   
        if (forma.equals("CACTUS"))
            return "cactus";	   
        if (forma.equals("BARRACA"))
            return "campsite";	   
        if (forma.equals("CANHAO"))
            return "cannon";	   
        if (forma.equals("CARRO"))
            return "car";	   
        if (forma.equals("CARRO2"))
            return "car side";	   
        if (forma.equals("GATO"))
            return "cat";	   
        if (forma.equals("LAGARTA"))
            return "caterpillar";	   
        if (forma.equals("XADREZBISPO"))
            return "chess bishop";	   
        if (forma.equals("XADREZREI"))
            return "chess king";	   
        if (forma.equals("XADREZCAVALO"))
            return "chess knight";	   
        if (forma.equals("XADREZPEAO"))
            return "chess pawn";	   
        if (forma.equals("XADREZRAINHA"))
            return "chess queen";	   
        if (forma.equals("XADREZTORRE"))
            return "chess rook";	   
        if (forma.equals("CIRCULO1"))
            return "circle";	   
        if (forma.equals("CIRCULO2"))
            return "circle 2";	   
        if (forma.equals("RELOGIO"))
            return "clock";	   
        if (forma.equals("NUVEM"))
            return "cloud";	   
        if (forma.equals("MOEDACARA"))
            return "coin heads";	   
        if (forma.equals("MOEDACOROA"))
            return "coin tails";	   
        if (forma.equals("COMPUTADOR"))
            return "computer workstation";	   
        if (forma.equals("VACA"))
            return "cow";	   
        if (forma.equals("CAIXA"))
            return "crate";	   
        if (forma.equals("COROA"))
            return "crown";	   
        if (forma.equals("CILINDRO"))
            return "cylinder";	   
        if (forma.equals("DARDO"))
            return "dart";	   
        if (forma.equals("SETAPADRAO"))
            return "default";	   
        if (forma.equals("DOMINO1"))
            return "die 1";	   
        if (forma.equals("DOMINO2"))
            return "die 2";	   
        if (forma.equals("DOMINO3"))
            return "die 3";	   
        if (forma.equals("DOMINO4"))
            return "die 4";	   
        if (forma.equals("DOMINO5"))
            return "die 5";	   
        if (forma.equals("DOMINO6"))
            return "die 6";	   
        if (forma.equals("CACHORRO"))
            return "dog";	   
        if (forma.equals("DOLAR"))
            return "dollar bill";	   
        if (forma.equals("PONTO"))
            return "dot";	   
        if (forma.equals("GOTA"))
            return "drop";	   
        if (forma.equals("OVO"))
            return "egg";	   
        if (forma.equals("EXCLAMACAO"))
            return "exclamation";	   
        if (forma.equals("OLHO"))
            return "eyeball";	   
        if (forma.equals("FACEFELIZ"))
            return "face happy";	   
        if (forma.equals("FACENEUTRA"))
            return "face neutral";	   
        if (forma.equals("FACETRISTE"))
            return "face sad";	   
        if (forma.equals("INDUSTRIA"))
            return "factory";	   
        if (forma.equals("FOGO"))
            return "fire";	   
        if (forma.equals("PEIXE"))
            return "fish";	   
        if (forma.equals("BANDEIRA"))
            return "flag";	   
        if (forma.equals("FLOR"))
            return "flower";	   
        if (forma.equals("PEGADA"))
            return "footprint human";	   
        if (forma.equals("SAPO"))
            return "frog top";	   
        if (forma.equals("LIXEIRA"))
            return "garbage can";	   
        if (forma.equals("FANTASMA"))
            return "ghost";	   
        if (forma.equals("FALCAO"))
            return "hawk";	   
        if (forma.equals("HEXAGONO"))
            return "hex";	   
        if (forma.equals("CASA"))
            return "house";	   
        if (forma.equals("CASA2"))
            return "house bungalow";	   
        if (forma.equals("CASA3"))
            return "house ranch";	   
        if (forma.equals("CHAVE"))
            return "key";	   
        if (forma.equals("FOLHA"))
            return "leaf";	   
        if (forma.equals("CARTA"))
            return "letter sealed";	   
        if (forma.equals("RAIO"))
            return "lightning";	   
        if (forma.equals("LINHA"))
            return "line";	   
        if (forma.equals("LINHAPEQUENA"))
            return "line half";	   
        if (forma.equals("IMA"))
            return "magnet";	   
        if (forma.equals("POLVO"))
            return "monster";	   
        if (forma.equals("LUA"))
            return "moon";	   
        if (forma.equals("RATO"))
            return "mouse side";	   
        if (forma.equals("NOTAMUSICAL1"))
            return "music notes 1";	   
        if (forma.equals("NOTAMUSICAL2"))
            return "music notes 3";	   
        if (forma.equals("PINCEL"))
            return "paintbrush";	   
        if (forma.equals("LAPIS"))
            return "pencil";	   
        if (forma.equals("PENTAGONO"))
            return "pentagon";	   
        if (forma.equals("PESSOA"))
            return "person";	   
        if (forma.equals("PESSOAEMPRESARIO"))
            return "person business";	   
        if (forma.equals("PESSOAGARI"))
            return "person construction";	   
        if (forma.equals("PESSOAMEDICO"))
            return "person doctor";	   
        if (forma.equals("PESSOAFAZENDEIRO"))
            return "person farmer";	   
        if (forma.equals("PESSOAFORMANDO"))
            return "person graduate";	   
        if (forma.equals("PESSOALENHADOR"))
            return "person lumberjack";	   
        if (forma.equals("PESSOAPOLICIAL"))
            return "person police";	   
        if (forma.equals("PESSOAGARCOM"))
            return "person service";	   
        if (forma.equals("PESSOASOLDADO"))
            return "person soldier";	   
        if (forma.equals("PESSOAESTUDANTE"))
            return "person student";	   
        if (forma.equals("PLANTA"))
            return "plant";	   
        if (forma.equals("ABOBORA"))
            return "pumpkin";	   
        if (forma.equals("COELHO"))
            return "rabbit";	   
        if (forma.equals("FOGUETE"))
            return "rocket";	   
        if (forma.equals("TUBARAO"))
            return "shark";	   
        if (forma.equals("OVELHA"))
            return "sheep";	   
        if (forma.equals("ARANHA"))
            return "spider";	   
        if (forma.equals("QUADRADO1"))
            return "square";	   
        if (forma.equals("QUADRADO2"))
            return "square 1";	   
        if (forma.equals("ESQUILO"))
            return "squirrel";	   
        if (forma.equals("ESTRELA"))
            return "star";	   
        if (forma.equals("MORANGO"))
            return "strawberry";	   
        if (forma.equals("LOSANGO"))
            return "suit diamond";	   
        if (forma.equals("CORACAO"))
            return "suit heart";	   
        if (forma.equals("SOL"))
            return "sun";	   
        if (forma.equals("TANQUE"))
            return "tank";	   
        if (forma.equals("ALVO"))
            return "target";	   
        if (forma.equals("TELEFONE"))
            return "telephone";	   
        if (forma.equals("TIJOLOS"))
            return "tile brick";	   
        if (forma.equals("MADEIRA"))
            return "tile log";	   
        if (forma.equals("PEDRAS"))
            return "tile stones";	   
        if (forma.equals("AGUA"))
            return "tile water";	   
        if (forma.equals("DENTE"))
            return "tooth";	   
        if (forma.equals("TREM"))
            return "train";	   
        if (forma.equals("VAGAO"))
            return "train freight hopper full";	   
        if (forma.equals("ARVORE"))
            return "tree";	   
        if (forma.equals("PINHEIRO"))
            return "tree pine";	   
        if (forma.equals("TRIANGULO1"))
            return "triangle";	   
        if (forma.equals("TRIANGULO2"))
            return "triangle 2";	   
        if (forma.equals("CAMINHAO"))
            return "truck";	   
        if (forma.equals("TARTARUGA"))
            return "turtle";	   
        if (forma.equals("DISCOVOADOR"))
            return "ufo side";	   
        if (forma.equals("VAN"))
            return "van side";	   
        if (forma.equals("RODA"))
            return "wheel";	   
        if (forma.equals("LOBO"))
            return "wolf";	   
        if (forma.equals("X"))
            return "x";	
        
        return "default";
    }
    
    //Traduz cor de portugues para correspondente em NetLogo
    public static String cor (String cor)
    {
        cor = cor.toUpperCase();
        
        if (cor.equals("AMARELO"))
            return "yellow";
        if (cor.equals("AZUL"))
            return "blue";
        if (cor.equals("AZULCLARO") || cor.equals("AZUL CLARO"))
            return "sky";
        if (cor.equals("BRANCO"))
            return "white";
        if (cor.equals("CIANO"))
            return "cyan";
        if (cor.equals("CINZA"))
            return "gray";
        if (cor.equals("LARANJA"))
            return "orange";
        if (cor.equals("MAGENTA") || cor.equals("MAGENTA2"))
            return "magenta";
        if (cor.equals("MARROM"))
            return "brown";
        if (cor.equals("PRETO"))
            return "black";
        if (cor.equals("ROSA"))
            return "pink";
        if (cor.equals("VERDE"))
            return "green";
        if (cor.equals("VERDELIMAO") || cor.equals("VERDE LIMAO"))
            return "lime";
        if (cor.equals("VERDETURQUESA") || cor.equals("VERDE TURQUESA"))
            return "turquoise";
        if (cor.equals("VERMELHO"))
            return "red";
        if (cor.equals("VIOLETA"))
            return "violet";
        return "lime";
    }
    
    //Acerta nome da cor
    public static String corPort (String cor)
    {
        String cor2 = cor.charAt(0) + 
                cor.substring(1).toLowerCase();
        
        if (cor.equals("AZULCLARO") || cor.equals("AZUL CLARO"))
            cor2 = "Azul Claro";
        if (cor.equals("VERDELIMAO") || cor.equals("VERDE LIMAO"))
            cor2 = "Verde Lim&atilde;o";
        if (cor.equals("VERDETURQUESA") || cor.equals("VERDE TURQUESA"))
            cor2 = "Verde Turquesa";
        if (cor.equals("MAGENTA2"))
            cor2 = "Magenta";
        
        return cor2;
    }
    
    public static String operador(String operador)
    {
        String op = "";
        if(operador.equals("igual"))
            op = "=";
        if(operador.equals("diferente"))
            op = "!=";
        if(operador.equals("maior"))
            op = ">";
        if(operador.equals("menor"))
            op = "<";
        if(operador.equals("maiorIgual"))
            op = ">=";
        if(operador.equals("menorIgual"))
            op = "<=";
        return op;
    }
}
