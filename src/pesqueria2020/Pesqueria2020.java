/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pesqueria2020;

/**
 *
 * @author Yonatan Daniel Guala Oyarzo
 */
import java.io.*;
import java.util.Random;

public class Pesqueria2020 {

//	****	main	****
	public static void main(String[] args) {
		// TODO code application logic here
		Pesqueria2020 p = new Pesqueria2020();
		p.inici();
	}
	
	
//	El inicio del programa presenta el menu y recoge el input del jugador
	private void inici() {
	
	char opcion=' ';

        while (opcion != 's') {
            opcion = menu();

//	    una vez el jugador elige la opción deseada procesamos el input y realizamos la acción correspondiente
            switch (opcion) {
               
                case 'a' : 
		System.out.println(" Alta d'usuari ");
		System.out.println("---------------");
		altaUsuari();
		    break;
                case 'b' : 
		System.out.println(" Baixa d'usuari ");
		System.out.println("---------------");
		baixaUsuari();
                    break;
                case 'c' : 
		System.out.println("Pescar en una pesquera ");
                System.out.println("-----------------------");
		pescar();
		    break;
		case 'd' : 
		System.out.println("Estadístiques per usuari");
                System.out.println("------------------------");
		    break;
		case 'e' : 
		System.out.println("Estadístiques globals ");
		System.out.println("----------------------");
                    break;
                case 's': 
		System.out.println("---------------");
		System.out.println("Adiós!!!");
		System.out.println("---------------");
                    break;
                default: System.out.println("Opció '" + opcion + "' no válida. Introduïu a,b,c,d,e o s per sortir");
               
	    }
        }
	}
	
//	La función menu() presenta un menú al jugador y devuelve la opción introducida como char
	private char menu() {
        LT teclat = new LT();
        
	System.out.println("**********************************************");
        System.out.println("**       Programa de Pesca 2020             **");
	System.out.println("**********************************************");
        System.out.println("**          Triï una opció                  **");
	System.out.println("**********************************************");
	System.out.println("**       (a)= Alta d'usuari                 **");
        System.out.println("**       (b)= Baixa d'suari                 **");
	System.out.println("**       (c)= Pescar en una pesquera        **");
	System.out.println("**       (d)= Estadístiques per usuari      **");
	System.out.println("**       (e)= Estadístiques globals         **");
	System.out.println("**       (s)= Sortir del programa           **");
	System.out.println("**********************************************");
	System.out.println("- OPCIÓ ? ");
	
        char menu = teclat.llegirCaracter();
        return menu;	
	}

//	leer usuario
	
	public String leerUsuario(){
	LT teclat = new LT();
	System.out.println("Introduïu el nom d'usuari");
	String usuari = teclat.llegirLinia();
	return usuari;
	}

//	**** Alta d'usuari
	
	private void altaUsuari(){
		
	String usuari = leerUsuario();
	
	if (usuariExisteix(usuari)){
		System.out.println("No s'ha donat d'alta l'usuari " + usuari);
		esperaUnPoc();
	}else{
		crearUsuario(usuari);
		System.out.println("Usuari " + usuari + " donat d'alta");
		esperaUnPoc();

		}
	}
	
//	**** crear usuario en usuaris.txt
	private void crearUsuario(String usuari){
	
		File u = new File("usuaris.txt");
		File a = new File("auxiliar.txt");

		try (
			FileReader f = new FileReader(u);
			BufferedReader br = new BufferedReader(f);
			
			FileWriter f2 = new FileWriter(a);
			BufferedWriter bw = new BufferedWriter(f2);) {
			
			String s = br.readLine();
			while (s != null) {
				bw.write(s);
				bw.newLine();
				s = br.readLine();
			}
			
			bw.write(usuari);
			
			bw.close();
			f2.close();
			br.close();
			f.close();

			u.delete();
			a.renameTo(u);

		} catch (Exception e) {
			System.out.println("error custom : " + e);
		}
   }
	
//	**** baixa de usuari
	private void baixaUsuari(){
	String usuari = leerUsuario();
	
	if (usuariExisteix(usuari)){
		borrarUsuario(usuari);
		System.out.println("l'usuari " + usuari + " s'ha borrat");
		esperaUnPoc();
	}else{
		System.out.println("Usuari " + usuari + " no borrat");
		esperaUnPoc();

		}
	
	}
	
//	**** borrar usuari en usuaris.txt
	
	private void borrarUsuario(String usuari){
	
		File u = new File("usuaris.txt");
		File a = new File("auxiliar.txt");

		try (
			FileReader f = new FileReader(u);
			BufferedReader br = new BufferedReader(f);
			
			FileWriter f2 = new FileWriter(a);
			BufferedWriter bw = new BufferedWriter(f2);) {
			
			String s = br.readLine();
			while (s != null) {
				 if (!s.equals(usuari)){
					bw.write(s);
					bw.newLine();
				}
				s = br.readLine();
			}
						
			bw.close();
			f2.close();
			br.close();
			f.close();

			u.delete();
			a.renameTo(u);

		} catch (Exception e) {
			System.out.println("error custom : " + e);
		}
	
	}
	
//	**** comprobar si el usuario existe
	private Boolean usuariExisteix(String usuari){
		
		File u = new File("usuaris.txt");
		try(
			FileReader f = new FileReader(u);
			BufferedReader br = new BufferedReader(f);) 
		
		{
		
			String s = br.readLine();
			while (s != null){
			if (s.equals(usuari)){
				System.out.println("l'usuari " + usuari + " existeix");
				return true;
			}  
			s = br.readLine();
     
			}
			
			br.close();
			f.close();
			System.out.println("l'usuari " + usuari + " no existeix");
			return false;
			
		} catch (Exception e) {
			System.out.println("error custom : " + e);
		}
		return true;
	}
	
//	wait 
	private void esperaUnPoc(){
	try {
		Thread.sleep(1000);
	} catch (Exception ex) {
		System.out.println("error!!!");
		}
	}

//	****	PESCAR
	private void pescar(){
	
	String usuari = leerUsuario();

	if (usuariExisteix(usuari)){
		System.out.println("A pescar!!!");
		esperaUnPoc();
		char pesqueria=' ';

		while (pesqueria != 's') {
			pesqueria = menuPescar();
			String fichero = "";

//	    una vez el jugador elige la opción deseada procesamos el input y realizamos la acción correspondiente
			switch (pesqueria) {

				case 'a':
					System.out.println("Hola " + usuari + " vas a pescar al Mar Mediterrani");
					System.out.println("---------------");
					fichero = "mediterrani.txt";
					tirarLaCaña(usuari,fichero);
										
					//pesqueria = 's';
					break;
				case 'b':
					System.out.println("Hola " + usuari + " vas a pescar a la Costa de Florida");
					System.out.println("---------------");
					fichero = "florida.txt";
					tirarLaCaña(usuari,fichero);
					
					//pesqueria = 's';
					break;
				case 's':
					System.out.println(" Bye!!!");
					System.out.println("---------------");
					break;
				default: System.out.println(usuari + " la opció '" + pesqueria + "' no es válida. Introduïu a,b o s per sortir");
			}
		}
		
	}else{
		System.out.println("Usuari no trobat");
		esperaUnPoc();

		}
	}

//	Menu Pescar
	
	private char menuPescar(){
	LT teclat = new LT();
        	
	System.out.println("**********************************************");
        System.out.println("**          Escull pesquera                 **");
	System.out.println("**********************************************");
	System.out.println("**       (a)= Mar Mediterrani               **");
        System.out.println("**       (b)= Costa de Florida              **");
	System.out.println("**       (s)= Sortir de pesqueres           **");
	System.out.println("**********************************************");
	
	char pesqueria = teclat.llegirCaracter();
	return pesqueria;
	}

//	Generar número aleatorio
	
	public double randGenerator(){
	Random r = new Random(); 
        System.out.println(r);
	double d = r.nextDouble(); // número aleatorio entre 0 y 1
        System.out.println(d);
	
	return d;
	}
//	Tirar la Caña
	
	private void tirarLaCaña(String usuari, String fichero){
		
		double n = randGenerator();
		
		File p = new File(fichero);
		try(
			FileReader f = new FileReader(p);
			BufferedReader br = new BufferedReader(f);) 
		{
		
			String s = br.readLine();
			while (s != null){
			System.out.println(s);
			s = br.readLine();
     
			}
			br.close();
			f.close();
			
		} catch (Exception e) {
			System.out.println("error custom : " + e);
		}
		
		System.out.println("Usuario " + usuari +" está pescando en " + fichero + " y ha pescado ");
	}
}
