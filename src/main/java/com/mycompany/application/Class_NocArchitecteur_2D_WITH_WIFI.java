/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.application;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Class_NocArchitecteur_2D_WITH_WIFI {
    
     private ArrayList<ArrayList<Class_noued>> Noc_architecteur;
    private int axe_x=0;
    private int axe_y=0;
    private double Eneregy_in_router=0;
    private double Energey_in_link=0;
    private double Wight_Traffic=0;
    private double bandwith_between_pe=0;
    
    
    private ArrayList<Class_noued> list_neurons_in_one_pe;
    private double Energey_in_router_wifi=0;
    private double Latence_router_wifi=0;
    private ArrayList<Class_ROUTER_WIFI> List_router_position;
    
    public Class_NocArchitecteur_2D_WITH_WIFI(int axe_x,int axe_y, double Eneregy_in_router,double Energey_in_link,double Wight_Traffic,double bandwith_between_pe,double Energey_in_router_wifi,double latence_router_wifi){
    this.axe_x=axe_x;
    this.axe_y=axe_y;
    this.Eneregy_in_router=Eneregy_in_router;
    this.Energey_in_link=Energey_in_link;
    this.Wight_Traffic=Wight_Traffic;
     this.bandwith_between_pe=bandwith_between_pe;
     this.Energey_in_router_wifi=Energey_in_router_wifi;
     this.Latence_router_wifi=latence_router_wifi;
    this.Noc_architecteur=new ArrayList<ArrayList<Class_noued>>();
     this.List_router_position=new ArrayList<Class_ROUTER_WIFI>();
     
     
    
    
   
    
    
    }
    
    public void genere_noc_archiecteur(){
         int index_cree_array=0;
         int fin_index= axe_x*axe_y;
         while(index_cree_array<fin_index){
             this.list_neurons_in_one_pe=new ArrayList<Class_noued> ();
             this.Noc_architecteur.add(index_cree_array,list_neurons_in_one_pe);
             index_cree_array++;
            
         }
         placement_router_wifi();
         //afficherplacement();
        }
    
    
    public void afficherplacement(){
            for (int i = 0; i < 4; i++) {
                System.out.println("voici le router numero "+i+"qui a comme id  "+List_router_position.get(i).getId_router()+
                        "qui a la position  "+List_router_position.get(i).getEmplacement());
            }
    }
    public void placement_router_wifi(){
        ArrayList<Integer> dimension_ensemble= get_ensemble_dimension_x_y(axe_x, axe_y);
        int dernier_position_dans_ensemble = return_position(dimension_ensemble.get(0),dimension_ensemble.get(1));
        ArrayList<Integer> medium_x_y = get_medium_indice_x_y(dernier_position_dans_ensemble);
        
        for(int index=0; index<4;index++){
       
        
       if(index==0){
       //le 1er ensemble 
           Class_ROUTER_WIFI router_wifi= new Class_ROUTER_WIFI(index,return_position(medium_x_y.get(0), medium_x_y.get(1)));
           List_router_position.add(router_wifi);
       
       }
       else{
            if(index==1){
            //le 2eme ensemble 
            if(!((axe_x%2)==0)){
            int position_x_medium = dimension_ensemble.get(0)+medium_x_y.get(0)-1;
            int position_y_medium= medium_x_y.get(1);
            
            Class_ROUTER_WIFI router_wifi= new Class_ROUTER_WIFI(index,return_position(position_x_medium, position_y_medium));
           List_router_position.add(router_wifi);
            }else{
             int position_x_medium = dimension_ensemble.get(0)+medium_x_y.get(0);
            int position_y_medium= medium_x_y.get(1);
            
            Class_ROUTER_WIFI router_wifi= new Class_ROUTER_WIFI(index,return_position(position_x_medium, position_y_medium));
           List_router_position.add(router_wifi);
            
            }
            
            }
            else{
            if(index==2){
                int position_x_medium = medium_x_y.get(0) ;
                int position_y_medium=0;
               if(!((axe_y%2)==0)){
               
               position_y_medium= dimension_ensemble.get(1)+medium_x_y.get(1)-1;
            
                
               
               
               } else{
               
               
                position_y_medium= dimension_ensemble.get(1)+medium_x_y.get(1);
            
                
               }
               Class_ROUTER_WIFI router_wifi= new Class_ROUTER_WIFI(index,return_position(position_x_medium, position_y_medium));
                List_router_position.add(router_wifi);
            
            
            }
            else{
                    if(index==3){
                        
                        int position_x_medium =0 ;
                        int position_y_medium=0;
                        
                         if(!((axe_x%2)==0)){
                            position_x_medium = dimension_ensemble.get(0)+medium_x_y.get(0)-1;
            
                            }else{
                             position_x_medium = dimension_ensemble.get(0)+medium_x_y.get(0);
           
            
                                  }
                         if(!((axe_y%2)==0)){
               
                            position_y_medium= dimension_ensemble.get(1)+medium_x_y.get(1)-1;
            
                            } else{
               
               
                            position_y_medium= dimension_ensemble.get(1)+medium_x_y.get(1);
            
                
                                   }
                         Class_ROUTER_WIFI router_wifi= new Class_ROUTER_WIFI(index,return_position(position_x_medium, position_y_medium));
                          List_router_position.add(router_wifi);
                    
                    
                    
                    }
            
            
            }
            
            }
       
       }
        
        
        
        }
        
        //division la 
    
    
    }
    public ArrayList<Integer> get_ensemble_dimension_x_y(int index_x,int index_y){
    ArrayList<Integer> X_Y= new ArrayList<Integer>();
    
    
    int indice_x_medium = (int)(index_x/2);
    if(!((index_x%2)==0)) indice_x_medium++;
    int indice_y_medium = (int)(index_y/2);
    if(!((index_y%2)==0)) indice_y_medium++;
    X_Y.add(indice_x_medium);
     X_Y.add(indice_y_medium);
    
    
    return X_Y;
    
    }
    public ArrayList<Integer> get_medium_indice_x_y(int position){
    ArrayList<Integer> X_Y = new ArrayList<Integer>();
    int indice_x=return_indice_column_x(position);
    int indice_y=return_indice_ligne_y(position);
    
    int indice_x_medium = (int)(indice_x/2);
    if(!((indice_x%2)==0)) indice_x_medium++;
    int indice_y_medium = (int)(indice_y/2);
    if(!((indice_y%2)==0)) indice_y_medium++;
    X_Y.add(indice_x_medium);
     X_Y.add(indice_y_medium);
    
    
    return X_Y;
    
    }
    public int return_position(int index_x,int index_y){
    
    int position=(index_y*axe_x)-(axe_x-index_x);
    
    return position;}
    
      public int return_indice_column_x(int position_pe){
     if(position_pe<=this.axe_x)return position_pe; 
     else{
     if((position_pe % this.axe_x)==0)return this.axe_x;
     else return (int) position_pe % (this.axe_x);
     } 
     }
     
     public int return_indice_ligne_y(int position_pe ){
     if(position_pe<=this.axe_x) return 1;
     else{
     if ((position_pe%this.axe_x)==0) return (int)position_pe/this.axe_x;
     else return (int)(position_pe/this.axe_x)+1;
     
     }
     
     
     
     }
    public double getEnergey_in_router_wifi(){ return this.Energey_in_router_wifi;}
    public void setEnergey_in_router_wifi(double Energey_in_router_wifi){this.Energey_in_router_wifi=Energey_in_router_wifi;}
    
    public double getLatence_router_wifi(){return  this.Latence_router_wifi;}
    public void setLatence_router_wifi(double Latence_router_wifi){this.Latence_router_wifi=Latence_router_wifi;}
    
    public ArrayList<Class_ROUTER_WIFI> getList_router_position(){return this.List_router_position;}
    public void setList_router_position(ArrayList<Class_ROUTER_WIFI> List_router_position ){this.List_router_position=List_router_position;}
    
    public double getEneregy_in_router(){return this.Eneregy_in_router;}
     public double getEnergey_in_link(){return this.Energey_in_link;}
      public double getWight_Traffic(){return this.Wight_Traffic;}
     public double getbandwith_between_pe(){return this.bandwith_between_pe;}
     public void setEneregy_in_router(double Eneregy_in_router){this.Eneregy_in_router=Eneregy_in_router;}
     public void setEnergey_in_link(double Energey_in_link){this.Energey_in_link=Energey_in_link;}
      public void setWight_Traffic(double Wight_Traffic){this.Wight_Traffic=Wight_Traffic;}
     public void setbandwith_between_pe(double bandwith_between_pe){this.bandwith_between_pe=bandwith_between_pe;}
     public int getAxe_x(){return this.axe_x;}
     public int getAxe_y(){return this.axe_y;}
     public void setAxe_x(int axe_x){ this.axe_x=axe_x;}
     public void setAxe_y(int axe_y){ this.axe_y=axe_y;}
     public ArrayList<ArrayList<Class_noued>> getNoc_architecteur(){return this.Noc_architecteur;}
     public void setNoc_architecteur(ArrayList<ArrayList<Class_noued>> Noc_architecteur){ this.Noc_architecteur=Noc_architecteur;}
     public ArrayList<Class_noued> getList_neurons_in_one_pe(){return this.list_neurons_in_one_pe;}
     public void setList_neurons_in_one_pe(ArrayList<Class_noued> list_neurons_in_one_pe ){this.list_neurons_in_one_pe=list_neurons_in_one_pe;}
    
    
}
