package taddic;

import java.util.List;

public abstract class TADDicionario {
	
    public static ItemTabHash NO_SUCH_KEY = new ItemTabHash(null, null, 0);
    protected int tamanho;
    protected int N;
    protected HashEngine dicEngine;
    
    public TADDicionario(int tamanho, HashEngine he) {
    	this.N = tamanho;
        this.tamanho = 0;
        this.dicEngine = he;
    }

    public TADDicionario(HashEngine he) {
        this.N = 100;
        this.tamanho = 0;
    	this.dicEngine = he;
    }

    public abstract Object findElements(Object chave);

    public abstract Object insertItem(Object chave, Object obj) ;

    public abstract Object removeElement(Object chave) ;

    public int size() {
        return this.tamanho;
    }

    public boolean isEmpty() {
        return this.tamanho == 0;
    }

    public abstract List<Object> keys() ;

    public abstract List<Object> elements() ;

}
