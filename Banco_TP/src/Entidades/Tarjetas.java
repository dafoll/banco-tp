package Entidades;

public class Tarjetas
{
    // private cuenta;
    private int id;
    private int id_tarjeta;
    private int disponibilidad;
    private int saldo_pagar;

    //GET Y SET

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public int getSaldo_pagar() {
        return saldo_pagar;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setSaldo_pagar(int saldo_pagar) {
        this.saldo_pagar = saldo_pagar;
    }

    public int getId_tarjeta() {
        return id_tarjeta;
    }

    public void setId_tarjeta(int id_tarjeta) {
        this.id_tarjeta = id_tarjeta;
    }
}
