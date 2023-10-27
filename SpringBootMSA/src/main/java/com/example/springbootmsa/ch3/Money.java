package com.example.springbootmsa.ch3;

import java.io.Serializable;
import java.util.Currency;
import java.util.Objects;

/**
 * 불변 클래스 설계
 * - 클래스를 반드시 final로 선언한다.
 * - 클래스의 멤버 변수들을 반드시 final로 선언한다.
 * - 생성자를 직접 선언하여 기본생성자가 있지 않도록 한다.
 * - 멤버 변수에서는 setter 메서드를 만들지 말고 getter 메서드를 만들어서 사용할 수 있다.
 */

public final class Money implements Serializable {
    private final Long value;
    private final Currency currency;

    public Money(Long value, Currency currency){
        if(value == null || value < 0){
            throw new IllegalArgumentException("invalid value= " + value);
        }
        if(currency == null)
            throw new IllegalArgumentException("invalid currency");

        this.value = value;
        this.currency = currency;
    }
    public Long getValue(){
        return value;
    }
    public Currency getCurrency(){
        return currency;
    }

    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;

        Money money = Money.class.cast(obj);
        return Objects.equals(value, money.value) && Objects.equals(currency, money.currency);
    }
}
