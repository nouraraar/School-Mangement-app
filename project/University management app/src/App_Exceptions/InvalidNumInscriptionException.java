/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package App_Exceptions;

/**
 *
 * @author amir,chedi,nour
 */
public class InvalidNumInscriptionException extends Exception  {
    @Override 
    public String getMessage()
    {
        return "invalid numInscription" ; 
    }
        
}
