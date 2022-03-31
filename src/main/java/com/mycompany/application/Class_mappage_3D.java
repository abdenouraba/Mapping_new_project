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
public class Class_mappage_3D {
    private Class_Dnn Dnn_modele;
    private Class_noued noued;
    private ArrayList<Class_noued> one_Layer;
    
    
    private double Energey_total=0;
    private double Latence_total=0;
    
    private int nombre_neuron_max;
    private int nombre_neuron_min;
  //  private ArrayList<Class_noued>  Noc_architecteur[][];
    //private int random_place;
    
    
    //private ArrayList<ArrayList<Class_noued>> Noc_architecteur;
    private ArrayList<Class_noued> list_neurons_in_one_pe;
    private Class_NocArchitecteur_3D_WITHOUT_WIFI Noc;
    
    
    
    @SuppressWarnings("unchecked")
    public Class_mappage_3D(Class_Dnn Dnn_modele, Class_NocArchitecteur_3D_WITHOUT_WIFI Noc_architecteur){
    this.Dnn_modele=Dnn_modele;
    this.Noc= Noc_architecteur;
    //this.axe_x=Noc_architecteur.getAxe_x();
    //this.axe_y=Noc_architecteur.getAxe_y();
    nombre_neuron_min=(int)Dnn_modele.getNbr_neuron()/(Noc_architecteur.getAxe_x()*Noc_architecteur.getAxe_y()*Noc_architecteur.getAxe_z());
    nombre_neuron_max=(int)((Dnn_modele.getNbr_neuron())*15/100);
    
    
    
    
    }
    public void Random_mapping(){
        this.Noc.genere_noc_archiecteur();
       // genere_noc_archiecteur();
        int size_dnn_architecteur = Dnn_modele.getDnn_architecteur().size();
        int index=0;
        while((index>=0)&&(index<size_dnn_architecteur )){
            one_Layer= Dnn_modele.getDnn_architecteur().get(index);
            int size_layer=one_Layer.size();
            int index_in_one_layer=0;
            while(index_in_one_layer>=0 && index_in_one_layer<size_layer){
            noued= one_Layer.get(index_in_one_layer);
            index_in_one_layer++;
           // int random_position_axe_z= (int) (Math.random()*(Noc.getAxe_z()-0));
          //  int random_position_x_y= (int) (Math.random()*(((this.Noc.getAxe_x()*this.Noc.getAxe_y())))-0);
            int  random_position= (int) (Math.random()*((this.Noc.getAxe_x()*this.Noc.getAxe_y()*this.Noc.getAxe_z()))-0);
            random_position++;
            
            
            int position_z =(int) (random_position/(this.Noc.getAxe_x()*this.Noc.getAxe_y()));
            
            if ((random_position % (this.Noc.getAxe_x()*this.Noc.getAxe_y()))==0) position_z--;
            
            
            int position_x_y=random_position-((this.Noc.getAxe_x()*this.Noc.getAxe_y())*position_z);
             position_x_y--;
             
                System.out.println("position x_y "+position_x_y+"position z "+position_z);
             
            int size_verification =this.Noc.getNoc_architecteur().get(position_z).get(position_x_y).size();
           
            while(size_verification>nombre_neuron_max){
                
                
            random_position= (int) (Math.random()*((this.Noc.getAxe_x()*this.Noc.getAxe_y()*this.Noc.getAxe_z()))-0);
            random_position++;
            
             position_z =(int) (random_position/(this.Noc.getAxe_x()*this.Noc.getAxe_y()));
            
            if ((random_position % (this.Noc.getAxe_x()*this.Noc.getAxe_y()))==0) position_z--;
            
            position_x_y=random_position-((this.Noc.getAxe_x()*this.Noc.getAxe_y())*position_z);
            
             position_x_y--;
             size_verification =this.Noc.getNoc_architecteur().get(position_z).get(position_x_y).size();
            }
             
            noued.setnumero_pe(random_position);
                boolean add = this.Noc.getNoc_architecteur().get(position_z).get(position_x_y).add(noued);
   
            
            }
            index++;
            
            
      
        
        
         }
       
       int index_z=0;
      while(index_z<Noc.getAxe_z()){
          int index_x_y=0;
          while(index_x_y<size_dnn_architecteur){
          if(this.Noc.getNoc_architecteur().get(index_z).get(index_x_y).size()<nombre_neuron_min) Random_mapping();
          index_x_y++;
          }
      
      index_z++;
      } 
    
    
    
    }
    
    /*
     public void genere_noc_archiecteur(){
         int index_cree_array=0;
         int fin_index= axe_x*axe_y;
         while(index_cree_array<fin_index){
             this.list_neurons_in_one_pe=new ArrayList<Class_noued> ();
             this.Noc.getNoc_architecteur().add(index_cree_array,list_neurons_in_one_pe);
             this.Noc.setNoc_architecteur(this.Noc.getNoc_architecteur());
             index_cree_array++;
            
         }
        }
*/
     public void affiche_resulta_mappage_3D(){
     for(int z=0;z<this.Noc.getAxe_z();z++){    
     for(int x_y=0; x_y< (this.Noc.getAxe_x()*this.Noc.getAxe_y());x_y++){
      System.out.println("\n"+"voisi les noueds de coeur n°: " +x_y+ "sur le axe z:"+z+"\n");  
     one_Layer=Noc.getNoc_architecteur().get(z).get(x_y);
     int size_pe=one_Layer.size();
     
     
     for(int j=0;j<size_pe;j++){
    System.out.println("id de neuron n° "+j+" : "+one_Layer.get(j).getId_noued());
     }
     }
     } 
     
     }
      public double Energey_in_Noc_x_y_z(ArrayList<ArrayList<ArrayList<Class_noued>>> Noc_3D){
     double energey_total_in_noc_3D=0;
     int size=Noc_3D.size();
     for(int i=0;i<size;i++){
     energey_total_in_noc_3D=energey_total_in_noc_3D+Energey_in_Noc_x_y(Noc_3D.get(i));
     }
     return(energey_total_in_noc_3D);}
     
     public double Energey_in_Noc_x_y(ArrayList<ArrayList<Class_noued>> Noc_2D){
     double energey_total_in_noc_2D=0;
     int size=Noc_2D.size();
     for(int i=0;i<size;i++){
     energey_total_in_noc_2D=energey_total_in_noc_2D+Energey_in_one_pe(Noc_2D.get(i));
     }
     return(energey_total_in_noc_2D);}
     
     
     public double Latence_in_Noc_x_y_z(ArrayList<ArrayList<ArrayList<Class_noued>>> Noc_3D){
     double Latence_total_in_noc_3D=0;
     
     int size=Noc_3D.size();
     for(int i=0;i<size;i++){
     Latence_total_in_noc_3D=Latence_total_in_noc_3D+Latence_in_Noc_x_y(Noc_3D.get(i));
     }
     return(Latence_total_in_noc_3D);
     
     
     }
     public double Latence_in_Noc_x_y(ArrayList<ArrayList<Class_noued>> Noc){
     double Latence_total_in_noc_x_y=0;
     int size=Noc.size();
     for(int i=0;i<size;i++){
     Latence_total_in_noc_x_y=Latence_total_in_noc_x_y+Latence_in_one_pe(Noc.get(i));
     }
     return(Latence_total_in_noc_x_y);}
     
     
     public double Energey_in_one_pe(ArrayList<Class_noued> PE){
         int size=PE.size();
         double eneregy_in_pe=0;
         if(size==0) return 0;
         else{
         for(int i=0;i<size;i++){
         eneregy_in_pe=eneregy_in_pe+(Eneregy_in_one_neuron_in_on_pe(PE.get(i)));
         }        
         }
     return eneregy_in_pe;
         }
     
     public double Latence_in_one_pe(ArrayList<Class_noued> PE){
         int size=PE.size();
         double Latence_in_pe=0;
         if(size==0) return 0;
         else{
         for(int i=0;i<size;i++){
         Latence_in_pe=Latence_in_pe+(Latence_in_one_neuron_in_one_pe(PE.get(i)));
         }        
         }
     return Latence_in_pe;
         }
     
     
     
     public double Eneregy_in_one_neuron_in_on_pe(Class_noued neuron){
         double eneregy_one_neuron_in_on_pe=0;
        ArrayList<Class_connect> list_voisin =neuron.getConnect_noued();
        int size =list_voisin.size();
        if(size==0) return 0;
        else{
        int index_x= return_indice_column((neuron.get_numero_pe()));
        int index_y= return_indice_ligne((neuron.get_numero_pe()));
        int index_z= return_indice_axe_z((neuron.get_numero_pe()));
        for(int i=0;i<size;i++){
           int position_destination=  trouve_position_neuron_par_id(list_voisin.get(i).getnoued_destination().getId_noued());
            System.out.println("voic votre position "+neuron.get_numero_pe()+" la position distanc est "+position_destination);
           int index_x_destination=return_indice_column(position_destination);
           int index_y_destination=return_indice_ligne(position_destination);
           int index_z_destination=return_indice_axe_z(position_destination);
           int distance_x=Math.abs(index_x_destination-index_x);
           int distance_y=Math.abs(index_y_destination-index_y);
           int distance_z=Math.abs(index_z_destination-index_z);
           int distance_mathaten=distance_x+distance_y+distance_z;
        eneregy_one_neuron_in_on_pe=eneregy_one_neuron_in_on_pe+(list_voisin.get(i).getNombre_bit()*((((distance_mathaten+1)*(this.Noc.getEneregy_in_router()))+(distance_mathaten*(this.Noc.getEnergey_in_link())))*((this.Noc.getWight_Traffic()))));
        
        }
        return eneregy_one_neuron_in_on_pe;
        }
     
     
     
     }
     public double Latence_in_one_neuron_in_one_pe(Class_noued neuron){
     double latence_one_neuron_on_pe=0;
     ArrayList<Class_connect> list_voisin =neuron.getConnect_noued();
     int size =list_voisin.size();
     if(size==0) return 0;
     
     else{
        int index_x= return_indice_column((neuron.get_numero_pe()));
        int index_y= return_indice_ligne((neuron.get_numero_pe()));
        int index_z= return_indice_axe_z((neuron.get_numero_pe()));
        for(int i=0;i<size;i++){
             int position_destination=  trouve_position_neuron_par_id(list_voisin.get(i).getnoued_destination().getId_noued());
           int index_x_destination=return_indice_column(position_destination);
           int index_y_destination=return_indice_ligne(position_destination);
           int index_z_destination=return_indice_axe_z(position_destination);
           int distance_x=Math.abs(index_x_destination-index_x);
           int distance_y=Math.abs(index_y_destination-index_y);
           int distance_z=Math.abs(index_z_destination-index_z);
           int distance_mathaten=distance_x+distance_y+distance_z;
       latence_one_neuron_on_pe=latence_one_neuron_on_pe+(list_voisin.get(i).getNombre_bit()*(distance_mathaten+1)*(1/this.Noc.getbandwith_between_pe()));
        
        }
        return latence_one_neuron_on_pe;
        }
     
     
     
     
     }
     public void genere_population(int nbr){
     for (int i=0 ;i<nbr;i++){
         System.out.println("population n° : "+i);
         this.Random_mapping();
         Energey_total= Energey_in_Noc_x_y_z(this.Noc.getNoc_architecteur());
         Latence_total= Latence_in_Noc_x_y_z(this.Noc.getNoc_architecteur());
         System.out.println("******energy total in noc is :" +Energey_total+"*****");
         System.out.println("******latence total in noc is :" +Latence_total +", second (s) *****");
         this.affiche_resulta_mappage_3D();
     
     }
     }
      public int trouve_position_neuron_par_id(String id_neuron){
          int position=-1;
          int index_z=0;
          while(index_z<Noc.getAxe_z()){
          
     int index=0;
     
     while(index<Noc.getAxe_x()*Noc.getAxe_y()){
     int size_coeur=Noc.getNoc_architecteur().get(index_z).get(index).size();
     int index_coeur=0;
     while(index_coeur<size_coeur){
     if(id_neuron.equals(Noc.getNoc_architecteur().get(index_z).get(index).get(index_coeur).getId_noued())){
     position=Noc.getNoc_architecteur().get(index_z).get(index).get(index_coeur).get_numero_pe();
     return position;
     }
     index_coeur++;
     }
     index++;
     }
     index_z++;
          }
     
    return position; }
     public int return_indice_axe_z(int position_pe){
     int position_z =(int) (position_pe/(this.Noc.getAxe_x()*this.Noc.getAxe_y()));
            
            if ((position_pe % (this.Noc.getAxe_x()*this.Noc.getAxe_y()))==0) position_z--;
     return position_z;
     
     }
     public int return_indice_column(int position_pe){
     if(position_pe<=this.Noc.getAxe_x())return position_pe; 
     else{
     if((position_pe % this.Noc.getAxe_x())==0)return this.Noc.getAxe_x();
     else return (int) position_pe % (this.Noc.getAxe_x());
     } 
     }
     
     public int return_indice_ligne(int position_pe ){
     if(position_pe<=this.Noc.getAxe_x()) return 1;
     else{
     if ((position_pe%this.Noc.getAxe_x())==0) return (int)position_pe/this.Noc.getAxe_x();
     else return (int)(position_pe/this.Noc.getAxe_x())+1;
     
     }
     
     
     
     }
     
     /*
     public ArrayList<ArrayList<Class_noued>> getNoc_architecteur(){return this.Noc_architecteur;}
     public void setNoc_architecteur(ArrayList<ArrayList<Class_noued>> Noc_architecteur){ this.Noc_architecteur=Noc_architecteur;}
*/
     public ArrayList<Class_noued> getList_neurons_in_one_pe(){return this.list_neurons_in_one_pe;}
     public void setList_neurons_in_one_pe(ArrayList<Class_noued> list_neurons_in_one_pe ){this.list_neurons_in_one_pe=list_neurons_in_one_pe;}
     public Class_NocArchitecteur_3D_WITHOUT_WIFI getNoc(){
     return this.Noc; 
     }
    
}
