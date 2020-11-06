package com.xxxx.crm.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 事务的原理是基于 AOP 切面
 *      数据源
 *      new 事务管理器(数据源)
 *      事务管理器规则
 *
 *      织入
 *      AOP配置切点切面
 */
@Configuration
public class TransactionConfig {

    //拦截面
    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.xxxx.crm.service..*.*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice(){
        /**
         * 事务传播行为
         *
         * TransactionDefinition.PROPAGATION_REQUIRED;
         *      支持当前事务,如果不存在,就新建一个事务(默认)
         * TransactionDefinition.PROPAGATION_SUPPORTS;
         *      支持当前事务,如果不存在,就不使用事务
         * TransactionDefinition.PROPAGATION_MANDATORY;
         *      支持当前事务,如果不存在,抛出异常
         *
         * TransactionDefinition.PROPAGATION_REQUIRES_NEW;
         *      如果有事务存在,挂起当前事务,创建一个新的事务
         * TransactionDefinition.PROPAGATION_NOT_SUPPORTED;
         *      以非事务的方式运行,如果有事务存在,挂起当前事务
         * TransactionDefinition.PROPAGATION_NEVER;
         *      以非事务方式运行,如果有事务存在,抛出异常
         * TransactionDefinition.PROPAGATION_NESTED;
         *      如果当前事务存在,则嵌套事务执行
         */
        DefaultTransactionAttribute txAttrRequired = new DefaultTransactionAttribute();
        txAttrRequired.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        //事务传播行为
        DefaultTransactionAttribute txAttrRequiredReadonly = new DefaultTransactionAttribute();
        txAttrRequiredReadonly.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttrRequiredReadonly.setReadOnly(true);

        /**
         * 匹配规则
         */
        NameMatchTransactionAttributeSource attributeSource = new NameMatchTransactionAttributeSource();
        //增
        attributeSource.addTransactionalMethod("add*",txAttrRequired);
        attributeSource.addTransactionalMethod("set*",txAttrRequired);
        attributeSource.addTransactionalMethod("save*",txAttrRequired);
        attributeSource.addTransactionalMethod("post*",txAttrRequired);
        attributeSource.addTransactionalMethod("insert*", txAttrRequired);
        //删
        attributeSource.addTransactionalMethod("delete*", txAttrRequired);
        //改
        attributeSource.addTransactionalMethod("update*",txAttrRequired);
        attributeSource.addTransactionalMethod("put*",txAttrRequired);
        //查
        attributeSource.addTransactionalMethod("get*",txAttrRequiredReadonly);
        attributeSource.addTransactionalMethod("find*",txAttrRequiredReadonly);
        attributeSource.addTransactionalMethod("query*",txAttrRequiredReadonly);
        attributeSource.addTransactionalMethod("count*",txAttrRequiredReadonly);
        attributeSource.addTransactionalMethod("select*",txAttrRequiredReadonly);
        /**
         * 配置事务管理器
         *      transactionManager      事务管理器
         *      attributeSource         事务规则
         */
        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager,attributeSource);
        return txAdvice;
    }

    @Bean
    public Advisor txAdviceAdvisor(){
        //拦截切面
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);

        //将切面和拦截规则织入
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut,txAdvice());
        return advisor;
    }
}
