/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/// nimraa wala /// 

package mycompiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


/**
 *
 * @author NIMRA
 */

//We add classes in main table //
class main_table{  
  String name;  
  String type;  
  String access_modifier;
  String category;
  String parent;
  

    public main_table(String name, String type, String access_modifier, String category, String parent) {
        this.name = name;
        this.type = type;
        this.access_modifier = access_modifier;
        this.category = category;
        this.parent = parent;
        
    }
  
  }
//we add data of attributes and function in class_table/Data_table //
class class_table{  
  String name;  
  String type;  
  String access_modifier;
  String type_modifier;
  String link;

    public class_table(String name, String type, String access_modifier, String type_modifier, String link) {
        this.name = name;
        this.type = type;
        this.access_modifier = access_modifier;
        this.type_modifier = type_modifier;
        this.link = link;
    }
  
  }
//we add data of function 
class function_table{
    String name;  
  String type;  
  int scope;


    public function_table(String name, String type, int scope) {
        this.name = name;
        this.type = type;
        this.scope = scope;
      
    }
}
//the list of all data table for every class //
class store_all_class_table{
    ArrayList<class_table> separate_class_table;

    public store_all_class_table(ArrayList<class_table> separate_class_table) {
        this.separate_class_table = separate_class_table;
    }
}



public class Syntax {
    

    /// for MAIN TABLE 
    static ArrayList<main_table> main_table_arraylist=new ArrayList<main_table>();
    String name_of_class = "";
    String type_of_class = "";
    String access_modifier_of_class = "";
    String catagory_of_class = "";
    String parent = "";
    
        /// for DATA_TBALE
    static ArrayList<class_table> separate_class_table=new ArrayList<class_table>();
    static ArrayList<store_all_class_table> store_all_class_table=new ArrayList<store_all_class_table>(); 
    String access_modifier_of_datable = "";
    String type_modifier_of_data_table = "";
    String type_of_data_table = "";
    String name_of_data_table = "";
    // for type attribute and concatinate with a arrow and comma 
    String retrunValue_of_data_table="";
    String PL_value_of_data_table="";
    String oldval;
    
    /// for FUNTION TABLE
     static ArrayList<function_table> function_table=new ArrayList<function_table>();
     static Stack<Integer> stack = new Stack<Integer>(); 
    int stack_number=0;
    String name_of_function_table = "";
    String type_of_funtion_table = "";
    static Stack<Integer> copy_of_stack = new Stack<Integer>(); 
    
    
    
     static int i = 0;
   // static int scope=0;
    static ArrayList<tokens> token = new ArrayList<>();
 
    
  
    
    
     public Syntax(){}

    public Syntax(ArrayList<tokens> token1) {

        token = token1;
     
    }
     public void validate() {

        if (s()) {

            if (i == token.size() - 1) {
                System.out.println("Valid Syntax");
            }

        }  else {
           // System.out.println(token.get(i).CP); // checking it working
            System.out.println("Error Occured At Line No: " + (token.get(i).line + 1));
        }

      
       
    }
    boolean s() {
       
            
            if(class2()){
              if(def()){
                  System.out.println("---------------------MAIN TABLE----------------------");
                  System.out.println("Name|TYPE  |AM       |category  |parent");
                  for(int i=0;i<main_table_arraylist.size();i++){
                      
             System.out.println(main_table_arraylist.get(i).name +"  "+main_table_arraylist.get(i).type+"    "+ main_table_arraylist.get(i).access_modifier+"    "+ main_table_arraylist.get(i).category+"      "+ main_table_arraylist.get(i).parent);
         }
        
                  System.out.println("---------------------DATA TABLE-----------------------");
                  System.out.println("N| TYPE  |  AM  |Category |Main table class   ");
         
         for(int i=0;i<store_all_class_table.size();i++){
             for(int j=0;j<store_all_class_table.get(i).separate_class_table.size();j++){
                              System.out.println( store_all_class_table.get(i).separate_class_table.get(j).name+"  "+store_all_class_table.get(i).separate_class_table.get(j).type+"    "+ store_all_class_table.get(i).separate_class_table.get(j).access_modifier+"    "+ store_all_class_table.get(i).separate_class_table.get(j).type_modifier+"      "+ store_all_class_table.get(i).separate_class_table.get(j).link);

             }
         }
            System.out.println("------------------FunctionTable--------------------");
         
         for(int i=0;i<function_table.size();i++){
             
             System.out.println(function_table.get(i).name +"  "+function_table.get(i).type+"    "+ function_table.get(i).scope);
         }
          return true;
                   
                  
              }
              
          }
         else{
              if(token.get(i).CP.equals("$")){
                  i++;// yee hogaa kee nhi ?
                  
                  return true;
              }
        }
          
       
          

    /*   if (def()) {   //selection set see sahi hogaa 
            i++;
            if (token.get(i).CP.equals("$")) {
                return true;
            } else {

                return s();
            }
        }*/
        return false;
    }
    boolean AM()
    {
    if(token.get(i).CP.equals("Access Modifier"))
    {
        access_modifier_of_class=token.get(i).VP;
        
        
        /// we have syntax issue wtih setting AM for Attributes 
       // access_modifier_of_datable = token.get(i).VP;;
        i++;
        return true;
    }
    else
    {if(token.get(i).CP.equals("ID")||
                token.get(i).CP.equals("static")||
                token.get(i).CP.equals("final")||
                token.get(i).CP.equals("abstract")
            ||token.get(i).CP.equals("class")||token.get(i).CP.equals("DT")
            ||token.get(i).CP.equals("enum")|| token.get(i).CP.equals("void")){
        
        return true;
    }
        }
    return false;
    }
    
    boolean class2(){
        if(token.get(i).CP.equals("Access Modifier")||
                token.get(i).CP.equals("static")||
                token.get(i).CP.equals("final")||
                token.get(i).CP.equals("abstract")||token.get(i).CP.equals("class")){
            
            if(AM())
            {
              if(A_S_F()){
                if(token.get(i).CP.equals("class"))
                    type_of_class=token.get(i).VP;
                    i++;
                    if(token.get(i).CP.equals("ID")){
                        name_of_class=token.get(i).VP;
                        
                        
                        i++;
                        if(inh()){
                            if(token.get(i).CP.equals("{"))
                                i++;
                              if(body_c()){
                                 if(fun_def_m()){
                                    
                                     if(token.get(i).CP.equals("}")){
                                         
                                         ArrayList<class_table> arrayListClone =  (ArrayList<class_table>) separate_class_table.clone();
                                         store_all_class_table.add(new store_all_class_table(arrayListClone));
                                         separate_class_table.clear();
                                         
                                          name_of_class = "";
                                         type_of_class = "";
                                         access_modifier_of_class = "";
                                         catagory_of_class = "";
                                         parent = "";
                                         i++;
                                         return true;
                                     }
                                 }
                             }
                        }
               }   }
            }
        }
   
        return false ;
        
    }
    
    boolean def(){
        if(token.get(i).CP.equals("class")||token.get(i).CP.equals("Access Modifier")
                ||token.get(i).VP.equals("static")||token.get(i).VP.equals("final")
                ||token.get(i).VP.equals("abstract")){
            
            if(AM()){
                
                if(e_c1()){
                    return true;
                }
               
            }
           
        }
        
        else{
            if(token.get(i).CP.equals("$")){
                //i++;  ana chaiyee yaa nhi 
                return true;
            }
        }
        return false;
    } 
    boolean e_c1(){
     
         if(token.get(i).CP.equals("enum")||token.get(i).CP.equals("abstract")
                 ||token.get(i).CP.equals("static")
                 ||token.get(i).CP.equals("final")
                 ||token.get(i).CP.equals("class")){
             
         if(token.get(i).CP.equals("enum")){
          i++;
          if(token.get(i).CP.equals("ID")){
              i++;
              if(token.get(i).CP.equals("{")){
                  i++;
                  if(enum_body()){
                      if(token.get(i).CP.equals("}")){
                                         ArrayList<class_table> arrayListClone =  (ArrayList<class_table>) separate_class_table.clone();
                                         store_all_class_table.add(new store_all_class_table(arrayListClone));
                                         separate_class_table.clear();
                                            name_of_class = "";
                                         type_of_class = "";
                                         access_modifier_of_class = "";
                                         catagory_of_class = "";
                                         parent = "";
                          i++;
                          return true;
                      }
                  }
              }
          }
      }
      
          if(A_S_F()){
              if(token.get(i).CP.equals("class")){
                  type_of_class=token.get(i).VP;
                  i++;
                  if(token.get(i).CP.equals("ID")){
                      name_of_class=token.get(i).VP;
                     //insert_main_table(name_of_class,type_of_class ,access_modifier_of_class ,catagory_of_class ,parent);
                      i++;
                      if(inh()){
                          if(token.get(i).CP.equals("{")){
                              i++;
                              if(body_c()){
                                  if(token.get(i).CP.equals("}")){
                                      
                                      ArrayList<class_table> arrayListClone =  (ArrayList<class_table>) separate_class_table.clone();
                                         store_all_class_table.add(new store_all_class_table(arrayListClone));
                                         separate_class_table.clear();
                                      name_of_class = "";
                                         type_of_class = "";
                                         access_modifier_of_class = "";
                                         catagory_of_class = "";
                                         parent = "";
                                      i++;
                                      if(def()){
                                          return true;
                                      }
                                  }
                              }
                          }
                      }
                  }
              }
          }
       }
         
        return false;
    }
    boolean body_c(){
        if(token.get(i).CP.equals("static")// removed access modifier
                ||token.get(i).CP.equals("final")||token.get(i).CP.equals("abstract")
                ||token.get(i).CP.equals("DT")||token.get(i).CP.equals("ID")
                ||token.get(i).CP.equals("enum")){
             if(AM()){
             if(C_A_Fdef_e()){
                 
                     return true;  
                 
                 
                 }
            
                }
        }
        else{
            if(token.get(i).CP.equals("Access Modifier")||token.get(i).CP.equals("}")){
                
                return true;
            }
        
        }
       
       return false;
    }
    boolean C_A_Fdef_e(){
        if(token.get(i).CP.equals("abstract")||token.get(i).CP.equals("final")||token.get(i).CP.equals("static")||
                token.get(i).CP.equals("DT")||token.get(i).CP.equals("ID")||
                token.get(i).CP.equals("enum")){
            if(A_S_F()){
                if(token.get(i).CP.equals("DT")){
                    type_of_data_table = token.get(i).VP;
                    
                    retrunValue_of_data_table=token.get(i).VP;
                    i++;
                    if(token.get(i).CP.equals("ID")){
                        name_of_data_table = token.get(i).VP;
                        i++;
                        if(A_def()){
                            if(body_c()){
                                 return true;
                            }

                        }
                    }
                }
            }
            if(token.get(i).CP.equals("DT")){
                 retrunValue_of_data_table=token.get(i).VP;
                i++;
                if(token.get(i).CP.equals("ID")){
                    name_of_data_table=token.get(i).VP;
                    i++;
            if(token.get(i).CP.equals("(")){
                i++;
                if(pl()){
                    if(token.get(i).CP.equals(")")){
                         //insert_class_table(name_of_data_table, type_of_data_table, access_modifier_of_datable, type_modifier_of_data_table,  name_of_class);
                        i++;
                        if(token.get(i).CP.equals("{")){
                            i++;
                            if(MST()){
                                if(token.get(i).CP.equals("}")){
                                    i++;
                                    if(body_c()){
                                 return true;
                            }
                                }
                            }
                        }
                    }
                }
            }
        }
            }
                if(token.get(i).CP.equals("enum")){
            i++;
            if(token.get(i).CP.equals("ID")){
                i++;
                if(token.get(i).CP.equals("{")){
                    i++;
                    if(enum_body()){
                        if(token.get(i).CP.equals("}")){
                            i++;
                             if(body_c()){
                                 return true;
                            }
                        }
                    }
                }
            }
        }
        }

    
        
        return false;
    }
    boolean A_def(){
        if(token.get(i).CP.equals(";")||token.get(i).CP.equals("Assignment Operator")||token.get(i).CP.equals("(")){
           
            if(attr_()){
                return true;
            }
            
        
        if(token.get(i).CP.equals("(")){
            i++;
            if(pl()){
                if(token.get(i).CP.equals(")"))
                    insert_class_table(name_of_data_table, type_of_data_table, access_modifier_of_datable, type_modifier_of_data_table,  name_of_class);   
                    
                    i++;
                if(token.get(i).CP.equals("{")){
                    i++;
                    if(MST()){
                        if(token.get(i).CP.equals("}")){
                            i++;
                            return true;
                        }
                    }
                }
            }
            
        }
        }
        
        return false;
    }
   
     boolean OE(){
         if(token.get(i).CP.equals("int_const")||
                 token.get(i).CP.equals("char_const")||
                 token.get(i).CP.equals("boolean_const")||
                 token.get(i).CP.equals("String_const")||
                 token.get(i).CP.equals("float_const")||
                 token.get(i).CP.equals("(")||
                 token.get(i).CP.equals("!")||
                 token.get(i).CP.equals("ID")||
                 token.get(i).CP.equals("this")||
                 token.get(i).CP.equals("super")||
                 token.get(i).CP.equals("inc_dec")){
             
              if(AE()){
              if(OE_()){
                 return true; 
             }
            }
         }
        
         
         return false;
     }
     boolean OE_(){
         if(token.get(i).CP.equals("||")){
             if(AE()){
                 if(OE_()){
                     return true;
                 }
             }
         }
         else{
             if(token.get(i).CP.equals(";")||
                     token.get(i).CP.equals(")")||
                     token.get(i).CP.equals(",")||
                     token.get(i).CP.equals("]"))
             {
                
                 return true;
             }
         }
         return false;
     }
     boolean AE(){
         if(token.get(i).CP.equals("int_const")||
                 token.get(i).CP.equals("char_const")
                 ||token.get(i).CP.equals("boolean_const")||
                token.get(i).CP.equals("String_const")
                 ||token.get(i).CP.equals("float_const")||token.get(i).CP.equals("(")||
                 token.get(i).CP.equals("!")||token.get(i).CP.equals("ID")||token.get(i).CP.equals("this")||
                 token.get(i).CP.equals("super")||token.get(i).CP.equals("inc_dec")){
              
               if(RE()){
             
                if(AE_()){
                 
                 return true; 
               }
            }
        }
         return false; 
     }
     boolean AE_(){
         if(token.get(i).CP.equals("&&")){
            i++;
             if(RE()){
                 
                 if(AE_()){
                     
                     return true; 
                 }
             }
         }
         else{
          if(token.get(i).CP.equals(";")
                  ||token.get(i).CP.equals("||")
                  ||token.get(i).CP.equals(",")
                  ||token.get(i).CP.equals(")")
                  ||token.get(i).CP.equals("]")){
              
              return true;
          }    
         }
         return false;
     }
     boolean RE(){
         if(token.get(i).CP.equals("int_const")||
                 token.get(i).CP.equals("char_const")
                 ||token.get(i).CP.equals("boolean_const")||
                token.get(i).CP.equals("String_const")
                 ||token.get(i).CP.equals("float_const")||token.get(i).CP.equals("(")||
                 token.get(i).CP.equals("!")||token.get(i).CP.equals("ID")||token.get(i).CP.equals("this")||
                 token.get(i).CP.equals("super")||token.get(i).CP.equals("inc_dec")){
             
             if(E()){
             
             if(RE_()){
                 
                 return true;
                }
            }
         }
        
         return false;
     }
     boolean RE_(){
         if(token.get(i).CP.equals("Relational Operator")){
             i++;
             if(E()){
                 
                 if(RE_()){
                     
                     return true;
                 }
             }
         }
         else{
            if(token.get(i).CP.equals(";")
                    ||token.get(i).CP.equals("||")
                    ||token.get(i).CP.equals(")")
                    ||token.get(i).CP.equals("&&")
                    ||token.get(i).CP.equals(",")||
                    token.get(i).CP.equals("]")){
                
                return true;
            }             
         }
         return false;
     }

     boolean E(){
          if(token.get(i).CP.equals("int_const")||
                 token.get(i).CP.equals("char_const")
                 ||token.get(i).CP.equals("boolean_const")
                 ||token.get(i).CP.equals("String_const")
                 ||token.get(i).CP.equals("float_const")
                 ||token.get(i).CP.equals("(")||
                 token.get(i).CP.equals("!")||token.get(i).CP.equals("ID")
                 ||token.get(i).CP.equals("this")||
                 token.get(i).CP.equals("super")
                 ||token.get(i).CP.equals("inc_dec")){
             
             if(T()){
             
             if(E_()){
                 
                 return true;
             }
         }
         }
         
         return false;
     }
     boolean E_(){
         if(token.get(i).CP.equals("PM")){
             i++;
             if(T()){
                 
                 if(E_()){
                 
                     return true;
                 }
             }
             
         }
         else{
             if(token.get(i).CP.equals("Relational Operator")||token.get(i).CP.equals("&&")
                     ||token.get(i).CP.equals("||")||token.get(i).CP.equals(";")
                     ||token.get(i).CP.equals(",")||token.get(i).CP.equals(")")
                     ||token.get(i).CP.equals("]")){
                 
                 return true;
             }
         }
         return false;
     }
     boolean T(){
         if(token.get(i).CP.equals("int_const")||
                 token.get(i).CP.equals("char_const")
                 ||token.get(i).CP.equals("boolean_const")||
                token.get(i).CP.equals("String_const")
                 ||token.get(i).CP.equals("float_const")||token.get(i).CP.equals("(")||
                 token.get(i).CP.equals("!")||token.get(i).CP.equals("ID")||token.get(i).CP.equals("this")||
                 token.get(i).CP.equals("super")||token.get(i).CP.equals("inc_dec")){
             
             if(F()){
             if(T_()){
              
                 return true;
             }
            }
         }
         
         return false;
     }
    boolean T_(){
        if(token.get(i).CP.equals("MDM")){
            i++;
            if(F()){
                
                if(T_()){
                    
                    return true;
                }
            }
        }
        else{
           if(token.get(i).CP.equals("PM")||token.get(i).CP.equals("Relational Operator")
                   ||token.get(i).CP.equals("&&")
                   ||token.get(i).CP.equals("||")||
                 token.get(i).CP.equals(";")||token.get(i).CP.equals(",") ||
                   token.get(i).CP.equals(")")||
                   token.get(i).CP.equals("]"))
               
           return true;
                       
        }
        return false;
    }
    boolean F(){
        if(token.get(i).CP.equals("int_const")
                ||token.get(i).CP.equals("char_const")
                ||token.get(i).CP.equals("String_const")
                ||token.get(i).CP.equals("boolean_const")||
                token.get(i).CP.equals("float_const")||token.get(i).CP.equals("(")||token.get(i).CP.equals("!")||token.get(i).CP.equals("ID")||token.get(i).CP.equals("this")||token.get(i).CP.equals("super")||
                token.get(i).CP.equals("inc_dec")){
           
            if(Const()){
         
            return true;
           }
            if(token.get(i).CP.equals("(")){
            i++;
            if(OE()){
                if(token.get(i).CP.equals(")")){
                    i++;
                     return true;
                }
                  
            }
         
            }
            if(token.get(i).CP.equals("!")){
                  i++;
                  if(F()){
                 return true;
                 }
            }   
            if(ZF()){
            
            return true;
           }
         }  
        return false;
    }
    boolean ZF(){
        if(token.get(i).CP.equals("ID")||token.get(i).CP.equals("this")
                ||token.get(i).CP.equals("super")||token.get(i).CP.equals("inc_dec")){
            
            if(token.get(i).CP.equals("ID")){
                i++;
                if(ZF2()){
                    return true;
                }
            }
            if(token.get(i).CP.equals("this")){
               i++;
               if(token.get(i).CP.equals("dot")){
                   i++;
                   if(token.get(i).CP.equals("ID")){
                       i++;
                        if(ZF2()){
                       return true;
                   }
                   }
                  
               }
               
            }
            if(token.get(i).CP.equals("super")){
                i++;
                if(token.get(i).CP.equals("dot")){
                    i++;
                    if(token.get(i).CP.equals("ID")){
                        i++;
                        if(ZF2()){
                        return true;
                    }
                    }
                    
                }
            }
            if(token.get(i).CP.equals("inc_dec")){
                i++;
                if(token.get(i).CP.equals("ID")){
                    i++;
                    if(ZF2()){
                        return true;
                    }
                }
            }
            
        }
        return false;
    }
    boolean ZF2(){
        if(token.get(i).CP.equals("inc_dec")||token.get(i).CP.equals("dot")||token.get(i).CP.equals("(")||token.get(i).CP.equals("[")){
         
            if(token.get(i).CP.equals("dot")){
                i++;
             if(token.get(i).CP.equals("ID")){
                 i++;
                 if(ZF2()){
                     return true;
                 }
             } 
            
          }
           if(token.get(i).CP.equals("(")){
         i++;
         if(arg()){
             if(token.get(i).CP.equals(")")){
                 i++;
                 if(ZF4()){
                     return true;
                 }
             }
         }
          }
          if(token.get(i).CP.equals("inc_dec")){
              i++;
              return true;
         
          }
          if(token.get(i).CP.equals("[")){
         i++;
         if(OE()){
             if(token.get(i).CP.equals("]")){
                 i++;
                 if(ZF4()){
                     return true;
                 }
             }
         }
          }
        }
     
     else{
         if(token.get(i).CP.equals("MDM")||token.get(i).CP.equals("PM")
                 ||token.get(i).CP.equals("Relational Operator")
                 ||token.get(i).CP.equals("&&")
                 ||token.get(i).CP.equals(",")||token.get(i).CP.equals("||")
                 ||token.get(i).CP.equals(";")||token.get(i).CP.equals(")")||token.get(i).CP.equals("]")){
            
            return true;
         }
     }
        return false;
    }
    boolean ZF4(){
        
        if(token.get(i).CP.equals(".")){
            i++;
            if(token.get(i).CP.equals("ID"))
                i++;
            if(ZF2()){
                return true;
            }
            
        }
       
        else{
             if(token.get(i).CP.equals("MDM")
                     ||token.get(i).CP.equals("PM")
                     ||token.get(i).CP.equals("Relational Operator")
                     ||token.get(i).CP.equals("&&")
                     ||token.get(i).CP.equals(",")
                     ||token.get(i).CP.equals("||")
                     ||token.get(i).CP.equals(";")
                     ||token.get(i).CP.equals(")")
                     ||token.get(i).CP.equals("]")){
            
            return true;
         }
        }
        return false;
    }
    boolean Dec(){
        if(token.get(i).CP.equals("DT")){
            //T=token.get(i).VP;
            //System.out.println("this is the value of T"+T);
            i++;
            if(token.get(i).CP.equals("ID")){
             //   N=token.get(i).VP;
               // System.out.println("this is the value of N"+N);
//               if(!sm.insertClass_FT(N, T, sm.scope)){
//                 //  System.out.println(token.get(i).VP+" is redeclare");
//               }
                i++;
                
                
                if(init()){
                    if(list()){
                           return true;
                    }
                }
            
            }
        }
        return false;
    }
    boolean list(){
        if(token.get(i).CP.equals(";")||token.get(i).CP.equals(",")){
             if(token.get(i).CP.equals(";")){
            i++;
            return true;
         
        }
        if(token.get(i).CP.equals(",")){
            i++;
            if(token.get(i).CP.equals("ID")){
                i++;
                if(init()){
                    if(list()){
                        return true;
                    }
                }
            }
            
        }
        }
       
        return false;
    }
    boolean init(){
        if(token.get(i).CP.equals("Assignment Operator")){
          //  OP="=";
            i++;
            if(OE()){
               return true;
               
            }
        }
        else{
            if(token.get(i).CP.equals(";")||token.get(i).CP.equals(",")){
                
                return true;
            }
        }
        return false;
    }
    
  
   
    boolean Const(){
       if(token.get(i).CP.equals("int_const")
               ||token.get(i).CP.equals("float_const")||
               token.get(i).CP.equals("String_const")
               ||token.get(i).CP.equals("char_const")
               ||token.get(i).CP.equals("boolean_const")){
           i++;
           return true;
       }
        return false;
    }
    
    
    boolean obj_dec(){
        if(token.get(i).CP.equals("ID")){
            
            i++;
            if(token.get(i).CP.equals("ID")){
                i++;
                if(token.get(i).CP.equals("Assignment Operator")){
                    i++;
                    if(token.get(i).CP.equals("ID")){
                        i++;
                        if(token.get(i).CP.equals("(")){
                            i++;
                            if(arg()){
                                if(token.get(i).CP.equals(")")){
                                    i++;
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            
        }
        return false;
    }
    boolean attr(){
        if(token.get(i).CP.equals("Access Modifier")||token.get(i).CP.equals("static")||token.get(i).CP.equals("final")||token.get(i).CP.equals("DT")||token.get(i).CP.equals("abstract")){
            if(AM()){
            
            if(A_S_F()){
                if(token.get(i).CP.equals("DT"))
                    i++;
                if(token.get(i).CP.equals("ID")){
                    i++;
                    if(attr_()){
                        return true;
                    }
                }
            }
        }
        }
        
        return false;
    }
    boolean attr_(){
        if(token.get(i).CP.equals(";")||token.get(i).CP.equals("Assignment Operator")){
             if(token.get(i).CP.equals(";")){
            insert_class_table(name_of_data_table, type_of_data_table, access_modifier_of_datable, type_modifier_of_data_table,  name_of_class);    
            i++;
            return true;
        }
        if(token.get(i).CP.equals("Assignment Operator")){
            i++;
            if(E()){
                if(token.get(i).CP.equals(";")){
                    i++;
                    return true;
                }
            }
        }
        }
       
        return false;
    }
    boolean pl(){
        if(token.get(i).CP.equals("DT")||token.get(i).CP.equals("ID")){
            if(token.get(i).CP.equals("DT")){
               PL_value_of_data_table=token.get(i).VP;
               
              
              // String PL=PL_value_of_data_table+",";
              // type_of_funtion_table=token.get(i).VP;
              
                      type_of_data_table=oldval+PL_value_of_data_table+"->"+retrunValue_of_data_table;     
                      oldval="";
              i++;
            if(token.get(i).CP.equals("ID")){
                i++;
                if(pl_()){
                    if(pl2()){
                        
                        return true;
                    }
                }
            }
            
        }
        if(token.get(i).CP.equals("ID")){
            i++;
            if(token.get(i).CP.equals("ID")){
                
                i++;
                if(pl2()){
                    return true;
                }
            }
        }
        }
     
        else{
            if(token.get(i).CP.equals(")")){
                
                return true;
            }
        }
        return false;
    }
    boolean pl_(){
        if(token.get(i).CP.equals("[")){
            i++;
            if(OE()){
                if(token.get(i).CP.equals("]")){
                    i++;
                    return true;
                    
                }
            }
            
        }
        else{
            if(token.get(i).CP.equals(")")||token.get(i).CP.equals(",")){
                
                return true;
            }
        }
        return false;
    }
    boolean pl2(){
        if(token.get(i).CP.equals(",")){
             oldval=PL_value_of_data_table+",";
            //PL_value_of_data_table=oldval;
          //  System.out.println(PL_value_of_data_table);
            i++;
            if(pl()){
                return true;
            }
           
        }
        else{
            if(token.get(i).CP.equals(")")){
                
                return true;
            }
        }
        return false;
    }
    boolean arg(){
        if(token.get(i).CP.equals("inc_dec")
                ||token.get(i).CP.equals("String_const")
                ||token.get(i).CP.equals("char_const")
                ||token.get(i).CP.equals("int_const")
                ||token.get(i).CP.equals("float_const")
                ||token.get(i).CP.equals("boolean_const")
                ||token.get(i).CP.equals("(")
                ||token.get(i).CP.equals("!")
                || token.get(i).CP.equals("this")
                ||token.get(i).CP.equals("super")
                ||token.get(i).CP.equals("ID")){
            if(fun_pass_attr()){
            return true;
        }
        }
       
        else{
            if(token.get(i).CP.equals(")"))
             
                return true;
        }
        return false;
    }
    boolean fun_pass_attr(){
        if(token.get(i).CP.equals("inc_dec")
                ||token.get(i).CP.equals("!")
                ||token.get(i).CP.equals("this")
                ||token.get(i).CP.equals("super")
                ||token.get(i).CP.equals("ID")
                ||token.get(i).CP.equals("(")
                ||token.get(i).CP.equals("int_const")
                ||token.get(i).CP.equals("char_const")
                ||token.get(i).CP.equals("String_const")
                ||token.get(i).CP.equals("boolean_const")
                ||token.get(i).CP.equals("float_const")){
            if(E()){
              if(fun_c_attr()){
                  return true;
              }
            
        }
        }
        
     
        return false;
    }
    boolean fun_c_attr(){
        if(token.get(i).CP.equals(",")){
            if(token.get(i).CP.equals(",")){
            i++;
            if(fun_pass_attr()){
                return true;
            }
           
        } 
        }
     
        else{
            if(token.get(i).CP.equals(")")){
                
                return true;
            }
        }
        return false;
    }
    boolean ret_st(){
        if(token.get(i).CP.equals("return")){
            i++;
            if(XO()){
                if(token.get(i).CP.equals(";")){
                    i++;
                    return true;
                }
                
            }
            
        }
        return false;
    }
    boolean XO(){
        if(token.get(i).CP.equals("inc_dec")
                ||token.get(i).CP.equals("!")
                ||token.get(i).CP.equals("this")
                ||token.get(i).CP.equals("super")
                ||token.get(i).CP.equals("ID")
                ||token.get(i).CP.equals("(")
                ||token.get(i).CP.equals("int_const")
                ||token.get(i).CP.equals("char_const")
                ||token.get(i).CP.equals("String_const")
                ||token.get(i).CP.equals("boolean_const")
                ||token.get(i).CP.equals("float_const")){
            if(OE()){
            return true;
            
        }
        }
        
        else{
            if(token.get(i).CP.equals(";")){
                
                return true;
            }
        }
        return false;
    }
   
    boolean inh(){
        if(token.get(i).CP.equals("extends")){
            if(token.get(i).CP.equals("extends")){
            i++;
            if(token.get(i).CP.equals("ID")){
                parent = token.get(i).VP;
                
                List<Object> lookup_main_table_returntype=lookup_main_table(parent);
                
                if(lookup_main_table_returntype.get(0).equals("")){
                    System.out.println("Undeclared");
                }
                if(lookup_main_table_returntype.get(0).equals("class") && lookup_main_table_returntype.get(1).equals("final")){
                    System.out.println("class can'not be inherited");
                }
                
                if(!lookup_main_table_returntype.get(0).equals("") && !lookup_main_table_returntype.get(0).equals("class") && !lookup_main_table_returntype.get(1).equals("final")     ){
                    
                    insert_main_table(name_of_class,type_of_class ,access_modifier_of_class ,catagory_of_class ,parent);
                }
//                if(lookup_main_table_returntype.get(4).equals("private")){
//                        
//                }
                
                
                i++;
                return true;
            }
           
        }
        }
        
        else{
            if(token.get(i).CP.equals("{")){
                insert_main_table(name_of_class,type_of_class ,access_modifier_of_class ,catagory_of_class ,parent);
                return true;
            }
        }
        return false;
    }
    boolean A_S_F(){
        if(token.get(i).CP.equals("static")||token.get(i).CP.equals("final")||token.get(i).CP.equals("abstract")){
            if(token.get(i).CP.equals("static")){
                type_modifier_of_data_table=token.get(i).VP;
            i++;
            return true;
        }
         if(token.get(i).CP.equals("final")){
             catagory_of_class=token.get(i).VP;
             type_modifier_of_data_table=token.get(i).VP;
            i++;
            return true;
        }
          if(token.get(i).CP.equals("abstract")){
              catagory_of_class=token.get(i).VP;
              type_modifier_of_data_table=token.get(i).VP;
            i++;
            return true;
        }
        
        }
        
        else {
            if(token.get(i).CP.equals("DT")||token.get(i).CP.equals("class")
                    ||token.get(i).CP.equals("void")
                    ||token.get(i).CP.equals("ID")){
                
                return true;
            }
        }
        return false;
    }
    
    boolean Array_in(){
        if(token.get(i).CP.equals("DT")){
            i++;
            if(token.get(i).CP.equals("[")){
                i++;
                if(token.get(i).CP.equals("int_const")){
                    i++;
                    if(token.get(i).CP.equals("]")){
                        i++;
                        if(token.get(i).CP.equals("Assignment Operator")){
                            i++;
                            if(OE()){
                                return true;
                            }
                        }
                    }
                }
            }
            
        }
        return false;
    }
    boolean Array_list(){
        if(token.get(i).CP.equals("ArrayList")){
            i++;
            if(token.get(i).CP.equals("<")){
                i++;
                if(token.get(i).CP.equals("ID")){
                    i++;
                    if(token.get(i).CP.equals(">")){
                        i++;
                        if(token.get(i).CP.equals("Assignment Operator")){
                            i++;
                            if(token.get(i).CP.equals("new")){
                                i++;
                                if(token.get(i).CP.equals("ArrayList")){
                                    i++;
                                    if(token.get(i).CP.equals("<")){
                                        i++;
                                        if(token.get(i).CP.equals("ID")){
                                            i++;
                                            if(token.get(i).CP.equals(">")){
                                                i++;
                                                if(token.get(i).CP.equals("(")){
                                                    i++;
                                                    if(token.get(i).CP.equals(")")){
                                                        return true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
        }
        return false;
    }
    boolean ret_type(){
        if(token.get(i).CP.equals("void")||token.get(i).CP.equals("DT")||token.get(i).CP.equals("ID")){
            if(token.get(i).CP.equals("void")){
                i++;
                return true;
            }
            if(token.get(i).CP.equals("DT")){
                i++;
                return true;
            }
            if(token.get(i).CP.equals("ID")){
                i++;
                if(ret_type_()){
                    return true;
                }
            }
           
        }
        return false;
    }
    boolean ret_type_(){
        if(token.get(i).CP.equals("[")){
            i++;
            if(OE()){
                if(token.get(i).CP.equals("]")){
                    i++;
                    return true;
                }
            }
            
        }
        else{
            if(token.get(i).CP.equals("ID")){
                
                return true;
            }
        }
        return false;
    }
    
    boolean for_st(){
        
        if(token.get(i).CP.equals("for")){
            i++;
            if(token.get(i).CP.equals("(")){
                i++;
                if(c1()){
                    
                    if(c2()){
                        
                        if(token.get(i).CP.equals(";")){
                            i++;
                            if(c3()){
                            
                                if(token.get(i).CP.equals(")")){
                               i++;
                                  if(token.get(i).CP.equals("{")){
                                   i++;
                                   if(body()){
                                       if(token.get(i).CP.equals("}")){
                                           i++;
                                           return true;
                                       }
                                   }
                               }
                                   
                                }
                            }
                        }
                        
                    }
                }
            }
        }
        return false;
    }
    
 
   /// masla in C1

    
    boolean c1(){
        if(token.get(i).CP.equals("DT")||token.get(i).CP.equals("this")||token.get(i).CP.equals("super")||token.get(i).CP.equals("ID")){
           if(Dec()){
                  return true; 

        }
         if(Assign_st()){
            return true;
          }
        
        if(token.get(i).CP.equals(";")){
            i++;
            return true;
        }
        }
  
        return false;
    }
    
    boolean c2(){
        if(token.get(i).CP.equals("int_const")||token.get(i).CP.equals("float_const")
                ||token.get(i).CP.equals("boolean_const")
                ||token.get(i).CP.equals("String_const")||
                token.get(i).CP.equals("char_const")||token.get(i).CP.equals("inc_dec")
                ||token.get(i).CP.equals("this")||token.get(i).CP.equals("super")||
                token.get(i).CP.equals("(")||token.get(i).CP.equals("!")||token.get(i).CP.equals("ID")){
                      
                           if(OE()){
            
                            return true; 
                         }
        }
        else{
            if(token.get(i).CP.equals(";")){
                 
            return true;
            }
               
        }
        return false;
    }
    
    boolean c3(){
        if(token.get(i).CP.equals("this")
                ||token.get(i).CP.equals("super")
                ||token.get(i).CP.equals("ID")||
                token.get(i).CP.equals("inc_dec")){
       
          
             if(ts()){
                 if(token.get(i).CP.equals("ID")){
                     i++;
                     if(ref()){
                         if(c3_()){
                             return true;
                         }
                     }
                 }
             
        }
           if(token.get(i).CP.equals("ince_dec")){
            i++;
            if(ts()){
                if(token.get(i).CP.equals("ID")){
                    i++;
                    if(ref()){
                        return true;
                    }
                }
            }
        }     
            
        }
       
        else{
            if(token.get(i).CP.equals(")")){
                
                return true;
            }
        }
      return false;
    }
    boolean c3_(){
        if(token.get(i).CP.equals("inc_dec")||token.get(i).CP.equals("Relational Operator")||token.get(i).CP.equals("Assignment Operator")){
            if(token.get(i).CP.equals("inc_dec")){
            
            i++;
            return true;
        }
        
        if(token.get(i).CP.equals("Assignment Operator")){
              i++;
            if(OE()){
                if(Assign_stN()){
                    return true;
                   }
                }
    
         }
        }
        return false;
    } 
    boolean Assign_stN(){
        if(token.get(i).CP.equals("this")||token.get(i).CP.equals("super")||token.get(i).CP.equals("ID")){
            if(Assign_st()){
                return true;
            }
        }
        else{
            if(token.get(i).CP.equals(")")){
                return true;
            }
        }
     return true;   
    }
    boolean ts(){
        if(token.get(i).CP.equals("this")||token.get(i).CP.equals("super")){
            
            if(token.get(i).CP.equals("this")){
                i++;
                 if(token.get(i).CP.equals("dot")){
                i++;
                return true;
            }
           
            }
            if(token.get(i).CP.equals("super")){
                i++;
                 if(token.get(i).CP.equals("dot")){
                i++;
                return true;
            }
           
            }
            
            
        }
        else{
            if(token.get(i).CP.equals("ID")){
                
                return true;
            }
        }
        return false;
    }
    boolean Assign_st(){
        if(token.get(i).CP.equals("this")||token.get(i).CP.equals("super")||token.get(i).CP.equals("ID")){
            
            if(ts()){
                if(token.get(i).CP.equals("ID")){
                    i++;
                    if(ref()){
                        if(token.get(i).CP.equals("Assignment Operator")){
                            i++;
                            if(OE()){
                                return true;
                            }
                        }
                    }
                }
            }
        }
       
        return false;
    }
    boolean ref(){
      if(token.get(i).CP.equals("dot")||token.get(i).CP.equals("[")||token.get(i).CP.equals("(")){
        if(token.get(i).CP.equals("dot")){
            i++;
            if(token.get(i).CP.equals("ID")){
                i++;
                if(ref()){
                    return true;
                }
            }
        }
        if(token.get(i).CP.equals("[")){
            i++;
            if(OE()){
                if(token.get(i).CP.equals("]")){
                    i++;
                    if(ref2()){
                        return true;
                    }
                }
            }
        }
        if(token.get(i).CP.equals("(")){
            i++;
            if(OE()){
                if(token.get(i).CP.equals(")")){
                    i++;
                    if(ref3()){
                        return true;
                    }
                }
            }
            
        }
            
        }
       
        else{
            if(token.get(i).CP.equals("inc_dec")||token.get(i).CP.equals("Assignment Operator")||token.get(i).CP.equals("Relational Operator")||token.get(i).CP.equals(")")){
                
                return true;
            }
        }
        return false;
    }
    boolean ref2(){
        
        if(token.get(i).CP.equals("dot")){
            i++;
            if(token.get(i).CP.equals("ID")){
                i++;
                if(ref()){
                    return true;
                }
            }
        }
        else{
            if(token.get(i).CP.equals("inc_dec")||token.get(i).CP.equals("Assignment Operator")||token.get(i).CP.equals("Relational Operator")||token.get(i).CP.equals(")")){
                
                return true;
            }
        }
        return false;
    }
    boolean ref3(){
        if(token.get(i).CP.equals("dot")||token.get(i).CP.equals("[")){
          if(token.get(i).CP.equals("dot")){
            i++;
            if(token.get(i).CP.equals("ID")){
                i++;
                if(ref()){
                    return true;
                }
            }
        }
          if(token.get(i).CP.equals("[")){
                i++;
                if(OE()){
                    if(token.get(i).CP.equals("]")){
                        i++;
                        if(ref2()){
                            return true;
                        }
                    }
                }
            }
          
        }
      
        return false;
    }
    boolean MST(){
        if(token.get(i).CP.equals("for")
                ||token.get(i).CP.equals("if")
                ||token.get(i).CP.equals("return")
                ||token.get(i).CP.equals("continue")
                ||token.get(i).CP.equals("ArrayList")
                ||token.get(i).CP.equals("break")
                ||token.get(i).CP.equals("DT")
                ||token.get(i).CP.equals("ID")
                ||token.get(i).CP.equals("super")
                ||token.get(i).CP.equals("this")
                ||token.get(i).CP.equals("inc_dec")){
            
            if(SST()){
                if(MST()){
                    return true;
                }
            }
        }
        else{
            if(token.get(i).CP.equals("}")){
                return true;
            }
        }
        return false;
    }
    boolean SST(){
       if(token.get(i).CP.equals("for")
                ||token.get(i).CP.equals("if")
                ||token.get(i).CP.equals("return")
                ||token.get(i).CP.equals("continue")
                ||token.get(i).CP.equals("break")
                ||token.get(i).CP.equals("ArrayList")
                ||token.get(i).CP.equals("DT")
                ||token.get(i).CP.equals("ID")
                ||token.get(i).CP.equals("super")
                ||token.get(i).CP.equals("this")
                ||token.get(i).CP.equals("inc_dec")){
            
          if(token.get(i).CP.equals("for")){
              
              if(for_st()){
                   return true;
              }
             
          }
          if(token.get(i).CP.equals("if")){
              if(if_else()){
                   return true;
              }
            
          }
          if( token.get(i).CP.equals("return")){
              if(ret_st()){
                  return true;
              }
          }
          if(token.get(i).CP.equals("break")){
              i++;
              if(token.get(i).CP.equals(";")){
                  i++;
                  return true;
              }
          }
          if(token.get(i).CP.equals("continue")){
              i++;
              if(token.get(i).CP.equals(";")){
                  i++;
                  return true;
              }
              
          }
          if(token.get(i).CP.equals("ArrayList")){
              if(Array_list()){
                  return true;
              }
          }
          if(token.get(i).CP.equals("DT")){
              i++;
              if(Dec_A_dec()){
                  return true;
              }
          }
          if(token.get(i).CP.equals("ID")){
              i++;
              if(Y()){
                  return true;
              }
          }
          if(token.get(i).CP.equals("super")){
              i++;
              if(token.get(i).CP.equals("dot")){
                  i++;
                  if(token.get(i).CP.equals("ID")){
                      i++;
                      if(fun_ref()){
                          return true;
                      }
                  }
              }
              
          }
          if(token.get(i).CP.equals("this")){
              i++;
              if(token.get(i).CP.equals("dot")){
                  i++;
                  if(token.get(i).CP.equals("ID")){
                      i++;
                      if(fun_ref()){
                          return true;
                      }
                  }
              }
              
              
          }
          if(token.get(i).CP.equals("inc_dec")){
              i++;
              if(ts()){
                  if(token.get(i).CP.equals("ID")){
                      i++;
                      if(fun_ref()){
                          return true;
                      }
                  }
              }
              
          }
        }
       return false;
    }

    boolean Y(){
      if(token.get(i).CP.equals("dot")
              ||token.get(i).CP.equals("[")
              ||token.get(i).CP.equals("(")
              ||token.get(i).CP.equals("Assignment Operator")
              ||token.get(i).CP.equals("Relational Operator")
              ||token.get(i).CP.equals("inc_dec")
              ||token.get(i).CP.equals("ID")){
          
          
        if(fun_ref()){
            return true;
        }
        if(token.get(i).CP.equals("ID")){
            i++;
            if(token.get(i).CP.equals("Assignment Operator")){
                i++;
                if(enum_dec_obj_dec()){
                    if(token.get(i).CP.equals(";")){
                        i++;
                        return true;
                    }
                }
            }
        }
        if(token.get(i).CP.equals("[")){
            i++;
            if(token.get(i).CP.equals("int_const")){
                i++;
                if(token.get(i).CP.equals("]")){
                    i++;
                    if(token.get(i).CP.equals("Assignment Operator")){
                        i++;
                        if(OE()){
                            if(token.get(i).CP.equals(";")){
                                i++;
                                return true;
                            }
                        }
                    }
                }
            }
        }
      }
        return false;
    }
    boolean enum_dec_obj_dec(){
        if(token.get(i).CP.equals("new")||token.get(i).CP.equals("ID")){
            if(token.get(i).CP.equals("new")){
                i++;
                if(token.get(i).CP.equals("ID")){
                    i++;
                    if(token.get(i).CP.equals("(")){
                        i++;
                        if(arg()){
                            if(token.get(i).CP.equals(")")){
                                i++;
                                return true;
                            }
                        }
                    }
                }
            }
            if(token.get(i).CP.equals("ID")){
                i++;
                if(token.get(i).CP.equals("dot")){
                    i++;
                    if(Const()){
                        
                        return true;
                    }
                    if(token.get(i).CP.equals("ID")){
                        i++;
                        return true;
                    }
                }
                
            }
        }
        return false;
    }
    boolean fun_ref(){
        if(token.get(i).CP.equals("dot")
                ||token.get(i).CP.equals("[")
                ||token.get(i).CP.equals("(")
                ||token.get(i).CP.equals("Assignment Operator")
                ||token.get(i).CP.equals("Relational Operator")
                ||token.get(i).CP.equals("inc_dec")){
            
            if(token.get(i).CP.equals("dot")){
                i++;
                if(token.get(i).CP.equals("ID")){
                    i++;
                    if(fun_ref()){
                        return true;
                    }
                }
                
            }
            if(token.get(i).CP.equals("[")){
                   i++;
                if(OE()){
                     if(token.get(i).CP.equals("]")){
                    i++;
                    if(fun_ref1()){
                        return true;
                    }
                }
            }
            
            }
            if(token.get(i).CP.equals("(")){
                i++;
                if(arg()){
                    if(token.get(i).CP.equals(")")){
                        i++;
                        if(fend_ref()){
                            return true;
                        }
                    }
                }
                
            }
            if(token.get(i).CP.equals("Assignment Operator")){
                i++;
                if(OE()){
                    if(token.get(i).CP.equals(";")){
                        i++;
                        return true;
                    }
                }
                
            }
            if(token.get(i).CP.equals("inc_dec")){
                i++;
                if(token.get(i).CP.equals(";")){
                    i++;
                    return true;
                }
                
            }
        }
        return false;
        
    }
    boolean fun_ref1(){
        if(token.get(i).CP.equals("dot")
                ||token.get(i).CP.equals("Assignment Operator")
                ||token.get(i).CP.equals("Relational Operator")
                ||token.get(i).CP.equals("inc_dec")){
            
            if(token.get(i).CP.equals("dot")){
                i++;
                if(token.get(i).CP.equals("ID")){
                    i++;
                    if(fun_ref()){
                        return true;
                    }
                }
                
            }
            if(token.get(i).CP.equals("Assignment Operator")){
                i++;
                if(OE()){
                    if(token.get(i).CP.equals(";")){
                        i++;
                          return true;
                    }
                  
                }
                
            }
            if(token.get(i).CP.equals("inc_dec")){
                i++;
                if(token.get(i).CP.equals(";")){
                    i++;
                    return true;
                }
            }
        }
        return false;
    }
    boolean fend_ref(){
        if(token.get(i).CP.equals("dot")
                ||token.get(i).CP.equals(";")
                ||token.get(i).CP.equals("[")){
            if(token.get(i).CP.equals("dot")){
                i++;
                if(token.get(i).CP.equals("ID")){
                    i++;
                    if(fun_ref()){
                        return true;
                    }
                }
                
            }
            if(token.get(i).CP.equals(";")){
                i++;
                return true;
                
            }
            if(token.get(i).CP.equals("[")){
                i++;
                if(OE()){
                    if(token.get(i).CP.equals("]")){
                        i++;
                        if(token.get(i).CP.equals("dot")){
                            i++;
                            if(token.get(i).CP.equals("ID")){
                                i++;
                                if(fun_ref()){
                                    return true;
                                }
                            }
                        }
                    }
                }
                
            }
        }
        return false;
    }
    boolean Dec_A_dec(){
        if(token.get(i).CP.equals("[")||token.get(i).CP.equals("ID")){
            if(token.get(i).CP.equals("ID")){
                i++;
                if(init()){
                    if(list()){
                        return true;
                    }
                }
                
            }
              if(token.get(i).CP.equals("[")){
                    i++;
                    if(token.get(i).CP.equals("]")){
                        i++;
                        
                            if(token.get(i).CP.equals("ID")){
                                i++;
                                if(Array_def()){
                                    if(token.get(i).CP.equals(";")){
                                        i++;
                                        return true;
                                    }
                                }
                            }
                        
                    }
                        
                
                }
            
        }
       return false;
    }
    boolean Array_def(){
        if(token.get(i).CP.equals("Assignment Operator")){
            if(token.get(i).CP.equals("Assignment Operator")){
                i++;
                if(Array_def1()){
                    return true;
                }
            }
           
        }
         else{
                if(token.get(i).CP.equals(";")){
                    return true;
                }
            }   
        return false;
    }
    boolean Array_def1(){
        if(token.get(i).CP.equals("new")||token.get(i).CP.equals("{")){
            if(token.get(i).CP.equals("new")){
                i++;
                if(token.get(i).CP.equals("DT")){
                    i++;
                    if(token.get(i).CP.equals("[")){
                        i++;
                        if(E()){
                            if(token.get(i).CP.equals("]")){
                                i++;
                                return true;
                            }
                        }
                    }
                }
                      
            }
            if(token.get(i).CP.equals("{")){
                i++;
                if(A_const()){
                    if(token.get(i).CP.equals("}")){
                        i++;
                        return true;
                    }
                }
                
            }
        }
        else{
            if(token.get(i).CP.equals(";")){
                return true;
            }
        }
        return false;
    }
    boolean A_const(){
        if(token.get(i).CP.equals("int_const")
                ||token.get(i).CP.equals("char_const")
                ||token.get(i).CP.equals("String_const")
                ||token.get(i).CP.equals("float_const")||
                token.get(i).CP.equals("boolean_const")){
           if(Const()){
               
               if(A_const1()){
                   return true;
               }
               
           }
        }
       
        return false;
    }
    boolean A_const1(){
        if(token.get(i).CP.equals(",")){
            i++;
            if(A_const()){
                return true;
            }
        }
        else{
            if(token.get(i).CP.equals("}"))
                return true;
        }
        return false;
    }
    boolean if_else(){
        if(token.get(i).CP.equals("if")){
            i++;
            if(token.get(i).CP.equals("(")){
                i++;
                if(OE()){
                    if(token.get(i).CP.equals(")")){
                        i++;
                        if(token.get(i).CP.equals("{")){
                            i++;
                            if(body()){
                                if(token.get(i).CP.equals("}")){
                                    i++;
                                    if(Oelse()){
                                        return true;
                                    }
                                }
                            }
                            
                        }
                        
                    }
                }
            }
        }
        return false;
    }
    boolean Oelse(){
        if(token.get(i).CP.equals("else")||token.get(i).CP.equals("else_if")){
            if(token.get(i).CP.equals("else")){
                i++;
                if(token.get(i).CP.equals("{")){
                    i++;
                    if(body()){
                        if(token.get(i).CP.equals("}")){
                            i++;
                            return true;
                        }
                    }
                }
                
            }
            if(token.get(i).CP.equals("else_if")){
                i++;
                if(token.get(i).CP.equals("(")){
                    i++;
                    if(OE()){
                        
                        if(token.get(i).CP.equals(")")){
                            i++;
                             if(token.get(i).CP.equals("{")){
                                 i++;
                                 if(body()){
                                     if(token.get(i).CP.equals("}")){
                                         i++;
                                         if(Oelse()){
                                             return true;
                                         }
                                     }
                                     
                                 }
                             }
                        }
                        
                    }
                }
                
            }
        }
       
        else{
            if(token.get(i).CP.equals("for")
                    ||token.get(i).CP.equals("if")
                    ||token.get(i).CP.equals("return")
                    ||token.get(i).CP.equals("break")||
                    token.get(i).CP.equals("continue")||
                    token.get(i).CP.equals("DT")||
                    token.get(i).CP.equals("ID")||
                    token.get(i).CP.equals("this")||
                    token.get(i).CP.equals("inc_dec")
                    ||token.get(i).CP.equals("super")){
                
                return true;
            }
        }
        return false;
    }
    boolean body(){
        if(token.get(i).CP.equals("for")
                    ||token.get(i).CP.equals("if")
                    ||token.get(i).CP.equals("return")
                    ||token.get(i).CP.equals("break")||
                    token.get(i).CP.equals("continue")||
                    token.get(i).CP.equals("DT")||
                    token.get(i).CP.equals("ID")||
                    token.get(i).CP.equals("this")||
                    token.get(i).CP.equals("inc_dec")
                    ||token.get(i).CP.equals("super")){
            if(MST()){
                return true;
            }
        }
        else{
            if(token.get(i).CP.equals("}")){
                
                return true;
            }
        }
            
        return false;
    }
    
    boolean fun_def_m(){
        if(token.get(i).VP.equals("public")){
            access_modifier_of_datable=token.get(i).VP;
            i++;
            if(token.get(i).CP.equals("static")){
                type_modifier_of_data_table=token.get(i).VP;
                i++;
                if(token.get(i).CP.equals("void")){
                    retrunValue_of_data_table=token.get(i).VP;
                    i++;
                    if(token.get(i).CP.equals("main")){
                        name_of_data_table=token.get(i).VP;
                        
                        i++;
                        if(token.get(i).CP.equals("(")){
                            i++;
                            if(token.get(i).CP.equals("DT")){
                                PL_value_of_data_table=token.get(i).VP;
                                type_of_funtion_table=token.get(i).VP;
                                type_of_data_table=PL_value_of_data_table+"->"+retrunValue_of_data_table;
                                
                                i++;
                                if(token.get(i).CP.equals("[")){
                                    i++;
                                    if(token.get(i).CP.equals("]")){
                                        i++;
                                        if(token.get(i).VP.equals("args")){
                                            name_of_function_table=token.get(i).VP;
                                            i++;
                                            if(token.get(i).CP.equals(")")){
                                                insert_class_table(name_of_data_table, type_of_data_table, access_modifier_of_datable, type_modifier_of_data_table,  name_of_class);
                                                i++;
                                             //   insert_function_table(name_of_function_table ,type_of_funtion_table ,stack.peek());
                                                if(token.get(i).CP.equals("{")){
                                                    create_scope();
                                                    i++;
                                                    if(MST()){
                                                        destory_scope();
                                                        if(token.get(i).CP.equals("}")){
                                                            i++;
                                                            return true;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
       
        
        return false;
    }
    boolean enum_body(){
        if(token.get(i).CP.equals("ID")){
            i++;
            if(enum_body1()){
                if(P()){
                    return true;
                }
            }
            
        }
       
        return false;
    }
    boolean enum_body1(){
        if(token.get(i).CP.equals(",")){
            i++;
            if(token.get(i).CP.equals("ID")){
                i++;
                if(enum_body1()){
                    return true;
                }
            }
        }
        else{
            if(token.get(i).CP.equals("}")){
                
                return true;
            }
        }
        return false;
    }
    boolean P(){
        if(token.get(i).CP.equals("Assignment Operator")){
            i++;
            if(E()){
               return true; 
            }
        }
        else{
            if(token.get(i).CP.equals("}")){
                return true;
            }
        }
        return false;
    }
    
    
    
    boolean insert_main_table(String name, String type, String access_modifier, String catagory, String parent){
//        System.out.println(name);
//        System.out.println(type);
         for(int i =0; i<main_table_arraylist.size(); i++){
             if(name.equals(main_table_arraylist.get(i).name)){
                 
                 System.out.println("Reclaration Error");
                 return false;
             }
         }
         
         main_table_arraylist.add( new main_table(name ,type ,access_modifier, catagory, parent )  );
//         System.out.println(main_table_arraylist.get(0).name);
  //       System.out.println(main_table_arraylist.get(0).type);
         
//         System.out.println(main_table_arraylist.get(1).name);
//         System.out.println(main_table_arraylist.get(1).type);
         
         return true;
        
        
    }
    
    List<Object> lookup_main_table(String name) {
        for(int i =0; i<main_table_arraylist.size(); i++){
             if(name.equals(main_table_arraylist.get(i).name)){
                 
                 return Arrays.asList(main_table_arraylist.get(i).type,main_table_arraylist.get(i).category ,main_table_arraylist.get(i).parent,main_table_arraylist.get(i).access_modifier);
             }
         }
        
        return Arrays.asList("","" ,"","");
    }
    
    boolean insert_class_table(String name, String type, String access_modifier, String type_modifier, String link){
        //System.out.println(name);
        for(int i =0; i<separate_class_table.size(); i++){
             if(name.equals(separate_class_table.get(i).name)){
                 
//                 if(separate_class_table.get(i).type.indexOf(",")!=-1){
//                     
//                 }
                 
                 System.out.println("Reclaration Error");
                 return false;
             }
         }
        separate_class_table.add( new class_table(name ,type ,access_modifier, type_modifier, link ));
        return true;
        
    }
    
        boolean insert_function_table(String name, String type, int scope){
       
        for(int i =0; i<function_table.size(); i++){
             if(name.equals(function_table.get(i).name) && scope==function_table.get(i).scope){
                 
                 System.out.println("Reclaration Error");
                 return false;
             }
         }
        if(!name.equals(""))function_table.add( new function_table(name ,type ,scope)  );
        return true;
        
  
    }
    void create_scope(){
        stack.push(stack_number);
        stack_number++;
    }
    
    void destory_scope(){
        stack.pop();
    }
    
    List<Object> lookup_function_table(String name, int scope){
        
        for(int j =0 ; j<stack.size()-1;j++){
            for(int i =0; i<function_table.size(); i++){
             if(name.equals(function_table.get(i).name) && scope==stack.get(stack.size()-1-j)){
                 int a= stack.get(0);
                 stack.clear();
                 stack.push(a);
                 
                 return Arrays.asList(function_table.get(i).type);
             }
         }
            
            
        }
        int a= stack.get(0);
        stack.clear();
        stack.push(a);
        
        return Arrays.asList("");
      
        
    }
    
    
}

