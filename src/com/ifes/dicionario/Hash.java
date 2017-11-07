/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifes.dicionario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author 20121bsi0040
 */


public class Hash {
    public int[] vetorh1 = new int[250];
    public int[] vetorh2 = new int[250];
    public int[] vetorh3 = new int[250];
    public int[] vetorh4 = new int[250];
    private int[][] matColisoes = new int[250][4];
    int TAM_VET = 250;
    
    public int funcao1(String cep){
        int retorno = Integer.parseInt(cep.replaceAll("[^0-9]", ""));
        return retorno;
    }
    public int funcao2(String cep){
        String[] cepnum = cep.replaceAll("[^0-9]", "").split("");
        int soma = 0;
        for (String s : cepnum){
            soma += Integer.parseInt(s);
        }
        return soma*10;
    }
    public int funcao3(String cep){
        String[] cepnum = cep.replaceAll("[^0-9]", "").split("");
        int soma = 0;
        for (String s : cepnum){
            soma += Integer.parseInt(s);
        }
        return soma;
    }
    public int funcao4(String cep){
        int somapoli = 0;
        int pos = 0;
        String[] cepnum = cep.replaceAll("[^0-9]", "").split("");
        for(String s : cepnum){
            somapoli += Integer.parseInt(s) * Math.pow(33, pos);
            pos++;
        }
        
        return Math.abs(somapoli);
    }
    
    public int[][] montaTabColisoes(String nomearq){
        BufferedReader br = null;
        for (int i = 0; i <TAM_VET ; i++){
            for (int j = 0; j < 4; j++){
                matColisoes[i][j] = 0;
            }
        }
        try{
            br = new BufferedReader(new FileReader(nomearq));
            String linha;
            String[] vetstr;
            
            linha = br.readLine();
            //int i = 0;
            while(linha != null){
                vetstr = linha.split(";");
                String srtcep = vetstr[0];
                int hx1 = this.funcao1(srtcep)%250;
                matColisoes[hx1][0]++;
                int hx2 = this.funcao2(srtcep)%250;
                matColisoes[hx2][1]++;
                int hx3 = this.funcao3(srtcep)%250;
                matColisoes[hx3][2]++;
                int hx4 = this.funcao4(srtcep)%250;
                matColisoes[hx4][3]++;
                
                linha = br.readLine();
            }
            br.close();
        }
        catch(IOException e){
           e.printStackTrace();
        }
        return matColisoes;
    }
        
    public static void main(String[] args){
        Hash h = new Hash();
        int[][] mcal = h.montaTabColisoes("C:\\Users\\20121bsi0040\\Downloads\\vix-ruas-ceps.txt");
        
        for (int i = 0; i<h.TAM_VET; i++){
            System.out.println(mcal[i][0] + "\t" + mcal[i][1] + "\t" + mcal[i][2] + "\t" + mcal[i][3]);
            
        }
    }
    
}

