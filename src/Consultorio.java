<<<<<<< HEAD
public class Consultorio {


}
=======
import java.util.InputMismatchException;
import java.util.Scanner;

public class Consultorio{
    public static void main(String[] args) throws Exception{
        while(!evaluateMenu(showMenu()));
    }


// Esta es la primera pantalla del programa, es el menú principal
public static Integer showMenu() throws Exception{
    Scanner entrada = new Scanner(System.in);
    boolean condicion = false; //Esta condición controla el ciclo while en donde se pregunta por la entrada del usuario.
    Integer valorEvaluado = 0;

    System.out.println("*************Inicio de Sesión*************");
    System.out.println("1) Inicio de sesión como administrador\n"
                +"2) Incio de sesión como doctor\n"
                +"3) Inicio de sesión como paciente\n\n"
                +"0) Cerrar programa"
    );
    System.out.println("*************Inicio de Sesión*************\n\n");

    while(!condicion){
        try{
            System.out.println("Ingrese una opción: ");
            Integer opcion = entrada.nextInt(); //Esta variable es la que ingresa el usuario.
            valorEvaluado = opcion;

            if(opcion < 0 || opcion > 3) throw new Exception();

            if(opcion < 0 || opcion > 3){
                condicion = false;
            }else{
                condicion = true;
            }

        } catch (InputMismatchException e){
            System.out.println("Dato inválido, ingrese un numero del 0 al 3");
            entrada.next();
        } catch (Exception e){
            System.out.println("Dato inválido, ingrese un numero del 0 al 3");
        }
    }

    return valorEvaluado;
}

//Método para evaluar la opcion del Menu principal
public static boolean evaluateMenu(Integer a){
            boolean condicion = false; //Esta condicion controla si ciclo que se encunetra en Main

            switch (a){
                case 0:
                    System.out.println("Cerrando el programa...");
                    System.exit(0);
                    break;

                case 1:
                    Administrador ejemplo1 = new Administrador();
                    ejemplo1.load();
                    if(ejemplo1.verifyLogin() == true){
                        condicion = true;
                        if(ejemplo1.verifyCredentials(ejemplo1.getUsuario(), ejemplo1.getContraseña()) == true){
                            if(ejemplo1.evaluatecustomMenu(ejemplo1.showuserMenu()) == false){
                                condicion = false;
                            }else{
                                        while(ejemplo1.evaluatecustomMenu(ejemplo1.showuserMenu()) == true);
                                        condicion = false;
                            }
                        }else {
                            System.out.println("Credenciales incorrectas");
                            System.out.println("Regresando al Menú Principal...\n\n");
                            condicion = false;
                        }
                    }else{
                        condicion = false;
                        System.out.println("Regresando al Menú principal....\n\n");
                    }
                    break;

                case 2:
                    Doctor ejemplo2 = new Doctor();
                    ejemplo2.load();
                    if(ejemplo2.verifyLoginD() == true){
                        condicion = true;
                        if(ejemplo2.verifyCredentialsD(ejemplo2.getUsuario(), ejemplo2.getContraseña()) == true){
                            ejemplo2.saveEsp();
                            if(ejemplo2.evaluatecustomMenu(ejemplo2.showuserMenu()) == false){
                                condicion = false;
                            }else{
                                        while(ejemplo2.evaluatecustomMenu(ejemplo2.showuserMenu()) == true);
                                        condicion = false;
                            }
                        }else{
                            System.out.println("Credenciales incorrectas");
                            System.out.println("Regresando al Menú Principal...\n\n");
                            condicion = false;
                        }
                    }else{
                        condicion = false;
                        System.out.println("Regresando al Menú principal....\n\n");
                    }
                    break;

                case 3:
                    Paciente ejemplo3 = new Paciente();
                    ejemplo3.load();
                    if(ejemplo3.verifyLoginP() == true){
                        condicion = true;
                        if(ejemplo3.verifyCredentialsP(ejemplo3.getUsuario(), ejemplo3.getContraseña()) == true){
                            if(ejemplo3.evaluatecustomMenu(ejemplo3.showuserMenu()) == false){
                                condicion = false;
                            }else{
                                        while (ejemplo3.evaluatecustomMenu(ejemplo3.showuserMenu()) == true);
                                        condicion = false;
                            }
                        }else{
                            System.out.println("Credenciales incorrectas");
                            System.out.println("Regresando al Menú Principal...\n\n");
                            condicion = false;
                        }
                    }else{
                        condicion = false;
                        System.out.println("Regresando al Menú principal....\n\n");
                    }
                    break;
            }
            return condicion;
}



}








>>>>>>> develop
