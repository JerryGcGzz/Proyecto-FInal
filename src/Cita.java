public class Cita {
    private String id;
    private String motivo;
    private String dia;
    private String mes;
    private String año;
    private String hora;
    private String minutos;


    //Constructores de la clase Cita
    public String id(){
        return id;
    }
    public void getid(String id){
        this.id = id;
    }

    public String motivo(){
        return motivo;
    }

    public void getmotivo(String motivo){
        this.motivo = motivo;
    }

    public  String dia(){
        return dia;
    }

    public void getDia(String dia){
        this.dia = dia;
    }

    public String mes(){
        return mes;
    }

    public void getMes(String mes){
        this.mes = mes;
    }

    public String año(){
        return año;
    }

    public void getAño(String año){
        this.año = año;
    }

    public String hora(){
        return hora;
    }

    public void getHora(String hora){
        this.hora = hora;
    }

    public String minutos(){
        return minutos;
    }

    public void getMinutos(String minutos){
        this.minutos = minutos;
    }



}
