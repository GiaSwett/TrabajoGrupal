/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Logica.ListaTareas;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Itati
 */
public class ListaTareasPersistencia {
    
    ListaTareasJpaController TareaJPAC = new ListaTareasJpaController();
    
    public void CrearTarea (ListaTareas tare){
       TareaJPAC.create(tare);
    }
    
    public void ActualizarTarea (ListaTareas tare){
        try {
            //Invocar a la función edit que se encuentra en estudianteJPACONTROLLER

           TareaJPAC.edit(tare);
        } catch (Exception ex) {
            Logger.getLogger(ListaTareasPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<ListaTareas> MostrarDatos(){
        return TareaJPAC.findListaTareasEntities();
    }
    
    public ListaTareas consultarTarea(int id) {
    
        return TareaJPAC.findListaTareas(id);
        
    }

    public ArrayList<ListaTareas> consultarTarea() {
        
        List<ListaTareas> analisis = TareaJPAC.findListaTareasEntities();
        ArrayList<ListaTareas> completa = new ArrayList<ListaTareas> (analisis);
        
        return completa;
    }
    
    public void EliminarTarea(int id) {
        try {
            TareaJPAC.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ListaTareasPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void EliminarTarea(){
    
    }
}
