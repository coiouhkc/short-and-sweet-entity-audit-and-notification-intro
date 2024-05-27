package me.abratuhi.demo.audit.repository;

import io.quarkus.hibernate.orm.PersistenceUnitExtension;
import lombok.extern.jbosslog.JBossLog;
import org.hibernate.CallbackException;
import org.hibernate.Interceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

@JBossLog
@PersistenceUnitExtension
public class AuditInterceptor implements Interceptor {
  public boolean onFlushDirty(
      Object entity,
      Object id,
      Object[] currentState,
      Object[] previousState,
      String[] propertyNames,
      Type[] types)
      throws CallbackException {
    log.infov("onFlushDirty");
    return false;
  }

  public boolean onSave(
      Object entity, Object id, Object[] state, String[] propertyNames, Type[] types)
      throws CallbackException {
    log.infov("onSave");
    return false;
  }

  public void onDelete(
      Object entity, Object id, Object[] state, String[] propertyNames, Type[] types)
      throws CallbackException {
    log.infov("onDelete");
  }

  public void afterTransactionCompletion(Transaction tx) {
    log.infov("Transaction status {0}", tx.getStatus());
  }
}
