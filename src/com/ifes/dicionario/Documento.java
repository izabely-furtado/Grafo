/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifes.dicionario;

import static java.lang.Math.sqrt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author 20121bsi0040
 */
class ItemFreqNgramas{
    private String nGrama;
    private int freq;
    
    public ItemFreqNgramas(String ng, int f){
        nGrama = ng;
        freq = f;
    }

    public String getnGrama() {
        return nGrama;
    }

    public void setnGrama(String nGrama) {
        this.nGrama = nGrama;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }
    
    
    public void maismais(){
        freq++;
    }
    
    public void menosmenos(){
        freq--;
    }
    
    public boolean equalNgrama(String ng){
        return nGrama.equals(ng);
    }
}

public class Documento {
    private String nomeArq;
    
    public Documento(String nomeArq){
        this.nomeArq = nomeArq;
    }
    
    public LinkedList<String> palavras(){
        BufferedReader br = null;
        LinkedList<String> lst = new LinkedList<>();
        String[] vetStr;
        
        try{
            br = new BufferedReader(new FileReader(nomeArq));
            String linha;
            
            linha = br.readLine();
            
            while (linha != null){
                vetStr = linha.split("[^a-zA-Z0-9\\p{Lu}\\p{Ll}]+");
                
                for (String vetStr1 : vetStr) {
                    lst.add(vetStr1);
                }
                
                linha = br.readLine();
            }
            br.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return lst;
    }
    
    
    public LinkedList<String> nGramas(int tam){
        LinkedList<String> lst = new LinkedList<String>();
        LinkedList<String> palavras = this.palavras();
        
        for (int i = 0; i < palavras.size() - tam; i++){
            String strGrama = "";
            
            for (int j=0; j<tam; j++) strGrama = strGrama+","+ palavras.get(i+j);
            
            lst.add(strGrama.substring(1, strGrama.length())); // fateamento da strng p/ n pegar a virgula do inicio
        }
        
        return lst;
    }
    
    public LinkedList<ItemFreqNgramas> freqNgramas(int tam){
        LinkedList<String> lstNgramas = nGramas(tam);
        LinkedList<ItemFreqNgramas> tabela = new LinkedList<ItemFreqNgramas>();
        
        for (int i = 0; i < lstNgramas.size() - tam; i++){
            String ngrama = lstNgramas.get(i);
            
            int j=0;
            while ( j<tabela.size()){
                if (tabela.get(j).equalNgrama(ngrama)){
                    tabela.get(j).maismais();
                    
                    j=tabela.size();
                }
                
                j++;
            }
            
            if (j == tabela.size()) tabela.add(new ItemFreqNgramas(ngrama, 1));
            
        }
        
        return tabela;
    }
    
    public static double cosseno(String documento1, String documento2, int n) {
        Documento doc1 = new Documento(documento1);
        Documento doc2 = new Documento(documento2);
        LinkedList<ItemFreqNgramas> freqs1 = doc1.freqNgramas(n);
        LinkedList<ItemFreqNgramas> freqs2 = doc2.freqNgramas(n);
        int acima = 0;
        double abaixo;
        double abaixodoc1 = 0;
        double abaixodoc2 = 0;
        double cosseno;
        for (ItemFreqNgramas item : freqs1){
            abaixodoc1 += item.getFreq()*item.getFreq();
        }
        abaixodoc1 = sqrt(abaixodoc1);
        for (ItemFreqNgramas item : freqs2){
            abaixodoc2 += item.getFreq()*item.getFreq();
        }
        abaixodoc2 = sqrt(abaixodoc2);
        abaixo = abaixodoc1 * abaixodoc2;
        
        for (ItemFreqNgramas i : freqs1){
            for (ItemFreqNgramas j : freqs2){
                if (i.getnGrama().equals(j.getnGrama())){
                    acima += i.getFreq() * j.getFreq(); 
                }
            }
        }
        cosseno = acima / abaixo;
        return cosseno;
    }
    
    public double cosseno(Documento doc2) {
        LinkedList<ItemFreqNgramas> freqs1 = this.freqNgramas(1);
        LinkedList<ItemFreqNgramas> freqs2 = doc2.freqNgramas(1);
        int acima = 0;
        double abaixo;
        double abaixodoc1 = 0;
        double abaixodoc2 = 0;
        double cosseno;
        for (ItemFreqNgramas item : freqs1){
            abaixodoc1 += item.getFreq()*item.getFreq();
        }
        abaixodoc1 = sqrt(abaixodoc1);
        for (ItemFreqNgramas item : freqs2){
            abaixodoc2 += item.getFreq()*item.getFreq();
        }
        abaixodoc2 = sqrt(abaixodoc2);
        abaixo = abaixodoc1 * abaixodoc2;
        
        for (ItemFreqNgramas i : freqs1){
            for (ItemFreqNgramas j : freqs2){
                if (i.getnGrama().equals(j.getnGrama())){
                    acima += i.getFreq() * j.getFreq(); 
                }
            }
        }
        cosseno = acima / abaixo;
        return cosseno;
    }
    
    public double simjacc(Documento doc2){
        List<String> palavras = this.palavras();
        palavras.addAll(doc2.palavras());
        return 0;
    }
    
    public double uniao(Documento doc2){
        List<String> palavras = this.palavras();
        palavras.addAll(doc2.palavras());
        int uniao = 0;
        LinkedList<String> lstNgramas = new LinkedList<String>();
        for (int i = 0; i < palavras.size() - 1; i++){
            String strGrama = "";
            for (int j=0; j<1; j++) strGrama = strGrama+","+ palavras.get(i+j);
            lstNgramas.add(strGrama.substring(1, strGrama.length())); // fateamento da strng p/ n pegar a virgula do inicio
        }
        LinkedList<ItemFreqNgramas> tabela = new LinkedList<ItemFreqNgramas>();
        for (int i = 0; i < lstNgramas.size() - 1; i++){
            String ngrama = lstNgramas.get(i);
            
            int j=0;
            while ( j<tabela.size()){
                if (tabela.get(j).equalNgrama(ngrama)){
                    tabela.get(j).maismais();
                    
                    j=tabela.size();
                }
                
                j++;
            }
            
            if (j == tabela.size()) tabela.add(new ItemFreqNgramas(ngrama, 1));
            
        }
        for (ItemFreqNgramas item : tabela){
            uniao += item.getFreq();
        }
        return uniao;
    }
    
    public static void main(String[] args) {
        HashEngineCosseno chave = new HashEngineCosseno();
        System.out.println("Izabely");
        System.out.println(chave.hashCode("Izabely"));
//        Documento doc = new Documento("C:\\Users\\20121bsi0040\\Desktop\\teste.txt");
//        Documento doc2 = new Documento("C:\\Users\\20121bsi0040\\Desktop\\teste2.txt");
//        
//        //LinkedList<?> freqs = doc.freqNgramas(3);
//        LinkedList<?> palavras = doc.palavras();
//        LinkedList<?> lista = doc.nGramas(3);
//        System.out.println(palavras);
//        
//        System.out.println("Quantidade de N gramas: " + lista.size());
//        
//        System.out.println(Documento.cosseno("C:\\Users\\20121bsi0040\\Desktop\\teste.txt", "C:\\Users\\20121bsi0040\\Desktop\\teste2.txt", 3));
//        System.out.println(doc.cosseno(doc2));
        
    }
    
    

}
