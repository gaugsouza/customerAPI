package com.mentorizacao.customer.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import java.io.Serializable;

import java.util.Date;

/**
 * © Copyright Beta IT 2019<br>
 *
 * The <code>Customer</code> class is POJO (Plain Old Java Object) for a set/get of movie (table) from H2 database.
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
    private String userName = null;

    /** The auto-generated version of the customer*/
    @Version
    private Integer version  = null;

    /** The actual state of customer: true = Active; false = Inactive. (Logical deletion)*/
    private boolean active = false;

    /** The customer's first name */
    private String firstName = null;

    /** The customer's last name */
    private String lastName = null;

    /** The customer's address */
    private String address = null;

    /** The customer's city */
    private String city = null;

    /** The customer's state */
    private String state = null;

    /** The customer's zip code*/
    private String zipCode = null;

    /** The customer's password encrypted */
    private String encryptedPassword= null;

    /** The creation date of the customer */
    private Date dateCreated = null;

    /** The last updated date of the customer */
    private Date lastUpdated = null;
}
