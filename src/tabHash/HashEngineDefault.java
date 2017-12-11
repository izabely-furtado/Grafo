package tabHash;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class HashEngineDefault extends HashEngine {
	public int calcCodeHash(Object k){
		byte [] meuVetBytes = null;
		
		try {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			ObjectOutputStream o = new ObjectOutputStream(b);
			o.writeObject(k);
		
			meuVetBytes = b.toByteArray();
		}
		catch (IOException e) {
			System.out.println("Preocupante!");			
		}
		
		long poliResult = 0;
		
		/* calculo do código de hashing) */
		for(int i=0; i < meuVetBytes.length; i++)
			poliResult = poliResult + (int)meuVetBytes[i] * (long)Math.pow(33,i);
		
		return Math.abs((int)poliResult);
	}
} // Class HashEngineDefault
