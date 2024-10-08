import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class JPATest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Before
    public void setUp() {
        // Initialize EntityManagerFactory using the persistence unit name
        entityManagerFactory = Persistence.createEntityManagerFactory("employeeV2Unit");
        entityManager = entityManagerFactory.createEntityManager();
    }
    @Test
    public void testConnection() {
        // Check if the EntityManager is not null, meaning the connection is established
        assertNotNull(entityManager);

        // Start a transaction and commit it (even though no operations are performed)
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        transaction.commit();
    }

    @After
    public void tearDown() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
