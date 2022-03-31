/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.application;

import javax.swing.JPanel;



public class Class_main extends JPanel{

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
      
      // JFrame_entre f=new JFrame_entre();
        //f.setVisible(true);
        Class_Dnn Dnn_modele ;
        ReadXmlFileDnn instantReadXml;
        
        Class_mappage_2D Solution;
        Class_mappage_3D  Solution_3D;
        Class_mappage_2D_with_4_wifi solution_2D_wifi_4;
        Class_NocArchitecteur_2D_WITHOUT_WIFI Noc;
        Class_NocArchitecteur_3D_WITHOUT_WIFI noc_3D;
        Class_NocArchitecteur_2D_WITH_WIFI Noc_2D_With_4_WIFI;
        Class_noued Noued_source,Noued_destination ;
       instantReadXml=new ReadXmlFileDnn("/input1.xml");
       instantReadXml.setModeleDnn();
        Dnn_modele=instantReadXml.getDnn_modele();
        
       Noc=new Class_NocArchitecteur_2D_WITHOUT_WIFI(2,3,7,5,2,5);
       noc_3D =new Class_NocArchitecteur_3D_WITHOUT_WIFI(2,2,3,7,5,2,5);
       
        Solution=new Class_mappage_2D(Dnn_modele,Noc );
        Solution.genere_population(5);
        System.out.println("***********************************************************************");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("***********************************************************************");
        
        Solution_3D= new Class_mappage_3D(Dnn_modele,noc_3D );
        Solution_3D.genere_population(5);
        
          System.out.println("***********************************************************************");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("***********************************************************************");
        Noc_2D_With_4_WIFI = new Class_NocArchitecteur_2D_WITH_WIFI(6,6,7,5,2,3,1,2);
        solution_2D_wifi_4=new Class_mappage_2D_with_4_wifi(Dnn_modele,Noc_2D_With_4_WIFI);
        solution_2D_wifi_4.genere_population(5);
         System.out.println("***********************************************************************");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("***********************************************************************");
        
        System.out.println("finish ");
          
     
      
      
        
        }
         
         
        
        
    }
    
