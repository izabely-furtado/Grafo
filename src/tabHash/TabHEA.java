/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabHash;

import java.util.LinkedList;
/**
 *
 * @author 20121bsi0040
 */
public class TabHEA {

    public static final ItemTabHash NO_SUCH_KEY = new ItemTabHash(null, null);
    private static final ItemTabHash DISPONIVEL = new ItemTabHash(null, null);
    private ItemTabHash[] conteudos;
    private HashEngine hengine = null;
    private int N;
    private int quant = 0;

    public TabHEA(int n, HashEngine paramHengine) {
        // Fator de carga de 0,5 para tabelas de hashing com encadeamento. Livro texto página 327.
        N = (int) (n / 0.5);

        // As listas ainda não foram criadas.
        conteudos = new ItemTabHash[N];

        hengine = paramHengine;
    }

    public TabHEA(HashEngine paramHengine) {
        // Fator de carga de 0,85 para tabelas de hashing com encadeamento. Livro texto página 327.
        N = 2000;

        // As listas ainda não foram criadas.
        conteudos = new ItemTabHash[N];

        hengine = paramHengine;
    }

    public TabHEA() {
        // Fator de carga de 0,85 para tabelas de hashing com encadeamento. Livro texto página 327.
        N = 2000;

        // As listas ainda não foram criadas.
        conteudos = new ItemTabHash[N];

        hengine = new HashEngineDefault();
    }

    private int encontraItem(Object k) {
        int pos = hengine.calcCodeHash(k) % N;
        int j = pos;

        do {
            if (conteudos[j] == null) {
                return -1;
            }

            if (conteudos[j].equals(TabHEA.DISPONIVEL)) {
                j = (j + 1) % N;
            } else if (conteudos[j].getK().equals(k)) {
                return j;
            } else // Teste linear..
            {
                j = (j + 1) % N;
            }

			// OU
            // Teste quadratico..(inicializar m com 1)
            //  j = (j + m*m) % N;
            //  m = m + 1
			// OU
            // Hashing duplo.. (inicializar m com 1)
            //  j = (j + m * h2(j)) % N;
        } while (j != pos);

        return -1;
    } // fim encontraItem

    private void redimensiona() {
        int NN = (int) (1.5 * N);
        ItemTabHash[] novosConteudos = new ItemTabHash[NN];

        int j = 0;

        // Varre o vetor de conteúdos transferindo o seu conteúdo para o vetor novosConteudos.
        while (j < conteudos.length) {
            if ((conteudos[j] != null) && (conteudos[j] != DISPONIVEL)) {
                Object k = conteudos[j].getK();
                Object pelem = conteudos[j].getElem();

                int pos = conteudos[j].getCacheCodHash() % NN;
                int i = pos;
                boolean fim = false;
				// int j = 1; 
                // ativar apenas se for usar teste quadrádico ou double hash.

                do {
                    // Altera um item já existente.
                    if ((novosConteudos[i] != null) && (novosConteudos[i].getK().equals(k))) {
                        novosConteudos[i].setElem(pelem);
                        fim = true;
                    } else {
                        // Inclui um item novo.
                        if ((novosConteudos[i] == null) || (novosConteudos[i] == DISPONIVEL)) {
                            novosConteudos[i] = conteudos[j];
                            fim = true;
                        }
						// else
                        //  Houve uma colisão. 
                        //  Não fazer nada, gerar um outro i = (i + 1) % NN e repetir as ações acima.
                    }

                    i = (i + 1) % NN;
                } while ((i != pos) && !fim);
            } // if((conteudos[j] != null) && (conteudos[j] != DISPONIVEL))..

            j = j + 1;
        } // while j..	

        conteudos = novosConteudos;
        N = NN;
    } // redimensiona..

    // IMPLEMENTE ESTE MÉTODO PARA A PROVA 1 TPA 2017-2
    public boolean insertItem(Object k, Object elem) {
        if (this.quant <= this.conteudos.length * 0.8) {
            int hash = this.hengine.calcCodeHash(k);
            int index = hash % this.conteudos.length;
            if (this.conteudos == null) {
                this.conteudos = new ItemTabHash[N];
                this.conteudos[index] = new ItemTabHash(k, elem, hash);
                this.quant++;
                return true;
            }
            else if(this.conteudos[index] == null){
                this.conteudos[index] = new ItemTabHash(k, elem, hash);
                this.quant++;
                return true;
            }
            else {
                //se existem chaves iguais
                if (this.conteudos[index].getK() == k) {
                    //altera o elemento
                    this.conteudos[index].setElem(elem);
                    return true;
                } else {
                    int i = 1;
                    //procura o proximo lugar vago
                    while ((i+index) < this.conteudos.length && this.conteudos[index + i] != null) {
                        i++;
                    }
                    if(i+index == this.conteudos.length){
                        i = 0;
                        boolean achou = false;
                        while (achou == false && i < index){
                            if(this.conteudos[i] != null){
                                i++;
                            }else {
                                achou = true;
                            }
                        }
                        this.conteudos[i] = new ItemTabHash(k, elem, hash);
                        this.quant++;
                        return true;
                    }
                    else {
                        this.conteudos[index + i] = new ItemTabHash(k, elem, hash);
                        this.quant++;
                        return true;
                    }
                    
                }
            }
        }
        else {
            this.redimensiona();
            this.insertItem(k, elem);
            return true;
        }
                    

    } // fim insertItem	

    public Object findElement(Object k) {
        int indice = encontraItem(k);

        if (indice == -1) {
            return NO_SUCH_KEY;
        } else {
            return conteudos[indice].getElem();
        }
    } // fim findElement

    public Object removeElement(Object k) {
        int indice = encontraItem(k);

        if (indice == -1) {
            return NO_SUCH_KEY;
        } else {
            ItemTabHash item = conteudos[indice];
            this.conteudos[indice] = null;
            this.quant--;
            return item;
        }
    } // fim removeElement

    public boolean isEmpty() {
        return quant == 0;
    } // fim isEmpty

    public int size() {
        return quant;
    } // fim size

    public LinkedList<Object> keys() {
        LinkedList<Object> lstKeys = new LinkedList<>();

        int i = 0;

        while (i < conteudos.length) {
            if ((conteudos[i] != null) && (conteudos[i] != DISPONIVEL)) {
                lstKeys.add(conteudos[i].getK());
            }

            i = i + 1;
        } // while..

        return lstKeys;
    } // fim de keys

    public LinkedList<Object> elements() {
        LinkedList<Object> lstElements = new LinkedList<>();

        int i = 0;

        while (i < conteudos.length) {
            if ((conteudos[i] != null) && (conteudos[i] != DISPONIVEL)) {
                lstElements.add(conteudos[i].getElem());
            }

            i = i + 1;
        } // while..

        return lstElements;
    } // fim elements	
} // fim Class TabHEA

