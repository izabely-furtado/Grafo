package appGrafos;

import java.util.LinkedList;
import tadgrafoLAdj.*;
import tadgrafoMAdjND.*;
import abstractGrafo.*;
import tadgrafoLAdj.*;
import algoGrafos.*;   

public class AppTestaTGF {		
	public static void main(String[] args){
		//TADGrafoMadjND gnd = new TADGrafoMadjND();
		TADGrafoLadjND gnd = new TADGrafoLadjND();
		
		// Povoando o appGrafos gnd.
    
    gnd.carrega("nome do arquivo.txt");[
    TPA2GS.exibeGrafo(gnd);
    
    }
}
