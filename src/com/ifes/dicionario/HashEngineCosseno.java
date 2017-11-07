/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifes.dicionario;

import taddic.*;

/**
 *
 * @author 20121bsi0040
 */
public class HashEngineCosseno extends HashEngine{
    
    @Override
    public int hashCode(Object k){
        int chave = 0;
        try {
            String palavra = (String) k;
            for (char letra : palavra.toCharArray()){
                chave += (int) letra;
            }
            return chave;
        } catch (Exception e) {
            throw new RuntimeException("O Objeto não pode ser transformado em String");
        }
    }
    
    
    
}
