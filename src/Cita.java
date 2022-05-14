import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Cita {

    private String id;
    private String motivo;
    private String fecha;
    private Object medico;
    private Object paciente;

    private List<Object> citas = new ArrayList();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Object getMedico() {
        return medico;
    }

    public void setMedico(Object medico) {
        this.medico = medico;
    }

    public Object getPaciente() {
        return paciente;
    }

    public void setPaciente(Object paciente) {
        this.paciente = paciente;
    }

    //Metodo para cargar lista de citas.
    void load(){
        String tokens[] = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Citas.txt"));
            String line;
            while((line = reader.readLine()) != null){
                tokens = line.split(",");
                citas.add(tokens[0]+","+tokens[1]+","+tokens[2]+","+tokens[3]+","+tokens[4]+","+tokens[5]+","+tokens[6]+","+tokens[7]);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo para añadir citas
    void save(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Citas.txt"));
            for(Object o : citas){
                writer.write(o+"\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo para dar de alta pacientes desde programa.
    public List<Object> addCitas(){
        citas.add(id+","+fecha+","+motivo+","+medico+","+paciente);
        return citas;
    }

    //Metodo para ver todas las citas del sistema
    void listCitas(){
        String tokens[] = null;
        int n=0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Citas.txt"));
            String line;
            while((line = reader.readLine()) != null){
                tokens = line.split(",");
                System.out.println(n+")"+"ID: "+tokens[0]+"\n"+"Fecha: "+tokens[1]+"\n"+"Motivo: "+tokens[2]+"\n"+"ID Doctor: "+tokens[3]+"\n"+"Doctor: "+tokens[4]+"\n"+"Especialidad: "+tokens[5]+"\n"+"ID Paciente: "+tokens[6]+"\n"+"Paciente: "+tokens[7]+"\n");
                n++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}







