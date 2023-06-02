/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blackerdk.bookstore.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author DUY KHANH
 */
    @NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorDTO implements Serializable{
    private String usernameLengthError;
    private String passwordLengthError;
    private String fullNameLengthError;
    private String confirmNotMatch;
    private String usernameDuplicate;
    private String phoneLengthError;
    private String addressLengthError;
    private String phoneDupilcate; 
}
