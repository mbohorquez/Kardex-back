package com.example.kardex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class DemoApplication implements CommandLineRunner{
	
	@Autowired
	private JdbcTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		template.execute("DROP TABLE PRODUCTO IF EXISTS");
		template.execute("DROP TABLE PEDIDO IF EXISTS");
		template.execute("DROP TABLE USUARIO IF EXISTS");
		template.execute("DROP TABLE FACTURA IF EXISTS");
		template.execute("DROP TABLE FACTURA_DET IF EXISTS");
		template.execute("DROP TABLE PEDIDO IF EXISTS");
		template.execute("DROP TABLE PEDIDO_DET IF EXISTS");
		template.execute("DROP TABLE KARDEX IF EXISTS");
		
		template.execute("CREATE TABLE PRODUCTO(ID INT PRIMARY KEY AUTO_INCREMENT, NOMBRE VARCHAR(255) NOT NULL, MINIMO INT NOT NULL,"
						+ " MAXIMO INT NOT NULL, ESTADO INT NOT NULL DEFAULT 1, CANTIDAD INT NOT NULL DEFAULT 0, VALOR INT NOT NULL DEFAULT 0 )");
		
		template.execute("CREATE TABLE PEDIDO(ID INT PRIMARY KEY AUTO_INCREMENT, CODIGO_FACTURA VARCHAR(255) NOT NULL,"
						+ " VALOR INT NOT NULL, FECHA DATE NOT NULL)");
		template.execute("CREATE TABLE PEDIDO_DET(SECUENCIA INT PRIMARY KEY AUTO_INCREMENT, ID_PEDIDO INT NOT NULL, FOREIGN KEY (ID_PEDIDO ) REFERENCES PEDIDO(ID), VALOR_UNITARIO INT NOT NULL, VALOR_TOTAL INT NOT NULL, ID_PRODUCTO INT NOT NULL,FOREIGN KEY (ID_PRODUCTO ) REFERENCES PRODUCTO(ID),"
				+ " CANTIDAD INT NOT NULL )");
		
		template.execute("CREATE TABLE USUARIO(ID INT PRIMARY KEY AUTO_INCREMENT, NOMBRE VARCHAR(255) NOT NULL, USUARIO  VARCHAR(255) NOT NULL, PASSWORD VARCHAR(255) NOT NULL, TARJETA_CREDITO VARCHAR(255) NOT NULL)");
		template.update("insert into USUARIO(NOMBRE,USUARIO,PASSWORD,TARJETA_CREDITO) values('MIGUEL BOHORQUEZ','admin','"+ new BCryptPasswordEncoder().encode("admin") +"','323223223232323')");
				
		template.execute("CREATE TABLE MEDIODEPAGO(ID INT PRIMARY KEY AUTO_INCREMENT, MEDIO VARCHAR(255) NOT NULL)");
		template.update("insert into MEDIODEPAGO(MEDIO) values('EFECTIVO')");
		template.update("insert into MEDIODEPAGO(MEDIO) values('TARJETA DE CREDITO')");
		
		
		template.execute("CREATE TABLE FACTURA(ID INT PRIMARY KEY AUTO_INCREMENT, VALOR INT NOT NULL, ID_MEDIODEPAGO  INT NOT NULL, FOREIGN KEY (ID_MEDIODEPAGO ) REFERENCES MEDIODEPAGO(ID),"
				+ " FECHA DATE NOT NULL, ID_USUARIO  INT NOT NULL, FOREIGN KEY (ID_USUARIO ) REFERENCES USUARIO(ID) )");
		
		template.execute("CREATE TABLE FACTURA_DET(SECUENCIA INT PRIMARY KEY AUTO_INCREMENT, ID_FACTURA INT NOT NULL, FOREIGN KEY (ID_FACTURA ) REFERENCES FACTURA(ID), VALOR_UNITARIO INT NOT NULL, VALOR_TOTAL INT NOT NULL, ID_PRODUCTO INT NOT NULL,FOREIGN KEY (ID_PRODUCTO ) REFERENCES PRODUCTO(ID),"
				+ " CANTIDAD INT NOT NULL )");
		
		template.execute("CREATE TABLE KARDEX(SECUENCIA INT PRIMARY KEY AUTO_INCREMENT, FECHA DATE NOT NULL, VALOR_UNITARIO INT NOT NULL DEFAULT 0, ENTRADA_CANTIDAD INT NULL DEFAULT 0, "
				+ "ENTRADA_VALOR INT NULL DEFAULT 0, SALIDA_CANTIDAD INT NULL DEFAULT 0, SALIDA_VALOR INT NULL DEFAULT 0, SALDO_VALOR INT NULL DEFAULT 0, "
				+ "SALDO_CANTIDAD INT NULL DEFAULT 0, ID_FACTURA INT NULL DEFAULT 0, ID_PEDIDO INT NULL DEFAULT 0, ID_PRODUCTO INT NOT NULL, "
				+ "FOREIGN KEY (ID_PRODUCTO ) REFERENCES PRODUCTO(ID) )");
		
		for (int i = 0; i < 3; i++) {
			template.update("insert into PRODUCTO(NOMBRE,MINIMO,MAXIMO) values('Producto 00"+ i +"',"+ i +","+ i +")");
		}
		
		
		
	}

}
