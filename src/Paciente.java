<<<<<<< HEAD
public class Paciente {

=======
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Paciente implements loginUser, userMenu{
    Scanner entrada = new Scanner(System.in);

    private String ID;
    private String Nombre;
    private List<Object> Pacientes = new ArrayList<>();


    //Constructores de la clase Paciente.
    public String ID(){
        return ID;
    }

    public void getIDP(String ID){
        this.ID = ID;
    }

    public String Nombre(){
        return Nombre;
    }

    public void getNombreP(String Nombre){
        this.Nombre = Nombre;
    }

    //Metodo para obtener elementos
    public String getPaciente(int a){
        String b = Pacientes.get(a).toString();
        return b;
    }

    //Meto para obtener tamaño del array de Paciente
    public int getsizePac(){
        return Pacientes.size();
    }

    //Metodo para cargar lista de pacientes actuales.
    void load(){
        String tokens[] = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Pacientes.txt"));
            String line;
            while((line = reader.readLine()) != null){
                tokens = line.split(",");
                Pacientes.add(tokens[0]+","+tokens[1]);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo para dar de alta a Pacientes y guardar cambios en archivo de texto.
    void save(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Pacientes.txt"));
            for(Object o : Pacientes){
                writer.write(o+"\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo para dar de alta pacientes desde programa.
    public List<Object> addPacients(){
        Pacientes.add(ID+","+Nombre);
        return Pacientes;
    }

    //Metodo para ver todos los pacientes dados de alta-
    public void listPacientes(){
        String tokens[] = null;
        int n=0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Pacientes.txt"));
            String line;
            while((line = reader.readLine()) != null){
                tokens = line.split(",");
                System.out.println(n+")"+"ID: "+tokens[0]+"\n"+"Nombre: "+tokens[1]+"\n");
                n++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo para verificar si se escogio el login correcto
    public boolean verifyLoginP(){
        Scanner entrada = new Scanner(System.in);
        Boolean condicion = false; //Variable para controlar el ciclo while, donde se le pide al usuario ingresar una opcion de continuar o salir
        Boolean condicion2 = false; //Variable que controla el ciclo while del main.
        Integer opcion;

        System.out.println("*************Inicio de Sesión como Paciente*************");
        System.out.println("¿Cuenta con una cuenta de Paciente?\n"
                + "1) Para continuar\n"
                + "0) Para salir\n"
        );
        while(!condicion){
            try{
                System.out.println("Digite una opcion: ");
                opcion = entrada.nextInt();

                if(opcion < 0 || opcion > 1) throw new Exception();
                if(opcion < 0 || opcion > 1){
                    condicion = false;
                }else if (opcion == 1){
                    condicion = true;
                    condicion2 = true;
                }else  if(opcion == 0){
                    condicion = true;
                    condicion2 = false;
                }

            }catch (InputMismatchException e){
                System.out.println("Dato inválido, ingrese un número del 0 al 1");
                entrada.next();
            }catch (Exception e){
                System.out.println("Dato inválido, ingrese un número del 0 al 1");
            }

        }
        return condicion2;
    }

    //Metodo para ingresar usuario
    public String getUsuario(){
        System.out.println("Ingrese su nombre: ");
        Nombre = entrada.next();

        return Nombre;
    }

    //Metodo para ingresar ID
    public String getContraseña(){
        System.out.println("Ingrese su ID: ");
        ID = entrada.next();

        return ID;
    }

    //Metodo para verificar credenciales de paciente
    public boolean verifyCredentialsP(String Usuario, String contraseña){
        boolean condicion = false;
        String tokens2[] = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Pacientes.txt"));
            String line;
            while((line = reader.readLine()) != null && condicion == false){
                tokens2 = line.split(",");
                String tempPass = tokens2[0];
                String tempUser = tokens2[1];
                if (Usuario.equals(tempUser) && contraseña.equals(tempPass)) {
                    condicion = true;
                    System.out.println("Inicio de Sesión correcta");
                    System.out.println("*************Inicio de Sesión como Paciente*************");
                    System.out.println("\nCargando menú de Paciente.....");
                    break;
                } else {
                    condicion = false;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return condicion;
    }


    //Metodo para imprimir el menu personalizado de los doctores
    public Integer showuserMenu(){
        Scanner entrada = new Scanner(System.in);
        boolean condicion = false; //Valor que controla el ciclo while de digite una opcion del menu personalizado de Paciente
        Integer valorEvaluado = 0;

        System.out.println("\n\n*************Menu de Paciente*************\n"
                +"1) Crear cita\n"
                +"2) Ver citas\n"
                +"0) Cerrar sesión\n"
        );
        while(!condicion){
            try{
                System.out.println("Digite una opción: ");
                Integer opcion = entrada.nextInt();

                if(opcion < 0 || opcion > 2) throw new Exception();
                if(opcion < 0 || opcion > 2){
                    condicion = false;
                }else{
                    condicion = true;
                }

                valorEvaluado = opcion;
            }catch (InputMismatchException e){
                System.out.println("Dato inválido, digite un número del 0 al 2");
                entrada.next();
            }catch(Exception e){
                System.out.println("Dato inválido, digite un número del 0 al 2");
            }

        }
        return valorEvaluado;
    }


    //Metodo para evaluar y ejecutar las opciones del menu personalizado administrador
    public boolean evaluatecustomMenu(Integer a){
        Boolean condicion = true;

        switch (a){
            case 0:
                condicion = false;
                System.out.println("*************Menu de Pacienter*************");
                System.out.println("Cerrando sesión....");
                System.out.println("Regresando al menú principal...\n\n");
                break;

            case 1:
                condicion = true;
                Scanner input = new Scanner(System.in);


                Doctor doctor = new Doctor();
                Paciente paciente = new Paciente();
                Cita cita = new Cita();

                doctor.load();
                paciente.load();
                cita.load();

                System.out.println("Cargando base de datos....");
                System.out.println("Doctores dados activos: ");
                doctor.listDoctor();

                //Llena los campos para generar una cita
                System.out.println("Llene los siguientes parametros: ");
                System.out.println("Ingrese el ID de la cita: ");
                cita.setId(input.nextLine());
                System.out.println("Ingrese la fecha de la cita: ");
                cita.setFecha(input.nextLine());
                System.out.println("Ingrese el motivo de la cita: ");
                cita.setMotivo(input.nextLine());

                //Metodo para comprobar que el doctor existe
                boolean condicionD = false;
                while(!condicionD)
                    try {
                        System.out.println("Ingrese el index del doctor al cual se le va agendar la cita: ");
                        int indexD = input.nextInt();

                        if(indexD < 0 || indexD >= doctor.getsizeDoc()) throw new Exception();
                        if(indexD < 0 || indexD >= doctor.getsizeDoc()){
                            condicionD = false;
                        }else{
                            condicionD = true;
                        }

                        String tempD = doctor.getDoctors(indexD);
                        cita.setMedico(tempD);
                    }catch (InputMismatchException e){
                        System.out.println("Dato invalido, ingrese un index");
                        input.next();
                    }catch (Exception e){
                        System.out.println("Dato invalido, fuera de rango");
                    }

                    //Se añade el paciente que inicio sesion
                    cita.setPaciente(ID+","+Nombre);

                cita.addCitas();
                cita.save();
                break;

            case 2:
                condicion = true;
                String tokens[] = null;
                int n=0;
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("Citas.txt"));
                    String line;
                    while((line = reader.readLine()) != null){
                        tokens = line.split(",");
                        String tempPass = tokens[6];
                        String tempUser = tokens[7];
                        if (Nombre.equals(tempUser) && ID.equals(tempPass)) {
                            System.out.println(n+")"+"ID: "+tokens[0]+"\n"+"Fecha: "+tokens[1]+"\n"+"Motivo: "+tokens[2]+"\n"+"ID Doctor: "+tokens[3]+"\n"+"Doctor: "+tokens[4]+"\n"+"Especialidad: "+tokens[5]+"\n"+"ID Paciente: "+tokens[6]+"\n"+"Paciente: "+tokens[7]+"\n");
                            n++;
                        }
                    }
                    reader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
        return condicion;
    }
>>>>>>> develop
}
