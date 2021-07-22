package edu.ieu.actividadndividual;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor2por2 {
	
	private DatagramSocket socket;
	private DatagramPacket paqueteRecibido;
	private DatagramPacket paqueteEnviado;
	
	public static void main(String[] args) {
		Servidor2por2 servidorUdp = new Servidor2por2();
		System.out.println("Iniciando Traductor en el puerto 6000....");
		servidorUdp.enviarYRecibir();
	}
	
	public void enviarYRecibir() {
		try {
			socket = new DatagramSocket(6000);
			byte[] mensajeBytesRecibido = new byte[256];
			byte[] mensajeBytesEnviado = new byte[256];
			String mensajeTexto = "";
			mensajeTexto = new String(mensajeBytesRecibido);
			String mensajeRespuesta = "";
			
			paqueteRecibido = new DatagramPacket(mensajeBytesRecibido,256);
			paqueteEnviado = new DatagramPacket(mensajeBytesEnviado,256); 
			
			int port;
			InetAddress address;
			byte[] mensaje2_bytes = new byte[256];
			
			do {
				socket.receive(paqueteRecibido);
				mensajeTexto = new String(mensajeBytesRecibido).trim();
				
				System.out.println("Numero recibido; " +
						mensajeTexto);
				port = paqueteRecibido.getPort();
				address = paqueteRecibido.getAddress();				
				System.out.println("del puerto: " + port + 
						", direccion ip: " + address.toString() );
				int numero = Integer.parseInt(mensajeTexto);
				int mul = (numero * numero);
				mensajeRespuesta = String.valueOf(mul);
				numero = 0;
				mul = 0;
				if(mensajeTexto.startsWith("fin")) {
					mensajeRespuesta = "Chauuuuu cliente";
				}
				mensajeBytesEnviado = mensajeRespuesta.getBytes();
				paqueteEnviado = new DatagramPacket(mensajeBytesEnviado,
						mensajeBytesEnviado.length,
						address,
						port
						);
				socket.send(paqueteEnviado);
			}while(true);
		}catch( IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
