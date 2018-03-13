package dev.chernykh.cellular.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.CurrencyType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;
import org.javamoney.moneta.Money;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoneyType implements UserType {
    @Override
    public int[] sqlTypes() {
        return new int[]{
            DoubleType.INSTANCE.sqlType(),
            CurrencyType.INSTANCE.sqlType()
        };
    }
    
    @Override
    public Class returnedClass() {
        return Money.class;
    }
    
    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return ObjectUtils.nullSafeEquals(x, y);
    }
    
    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }
    
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
        throws HibernateException, SQLException {
        assert names.length == 2;
        double amount = (double) DoubleType.INSTANCE.get(rs, names[0], session);
        String currency = (String) StringType.INSTANCE.get(rs, names[1], session);
        return Money.of(amount, currency);
    }
    
    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
        throws HibernateException, SQLException {
        if (value == null) {
            DoubleType.INSTANCE.set(st, null, index, session);
            StringType.INSTANCE.set(st, null, index + 1, session);
        } else {
            final Money money = (Money) value;
            DoubleType.INSTANCE.set(st, money.getNumber().doubleValue(), index, session);
            StringType.INSTANCE.set(st, money.getCurrency().getCurrencyCode(), index + 1, session);
        }
    }
    
    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }
    
    @Override
    public boolean isMutable() {
        return true;
    }
    
    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }
    
    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }
    
    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }
}
