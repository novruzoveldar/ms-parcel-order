package com.guavapay.model.entity;

import com.guavapay.model.converter.AtomicIntegerConverter;
import com.guavapay.model.type.AccountState;
import com.guavapay.model.type.GenderType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", sequenceName = "seq_account", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "create_date", updatable = false, nullable = false)
    private Date createDate;

    private Date accessDate;

    private Date attemptDate;

    @Column(name = "attempts", columnDefinition = "int2 default 0")
    @Convert(converter = AtomicIntegerConverter.class)
    private AtomicInteger attempt;

    @Column(nullable = false, unique = true, columnDefinition = "CHAR(36)")
    private String registrationToken;

    @Column(unique = true)
    private String mobile;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private AccountState state;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private Date birthday;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

}
