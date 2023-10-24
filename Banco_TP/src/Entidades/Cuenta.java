package Entidades;

import java.util.ArrayList;
public class Cuenta
{
    //private Usuario
    private int id;
    private int cbu;
    private String alias;
    private int caja_ahorro;
    private int caja_ahorro_USD;


    public Cuenta(int cbu, int caja_ahorro, int caja_ahorro_USD, String alias)
    {
        this.cbu = cbu;
        this.alias = alias;
        this.caja_ahorro = caja_ahorro;
        this.caja_ahorro_USD = caja_ahorro_USD;
    }

    public Cuenta(){

    }

    //SET Y GET;


    public void setId(int id) {this.id = id;}

    public int getId() {return id;}

    public String getAlias() {
        return alias;
    }

    public int getCaja_ahorro() {
        return caja_ahorro;
    }

    public int getcbu() {
        return cbu;
    }

    public int getCaja_ahorro_USD() {
        return caja_ahorro_USD;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setcbu(int cbu) {
        this.cbu = cbu;
    }

    public void setCaja_ahorro(int caja_ahorro) {
        this.caja_ahorro = caja_ahorro;
    }

    public void setCaja_ahorro_USD(int caja_ahorro_USD) {
        this.caja_ahorro_USD = caja_ahorro_USD;
    }


}
