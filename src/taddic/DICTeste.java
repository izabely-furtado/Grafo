/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taddic;

/**
 *
 * @author 20121bsi0040
 */
public class DICTeste { 
    public static void main(String[] args){
        TadDicChain dic = new TadDicChain();
        dic.insertItem("1", "teste1");
        dic.insertItem("2", "teste2");
        dic.insertItem("3", "teste3");
        dic.insertItem("4", "teste4");
        dic.insertItem("5", "teste5");
        dic.insertItem("6", "teste6");
        dic.insertItem("7", "teste7");
        dic.insertItem("8", "teste8");
        dic.insertItem("9", "teste9");
        System.out.println(dic.removeElement("1"));
        
        for(Object chave : dic.keys()){
            System.out.println(chave);
        }
        
        System.out.println("-------------------------------");
        
        TadDicEA dic2 = new TadDicEA();
        dic2.insertItem("1", "teste1");
        dic2.insertItem("2", "teste2");
        dic2.insertItem("3", "teste3");
        dic2.insertItem("4", "teste4");
        dic2.insertItem("5", "teste5");
        dic2.insertItem("6", "teste6");
        dic2.insertItem("7", "teste7");
        dic2.insertItem("8", "teste8");
        dic2.insertItem("9", "teste9");
        System.out.println(dic.size());
        
        for(Object chave : dic.keys()){
            System.out.println(chave);
        }
    }
}
