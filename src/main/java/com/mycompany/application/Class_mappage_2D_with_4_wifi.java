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
public class Class_mappage_2D_with_4_wifi {
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
    private Class_NocArchitecteur_2D_WITH_WIFI Noc;
    public Class_mappage_2D_with_4_wifi(Class_Dnn Dnn_modele, Class_NocArchitecteur_2D_WITH_WIFI Noc_architecteur){
    this.Dnn_modele=Dnn_modele;
    this.Noc= Noc_architecteur;
    //this.axe_x=Noc_architecteur.getAxe_x();
    //this.axe_y=Noc_architecteur.getAxe_y();
    nombre_neuron_min=(int)Dnn_modele.getNbr_neuron()/(Noc_architecteur.getAxe_x()*Noc_architecteur.getAxe_y());
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
            int random_position= (int) (Math.random()*(((this.Noc.getAxe_x()*this.Noc.getAxe_y())))-0);
            int size_verification =this.Noc.getNoc_architecteur().get(random_position).size();
            if(size_verification>nombre_neuron_max){
            random_position= (int) (Math.random()*((this.Noc.getAxe_x()*this.Noc.getAxe_y()))-0);
            }
            noued.setnumero_pe(random_position+1);
            this.Noc.getNoc_architecteur().get(random_position).add(noued);
   
            
            }
            index++;
            
            
      
        
        
         }
       int f=0;
      while(f<size_dnn_architecteur){
      if(this.Noc.getNoc_architecteur().get(f).size()<nombre_neuron_min) Random_mapping();
      f++;
      } 
    
    
    
    }
      
      
        public void affiche_resulta_mappage(){
     for(int i=0; i< (this.Noc.getAxe_x()*this.Noc.getAxe_y());i++){
     one_Layer=Noc.getNoc_architecteur().get(i);
     int size_pe=one_Layer.size();
     System.out.println("vous etes dans l'axe x : "+return_indice_column(i+1)+" et l'indice ligne y :"+return_indice_ligne(i+1));
     System.out.println("\n"+"voisi les noueds de PE n°: "+i+"\n");
     for(int j=0;j<size_pe;j++){
    System.out.println("id de neuron n° "+j+" : "+one_Layer.get(j).getId_noued());
     }
     }
     }
     
     
     public double Energey_in_Noc(ArrayList<ArrayList<Class_noued>> Noc){
     double energey_total_in_noc=0;
     int size=Noc.size();
     for(int i=0;i<size;i++){
     energey_total_in_noc=energey_total_in_noc+Energey_in_one_pe(Noc.get(i));
     }
     return(energey_total_in_noc);}
     
     public double Latence_in_Noc(ArrayList<ArrayList<Class_noued>> Noc){
     double Latence_total_in_noc=0;
     int size=Noc.size();
     for(int i=0;i<size;i++){
     Latence_total_in_noc=Latence_total_in_noc+Latence_in_one_pe(Noc.get(i));
     }
     return(Latence_total_in_noc);}
     
     
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
       double Eneregy_one_neuron_on_pe=0;
     ArrayList<Class_connect> list_voisin =neuron.getConnect_noued();
     int size =list_voisin.size();
     if(size==0) return 0;
     
     else{
        int index_x= return_indice_column((neuron.get_numero_pe()));
        int index_y= return_indice_ligne((neuron.get_numero_pe()));
        for(int i=0;i<size;i++){
             int position_destination=  trouve_position_neuron_par_id(list_voisin.get(i).getnoued_destination().getId_noued());
           int index_x_destination=return_indice_column(position_destination);
           int index_y_destination=return_indice_ligne(position_destination);
           int distance_x_source_distination_without_wifi=Math.abs(index_x_destination-index_x);
           int distance_y_source_distination_without_wifi=Math.abs(index_y_destination-index_y);
           int distance_mathaten_source_distination_without_wifi=distance_x_source_distination_without_wifi+distance_y_source_distination_without_wifi;
           double Eneregy_communication_source_distination_without_wifi=(list_voisin.get(i).getNombre_bit()*(distance_mathaten_source_distination_without_wifi+1)*(1/this.Noc.getEnergey_in_link()));
          
           ArrayList<Class_ROUTER_WIFI> router_source_destination= new ArrayList<Class_ROUTER_WIFI>();
          double Eneregy_source_router=Latence_total_coeur_router(neuron.get_numero_pe(), list_voisin.get(i).getNombre_bit(), router_source_destination);
          double Eneregy_router_destination=Latence_total_coeur_router(position_destination, list_voisin.get(i).getNombre_bit(), router_source_destination);
          System.out.println("la position de router source est "+router_source_destination.get(0).getEmplacement()   +"   la positioon de retour distination est :"+router_source_destination.get(1).getEmplacement());
          System.out.println("la position de neuron source est "+neuron.get_numero_pe()  +"   la positioon de neuron distination est :"+position_destination);
          double Eneregy_R_source_R_destination = Latence_Router_Router(router_source_destination, list_voisin.get(i).getNombre_bit());
          
          // System.out.println("la position de router source est "+router_source_destination.get(0).getEmplacement()   +"   la positioon de retour distination est :"+router_source_destination.get(1).getEmplacement());
          
            System.out.println("1,2,3"+Eneregy_source_router+" "+Eneregy_router_destination+"  "+Eneregy_R_source_R_destination);
          double Eneregy_total_with_wifi=Eneregy_source_router+Eneregy_router_destination+Eneregy_R_source_R_destination;
            System.out.println("com "+Eneregy_total_with_wifi);
          
          if(Eneregy_total_with_wifi<Eneregy_communication_source_distination_without_wifi){
         Eneregy_one_neuron_on_pe=Eneregy_one_neuron_on_pe+Eneregy_total_with_wifi;
         System.out.println("j'ai choisir le wifi pour Eneregy "+Eneregy_total_with_wifi+"car la Eneregy par lien physique ici est "+Eneregy_communication_source_distination_without_wifi);
          }
          else{
              Eneregy_one_neuron_on_pe=Eneregy_one_neuron_on_pe+Eneregy_communication_source_distination_without_wifi;
          }
           
           
           
       
        
        }
        return Eneregy_one_neuron_on_pe;
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
        for(int i=0;i<size;i++){
             int position_destination=  trouve_position_neuron_par_id(list_voisin.get(i).getnoued_destination().getId_noued());
           int index_x_destination=return_indice_column(position_destination);
           int index_y_destination=return_indice_ligne(position_destination);
           int distance_x_source_distination_without_wifi=Math.abs(index_x_destination-index_x);
           int distance_y_source_distination_without_wifi=Math.abs(index_y_destination-index_y);
           int distance_mathaten_source_distination_without_wifi=distance_x_source_distination_without_wifi+distance_y_source_distination_without_wifi;
           double latence_communication_source_distination_without_wifi=(list_voisin.get(i).getNombre_bit()*(distance_mathaten_source_distination_without_wifi+1)*(1/this.Noc.getbandwith_between_pe()));
          
           ArrayList<Class_ROUTER_WIFI> router_source_destination= new ArrayList<Class_ROUTER_WIFI>();
          double Latence_source_router=Latence_total_coeur_router(neuron.get_numero_pe(), list_voisin.get(i).getNombre_bit(), router_source_destination);
          double Latence_router_destination=Latence_total_coeur_router(position_destination, list_voisin.get(i).getNombre_bit(), router_source_destination);
          
            System.out.println("le router source est "+router_source_destination.get(0).getEmplacement()+"    le router destination est     "+router_source_destination.get(1).getEmplacement());
          double Latence_R_source_R_destination = Latence_Router_Router(router_source_destination, list_voisin.get(i).getNombre_bit());
          
          double Latence_total_with_wifi=Latence_source_router+Latence_router_destination+Latence_R_source_R_destination;
          
          if(Latence_total_with_wifi<=latence_communication_source_distination_without_wifi){
          latence_one_neuron_on_pe=latence_one_neuron_on_pe+Latence_total_with_wifi;
              System.out.println("j'ai choisir le wifi pour Latence "+Latence_total_with_wifi+"car la latence par lien physique ici est "+latence_communication_source_distination_without_wifi);
              
          }
          else{
              latence_one_neuron_on_pe=latence_one_neuron_on_pe+latence_communication_source_distination_without_wifi;
          }
           
           
           
       
        
        }
        return latence_one_neuron_on_pe;
        }
     
     
     
     
     }
     
     
     
     
     
     public double Eneregy_Router_Router(ArrayList<Class_ROUTER_WIFI> router_source_destination,int nombre_bit){
     double Eneregy_Router_Router=0;
      int index_x_router_source= return_indice_column(router_source_destination.get(0).getEmplacement());
     int index_y_router_source= return_indice_ligne(router_source_destination.get(0).getEmplacement());
     
     int index_x_router_destination= return_indice_column(router_source_destination.get(1).getEmplacement());
     int index_y_router_destination= return_indice_ligne(router_source_destination.get(1).getEmplacement());
     
     int distance_router_source_router_destination_x=index_x_router_source-index_x_router_destination;
     int distance_router_source_router_destination_y=index_y_router_source-index_y_router_destination;
     double distance_ecludienne_Router_Router= Math.sqrt(Math.pow(distance_router_source_router_destination_x,2) + Math.pow(distance_router_source_router_destination_y,2));
    Eneregy_Router_Router =nombre_bit*(1/this.Noc.getEnergey_in_router_wifi())*(distance_ecludienne_Router_Router);
     return   Eneregy_Router_Router;
     
     
     }
     public double Eneregy_total_coeur_router(int position_neuron,int nombre_bit,ArrayList<Class_ROUTER_WIFI> router_source_destination){
     double Eneregy_coeur_router=0;
     Class_ROUTER_WIFI router_wifi = recherche_le_Router_le_plus_proche(position_neuron,router_source_destination);
     //router_source_destination.add(router_wifi);
     int index_x_source=return_indice_column(position_neuron);
     int index_y_source=return_indice_ligne(position_neuron);
     
     int index_x_destination=return_indice_column(router_wifi.getEmplacement());
     int index_y_destination=return_indice_ligne(router_wifi.getEmplacement());
     
     int distance_x_source_destination= Math.abs(index_x_source-index_x_destination);
     int distance_y_source_destination = Math.abs(index_y_source-index_y_destination);
     int distance_mathaten_router_coeur=distance_x_source_destination+distance_y_source_destination;
     Eneregy_coeur_router=(1/this.Noc.getEnergey_in_link())*nombre_bit*distance_mathaten_router_coeur;
     
     return Eneregy_coeur_router;
     
     }
     
     
     public double Latence_Router_Router(ArrayList<Class_ROUTER_WIFI> router_source_destination,int nombre_bit){
         double Latence =0;
     int index_x_router_source= return_indice_column(router_source_destination.get(0).getEmplacement());
     int index_y_router_source= return_indice_ligne(router_source_destination.get(0).getEmplacement());
     
     int index_x_router_destination= return_indice_column(router_source_destination.get(1).getEmplacement());
     int index_y_router_destination= return_indice_ligne(router_source_destination.get(1).getEmplacement());
     
     int distance_router_source_router_destination_x=index_x_router_source-index_x_router_destination;
     int distance_router_source_router_destination_y=index_y_router_source-index_y_router_destination;
      double distance_ecludienne_Router_Router= Math.sqrt(Math.pow(distance_router_source_router_destination_x,2) + Math.pow(distance_router_source_router_destination_y,2));
     Latence=nombre_bit*(1/this.Noc.getLatence_router_wifi())*(distance_ecludienne_Router_Router);
     return  Latence;
     }
      
     public double Latence_total_coeur_router(int position_neuron,int nombre_bit, ArrayList<Class_ROUTER_WIFI> router_source_destination){
     double Latence=0;
     Class_ROUTER_WIFI router_wifi = recherche_le_Router_le_plus_proche(position_neuron,router_source_destination);
     //router_source_destination.add(router_wifi);
     int index_x_source=return_indice_column(position_neuron);
     int index_y_source=return_indice_ligne(position_neuron);
     
     int index_x_destination=return_indice_column(router_wifi.getEmplacement());
     int index_y_destination=return_indice_ligne(router_wifi.getEmplacement());
     
     int distance_x_source_destination= Math.abs(index_x_source-index_x_destination);
     int distance_y_source_destination = Math.abs(index_y_source-index_y_destination);
     int distance_mathaten_router_coeur=distance_x_source_destination+distance_y_source_destination;
     Latence=(1/this.Noc.getbandwith_between_pe())*nombre_bit*distance_mathaten_router_coeur;
     
     return Latence;
     
     
     } 
      // CBN VERIFIER MANQUE SEULEMENT LA DISTANCE ECLUDIENNE 
    public Class_ROUTER_WIFI recherche_le_Router_le_plus_proche(int position, ArrayList<Class_ROUTER_WIFI> list_R_source_R_distance){
        System.out.println("µµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµ***");
    ArrayList<Class_ROUTER_WIFI> list_Router=this.Noc.getList_router_position();
    Class_ROUTER_WIFI router= list_Router.get(0);
    int indice_x_coeur=return_indice_column(position);
    int indice_y_coeur=return_indice_ligne(position);
    
    int indice_x_Router=return_indice_column(router.getEmplacement());
    int indice_y_Router=return_indice_ligne(router.getEmplacement());
    
        //System.out.println("/n /n voici la position de router acteul avant boucle while est leur x y"+router.getEmplacement()+" x y "+ indice_x_Router+" "+indice_y_Router);
    
    int distance_x=Math.abs(indice_x_coeur-indice_x_Router);
    int distance_y=Math.abs(indice_y_coeur-indice_y_Router);
    int distance_mathaten=distance_x+distance_y;
    
    int index=1;
    
    while(index<4){
      
      Class_ROUTER_WIFI router_index= list_Router.get(index);
   
    
    int indice_x_Router_index=return_indice_column(router_index.getEmplacement());
    int indice_y_Router_index=return_indice_ligne(router_index.getEmplacement());
    
    int distance_x_index=Math.abs(indice_x_coeur-indice_x_Router_index);
    int distance_y_index=Math.abs(indice_y_coeur-indice_y_Router_index);
    int distance_mathaten_index=distance_x_index+distance_y_index;
    
    if(distance_mathaten_index<distance_mathaten){
        
             //System.out.println("/n /n voici la position de router acteul dans boucle while est leur x y"+router.getEmplacement()+"car distance actuel est :"+distance_mathaten+"   la distance indexe est "+distance_mathaten_index);

      router=router_index;    
             
             
    
    
     indice_x_Router=return_indice_column(router.getEmplacement());
     indice_y_Router=return_indice_ligne(router.getEmplacement());
      distance_x=Math.abs(indice_x_coeur-indice_x_Router);
     distance_y=Math.abs(indice_y_coeur-indice_y_Router);
     distance_mathaten=distance_x+distance_y;
    
        //System.out.println("/n /n voici la position de router acteul avant boucle while est leur x y"+router.getEmplacement()+" x y "+ indice_x_Router+" "+indice_y_Router);
    
    
        
        
   
   
    }
    index++;
    // System.out.println("est voila la position de router "+router.getEmplacement()+" elle est plus proche a la position de coeur pe  "+position);
     
    
    }
      System.out.println("µµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµµ***");
    list_R_source_R_distance.add(router);
    return router;
    
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
     
      public void genere_population(int nbr){
     for (int i=0 ;i<nbr;i++){
         System.out.println("population n° : "+i);
         Random_mapping();
         Energey_total= Energey_in_Noc(this.Noc.getNoc_architecteur());
         Latence_total= Latence_in_Noc(this.Noc.getNoc_architecteur());
         System.out.println("******energy total in noc is :" +Energey_total+"*****");
         System.out.println("******latence total in noc is :" +Latence_total +", second (s) *****");
         affiche_resulta_mappage();
     
     }
     }
      public int trouve_position_neuron_par_id(String id_neuron){
     int index=0;
     int position=-1;
     while(index<Noc.getAxe_x()*Noc.getAxe_y()){
     int size_coeur=Noc.getNoc_architecteur().get(index).size();
     int index_coeur=0;
     while(index_coeur<size_coeur){
     if(id_neuron.equals(Noc.getNoc_architecteur().get(index).get(index_coeur).getId_noued())){
     position=Noc.getNoc_architecteur().get(index).get(index_coeur).get_numero_pe();
     return position;
     }
     index_coeur++;
     }
     index++;
     }
     
    return position; }
     
     /*
     public ArrayList<ArrayList<Class_noued>> getNoc_architecteur(){return this.Noc_architecteur;}
     public void setNoc_architecteur(ArrayList<ArrayList<Class_noued>> Noc_architecteur){ this.Noc_architecteur=Noc_architecteur;}
*/
     public ArrayList<Class_noued> getList_neurons_in_one_pe(){return this.list_neurons_in_one_pe;}
     public void setList_neurons_in_one_pe(ArrayList<Class_noued> list_neurons_in_one_pe ){this.list_neurons_in_one_pe=list_neurons_in_one_pe;}
     public Class_NocArchitecteur_2D_WITH_WIFI getNoc(){
     return this.Noc; 
     }  
     
}
