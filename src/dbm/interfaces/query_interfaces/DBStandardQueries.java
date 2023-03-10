package dbm.interfaces.query_interfaces;

import dbm.handler.DB_QueryEditingHandler;
import dbm.handler.DB_QueryRequestHandler;
import utility.UI;

/**
 * This interface is acting as a standard skeleton for DB queries regarding - create, edit, delete, show etc. concept.
 * @implNote {@link #showTable(DB_QueryRequestHandler)} mirrors the intention of SELECT * FROM query within MySQL <br>
 * {@link #showTableOrdered(DB_QueryRequestHandler)} mirrors the intention of SELECT * FROM ORDERED BY query within MySQL <br>
 * {@link #insertToTable(DB_QueryEditingHandler, UI)} mirrors the intention of creating / adding new rows INSERT TO query within MySQL <br>
 * {@link #updateTable(DB_QueryEditingHandler, UI)} mirrors the intention of editing / updating a row data set UPDATE SET query within MySQL <br>
 * {@link #deleteFromTable(DB_QueryEditingHandler, UI)} mirrors the intention of deleting a row set DELETE query within MySQL <br>
 * {@link #alterTable()} mirrors the intention of altering / adding column ALTER TABLE query within MySQL <br>
 */

public interface DBStandardQueries {

    void showTable(DB_QueryRequestHandler requestHandler);

    void showTableOrdered(DB_QueryRequestHandler requestHandler);

    void insertToTable(DB_QueryEditingHandler editingHandler, DB_QueryRequestHandler requestHandler, UI ui);

    void updateTable(DB_QueryEditingHandler editingHandler, UI ui);

    void deleteFromTable(DB_QueryEditingHandler editingHandler, UI ui);

    void alterTable();

}