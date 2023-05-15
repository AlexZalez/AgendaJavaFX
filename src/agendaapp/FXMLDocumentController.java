/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package agendaapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Administrador
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label Estado;
    @FXML
    private TextArea campoTarea;
    @FXML
    private Button aggTarea;
    @FXML
    private TableView<Tarea> tabla;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn stateColumn;
    @FXML
    private Button delTarea;
    @FXML
    private Button Cumplida;
    
    private ObservableList<Tarea> Tareas;
    
    @FXML
    private void Agregar(ActionEvent event) {
        String name = this.campoTarea.getText();
        
        Tarea t = new Tarea(name, false);
        
        this.Tareas.add(t);
        this.tabla.setItems(Tareas);
        this.campoTarea.setText("");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Tareas = FXCollections.observableArrayList();
        
        this.nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        this.stateColumn.setCellValueFactory(new PropertyValueFactory("state"));
    }    
    
    @FXML
    public void select(MouseEvent event){
        Tarea t = this.tabla.getSelectionModel().getSelectedItem();
        
        if (t != null) {
            this.campoTarea.setText(t.getName());
            
            String estado;
            if (t.getState()) {
                estado = "Cumplida";
            } else {
                estado = "Pendiente";
            }
            this.Estado.setText(estado);
        } else {
            System.out.println("Debes seleccionar algo");
        }
    }
    
    @FXML
    public void Eliminar(ActionEvent event){
        Tarea t = this.tabla.getSelectionModel().getSelectedItem();
        
        if (t == null) {
            System.out.println("Debes seleccionar algo");
        } else {
            
           this.Tareas.remove(t);
           this.Estado.setText("");
           this.campoTarea.setText("");
           this.tabla.refresh();
            
        }
    }
    
    @FXML
    public void Cumplido(ActionEvent event){
        Tarea t = this.tabla.getSelectionModel().getSelectedItem();
        
        if (t == null) {
            System.out.println("Debes seleccionar algo");
        } else {
            
            String name = this.campoTarea.getText();
            Boolean state;
            state = !"Pendiente".equals(this.Estado.getText());
             Tarea aux = new Tarea(name, state);
             
             if (!t.getState()) {
                t.setState(true);
                this.Estado.setText("Cumplido");
            }
            
             this.tabla.refresh();
            
            
        }
    }
    
    
}
