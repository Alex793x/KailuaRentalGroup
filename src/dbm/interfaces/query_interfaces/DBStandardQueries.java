package dbm.interfaces.query_interfaces;

import dbm.DB_Dependencies;
import dbm.handler.DB_QueryEditingHandler;
import dbm.handler.DB_QueryRequestHandler;
import utility.UI;

/**
 * This interface is acting as a standard skeleton for DB queries regarding - create, edit, delete, show etc. concept.
 * @implNote {@link #showTable(DB_QueryRequestHandler, DB_Dependencies)} mirrors the intention of SELECT * FROM query within MySQL <br>
 * {@link #showTableOrdered(DB_QueryRequestHandler, DB_Dependencies)} mirrors the intention of SELECT * FROM ORDERED BY query within MySQL <br>
 * {@link #insertToTable(DB_QueryEditingHandler,DB_QueryRequestHandler, UI, DB_Dependencies)} mirrors the intention of creating / adding new rows INSERT TO query within MySQL <br>
 * {@link #updateTable(DB_QueryEditingHandler, DB_QueryRequestHandler, UI, DB_Dependencies)} mirrors the intention of editing / updating a row data set UPDATE SET query within MySQL <br>
 * {@link #deleteFromTable(DB_QueryEditingHandler, DB_QueryRequestHandler, UI, DB_Dependencies)} mirrors the intention of deleting a row set DELETE query within MySQL <br>
 */

public interface DBStandardQueries {

    void showTable(DB_QueryRequestHandler requestHandler, DB_Dependencies db_dependencies);

    void showTableOrdered(DB_QueryRequestHandler requestHandler, DB_Dependencies db_dependencies);

    void insertToTable(DB_QueryEditingHandler editingHandler, DB_QueryRequestHandler requestHandler, UI ui, DB_Dependencies db_dependencies);

    void updateTable(DB_QueryEditingHandler editingHandler,  DB_QueryRequestHandler requestHandler, UI ui, DB_Dependencies db_dependencies);

    void deleteFromTable(DB_QueryEditingHandler editingHandler,  DB_QueryRequestHandler requestHandler, UI ui, DB_Dependencies db_dependencies);

}