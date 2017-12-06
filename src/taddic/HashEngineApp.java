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
public class HashEngineApp extends HashEngine{
    
    @Override
	public int calcCodeHash(Object k) {
		return k.hashCode();
	}
}
