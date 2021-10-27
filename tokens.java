/*
 * here we intriduce our token
 */
package mycompiler;

/**
 *
 * @author HP
 */
public class tokens {
    String CP;// class part
    String VP;// value part 
    int line;//line
    public tokens(){// empty consturctor
        
    }
    //value constuctor
    public tokens(String classPart,String valuePart,int LineNum){
        this.CP=classPart;
        this.VP=valuePart;
        this.line=LineNum;
    }
    // setter and getters for CP,VP,LineNumber
    public void setCP(String classPart){
        this.CP=classPart;
    }
    public String getCP(){
        return CP;
    }
     public void setVP(String valuePart){
        this.VP=valuePart;
    }
    public String getVP(){
        return VP;
    }
     public void setLine(int LineNum){
        this.line=LineNum;
    }
    public int getLine(){
        return line;
    }
    //method adding token 
    
}


