package agenda;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class AgendaUsuario implements Serializable {
	private ArrayList<Contacto> listaContactos;
	private String nombreUsuario;

	public AgendaUsuario(String nombreUsuario) {
		listaContactos = new ArrayList();
		this.nombreUsuario = nombreUsuario;
	}

	public AgendaUsuario() {
		listaContactos = new ArrayList();
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void registrarContacto() {
		Contacto c1 = new Contacto();
		int indice = encontrarContacto(c1.getNombre(), c1.getApellidos());
		if (indice == -1) {
			listaContactos.add(c1);
		} else {
			System.out.println("No puedes añadir contactos con el MISMO nombre y apellidos");
		}
	}

	public void buscarContacto(String nombre, String apellidos) {
		int indice = encontrarContacto(nombre, apellidos);
		if (listaContactos.size() != 0) {
			if (indice != -1) {
				for (int i = 0; i < listaContactos.size(); i++) {
					if (listaContactos.get(i).getNombre().equals(nombre)
							&& listaContactos.get(i).getApellidos().equals(apellidos)) {
						System.out.println("El contacto EXISTE: ");
						System.out.println(listaContactos.get(i).toString());
					}
				}
			} else {
				System.out.println("El contacto NO EXISTE.");
			}
		} else {
			System.out.println("La agenda esta vacia...");
		}
	}

	public void eliminarContacto(String nombre, String apellidos) {
		Scanner sc = new Scanner(System.in);
		int indice = encontrarContacto(nombre, apellidos);
		int opcion = 0;
		if (listaContactos.size() != 0) {
			if (indice != -1) {
				do {
					try {
						System.out.println("¿Desea ELIMINAR el contacto?");
						System.out.println("1. SI");
						System.out.println("2. NO");
						opcion = sc.nextInt();
					} catch (Exception e) {
						System.out.println("Introduce un numero 1-2");
					}
				} while (opcion != 1 && opcion != 2);
				if (opcion == 1) {
					listaContactos.remove(indice);
					System.out.println("Contacto ELIMINADO");
				}
			} else {
				System.out.println("El contacto NO EXISTE no se puede ELIMINAR.");
			}
		} else {
			System.out.println("La agenda esta vacia...");
		}
	}

	public void verContactos() {
		if (listaContactos.size() == 0) {
			System.out.println("No hay ningun contacto");
		} else {
			for (int i = 0; i < listaContactos.size(); i++) {
				System.out.println(listaContactos.get(i).toString());
			}
		}
	}

	public int encontrarContacto(String nombre, String apellidos) {
		int valor = -1;
		if (listaContactos.size() != 0) {
			for (int i = 0; i < listaContactos.size(); i++) {
				if (listaContactos.get(i).getNombre().equals(nombre)
						&& listaContactos.get(i).getApellidos().equals(apellidos)) {
					valor = i;
					break;
				}
			}
		}
		return valor;
	}
}
