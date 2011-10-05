/*
 * Copyright (c) 2011 Mysema Ltd.
 * All rights reserved.
 *
 */
package com.mysema.query.support;

import javax.annotation.Nullable;

import com.mysema.query.QueryMetadata;
import com.mysema.query.types.ConstantImpl;
import com.mysema.query.types.Expression;
import com.mysema.query.types.Operator;
import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadataFactory;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.expr.BooleanOperation;
import com.mysema.query.types.expr.CaseBuilder;
import com.mysema.query.types.expr.ComparableExpression;
import com.mysema.query.types.expr.ComparableOperation;
import com.mysema.query.types.expr.DateExpression;
import com.mysema.query.types.expr.DateOperation;
import com.mysema.query.types.expr.DateTimeExpression;
import com.mysema.query.types.expr.DateTimeOperation;
import com.mysema.query.types.expr.NumberExpression;
import com.mysema.query.types.expr.NumberOperation;
import com.mysema.query.types.expr.SimpleExpression;
import com.mysema.query.types.expr.SimpleOperation;
import com.mysema.query.types.expr.StringExpression;
import com.mysema.query.types.expr.StringOperation;
import com.mysema.query.types.expr.TimeExpression;
import com.mysema.query.types.expr.TimeOperation;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.ComparablePath;
import com.mysema.query.types.path.DatePath;
import com.mysema.query.types.path.DateTimePath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.SimplePath;
import com.mysema.query.types.path.StringPath;
import com.mysema.query.types.path.TimePath;
import com.mysema.query.types.query.SimpleSubQuery;
import com.mysema.query.types.template.BooleanTemplate;
import com.mysema.query.types.template.ComparableTemplate;
import com.mysema.query.types.template.NumberTemplate;
import com.mysema.query.types.template.SimpleTemplate;
import com.mysema.query.types.template.StringTemplate;

/**
 * Expression factory class
 * 
 * @author tiwe
 *
 */
public final class Expressions {

    @Nullable
    public static BooleanExpression allOf(BooleanExpression... exprs){
        return BooleanExpression.allOf(exprs);
    }

    @Nullable
    public static BooleanExpression anyOf(BooleanExpression... exprs){
        return BooleanExpression.anyOf(exprs);
    }
        
    public static <T> Expression<T> constant(T value) {
        return new ConstantImpl<T>(value);    
    }
    
    public static <T> SimpleExpression<T> template(Class<T> cl, String template, Expression<?>... args) {
        return SimpleTemplate.create(cl, template, args);
    }
    
    public static <T extends Comparable<?>> ComparableExpression<T> comparableTemplate(Class<T> cl, String template, Expression<?>... args) {
        return ComparableTemplate.create(cl, template, args);
    }
    
    public static <T extends Number & Comparable<?>> NumberExpression<T> numberTemplate(Class<T> cl, String template, Expression<?>... args) {
        return NumberTemplate.create(cl, template, args);
    }
    
    public static StringExpression stringTemplate(String template, Expression<?>... args) {
        return StringTemplate.create(template, args);
    }
    
    public static BooleanExpression booleanTemplate(String template, Expression<?>... args) {
        return BooleanTemplate.create(template, args);
    }
    
    public static <T> SimpleExpression<T> subQuery(Class<T> type, QueryMetadata metadata) {
        return new SimpleSubQuery<T>(type, metadata);
    }
    
    public static BooleanExpression predicate(Operator<Boolean> operation, Expression<?>... args) {
	return BooleanOperation.create(operation, args);
    }

    public static <T> SimpleExpression<T> operation(Class<T> type, Operator<? super T> operator, Expression<?>... args) {
        return SimpleOperation.create(type, operator, args);
    }
    
    public static BooleanExpression booleanOperation(Operator<Boolean> operation, Expression<?>... args) {
        return predicate(operation, args);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<?>> ComparableExpression<T> comparableOperation(Class<T> type, Operator<? super T> operator, Expression<?>... args) {
        return ComparableOperation.create(type, operator, args);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<?>> DateExpression<T> dateOperation(Class<T> type, Operator<? super T> operator, Expression<?>... args) {
        return DateOperation.create(type, operator, args);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<?>> DateTimeExpression<T> dateTimeOperation(Class<T> type, Operator<? super T> operator, Expression<?>... args) {
        return DateTimeOperation.create(type, operator, args);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<?>> TimeExpression<T> timeOperation(Class<T> type, Operator<? super T> operator, Expression<?>... args) {
        return TimeOperation.create(type, operator, args);
    }    
    
    @SuppressWarnings("unchecked")
    public static <T extends Number & Comparable<?>> NumberExpression<T> numberOperation(Class<T> type, Operator<? super T> operator, Expression<?>... args) {
        return NumberOperation.create(type, operator, args);
    }
    
    public static StringExpression stringOperation(Operator<? super String> operator, Expression<?>... args) {
        return StringOperation.create(operator, args);
    }    

    public static <T> SimplePath<T> path(Class<T> type, String variable) {
        return new SimplePath<T>(type, PathMetadataFactory.forVariable(variable));
    }

    public static <T> SimplePath<T> path(Class<T> type, Path<?> parent, String property) {
        return new SimplePath<T>(type, PathMetadataFactory.forProperty(parent, property));
    }    
    
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<?>> ComparablePath<T> comparablePath(Class<T> type, String variable) {
        return new ComparablePath<T>(type, PathMetadataFactory.forVariable(variable));
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<?>> ComparablePath<T> comparablePath(Class<T> type, Path<?> parent, String property) {
        return new ComparablePath<T>(type, PathMetadataFactory.forProperty(parent, property));
    }  
    
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<?>> DatePath<T> datePath(Class<T> type, String variable) {
        return new DatePath<T>(type, PathMetadataFactory.forVariable(variable));
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<?>> DatePath<T> datePath(Class<T> type, Path<?> parent, String property) {
        return new DatePath<T>(type, PathMetadataFactory.forProperty(parent, property));
    }  
    
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<?>> DateTimePath<T> dateTimePath(Class<T> type, String variable) {
        return new DateTimePath<T>(type, PathMetadataFactory.forVariable(variable));
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<?>> DateTimePath<T> dateTimePath(Class<T> type, Path<?> parent, String property) {
        return new DateTimePath<T>(type, PathMetadataFactory.forProperty(parent, property));
    }  
    
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<?>> TimePath<T> timePath(Class<T> type, String variable) {
        return new TimePath<T>(type, PathMetadataFactory.forVariable(variable));
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<?>> TimePath<T> timePath(Class<T> type, Path<?> parent, String property) {
        return new TimePath<T>(type, PathMetadataFactory.forProperty(parent, property));
    }  
    
    public static <T extends Number & Comparable<?>> NumberPath<T> numberPath(Class<T> type, String variable) {
        return new NumberPath<T>(type, PathMetadataFactory.forVariable(variable));
    }

    public static <T extends Number & Comparable<?>> NumberPath<T> numberPath(Class<T> type, Path<?> parent, String property) {
        return new NumberPath<T>(type, PathMetadataFactory.forProperty(parent, property));
    }   
    
    public static StringPath stringPath(String variable) {
        return new StringPath(PathMetadataFactory.forVariable(variable));
    }

    public static StringPath stringPath(Path<?> parent, String property) {
        return new StringPath(PathMetadataFactory.forProperty(parent, property));
    }    
    
    public static BooleanPath booleanPath(String variable) {
        return new BooleanPath(PathMetadataFactory.forVariable(variable));
    }

    public static BooleanPath booleanPath(Path<?> parent, String property) {
        return new BooleanPath(PathMetadataFactory.forProperty(parent, property));
    }    
    
    public static CaseBuilder cases() {
        return new CaseBuilder();
    }
        
    private Expressions(){}

}
