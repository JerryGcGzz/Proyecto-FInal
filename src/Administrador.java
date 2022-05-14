<<<<<<< HEAD
public class Administrador {
=======
import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Administrador implements loginUser, userMenu {
    private Scanner entrada = new Scanner(System.in);
    private String Usuario;
    private String contraseña;
    private HashMap<String, String> Administradores = new HashMap<>();

    //Metodo para cargar todos los administradores activos
    void load(){
        String tokens[] = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Administradores.txt"));
            String line;
            while((line = reader.readLine()) != null){
                tokens = line.split(",");
                Administradores.put(tokens[0], tokens[1]);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo para añadir nuevos administradores
    void save(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Administradores.txt"));
            for(Map.Entry<String, String> entry: Administradores.entrySet()){
                writer.write(entry.getKey()+","+entry.getValue()+"\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo para verificar si se escogio el login correcto
    public boolean verifyLogin(){
        Scanner entrada = new Scanner(System.in);
        Boolean condicion = false; //Variable para controlar el ciclo while, donde se le pide al usuario ingresar una opcion de continuar o salir
        Boolean condicion2 = false; //Variable que controla el ciclo while del main.
        Integer opcion;

        System.out.println("*************Inicio de Sesión como Administrador*************");
        System.out.println("¿Cuenta con una cuenta de administrador?\n"
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
        System.out.println("\n\nIngrese su usuario: ");
        Usuario = entrada.next();

        return Usuario;

    }

    //Metodo para ingresar contraseña
    @Override
    public  String getContraseña(){
        System.out.println("Ingrese su contraseña: ");
        contraseña = entrada.next();

        return contraseña;
    }

    //Metodo para verificar credenciales de Administrador
    public boolean verifyCredentials(String Usuario, String contraseña){
        Boolean condicion = false;

        for(Map.Entry<String, String> entry: Administradores.entrySet()) {
            int n = 0;
            while (n < Administradores.size() && condicion == false) {
                String tempUser = entry.getKey();
                String tempPass = entry.getValue();
                n++;
                if (Usuario.equals(tempUser) && contraseña.equals(tempPass)) {
                    condicion = true;
                    System.out.println("Inicio de Sesión correcta");
                    System.out.println("*************Inicio de Sesión como Administrador*************");
                    System.out.println("\nCargando menú de administrador.....");
                } else {
                    condicion = false;
                }
            }
        }
        return condicion;
    }

    //Metodo para imprimir el menu personalizado de los administradores
    @Override
    public Integer showuserMenu() {
        Scanner entrada = new Scanner(System.in);
        boolean condicion = false; //Valor que controla el ciclo while de digite una opcion del menu personalizado de Administrador
        Integer valorEvaluado = 0;

        System.out.println("\n\n*************Menu de Administrador*************\n"
            +"1) Dar de alta doctor\n"
            +"2) Dar de alta paciente\n"
            +"3) Generar una cita\n"
            +"4) Ver todas las citas\n"
            +"5) Ver lista de doctores\n"
            +"6) Ver lista de pacientes\n"
            +"0) Cerrar sesión\n"
        );
        while(!condicion){
            try{
                System.out.println("Digite una opción: ");
                Integer opcion = entrada.nextInt();

                if(opcion < 0 || opcion > 6) throw new Exception();
                if(opcion < 0 || opcion > 6){
                    condicion = false;
                }else{
                    condicion = true;
                }

                valorEvaluado = opcion;
            }catch (InputMismatchException e){
                System.out.println("Dato inválido, digite un número del 0 al 6");
                entrada.next();
            }catch(Exception e){
                System.out.println("Dato inválido, digite un número del 0 al 6");
            }

        }
        return valorEvaluado;
    }

    //Metodo para evaluar y ejecutar las opciones del menu personalizado administrador
    @Override
    public boolean evaluatecustomMenu(Integer a) {
        Boolean condicion = true;

        switch (a){
            case 0:
                condicion = false;
                System.out.println("*************Menu de Administrador*************");
                System.out.println("Cerrando sesión....");
                System.out.println("Regresando al menú principal...\n\n");
                break;
            case 1:
                condicion = true;
                Scanner entrada = new Scanner(System.in);
                Doctor doctor = new Doctor();

                doctor.load();
                System.out.println("Ingrese el ID del doctor: ");
                doctor.getID(entrada.next());
                System.out.println("Ingrese el nombre del doctor: ");
                doctor.getNombre(entrada.next());
                System.out.println("Ingrese la especialidad del doctor: ");
                doctor.getEspecialidad(entrada.next());
                doctor.addDoctors();
                doctor.save();
                break;
            case 2:
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

            case 3:

                condicion = true;
                Scanner teclado = new Scanner(System.in);

                //Creacion de objetos
                Doctor doctor2 = new Doctor();
                Paciente paciente2 = new Paciente();
                Cita cita = new Cita();

                //cargar los archivos respectivos
                doctor2.load();
                paciente2.load();
                cita.load();

                //Muestra los dcotores y pacientes dados de alta
                System.out.println("Cargando base de datos de doctores....\n");
                System.out.println("Doctores disponibles: ");
                doctor2.listDoctor();
                System.out.println("Cargando base de datos de pacientes....\n");
                System.out.println("Pacientes dados de alta: ");
                paciente2.listPacientes();



                //Llena los campos para generar una cita
                System.out.println("Llene los siguientes parametros: ");
                System.out.println("Ingrese el ID de la cita: ");
                cita.setId(teclado.nextLine());
                System.out.println("Ingrese la fecha de la cita: ");
                cita.setFecha(teclado.nextLine());
                System.out.println("Ingrese el motivo de la cita: ");
                cita.setMotivo(teclado.nextLine());

                //Metodo para comprobar que el doctor existe
                boolean condicionD = false;
                while(!condicionD)
                try {
                    System.out.println("Ingrese el index del doctor al cual se le va agendar la cita: ");
                    int indexD = teclado.nextInt();

                    if(indexD < 0 || indexD >= doctor2.getsizeDoc()) throw new Exception();
                    if(indexD < 0 || indexD >= doctor2.getsizeDoc()){
                        condicionD = false;
                    }else{
                        condicionD = true;
                    }

                    String tempD = doctor2.getDoctors(indexD);
                    cita.setMedico(tempD);
                }catch (InputMismatchException e){
                    System.out.println("Dato invalido, ingrese un index");
                    teclado.next();
                }catch (Exception e){
                    System.out.println("Dato invalido, fuera de rango");
                }

                //Metodo para comprobar que el paciente existe
                boolean condicionP = false;
                while (!condicionP) {
                    try {
                        System.out.println("Ingrese el index del paciente al cual se le va agendar la cita: ");
                        int indexP = teclado.nextInt();

                        if(indexP < 0 || indexP >= paciente2.getsizePac()) throw new Exception();
                        if(indexP < 0 || indexP >= paciente2.getsizePac()){
                            condicionP = false;
                        }else{
                            condicionP = true;
                        }

                        String tempP = paciente2.getPaciente(indexP);
                        cita.setPaciente(tempP);
                    }catch (InputMismatchException e){
                        System.out.println("Dato invalido, ingrese un index");
                        teclado.next();
                    }catch (Exception e){
                        System.out.println("Dato invalido, fuera de rango");
                    }
                }

                cita.addCitas();
                cita.save();
                break;

            case 4:
                condicion = true;
                Cita cita1 = new Cita();

                cita1.load();
                System.out.println("Citas dentro del sistema: ");
                cita1.listCitas();
                break;

            case 5:
                condicion = true;
                Doctor doctor1 = new Doctor();

                doctor1.load();
                System.out.println("Lista de docotes activos: ");
                doctor1.listDoctor();
                break;

            case 6:
                condicion = true;
                Paciente paciente1 = new Paciente();

                paciente1.load();
                System.out.println("Lista de pacientes dados de alta: ");
                paciente1.listPacientes();
                break;
        }
        return condicion;
    }


>>>>>>> develop

}
