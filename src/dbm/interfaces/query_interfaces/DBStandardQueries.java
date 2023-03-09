package dbm.interfaces.query_interfaces;

/**
 * This interface is acting as a standard skeleton for DB queries regarding - create, edit, delete, show etc. concept.
 * @implNote {@link #showTable()} mirrors the intention of SELECT * FROM query within MySQL <br>
 * {@link #showTableOrdered()} mirrors the intention of SELECT * FROM ORDERED BY query within MySQL <br>
 * {@link #insertToTable()} mirrors the intention of creating / adding new rows INSERT TO query within MySQL <br>
 * {@link #updateTable()} mirrors the intention of editing / updating a row data set UPDATE SET query within MySQL <br>
 * {@link #deleteFromTable()} mirrors the intention of deleting a row set DELETE query within MySQL <br>
 * {@link #alterTable()} mirrors the intention of altering / adding column ALTER TABLE query within MySQL <br>
 */

public interface DBStandardQueries {

    void showTable();

    void showTableOrdered();

    void insertToTable();

    void updateTable();

    void deleteFromTable();

    void alterTable();

}