package agenda;

import java.io.Serializable;
import java.util.Scanner;

public class Contacto implements Serializable {
	private String nombre;
	private String apellidos;
	private String telefono;
	private String mail;
	private int edad;

	public Contacto() {
		Scanner sc = new Scanner(System.in);
		boolean valido = false;
		do {
			try {
				System.out.println("Introduce el nombre del contacto");
				nombre = sc.next();
				pedirApellidos();
				System.out.println("Introduce el telefono del contacto");
				telefono = sc.next();
				System.out.println("Introduce el mail del contacto");
				mail = sc.next();
				excepcionNombre(mail);
				System.out.println("Introduce la edad del contacto");
				edad = sc.nextInt();
				valido = true;
			} catch (Exception e) {
				System.out.println("Introduce datos validos");
				sc.nextLine();
			}
		} while (!valido);
	}

	public boolean excepcionNombre(String mail) throws ExcepcionNombre {
		boolean mailValido = false;
		if (!mail.contains("@")) {
			System.out.println("Debes introducir un mail valido");
			throw new ExcepcionNombre("Debes introducir un mail valido");
		} else {
			mailValido = true;
		}
		return mailValido;
	}

	public void pedirApellidos() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce los apellidos del contacto");
		apellidos = sc.nextLine();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "----------------------------------------------" + "\nNombre: " + nombre + "\nApellidos: " + apellidos
				+ "\nTelefono: " + telefono + "\nMail: " + mail + "\nEdad: " + edad
				+ "\n----------------------------------------------";
	}
}
