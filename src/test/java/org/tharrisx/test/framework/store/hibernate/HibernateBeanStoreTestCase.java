package org.tharrisx.test.framework.store.hibernate;

import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import junit.framework.TestCase;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.tharrisx.framework.store.BeanStoreFactory;
import org.tharrisx.framework.store.StorableBean;
import org.tharrisx.framework.store.hibernate.HibernateBeanStoreFactory;
import org.tharrisx.frameworkexample.beans.User;
import org.tharrisx.frameworkexample.beans.UserEvent;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Work in progress...
 * 
 * @author tharrisx
 * @since 1.0.0
 * @version 1.0.0
 */
public class HibernateBeanStoreTestCase extends TestCase {

  private BeanStoreFactory beanStoreFactory = null;

  protected BeanStoreFactory getBeanStoreFactory() {
    return this.beanStoreFactory;
  }

  protected void setBeanStoreFactory(BeanStoreFactory arg) {
    this.beanStoreFactory = arg;
  }

  @Test
  public void test() throws Exception {
    getBeanStoreFactory().getBeanStore(User.class);
  }

  @Override
  @Before
  protected void setUp() throws Exception {

    // Construct DataSource
    ComboPooledDataSource dataSource = new ComboPooledDataSource();
    dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/restdb");
    dataSource.setUser("tharris");
    dataSource.setPassword("QazWsx4433");

    // Create initial context
    System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
    System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
    InitialContext ic = new InitialContext();
    ic.createSubcontext("java:");
    ic.createSubcontext("java:/comp");
    ic.createSubcontext("java:/comp/env");
    ic.createSubcontext("java:/comp/env/jdbc");
    ic.bind("java:/comp/env/jdbc/restdb", dataSource);

    List<Class<? extends StorableBean>> beanClasses = new LinkedList<>();
    beanClasses.add(User.class);
    beanClasses.add(UserEvent.class);

    Configuration configuration = getHibernateConfiguration();
    ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder().applySettings(configuration.getProperties());
    SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistryBuilder.buildServiceRegistry());
    setBeanStoreFactory(new HibernateBeanStoreFactory(beanClasses, sessionFactory));

  }

  @Override
  protected void tearDown() throws Exception {
    // nothing
  }

  private Configuration getHibernateConfiguration() {
    return new Configuration()

        .addPackage("org.tharrisx.frameworkexample.beans.user")
        .addAnnotatedClass(User.class)

        .addPackage("org.tharrisx.frameworkexample.beans.userevent")
        .addAnnotatedClass(UserEvent.class)

        .setProperty("hibernate.connection.datasource", "java:/comp/env/jdbc/restdb")
        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect")
        .setProperty("hibernate.default_entity_mode", "pojo")
        .setProperty("hibernate.max_fetch_depth", "3")
        .setProperty("hibernate.default_batch_fetch_size", "16")
        .setProperty("hibernate.order_updates", "true")
        .setProperty("hibernate.use_identifer_rollback", "true")
        .setProperty("hibernate.connection.isolation", "2")
        .setProperty("hibernate.query.substitutions", "true=1, false=0")
        .setProperty("hibernate.bytecode.use_reflection_optimizer", "true")

        // debugging settings
        .setProperty("hibernate.show_sql", "false")
        .setProperty("hibernate.use_sql_comments", "false")
        .setProperty("hibernate.format_sql", "false")

        .setProperty("hibernate.cache.use_second_level_cache", "false")
        .setProperty("hibernate.cache.use_query_cache", "false")
        .setProperty("hibernate.cache.use_structured_entries", "false")
        .setProperty("hibernate.generate_statistics", "false");
  }
}
