package Clases;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import modelo.LineaKardex;

/**
 * Registros del kardex de Materia Prima PET
 * @author Marvin
 */
public class MateriaPrimaTableModel extends AbstractTableModel{
    private ArrayList<LineaKardex> lineasKardex = new ArrayList<>();

    public ArrayList<LineaKardex> getLineasKardex() {
        return lineasKardex;
    }

    public void setLineasKardex(ArrayList<LineaKardex> lineasKardex) {
        this.lineasKardex = lineasKardex;
    }
    
    public void agregarLineaKardex(LineaKardex linea){
        lineasKardex.add(linea);
    }

    @Override
    public int getRowCount() {
        return lineasKardex.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object valor = null;
        LineaKardex linea = lineasKardex.get(rowIndex);
        switch(columnIndex){
            case 0: valor = linea.getFechaLinea(); break;
            case 1: valor = linea.getEntraCant(); break;
            case 2: valor = linea.getEntraUnit(); break;
            case 3: valor = linea.getEntraMont(); break;
            case 4: valor = linea.getSaliCant(); break;
            case 5: valor = linea.getSaliUnit(); break;
            case 6: valor = linea.getSaliMont(); break;
            case 7: valor = linea.getMontCant(); break;
            case 8: valor = linea.getMontUnit(); break;
            case 9: valor = linea.getMontMont();
        }
        return valor;
    }
}
