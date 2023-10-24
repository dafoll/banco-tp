package Entidades;

public class Trasferencias implements Comparable<Trasferencias>
{
    private int id_trasferencia;
    private int cant_trasferir;
    private int id_origen;
    private int id_destino;

    public int getId_trasferencia() {
        return id_trasferencia;
    }

    public int getCant_trasferir() {
        return cant_trasferir;
    }

    public void setCant_trasferir(int cant_trasferir) {
        this.cant_trasferir = cant_trasferir;
    }

    public void setId_trasferencia(int id_trasferencia) {
        this.id_trasferencia = id_trasferencia;
    }

    public int getId_destino() {
        return id_destino;
    }

    public int getId_origen() {
        return id_origen;
    }

    public void setId_destino(int id_destino) {
        this.id_destino = id_destino;
    }

    public void setId_origen(int id_origen) {
        this.id_origen = id_origen;
    }

    @Override
    public int compareTo(Trasferencias o)
    {
        if(this.getId_trasferencia() > o.getId_trasferencia()){
            return 1;
        }
        else if(this.getId_trasferencia() < o.getId_trasferencia()){
            return  -1;
        }
        return 0;
    }
}
