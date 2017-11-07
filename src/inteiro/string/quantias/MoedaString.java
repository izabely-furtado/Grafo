/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inteiro.string.quantias;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import taddic.TADDicChain;
import taddic.TADDicEA;

/**
 *
 * @author 20121bsi0040
 */
public class MoedaString {
    TADDicEA dicionario1 = new TADDicEA(new HashEngineIntStr());
    TADDicChain dicionario2 = new TADDicChain(new HashEngineIntStr());
    
    public static String inteiroPorExtenso(int i){
        String[] zeroA9 = {"zero", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove"};
        String[] dezA19 = {"dez", "onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"};
        String[] zeroA90 = {"", "dez", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"};
        if (i > 99 || i < 0){
            return "Não é tratado número acima de 99 ou abaixo de 0";
        }
        else {
            int primeiraParte = i/10;
            int segundaParte = i%10;
            if (i >= 0 && i <= 9){
                return zeroA9[i];
            }
            else if (i >= 10 && i <= 19){
                return dezA19[segundaParte];
            }
            else if (primeiraParte > 0 && segundaParte == 0){
            	return zeroA90[primeiraParte];
            }
            else {
                return zeroA90[primeiraParte] + " e " + zeroA9[segundaParte];
            }
            
        }
    }
    
    public static String moedaPorExtenso(String moeda){
        
    	
    	return "";
    }
    
    public void inserindoDicEA(){
        for (int i = 0; i <= 99; i++){
            this.dicionario1.insertItem(i, MoedaString.inteiroPorExtenso(i));
        }
    }
    
    @Override
    public String toString(){
        String retorno = "";
        this.inserindoDicEA();
        for (int i = 0; i <= 99; i++){
            retorno += this.dicionario1.findElements(i) + " - ";
        }
        return retorno;
    }
    
    public static void main (String[]args){
      try{
         BufferedReader br = new BufferedReader(new FileReader("c:/arquivo.html"));
         while(br.ready()){
            String linha = br.readLine();
            System.out.println(linha);
         }
         br.close();
       }catch(IOException ioe){
         ioe.printStackTrace();
      }
   }
    
    
    
    
    
}
