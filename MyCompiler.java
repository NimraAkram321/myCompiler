/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompiler;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author HP
 */
public class MyCompiler extends tokens{
    //Using arraylist for Printing the output
    static ArrayList<tokens> tokens=new ArrayList<>();
    static int d=0;//for index in out line
    static int lineNum=0; //for moving to the next line
    static String temp2="";// just for punctuators
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //Giving input file 
        FileInputStream file=new FileInputStream("test.txt");
        BufferedReader br=new BufferedReader(new InputStreamReader(file));
        
        String strLine;
        //checking for end of the line after null it will increment by 1
        while((strLine=br.readLine())!=null){
            System.out.println("Length of the line :");
            System.out.println(strLine.length());
            
             //creating an object for validation
            validationClass value=new validationClass();
            // a temp variable for storing the value for checking 
            String temp="";
            
            //line first char at 0 that's why -1 length ,
            //this "while" is for the checking 
            //length of the line is equal to "0"
            
            while((strLine.length()-1)==d){/// check hogaa yee 
                temp=String.valueOf(strLine.charAt(d));    // we have a problem with our int-cnst as it wound not  check for the int to  be on our first line 
                if (value.Validate_Unsign(temp)) {
                        tokens id = new tokens("int-const", temp, lineNum);
                        System.out.println("this is for int const on first line ");
                        tokens.add(id);
                        

                    } 
                
                 if(strLine.charAt(0)=='}'){ //when only this occurs in a line.
                  
                tokens pun = new tokens("}", "}", lineNum); //as } is creating issue
                     System.out.println("this is for curly braket close");
                    tokens.add(pun);
              }
                break;
                
            }
            while((strLine.length()-1)!=d){
                //temp will contain the splitword after every breaker 
                //point in the line
                temp=splitwords(strLine);
                System.out.println("temp="+temp);
                
                //check if its empty
                if(temp.isEmpty()){
                    System.out.println("temp is empty");
                }
                //check for comments
                else if(temp.contains("?-")){
                    String comment=String.valueOf(strLine.charAt(d));
                    d++;//for multi line
                    while(!((strLine.charAt(d)=='-')&&(strLine.charAt(d+1)=='?'))){
                        if(d==(strLine.length()-1)){
                            strLine=br.readLine();
                            lineNum++;// increment in line 
                            d=0;// making it zero again 
                        }
                        else{//for single line
                             comment +=String.valueOf(strLine.charAt(d));
                             d++;
                        }
                    }
                    d=strLine.length()-1;
                    System.out.println("comment:"+ comment);
                    
                }
                else if(temp.equals(".")){//if temp contain a "."
                    tokens dot=new tokens("dot",".",lineNum);
                    tokens.add(dot);
                }
                else if(value.Validate_flaot(temp)){
                    tokens Flt=new tokens("float_const",temp,lineNum);
                    tokens.add(Flt);
                }
                else if(Character.isAlphabetic(temp.charAt(0))){
                    int len=temp.length()-1;
                    if(value.Validate_Identifier(String.valueOf(temp))){
                        if(value.Validate_IsKeyword(temp)){
                            tokens keyword=new tokens(KeyWords(value.checking),temp,lineNum);
                            tokens.add(keyword); //keyWords is function 
                        }
                        else if(temp.charAt(len)!='_'){
                            tokens Ident=new tokens("ID",temp,lineNum);
                            tokens.add(Ident);
                        }
                        else{
                            tokens Inv=new tokens("invalid ID",temp,lineNum);
                            tokens.add(Inv);
                        }
                    }
                    else{
                        tokens Inv=new tokens("invalid ID",temp,lineNum);
                        tokens.add(Inv);
                    }
                            
                }
                else{
                    if(value.Validate_Unsign(temp)){
                        tokens INT=new tokens("int_const",temp,lineNum);
                        tokens.add(INT);
                    }
                    else{
                        tokens Inv=new tokens("invalid",temp,lineNum);
                        tokens.add(Inv);
                    }
                }
                if(!temp2.isEmpty()){// for puctuators   // check this too
                    tokens puc=new tokens(temp2,temp2,lineNum);
                    tokens.add(puc);
                    temp2="";//empty it
                }
                
            }
            lineNum++;// line count plus
            d=0; // after every new line d again becomes 0
            
        }
        // file that we will output, seting the path 
        File Fout=new File("output.txt");
        FileOutputStream fos=new FileOutputStream(Fout);
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
           bw.write("(ClassPart,ValuePart,LineNumber)");
           bw.newLine();
           bw.write("<*********><*********><**********>");
           bw.newLine();
           for(int i=0;i<tokens.size();i++){
               bw.write("{"+tokens.get(i).CP+ " ," +tokens.get(i).VP+ " ,"+(tokens.get(i).line+1)+" }");
               bw.newLine();
           }
        bw.close();
    }
    // Function of SplitWords
    public static String splitwords(String st) throws IOException{
        validationClass Val=new validationClass();// new object
        String temp="";
        boolean FltChk=false;
        boolean invalidFlt=false;
        
        for(int i=d;i<=st.length()-1;i++){
            // we check for float breaker
            if(FltChk){//when it will be true, work as a breaker if again . occurs
                if(st.charAt(i)=='.'){
                    d=i;
                    return temp;
                }
                if(st.charAt(i)==';'){// if it occur after float value
                    temp2=";";
                    d=++i;//because i give ; value
                    return temp;
                            
                }
                if(st.charAt(i)==' '){
                    d=++i; 
                    return temp;
                }
                if(Character.isAlphabetic(st.charAt(i))){
                    if(invalidFlt){
                        temp+=st.charAt(i);
                    }
                    else{     //same if 6.abc any letter occurs after . return temp=6.
                        d=i;
                        return temp;
                    }
                }else if(Character.isDigit(st.charAt(i))){
                    invalidFlt=true;
                    temp+=st.charAt(i);
                }
                else{
                    
                }
         
            }
            else if(Character.isAlphabetic(st.charAt(i))){
                temp+=st.charAt(i);
            }
            else if(st.charAt(i)=='_'){
                temp +=st.charAt(i);
            }
            //for space in temp
            else if(st.charAt(i)==' '){
                if(!temp.isEmpty()){
                    i++;
                    d=i;
                    if(d==(st.length()-1)){//when space occurs just before last character 
                        d=--i;
                    }
                    return temp;
                }
            }
            //operator
            else if(Val.validate_operator(String.valueOf(st.charAt(i)))){
                if(!temp.isEmpty()){// this is for breaking 
                    d=i;
                    return temp;
                }
                if(Val.validate_operator(String.valueOf(st.charAt(i+1)))){
                    if(Val.validate_operator(String.valueOf(st.charAt(i)).concat(String.valueOf(st.charAt(i+1))))){//now check combined value like ++
                        tokens op=new tokens(Val.checkingOperator,String.valueOf(st.charAt(i)).concat(String.valueOf(st.charAt(i + 1))), lineNum);
                        tokens.add(op);
                        i=i+1;
                    }
                    else{
                        Val.validate_operator(String.valueOf(st.charAt(i)));
                        tokens op=new tokens(Val.checkingOperator,String.valueOf(st.charAt(i)),lineNum);
                        tokens.add(op);
                    }
                }
                else{
                    Val.validate_operator(String.valueOf(st.charAt(i)));
                    tokens op=new tokens(Val.checkingOperator,String.valueOf(st.charAt(i)),lineNum);
                    tokens.add(op);
                }
            }
            //punctuator
            else if(Val.Validate_Punctuator(String.valueOf(st.charAt(i)))){
                if(!temp.isEmpty()){
                    if((st.length()-1)==i){//if punc occurs at last (because next time we cant aproach this as our method moves to next line
                        temp2=String.valueOf(st.charAt(i));
                    }
                    d=i;
                    return temp;
                }
                if(st.charAt(i)=='.'){//if . comes first then we check for int like .67 
                    temp+=st.charAt(i);
                    while(Val.Validate_Unsign(String.valueOf(st.charAt(i+1)))){
                        i++;
                        temp+=st.charAt(i);
                    }
                }
                else{
                     tokens pun = new tokens(String.valueOf(st.charAt(i)), String.valueOf(st.charAt(i)), lineNum);
                    tokens.add(pun);
                }
            }
            //commnet case
            else if(st.charAt(i)=='?'){
               if (st.charAt(i + 1) == '-') {  //it means it is multi line cmnt
                    temp = "?-";
                    i++;
                    d = i;
                    return temp;          
                } 
               i++;
                String comment = String.valueOf(st.charAt(i));
                while (!(i == (st.length() - 1))) {   //single line comment 
                    i++;
                    comment += st.charAt(i);

                }
                System.out.println("Single Line Comments: " + comment);
            }
            
            else if(Val.Validate_Unsign(String.valueOf(st.charAt(i)))){
                temp += st.charAt(i);
            }
            else if(st.charAt(i)=='.'){
                if ((Val.Validate_Unsign(temp))) {
                       
                    FltChk = true;
                    //    if(val.Validate_Int(String.valueOf(st.charAt(i+1)))){
                    temp += st.charAt(i);
                    // }
                } 
                 else if(Val.Validate_flaot(temp)){
                 
                   d=i;
                   return temp;
                 
                }
                  else if (Val.Validate_Identifier(temp)) {
                     
                    tokens id = new tokens("ID", temp, lineNum);
                    tokens.add(id);
                    if (i < st.length() - 1) {
                        
                        if (Character.isAlphabetic(st.charAt(i + 1))) { //a.b (after .b is also alphabet so . belongs its own class)
                            tokens dot = new tokens("dot", ".", lineNum);
                            tokens.add(dot);
                            temp = "";
                        } else {   //its means that after . digit exist
                            temp = ".";
                           
                           
                        }
                    }

                }
                 else if(temp.isEmpty()){
                    System.out.println("++++"+st.charAt(i+1));
                     // temp += st.charAt(i);
                      tokens dot = new tokens("dot", ".", lineNum);
                      tokens.add(dot);
                    //fc = true;  
                 }
                 else{
                     tokens invalid = new tokens("invalid", temp, lineNum);
                    tokens.add(invalid);
                    if (i < st.length() - 1) {
                        if (Character.isAlphabetic(st.charAt(i + 1))) {
                            tokens dot = new tokens("dot", ".", lineNum);
                            tokens.add(dot);
                            temp = "";
                        } else {
                            temp = ".";
                        }
                    } 
                 }
                
            }
            // char case 
            else if(st.charAt(i)=='\''){
                if (!temp.isEmpty()) { //if temp contains already something before first \" so just return it will not be a string constant.

                    d = i;
                    return temp;

                }
                temp+=st.charAt(i);
                if (i == (st.length() - 1)) { //when \' is at end of line

                    d = i;

                    tokens.add(new tokens("invalid-ch", temp, lineNum));
                    temp = "";
                    break;

                }
                if (st.charAt(i + 1) == '\\') {
                     i++;
                    int num = i + 2;
                    while (i <= num && i != (st.length() - 1)) { //at any momemt i aproach at the end of line loop will end. 

                        temp += st.charAt(i);
                        i++;

                    }
                     if (temp.length() != 4) {  //we add last value of line due to above loop condition. but this is only done in one case when temp length is not
                        //equal to 4.
                        temp += st.charAt(i);
                        i++;
                    }

                    i = --i;               //decreamenting as in above loop value comes after increament i++             
                    d = i;
                    if (Val.Validate_char(temp)) {

                        tokens.add(new tokens("char-const", temp, lineNum));
                        temp = "";
                    }
                    else {
                        tokens.add(new tokens("invalid-ch", temp, lineNum));
                        temp = "";

                    }

                }
               else {
                    i++;
                    int num = i + 1;
                    while (i <= num && i != (st.length() - 1)) {

                        temp += st.charAt(i);
                        i++;

                    }
                    if (temp.length() != 3) {// char has a fixed length
                        temp += st.charAt(i);
                        i++;
                    }

                    i = --i;
                    d = i; 
                    if(Val.Validate_char(temp)){
                        tokens.add(new tokens("char-const", temp, lineNum));
                        temp = "";

                    }
                    else{
                        tokens.add(new tokens("invalid-ch", temp, lineNum));
                        temp = "";

                    }
                }
            
             }
            //for String checking "val"
            else if (st.charAt(i) == '\"') {
                if(!temp.isEmpty()){ //if temp contains already something before first \" so just return it will not be a string constant.
                  
                    d=i;
                    return temp;
                
                }
               
               temp+=st.charAt(i);
               i++;
               
               while(st.charAt(i)!='\"'&& st.length()-1!=i){  //it will store everything until it find \" or if \" not exist we have 2nd cond
                   temp+=st.charAt(i);
                   /*if(st.charAt(i)=='\\' && st.charAt(i+1)=='\\'){ //break if double back slash occurs
                     
                      
                       i++;
                       break;
                      
                   }*/
                   i++;  
                   
               }
                 temp+=st.charAt(i);  // to add " 
               
               if(Val.Validate_String(temp)){          //if the string is valid it return true
                  // System.out.println("c=>"+temp);
                    
                  /*int len=temp.length()-1;
                     if(temp.charAt(len)=='\\'||temp.charAt(len-1)!='\\'){
                          tokens.add(new tokenSet("invalid", temp, lineNum));
                           temp = "";
                         return temp;
                     }*/
                  
                     tokens.add(new tokens("String-const", temp, lineNum));
                     temp = "";
                  }
               
               else{
               
                   tokens.add(new tokens("invalid", temp, lineNum));
                     temp = "";
               }
             d=i;
        }
            
            else if(st.charAt(i)=='\n'){
                return temp;
            }
            else {
                tokens id = new tokens("invalid", String.valueOf(st.charAt(i)), lineNum);
                tokens.add(id);

            }

        }
          d = st.length() - 1;
        System.out.println("==>d" + d);
   
        return temp;
        
    }
   
    // function fo
    public static String KeyWords(int ch){
        switch(ch){
            case 0:
                return "DT";
            case 1:
                return "String";
            case 2:
                return "for";
            case 3:
                return "if";
            case 4:
                return "else";
            case 5:
                return "break";
            case 6:
                return "continue";
            case 7:
                return "Access Modifier";
            case 8:
                return "extends";
            case 9:
                return "implement";
            case 10:
                return "refer";
            case 11:
                return "new";
            case 12:
                return "bool_const";
            case 13:
                return "enum";
            default:
                System.out.println("none");
                        
        }
        return null;
    }
}
