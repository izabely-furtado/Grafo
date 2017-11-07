/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifes.dicionario;

import taddic.TADDicChain;
import taddic.TADDicEA;

/**
 *
 * @author 20121bsi0040
 */
public class InteiroString {
    TADDicEA dicionario1 = new TADDicEA(new HashEngineIntStr());
    TADDicChain dicionario2 = new TADDicChain(new HashEngineIntStr());
    
    private String inteiroPorExtenso(int i){
        String[] zeroA9 = {"zero", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove"};
        String[] dezA19 = {"dez", "onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"};
        String[] zeroA90 = {"", "dez", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"};
        if (i > 99){
            return "Não é tratado número acima de 99";
        }
        else {
            int primeiraParte = i/10;
            int segundaParte = i%10;
            if (primeiraParte <= 0){
                return zeroA9[primeiraParte];
            }
            else if (i >= 10 && i <= 19){
                return dezA19[segundaParte];
            }
            else {
                return zeroA90[primeiraParte] + " e " + zeroA9[segundaParte];
            }
            
        }
    }
    
    private void inserindoDicEA(){
        for (int i = 0; i <= 99; i++){
            this.dicionario1.insertItem(i, this.inteiroPorExtenso(i));
        }
    }
    
    @Override
    public String toString(){
        String retorno = "";
        this.inserindoDicEA();
        for (int i = 0; i <= 99; i++){
            retorno += this.dicionario1.findElements(i) + "";
        }
        return retorno;
    }
    
    public static void main(String[] args){
        InteiroString intStr = new InteiroString();
        System.out.println(intStr);
    }
    
    
    
    
    
}
