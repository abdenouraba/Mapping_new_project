/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.application;

/**
 *
 * @author user
 */
public class Class_ROUTER_WIFI {
    private int id_router;
    private int emplacement;
    public Class_ROUTER_WIFI(){}
    
    public Class_ROUTER_WIFI(int id_router){
    this.id_router=id_router;
    } 
    
    public Class_ROUTER_WIFI(int id_router,int emplacement){
    this.id_router=id_router;
    this.emplacement=emplacement;
    }
    
    
    public void setId_router(int id_router ) {this.id_router=id_router;}
    public void setEmplacement(int emplacement ){this.emplacement=emplacement;}
    public int getId_router() {return this.id_router;}
    public int getEmplacement(){return this.emplacement;}
    
}
