package inteiro.string.quantias;

public class BDQuantias {
    //public tipoMoeda; 
    public BDQuantias(){
        
    }
    
    public String quantiaString(String quantia) {
        String[] quant = quantia.split(" , ");
        String moeda = quant[0];
        Integer numero = Integer.parseInt(quant[1]);
        return InteiroString.inteiroPorExtenso(numero) + " " + moeda;
    }

}
