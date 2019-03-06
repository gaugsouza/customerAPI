package com.mentorizacao.customer.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.*;

import java.io.Serializable;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * © Copyright Beta IT 2019<br>
 *
 * The <code>Customer</code> class is POJO (Plain Old Java Object) for a set/get of customer (table) from H2 database.
 *
 * @author gaugusto@betait.com.br
 * @version 1.0
 * @since JDK1.8
 *
 * @see AllArgsConstructor
 * @see Builder
 * @see Data
 * @see Entity
 * @see NoArgsConstructor
 */
@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
public class Customer implements Serializable {
    private static final long serialVersionUID = -5446424219885333861L;

    /** The customer's user name and its primary key*/
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id = null;

    @NotNull(message = "The username can't be null.")
    @Length(min = 3, max = 12, message = "The username must have between 3 and 12 characters.")
    private String userName = null;

    /** The auto-generated version of the customer*/
    @Version
    private Integer version  = null;

    /** The actual state of customer: true = Active; false = Inactive. (Logical deletion)*/
    private boolean active = false;

    /** The customer's first name */
    @NotNull(message = "The first name can't be null.")
    private String firstName = null;

    /** The customer's last name */
    @NotNull(message = "The last name can't be null.")
    private String lastName = null;

    /** The customer's address */
    private String address = null;

    /** The customer's city */
    private String city = null;

    /** The customer's state */
    private String state = null;

    /** The customer's zip code*/
    @NotNull(message = "The zip code can't be null.")
    @Length(min = 8, max = 8, message = "The CEP must have 8 digits.")
    private String zipCode = null;

    /** The customer's password encrypted */
    @NotNull(message = "The password can't be null.")
    @Length(min = 6, message = "The password must have at least 6 characters.")
    private String encryptedPassword= null;

    /** The creation date of the customer */
    private Date dateCreated = null;

    /** The last updated date of the customer */
    private Date lastUpdated = null;
}
