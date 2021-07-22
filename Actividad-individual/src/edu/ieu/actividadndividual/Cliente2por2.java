package edu.ieu.actividadndividual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente2por2 {
	
private BufferedReader inTeclado;
	
	private DatagramSocket socket;
	private DatagramPacket paqueteRecibido;
	private DatagramPacket paqueteEnviado;
	private InetAddress address;
	private int port = 6000;
	private byte[] mensajeBytesRecibido = new byte[256];
	private byte[] mensajeBytesEnviado = new byte[256];
	private String MensajeTextoRecibido  = "";
	private String MensajeTextoEnviado = "";
	
	public Cliente2por2() {
		inTeclado = new BufferedReader( new InputStreamReader(System.in));
	}
	
	public static void main(String[] args) {
		Cliente2por2 clienteUdp = new Cliente2por2();
		clienteUdp.enviarYRecibir();
	}
	
	public void enviarYRecibir() {
		try {
			socket = new DatagramSocket();
			address = InetAddress.getByName("localhost");
			
			do {
				System.out.println("Escriba el numero para tener al cuadrado: ");
				MensajeTextoEnviado = inTeclado.readLine();
				mensajeBytesEnviado = MensajeTextoEnviado.getBytes();
				
				paqueteEnviado = new DatagramPacket(mensajeBytesEnviado, 
						mensajeBytesEnviado.length,
						address,
						port);
				socket.send(paqueteEnviado);
				//System.out.println("Enviado al tra " + address.toString() 
				//+ " puerto " + port + " mensaje " + MensajeTextoEnviado);
				
				paqueteRecibido = new DatagramPacket(mensajeBytesRecibido, 256);
				socket.receive(paqueteRecibido);
				MensajeTextoRecibido = new String(mensajeBytesRecibido).trim();
				System.out.println("El resultado es:  " + 
						MensajeTextoRecibido);
			}while(!MensajeTextoRecibido.startsWith("Chauuuuu"));
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}

}
