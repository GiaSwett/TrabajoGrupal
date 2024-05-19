/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Persistencia.ListaTareasPersistencia;
import java.util.List;

/**
 *
 * @author Itati
 */
public class ControladorLogica_Lista {
    
    ListaTareasPersistencia LisPer = new ListaTareasPersistencia();
    
    public void CrearTarea(ListaTareas tare){
        LisPer.CrearTarea(tare);
    }
    
    public List<ListaTareas> ConsultarTarea (){
        return LisPer.consultarTarea();   
    }
    
    public ListaTareas ConsultarTarea (int id){
       return LisPer.consultarTarea(id);
        
    }
    
    public void ActualizarTarea (ListaTareas tare){
        LisPer.ActualizarTarea(tare);
    }
    
    public List<ListaTareas> MostrarDatos(){
        return LisPer.MostrarDatos();
    }
    
    public void EliminarTarea(int id){
        LisPer.EliminarTarea(id);
    }
        
    public void EliminarTarea (){
        LisPer.EliminarTarea();
    }
}
