import java.io.*;
import java.util.*;

public class Doctor implements loginUser, userMenu{
    Scanner entrada = new Scanner(System.in);

     private String ID;
     private String nombre;
     private String especialidad;
     private List<Object> Doctors = new ArrayList<>();

    //Metodo para añadir doctores desde el progrmama
    public List<Object> addDoctors(){
        Doctors.add(ID+","+nombre+","+especialidad);
        return Doctors;
    }

    //Constructores de clase Doctor
    public String ID(){
        return ID;
    }

    public void getID(String ID){
        this.ID = ID;
    }

    public String nombre(){
        return nombre;
    }

    public void getNombre(String nombre){
        this.nombre = nombre;
    }

    public String especialidad(){
        return especialidad;
    }

    public void getEspecialidad(String especialidad){
        this.especialidad = especialidad;
    }

    public  void listDoctor(){
        System.out.println("Lista de Doctores: ");
        System.out.println("[ID]"+" [Nombre]"+" [Especialidad]");
        for (Object o : Doctors) {
            System.out.println(o);
        }

    }

    //Metodo para cargar doctores existentes
    void load(){
        String tokens[] = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Doctores.txt"));
            String line;
            while((line = reader.readLine()) != null){
                tokens = line.split(",");
                Doctors.add(tokens[0]+","+tokens[1]+","+tokens[2]);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo para añadir doctores
    void save(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Doctores.txt"));
            for(Object o : Doctors){
                writer.write(o+"\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo para verificar si se escogio el login correcto
    public boolean verifyLoginD(){
        Scanner entrada = new Scanner(System.in);
        Boolean condicion = false; //Variable para controlar el ciclo while, donde se le pide al usuario ingresar una opcion de continuar o salir
        Boolean condicion2 = false; //Variable que controla el ciclo while del main.
        Integer opcion;

        System.out.println("*************Inicio de Sesión como Doctor*************");
        System.out.println("¿Cuenta con una cuenta de Doctor?\n"
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
    @Override
    public String getUsuario(){
        System.out.println("Ingrese su nombre: ");
        nombre = entrada.next();

        return nombre;
    }

    //Metodo para ingresar ID
    @Override
    public String getContraseña(){
        System.out.println("Ingrese su ID: ");
        ID = entrada.next();

        return ID;
    }

    //Metodo para verificar credenciales de doctor
    public boolean verifyCredentialsD(String Usuario, String contraseña){
    boolean condicion = false;
        String tokens2[] = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Doctores.txt"));
            String line;
            while((line = reader.readLine()) != null && condicion == false){
                tokens2 = line.split(",");
                String tempPass = tokens2[0];
                String tempUser = tokens2[1];
                if (Usuario.equals(tempUser) && contraseña.equals(tempPass)) {
                    condicion = true;
                    System.out.println("Inicio de Sesión correcta");
                    System.out.println("*************Inicio de Sesión como Doctor*************");
                    System.out.println("\nCargando menú de Doctor.....");
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
    @Override
    public Integer showuserMenu(){
        Scanner entrada = new Scanner(System.in);
        boolean condicion = false; //Valor que controla el ciclo while de digite una opcion del menu personalizado de Doctor
        Integer valorEvaluado = 0;

        System.out.println("\n\n*************Menu de Doctor*************\n"
                +"1) Dar de alta paciente\n"
                +"2) Crear cita\n"
                +"3) Ver Citas\n"
                +"0) Cerrar sesión\n"
        );
        while(!condicion){
            try{
                System.out.println("Digite una opción: ");
                Integer opcion = entrada.nextInt();

                if(opcion < 0 || opcion > 3) throw new Exception();
                if(opcion < 0 || opcion > 3){
                    condicion = false;
                }else{
                    condicion = true;
                }

                valorEvaluado = opcion;
            }catch (InputMismatchException e){
                System.out.println("Dato inválido, digite un número del 0 al 3");
                entrada.next();
            }catch(Exception e){
                System.out.println("Dato inválido, digite un número del 0 al 3");
            }

        }
        return valorEvaluado;
    }


    //Metodo para evaluar y ejecutar las opciones del menu personalizado administrador
    @Override
    public boolean evaluatecustomMenu(Integer a){
        Boolean condicion = true;

        switch (a){
            case 0:
                condicion = false;
                System.out.println("*************Menu de Doctor*************");
                System.out.println("Cerrando sesión....");
                System.out.println("Regresando al menú principal...\n\n");
                break;

            case 1:
                condicion = true;
                Scanner valor = new Scanner(System.in);
                Paciente paciente = new Paciente();

                paciente.load();
                System.out.println("Ingrese el ID del paciente: ");
                paciente.getIDP(valor.next());
                System.out.println("Ingrese el nombre del paciente: ");
                paciente.getNombreP(valor.next());
                paciente.addPacients();
                paciente.save();
                break;

            case 2:
                condicion = true;
                System.out.println("Crear cita");
                break;

            case 3:
                condicion = true;
                System.out.println("Ver citas");
                break;
        }
        return condicion;
    }









}
