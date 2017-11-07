/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taddic;

import java.util.ArrayList;
import java.util.List;

/**
*
* @author 20121bsi0040
*/
public class TADDicEA extends TADDicionario {

   private Item[] vetBulckets;

   public TADDicEA(int tamanho, HashEngine he) {
       super(tamanho, he);
       this.vetBulckets = new Item[tamanho];

   }

   public TADDicEA(HashEngine he) {
       super(he);
       this.vetBulckets = new Item[10000];

   }
   
   public TADDicEA() {
       super(new HashEngineApp());
       this.vetBulckets = new Item[10000];

   }

   private void redimensiona() {
       Item[] vetorAntigo = this.vetBulckets;
       if (this.tamanho > vetorAntigo.length * 0.8) {
           this.N = (int) (N * 1.5);
           this.vetBulckets = new Item[N];
           this.tamanho = 0;
           for (Item item : vetorAntigo) {
           	if(item != null){
           		this.insertItem3(item.getChave(), item.getElemento(), item.getCalculohash());
           	}
           }
       }
   }

   @Override
   public Object findElements(Object chave) {
       int i = this.dicEngine.hashCode(chave);
       int p = 0;
       do {
           Item c = this.vetBulckets[i];
           if (c == null) {
               return TADDicEA.NO_SUCH_KEY;
           } else {
               if (!chave.equals(c.getChave())) {
                   i++;
                   i = i % N;
                   p++;
               } else {
                   return c.getElemento();
               }
           }
       } while (p != this.N);
       return TADDicEA.NO_SUCH_KEY;
   }

//   public boolean insertItem2(Object k, Object o) {
//       int i = this.dicEngine.hashCode(k);
//       int p = 0;
//       do {
//           Item item = this.vetBulckets[i];
//           if (item == null) {
//               this.vetBulckets[i] = new Item(k, o);
//               return true;
//           } else {
//               i = (i + 1) % N;
//               p++;
//           }
//       } while (p != N);
//       return false;
//   }

   public Object insertItem3(Object chave, Object obj, int calculoHash) {
       int index = calculoHash % this.vetBulckets.length;
       if (this.vetBulckets[index] == null) {
           this.vetBulckets[index] = new Item(chave, obj, calculoHash);
           this.tamanho++;
       } else {
           if (this.vetBulckets[index].getChave() == chave) {
               //altera o elemento
               this.vetBulckets[index].setElemento(obj);
           } else {
               int i = 1;
               //procura o proximo lugar vago
               while (this.vetBulckets[index + i] != null) {
                   i++;
               }
               this.vetBulckets[index + i] = new Item(chave, obj, calculoHash);
               this.tamanho++;
           }
       }
       return null;
   }

   @Override
   public Object insertItem(Object chave, Object obj) {
       if (this.tamanho <= this.vetBulckets.length * 0.8) {
           int hash = this.dicEngine.hashCode(chave);
           int index = hash % this.vetBulckets.length;
           if (this.vetBulckets[index] == null) {
               this.vetBulckets[index] = new Item(chave, obj, hash);
               this.tamanho++;
           } else {
               if (this.vetBulckets[index].getChave() == chave) {
                   //altera o elemento
                   this.vetBulckets[index].setElemento(obj);
               } else {
                   int i = 1;
                   //procura o proximo lugar vago
                   while (this.vetBulckets[index + i] != null) {
                       i++;
                   }
                   this.vetBulckets[index + i] = new Item(chave, obj, hash);
                   this.tamanho++;
               }
           }
       } else {
           this.redimensiona();
           this.insertItem(chave, obj);
       }
       return null;
   }

   @Override
   public Object removeElement(Object chave) {
       int index = this.dicEngine.hashCode(chave);
       Item item = null;
       int i = 0;
       while (this.vetBulckets[index + i].getChave() != chave && i < this.vetBulckets.length) {
           i++;
       }
       if (this.vetBulckets[index + i].getChave() == chave) {
           item = this.vetBulckets[index + i];
           this.vetBulckets[index + i] = null;
           this.tamanho--;
       }
       return item;
   }

   @Override
   public List<Object> keys() {
       List<Object> chaves = new ArrayList<>();
       for (Item item : this.vetBulckets) {
           if (item != null) {
               chaves.add(item.getChave());
           }
       }
       return chaves;
   }

   @Override
   public List<Object> elements() {
       List<Object> elementos = new ArrayList<>();
       for (Item item : this.vetBulckets) {
           if (item != null) {
               elementos.add(item.getElemento());
           }
       }
       return elementos;
   }

}