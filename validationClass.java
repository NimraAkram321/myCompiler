/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompiler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author HP
 */
public class validationClass {
    //2d array for storing our keywords in our language
    static String[][] key={{"int","char","float","bool"}//keywords DT
            ,{"String"} //DT
            
            ,{"for"}//loops
            
            ,{"if"} //condition
            ,{"else"} //condition
            ,{"break"}// condition 
            ,{"continue"}//condition 
              //OOP
            ,{"public"}//Access modifier
            ,{"extend"}
            ,{"implement"}
            ,{"this","super"}
            ,{"new"}
            ,{"true","false"}
            ,{"enum"}
            
    };
    public validationClass(){// empty constuctor
     }
    Matcher matcher;
    //RE for character
    public boolean Validate_char(String word){
        matcher=character.matcher(word);
        return matcher.matches();
    }
    Pattern  character=Pattern.compile("(\\'(\\\\\\\\(\\\\\\\\|\\\"|\\'|r|b|t|n|o))\\')|(\\'.\\')");
    //Re for Unsign integer
    public boolean Validate_Unsign(String word){
        matcher=Unsign.matcher(word);
        return matcher.matches();
    }
    Pattern  Unsign=Pattern.compile("[0-9]+");
    //Re for Sign integer
    public boolean Validate_Sign(String word){
        matcher=Sign.matcher(word);
        return matcher.matches();
    }
    Pattern  Sign=Pattern.compile("[+-][0-9]+");//[+-]\\d+
    //Re for Float
    public boolean Validate_flaot(String word){
        matcher=Floating.matcher(word);
        return matcher.matches();
    }    
    Pattern  Floating= Pattern.compile("[-+]?[0-9]*[.][0-9]*");//   [-+]?[0-9]*\\.?[0-9]* //[+-]?([0-9]*[.])?[0-9]*
    //Re for Identifier
    public boolean Validate_Identifier(String word){
        matcher=Identifier.matcher(word);
        return matcher.matches();
    }    
    Pattern Identifier=Pattern.compile("^([a-zA-Z_@][a-zA-Z\\d]*)");
    //string 
    public boolean Validate_String(String word){
   matcher=String.matcher(word);
  
         
        return matcher.matches();
   }
       Pattern String = Pattern.compile("\"((\\\\[\'\"\\\\])|(\\\\[bfnrt0])|([\\!#-&\\(-/0-9:-@A-Z\\[\\]-`ac-eg-mo-qsu-z\\{-~])|([bnfrt0])|(\\s))*\"");
    // validation of KeyWords of our language 
   static  int checking;
    public boolean Validate_IsKeyword(String word){
        for(int row=0;row<key.length;row++){ //nested loop for 2d array checking
            for(int col=0;col<key[row].length;col++){
                if(word.equals(key[row][col])){
                    checking=row;
                    return true;
                }
            }
        }
        return false;
    }
    public boolean Validate_Punctuator(String temp){
        switch(temp.charAt(0)){ // we have include all punctuator in our langugage
            case '(':
                return true;
            
            case ')':
                return true;
                
            case '[':
                return true;
                
            case ']':
                return true;
            
            case '{':
                return true;
               
            case '}':
                return true;
                
            case ',':
                return true;
            
            case ':':
                return true;
            
            case ';':
                return true;
                
            default:
                return false;
        }
        
    }
    static String checkingOperator="";
    public boolean validate_operator(String str){
        if(str.equals("=")){ // belong to its our class 
            checkingOperator="Assignment Operator";
            return true;
        }
       
        //for Relational operators
        if(str.equals("<")||str.equals(">")
                ||str.equals("!=")||str.equals("<=")||
                str.equals(">=")||str.equals("==")){
            checkingOperator="Relational Operator";
            return true;
        }
        //for Compound operator
        if(str.equals("+=")||str.equals("-=")||
                str.equals("/=")||str.equals("*=")){
            checkingOperator="Compound Operator";
            return true;
        }
        //for addition subtraction
        if(str.equals("+")||str.equals("-")){
            checkingOperator="AM";
            return true;
        }
        //for Multiply ,divide, mode
        if(str.equals("/")||str.equals("*")||str.equals("%")){
            checkingOperator="MDM";
            return true;
        }
        //increment-decrement
        if(str.equals("++")||str.equals("--")){
            checkingOperator="inc_dec";
            return true;
        }
        // for logical
        if(str.equals("&")||str.equals("|")){
            checkingOperator="Logical Operator";
            return true;
        }
        // for Not
        if(str.equals("!")){
            checkingOperator="Not";
            return true;
        }
        else
            checkingOperator="";
        return false;
    }
    
}   

