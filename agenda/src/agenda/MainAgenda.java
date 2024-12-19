package agenda;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MainAgenda {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		AgendaUsuario a1 = new AgendaUsuario();
		File listaUsuarios = new File("listadoUsuarios.txt");
		int opcion = 0;
		String nombre = "";
		do {
			opcion = mostrarMenu(sc);
			if (opcion != 3) {
				boolean correrPrograma = true;
				if (opcion == 1) {
					nombre = añadirUsuario();
					AgendaUsuario agendaCreada = new AgendaUsuario(nombre);
					while (correrPrograma) {
						correrPrograma = arrancarPrograma(nombre, agendaCreada);
					}
					serializarAgenda(nombre, agendaCreada);
				} else {
					nombre = loguearUsuario(sc, listaUsuarios);
					if (!nombre.equals("invalido")) {
						AgendaUsuario agendaCargada = deserializarAgenda(nombre);
						while (correrPrograma) {
							correrPrograma = arrancarPrograma(nombre, agendaCargada);
						}
						serializarAgenda(nombre, agendaCargada);
					} else {
						opcion = 3;
					}
				}
			}
		} while (opcion != 3);
	}

	public static int mostrarMenu(Scanner sc) {
		int opcion = 0;
		boolean valido = false;
		do {
			try {
				System.out.println("AGENDA DAW");
				System.out.println("1. Crear un nuevo usuario");
				System.out.println("2. Logarse como ususario");
				System.out.println("3. SALIR");
				System.out.println("Introduce una opción");
				opcion = sc.nextInt();
				if (opcion >= 1 && opcion <= 3) {
					valido = true;
				} else {
					System.out.println("Datos válidos: 1, 2 Ó 3");
				}
			} catch (Exception e) {
				System.out.println("Introduce un valor válido");
				sc.next();
			}
		} while (!valido);
		return opcion;
	}

	public static int mostrarMenuPrograma() {
		Scanner sc = new Scanner(System.in);
		boolean valido = false;
		boolean salir = false;
		int opcion = 0;
		do {
			try {
				System.out.println("---------------- MENU ----------------");
				System.out.println("1. Registrar contacto.");
				System.out.println("2. Ver contactos.");
				System.out.println("3. Buscar contacto.");
				System.out.println("4. Eliminar contacto.");
				System.out.println("5. Salir.");
				opcion = sc.nextInt();
				if (opcion >= 1 && opcion <= 5) {
					valido = true;
					if (opcion == 5) {
						salir = true;
					}
				}
			} catch (Exception e) {
				System.out.println("Introduce un valor valido");
			}
		} while (!valido && !salir);
		return opcion;
	}

	public static boolean arrancarPrograma(String nombreUsuario, AgendaUsuario agenda) {
		Scanner sc = new Scanner(System.in);
		String nombre = "";
		String apellidos = "";
		boolean correrPrograma = true;
		int opcion = mostrarMenuPrograma();
		if (opcion == 1) {
			agenda.registrarContacto();
		} else if (opcion == 2) {
			agenda.verContactos();
		} else if (opcion == 3 || opcion == 4) {
			System.out.println("Introduce el nombre del contacto: ");
			nombre = sc.next();
			sc.nextLine();
			System.out.println("Introduce los apellidos del contacto: ");
			apellidos = sc.nextLine();
			if (opcion == 3) {
				agenda.buscarContacto(nombre, apellidos);
			} else {
				agenda.eliminarContacto(nombre, apellidos);
			}
		} else {
			System.out.println("Saliendo...");
			correrPrograma = false;
		}
		return correrPrograma;
	}

	public static void serializarAgenda(String nombre, AgendaUsuario agenda) {
		char inicial = nombre.charAt(0);
		String resto = nombre.substring(1);
		String nombreAgenda = "agenda" + (String.valueOf(inicial)).toUpperCase() + resto + ".dat";
		try {
			FileOutputStream archivoAgenda = new FileOutputStream(nombreAgenda, true);
			ObjectOutputStream escritura = new ObjectOutputStream(archivoAgenda);
			escritura.writeObject(agenda);
			escritura.close();
			archivoAgenda.close();
		} catch (IOException e) {
			System.out.println("Fallito");
		}
	}

	public static AgendaUsuario deserializarAgenda(String nombre) {
		char inicial = nombre.charAt(0);
		String resto = nombre.substring(1);
		String nombreAgenda = "agenda" + (String.valueOf(inicial)).toUpperCase() + resto + ".dat";
		AgendaUsuario a1 = null;
		try {
			FileInputStream archivoAgenda = new FileInputStream(nombreAgenda);
			ObjectInputStream leectura = new ObjectInputStream(archivoAgenda);
			a1 = (AgendaUsuario) leectura.readObject();
			leectura.close();
			archivoAgenda.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Fallito");
		}
		return a1;
	}

	public static String loguearUsuario(Scanner sc, File listaUsuarios) {
		boolean valido[];
		boolean nombreValido = false;
		boolean claveValido = false;
		boolean terminar = false;
		int fallaNombre = 0;
		int fallaClave = 0;
		int intentos = 3;
		String nombre = "";
		do {
			try {
				System.out.println("TIENES " + intentos + " INTENTOS PARA LOGEARSE");
				System.out.println("Introduce el nombre de usuario");
				nombre = sc.next();
				System.out.println("Introduce su clave");
				String clave = sc.next();
				valido = verificarLogueo(nombre, clave);
				nombreValido = valido[0];
				claveValido = valido[1];
				if (!nombreValido || !claveValido) {
					intentos--;
					System.out.println("Nombre/ clave incorrecto");
					if (nombreValido == false) {
						fallaNombre++;
						if (fallaNombre == 3) {
							System.out.println("Ha llegado al maximo numero de intentos");
							terminar = true;
						}
					}
					if (claveValido == false) {
						fallaClave++;
						if (fallaClave == 3 && nombreValido) {
							terminar = true;
							System.out.println("Ha fallado 3 veces la constraseña, se eliminara el usuario");
							borrarUsuario(nombre, listaUsuarios);
						}
					}
					nombre = "invalido";
				} else {
					terminar = true;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (!terminar && intentos > 0);
		if (!nombreValido || !claveValido) {
			System.out.println("El logueo no ha sido posible");
		} else {
			System.out.println("Se ha logueado con exito");
		}
		return nombre;
	}

	public static void borrarUsuario(String nombre, File listadoUsuarios) {
		File listadoUsuariosAux = new File("listadoUsuariosAux.txt");
		try {
			FileReader lector = new FileReader(listadoUsuarios);
			BufferedReader bufferLector = new BufferedReader(lector);
			FileWriter escritor = new FileWriter(listadoUsuariosAux);
			BufferedWriter bufferEscritor = new BufferedWriter(escritor);
			String linea = "";
			while ((linea = bufferLector.readLine()) != null) {
				String arrayNombres[] = linea.split("\\s+");
				int j = 0;
				for (int i = 0; i < arrayNombres.length; i++) {
					if (i % 2 == 2) {
						if (arrayNombres[i].equals(nombre)) {
							j = i + 1;
						}
					}
					if (!arrayNombres[i].equals(nombre) && i != j) {
						bufferEscritor.write(arrayNombres[i]);
						bufferEscritor.write(' ');
					}
				}
				bufferEscritor.newLine();
			}
			bufferLector.close();
			lector.close();
			bufferEscritor.close();
			escritor.close();
			listadoUsuarios.delete();
			listadoUsuariosAux.renameTo(listadoUsuarios);
		} catch (IOException e) {
			System.out.println("NO SE PUDO CREAR EL ARCHIVO");
		}
	}

	public static String añadirUsuario() {
		FileWriter escritor = null;
		BufferedWriter bufferEscritura = null;
		try {
			escritor = new FileWriter("listadoUsuarios.txt", true);
			bufferEscritura = new BufferedWriter(escritor);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		String nombreClave[] = solicitarDatosUsuario();
		String nombre = nombreClave[0];
		String clave = nombreClave[1];
		try {
			for (int i = 0; i < nombre.length(); i++) {
				bufferEscritura.write(nombre.charAt(i));
			}
			bufferEscritura.write(" ");
			for (int i = 0; i < clave.length(); i++) {
				bufferEscritura.write(clave.charAt(i));
			}
			bufferEscritura.write(" ");
			bufferEscritura.close();
			escritor.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return nombre;
	}

	public static String[] solicitarDatosUsuario() {
		Scanner sc = new Scanner(System.in);
		boolean valido = false;
		boolean nombreRepetido = false;
		String nombreClave[] = new String[2];
		String nombre = "";
		String clave = "";
		do {
			try {
				System.out.println("Introduce tu nombre de usuario");
				nombre = sc.nextLine();
				excepcionNombre(nombre);
				System.out.println("Introduce tu clave");
				clave = sc.nextLine();
				excepcionNombre(clave);
				valido = true;
				nombreClave[0] = nombre;
				nombreClave[1] = clave;
				nombreRepetido = verNombreRepetido(nombre, clave);
				if (nombreRepetido) {
					System.out.println("Nombre de usuario ya existe, inserte otro");
				}
			} catch (ExcepcionNombre a) {
				System.out.println(a.getMessage());
			}
		} while (!valido || nombreRepetido);
		return nombreClave;
	}

	public static void excepcionNombre(String nombre) throws ExcepcionNombre {
		if (nombre.contains(" ")) {
			throw new ExcepcionNombre("No se permiten espacios");
		}
	}

	public static boolean verNombreRepetido(String nombre, String clave) {
		boolean repetido[] = verificarLogueo(nombre, clave);
		boolean nombreRepetido = repetido[0];
		if (nombreRepetido)
			return true;
		else
			return false;
	}

	public static boolean[] verificarLogueo(String nombre, String clave) {
		ArrayList<String> listaNombreClave = listarNombresClaves();
		boolean nombreValido = false;
		boolean claveValido = false;
		int j = 0;
		for (int i = 0; i < listaNombreClave.size(); i++) {
			if (i % 2 == 0) {
				if (nombre.equals(listaNombreClave.get(i))) {
					nombreValido = true;
					j = i + 1;
				}
			} else {
				if (i == j) {
					if (clave.equals(listaNombreClave.get(i))) {
						claveValido = true;
					}
				}
			}
		}
		boolean[] valido = { nombreValido, claveValido };
		return valido;
	}

	public static ArrayList<String> listarNombresClaves() {
		ArrayList<String> palabras = new ArrayList();
		String palabrasAux[] = null;
		try {
			FileReader lector = new FileReader("listadoUsuarios.txt");
			BufferedReader bufferLectura = new BufferedReader(lector);
			String linea;
			while ((linea = bufferLectura.readLine()) != null) {
				palabrasAux = linea.split("\\s+");
				for (int i = 0; i < palabrasAux.length; i++) {
					palabras.add(palabrasAux[i]);
				}
			}
			bufferLectura.close();
			lector.close();
		} catch (IOException e) {
			System.out.println("No se ha podido leer el archivo");
		}
		return palabras;
	}
}
