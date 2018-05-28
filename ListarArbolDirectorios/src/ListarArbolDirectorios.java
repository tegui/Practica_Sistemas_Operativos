import java.io.File;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
/*implements NativeKeyListener */
public class ListarArbolDirectorios extends TimerTask {
	
	public static String filename = "listado.txt";
	public static String path = "C:/testfolder";
	
    public static void main(String[] args) throws IOException {
    	//EscribirEnArchivoDeTexto();
    	
    	//ListarDirectorios(new File("C:/Users/Juan Pablo Abad/SO")); //Replace this with a suitable directory
    	
    	//ListarDirectoriosEnArchivoDeTexto(new File(ListarArbolDirectorios.path));
    	
    	DetectarNuevosArchivosEnDirectorio();
    	
//    	ListarMientrasDetectaNuevosArchivosEnDirectorio(new File("C:/Users/Juan Pablo Abad/SO"));
    }
    
    public void run() {
    	checkAndNotify();
    }
 
    public static void ListarDirectorios(File dir) {
 
        File listFile[] = dir.listFiles();
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                	ListarDirectorios(listFile[i]);
                } else {
                    System.out.println(listFile[i].getPath());
                }
            }
        }
    }
    
    public static void EscribirEnArchivoDeTexto() throws FileNotFoundException, IOException {
    	
    	FileWriter archivoDeTexto = null;
        PrintWriter pw = null;
        try
        {
        	archivoDeTexto = new FileWriter("F:/prueba/ESCRIBIR.txt",true);
            pw = new PrintWriter(archivoDeTexto);

            for (int i = 0; i < 10; i++)
                pw.println("Linea " + i);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != archivoDeTexto)
        	   archivoDeTexto.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        
       
    }
    
    public static void ListarDirectoriosEnArchivoDeTexto(File dir) throws FileNotFoundException, IOException{
    	String filename = ListarArbolDirectorios.filename;
    	FileWriter archivoDeTexto = null;
        PrintWriter pw = null;
        try{
        	archivoDeTexto = new FileWriter(filename,true);
	        pw = new PrintWriter(archivoDeTexto);
	    	
	        File listFile[] = dir.listFiles();
	        if (listFile != null) {
	            for (int i = 0; i < listFile.length; i++) {
	                if (listFile[i].isDirectory()) {
	                	ListarDirectoriosEnArchivoDeTexto(listFile[i]);
	                } else {
	                	pw.println(listFile[i].getPath());
	                    //System.out.println(listFile[i].getPath());
	                }
	            }
	        }
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally{
	    	try {
	            // Nuevamente aprovechamos el finally para 
	            // asegurarnos que se cierra el fichero.
	            if (null != archivoDeTexto)
	            	archivoDeTexto.close();
	            } catch (Exception e2) {
	               e2.printStackTrace();
	            }
        }
//        Timer timer = new Timer(true);
//    	timer.schedule(new ListarArbolDirectorios(), 0, 5000);
    }
    
    public static void ListarUltimoDirectorioEnArchivoDeTexto(File[] listainicial,File modificada) throws FileNotFoundException, IOException{
    	Boolean valor =false;
    	String filename = ListarArbolDirectorios.filename;
    	FileWriter archivoDeTexto = null;
        PrintWriter pw = null;
        try{
        	archivoDeTexto = new FileWriter(filename,true);
	        pw = new PrintWriter(archivoDeTexto);
	    	
	        File listFile[] = modificada.listFiles();
	        if (listFile != null) {
	            for (int i = 0; i < listFile.length; i++) {
	            	for (int j = 0; j < listainicial.length; i++) {
		                if (listFile[i]==listainicial[j]) {
		                	valor = true;
		                	continue;
		                    //System.out.println(listFile[i].getPath());
		                }
	            	}
	            	if(valor== false){
	            		pw.println(listFile[i].getPath());
	            	}
	            }
	        }
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally{
	    	try {
	            // Nuevamente aprovechamos el finally para 
	            // asegurarnos que se cierra el fichero.
	            if (null != archivoDeTexto)
	            	archivoDeTexto.close();
	            } catch (Exception e2) {
	               e2.printStackTrace();
	            }
        }
//        Timer timer = new Timer(true);
//    	timer.schedule(new ListarArbolDirectorios(), 0, 5000);
    }
    
    
    public static void DetectarNuevosArchivosEnDirectorio()throws IOException{
    	Path dir = Paths.get(ListarArbolDirectorios.path);
        WatchService service = FileSystems.getDefault().newWatchService();
        WatchKey key = dir.register(service, ENTRY_CREATE);
        ListarDirectoriosEnArchivoDeTexto(new File(ListarArbolDirectorios.path));
        File list1[] = new File(ListarArbolDirectorios.path).listFiles();
        System.out.println("Watching directory: "+dir.toString());
        for(;;){
            WatchKey key1;
            try {
                key1 = service.take();
            } catch (InterruptedException x) {
                break;
            }
            for (WatchEvent<?> event: key1.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                if (kind == OVERFLOW) {
                    continue;
                }
                WatchEvent<Path> ev = (WatchEvent<Path>)event;
                Path filename = ev.context();
                Path child = dir.resolve(filename);
                System.out.println("New file: "+child.toString()+" created.");
                sendMail(child.toString());
                try{
                    FileInputStream in = new FileInputStream(child.toFile());
//                    System.out.println("File opened for reading");
                    in.close();
//                    System.out.println("File Closed");
                }catch(Exception x){
                    x.printStackTrace();
                }
            }
            ListarUltimoDirectorioEnArchivoDeTexto(list1,new File(ListarArbolDirectorios.path));
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }
    
    public static void checkAndNotify() {
    	
    }
    
    public static boolean compareFiles() throws FileNotFoundException, IOException {
    	String originalFilename = ListarArbolDirectorios.filename;
    	BufferedReader reader1 = new BufferedReader(new FileReader(originalFilename));
		BufferedReader reader2 = new BufferedReader(new FileReader("temp.txt"));
		String line1 = reader1.readLine();
		String line2 = reader2.readLine();
		boolean areEqual = true;
		int lineNum = 1;
		while (line1 != null || line2 != null)
		{
			if(line1 == null || line2 == null)
			{
				areEqual = false;
				break;
			}
			else if(! line1.equalsIgnoreCase(line2))
			{
				areEqual = false;
				break;
			}
			line1 = reader1.readLine();
			line2 = reader2.readLine();
			lineNum++;
		}
		reader1.close();
		reader2.close();
		if(areEqual)
		{
			System.out.println("Two files have same content.");
			return true;
		}
//		else
//		{
//			System.out.println("Two files have different content. They differ at line "+lineNum);
//			System.out.println("File1 has "+line1+" and File2 has "+line2+" at line "+lineNum);
//		}
		return false;
    }
    
    public static void sendMail(String newPath) {
    	 final String username = "jpabadlz@gmail.com";
        final String password = "jnalpablo13";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("your_user_name@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("to_email_address@domain.com"));
            message.setSubject("Practica Sistemas Operativos");
            message.setText(newPath);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    //https://loquemeinteresadelared.wordpress.com/2016/02/15/como-detectar-si-un-fichero-ha-sido-creado-modificado-o-borrado-utilizando-java/
    //https://stackoverflow.com/questions/13998379/directory-watching-for-changes-in-java
    //https://docs.oracle.com/javase/tutorial/essential/io/notification.html
    //https://docs.oracle.com/javase/tutorial/essential/io/examples/WatchDir.java
    public static void ListarMientrasDetectaNuevosArchivosEnDirectorio(File directorio) throws FileNotFoundException, IOException{
    	Path dir = Paths.get("F:/prueba/");
        WatchService service = FileSystems.getDefault().newWatchService();
        WatchKey key = dir.register(service, ENTRY_CREATE);

        System.out.println("Watching directory: "+dir.toString());
        for(;;){
            WatchKey key1;
            try {
                key1 = service.take();
            } catch (InterruptedException x) {
                break;
            }

            for (WatchEvent<?> event: key1.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                if (kind == OVERFLOW) {
                    continue;
                }

                WatchEvent<Path> ev = (WatchEvent<Path>)event;
                Path filename = ev.context();
                Path child = dir.resolve(filename);
                System.out.println("New file: "+child.toString()+" created.");
                try{
                    FileInputStream in = new FileInputStream(child.toFile());
                    System.out.println("File opened for reading");
                    in.close();
                    System.out.println("File Closed");
                }catch(Exception x){
                    x.printStackTrace();
                }
            }

            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }

    }
}

