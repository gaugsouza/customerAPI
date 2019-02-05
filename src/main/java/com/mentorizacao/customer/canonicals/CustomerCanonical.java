package com.mentorizacao.customer.canonicals;

import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * © Copyright Beta IT 2019<br>
 *
 * The <code>CustomerCanonical</code> class is the response regarding Customer.
 *
 * @author gaugusto@betait.com.br
 * @version 1.0
 * @since JDK1.8
 *
 * @see AllArgsConstructor
 * @see Builder
 * @see Data
 * @see NoArgsConstructor
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CustomerCanonical extends Canonical {
    @ApiModelProperty(notes = "The customer's user name and its primary key")
    private String userName = null;

    @ApiModelProperty(notes = "The auto-generated version of the customer")
    private Integer version  = null;

    @ApiModelProperty(notes = "The customer's first name")
    private String firstName = null;

    @ApiModelProperty(notes = "The customer's last name")
    private String lastName = null;

    @ApiModelProperty(notes = "The customer's address")
    private String address = null;

    @ApiModelProperty(notes = "The customer's city")
    private String city = null;

    @ApiModelProperty(notes = "The customer's state")
    private String state = null;

    @ApiModelProperty(notes = "The customer's zip code")
    private String zipCode = null;

    @ApiModelProperty(notes = "The customer's password encrypted")
    private String encryptedPassword= null;

    @ApiModelProperty(notes = "The creation date of the customer")
    private Date dateCreated = null;

    @ApiModelProperty(notes = "The last updated date of the customer")
    private Date lastUpdated = null;
}

