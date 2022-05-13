import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Paciente implements loginUser{
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






}
