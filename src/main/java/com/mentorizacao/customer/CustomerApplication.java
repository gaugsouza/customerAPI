package com.mentorizacao.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * © Copyright Beta IT 2019<br>
 *
 * The <code>CustomerApplication</code> class is a kind of starter of the web application (boot), in this case, it
 * is the Customer Project as back-end.
 *
 * @author gaugusto@betait.com.br
 * @version 1.0
 * @since JDK1.8
 *
 * @see SpringApplication
 * @see SpringBootApplication
 */

@SpringBootApplication
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

}

